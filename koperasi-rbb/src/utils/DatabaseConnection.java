/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author Aziz
 * @author wtf
 */

public class DatabaseConnection {
   public static Connection connect() {
        Connection conn = null;

        try {
            Properties prop = new Properties();
            InputStream input = new FileInputStream("src/config.properties");
            prop.load(input);

            String host = prop.getProperty("db.host");
            String port = prop.getProperty("db.port");
            String db = prop.getProperty("db.name");
            String user = prop.getProperty("db.user");
            String pass = prop.getProperty("db.pass");

            String url = "jdbc:mysql://" + host + ":" + port + "/" + db;

            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Koneksi berhasil");
        } catch (Exception e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }

        return conn;
    }
}
