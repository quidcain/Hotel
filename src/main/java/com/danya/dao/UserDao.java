package com.danya.dao;

import com.danya.model.User;

public interface UserDao {
    void save(User user) throws DaoException;
    User findByName(String name) throws DaoException;
}
