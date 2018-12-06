package Repository;

import Utils.EMF;
import model.Miasto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class MiastoRepo {
    public void save(Miasto miasto) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(miasto);
        em.getTransaction().commit();
        em.close();
    }

    public Miasto getById(Long id) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Miasto miasto = em.find(Miasto.class, id);
        em.close();
        return miasto;
    }

    public List<Miasto> getAll() {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Miasto c", Miasto.class);
        List<Miasto> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public void remove(Miasto miasto) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(miasto);
        em.getTransaction().commit();
        em.close();
    }
}
