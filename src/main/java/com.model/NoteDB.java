package com.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class NoteDB {
    EntityManager em = DBUtil.getEmf().createEntityManager();

    public List<Note> getAll() {
        List<Note> list;

        try {
            list = em.createQuery("SELECT n from Note n", Note.class).getResultList();

            if (list == null || list.isEmpty()) {
                list = null;
            }
        } catch (Exception e) {
            list = null;
        } finally { em.close(); }

        return list;
    }
    public Note findNote(int userID, int listingNum) {
        Note note;

        try {
            TypedQuery<Note> tq = em.createQuery("SELECT n FROM Note n WHERE n.userId = :userID AND n.listingNum = :listingNum", Note.class);
            tq.setParameter("userID", userID);
            tq.setParameter("listingNum", listingNum);

            note = tq.getSingleResult();

            if (note == null) {
                note = null;
            }
        }catch (Exception e) {
            System.out.println(e);
            note = null;
        } finally { em.close(); }

        return note;
    }

    public void editNote(Note n) {
        EntityManager emtest = DBUtil.getEmf().createEntityManager();

        EntityTransaction trans = emtest.getTransaction();

        try {
            trans.begin();
            emtest.merge(n);
            trans.commit();
        }catch (Exception e) {
            System.out.println(e);
        } finally { em.close(); }

    }

    public void addNote(Note n) {
        try {
            em.getTransaction().begin();
            em.persist(n);
            em.getTransaction().commit();
        }catch (Exception e) {
            System.out.println(e);
            em.getTransaction().rollback();
        } finally { em.close(); }
    }

    public Boolean deleteNoteByListingNum(int listingNum){
        try{
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Note n WHERE n.listingNum = :listingNum").setParameter("listingNum", listingNum).executeUpdate();
            em.getTransaction().commit();
            return true;

        }catch (Exception e){
            System.out.println(e);
        }finally { em.close(); }

        return false;
    }
}
