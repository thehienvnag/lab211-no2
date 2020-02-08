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

    public String getUsername(String userId) throws ClassNotFoundException, SQLException {
        String sql = "SELECT Username FROM Registration WHERE UserID=? and Status=?";
        RegistrationDTO data = null;
        String userName = "failed";
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);

            pst.setString(1, userId);
            pst.setString(2, "Active");
            rs = pst.executeQuery();

            while (rs.next()) {
                userName = rs.getString("Username");

            }

        } finally {
            closeConnection();
        }

        return userName;
    }

    public Vector getUserByID(String userId) throws ClassNotFoundException, SQLException {
        String sql = "SELECT Username, Email, Phone, Photo FROM Users WHERE UserID=? and Status=1";
        Vector data = null;
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);

            pst.setString(1, userId);

            rs = pst.executeQuery();

            while (rs.next()) {
                data = new Vector();
                data.add(rs.getString("Username"));
                data.add(rs.getString("Email"));
                data.add(rs.getString("Phone"));
                data.add(rs.getString("Photo"));
            }

        } finally {
            closeConnection();
        }

        return data;
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

    public Vector<Vector> getUserListByRole(String role) throws ClassNotFoundException, SQLException {
        Vector<Vector> list = new Vector<>();
        String sql = "Select UserID, Username, Email, Phone, Role, Photo from Users where Role=? and Status=1";

        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, role);

            rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getString("UserID");
                username = rs.getString("Username");
                email = rs.getString("Email");
                phone = rs.getString("Phone");
                userRole = rs.getString("Role");
                photo = rs.getString("Photo");

                //userDTO = new RegistrationDTO(id, username, email, phone, userRole, photo);
                list.add(userDTO.toDataVector());
            }

        } finally {
            closeConnection();
        }
        return list;
    }

    public Vector<Vector> getUserList() throws ClassNotFoundException, SQLException {
        Vector<Vector> list = new Vector<>();
        String sql = "Select UserID, Username, Email, Phone, Role, Photo from Users where Status=1 ";

        try {
            conn = DBConnection.openConnection();
            st = conn.createStatement();

            rs = st.executeQuery(sql);
            while (rs.next()) {
                id = rs.getString("UserID");
                username = rs.getString("Username");
                email = rs.getString("Email");
                phone = rs.getString("Phone");
                userRole = rs.getString("Role");
                photo = rs.getString("Photo");

                //userDTO = new RegistrationDTO(id, username, email, phone, userRole, photo);
                list.add(userDTO.toDataVector());
            }

        } finally {
            closeConnection();
        }
        return list;
    }

    public Vector<Vector> searchUser(String input) throws ClassNotFoundException, SQLException {
        Vector<Vector> list = new Vector<>();

        String search = "%" + input + "%";
        String sql = "SELECT UserID, Username, Email, Phone, Role, Photo from Users where Username like ? and Status=1";
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, search);

            rs = pst.executeQuery();

            while (rs.next()) {
                id = rs.getString("UserID");
                username = rs.getString("Username");
                email = rs.getString("Email");
                phone = rs.getString("Phone");
                userRole = rs.getString("Role");
                photo = rs.getString("Photo");

                //userDTO = new RegistrationDTO(id, username, email, phone, userRole, photo);
                list.add(userDTO.toDataVector());
            }

        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean updateData(RegistrationDTO data) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Users SET Username=?, Phone=?, Address=?, Email=? WHERE UserID=? and Status=?";
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

    public boolean removeUser(String userId) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Users SET Status=? where UserID=? and Status=1";
        int affectedRow = 0;
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);

            pst.setBoolean(1, false);
            pst.setString(2, userId);

            affectedRow = pst.executeUpdate();
        } finally {
            closeConnection();
        }
        return affectedRow == 1;
    }

    public boolean resetPassword(String userId, String pass) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE Users SET Password=? WHERE UserID=? and Status=1";
        String encryptNewPass = Encryption.getEncryptPass(pass);
        int affectedRow = 0;
        try {
            conn = DBConnection.openConnection();
            pst = conn.prepareStatement(sql);

            pst.setString(1, encryptNewPass);
            pst.setString(2, userId);

            affectedRow = pst.executeUpdate();

        } finally {
            closeConnection();
        }
        return affectedRow == 1;
    }

    public boolean changePassword(String userId, String oldPassword, String newPass) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE Users SET Password=? WHERE UserID=? and Password=? and Status=1";
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
