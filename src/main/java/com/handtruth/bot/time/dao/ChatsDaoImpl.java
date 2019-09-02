package com.handtruth.bot.time.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.handtruth.bot.time.entities.Chats;
import com.handtruth.bot.time.entities.Dtask;
import com.handtruth.bot.time.utils.HibernateSessionFactoryUtil;

public class ChatsDaoImpl implements ChatsDao{
    public Chats findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Chats.class, id);
    }

    public void save(Chats chats) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(chats);
        tx1.commit();
        session.close();
    }

    public void delete(Chats chats) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(chats);
        tx1.commit();
        session.close();
    }

    @SuppressWarnings("JpaQlInspection")
    public void deleteAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("DELETE FROM Chats");
        Transaction tx1 = session.beginTransaction();
        int result = query.executeUpdate();
        session.flush();
        tx1.commit();
        session.close();
    }

    @SuppressWarnings({"unchecked", "JpaQlInspection"})
    public List<Chats> findAll() {
        List chats = HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("FROM Chats").list();
        return (List<Chats>) chats;
    }

}
