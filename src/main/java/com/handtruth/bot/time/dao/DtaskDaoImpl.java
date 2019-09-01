package com.handtruth.bot.time.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.handtruth.bot.time.entities.Dtask;
import com.handtruth.bot.time.utils.HibernateSessionFactoryUtil;

import afu.org.checkerframework.checker.oigj.qual.Modifier;

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

    public void deleteAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("DELETE FROM Users");
        Transaction tx1 = session.beginTransaction();
        int result = query.executeUpdate();
        session.flush();
        tx1.commit();
        session.close();
    }


    public void deleteByUserId(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("DELETE FROM Users where id = :user_id ");
        query.setParameter("user_id", id);
        Transaction tx1 = session.beginTransaction();
        int result = query.executeUpdate();
        session.flush();
        tx1.commit();
        session.close();

//        query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Dtask> findAll() {
        List dtasks = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("FROM Dtask").list();
        return (List<Dtask>) dtasks;
    }
}
