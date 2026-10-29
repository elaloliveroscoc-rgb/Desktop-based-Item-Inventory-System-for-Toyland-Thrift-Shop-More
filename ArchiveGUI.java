/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package archive;

/**
 *
 * @author Hp
 */
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;

public class ArchiveGUI extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ArchiveGUI.class.getName());
    private static final java.util.List<Object[]> archivedDataList = new java.util.ArrayList<>();

    public ArchiveGUI() {
        initComponents();
    setLocationRelativeTo(null); 
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    

    btnRestore.setOpaque(true);
    btnRestore.setBorderPainted(false);
    btnRestore.setFocusPainted(false);
    btnRestore.setBackground(new java.awt.Color(35, 45, 65));
    btnRestore.setForeground(java.awt.Color.WHITE);       
    
    loadArchivedData();
    }

    public static void addArchivedItem(Object[] rowData) {
        Object[] extendedRow = new Object[rowData.length + 1];
        System.arraycopy(rowData, 0, extendedRow, 0, rowData.length);
        extendedRow[rowData.length] = LocalDate.now().toString(); // Add Current Date sa ARCHIVED DATE column
        archivedDataList.add(extendedRow);
    }

    private void loadArchivedData() {
       DefaultTableModel model = (DefaultTableModel) tblArchive.getModel();
        model.setRowCount(0); // Clear table rows

        String query = "SELECT * FROM archive";

        try (java.sql.Connection conn = none.shopinventorysystem.DBConnection.getConnection();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(query);
             java.sql.ResultSet rs = pstmt.executeQuery()) {

            int counter = 1;
            while (rs.next()) {
                Object[] row = new Object[] {
                    counter++,
                    rs.getString("item_name"),
                    rs.getInt("quantity"),
                    rs.getString("size"),
                    rs.getString("weight"),
                    "₱" + rs.getDouble("price"),
                    rs.getString("status"),
                    rs.getString("date_sold"),
                    rs.getTimestamp("archived_at") != null ? rs.getTimestamp("archived_at").toString() : ""
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error loading archive data: " + e.getMessage());
        }
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblArchive = new javax.swing.JTable();
        btnRestore = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(251, 192, 19));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("ARCHIVED ITEMS");

        tblArchive.setBackground(new java.awt.Color(255, 255, 255));
        tblArchive.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "ITEM", "QUANTITY", "SIZE (in)", "WEIGHT (kg)", "PRICE", "IN/OUT", "DATE SOLD", "ARCHIVED DATE"
            }
        ));
        jScrollPane1.setViewportView(tblArchive);

        btnRestore.setBackground(new java.awt.Color(35, 45, 65));
        btnRestore.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRestore.setText("RESTORE ITEM");
        btnRestore.addActionListener(this::btnRestoreActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 954, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(413, 413, 413)
                        .addComponent(btnRestore))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(370, 370, 370)
                        .addComponent(jLabel1)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRestore)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreActionPerformed
        // TODO add your handling code here:
       int selectedRow = tblArchive.getSelectedRow();
    if (selectedRow == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Please select an item from the archive to restore.", 
            "Selection Warning", 
            javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    int confirm = javax.swing.JOptionPane.showConfirmDialog(this, 
        "Are you sure you want to restore this item back to inventory?", 
        "Confirm Restore", 
        javax.swing.JOptionPane.YES_NO_OPTION);

    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
        DefaultTableModel model = (DefaultTableModel) tblArchive.getModel();
        
        // Alaen dagiti datos manipud iti napili a row
        String itemName = model.getValueAt(selectedRow, 1).toString();
        int qty = Integer.parseInt(model.getValueAt(selectedRow, 2).toString());
        String size = model.getValueAt(selectedRow, 3) != null ? model.getValueAt(selectedRow, 3).toString() : "";
        String weight = model.getValueAt(selectedRow, 4) != null ? model.getValueAt(selectedRow, 4).toString() : "";
        String priceStr = model.getValueAt(selectedRow, 5).toString().replace("₱", "").trim();
        double price = Double.parseDouble(priceStr);
        String status = model.getValueAt(selectedRow, 6) != null ? model.getValueAt(selectedRow, 6).toString() : "";
        String dateSold = model.getValueAt(selectedRow, 7) != null ? model.getValueAt(selectedRow, 7).toString() : "";

        // SQL Queries
        String insertInventorySql = "INSERT INTO inventory (item_name, quantity, size, weight, price, status, date_sold) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String deleteArchiveSql = "DELETE FROM archive WHERE item_name = ?";

        try (java.sql.Connection conn = none.shopinventorysystem.DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Enable Transaction Safety

            // 1. I-insert manen iti inventory table
            try (java.sql.PreparedStatement pstmtInsert = conn.prepareStatement(insertInventorySql)) {
                pstmtInsert.setString(1, itemName);
                pstmtInsert.setInt(2, qty);
                pstmtInsert.setString(3, size);
                pstmtInsert.setString(4, weight);
                pstmtInsert.setDouble(5, price);
                pstmtInsert.setString(6, status);
                pstmtInsert.setString(7, dateSold);
                pstmtInsert.executeUpdate();
            }

            // 2. I-delete manipud iti archive table
            try (java.sql.PreparedStatement pstmtDelete = conn.prepareStatement(deleteArchiveSql)) {
                pstmtDelete.setString(1, itemName);
                pstmtDelete.executeUpdate();
            }

            conn.commit(); // Save changes to MySQL Database

            loadArchivedData(); // Refresh ti Archive GUI Table
            
            // Re-load ti Main Inventory GUI Table
            none.shopinventorysystem.ShopInventorySystem.restoreItemToTable(new Object[0]);

            javax.swing.JOptionPane.showMessageDialog(this, "Item restored back to inventory successfully!");

        } catch (Exception  e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error restoring item: " + e.getMessage());
        }
    }
    
    }//GEN-LAST:event_btnRestoreActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new ArchiveGUI().setVisible(true));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRestore;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblArchive;
    // End of variables declaration//GEN-END:variables
}
