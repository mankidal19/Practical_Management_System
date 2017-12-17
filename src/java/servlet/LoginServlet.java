/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

/**
 *
 * @author Yong Keong
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import beans.UserAccount;
//import utils.DBUtils;
//import utils.MyUtils;
import utils.*;
import beans.*;
import static java.lang.System.out;

//@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    // Show Login page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserAccount user = null;
        HttpSession session = request.getSession();
        user = MyUtils.getLoginedUser(session);
        RequestDispatcher dispatcher = null;
        if(user == null){
            // Forward to /WEB-INF/views/loginView.jsp
            // (Users can not access directly into JSP pages placed in WEB-INF)
            dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
            dispatcher.forward(request, response);
        }else{
            switch (user.getUserLevel()) {
                case 1:
                    response.sendRedirect(request.getContextPath() + "/adminMain");
                    break;
                case 2:
                    response.sendRedirect(request.getContextPath() + "/coordinatorMain");
                    break;
                case 3:
                    response.sendRedirect(request.getContextPath() + "/studentMain");
                    break;
                default:
                    dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        }
    }

    // When the user enters userName & password, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        String userType = request.getParameter("usertype");
        boolean remember = "Y".equals(rememberMeStr);

        UserAccount user = null;
        Student userStudent = null;
        Coordinator userCoordinator = null;
        Admin userAdmin = null;

        boolean hasError = false;
        String errorString = null;
       
        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else if (userType == null) {

            hasError = true;
            errorString = "Choose user type!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Find the user in the DB.

                if (userType.equals("student")) {
                    userStudent = DBUtils.findStudent(conn, userName, password);
                    user = userStudent;
                    if(user != null)
                        user.setUserLevel(userStudent.getStd_level());
                } else if (userType.equals("coordinator")) {
                    userCoordinator = DBUtils.findCoordinator(conn, userName, password);
                    user = userCoordinator;
                    if(user != null)
                        user.setUserLevel(userCoordinator.getCoordinatorLevel());
                } else if (userType.equals("admin")) {
                    userAdmin = DBUtils.findAdmin(conn, userName, password);
                    user = userAdmin;
                    if(user != null)
                        user.setUserLevel(userAdmin.getAdminLevel());
                }

                //user = DBUtils.findUser(conn, userName, password);
                if (userAdmin == null && userStudent == null && userCoordinator == null) {
                    hasError = true;
                    errorString = "Username or password invalid";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // If error, forward to /WEB-INF/views/login.jsp
        if (hasError) {
            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            
            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

            dispatcher.forward(request, response);
        } // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else {
            HttpSession session = request.getSession();
            
            //set timeout to 15 minutes
            session.setMaxInactiveInterval(15 * 60);
            
            MyUtils.storeLoginedUser(session, user);
            RequestDispatcher dispatcher = null;

            // If user checked "Remember me".
            if (remember) {
                MyUtils.storeUserCookie(response, user);
            } // Else delete cookie.
            else {
                MyUtils.deleteUserCookie(response);
            }

            // Redirect to userInfo page.
            //response.sendRedirect(request.getContextPath() + "/userInfo");
            
//            switch (userType) {
//                case "student":
//                    response.sendRedirect(request.getContextPath() + "/studentMain");
//                    break;
//                case "coordinator":
//                    response.sendRedirect(request.getContextPath() + "/coordinatorMain");
//                    break;
//                case "admin":
//                    response.sendRedirect(request.getContextPath() + "/adminMain");
//                    break;
//                default:
//                    break;
//            }
            switch (user.getUserLevel()) {
                case 1:
                    response.sendRedirect(request.getContextPath() + "/adminMain");
                    MyUtils.storeLoginedUser(session, userAdmin);
                    break;
                case 2:
                    response.sendRedirect(request.getContextPath() + "/coordinatorMain");
                    MyUtils.storeLoginedUser(session, userCoordinator);
                    break;
                case 3:
                    response.sendRedirect(request.getContextPath() + "/studentMain");
                    MyUtils.storeLoginedUser(session, userStudent);
                    break;
                default:
                    dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        }
        
    }

}
