/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf;

import codes.DBconnect;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Prabath
 */
public class inventory extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    PreparedStatement pst1 = null;
    PreparedStatement pst2 = null;
    ResultSet rs = null;
    DefaultTableModel df = null;

    /**
     * Creates new form inventory
     */
    public inventory() {
        initComponents();
    }

    String pnom;
    String npno;

    public inventory(String pno) {
        initComponents();
        conn = DBconnect.connect();

        this.pnom = pno;
        npno = pnom;

        lblpno.setText(npno);
    }

    public void sales() {

        DateTimeFormatter da = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        String date = da.format(now);

        String subtot = txttotal.getText();
        String pay = txtpay.getText();
        String balance = txtbal.getText();

        int lastinsertid = 0;

        try {
            String query = "insert into sales(date,subtotal,pay,balance) values(?,?,?,?)";
            pst = conn.prepareStatement(query);
            pst.setString(1, date);
            pst.setString(2, subtot);
            pst.setString(3, pay);
            pst.setString(4, balance);

            pst.executeUpdate();
            rs = pst.getGeneratedKeys();

            if (rs.next()) {

                lastinsertid = rs.getInt(1);

            }

            int rows = table.getColumnCount();

            String query1 = "insert into sale_product(salesid,prodid,sellprice,qty,total) values(?,?,?,?,?)";
            pst = conn.prepareStatement(query1);

            String presid;
            String itemid;
            String itemname;
            double price;
            String qty;
            double total;

            for (int i = 0; i < table.getRowCount(); i++) {

                presid = (String) table.getValueAt(i, 0);
                itemid = (String) table.getValueAt(i, 1);

                qty = table.getValueAt(i, 3).toString();
                int qty1 = Integer.parseInt(qty);
                price = (double) table.getValueAt(i, 4);
                total = (double) table.getValueAt(i, 5);

                pst.setInt(1, lastinsertid);
                pst.setString(2, itemid);
                pst.setDouble(3, price);
                pst.setInt(4, qty1);

                pst.setDouble(5, total);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Sales added successfully..!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(inventory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblpno = new javax.swing.JLabel();
        txtbal = new javax.swing.JTextField();
        txtdcode = new javax.swing.JTextField();
        txtqty = new javax.swing.JSpinner();
        txtdname = new javax.swing.JTextField();
        txttotal = new javax.swing.JTextField();
        txtpay = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setFont(new java.awt.Font("Eras Medium ITC", 1, 14)); // NOI18N
        table.setForeground(new java.awt.Color(0, 102, 204));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Prescription ID", "Drug code", "Drug name", "Qty", "Price", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 399, 820, 280));

        jPanel2.setBackground(new java.awt.Color(0, 102, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Eras Medium ITC", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Prescription ID");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Eras Medium ITC", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Drug Code");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabel4.setFont(new java.awt.Font("Eras Medium ITC", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Drug Name");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel5.setFont(new java.awt.Font("Eras Medium ITC", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Quantity");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        jLabel6.setFont(new java.awt.Font("Eras Medium ITC", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Total Cost");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, -1, -1));

        jLabel7.setFont(new java.awt.Font("Eras Medium ITC", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Payment");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, -1, -1));

        jLabel8.setFont(new java.awt.Font("Eras Medium ITC", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Balance");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, -1, -1));

        lblpno.setFont(new java.awt.Font("Eras Medium ITC", 0, 14)); // NOI18N
        lblpno.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lblpno, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 90, 40));

        txtbal.setFont(new java.awt.Font("Eras Medium ITC", 0, 14)); // NOI18N
        txtbal.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(txtbal, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 170, 170, -1));

        txtdcode.setFont(new java.awt.Font("Eras Medium ITC", 0, 14)); // NOI18N
        txtdcode.setForeground(new java.awt.Color(0, 0, 0));
        txtdcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdcodeKeyPressed(evt);
            }
        });
        jPanel2.add(txtdcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 170, -1));
        jPanel2.add(txtqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 50, -1));

        txtdname.setFont(new java.awt.Font("Eras Medium ITC", 0, 14)); // NOI18N
        txtdname.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(txtdname, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 170, -1));

        txttotal.setFont(new java.awt.Font("Eras Medium ITC", 0, 14)); // NOI18N
        txttotal.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(txttotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, 170, -1));

        txtpay.setFont(new java.awt.Font("Eras Medium ITC", 0, 14)); // NOI18N
        txtpay.setForeground(new java.awt.Color(0, 0, 0));
        jPanel2.add(txtpay, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 110, 170, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Eras Medium ITC", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 204));
        jButton1.setText("Close");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, 100, 40));

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Eras Medium ITC", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 102, 204));
        jButton2.setText("Sales Update");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(607, 230, 130, 50));

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 30, 210));

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, 30, 210));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Eras Medium ITC", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 102, 204));
        jButton3.setText("Add");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 220, 100, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 820, 320));

        jLabel1.setFont(new java.awt.Font("Eras Medium ITC", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("SALES");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 690));

        setSize(new java.awt.Dimension(972, 729));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtdcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdcodeKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String dcode = txtdcode.getText();

            try {
                pst = conn.prepareStatement("select * from item where itemid = ?");
                pst.setString(1, dcode);
                rs = pst.executeQuery();

                if (rs.next() == false) {

                    JOptionPane.showMessageDialog(this, "Drug not found..!");
                    txtdcode.setText("");

                } else {

                    String dname = rs.getString("itemname");
                    txtdname.setText(dname.trim());
                    txtqty.requestFocus();

                }
            } catch (SQLException ex) {
                Logger.getLogger(inventory.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    rs.close();
                    pst.close();
                } catch (Exception e) {
                }
            }

        }


    }//GEN-LAST:event_txtdcodeKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        String dcode = txtdcode.getText();
        try {
            pst = conn.prepareStatement("select * from item where itemid = ?");
            pst.setString(1, dcode);
            rs = pst.executeQuery();

            while (rs.next()) {
                int currentQty;
                double sellprice;
                int qty;

                currentQty = rs.getInt("qty");
                sellprice = rs.getDouble("sellprice");

                qty = Integer.parseInt(txtqty.getValue().toString());

                double tot = sellprice * qty;

                if (qty >= currentQty) {

                    JOptionPane.showMessageDialog(this, "Available item is" + currentQty);
                    JOptionPane.showMessageDialog(this, "Quantity not enough...!");

                } else {

                    DefaultTableModel df = (DefaultTableModel) table.getModel();
                    df.addRow(new Object[]{
                        lblpno.getText(),
                        txtdcode.getText(),
                        txtdname.getText(),
                        txtqty.getValue().toString(),
                        sellprice,
                        tot,}
                    );

                    double sum = 0;
                    for (int i = 0; i < table.getRowCount(); i++) {

                        sum = sum + Double.parseDouble(table.getValueAt(i, 5).toString());

                    }

                    txttotal.setText(Double.toString(sum));

                    txtdcode.setText("");
                    txtdname.setText("");
                    txtqty.setValue(0);

                    txtdcode.requestFocus();

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(inventory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        double pay = (Double.parseDouble(txtpay.getText()));
        double totcost = (Double.parseDouble(txttotal.getText()));

        double balance = pay - totcost;

        txtbal.setText(String.valueOf(balance));

        sales();

    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inventory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblpno;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtbal;
    private javax.swing.JTextField txtdcode;
    private javax.swing.JTextField txtdname;
    private javax.swing.JTextField txtpay;
    private javax.swing.JSpinner txtqty;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
}
