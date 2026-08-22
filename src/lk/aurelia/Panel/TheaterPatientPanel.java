package lk.aurelia.Panel;

import java.io.IOException;
import java.io.InputStream;
import lk.aurelia.component.table.TableCustom;
import lk.aurelia.connection.MySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.table.DefaultTableModel;
import lk.aurelia.Gui.TheaterDashboard;
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
public class TheaterPatientPanel extends javax.swing.JPanel {

    /**
     * Creates new form DoctorDashboardPannel
     */
    private Map<String, Integer> paymentStatus;
    private Map<String, Integer> patientMap;
    private TheaterDashboard dash;
    private final SimpleDateFormat sdf;
    private static Logger logger;
    private static FileHandler handler;
    private String operationBarcode;
    private static String printDate;

    public TheaterPatientPanel(TheaterDashboard td) {
        this.dash = td;
        initComponents();

        try {
            logger = Logger.getLogger(TheaterPatientPanel.class.getName());
            handler = new FileHandler(TheaterPatientPanel.class.getName() + ".log", true);
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.paymentStatus = new HashMap<>();
        this.patientMap = new HashMap<>();
        this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        changeBarcodeNo();

        Notifications.getInstance().setJFrame(dash);
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        loadDoctor();
        loadPatient();
        loadStatus();
        loadTable();
    }

    private void changeBarcodeNo() {
        this.operationBarcode = String.valueOf(System.currentTimeMillis()) + "_" + "OP";
    }

    private void loadTable() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `operation` INNER JOIN `patient`"
                    + " ON `operation`.`patient_id`=`patient`.`id` INNER JOIN `payment_status`"
                    + " ON `payment_status`.`id`=`operation`.`payment_status_id`");

            DefaultTableModel model = (DefaultTableModel) operationTable.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Vector data = new Vector();
                data.add(rs.getString("barcode"));
                data.add(rs.getString("fname") + " " + rs.getString("lname"));
                data.add(rs.getString("doctor_barcode"));
                data.add(rs.getString("date"));
                data.add(rs.getString("price"));
                data.add(rs.getString("type"));
                model.addRow(data);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDoctor() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `doctor`");

            Vector data = new Vector();
            data.add("Select");

            while (rs.next()) {
                data.add(rs.getString("barcode"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(data);
            doctorCombo.setModel(model);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadPatient() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `patient`");

            Vector data = new Vector();
            data.add("Select");

            while (rs.next()) {
                String patient = rs.getString("fname") + "_" + rs.getString("email");
                data.add(patient);
                patientMap.put(patient, rs.getInt("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(data);
            patientCombo.setModel(model);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadStatus() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `payment_status`");

            Vector data = new Vector();
            data.add("Select");

            while (rs.next()) {
                data.add(rs.getString("type"));
                paymentStatus.put(rs.getString("type"), rs.getInt("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(data);
            statusCombo.setModel(model);

        } catch (SQLException e) {
            throw new RuntimeException(e);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        operationTable = new javax.swing.JTable();
        background1 = new lk.aurelia.component.Background();
        operationIDField = new lk.aurelia.component.RoundTextField();
        doctorCombo = new lk.aurelia.component.RoundComboBox();
        jLabel2 = new javax.swing.JLabel();
        patientCombo = new lk.aurelia.component.RoundComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        statusCombo = new lk.aurelia.component.RoundComboBox();
        addButton = new lk.aurelia.component.RoundButton();
        searchButton = new lk.aurelia.component.RoundButton();
        printButton = new lk.aurelia.component.RoundButton();
        priceField = new lk.aurelia.component.RoundFormattedField();
        jLabel5 = new javax.swing.JLabel();

        operationTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Operation ID", "Patient", "Doctor", "Date", "Price", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        operationTable.getTableHeader().setReorderingAllowed(false);
        operationTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                operationTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(operationTable);

        operationIDField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        operationIDField.setPreferredSize(new java.awt.Dimension(180, 25));

        jLabel2.setText("Patient");

        patientCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                patientComboActionPerformed(evt);
            }
        });

        jLabel3.setText("Price");

        jLabel4.setText("Status");

        addButton.setBackground(new java.awt.Color(4, 79, 118));
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("Add");
        addButton.setBorderPainted(false);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
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

        printButton.setBackground(new java.awt.Color(211, 231, 240));
        printButton.setText("Print Slip");
        printButton.setBorderPainted(false);
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        priceField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jLabel5.setText("Doctor");

        javax.swing.GroupLayout background1Layout = new javax.swing.GroupLayout(background1);
        background1.setLayout(background1Layout);
        background1Layout.setHorizontalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(operationIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(doctorCombo, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(patientCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(statusCombo, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                        .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        background1Layout.setVerticalGroup(
            background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background1Layout.createSequentialGroup()
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(operationIDField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(background1Layout.createSequentialGroup()
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(statusCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(patientCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(doctorCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(background1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel5)))
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, background1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1)
                .addGap(9, 9, 9))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        boolean isSuccess = updatePaymentTable();
        if (isSuccess) {
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("operation_barcode", operationBarcode);
            parameters.put("date_txt", printDate);
            parameters.put("payment_status", (String) statusCombo.getSelectedItem());
            String[] patient = String.valueOf(patientCombo.getSelectedItem()).split("_");
            parameters.put("patient", patient[0]);
            parameters.put("email", patient[1]);
            parameters.put("doctor", (String) doctorCombo.getSelectedItem());
            parameters.put("price", priceField.getText());

            InputStream stream = TheaterPatientPanel.class.getResourceAsStream("/lk/aurelia/reports/theaterSlip.jasper");

            try {

                JasperPrint fillReport = JasperFillManager.fillReport(stream, parameters, new JREmptyDataSource());
                JasperViewer.viewReport(fillReport, false);
                cleanValues();

            } catch (JRException ex) {
                logger.warning(ex.getMessage());
                throw new RuntimeException(ex);
            }
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private boolean updatePaymentTable() {
        if (doctorCombo.getSelectedItem() == "Select") {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Select a Doctor");
            return false;
        } else if (patientCombo.getSelectedItem() == "Select") {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Select a Patient");
            return false;
        } else if (priceField.getText().isEmpty()) {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Price cannot be Empty");
            return false;
        } else if (statusCombo.getSelectedItem() == "Select") {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Select a payment Status");
            return false;
        } else {
            String doctor = (String) doctorCombo.getSelectedItem();
            int patient = patientMap.get((String) patientCombo.getSelectedItem());
            int status = paymentStatus.get((String) statusCombo.getSelectedItem());

            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            printDate = date;

            try {
                MySQL.iud("INSERT INTO `operation` (`barcode`,`doctor_barcode`,`patient_id`,`date`,`price`,`payment_status_id`)  "
                        + "VALUES('" + operationBarcode + "','" + doctor + "','" + patient + "','" + date + "','" + priceField.getText() + "',"
                        + "'" + status + "')");
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, "Success!");
                loadTable();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        if (!operationIDField.getText().isEmpty()) {
            String id = operationIDField.getText();
            try {
                ResultSet rs = MySQL.search("SELECT * FROM `operation` INNER JOIN `patient`"
                        + " ON `operation`.`patient_id`=`patient`.`id` INNER JOIN `payment_status`"
                        + " ON `payment_status`.`id`=`operation`.`payment_status_id` WHERE `barcode`='" + id + "'");

                DefaultTableModel model = (DefaultTableModel) operationTable.getModel();
                model.setRowCount(0);

                while (rs.next()) {
                    Vector data = new Vector();
                    data.add(rs.getString("barcode"));
                    data.add(rs.getString("fname") + " " + rs.getString("lname"));
                    data.add(rs.getString("doctor_barcode"));
                    data.add(rs.getString("date"));
                    data.add(rs.getString("price"));
                    data.add(rs.getString("type"));
                    model.addRow(data);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        if (!operationTable.getSelectionModel().isSelectionEmpty()) {
            int sR = operationTable.getSelectedRow();
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("operation_barcode", (String) operationTable.getValueAt(sR, 0));
            parameters.put("date_txt", (String) operationTable.getValueAt(sR, 3));
            parameters.put("payment_status", (String) operationTable.getValueAt(sR, 5));
            parameters.put("patient", (String) operationTable.getValueAt(sR, 1));
            parameters.put("doctor", (String) operationTable.getValueAt(sR, 2));
            parameters.put("price", (String) operationTable.getValueAt(sR, 4));

            InputStream stream = TheaterPatientPanel.class.getResourceAsStream("/lk/aurelia/reports/theaterSlip.jasper");

            try {

                JasperPrint fillReport = JasperFillManager.fillReport(stream, parameters, new JREmptyDataSource());
                JasperViewer.viewReport(fillReport, false);

                cleanValues();

            } catch (JRException ex) {
                logger.warning(ex.getMessage());
                throw new RuntimeException(ex);
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, "Select an Operation from the table");
        }
    }//GEN-LAST:event_printButtonActionPerformed

    private void operationTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_operationTableMouseClicked
        if (evt.getClickCount() == 2) {
            operationTable.clearSelection();
        }

    }//GEN-LAST:event_operationTableMouseClicked

    private void patientComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_patientComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_patientComboActionPerformed

    private void cleanValues() {
        doctorCombo.setSelectedItem("Select");
        patientCombo.setSelectedItem("Select");
        statusCombo.setSelectedItem("Select");
        priceField.setText("0.00");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lk.aurelia.component.RoundButton addButton;
    private lk.aurelia.component.Background background1;
    private lk.aurelia.component.RoundComboBox doctorCombo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private lk.aurelia.component.RoundTextField operationIDField;
    private javax.swing.JTable operationTable;
    private lk.aurelia.component.RoundComboBox patientCombo;
    private lk.aurelia.component.RoundFormattedField priceField;
    private lk.aurelia.component.RoundButton printButton;
    private lk.aurelia.component.RoundButton searchButton;
    private lk.aurelia.component.RoundComboBox statusCombo;
    // End of variables declaration//GEN-END:variables
}
