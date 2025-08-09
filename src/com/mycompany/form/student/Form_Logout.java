package com.mycompany.form.student;

import java.awt.LayoutManager;

public class Form_Logout extends javax.swing.JPanel {

    public Form_Logout() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        pnlLogout = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnYes = new javax.swing.JButton();
        btnNo = new javax.swing.JButton();

        setBackground(new java.awt.Color(204, 255, 255));
        setPreferredSize(new java.awt.Dimension(400, 400));
        setLayout(new java.awt.GridBagLayout());

        pnlLogout.setBackground(new java.awt.Color(51, 255, 255));
        pnlLogout.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("BẠN CÓ MUỐN ĐĂNG XUẤT ?");

        btnYes.setBackground(new java.awt.Color(255, 0, 51));
        btnYes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnYes.setText("YES");
        btnYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYesActionPerformed(evt);
            }
        });

        btnNo.setBackground(new java.awt.Color(255, 0, 51));
        btnNo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNo.setText("NO");

        javax.swing.GroupLayout pnlLogoutLayout = new javax.swing.GroupLayout(pnlLogout);
        pnlLogout.setLayout(pnlLogoutLayout);
        pnlLogoutLayout.setHorizontalGroup(
            pnlLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogoutLayout.createSequentialGroup()
                .addGroup(pnlLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLogoutLayout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(btnYes)
                        .addGap(84, 84, 84)
                        .addComponent(btnNo))
                    .addGroup(pnlLogoutLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)))
                .addGap(20, 20, 20))
        );
        pnlLogoutLayout.setVerticalGroup(
            pnlLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLogoutLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(pnlLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnYes)
                    .addComponent(btnNo))
                .addGap(30, 30, 30))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(pnlLogout, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYesActionPerformed
        // TODO add your handling code here:
        java.awt.Window mainWindow = javax.swing.SwingUtilities.getWindowAncestor(this);
        if (mainWindow != null) {
            mainWindow.dispose();
        }

        // Mở lại form đăng nhập
        new login_form.Logins().setVisible(true);
    }//GEN-LAST:event_btnYesActionPerformed


    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNo;
    private javax.swing.JButton btnYes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel pnlLogout;
    // End of variables declaration//GEN-END:variables
}
