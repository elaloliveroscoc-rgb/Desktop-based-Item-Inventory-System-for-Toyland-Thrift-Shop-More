/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package report;

/**
 *
 * @author Hp
 */
public class ReportsGUI extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ReportsGUI.class.getName());

    public ReportsGUI() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        // Initial load dynamic calculation pag-open sa window
        if (cbReportType != null && cbReportType.getSelectedItem() != null) {
            cbReportTypeActionPerformed(null);
        }
    }

   public ReportsGUI(javax.swing.table.DefaultTableModel model) {
        this();
    }
private void saveReportToDatabase(String reportType, int totalItems, int available, int sold, int reserved, double totalSales) {
    String sql = "INSERT INTO reports (report_type, total_items, available_qty, sold_qty, reserved_qty, total_sales_value) VALUES (?, ?, ?, ?, ?, ?)";

    try (java.sql.Connection conn = none.shopinventorysystem.DBConnection.getConnection();
         java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, reportType);
        pstmt.setInt(2, totalItems);
        pstmt.setInt(3, available);
        pstmt.setInt(4, sold);
        pstmt.setInt(5, reserved);
        pstmt.setDouble(6, totalSales);

        pstmt.executeUpdate();

    } catch (Exception e) {
        System.err.println("Error saving report to database: " + e.getMessage());
    }
}
   
    // Method nga mag-calculate base sa napili nga period
public void loadReportsByPeriod(String reportType) {
       int totalItems = 0;
    int availableCount = 0;
    int soldCount = 0;
    int reservedCount = 0;
    double totalSalesValue = 0.0;

    // 1. Filter condition para sa sales_transactions table
    String salesDateCondition = "";
    if ("DAILY".equalsIgnoreCase(reportType)) {
        salesDateCondition = " WHERE DATE(transaction_date) = CURRENT_DATE() ";
    } else if ("WEEKLY".equalsIgnoreCase(reportType)) {
        salesDateCondition = " WHERE YEARWEEK(transaction_date, 1) = YEARWEEK(CURRENT_DATE(), 1) ";
    } else if ("MONTHLY".equalsIgnoreCase(reportType)) {
        salesDateCondition = " WHERE MONTH(transaction_date) = MONTH(CURRENT_DATE()) AND YEAR(transaction_date) = YEAR(CURRENT_DATE()) ";
    }

    // SQL Queries
    String sqlTotalItems = "SELECT SUM(quantity) AS total_qty FROM inventory";
    String sqlAvailable = "SELECT SUM(quantity) AS avail_qty FROM available";
    String sqlReserved = "SELECT SUM(quantity) AS res_qty FROM inventory WHERE UPPER(status) = 'RESERVED'";
    String sqlSales = "SELECT SUM(quantity) AS total_sold, SUM(total_amount) AS total_sales FROM sales_transactions" + salesDateCondition;

    try (java.sql.Connection conn = none.shopinventorysystem.DBConnection.getConnection()) {

        // A. Total Quantity sa Inventory
        try (java.sql.PreparedStatement pstmtTotal = conn.prepareStatement(sqlTotalItems);
             java.sql.ResultSet rsTotal = pstmtTotal.executeQuery()) {
            if (rsTotal.next()) {
                totalItems = rsTotal.getInt("total_qty");
            }
        }

        // B. Total Available Quantity
        try (java.sql.PreparedStatement pstmtAvail = conn.prepareStatement(sqlAvailable);
             java.sql.ResultSet rsAvail = pstmtAvail.executeQuery()) {
            if (rsAvail.next()) {
                availableCount = rsAvail.getInt("avail_qty");
            }
        }

        // C. Total Reserved Quantity
        try (java.sql.PreparedStatement pstmtRes = conn.prepareStatement(sqlReserved);
             java.sql.ResultSet rsRes = pstmtRes.executeQuery()) {
            if (rsRes.next()) {
                reservedCount = rsRes.getInt("res_qty");
            }
        }

        // D. Total Sold Items & Sales Value gikan sa sales_transactions
        try (java.sql.PreparedStatement pstmtSales = conn.prepareStatement(sqlSales);
             java.sql.ResultSet rsSales = pstmtSales.executeQuery()) {
            if (rsSales.next()) {
                soldCount = rsSales.getInt("total_sold");
                totalSalesValue = rsSales.getDouble("total_sales");
            }
        }

        // Display Values sa GUI Labels
        if (lblTotalItems != null) lblTotalItems.setText(String.valueOf(totalItems));
        if (lblAvailable != null) lblAvailable.setText(String.valueOf(availableCount));
        if (lblSold != null) lblSold.setText(String.valueOf(soldCount));
        if (lblReserved != null) lblReserved.setText(String.valueOf(reservedCount));
        if (lblTotalSales != null) {
            lblTotalSales.setText("TOTAL SALES VALUE: ₱" + String.format("%,.2f", totalSalesValue));
            saveReportToDatabase(reportType, totalItems, availableCount, soldCount, reservedCount, totalSalesValue);
        }

    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error loading report: " + e.getMessage());
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblTotalItems = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblAvailable = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblSold = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblReserved = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lblTotalSales = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cbReportType = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(251, 192, 19));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("TOTAL ITEMS");

        lblTotalItems.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotalItems.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalItems.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTotalItems)
                .addGap(58, 58, 58))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(94, 94, 94)
                .addComponent(lblTotalItems)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("AVAILABLE");

        lblAvailable.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblAvailable.setForeground(new java.awt.Color(0, 0, 0));
        lblAvailable.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(lblAvailable)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(94, 94, 94)
                .addComponent(lblAvailable)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("SOLD");

        lblSold.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblSold.setForeground(new java.awt.Color(0, 0, 0));
        lblSold.setText("0");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSold)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(93, 93, 93)
                .addComponent(lblSold)
                .addContainerGap(116, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("RESERVED");

        lblReserved.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblReserved.setForeground(new java.awt.Color(0, 0, 0));
        lblReserved.setText("0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(27, 27, 27))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(lblReserved)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(86, 86, 86)
                .addComponent(lblReserved)
                .addContainerGap(123, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("SALES SUMMARY");

        lblTotalSales.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTotalSales.setForeground(new java.awt.Color(0, 0, 0));
        lblTotalSales.setText("TOTAL SALES VALUE: ₱0.00");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblTotalSales, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(lblTotalSales)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("INVENTORY REPORT");

        cbReportType.setBackground(new java.awt.Color(255, 255, 255));
        cbReportType.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbReportType.setForeground(new java.awt.Color(0, 0, 0));
        cbReportType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DAILY REPORT", "WEEKLY REPORT", "MONTHLY REPORT" }));
        cbReportType.addActionListener(this::cbReportTypeActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(cbReportType, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(65, 65, 65)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(21, 21, 21))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbReportType, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbReportTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbReportTypeActionPerformed
        // TODO add your handling code here:
        if (cbReportType.getSelectedItem() != null) {
        String selected = cbReportType.getSelectedItem().toString();
        if (selected.contains("DAILY")) {
            loadReportsByPeriod("DAILY");
        } else if (selected.contains("WEEKLY")) {
            loadReportsByPeriod("WEEKLY");
        } else if (selected.contains("MONTHLY")) {
            loadReportsByPeriod("MONTHLY");
        }
    }
    }//GEN-LAST:event_cbReportTypeActionPerformed

    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
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
        java.awt.EventQueue.invokeLater(() -> new ReportsGUI().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbReportType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lblAvailable;
    private javax.swing.JLabel lblReserved;
    private javax.swing.JLabel lblSold;
    private javax.swing.JLabel lblTotalItems;
    private javax.swing.JLabel lblTotalSales;
    // End of variables declaration//GEN-END:variables
}
