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
import khangtl.dtos.BooksDTO;

/**
 *
 * @author Peter
 */
public class BooksDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement preStm = null;
    private ResultSet rs = null;

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

    public ArrayList<BooksDTO> fetchBooks() throws Exception {
        ArrayList<BooksDTO> result;
        String code, name, author, publisher;
        int publisherYear;
        boolean forRent;
        try {
            conn = MyConnection.getConnection();
            String sql = "SELECT BookCode, BookName, Author, Publisher, PublisherYear, ForRent FROM dbo.books";
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                code = rs.getString(1);
                name = rs.getString(2);
                author = rs.getString(3);
                publisher = rs.getString(4);
                publisherYear = rs.getInt(5);
                forRent = rs.getBoolean(6);
                BooksDTO book = new BooksDTO(code, name, author, publisher, publisherYear, forRent);
                result.add(book);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertNewBook(String code, String name, String author, String publisher, int publisherYear, boolean forRent) throws Exception {
        boolean checkInsert = false;
        try {
            // Call connection
            conn = MyConnection.getConnection();
            // Query string
            String sql = "INSERT INTO dbo.books (BookCode, BookName, Author, Publisher, PublisherYear, ForRent) VALUES (?, ?, ?, ?, ?, ?)";
            // Create statement
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, code);
            preStm.setString(2, name);
            preStm.setString(3, author);
            preStm.setString(4, publisher);
            preStm.setInt(5, publisherYear);
            preStm.setBoolean(6, forRent);
            // Execute query
            checkInsert = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkInsert;
    }

    public boolean updateNewBook(String code, String name, String author, String publisher, int publisherYear, boolean forRent) throws Exception {
        boolean checkUpdate = false;
        try {
            conn = MyConnection.getConnection();
            // Query string
            String sql = "UPDATE dbo.books SET BookName=?, Author=?, Publisher=?, PublisherYear=?, ForRent=? WHERE BookCode=?";
            // Create statement
            preStm = conn.prepareStatement(sql);
            preStm.setString(6, code);
            preStm.setString(1, name);
            preStm.setString(2, author);
            preStm.setString(3, publisher);
            preStm.setInt(4, publisherYear);
            preStm.setBoolean(5, forRent);
            // Execute query
            checkUpdate = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkUpdate;
    }

    public boolean deleteBook(String code) throws Exception {
        boolean checkDelete = false;
        try {
            conn = MyConnection.getConnection();
            // Query string
            String sql = "DELETE FROM dbo.books WHERE BookCode=?";
            // Create statement
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, code);
            // Execute query
            checkDelete = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return checkDelete;
    }
}
