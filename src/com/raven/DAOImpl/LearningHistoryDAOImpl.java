
package com.raven.DAOImpl;

import com.raven.DAO.LearningHistoryDAO;
import com.raven.entity.LearningHistory;
import com.raven.util.XJdbc;
import com.raven.util.XQuery;
import java.util.List;


public class LearningHistoryDAOImpl implements LearningHistoryDAO{
    String createSql = "INSERT INTO LichSuHocTap(id_hoc_vien, id_lop, id_lich_hoc, ngay_hoc_thuc_te, co_mat, ghi_chu) VALUES(?, ?, ?, ?, ?, ?)"; 
    String updateSql = "UPDATE LichSuHocTap SET id_hoc_vien=?, id_lop=?, id_lich_hoc=?, ngay_hoc_thuc_te=?, co_mat=?, ghi_chu=? WHERE id=?"; 
    String deleteSql = "DELETE FROM LichSuHocTap WHERE id=?"; 
    String findAllSql = "SELECT * FROM LichSuHocTap"; 
    String findByIdSql = "SELECT * FROM LichSuHocTap WHERE id=?";
    
     @Override 
    public LearningHistory create(LearningHistory entity) { 
        Object[] values = { 
            entity.getId_hoc_vien(),
            entity.getId_lop(),
            entity.getId_lich_hoc(),
            entity.getNgay_hoc_thuc_te(),
            entity.getCo_mat(),
            entity.getGhi_chu()
        }; 
        XJdbc.executeUpdate(createSql, values); 
        return entity; 
    } 
    @Override 
    public void update(LearningHistory entity) { 
        Object[] values = {  
            entity.getId_hoc_vien(),
            entity.getId_lop(),
            entity.getId_lich_hoc(),
            entity.getNgay_hoc_thuc_te(),
            entity.getCo_mat(),
            entity.getGhi_chu(),
            entity.getId()
        }; 
        XJdbc.executeUpdate(updateSql, values); 
    } 
    @Override 
    public void deleteById(String id) { 
        XJdbc.executeUpdate(deleteSql, id); 
    } 
    @Override 
    public List<LearningHistory> findAll() { 
        return XQuery.getBeanList(LearningHistory.class, findAllSql); 
    } 
    @Override 
    public LearningHistory findById(String id) { 
        return XQuery.getSingleBean(LearningHistory.class, findByIdSql, id); 
    } 
}
