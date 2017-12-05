/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import beans.Coordinator;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import utils.CoordinatorFunctionUtils;
import utils.MyUtils;

/**
 *
 * @author Yong Keong
 */
@WebServlet(name = "UploadCoordinatorPhotoServlet", urlPatterns = {"/UploadCoordinatorPhotoServlet"})
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class UploadCoordinatorPhotoServlet extends HttpServlet {


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
        Connection conn = MyUtils.getStoredConnection(request);
 
        String errorString = null;
        Coordinator coordinator = null;
        HttpSession session = request.getSession();
        
         try {
            coordinator = MyUtils.getLoginedCoordinator(session);
            
        }
        catch (Exception e) {
            errorString = e.getMessage();
        }
        byte[] imgData = CoordinatorFunctionUtils.queryCoordinatorPhoto(conn,coordinator.getCoordinatorId());
        request.setAttribute("errorString", errorString);
        request.setAttribute("coordinator", coordinator);
        request.setAttribute("coordinatorPhoto", imgData);
       
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/coordinatorUploadView.jsp");
        dispatcher.forward(request, response);
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
        
HttpSession session = request.getSession();
        Coordinator coordinator = null;
        coordinator = MyUtils.getLoginedCoordinator(session);

        InputStream inputStream = null; // input stream of the upload file
         
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
             
            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
         
        Connection conn = null; // connection to the database
        String message = null;  // message will be sent back to client
         
        try {
            // connects to the database
            conn = MyUtils.getStoredConnection(request);
            int row = CoordinatorFunctionUtils.uploadCoordinatorPhoto(conn, coordinator.getCoordinatorId(), inputStream);
            if (row > 0) {
                message = "File uploaded and saved into database";
            }
        } catch (SQLException ex) {
            message = "ERROR: " + ex.getMessage();
            ex.printStackTrace();
        } 
        // sets the message in request scope
        request.setAttribute("Message", message);

        // forwards to the message page
        getServletContext().getRequestDispatcher("/coordinatorDisplay").forward(request, response);
        
    }

 

}
