package fdmc.web.mbeans;

import fdmc.domain.models.binding.CatCreateBindingModel;
import fdmc.domain.models.service.CatServiceModel;
import fdmc.services.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Date;

@Named
@RequestScoped
public class CatCreateBean {
    private CatCreateBindingModel catCreateBindingModel;
    private CatService catService;
    private ModelMapper modelMapper;

    public CatCreateBean() {
        this.catCreateBindingModel = new CatCreateBindingModel();
    }

    @Inject
    public CatCreateBean(CatCreateBindingModel catCreateBindingModel, CatService catService, ModelMapper modelMapper) {
        this();
        this.catService = catService;
        this.modelMapper = modelMapper;

    }

    public CatCreateBindingModel getCatCreateBindingModel() {
        return this.catCreateBindingModel;
    }

    public void setCatCreateBindingModel(CatCreateBindingModel catCreateBindingModel) {
        this.catCreateBindingModel = catCreateBindingModel;
    }


    public void createCat() throws IOException {

        CatServiceModel catServiceModel = this.modelMapper.map(this.catCreateBindingModel, CatServiceModel.class);



        this.catService.save(catServiceModel);

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/jsf/all-cats.xhtml");
    }
}
