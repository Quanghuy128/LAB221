/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import khangtl.db.MyConnection;
import khangtl.dtos.StocksDTO;

/**
 *
 * @author Peter
 */
public class StocksDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

    ArrayList<StocksDTO> Stocks;

    public ArrayList<StocksDTO> getStocks() {
        return Stocks;
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public void fetchStocks() throws Exception {
        int id;
        String name;
        String address;
        Date dateAvailable;
        String note;
        try {
            conn = MyConnection.getConnection();
            String sql = "SELECT StockID, StockName, Address, DateAvailable, Note FROM dbo.stocks";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
                name = rs.getString(2);
                address = rs.getString(3);
                dateAvailable = rs.getDate(4);
                note = rs.getString(5);

                StocksDTO stock = new StocksDTO(id, name, address, dateAvailable, note);
                if (this.getStocks() == null) {
                    this.Stocks = new ArrayList<>();
                }
                this.Stocks.add(stock);
            }
        } finally {
            closeConnection();
        }
    }
}
