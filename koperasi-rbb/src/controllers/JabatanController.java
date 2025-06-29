/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import models.JabatanModel;
import repository.JabatanDAO;

/**
 *
 * @author wtf
 */
public class JabatanController {
    public static List<JabatanModel> getAllJabatan() {
        return JabatanDAO.getAll();
    }
    
    public static int getIdJabatanBynama(String nama) {
        return JabatanDAO.getIdJabatanBynama(nama);
    } 
}
