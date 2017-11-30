/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import beans.Application;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nurfarahin Nadhirah
 */
public class StudentFunctionUtils {
        public static List<Application> queryApplyCompany(Connection conn) throws SQLException {
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
            
           
            Application user = new Application(applicationId,applicationCompany, applicationAddress, applicationName,appplicationNumber, applicationEmail,applicationJob, applicationJobTitle);
            
            list.add(user);
        }
        return list;
    }

}
