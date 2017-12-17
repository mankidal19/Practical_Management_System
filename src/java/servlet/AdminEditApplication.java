/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

/**
 *
 * @author NURUL AIMAN
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

import beans.*;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = {"/editApplication"})
public class AdminEditApplication extends HttpServlet {

    public AdminEditApplication() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String id = (String) request.getParameter("id");

        Application app = null;

        String errorString = null;

        try {
            app = DBUtils.findApplication(conn, id);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        // If no error.
        // The product does not exist to edit.
        // Redirect to productList page.
        if (errorString != null && app == null) {
            response.sendRedirect(request.getServletPath() + "/applicationList");
            return;
        }

        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("application", app);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/adminEditApplicationView.jsp");

        //     RequestDispatcher dispatcher = request.getServletContext()
        //           .getRequestDispatcher("/WEB-INF/views/adminCoordinatorListView.jsp#modal-edit");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String errorString = null;

        Connection conn = MyUtils.getStoredConnection(request);

        String cid = request.getParameter("cId");
        //String index = "AP" + String.format ("%03d", cid);
        String cName = request.getParameter("cName");
        String cAddress = request.getParameter("cAddress");
        String cContact = request.getParameter("cContactName");
        String cPhone = request.getParameter("cContactNumber");
        String cEmail = request.getParameter("cContactEmail");
        String job = request.getParameter("cJob");
        String jobtitle = request.getParameter("cJobTitle");
        int jobNum = 0;

        try {
            jobNum = Integer.parseInt(job);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        if (cName == null || "".equals(cName)) {
            errorString = "COMPANY NAME REQUIRED";
        }
        if (cAddress == null || "".equals(cAddress)) {
            errorString = "COMPANY ADDRESS REQUIRED";
        }
        if (cContact == null || "".equals(cContact)) {
            errorString = "COMPANY CONTACT PERSON REQUIRED";
        }
        if (cPhone == null || "".equals(cPhone)) {
            errorString = "COMPANY PHONE REQUIRED";
        }
        if (cEmail == null || "".equals(cEmail)) {
            errorString = "COMPANY EMAIL REQUIRED";
        }
        if (job == null || "".equals(job)) {
            errorString = "INCOMPLETE JOB DETAILS";
        }
        if (jobtitle == null || "".equals(jobtitle)) {
            errorString = "INCOMPLETE JOB DETAILS";
        }

        //checks for valid email
        if (!MyUtils.isValidEmailAddress(cEmail)) {

            errorString = "Invalid e-mail format!";
        }

        Application app = new Application(cid, cName, cAddress, cContact, cPhone, cEmail, jobNum, jobtitle);
        request.setAttribute("errorString", errorString);
        request.setAttribute("application", app);
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/adminAddApplicationView.jsp");
            dispatcher.forward(request, response);
        } else {

            try {
                DBUtils.updateApplication(conn, app);

            } catch (Exception e) {
                e.printStackTrace();
            }
            out.println("Your form has been updated successfully!Directing you to Application List");

            response.sendRedirect(request.getContextPath() + "/applicationList");
        }
    }

}
