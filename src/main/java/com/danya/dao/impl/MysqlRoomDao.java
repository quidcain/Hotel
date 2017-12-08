package com.danya.dao.impl;

import com.danya.dao.DaoException;
import com.danya.dao.RoomDao;
import com.danya.model.Room;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlRoomDao implements RoomDao {
    private static String SAVE_QUERY =
            "INSERT INTO room (size, sea_viewable, free_minibar, description_en, description_ru) " +
                    "VALUES (?, ?, ?, ?, ?);";
    private static String UPDATE_QUERY = "UPDATE room SET userId = ? WHERE id = ?;";
    private static String FIND_ALL_QUERY = "SELECT * FROM user;";
    private static String FIND_BY_USER_ID = "SELECT * FROM user WHERE user_id=?;";
    private DataSource dataSource;

    public MysqlRoomDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Room room) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_QUERY);
            preparedStatement.setInt(1, room.getSize().ordinal());
            preparedStatement.setBoolean(2, room.isSeaViewable());
            preparedStatement.setBoolean(3, room.isFreeMinibar());
            preparedStatement.setString(3, room.getEnDescription());
            preparedStatement.setString(3, room.getRuDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(long id, long userId) throws DaoException {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Room> findAll() {
        try(Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
            return roomsFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Room> findByUserId(long userId) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_ID);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return roomsFromResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Room> roomsFromResultSet(ResultSet resultSet) throws SQLException {
        List<Room> rooms = new ArrayList<>();
        while(resultSet.next()) {
            Room room = new Room();
            room.setId(resultSet.getLong("id"));
            room.setSize(Room.Size.valueOf(resultSet.getInt("size")));
            room.setSeaViewable(resultSet.getBoolean("sea_viewable"));
            room.setFreeMinibar(resultSet.getBoolean("free_minibar"));
            room.setEnDescription(resultSet.getString("description_en"));
            room.setRuDescription(resultSet.getString("description_ru"));
            rooms.add(room);
        }
        return rooms;
    }
}
