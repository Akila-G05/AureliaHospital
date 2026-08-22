/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package lk.aurelia.Panel;

import com.formdev.flatlaf.FlatClientProperties;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import lk.aurelia.Dialog.viewMoreNurseDetails;
import lk.aurelia.Dialog.viewMoreReceptionDetails;
import lk.aurelia.Validation.Validation;
import lk.aurelia.component.table.TableCustom;
import lk.aurelia.connection.MySQL;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import raven.toast.Notifications;

/**
 *
 * @author aloka
 */
public class ManageNursesPanel extends javax.swing.JPanel {

    private JFrame dash;
    private String managerName;
    private Map<String, Integer> nurseStatusMap;
    private Logger logger;
    private FileHandler handler;
    private String nurseBarcode;

    /**
     * Creates new form ManageNursesPanel
     */
    public ManageNursesPanel(JFrame parent, String name) {
        initComponents();
        this.dash = parent;
        this.managerName = name;
        this.nurseStatusMap = new HashMap<>();
        try {
            logger = Logger.getLogger(ManageNursesPanel.class.getName());
            handler = new FileHandler(ManageNursesPanel.class.getName() + ".log", true);
            logger.addHandler(handler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);

        loadTable("", "");
        init();
        loadStatusCombo();
        filterCombo();
    }

    private void filterCombo() {
        try {
            ResultSet resultSet = MySQL.search("SELECT * FROM `status`");

            Vector<String> v = new Vector<>();
            v.add("Select");
            while (resultSet.next()) {
                v.add(resultSet.getString("status"));

            }
            DefaultComboBoxModel m = new DefaultComboBoxModel(v);
            nurseFilterCombo.setModel(m);

        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    private void loadStatusCombo() {
        try {
            ResultSet resultSet = MySQL.search("SELECT * FROM `status`");

            Vector<String> v = new Vector<>();
            v.add("Select");
            while (resultSet.next()) {
                v.add(resultSet.getString("status"));
                nurseStatusMap.put(resultSet.getString("status"), resultSet.getInt("id"));

            }
            DefaultComboBoxModel m = new DefaultComboBoxModel(v);
            nurseCombo.setModel(m);

        } catch (Exception e) {
            logger.warning(e.getMessage());
        }

    }

    private void init() {

        Notifications.getInstance().setJFrame(dash);
        nurseField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "First Name");
        nurseField2.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Last Name");
        nurseField3.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email");
        nurseField4.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mobile");
        nurseField6.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Address");
        nurseField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search Here...");

    }

    private void reset() {

        nurseField1.setText("");
        nurseField.setText("");
        nurseField2.setText("");
        nurseField3.setText("");
        nurseField4.setText("");
        nurseField6.setText("");
        nurseCombo.setSelectedIndex(0);
        nurseField1.grabFocus();

    }

    private void searchTable(String query) {
        try {
            ResultSet resultSet = MySQL.search(query);
            DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
            m.setRowCount(0);
            while (resultSet.next()) {
                Vector<String> v = new Vector<>();
                v.add(resultSet.getString("nurses.barcode"));
                v.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));
                v.add(resultSet.getString("email"));
                v.add(resultSet.getString("mobile"));

                m.addRow(v);
                jTable1.setModel(m);

            }
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }

    }

    private String query = "SELECT * FROM `nurses`";

    private void loadTable(String search, String status) {

        if (search.isBlank() && status.isBlank()) {
            searchTable(query);
        } else if (!search.isBlank() && status.isBlank()) {
            String newQuery = query + " WHERE (`nurses`.fname LIKE '%" + search + "%' OR `nurses`.mobile LIKE '%" + search + "%' )";
            searchTable(newQuery);
        } else if (search.isBlank() && !status.isBlank()) {
            String newQuery = query + " WHERE `nurses`.status_id = '" + status + "' ";
            searchTable(newQuery);
        } else if (!search.isBlank() && !status.isBlank()) {
            String newQuery = query + " WHERE `nurses`.status_id = '" + status + "' AND (`nurses`.fname LIKE '%" + search + "%' "
                    + "OR `nurses`.mobile LIKE '%" + search + "%' ) )";
            searchTable(newQuery);
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

        jPanel1 = new javax.swing.JPanel();
        searchButton = new lk.aurelia.component.RoundButton();
        nurseField = new lk.aurelia.component.RoundTextField();
        nurseField1 = new lk.aurelia.component.RoundTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nurseField2 = new lk.aurelia.component.RoundTextField();
        nurseField4 = new lk.aurelia.component.RoundTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nurseField3 = new lk.aurelia.component.RoundTextField();
        jLabel10 = new javax.swing.JLabel();
        nurseField6 = new lk.aurelia.component.RoundTextField();
        nurseCombo = new lk.aurelia.component.RoundComboBox();
        jLabel11 = new javax.swing.JLabel();
        addButton = new lk.aurelia.component.RoundButton();
        addButton1 = new lk.aurelia.component.RoundButton();
        addButton2 = new lk.aurelia.component.RoundButton();
        nurseFilterCombo = new lk.aurelia.component.RoundComboBox();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        printButton = new lk.aurelia.component.RoundButton();

        searchButton.setBackground(new java.awt.Color(211, 231, 240));
        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/aurelia/img/search 16x16.png"))); // NOI18N
        searchButton.setText("Search");
        searchButton.setBorderPainted(false);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        nurseField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nurseField.setPreferredSize(new java.awt.Dimension(180, 25));

        nurseField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nurseField1.setPreferredSize(new java.awt.Dimension(180, 25));

        jLabel5.setText("First name");

        jLabel6.setText("Last name");

        nurseField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nurseField2.setPreferredSize(new java.awt.Dimension(180, 25));

        nurseField4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nurseField4.setPreferredSize(new java.awt.Dimension(180, 25));

        jLabel7.setText(" Mobile");

        jLabel8.setText("Email");

        nurseField3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nurseField3.setPreferredSize(new java.awt.Dimension(180, 25));

        jLabel10.setText("Address");

        nurseField6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nurseField6.setPreferredSize(new java.awt.Dimension(180, 25));

        jLabel11.setText("Type");

        addButton.setBackground(new java.awt.Color(4, 79, 118));
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Add Nurse");
        addButton.setBorderPainted(false);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        addButton1.setBackground(new java.awt.Color(0, 102, 102));
        addButton1.setForeground(new java.awt.Color(255, 255, 255));
        addButton1.setText("Update Nurse");
        addButton1.setBorderPainted(false);
        addButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButton1ActionPerformed(evt);
            }
        });

        addButton2.setBackground(new java.awt.Color(0, 153, 153));
        addButton2.setForeground(new java.awt.Color(255, 255, 255));
        addButton2.setText("View Address");
        addButton2.setBorderPainted(false);
        addButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButton2ActionPerformed(evt);
            }
        });

        nurseFilterCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nurseFilterComboItemStateChanged(evt);
            }
        });

        jLabel12.setText("Filter :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nurseFilterCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nurseField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nurseField1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                    .addComponent(nurseCombo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nurseField2, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                    .addComponent(nurseField4, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nurseField6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nurseField3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nurseField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nurseField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nurseField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nurseField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(nurseCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(nurseField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nurseField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nurseFilterCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Barcode", "Full Name", "Email", "Mobile"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        printButton.setBackground(new java.awt.Color(211, 231, 240));
        printButton.setText("Print Nurses");
        printButton.setBorderPainted(false);
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        nurseBarcode = String.valueOf(System.currentTimeMillis()) + "_" + "NU";
        String fname = nurseField1.getText();
        String lname = nurseField2.getText();
        String email = nurseField3.getText();
        String status = String.valueOf(nurseCombo.getSelectedItem());
        String mobile = nurseField4.getText();
        String address = nurseField6.getText();

        if (fname.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "First Name is Required");
        } else if (lname.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Last Name is Required");

        } else if (email.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Email is Required");

        } else if (!email.matches(Validation.EMAIL_VALIDATION.validate())) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please Enter a Valid Email");

        } else if (status.equals("Select")) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please Select a Type");

        } else if (mobile.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Mobile is Required");

        } else if (!mobile.matches(Validation.MOBILE_VALIDATION.validate())) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please Enter a Valid Mobile");

        } else if (address.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Address is Required");

        } else {
            try {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = format.format(new Date());
                int nurseStatusID = nurseStatusMap.get(status);
                ResultSet resultSet = MySQL.search("SELECT * FROM `nurses` WHERE `email` = '" + email + "'");
                if (resultSet.next()) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "This user is Already Existed");
                } else {
                    MySQL.iud("INSERT INTO `nurses`(`barcode`,`fname`,`lname`,`mobile`,`email`,`registered_date`,`address`,`status_id`) "
                            + " VALUES('"+nurseBarcode+"','" + fname + "','" + lname + "','" + mobile + "','" + email + "','" + date + "',"
                            + "'" + address + "','" + nurseStatusID + "') ");

                    reset();
                    loadTable("", "");
                }

            } catch (SQLException e) {
                logger.warning(e.getMessage());
            }
        }

    }//GEN-LAST:event_addButtonActionPerformed

    private void addButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButton1ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please Select a Row First");
        } else {
            String fname = nurseField1.getText();
            String lname = nurseField2.getText();
            String mobile = nurseField4.getText();
            String status = String.valueOf(nurseCombo.getSelectedItem());
            String address = nurseField6.getText();

            if (fname.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "First name is Required ");
            } else if (lname.isBlank()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Last name is Required ");

            } else if (mobile.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Mobile is Required ");

            } else if (!mobile.matches(Validation.MOBILE_VALIDATION.validate())) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please Enter a Valid Mobile ");

            } else if (status.equals("Select")) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please Select a Type ");

            } else if (address.isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Address is Required ");

            } else {
                try {
                    int statusId = nurseStatusMap.get(status);
                    MySQL.iud("UPDATE `nurses`  SET `fname` = '" + fname + "',`lname`='" + lname + "',`mobile`='" + mobile + "'"
                            + ",`address` = '" + address + "',`status_id` = '" + statusId + "' WHERE `nurses`.barcode  = '" + String.valueOf(jTable1.getValueAt(selectedRow, 0)) + "' ");

                    reset();
                    activeField();
                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Updated Successfully");

                } catch (SQLException e) {
                    logger.warning(e.getMessage());
                }
            }

        }

    }//GEN-LAST:event_addButton1ActionPerformed

    private void activeField() {
        nurseField3.setEditable(true);
    }

    private void deactiveField() {
        nurseField3.setEditable(false);
    }
    private void addButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButton2ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please Select a Row First");
        } else {

            new viewMoreNurseDetails(dash, true, String.valueOf(jTable1.getValueAt(selectedRow, 0))).setVisible(true);

        }
    }//GEN-LAST:event_addButton2ActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed

        InputStream stream = getClass().getResourceAsStream("/lk/aurelia/reports/nurse_details.jasper");

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String date = format.format(new Date());
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("name", managerName);
            parameters.put("date", date);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
            JasperPrint print = JasperFillManager.fillReport(stream, parameters, dataSource);
            JasperViewer.viewReport(print, false);

        } catch (JRException ex) {
            logger.warning(ex.getMessage());
        }
        
    }//GEN-LAST:event_printButtonActionPerformed

    private void nurseFilterComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nurseFilterComboItemStateChanged

        if (nurseFilterCombo.getSelectedIndex() == 0) {
            loadTable(nurseField.getText(), "");
        } else {
            loadTable(nurseField.getText(), String.valueOf(nurseFilterCombo.getSelectedIndex()));
        }
    }//GEN-LAST:event_nurseFilterComboItemStateChanged

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        if (nurseField.getText().isEmpty()) {
            loadTable("", "");
        } else {
            loadTable(nurseField.getText(), "");
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            try {

                int selectedRow = jTable1.getSelectedRow();
                String fullName = String.valueOf(jTable1.getValueAt(selectedRow, 1));
                String email = String.valueOf(jTable1.getValueAt(selectedRow, 2));
                String mobile = String.valueOf(jTable1.getValueAt(selectedRow, 3));

                ResultSet resultSet = MySQL.search("SELECT * FROM `nurses` "
                        + " WHERE nurses.barcode = '" + String.valueOf(jTable1.getValueAt(selectedRow, 0)) + "'  ");
                String address = "";
                int statusId = 0;
                if (resultSet.next()) {
                    address = resultSet.getString("nurses.address");
                    statusId = resultSet.getInt("nurses.status_id");
                }

                nurseField1.setText(fullName.split(" ")[0]);
                nurseField2.setText(fullName.split(" ")[1]);
                nurseField3.setText(email);
                nurseField4.setText(mobile);
                nurseField6.setText(address);
                nurseCombo.setSelectedIndex(statusId);
                deactiveField();

            } catch (SQLException e) {
                logger.warning(e.getMessage());
            }

        }
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.aurelia.component.RoundButton addButton;
    private lk.aurelia.component.RoundButton addButton1;
    private lk.aurelia.component.RoundButton addButton2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private lk.aurelia.component.RoundComboBox nurseCombo;
    private lk.aurelia.component.RoundTextField nurseField;
    private lk.aurelia.component.RoundTextField nurseField1;
    private lk.aurelia.component.RoundTextField nurseField2;
    private lk.aurelia.component.RoundTextField nurseField3;
    private lk.aurelia.component.RoundTextField nurseField4;
    private lk.aurelia.component.RoundTextField nurseField6;
    private lk.aurelia.component.RoundComboBox nurseFilterCombo;
    private lk.aurelia.component.RoundButton printButton;
    private lk.aurelia.component.RoundButton searchButton;
    // End of variables declaration//GEN-END:variables
}
