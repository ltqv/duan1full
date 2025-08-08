package com.raven.DAOImpl;

import com.raven.DAO.CourseDAO;
import com.raven.entity.Course;
import java.util.List;
import com.raven.util.XJdbc;
import com.raven.util.XQuery;

public class CourseDAOImpl implements CourseDAO{
    String createSql = "INSERT INTO KhoaHoc(id, ten_khoa_hoc, ngay_tao) VALUES(?, ?, ?)"; 
    String updateSql = "UPDATE KhoaHoc SET ten_khoa_hoc=?, ngay_tao=? WHERE id=?"; 
    String deleteSql = "DELETE FROM KhoaHoc WHERE id=?"; 
    String findAllSql = "SELECT * FROM KhoaHoc"; 
    String findByIdSql = "SELECT * FROM KhoaHoc WHERE id=?"; 
    
    @Override 
    public Course create(Course entity) { 
        Object[] values = { 
            entity.getId(), 
            entity.getTen_khoa_hoc(),
            entity.getNgay_tao()
        }; 
        XJdbc.executeUpdate(createSql, values); 
        return entity; 
    } 
    @Override 
    public void update(Course entity) { 
        Object[] values = {  
            entity.getTen_khoa_hoc(),
            entity.getNgay_tao(),
            entity.getId()
        }; 
        XJdbc.executeUpdate(updateSql, values); 
    } 
    @Override 
    public void deleteById(String id) { 
        XJdbc.executeUpdate(deleteSql, id); 
    } 
    @Override 
    public List<Course> findAll() { 
        return XQuery.getBeanList(Course.class, findAllSql); 
    } 
    @Override 
    public Course findById(String id) { 
        return XQuery.getSingleBean(Course.class, findByIdSql, id); 
    } 
}
