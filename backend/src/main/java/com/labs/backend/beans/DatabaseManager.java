package com.labs.backend.beans;

import com.labs.backend.entities.Dot;
import com.labs.backend.entities.User;

import javax.ejb.Singleton;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DatabaseManager {
    @PersistenceContext(unitName = "dotUnit")
    private EntityManager dotEntityManager;

    @PersistenceContext(unitName = "userUnit")
    private EntityManager userEntityManager;

    synchronized public boolean addDot(Dot dot) {
        try {
            dotEntityManager.getTransaction().begin();
            dotEntityManager.persist(dot);
            dotEntityManager.getTransaction().commit();
            return true;
        } catch (Exception e){
            dotEntityManager.getTransaction().rollback();
            return false;
        }
    }

    synchronized public boolean addUser(User user) {
        try {
            userEntityManager.getTransaction().begin();
            userEntityManager.persist(user);
            userEntityManager.getTransaction().commit();
            return true;
        } catch (Exception e){
            userEntityManager.getTransaction().rollback();
            return false;
        }
    }

    synchronized public User getUser(String login) {
        try {
            userEntityManager.getTransaction().begin();
            User user = userEntityManager.find(User.class, login);
            userEntityManager.getTransaction().commit();
            return user;
        } catch (Exception e) {
            userEntityManager.getTransaction().rollback();
            return null;
        }
    }

    synchronized public List<Dot> getDotList() {
        try {
            dotEntityManager.getTransaction().begin();
            Query query = dotEntityManager.createQuery("SELECT o FROM Dot o");
            return query.getResultList();
        } catch (Exception e) {
            dotEntityManager.getTransaction().rollback();
            return new ArrayList<>();
        }
    }

    synchronized public List<User> getUserList() {
        try {
            userEntityManager.getTransaction().begin();
            Query query = userEntityManager.createQuery("SELECT o FROM User o");
            return query.getResultList();
        } catch (Exception e) {
            userEntityManager.getTransaction().rollback();
            return new ArrayList<>();
        }
    }

    synchronized public boolean clearDotList(String login) {
        try {
            dotEntityManager.getTransaction().begin();
            Query query = dotEntityManager.createQuery("DELETE FROM Dot o WHERE o.login=:login");
            query.setParameter("login", login);
            query.executeUpdate();
            dotEntityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            dotEntityManager.getTransaction().rollback();
            return false;
        }
    }

    synchronized public boolean clearUserList() {
        try {
            userEntityManager.getTransaction().begin();
            Query query = userEntityManager.createQuery("DELETE FROM User");
            query.executeUpdate();
            userEntityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            userEntityManager.getTransaction().rollback();
            return false;
        }
    }


}
