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
        
        String username = MyUtils.getUserNameInCookie(request);
        String password = MyUtils.getUserPasswordInCookie(request);
        
        if(username!=null && password !=null){
            out.println(username+" "+password);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
        }
        
        RequestDispatcher dispatcher = null;
        if (user == null) {
            // Forward to /WEB-INF/views/loginView.jsp
            // (Users can not access directly into JSP pages placed in WEB-INF)

            dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
            dispatcher.forward(request, response);

        } else {
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
        //String userType = request.getParameter("usertype");
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
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Find the user in the DB.

                if (userName.charAt(0) == 'S') {
                    userStudent = DBUtils.findStudent(conn, userName, password);
                    user = userStudent;
                    user.setUserLevel(userStudent.getStd_level());

                } else if (userName.charAt(0) == 'C') {
                    userCoordinator = DBUtils.findCoordinator(conn, userName, password);
                    user = userCoordinator;
                    user.setUserLevel(userCoordinator.getCoordinatorLevel());
                } else if (userName.charAt(0) == 'A') {
                    userAdmin = DBUtils.findAdmin(conn, userName, password);
                    user = userAdmin;
                    user.setUserLevel(userAdmin.getAdminLevel());
                } else {
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
            MyUtils.storeLoginedUser(session, user);
            //set timeout to 15 minutes
            session.setMaxInactiveInterval(15 * 60 * 60);

            MyUtils.storeLoginedUser(session, user);
            RequestDispatcher dispatcher = null;

            switch (user.getUserLevel()) {
                case 1:
                    MyUtils.storeLoginedUser(session, userAdmin);
                    // If user checked "Remember me".
                    if (remember) {
                        MyUtils.storeUserCookie(response, userAdmin);
                        out.println("remember you");
                    } // Else delete cookie.
                    else {
                        MyUtils.deleteUserCookie(response);
                    }
                    
                    response.sendRedirect(request.getContextPath() + "/adminMain");
                    
                    break;
                case 2:
                    MyUtils.storeLoginedUser(session, userCoordinator);
                    // If user checked "Remember me".
                    if (remember) {
                        MyUtils.storeUserCookie(response, userCoordinator);
                    } // Else delete cookie.
                    else {
                        MyUtils.deleteUserCookie(response);
                    }
                    
                    response.sendRedirect(request.getContextPath() + "/coordinatorMain");
                    
                    break;
                case 3:
                    
                    MyUtils.storeLoginedUser(session, userStudent);
                    // If user checked "Remember me".
                    if (remember) {
                        MyUtils.storeUserCookie(response, userStudent);
                    } // Else delete cookie.
                    else {
                        MyUtils.deleteUserCookie(response);
                    }
                    
                    response.sendRedirect(request.getContextPath() + "/studentMain");
                    break;
                default:
                    dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        }

    }

}
