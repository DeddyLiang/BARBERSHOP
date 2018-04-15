package Menu;

import Menu.MenuTable.tblStock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MnuItem extends javax.swing.JFrame {

    public static MnuItem instance;
    private Connection conn;
    
    public MnuItem(Connection conn) {
        this.conn = conn;
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtWax = new javax.swing.JButton();
        txtHairSpray = new javax.swing.JButton();
        txtPomade = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setTitle("ITEM MENU");
        setAlwaysOnTop(true);
        setResizable(false);

        txtWax.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtWax.setText("Wax");
        txtWax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtWaxActionPerformed(evt);
            }
        });

        txtHairSpray.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtHairSpray.setText("Hair Spray");
        txtHairSpray.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHairSprayActionPerformed(evt);
            }
        });

        txtPomade.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtPomade.setText("Pomade");
        txtPomade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPomadeActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/PomadeV.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtPomade, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtWax, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtHairSpray, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(177, 177, 177)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHairSpray)
                    .addComponent(txtPomade)
                    .addComponent(txtWax))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPomadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPomadeActionPerformed
        executeMenu("Pomade", 2);
    }//GEN-LAST:event_txtPomadeActionPerformed

    private void txtWaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtWaxActionPerformed
        executeMenu("Wax", 3);
    }//GEN-LAST:event_txtWaxActionPerformed

    private void txtHairSprayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHairSprayActionPerformed
        executeMenu("Hair Spray", 4);
    }//GEN-LAST:event_txtHairSprayActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton txtHairSpray;
    private javax.swing.JButton txtPomade;
    private javax.swing.JButton txtWax;
    // End of variables declaration//GEN-END:variables

    private void executeMenu(String Transaction, int ItemID){
        
        FrmMain.txtTransaction.setText(Transaction);
        FrmMain.txtItemID.setText(String.valueOf(ItemID));
        
        try {

            String sql = "select * from item where itemid=?";
            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setInt(1, ItemID);
            ResultSet rs = pstatement.executeQuery();
            try {
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {

                        FrmMain.txtTransaction.setText(String.valueOf(rs.getString("itemname")));
                        FrmMain.txtStock.setText(String.valueOf(rs.getInt("stock")));
                        FrmMain.txtUnit.setText(String.valueOf(rs.getDouble("unitprice")));

                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Gagal koneksi " + e);
                rs.close();
                pstatement.close();
                conn.close();
            }

        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());

        }
     dispose();
    
    }    

}
