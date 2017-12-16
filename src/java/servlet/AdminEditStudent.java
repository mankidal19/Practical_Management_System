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
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;
import utils.MyUtils;
 
@WebServlet(urlPatterns = { "/editStudent" })
public class AdminEditStudent  extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    public AdminEditStudent(){
        super();
    }
    
     // Show view student page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String id = (String) request.getParameter("id");
 
        Student stu = null;
 
        String errorString = null;
 
        try {
            stu = DBUtils.findStudent(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        // If no error.
        // The product does not exist to edit.
        // Redirect to productList page.
        if (errorString != null && stu == null) {
            response.sendRedirect(request.getServletPath() + "/studentList");
            return;
        }
 
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("student", stu);
 
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/adminEditStudentView.jsp");
            
     //     RequestDispatcher dispatcher = request.getServletContext()
     //           .getRequestDispatcher("/WEB-INF/views/adminCoordinatorListView.jsp#modal-edit");
        dispatcher.forward(request, response);
 
    }
 
    // After the user modifies the product information, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String id = (String) request.getParameter("std_id");
        String name = (String) request.getParameter("name");
        String gender = (String) request.getParameter("gender");
        String contact = (String) request.getParameter("contact");
        String email = (String) request.getParameter("email");
        String matric = (String) request.getParameter("matric");
        String course = (String) request.getParameter("course");
        float cgpa = 0;
        String coId = (String) request.getParameter("coID");
        String yearStr =(String) request.getParameter("year");
        int year=0;
        
        String errorString = null;
        
        String cgpaStr = (String) request.getParameter("cgpa");
        
        try {
            cgpa = Float.parseFloat(cgpaStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        
        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        
       //checks for valid coordinator ID
        try {
            if(DBUtils.findCoordinator(conn, coId)==null){
                errorString = "Invalid Coordinator ID!";
            }
        } catch (SQLException ex) {
            errorString = ex.getMessage();
        }
        
        //checks for valid email
        if(!MyUtils.isValidEmailAddress(email)){
            errorString = "Invalid e-mail format!";
        }
        Student stu = new Student(id,name,gender,contact,email,matric,course,cgpa,coId,year);
 
        try {
            DBUtils.updateStudent(conn, stu);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("student", stu);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/adminEditStudentView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/studentList");
        }
    }
    
}
