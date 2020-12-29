/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import khangtl.db.MyConnection;
import khangtl.dtos.StocksDTO;

/**
 *
 * @author Peter
 */
public class StocksDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;

    private void closeConnection() throws Exception {
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public void insert(List<StocksDTO> stocks) throws Exception {
        try {
            String sql = "INSERT INTO dbo.stocks(StockID, StockName, Address, DateAvailable, Note) VALUES(?, ?, ?, ?, ?)";
            conn = MyConnection.getConnection();
            conn.setAutoCommit(false);
            preStm = conn.prepareStatement(sql);
            int i = 0;
            for (StocksDTO stock : stocks) {
                preStm.setInt(1, stock.getId());
                preStm.setString(2, stock.getName());
                preStm.setString(3, stock.getAddress());
                preStm.setDate(4, new java.sql.Date(stock.getDateAvailable().getTime()));
                preStm.setString(5, stock.getNote());
                preStm.addBatch();
                i++;
                if (i % 5 == 0 || i == stocks.size()) {
                    preStm.executeBatch();
                }
            }
            conn.commit();
            preStm.clearBatch();
        } finally {
            closeConnection();
        }
    }
}
