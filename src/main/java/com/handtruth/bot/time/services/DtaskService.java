package com.handtruth.bot.time.services;

import java.util.List;

import org.telegram.telegrambots.meta.api.objects.User;

import com.handtruth.bot.time.dao.DtaskDao;
import com.handtruth.bot.time.dao.DtaskDaoImpl;
import com.handtruth.bot.time.entities.Dtask;

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
            addById(user);
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
            addById(user);
        } else {
            dtask.setCount(0);
            dtaskDao.update(dtask);
        }
    }

    public void addById(User user) {
        Dtask dtask = new Dtask(user.getId(), 0, user.getUserName());
        dtaskDao.save(dtask);
    }

    public List<Dtask> all() {
        return dtaskDao.findAll();
    }

    public int getCountById(User user) {
        Dtask dtask = dtaskDao.findById(user.getId());
        if (dtask == null) {
            addById(user);
            return 0;
        } else {
            return dtask.getCount();
        }
    }
}
