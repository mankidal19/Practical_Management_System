/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Report;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.StudentFunctionsUtils;
import utils.MyUtils;

/**
 *
 * @author Nurfarahin Nadhirah
 */
@WebServlet(urlPatterns = {"/studentDeleteLogBook"})
public class studentDeleteLogBookServlet extends HttpServlet {

    public studentDeleteLogBookServlet(){
        super();
    }
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String reportID = (String) request.getParameter("id");
 
        Report report = null;
 
        String errorString = null;
 
        try {
           StudentFunctionsUtils.deleteReport(conn, reportID);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        // If no error.
        // The product does not exist to edit.
        // Redirect to productList page.
        if (errorString != null && report == null) {
            // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("report", report);
            response.sendRedirect(request.getContextPath() + "/studentViewLogBookList");
            return;
        }
        
        else{
            response.sendRedirect(request.getContextPath() + "/studentViewLogBookList");
        }
 
        
 
        //RequestDispatcher dispatcher = request.getServletContext()
               // .getRequestDispatcher("/WEB-INF/views/adminDeleteCoordinatorView.jsp");
        //dispatcher.forward(request, response);
        //response.sendRedirect(request.getServletPath() + "/coordinatorList");
        //doGet(request, response);
 
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}