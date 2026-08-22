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
import lk.aurelia.Dialog.viewMorePharmacistDetails;
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
public class ManagePharmacistPanel extends javax.swing.JPanel {

    private String pharmacistBarcode;
    private Map<String, Integer> pharmacistStatusMap;
    private Logger logger;
    private FileHandler handler;
    private String managerName;
    private JFrame dash;

    /**
     * Creates new form ManagePharmacistPanel
     */
    public ManagePharmacistPanel(JFrame parent, String name) {
        initComponents();
        this.managerName = name;
        this.dash = parent;
        this.pharmacistStatusMap = new HashMap<>();
        try {
            logger = Logger.getLogger(ManagePharmacistPanel.class.getName());
            handler = new FileHandler(ManagePharmacistPanel.class.getName() + ".log", true);
            logger.addHandler(handler);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);

        filterCombo();
        loadTable("", "");
        loadStatusCombo();
        init();
    }

    private void init() {
        Notifications.getInstance().setJFrame(dash);
        pharmacistField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "First Name");
        pharmacistField2.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Last Name");
        pharmacistField3.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Email");
        passwordField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
        pharmacistField4.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Mobile");
        pharmacistField6.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Address");
        pharmacistField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search Here...");
    }

    private void loadStatusCombo() {
        try {
            ResultSet resultSet = MySQL.search("SELECT * FROM `status`");

            Vector<String> v = new Vector<>();
            v.add("Select");
            while (resultSet.next()) {
                v.add(resultSet.getString("status"));
                pharmacistStatusMap.put(resultSet.getString("status"), resultSet.getInt("id"));

            }
            DefaultComboBoxModel m = new DefaultComboBoxModel(v);
            pharmacyCombo.setModel(m);

        } catch (Exception e) {
            logger.warning(e.getMessage());
        }

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
            pharmacyFilterCombo.setModel(m);

        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }
    }

    private void searchTable(String query) {
        try {
            ResultSet resultSet = MySQL.search(query);
            DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
            m.setRowCount(0);
            while (resultSet.next()) {
                Vector<String> v = new Vector<>();
                v.add(resultSet.getString("pharmacist.barcode"));
                v.add(resultSet.getString("fname") + " " + resultSet.getString("lname"));
                v.add(resultSet.getString("email"));
                v.add(resultSet.getString("password"));
                v.add(resultSet.getString("mobile"));

                m.addRow(v);
                jTable1.setModel(m);

            }
        } catch (SQLException e) {
            logger.warning(e.getMessage());
        }

    }

    private String query = "SELECT * FROM `user_details` INNER JOIN `pharmacist` ON `pharmacist`.user_details_id  = user_details.id ";

    private void loadTable(String search, String status) {

        if (search.isBlank() && status.isBlank()) {
            searchTable(query);
        } else if (!search.isBlank() && status.isBlank()) {
            String newQuery = query + " WHERE (`user_details`.fname LIKE '%" + search + "%' OR `user_details`.mobile LIKE '%" + search + "%' )";
            searchTable(newQuery);
        } else if (search.isBlank() && !status.isBlank()) {
            String newQuery = query + " WHERE `user_details`.status_id = '" + status + "' ";
            searchTable(newQuery);
        } else if (!search.isBlank() && !status.isBlank()) {
            String newQuery = query + " WHERE `user_details`.status_id = '" + status + "' AND (`user_details`.fname LIKE '%" + search + "%' "
                    + "OR `user_details`.mobile LIKE '%" + search + "%' ) )";
            searchTable(newQuery);
        }

    }

    private void reset() {

        pharmacistField1.setText("");
        pharmacistField.setText("");
        pharmacistField2.setText("");
        pharmacistField3.setText("");
        pharmacistField4.setText("");
        pharmacistField6.setText("");
        pharmacyCombo.setSelectedIndex(0);
        pharmacistField1.grabFocus();
        passwordField.setText("");

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
        pharmacistField = new lk.aurelia.component.RoundTextField();
        pharmacistField1 = new lk.aurelia.component.RoundTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pharmacistField2 = new lk.aurelia.component.RoundTextField();
        pharmacistField4 = new lk.aurelia.component.RoundTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        pharmacistField3 = new lk.aurelia.component.RoundTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pharmacistField6 = new lk.aurelia.component.RoundTextField();
        pharmacyCombo = new lk.aurelia.component.RoundComboBox();
        jLabel11 = new javax.swing.JLabel();
        addButton = new lk.aurelia.component.RoundButton();
        addButton1 = new lk.aurelia.component.RoundButton();
        passwordField = new lk.aurelia.component.RoundPasswordField();
        addButton2 = new lk.aurelia.component.RoundButton();
        pharmacyFilterCombo = new lk.aurelia.component.RoundComboBox();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        printButton = new lk.aurelia.component.RoundButton();

        setPreferredSize(new java.awt.Dimension(837, 598));

        searchButton.setBackground(new java.awt.Color(211, 231, 240));
        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lk/aurelia/img/search 16x16.png"))); // NOI18N
        searchButton.setText("Search");
        searchButton.setBorderPainted(false);
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        pharmacistField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pharmacistField.setPreferredSize(new java.awt.Dimension(180, 25));

        pharmacistField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pharmacistField1.setPreferredSize(new java.awt.Dimension(180, 25));

        jLabel5.setText("First name");

        jLabel6.setText("Last name");

        pharmacistField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pharmacistField2.setPreferredSize(new java.awt.Dimension(180, 25));

        pharmacistField4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pharmacistField4.setPreferredSize(new java.awt.Dimension(180, 25));

        jLabel7.setText(" Mobile");

        jLabel8.setText("Email");

        pharmacistField3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pharmacistField3.setPreferredSize(new java.awt.Dimension(180, 25));

        jLabel9.setText("Password");

        jLabel10.setText("Address");

        pharmacistField6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pharmacistField6.setPreferredSize(new java.awt.Dimension(180, 25));

        jLabel11.setText("Status");

        addButton.setBackground(new java.awt.Color(4, 79, 118));
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Add Pharmacist");
        addButton.setBorderPainted(false);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        addButton1.setBackground(new java.awt.Color(0, 102, 102));
        addButton1.setForeground(new java.awt.Color(255, 255, 255));
        addButton1.setText("Update Pharmacist");
        addButton1.setBorderPainted(false);
        addButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButton1ActionPerformed(evt);
            }
        });

        passwordField.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N

        addButton2.setBackground(new java.awt.Color(0, 153, 153));
        addButton2.setForeground(new java.awt.Color(255, 255, 255));
        addButton2.setText("View More");
        addButton2.setBorderPainted(false);
        addButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButton2ActionPerformed(evt);
            }
        });

        pharmacyFilterCombo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                pharmacyFilterComboItemStateChanged(evt);
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
                        .addComponent(pharmacyFilterCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pharmacistField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                    .addComponent(pharmacistField1, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                    .addComponent(pharmacyCombo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pharmacistField2, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                        .addComponent(pharmacistField4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pharmacistField3, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pharmacistField6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pharmacistField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pharmacistField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pharmacistField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pharmacistField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pharmacyCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(22, 22, 22)
                            .addComponent(pharmacistField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pharmacistField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pharmacyFilterCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Barcode", "Full Name", "Email", "Password", "Mobile"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        printButton.setText("Print Pharmacist");
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        pharmacistBarcode = String.valueOf(System.currentTimeMillis()) + "_" + "PH";
        String fname = pharmacistField1.getText();
        String lname = pharmacistField2.getText();
        String email = pharmacistField3.getText();
        String password = String.valueOf(passwordField.getPassword());
        String status = String.valueOf(pharmacyCombo.getSelectedItem());
        String mobile = pharmacistField4.getText();
        String address = pharmacistField6.getText();

        if (fname.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "First Name is Required");
        } else if (lname.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Last Name is Required");

        } else if (email.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Email is Required");

        } else if (!email.matches(Validation.EMAIL_VALIDATION.validate())) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please Enter a Valid Email");

        } else if (password.isBlank()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Password is Required");

        } else if (!password.matches(Validation.PASSWORD_VALIDATION.validate())) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Password must inclued one Uppercase,Number & "
                    + "Special Character ");
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
                int receptionStatusID = pharmacistStatusMap.get(status);
                ResultSet resultSet = MySQL.search("SELECT * FROM `user_details` WHERE `email` = '" + email + "' AND `user_type_id` = '6' ");
                if (resultSet.next()) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "This user is Already Existed");
                } else {
                    MySQL.iud("INSERT INTO `user_details`(`fname`,`lname`,`mobile`,`email`,`password`,`registered_date`,`address`,`status_id`,"
                            + "`user_type_id`)  VALUES('" + fname + "','" + lname + "','" + mobile + "','" + email + "','" + password + "','" + date + "',"
                            + "'" + address + "','" + receptionStatusID + "','6') ");
                    ResultSet resultSetPharmacist = MySQL.search("SELECT * FROM `user_details` WHERE `email` = '" + email + "' AND `user_type_id` = '6' ");
                    if (resultSetPharmacist.next()) {
                        String userId = resultSetPharmacist.getString("id");
                        MySQL.iud("INSERT INTO `pharmacist` VALUES('" + pharmacistBarcode + "','" + userId + "') ");
                        loadTable("", "");
                        reset();
                    } else {
                        Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Something Went Wrong");

                    }

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
            String fname = pharmacistField1.getText();
            String lname = pharmacistField2.getText();
            String mobile = pharmacistField4.getText();
            String status = String.valueOf(pharmacyCombo.getSelectedItem());
            String address = pharmacistField6.getText();

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
                    int statusId = pharmacistStatusMap.get(status);
                    MySQL.iud("UPDATE `user_details` INNER JOIN `pharmacist` ON `pharmacist`.user_details_id = user_details.id SET `fname` = '" + fname + "',`lname`='" + lname + "',`mobile`='" + mobile + "'"
                            + ",`address` = '" + address + "',`status_id` = '" + statusId + "' WHERE `pharmacist`.barcode  = '" + String.valueOf(jTable1.getValueAt(selectedRow, 0)) + "' ");

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
        pharmacistField3.setEditable(true);
        passwordField.setEditable(true);
    }

    private void deactiveField() {
        pharmacistField3.setEditable(false);
        passwordField.setEditable(false);
    }
    private void addButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButton2ActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Please Select a Row First");
        } else {

            new viewMorePharmacistDetails(dash, true, String.valueOf(jTable1.getValueAt(selectedRow, 0))).setVisible(true);

        }
    }//GEN-LAST:event_addButton2ActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed

        InputStream stream = getClass().getResourceAsStream("/lk/aurelia/reports/pharmesist_report.jasper");

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String date = format.format(new Date());
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("name", managerName);
            parameters.put("date_txt", date);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());
            JasperPrint print = JasperFillManager.fillReport(stream, parameters, dataSource);
            JasperViewer.viewReport(print, false);

        } catch (JRException ex) {
            logger.warning(ex.getMessage());
        }


    }//GEN-LAST:event_printButtonActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            try {

                int selectedRow = jTable1.getSelectedRow();
                String fullName = String.valueOf(jTable1.getValueAt(selectedRow, 1));
                String email = String.valueOf(jTable1.getValueAt(selectedRow, 2));
                String password = String.valueOf(jTable1.getValueAt(selectedRow, 3));
                String mobile = String.valueOf(jTable1.getValueAt(selectedRow, 4));

                ResultSet resultSet = MySQL.search("SELECT * FROM `user_details` INNER JOIN `pharmacist` ON "
                        + "`pharmacist`.user_details_id = `user_details`.id "
                        + " WHERE pharmacist.barcode = '" + String.valueOf(jTable1.getValueAt(selectedRow, 0)) + "'  ");
                String address = "";
                int statusId = 0;
                if (resultSet.next()) {
                    address = resultSet.getString("user_details.address");
                    statusId = resultSet.getInt("user_details.status_id");
                }

                pharmacistField1.setText(fullName.split(" ")[0]);
                pharmacistField2.setText(fullName.split(" ")[1]);
                pharmacistField3.setText(email);
                passwordField.setText(password);
                pharmacistField4.setText(mobile);
                pharmacistField6.setText(address);
                pharmacyCombo.setSelectedIndex(statusId);
                deactiveField();

            } catch (SQLException e) {
                logger.warning(e.getMessage());
            }

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        if (pharmacistField.getText().isEmpty()) {
            loadTable("", "");
        } else {
            loadTable(pharmacistField.getText(), "");
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void pharmacyFilterComboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_pharmacyFilterComboItemStateChanged
        if (pharmacyFilterCombo.getSelectedIndex() == 0) {
            loadTable(pharmacistField.getText(), "");
        } else {
            loadTable(pharmacistField.getText(), String.valueOf(pharmacyFilterCombo.getSelectedIndex()));
        }
    }//GEN-LAST:event_pharmacyFilterComboItemStateChanged


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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private lk.aurelia.component.RoundPasswordField passwordField;
    private lk.aurelia.component.RoundTextField pharmacistField;
    private lk.aurelia.component.RoundTextField pharmacistField1;
    private lk.aurelia.component.RoundTextField pharmacistField2;
    private lk.aurelia.component.RoundTextField pharmacistField3;
    private lk.aurelia.component.RoundTextField pharmacistField4;
    private lk.aurelia.component.RoundTextField pharmacistField6;
    private lk.aurelia.component.RoundComboBox pharmacyCombo;
    private lk.aurelia.component.RoundComboBox pharmacyFilterCombo;
    private lk.aurelia.component.RoundButton printButton;
    private lk.aurelia.component.RoundButton searchButton;
    // End of variables declaration//GEN-END:variables
}
