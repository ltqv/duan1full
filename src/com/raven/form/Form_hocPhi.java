package com.raven.form;

import com.raven.Controller.Form_hocPhiHv;
import com.raven.DAO.TuitionfeesDAO;
import com.raven.DAOImpl.TuitionfeesDAOImpl;
import com.raven.entity.Tuitionfees;
import com.raven.util.XDialog;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

public class Form_hocPhi extends javax.swing.JPanel implements Form_hocPhiHv {

    TuitionfeesDAO dao = new TuitionfeesDAOImpl();
    List<Tuitionfees> items = List.of();

    public Form_hocPhi() {
        initComponents();
        this.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                open(); // Gọi khi panel được thêm vào giao diện
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
        });

    }

    @Override
    public void open() {
        this.fillToTable();
        this.clear();
    }

    @Override
    public void fillToTable() {
        DefaultTableModel model1 = (DefaultTableModel) tblHocphi.getModel();
        model1.setRowCount(0);

        items = dao.findAll();
        items.forEach(item -> {
            Object[] rowData = {
                item.getId_hoc_vien(),
                item.getHoc_ky(),
                item.getNam_hoc(),
                item.getTong_hoc_phi(),
                item.getDa_dong(),
                item.getCon_lai(),
                item.isTrang_thai() ? "Đã đóng" : "Chưa đóng",
                item.getNgay_dong_cuoi()
            };
            model1.addRow(rowData);
        });
        tblHocphi.setDefaultEditor(Object.class, null);

        DefaultTableModel model2 = (DefaultTableModel) tblHocPhi2.getModel();
        model2.setRowCount(0);

        items = dao.findAll();
        items.forEach(item -> {
            Object[] rowData = {
                item.getId_hoc_vien(),
                item.getHoc_ky(),
                item.getTong_hoc_phi(),
                item.isTrang_thai() ? "Đã đóng" : "Chưa đóng",
                item.getNgay_dong_cuoi()
            };
            model2.addRow(rowData);
        });
        tblHocPhi2.setDefaultEditor(Object.class, null);
    }

    @Override
    public void edit() {
        Tuitionfees entity = items.get(tblHocPhi2.getSelectedRow());
        this.setForm(entity);
        this.setEditable(true);

        tabs.setSelectedIndex(1);
    }

    @Override
    public void checkAll() {
        this.setCheckedAll(true);
    }

    @Override
    public void uncheckAll() {
        this.setCheckedAll(false);
    }

    private void setCheckedAll(boolean checked) {
        for (int i = 0; i < tblHocPhi2.getRowCount(); i++) {
            tblHocPhi2.setValueAt(checked, i, 2);
        }
    }

    @Override
    public void deleteCheckedItems() {
        if (XDialog.confirm("Bạn thực sự muốn xóa các mục chọn?")) {
            for (int i = 0; i < tblHocphi.getRowCount(); i++) {
                if ((Boolean) tblHocphi.getValueAt(i, 2)) {
                    dao.deleteById(items.get(i).getId_hoc_vien());
                }
            }
            this.fillToTable();
        }
    }

    @Override
    public void setForm(Tuitionfees entity) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        txtIIDHocPhi.setText(String.valueOf(entity.getId_hoc_phi()));
        txtIDHocSinhHP.setText(entity.getId_hoc_vien());
        txtHocKy.setText(entity.getHoc_ky());
        txtNamHoc.setText(entity.getNam_hoc());

        txtTongHocPhi.setText(entity.getTong_hoc_phi() != null ? entity.getTong_hoc_phi().toString() : "");

        // Chọn trạng thái trên combobox
        if (entity.isTrang_thai() == true) {
            cbotrangThai.setSelectedItem("DADONG");
        } else {
            cbotrangThai.setSelectedItem("CHUADONG"); // Không chọn gì
        }

        if (entity.getNgay_dong_cuoi() != null) {
            txtNgayDongCuoi.setText(sdf.format(entity.getNgay_dong_cuoi()));
        } else {
            txtNgayDongCuoi.setText("");
        }
    }

    @Override
    public Tuitionfees getForm() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Tuitionfees entity = new Tuitionfees();
        
        entity.setId_hoc_phi(Integer.parseInt(txtIIDHocPhi.getText()));
        entity.setId_hoc_vien(txtIDHocSinhHP.getText().trim());
        entity.setHoc_ky(txtHocKy.getText().trim());
        entity.setNam_hoc(txtNamHoc.getText().trim());

        BigDecimal tongHocPhi = parseBigDecimalOrZero(txtTongHocPhi.getText().trim());
        entity.setTong_hoc_phi(tongHocPhi);

        // Xác định trạng thái
        String trangThai = cbotrangThai.getSelectedItem().toString().trim();
        if ("DADONG".equalsIgnoreCase(trangThai)) {
            entity.setTrang_thai(true);
            entity.setDa_dong(tongHocPhi);           // Trả đủ
        } else {
            entity.setTrang_thai(false);
            entity.setDa_dong(BigDecimal.ZERO);      // Chưa đóng gì
        }

        // Tính còn lại
        entity.setCon_lai(tongHocPhi.subtract(entity.getDa_dong()));

        // Ngày đóng cuối
        try {
            if (!txtNgayDongCuoi.getText().trim().isEmpty()) {
                entity.setNgay_dong_cuoi(sdf.parse(txtNgayDongCuoi.getText().trim()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            entity.setNgay_dong_cuoi(null);
        }

        return entity;
    }

    private BigDecimal parseBigDecimalOrZero(String value) {
        if (value == null || value.isEmpty()) {
            return BigDecimal.ZERO;
        }
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }

    @Override
    public void create() {
        Tuitionfees entity = this.getForm();
        dao.create(entity);
        this.fillToTable();
        this.clear();
    }

    @Override
    public void update() {
        Tuitionfees entity = this.getForm();
        dao.update(entity);
        this.fillToTable();
    }

    @Override
    public void delete() {
        if (XDialog.confirm("Bạn thực sự muốn xóa?")) {
            String id = txtIIDHocPhi.getText();
            dao.deleteById(id);
            this.fillToTable();
            this.clear();
        }
    }

    @Override
    public void clear() {
        this.setForm(new Tuitionfees());
        this.setEditable(false);
    }

    @Override
    public void setEditable(boolean editable) {
        txtIIDHocPhi.setEnabled(editable);
        btnthem.setEnabled(!editable);
        btncapNhat.setEnabled(editable);
        btnxoa.setEnabled(editable);

        int rowCount = tblHocphi.getRowCount();

    }

    @Override
    public void moveFirst() {
        this.moveTo(0);
    }

    @Override
    public void movePrevious() {
        this.moveTo(tblHocphi.getSelectedRow() - 1);
    }

    @Override
    public void moveNext() {
        this.moveTo(tblHocphi.getSelectedRow() + 1);
    }

    @Override
    public void moveLast() {
        this.moveTo(tblHocphi.getRowCount() - 1);
    }

    @Override
    public void moveTo(int index) {
        if (index < 0) {
            this.moveLast();
        } else if (index >= tblHocphi.getRowCount()) {
            this.moveFirst();
        } else {
            tblHocphi.clearSelection();
            tblHocphi.setRowSelectionInterval(index, index);
            this.edit();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblHocphi = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        txtIDHocphi = new javax.swing.JLabel();
        txtIIDHocPhi = new javax.swing.JTextField();
        txtIDHocSinh = new javax.swing.JLabel();
        txtIDHocSinhHP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHocKy = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNamHoc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTongHocPhi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNgayDongCuoi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHocPhi2 = new javax.swing.JTable();
        btnthem = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        cbotrangThai = new javax.swing.JComboBox<>();
        btncapNhat = new javax.swing.JButton();
        btnlamMoi = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblHocphi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID học phí", "ID học sinh", "Học kỳ", "Năm học", "Tổng học phí", "Đã đóng", "Còn lại", "Trạng thái", "Ngày đóng cuối"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblHocphi);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 922, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 51, Short.MAX_VALUE))
        );

        tabs.addTab("Tổng quát", jPanel8);

        txtIDHocphi.setText("ID học phí");

        txtIDHocSinh.setText("ID học sinh");

        jLabel3.setText("Học kỳ");

        jLabel4.setText("Năm học");

        jLabel6.setText("Tổng học phí");

        jLabel7.setText("Trạng thái");

        jLabel8.setText("Ngày đóng cuối");

        tblHocPhi2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID học sinh", "Học kỳ", "Tổng học phí", "Trạng thái", "Ngày đóng cuối"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHocPhi2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHocPhi2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHocPhi2);

        btnthem.setText("Thêm");
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnxoa.setText("Xóa");
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        cbotrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DADONG", "CHUADONG" }));
        cbotrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbotrangThaiActionPerformed(evt);
            }
        });

        btncapNhat.setText("Cập nhật");
        btncapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncapNhatActionPerformed(evt);
            }
        });

        btnlamMoi.setText("Làm mới");
        btnlamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIDHocphi)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtTongHocPhi)
                            .addComponent(txtIIDHocPhi, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(cbotrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel8)
                                .addGap(28, 28, 28)
                                .addComponent(txtNgayDongCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtIDHocSinh)
                                .addGap(18, 18, 18)
                                .addComponent(txtIDHocSinhHP, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel3)
                                .addGap(38, 38, 38)
                                .addComponent(txtHocKy, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtNamHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnthem)
                        .addGap(18, 18, 18)
                        .addComponent(btnxoa)
                        .addGap(18, 18, 18)
                        .addComponent(btncapNhat)
                        .addGap(18, 18, 18)
                        .addComponent(btnlamMoi))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 896, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDHocphi)
                    .addComponent(txtIIDHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDHocSinh)
                    .addComponent(txtIDHocSinhHP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtHocKy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtNamHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTongHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtNgayDongCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cbotrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem)
                    .addComponent(btnxoa)
                    .addComponent(btncapNhat)
                    .addComponent(btnlamMoi))
                .addContainerGap())
        );

        tabs.addTab("Chỉnh sửa", jPanel6);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1044, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 418, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1044, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 418, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHocPhi2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHocPhi2MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.edit();
        }
    }//GEN-LAST:event_tblHocPhi2MouseClicked

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
        this.create();
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
        this.delete();
    }//GEN-LAST:event_btnxoaActionPerformed

    private void cbotrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbotrangThaiActionPerformed

    private void btncapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapNhatActionPerformed
        this.update();
    }//GEN-LAST:event_btncapNhatActionPerformed

    private void btnlamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlamMoiActionPerformed
        this.clear();
    }//GEN-LAST:event_btnlamMoiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncapNhat;
    private javax.swing.JButton btnlamMoi;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.JComboBox<String> cbotrangThai;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblHocPhi2;
    private javax.swing.JTable tblHocphi;
    private javax.swing.JTextField txtHocKy;
    private javax.swing.JLabel txtIDHocSinh;
    private javax.swing.JTextField txtIDHocSinhHP;
    private javax.swing.JLabel txtIDHocphi;
    private javax.swing.JTextField txtIIDHocPhi;
    private javax.swing.JTextField txtNamHoc;
    private javax.swing.JTextField txtNgayDongCuoi;
    private javax.swing.JTextField txtTongHocPhi;
    // End of variables declaration//GEN-END:variables
}
