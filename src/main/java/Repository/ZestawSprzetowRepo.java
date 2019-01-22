package Repository;

import Utils.EMF;
import model.ZestawSprzetow;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class ZestawSprzetowRepo {
    public void save(ZestawSprzetow zestawSprzetow) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(zestawSprzetow);
        em.getTransaction().commit();
        em.close();
    }

    public ZestawSprzetow getById(Long id) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        ZestawSprzetow zestawSprzetow = em.find(ZestawSprzetow.class, id);
        em.close();
        return zestawSprzetow;
    }

    public List<ZestawSprzetow> getAll() {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from ZestawSprzetow c", ZestawSprzetow.class);
        List<ZestawSprzetow> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public void remove(ZestawSprzetow zestawSprzetow) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(zestawSprzetow);
        em.getTransaction().commit();
        em.close();
    }
}
