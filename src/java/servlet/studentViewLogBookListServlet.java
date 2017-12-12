/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Report;
import beans.Student;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
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
import utils.StudentFunctionsUtils;
import utils.MyUtils;

/**
 *
 * @author Nurfarahin Nadhirah
 */
@WebServlet(urlPatterns = {"/studentViewLogBookList"})
public class studentViewLogBookListServlet extends HttpServlet {

    public studentViewLogBookListServlet(){
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
        
            Student student = null;
            HttpSession session = request.getSession();
            student = MyUtils.getLoginedStudent(session);
            int index = 0;
            
            Connection conn = MyUtils.getStoredConnection(request);
            String errorString = null;
            List<Report> list = null;
          
        try {
            list = StudentFunctionsUtils.queryReport(conn);
        } catch (SQLException ex) {
            Logger.getLogger(studentViewLogBookListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }


            // Store info in request attribute, before forward to views
            request.setAttribute("errorString", errorString);
            request.setAttribute("reportList", list);
            request.setAttribute("index", index);

           // Forward to /WEB-INF/views/productListView.jsp
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/studentViewLogBookList.jsp");
            dispatcher.forward(request, response);
            
            }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                doGet(request, response);
            }
}