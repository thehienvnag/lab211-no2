/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import java.sql.Timestamp;
import java.util.Vector;
import ulti.Ulti;

/**
 *
 * @author thehien
 */
public class BillsDTO {
    private String billCode, discountCode, userID;
    private Timestamp importDate;
    private float totalPrice, discountValue, truePrice;
    
    public Vector toVector(){
        Vector data = new Vector();
        data.add(billCode);
        data.add(Ulti.getDateFormatDetail(importDate));
        data.add(totalPrice);
        data.add(discountCode);
        data.add(discountValue);
        data.add(truePrice);
        
        return data;
    }
    

    public float getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(float discountValue) {
        this.discountValue = discountValue;
    }

    public float getTruePrice() {
        return truePrice;
    }

    public void setTruePrice(float truePrice) {
        this.truePrice = truePrice;
    }
    

    
    
    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BillsDTO(String billCode, String discountCode, Timestamp importDate, float totalPrice, float discountValue) {
        this.billCode = billCode;
        this.discountCode = discountCode;
        this.importDate = importDate;
        this.totalPrice = totalPrice;
        this.discountValue = discountValue;
        this.truePrice = (float) (totalPrice - totalPrice * discountValue * 0.01);
    }
    
    public BillsDTO(String billCode, String discountCode, String userID) {
        this.billCode = billCode;
        this.discountCode = discountCode;
        this.userID = userID;
        this.importDate = new Timestamp(System.currentTimeMillis());
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Timestamp getImportDate() {
        return importDate;
    }

    public void setImportDate(Timestamp importDate) {
        this.importDate = importDate;
    }

    @Override
    public String toString() {
        return "BillsDTO{" + "billCode=" + billCode + ", discountCode=" + discountCode + ", userID=" + userID + ", importDate=" + importDate + '}';
    }
    
    
}
