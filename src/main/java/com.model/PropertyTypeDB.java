package com.model;

import javax.persistence.EntityManager;
import java.util.List;

public class PropertyTypeDB {
    EntityManager em = DBUtil.getEmf().createEntityManager();

    public List<PropertyType> getAll() {
        List<PropertyType> list;

        try {
            list = em.createQuery("SELECT p from PropertyType p", PropertyType.class).getResultList();

            if (list == null || list.isEmpty()) {
                list = null;
            }
        } catch (Exception e) {
            list = null;
        } finally { em.close(); }

        return list;
    }

}
