package com.handtruth.bot.time.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.handtruth.bot.time.entities.Dtask;
import com.handtruth.bot.time.utils.HibernateSessionFactoryUtil;

public class DtaskDaoImpl implements DtaskDao{
    public Dtask findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Dtask.class, id);
    }

    public void save(Dtask dtask) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(dtask);
        tx1.commit();
        session.close();
    }

    public void update(Dtask dtask) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(dtask);
        tx1.commit();
        session.close();
    }

    public void delete(Dtask dtask) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(dtask);
        tx1.commit();
        session.close();
    }

    public List<Dtask> findAll() {
        List dtasks = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("FROM dtasks").list();
        return dtasks;
    }
}
