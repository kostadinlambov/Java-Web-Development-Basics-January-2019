package org.softuni.exam.repository;

import org.softuni.exam.domain.entities.JobApplication;

public interface JobApplicationRepository extends GenericRepository<JobApplication, String> {
    void delete(String id);
}
