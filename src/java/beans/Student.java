/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Nurfarahin Nadhirah
 */
public class Student extends UserAccount{
    private String stdID;
    private String stdPassword;
    private int stdLevel;
    private String stdName;
    private String stdGender;
    private String stdContact;
    private String stdEmail;
    private String stdMatric;
    private String stdCourse;
    private float stdCGPA;
    private String stdStatus;
    private String coID;
    private String appID;
    
    public Student(){
        
    }
    
    public Student(String stdID, String stdPassword, int stdLevel, String stdName, String stdGender, String stdContact, String stdEmail, String stdMatric, String stdCourse, float stdCGPA, String coID, String appID){
        this.stdID=stdID;
        this.stdPassword=stdPassword;
        this.stdLevel=stdLevel;
        this.stdName=stdName;
        this.stdGender=stdGender;
        this.stdContact=stdContact;
        this.stdEmail=stdEmail;
        this.stdMatric=stdMatric;
        this.stdCourse=stdCourse;
        this.stdCGPA=stdCGPA;
        this.stdStatus=stdStatus;
        this.coID=coID;
        this.appID=appID;
    }

    public String getStdID() {
        return stdID;
    }

    public void setStdID(String stdID) {
        this.stdID = stdID;
    }

    public String getStdPassword() {
        return stdPassword;
    }

    public void setStdPassword(String stdPassword) {
        this.stdPassword = stdPassword;
    }

    public int getStdLevel() {
        return stdLevel;
    }

    public void setStdLevel(int stdLevel) {
        this.stdLevel = stdLevel;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getStdGender() {
        return stdGender;
    }

    public void setStdGender(String stdGender) {
        this.stdGender = stdGender;
    }

    public String getStdContact() {
        return stdContact;
    }

    public void setStdContact(String stdContact) {
        this.stdContact = stdContact;
    }

    public String getStdEmail() {
        return stdEmail;
    }

    public void setStdEmail(String stdEmail) {
        this.stdEmail = stdEmail;
    }

    public String getStdMatric() {
        return stdMatric;
    }

    public void setStdMatric(String stdMatric) {
        this.stdMatric = stdMatric;
    }

    public String getStdCourse() {
        return stdCourse;
    }

    public void setStdCourse(String stdCourse) {
        this.stdCourse = stdCourse;
    }

    public float getStdCGPA() {
        return stdCGPA;
    }

    public void setStdCGPA(float stdCGPA) {
        this.stdCGPA = stdCGPA;
    }

    public String getStdStatus() {
        return stdStatus;
    }

    public void setStdStatus(String stdStatus) {
        this.stdStatus = stdStatus;
    }

    public String getCoID() {
        return coID;
    }

    public void setCoID(String coID) {
        this.coID = coID;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }
}