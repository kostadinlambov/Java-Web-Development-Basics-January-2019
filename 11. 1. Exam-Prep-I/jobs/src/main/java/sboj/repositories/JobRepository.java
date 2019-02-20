package sboj.repositories;

import sboj.domain.entities.JobApplication;

public interface JobRepository extends GenericRepository<JobApplication, String> {

    void deleteJobApplication(String id);
}
