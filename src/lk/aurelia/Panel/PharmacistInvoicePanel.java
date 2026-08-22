/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.aurelia.Panel;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import lk.aurelia.Dialog.selectPharmacistPatient;
import lk.aurelia.Gui.PharmacistDashboard;
import lk.aurelia.component.table.TableCustom;
import lk.aurelia.connection.MySQL;
import lk.aurelia.model.userDetails;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;

/**
 *
 * @author Akila_Ya
 */
public class PharmacistInvoicePanel extends javax.swing.JPanel {

    /**
     * Creates new form PharmacistInvoicePannel
     */
    
    private static String query = "";
    
    public static PharmacistInvoicePanel piPannel;
    public static Vector<String> patientDetailsVector;
    private static String qtytb1;
    private static String stockId;
    private static String sellingPrice;
    private static String brand;
    
    private static Double total2;
    
    private static int selectedRow;
 
    public PharmacistInvoicePanel() {
        initComponents();
        
        piPannel = this;
        
        patientDetailsVector = new Vector<>();
        patientDetailsVector.add("");
        patientDetailsVector.add("");
        
         jLabel25.setText("");
         jLabel27.setText("");
        
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(jScrollPane2, TableCustom.TableType.MULTI_LINE);
        
        
        pharmacistField5.setEnabled(false);
        searchButton3.setEnabled(false);
        searchButton2.setEnabled(false);
        
        loadStockTable();
    }
    
    public void setLabelData(){
                jLabel25.setText(patientDetailsVector.get(0));
                 jLabel27.setText(patientDetailsVector.get(1));
    }
    
    private void reset(){
        DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
        model.setRowCount(0);
        
        loadStockTable();
        
        searchButton3.setEnabled(false);
        searchButton2.setEnabled(false);
        jTable1.clearSelection();
        jLabel25.setText("");
        jLabel27.setText("");
        searchButton4.grabFocus();
        jLabel17.setText("0.00");
        pharmacistField7.setText("0.00");
        jLabel21.setText("0.00");
    }
    
    public void loadStockTable(){
        try {
            
            ResultSet rs = MySQL.search("SELECT * FROM `stock` \n" +
                                    "INNER JOIN `grn_item` ON `stock`.`grn_item_id` = `grn_item`.`id`\n" +
                                    "INNER JOIN `drug_cat` ON `grn_item`.`drug_cat_id` = `drug_cat`.`id`" + query);
            
            DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
            model.setRowCount(0);
            
            while(rs.next()){
                Vector<String> v = new Vector<>();
                 v.add(rs.getString("id"));
                v.add(rs.getString("brand"));
                v.add(rs.getString("stock.qty"));
                v.add(rs.getString("selling_price"));
                v.add(rs.getString("category"));
                
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        searchButton2 = new lk.aurelia.component.RoundButton();
        jLabel22 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        searchButton3 = new lk.aurelia.component.RoundButton();
        pharmacistField6 = new lk.aurelia.component.RoundTextField();
        jLabel23 = new javax.swing.JLabel();
        searchButton4 = new lk.aurelia.component.RoundButton();
        jLabel24 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        pharmacistField5 = new javax.swing.JFormattedTextField();
        pharmacistField7 = new javax.swing.JFormattedTextField();
        searchButton5 = new lk.aurelia.component.RoundButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "No", "Brand", "Qty", "Price", "Category"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Qty", "Total Price", "Brand"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel16.setText("Total :");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText(" 00.0");

        jLabel18.setText("Payment :");

        jLabel20.setText("Balance :");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText(" 00.0");

        searchButton2.setBackground(new java.awt.Color(211, 231, 240));
        searchButton2.setText("Add Invoice");
        searchButton2.setBorderPainted(false);
        searchButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton2ActionPerformed(evt);
            }
        });

        jLabel22.setText("Qty :");

        searchButton3.setBackground(new java.awt.Color(211, 231, 240));
        searchButton3.setText("ADD");
        searchButton3.setBorderPainted(false);
        searchButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton3ActionPerformed(evt);
            }
        });

        pharmacistField6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pharmacistField6.setPreferredSize(new java.awt.Dimension(180, 25));
        pharmacistField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pharmacistField6ActionPerformed(evt);
            }
        });
        pharmacistField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pharmacistField6KeyReleased(evt);
            }
        });

        jLabel23.setText("Search Medicine");

        searchButton4.setBackground(new java.awt.Color(211, 231, 240));
        searchButton4.setText("Select Patient");
        searchButton4.setBorderPainted(false);
        searchButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton4ActionPerformed(evt);
            }
        });

        jLabel24.setText("Name : ");

        jLabel26.setText("Email  : ");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("Name : ");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText(".");

        pharmacistField5.setBackground(new java.awt.Color(211, 231, 240));
        pharmacistField5.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        pharmacistField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        pharmacistField5.setText("0");
        pharmacistField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pharmacistField5ActionPerformed(evt);
            }
        });
        pharmacistField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pharmacistField5KeyReleased(evt);
            }
        });

        pharmacistField7.setBackground(new java.awt.Color(211, 231, 240));
        pharmacistField7.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        pharmacistField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        pharmacistField7.setText("0.00");
        pharmacistField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pharmacistField7KeyReleased(evt);
            }
        });

        searchButton5.setBackground(new java.awt.Color(211, 231, 240));
        searchButton5.setText("Clear Data");
        searchButton5.setBorderPainted(false);
        searchButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel18))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pharmacistField7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(searchButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(searchButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(searchButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(pharmacistField5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pharmacistField6, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(searchButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(403, 403, 403))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(pharmacistField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(jLabel27))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(searchButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pharmacistField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(pharmacistField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton2ActionPerformed
        // TODO add your handling code here:

        try {   
            
            userDetails ud = new userDetails();
            
            Date date = new Date();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String fDate = dateFormatter.format(date);

            String total = jLabel17.getText();
            String patientId = patientDetailsVector.get(2);
            
            Random rand = new Random();
            int randInt = rand.nextInt(100);
            
            ResultSet rs = MySQL.search("SELECT * FROM `pharmacist` WHERE `user_details_id`='9'");
            String pharmacistBarcode = "";
            while(rs.next()){
                pharmacistBarcode = rs.getString("barcode");
                
                MySQL.iud("INSERT INTO `invoice` (`id`,`date`,`total`,`pharmacist_barcode`,`patient_id`) "
                    + "VALUES('"+randInt+"', '"+fDate+"', '"+total+"', '"+pharmacistBarcode+"', '"+patientId+"')");
                
                System.out.println("Success");
            }
            
            for (int i = 0; i < jTable2.getRowCount(); i++) {
              String sId = (String)jTable2.getValueAt(i, 0);
              int qty = (Integer)jTable2.getValueAt(i, 1);

              MySQL.iud("INSERT INTO `invoice_items` (`stock_id`,`qty`,`invoice_id`) "
                    + "VALUES('"+sId+"', '"+qty+"', '"+randInt+"')");
              
              ResultSet rs2 = MySQL.search("SELECT * FROM `stock` WHERE `id`='"+sId+"'");
              
              while(rs2.next()){
                  int newQty = rs2.getInt("qty") - qty;
                  MySQL.iud("UPDATE `stock` SET `qty` = '"+newQty+"' WHERE `id`='"+sId+"'");
              }
              
            }
            
            String Payment = pharmacistField7.getText();
            String balance = jLabel21.getText();
            
//             try {
//                    HashMap<String, Object> parameters = new HashMap<>();
//                    parameters.put("name", patientDetailsVector.get(0));
//                     parameters.put("date_txt", fDate);
//                     parameters.put("Parameter1", total);
//                     parameters.put("Parameter2", Payment);
//                     parameters.put("Parameter3", balance);
//                    
////                    System.out.println(stuNo);
//                    JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable2.getModel());
////                    JREmptyDataSource dataSource = new JREmptyDataSource();
//                    InputStream stream = getClass().getResourceAsStream("lk/aurelia/reports/PhamacistInvoice.jasper");
//                    JasperPrint print = JasperFillManager.fillReport(stream, parameters, dataSource);
//                    System.out.println("3");
//                    JasperViewer.viewReport(print, false);
//                    
//                } catch (JRException e) {
//                    throw new RuntimeException(e);
//                }
            
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Invoice Added Successfully");
            
            reset();
        
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        printReport();
    }//GEN-LAST:event_searchButton2ActionPerformed

    private void searchButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton3ActionPerformed
        // TODO add your handling code here:
        int buyqty = Integer.valueOf(pharmacistField5.getText());
        
         int addqty = Integer.valueOf(qtytb1) - buyqty;
         
         Double total = buyqty * Double.parseDouble(sellingPrice);
         
         DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
         
//         String numb;
//         for (int i = 0; i < jTable2.getRowCount(); i++) {
//            numb =  (String)jTable2.getValueAt(i, 0);
//            
//            if(numb == stockId){
//                System.out.println("duplicate row");
//                String duplicateQty =  (String)jTable2.getValueAt(i, 1);
//                String duplicatePrice =  (String)jTable2.getValueAt(i, 2);
//                
//                System.out.println(duplicateQty);
//                System.out.println(duplicatePrice);
//            }
//         }
                 
         Vector<Object> v = new Vector();
         v.add(stockId);
         v.add(buyqty);
         v.add(total);
         v.add(brand);
         
         model.addRow(v);
         
         jTable1.setValueAt(addqty, selectedRow, 2);
        
        jTable1.setEnabled(true);
        pharmacistField5.setEnabled(false);
        pharmacistField5.setText("0");
        jTable1.clearSelection();
        searchButton3.setEnabled(false);
        
        Double total3 = 0.00;
        for (int i = 0; i < jTable2.getRowCount(); i++) {
              Double price = (Double)jTable2.getValueAt(i, 2);
              
              total3 += price;
              System.out.println(price);
         }
        
        total2 = total3;
        
         jLabel17.setText(String.valueOf(total2));
    }//GEN-LAST:event_searchButton3ActionPerformed

    private void searchButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton4ActionPerformed
        // TODO add your handling code here:
        selectPharmacistPatient sppDialog = new selectPharmacistPatient(PharmacistDashboard.dashboard, true);
        sppDialog.setLocationRelativeTo(PharmacistDashboard.dashboard);
        sppDialog.setVisible(true);
        
    }//GEN-LAST:event_searchButton4ActionPerformed

    private void pharmacistField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pharmacistField6KeyReleased
        // TODO add your handling code here:
        String key = pharmacistField6.getText();
        
        query = " WHERE `brand` LIKE '%"+key+"%'";
        
        loadStockTable();
    }//GEN-LAST:event_pharmacistField6KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2){
            pharmacistField5.setEnabled(true);
            pharmacistField5.grabFocus();
            
            selectedRow = jTable1.getSelectedRow();
            
            stockId = (String)jTable1.getValueAt(selectedRow, 0);
            qtytb1 = (String)jTable1.getValueAt(selectedRow, 2);
            sellingPrice = (String)jTable1.getValueAt(selectedRow, 3);
            brand = (String)jTable1.getValueAt(selectedRow, 1);
            
            jTable1.setEnabled(false);
            
            
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void pharmacistField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pharmacistField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pharmacistField6ActionPerformed

    private void pharmacistField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pharmacistField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pharmacistField5ActionPerformed

    private void pharmacistField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pharmacistField5KeyReleased
        // TODO add your handling code here:
        try {
             int buyqty = Integer.valueOf(pharmacistField5.getText());
            if(buyqty <= Integer.valueOf(qtytb1)){
                searchButton3.setEnabled(true);
            }else if(buyqty >= Integer.valueOf(qtytb1)){
                searchButton3.setEnabled(false);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_pharmacistField5KeyReleased

    private void pharmacistField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pharmacistField7KeyReleased
        // TODO add your handling code here:
        try {
            Double payment = Double.parseDouble(pharmacistField7.getText());
            Double balance =   payment - total2;

            jLabel21.setText(String.valueOf(balance));
            searchButton2.setEnabled(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_pharmacistField7KeyReleased

    private void searchButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton5ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_searchButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JFormattedTextField pharmacistField5;
    private lk.aurelia.component.RoundTextField pharmacistField6;
    private javax.swing.JFormattedTextField pharmacistField7;
    private lk.aurelia.component.RoundButton searchButton2;
    private lk.aurelia.component.RoundButton searchButton3;
    private lk.aurelia.component.RoundButton searchButton4;
    private lk.aurelia.component.RoundButton searchButton5;
    // End of variables declaration//GEN-END:variables
}
