/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Application;
import beans.Coordinator;
import beans.History;
import beans.Student;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.CoordinatorFunctionUtils;
import utils.CoordinatorUtils;
import utils.DBUtils;
import utils.MyUtils;
import utils.StudentFunctionsUtils;

/**
 *
 * @author USER
 */
@WebServlet(name = "coordinatorApprove", urlPatterns = {"/coordinatorApprove"})
public class coordinatorApprove extends HttpServlet {

    public coordinatorApprove(){
        super();
    }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
      HttpSession session = request.getSession();
      Connection conn = MyUtils.getStoredConnection(request);
      String errorString = null;
      List<Application> appList = new ArrayList<Application>();
      String historyId = request.getParameter("id");
      History history = null;
      Application app = null;
       Coordinator coordinator = null;
       Student std = null;
        coordinator = MyUtils.getLoginedCoordinator(session);
      request.setAttribute("coordinator", coordinator);
      
        
      
        try {
            history = CoordinatorUtils.findHistory(conn, historyId);
        } catch (SQLException ex) {
            Logger.getLogger(coordinatorApprove.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            std=DBUtils.findStudent(conn, history.getStdID());
        } catch (SQLException ex) {
            Logger.getLogger(coordinatorApprove.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            app = DBUtils.findApplication(conn, history.getAppID());
        } catch (SQLException ex) {
            Logger.getLogger(coordinatorApprove.class.getName()).log(Level.SEVERE, null, ex);
        }
      if(app.getApplicationJob() == 0){
          errorString = "No available job";
      }
      else try {
          if(StudentFunctionsUtils.approveExist(conn, history.getStdID())){
              errorString = "Previous application already approved"; 
          }
          
          } catch (SQLException ex) {
            Logger.getLogger(coordinatorApprove.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      if(errorString != null){
                  request.setAttribute("errorString", errorString);
                  RequestDispatcher dispatcher = request.getServletContext()
                          .getRequestDispatcher("/coordinatorApplicationList");
                  dispatcher.forward(request, response);
              }
              
              else{
                  try {
                      StudentFunctionsUtils.updateHistory(conn, history, "A");
                      CoordinatorFunctionUtils.updateStudent(conn,history.getStdID(),"A");
                      
                  } catch (SQLException ex) {
                      Logger.getLogger(coordinatorApprove.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  try {
                      StudentFunctionsUtils.updateApplication(conn, app);
                  } catch (SQLException ex) {
                      Logger.getLogger(coordinatorApprove.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  request.setAttribute("successString", "success approved");
                  RequestDispatcher dispatcher = request.getServletContext()
                          .getRequestDispatcher("/coordinatorApplicationHistory");
                  dispatcher.forward(request, response);
                  
              }
    }

 

}
