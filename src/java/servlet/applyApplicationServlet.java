/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Application;
import beans.Student;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import utils.MyUtils;
import utils.StudentFunctionsUtils;

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
    
    Connection conn = MyUtils.getStoredConnection(request);
    
    List<Application> list = null;
      try {
          list = StudentFunctionsUtils.queryApplyCompany(conn);
          request.setAttribute("companyDisplay", list);
      } catch (SQLException e) {
          e.printStackTrace();
      }
    
     RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/studentApplyApplication.jsp");
        dispatcher.forward(request, response);
    }
  
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//                doGet(request, response);
            Connection conn = MyUtils.getStoredConnection(request);
            String appID = request.getParameter("id");
            
            Student student = null;
            HttpSession session = request.getSession();
            student = MyUtils.getLoginedStudent(session);
            String stdID = student.getStd_id();
            
            try{
                StudentFunctionsUtils.updateStudent(conn, appID, stdID);
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/studentViewApplicationStatus");
            dispatcher.forward(request, response);
            } 
}