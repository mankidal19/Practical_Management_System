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
 * @author NURUL AIMAN
 */
@WebServlet(urlPatterns = { "/studentDisplay" })
public class StudentDisplayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StudentDisplayServlet() {
        super();
    }
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String errorString = null;
        Student student = null;
        HttpSession session = request.getSession();
        
        
        try {
            student = MyUtils.getLoginedStudent(session);
            
        }
        catch (Exception e) {
            errorString = e.getMessage();
        }
        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("displayStudent", student);
         
       
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/studentProfile.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }   
}