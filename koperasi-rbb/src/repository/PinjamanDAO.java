/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import enums.StatusPinjamanEnum;
import java.util.ArrayList;
import java.util.List;
import models.PinjamanModel;
import utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author wtf
 */
public class PinjamanDAO {
    public static boolean insert(PinjamanModel pinjaman) {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "INSERT INTO pinjaman (id_nasabah, nominal_pinjaman, tenor, alasan_pengajuan) VALUES (?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, pinjaman.getIdNasabah());
                stmt.setInt(2, pinjaman.getNominalPinjaman());
                stmt.setInt(3, pinjaman.getTenor());
                stmt.setString(4, pinjaman.getKeterangan());

                int affectedRows = stmt.executeUpdate();
                
                if(affectedRows > 0) {
                    try (ResultSet rs = stmt.getGeneratedKeys()) {
                        if (rs.next()) {
                            int generatedId = rs.getInt(1);
                            
                            query = "INSERT INTO status_pinjaman (id_pinjaman, status) VALUES (?, ?)";
                            
                            try (PreparedStatement statusStmt = conn.prepareStatement(query)) {
                                statusStmt.setInt(1, generatedId);
                                statusStmt.setString(2, pinjaman.getStatus());

                                statusStmt.executeUpdate();
                            }

                            return true;
                        }
                    }
                } 
            }
        } catch (SQLException e) {
            System.out.println("Error saat insert pinjaman: " + e.getMessage());
        }
        
        return false;
    }
    
    public static List<PinjamanModel> getAll() {
        List<PinjamanModel> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect()) {
           String query = "SELECT p.id_pinjaman, n.nama_lengkap, p.nominal_pinjaman, p.alasan_pengajuan, p.tenor, p.tgl_pengajuan, sp.status " +
                          "FROM pinjaman p JOIN status_pinjaman sp ON p.id_pinjaman = sp.id_pinjaman " +
                          "JOIN nasabah n ON p.id_nasabah = n.id_nasabah";

           try (PreparedStatement stmt = conn.prepareStatement(query)) {
               ResultSet rs = stmt.executeQuery();
               while (rs.next()) {
                   PinjamanModel pinjaman = new PinjamanModel();
                   pinjaman.setId(rs.getInt("id_pinjaman"));
                   pinjaman.setNamaNasabah(rs.getString("nama_lengkap"));
                   pinjaman.setNominalPinjaman(rs.getInt("nominal_pinjaman"));
                   pinjaman.setKeterangan(rs.getString("alasan_pengajuan"));
                   pinjaman.setTenor(rs.getInt("tenor"));
                   pinjaman.setTanggalPengajuan(rs.getDate("tgl_pengajuan"));
                   pinjaman.setStatus(StatusPinjamanEnum.fromString(rs.getString("status")));

                   list.add(pinjaman);
               }
           }
       } catch (SQLException e) {
           System.out.println(e);
       }
         
        return list;
    }
    
    public static List<PinjamanModel> getAllById(int id) {
        List<PinjamanModel> list = new ArrayList<>();
         try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT p.id_pinjaman, p.nominal_pinjaman, p.alasan_pengajuan, p.tenor, p.tgl_pengajuan, sp.status " +
                           "FROM pinjaman p JOIN status_pinjaman sp ON p.id_pinjaman = sp.id_pinjaman " +
                           "JOIN nasabah n ON p.id_nasabah = n.id_nasabah " +
                           "WHERE p.id_nasabah = ? ORDER BY p.tgl_pengajuan";
            
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    PinjamanModel pinjaman = new PinjamanModel();
                    pinjaman.setId(rs.getInt("id_pinjaman"));
                    pinjaman.setNominalPinjaman(rs.getInt("nominal_pinjaman"));
                    pinjaman.setKeterangan(rs.getString("alasan_pengajuan"));
                    pinjaman.setTenor(rs.getInt("tenor"));
                    pinjaman.setTanggalPengajuan(rs.getDate("tgl_pengajuan"));
                    pinjaman.setStatus(StatusPinjamanEnum.fromString(rs.getString("status")));
                    
                    list.add(pinjaman);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
         
        return list;
    }
    
    public static List<PinjamanModel> getByStatus(StatusPinjamanEnum status) {
        List<PinjamanModel> list = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT p.id_pinjaman, n.nama_lengkap, p.nominal_pinjaman, p.alasan_pengajuan, p.tenor, p.tgl_pengajuan, sp.status " +
                           "FROM pinjaman p JOIN status_pinjaman sp ON p.id_pinjaman = sp.id_pinjaman " +
                           "JOIN nasabah n ON p.id_nasabah = n.id_nasabah " +
                           "WHERE sp.status = ?";
            
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, status.getLabel());
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    PinjamanModel pinjaman = new PinjamanModel();
                    pinjaman.setId(rs.getInt("id_pinjaman"));
                    pinjaman.setNamaNasabah(rs.getString("nama_lengkap"));
                    pinjaman.setNominalPinjaman(rs.getInt("nominal_pinjaman"));
                    pinjaman.setKeterangan(rs.getString("alasan_pengajuan"));
                    pinjaman.setTenor(rs.getInt("tenor"));
                    pinjaman.setTanggalPengajuan(rs.getDate("tgl_pengajuan"));
                    pinjaman.setStatus(StatusPinjamanEnum.fromString(rs.getString("status")));
                    
                    list.add(pinjaman);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
         
        return list;
    }
    
    public static boolean updateStatus(int id, StatusPinjamanEnum status) {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "UPDATE status_pinjaman SET status = ? WHERE id_pinjaman = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, status.name());
                stmt.setInt(2, id);

                int affected = stmt.executeUpdate();
                return affected > 0;
            }
        } catch(SQLException e) {
            System.err.println(e);
        }

        return false;
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
    
    public static String getStatusById(int idPinjaman) {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT status FROM status_pinjaman WHERE id_pinjaman = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idPinjaman);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getString("status");
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
          
        return "";
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
}
