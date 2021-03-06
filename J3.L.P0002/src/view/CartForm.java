/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.dao.BillsDAO;
import model.dao.DiscountsDAO;
import model.dto.BillsDTO;
import model.dto.BookSaleDTO;
import model.dto.BooksDTO;
import model.dto.DiscountsDTO;
import model.dto.RegistrationDTO;
import ulti.Ulti;

/**
 *
 * @author THE HIEN
 */
public class CartForm extends javax.swing.JFrame {

    /**
     * Creates new form CartForm
     */
    private boolean isConfirmed = false;
    private Vector<BooksDTO> listBook;
    private RegistrationDTO user;
    private DiscountsDTO currentDiscount;
    private UserForm frame;

    public CartForm(Vector<BooksDTO> listBook, RegistrationDTO user, UserForm frame) {
        initComponents();
        this.listBook = listBook;
        this.user = user;
        this.frame = frame;
        this.setLocationRelativeTo(frame);

        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initBillCode();
        initCart();
        makeSummary();
        setAllTxtFieldUnEditable();

    }

    private void initBillCode() {
        BillsDAO billDAO = new BillsDAO();

        try {
            String code = Ulti.generateCode(6) + billDAO.generateBillCode();
            txtBillCode.setText(code);
        } catch (SQLException ex) {
            Logger.getLogger(CartForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void resetTable(DefaultTableModel model) {
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
    }

    private void initCart() {
        DefaultTableModel model = (DefaultTableModel) tblCart.getModel();
        resetTable(model);

        for (BooksDTO booksDTO : listBook) {
            model.addRow(booksDTO.toCartVector());
        }

        makeSummary();
    }

    private void setAllTxtFieldUnEditable() {

        txtID.setEditable(false);
        txtTitle.setEditable(false);
        txtDiscountExp.setEditable(false);
        txtTotalPrice.setEditable(false);
        txtDiscountValue.setEditable(false);
        txtTruePrice.setEditable(false);
        txtPrice.setEditable(false);
        txtBillCode.setEditable(false);
    }

    private void initCbDiscounts() {
        try {
            DiscountsDAO discountDAO = new DiscountsDAO();
            Vector<DiscountsDTO> list = discountDAO.getUserDiscounts(user.getUserID());

            if (!list.isEmpty()) {
                cbCodes.removeAllItems();

                for (DiscountsDTO discountsDTO : list) {
                    cbCodes.addItem(new DiscountsDTO("Not Apply Code", 0));
                    cbCodes.addItem(discountsDTO);
                }
                cbCodes.setSelectedIndex(0);
                DiscountsDTO discount = cbCodes.getItemAt(0);
                txtDiscountValue.setText(discount.getDiscountValue() + "");
            } else {
                cbCodes.removeAllItems();
                cbCodes.addItem(new DiscountsDTO("No Code Available", 0));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CartForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private float calculateTotalPrice() {
        float total = 0;
        for (BooksDTO booksDTO : listBook) {
            total += booksDTO.getPrice() * booksDTO.getQuantity();
        }
        return total;
    }

    private void makeSummary() {

        float total = calculateTotalPrice();
        txtTotalPrice.setText("" + total);
        initCbDiscounts();
        setValueAfterDiscount(total);
    }

    private void setValueAfterDiscount(float total) {
        if (currentDiscount != null) {
            txtDiscountValue.setText(currentDiscount.getDiscountValue() + "");
            float truePrice = (float) (total - total * currentDiscount.getDiscountValue() * 0.01);
            txtTruePrice.setText(truePrice + "");
            txtDiscountValue.setText(currentDiscount.getDiscountValue() + "");
            if (currentDiscount.getExpiredDate() != null) {
                txtDiscountExp.setText(Ulti.getDateFormatDetail(currentDiscount.getExpiredDate()));
            }else{
                txtDiscountExp.setText("");
            }

        } else {
            txtTruePrice.setText(total + "");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupMenu1 = new java.awt.PopupMenu();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtPrice = new javax.swing.JTextField();
        btnRemoveBook = new javax.swing.JButton();
        btnUpdateBook = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtAmount = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTotalPrice = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbCodes = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtTruePrice = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDiscountValue = new javax.swing.JTextField();
        btnConfirm = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtDiscountExp = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtBillCode = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();

        popupMenu1.setLabel("popupMenu1");

        jLabel2.setText("jLabel2");

        jLabel11.setText("jLabel11");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BookID", "Book Title", "Price", "Amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblCart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCartMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCart);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("SELECTED BOOK");

        jLabel3.setText("Book Title:");

        jLabel4.setText("Amount:");

        jLabel5.setText("Price:");

        btnRemoveBook.setText("REMOVE");
        btnRemoveBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveBookActionPerformed(evt);
            }
        });

        btnUpdateBook.setText("UPDATE");
        btnUpdateBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateBookActionPerformed(evt);
            }
        });

        jLabel14.setText("Book ID:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(btnRemoveBook)
                .addGap(18, 18, 18)
                .addComponent(btnUpdateBook)
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtID)
                    .addComponent(txtPrice)
                    .addComponent(txtTitle)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtAmount))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRemoveBook)
                    .addComponent(btnUpdateBook))
                .addGap(6, 6, 6))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Total Price:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("PAYMENT");

        txtTotalPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalPriceActionPerformed(evt);
            }
        });

        jLabel8.setText("Available Code:");

        cbCodes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCodesActionPerformed(evt);
            }
        });

        jLabel9.setText("Pay Price:");

        jLabel10.setText("Discount:");

        btnConfirm.setText("CONFIRM");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        jLabel15.setText("Discount Exp:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtTotalPrice)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiscountExp)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnConfirm)
                                .addGap(0, 87, Short.MAX_VALUE))
                            .addComponent(txtTruePrice)
                            .addComponent(cbCodes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDiscountValue))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbCodes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtDiscountValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtDiscountExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtTruePrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConfirm)
                .addContainerGap())
        );

        jLabel12.setText("Bill Code");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cart.png"))); // NOI18N
        jLabel13.setText("SHOPPING CART");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtBillCode, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBillCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblCartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCartMouseClicked
        int selected = tblCart.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblCart.getModel();
        String id = (String) model.getValueAt(selected, 0);
        String title = (String) model.getValueAt(selected, 1);
        float price = (Float) model.getValueAt(selected, 2);
        int quantity = (Integer) model.getValueAt(selected, 3);

        txtID.setText(id);
        txtTitle.setText(title);
        txtPrice.setText(price + "");
        txtAmount.setText(quantity + "");


    }//GEN-LAST:event_tblCartMouseClicked


    private void cbCodesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCodesActionPerformed
        if (cbCodes.getItemCount() > 0) {
            int selected = cbCodes.getSelectedIndex();
            currentDiscount = (DiscountsDTO) cbCodes.getItemAt(selected);
            float total = Float.parseFloat(txtTotalPrice.getText().trim());
            setValueAfterDiscount(total);
        }
    }//GEN-LAST:event_cbCodesActionPerformed

    private void btnUpdateBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateBookActionPerformed
        String bookID = txtID.getText().trim();
        int amount = -1;
        String valid = "";

        if (bookID.isEmpty()) {
            valid += "Click on table to select book!";
        }

        int r = JOptionPane.showConfirmDialog(this, "Update book amount in cart!", "Update?", JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {
            try {
                amount = Ulti.getInt(txtAmount.getText().trim());
            } catch (Exception ex) {
                valid += "Not valid amount of book!";
            }

            if (valid.isEmpty()) {
                for (BooksDTO booksDTO : listBook) {
                    if (booksDTO.getId().equals(bookID)) {
                        booksDTO.setQuantity(amount);
                    }
                }
                JOptionPane.showMessageDialog(this, "Successfully updated cart!");
                initCart();
            } else {
                JOptionPane.showMessageDialog(this, valid);
            }
        }


    }//GEN-LAST:event_btnUpdateBookActionPerformed

    private void setEmptyTxtField() {
        txtID.setText("");
        txtTitle.setText("");
        txtAmount.setText("");
        txtPrice.setText("");
    }

    private void btnRemoveBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveBookActionPerformed
        String bookID = txtID.getText().trim();

        if (bookID.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Click on table to select book!");
        }

        int r = JOptionPane.showConfirmDialog(this, "Remove book in cart?", "Remove?", JOptionPane.YES_NO_OPTION);

        if (r == JOptionPane.YES_OPTION) {
            listBook.removeIf((BooksDTO book) -> book.getId().equals(bookID));
            setEmptyTxtField();
            JOptionPane.showMessageDialog(this, "Successfully updated cart!");
            initCart();
            frame.setBtnViewCart();
        }
    }//GEN-LAST:event_btnRemoveBookActionPerformed

    private void txtTotalPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalPriceActionPerformed
        makeSummary();
    }//GEN-LAST:event_txtTotalPriceActionPerformed

    private Vector<BookSaleDTO> cloneCartList(Vector<BooksDTO> listBook, String code) {
        Vector<BookSaleDTO> listBookSale = new Vector<>();
        for (BooksDTO booksDTO : listBook) {
            listBookSale.add(booksDTO.toBookSaleDTP(code));
        }
        return listBookSale;
    }

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed

        if (listBook.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Empty cart!");
            return;
        }

        if (!isConfirmed) {
            int r = JOptionPane.showConfirmDialog(this, "CONFIRM BUYING BOOKS?", "CONFIRMATION?", JOptionPane.YES_NO_OPTION);

            if (r == JOptionPane.YES_OPTION) {

                String code = txtBillCode.getText().trim();
                Vector<BookSaleDTO> listBookSale = cloneCartList(listBook, code);
                String discountCode = currentDiscount != null ? currentDiscount.getCode() : null;
                BillsDTO billDTO = new BillsDTO(code, discountCode, user.getUserID());

                try {
                    new BillsDAO().insertBillCode(billDTO, listBookSale);

                    JOptionPane.showMessageDialog(this, "Successfully buy books!");

                    isConfirmed = true;

                } catch (ClassNotFoundException ex) {

                    ex.printStackTrace();
                } catch (SQLException ex) {

                    String bookID = ex.getMessage();
                    String bookName = getBookName(bookID);
                    if (bookName != null) {
                        JOptionPane.showMessageDialog(this, "Not enough quantity of \"" + bookName + "\" in store!!!");
                    }

                }

            }
        } else {
            int r = JOptionPane.showConfirmDialog(this, "Finish buying book, continue shopping?", "Continue?", JOptionPane.YES_NO_OPTION);

            if (r == JOptionPane.YES_OPTION) {
                listBook.removeAllElements();
                frame.setBtnViewCart();
                this.dispose();
            }
        }
    }//GEN-LAST:event_btnConfirmActionPerformed

    private String getBookName(String id) {
        for (BooksDTO booksDTO : listBook) {
            if (booksDTO.getId().equals(id)) {
                return booksDTO.getTitle();
            }
        }
        return null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnRemoveBook;
    private javax.swing.JButton btnUpdateBook;
    private javax.swing.JComboBox<DiscountsDTO> cbCodes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JTable tblCart;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtBillCode;
    private javax.swing.JTextField txtDiscountExp;
    private javax.swing.JTextField txtDiscountValue;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JTextField txtTotalPrice;
    private javax.swing.JTextField txtTruePrice;
    // End of variables declaration//GEN-END:variables
}
