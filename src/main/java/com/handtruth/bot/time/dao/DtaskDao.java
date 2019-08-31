package com.handtruth.bot.time.dao;


import java.util.List;

import com.handtruth.bot.time.entities.Dtask;

public interface DtaskDao {
    Dtask findById(long id);
    void save(Dtask dtask);
    void update(Dtask dtask);
    void delete(Dtask dtask);
    List<Dtask> findAll();
}
