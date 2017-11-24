/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author NURUL AIMAN
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import beans.Product;
import beans.*;

public class DBUtils {

//    public static UserAccount findUser(Connection conn, //
//            String userName, String password) throws SQLException {
//
//        String sql = "SELECT admin_id,admin_pw  FROM admin WHERE admin_id=? AND admin_pw=?"
//                + "UNION SELECT std_id,std_pw FROM student WHERE std_id=? AND std_pw=?"
//                + "UNION SELECT co_id,co_pw FROM coordinator WHERE co_id=? AND co_pw=?";
//
//        PreparedStatement pstm = conn.prepareStatement(sql);
//        pstm.setString(1, userName);
//        pstm.setString(2, password);
//        pstm.setString(3, userName);
//        pstm.setString(4, password);
//        pstm.setString(5, userName);
//        pstm.setString(6, password);
//        
//        ResultSet rs = pstm.executeQuery();
//
//        if (rs.next()) {
//            UserAccount user = new UserAccount();
//            user.setUserName(userName);
//            user.setPassword(password);
//            return user;
//        }
//        return null;
//    }

   /* public static UserAccount findUser(Connection conn, //
            String userName, String password) throws SQLException {

        String sql = "SELECT admin_id,admin_pw  FROM admin WHERE admin_id=? AND admin_pw=?"
                + "UNION SELECT std_id,std_pw FROM student WHERE std_id=? AND std_pw=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);
        pstm.setString(3, userName);
        pstm.setString(4, password);
        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            UserAccount user = new UserAccount();
            user.setUserName(userName);
            user.setPassword(password);
            return user;
        }
        return null;
    }
*/
    public static Student findStudent(Connection conn, String userName, String password) throws SQLException {

        String sql = "Select * from Student "//
                + " where std_id = ? and std_pw = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int level = rs.getInt("std_level");
            String name = rs.getString("std_name");
            String gender = rs.getString("std_gender");
            String contact = rs.getString("std_contact");
            String email = rs.getString("std_email");
            String matric = rs.getString("std_matric");
            String course = rs.getString("std_course");
            float cgpa = rs.getFloat("std_cgpa");
            String status = rs.getString("std_status");
            String coordinatorId = rs.getString("co_id");
            String appId = rs.getString("app_id");

            //String password = rs.getString("std_pw");
            //String password = rs.getString("std_pw");
            Student user = new Student(userName, password, level, name, gender, contact, email, matric, course, cgpa, status, coordinatorId, appId);
            //user.setUserName(userName);
            //user.setPassword(password);
            return user;
        }
        return null;
    }

    public static Student findStudent(Connection conn, String userName) throws SQLException {

        String sql = "Select * from Student "//
                + " where std_id = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);

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
            String coordinatorId = rs.getString("co_id");
            String appId = rs.getString("app_id");

            //String password = rs.getString("std_pw");
            //String password = rs.getString("std_pw");
            Student user = new Student(userName, password, level, name, gender, contact, email, matric, course, cgpa, status, coordinatorId, appId);
            //user.setUserName(userName);
            //user.setPassword(password);
            return user;
        }
        return null;
    }

    public static Coordinator findCoordinator(Connection conn, String userName, String password) throws SQLException {

        String sql = "Select * from Coordinator "//
                + " where co_id = ? and co_pw = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int level = rs.getInt("co_level");
            String name = rs.getString("co_name");
            String department = rs.getString("co_department");
            String position = rs.getString("co_position");
            
            //String password = rs.getString("std_pw");
            //String password = rs.getString("std_pw");
            Coordinator user = new Coordinator(userName, password, level, name, department, position);
            //user.setUserName(userName);
            //user.setPassword(password);
            return user;
        }
        return null;
    }

    public static Coordinator findCoordinator(Connection conn, String userName) throws SQLException {

        String sql = "Select * from Coordinator "//
                + " where co_id = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int level = rs.getInt("co_level");
            String name = rs.getString("co_name");
            String password = rs.getString("co_pw");
            String department = rs.getString("co_department");
            String position = rs.getString("co_position");
            
            //String password = rs.getString("std_pw");
            //String password = rs.getString("std_pw");
            Coordinator user = new Coordinator(userName, password, level, name, department, position);
            //user.setUserName(userName);
            //user.setPassword(password);
            return user;
        }
        return null;
    }

    
    public static List<Product> queryProduct(Connection conn) throws SQLException {
        String sql = "Select a.Code, a.Name, a.Price from Product a ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Product> list = new ArrayList<Product>();
        while (rs.next()) {
            String code = rs.getString("Code");
            String name = rs.getString("Name");
            float price = rs.getFloat("Price");
            Product product = new Product();
            product.setCode(code);
            product.setName(name);
            product.setPrice(price);
            list.add(product);
        }
        return list;
    }
    
    public static Admin findAdmin(Connection conn, String userName, String password) throws SQLException {

        String sql = "Select * from Admin "//
                + " where admin_id = ? and admin_pw = ? ";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);
        pstm.setString(2, password);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int level = rs.getInt("admin_level");
            String name = rs.getString("admin_name");
            
            //String password = rs.getString("std_pw");
            //String password = rs.getString("std_pw");
            Admin user = new Admin(userName, password, level, name);
            //user.setUserName(userName);
            //user.setPassword(password);
            return user;
        }
        return null;
    }


     public static Admin findAdmin(Connection conn, String userName) throws SQLException {

        String sql = "Select * from Admin "//
                + " where admin_id = ?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, userName);

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            int level = rs.getInt("admin_level");
            String name = rs.getString("admin_name");
            
            String password = rs.getString("admin_pw");
            //String password = rs.getString("std_pw");
            Admin user = new Admin(userName, password, level, name);
            //user.setUserName(userName);
            //user.setPassword(password);
            return user;
        }
        return null;
    }

    
    public static Product findProduct(Connection conn, String code) throws SQLException {
        String sql = "Select a.Code, a.Name, a.Price from Product a where a.Code=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, code);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            String name = rs.getString("Name");
            float price = rs.getFloat("Price");
            Product product = new Product(code, name, price);
            return product;
        }
        return null;
    }

    public static void updateProduct(Connection conn, Product product) throws SQLException {
        String sql = "Update Product set Name =?, Price=? where Code=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, product.getName());
        pstm.setFloat(2, product.getPrice());
        pstm.setString(3, product.getCode());
        pstm.executeUpdate();
    }

    public static void insertProduct(Connection conn, Product product) throws SQLException {
        String sql = "Insert into Product(Code, Name,Price) values (?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, product.getCode());
        pstm.setString(2, product.getName());
        pstm.setFloat(3, product.getPrice());

        pstm.executeUpdate();
    }

    public static void deleteProduct(Connection conn, String code) throws SQLException {
        String sql = "Delete From Product where Code= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, code);

        pstm.executeUpdate();
    }
    
     public static List<Coordinator> queryCoordinator(Connection conn) throws SQLException {
        String sql = "Select * from Coordinator ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Coordinator> list = new ArrayList<Coordinator>();
        while (rs.next()) {
            //String code = rs.getString("Code");
            //String name = rs.getString("Name");
            //float price = rs.getFloat("Price");
            
            int level = rs.getInt("co_level");
            String userName = rs.getString("co_id");
            String name = rs.getString("co_name");
            String password = rs.getString("co_pw");
            String department = rs.getString("co_department");
            String position = rs.getString("co_position");
            
           
            Coordinator user = new Coordinator(userName, password, level, name, department, position);
            
            list.add(user);
        }
        return list;
    }
    

}
