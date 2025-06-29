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

/**
 *
 * @author wtf
 */
public class AdminDAO {
        public static String getUsername(String username) {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT username FROM admin WHERE username = ?";
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
    
    public static String getHashedPassword(String username) {
        try (Connection conn = DatabaseConnection.connect()) {
            String query = "SELECT hashed_password FROM admin WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getString("hashed_password"); 
                }
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        
        return "";
    }
}
