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
import java.util.List;
import java.util.ArrayList;
import models.MetodePembayaranModel;
import utils.DatabaseConnection;
        
/**
 *
 * @author wtf
 */
public class metodePembayaranDAO {
    public static List<MetodePembayaranModel> getAll() {
         List<MetodePembayaranModel> list = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT * FROM metode_pembayaran";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                   MetodePembayaranModel metode = new MetodePembayaranModel(rs.getInt("id_metode_pembayaran"), rs.getString("nama_metode_pembayaran"));
                    
                    list.add(metode);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return list;
    }
}
