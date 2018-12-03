package Crud;

import Utils.EMF;
import model.Grupa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class GrupaRepo {
    public void save(Grupa grupa) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(grupa);
        em.getTransaction().commit();
        em.close();
    }

    public Grupa getById(Long id) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Grupa grupa = em.find(Grupa.class, id);
        em.close();
        return grupa;
    }

    public List<Grupa> getAll() {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Grupa  c", Grupa.class);
        List<Grupa> resultList = query.getResultList();
        return resultList;

    }

    public void remove(Grupa grupa) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(grupa);
        em.getTransaction().commit();
        em.close();
    }

}
