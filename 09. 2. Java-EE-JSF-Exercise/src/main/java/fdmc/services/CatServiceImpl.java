package fdmc.services;

import fdmc.repositories.CatRepository;
import org.modelmapper.ModelMapper;

public class CatServiceImpl implements CatService {

    private final ModelMapper modelMapper;
    private final CatRepository catRepository;


    public CatServiceImpl(ModelMapper modelMapper, CatRepository catRepository) {
        this.modelMapper = modelMapper;
        this.catRepository = catRepository;
    }




}
