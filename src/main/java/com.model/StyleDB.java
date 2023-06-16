package com.model;

import javax.persistence.EntityManager;
import java.util.List;

public class StyleDB {
    EntityManager em = DBUtil.getEmf().createEntityManager();

    public List<Style> getAll() {
        List<Style> list;

        try {
            list = em.createQuery("SELECT s from Style s", Style.class).getResultList();

            if (list == null || list.isEmpty()) {
                list = null;
            }
        } catch (Exception e) {
            list = null;
        } finally { em.close(); }

        return list;
    }
}
