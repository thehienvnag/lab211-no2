/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Vector;

/**
 *
 * @author THE HIEN
 */
public class BooksDTO implements Serializable{
    private String id, title, author, category, status, imgName;
    private int quantity;
    private float price;
    private Timestamp importDate;
    
    
    public Vector toVector(){
        Vector data = new Vector();
        data.add(id);
        data.add(title);
        data.add(author);
        data.add(category);
        data.add(price);
        data.add(quantity);
        data.add(imgName);
        data.add(importDate);
        data.add(status);
        return data;
    }

    public BooksDTO(String id, String title, String author, String category, String imgName, int quantity, float price, Timestamp importDate, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = status;
        this.imgName = imgName;
        this.quantity = quantity;
        this.price = price;
        this.importDate = importDate;
        System.out.println(this);
    }
    
    public BooksDTO(String id, String title, String author, String category, String imgName, int quantity, float price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = "Active";
        this.imgName = imgName;
        this.quantity = quantity;
        this.price = price;
        this.importDate = new Timestamp(System.currentTimeMillis());
        System.out.println(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Timestamp getImportDate() {
        return importDate;
    }

    public void setImportDate(Timestamp importDate) {
        this.importDate = importDate;
    }

    @Override
    public String toString() {
        return "BooksDTO{" + "id=" + id + ", title=" + title + ", author=" + author + ", category=" + category + ", status=" + status + ", imgName=" + imgName + ", quantity=" + quantity + ", price=" + price + ", importDate=" + importDate + '}';
    }
    
    
}
