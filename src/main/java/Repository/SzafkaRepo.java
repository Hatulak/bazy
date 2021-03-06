package Repository;

import Utils.EMF;
import model.Szafka;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class SzafkaRepo {

    public void update(Szafka szafka) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(szafka);
        em.getTransaction().commit();
        em.close();
    }

    public void save(Szafka szafka) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(szafka);
        em.getTransaction().commit();
        em.close();
    }

    public Szafka getById(Long id) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Szafka szafka = em.find(Szafka.class, id);
        em.close();
        return szafka;
    }

    public List<Szafka> getAll() {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Szafka c", Szafka.class);
        List<Szafka> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public void remove(Szafka szafka) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Szafka szafka1 = em.find(Szafka.class, szafka.getId());
        em.remove(szafka1);
        em.getTransaction().commit();
        em.close();
    }

    public List<Szafka> getEmptySzafkas() {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Szafka c where c.dziecko.id is null", Szafka.class);
        List<Szafka> resultList = query.getResultList();
        em.close();
        return resultList;
    }

    public Szafka getDzieckoSzafka(Long dzieckoID) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Szafka c where c.dziecko.id  = :pDziecko", Szafka.class).setParameter("pDziecko", dzieckoID);
        List<Szafka> result = query.getResultList();
        em.close();
        if (result.size() == 0) return null;
        else return result.get(0);
    }
}
