package com.raven.DAOImpl;

import com.raven.DAO.SubmissionDAO;
import com.raven.entity.Submission;
import java.util.List;
import com.raven.util.XJdbc;
import com.raven.util.XQuery;

public class SubmissionDAOImpl implements SubmissionDAO{
    String createSql = "INSERT INTO BaiLam(id, id_giang_vien, id_bai_kt, thoi_gian_nop, ket_qua_json, so_cau_dung) VALUES(?, ?, ?, ?, ?, ?)"; 
    String updateSql = "UPDATE BaiLam SET id_giang_vien=?, id_bai_kt=?, thoi_gian_nop=?, ket_qua_json=?, so_cau_dung=? WHERE id=?"; 
    String deleteSql = "DELETE FROM BaiLam WHERE id=?"; 
    String findAllSql = "SELECT * FROM BaiLam"; 
    String findByIdSql = "SELECT * FROM BaiLam WHERE id=?"; 
    
    @Override 
    public Submission create(Submission entity) { 
        Object[] values = { 
            entity.getId(), 
            entity.getId_giang_vien(),
            entity.getId_bai_kt(),
            entity.getThoi_gian_nop(),
            entity.getKet_qua_json(),
            entity.getSo_cau_dung()
        }; 
        XJdbc.executeUpdate(createSql, values); 
        return entity; 
    } 
    @Override 
    public void update(Submission entity) { 
        Object[] values = {  
            entity.getId_giang_vien(),
            entity.getId_bai_kt(),
            entity.getThoi_gian_nop(),
            entity.getKet_qua_json(),
            entity.getSo_cau_dung(),
            entity.getId()
        }; 
        XJdbc.executeUpdate(updateSql, values); 
    } 
    @Override 
    public void deleteById(String id) { 
        XJdbc.executeUpdate(deleteSql, id); 
    } 
    @Override 
    public List<Submission> findAll() { 
        return XQuery.getBeanList(Submission.class, findAllSql); 
    } 
    @Override 
    public Submission findById(String id) { 
        return XQuery.getSingleBean(Submission.class, findByIdSql, id); 
    } 
}
