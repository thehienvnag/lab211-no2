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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import model.dao.DiscountsDAO;
import model.dao.RegistrationDAO;
import model.dto.DiscountsDTO;
import model.dto.RegistrationDTO;
import ulti.Ulti;

/**
 *
 * @author THE HIEN
 */
public class DiscountAddingForm extends javax.swing.JFrame {

    /**
     * Creates new form PromotionAddingForm
     */
    int DEFAULT_RANK = 5;
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Discounts Management");
    DefaultTreeModel treeModel = new DefaultTreeModel(root);
    RegistrationDAO userDAO;
    Vector<RegistrationDTO> userList = new Vector<>();

    public DiscountAddingForm(RegistrationDAO userDAO) {
        initComponents();
        JFrame frame = this;
        this.userDAO = userDAO;

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int r = JOptionPane.showConfirmDialog(frame, "Are you sure you want to close this window?", "Close Window?", JOptionPane.YES_NO_OPTION);
                if (r == JOptionPane.YES_OPTION) {
                    frame.dispose();
                }
            }
        });

        initUserCbx();
        initTree();
        registerSearch();

    }

    private void initTreeForUserSearch(RegistrationDTO user) {
        DiscountsDAO discountDAO = new DiscountsDAO();
        resetTree();
        try {
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(user);
            root.add(treeNode);
            Vector<DiscountsDTO> discountList = discountDAO.getUserDiscounts(user.getUserID());
            for (DiscountsDTO discountsDTO : discountList) {
                treeNode.add(new DefaultMutableTreeNode(discountsDTO));
                System.out.println(discountsDTO);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiscountAddingForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DiscountAddingForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        treeDiscount.setModel(treeModel);
        treeDiscount.updateUI();
        expandAllNodes(treeDiscount, 0, treeDiscount.getRowCount());
    }

    private void registerSearch() {
        txtSearchUser.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchUser();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchUser();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchUser();
            }
        });
    }

    private void searchUser() {
        String search = txtSearchUser.getText().trim().toLowerCase();
        if (search.isEmpty()) {
            initTree();
        } else {
            setSelectedUserSearch(search);
        }

    }

    private void setSelectedUserSearch(String searchName) {

        for (int i = 0; i < cbxUser.getItemCount(); i++) {
            RegistrationDTO user = cbxUser.getItemAt(i);
            String username = user.getUserName().toLowerCase();
            if (username.contains(searchName)) {
                cbxUser.setSelectedIndex(i);
                RegistrationDTO userSelected = (RegistrationDTO) cbxUser.getSelectedItem();
                initTreeForUserSearch(userSelected);
                return;
            }
        }
    }

    private void setSelectedUser(String userID) {
        for (int i = 0; i < cbxUser.getItemCount(); i++) {
            RegistrationDTO user = cbxUser.getItemAt(i);
            if (user.getUserID().equals(userID)) {
                cbxUser.setSelectedIndex(i);
                return;
            }
        }
    }

    private void initUserCbx() {
        try {
            userList = userDAO.getUserRole("USER");
            cbxUser.removeAllItems();

            for (RegistrationDTO userDTO : userList) {
                cbxUser.addItem(userDTO);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DiscountAddingForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DiscountAddingForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void resetTree() {
        root.removeAllChildren();
    }

    private void expandAllNodes(JTree tree, int startingIndex, int rowCount) {
        for (int i = startingIndex; i < rowCount; ++i) {
            tree.expandRow(i);
        }

        if (tree.getRowCount() != rowCount) {
            expandAllNodes(tree, rowCount, tree.getRowCount());
        }
    }

    private void initTree() {

        root.removeAllChildren();
        treeDiscount.setExpandsSelectedPaths(false);

        resetTree();

        for (RegistrationDTO user : userList) {
            System.out.println(user);
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(user);
            root.add(treeNode);
            DiscountsDAO discountDAO = new DiscountsDAO();
            try {
                Vector<DiscountsDTO> discountList = discountDAO.getUserDiscounts(user.getUserID());
                for (DiscountsDTO discountsDTO : discountList) {
                    treeNode.add(new DefaultMutableTreeNode(discountsDTO));
                    System.out.println(discountsDTO);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DiscountAddingForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DiscountAddingForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        treeDiscount.setModel(treeModel);
        treeDiscount.updateUI();
        expandAllNodes(treeDiscount, 0, treeDiscount.getRowCount());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cbxUser = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnAddDiscount = new javax.swing.JButton();
        txtDiscountCode = new javax.swing.JTextField();
        btnGenerateCode = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtDiscount = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtExpiredDate = new javax.swing.JTextField();
        txtSearchUser = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnRemoveDiscount = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        treeDiscount = new javax.swing.JTree();

        jLabel8.setText("jLabel8");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbxUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxUserActionPerformed(evt);
            }
        });

        jLabel1.setText("Choose User:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("ADD DISCOUNT FOR USER");

        jLabel3.setText("Discount Code:");

        btnAddDiscount.setText("ADD USER DISCOUNT");
        btnAddDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDiscountActionPerformed(evt);
            }
        });

        btnGenerateCode.setText("Generate Code");
        btnGenerateCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateCodeActionPerformed(evt);
            }
        });

        jLabel4.setText("Discount (%):");

        jLabel5.setText("ExpiredDate");

        jLabel6.setText("Search User");

        btnRemoveDiscount.setText("REMOVE DISCOUNT");
        btnRemoveDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveDiscountActionPerformed(evt);
            }
        });

        btnNew.setText("NEW");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxUser, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtExpiredDate, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiscountCode, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGenerateCode, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtSearchUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNew)
                .addGap(14, 14, 14)
                .addComponent(btnAddDiscount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRemoveDiscount)
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDiscountCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGenerateCode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtExpiredDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddDiscount)
                    .addComponent(btnRemoveDiscount)
                    .addComponent(btnNew))
                .addContainerGap())
        );

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("User Discounts");
        treeDiscount.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        treeDiscount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treeDiscountMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(treeDiscount);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void treeDiscountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeDiscountMouseClicked
        DefaultMutableTreeNode nodeSelected = (DefaultMutableTreeNode) treeDiscount.getLastSelectedPathComponent();
        if (nodeSelected != null) {
            Object objectSelected = nodeSelected.getUserObject();

            if (objectSelected instanceof RegistrationDTO) {
                RegistrationDTO userSelected = (RegistrationDTO) objectSelected;
                setSelectedUser(userSelected.getUserID());
            } else if (objectSelected instanceof DiscountsDTO) {
                DiscountsDTO discount = (DiscountsDTO) objectSelected;
                txtDiscountCode.setEditable(false);
                txtDiscountCode.setText(discount.getCode());
                txtDiscount.setText(String.valueOf(discount.getDiscountValue()));
                txtExpiredDate.setText(Ulti.getDateFormatDetail(discount.getExpiredDate()));
            }
        }
    }//GEN-LAST:event_treeDiscountMouseClicked

    private void btnAddDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDiscountActionPerformed
        String valid = "";
        String userID = ((RegistrationDTO) cbxUser.getModel().getSelectedItem()).getUserID();
        String code = txtDiscountCode.getText().trim().toUpperCase();
        float discountValue = -1;

        if (!code.matches("[A-Z0-9]{6}")) {
            valid += "Discount Code must be number or alphabet (6 letters allowed)!\n";
        }
        try {
            discountValue = Float.parseFloat(txtDiscount.getText().trim());
        } catch (Exception e) {
            valid += "Discount Value must be decimal or integer!\n";
        }
        Timestamp expiredDate = null;
        try {
            expiredDate = Ulti.getDate(txtExpiredDate.getText().trim());
        } catch (ParseException e) {
            if (e.getMessage().equals("INVALID_TIME")) {
                valid += "At least one day before expired!\n";
            } else {
                valid += "Invalid date format!\n";
            }
        }
        if (valid.isEmpty()) {
            DiscountsDAO discountDAO = new DiscountsDAO();

            try {
                boolean check = discountDAO.insertDiscount(new DiscountsDTO(code, discountValue, userID, expiredDate));
                System.out.println(check);
                if (check) {
                    JOptionPane.showMessageDialog(this, "Successfully added new discount code!");
                    initTree();
                } else {
                    JOptionPane.showMessageDialog(this, "Fail to add new discount code!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Discount Code is duplicated!");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DiscountAddingForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, valid);
        }
    }//GEN-LAST:event_btnAddDiscountActionPerformed

    private void cbxUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxUserActionPerformed

    }//GEN-LAST:event_cbxUserActionPerformed

    private void btnGenerateCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateCodeActionPerformed
        int codeLength = 6;
        String code = Ulti.generateCode(codeLength);
        txtDiscountCode.setEditable(true);
        txtDiscountCode.setText(code);
    }//GEN-LAST:event_btnGenerateCodeActionPerformed

    private void btnRemoveDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveDiscountActionPerformed

    }//GEN-LAST:event_btnRemoveDiscountActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        int r = JOptionPane.showConfirmDialog(this, "New Discount Code Input?", "Reset form?", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION) {
            cbxUser.setSelectedIndex(0);
            txtDiscountCode.setEditable(true);
            txtDiscountCode.setText("");
            txtDiscount.setText("");
            txtExpiredDate.setText("");
        }
    }//GEN-LAST:event_btnNewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddDiscount;
    private javax.swing.JButton btnGenerateCode;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnRemoveDiscount;
    private javax.swing.JComboBox<model.dto.RegistrationDTO> cbxUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree treeDiscount;
    private javax.swing.JTextField txtDiscount;
    private javax.swing.JTextField txtDiscountCode;
    private javax.swing.JTextField txtExpiredDate;
    private javax.swing.JTextField txtSearchUser;
    // End of variables declaration//GEN-END:variables
}
