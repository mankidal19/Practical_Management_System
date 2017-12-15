/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Report;
import beans.Student;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(urlPatterns = {"/studentAddLogBook"})
public class studentAddLogBookServlet extends HttpServlet {

    public studentAddLogBookServlet(){
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
        
        Connection conn = MyUtils.getStoredConnection(request);
        
        Student student = null;
        HttpSession session = request.getSession();
        student = MyUtils.getLoginedStudent(session);
        String index = null;
        String errorString = null;
        try {
               index = StudentFunctionsUtils.queryReportIndex(conn);
           } catch (SQLException e) {
               e.printStackTrace();
               errorString = e.getMessage();
           }

           index = index.substring(1, 4);
           
           // Store info in request attribute, before forward to views
           request.setAttribute("errorString", errorString);
           request.setAttribute("studentID", student.getStd_id());
           request.setAttribute("reportLastIndex", index);
           RequestDispatcher dispatcher = request.getServletContext()
                   .getRequestDispatcher("/WEB-INF/views/studentAddLogBook.jsp");
           dispatcher.forward(request, response);
        }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    int reportID = Integer.parseInt(request.getParameter("id"));
    String index = "R" + String.format ("%03d", reportID);
    String reportName = request.getParameter("title");
    String reportContent = request.getParameter("content");
    HttpSession session = request.getSession();
    Student student = null; 
    student = MyUtils.getLoginedStudent(session);
    String stdID = student.getStd_id();
    ArrayList error  = new ArrayList();
    if(reportName == null || "".equals(reportName)){
        error.add("Report must have a title!");
    }

    if(reportContent == null || "".equals(reportContent)){
        error.add("Content is required!");
    }
    
    
    if (!error.isEmpty()) {
         RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/studentAddLogBook");
        dispatcher.forward(request, response);   
    }else{    

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = MyUtils.getStoredConnection(request);

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO REPORT VALUES (?,?,?,?)");
            
            pstmt.setString(1, index);
            pstmt.setString(2, reportName);
            pstmt.setString(3, reportContent);
            pstmt.setString(4, stdID);

            pstmt.executeUpdate();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        out.println("Your form has been submitted successfully!Directing you to Log Book List");

         RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/studentViewLogBookList");
            dispatcher.forward(request, response);
        }
    }
}