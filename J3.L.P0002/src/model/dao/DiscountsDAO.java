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
import model.dto.DiscountsDTO;

/**
 *
 * @author thehien
 */
public class DiscountsDAO {

    public DiscountsDAO() {
    }
    
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
    
    
    public boolean insertDiscount(DiscountsDTO discount) throws SQLException, ClassNotFoundException{
        int affectedRows = 0;
        String sql = "INSERT INTO Discounts (DiscountCode, DiscountValue, UserID, ExpiredDate) VALUES (?,?,?,?)";
        System.out.println(discount);
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);
            String code = discount.getCode();
            float discountValue = discount.getDiscountValue();
            String userID = discount.getUserID();
            Timestamp expiredDate = discount.getExpiredDate();
            
            pst.setString(1, code);
            pst.setFloat(2, discountValue);
            pst.setString(3, userID);
            pst.setTimestamp(4, expiredDate);
            
            affectedRows = pst.executeUpdate();
            System.out.println(affectedRows == 1);
            
        } finally{
            closeConnection();     
        }
        return affectedRows == 1;
    }
    
    public void useDiscount(String code) throws SQLException, ClassNotFoundException{
        String sql = "UPDATE Discounts SET Status=0 WHERE DiscountCode=? AND Status=1";
       
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, code);
            
	    pst.executeUpdate();
        } finally{
            closeConnection();
        }
        
    }
    
    
    
    public Vector<DiscountsDTO> getUserDiscounts(String userID) throws SQLException, ClassNotFoundException{
        Vector<DiscountsDTO> discountList = new Vector<>();
        String sql = "SELECT DiscountCode, DiscountValue, ExpiredDate FROM Discounts where UserID=? AND Status=1";
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, userID);
            rs = pst.executeQuery();
            
            while(rs.next()){
                String code = rs.getString("DiscountCode");
                float discountValue = rs.getFloat("DiscountValue");
                Timestamp expiredDate = rs.getTimestamp("ExpiredDate");
                
                discountList.add(new DiscountsDTO(code, discountValue, expiredDate));
            }
            
            
        } finally{
            closeConnection();
        }
        return discountList;
    }
    
    
}
