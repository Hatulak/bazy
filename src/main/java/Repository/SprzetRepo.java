package Repository;

import Utils.EMF;
import model.Sprzet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class SprzetRepo {
    public void save(Sprzet sprzet) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(sprzet);
        em.getTransaction().commit();
        em.close();
    }

    public Sprzet getById(Long id) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Sprzet sprzet = em.find(Sprzet.class, id);
        em.close();
        return sprzet;
    }

    public List<Sprzet> getAll() {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Sprzet c", Sprzet.class);
        List<Sprzet> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public void remove(Sprzet sprzet) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Sprzet toRemove = em.find(Sprzet.class, sprzet.getId());
        em.remove(toRemove);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Sprzet sprzet) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(sprzet);
        em.getTransaction().commit();
        em.close();
    }
}
