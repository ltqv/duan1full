package com.raven.DAOImpl;

import com.raven.DAO.TuitionfeesDAO;
import com.raven.entity.Tuitionfees;
import com.raven.util.XJdbc;
import com.raven.util.XQuery;
import java.util.List;

public class TuitionfeesDAOImpl implements TuitionfeesDAO {

    String createSql = "INSERT INTO HocPhi(id_hoc_vien, hoc_ky, nam_hoc, tong_hoc_phi, da_dong, trang_thai, ngay_dong_cuoi) VALUES(?, ?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE HocPhi SET id_hoc_vien=?, hoc_ky=?, nam_hoc=?, tong_hoc_phi=?, da_dong=?, trang_thai=?, ngay_dong_cuoi=? WHERE id_hoc_phi=?";
    String deleteSql = "DELETE FROM HocPhi WHERE id_hoc_phi=?";
    String findAllSql = "SELECT * FROM HocPhi";
    String findByIdSql = "SELECT * FROM HocPhi WHERE id_hoc_phi=?";

    @Override
    public Tuitionfees create(Tuitionfees entity) {
        Object[] values = {
            entity.getId_hoc_vien(),
            entity.getHoc_ky(),
            entity.getNam_hoc(),
            entity.getTong_hoc_phi(),
            entity.getDa_dong(),
            entity.isTrang_thai(),
            entity.getNgay_dong_cuoi()
        };

        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(Tuitionfees entity) {
        Object[] values = {
            entity.getId_hoc_vien(),
            entity.getHoc_ky(),
            entity.getNam_hoc(),
            entity.getTong_hoc_phi(),
            entity.getDa_dong(),
            entity.isTrang_thai(),
            entity.getNgay_dong_cuoi(),
            entity.getId_hoc_phi()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String id) {
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<Tuitionfees> findAll() {
        return XQuery.getBeanList(Tuitionfees.class, findAllSql);
    }

    @Override
    public Tuitionfees findById(String id) {
        return XQuery.getSingleBean(Tuitionfees.class, findByIdSql, id);
    }
}
