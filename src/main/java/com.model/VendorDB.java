package com.model;

import javax.persistence.EntityManager;
import java.util.List;

public class VendorDB {
    EntityManager em = DBUtil.getEmf().createEntityManager();

    public List<Vendor> getAll() {
        List<Vendor> list;

        try {
            list = em.createQuery("SELECT v from Vendor v", Vendor.class).getResultList();

            if (list == null || list.isEmpty()) {
                list = null;
            }
        } catch (Exception e) {
            list = null;
        } finally { em.close(); }

        return list;
    }

    public List<Vendor> getByAgentId(int agentId) {
        List<Vendor> list;

        try {
            list = em.createQuery("SELECT DISTINCT v from Vendor v WHERE v.agentId = :agentId", Vendor.class)
                    .setParameter("agentId", agentId)
                    .getResultList();

            if (list == null || list.isEmpty()) {
                list = null;
            }
        } catch (Exception e) {
            list = null;
        } finally { em.close(); }

        return list;
    }

    public Vendor getById(int id) {
        Vendor vendor;

        try {
            vendor = em.find(Vendor.class, id);

            if (vendor == null) {
                vendor = null;
            }
        } catch (Exception e) {
            vendor = null;
        } finally { em.close(); }

        return vendor;
    }

    public void updateVendor(Vendor v) {
        try {
            em.getTransaction().begin();
            em.merge(v);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);;
        } finally { em.close(); }
    }
}
