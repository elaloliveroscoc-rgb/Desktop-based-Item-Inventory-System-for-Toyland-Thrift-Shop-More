/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package LowStockGUI;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Hp
 */
public class LowStockGUI extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LowStockGUI.class.getName());

    public LowStockGUI() {
        initComponents();
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        loadLowStockFromDatabase(); 
    }

    public LowStockGUI(DefaultTableModel mainModel) {
        this(); // Nagpatawag na sa LowStockGUI() diin naka-load na ang loadLowStockFromDatabase()
    }

  private void loadLowStockFromDatabase() {
     DefaultTableModel lowStockModel = (DefaultTableModel) tblLowStock.getModel();
    lowStockModel.setRowCount(0); // Clear sa table UI

    // 1. Clear daang data sa low_stock table
    String deleteSql = "DELETE FROM low_stock";
    
    // 2. Sync sa items nga quantity <= 5 ug AVAILABLE gikan sa inventory
    String syncSql = "INSERT INTO low_stock (item_name, quantity, price, status) "
                   + "SELECT item_name, quantity, price, status "
                   + "FROM inventory WHERE quantity <= 5 AND UPPER(status) = 'AVAILABLE'";

    // 3. Fetch datos gikan sa low_stock table
    String fetchSql = "SELECT * FROM low_stock";

    try (java.sql.Connection conn = none.shopinventorysystem.DBConnection.getConnection()) {
        
        // Step A: Clear low_stock table
        try (java.sql.PreparedStatement pstmtDelete = conn.prepareStatement(deleteSql)) {
            pstmtDelete.executeUpdate();
        }

        // Step B: Populate/Sync low_stock table
        try (java.sql.PreparedStatement pstmtSync = conn.prepareStatement(syncSql)) {
            pstmtSync.executeUpdate();
        }

        // Step C: Read ug Display sa JTable
        try (java.sql.PreparedStatement pstmtFetch = conn.prepareStatement(fetchSql);
             java.sql.ResultSet rs = pstmtFetch.executeQuery()) {

            int counter = 1;
            while (rs.next()) {
                lowStockModel.addRow(new Object[] {
                    counter++,
                    rs.getString("item_name"),
                    rs.getInt("quantity"),
                    "₱" + rs.getDouble("price"),
                    rs.getString("status")
                });
            }
        }

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error loading low stock data: " + e.getMessage());
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLowStock = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 51));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("LOW STOCK ITEMS");

        tblLowStock.setBackground(new java.awt.Color(255, 255, 255));
        tblLowStock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "#", "ITEM", "QUANTITY", "PRICE", "STATUS"
            }
        ));
        jScrollPane1.setViewportView(tblLowStock);

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Items with quantity of 5 or below are considered LOW STOCK.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new LowStockGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLowStock;
    // End of variables declaration//GEN-END:variables
}
