package com.danya.dao.impl;

import com.danya.dao.DaoException;
import com.danya.dao.UserDao;
import com.danya.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlUserDao implements UserDao {
    private static String SAVE_QUERY = "INSERT INTO user (name, password) VALUES (?,?);";
    private static String FIND_BY_NAME_QUERY = "SELECT * FROM user WHERE name = ?;";
    private DataSource dataSource;

    public MysqlUserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User user) throws DaoException {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_QUERY);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }

    @Override
    public User findByName(String name) throws DaoException {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME_QUERY);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("passsword"));
                return user;
            } else {
                throw new DaoException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
