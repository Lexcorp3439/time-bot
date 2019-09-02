package com.handtruth.bot.time.dao;

import java.util.List;

import com.handtruth.bot.time.entities.Chats;

public interface ChatsDao {
    Chats findById(long id);
    void save(Chats chat);
    void delete(Chats chat);
    void deleteAll();
    List<Chats> findAll();
}
