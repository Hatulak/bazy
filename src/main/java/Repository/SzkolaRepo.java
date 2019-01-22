package Repository;

import Utils.EMF;
import model.Szkola;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class SzkolaRepo {
    public void save(Szkola szkola) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(szkola);
        em.getTransaction().commit();
        em.close();
    }

    public Szkola getById(Long id) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Szkola szkola = em.find(Szkola.class, id);
        em.close();
        return szkola;
    }

    public List<Szkola> getByName(String name) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Szkola c where c.nazwa = :schoolName", Szkola.class)
                .setParameter("schoolName", name);
        List<Szkola> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public List<Szkola> getAll() {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Szkola c", Szkola.class);
        List<Szkola> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public void remove(Szkola szkola) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(szkola);
        em.getTransaction().commit();
        em.close();
    }
}
