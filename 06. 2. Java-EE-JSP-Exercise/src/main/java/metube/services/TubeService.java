package metube.services;

import metube.domain.models.service.TubeServiceModel;
import metube.domain.models.view.TubeAllViewModel;
import metube.domain.models.view.TubeDetailsViewModel;

import java.util.List;

public interface TubeService {
    void saveTube(TubeServiceModel tubeServiceModel);

    List<TubeAllViewModel> getAll();

    TubeDetailsViewModel getByName(String name);
}
