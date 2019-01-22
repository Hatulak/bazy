package Repository;

import Utils.EMF;
import lombok.extern.java.Log;
import model.Nauczyciel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Log
public class NauczycielRepo {
    public void save(Nauczyciel nauczyciel) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(nauczyciel);
        em.getTransaction().commit();
        em.close();
    }

    public Nauczyciel getById(Long id) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Nauczyciel nauczyciel = em.find(Nauczyciel.class, id);
        em.close();
        return nauczyciel;
    }

    public List<Nauczyciel> getAll() {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Nauczyciel c", Nauczyciel.class);
        List<Nauczyciel> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public void remove(Nauczyciel nauczyciel) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(nauczyciel);
        em.getTransaction().commit();
        em.close();
    }
}
