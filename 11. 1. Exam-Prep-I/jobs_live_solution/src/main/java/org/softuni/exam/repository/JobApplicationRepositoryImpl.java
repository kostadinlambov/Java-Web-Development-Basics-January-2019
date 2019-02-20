package org.softuni.exam.repository;

import org.softuni.exam.domain.entities.JobApplication;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class JobApplicationRepositoryImpl extends BaseRepository implements JobApplicationRepository {
    @Inject
    public JobApplicationRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public void delete(String id) {
        this.executeTransaction((em) -> {
            em.createNativeQuery("DELETE FROM job_applications WHERE id='" + id + "'").executeUpdate();
            return null;
        });
    }

    @Override
    public JobApplication save(JobApplication jobApplication) {
        return this.executeTransaction((em) -> {
            em.persist(jobApplication);
            return jobApplication;
        });
    }

    @Override
    public List<JobApplication> findAll() {
        return this.executeTransaction((em) -> {
            return em.createNativeQuery("SELECT * FROM job_applications", JobApplication.class)
                    .getResultList();
        });
    }

    @Override
    public JobApplication findById(String id) {
        return this.executeTransaction((em) -> {
            return (JobApplication) em.createNativeQuery("SELECT * FROM job_applications WHERE id = '" + id + "'", JobApplication.class)
                    .getSingleResult();
        });
    }
}
