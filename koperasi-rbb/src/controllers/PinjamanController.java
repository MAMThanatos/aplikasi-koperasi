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
        int totalSimpanan = SimpananModel.getTotalSimpanan();
        int nominal = PinjamanModel.getNominalById(idPinjaman);
        
        if(nominal > totalSimpanan) return false;

        int tenor = PinjamanModel.getTenorById(idPinjaman);
        boolean angsuranStatus = buatAngsuran(idPinjaman, nominal, tenor);
        
        if(!angsuranStatus) {
            PinjamanModel.setStatus(idPinjaman, StatusPinjamanEnum.MENUNGGU);
        }
        
        return PinjamanModel.setStatus(idPinjaman, StatusPinjamanEnum.DISETUJUI);
    }
    
    private static boolean buatAngsuran(int idPinjaman, int nominal, int tenor) {
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
