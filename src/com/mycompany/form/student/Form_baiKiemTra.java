
package com.mycompany.form.student;

import com.raven.Controller.Form_baiKiemtraHv;
import com.raven.DAO.ExamDAO;
import com.raven.DAOImpl.ExamDAOImpl;
import com.raven.entity.Exam;
import com.raven.util.XDialog;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;


public class Form_baiKiemTra extends javax.swing.JPanel implements Form_baiKiemtraHv{
     ExamDAO dao = new ExamDAOImpl();
    List<Exam> items = List.of();

    public Form_baiKiemTra() {
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
        DefaultTableModel model1 = (DefaultTableModel) tblbaiKiemTra.getModel();
        model1.setRowCount(0);

        items = dao.findAll();
        items.forEach(item -> {
            Object[] rowData = {
            item.getId(), 
            item.getTieu_de(),
            item.getNgay_tao(),
            item.getThoi_luong(),
            item.getTong_so_cau(),
            item.getThoi_gian_bat_dau(),
            item.getThoi_gian_ket_thuc(),
            item.isKich_hoat()
            };
            model1.addRow(rowData);
        });
        tblbaiKiemTra.setDefaultEditor(Object.class, null);

        DefaultTableModel model2 = (DefaultTableModel) tblbaiKiemTra2.getModel();
        model2.setRowCount(0);

        items = dao.findAll();
        items.forEach(item -> {
            Object[] rowData = {
            item.getId(), 
            item.getTieu_de(),
            item.getNgay_tao(),
            item.getThoi_luong(),
            item.getThoi_gian_bat_dau(),
            item.getThoi_gian_ket_thuc(),
            item.isKich_hoat()
            };
            model2.addRow(rowData);
        });
        tblbaiKiemTra2.setDefaultEditor(Object.class, null);
    }

    @Override
    public void edit() {
        Exam entity = items.get(tblbaiKiemTra2.getSelectedRow());
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
        for (int i = 0; i < tblbaiKiemTra2.getRowCount(); i++) {
            tblbaiKiemTra2.setValueAt(checked, i, 2);
        }
    }

    @Override
    public void deleteCheckedItems() {
        if (XDialog.confirm("Bạn thực sự muốn xóa các mục chọn?")) {
            for (int i = 0; i < tblbaiKiemTra.getRowCount(); i++) {
                if ((Boolean) tblbaiKiemTra.getValueAt(i, 2)) {
                    dao.deleteById(items.get(i).getId());
                }
            }
            this.fillToTable();
        }
    }

@Override
public void setForm(Exam entity) {
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    txtIdbaiKT.setText(entity.getId());
    txtTenBaiKT.setText(entity.getTieu_de());
    txtNgayTao.setText(String.valueOf(entity.getNgay_tao()));
    txtthoiLuong.setText(String.valueOf(entity.getThoi_luong()));
    txtsoCauhoi.setText(String.valueOf(entity.getTong_so_cau()));
    txtthoiGianbatDau.setText(String.valueOf(entity.getThoi_gian_bat_dau() != null ? sdf.format(entity.getThoi_gian_bat_dau()) : "") );
    txtthoiGianKetThuc.setText(String.valueOf(entity.getThoi_gian_ket_thuc() != null ? sdf.format(entity.getThoi_gian_ket_thuc()) : ""));
    cbotrangThai.setSelectedItem(entity.isKich_hoat() ? "Kích hoạt" : "Không kích hoạt");
}

@Override
public Exam getForm() {
    Exam entity = new Exam();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    try {
        entity.setId(txtIdbaiKT.getText());
        entity.setTieu_de(txtTenBaiKT.getText());

        // Sửa chỗ này: dùng sdf.parse thay vì new Date(String)
        if (!txtNgayTao.getText().isEmpty()) {
            entity.setNgay_tao(new java.sql.Timestamp(sdf.parse(txtNgayTao.getText()).getTime()));
        }

        entity.setThoi_luong(Integer.parseInt(txtthoiLuong.getText()));
        entity.setTong_so_cau(Integer.parseInt(txtsoCauhoi.getText()));
        if (!txtthoiGianbatDau.getText().isEmpty()) {
    entity.setThoi_gian_bat_dau(new java.sql.Timestamp(sdf.parse(txtthoiGianbatDau.getText()).getTime()));
    }
        if (!txtthoiGianKetThuc.getText().isEmpty()) {
    entity.setThoi_gian_ket_thuc(new java.sql.Timestamp(sdf.parse(txtthoiGianKetThuc.getText()).getTime()));
    }


        String selected = (String) cbotrangThai.getSelectedItem();
        entity.setKich_hoat("Kích hoạt".equals(selected));

        return entity; // Đừng return null nếu thành công

    } catch (Exception e) {
        e.printStackTrace();
        XDialog.alert("Lỗi nhập liệu: " + e.getMessage());
        return null;
    }
}

    @Override
    public void create() {
        Exam entity = this.getForm();
        dao.create(entity);
        this.fillToTable();
        this.clear();
    }

    @Override
    public void update() {
        Exam entity = this.getForm();
        dao.update(entity);
        this.fillToTable();
    }

    @Override
    public void delete() {
        if (XDialog.confirm("Bạn thực sự muốn xóa?")) {
            String id = txtIdbaiKT.getText();
            dao.deleteById(id);
            this.fillToTable();
            this.clear();
        }
    }

    @Override
    public void clear() {
        this.setForm(new Exam());
        this.setEditable(false);
    }

    @Override
    public void setEditable(boolean editable) {
        txtIdbaiKT.setEnabled(!editable);
        btnthem.setEnabled(!editable);
        btncapNhat.setEnabled(editable);
        btnxoa.setEnabled(editable);

        int rowCount = tblbaiKiemTra.getRowCount();

    }
    @Override
    public void moveFirst() {
        this.moveTo(0);
    }

    @Override
    public void movePrevious() {
        this.moveTo(tblbaiKiemTra.getSelectedRow() - 1);
    }

    @Override
    public void moveNext() {
        this.moveTo(tblbaiKiemTra.getSelectedRow() + 1);
    }

    @Override
    public void moveLast() {
        this.moveTo(tblbaiKiemTra.getRowCount() - 1);
    }

    @Override
    public void moveTo(int index) {
        if (index < 0) {
            this.moveLast();
        } else if (index >= tblbaiKiemTra.getRowCount()) {
            this.moveFirst();
        } else {
            tblbaiKiemTra.clearSelection();
            tblbaiKiemTra.setRowSelectionInterval(index, index);
            this.edit();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblbaiKiemTra = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtIdbaiKT = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTenBaiKT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtthoiLuong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtthoiGianbatDau = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtthoiGianKetThuc = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbaiKiemTra2 = new javax.swing.JTable();
        btnthem = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        cbotrangThai = new javax.swing.JComboBox<>();
        btncapNhat = new javax.swing.JButton();
        btnlamMoi = new javax.swing.JButton();
        txtsoCauhoi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblbaiKiemTra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID bài KT", "Tên bài KT", "Ngày tạo", "Thời lượng", "Số câu hỏi", "Thời gian bắt đầu", "Thời gian kết thúc", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblbaiKiemTra);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1075, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 51, Short.MAX_VALUE))
        );

        tabs.addTab("Tổng quát", jPanel8);

        jLabel1.setText("ID bai KT");

        jLabel2.setText("Tên bài KT");

        jLabel3.setText("Ngày tạo");

        jLabel4.setText("Thời lượng");

        jLabel6.setText("Thời gian bắt đầu");

        jLabel7.setText("Thời gian kết thúc");

        jLabel8.setText("Trạng thái");

        tblbaiKiemTra2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID bài KT", "Tên bài KT", "Ngày tạo", "Thời lượng", "Thời gian bắt đầu", "Thời gian kết thúc", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblbaiKiemTra2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbaiKiemTra2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblbaiKiemTra2);

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

        cbotrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HOANTHANH", "CHUAHOANTHANH" }));
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

        jLabel5.setText("Số câu hỏi");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtthoiGianbatDau)
                            .addComponent(txtIdbaiKT, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtthoiGianKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(txtTenBaiKT))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtsoCauhoi)
                            .addComponent(txtNgayTao, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtthoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(cbotrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnthem)
                        .addGap(18, 18, 18)
                        .addComponent(btnxoa)
                        .addGap(18, 18, 18)
                        .addComponent(btncapNhat)
                        .addGap(18, 18, 18)
                        .addComponent(btnlamMoi))
                    .addComponent(jScrollPane1))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdbaiKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenBaiKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtthoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtthoiGianbatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtthoiGianKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cbotrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtsoCauhoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem)
                    .addComponent(btnxoa)
                    .addComponent(btncapNhat)
                    .addComponent(btnlamMoi))
                .addContainerGap())
        );

        tabs.addTab("Chỉnh sửa", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 1081, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1126, Short.MAX_VALUE)
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

    private void tblbaiKiemTra2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbaiKiemTra2MouseClicked
        // TODO add your handling code here:
               if (evt.getClickCount() == 2) {
            this.edit();
        }
    }//GEN-LAST:event_tblbaiKiemTra2MouseClicked

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        // TODO add your handling code here:
       this.create();
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
      this.delete();
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btncapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncapNhatActionPerformed
       this.update();
    }//GEN-LAST:event_btncapNhatActionPerformed

    private void btnlamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlamMoiActionPerformed
       this.clear();
    }//GEN-LAST:event_btnlamMoiActionPerformed

    private void cbotrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbotrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbotrangThaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncapNhat;
    private javax.swing.JButton btnlamMoi;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.JComboBox<String> cbotrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblbaiKiemTra;
    private javax.swing.JTable tblbaiKiemTra2;
    private javax.swing.JTextField txtIdbaiKT;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtTenBaiKT;
    private javax.swing.JTextField txtsoCauhoi;
    private javax.swing.JTextField txtthoiGianKetThuc;
    private javax.swing.JTextField txtthoiGianbatDau;
    private javax.swing.JTextField txtthoiLuong;
    // End of variables declaration//GEN-END:variables
}
