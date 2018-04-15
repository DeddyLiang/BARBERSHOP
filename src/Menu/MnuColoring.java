package Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MnuColoring extends javax.swing.JFrame {

    public static MnuColoring instance;
    private Connection conn;

    public MnuColoring(Connection conn) {
        this.conn = conn;
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TxtHitam = new javax.swing.JButton();
        TxtDuaWarna = new javax.swing.JButton();
        TxtTattoo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setTitle("COLORING MENU");
        setAlwaysOnTop(true);
        setResizable(false);

        TxtHitam.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtHitam.setText("Hitam");
        TxtHitam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtHitamActionPerformed(evt);
            }
        });

        TxtDuaWarna.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtDuaWarna.setText("Dua Warna");
        TxtDuaWarna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtDuaWarnaActionPerformed(evt);
            }
        });

        TxtTattoo.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtTattoo.setText("Tattoo");
        TxtTattoo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtTattooActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Colour.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TxtHitam, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TxtDuaWarna, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TxtTattoo, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtTattoo)
                    .addComponent(TxtHitam)
                    .addComponent(TxtDuaWarna))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtHitamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtHitamActionPerformed
        executeMenu("Cat warna hitam", 14);
    }//GEN-LAST:event_TxtHitamActionPerformed

    private void TxtDuaWarnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDuaWarnaActionPerformed
        executeMenu("Cat dua warna", 15);
    }//GEN-LAST:event_TxtDuaWarnaActionPerformed

    private void TxtTattooActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtTattooActionPerformed
        executeMenu("Tatto", 16);
    }//GEN-LAST:event_TxtTattooActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton TxtDuaWarna;
    private javax.swing.JButton TxtHitam;
    private javax.swing.JButton TxtTattoo;
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
