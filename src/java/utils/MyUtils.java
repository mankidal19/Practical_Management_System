/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author NURUL AIMAN
 */
import java.sql.Connection;
 
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import beans.*;
 
public class MyUtils {
 
    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
 
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
 
    private static final String ATT_NAME_USER_LEVEL = "ATTRIBUTE_FOR_STORE_USER_LVL_IN_COOKIE";
    
    // Store Connection in request attribute.
    // (Information stored only exist during requests)
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }
 
    // Get the Connection object has been stored in attribute of the request.
    public static Connection getStoredConnection(ServletRequest request) {
        Connection conn = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
        return conn;
    }
 
    // Store user info in Session.
    public static void storeLoginedUser(HttpSession session, UserAccount loginedUser) {
        // On the JSP can access via ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }
    
    // Store user student info in Session.
    public static void storeLoginedUser(HttpSession session, Student loginedUser) {
        // On the JSP can access via ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }
    
    // Store user coordinator info in Session.
    public static void storeLoginedUser(HttpSession session, Coordinator loginedUser) {
        // On the JSP can access via ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }
    
    // Store user admin info in Session.
    public static void storeLoginedUser(HttpSession session, Admin loginedUser) {
        // On the JSP can access via ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }
 
    // Get the user information stored in the session.
    public static UserAccount getLoginedUser(HttpSession session) {
        UserAccount loginedUser = (UserAccount) session.getAttribute("loginedUser");
        return loginedUser;
    }
    
    // Get the user student information stored in the session.
    public static Student getLoginedStudent(HttpSession session) {
        Student loginedUser = (Student) session.getAttribute("loginedUser");
        return loginedUser;
    }
    
    // Get the user coordinator information stored in the session.
    public static Coordinator getLoginedCoordinator(HttpSession session) {
        Coordinator loginedUser = (Coordinator) session.getAttribute("loginedUser");
        return loginedUser;
    }
    
    // Get the user Admin information stored in the session.
    public static Admin getLoginedAdmin(HttpSession session) {
        Admin loginedUser = (Admin) session.getAttribute("loginedUser");
        return loginedUser;
    }
 
    // Store info in Cookie
    public static void storeUserCookie(HttpServletResponse response, UserAccount user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUserName());
        
        // 1 day (Converted to seconds)
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }
 
     public static void storeUserCookie(HttpServletResponse response, Student user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getStd_id());
        Cookie cookieUserPassword = new Cookie(ATT_NAME_USER_LEVEL, user.getStd_pw());
        
        // 1 day (Converted to seconds)
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
        response.addCookie(cookieUserPassword);
    }
     
     public static void storeUserCookie(HttpServletResponse response, Coordinator user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getCoordinatorName());
        Cookie cookieUserPassword = new Cookie(ATT_NAME_USER_LEVEL, user.getCoordinatorPassword());
        
        // 1 day (Converted to seconds)
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
        response.addCookie(cookieUserPassword);
    }
     
     public static void storeUserCookie(HttpServletResponse response, Admin user) {
        System.out.println("Store user cookie admin" + Integer.toString(user.getAdminLevel()));
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getAdminId());
        Cookie cookieUserPassword = new Cookie(ATT_NAME_USER_LEVEL, user.getAdminPassword());
        
        
        // 1 day (Converted to seconds)
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
        response.addCookie(cookieUserPassword);
    }
    
    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
 
    public static String getUserPasswordInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_LEVEL.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    
    // Delete cookie.
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        Cookie cookieUserPassword = new Cookie(ATT_NAME_USER_LEVEL, null);
        
        // 0 seconds (This cookie will expire immediately)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
        response.addCookie(cookieUserPassword);
        
    }
    
     public static boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
 
}