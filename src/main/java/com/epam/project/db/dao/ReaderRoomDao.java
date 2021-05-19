package com.epam.project.db.dao;

import com.epam.project.entityes.ReaderRoom;

import java.util.List;

public interface ReaderRoomDao extends BaseDao<Integer, ReaderRoom> {
    @Override
    List<ReaderRoom> findAllEntities();

    @Override
    ReaderRoom findEntityById(Integer id);

    @Override
    boolean create(ReaderRoom entity);

    @Override
    boolean delete(Integer id);

    @Override
    ReaderRoom update(ReaderRoom entity);
}
