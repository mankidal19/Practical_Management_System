/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Application;
import beans.History;
import beans.Student;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.List;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import utils.DBUtils;
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
        String appId = (String) request.getParameter("id");

        int count = 0;
        String historyID = null;
        String errorString = null;
        Application app = null;

        HttpSession session = request.getSession();

        Student stu = MyUtils.getLoginedStudent(session);

        try {
            app = DBUtils.findApplication(conn, appId);
        } catch (SQLException ex) {
            Logger.getLogger(applyApplicationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        History history = null;

        String stdId = stu.getStd_id();

        try {
            count = StudentFunctionsUtils.getNumOfHistory(conn) + 1;
        } catch (SQLException ex) {
            Logger.getLogger(applyApplicationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (count < 10) {
            historyID = "H00" + count;
        } else if (count < 99) {
            historyID = "H0" + count;
        } else if (count < 999) {
            historyID = "H" + count;
        } else {
            errorString = "exceed server's limit of creating new history reached";
        }

        if (app.getApplicationJob() == 0) {
            errorString = "Job already full!";
        } else {
            try {
                if (StudentFunctionsUtils.applyExist(conn, stdId)) {
                    errorString = "Duplicate Application!";
                } else if (StudentFunctionsUtils.approveExist(conn, stdId)) {
                    errorString = "Your previous application has been approved!";
                } else {
                    java.util.Date utilDate = new java.util.Date();
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    history = new History(historyID, stdId, appId, sqlDate);

                    //update number of job available
                    /* try {
                StudentFunctionsUtils.updateApplication(conn, app.getApplicationJob() - 1, appId);
                } catch (SQLException ex) {
                Logger.getLogger(applyApplicationServlet.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                    try {
                        //insert history
                        StudentFunctionsUtils.insertHistory(conn, history);
                        StudentFunctionsUtils.updateStudentStatus(conn, "P", stdId);
                    } catch (SQLException ex) {
                        Logger.getLogger(applyApplicationServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //update student details
                    stu.setApp_id(appId);
                    stu.setStd_status(stdId);

                    try {
                        DBUtils.updateStudent(conn, stu);
                    } catch (SQLException ex) {
                        Logger.getLogger(applyApplicationServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(applyApplicationServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (errorString != null) {
//         response.sendRedirect(request.getServletContext().getContextPath());
            
            request.setAttribute("errorString", errorString);
            out.println(errorString);
            response.sendRedirect(request.getServletContext().getContextPath()+"/applicationList");
            
//            RequestDispatcher dispatcher = request.getServletContext()
//                    .getRequestDispatcher("/applicationList");
//            dispatcher.forward(request, response);
            return;
        }

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/StudentViewApplicationHistory");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
