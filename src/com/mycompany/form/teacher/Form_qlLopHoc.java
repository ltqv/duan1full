package com.mycompany.form.teacher;

import com.raven.Controller.Form_2Controller;
import com.raven.DAOImpl.ClassroomDAOImpl;
import com.raven.entity.Classroom;
import com.raven.util.XDialog;
import java.util.List;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import com.raven.DAO.ClassroomDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class Form_qlLopHoc extends javax.swing.JPanel implements Form_2Controller {

    ClassroomDAO dao = new ClassroomDAOImpl();
    List<Classroom> items = List.of();

    public Form_qlLopHoc() {
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
        DefaultTableModel model1 = (DefaultTableModel) tblClass1.getModel();
        model1.setRowCount(0);

        items = dao.findAll();
        items.forEach(item -> {
            Object[] rowData = {
                item.getId(),
                item.getId_khoa_hoc(),
                item.getHoc_ky(),
                item.getTong_buoi(),
                item.getId_giang_vien(),
                item.getNgay_bat_dau(),
                item.getNgay_ket_thuc()
            };
            model1.addRow(rowData);
        });
        tblClass1.setDefaultEditor(Object.class, null);
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
        for (int i = 0; i < tblClass1.getRowCount(); i++) {
            tblClass1.setValueAt(checked, i, 2);
        }
    }

    @Override
    public void deleteCheckedItems() {
        if (XDialog.confirm("Bạn thực sự muốn xóa các mục chọn?")) {
            for (int i = 0; i < tblClass1.getRowCount(); i++) {
                if ((Boolean) tblClass1.getValueAt(i, 2)) {
                    dao.deleteById(items.get(i).getId());
                }
            }
            this.fillToTable();
        }
    }

    @Override
    public void setForm(Classroom entity) {
       
    }

    @Override
    public Classroom getForm() {
        Classroom entity = new Classroom();
        return entity;
    }

    @Override
    public void create() {
        Classroom entity = this.getForm();
        dao.create(entity);
        this.fillToTable();
        this.clear();
    }

    @Override
    public void update() {
        Classroom entity = this.getForm();
        dao.update(entity);
        this.fillToTable();
    }

    @Override
    public void delete() {
        
    }

    @Override
    public void clear() {
        this.setForm(new Classroom());
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
        this.moveTo(tblClass1.getSelectedRow() - 1);
    }

    @Override
    public void moveNext() {
        this.moveTo(tblClass1.getSelectedRow() + 1);
    }

    @Override
    public void moveLast() {
        this.moveTo(tblClass1.getRowCount() - 1);
    }

    @Override
    public void moveTo(int index) {
        if (index < 0) {
            this.moveLast();
        } else if (index >= tblClass1.getRowCount()) {
            this.moveFirst();
        } else {
            tblClass1.clearSelection();
            tblClass1.setRowSelectionInterval(index, index);
            this.edit();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        tabs = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblClass1 = new javax.swing.JTable();

        tblClass1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Khóa học", "Học kỳ", "Số buổi", "Giảng viên", "Ngày bắt đầu", "Ngày kết thúc"
            }
        ));
        jScrollPane3.setViewportView(tblClass1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 981, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabs.addTab("Tổng quát", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblClass1;
    // End of variables declaration//GEN-END:variables
}
