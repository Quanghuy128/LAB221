/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Peter
 */
public class CourseManagement extends javax.swing.JFrame {

    AddCourse addCourses = new AddCourse();
    SearchCourse searchCourses = new SearchCourse();
    ListCourse listCourses = new ListCourse();

    /**
     * Creates new form CourseManagement
     */
    public CourseManagement() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int close = JOptionPane.showConfirmDialog(null, "Do you want to close program?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (close == JOptionPane.YES_OPTION) {
                    if (addCourses.isVisible()) {
                        addCourses.dispose();
                    }
                    if (searchCourses.isVisible()) {
                        searchCourses.dispose();
                    }
                    if (listCourses.isVisible()) {
                        listCourses.dispose();
                    }
                    e.getWindow().dispose();
                    System.exit(0);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        courseManagementLbl = new javax.swing.JLabel();
        addCourseBtn = new javax.swing.JButton();
        displayAllCoursesBtn = new javax.swing.JButton();
        searchCourseByCodeBtn = new javax.swing.JButton();
        exitAppBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(800, 200));
        setResizable(false);

        courseManagementLbl.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        courseManagementLbl.setText("Course Management");

        addCourseBtn.setText("Add a new Course");
        addCourseBtn.setToolTipText("");
        addCourseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCourseBtnActionPerformed(evt);
            }
        });

        displayAllCoursesBtn.setText("Display all Courses");
        displayAllCoursesBtn.setToolTipText("");
        displayAllCoursesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayAllCoursesBtnActionPerformed(evt);
            }
        });

        searchCourseByCodeBtn.setText("Search Course by Course Code");
        searchCourseByCodeBtn.setToolTipText("");
        searchCourseByCodeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCourseByCodeBtnActionPerformed(evt);
            }
        });

        exitAppBtn.setText("Exit Application");
        exitAppBtn.setToolTipText("");
        exitAppBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitAppBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(courseManagementLbl)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(exitAppBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchCourseByCodeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(displayAllCoursesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addCourseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(courseManagementLbl)
                .addGap(18, 18, 18)
                .addComponent(addCourseBtn)
                .addGap(18, 18, 18)
                .addComponent(displayAllCoursesBtn)
                .addGap(18, 18, 18)
                .addComponent(searchCourseByCodeBtn)
                .addGap(18, 18, 18)
                .addComponent(exitAppBtn)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addCourseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCourseBtnActionPerformed
        // TODO add your handling code here:
        if (!addCourses.isVisible()) {
            addCourses.setVisible(true);
        }
    }//GEN-LAST:event_addCourseBtnActionPerformed

    private void displayAllCoursesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayAllCoursesBtnActionPerformed
        // TODO add your handling code here:
        if (!listCourses.isVisible()) {
            listCourses.getAllCourses();
            listCourses.setVisible(true);
        } else {
            listCourses.dispose();
            listCourses.getAllCourses();
            listCourses.setVisible(true);
        }
    }//GEN-LAST:event_displayAllCoursesBtnActionPerformed

    private void searchCourseByCodeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCourseByCodeBtnActionPerformed
        // TODO add your handling code here:
        if (!searchCourses.isVisible()) {
            searchCourses.setVisible(true);
        }
    }//GEN-LAST:event_searchCourseByCodeBtnActionPerformed

    private void exitAppBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitAppBtnActionPerformed
        // TODO add your handling code here:
        int close = JOptionPane.showConfirmDialog(null, "Do you want to close program?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (close == JOptionPane.YES_OPTION) {
            if (addCourses.isVisible()) {
                addCourses.dispose();
            }
            if (searchCourses.isVisible()) {
                searchCourses.dispose();
            }
            if (listCourses.isVisible()) {
                listCourses.dispose();
            }
            this.dispose();
            System.exit(0);
        }
    }//GEN-LAST:event_exitAppBtnActionPerformed

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
            java.util.logging.Logger.getLogger(CourseManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CourseManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CourseManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CourseManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CourseManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCourseBtn;
    private javax.swing.JLabel courseManagementLbl;
    private javax.swing.JButton displayAllCoursesBtn;
    private javax.swing.JButton exitAppBtn;
    private javax.swing.JButton searchCourseByCodeBtn;
    // End of variables declaration//GEN-END:variables
}
