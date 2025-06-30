/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    
    public static List<SimpananModel> getAllSimpananByIdNasabah(int idNasabah) {
        List<SimpananModel> list = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT nominal_simpanan, tgl_uang_masuk FROM simpanan " +
                           "WHERE id_nasabah = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idNasabah);
                ResultSet rs = stmt.executeQuery();
                
                while(rs.next()) {
                    SimpananModel simpanan = new SimpananModel();
                    simpanan.setNominal(rs.getInt("nominal_simpanan"));
                    simpanan.setTanggalUangMasuk(rs.getDate("tgl_uang_masuk"));
                    
                    list.add(simpanan);
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return list;
    }
    
    public static int getTotalByIdNasabah(int idNasabah) {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT SUM(nominal_simpanan) AS total_simpanan FROM simpanan " +
                           "WHERE id_nasabah = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idNasabah);
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
