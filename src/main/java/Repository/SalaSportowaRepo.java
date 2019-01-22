package Repository;

import Utils.EMF;
import model.SalaSportowa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class SalaSportowaRepo {
    public void save(SalaSportowa salaSportowa) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(salaSportowa);
        em.getTransaction().commit();
        em.close();
    }

    public SalaSportowa getById(Long id) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        SalaSportowa salaSportowa = em.find(SalaSportowa.class, id);
        em.close();
        return salaSportowa;
    }

    public List<SalaSportowa> getAll() {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from SalaSportowa c", SalaSportowa.class);
        List<SalaSportowa> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public void remove(SalaSportowa salaSportowa) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(salaSportowa);
        em.getTransaction().commit();
        em.close();
    }
}
