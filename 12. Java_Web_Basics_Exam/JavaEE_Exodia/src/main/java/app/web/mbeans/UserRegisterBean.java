package app.web.mbeans;

import app.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import app.domain.models.binding.UserRegisterBindingModel;
import app.domain.models.service.UserServiceModel;
import app.services.UserService;

import javax.annotation.PostConstruct;
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
    private ValidationUtil validationUtil;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(ModelMapper modelMapper, UserService userService, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.validationUtil = validationUtil;
    }

    @PostConstruct
    public void init(){
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

        if(!this.validationUtil.isValid(this.userRegisterBindingModel)){
            throw new IllegalArgumentException("Something went wrong!");
        }

        UserServiceModel userServiceModel = this.modelMapper.map(this.userRegisterBindingModel, UserServiceModel.class);
        UserServiceModel savedUserServiceModel = this.userService.userRegister(userServiceModel);


        if(savedUserServiceModel == null){
            throw new IllegalArgumentException("Something went wrong!");
        }

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("/faces/jsf/login.xhtml");
    }
}
