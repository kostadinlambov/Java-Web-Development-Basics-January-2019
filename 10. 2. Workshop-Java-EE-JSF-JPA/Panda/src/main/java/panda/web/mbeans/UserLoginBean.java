package panda.web.mbeans;

import org.modelmapper.ModelMapper;
import panda.domain.models.binding.UserLoginBindingModel;
import panda.domain.models.service.UserServiceModel;
import panda.services.UserService;

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

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userLoginBindingModel = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return this.userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }

    public void login() throws IOException {
        UserServiceModel userServiceModel = this.modelMapper.map(this.userLoginBindingModel, UserServiceModel.class);
        UserServiceModel user = this.userService.userLogin(userServiceModel);


        if(user == null){
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/jsf/login.xhtml");
            return;
        }

        HttpSession session = (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(false);

        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole());

        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/jsf/home.xhtml");

    }

}
