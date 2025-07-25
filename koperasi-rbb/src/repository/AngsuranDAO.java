/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import enums.StatusAngsuranEnum;
import models.AngsuranModel;
import utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wtf
 */
public class AngsuranDAO {
    public static boolean insert(AngsuranModel angsuran) {
        String query = "INSERT INTO angsuran (id_pinjaman, angsuran_ke, nominal_angsuran, status) " +
                       "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, angsuran.getIdPinjaman());
            stmt.setInt(2, angsuran.getAngsuranke());
            stmt.setInt(3, angsuran.getNominalAngsuran());
            stmt.setString(4, angsuran.getStatus());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Insert Angsuran gagal: " + e.getMessage());
            return false;
        }
    }

    public static List<AngsuranModel> getByPinjamanId(int idPinjaman) {
        List<AngsuranModel> list = new ArrayList<>();
        String query = "SELECT a.id_angsuran, a.id_pinjaman, a.angsuran_ke, a.nominal_angsuran, a.tgl_pembayaran, mp.nama_metode_pembayaran, a.status " +
                       "FROM angsuran a LEFT JOIN metode_pembayaran mp ON a.id_metode_pembayaran = mp.id_metode_pembayaran " +
                       "WHERE a.id_pinjaman = ? ORDER BY angsuran_ke";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idPinjaman);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                AngsuranModel a = new AngsuranModel();
                a.setId(rs.getInt("id_angsuran"));
                a.setIdPinjaman(rs.getInt("id_pinjaman"));
                a.setAngsuranKe(rs.getInt("angsuran_ke"));
                a.setNominalAngsuran(rs.getInt("nominal_angsuran"));
                a.setTanggalPembayaran(rs.getDate("tgl_pembayaran"));
                a.setMetodePembayaran(rs.getString("nama_metode_pembayaran"));
                a.setStatus(StatusAngsuranEnum.fromString(rs.getString("status")));
                list.add(a);
            }

        } catch (SQLException e) {
            System.err.println("Gagal mengambil data angsuran: " + e.getMessage());
        }

        return list;
    }
    
    public static boolean semuaAngsuranLunas(int idPinjaman) {
        String query = "SELECT COUNT(*) FROM angsuran WHERE id_pinjaman = ? AND status != 'LUNAS'";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idPinjaman);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
        } catch (SQLException e) {
            System.err.println("Gagal cek semua angsuran lunas: " + e.getMessage());
        }
        return false;
    }
    
    public static boolean updateStatusLunas(AngsuranModel angsuran) {
         try (Connection conn = DatabaseConnection.connect()) {
            String query = "UPDATE angsuran SET status = ?, tgl_pembayaran = ?, id_metode_pembayaran = ? WHERE id_angsuran = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, angsuran.getStatus());
                stmt.setDate(2, new java.sql.Date(angsuran.getTanggalPembayaran().getTime()));
                stmt.setInt(3, angsuran.getIdMetodePembayaran());
                stmt.setInt(4, angsuran.getId());

                int affected = stmt.executeUpdate();
                return affected > 0;
            }
        } catch(SQLException e) {
            System.out.println(e);
        }

        return false;
    }

    public static boolean deleteByPinjamanId(int idPinjaman) {
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
