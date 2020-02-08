/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Vector;
import model.dto.BooksDTO;

/**
 *
 * @author THE HIEN
 */
public class BooksDAO {
    Connection conn;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;

    public void closeConnection() throws SQLException {

        if (rs != null) {
            rs.close();
        }
        if (pst != null) {
            pst.close();
        }
        if (st != null) {
            st.close();
        }
        if (conn != null) {
            conn.close();
        }

    }
    
    public Vector<BooksDTO> searchBook(String input) throws ClassNotFoundException, SQLException{
        Vector<BooksDTO> list = new Vector<>();
        String sql = "SELECT BookID, BookTitle, Price, Author, Quantity, Categories, ImageName, ImportDate, Status from Books "
                + "WHERE BookTitle LIKE ?";
        
        String id, title, author, category, imgName, status;
        int quantity;
        float price;
        Timestamp importDate;
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, "Active");
            pst.setString(2, "%" + input + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getString("BookID");
                title = rs.getString("BookTitle");
                price = rs.getFloat("Price");
                author = rs.getString("Author");
                quantity = rs.getInt("Quantity");
                category = rs.getString("Categories");
                importDate = rs.getTimestamp("ImportDate");
                imgName = rs.getString("ImageName");
                status = rs.getString("Status");
                
                list.add(new BooksDTO(id, title, author, category, imgName, quantity, price, importDate, status));
            }

        } finally {
            closeConnection();
        }
        
        return list;
    }
    
    public boolean deleteBook(String id) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE Books SET Status=? WHERE BookID=?";
        int affectedRow = 0;
        
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, "Inactive");
            pst.setString(2, id);
            affectedRow = pst.executeUpdate();
            
        } finally{
            closeConnection();
        }
        
        return affectedRow != 0;
    }
    
    public boolean updateBook(BooksDTO book) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE Books SET BookTitle=?, Price=?, Quantity=?, Author=?, Categories=?, ImageName=? WHERE BookID=?";
        int affectedRow = 0;
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);

            pst.setString(1, book.getTitle());
            pst.setFloat(2, book.getPrice());
            pst.setInt(3, book.getQuantity());
            pst.setString(4, book.getAuthor());
            pst.setString(5, book.getCategory());
            pst.setString(6, book.getImgName());
            pst.setString(7, book.getId());

            affectedRow = pst.executeUpdate();

        } finally {
            closeConnection();
        }
        return affectedRow == 1;
    }
    
    public boolean insertBook(BooksDTO book) throws ClassNotFoundException, SQLException{
        String sql = "INSERT INTO Books (BookID, BookTitle, Price, Quantity, Author, Categories, ImageName, ImportDate, Status) VALUES(?,?,?,?,?,?,?,?,?)";
        int affectedRows = 0;
        try {
            System.out.println(book);
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, book.getId());
            pst.setString(2, book.getTitle());
            pst.setFloat(3, book.getPrice());
            pst.setInt(4, book.getQuantity());
            pst.setString(5, book.getAuthor());
            pst.setString(6, book.getCategory());
            pst.setString(7, book.getImgName());
            pst.setTimestamp(8, book.getImportDate());
            pst.setString(9, "Active");

            affectedRows = pst.executeUpdate();
        } finally {
            closeConnection();
        }
        return affectedRows != 0;
    }
    public Vector<BooksDTO> getActiveBooks() throws SQLException, ClassNotFoundException{
        Vector<BooksDTO> list = new Vector<>();
        String sql = "Select BookID, BookTitle, Price, Author, Quantity, Categories, ImageName, ImportDate from Books where Status=?";
        String id, title, author, category, imgName;
        int quantity;
        float price;
        Timestamp importDate;
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, "Active");

            rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getString("BookID");
                title = rs.getString("BookTitle");
                price = rs.getFloat("Price");
                author = rs.getString("Author");
                quantity = rs.getInt("Quantity");
                category = rs.getString("Categories");
                importDate = rs.getTimestamp("ImportDate");
                imgName = rs.getString("ImageName");
                
                
                list.add(new BooksDTO(id, title, author, category, imgName, quantity, price, importDate, "Active"));
            }

        } finally {
            closeConnection();
        }
        return list;
    }
    
    public Vector<BooksDTO> getAllBooks() throws SQLException, ClassNotFoundException{
        Vector<BooksDTO> list = new Vector<>();
        String sql = "Select BookID, BookTitle, Price, Author, Quantity, Categories, ImageName, ImportDate, Status from Books";
        String id, title, author, category, imgName, status;
        int quantity;
        float price;
        Timestamp importDate;
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);

            rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getString("BookID");
                title = rs.getString("BookTitle");
                price = rs.getFloat("Price");
                author = rs.getString("Author");
                quantity = rs.getInt("Quantity");
                category = rs.getString("Categories");
                importDate = rs.getTimestamp("ImportDate");
                imgName = rs.getString("ImageName");
                status = rs.getString("Status");
                
                list.add(new BooksDTO(id, title, author, category, imgName, quantity, price, importDate, status));
            }

        } finally {
            closeConnection();
        }
        return list;
    }
}
