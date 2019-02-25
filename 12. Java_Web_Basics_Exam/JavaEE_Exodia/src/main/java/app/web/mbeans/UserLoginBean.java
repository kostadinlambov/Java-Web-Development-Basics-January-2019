package app.web.mbeans;

import app.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import app.domain.models.binding.UserLoginBindingModel;
import app.domain.models.service.UserServiceModel;
import app.services.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class UserLoginBean {
    private UserLoginBindingModel userLoginBindingModel;
    private ModelMapper modelMapper;
    private UserService userService;
    private ValidationUtil validationUtil;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(ModelMapper modelMapper, UserService userService, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.validationUtil = validationUtil;

    }

    @PostConstruct
    public void init(){
        this.userLoginBindingModel = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return this.userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }

    public void login() throws IOException {
        if(!this.validationUtil.isValid(this.userLoginBindingModel)) {
           throw new IllegalArgumentException("Incorrect credentials!");
        }

        UserServiceModel userServiceModel = this.modelMapper.map(this.userLoginBindingModel, UserServiceModel.class);
        UserServiceModel user = this.userService.userLogin(userServiceModel);

        if(user == null){
            throw new IllegalArgumentException("Something went wrong!");
        }

        HttpSession session = (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(false);

        session.setAttribute("username", user.getUsername());
        session.setAttribute("id", user.getId());

        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/jsf/home.xhtml");
    }
}
