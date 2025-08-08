package com.raven.util;

import com.raven.entity.User;

public class XAuth {
    public static User user = null;

    public static void clear() {
        user = null;
    }

    public static boolean isLogin() {
        return user != null;
    }
    
    public static boolean isAdmin() {
        return isLogin() && "QUAN_TRI".equalsIgnoreCase(user.getVai_tro());
    }

    public static boolean isGiangVien() {
        return isLogin() && "GIAO_VIEN".equalsIgnoreCase(user.getVai_tro());
    }

    public static boolean isHocVien() {
        return isLogin() && "HOC_VIEN".equalsIgnoreCase(user.getVai_tro());
    }
}
