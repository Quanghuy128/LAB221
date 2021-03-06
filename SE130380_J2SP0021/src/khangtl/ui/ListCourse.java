/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import khangtl.daos.CoursesDAO;
import khangtl.dtos.CoursesDTO;

/**
 *
 * @author Peter
 */
public class ListCourse extends javax.swing.JFrame {

    /**
     * Creates new form ListCourse
     */
    public ListCourse() {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        txtList.setEditable(false);
        getAllCourses();
    }

    public void getAllCourses() {
        ArrayList<CoursesDTO> result;
        CoursesDAO dao = new CoursesDAO();
        try {
            dao.getAllCourses();
            result = dao.getCourses();
            // Sort list by credit
            Collections.sort(result, new Comparator<CoursesDTO>() {
                public int compare(CoursesDTO o1, CoursesDTO o2) {
                    return o1.getCredit() - o2.getCredit();
                }
            });

            // Display text on screen
            if (result.size() > 0) {
                txtList.setText(formatDisplayAllCoures(result));
            } else {
                txtList.setText("You don't have any courses");
            }
        } catch (Exception ex) {
            Logger.getLogger(ListCourse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String formatDisplayAllCoures(ArrayList<CoursesDTO> listCourses) {
        String finalFormat = "";
        // Format follow requirement
        for (int i = 0; i < listCourses.size(); i++) {
            finalFormat += listCourses.get(i).getCode() + " | ";
            finalFormat += listCourses.get(i).getName() + " | ";
            finalFormat += listCourses.get(i).getCredit();
            finalFormat += "\n";
        }
        return finalFormat;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtList = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(750, 550));
        setResizable(false);

        listLbl.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        listLbl.setText("List of all courses (order by Credit)");

        txtList.setColumns(20);
        txtList.setRows(5);
        jScrollPane1.setViewportView(txtList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(listLbl)
                .addContainerGap(100, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(listLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
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
            java.util.logging.Logger.getLogger(ListCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListCourse.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListCourse().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel listLbl;
    private javax.swing.JTextArea txtList;
    // End of variables declaration//GEN-END:variables
}
