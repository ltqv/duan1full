package com.raven.DAOImpl;

import com.raven.DAO.ScoreDAO;
import com.raven.entity.Score;
import com.raven.util.XJdbc;
import com.raven.util.XQuery;
import java.util.List;

public class ScoreDAOImpl implements ScoreDAO{
    String createSql = "INSERT INTO Diem(id, id_bai_kiem_tra, diem, nhan_xet, ngay_cham) VALUES(?, ?, ?, ?, ?)"; 
    String updateSql = "UPDATE Diem SET id_bai_kiem_tra=?, diem=?, nhan_xet=?, ngay_cham=? WHERE id=?"; 
    String deleteSql = "DELETE FROM Diem WHERE id=?"; 
    String findAllSql = "SELECT * FROM Diem"; 
    String findByIdSql = "SELECT * FROM Diem WHERE id=?"; 
    
    @Override 
    public Score create(Score entity) { 
        Object[] values = { 
            entity.getId(), 
            entity.getId_bai_kiem_tra(),
            entity.getDiem(),
            entity.getNhan_xet(),
            entity.getNgay_cham()
        }; 
        XJdbc.executeUpdate(createSql, values); 
        return entity; 
    } 
    @Override 
    public void update(Score entity) { 
        Object[] values = {  
            entity.getId_bai_kiem_tra(),
            entity.getDiem(),
            entity.getNhan_xet(),
            entity.getNgay_cham(),
            entity.getId()
        }; 
        XJdbc.executeUpdate(updateSql, values); 
    } 
    @Override 
    public void deleteById(String id) { 
        XJdbc.executeUpdate(deleteSql, id); 
    } 
    @Override 
    public List<Score> findAll() { 
        return XQuery.getBeanList(Score.class, findAllSql); 
    } 
    @Override 
    public Score findById(String id) { 
        return XQuery.getSingleBean(Score.class, findByIdSql, id); 
    } 
}
