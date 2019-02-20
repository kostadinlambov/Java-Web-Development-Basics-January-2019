package sboj.services;

import org.modelmapper.ModelMapper;
import sboj.domain.entities.JobApplication;
import sboj.domain.models.service.JobApplicationServiceModel;
import sboj.repositories.JobRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobApplicationServiceImpl implements JobApplicationService {
    private final ModelMapper modelMapper;
    private final JobRepository jobRepository;

    @Inject
    public JobApplicationServiceImpl(ModelMapper modelMapper, JobRepository jobRepository) {
        this.modelMapper = modelMapper;
        this.jobRepository = jobRepository;
    }


    @Override
    public JobApplicationServiceModel createJobApplication(JobApplicationServiceModel jobApplicationServiceModel) {
        try {
            JobApplication jobApplication = this.modelMapper.map(jobApplicationServiceModel, JobApplication.class);
            JobApplication savedJobApplication = this.jobRepository.save(jobApplication);
            return this.modelMapper.map(savedJobApplication, JobApplicationServiceModel.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Something went wrong!");
        }
    }

    @Override
    public JobApplicationServiceModel findJobApplicationById(String id) {
        try {
            JobApplication jobApplication = this.jobRepository.findById(id);
            return this.modelMapper.map(jobApplication, JobApplicationServiceModel.class);

        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public List<JobApplicationServiceModel> findAllJobApplications() {
        List<JobApplication> jobApplications = this.jobRepository.findAll();

        if (jobApplications.size() > 0) {
            return jobApplications
                    .stream()
                    .map(jobApplication -> this.modelMapper.map(jobApplication, JobApplicationServiceModel.class))
                    .collect(Collectors.toList());
        }

        return null;
    }

    @Override
    public void deleteJobApp(String id) {
        try{
            this.jobRepository.deleteJobApplication(id);
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}
