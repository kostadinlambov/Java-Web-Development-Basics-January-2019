package metube.services;

import metube.domain.entities.Tube;
import metube.domain.models.service.TubeServiceModel;
import metube.repositories.TubeRepository;
import metube.util.ModelMapper;

import javax.inject.Inject;

public class TubeServiceImpl implements TubeService {
    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveTube(TubeServiceModel tubeServiceModel){
        Tube tube = this.modelMapper.map(tubeServiceModel, Tube.class);

        Tube savedTube = this.tubeRepository.save(tube);
//
//        if(savedTube == null){
//            throw new Exception("Something went wrong!");
//        }

    }
}
