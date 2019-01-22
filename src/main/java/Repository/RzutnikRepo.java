package Repository;

import Utils.EMF;
import model.Rzutnik;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class RzutnikRepo {

    public List<Rzutnik> getRzutniksWhereRzutnikInSalaIsNull() {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Rzutnik c where c.id not in (select s.rzutnik from Sala s)", Rzutnik.class);
        List<Rzutnik> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public List<Rzutnik> getByModel(String model) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Rzutnik c where c.model = :modelName", Rzutnik.class)
                .setParameter("modelName", model);
        List<Rzutnik> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public void save(Rzutnik rzutnik) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(rzutnik);
        em.getTransaction().commit();
        em.close();
    }

    public Rzutnik getById(Long id) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Rzutnik rzutnik = em.find(Rzutnik.class, id);
        em.close();
        return rzutnik;
    }

    public List<Rzutnik> getAll() {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Rzutnik c", Rzutnik.class);
        List<Rzutnik> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public void remove(Rzutnik rzutnik) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.remove(rzutnik);
        em.getTransaction().commit();
        em.close();
    }
}
