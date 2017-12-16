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
public class History {
      private String stdName;
      private String stdMatric;
      private String companyName;
      private String stdStatus;
    

    public History(){
    }

    public History(String stdName, String stdMatric, String companyName, String stdStatus) {
        this.stdName=stdName;
        this.stdMatric=stdMatric;
        this.companyName=companyName;
        this.stdStatus=stdStatus;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getStdMatric() {
        return stdMatric;
    }

    public void setStdMatric(String stdMatric) {
        this.stdMatric = stdMatric;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStdStatus() {
        return stdStatus;
    }

    public void setStdStatus(String stdStatus) {
        this.stdStatus = stdStatus;
    }
          
}