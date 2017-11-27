/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import utils.MyUtils;

/**
 *
 * @author Nurfarahin Nadhirah
 */
@WebServlet(urlPatterns = {"/applyApplication"})
public class applyApplicationServlet extends HttpServlet {
  public void doGet(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    String cname = request.getParameter("cname");
    String caddress = request.getParameter("caddress");
    String ccontact = request.getParameter("ccontact");
    String sname = request.getParameter("sname");
    String semail = request.getParameter("semail");
    String joblevel = request.getParameter("joblevel");
    String jobtitle = request.getParameter("jobtitle");
    
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = MyUtils.getStoredConnection(request);
        
        PreparedStatement pstmt = conn.prepareStatement("insert into application values (?,?,?,?,?,?,?)");
        
        pstmt.setString(1, cname);
        pstmt.setString(2, caddress);
        pstmt.setString(3, ccontact);
        pstmt.setString(4, sname);
        pstmt.setString(5, semail);
        pstmt.setString(6, joblevel);
        pstmt.setString(7, jobtitle);
        
        pstmt.executeUpdate();
        
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    out.println("Your form has been submitted successfully!");
    
     RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/applyApplication.jsp");
        dispatcher.forward(request, response);
    }
  
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                doGet(request, response);
            } 
}