package metube.repositories;

import metube.domain.entities.Tube;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class TubeRepositoryImpl implements TubeRepository {

    private EntityManager entityManager;

    public TubeRepositoryImpl() {
        this.entityManager = Persistence.createEntityManagerFactory("metube").createEntityManager();
    }

    @Override
    public Tube findByName(String name) {
        this.entityManager.getTransaction().begin();

        Tube tube = this.entityManager
                .createQuery("SELECT t FROM tubes as t WHERE t.name = :name", Tube.class)
                .setParameter("name", name)
                .getSingleResult();

        this.entityManager.getTransaction().commit();

        return tube;
    }

    @Override
    public Tube save(Tube tube) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(tube);
        this.entityManager.getTransaction().commit();

        return tube;
    }

    @Override
    public Tube findById(String id) {
        this.entityManager.getTransaction().begin();

        Tube tube = this.entityManager
                .createQuery("SELECT t FROM tubes as t WHERE t.id = :id", Tube.class)
                .setParameter("id", id)
                .getSingleResult();

        this.entityManager.getTransaction().commit();

        return tube;
    }

    @Override
    public List<Tube> getAll() {
        this.entityManager.getTransaction().begin();

        List<Tube> tubes = this.entityManager
                .createQuery("SELECT t FROM tubes as t", Tube.class)
                .getResultList();

        this.entityManager.getTransaction().commit();

        return tubes;
    }
}
