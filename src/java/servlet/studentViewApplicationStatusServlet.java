/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.History;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.Student;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import utils.MyUtils;
import utils.StudentFunctionsUtils;

/**
 *
 * @author Nurfarahin Nadhirah
 */
@WebServlet(urlPatterns = {"/studentViewApplicationStatus"})
public class studentViewApplicationStatusServlet extends HttpServlet {

    public studentViewApplicationStatusServlet(){
        super();
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        Connection conn = MyUtils.getStoredConnection(request);
        String errorString = null;
        Student student = null;
        HttpSession session = request.getSession();
        student = MyUtils.getLoginedStudent(session);
        List<History> list = new ArrayList();   
        try{
            list = StudentFunctionsUtils.queryApply(conn, student.getStd_id());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        request.setAttribute("status", list);
        request.setAttribute("errorString", errorString);
        
        RequestDispatcher dispatcher = request.getServletContext()
                   .getRequestDispatcher("/WEB-INF/views/studentViewApplicationStatus.jsp");
           dispatcher.forward(request, response);
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
