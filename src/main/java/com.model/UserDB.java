package com.model;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDB {
    EntityManager em = DBUtil.getEmf().createEntityManager();

    public Boolean findByEmail(String email) {
        try {
            TypedQuery<User> tq = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            tq.setParameter("email", email);
            List<User> u = tq.getResultList();

            if(!u.isEmpty())
                return true;

        }catch(Exception e) {

        }finally { em.close(); }

        return false;
    }
    public Boolean addUser(User user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        } finally { em.close(); }
        return false;
    }

    public User login(String email, String password) {
        try {
            TypedQuery<User> tq = em.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class);
            tq.setParameter("email", email);
            tq.setParameter("password", password);
            List<User> u = tq.getResultList();

            if(!u.isEmpty())
                return u.get(0);

        }catch(Exception e) {

        }finally { em.close(); }

        return null;
    }
}
