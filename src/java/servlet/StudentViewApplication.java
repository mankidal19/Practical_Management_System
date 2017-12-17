/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

/**
 *
 * @author NURUL AIMAN
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import beans.*;
import static java.lang.System.out;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = {"/viewApplication"})
public class StudentViewApplication extends HttpServlet{

    public StudentViewApplication() {
        super();
    }
    
     @Override
     //view application page
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String id = (String) request.getParameter("id");
 
        Application app = null;
        
        String errorString = null;
 
        try {
            app = DBUtils.findApplication(conn, id);
            
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        
        
        // If no error.
        // The product does not exist to edit.
        // Redirect to productList page.
        if (errorString != null && app == null) {
            response.sendRedirect(request.getServletPath() + "/applicationList");
            return;
        }
        
       
        
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("application", app);
      
        
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/studentViewApplicationView.jsp");
            
    
        dispatcher.forward(request, response);
 
    }
}
