package fdmc.services;

import fdmc.domain.models.service.CatServiceModel;

import java.util.List;

public interface CatService {
    boolean save(CatServiceModel catServiceModel);

    List<CatServiceModel> getAllCats();
}
