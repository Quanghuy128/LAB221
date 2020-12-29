/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duc.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import duc.db.MyConnection;
import duc.dtos.StocksDTO;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class StocksDAO implements Serializable {

    private Connection conn = null;
    private Statement stm = null;

    private void closeConnection() throws Exception {
        if (stm != null) {
            stm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public boolean insert(List<StocksDTO> stocks) throws Exception {
        boolean isInserted = true;
        try {
            conn = MyConnection.getConnection();
            stm = conn.createStatement();
            for (StocksDTO stock : stocks) {
                String sql = "INSERT INTO dbo.Stocks(StockID, StockName, Address, DateAvailable, Note) VALUES('"
                        + stock.getId() + "', '"
                        + stock.getName() + "', '"
                        + stock.getAddress() + "', '"
                        + new java.sql.Date(stock.getDateAvailable().getTime()) + "', '"
                        + stock.getNote() + "')";
                stm.addBatch(sql);
            }
            stm.executeBatch();
            stm.clearBatch();
        } catch (SQLException se) {
            isInserted = false;
            se = new SQLException("[ERR] Some error records cannot insert to database, another is good");
            throw se;
        } finally {
            closeConnection();
        }
        return isInserted;
    }
}
