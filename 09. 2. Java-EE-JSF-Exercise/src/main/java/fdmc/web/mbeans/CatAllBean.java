package fdmc.web.mbeans;

import fdmc.domain.entities.Cat;
import fdmc.domain.models.View.CatAllViewModel;
import fdmc.services.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class CatAllBean {
    private CatService catService;
    private ModelMapper modelMapper;
    private List<CatAllViewModel> cats;

    public CatAllBean() {
    }

    @Inject
    public CatAllBean(CatService catService, ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.cats = this.catService
                .getAllCats()
                .stream()
                .map(catServiceModel -> this.modelMapper
                        .map(catServiceModel, CatAllViewModel.class))
                .collect(Collectors.toList());

    }

    public List<CatAllViewModel> getCats() {
        return this.cats;
    }

    public void setCats(List<CatAllViewModel> cats) {
        this.cats = cats;
    }
}
