/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import java.sql.Timestamp;

/**
 *
 * @author thehien
 */
public class DiscountsDTO {
    String code;
    float discountValue;
    String userID;
    Timestamp expiredDate;

    public DiscountsDTO(String code, float discountValue) {
        this.code = code;
        this.discountValue = discountValue;
    }

    
    
    public DiscountsDTO(String code, float discountValue, Timestamp expiredDate) {
        this.code = code;
        this.discountValue = discountValue;
        this.expiredDate = expiredDate;
    }

    
    
    public DiscountsDTO(String code, float discountValue, String userID, Timestamp expiredDate) {
        this.code = code;
        this.discountValue = discountValue;
        this.userID = userID;
        this.expiredDate = expiredDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(float discountValue) {
        this.discountValue = discountValue;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Timestamp getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public String toString() {
        return code + " - [" + discountValue + " %]";
    }
    
    
}
