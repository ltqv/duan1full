
package com.mycompany.form.teacher;

import com.raven.Controller.Form_lsHoctapHv;
import com.raven.DAO.LearningHistoryDAO;
import com.raven.DAOImpl.LearningHistoryDAOImpl;
import com.raven.entity.LearningHistory;
import com.raven.util.XDialog;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;


public class Form_lsHocTap extends javax.swing.JPanel implements Form_lsHoctapHv{
    
     LearningHistoryDAO dao = new LearningHistoryDAOImpl();
     List<LearningHistory> items = List.of();


    public Form_lsHocTap() {
        initComponents();
        txtID.setEditable(false);
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
        DefaultTableModel model1 = (DefaultTableModel) tbllsHocTap.getModel();
        model1.setRowCount(0);

        items = dao.findAll();
        items.forEach(item -> {
            Object[] rowData = {
                item.getId_hoc_vien(),
                item.getId_lop(),
                item.getId_lich_hoc(),
                item.getNgay_hoc_thuc_te(),
                item.isCo_mat()? "Có mặt":"Không có mặt",
                item.getGhi_chu()
            };
            model1.addRow(rowData);
        });
        tbllsHocTap.setDefaultEditor(Object.class, null);

        DefaultTableModel model2 = (DefaultTableModel) tbllsHocTap2.getModel();
        model2.setRowCount(0);

        items = dao.findAll();
        items.forEach(item -> {
            Object[] rowData = {
                item.getId_hoc_vien(),
                item.getId_lop(),
                item.getId_lich_hoc(),
                item.getNgay_hoc_thuc_te(),
                item.isCo_mat()? "Có mặt":"Không có mặt",
                item.getGhi_chu()
            };
            model2.addRow(rowData);
        });
        tbllsHocTap2.setDefaultEditor(Object.class, null);
    }

    @Override
    public void edit() {
        LearningHistory entity = items.get(tbllsHocTap2.getSelectedRow());
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
        for (int i = 0; i < tbllsHocTap.getRowCount(); i++) {
            tbllsHocTap.setValueAt(checked, i, 2);
        }
    }

 @Override
    public void deleteCheckedItems() {
        if (XDialog.confirm("Bạn thực sự muốn xóa các mục chọn?")) {
            for (int i = 0; i < tbllsHocTap.getRowCount(); i++) {
                if ((Boolean) tbllsHocTap.getValueAt(i, 2)) {
                }
            }
            this.fillToTable();
        }
    }




    @Override
    public void setForm(LearningHistory entity) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        txtID.setText(String.valueOf(entity.getId()));
        txtIDHV.setText(entity.getId_hoc_vien());
        txtIDLop.setText(entity.getId_lop());
        txtIDLichHoc.setText(String.valueOf(entity.getId_lich_hoc()));
        txtngayHoc.setText(String.valueOf(entity.getNgay_hoc_thuc_te()!= null ? sdf.format(entity.getNgay_hoc_thuc_te()) : "") );
        if(entity.isCo_mat() == true){
            chkcomat.setSelected(true);
        }else{
            chkcomat.setSelected(false);
        }
        chkcomat.isSelected();
        txtghiChu.setText(entity.getGhi_chu());
    }

    @Override
    public LearningHistory getForm() {
        LearningHistory entity = new LearningHistory();
        entity.setId(Integer.parseInt(txtID.getText()));
        entity.setId_hoc_vien(txtIDHV.getText());
        entity.setId_lop(txtIDLop.getText());
        entity.setId_lich_hoc(txtIDLichHoc.getText());
        if(chkcomat.isSelected()){
            entity.setCo_mat(true);
        }else{
            entity.setCo_mat(false);
        }
        entity.setGhi_chu(txtghiChu.getText());
        try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // hoặc "dd/MM/yyyy"
        Date ngayHoc = sdf.parse(txtngayHoc.getText());
        entity.setNgay_hoc_thuc_te(new java.sql.Date(ngayHoc.getTime())); // Gán lại vào entity
    } catch (ParseException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Sai định dạng ngày. Vui lòng nhập đúng định dạng yyyy-MM-dd.");
    }


        return entity;
    }

    @Override
    public void create() {
        LearningHistory entity = this.getForm();
        dao.create(entity);
        this.fillToTable();
        this.clear();
    }

    @Override
    public void update() {
        LearningHistory entity = this.getForm();
        dao.update(entity);
        this.fillToTable();
    }

    @Override
    public void delete() {
        if (XDialog.confirm("Bạn thực sự muốn xóa?")) {
            String id = txtID.getText();
            dao.deleteById(id);
            this.fillToTable();
            this.clear();
        }
    }

    @Override
    public void clear() {
        this.setForm(new LearningHistory());
        this.setEditable(false);
    }

    @Override
    public void setEditable(boolean editable) {
        txtID.setEnabled(!editable);
        btnthem.setEnabled(!editable);
        btncapNhat.setEnabled(editable);
        btnxoa.setEnabled(editable);

        int rowCount = tbllsHocTap.getRowCount();

    }

    @Override
    public void moveFirst() {
        this.moveTo(0);
    }

    @Override
    public void movePrevious() {
        this.moveTo(tbllsHocTap.getSelectedRow() - 1);
    }

    @Override
    public void moveNext() {
        this.moveTo(tbllsHocTap.getSelectedRow() + 1);
    }

    @Override
    public void moveLast() {
        this.moveTo(tbllsHocTap.getRowCount() - 1);
    }

    @Override
    public void moveTo(int index) {
        if (index < 0) {
            this.moveLast();
        } else if (index >= tbllsHocTap.getRowCount()) {
            this.moveFirst();
        } else {
            tbllsHocTap.clearSelection();
            tbllsHocTap.setRowSelectionInterval(index, index);
            this.edit();
        }
    }

 


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbllsHocTap = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        txtIDHocphi = new javax.swing.JLabel();
        txtngayHoc = new javax.swing.JTextField();
        txtIDHocSinh = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtghiChu = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtIDLichHoc = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbllsHocTap2 = new javax.swing.JTable();
        btnthem = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btncapNhat = new javax.swing.JButton();
        btnlamMoi = new javax.swing.JButton();
        txtIDHocphi1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtIDHocSinh1 = new javax.swing.JLabel();
        txtIDHV = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtIDLop = new javax.swing.JTextField();
        chkcomat = new javax.swing.JCheckBox();

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbllsHocTap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Học viên", "ID Lớp", "ID Lịch học", "Ngày học thực tê", "Có  mặt", "Ghi chú"
            }
        ));
        jScrollPane5.setViewportView(tbllsHocTap);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1034, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 51, Short.MAX_VALUE))
        );

        tabs.addTab("Tổng quát", jPanel8);

        txtIDHocphi.setText("ID");

        txtIDHocSinh.setText("ID Học viên");

        jLabel3.setText("ID Lớp");

        jLabel4.setText("ID Lịch học");

        tbllsHocTap2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Học viên", "ID Lớp", "ID Lịch học", "Ngày học thực tế", "Có mặt", "Ghi chú"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbllsHocTap2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbllsHocTap2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbllsHocTap2);

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

        txtIDHocphi1.setText("Ngày học");

        txtIDHocSinh1.setText("Có mặt");

        jLabel5.setText("Ghi chú");

        chkcomat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkcomatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(btnthem)
                                .addGap(18, 18, 18)
                                .addComponent(btnxoa)
                                .addGap(18, 18, 18)
                                .addComponent(btncapNhat)
                                .addGap(18, 18, 18)
                                .addComponent(btnlamMoi))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(23, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtIDHocphi)
                        .addGap(23, 23, 23)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(txtIDHocSinh)
                                .addGap(27, 27, 27)
                                .addComponent(txtIDHV, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(36, 36, 36)
                                .addComponent(txtIDLop, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(jLabel4)
                                .addGap(34, 34, 34)
                                .addComponent(txtIDLichHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(txtIDHocphi1)
                                .addGap(18, 18, 18)
                                .addComponent(txtngayHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(txtIDHocSinh1)
                                .addGap(27, 27, 27)
                                .addComponent(chkcomat)
                                .addGap(105, 105, 105)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtghiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDHocphi)
                    .addComponent(txtIDHocSinh)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtIDLichHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDHV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDHocphi1)
                    .addComponent(txtngayHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDHocSinh1)
                    .addComponent(jLabel5)
                    .addComponent(txtghiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkcomat))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem)
                    .addComponent(btnxoa)
                    .addComponent(btncapNhat)
                    .addComponent(btnlamMoi))
                .addContainerGap())
        );

        tabs.addTab("Chỉnh sửa", jPanel6);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1044, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 418, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1044, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 418, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
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

    private void tbllsHocTap2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbllsHocTap2MouseClicked
        // TODO add your handling code here:
                   if (evt.getClickCount() == 2) {
            this.edit();
        }
    }//GEN-LAST:event_tbllsHocTap2MouseClicked

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

    private void chkcomatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkcomatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkcomatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncapNhat;
    private javax.swing.JButton btnlamMoi;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.JCheckBox chkcomat;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tbllsHocTap;
    private javax.swing.JTable tbllsHocTap2;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDHV;
    private javax.swing.JLabel txtIDHocSinh;
    private javax.swing.JLabel txtIDHocSinh1;
    private javax.swing.JLabel txtIDHocphi;
    private javax.swing.JLabel txtIDHocphi1;
    private javax.swing.JTextField txtIDLichHoc;
    private javax.swing.JTextField txtIDLop;
    private javax.swing.JTextField txtghiChu;
    private javax.swing.JTextField txtngayHoc;
    // End of variables declaration//GEN-END:variables
}
