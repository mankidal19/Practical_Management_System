/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Application;
import beans.History;
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
import utils.CoordinatorUtils;
import utils.DBUtils;
import utils.MyUtils;
import utils.StudentFunctionsUtils;

/**
 *
 * @author USER
 */
@WebServlet(name = "coordinatorReject", urlPatterns = {"/coordinatorReject"})
public class coordinatorReject extends HttpServlet {

    public coordinatorReject(){
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
      
        try {
            history = CoordinatorUtils.findHistory(conn, historyId);
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
          
          else{
              if(errorString != null){
                  request.setAttribute("errorString", errorString);
                  RequestDispatcher dispatcher = request.getServletContext()
                          .getRequestDispatcher("/coordinatorApplicationHistory");
                  dispatcher.forward(request, response);
              }
              
              else{
                  try {
                      StudentFunctionsUtils.updateHistory(conn, history, "R");
                  } catch (SQLException ex) {
                      Logger.getLogger(coordinatorApprove.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  
                  request.setAttribute("successString", "Application rejected");
                  RequestDispatcher dispatcher = request.getServletContext()
                          .getRequestDispatcher("/coordinatorApplicationHistory");
                  dispatcher.forward(request, response);
                  
              }
          } } catch (SQLException ex) {
            Logger.getLogger(coordinatorApprove.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

 

}
