package com.raven.DAOImpl;

import com.raven.DAO.StudentInClassDAO;
import com.raven.entity.StudentInClass;
import java.util.List;
import com.raven.util.XJdbc;
import com.raven.util.XQuery;

public class StudenInClassDAOImpl implements StudentInClassDAO{
    String createSql = "INSERT INTO HocVienTrongLop(id_lop, id_hoc_vien, trang_thai) VALUES(?, ?, ?)"; 
    String updateSql = "UPDATE HocVienTrongLop SET id_lop=?, id_hoc_vien=?, trang_thai=? WHERE id=?"; 
    String deleteSql = "DELETE FROM HocVienTrongLop WHERE id=?"; 
    String findAllSql = "SELECT * FROM HocVienTrongLop"; 
    String findByIdSql = "SELECT * FROM HocVienTrongLop WHERE id=?"; 
    
    @Override 
    public StudentInClass create(StudentInClass entity) { 
        Object[] values = { 
            entity.getId_lop(),
            entity.getId_hoc_vien(),
            entity.getTrang_thai()
        }; 
        XJdbc.executeUpdate(createSql, values); 
        return entity; 
    } 
    @Override 
    public void update(StudentInClass entity) { 
        Object[] values = {  
            entity.getId_lop(),
            entity.getId_hoc_vien(),
            entity.getTrang_thai()
        }; 
        XJdbc.executeUpdate(updateSql, values); 
    } 
    @Override 
    public void deleteById(Integer id) { 
        XJdbc.executeUpdate(deleteSql, id); 
    } 
    @Override 
    public List<StudentInClass> findAll() { 
        return XQuery.getBeanList(StudentInClass.class, findAllSql); 
    } 
    @Override 
    public StudentInClass findById(Integer id) { 
        return XQuery.getSingleBean(StudentInClass.class, findByIdSql, id); 
    } 
}
