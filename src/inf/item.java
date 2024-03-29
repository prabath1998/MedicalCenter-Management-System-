/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inf;

import codes.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Prabath
 */
public class item extends javax.swing.JPanel {

    Connection conn = null;
    PreparedStatement pst = null;
    PreparedStatement pst1 = null;
    PreparedStatement pst2 = null;
    ResultSet rs = null;
    DefaultTableModel df = null;

    /**
     * Creates new form patient
     */
    public item() {
        initComponents();
        conn = DBconnect.connect();
        AutoID();
        tableLoad();
    }

    public void tableLoad() {

//        try {
//            pst = conn.prepareStatement("select * from patient");
//            rs = pst.executeQuery();
//
//            ResultSetMetaData rsm = rs.getMetaData();
//            int c;
//            c = rsm.getColumnCount();
//
//            DefaultTableModel df = (DefaultTableModel) table.getModel();
//            df.setRowCount(c);
//
//            while (rs.next()) {
//                Vector v2 = new Vector();
//
//                for (int i = 1; i <= c; i++) {
//
//                    v2.add(rs.getString("patientno"));
//                    v2.add(rs.getString("name"));
//                    v2.add(rs.getString("phone"));
//                    v2.add(rs.getString("address"));
//
//                }
//                
//                df.addRow(v2);
//
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(patient.class.getName()).log(Level.SEVERE, null, ex);
//        }
        String sql = "SELECT * FROM item";

        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }

    }

    public void clear() {

        txtname.setText("");
        txtqty.setText("");
        txtdescription.setText("");
        txtsellprice.setText("");
        txtbprice.setText("");
    }

    public void AutoID() {

        try {
            Statement s = conn.createStatement();
            rs = s.executeQuery("select MAX(itemid) from item");
            rs.next();
            rs.getString("MAX(itemid)");

            if (rs.getString("MAX(itemid)") == null) {

                lblitemid.setText("IU001");

            } else {

                long id = Long.parseLong(rs.getString("MAX(itemid)").substring(2, rs.getString("MAX(itemid)").length()));
                id++;
                lblitemid.setText("IU" + String.format("%03d", id));

            }

        } catch (SQLException ex) {
            Logger.getLogger(item.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtqty = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lblitemid = new javax.swing.JLabel();
        txtdescription = new javax.swing.JTextField();
        txtsellprice = new javax.swing.JTextField();
        txtbprice = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btnadd = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Create Item", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Eras Medium ITC", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Eras Medium ITC", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Item ID");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Eras Medium ITC", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Item Name");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        jLabel4.setFont(new java.awt.Font("Eras Medium ITC", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Description");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        jLabel5.setFont(new java.awt.Font("Eras Medium ITC", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Quantity");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, -1));

        txtqty.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        txtqty.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(txtqty, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 400, 190, -1));

        txtname.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        txtname.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 190, -1));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setToolTipText("");
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 30, 370));

        lblitemid.setFont(new java.awt.Font("Eras Medium ITC", 1, 16)); // NOI18N
        lblitemid.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lblitemid, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 190, 30));

        txtdescription.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        txtdescription.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(txtdescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 190, -1));

        txtsellprice.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        txtsellprice.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(txtsellprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 190, -1));

        txtbprice.setFont(new java.awt.Font("Eras Medium ITC", 0, 16)); // NOI18N
        txtbprice.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.add(txtbprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 330, 190, -1));

        jLabel6.setFont(new java.awt.Font("Eras Medium ITC", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Sell Price");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 259, -1, 20));

        jLabel7.setFont(new java.awt.Font("Eras Medium ITC", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Buy Price");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 470, 470));

        jLabel1.setFont(new java.awt.Font("Eras Medium ITC", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("CREATE ITEM");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Eras Medium ITC", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 204));
        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Eras Medium ITC", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 102, 204));
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4);

        btnupdate.setBackground(new java.awt.Color(255, 255, 255));
        btnupdate.setFont(new java.awt.Font("Eras Medium ITC", 1, 14)); // NOI18N
        btnupdate.setForeground(new java.awt.Color(0, 102, 204));
        btnupdate.setText("Update");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });
        jPanel2.add(btnupdate);

        btnadd.setBackground(new java.awt.Color(255, 255, 255));
        btnadd.setFont(new java.awt.Font("Eras Medium ITC", 1, 14)); // NOI18N
        btnadd.setForeground(new java.awt.Color(0, 102, 204));
        btnadd.setText("Add");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });
        jPanel2.add(btnadd);

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 610, 330, 50));

        table.setFont(new java.awt.Font("Eras Medium ITC", 1, 12)); // NOI18N
        table.setForeground(new java.awt.Color(0, 102, 204));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item No", "Item name", "Description", "Sell Price", "Buy Price", "Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 130, 540, 580));
    }// </editor-fold>//GEN-END:initComponents

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed

        String itemno = lblitemid.getText();
        String itemname = txtname.getText();
        String itemdes = txtdescription.getText();
        String sellprice = txtsellprice.getText();
        String buyprice = txtbprice.getText();
        
        String qty = txtqty.getText();
      

        try {
            pst = conn.prepareStatement("insert into item(itemid,itemname,description,sellprice,buyprice,qty)values(?,?,?,?,?,?)");
            pst.setString(1, itemno);
            pst.setString(2, itemname);
            pst.setString(3, itemdes);
            pst.setString(4, sellprice);
            pst.setString(5, buyprice);
            pst.setString(6, qty);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Item is added successfully!");
            AutoID();
            clear();
            txtname.requestFocus();
            tableLoad();

        } catch (SQLException ex) {
            Logger.getLogger(item.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }


    }//GEN-LAST:event_btnaddActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

        DefaultTableModel d1 = (DefaultTableModel) table.getModel();
        int SelectIndex = table.getSelectedRow();

        lblitemid.setText(d1.getValueAt(SelectIndex, 0).toString());
        txtname.setText(d1.getValueAt(SelectIndex, 1).toString());
        txtdescription.setText(d1.getValueAt(SelectIndex, 2).toString());
        txtsellprice.setText(d1.getValueAt(SelectIndex, 3).toString());
        txtbprice.setText(d1.getValueAt(SelectIndex, 4).toString());
        txtqty.setText(d1.getValueAt(SelectIndex, 5).toString());

        btnadd.setEnabled(false);

    }//GEN-LAST:event_tableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        clear();
        AutoID();
        btnadd.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
         String itemno = lblitemid.getText();
        String itemname = txtname.getText();
        String itemdes = txtdescription.getText();
        String sellprice = txtsellprice.getText();
        String buyprice = txtbprice.getText();
        
        String qty = txtqty.getText();
      

        try {
            pst = conn.prepareStatement("update item set itemname = ?, description = ?, sellprice = ?, buyprice = ?, qty = ? where itemid = ?");
            
            pst.setString(1, itemname);
            pst.setString(2, itemdes);
            pst.setString(3, sellprice);
            pst.setString(4, buyprice);
            pst.setString(5, qty);
            pst.setString(6, itemno);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Item is Updated successfully!");
            AutoID();
            clear();
            txtname.requestFocus();
            tableLoad();

        } catch (SQLException ex) {
            Logger.getLogger(item.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }

    }//GEN-LAST:event_btnupdateActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int check = JOptionPane.showConfirmDialog(null, "Do you want to delete?");

        if (check == 0) {
            df = (DefaultTableModel) table.getModel();
            int selected = table.getSelectedRow();
            String id = (df.getValueAt(selected, 0).toString());
            try {

                String sql = "DELETE FROM item WHERE itemid='" + id + "'";
                pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Deleted!");
                AutoID();

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, e);
            }finally {
            try {
                rs.close();
                pst.close();
            } catch (Exception e) {
            }
        }

        }
        tableLoad();
        clear();
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnupdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblitemid;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtbprice;
    private javax.swing.JTextField txtdescription;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtqty;
    private javax.swing.JTextField txtsellprice;
    // End of variables declaration//GEN-END:variables
}
