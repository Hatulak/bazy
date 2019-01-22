package Repository;

import Utils.EMF;
import model.Sala;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class SalaRepo {
    public void update(Sala sala) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(sala);
        em.getTransaction().commit();
        em.close();
    }

    public void save(Sala sala) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(sala);
        em.getTransaction().commit();
        em.close();
    }

    public Sala getById(Long id) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Sala sala = em.find(Sala.class, id);
        em.close();
        return sala;
    }

    public List<Sala> getBySalaNumber(String number) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Sala c where c.numerSali = :numer", Sala.class)
                .setParameter("numer", number);
        List<Sala> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public List<Sala> getAll() {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Sala c", Sala.class);
        List<Sala> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public void remove(Sala sala) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Sala sala1 = em.find(Sala.class, sala.getId());
        em.remove(sala1);
        em.getTransaction().commit();
        em.close();
    }
}
