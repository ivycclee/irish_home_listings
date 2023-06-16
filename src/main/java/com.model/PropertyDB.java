package com.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class PropertyDB implements Repository{
    EntityManager em = DBUtil.getEmf().createEntityManager();
    @Override
    public List<Property> getAll() {
        List<Property> list;

        try {
            list = em.createQuery("SELECT p from Property p", Property.class).getResultList();

            if (list == null || list.isEmpty()) {
                list = null;
            }
        } catch (Exception e) {
            list = null;
        } finally { em.close(); }

        return list;
    }

    @Override
    public Optional<Property> getByID(int id) {
        Optional<Property> p = Optional.empty();
        try {
            p = Optional.of(em.find(Property.class, id));
        }catch (Exception e) {
            System.out.println(e);
        } finally { em.close(); }

        return p;
    }

    public List<Property> getPropertyByAgentID(int agentID) {
        List<Property> list;

        try {
            TypedQuery<Property> tq = em.createQuery("SELECT p FROM Property p WHERE p.agentId = :agentID", Property.class);
            tq.setParameter("agentID", agentID);

            list = tq.getResultList();
        }catch (Exception e) {
            list = null;
        } finally { em.close(); }

        return list;
    }

    public void updateProperty(Property p) {
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        }catch (Exception e) {
            System.out.println(e);
        } finally { em.close(); }
    }

    public Boolean deleteProperty(Property p) {
        try {
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
            return true;
        }catch (Exception e) {
            System.out.println(e);
        } finally { em.close(); }
        return false;
    }

    public Boolean deleteProperty(int propertyID) {
        try {
            Property p = em.find(Property.class, propertyID);
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
            return true;

        }catch (Exception e) {
            System.out.println(e);
            return false;
        } finally { em.close(); }
    }

    public Boolean insertProperty(Property p) {
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return true;
        }catch (Exception e) {
            System.out.println(e);
        } finally { em.close(); }
        return false;
    }


}
