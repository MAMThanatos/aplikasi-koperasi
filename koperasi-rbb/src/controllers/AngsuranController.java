/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import enums.StatusAngsuranEnum;
import java.util.Date;
import java.util.List;

import models.AngsuranModel;
import repository.AngsuranDAO;
/**
 *
 * @author wtf
 */
public class AngsuranController {
    public static List<AngsuranModel> getAllAngsuranByIdPinjaman(int idPinjaman) {
        return AngsuranDAO.getByPinjamanId(idPinjaman);
    }
    
    public static boolean updateAngsuranStatusLunas(int idPinjaman, int id, int idMetodePembayaran, Date tanggalPembayaran) {
        AngsuranModel angsuran = new AngsuranModel();
    
        angsuran.setId(id);
        angsuran.setIdMetodePembayaran(idMetodePembayaran);
        angsuran.setTanggalPembayaran(tanggalPembayaran);
        angsuran.setStatus(StatusAngsuranEnum.LUNAS);
        
        boolean updated = AngsuranDAO.updateStatusLunas(angsuran);
        
        if(updated) {
            boolean semuaLunas = AngsuranDAO.semuaAngsuranLunas(idPinjaman);
            
            if(semuaLunas) {
                PinjamanController.UpdatePinjamanLunas(idPinjaman);
            }
        }
        
        return updated;
    }
}
