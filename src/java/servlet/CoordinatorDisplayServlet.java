/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.*;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.*;

/**
 *
 * @author Nurfarahin Nadhirah
 */
@WebServlet(name = "CoordinatorDisplayServlet", urlPatterns = {"/coordinatorDisplay"})
public class CoordinatorDisplayServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CoordinatorDisplayServlet() {
        super();
    }
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String errorString = null;
        Coordinator coordinator = null;
        HttpSession session = request.getSession();
        
        
        try {
            coordinator = MyUtils.getLoginedCoordinator(session);
            
        }
        catch (Exception e) {
            errorString = e.getMessage();
        }
        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("displayCoordinator", coordinator);
         
       
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/coordinatorProfile.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }   

    

}
