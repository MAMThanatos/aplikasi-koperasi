/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DatabaseConnection;
import enums.StatusPinjamanEnum;

/**
 *
 * @author wtf
 */
public class PinjamanModel {
    public static List<String[]> getAllPinjaman() {
        List<String[]> pinjamanList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT p.id_pinjaman, n.nama_lengkap, p.nominal_pinjaman, p.alasan_pengajuan, p.tenor, p.tgl_pengajuan, sp.status " +
                           "FROM pinjaman p JOIN status_pinjaman sp ON p.id_pinjaman = sp.id_pinjaman " +
                           "JOIN nasabah n ON p.id_nasabah = n.id_nasabah";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String[] row = new String[8];
                    row[0] = rs.getString("id_pinjaman");
                    row[1] = rs.getString("nama_lengkap");
                    row[2] = rs.getString("nominal_pinjaman");
                    row[3] = rs.getString("alasan_pengajuan");
                    row[4] = rs.getString("tenor");
                    row[5] = rs.getString("tgl_pengajuan");
                    row[6] = rs.getString("status");

                    pinjamanList.add(row);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return pinjamanList;
    }
    
    public static int getNominalById(int idPinjaman) {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT nominal_pinjaman FROM pinjaman WHERE id_pinjaman = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idPinjaman);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("nominal_pinjaman");
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
    }
    
    public static int getTenorById(int idPinjaman) {
          try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT tenor FROM pinjaman WHERE id_pinjaman = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idPinjaman);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("tenor");
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return -1;
    }
    
    public static boolean setStatus(int idPinjaman, StatusPinjamanEnum status) {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "UPDATE status_pinjaman SET status = ? WHERE id_pinjaman = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, status.name());
                stmt.setInt(2, idPinjaman);
        
                int affected = stmt.executeUpdate();
                return affected > 0;
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        
        return false;
    }
}
