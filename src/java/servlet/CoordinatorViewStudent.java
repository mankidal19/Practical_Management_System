/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Coordinator;
import beans.Student;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.DBUtils;
import utils.MyUtils;

/**
 *
 * @author Yong Keong
 */
@WebServlet(name = "CoordinatorViewStudent", urlPatterns = {"/CoordinatorViewStudent"})
public class CoordinatorViewStudent extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String id = (String) request.getParameter("id");
 
        Student stu = null;
        Coordinator co = null;
        
        String errorString = null;
 
        try {
            stu = DBUtils.findStudent(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
 
        
        try {
            co = DBUtils.findCoordinator(conn, stu.getCo_id());
            if(co==null){
                stu.setCo_id(null);
                out.print("co is null");
                out.print(stu.getCo_id());
            }
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
        
        //set string gender,application and status
        String gender;
        String status;
        String application;
        if(stu.getStd_gender().equals("F")){
            gender = "Female";
        }
        
        else{
            gender="Male";
        }
        
        if(stu.getStd_status().equals("A")){
            status = "Approved";
        }
        else if(stu.getStd_status().equals("P")){
            status="Pending";
        }
        else{
            status="Unavailable";
        }
        
        if(stu.getApp_id()==null){
            application="Unavailable";
        }
        else{
            application=stu.getApp_id();
        }
        
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("student", stu);
        request.setAttribute("coordinator", co);
        request.setAttribute("status", status);
        request.setAttribute("gender", gender);
        request.setAttribute("application", application);
 
        
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/coordinatorViewStudent.jsp");
            
     //     RequestDispatcher dispatcher = request.getServletContext()
     //           .getRequestDispatcher("/WEB-INF/views/adminCoordinatorListView.jsp#modal-edit");
        dispatcher.forward(request, response);
 
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
