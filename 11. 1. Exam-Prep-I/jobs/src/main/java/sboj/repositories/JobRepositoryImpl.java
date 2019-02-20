package sboj.repositories;

import sboj.domain.entities.JobApplication;
import sboj.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class JobRepositoryImpl implements JobRepository {
    private final EntityManager entityManager;

    @Inject
    public JobRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public JobApplication save(JobApplication entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public List<JobApplication> findAll() {
        this.entityManager.getTransaction().begin();

        List<JobApplication> jobApplicationList = this.entityManager
                .createQuery("SELECT j FROM JobApplication AS j", JobApplication.class)
                .getResultList();

        this.entityManager.getTransaction().commit();

        return jobApplicationList;
    }

    @Override
    public JobApplication findById(String id) {
        this.entityManager.getTransaction().begin();

        JobApplication jobApplication = this.entityManager
                .createQuery("SELECT j FROM JobApplication AS j WHERE j.id = :id", JobApplication.class)
                .setParameter("id", id)
                .getSingleResult();

        this.entityManager.getTransaction().commit();

        return jobApplication;
    }

    @Override
    public Long size() {
        this.entityManager.getTransaction().begin();

        Long size = this.entityManager
                .createQuery("SELECT count(j) FROM JobApplication AS j", Long.class).getSingleResult();

        this.entityManager.getTransaction().commit();

        return size;
    }

    @Override
    public void deleteJobApplication(String id) {
        this.entityManager.getTransaction().begin();

        int count = this.entityManager
                .createQuery("DELETE FROM JobApplication AS j WHERE j.id = :id")
                .setParameter("id", id)
                .executeUpdate();


        this.entityManager.getTransaction().commit();

    }
}
