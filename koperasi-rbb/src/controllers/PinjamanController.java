/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import enums.StatusAngsuranEnum;
import enums.StatusPinjamanEnum;
import java.util.List;
import models.PinjamanModel;
import models.AngsuranModel;
import repository.SimpananDAO;
import repository.AngsuranDAO;
import repository.PinjamanDAO;

/**
 *
 * @author wtf
 */
public class PinjamanController {
    public static List<PinjamanModel> getAllPinjaman() {
        return PinjamanDAO.getAll();
    }
    
    public static List<PinjamanModel> getAllPinjamanByid(int idNasabah) {
        return PinjamanDAO.getAllById(idNasabah);
    }
    
    public static List<PinjamanModel> getAllAcceptedPinjaman() {
        return PinjamanDAO.getByStatus(StatusPinjamanEnum.DISETUJUI);
    }
    
    public static boolean UpdatePinjamanLunas(int idPinjaman) {
        return PinjamanDAO.updateStatus(idPinjaman, StatusPinjamanEnum.LUNAS);
    }
    
    public static boolean tolakPinjaman(int idPinjaman) {
        String currentStatus = PinjamanDAO.getStatusById(idPinjaman);

        if (!currentStatus.equalsIgnoreCase(StatusPinjamanEnum.MENUNGGU.getLabel())) return false;
        
        AngsuranDAO.deleteByPinjamanId(idPinjaman);
        return PinjamanDAO.updateStatus(idPinjaman, StatusPinjamanEnum.DITOLAK);
    }
    
    public static boolean setujuiPinjaman(int idPinjaman) {
        String currentStatus = PinjamanDAO.getStatusById(idPinjaman);

        if (!currentStatus.equalsIgnoreCase(StatusPinjamanEnum.MENUNGGU.getLabel())) return false;
        
        int totalSimpanan = SimpananDAO.getTotal();
        int nominal = PinjamanDAO.getNominalById(idPinjaman);
        
        if(nominal > totalSimpanan) return false;

        int tenor = PinjamanDAO.getTenorById(idPinjaman);
        boolean angsuranStatus = buatAngsuran(idPinjaman, nominal, tenor);
        
        if(!angsuranStatus) {
            PinjamanDAO.updateStatus(idPinjaman, StatusPinjamanEnum.MENUNGGU);
        }
        
        return PinjamanDAO.updateStatus(idPinjaman, StatusPinjamanEnum.DISETUJUI);
    }
    
    public static boolean batalkanPinjaman(int idPinjaman) {
        String currentStatus = PinjamanDAO.getStatusById(idPinjaman);
        
        if(!currentStatus.equalsIgnoreCase(StatusPinjamanEnum.MENUNGGU.getLabel())) return false;
        
        return PinjamanDAO.updateStatus(idPinjaman, StatusPinjamanEnum.DIBATALKAN);
    }
    
    private static boolean buatAngsuran(int idPinjaman, int nominal, int tenor) {
        int cicilan = (int) (Math.round((double) nominal / tenor / 1000) * 1000);
        int totalAngsuran = cicilan * tenor;
        int selisih = totalAngsuran - nominal;

        boolean hasCreatedAny = false;
        
        for (int bulan = 1; bulan <= tenor; bulan++) {
            AngsuranModel angsuran = new AngsuranModel();
            angsuran.setIdPinjaman(idPinjaman);
            angsuran.setAngsuranKe(bulan);

            if (bulan == tenor) {
                angsuran.setNominalAngsuran(cicilan - selisih);
            } else {
                angsuran.setNominalAngsuran(cicilan);
            }

            angsuran.setStatus(StatusAngsuranEnum.BELUM_LUNAS);

            boolean success = AngsuranDAO.insert(angsuran);
            
            if (!success) {
                if (hasCreatedAny) {
                    AngsuranDAO.deleteByPinjamanId(idPinjaman);
                }
                return false;
            }
            hasCreatedAny = true;
        }

        return true;
    }

    public static boolean ajukanPinjaman(int idNasabah, int nominal, int tenor, String alasan) {
      PinjamanModel newPinjaman = new PinjamanModel();
      
      newPinjaman.setIdNasabah(idNasabah);
      newPinjaman.setNominalPinjaman(nominal);
      newPinjaman.setTenor(tenor);
      newPinjaman.setKeterangan(alasan);
      newPinjaman.setStatus(StatusPinjamanEnum.MENUNGGU);
      
      return PinjamanDAO.insert(newPinjaman);
    }
}
