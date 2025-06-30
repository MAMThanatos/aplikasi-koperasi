/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import repository.NasabahDAO;
import models.NasabahModel;
import utils.HashUtils;
        
/**
 *
 * @author wtf
 */
public class DataNasabahController {
    public static List<NasabahModel> getAllNasabah() {
        return NasabahDAO.getAll();
    }
    
    public static boolean updateNasabah(NasabahModel nasabah) {
        String newPassword = nasabah.getPassword();
        
        nasabah.setIdJabatan(JabatanController.getIdJabatanBynama(nasabah.getJabatan()));
        
        if(!newPassword.isEmpty()) {
            nasabah.setHashedPassword(HashUtils.hashPassword(newPassword));
        }
        
        
        return NasabahDAO.update(nasabah);
    }
    
    public static NasabahModel getNasabahById(int id) {
        return NasabahDAO.getById(id);
    }
}
