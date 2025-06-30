package koperasi.rbb;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import views.LoginView;

public class KoperasRbb {

    public static void main(String[] args) {
        if (!isDatabaseExist("koperasi_rbb")) {
            JOptionPane.showMessageDialog(
                null,
                "Database 'koperasi_rbb' tidak ditemukan!\nSilakan buat database terlebih dahulu.",
                "Database Tidak Ditemukan",
                JOptionPane.ERROR_MESSAGE
            );
            System.exit(1);
        }

        SwingUtilities.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }


    public static boolean isDatabaseExist(String dbName) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "")) {
            ResultSet rs = conn.getMetaData().getCatalogs();
            while (rs.next()) {
                if (rs.getString(1).equalsIgnoreCase(dbName)) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
