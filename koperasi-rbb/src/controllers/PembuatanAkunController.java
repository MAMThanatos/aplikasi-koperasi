/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import enums.StatusNasabahEnum;
import models.JabatanModel;
import models.NasabahModel;
import repository.NasabahDAO;
import repository.JabatanDAO;
import java.util.List;
import utils.HashUtils;

/**
 *
 * @author wtf
 */
public class PembuatanAkunController {
    private static boolean isUsernameExists(String username) {
        String rs = NasabahDAO.getUsername(username);
        
        return !"".equals(rs);
    }
    
    public static String createAccount(String namaLengkap, String username, String password, int idJabatan) {
        if(isUsernameExists(username)) {
            return "Username sudah digunakan";
        }
        
        if (password.length() < 6) {
            return "Password minimal 6 karakter.";
        }
        
        String hashedPassword = HashUtils.hashPassword(password);
        NasabahModel nasabah = new NasabahModel();
        
        nasabah.setNama(namaLengkap);
        nasabah.setUsername(username);
        nasabah.setHashedPassword(hashedPassword);
        nasabah.setStatus(StatusNasabahEnum.AKTIF);
        
        boolean rs = NasabahDAO.insert(nasabah, idJabatan);
        
        if(!rs) {
            return "Gagal membuat akun baru!";
        }
        
        return "Sukses membuat akun baru!";
    }
    
    
    public static List<JabatanModel> getAllJabatan() {
        return JabatanDAO.getAll();
    }
    
    public static int getIdJabatanBynama(String nama) {
        return JabatanDAO.getIdJabatanBynama(nama);
    }
}
