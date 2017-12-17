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
import java.io.BufferedOutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.CoordinatorFunctionUtils;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet("/ImageServlet")
public class imageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(request);
        String userId = (String) request.getParameter("id");
        char userType = userId.charAt(0);
        //byte imgData[] = new byte()[];

        //check userType
        if (userType == 'C') {
            try {
                Coordinator co = DBUtils.findCoordinator(conn, userId);
                //imgData = co.getCoordinatorPhoto();
                out.println(co.getCoordinatorId());

                    out.println(co.getCoordinatorPhoto());
                try (BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
                    bos.write(co.getCoordinatorPhoto());
                    bos.flush();
                    bos.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(imageServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (userType == 'S') {
            try {
                Student stu = DBUtils.findStudent(conn, userId);
                //imgData = stu.getStdPhoto();
                try (BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())) {
                    bos.write(stu.getStdPhoto());
                    bos.flush();
                    bos.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(imageServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        response.setContentType("image/png");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
