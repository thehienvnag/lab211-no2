/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import java.util.Vector;

/**
 *
 * @author THE HIEN
 */
public class RegistrationDTO {

    private String userID, userName, pass, phone, address, email, role;

    public RegistrationDTO(String userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    public RegistrationDTO(String userID, String userName, String phone, String address, String email) {
        this.userID = userID;
        this.userName = userName;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }
    
    

    public RegistrationDTO(String userID, String userName, String pass, String phone, String address, String email, String role) {
        this.userID = userID;
        this.userName = userName;
        this.pass = pass;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.role = role;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
    

    public Vector toDataVector() {
        Vector data = new Vector();
        data.add(userID);
        data.add(userName);
        data.add(email);
        data.add(phone);
        data.add(role);
        return data;
    }

    
    
    @Override
    public String toString() {
        return userID + " - " + userName;
    }

//    @Override
//    public String toString() {
//        return "RegistrationDTO{" + "userID=" + userID + ", userName=" + userName + ", pass=" + pass + ", phone=" + phone + ", address=" + address + ", email=" + email + ", role=" + role + '}';
//    }

}
