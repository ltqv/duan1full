
package com.mycompany.form.student;

import com.raven.Controller.Form_hocPhiHv;
import com.raven.DAO.TuitionfeesDAO;
import com.raven.DAOImpl.TuitionfeesDAOImpl;
import com.raven.entity.Tuitionfees;
import com.raven.util.XDialog;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;


public class Form_hocPhi extends javax.swing.JPanel implements Form_hocPhiHv{

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
            item.getId_hoc_phi(), 
            item.getId_hoc_vien(),
            item.getHoc_ky(),
            item.getNam_hoc(),
            item.getTong_hoc_phi(),
            item.getDa_dong(),
            item.getCon_lai(),
            item.getTrang_thai(),
            item.getNgay_dong_cuoi()
            };
            model1.addRow(rowData);
        });
        tblHocphi.setDefaultEditor(Object.class, null);

        
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
   
}

@Override
public Tuitionfees getForm() {
    Tuitionfees entity = new Tuitionfees();
      return entity;
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
       
    }

    @Override
    public void clear() {
        this.setForm(new Tuitionfees());
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblHocphi;
    // End of variables declaration//GEN-END:variables
}
