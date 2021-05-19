package com.epam.project.db.dao.impl;

import com.epam.project.db.dao.BaseDao;
import com.epam.project.entities.ReaderRoom;

import java.util.List;

public class ReaderRoomDoomImpl implements BaseDao<Integer,ReaderRoom> {
    @Override
    public List<ReaderRoom> findAllEntities() {
        return null;
    }

    @Override
    public ReaderRoom findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean create(ReaderRoom entity) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public ReaderRoom update(ReaderRoom entity) {
        return null;
    }
}
