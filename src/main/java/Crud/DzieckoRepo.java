package Crud;

import Utils.EMF;
import model.Dziecko;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class DzieckoRepo {
    public void save(Dziecko dziecko) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(dziecko);
        em.getTransaction().commit();
        em.close();
    }

    public Dziecko getById(Long id) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Dziecko dziecko = em.find(Dziecko.class, id);
        em.close();
        return dziecko;
    }

    public List<Dziecko> getAll() {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select d from Dziecko d", Dziecko.class);
        List<Dziecko> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public void remove(Dziecko dziecko) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(dziecko);
        em.getTransaction().commit();
        em.close();
    }
}
