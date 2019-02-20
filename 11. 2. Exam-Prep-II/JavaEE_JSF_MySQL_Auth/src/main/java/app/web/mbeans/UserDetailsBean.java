package app.web.mbeans;

import app.domain.models.service.UserServiceModel;
import app.domain.models.view.UserDetailsViewModel;
import app.services.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class UserDetailsBean {
    private ModelMapper modelMapper;
    private UserService userService;
    private UserDetailsViewModel userDetailsViewModel;

    public UserDetailsBean() {
    }

    @Inject
    public UserDetailsBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostConstruct
    public void init(){
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        String id = context.getRequestParameterMap().get("id");

        UserServiceModel user = this.userService.findUserById(id);

        UserDetailsViewModel userDetailsViewModel = this.modelMapper.map(user, UserDetailsViewModel.class);

        if(userDetailsViewModel == null){
            throw new IllegalArgumentException("Something went wrong");
        }

        this.userDetailsViewModel = userDetailsViewModel;
    }

    public UserDetailsViewModel getUserDetailsViewModel() {
        return this.userDetailsViewModel;
    }

    public void setUserDetailsViewModel(UserDetailsViewModel userDetailsViewModel) {
        this.userDetailsViewModel = userDetailsViewModel;
    }
}
