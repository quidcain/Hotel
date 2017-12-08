package com.danya.dao;

import com.danya.model.Room;

import java.util.List;

public interface RoomDao {
    void save(Room room);
    void update(long id, long userId) throws DaoException;
    List<Room> findAll();
    List<Room> findByUserId(long userId);
}
