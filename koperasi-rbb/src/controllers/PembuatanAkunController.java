/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import models.JabatanModel;
import models.NasabahModel;
import java.util.List;
import utils.HashUtils;

/**
 *
 * @author wtf
 */
public class PembuatanAkunController {
    private static boolean isUsernameExists(String username) {
        String rs = NasabahModel.getUsername(username);
        
        return !"".equals(rs);
    }
    
    public static String createAccount(String namaLengkap, String username, String password, int idJabatan, String status) {
        if(isUsernameExists(username)) {
            return "Username sudah digunakan";
        }
        
        if (password.length() < 6) {
            return "Password minimal 6 karakter.";
        }
        
        String hashedPassword = HashUtils.hashPassword(password);
        
        boolean rs = NasabahModel.insertNasabah(namaLengkap, username, hashedPassword, idJabatan, status);
        
        if(!rs) {
            return "Gagal membuat akun baru!";
        }
        
        return "Sukses membuat akun baru!";
    }
    
    
    public static List<String> getAllJabatan() {
        return JabatanModel.getAllJabatan();
    }
    
    public static int getIdJabatanBynama(String nama) {
        return JabatanModel.getIdJabatanBynama(nama);
    }
}
