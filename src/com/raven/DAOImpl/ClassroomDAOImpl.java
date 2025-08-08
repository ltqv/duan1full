package com.raven.DAOImpl;

import com.raven.entity.Classroom;
import java.util.List;
import com.raven.util.XJdbc;
import com.raven.util.XQuery;
import com.raven.DAO.ClassroomDAO;

public class ClassroomDAOImpl implements ClassroomDAO{
    String createSql = "INSERT INTO LopHoc(id, id_khoa_hoc, hoc_ky, tong_buoi, id_giang_vien, ngay_bat_dau, ngay_ket_thuc) VALUES(?, ?, ?, ?, ?, ?, ?)"; 
    String updateSql = "UPDATE LopHoc SET id_khoa_hoc=?, hoc_ky=?, tong_buoi=?, id_giang_vien=?, ngay_bat_dau=?, ngay_ket_thuc=? WHERE id=?"; 
    String deleteSql = "DELETE FROM LopHoc WHERE id=?"; 
    String findAllSql = "SELECT * FROM LopHoc"; 
    String findByIdSql = "SELECT * FROM LopHoc WHERE id=?"; 
    
    @Override 
    public Classroom create(Classroom entity) { 
        Object[] values = { 
            entity.getId(), 
            entity.getId_khoa_hoc(),
            entity.getHoc_ky(),
            entity.getTong_buoi(),
            entity.getId_giang_vien(),
            entity.getNgay_bat_dau(),
            entity.getNgay_ket_thuc()
        }; 
        XJdbc.executeUpdate(createSql, values); 
        return entity; 
    } 
    @Override 
    public void update(Classroom entity) { 
        Object[] values = {  
            entity.getId_khoa_hoc(),
            entity.getHoc_ky(),
            entity.getTong_buoi(),
            entity.getId_giang_vien(),
            entity.getNgay_bat_dau(),
            entity.getNgay_ket_thuc(),
            entity.getId()
        }; 
        XJdbc.executeUpdate(updateSql, values); 
    } 
    @Override 
    public void deleteById(String id) { 
        XJdbc.executeUpdate(deleteSql, id); 
    } 
    @Override 
    public List<Classroom> findAll() { 
        return XQuery.getBeanList(Classroom.class, findAllSql); 
    } 
    @Override 
    public Classroom findById(String id) { 
        return XQuery.getSingleBean(Classroom.class, findByIdSql, id); 
    } 
}
