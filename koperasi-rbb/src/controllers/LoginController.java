/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import utils.HashUtils;
import repository.AdminDAO;
import repository.NasabahDAO;

/**
 *
 * @author wtf
 */
public class LoginController {
    public static boolean verifyAdmin(String username, String password) {
        String adminUsername = AdminDAO.getUsername(username);
        
        if("".equals(adminUsername)) {
            return false;
        }
        
        String adminHashedPassword = AdminDAO.getHashedPassword(adminUsername);
        
        if("".equals(adminHashedPassword)) {
            return false;
        }
        
        String hashedPassword = HashUtils.hashPassword(password);
        
        return hashedPassword.equals(adminHashedPassword);
    }
    
    public static int verifyNasabah(String username, String password) {
        String nasabahUsername = NasabahDAO.getUsername(username);
        
        if("".equals(nasabahUsername)) {
            return -1;
        }
        
        String nasabahHashedPassword = NasabahDAO.getHashedPassword(nasabahUsername);
        
        if("".equals(nasabahHashedPassword)) {
            return -1;
        }
        
        String hashedPassword = HashUtils.hashPassword(password);
        
        boolean passSame = hashedPassword.equals(nasabahHashedPassword);
        
        if(passSame) {
            return NasabahDAO.getId(username);
        }
        
        return -1;
    }
}
