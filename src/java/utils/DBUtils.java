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
import static java.lang.System.out;

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
            String co = rs.getString("co_id");
            String app = rs.getString("app_id");

            //String password = rs.getString("std_pw");
            //String password = rs.getString("std_pw");
            Student user = new Student(userName, password, level, name, gender, contact, email, matric, course, cgpa, status, co, app);
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
    
    public static Student displayStudent(Connection conn, String userName) throws SQLException {
        String sql = "SELECT * FROM Student"+"where std_id=?";
        
        PreparedStatement pstm = conn.prepareCall(sql);
        pstm.setString(1, userName);
        
        ResultSet rs = pstm.executeQuery();
        Student student = new Student();
        while (rs.next()){
            String id = rs.getString("std_id");           
            String name = rs.getString("std_name");           
            String gender = rs.getString("std_gender");
            String contact = rs.getString("std_contact");
            String email = rs.getString("std_email");
            String matric = rs.getString("std_matric");
            String course = rs.getString("std_course");
            float cgpa = rs.getFloat("std_cgpa");
            String status = rs.getString("std_status");
            String co = rs.getString("co_id");
            String app = rs.getString("app_id");
            
            
            student.setStd_id(id);
            student.setStd_matric(matric);
            student.setStd_name(name);
            student.setStd_contact(contact);
            student.setStd_email(email);
            student.setStd_gender(gender);
            student.setStd_course(course);
            student.setStd_cgpa(cgpa);
            student.setStd_status(status);
            student.setCo_id(co);
            student.setApp_id(app);

        }
        return student;
    }
    
    public static Coordinator displayCoordinator(Connection conn, String userName) throws SQLException {
        String sql = "SELECT * FROM Coordinator"+"where co_id=?";
        
        PreparedStatement pstm = conn.prepareCall(sql);
        pstm.setString(1, userName);
        
        ResultSet rs = pstm.executeQuery();
        Coordinator coordinator = new Coordinator();
        
        while (rs.next()){
            String id = rs.getString("co_id");           
            String name = rs.getString("co_name");           
            String department = rs.getString("co_department");
            String position = rs.getString("co_position");
            
            
            
            coordinator.setCoordinatorId(id);
            coordinator.setCoordinatorName(name);
            coordinator.setCoordinatorDepartment(department);
            coordinator.setCoordinatorPosition(position);
            

        }
        return coordinator;
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

    public static void updateCoordinator(Connection conn, Coordinator co) throws SQLException {
        String sql = "update coordinator set co_pw=?, co_level=?, co_name =?, co_department=?, co_position=? where co_id=? ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(3, co.getCoordinatorName());
        pstm.setInt(2, co.getCoordinatorLevel());
        pstm.setString(4, co.getCoordinatorDepartment());
        pstm.setString(5, co.getCoordinatorPosition());
        pstm.setString(1, co.getCoordinatorPassword());
        pstm.setString(6, co.getCoordinatorId());
        
        pstm.executeUpdate();
        
        out.println(conn);
        out.println(pstm);
        
    }

    
    public static void insertProduct(Connection conn, Product product) throws SQLException {
        String sql = "Insert into Product(Code, Name,Price) values (?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, product.getCode());
        pstm.setString(2, product.getName());
        pstm.setFloat(3, product.getPrice());

        pstm.executeUpdate();
    }
    
    public static void insertCoordinator(Connection conn, Coordinator co) throws SQLException {
        String sql = "Insert into Coordinator(co_id, co_pw, co_level, co_name, co_department, co_position) values (?,?,?,?,?,?)";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(4, co.getCoordinatorName());
        pstm.setInt(3, co.getCoordinatorLevel());
        pstm.setString(5, co.getCoordinatorDepartment());
        pstm.setString(6, co.getCoordinatorPosition());
        pstm.setString(2, co.getCoordinatorPassword());
        pstm.setString(1, co.getCoordinatorId());

        pstm.executeUpdate();
    }

    public static void deleteProduct(Connection conn, String code) throws SQLException {
        String sql = "Delete From Product where Code= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, code);

        pstm.executeUpdate();
    }
    
    public static void deleteCoordinator(Connection conn, String id) throws SQLException {
        String sql = "Delete From Coordinator where co_id= ?";

        PreparedStatement pstm = conn.prepareStatement(sql);

        pstm.setString(1, id);

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
     
     public static int getNumOfCoordinator(Connection conn) throws SQLException{
      String sql = "Select count(*) as total from Coordinator ";

        PreparedStatement pstm = conn.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        int count=0;
        
        while (rs.next()) {
            count=rs.getInt("total");
        }
        
        out.println("num of rows:" + count);
        return count;
        
     }

}
