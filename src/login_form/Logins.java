package login_form;

import com.mycompany.main.student.MainStudent;
import com.mycompany.main.teacher.MainTeacher;
import com.raven.Controller.LoginController;
import com.raven.DAO.UserDAO;
import com.raven.DAOImpl.UserDAOImpl;
import com.raven.entity.User;
import com.raven.main.Main;
import com.raven.util.XAuth;
import com.raven.util.XDialog;
import java.awt.Color;
import javax.swing.JCheckBox;

public class Logins extends javax.swing.JFrame implements LoginController {

    public Logins() {
        initComponents();
this.setUndecorated(true);

        setLocationRelativeTo(null);

        JCheckBox chkRemember = new JCheckBox("Remember me");

        chkRemember.setForeground(new Color(242, 242, 242, 80));
        
        autoLoginIfRemembered(); 
    }
    
    private void autoLoginIfRemembered() {
        String[] data = Security.load();
        if (data != null) {
            String username = data[0];
            String password = data[1];

            User user = new UserDAOImpl().findByUsername(username);
            if (user != null && password.equals(user.getMat_khau())) {
                XAuth.user = user;
                openMainForRole();      
                this.dispose();// vào thẳng hệ thống
            }
        }
    }

    @Override
    public void open() {
        this.setLocationRelativeTo(null);
    }

    @Override
    public void login() {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText().trim();

        UserDAO dao = new UserDAOImpl();
        User user = dao.findByUsername(username);

        

        if (user != null && password.equals(user.getMat_khau())) {
            XAuth.user = user;

            
            if (chkRemember.isSelected()) {
                Security.save(username, password);
            } else {
                Security.clear();
            }

            openMainForRole();
        }
    }

    private void openMainForRole() {
        if (XAuth.isAdmin()) {
            XDialog.alert("Đăng nhập thành công (Admin)");
            Main.main(new String[]{});
        } else if (XAuth.isGiangVien()) {
            XDialog.alert("Đăng nhập thành công (Giảng viên)");
            MainTeacher.main(new String[]{});
        } else {
            XDialog.alert("Đăng nhập thành công (Học viên)");
             MainStudent.main(new String[]{});
        }
        dispose();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        chkRemember = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login page");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0,80));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Login");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("UserName:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Password:");

        btnLogin.setBackground(new java.awt.Color(49, 93, 184));
        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("Login");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        chkRemember.setBackground(new java.awt.Color(0, 102, 204));
        chkRemember.setForeground(new java.awt.Color(255, 255, 255));
        chkRemember.setText("Remember me");
        chkRemember.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkRemember.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRememberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(jLabel3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(chkRemember)
                        .addGap(43, 43, 43)
                        .addComponent(btnLogin)))
                .addContainerGap(122, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogin)
                    .addComponent(chkRemember))
                .addContainerGap(125, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 70, 430, 380));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/Glass Effect Login Page (Community) (1).png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 590));

        setSize(new java.awt.Dimension(1047, 592));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // TODO add your handling code here:
        login();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void chkRememberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRememberActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_chkRememberActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Logins().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JCheckBox chkRemember;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
