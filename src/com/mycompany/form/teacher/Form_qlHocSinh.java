package com.mycompany.form.teacher;

import com.raven.DAO.StudentInClassDAO;
import com.raven.DAOImpl.StudenInClassDAOImpl;
import com.raven.entity.StudentInClass;
import com.raven.util.XDialog;
import java.util.List;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

public class Form_qlHocSinh extends javax.swing.JPanel implements com.raven.Controller.Form_qlHocSinh {

    StudentInClassDAO dao = new StudenInClassDAOImpl();
    List<StudentInClass> items = List.of();

    public Form_qlHocSinh() {
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
        DefaultTableModel model1 = (DefaultTableModel) tblhocVien.getModel();
        model1.setRowCount(0);

        items = dao.findAll();
        items.forEach(item -> {
            Object[] rowData = {
                item.getId_lop(),
                item.getId_hoc_vien(),
                item.isTrang_thai() ? "Đang học" : "Bảo lưu"
            };
            model1.addRow(rowData);
        });
        tblhocVien.setDefaultEditor(Object.class, null);
    }

    @Override
    public void edit() {
        
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
        for (int i = 0; i < tblhocVien.getRowCount(); i++) {
            tblhocVien.setValueAt(checked, i, 2);
        }
    }

    @Override
    public void deleteCheckedItems() {
        if (XDialog.confirm("Bạn thực sự muốn xóa các mục chọn?")) {
            for (int i = 0; i < tblhocVien.getRowCount(); i++) {
                if ((Boolean) tblhocVien.getValueAt(i, 2)) {
                    dao.deleteById(items.get(i).getId());
                }
            }
            this.fillToTable();
        }
    }

    @Override
    public void setForm(StudentInClass entity) {

    }

    @Override
    public StudentInClass getForm() {
        StudentInClass entity = new StudentInClass();
        return entity;
    }

    @Override
    public void create() {
        StudentInClass entity = this.getForm();
        dao.create(entity);
        this.fillToTable();
        this.clear();
    }

    @Override
    public void update() {
        StudentInClass entity = this.getForm();
        dao.update(entity);
        this.fillToTable();
    }

    @Override
    public void delete() {

    }

    @Override
    public void clear() {
        this.setForm(new StudentInClass());
        this.setEditable(false);
    }

    @Override
    public void setEditable(boolean editable) {

    }

    @Override
    public void moveFirst() {
        this.moveTo(0);
    }

    @Override
    public void movePrevious() {
        this.moveTo(tblhocVien.getSelectedRow() - 1);
    }

    @Override
    public void moveNext() {
        this.moveTo(tblhocVien.getSelectedRow() + 1);
    }

    @Override
    public void moveLast() {
        this.moveTo(tblhocVien.getRowCount() - 1);
    }

    @Override
    public void moveTo(int index) {
        if (index < 0) {
            this.moveLast();
        } else if (index >= tblhocVien.getRowCount()) {
            this.moveFirst();
        } else {
            tblhocVien.clearSelection();
            tblhocVien.setRowSelectionInterval(index, index);
            this.edit();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblhocVien = new javax.swing.JTable();

        tblhocVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID Lớp", "ID Học viên", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblhocVien);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1085, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 1040, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(23, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(14, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblhocVien;
    // End of variables declaration//GEN-END:variables
}
