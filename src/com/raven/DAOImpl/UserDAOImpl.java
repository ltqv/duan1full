package com.raven.DAOImpl;

import com.raven.DAO.UserDAO;
import com.raven.entity.User;
import java.util.List;
import com.raven.util.XJdbc;
import com.raven.util.XQuery;

public class UserDAOImpl implements UserDAO {

    String createSql = "INSERT INTO NguoiDung(id, ten_dang_nhap, mat_khau, ho_ten, so_dien_thoai, email, vai_tro, ngay_tao) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE NguoiDung SET ten_dang_nhap=?, mat_khau=?, ho_ten=?, so_dien_thoai=?, email=?, vai_tro=?, ngay_tao=? WHERE id=?";
    String deleteSql = "DELETE FROM NguoiDung WHERE id=?";
    String findAllSql = "SELECT * FROM NguoiDung";
    String findByIdSql = "SELECT * FROM NguoiDung WHERE id=?";
    String findByUsername = "SELECT * FROM NguoiDung WHERE ten_dang_nhap=?";

    @Override
    public User create(User entity) {
        Object[] values = {
            entity.getId(),
            entity.getTen_dang_nhap(),
            entity.getMat_khau(),
            entity.getHo_ten(),
            entity.getSo_dien_thoai(),
            entity.getEmail(),
            entity.getVai_tro(),
            entity.getNgay_tao()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(User entity) {
        Object[] values = {
            entity.getTen_dang_nhap(),
            entity.getMat_khau(),
            entity.getHo_ten(),
            entity.getSo_dien_thoai(),
            entity.getEmail(),
            entity.getVai_tro(),
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
    public List<User> findAll() {
        return XQuery.getBeanList(User.class, findAllSql);
    }

    @Override
    public User findById(String id) {
        return XQuery.getSingleBean(User.class, findByIdSql, id);
    }
    
    @Override
    public User findByUsername(String username){
        return XQuery.getSingleBean(User.class, findByUsername, username);
    }
}
