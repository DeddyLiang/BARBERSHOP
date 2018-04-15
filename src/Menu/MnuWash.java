package Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MnuWash extends javax.swing.JFrame {

    public static MnuWash instance;
    private Connection conn;

    public MnuWash(Connection conn) {
        this.conn = conn;
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TxtCuci = new javax.swing.JButton();
        TxtBlow = new javax.swing.JButton();
        TxtStyling = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setTitle("WASHING MENU");
        setAlwaysOnTop(true);
        setResizable(false);

        TxtCuci.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtCuci.setText("Cuci");
        TxtCuci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCuciActionPerformed(evt);
            }
        });

        TxtBlow.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtBlow.setText("Cuci + Blow");
        TxtBlow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtBlowActionPerformed(evt);
            }
        });

        TxtStyling.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtStyling.setText("Cuci Styling");
        TxtStyling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtStylingActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Wash_1.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(TxtCuci, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtBlow, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtStyling)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtStyling)
                    .addComponent(TxtCuci)
                    .addComponent(TxtBlow))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtCuciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCuciActionPerformed
        executeMenu("Cuci", 10);
    }//GEN-LAST:event_TxtCuciActionPerformed

    private void TxtBlowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtBlowActionPerformed
        executeMenu("Cuci & Blow", 6);
    }//GEN-LAST:event_TxtBlowActionPerformed

    private void TxtStylingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtStylingActionPerformed
        executeMenu("Cuci Styling", 11);
    }//GEN-LAST:event_TxtStylingActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton TxtBlow;
    private javax.swing.JButton TxtCuci;
    private javax.swing.JButton TxtStyling;
    private javax.swing.JLabel jLabel1;
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
