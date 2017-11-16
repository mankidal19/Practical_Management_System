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
public class Coordinator {
    private String coordinatorId;
    private String coordinatorPassword;
    private int coordinatorLevel;
    private String coordinatorName;
    private String coordinatorDepartment;
    private String coordinatorPosition;

    public Coordinator() {
    }

    public Coordinator(String coordinatorId, String coordinatorPassword, int coordinatorLevel, String coordinatorName, String coordinatorDepartment, String coordinatorPosition) {
        this.coordinatorId = coordinatorId;
        this.coordinatorPassword = coordinatorPassword;
        this.coordinatorLevel = coordinatorLevel;
        this.coordinatorName = coordinatorName;
        this.coordinatorDepartment = coordinatorDepartment;
        this.coordinatorPosition = coordinatorPosition;
    }

    public String getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(String coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public String getCoordinatorPassword() {
        return coordinatorPassword;
    }

    public void setCoordinatorPassword(String coordinatorPassword) {
        this.coordinatorPassword = coordinatorPassword;
    }

    public int getCoordinatorLevel() {
        return coordinatorLevel;
    }

    public void setCoordinatorLevel(int coordinatorLevel) {
        this.coordinatorLevel = coordinatorLevel;
    }

    public String getCoordinatorName() {
        return coordinatorName;
    }

    public void setCoordinatorName(String coordinatorName) {
        this.coordinatorName = coordinatorName;
    }

    public String getCoordinatorDepartment() {
        return coordinatorDepartment;
    }

    public void setCoordinatorDepartment(String coordinatorDepartment) {
        this.coordinatorDepartment = coordinatorDepartment;
    }

    public String getCoordinatorPosition() {
        return coordinatorPosition;
    }

    public void setCoordinatorPosition(String coordinatorPosition) {
        this.coordinatorPosition = coordinatorPosition;
    }
    
    
    
}
