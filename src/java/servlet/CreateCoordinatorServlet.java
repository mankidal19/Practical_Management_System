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
import java.io.InputStream;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import utils.CoordinatorFunctionUtils;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = {"/createCoordinator"})
@MultipartConfig
public class CreateCoordinatorServlet extends HttpServlet {

    public CreateCoordinatorServlet() {
        super();
    }

    // Show product creation page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(request);
        String errorString = null;
        int numOfRow = 0;
        String newId = "C";

        try {
            numOfRow = DBUtils.getNumOfCoordinator(conn);

        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        if (numOfRow < 10) {
            String num = "00" + (numOfRow + 1);
            newId += num;
        } else if (numOfRow >= 10) {
            String num = "0" + (numOfRow + 1);
            newId += num;
        } else {
            errorString = "limit of creating new coordinator reached";
        }

        if (errorString != null) {
            response.sendRedirect(request.getServletPath() + "/adminMain");
            return;
        }

        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("newId", newId);

        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/adminAddCoordinatorView.jsp");
        dispatcher.forward(request, response);
    }

    // When the user enters the product information, and click Submit.
    // This method will be called.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String id = (String) request.getParameter("co_id");
        String name = (String) request.getParameter("name");
        String dept = (String) request.getParameter("department");
        String post = (String) request.getParameter("position");
        String pass = (String) request.getParameter("password");

        String errorString = null;

        String levelStr = (String) request.getParameter("level");
        int level = 2;
       

        Coordinator c = new Coordinator(id, pass, level, name, dept, post);

        //upload photo features
        InputStream inputStream = null; // input stream of the upload file

        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        out.println(filePart);
        
        //update img blob if new image uploaded
        if (filePart.getSize()!=0) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();

            String message = null;  // message will be sent back to client

            try {
                // connects to the database
                int row = CoordinatorFunctionUtils.uploadCoordinatorPhoto(conn, c.getCoordinatorId(), inputStream);
                if (row > 0) {
                    message = "File uploaded and saved into database";
                }
            } catch (SQLException ex) {
                message = "ERROR: " + ex.getMessage();
                ex.printStackTrace();
            }
            // sets the message in request scope
            request.setAttribute("Message", message);
        }
        
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("coordinator", c);

        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/adminAddCoordinatorView.jsp");
            dispatcher.forward(request, response);
        } // If everything nice.
        // Redirect to the product listing page.
        else {
            try {
                DBUtils.insertCoordinator(conn, c);
            } catch (SQLException e) {
                e.printStackTrace();
                errorString = e.getMessage();
            }
            response.sendRedirect(request.getContextPath() + "/coordinatorList");
        }
    }

}
