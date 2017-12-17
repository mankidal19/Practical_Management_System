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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import beans.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import utils.DBUtils;
import utils.MyUtils;
 
@WebServlet(urlPatterns = { "/deleteApplication" })

public class AdminDeleteApplication extends HttpServlet{

    public AdminDeleteApplication() {
        super();
    }
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String appId = (String) request.getParameter("id");
 
        Application app = null;
 
        String errorString = null;
        
        
        
        try {
           DBUtils.deleteApplication(conn, appId);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        // If no error.
        // The product does not exist to edit.
        // Redirect to productList page.
        if (errorString != null && app == null) {
            // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("company", app);
            response.sendRedirect(request.getContextPath() + "/applicationList");
            return;
        }
        
        else{
            response.sendRedirect(request.getContextPath() + "/applicationList");
        }
 
        
 
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
