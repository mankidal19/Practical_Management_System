/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Application;
import beans.UserAccount;
import java.io.IOException;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.CoordinatorFunctionUtils;
import utils.DBUtils;
import utils.MyUtils;

/**
 *
 * @author Yong Keong
 */
@WebServlet(name = "companyListViewServlet", urlPatterns = {"/companyListViewServlet"})
public class CompanyListViewServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
  
    public CompanyListViewServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
        
        UserAccount user = null;
        HttpSession session = request.getSession();
        user = MyUtils.getLoginedUser(session);
        out.println(user.getUserLevel());
        if(user.getUserLevel() == 3){
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/studentMain");
            dispatcher.forward(request, response);
        }else{
            Connection conn = MyUtils.getStoredConnection(request);
            String errorString = null;
            List<Application> list = null;
            try {
                list = CoordinatorFunctionUtils.queryCompany(conn);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
            // Store info in request attribute, before forward to views
            request.setAttribute("errorString", errorString);
            request.setAttribute("companyList", list);

           // Forward to /WEB-INF/views/productListView.jsp
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/companyListView.jsp");
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
        doGet(request, response);
    }

   

}
