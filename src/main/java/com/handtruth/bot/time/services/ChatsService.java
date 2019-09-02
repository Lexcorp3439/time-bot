package com.handtruth.bot.time.services;

import java.util.List;

import com.handtruth.bot.time.dao.ChatsDao;
import com.handtruth.bot.time.dao.ChatsDaoImpl;
import com.handtruth.bot.time.entities.Chats;

public class ChatsService {
    private static final ChatsService INSTANCE = new ChatsService();
    private ChatsDao chatsDao = new ChatsDaoImpl();

    private ChatsService() {
    }

    public static ChatsService getInstance() {
        return INSTANCE;
    }

    public void save(Chats chats) {
        chatsDao.save(chats);
    }

    public void delete(long id) {
        delete(new Chats(id));
    }

    public void delete(Chats chats) {
        chatsDao.delete(chats);
    }

    public List<Chats> all() {
        return chatsDao.findAll();
    }

    public void deleteAll() {
        chatsDao.deleteAll();
    }

    public boolean hasChat(long id) {
        return chatsDao.findById(id) != null;
    }

}
