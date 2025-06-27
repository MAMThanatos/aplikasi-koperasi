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

/**
 *
 * @author wtf
 */
public class PinjamanController {
    public static List<String[]> getAllPinjaman() {
        return PinjamanModel.getAllPinjaman();   
    }
    
    public static boolean tolakPinjaman(int idPinjaman) {
        AngsuranModel.deleteAllByPinjamanId(idPinjaman);
        return PinjamanModel.setStatus(idPinjaman, StatusPinjamanEnum.DITOLAK);
    }
    
    public static boolean setujuiPinjaman(int idPinjaman) {
        boolean success = PinjamanModel.setStatus(idPinjaman, StatusPinjamanEnum.DISETUJUI);
        
        if(!success) return false;
        boolean angsuranStatus = buatAngsuran(idPinjaman);
        
        if(!angsuranStatus) {
            PinjamanModel.setStatus(idPinjaman, StatusPinjamanEnum.MENUNGGU);
        }
        
       return true;
    }
    
    private static boolean buatAngsuran(int idPinjaman) {
        int tenor = PinjamanModel.getTenorById(idPinjaman);
        int nominal = PinjamanModel.getNominalById(idPinjaman);
        int cicilan = nominal / tenor;

        boolean hasCreatedAny = false;

        for (int bulan = 1; bulan <= tenor; bulan++) {
            boolean success = AngsuranModel.insertAngsuran(idPinjaman, bulan, cicilan, StatusAngsuranEnum.BELUM_LUNAS);
            if (!success) {
                System.err.println("Gagal membuat angsuran bulan ke-" + bulan + " untuk ID Pinjaman: " + idPinjaman);

                if (hasCreatedAny) {
                    AngsuranModel.deleteAllByPinjamanId(idPinjaman);
                    System.out.println("Angsuran yang sudah terbuat dihapus karena error.");
                }

                return false;
            }

            hasCreatedAny = true;
        }

        return true;
    }
}
