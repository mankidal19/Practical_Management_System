/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
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
import utils.DBUtils;
import utils.MyUtils;

/**
 *
 * @author NURUL AIMAN
 */
@WebServlet(urlPatterns = {"/adminMain"})
public class AdminMainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AdminMainServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        int stdNum = 0;
        int coNum = 0;
        int appNum = 0;
        int histNum = 0;
        String errorString = null;
        
        // Forward to /WEB-INF/views/homeView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/adminMainView.jsp");
        try {
            stdNum = DBUtils.getNumOfStudent(conn);
            
                    } catch (SQLException ex) {
            Logger.getLogger(AdminMainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            coNum = DBUtils.getNumOfCoordinator(conn);
        } catch (SQLException ex) {
            Logger.getLogger(AdminMainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            appNum = DBUtils.getNumOfApplication(conn);
        } catch (SQLException ex) {
            Logger.getLogger(AdminMainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            histNum = DBUtils.getNumOfHistory(conn);
        } catch (SQLException ex) {
            Logger.getLogger(AdminMainServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("studentNum", stdNum);
        request.setAttribute("coordinatorNum", coNum);
        request.setAttribute("applicationNum", appNum);
        request.setAttribute("historyNum", histNum);
        
        
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
