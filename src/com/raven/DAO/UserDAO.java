package com.raven.DAO;

import com.raven.entity.User;

public interface UserDAO extends CrudDAO<User, String>{
    User findByUsername(String username);
}
