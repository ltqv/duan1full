package com.raven.form;

import com.raven.Controller.Form_HomeController;
import com.raven.DAO.UserDAO;
import com.raven.DAOImpl.UserDAOImpl;
import com.raven.entity.User;
import com.raven.model.Model_Card;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

public class Form_Home extends javax.swing.JPanel implements Form_HomeController{

    UserDAO userDAO = new UserDAOImpl();
    List<User> user = List.of();

    public Form_Home() {
        initComponents();
        card1.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/teacher.png")), "Số lượng giáo viên", "10", ""));
        card2.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/sdt.png")), "Số lượng học sinh", "30", ""));
        card3.setData(new Model_Card(new ImageIcon(getClass().getResource("/com/raven/icon/door.png")), "Số phòng học hiện trống", "0", ""));
        //  add row table
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

        user = userDAO.findAll(); 
        user.forEach(item -> { 
            Object[] rowData = { 
                item.getHo_ten(),
                item.getEmail(),
                item.getSo_dien_thoai(),
                item.getVai_tro(),
                item.getNgay_tao(),
                item.getVai_tro()
            }; 
            model.addRow(rowData); 
        }); 
    } 

    @Override 
    public void edit() { 
        User entity = user.get(table.getSelectedRow()); 
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
    public void setForm(User entity) { 
        
    } 

    @Override 
    public User getForm() { 
        User user = new User();
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
        this.setForm(new User()); 
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
        card1 = new com.raven.component.Card();
        card2 = new com.raven.component.Card();
        card3 = new com.raven.component.Card();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        spTable = new javax.swing.JScrollPane();
        table = new com.raven.swing.Table();

        panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        card1.setColor1(new java.awt.Color(142, 142, 250));
        card1.setColor2(new java.awt.Color(123, 123, 245));
        panel.add(card1);

        card2.setColor1(new java.awt.Color(186, 123, 247));
        card2.setColor2(new java.awt.Color(167, 94, 236));
        panel.add(card2);

        card3.setColor1(new java.awt.Color(241, 208, 62));
        card3.setColor2(new java.awt.Color(211, 184, 61));
        panel.add(card3);

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("Thông tin tổng quát");

        spTable.setBorder(null);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Họ tên", "Email", "Số điện thoại", "Vai trò", "Ngày tạo", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
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
                    .addComponent(spTable))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.component.Card card1;
    private com.raven.component.Card card2;
    private com.raven.component.Card card3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane panel;
    private com.raven.swing.PanelBorder panelBorder1;
    private javax.swing.JScrollPane spTable;
    private com.raven.swing.Table table;
    // End of variables declaration//GEN-END:variables
}
