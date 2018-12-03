package Crud;

import Utils.EMF;
import model.Czesne;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class CzesneRepo {
    public void save(Czesne czesne) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(czesne);
        em.getTransaction().commit();
        em.close();
    }

    public Czesne getById(Long id) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Czesne czesne = em.find(Czesne.class, id);
        em.close();
        return czesne;
    }

    public List<Czesne> getAll() {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Czesne  c", Czesne.class);
        List<Czesne> resultList = query.getResultList();
        return resultList;

    }

    public void remove(Czesne czesne) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(czesne);
        em.getTransaction().commit();
        em.close();
    }


}
