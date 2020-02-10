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
import java.util.Vector;
import model.dto.BookSaleDTO;

/**
 *
 * @author thehien
 */
public class BookSaleDAO {
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
    

    public BookSaleDAO() {
    }
      
    
    
    public Vector<BookSaleDTO> getAllBooksInBill(String billCode) throws SQLException, ClassNotFoundException{
        String sql = "SELECT BK.BookID, BK.BookTitle, BK.Price, BS.Amount "
                + "FROM [dbo].[Bills] BL, [dbo].[BookSale] BS, [dbo].[Books] BK "
                + "WHERE BL.BillCode=? AND BL.BillCode=BS.BillCode AND BK.BookID=BS.BookID";
        Vector<BookSaleDTO> listData = new Vector<>();
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, billCode);
                  
            rs = pst.executeQuery();
            while(rs.next()){
                String bookID = rs.getString("BookID");
                String bookTitle = rs.getString("BookTitle");
                float price = rs.getFloat("Price");
                int amount = rs.getInt("Amount");
                
                listData.add(new BookSaleDTO(billCode, bookID, bookTitle, amount, price));
            }
            
        } finally{
            closeConnection();
        }
        return listData;
    }

    public void insertItemsToBill(Vector<BookSaleDTO> listItems, Connection connection) throws SQLException {

        String sql = "INSERT INTO BookSale (BillCode, BookID, Amount) VALUES (?,?,?)";
        PreparedStatement pst = null;
        for (BookSaleDTO item : listItems) {
            try {
                System.out.println(item);
                pst = connection.prepareStatement(sql);
                pst.setString(1, item.getBillCode());
                pst.setString(2, item.getBookID());
                pst.setInt(3, item.getAmount());
                
                new BooksDAO().setBookQuantity(item.getBookID(), item.getAmount(), connection);
                
                pst.executeUpdate();
                
            } finally {
                if (pst != null) {
                    pst.close();
                }
            }
        }

    }

}
