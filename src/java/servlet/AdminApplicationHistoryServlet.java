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
import beans.Application;
import beans.History;
import beans.Report;
import beans.Student;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.DBUtils;
import utils.StudentFunctionsUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = {"/historyList"})

public class AdminApplicationHistoryServlet extends HttpServlet {

    public AdminApplicationHistoryServlet() {
        super();
    }
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        Connection conn = MyUtils.getStoredConnection(request);
        String errorString = null;
        List<Application> appList = new ArrayList<Application>();
        List<History> historyList = new ArrayList<History>();
        List<Student> studentList = new ArrayList<Student>();
        List<String> statusList = new ArrayList<String>();
        
        
        //debug
//        out.println(student.getStd_name());
        try {
            historyList = DBUtils.queryHistory(conn);
        } catch (SQLException ex) {
            Logger.getLogger(StudentViewApplicationHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        //finding the related application details
        for (History elem : historyList) {
            //System.out.println(elem);

            Application app = null;
            Student std = null;

            try {
                app = DBUtils.findApplication(conn, elem.getAppID());
                std = DBUtils.findStudent(conn, elem.getStdID());

                //debug
                out.println(app.getApplicationId());

                appList.add(app);
                studentList.add(std);
                
                //set status list string
                if(elem.getAppStatus().equals("P")){
                    statusList.add("Pending");
                }
                
                else if(elem.getAppStatus().equals("R")){
                    statusList.add("Rejected");
                }
                
                else if(elem.getAppStatus().equals("A")){
                    statusList.add("Approved");
                }

            } catch (SQLException ex) {
                Logger.getLogger(StudentViewApplicationHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            

        }

        request.setAttribute("historyList", historyList);
        request.setAttribute("applicationList", appList);
        request.setAttribute("statusList", statusList);
        request.setAttribute("studentList", studentList);
        
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/adminHistoryListView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    
}
