package com.model;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AgentDB {
    EntityManager em = DBUtil.getEmf().createEntityManager();

    public Agent login(String email, String password) {
        try{
            TypedQuery<Agent> tq = em.createQuery("SELECT a FROM Agent a WHERE a.email = :email AND a.password = :password", Agent.class);
            tq.setParameter("email", email);
            tq.setParameter("password", password);
            List<Agent> a = tq.getResultList();

            if(!a.isEmpty())
                return a.get(0);

        }catch (Exception e) {
            System.out.println(e);
        } finally { em.close(); }

        return null;
    }

    public Agent getById(int id) {
        Agent a;
        try {
             a = em.find(Agent.class, id);

            if (a != null)
                return a;

            else
                a = null;
        }catch (Exception e) {
            System.out.println(e);
            a = null;
        } finally { em.close(); }

        return a;
    }
}
