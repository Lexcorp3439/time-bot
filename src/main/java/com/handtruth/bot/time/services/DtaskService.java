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

    public static DtaskService getInstance() {
        return INSTANCE;
    }

    public Dtask find(User user) {
        Dtask dtask = dtaskDao.findById(user.getId());
        if (dtask == null) {
            dtask = addByUser(user);
        }
        return dtask;
    }

    public void update(User user, Dtask dtaskK) {
        Dtask dtask = dtaskDao.findById(user.getId());
        if (dtask == null) {
            addByUser(user);
        }
        dtask.setMask(dtaskK);
        dtaskDao.update(dtask);
    }

    public void clearAll() {
        List<Dtask> dtasks = dtaskDao.findAll();
        for (Dtask d: dtasks) {
            d.clear();
            dtaskDao.update(d);
        }
    }

    private Dtask addByUser(User user) {
        Dtask dtask = new Dtask(new Users(user.getId(), user.getUserName()));
        dtaskDao.save(dtask);
        return dtask;
    }

    public List<Dtask> all() {
        return dtaskDao.findAll();
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
