/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DatabaseConnection;
/**
 *
 * @author wtf
 */
public class SimpananModel {
    public static boolean insertSimpanan(int idNasabah, int nominalSimpanan, Date tglUangMasuk) {
        return true;
    }
    
    public static int getTotalSimpanan() {
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
