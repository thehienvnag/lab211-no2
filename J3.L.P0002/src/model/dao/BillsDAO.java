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
import model.dto.BillsDTO;
import model.dto.BookSaleDTO;

/**
 *
 * @author thehien
 */
public class BillsDAO {

    Connection conn;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;

    Vector<PreparedStatement> listStatement = new Vector<>();

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

    public Vector<BillsDTO> searchBillsByDate(Timestamp searchDate, String userID) throws ClassNotFoundException, SQLException {
        Vector<BillsDTO> listBill = new Vector<>();
        String sql = "SELECT BL.BillCode, BL.ImportDate, SUM(BK.Price * BS.Amount) AS TotalPrice, BL.DiscountCode, "
                + "(SELECT DiscountValue From Discounts WHERE DiscountCode = BL.DiscountCode) AS DiscountValue "
                + "FROM Bills BL, BookSale BS, Books BK, "
                + "(SELECT BillCode FROM Bills WHERE UserID=?) CL "
                + "WHERE BL.BillCode = BS.BillCode AND BK.BookID=BS.BookID AND CL.BillCode=BL.BillCode "
                + "GROUP BY BL.BillCode, BL.ImportDate, BL.DiscountCode "
                + "HAVING CONVERT(date, BL.ImportDate)=?";
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, userID);
            pst.setTimestamp(2, searchDate);
            rs = pst.executeQuery();

            while (rs.next()) {
                String billCode = rs.getString("BillCode");
                Timestamp importDate = rs.getTimestamp("ImportDate");
                float totalPrice = rs.getFloat("TotalPrice");
                String discountCode = rs.getString("DiscountCode");
                float discountValue = rs.getFloat("DiscountValue");

                listBill.add(new BillsDTO(billCode, discountCode, importDate, totalPrice, discountValue));
            }
        } finally {
            closeConnection();
        }
        return listBill;
    }

    public Vector<BillsDTO> searchBillsByBookTitles(String search, String userID) throws ClassNotFoundException, SQLException {
        Vector<BillsDTO> listBill = new Vector<>();
        String sql = "SELECT BL.BillCode, BL.ImportDate, SUM(BK.Price * BS.Amount) AS TotalPrice, BL.DiscountCode, "
                + "(SELECT DiscountValue From Discounts WHERE DiscountCode = BL.DiscountCode) AS DiscountValue "
                + "FROM Bills BL, BookSale BS, Books BK, "
                + "(SELECT BillCode FROM Bills WHERE UserID=?) CL "
                + "WHERE BL.BillCode = BS.BillCode AND BK.BookID=BS.BookID AND CL.BillCode=BL.BillCode "
                + "GROUP BY BL.BillCode, BL.ImportDate, BL.DiscountCode "
                + "HAVING BL.BillCode IN "
                + "(SELECT BS.BillCode FROM BookSale BS, Books BK WHERE BS.BookID=BK.BookID AND BK.BookTitle LIKE ?)";

        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, userID);
            pst.setString(2, "%" + search + "%");
            rs = pst.executeQuery();

            while (rs.next()) {
                String billCode = rs.getString("BillCode");
                Timestamp importDate = rs.getTimestamp("ImportDate");
                float totalPrice = rs.getFloat("TotalPrice");
                String discountCode = rs.getString("DiscountCode");

                float discountValue = rs.getFloat("DiscountValue");

                listBill.add(new BillsDTO(billCode, discountCode, importDate, totalPrice, discountValue));
            }
        } finally {
            closeConnection();
        }

        return listBill;
    }

    public Vector<BillsDTO> getUserBills(String userID) throws SQLException, ClassNotFoundException {
        Vector<BillsDTO> listBill = new Vector<>();

        String sql = "SELECT BL.BillCode, BL.ImportDate, SUM(BK.Price * BS.Amount) AS TotalPrice, BL.DiscountCode, "
                + "(SELECT DiscountValue From Discounts WHERE DiscountCode = BL.DiscountCode) AS DiscountValue "
                + "FROM Bills BL, BookSale BS, Books BK, "
                + "(SELECT BillCode "
                + "FROM Bills "
                + "WHERE UserID=?) CL "
                + "WHERE BL.BillCode = BS.BillCode AND BK.BookID=BS.BookID AND CL.BillCode=BL.BillCode "
                + "GROUP BY BL.BillCode, BL.ImportDate, BL.DiscountCode";
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, userID);
            rs = pst.executeQuery();

            while (rs.next()) {
                String billCode = rs.getString("BillCode");
                Timestamp importDate = rs.getTimestamp("ImportDate");
                float totalPrice = rs.getFloat("TotalPrice");
                String discountCode = rs.getString("DiscountCode");
                float discountValue = rs.getFloat("DiscountValue");

                listBill.add(new BillsDTO(billCode, discountCode, importDate, totalPrice, discountValue));
            }
        } finally {
            closeConnection();
        }

        return listBill;
    }

    public void insertBillCode(BillsDTO bill, Vector<BookSaleDTO> listItems) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Bills (BillCode, DiscountCode, UserID, ImportDate) VALUES (?,?,?,?)";

        Connection connection = null;
        PreparedStatement billPst = null;
        System.out.println(bill);
        try {
            connection = DBConnection.openConnection();
            connection.setAutoCommit(false);
            billPst = connection.prepareStatement(sql);

            billPst.setString(1, bill.getBillCode());
            billPst.setString(2, bill.getDiscountCode());
            billPst.setString(3, bill.getUserID());
            billPst.setTimestamp(4, bill.getImportDate());

            billPst.executeUpdate();

            new BookSaleDAO().insertItemsToBill(listItems, connection);

            new DiscountsDAO().useDiscount(bill.getDiscountCode());

            connection.commit();
        } catch (SQLException ex) {
            if (connection != null) {
                connection.rollback();
                throw new SQLException(ex.getMessage());
            }
        } finally {
            if (billPst != null) {
                billPst.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public String generateBillCode() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(BillCode) AS NumOfBill FROM Bills";
        String code = "";
        try {
            conn = DBConnection.openConnection();
            st = conn.createStatement();

            rs = st.executeQuery(sql);

            if (rs.next()) {
                code += (rs.getInt("NumOfBill") + 1);
            }

        } finally {
            closeConnection();
        }
        return code;
    }

}
