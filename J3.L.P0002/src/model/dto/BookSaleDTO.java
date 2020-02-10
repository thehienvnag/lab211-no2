/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import java.util.Vector;

/**
 *
 * @author thehien
 */
public class BookSaleDTO {
    private String billCode, bookID, bookTitle;
    private int amount;
    private float price;

    public Vector toVector(){
        Vector data = new Vector();
        data.add(bookID);
        data.add(bookTitle);
        data.add(price);
        data.add(amount);
        
        return  data;
    }
    
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    

    public BookSaleDTO(String billCode, String bookID, String bookTitle, int amount, float price) {
        this.billCode = billCode;
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.amount = amount;
        this.price = price;
    }

    public BookSaleDTO(String billCode, String bookID, int amount) {
        this.billCode = billCode;
        this.bookID = bookID;
        this.amount = amount;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BookSaleDTO{" + "billCode=" + billCode + ", bookID=" + bookID + ", amount=" + amount + '}';
    }
    
}
