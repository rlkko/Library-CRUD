/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author ricar
 */
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionManager {

    private static final String URL = "jdbc:mysql://localhost:3306/bookstore";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rikko";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public ConnectionManager() {
    }

    public static Connection openConnection() {
        try {
            Class.forName(DRIVER);
            Connection c = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return c;
        } catch (SQLException ex) {
            System.out.println("mysql jdbc class not found, please install it!");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return null;
    }

}
