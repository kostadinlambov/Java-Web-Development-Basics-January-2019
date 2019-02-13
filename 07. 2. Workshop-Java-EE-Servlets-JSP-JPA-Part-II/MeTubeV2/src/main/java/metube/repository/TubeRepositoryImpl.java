package metube.repository;

import metube.domain.entities.Tube;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class TubeRepositoryImpl implements TubeRepository {

    private final EntityManager entityManager;

    @Inject
    public TubeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Tube save(Tube entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public List<Tube> findAll() {
        this.entityManager.getTransaction().begin();
        List<Tube> allTubes = this.entityManager
                .createQuery("SELECT t FROM Tube t", Tube.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return allTubes;
    }

    @Override
    public Tube findById(String id) {
//        this.entityManager.getTransaction().begin();
        Tube tube = this.entityManager
                .createQuery("SELECT t FROM Tube t WHERE t.id = :id", Tube.class)
                .setParameter("id", id)
                .getSingleResult();
//        this.entityManager.getTransaction().commit();

        return tube;
    }

    @Override
    public long size() {
        this.entityManager.getTransaction().begin();
        long size = this.entityManager
                .createQuery("SELECT count(t) FROM Tube t", long.class)
                .getSingleResult();
        this.entityManager.getTransaction().commit();

        return size;
    }

    @Override
    public Tube update(Tube tube) {
        this.entityManager.merge(tube);
        return tube;
    }
}
