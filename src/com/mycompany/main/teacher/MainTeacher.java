package com.mycompany.main.teacher;

import com.mycompany.event.teacher.EventMenuSelected;
<<<<<<< HEAD
=======
import com.mycompany.form.teacher.Form_Logout;
>>>>>>> a13e521 (up lại)
import com.mycompany.form.teacher.Form_Diem;
import com.mycompany.form.teacher.Form_Home;

import com.mycompany.form.teacher.Form_qlBaiKiemTra;
import com.mycompany.form.teacher.Form_qlHocSinh;
import com.mycompany.form.teacher.Form_qlLichHoc;
import com.mycompany.form.teacher.Form_qlLopHoc;

import com.mycompany.swing.teacher.ScrollBar;
import java.awt.Color;
import javax.swing.JComponent;

public class MainTeacher extends javax.swing.JFrame {

    public MainTeacher() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        menu.initMoving(MainTeacher.this);
        menu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                if (index == 0) {
                    setForm(new Form_Home());
                } else if (index == 1) {
                 setForm(new Form_qlLopHoc());
                } else if (index == 2) {
                     setForm(new Form_qlLichHoc());
                } else if (index == 3) {
                   setForm(new Form_qlHocSinh());
                } else if (index == 4) {
                      setForm(new Form_qlBaiKiemTra());
                } else if (index == 5) {
                    setForm(new Form_Diem());
                } else if (index == 6) {
<<<<<<< HEAD
             
=======
               setForm(new Form_Logout());
>>>>>>> a13e521 (up lại)
                }
            }
        });
        setForm(new Form_Home());
    }
    
    private void setForm(JComponent com) {
        mainPanel.removeAll();
        mainPanel.add(com);
        mainPanel.repaint();
        mainPanel.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.mycompany.swing.teacher.PanelBorder();
        menu = new com.mycompany.component.teacher.Menu();
        header2 = new com.mycompany.component.teacher.Header();
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        header2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(header2, javax.swing.GroupLayout.DEFAULT_SIZE, 1069, Short.MAX_VALUE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(header2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainTeacher().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.component.teacher.Header header2;
    private javax.swing.JPanel mainPanel;
    private com.mycompany.component.teacher.Menu menu;
    private com.mycompany.swing.teacher.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
