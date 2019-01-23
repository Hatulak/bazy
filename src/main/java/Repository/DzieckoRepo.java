package Repository;

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
        Dziecko toRemove = em.find(Dziecko.class, dziecko.getId());
        em.remove(toRemove);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Dziecko dziecko) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(dziecko);
        em.getTransaction().commit();
        em.close();
    }
}
