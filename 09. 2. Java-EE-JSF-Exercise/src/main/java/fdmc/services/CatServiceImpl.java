package fdmc.services;

import fdmc.domain.entities.Cat;
import fdmc.domain.models.service.CatServiceModel;
import fdmc.repositories.CatRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class CatServiceImpl implements CatService {

    private final ModelMapper modelMapper;
    private final CatRepository catRepository;


    @Inject
    public CatServiceImpl(ModelMapper modelMapper, CatRepository catRepository) {
        this.modelMapper = modelMapper;
        this.catRepository = catRepository;
    }

    @Override
    public boolean save(CatServiceModel catServiceModel){
        try{
            Cat cat = this.modelMapper.map(catServiceModel, Cat.class);
            this.catRepository.save(cat);
        }catch (Exception e){
            throw new IllegalArgumentException("Something went wrong!");
        }

        return true;
    }

    @Override
    public List<CatServiceModel> getAllCats() {
        List<Cat> cats = this.catRepository.getAll();

        List<CatServiceModel> catServiceModels = cats
                .stream()
                .map(cat -> this.modelMapper.map(cat, CatServiceModel.class))
                .collect(Collectors.toList());

        return catServiceModels;
    }


}
