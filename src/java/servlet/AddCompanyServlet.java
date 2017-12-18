/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Application;
import beans.Coordinator;
import beans.UserAccount;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "addCompanyServlet", urlPatterns = {"/addCompanyServlet"})
public class AddCompanyServlet extends HttpServlet {


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
        HttpSession session = request.getSession();
        user = MyUtils.getLoginedUser(session);
        Coordinator coordinator = null;
        coordinator = MyUtils.getLoginedCoordinator(session);
        out.println(user.getUserLevel());
        if(user.getUserLevel() == 3){
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/studentMain");
            dispatcher.forward(request, response);
        }else{
            Connection conn = MyUtils.getStoredConnection(request);
           String errorString = null;
           List<Application> list = null;
           String index = null;
           try {
               index = CoordinatorFunctionUtils.queryCompanyIndex(conn);
           } catch (SQLException e) {
               e.printStackTrace();
               errorString = e.getMessage();
           }

           index = index.substring(2, 5);
           // Store info in request attribute, before forward to views
           request.setAttribute("errorString", errorString);
           request.setAttribute("companyLastIndex", index);
           request.setAttribute("coordinator", coordinator);

           RequestDispatcher dispatcher = request.getServletContext()
                   .getRequestDispatcher("/WEB-INF/views/addCompanyView.jsp");
           dispatcher.forward(request, response);
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
            response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    int cid = Integer.parseInt(request.getParameter("cId"));
    String index = "AP" + String.format ("%03d", cid);
    String cName = request.getParameter("cName");
    String cAddress = request.getParameter("cAddress");
    String cContact = request.getParameter("cContactName");
    String cPhone = request.getParameter("cContactNumber");
    String cEmail = request.getParameter("cContactEmail");
    String job = request.getParameter("cJob");
    String jobtitle = request.getParameter("cJobTitle");
    
    ArrayList error  = new ArrayList();
    if(cName == null || "".equals(cName)){
        error.add("COMPANY NAME REQUIRED");
    }
    if(cAddress == null || "".equals(cAddress)){
        error.add("COMPANY NAME REQUIRED");
    }
    if(cContact == null || "".equals(cContact)){
        error.add("COMPANY NAME REQUIRED");
    }
    if(cPhone == null || "".equals(cPhone)){
        error.add("COMPANY NAME REQUIRED");
    }
    if(cEmail == null || "".equals(cEmail)){
        error.add("COMPANY NAME REQUIRED");
    }
    if(job == null || "".equals(job)){
        error.add("COMPANY NAME REQUIRED");
    }
    if(jobtitle == null || "".equals(jobtitle)){
        error.add("COMPANY NAME REQUIRED");
    }
    
    if (!error.isEmpty()) {
         RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/AddCompanyDisplayServlet");
        dispatcher.forward(request, response);   
    }else{    

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = MyUtils.getStoredConnection(request);

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO APPLICATION VALUES (?,?,?,?,?,?,?,?)");
            
            pstmt.setString(1, index);
            pstmt.setString(2, cName);
            pstmt.setString(3, cContact);
            pstmt.setString(4, cAddress);
            pstmt.setString(5, cPhone);
            pstmt.setString(6, cEmail);
            pstmt.setString(7, job);
            pstmt.setString(8, jobtitle);
           

            pstmt.executeUpdate();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        out.println("Your form has been submitted successfully!Directing you to Company List");

         RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/companyListViewServlet");
            dispatcher.forward(request, response);
        }
    }

}
