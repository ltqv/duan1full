package com.mycompany.form.student;

import com.mycompany.model.student.Model_Card;
import com.mycompany.swing.student.ScrollBar;
import com.raven.Controller.Form_HometeacherController;
import com.raven.DAO.Form_HometeacherDAO;
import com.raven.DAOImpl.Form_HometeacherDAOImpl;
import com.raven.entity.Form_Hometeacher;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

public class Form_Home extends javax.swing.JPanel implements Form_HometeacherController{
    Form_HometeacherDAO userDAO = new Form_HometeacherDAOImpl();
    List<Form_Hometeacher> user = List.of();

    public Form_Home() {
        initComponents();
        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/mycompany/icon/student/course_card.png")), "SỐ KHÓA HỌC HIỆN TẠI", "2", ""));
        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/mycompany/icon/student/successcourse_card.png")), "SÓ KHÓA HỌC HOÀN THÀNH", "5", ""));
        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/mycompany/icon/student/test_card.png")), "ĐIỂM KIỂM TRA", "95 ĐIỂM", ""));
//          add row table
        spTable.setVerticalScrollBar(new ScrollBar());
        spTable.getVerticalScrollBar().setBackground(Color.WHITE);
        spTable.getViewport().setBackground(Color.WHITE);
        
        this.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                open(); // Gọi khi panel được thêm vào giao diện
            }
            
            @Override
            public void ancestorRemoved(AncestorEvent event) {}

            @Override
            public void ancestorMoved(AncestorEvent event) {}
        });
    }
    
    @Override 
    public void open() { 
        this.fillToTable(); 
        this.clear(); 
    } 

    @Override 
    public void fillToTable() { 
        DefaultTableModel model = (DefaultTableModel) table.getModel(); 
        model.setRowCount(0); 

        user = userDAO.findStudent(); 
        user.forEach(item -> { 
            Object[] rowData = { 
                item.getHo_ten(),
                item.getSo_dien_thoai(),
                item.getEmail(),
                item.getVai_tro(),
                item.getId_lop()
            }; 
            model.addRow(rowData); 
        }); 
    } 

    @Override 
    public void edit() { 
        Form_Hometeacher entity = user.get(table.getSelectedRow()); 
        this.setForm(entity); 
        this.setEditable(true); 
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
       
    } 

    @Override 
    public void setForm(Form_Hometeacher entity) { 
        
    } 

    @Override 
    public Form_Hometeacher getForm() { 
        Form_Hometeacher user = new Form_Hometeacher();
        return user;
    } 



    @Override 
    public void create() { 
        
    } 

    @Override 
    public void update() { 
        
    } 

    @Override 
    public void delete() { 
         
    } 

    @Override 
    public void clear() { 
        this.setForm(new Form_Hometeacher()); 
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
        
    } 

    @Override 
    public void moveNext() { 
        
    } 

    @Override 
    public void moveLast() { 
        
    } 

    @Override 
    public void moveTo(int index) { 
        
    } 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JLayeredPane();
        card1 = new com.mycompany.component.Student.Card();
        card3 = new com.mycompany.component.Student.Card();
        card2 = new com.mycompany.component.Student.Card();
        panelBorder1 = new com.mycompany.swing.student.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.mycompany.swing.student.Table();

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(0, 204, 204));
        card1.setColor2(new java.awt.Color(0, 153, 153));
        panel.add(card1);

        card3.setColor1(new java.awt.Color(255, 102, 204));
        card3.setColor2(new java.awt.Color(255, 204, 255));
        panel.add(card3);

        card2.setColor1(new java.awt.Color(255, 255, 0));
        card2.setColor2(new java.awt.Color(204, 204, 0));
        panel.add(card2);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Standard Table Design");

        spTable.setBorder(null);

        table.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Họ tên", "Số điện thoại", "Email", "Vai trò", "Lớp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spTable.setViewportView(table);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spTable, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.component.Student.Card card1;
    private com.mycompany.component.Student.Card card2;
    private com.mycompany.component.Student.Card card3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private com.mycompany.swing.student.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.mycompany.swing.student.Table table;
    // End of variables declaration//GEN-END:variables
}
