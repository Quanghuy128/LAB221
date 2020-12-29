/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Khang Tran
 */
public class MyConnection implements Serializable {

    public static Connection makeConnection() {
        try {
            // Load driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Url String:
            String url = "jdbc:sqlserver://localhost:1433;databaseName=ItemSupplierManagement;instanceName=KHANGTL";
            // User database's info
            String user = "sa";
            String pass = "";
            // Connect to database server
            Connection conn = DriverManager.getConnection(url, user, pass);
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
