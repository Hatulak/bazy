package Repository;

import Utils.EMF;
import model.Rodzic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class RodzicRepo {
    public void save(Rodzic rodzic) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(rodzic);
        em.getTransaction().commit();
        em.close();
    }

    public Rodzic getById(Long id) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Rodzic rodzic = em.find(Rodzic.class, id);
        em.close();
        return rodzic;
    }

    public List<Rodzic> getAll() {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Rodzic c", Rodzic.class);
        List<Rodzic> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public void remove(Rodzic rodzic) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(rodzic);
        em.getTransaction().commit();
        em.close();
    }
}
