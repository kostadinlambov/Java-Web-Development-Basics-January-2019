package metube.services;

import metube.domain.entities.Tube;
import metube.domain.models.service.TubeServiceModel;
import metube.domain.models.view.TubeAllViewModel;
import metube.domain.models.view.TubeDetailsViewModel;
import metube.repositories.TubeRepository;
import metube.util.ModelMapper;
import metube.util.ValidationUtil;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

public class TubeServiceImpl implements TubeService {
    private final TubeRepository tubeRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Inject
    public TubeServiceImpl(TubeRepository tubeRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.tubeRepository = tubeRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void saveTube(TubeServiceModel tubeServiceModel) throws IllegalAccessException {
        if(!this.validationUtil.isValid(tubeServiceModel)){
            throw new IllegalAccessException("Required fields are missing or incorrect.");
        }

        try{
            TubeDetailsViewModel tubeFromDb = this.getByName(tubeServiceModel.getName());
        }catch (NoResultException nre){
            Tube tube = this.modelMapper.map(tubeServiceModel, Tube.class);
            Tube savedTube = this.tubeRepository.save(tube);
            return;
        }

        throw new IllegalArgumentException(String.format("Tube with name - \"%s\" already exists", tubeServiceModel.getName()));
    }

    @Override
    public List<TubeAllViewModel> getAll() {
        List<Tube> tubeList = this.tubeRepository.getAll();

        List<TubeAllViewModel> allViewModels = tubeList.stream()
                .map(tube -> this.modelMapper.map(tube, TubeAllViewModel.class))
                .collect(Collectors.toList());

        return allViewModels;
    }

    @Override
    public TubeDetailsViewModel getByName(String name) {
        Tube tube = this.tubeRepository.findByName(name);
        if(tube != null){
            return this.modelMapper.map(tube, TubeDetailsViewModel.class);
        }

        return null;
    }
}
