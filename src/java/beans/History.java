/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;

/**
 *
 * @author Nurfarahin Nadhirah
 */
public class History {
      private String histID;
      private String stdID;
      private String appID;
      private String appStatus;
      private Date appDate;
    

    public History(){
    }

    public History(String histID, String stdID, String appID, Date appDate) {
        this.histID = histID;
        this.stdID = stdID;
        this.appID = appID;
        this.appDate = appDate;
        this.appStatus = "P";
    }

    public History(String histID, String stdID, String appID, String appStatus, Date appDate) {
        this.histID = histID;
        this.stdID = stdID;
        this.appID = appID;
        this.appStatus = appStatus;
        this.appDate = appDate;
    }

    public String getHistID() {
        return histID;
    }

    public void setHistID(String histID) {
        this.histID = histID;
    }

    public String getStdID() {
        return stdID;
    }

    public void setStdID(String stdID) {
        this.stdID = stdID;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }
    
    
    
}