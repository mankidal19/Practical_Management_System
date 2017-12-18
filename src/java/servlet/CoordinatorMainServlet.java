/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Coordinator;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
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
import utils.DBUtils;
import utils.MyUtils;

/**
 *
 * @author USER
 */
@WebServlet(name = "CoordinatorMainServlet", urlPatterns = {"/coordinatorMain"})
public class CoordinatorMainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    public CoordinatorMainServlet() {
        super();
    }
    
    @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
        Coordinator coordinator = null;
        HttpSession session = request.getSession();
        coordinator = MyUtils.getLoginedCoordinator(session);
        Connection conn = MyUtils.getStoredConnection(request);
        int stdNum = 0;
        int appNum = 0;
        int histNum = 0;
        String errorString = null;
        try {
            // Forward to /WEB-INF/views/homeView.jsp
            // (Users can not access directly into JSP pages placed in WEB-INF)
            stdNum = CoordinatorFunctionUtils.getNumOfStudent(conn, coordinator.getCoordinatorId());
            appNum = CoordinatorFunctionUtils.getNumOfApplication(conn);
        } catch (SQLException ex) {
            Logger.getLogger(CoordinatorMainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
  
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("studentNum", stdNum);
        request.setAttribute("applicationNum", appNum);
        request.setAttribute("historyNum", histNum);
       RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/coordinatorMainView.jsp");
       request.setAttribute("coordinator", coordinator);
       dispatcher.forward(request, response);
        
   }
 
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
       doGet(request, response);
   }
 
    }
