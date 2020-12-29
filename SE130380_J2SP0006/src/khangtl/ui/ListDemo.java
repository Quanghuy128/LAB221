/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.util.HashMap;
import java.util.Set;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Peter
 */
public class ListDemo extends javax.swing.JFrame {

    private JList<String> nameList;
    HashMap<String, String> imageHashMap = new HashMap<>();

    /**
     * Creates new form ListDemo
     */
    public ListDemo() {
        initComponents();

        // Create hashmap to store key and value
        imageHashMap.put("Cat", "src/assets/images/cat.jpg");
        imageHashMap.put("Dog", "src/assets/images/dog.jpg");
        imageHashMap.put("Pig", "src/assets/images/pig.jpg");
        imageHashMap.put("Rabbit", "src/assets/images/rabbit.jpg");
        imageHashMap.put("Bird", "src/assets/images/bird.jpg");
        Set imageKeySet = imageHashMap.keySet();
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Object key : imageKeySet.toArray()) {
            model.addElement((String) key);
        }
        nameList = new JList<>(model);

        // Set up JList item with image
        nameList.setSelectedIndex(0);
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(nameList);
        ImageIcon image = new ImageIcon(new ImageIcon(imageHashMap.get(nameList.getSelectedValue())).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        JLabel imageLbl = new JLabel(image);
        getContentPane().add(imageLbl);

        // Format selected item in JList
        nameList.setCellRenderer(new SelectedListCellRenderer());

        // Add selection item event listener
        nameList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    ImageIcon image = new ImageIcon(new ImageIcon(imageHashMap.get(nameList.getSelectedValue())).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
                    imageLbl.setIcon(image);
                    nameList.setCellRenderer(new SelectedListCellRenderer());
                }
            }
        });
    }

    public class SelectedListCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (isSelected) {
                c.setBackground(Color.lightGray);
                c.setFont(new Font(nameList.getFont().getName(), Font.BOLD, nameList.getFont().getSize()));
            }
            return c;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(800, 300));
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 211, Short.MAX_VALUE)
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListDemo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
