package Menu;

import Menu.MenuTable.RosterBarber;
import Menu.MenuTable.RosterMember;
import Menu.MenuTable.TableOrderCRUD;
import Menu.MenuTable.tblStock;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import util.Sutil;

public class FrmMain extends javax.swing.JFrame {
    int qty;
    Double totalprice, paid, returnpaid;
    private Connection conn;
    public static int receiptCounter = 1;

    public FrmMain(Connection conn) {
        this.conn = conn;
        initComponents();
        load();
        setLocationRelativeTo(null);
        
        LoadTableTransaction();
    }

    private void executeMenu(String Transaction, int ItemID) {

        txtTransaction.setText(Transaction);
        txtItemID.setText(String.valueOf(ItemID));

        try {

            String sql = "select * from item where itemid=?";
            PreparedStatement pstatement = conn.prepareStatement(sql);
            pstatement.setInt(1, ItemID);
            ResultSet rs = pstatement.executeQuery();
            try {
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {

                        txtTransaction.setText(String.valueOf(rs.getString("itemname")));
                        txtStock.setText(String.valueOf(rs.getInt("stock")));
                        txtUnit.setText(String.valueOf(rs.getDouble("unitprice")));

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

    private void LoadStockWax() {

        try {

            String sql = "select stock from item where itemid=3";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            ResultSet rs = pstatement.executeQuery();
            try {
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {

                        txtStock.setText(String.valueOf(rs.getInt("stock")));

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

    }

    private void LoadStockPomade() {

        try {

            String sql = "select stock from item where itemid=2";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            ResultSet rs = pstatement.executeQuery();
            try {
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {

                        txtStock.setText(String.valueOf(rs.getInt("stock")));

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

    }

    private void LoadStockHairSpray() {

        try {

            String sql = "select stock from item where itemid=4";
            PreparedStatement pstatement = conn.prepareStatement(sql);

            ResultSet rs = pstatement.executeQuery();
            try {
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {

                        txtStock.setText(String.valueOf(rs.getInt("stock")));

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

    }

    private void executeCalculate() {
       
        if (stock < qty) {
            Sutil.msg(this, "Stock below order qty!");
        } else if ((((txtStock.getText().equals("") || txtQty.getText().equals("")) || txtUnit.getText().equals("")) || txtTransaction.getText().equals(""))) {
            util.Sutil.msg(this, "The Required Field Cannot empty!");
        } else {

            totalprice = Double.parseDouble(txtUnit.getText()) * Integer.valueOf(txtQty.getText());
            txtTotalPrice.setText(String.valueOf(totalprice));

        }

    }

    public void WriteToDatabase(int memberid, int barberid, int itemid, Double price, int quantity, Double totalprice, Object date) {
        try {

            String database = "jdbc:mysql://localhost:3306/barbershop.transaction";
            System.out.println("Connection Passed");
            Statement sta = conn.createStatement();

            String insertSql = "INSERT INTO barbershop.transaction "
                    + "(memberid,barberid,itemid,price,quantity,totalprice,date)"
                    + "VALUES (?,?,?,?,?,?,?);";
            PreparedStatement pstatement = conn.prepareStatement(insertSql);
            pstatement.setInt(1, memberid);
            pstatement.setInt(2, barberid);
            pstatement.setInt(3, itemid);
            pstatement.setDouble(4, price);
            pstatement.setInt(5, quantity);
            pstatement.setDouble(6, totalprice);
            pstatement.setObject(7, date);

            pstatement.executeUpdate();
            System.out.println("Record insert.");

            System.out.println("Inserted to DB !");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void removeTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) tblOrderCRUD.getModel();
        tableModel.setRowCount(0);
    }

    private void LoadTableTransaction() {

        try {

            String sql = "SELECT * FROM barbershop.transaction";
            PreparedStatement pstatement = conn.prepareStatement(sql);
            ResultSet rs = pstatement.executeQuery();
            try {
                DefaultTableModel tableModel = (DefaultTableModel) tblOrderCRUD.getModel();
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {
                        Object[] o = new Object[8];
                        o[0] = rs.getInt("transactionno");
                        o[1] = getMemberNamebyID(rs.getInt("memberid"));
                        o[2] = getBarberNamebyID(rs.getInt("barberid"));
                        o[3] = getItemnamebyItemid(rs.getInt("itemid"));
                        o[4] = rs.getDouble("price");
                        o[5] = rs.getInt("quantity");
                        o[6] = rs.getDouble("totalprice");
                        o[7] = rs.getDate("date");

                        tableModel.addRow(o);

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

    }

    private void stockdecrease() {
//        int qty = INteger.
    }

    public void generatePIN() {

        //generate a 4 digit integer 1000 <10000
        int randomPIN = (int) (Math.random() * 9000) + 1000;

        //Store integer in a string
        String PINString = String.valueOf(randomPIN);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jButton5 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TxtStatus = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMemberID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtBarberID = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lblBarber = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblReceipt = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMemberName = new javax.swing.JTextField();
        txtBarberName = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtPaid = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtReturnPaid = new javax.swing.JTextField();
        btnPaid = new javax.swing.JButton();
        btnSaveTransaction = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtTransaction = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        btnCalculate = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtTotalPrice = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtItemID = new javax.swing.JTextField();
        btnCut = new javax.swing.JButton();
        btnColor = new javax.swing.JButton();
        btnWash = new javax.swing.JButton();
        btnShaving = new javax.swing.JButton();
        btnItem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOrderCRUD = new javax.swing.JTable();
        btnSelectMember = new javax.swing.JButton();
        btnSelectBarber = new javax.swing.JButton();
        jDate = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jRF = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MnuNewOrder = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        MnuLogOut = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        MnuExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        RegMember = new javax.swing.JMenuItem();
        RegBarber = new javax.swing.JMenuItem();
        MnuHelp = new javax.swing.JMenu();
        About = new javax.swing.JMenuItem();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Wash.png"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menu Barber");
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Status :");

        TxtStatus.setEditable(false);
        TxtStatus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TxtStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtStatusActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Member ID :");

        txtMemberID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtMemberID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMemberIDActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Barber ID :");

        txtBarberID.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtBarberID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarberIDActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Date");

        lblBarber.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/The-Barber-Shop-logo-black1-300x149[1].png"))); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblReceipt.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblReceipt.setText("Reciept");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Member Name");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Barber Name");

        txtMemberName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMemberNameActionPerformed(evt);
            }
        });

        txtBarberName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarberNameActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Paid");

        txtPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaidActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Return Paid");

        txtReturnPaid.setEditable(false);

        btnPaid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnPaid.setText("Paid");
        btnPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaidActionPerformed(evt);
            }
        });

        btnSaveTransaction.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnSaveTransaction.setText("Save Transaction");
        btnSaveTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveTransactionActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Transaction");

        txtTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTransactionActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("1 Unit ");

        txtUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnitActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Quantity");

        txtQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQtyActionPerformed(evt);
            }
        });

        btnCalculate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCalculate.setText("Calculate");
        btnCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculateActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Stock");

        txtStock.setEditable(false);
        txtStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Total Price");

        txtTotalPrice.setEditable(false);
        txtTotalPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalPriceActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("ViewStock");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Item ID");

        txtItemID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemIDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel15)
                            .addComponent(jLabel9)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMemberName)
                                .addComponent(txtBarberName)
                                .addComponent(txtTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtItemID, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(lblReceipt))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel14)
                                .addComponent(jLabel17))
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnPaid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSaveTransaction))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnCalculate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtReturnPaid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTotalPrice)
                                    .addComponent(txtUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtPaid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMemberName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtBarberName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtItemID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(txtTotalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCalculate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(txtReturnPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPaid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSaveTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel12)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel13)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btnCut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Cut.png"))); // NOI18N
        btnCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCutActionPerformed(evt);
            }
        });

        btnColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Colour.png"))); // NOI18N
        btnColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorActionPerformed(evt);
            }
        });

        btnWash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Wash_1.png"))); // NOI18N
        btnWash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWashActionPerformed(evt);
            }
        });

        btnShaving.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Shave.png"))); // NOI18N
        btnShaving.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShavingActionPerformed(evt);
            }
        });

        btnItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/PomadeV.png"))); // NOI18N
        btnItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnItemActionPerformed(evt);
            }
        });

        tblOrderCRUD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction No", "Member Name", "Barber Name", "Item Name", "Price", "Qty", "Total Price", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblOrderCRUD);

        btnSelectMember.setText("Select");
        btnSelectMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectMemberActionPerformed(evt);
            }
        });

        btnSelectBarber.setText("Select");
        btnSelectBarber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectBarberActionPerformed(evt);
            }
        });

        jDate.setDateFormatString("dd/MM/yyyy");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("View Transaction");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jRF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jRF.setText("Refresh Table");
        jRF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRFActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/PoleBarber.gif"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/PoleBarber.gif"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Good appearance determines your own characteristic!");

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        MnuNewOrder.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        MnuNewOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/new.png"))); // NOI18N
        MnuNewOrder.setText("New Order");
        MnuNewOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnuNewOrderActionPerformed(evt);
            }
        });
        jMenu1.add(MnuNewOrder);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/text_add.png"))); // NOI18N
        jMenuItem2.setText("View Stock");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        MnuLogOut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        MnuLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/logout.png"))); // NOI18N
        MnuLogOut.setText("Log Out");
        MnuLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnuLogOutActionPerformed(evt);
            }
        });
        jMenu1.add(MnuLogOut);
        jMenu1.add(jSeparator1);

        MnuExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        MnuExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/exit.png"))); // NOI18N
        MnuExit.setText("Exit");
        MnuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnuExitActionPerformed(evt);
            }
        });
        jMenu1.add(MnuExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Register");

        RegMember.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        RegMember.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/administrator.png"))); // NOI18N
        RegMember.setText("Member");
        RegMember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegMemberActionPerformed(evt);
            }
        });
        jMenu2.add(RegMember);

        RegBarber.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        RegBarber.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/ceo.png"))); // NOI18N
        RegBarber.setText("Barber");
        RegBarber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegBarberActionPerformed(evt);
            }
        });
        jMenu2.add(RegBarber);

        jMenuBar1.add(jMenu2);

        MnuHelp.setText("Help");

        About.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        About.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/about.png"))); // NOI18N
        About.setText("About");
        About.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutActionPerformed(evt);
            }
        });
        MnuHelp.add(About);

        jMenuBar1.add(MnuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSelectMember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMemberID)
                            .addComponent(TxtStatus)
                            .addComponent(txtBarberID, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(btnSelectBarber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(129, 129, 129)
                                        .addComponent(btnColor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(128, 128, 128)
                                        .addComponent(btnItem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel6)
                                            .addComponent(btnCut, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(131, 131, 131)
                                                .addComponent(btnWash, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(116, 116, 116))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lblBarber)
                                                .addGap(32, 32, 32)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(btnShaving, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(181, 181, 181)
                                .addComponent(jRF)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TxtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(txtMemberID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSelectMember)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtBarberID, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSelectBarber))
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblBarber, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCut, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnWash, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnShaving, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnItem, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnColor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRF)
                    .addComponent(jButton1)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private String log;

    private void MnuNewOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnuNewOrderActionPerformed
        executeNewOrder();
    }//GEN-LAST:event_MnuNewOrderActionPerformed
    public void load() {
        this.log = DlgLogin.getLog();
        TxtStatus.setText(this.log);
    }


    private void txtBarberIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarberIDActionPerformed

    }//GEN-LAST:event_txtBarberIDActionPerformed

    private void AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutActionPerformed
        Sutil.msg(About, "Barber Shop.\n Version 1.0\n Author: Deddy (03081170041) , Theo (03081170039)");
    }//GEN-LAST:event_AboutActionPerformed

    private void MnuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnuExitActionPerformed
        executeExit();
    }//GEN-LAST:event_MnuExitActionPerformed

    private void MnuLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnuLogOutActionPerformed
        executeLogout();
    }//GEN-LAST:event_MnuLogOutActionPerformed

    private void TxtStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtStatusActionPerformed

    }//GEN-LAST:event_TxtStatusActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed

    }//GEN-LAST:event_jMenu1ActionPerformed

    private void btnCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCutActionPerformed
        new MnuCut(conn).setVisible(true);
    }//GEN-LAST:event_btnCutActionPerformed

    private void btnColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorActionPerformed
        new MnuColoring(conn).setVisible(true);
    }//GEN-LAST:event_btnColorActionPerformed

    private void btnWashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWashActionPerformed
        new MnuWash(conn).setVisible(true);
    }//GEN-LAST:event_btnWashActionPerformed

    private void btnShavingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShavingActionPerformed
        new MnuShaving(conn).setVisible(true);
    }//GEN-LAST:event_btnShavingActionPerformed

    private void btnItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnItemActionPerformed
        new MnuItem(conn).setVisible(true);
    }//GEN-LAST:event_btnItemActionPerformed

    private void btnPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaidActionPerformed
        if (txtMemberID.getText().equals("") || txtMemberName.getText().equals("") || txtBarberID.getText().equals("") || txtBarberName.getText().equals("") || txtTransaction.getText().equals("") || txtUnit.getText().equals("") || txtPaid.getText().equals("") || jDate.equals(null)) {
            util.Sutil.msg(this, "Please Check the empty field!");
            txtBarberName.requestFocusInWindow();
        } else {

            returnpaid = Double.parseDouble(txtPaid.getText()) - Double.parseDouble(txtTotalPrice.getText());

            txtReturnPaid.setText(String.valueOf(returnpaid));
        }

    }//GEN-LAST:event_btnPaidActionPerformed

    private void txtMemberNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMemberNameActionPerformed
        executeGet();
    }//GEN-LAST:event_txtMemberNameActionPerformed

    private void txtBarberNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarberNameActionPerformed
        executeGet();
    }//GEN-LAST:event_txtBarberNameActionPerformed

    private void txtMemberIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMemberIDActionPerformed

    }//GEN-LAST:event_txtMemberIDActionPerformed

    private void btnSaveTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveTransactionActionPerformed

        if (txtTransaction.getText().equals("") || txtUnit.getText().equals("") || txtQty.getText().equals("") || txtTotalPrice.getText().equals("") || jDate.getDate().equals(null)) {
            util.Sutil.msg(this, "Check the Empty Field ! / Required Field !");
        } else {
            stock();
            WriteToDatabase(
                    (Integer.parseInt(txtMemberID.getText())),
                    (Integer.parseInt(txtBarberID.getText())),
                    (Integer.parseInt(txtItemID.getText())),
                    (Double.parseDouble(txtUnit.getText())),
                    (Integer.parseInt(txtQty.getText())),
                    (Double.parseDouble(txtTotalPrice.getText())),
                    (jDate.getDate()));

            util.Sutil.msg(this, "Transaction Saved to DATABASE !");
            removeTableData();
            LoadTableTransaction();
            executeNewOrder();

        }
    }//GEN-LAST:event_btnSaveTransactionActionPerformed

    private void RegBarberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegBarberActionPerformed
        new MnuBarber(conn).setVisible(true);
    }//GEN-LAST:event_RegBarberActionPerformed

    private void RegMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegMemberActionPerformed
        new MnuMember(conn).setVisible(true);
    }//GEN-LAST:event_RegMemberActionPerformed

    private void txtTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTransactionActionPerformed

    }//GEN-LAST:event_txtTransactionActionPerformed

    private void txtUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnitActionPerformed

    }//GEN-LAST:event_txtUnitActionPerformed

    private void txtQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQtyActionPerformed

    }//GEN-LAST:event_txtQtyActionPerformed

    private void btnCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculateActionPerformed
        executeCalculate();
    }//GEN-LAST:event_btnCalculateActionPerformed

    private void txtStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockActionPerformed

    }//GEN-LAST:event_txtStockActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new TableOrderCRUD().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTotalPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalPriceActionPerformed

    }//GEN-LAST:event_txtTotalPriceActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new tblStock().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new tblStock().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnSelectMemberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectMemberActionPerformed
        new RosterMember().setVisible(true);
    }//GEN-LAST:event_btnSelectMemberActionPerformed

    private void btnSelectBarberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectBarberActionPerformed
        new RosterBarber().setVisible(true);
    }//GEN-LAST:event_btnSelectBarberActionPerformed

    private void jRFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRFActionPerformed
        removeTableData();
        LoadTableTransaction();

    }//GEN-LAST:event_jRFActionPerformed

    private void txtItemIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtItemIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtItemIDActionPerformed

    private void txtPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaidActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem About;
    private javax.swing.JMenuItem MnuExit;
    private javax.swing.JMenu MnuHelp;
    private javax.swing.JMenuItem MnuLogOut;
    private javax.swing.JMenuItem MnuNewOrder;
    private javax.swing.JMenuItem RegBarber;
    private javax.swing.JMenuItem RegMember;
    public static javax.swing.JTextField TxtStatus;
    private javax.swing.JButton btnCalculate;
    private javax.swing.JButton btnColor;
    private javax.swing.JButton btnCut;
    private javax.swing.JButton btnItem;
    private javax.swing.JButton btnPaid;
    private javax.swing.JButton btnSaveTransaction;
    private javax.swing.JButton btnSelectBarber;
    private javax.swing.JButton btnSelectMember;
    private javax.swing.JButton btnShaving;
    private javax.swing.JButton btnWash;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private com.toedter.calendar.JDateChooser jDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton jRF;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblBarber;
    private javax.swing.JLabel lblReceipt;
    public static javax.swing.JTable tblOrderCRUD;
    public static javax.swing.JTextField txtBarberID;
    public static javax.swing.JTextField txtBarberName;
    public static javax.swing.JTextField txtItemID;
    public static javax.swing.JTextField txtMemberID;
    public static javax.swing.JTextField txtMemberName;
    public static javax.swing.JTextField txtPaid;
    public static javax.swing.JTextField txtQty;
    public static javax.swing.JTextField txtReturnPaid;
    public static javax.swing.JTextField txtStock;
    public static javax.swing.JTextField txtTotalPrice;
    public static javax.swing.JTextField txtTransaction;
    public static final javax.swing.JTextField txtUnit = new javax.swing.JTextField();
    // End of variables declaration//GEN-END:variables

    private void executeLogout() {
        this.setVisible(false);
        DlgLogin.instance.setVisible(true);
    }

    private void executeNewOrder() {

        txtPaid.setText("");
        txtQty.setText("");
        txtUnit.setText("");
        txtStock.setText("");
        txtTotalPrice.setText("");
        txtReturnPaid.setText("");
        txtTransaction.setText("");
        txtMemberID.setText("");
        txtMemberName.setText("");
        txtBarberID.setText("");
        txtBarberName.setText("");
    }

    private void executeGet() {
        txtMemberName.getText();
        txtBarberName.getText();
    }

    private void executeExit() {
        if (Sutil.msq(this, "Exit confirmation?") == 0) {
            System.exit(0);
        }
    }

    private String getMemberNamebyID(int id) throws SQLException {
        String MemberName = "";
        String sqlMemberLookup = "SELECT name FROM member WHERE memberid = ?";

        PreparedStatement pstMemberLookup = conn.prepareStatement(sqlMemberLookup);
        pstMemberLookup.setInt(1, id);

        ResultSet rsMemberLookup = pstMemberLookup.executeQuery();
        while (rsMemberLookup.next()) {
            MemberName = rsMemberLookup.getString("name");
        }
        return MemberName;
    }

    private String getItemnamebyItemid(int id) throws SQLException {
        String ItemName = "";
        String sqlItemLookup = "SELECT itemname FROM item WHERE itemid = ?";

        PreparedStatement pstItemLookup = conn.prepareStatement(sqlItemLookup);
        pstItemLookup.setInt(1, id);

        ResultSet rsItemLookup = pstItemLookup.executeQuery();
        while (rsItemLookup.next()) {
            ItemName = rsItemLookup.getString("itemname");
        }
        return ItemName;
    }

    private String getBarberNamebyID(int id) throws SQLException {
        String BarberName = "";
        String sqlBarberLookup = "SELECT barbername FROM barber WHERE barberid = ?";

        PreparedStatement pstBarberLookup = conn.prepareStatement(sqlBarberLookup);
        pstBarberLookup.setInt(1, id);

        ResultSet rsBarberLookup = pstBarberLookup.executeQuery();
        while (rsBarberLookup.next()) {
            BarberName = rsBarberLookup.getString("barbername");
        }
        return BarberName;
    }

    public int stock = 0;
    public int stocklow = 0;

    private void stock() {
        int qty = Integer.valueOf(txtQty.getText());
        try {

            if (conn != null) {
                System.out.println("Connected Stock");
                String sql = "SELECT stock FROM item WHERE itemid = ?";
                PreparedStatement pStatement = conn.prepareStatement(sql);

                pStatement.setString(1, (txtItemID.getText().trim()));
                ResultSet rs = pStatement.executeQuery();
                if (rs.isBeforeFirst()) {
                    while (rs.next()) {

                        stock = (rs.getInt("stock"));

                    }
                }
                rs.close();
                pStatement.close();

            }
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        } catch (Exception e) {
            System.out.println("gagal");
        }

        stocklow = stock - qty;

        try {

            if (conn != null) {
                System.out.println("Connected Stock");
                String sql = "UPDATE item set stock=? where itemid=?";
                PreparedStatement pStatement = conn.prepareStatement(sql);
                pStatement.setInt(1, stocklow);
                pStatement.setString(2, (txtItemID.getText().trim()));

                pStatement.executeUpdate();

                pStatement.close();

            }
        } catch (SQLException ex) {
            System.out.println("Error:\n" + ex.getLocalizedMessage());
        } catch (Exception e) {
            System.out.println("gagal");
        }
    }

}

//    private void WriteToDatabase(int i, int i0, String string, int i1, double d, String dateFormatString) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

