package com.model;

import javax.persistence.EntityManager;
import java.util.List;

public class GarageTypeDB {
    EntityManager em = DBUtil.getEmf().createEntityManager();

    public List<GarageType> getAll() {
        List<GarageType> list;

        try {
            list = em.createQuery("SELECT g from GarageType g", GarageType.class).getResultList();

            if (list == null || list.isEmpty()) {
                list = null;
            }
        } catch (Exception e) {
            list = null;
        } finally { em.close(); }

        return list;
    }
}
