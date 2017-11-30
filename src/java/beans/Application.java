/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author NURUL AIMAN
 */
public class Application {
    private String applicationId;
    private String applicationCompany;
    private String applicationAddress;
    private String applicationName;
    private String appplicationNumber;
    private String applicationEmail;
    private int applicationJob;
    private String applicationJobTitle;

    public Application() {
    }

    public Application(String applicationId, String applicationCompany, String applicationAddress, String applicationName, String appplicationNumber, String applicationEmail, int applicationJob, String applicationJobTitle) {
        this.applicationId = applicationId;
        this.applicationCompany = applicationCompany;
        this.applicationAddress = applicationAddress;
        this.applicationName = applicationName;
        this.appplicationNumber = appplicationNumber;
        this.applicationEmail = applicationEmail;
        this.applicationJob = applicationJob;
        this.applicationJobTitle = applicationJobTitle;
    }

    public Application(String applicationCompany, String applicationAddress, String applicationName, String appplicationNumber, String applicationEmail, int applicationJob, String applicationJobTitle) {
        this.applicationCompany = applicationCompany;
        this.applicationAddress = applicationAddress;
        this.applicationName = applicationName;
        this.appplicationNumber = appplicationNumber;
        this.applicationEmail = applicationEmail;
        this.applicationJob = applicationJob;
        this.applicationJobTitle = applicationJobTitle;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationCompany() {
        return applicationCompany;
    }

    public void setApplicationCompany(String applicationCompany) {
        this.applicationCompany = applicationCompany;
    }

    public String getApplicationAddress() {
        return applicationAddress;
    }

    public void setApplicationAddress(String applicationAddress) {
        this.applicationAddress = applicationAddress;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getAppplicationNumber() {
        return appplicationNumber;
    }

    public void setAppplicationNumber(String appplicationNumber) {
        this.appplicationNumber = appplicationNumber;
    }

    public String getApplicationEmail() {
        return applicationEmail;
    }

    public void setApplicationEmail(String applicationEmail) {
        this.applicationEmail = applicationEmail;
    }

    public int getApplicationJob() {
        return applicationJob;
    }

    public void setApplicationJob(int applicationJob) {
        this.applicationJob = applicationJob;
    }

    public String getApplicationJobTitle() {
        return applicationJobTitle;
    }

    public void setApplicationJobTitle(String applicationJobTitle) {
        this.applicationJobTitle = applicationJobTitle;
    }
    
    
}
