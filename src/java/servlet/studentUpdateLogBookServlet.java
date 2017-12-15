/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Report;
import beans.Student;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.DBUtils;
import utils.MyUtils;
import utils.StudentFunctionsUtils;

/**
 *
 * @author Nurfarahin Nadhirah
 */
@WebServlet(urlPatterns = {"/studentUpdateLogBook"})
public class studentUpdateLogBookServlet extends HttpServlet {

    public studentUpdateLogBookServlet(){
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
            report = StudentFunctionsUtils.findReport(conn, reportID);
        } catch (Exception e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        // If no error.
        // The product does not exist to edit.
        // Redirect to productList page.
        if (errorString != null && report == null) {
            response.sendRedirect(request.getServletPath() + "/studentViewLogBookList");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("report", report);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/studentUpdateLogBook.jsp");
        dispatcher.forward(request, response);
 
    }
 
    // After the user modifies the product information, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String reportID = (String) request.getParameter("id");
        String title = (String) request.getParameter("title");
        String content = (String) request.getParameter("content");
//        HttpSession session = request.getSession();
        String errorString = null;
        
        
//        try {
//            report = MyUtils.getLoginedStudent(session);
//            
//        }
//        catch (Exception e) {
//            errorString = e.getMessage();
//        }
        
        try {
            if(title != null && content != null )
            StudentFunctionsUtils.updateReport(conn, reportID, title, content);
        } catch (Exception e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/studentUpdateLogBook.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/studentViewLogBookList");
        }
    }
 
}
