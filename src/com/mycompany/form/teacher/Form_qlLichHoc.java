package com.mycompany.form.teacher;

import com.raven.Controller.Form_3Controller;
import com.raven.util.XDialog;
import java.util.List;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import com.raven.DAO.ScheduleDAO;
import com.raven.DAOImpl.ScheduleDAOImpl;
import com.raven.entity.Schedule;
import java.text.SimpleDateFormat;

public class Form_qlLichHoc extends javax.swing.JPanel implements Form_3Controller {

    ScheduleDAO dao = new ScheduleDAOImpl();
    List<Schedule> items = List.of();

    public Form_qlLichHoc() {
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
        DefaultTableModel model1 = (DefaultTableModel) tblForm3.getModel();
        model1.setRowCount(0);

        items = dao.findAll();
        items.forEach(item -> {
            Object[] rowData = {
                item.getId(),
                item.getId_lop(),
                item.getThu_trong_tuan(),
                item.getGio_bat_dau(),
                item.getGio_ket_thuc(),
                item.getPhong_hoc(),
            };
            model1.addRow(rowData);
        });
        tblForm3.setDefaultEditor(Object.class, null);
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
        for (int i = 0; i < tblForm3.getRowCount(); i++) {
            tblForm3.setValueAt(checked, i, 2);
        }
    }

    @Override
    public void deleteCheckedItems() {
        if (XDialog.confirm("Bạn thực sự muốn xóa các mục chọn?")) {
            for (int i = 0; i < tblForm3.getRowCount(); i++) {
                if ((Boolean) tblForm3.getValueAt(i, 2)) {
                    dao.deleteById(items.get(i).getId());
                }
            }
            this.fillToTable();
        }
    }

    @Override
    public void setForm(Schedule entity) { 

    }

    @Override
    public Schedule getForm() {
        Schedule entity = new Schedule();
        
    return entity;
}

    @Override
    public void create() {
        Schedule entity = this.getForm();
        dao.create(entity);
        this.fillToTable();
        this.clear();
    }

    @Override
    public void update() {
        Schedule entity = this.getForm();
        dao.update(entity);
        this.fillToTable();
    }

    @Override
    public void delete() {
       
    }

    @Override
    public void clear() {
        this.setForm(new Schedule());
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
        this.moveTo(tblForm3.getSelectedRow() - 1);
    }

    @Override
    public void moveNext() {
        this.moveTo(tblForm3.getSelectedRow() + 1);
    }

    @Override
    public void moveLast() {
        this.moveTo(tblForm3.getRowCount() - 1);
    }

    @Override
    public void moveTo(int index) {
        if (index < 0) {
            this.moveLast();
        } else if (index >= tblForm3.getRowCount()) {
            this.moveFirst();
        } else {
            tblForm3.clearSelection();
            tblForm3.setRowSelectionInterval(index, index);
            this.edit();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblForm3 = new javax.swing.JTable();

        tblForm3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Lớp", "Thứ trong tuần", "Giờ bắt đầu", "Giờ kết thúc", "Phòng học"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblForm3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 939, Short.MAX_VALUE)
                .addContainerGap())
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
            .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblForm3;
    // End of variables declaration//GEN-END:variables
}
