/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import java.sql.*;
/**
 *
 * @author NURUL AIMAN
 */
public class Student extends UserAccount{
    private String std_id;
    private String std_pw;
    private int std_level;
    private String std_name;
    private String std_gender;
    private String std_contact;
    private String std_email;
    private String std_matric;
    private String std_course;
    private float std_cgpa;
    private String std_status;
    private String co_id;
    private String app_id;

    public Student() {
    }

    public Student(String std_id, String std_pw, int std_level, String std_name, String std_gender, String std_contact, String std_email, String std_matric, String std_course, float std_cgpa, String std_status, String co_id, String app_id) {
        this.std_id = std_id;
        this.std_pw = std_pw;
        this.std_level = std_level;
        this.std_name = std_name;
        this.std_gender = std_gender;
        this.std_contact = std_contact;
        this.std_email = std_email;
        this.std_matric = std_matric;
        this.std_course = std_course;
        this.std_cgpa = std_cgpa;
        this.std_status = std_status;
        this.co_id = co_id;
        this.app_id = app_id;
    }

    public String getCo_id() {
        return co_id;
    }

    public void setCo_id(String co_idd) {
        this.co_id = co_id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }
    
    public String getStd_id() {
        return std_id;
    }

    public void setStd_id(String std_id) {
        this.std_id = std_id;
    }

    public String getStd_pw() {
        return std_pw;
    }

    public void setStd_pw(String std_pw) {
        this.std_pw = std_pw;
    }

    public int getStd_level() {
        return std_level;
    }

    public void setStd_level(int std_level) {
        this.std_level = std_level;
    }

    public String getStd_name() {
        return std_name;
    }

    public void setStd_name(String std_name) {
        this.std_name = std_name;
    }

    public String getStd_gender() {
        return std_gender;
    }

    public void setStd_gender(String std_gender) {
        this.std_gender = std_gender;
    }

    public String getStd_contact() {
        return std_contact;
    }

    public void setStd_contact(String std_contact) {
        this.std_contact = std_contact;
    }

    public String getStd_email() {
        return std_email;
    }

    public void setStd_email(String std_email) {
        this.std_email = std_email;
    }

    public String getStd_matric() {
        return std_matric;
    }

    public void setStd_matric(String std_matric) {
        this.std_matric = std_matric;
    }

    public String getStd_course() {
        return std_course;
    }

    public void setStd_course(String std_course) {
        this.std_course = std_course;
    }

    public float getStd_cgpa() {
        return std_cgpa;
    }

    public void setStd_cgpa(float std_cgpa) {
        this.std_cgpa = std_cgpa;
    }

    public String getStd_status() {
        return std_status;
    }

    public void setStd_status(String std_status) {
        this.std_status = std_status;
    }

public Student (ResultSet resultSet) throws SQLException
    {
        this.std_id = resultSet.getString("std_id");
        this.std_pw = resultSet.getString("std_pw");
        this.std_level = resultSet.getInt("std_level");
        this.std_name = resultSet.getString("std_name");
        this.std_gender = resultSet.getString("std_gender");
        this.std_contact = resultSet.getString("std_contact");
        this.std_email = resultSet.getString("std_email");
        this.std_matric = resultSet.getString("std_matric");
        this.std_course = resultSet.getString("std_course");
        this.std_cgpa = resultSet.getFloat("std_cgpa");
        this.std_status = resultSet.getString("std_status");
        this.co_id = resultSet.getString("co_id");
        this.app_id = resultSet.getString("app_id");
        
    }
   
}