/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection conn = MyUtils.getStoredConnection(request);
//
//            PreparedStatement pstmt = conn.prepareStatement("insert into application values (?,?,?,?,?,?,?)");
//
//            pstmt.setString(1, cname);
//            pstmt.setString(2, caddress);
//            pstmt.setString(3, ccontact);
//            pstmt.setString(4, sname);
//            pstmt.setString(5, semail);
//            pstmt.setString(6, joblevel);
//            pstmt.setString(7, jobtitle);
//
//            pstmt.executeUpdate();
//
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        out.println("Your form has been submitted successfully!Directing you to Company List");
//
//         RequestDispatcher dispatcher = request.getServletContext()
//                    .getRequestDispatcher("/WEB-INF/views/companyListServlet");
//            dispatcher.forward(request, response);
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
