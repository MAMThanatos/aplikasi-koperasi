/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DatabaseConnection;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author wtf
 */

public class NasabahModel {
    public static String[][] getAllNasabah() {
        List<String[]> nasabahList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT n.id_nasabah, j.nama_jabatan, n.nama_lengkap AS nama, n.username, n.status " +
                           "FROM nasabah n JOIN jabatan j ON n.id_jabatan = j.id_jabatan";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String[] row = new String[5];
                    row[0] = rs.getString("id_nasabah");
                    row[1] = rs.getString("nama_jabatan");
                    row[2] = rs.getString("nama");
                    row[3] = rs.getString("username");
                    row[4] = rs.getString("status");
                    nasabahList.add(row);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return nasabahList.toArray(String[][]::new);
    }
    
    public static String getUsername(String username) {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT username FROM nasabah WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getString("username"); 
                }
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        
        return "";
    }
    
    public static boolean insertNasabah(String namaLengkap, String username, String hashedPassword, int idJabatan, String status) {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "INSERT INTO nasabah (id_jabatan, nama_lengkap, username, hashed_password, status) VALUES(?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idJabatan);
                stmt.setString(2, namaLengkap);
                stmt.setString(3, username);
                stmt.setString(4, hashedPassword);
                stmt.setString(5, status);
                stmt.executeUpdate();
                
                return true;
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        
        return false;
    }
}
