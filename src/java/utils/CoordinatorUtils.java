/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import beans.Application;
import beans.Coordinator;
import beans.History;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yong Keong
 */
public class CoordinatorUtils {
    public static void updateCoordinator(Connection conn, Coordinator co) throws SQLException {
        String sql = "update coordinator set co_department=?, co_position=? where co_id=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        
        pstm.setString(1, co.getCoordinatorDepartment());
        pstm.setString(2, co.getCoordinatorPosition());
        
        pstm.setString(3, co.getCoordinatorId());
        
        pstm.executeUpdate();
        
        out.println(conn);
        out.println(pstm);
        
    }
    
    public static List<Coordinator> queryCoordinator(Connection conn) throws SQLException {
        String sql = "Select * from Coordinator where co_id=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Coordinator> list = new ArrayList<Coordinator>();
        while (rs.next()) {
            //String code = rs.getString("Code");
            //String name = rs.getString("Name");
            //float price = rs.getFloat("Price");
            
            //int level = rs.getInt("co_level");
            String id = rs.getString("co_id");
            String name = rs.getString("co_name");
            String password = rs.getString("co_pw");
            String department = rs.getString("co_department");
            String position = rs.getString("co_position");
            
           
            Coordinator user = new Coordinator(id, password, name, department, position);
            
            list.add(user);
        }
        return list;
    }
    
    public static Coordinator findCoordinator(Connection conn, String coID) throws SQLException {

        String sql = "Select * from Coordinator "//
                + " where co_id = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, coID);
   

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            String id = rs.getString("co_id");
            String name = rs.getString("co_name");
            String password = rs.getString("co_pw");
            String department = rs.getString("co_department");
            String position = rs.getString("co_position");
//            int level = rs.getInt("co_level");
            
           
            Coordinator user = new Coordinator(id, password, name, department, position);
            return user;
        }
        return null;
    }
    
    public static void updateCoordinator(Connection conn, String coordinatorId, String department, String position) throws SQLException {
        String sql = "UPDATE coordinator set co_department=?, co_position=? where co_id=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, department);
        pstm.setString(2, position);
        pstm.setString(3, coordinatorId);
        pstm.executeUpdate();
    }
    
    public static List<Application> queryCompany(Connection conn) throws SQLException {
        String sql = "Select * from Application ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Application> list = new ArrayList<Application>();
        while (rs.next()) {
            String applicationId = rs.getString("app_id");
            String applicationCompany = rs.getString("app_company");
            String applicationAddress = rs.getString("app_address");
            String applicationName = rs.getString("app_name");
            String appplicationNumber = rs.getString("app_number");
            String applicationEmail = rs.getString("app_email");
            int applicationJob = rs.getInt("app_job");
            String applicationJobTitle = rs.getString("app_jobtitle");
            
           
            Application app = new Application(applicationId, applicationCompany, applicationAddress, applicationName,appplicationNumber, applicationEmail,applicationJob, applicationJobTitle);
            
            list.add(app);
        }
        return list;
    }
    
    public static void updateApplication(Connection conn, Application app) throws SQLException {
        String sql = "update application set app_company =?, app_name=?, app_address=?, app_number=?, app_email=?, app_job=?, app_jobtitle=? where app_id=?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, app.getApplicationCompany());
        pstm.setString(2, app.getApplicationName());
        pstm.setString(3, app.getApplicationAddress());
        pstm.setString(4, app.getAppplicationNumber());
        pstm.setString(5, app.getApplicationEmail());
        pstm.setInt(6, app.getApplicationJob());
        pstm.setString(7, app.getApplicationJobTitle());
        pstm.setString(8, app.getApplicationId());
        
        
        pstm.executeUpdate();
        
        out.println(conn);
        out.println(pstm);
        
    }
    
    public static List<History> queryHistoryPending(Connection conn, String coordinatorId) throws SQLException {
        String sql1 = "SELECT * FROM history";
        String sql2 = "SELECT * FROM student where co_id=?";
        
                PreparedStatement pstm1 = conn.prepareStatement(sql1);

        PreparedStatement pstm2 = conn.prepareStatement(sql2);
        pstm2.setString(1, coordinatorId);
        
        ResultSet rs1 = pstm1.executeQuery();
                ResultSet rs2 = pstm2.executeQuery();

        List<History> list = new ArrayList<History>();
        rs2.next();
      out.println("pending");
        
        while (rs1.next()) {
            if(rs1.getString("std_id").equals(rs2.getString("std_id"))&& rs1.getString("std_status").equals("P")){
                String historyId = rs1.getString("history_id");
             String appId = rs1.getString("app_id");
            String status = rs1.getString("std_status");
            java.util.Date appDate = rs1.getDate("app_date");
            String stdId = rs1.getString("std_id");
            
            History hist = new History(historyId, stdId, appId, status, appDate);
            
            list.add(hist);
            }
            
            
        }
        return list;
    }
    
    public static List<History> queryHistoryNotPending(Connection conn, String coordinatorId) throws SQLException {
        String sql1 = "SELECT * FROM history";
        String sql2 = "SELECT * FROM student where co_id=?";
        
                PreparedStatement pstm1 = conn.prepareStatement(sql1);

                out.println("not pending");
                
        PreparedStatement pstm2 = conn.prepareStatement(sql2);
        pstm2.setString(1, coordinatorId);
        
        ResultSet rs1 = pstm1.executeQuery();
                ResultSet rs2 = pstm2.executeQuery();

        List<History> list = new ArrayList<History>();
        rs2.next();
      
        
        while (rs1.next()) {
            if(rs1.getString("std_id").equals(rs2.getString("std_id"))&& !rs1.getString("std_status").equals("P")){
                String historyId = rs1.getString("history_id");
             String appId = rs1.getString("app_id");
            String status = rs1.getString("std_status");
            java.util.Date appDate = rs1.getDate("app_date");
            String stdId = rs1.getString("std_id");
            
            History hist = new History(historyId, stdId, appId, status, appDate);
            
            list.add(hist);
            }
            
            
        }
        return list;
    }
    
    
    public static History findHistory(Connection conn, String historyId) throws SQLException {
        String sql = "Select * from History where history_id=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, historyId);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            
             String appId = rs.getString("app_id");
            String status = rs.getString("std_status");
            String stdId = rs.getString("std_id");
            java.util.Date appDate = rs.getDate("app_date");
            
            History hist = new History(historyId, stdId, appId, status, appDate);
            
            return hist;
            
        }
        return null;
    }
}
