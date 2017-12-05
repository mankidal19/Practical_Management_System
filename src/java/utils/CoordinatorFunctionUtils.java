/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import beans.Application;
import beans.Coordinator;
import java.io.InputStream;
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
public class CoordinatorFunctionUtils {

    public static List<Application> queryCompany(Connection conn) throws SQLException {
        String sql = "Select * from Application ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Application> list = new ArrayList<Application>();
        while (rs.next()) {
          
            String applicationCompany = rs.getString("app_company");
            String applicationAddress = rs.getString("app_address");
            String applicationName = rs.getString("app_name");
            String appplicationNumber = rs.getString("app_number");
            String applicationEmail = rs.getString("app_email");
            int applicationJob = rs.getInt("app_job");
            String applicationJobTitle = rs.getString("app_jobtitle");
            
           
            Application user = new Application(applicationCompany, applicationAddress, applicationName,appplicationNumber, applicationEmail,applicationJob, applicationJobTitle);
            
            list.add(user);
        }
        return list;
    }

    public static String queryCompanyIndex(Connection conn) throws SQLException {
         String sql = "Select * from Application ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        String index = null;
        while (rs.next()) {
            index = rs.getString("app_id");
        }
        return index;
    }
    
    public static int uploadCoordinatorPhoto(Connection conn, String coID, InputStream inputStream) throws SQLException{
        // constructs SQL statement
            String sql = "UPDATE coordinator SET co_photo=? WHERE co_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(2, coID);
             
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                statement.setBlob(1, inputStream);
            }
 
            // sends the statement to the database server
            int row = statement.executeUpdate();
            System.out.println(statement);
            return row;
    }    
}
