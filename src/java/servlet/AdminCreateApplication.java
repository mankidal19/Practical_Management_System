/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

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
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.CoordinatorFunctionUtils;
import utils.DBUtils;
import utils.MyUtils;

import utils.MyUtils;
/**
 *
 * @author NURUL AIMAN
 */@WebServlet(name = "AdminCreateApplication", urlPatterns = {"/createApplication"})

public class AdminCreateApplication extends HttpServlet {
    
     public AdminCreateApplication(){
         super();
     }
     
     // Show product creation page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        Connection conn = MyUtils.getStoredConnection(request);
        String errorString = null;
        String index=null;
        
       try {
               index = CoordinatorFunctionUtils.queryCompanyIndex(conn);
           } catch (SQLException e) {
               e.printStackTrace();
               errorString = e.getMessage();
           }

           index = index.substring(2, 5);
           // Store info in request attribute, before forward to views
           request.setAttribute("errorString", errorString);
           request.setAttribute("companyLastIndex", index);

        
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/adminAddApplicationView.jsp");
        dispatcher.forward(request, response);
    }
 
    // When the user enters the product information, and click Submit.
    // This method will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    int cid = Integer.parseInt(request.getParameter("cId"));
    String index = "AP" + String.format ("%03d", cid);
    String cName = request.getParameter("cName");
    String cAddress = request.getParameter("cAddress");
    String cContact = request.getParameter("cContactName");
    String cPhone = request.getParameter("cContactNumber");
    String cEmail = request.getParameter("cContactEmail");
    String job = request.getParameter("cJob");
    String jobtitle = request.getParameter("cJobTitle");
    
    ArrayList error  = new ArrayList();
    if(cName == null || "".equals(cName)){
        error.add("COMPANY NAME REQUIRED");
    }
    if(cAddress == null || "".equals(cAddress)){
        error.add("COMPANY ADDRESS REQUIRED");
    }
    if(cContact == null || "".equals(cContact)){
        error.add("COMPANY CONTACT PERSON REQUIRED");
    }
    if(cPhone == null || "".equals(cPhone)){
        error.add("COMPANY PHONE REQUIRED");
    }
    if(cEmail == null || "".equals(cEmail)){
        error.add("COMPANY EMAIL REQUIRED");
    }
    if(job == null || "".equals(job)){
        error.add("INCOMPLETE JOB DETAILS");
    }
    if(jobtitle == null || "".equals(jobtitle)){
        error.add("INCOMPLETE JOB DETAILS");
    }
    
    if (!error.isEmpty()) {
         RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/adminAddApplicationView.jsp");
        dispatcher.forward(request, response);   
    }else{    

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = MyUtils.getStoredConnection(request);

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO APPLICATION VALUES (?,?,?,?,?,?,?,?)");
            
            pstmt.setString(1, index);
            pstmt.setString(2, cName);
            pstmt.setString(3, cContact);
            pstmt.setString(4, cAddress);
            pstmt.setString(5, cPhone);
            pstmt.setString(6, cEmail);
            pstmt.setString(7, job);
            pstmt.setString(8, jobtitle);
           

            pstmt.executeUpdate();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        out.println("Your form has been submitted successfully!Directing you to Company List");

         response.sendRedirect(request.getContextPath() + "/applicationList");
        }
    }
}
