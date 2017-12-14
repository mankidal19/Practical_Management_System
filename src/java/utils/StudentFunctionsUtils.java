/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.*;
import beans.*;
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
        System.out.println("bubu");
        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, contact);
        pstm.setString(2, email);
        pstm.setString(3, stdID);
        pstm.executeUpdate();
    }
    
    public static void updateReport(Connection conn, Report report) throws SQLException {
        String sql = "Update Report set report_name=?, report_content=? where report_id=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, report.getReportName());
        pstm.setString(2, report.getReportContent());
        pstm.setString(3, report.getReportId());
        pstm.executeUpdate();
    }    
    
        public static List<Application> queryApplyCompany(Connection conn) throws SQLException {
        String sql = "Select * from Application where app_id=?";

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

    public static Student getStudentDetail(Connection conn, String std_id) throws SQLException {
        String sql = "Select * from Student "//
                + " where std_id = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, std_id);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int level = rs.getInt("std_level");
            String name = rs.getString("std_name");
            String password = rs.getString("std_pw");
            
            String gender = rs.getString("std_gender");
            String contact = rs.getString("std_contact");
            String email = rs.getString("std_email");
            String matric = rs.getString("std_matric");
            String course = rs.getString("std_course");
            float cgpa = rs.getFloat("std_cgpa");
            String status = rs.getString("std_status");
            String co = rs.getString("co_id");
            String app = rs.getString("app_id");

            //String password = rs.getString("std_pw");
            //String password = rs.getString("std_pw");
            Student user = new Student(std_id, password, level, name, gender, contact, email, matric, course, cgpa, status, co, app);
            //user.setUserName(userName);
            //user.setPassword(password);
            return user;
        }
        return null;
    }
        public static String queryReportIndex(Connection conn) throws SQLException {
        String sql = "Select * from Report";

        PreparedStatement pstm = conn.prepareStatement(sql);
        
        ResultSet rs = pstm.executeQuery();
        String index = null;
        while (rs.next()) {
            index = rs.getString("report_id");
        }
        return index;
    }
         
      public static List<Report> queryReport(Connection conn) throws SQLException {
        String sql = "Select * from Report where student_id";

        PreparedStatement pstm = conn.prepareStatement(sql);
        
        ResultSet rs = pstm.executeQuery();
        List<Report> list = new ArrayList<Report>();
        while (rs.next()) {
            
            String reportID = rs.getString("report_id");
            String reportName = rs.getString("report_name");
            String reportContent = rs.getString("report_content");
            java.sql.Date date = rs.getDate("report_date");
            
            String stdID = rs.getString("student_id");
            Report logbook = new Report(reportID, reportName, reportContent, stdID, date);
            
            list.add(logbook);
        }
        return list;
    }
      
        public static void deleteReport(Connection conn, String id) throws SQLException {
        String sql = "Delete From Report where report_id= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, id);

        pstm.executeUpdate();
    }
}
