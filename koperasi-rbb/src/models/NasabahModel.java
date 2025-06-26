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

/**
 *
 * @author wtf
 */
public class NasabahModel {
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
