/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import utils.MyUtils;

/**
 *
 * @author Yong Keong
 */
@WebServlet(name = "UploadCoordinatorPhotoServlet", urlPatterns = {"/UploadCoordinatorPhotoServlet"})
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
        doPost(request, response);
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
        
//        InputStream inputStream = null; // input stream of the upload file
//         
//        // obtains the upload file part in this multipart request
//        Part filePart = request.getPart("photo");
//        if (filePart != null) {
////            // prints out some information for debugging
////            System.out.println(filePart.getName());
////            System.out.println(filePart.getSize());
////            System.out.println(filePart.getContentType());
//             
//            // obtains input stream of the upload file
//            inputStream = filePart.getInputStream();
//        }
//         
//        Connection conn = MyUtils.getStoredConnection(request);
//        String message = null;  // message will be sent back to client
//         
//        try {
//            // connects to the database
// 
//            // constructs SQL statement
//            String sql = "INSERT INTO coordinator ";
//            PreparedStatement statement = conn.prepareStatement(sql);
//            statement.setString(1,coordinatorID);
//             
//            if (inputStream != null) {
//                // fetches input stream of the upload file for the blob column
//                statement.setBlob(2, inputStream);
//            }
// 
//            // sends the statement to the database server
//            int row = statement.executeUpdate();
//            if (row > 0) {
//                message = "File uploaded and saved into database";
//            }
//        } catch (SQLException ex) {
//            message = "ERROR: " + ex.getMessage();
//            ex.printStackTrace();
//        } finally {
//            if (conn != null) {
//                // closes the database connection
//                try {
//                    conn.close();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            // sets the message in request scope
//            request.setAttribute("Message", message);
//             
//            // forwards to the message page
//            getServletContext().getRequestDispatcher("/coordinatorDisplay").forward(request, response);
//        }
    }

 

}
