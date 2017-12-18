/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import beans.Application;
import beans.Coordinator;
import beans.Student;
import java.io.InputStream;
import static java.lang.System.out;
import java.sql.Blob;
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
    
    public static void updateStudent(Connection conn, String stdID, String status) throws SQLException {
       String sql2 = "update student set std_status = ? where std_id=?";

        PreparedStatement pstm2 = conn.prepareStatement(sql2);
        
       
        
        pstm2.setString(1, status);
        pstm2.setString(2, stdID);
        pstm2.executeUpdate();
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

    public static byte[] queryCoordinatorPhoto(Connection conn, String coordinatorId) throws SQLException {
        String sql = "Select * from coordinator WHERE co_id=? ";
        Blob img;
        byte[] imgData = null ;

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, coordinatorId);
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            img = rs.getBlob("co_photo");
            imgData = img.getBytes(1,(int)img.length());
        }

//        rs.close();
//        pstm.close();

        return imgData ;
    }

    public static List<Student> queryStudent(Connection conn, String coID) throws SQLException {
        String sql = "Select * from student Where co_id=?";
        List<Student> list = new ArrayList<Student>();
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, coID);
        ResultSet rs = pstm.executeQuery();
        
        while (rs.next()) { 
            int level = rs.getInt("std_level");
            float cgpa = rs.getFloat("std_cgpa");
            String userName = rs.getString("std_id");
            String name = rs.getString("std_name");
            String password = rs.getString("std_pw");
            String gender = rs.getString("std_gender");
            String contact = rs.getString("std_contact");
            String email = rs.getString("std_email");
            String matric = rs.getString("std_matric");
            String course = rs.getString("std_course");
            String status = rs.getString("std_status");
            String coordinatorId = rs.getString("co_id");
            String applicationId = rs.getString("app_id");
            int year = rs.getInt("std_year");

            Student user = new Student(userName, password, level, name, gender, contact, email, matric, course, cgpa, status, coordinatorId, applicationId,year);
            
            list.add(user);
        }
        return list;
    }
    
    public static int getNumOfApplication(Connection conn) throws SQLException{
      String sql = "Select count(*) as total from Application";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        int count=0;
        
        while (rs.next()) {
            count=rs.getInt("total");
        }
        
        out.println("num of rows:" + count);
        return count;
        
     }

     public static int getNumOfHistory(Connection conn, String coID) throws SQLException{
      String sql = "Select count(*) as total from history as a join student as b where b.co_id=? and a.std_id=b.std_id";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, coID);
        ResultSet rs = pstm.executeQuery();
        int count=0;
        
        while (rs.next()) {
            count=rs.getInt("total");
        }
        
        out.println("num of rows:" + count);
        return count;
        
     }

     
     public static int getNumOfStudent(Connection conn, String coID) throws SQLException{
      String sql = "Select count(*) as total from Student Where co_id=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, coID);
        ResultSet rs = pstm.executeQuery();
        int count=0;
        
        if (rs.next()) {
            count=rs.getInt("total");
        }
        
        out.println("num of rows:" + count);
        return count;
        
     }
    
}
