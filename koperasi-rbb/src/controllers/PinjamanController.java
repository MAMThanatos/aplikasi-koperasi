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
import models.SimpananModel;
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
    
    public static List<PinjamanModel> getAllAcceptedPinjaman() {
        return PinjamanDAO.getByStatus(StatusPinjamanEnum.DISETUJUI);
    }
    
    public static boolean UpdatePinjamanLunas(int idPinjaman) {
        return PinjamanDAO.updateStatus(idPinjaman, StatusPinjamanEnum.LUNAS);
    }
    
    public static boolean tolakPinjaman(int idPinjaman) {
        AngsuranDAO.deleteByPinjamanId(idPinjaman);
        return PinjamanDAO.updateStatus(idPinjaman, StatusPinjamanEnum.DITOLAK);
    }
    
    public static boolean setujuiPinjaman(int idPinjaman) {
        int totalSimpanan = SimpananModel.getTotalSimpanan();
        int nominal = PinjamanDAO.getNominalById(idPinjaman);
        
        if(nominal > totalSimpanan) return false;

        int tenor = PinjamanDAO.getTenorById(idPinjaman);
        boolean angsuranStatus = buatAngsuran(idPinjaman, nominal, tenor);
        
        if(!angsuranStatus) {
            PinjamanDAO.updateStatus(idPinjaman, StatusPinjamanEnum.MENUNGGU);
        }
        
        return PinjamanDAO.updateStatus(idPinjaman, StatusPinjamanEnum.DISETUJUI);
    }
    
    private static boolean buatAngsuran(int idPinjaman, int nominal, int tenor) {
        int cicilan = nominal / tenor;

        boolean hasCreatedAny = false;

        for (int bulan = 1; bulan <= tenor; bulan++) {
            AngsuranModel angsuran = new AngsuranModel();
            
            angsuran.setIdPinjaman(idPinjaman);
            angsuran.setAngsuranKe(bulan);
            angsuran.setNominalAngsuran(cicilan);
            angsuran.setStatus(StatusAngsuranEnum.BELUM_LUNAS);
            
            boolean success = AngsuranDAO.insert(angsuran);
            
//            boolean success = AngsuranModel.insertAngsuran(idPinjaman, bulan, cicilan, StatusAngsuranEnum.BELUM_LUNAS);
            if (!success) {
                System.err.println("Gagal membuat angsuran bulan ke-" + bulan + " untuk ID Pinjaman: " + idPinjaman);

                if (hasCreatedAny) {
                    AngsuranDAO.deleteByPinjamanId(idPinjaman);
                    System.out.println("Angsuran yang sudah terbuat dihapus karena error.");
                }

                return false;
            }

            hasCreatedAny = true;
        }

        return true;
    }
}
