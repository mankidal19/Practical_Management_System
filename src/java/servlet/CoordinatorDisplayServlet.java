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


@WebServlet(name = "CoordinatorDisplayServlet", urlPatterns = {"/coordinatorDisplay"})
public class CoordinatorDisplayServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CoordinatorDisplayServlet() {
        super();
    }
    
     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
 
        String errorString = null;
        Coordinator coordinator = null;
        HttpSession session = request.getSession();
        
        
        try {
            coordinator = MyUtils.getLoginedCoordinator(session);
            coordinator = CoordinatorUtils.findCoordinator(conn, coordinator.getCoordinatorId());
//            MyUtils.storeLoginedUser(session, coordinator);
               
        }
        catch (Exception e) {
            errorString = e.getMessage();
        }
        
        
        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("displayCoordinator", coordinator);
        request.setAttribute("coordinator", coordinator);
         
       
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/coordinatorProfile.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Connection conn = MyUtils.getStoredConnection(request);
        String coordinatorDepartment = (String) request.getParameter("department");
        String coordinatorPosition = (String) request.getParameter("position");
        
        Coordinator coordinator =  null;
        HttpSession session = request.getSession();
        String errorString = null;
        
        try {
            coordinator = MyUtils.getLoginedCoordinator(session);
            
        }
        catch (Exception e) {
            errorString = e.getMessage();
        }
        
        try {
            if(coordinatorDepartment != null && coordinatorPosition != null && coordinator != null)
                CoordinatorUtils.updateCoordinator(conn, coordinator.getCoordinatorId(), coordinatorDepartment, coordinatorPosition);
        } catch (Exception e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        
        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/coordinatorProfile.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            System.out.println("haha");
            response.sendRedirect(request.getContextPath() + "/coordinatorDisplay");
   }
        
//        RequestDispatcher dispatcher = request.getServletContext()
//                .getRequestDispatcher("/coordinatorEditDetails");
//        dispatcher.forward(request, response);
    }   

    

}
