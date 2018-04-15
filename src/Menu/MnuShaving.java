package Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MnuShaving extends javax.swing.JFrame {

    public static MnuShaving instance;
    private Connection conn;

    public MnuShaving(Connection conn) {
        this.conn = conn;
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TxtKumis = new javax.swing.JButton();
        TxtJenggot = new javax.swing.JButton();
        TxtFull = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setTitle("SHAVING MENU");
        setAlwaysOnTop(true);
        setResizable(false);

        TxtKumis.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtKumis.setText("Cukur Kumis");
        TxtKumis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtKumisActionPerformed(evt);
            }
        });

        TxtJenggot.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtJenggot.setText("Cukur Jenggot");
        TxtJenggot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtJenggotActionPerformed(evt);
            }
        });

        TxtFull.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtFull.setText("Cukur Full");
        TxtFull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtFullActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Shave.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TxtKumis)
                .addGap(18, 18, 18)
                .addComponent(TxtJenggot)
                .addGap(18, 18, 18)
                .addComponent(TxtFull, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(181, 181, 181))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtFull)
                    .addComponent(TxtKumis)
                    .addComponent(TxtJenggot))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtKumisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtKumisActionPerformed
        executeMenu("Cukur Kumis", 7);
    }//GEN-LAST:event_TxtKumisActionPerformed

    private void TxtJenggotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtJenggotActionPerformed
        executeMenu("Cukur Jenggot", 12);
    }//GEN-LAST:event_TxtJenggotActionPerformed

    private void TxtFullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtFullActionPerformed
        executeMenu("Cukur Kumis + Jenggot", 13);
    }//GEN-LAST:event_TxtFullActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton TxtFull;
    private javax.swing.JButton TxtJenggot;
    private javax.swing.JButton TxtKumis;
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
