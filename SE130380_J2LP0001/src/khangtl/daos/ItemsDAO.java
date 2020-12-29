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
import khangtl.dtos.ItemsDTO;

/**
 *
 * @author Peter
 */
public class ItemsDAO implements Serializable {

    // Initialize global variables
    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    ArrayList<ItemsDTO> Items;

    public ArrayList<ItemsDTO> getItems() {
        return Items;
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

    public void fetchItem() throws SQLException, SQLServerException, NamingException {
        String code;
        String name;
        String supCode;
        String unit;
        int price;
        boolean supplying;
        try {
            // Call connection
            conn = MyConnection.makeConnection();
            String sql = "SELECT itemCode, itemName, supCode, unit, price, supplying FROM dbo.items";
            preStm = conn.prepareStatement(sql);
            // Execute query string
            rs = preStm.executeQuery();
            // Check one by one line
            while (rs.next()) {
                code = rs.getString(1);
                name = rs.getString(2);
                supCode = rs.getString(3);
                unit = rs.getString(4);
                price = rs.getInt(5);
                supplying = rs.getBoolean(6);
                // Create item DTO with constructor
                ItemsDTO item = new ItemsDTO(code, name, supCode, unit, price, supplying);
                if (this.getItems() == null) {
                    this.Items = new ArrayList<>();
                }
                this.Items.add(item);
            }
        } finally {
            closeConnection();
        }
    }

    public boolean insertNewItem(String code, String name, String supCode, String unit, int price, boolean supplying) throws SQLException, SQLServerException, NamingException {
        boolean checkInsert = false;
        try {
            // Call connection
            conn = MyConnection.makeConnection();
            if (conn != null) {
                // Query string
                String sql = "INSERT INTO dbo.items (itemCode, itemName, supCode, unit, price, supplying) VALUES (?, ?, ?, ?, ?, ?)";
                // Create statement
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, code);
                preStm.setString(2, name);
                preStm.setString(3, supCode);
                preStm.setString(4, unit);
                preStm.setInt(5, price);
                preStm.setBoolean(6, supplying);
                // Execute query
                checkInsert = preStm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return checkInsert;
    }

    public boolean updateNewItem(String code, String name, String supCode, String unit, int price, boolean supplying) throws SQLException, SQLServerException, NamingException {
        boolean checkUpdate = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                // Query string
                String sql = "UPDATE dbo.items SET itemName=?, supCode=?, unit=?, price=?, supplying=? WHERE itemCode=?";
                // Create statement
                preStm = conn.prepareStatement(sql);
                preStm.setString(6, code);
                preStm.setString(1, name);
                preStm.setString(2, supCode);
                preStm.setString(3, unit);
                preStm.setInt(4, price);
                preStm.setBoolean(5, supplying);
                // Execute query
                checkUpdate = preStm.executeUpdate() > 0;
            }
        } finally {
            closeConnection();
        }
        return checkUpdate;
    }

    public boolean deleteItem(String code) throws SQLException, SQLServerException, NamingException {
        boolean checkDelete = false;
        try {
            conn = MyConnection.makeConnection();
            if (conn != null) {
                // Query string
                String sql = "DELETE FROM dbo.items WHERE itemCode=?";
                // Create statement
                preStm = conn.prepareStatement(sql);
                preStm.setString(1, code);
                // Execute query
                checkDelete = preStm.executeUpdate() > 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return checkDelete;
    }
}
