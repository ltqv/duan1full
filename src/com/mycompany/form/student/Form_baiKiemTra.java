
package com.mycompany.form.student;

import com.raven.Controller.Form_baiKiemtraHv;
import com.raven.DAO.ExamDAO;
import com.raven.DAOImpl.ExamDAOImpl;
import com.raven.entity.Exam;
import com.raven.util.XDialog;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JTextField;
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
            item.isKich_hoat() ? "Hoàn thành":"Chưa hoàn thành"
            };
            model1.addRow(rowData);
        });
        tblbaiKiemTra.setDefaultEditor(Object.class, null);

        

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
  
}

@Override
public Exam getForm() {
    Exam entity = new Exam();
    return entity;
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
       
    }

    @Override
    public void clear() {
        this.setForm(new Exam());
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblbaiKiemTra;
    // End of variables declaration//GEN-END:variables
}
