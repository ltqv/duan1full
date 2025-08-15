package com.raven.DAO;

import com.raven.entity.User;
import java.util.List;

public interface UserDAO extends CrudDAO<User, String>{
    User findByUsername(String username);
    List<User> findStudent();
}
