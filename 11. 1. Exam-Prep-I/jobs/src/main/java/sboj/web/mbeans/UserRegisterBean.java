package sboj.web.mbeans;

import org.modelmapper.ModelMapper;
import sboj.domain.models.binding.UserRegisterBindingModel;
import sboj.domain.models.service.UserServiceModel;
import sboj.services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UserRegisterBean {

    private UserRegisterBindingModel userRegisterBindingModel;
    private ModelMapper modelMapper;
    private UserService userService;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return this.userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void register() throws IOException {
        if(!this.userRegisterBindingModel.getPassword().equals(this.userRegisterBindingModel.getConfirmPassword())){
            throw new IllegalArgumentException("Passwords does not match");
        }

        UserServiceModel userServiceModel = this.modelMapper.map(this.userRegisterBindingModel, UserServiceModel.class);
        this.userService.userRegister(userServiceModel);

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/jsf/login.xhtml");
    }
}
