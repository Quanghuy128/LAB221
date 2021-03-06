/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import khangtl.daos.StocksDAO;
import khangtl.dtos.StocksDTO;

/**
 *
 * @author Peter
 */
public class Main extends javax.swing.JFrame {

    Object stockHeader[] = new Object[5];
    Object stockData[] = new Object[5];
    List<StocksDTO> stockList = new ArrayList<>();
    DefaultTableModel stockModel;

    /**
     * Creates new form Main
     */
    public Main() throws Exception {
        initComponents();
        loadData();
    }

    public void loadData() throws Exception {
        stockHeader[0] = "StockID";
        stockHeader[1] = "StockName";
        stockHeader[2] = "Address";
        stockHeader[3] = "DateAvailable";
        stockHeader[4] = "Note";
        stockModel = (DefaultTableModel) this.stockTbl.getModel();
        stockModel.setColumnIdentifiers(stockHeader);
        stockList.add(new StocksDTO(11, "Stock one", "Washington", new SimpleDateFormat("yyyy-MM-dd").parse("2020-04-03"), "Note 1"));
        stockList.add(new StocksDTO(12, "Stock two", "New York", new SimpleDateFormat("yyyy-MM-dd").parse("2022-11-03"), "Note 2"));
        stockList.add(new StocksDTO(13, "Stock three", "Maschusettes", new SimpleDateFormat("yyyy-MM-dd").parse("2021-07-23"), "Note 3"));
        stockList.add(new StocksDTO(14, "Stock four", "Victoria", new SimpleDateFormat("yyyy-MM-dd").parse("2019-02-14"), "Note 4"));
        stockList.add(new StocksDTO(15, "Stock five", "Twin", new SimpleDateFormat("yyyy-MM-dd").parse("2011-11-07"), "Note 5"));
        for (int i = 0; i < stockList.size(); i++) {
            stockData[0] = stockList.get(i).getId();
            stockData[1] = stockList.get(i).getName();
            stockData[2] = stockList.get(i).getAddress();
            stockData[3] = stockList.get(i).getDateAvailable();
            stockData[3] = formatDate((Date) stockData[3]);
            stockData[4] = stockList.get(i).getNote();
            stockModel.addRow(stockData);
            stockModel.getDataVector().elementAt(i);
        }
    }

    public String formatDate(Date oldDate) throws ParseException {
        // Format Date Object to new format
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(oldDate);
        return date;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        stockTbl = new javax.swing.JTable();
        insertBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        stockTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "StockID", "StockName", "Address", "DateAvailable", "Note"
            }
        ));
        jScrollPane1.setViewportView(stockTbl);

        insertBtn.setText("Insert to DB");
        insertBtn.setToolTipText("");
        insertBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addComponent(insertBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(insertBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertBtnActionPerformed
        // TODO add your handling code here:
        StocksDAO dao = new StocksDAO();
        stockList = new ArrayList<>();
        try {
            for (int i = 0; i < stockTbl.getModel().getRowCount(); i++) {
                StocksDTO dto = new StocksDTO(
                        Integer.parseInt(stockTbl.getModel().getValueAt(i, 0).toString()),
                        stockTbl.getModel().getValueAt(i, 1).toString(),
                        stockTbl.getModel().getValueAt(i, 2).toString(),
                        new SimpleDateFormat("dd/MM/yyyy").parse(stockTbl.getModel().getValueAt(i, 3).toString()),
                        stockTbl.getModel().getValueAt(i, 4).toString()
                );
                stockList.add(dto);
            }
            dao.insert(stockList);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getMessage().contains("duplicate")) {
                int start = ex.getMessage().indexOf("(");
                {
                    int end = ex.getMessage().indexOf(")");
                    String duplicate = ex.getMessage().substring(start + 1, end);
                    String finalStr = "Please check your id, it's existed: " + duplicate;
                    JOptionPane.showMessageDialog(this, finalStr);
                }
                if (ex.getMessage().contains("Unparseable date")) {
                    JOptionPane.showMessageDialog(this, "Please check your date, follow this format: yyyy-MM-dd");
                }
            }
        }
    }//GEN-LAST:event_insertBtnActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Main().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton insertBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable stockTbl;
    // End of variables declaration//GEN-END:variables

}
