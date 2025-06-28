/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import enums.StatusNasabahEnum;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DatabaseConnection;
import java.util.List;
import java.util.ArrayList;
import models.NasabahModel;

/**
 *
 * @author wtf
 */
public class NasabahDAO {
    public static List<NasabahModel> getAll() {
        List<NasabahModel> list = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT n.id_nasabah, j.nama_jabatan, n.nama_lengkap AS nama, n.username, n.status " +
                           "FROM nasabah n JOIN jabatan j ON n.id_jabatan = j.id_jabatan";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    NasabahModel nasabah = new NasabahModel();
                    
                    nasabah.setId(rs.getInt("id_nasabah"));
                    nasabah.setJabatan(rs.getString("nama_jabatan"));
                    nasabah.setNama(rs.getString("nama"));
                    nasabah.setUsername(rs.getString("username"));
                    nasabah.setStatus(StatusNasabahEnum.fromString(rs.getString("status")));
                    
                    list.add(nasabah);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
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
    
    public static boolean insert(NasabahModel nasabah, int idJabatan) {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "INSERT INTO nasabah (id_jabatan, nama_lengkap, username, hashed_password, status) VALUES(?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idJabatan);
                stmt.setString(2, nasabah.getNama());
                stmt.setString(3, nasabah.getUsername());
                stmt.setString(4, nasabah.getHashedPassword());
                stmt.setString(5, nasabah.getStatus());
                stmt.executeUpdate();
                
                return true;
            }
        } catch(SQLException e) {
            System.out.println(e);
        }

        return false;
    }
}
