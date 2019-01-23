package Repository;

import Utils.EMF;
import model.Czesne;
import model.Dziecko;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class CzesneRepo {

    public void update(Czesne czesne) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(czesne);
        em.getTransaction().commit();
        em.close();
    }

    public List<Czesne> getByDziecko(Dziecko dziecko) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Czesne  c where c.dziecko = :pDziecko", Czesne.class)
                .setParameter("pDziecko", dziecko);
        List<Czesne> resultList = query.getResultList();
        return resultList;
    }


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
        Czesne czesne1 = em.find(Czesne.class, czesne.getId());
        em.remove(czesne1);
        em.getTransaction().commit();
        em.close();
    }


}
