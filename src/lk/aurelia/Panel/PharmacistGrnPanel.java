/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lk.aurelia.Panel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import lk.aurelia.component.table.TableCustom;
import lk.aurelia.connection.MySQL;
import raven.toast.Notifications;

/**
 *
 * @author aloka
 */
public class PharmacistGrnPanel extends javax.swing.JPanel {

    /**
     * Creates new form PharmacistGrnPanel
     */
    private static String stockId;
    
    public PharmacistGrnPanel() {
        initComponents();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        
        loadSupplier();
        loadDrugCategory();
        loadGRNTable();
        reset();
    }

    private void loadSupplier(){
        
        try {
            ResultSet rs =  MySQL.search("SELECT * FROM `supplier`");
            
            Vector<String> v = new Vector<>();
            v.add("Select Supplier");
            
            while(rs.next()){    
                v.add(rs.getString("company"));
            }
            
            DefaultComboBoxModel model = new DefaultComboBoxModel(v);
            pharmacyCombo.setModel(model);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    private void loadDrugCategory(){
        
        try {
            ResultSet rs =  MySQL.search("SELECT * FROM `drug_cat`");
            
            Vector<String> v = new Vector<>();
            v.add("Select Category");
            
            while(rs.next()){    
                v.add(rs.getString("category"));
            }
            
            DefaultComboBoxModel model = new DefaultComboBoxModel(v);
            pharmacyCombo1.setModel(model);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    private void reset(){
        pharmacyCombo.setSelectedIndex(0);
        pharmacistField2.setText("");
        pharmacyCombo.setSelectedIndex(0);
        pharmacistField3.setText("");
        pharmacistField4.setText("");
        
        jLabel19.setText("");
        pharmacistField5.setEnabled(false);
        pharmacistField6.setEnabled(false);
        addButton2.setEnabled(false);
        
        addButton1.setEnabled(false);
        pharmacistField5.setEditable(false);
    }
    
    public void loadGRNTable(){
        try {
            
            ResultSet rs = MySQL.search("SELECT * FROM `grn_item`\n" +
                                                            "INNER JOIN `grn` ON `grn_item`.`grn_id` = `grn`.`id` \n" +
                                                            "INNER JOIN `supplier` ON `grn`.`supplier_id` = `supplier`.`id` \n" +
                                                            "INNER JOIN `drug_cat` ON `grn_item`.`drug_cat_id` = `drug_cat`.`id`");
            
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                Vector<String> v = new Vector<>();
                 v.add(rs.getString("grn_item.id"));
                v.add(rs.getString("brand"));
                v.add(rs.getString("category"));
                v.add(rs.getString("qty"));   
                v.add(rs.getString("unit_price"));
                v.add(rs.getString("company"));
                v.add(rs.getString("hotline"));
                
                model.addRow(v);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addButton1 = new lk.aurelia.component.RoundButton();
        addButton = new lk.aurelia.component.RoundButton();
        pharmacistField4 = new lk.aurelia.component.RoundTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        pharmacistField3 = new lk.aurelia.component.RoundTextField();
        jLabel13 = new javax.swing.JLabel();
        pharmacyCombo1 = new lk.aurelia.component.RoundComboBox();
        jLabel12 = new javax.swing.JLabel();
        pharmacistField2 = new lk.aurelia.component.RoundTextField();
        jLabel11 = new javax.swing.JLabel();
        pharmacyCombo = new lk.aurelia.component.RoundComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pharmacistField6 = new lk.aurelia.component.RoundTextField();
        jLabel17 = new javax.swing.JLabel();
        pharmacistField5 = new lk.aurelia.component.RoundTextField();
        jLabel16 = new javax.swing.JLabel();
        addButton2 = new lk.aurelia.component.RoundButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        addButton1.setBackground(new java.awt.Color(0, 102, 102));
        addButton1.setForeground(new java.awt.Color(255, 255, 255));
        addButton1.setText("Update GRN");
        addButton1.setBorderPainted(false);
        addButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButton1ActionPerformed(evt);
            }
        });

        addButton.setBackground(new java.awt.Color(4, 79, 118));
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Add GRN");
        addButton.setBorderPainted(false);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        pharmacistField4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pharmacistField4.setPreferredSize(new java.awt.Dimension(180, 25));

        jLabel15.setText("Unit Price");

        jLabel14.setText("Qty");

        pharmacistField3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pharmacistField3.setPreferredSize(new java.awt.Dimension(180, 25));

        jLabel13.setText("Category");

        jLabel12.setText("Brand");

        pharmacistField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pharmacistField2.setPreferredSize(new java.awt.Dimension(180, 25));

        jLabel11.setText("Supplier");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(pharmacyCombo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pharmacistField2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pharmacyCombo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pharmacistField3, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(pharmacistField4, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)))))
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pharmacyCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pharmacistField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pharmacyCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pharmacistField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pharmacistField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Brand", "Category", "Quantity", "Unit Price", "Supplier", "Hotline"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
        }

        pharmacistField6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pharmacistField6.setPreferredSize(new java.awt.Dimension(180, 25));

        jLabel17.setText("Seeling Price");

        pharmacistField5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pharmacistField5.setPreferredSize(new java.awt.Dimension(180, 25));

        jLabel16.setText("Qty");

        addButton2.setBackground(new java.awt.Color(0, 102, 102));
        addButton2.setForeground(new java.awt.Color(255, 255, 255));
        addButton2.setText("Add Stock");
        addButton2.setBorderPainted(false);
        addButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButton2ActionPerformed(evt);
            }
        });

        jLabel18.setText("Stock Id:");

        jLabel19.setText("..");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pharmacistField5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pharmacistField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pharmacistField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pharmacistField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        
        int supplier = pharmacyCombo.getSelectedIndex();
        String brand = pharmacistField2.getText();
        int cat = pharmacyCombo.getSelectedIndex();
        String qty = pharmacistField3.getText();
        String unitPrice = pharmacistField4.getText();

        if (supplier == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Supllier is Required");
        } else if (brand.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Brand is Required");
        } else if (cat == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Brand is Required");
        } else if (qty.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Qty is Required");
        }else if (unitPrice.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "UnitPrice is Required");
        } else {
            try {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = format.format(new Date());
                
                Random rand = new Random();
                int randInt = rand.nextInt(100);

                MySQL.iud("INSERT INTO `grn`  (`id`, `date`, `supplier_id`) VALUES('"+randInt+"','"+date+"', '"+supplier+"') ");
                MySQL.iud("INSERT INTO `grn_item`(`brand`, `drug_cat_id`, `grn_id`, `qty`, `unit_price`) "
                        + "VALUES('"+brand+"','"+cat+"', '"+randInt+"', '"+qty+"', '"+unitPrice+"') ");
                
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "GRN Added Successfully");
                
                loadGRNTable();
                reset();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void addButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButton1ActionPerformed
                
        int supplier = pharmacyCombo.getSelectedIndex();
        String brand = pharmacistField2.getText();
        int cat = pharmacyCombo.getSelectedIndex();
        String qty = pharmacistField3.getText();
        String unitPrice = pharmacistField4.getText();

        if (supplier == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Supllier is Required");
        } else if (brand.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Brand is Required");
        } else if (cat == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Brand is Required");
        } else if (qty.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Qty is Required");
        }else if (unitPrice.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "UnitPrice is Required");
        } else {
            try {

                MySQL.iud("UPDATE `grn_item` SET `brand`='"+brand+"', `drug_cat_id`='"+cat+"',`qty`='"+qty+"', `unit_price`='"+unitPrice+"' "
                        + "WHERE `id`='"+stockId+"'");
                
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "GRN Updated Successfully");
                
                loadGRNTable();
                reset();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }//GEN-LAST:event_addButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            int selectedRow = jTable1.getSelectedRow();
            
            String qty = (String)jTable1.getValueAt(selectedRow, 3);
            stockId = (String)jTable1.getValueAt(selectedRow, 0);
            String brand = (String)jTable1.getValueAt(selectedRow, 1);
            
            String supplier = (String)jTable1.getValueAt(selectedRow, 5);
            String cat = (String)jTable1.getValueAt(selectedRow, 2);
            String uPrice = (String)jTable1.getValueAt(selectedRow, 4);
            
            pharmacyCombo.setSelectedItem(supplier);
            pharmacistField2.setText(brand);
            pharmacyCombo.setSelectedItem(cat);
            pharmacistField3.setText(qty);
            pharmacistField4.setText(uPrice);
            pharmacistField5.setText(qty);
            
            jLabel19.setText(stockId +" ("+brand+")");
            pharmacistField5.setEnabled(true);
            pharmacistField6.setEnabled(true);
            addButton2.setEnabled(true);
            addButton1.setEnabled(true);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void addButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButton2ActionPerformed
        // TODO add your handling code here:
        String qty = pharmacistField5.getText();
        String price =pharmacistField6.getText();
        
        if (qty.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Qty is Required");
        }else if (price.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "SellingPrice is Required");
        } else {
            try {
                MySQL.iud("INSERT INTO `stock`  ( `selling_price`, `grn_item_id`,`qty`) VALUES('"+price+"','"+stockId+"', '"+qty+"') ");
                
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Stock Updated");
                reset();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        
    }//GEN-LAST:event_addButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.aurelia.component.RoundButton addButton;
    private lk.aurelia.component.RoundButton addButton1;
    private lk.aurelia.component.RoundButton addButton2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private lk.aurelia.component.RoundTextField pharmacistField2;
    private lk.aurelia.component.RoundTextField pharmacistField3;
    private lk.aurelia.component.RoundTextField pharmacistField4;
    private lk.aurelia.component.RoundTextField pharmacistField5;
    private lk.aurelia.component.RoundTextField pharmacistField6;
    private lk.aurelia.component.RoundComboBox pharmacyCombo;
    private lk.aurelia.component.RoundComboBox pharmacyCombo1;
    // End of variables declaration//GEN-END:variables
}
