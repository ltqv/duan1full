package com.raven.DAOImpl;

import com.raven.DAO.Form_HometeacherDAO;
import com.raven.entity.Form_Hometeacher;
import com.raven.util.XQuery;
import java.util.List;

public class Form_HometeacherDAOImpl implements Form_HometeacherDAO {

    String findStudent = "SELECT \n"
            + "    n.id AS id_nguoi_dung, \n"
            + "    n.ho_ten, \n"
            + "    n.so_dien_thoai, \n"
            + "    n.email, \n"
            + "    n.vai_tro, \n"
            + "    h.id_lop, \n"
            + "    h.id_hoc_vien\n"
            + "FROM NguoiDung n\n"
            + "JOIN HocVienTrongLop h ON n.id = h.id_hoc_vien\n"
            + "WHERE n.vai_tro = ?;";

    @Override
    public List<Form_Hometeacher> findStudent() {
        return XQuery.getBeanList(Form_Hometeacher.class, findStudent, "HOC_VIEN");
    }
}
