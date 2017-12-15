/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Date;

/**
 *
 * @author NURUL AIMAN
 */
public class Report {
    private String reportId;
    private String reportName;
    private String reportContent;
    private String studentId;
    private Date date;
    

    public Report() {
    }

    public Report(String reportId, String reportName, String reportContent, String studentId, Date date) {
        this.reportId = reportId;
        this.reportName = reportName;
        this.reportContent = reportContent;
        this.studentId = studentId;
        this.date = date;
    }
        public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    
}
