package com.model;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


public class ArchiveDB {
    EntityManager em = DBUtil.getEmf().createEntityManager();

    //set this to bool to check that the transaction is successful before
    //deleting the record from property table
    public Boolean archiveProperty(int propertyID) {
        try {
            //find property by id
            Property p = em.find(Property.class, propertyID);
            System.out.println(p);

            //turn it into an archive object
            Archive a = new Archive(p.getId(), p.getStreet(), p.getCity(), p.getListingNum(), p.getStyleId(), p.getTypeId(), p.getBedrooms(), p.getBathrooms(), p.getSquarefeet(), p.getBerRating(), p.getDescription(), p.getLotsize(), p.getGaragesize(), p.getGarageId(), p.getAgentId(), p.getPrice(), p.getDateAdded(), p.getVendorId());

            //Archive a = Archive.class.cast(p);
            System.out.println(a);

            //insert into archive table
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();

            return true;

        }catch(Exception e) {
            return false;
        }finally { em.close(); }
    }

    public List<Archive> getArchivedByAgentID(int agentID) {
        List<Archive> list;

        try {
            TypedQuery<Archive> tq = em.createQuery("SELECT a FROM Archive a WHERE a.agentId = :agentID", Archive.class);
            tq.setParameter("agentID", agentID);

            list = tq.getResultList();
        }catch (Exception e) {
            list = null;
        } finally { em.close(); }

        return list;
    }

    public Archive findByListingNum(int listingNum) {
        //check if property exists in archive table using listing number
        Archive a;

        try {
            TypedQuery<Archive> tq = em.createQuery("SELECT a FROM Archive a WHERE a.listingNum = :listingNum", Archive.class);
            tq.setParameter("listingNum", listingNum);

            a = tq.getSingleResult();
        }catch (Exception e) {
            a = null;
        } finally { em.close(); }

        return a;
    }
    public Boolean removeFromArchive(int archiveId){
        try {
            Archive a = em.find(Archive.class, archiveId);
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
            return true;
        }catch (Exception e) {
            System.out.println(e);
            return false;
        }finally { em.close(); }
    }



}
