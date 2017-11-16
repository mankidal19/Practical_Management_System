/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import beans.*;

/**
 *
 * @author NURUL AIMAN
 */
public class UserAccount {
    
   private String userName;
   private String password;
    
 
   public UserAccount() {
        
   }
    
   public String getUserName() {
       return userName;
   }
 
   public void setUserName(String userName) {
       this.userName = userName;
   }
 

   public String getPassword() {
       return password;
   }
 
   public void setPassword(String password) {
       this.password = password;
   }
}
