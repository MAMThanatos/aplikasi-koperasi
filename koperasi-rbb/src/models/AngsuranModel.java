/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import enums.StatusAngsuranEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import utils.DatabaseConnection;
/**
 *
 * @author wtf
 */
public class AngsuranModel {
    public static boolean insertAngsuran(int idPinjaman, int bulanKe, int nominal, StatusAngsuranEnum status) {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "INSERT INTO angsuran (id_pinjaman, bulan_ke, nominal_angsuran, status) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idPinjaman);
                stmt.setInt(2, bulanKe);
                stmt.setInt(3, nominal);
                stmt.setString(4, status.getLabel());
                
                int affected = stmt.executeUpdate();
                return affected > 0;
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return false;
    }
    
    public static boolean deleteAllByPinjamanId(int idPinjaman) {
        String query = "DELETE FROM angsuran WHERE id_pinjaman = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idPinjaman);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Gagal menghapus angsuran: " + e.getMessage());
            return false;
        }
    }
}
