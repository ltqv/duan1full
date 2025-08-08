package com.raven.DAOImpl;

import com.raven.DAO.ViewedSubmissionDAO;
import com.raven.entity.ViewedSubmission;
import java.util.List;
import com.raven.util.XJdbc;
import com.raven.util.XQuery;

public class ViewedSubmissionDAOImpl implements ViewedSubmissionDAO{
    String createSql = "INSERT INTO HocVienXemBai(id_hocvien, id_bailam) VALUES(?, ?)"; 
    String updateSql = "UPDATE HocVienXemBai SET ten_dang_nhap=?, mat_khau=?, ho_ten=?, so_dien_thoai=?, email=?, vai_tro=?, ngay_tao=? WHERE id=?"; 
    String deleteSql = "DELETE FROM HocVienXemBai WHERE id=?"; 
    String findAllSql = "SELECT * FROM HocVienXemBai"; 
    String findByIdSql = "SELECT * FROM HocVienXemBai WHERE id=?"; 
    
    @Override 
    public ViewedSubmission create(ViewedSubmission entity) { 
        Object[] values = { 
            entity.getId_bailam(),
            entity.getId_hocvien()
        }; 
        XJdbc.executeUpdate(createSql, values); 
        return entity; 
    } 
    @Override 
    public void update(ViewedSubmission entity) { 
        Object[] values = {  
            entity.getId_bailam(),
            entity.getId_hocvien(),
            entity.getId()
        }; 
        XJdbc.executeUpdate(updateSql, values); 
    } 
    @Override 
    public void deleteById(Integer id) { 
        XJdbc.executeUpdate(deleteSql, id); 
    } 
    @Override 
    public List<ViewedSubmission> findAll() { 
        return XQuery.getBeanList(ViewedSubmission.class, findAllSql); 
    } 
    @Override 
    public ViewedSubmission findById(Integer id) { 
        return XQuery.getSingleBean(ViewedSubmission.class, findByIdSql, id); 
    } 
}
