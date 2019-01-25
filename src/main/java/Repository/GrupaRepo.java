package Repository;

import Utils.EMF;
import model.Grupa;
import model.Nauczyciel;
import model.Sala;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class GrupaRepo {
    public void save(Grupa grupa) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Nauczyciel nauczyciel = em.find(Nauczyciel.class, grupa.getNauczyciel().getId());
        grupa.setNauczyciel(nauczyciel);
        Sala sala = em.find(Sala.class, grupa.getSala().getId());
        grupa.setSala(sala);
        em.persist(grupa);
        em.getTransaction().commit();
        em.close();
    }

    public Grupa getById(Long id) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Grupa grupa = em.find(Grupa.class, id);
        em.close();
        return grupa;
    }

    public List<Grupa> getAll() {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("select c from Grupa  c", Grupa.class);
        List<Grupa> resultList = query.getResultList();
        em.close();
        return resultList;

    }

    public void remove(Grupa grupa) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Grupa toRemove = em.find(Grupa.class, grupa.getId());
        em.remove(toRemove);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Grupa grupa) {
        EntityManagerFactory entityManagerFactory = EMF.getEntityManagerFactory();
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(grupa);
        em.getTransaction().commit();
        em.close();
    }

}
