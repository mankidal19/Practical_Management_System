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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection conn = MyUtils.getStoredConnection(request);
        
        String errorString = null;
        Student student = null;
        HttpSession session = request.getSession();
        
        
        try {
            student = MyUtils.getLoginedStudent(session);
            
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
        String stdID = (String) request.getParameter("id");
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
            StudentFunctionsUtils.updateStudent(conn, student.getStdID(), getStdContact, getStdEmail);
        } catch (Exception e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("studentUpdate", student);
 
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/studentProfilePage.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/studentProfile");
        }
        
        doGet(request, response);
    }
}