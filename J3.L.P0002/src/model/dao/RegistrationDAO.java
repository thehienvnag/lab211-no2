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
import model.dto.RegistrationDTO;
import ulti.Encryption;

/**
 *
 * @author THE HIEN
 */
public class RegistrationDAO {
	
    private String id, username, pass, email, phone, userRole, photo;
    private RegistrationDTO userDTO;

    public RegistrationDAO() {
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
    
    public String getUsername(String userId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Username FROM Registration WHERE UserID=? and Status=?";
        String username = "failed";
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);

            pst.setString(1, userId);
            pst.setString(2, "Active");
            rs = pst.executeQuery();

            if (rs.next()) {
                username = rs.getString("Username");  
            }
        } finally {
            closeConnection();
        }

        return username;
    }
    
    public RegistrationDTO getUserByID(String userId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT Username, Phone, Address, Email FROM Registration WHERE UserID=? and Status=?";
        
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);

            pst.setString(1, userId);
            pst.setString(2, "Active");
            rs = pst.executeQuery();

            if (rs.next()) {
                String userName = rs.getString("Username");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");
                String email = rs.getString("Email");
                
                return new RegistrationDTO(userId, userName, phone, address, email);
            }
        } finally {
            closeConnection();
        }

        return null;
    }

    public boolean insertData(RegistrationDTO data) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO Registration (UserID, Username, Password, Phone, Address, Email, Role, Status) VALUES(?,?,?,?,?,?,?,?)";
        int affectedRow = 0;
        try {
            System.out.println(data);
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);

            pst.setString(1, data.getUserID());
            pst.setString(2, data.getUserName());
            pst.setString(3, Encryption.getEncryptPass(data.getPass()));
            pst.setString(4, data.getPhone());
            pst.setString(5, data.getAddress());
            pst.setString(6, data.getEmail());
            pst.setString(7, data.getRole());
            pst.setString(8, "Active");

            affectedRow = pst.executeUpdate();
        } finally {
            closeConnection();
        }
        return affectedRow == 1;
    }

    public String getRoleAfterLogin(String userID, String pass, String status) throws ClassNotFoundException, SQLException {

        String role = "failed";
        String sql = "Select Role from Registration where UserID=? and Password=? and Status=?";
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, userID);
            pst.setString(2, Encryption.getEncryptPass(pass));
            System.out.println(Encryption.getEncryptPass(pass));
            pst.setString(3, status);

            rs = pst.executeQuery();
            while (rs.next()) {
                role = rs.getString("Role");
            }
        } finally {
            closeConnection();
        }
        return role;
    }


    public boolean updateData(RegistrationDTO data) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Registration SET Username=?, Phone=?, Address=?, Email=? WHERE UserID=? and Status=?";
        int affectedRow = 0;
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);

            pst.setString(1, data.getUserName());
            pst.setString(2, data.getPhone());
            pst.setString(3, data.getAddress());
            pst.setString(4, data.getEmail());
            pst.setString(5, data.getUserID());
            pst.setString(6, "Active");

            affectedRow = pst.executeUpdate();

        } finally {
            closeConnection();
        }
        return affectedRow == 1;
    }

    public boolean changePassword(String userId, String oldPassword, String newPass) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Registration SET Password=? WHERE UserID=? and Password=? and Status=1";
        String encryptNewPass = Encryption.getEncryptPass(newPass);
        String encryptOldPass = Encryption.getEncryptPass(oldPassword);
        int affectedRow = 0;
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);

            pst.setString(1, encryptNewPass);
            pst.setString(2, userId);
            pst.setString(3, encryptOldPass);

            affectedRow = pst.executeUpdate();

        } finally {
            closeConnection();
        }
        return affectedRow == 1;
    }

    public Vector<RegistrationDTO> getUserRole(String role) throws ClassNotFoundException, SQLException {
        String sql = "SELECT UserID, Username FROM Registration WHERE Status=? and Role=?";
        Vector<RegistrationDTO> data = new Vector<>();
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, "Active");
            pst.setString(2, role);

            rs = pst.executeQuery();

            while (rs.next()) {

                id = rs.getString("UserID");
                username = rs.getString("Username");

                data.add(new RegistrationDTO(id, username));
            }

        } finally {
            closeConnection();
        }

        return data;
    }
}
