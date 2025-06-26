/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import models.AdminModel;
import utils.HashUtils;
/**
 *
 * @author wtf
 */
public class LoginController {
    public static boolean verifyAdmin(String username, String password) {
        String adminUsername = AdminModel.getUsername(username);
        
        if("".equals(adminUsername)) {
            return false;
        }
        
        String adminHashedPassword = AdminModel.getHashedPassword(adminUsername);
        
        if("".equals(adminHashedPassword)) {
            return false;
        }
        
        String hashedPassword = HashUtils.hashPassword(password);
        
        if(hashedPassword.equals(adminHashedPassword)) {
            return true;
        }
        
        return false;
    }
    
    public static boolean verifyNasabah(String username, String password) {
        return false;
    }
}
