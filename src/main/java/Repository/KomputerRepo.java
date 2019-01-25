package Repository;

import Utils.EMF;
import model.Komputer;
import model.Sala;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class KomputerRepo {

    public void update(Komputer komputer) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(komputer);
        em.getTransaction().commit();
        em.close();
    }

    public List<Komputer> getBySala(Sala sala) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Komputer c where c.sala = :sala", Komputer.class)
                .setParameter("sala", sala);
        List<Komputer> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public List<Komputer> getAllKomputersWhereSalaIdIsNull() {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Komputer c where c.sala is null", Komputer.class);
        List<Komputer> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public Long save(Komputer komputer) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(komputer);
        em.flush();
        Long konputerID = komputer.getId();
        em.getTransaction().commit();
        em.close();
        return konputerID;
    }

    public Komputer getById(Long id) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Komputer komputer = em.find(Komputer.class, id);
        em.close();
        return komputer;
    }

    public List<Komputer> getAll() {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Komputer  c", Komputer.class);
        List<Komputer> resultList = query.getResultList();
        em.close();
        return resultList;

    }

    public void remove(Komputer komputer) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Komputer komputer1 = em.find(Komputer.class, komputer.getId());
        em.remove(komputer1);
        em.getTransaction().commit();
        em.close();
    }
}
