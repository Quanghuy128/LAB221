/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.daos;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import khangtl.db.MyConnection;
import khangtl.dtos.SuppliersDTO;

/**
 *
 * @author Peter
 */
public class SuppliersDAO implements Serializable {
    
    // Initialize global variables
    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    ArrayList<SuppliersDTO> suppliers;

    public ArrayList<SuppliersDTO> getSuppliers() {
        return suppliers;
    }

    private void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchSupplier() throws SQLException, SQLServerException, NamingException {
        String code;
        String name;
        String address;
        boolean collaborating;
        try {
            // Call connection
            conn = MyConnection.makeConnection();
            String sql = "SELECT supCode, supName, address, collaborating FROM dbo.suppliers";
            preStm = conn.prepareStatement(sql);
            // Execute query string
            rs = preStm.executeQuery();
            // Check one by one line
            while (rs.next()) {
                code = rs.getString(1);
                name = rs.getString(2);
                address = rs.getString(3);
                collaborating = rs.getBoolean(4);
                // Create supplier DTO with constructor
                SuppliersDTO supplier = new SuppliersDTO(code, name, address, collaborating);
                if (this.getSuppliers() == null) {
                    this.suppliers = new ArrayList<>();
                }
                this.suppliers.add(supplier);
            }
        } finally {
            closeConnection();
        }
    }

    public boolean insertNewSupplier(String code, String name, String address, boolean collaborating) throws SQLException, SQLServerException, NamingException {
        boolean checkInsert = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                // Query string
                String sql = "INSERT INTO dbo.suppliers (supCode, supName, address, collaborating) VALUES (?, ?, ?, ?)";
                // Create statement
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, code);
                preStm.setString(2, name);
                preStm.setString(3, address);
                preStm.setBoolean(4, collaborating);
                // Execute query
                checkInsert = preStm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return checkInsert;
    }

    public boolean updateSupplier(String code, String name, String address, boolean collaborating) throws SQLException, SQLServerException, NamingException {
        boolean checkUpdate = false;
        try {
            conn = MyConnection.makeConnection();
            // Query string
            String sql = "UPDATE dbo.suppliers SET supName=?, address=?, collaborating=? WHERE supCode=?";
            // Create statement
            preStm = conn.prepareStatement(sql);
            preStm.setString(4, code);
            preStm.setString(1, name);
            preStm.setString(2, address);
            preStm.setBoolean(3, collaborating);
            // Execute query
            checkUpdate = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkUpdate;
    }

    public boolean deleteSupplier(String code) throws SQLException, SQLServerException, NamingException {
        boolean checkDelete = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                // Query string
                String sql = "DELETE FROM dbo.suppliers WHERE supCode=?";
                // Create statement
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, code);
                // Execute query
                checkDelete = preStm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return checkDelete;
    }
}
