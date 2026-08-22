/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.aurelia.Panel;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import lk.aurelia.Gui.ReceptionDashboard;
import lk.aurelia.component.table.TableCustom;
import lk.aurelia.connection.MySQL;
import raven.toast.Notifications;
import lk.aurelia.Validation.Validation;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Akila_Ya
 */
public class ReceptionPatientPanel extends javax.swing.JPanel {

    private ReceptionDashboard dashboard;
    private Logger logger;
    private FileHandler handler;

    /**
     * Creates new form DoctorDashboardPannel
     */
    public ReceptionPatientPanel(ReceptionDashboard receptionDashboard) {
        initComponents();
        dashboard = receptionDashboard;
                try {
            logger = Logger.getLogger(ManageTheaterPanel.class.getName());
            handler = new FileHandler(ManageTheaterPanel.class.getName() + ".log", true);
            logger.addHandler(handler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Notifications.getInstance().setJFrame(dashboard);
        TableCustom.apply(scrollPane, TableCustom.TableType.MULTI_LINE);
        loadPatientTable("");
        loadPatientType();
    }

    private void updateFields() {
        fName.setText("");
        lName.setText("");
        email.setText("");
        email.setEnabled(true);
        mobile.setText("");
        address.setText("");
        patientTypeCombo.setSelectedIndex(0);
        updatePatient.setEnabled(false);
        addPatient.setEnabled(true);
        loadPatientTable("");
    }

    private void loadPatientTable(String search) {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM  `patient` INNER JOIN `patient_type`"
                    + " ON `patient`.`patient_type_id`=`patient_type`.`id` WHERE "
                    + "`fname` LIKE '%" + search + "%' OR `lname` LIKE '%" + search + "%' OR `email` "
                    + "LIKE '%" + search + "%' OR `mobile` LIKE '%" + search + "%'");

            DefaultTableModel model = (DefaultTableModel) patientTable.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector data = new Vector();
                data.add(rs.getString("id"));
                data.add(rs.getString("fname") + " " + rs.getString("lname"));
                data.add(rs.getString("email"));
                data.add(rs.getString("mobile"));
                data.add(rs.getString("address"));
                data.add(rs.getString("type"));
                model.addRow(data);
            }
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
    }

    private void loadPatientType() {
        try {

            ResultSet resultSet = MySQL.search("SELECT * FROM `patient_type`");
            Vector<String> patientType = new Vector<>();
            patientType.add("Select");
            while (resultSet.next()) {
                patientType.add(resultSet.getString("type"));
            }

            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(patientType);
            this.patientTypeCombo.setModel(comboBoxModel);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean emailValidation(String value) {
        if (value.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Email Cannot Be empty!");
            return true;
        } else if (!value.matches(Validation.EMAIL_VALIDATION.validate())) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Invalid Email!");
            return true;
        }

        return false;
    }

    private boolean nameValidation(String value) {

        if (value.isEmpty()) {

            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Name is Required");
            return true;
        }

        return false;
    }

    private boolean mobileValidation(String value) {

        if (value.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Mobile No is Required");
            return true;

        } else if (!value.matches(Validation.MOBILE_VALIDATION.validate())) {

            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please Enter a Valid Mobile No1");
            return true;
        }

        return false;
    }
    
    private void printReport() {
        try {

            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");//("yyyy-MM-dd HH:mm:ss")
            String Joinedate = dateFormatter.format(new Date());

            String Name = dashboard.getShowName().getText();

            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("reception_name", Name);
            parameters.put("date", Joinedate);

            String filePath = "src//lk//aurelia//reports//reception_patient_report.jasper";

            JRDataSource dataSource = new JRTableModelDataSource(patientTable.getModel());

            JasperPrint print = JasperFillManager.fillReport(filePath, parameters, dataSource);
            JasperViewer.viewReport(print, false);

        } catch (JRException e) {
            logger.warning(e.getMessage());
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

        background1 = new lk.aurelia.component.Background();
        jLabel1 = new javax.swing.JLabel();
        background2 = new lk.aurelia.component.Background();
        searchText = new lk.aurelia.component.RoundTextField();
        searchButton = new lk.aurelia.component.RoundButton();
        jLabel2 = new javax.swing.JLabel();
        fName = new lk.aurelia.component.RoundTextField();
        jLabel3 = new javax.swing.JLabel();
        lName = new lk.aurelia.component.RoundTextField();
        jLabel4 = new javax.swing.JLabel();
        email = new lk.aurelia.component.RoundTextField();
        jLabel5 = new javax.swing.JLabel();
        mobile = new lk.aurelia.component.RoundTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        address = new lk.aurelia.component.RoundTextArea();
        jLabel7 = new javax.swing.JLabel();
        patientTypeCombo = new lk.aurelia.component.RoundComboBox();
        addPatient = new lk.aurelia.component.RoundButton();
        updatePatient = new lk.aurelia.component.RoundButton();
        searchButton1 = new lk.aurelia.component.RoundButton();
        addPatient1 = new lk.aurelia.component.RoundButton();
        scrollPane = new javax.swing.JScrollPane();
        patientTable = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 18)); // NOI18N
        jLabel1.setText("Manage Patient Details");

        searchText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextActionPerformed(evt);
            }
        });

        searchButton.setBackground(new java.awt.Color(211, 231, 240));
        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/aurelia/img/search 16x16.png"))); // NOI18N
        searchButton.setText("Search");
        searchButton.setBorderPainted(false);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel2.setText("First Name");

        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel3.setText("Last Name");

        lName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lNameActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel4.setText("Email");

        jLabel5.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel5.setText("Mobile");

        jLabel6.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel6.setText("Address");

        address.setColumns(20);
        address.setRows(2);
        jScrollPane1.setViewportView(address);

        jLabel7.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        jLabel7.setText("Patient Type");

        addPatient.setForeground(new java.awt.Color(0, 0, 0));
        addPatient.setText("Add Patient");
        addPatient.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 14)); // NOI18N
        addPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPatientActionPerformed(evt);
            }
        });

        updatePatient.setForeground(new java.awt.Color(0, 0, 0));
        updatePatient.setText("Update Patient");
        updatePatient.setEnabled(false);
        updatePatient.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 14)); // NOI18N
        updatePatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePatientActionPerformed(evt);
            }
        });

        searchButton1.setBackground(new java.awt.Color(211, 231, 240));
        searchButton1.setText("Reset");
        searchButton1.setBorderPainted(false);
        searchButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButton1ActionPerformed(evt);
            }
        });

        addPatient1.setForeground(new java.awt.Color(0, 0, 0));
        addPatient1.setText("Print Report");
        addPatient1.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 14)); // NOI18N
        addPatient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPatient1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout background2Layout = new javax.swing.GroupLayout(background2);
        background2.setLayout(background2Layout);
        background2Layout.setHorizontalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background2Layout.createSequentialGroup()
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, background2Layout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(searchText, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(searchButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, background2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(background2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1))
                            .addGroup(background2Layout.createSequentialGroup()
                                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(background2Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(fName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(background2Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(39, 39, 39)
                                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(background2Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(mobile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(background2Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(background2Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(patientTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addPatient1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updatePatient, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
        );
        background2Layout.setVerticalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(fName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(mobile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(patientTypeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addPatient, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatePatient, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addPatient1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        scrollPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scrollPaneMouseClicked(evt);
            }
        });

        patientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patient Id", "Name", "Email", "Mobile", "Address", "Patient Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        patientTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientTableMouseClicked(evt);
            }
        });
        scrollPane.setViewportView(patientTable);

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPane)
                    .addComponent(background2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, background1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(9, 9, 9))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(background2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        loadPatientTable(String.valueOf(searchText.getText()));
    }//GEN-LAST:event_searchButtonActionPerformed

    private void searchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTextActionPerformed

    private void lNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lNameActionPerformed

    private void addPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPatientActionPerformed
        // TODO add your handling code here:

        String firstName = fName.getText();
        String lastName = lName.getText();
        String pEmail = email.getText();
        String pMobile = mobile.getText();
        String pAddress = address.getText();
        int patientType = patientTypeCombo.getSelectedIndex();

        if (nameValidation(firstName)) {
            return;
        }
        if (nameValidation(lastName)) {
            return;
        }
        if (emailValidation(pEmail)) {
            return;
        }
        if (mobileValidation(pMobile)) {
            return;
        }
        if (pAddress.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please Enter The Patient Address!");
        }
        if (patientTypeCombo.getSelectedIndex() == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please Select the Patient Type!");
        }
        try {
            synchronized (this) {

                java.sql.ResultSet resultSet = MySQL.search("SELECT * FROM `patient` WHERE `email`= '" + pEmail + "'");
                if (resultSet.next()) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Entered Email is already Entered!");
                } else {

                    MySQL.iud("INSERT INTO `patient`(`fname`,`lname`,`email`,`mobile`,`address`,`patient_type_id`)"
                            + "VALUES('" + firstName + "','" + lastName + "','" + pEmail + "','" + pMobile + "',"
                            + "'" + pAddress + "','" + patientType + "')");

                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Patient Registered Successfully!");
                    updateFields();
                }

            }

        } catch (SQLException se) {
            logger.warning(se.getMessage());
        }

    }//GEN-LAST:event_addPatientActionPerformed

    private void updatePatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePatientActionPerformed

        System.out.println("Okk");

        String firstName = fName.getText();
        String lastName = lName.getText();
        String pEmail = email.getText();
        String pMobile = mobile.getText();
        String pAddress = address.getText();
        int patientType = patientTypeCombo.getSelectedIndex();

        if (nameValidation(firstName)) {
            return;
        }
        if (nameValidation(lastName)) {
            return;
        }
        if (mobileValidation(pMobile)) {
            return;
        }
        if (pAddress.isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please Enter The Patient Address!");
        }
        if (patientTypeCombo.getSelectedIndex() == 0) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please Select the Patient Type!");
        }
        try {
            synchronized (this) {

                java.sql.ResultSet resultSet = MySQL.search("SELECT * FROM `patient` WHERE `email`= '" + pEmail + "'");
                if (resultSet.next()) {

                    MySQL.iud("UPDATE `patient` SET `fname`='" + firstName + "',`lname`='" + lastName + "',`mobile`='" + pMobile + "',`address`='" + pAddress + "',"
                            + "`patient_type_id`='" + patientType + "' WHERE `email`='" + pEmail + "' ");

                    Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Patient Updated Successfully!");
                    updateFields();

                } else {
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, "Patient Not Found!");
                }

            }

        } catch (SQLException se) {
            logger.warning(se.getMessage());
        }

    }//GEN-LAST:event_updatePatientActionPerformed

    private void scrollPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_scrollPaneMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_scrollPaneMouseClicked

    private void patientTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientTableMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {

            addPatient.setEnabled(false);
            updatePatient.setEnabled(true);
            email.setEditable(false);

            int selectedRow = patientTable.getSelectedRow();
            String id = (String) patientTable.getValueAt(selectedRow, 0);
            String fullName = (String) patientTable.getValueAt(selectedRow, 1);
            String email = (String) patientTable.getValueAt(selectedRow, 2);
            String mobile = (String) patientTable.getValueAt(selectedRow, 3);
            String address = (String) patientTable.getValueAt(selectedRow, 4);
            String type = (String) patientTable.getValueAt(selectedRow, 5);

            String[] split = fullName.split(" ");
            String firstName = split[0];
            String lastName = split[1];

            fName.setText(firstName);
            lName.setText(lastName);
            this.email.setText(email);
            this.mobile.setText(mobile);
            this.address.setText(address);
            patientTypeCombo.setSelectedItem(type);

        }
    }//GEN-LAST:event_patientTableMouseClicked

    private void searchButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButton1ActionPerformed
        // TODO add your handling code here:
        updateFields();
    }//GEN-LAST:event_searchButton1ActionPerformed

    private void addPatient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPatient1ActionPerformed
        // TODO add your handling code here:
        printReport();
    }//GEN-LAST:event_addPatient1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.aurelia.component.RoundButton addPatient;
    private lk.aurelia.component.RoundButton addPatient1;
    private lk.aurelia.component.RoundTextArea address;
    private lk.aurelia.component.Background background1;
    private lk.aurelia.component.Background background2;
    private lk.aurelia.component.RoundTextField email;
    private lk.aurelia.component.RoundTextField fName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private lk.aurelia.component.RoundTextField lName;
    private lk.aurelia.component.RoundTextField mobile;
    private javax.swing.JTable patientTable;
    private lk.aurelia.component.RoundComboBox patientTypeCombo;
    private javax.swing.JScrollPane scrollPane;
    private lk.aurelia.component.RoundButton searchButton;
    private lk.aurelia.component.RoundButton searchButton1;
    private lk.aurelia.component.RoundTextField searchText;
    private lk.aurelia.component.RoundButton updatePatient;
    // End of variables declaration//GEN-END:variables
}
