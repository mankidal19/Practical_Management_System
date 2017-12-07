/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.*;
import beans.Student;
import beans.Application;
import java.io.InputStream;
import java.util.*;

/**
 *
 * @author Nurfarahin Nadhirah
 */
public class StudentFunctionsUtils {
        public static Student displayStudent(Connection conn, String userName) throws SQLException {
        String sql = "SELECT * FROM Student" + "where std_id=?";
        
        PreparedStatement pstm = conn.prepareCall(sql);
        pstm.setString(1, userName);
        
        ResultSet rs = pstm.executeQuery();
        Student student = new Student();
        while (rs.next()){
            String stdID = rs.getString("std_id");     
            String stdPassword = rs.getString("std_pw");
            String stdName = rs.getString("std_name");           
            String stdGender = rs.getString("std_gender");
            String stdContact = rs.getString("std_contact");
            String stdEmail = rs.getString("std_email");
            String stdMatric = rs.getString("std_matric");
            String stdCourse = rs.getString("std_course");
            float stdCGPA = rs.getFloat("std_cgpa");
            String stdStatus = rs.getString("std_status");
            String coID = rs.getString("co_id");
            String appID = rs.getString("app_id");
            
            
            student.setStd_id(stdID);
            student.setStd_pw(stdPassword);
            student.setStd_name(stdName);
            student.setStd_gender(stdGender);
            student.setStd_contact(stdContact);
            student.setStd_email(stdEmail);
            student.setStd_matric(stdMatric);
            student.setStd_course(stdCourse);
            student.setStd_cgpa(stdCGPA);
            student.setStd_status(stdStatus);
            student.setCo_id(coID);
            student.setApp_id(appID);
            
        }
        return student;
    }
        
        public static void updateStudent(Connection conn, String stdID, String contact, String email) throws SQLException {
        String sql = "UPDATE student set std_contact=?, std_email=? where std_id=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, contact);
        pstm.setString(2, email);
        pstm.setString(3, stdID);
        pstm.executeUpdate();
    }
        
        public static List<Application> queryApplyCompany(Connection conn) throws SQLException {
        String sql = "Select * from Application";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Application> list = new ArrayList<Application>();
        while (rs.next()) {
            String appId = rs.getString("app_id");
            String appCompany = rs.getString("app_company");
            String appAddress = rs.getString("app_address");
            String appName = rs.getString("app_name");
            String appNumber = rs.getString("app_number");
            String appEmail = rs.getString("app_email");
            int appJob = rs.getInt("app_job");
            String appJobTitle = rs.getString("app_jobtitle");
            
            Application user = new Application(appId,appCompany, appAddress, appName,appNumber, appEmail, appJob, appJobTitle);
            
            list.add(user);
        }
        return list;
    }
        
        public static int uploadStudentPhoto(Connection conn, String stdID, InputStream inputStream) throws SQLException{
        // constructs SQL statement
            String sql = "UPDATE student SET std_photo=? WHERE std_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(2, stdID);
             
            if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                statement.setBlob(1, inputStream);
            }
 
            // sends the statement to the database server
            int row = statement.executeUpdate();
            System.out.println(statement);
            return row;
    } 

    public static byte[] queryStudentPhoto(Connection conn, String stdId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
}
