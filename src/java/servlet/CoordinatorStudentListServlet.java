/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Coordinator;
import beans.Student;
import beans.UserAccount;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
import utils.MyUtils;

/**
 *
 * @author Yong Keong
 */
@WebServlet(name = "CoordinatorStudentListServlet", urlPatterns = {"/coStudentList"})
public class CoordinatorStudentListServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserAccount user = null;
        Coordinator coordinator = null;
        HttpSession session = request.getSession();
        user = MyUtils.getLoginedUser(session);
        coordinator = MyUtils.getLoginedCoordinator(session);
        
        if(user.getUserLevel() == 3){
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/studentMain");
            dispatcher.forward(request, response);
        }else{
            try {
                Connection conn = MyUtils.getStoredConnection(request);
                String errorString = null;
                List<Student> list = null;
                list = CoordinatorFunctionUtils.queryStudent(conn,coordinator.getCoordinatorId());
                // Store info in request attribute, before forward to views
                request.setAttribute("errorString", errorString);
                request.setAttribute("coStudentList", list);
                
                // Forward to /WEB-INF/views/productListView.jsp
                RequestDispatcher dispatcher = request.getServletContext()
                        .getRequestDispatcher("/WEB-INF/views/coordinatorStudentList.jsp");
                dispatcher.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(CoordinatorStudentListServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
