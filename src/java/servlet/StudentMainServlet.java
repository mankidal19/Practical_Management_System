/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Student;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.MyUtils;

/**
 *
 * @author NURUL AIMAN
 */
@WebServlet(urlPatterns = {"/studentMain"})
public class StudentMainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public StudentMainServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student student = null;
        HttpSession session = request.getSession();
        student = MyUtils.getLoginedStudent(session);
        Connection conn = MyUtils.getStoredConnection(request);
        String errorString = null;
        
        // (Users can not access directly into JSP pages placed in WEB-INF)
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/studentMainView.jsp");
        request.setAttribute("errorString", errorString);
        
        request.setAttribute("student", student);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
