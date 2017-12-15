/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;
import beans.*;
import utils.*;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author Nurfarahin Nadhirah
 */
@WebServlet(urlPatterns = {"/studentProfile"})
public class StudentProfilePageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StudentProfilePageServlet() {
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
       
        Connection conn = MyUtils.getStoredConnection(request);
        
        String errorString = null;
        Student student = null;
        HttpSession session = request.getSession();
        
        
        try {
            student = MyUtils.getLoginedStudent(session);
            student = StudentFunctionsUtils.getStudentDetail(conn, student.getStd_id());
            
        }
        catch (Exception e) {
            errorString = e.getMessage();
        }
        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("profileStudent", student);
         
       
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/studentProfilePage.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Connection conn = MyUtils.getStoredConnection(request);
        String stdContact = (String) request.getParameter("contact");
        String stdEmail = (String) request.getParameter("email");
        
        Student student =  null;
        HttpSession session = request.getSession();
        String errorString = null;
        
        try {
            student = MyUtils.getLoginedStudent(session);
            
        }
        catch (Exception e) {
            errorString = e.getMessage();
        }
 
        try {
            if(stdContact != null && stdEmail != null && student != null)
                StudentFunctionsUtils.updateStudent(conn, student.getStd_id(), stdContact, stdEmail);
        } catch (Exception e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
//        request.setAttribute("studentUpdate", student);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/studentProfilePage.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            System.out.println("haha");
            response.sendRedirect(request.getContextPath() + "/studentProfile");
   }
        
        
    }
}