/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DatabaseConnection;
import models.SimpananModel;

/**
 *
 * @author wtf
 */

public class SimpananDAO {
    public static boolean insert(SimpananModel simpanan) {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "INSERT INTO simpanan (id_nasabah, nominal_simpanan, tgl_uang_masuk) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, simpanan.getIdNasabah());
                stmt.setInt(2, simpanan.getNominal());
                stmt.setDate(3, new java.sql.Date(simpanan.getTanggalUangMasuk().getTime()));
                
                int affected = stmt.executeUpdate();
                return affected > 0;
            }
        } catch(SQLException e) {
            System.err.println(e);
        }
        
        return false;
    }
    
    public static int getTotal() {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT SUM(nominal_simpanan) AS total_simpanan FROM simpanan";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("total_simpanan");
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return 0;
    }
}
