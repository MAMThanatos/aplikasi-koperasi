/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.Date;
import models.SimpananModel;
import repository.SimpananDAO;
import java.util.List;
        
/**
 *
 * @author wtf
 */
public class SimpananController {
    public static boolean insertSimpanan(int idNasabah, int nominalSimpanan, Date tglUangMasuk) {
        SimpananModel simpanan = new SimpananModel();
        
        simpanan.setIdNasabah(idNasabah);
        simpanan.setNominal(nominalSimpanan);
        simpanan.setTanggalUangMasuk(tglUangMasuk);
        
        return SimpananDAO.insert(simpanan);
    }
    
    public static int getTotalSimpananNasabah(int idNasabah) {
        return SimpananDAO.getTotalByIdNasabah(idNasabah);
    }
    
    public static List<SimpananModel> getAllSimpananNasabah(int idNasabah) {
        return SimpananDAO.getAllSimpananByIdNasabah(idNasabah);
    }
}
