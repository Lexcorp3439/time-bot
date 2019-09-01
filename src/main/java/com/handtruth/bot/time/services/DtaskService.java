package com.handtruth.bot.time.services;

import java.util.List;

import org.telegram.telegrambots.meta.api.objects.User;

import com.handtruth.bot.time.dao.DtaskDao;
import com.handtruth.bot.time.dao.DtaskDaoImpl;
import com.handtruth.bot.time.entities.Dtask;
import com.handtruth.bot.time.entities.Users;

public class DtaskService {
    private static volatile DtaskService INSTANCE = new DtaskService();
    private DtaskDao dtaskDao = new DtaskDaoImpl();

    private DtaskService() {
    }

    public static DtaskService getINSTANCE() {
        return INSTANCE;
    }

    public void updateCountById(User user) {
        Dtask dtask = dtaskDao.findById(user.getId());
        if (dtask == null) {
            addByUser(user);
        } else {
            int count = dtask.getCount();
            if (count != 5) {
                dtask.setCount(++count);
            }
            dtaskDao.update(dtask);
        }
    }

    public void clearCountById(User user) {
        Dtask dtask = dtaskDao.findById(user.getId());
        if (dtask == null) {
            addByUser(user);
        } else {
            dtask.setCount(0);
            dtaskDao.update(dtask);
        }
    }

    private void addByUser(User user) {
        Dtask dtask = new Dtask(0, new Users(user.getId(), user.getUserName()));
        dtaskDao.save(dtask);
    }

    public List<Dtask> all() {
        return dtaskDao.findAll();
    }

    public int getCountById(User user) {
        Dtask dtask = dtaskDao.findById(user.getId());
        if (dtask == null) {
            addByUser(user);
            return 0;
        } else {
            return dtask.getCount();
        }
    }

    public void createDtask(Dtask dtask) {
        dtaskDao.save(dtask);
    }

    public void dropDtask(Dtask dtask) {
        dtaskDao.delete(dtask);
    }

    public void dropDtaskByUserId(long userId) {
        dtaskDao.deleteByUserId(userId);
    }

    public void dropAll() {
        dtaskDao.deleteAll();
    }
}
