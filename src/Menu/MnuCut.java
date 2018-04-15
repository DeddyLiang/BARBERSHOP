package Menu;

import static Menu.FrmMain.txtItemID;
import static Menu.FrmMain.txtStock;
import static Menu.FrmMain.txtTransaction;
import static Menu.FrmMain.txtUnit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MnuCut extends javax.swing.JFrame {

    public static MnuCut instance;
    private Connection conn;

    public MnuCut(Connection conn) {
        this.conn = conn;
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        BtnPangkas = new javax.swing.JButton();
        BtnBotak = new javax.swing.JButton();
        TxtHair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("CUT MENU");
        setAlwaysOnTop(true);
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Cut.png"))); // NOI18N

        BtnPangkas.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        BtnPangkas.setText("Pangkas");
        BtnPangkas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPangkasActionPerformed(evt);
            }
        });

        BtnBotak.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        BtnBotak.setText("Botak");
        BtnBotak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBotakActionPerformed(evt);
            }
        });

        TxtHair.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtHair.setText("Hair Line");
        TxtHair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtHairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnPangkas, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnBotak, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(TxtHair, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnPangkas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnBotak, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtHair, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void BtnPangkasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPangkasActionPerformed
        executeMenu("Pangkas", 5);
    }//GEN-LAST:event_BtnPangkasActionPerformed

    private void BtnBotakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBotakActionPerformed
        executeMenu("Botak", 8);
    }//GEN-LAST:event_BtnBotakActionPerformed

    private void TxtHairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtHairActionPerformed
        executeMenu("Hair Line", 9);
    }//GEN-LAST:event_TxtHairActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton BtnBotak;
    public static javax.swing.JButton BtnPangkas;
    public static javax.swing.JButton TxtHair;
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
