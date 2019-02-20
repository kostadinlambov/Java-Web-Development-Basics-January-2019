package app.web.mbeans;

import org.modelmapper.ModelMapper;
import app.domain.models.binding.UserLoginBindingModel;
import app.domain.models.service.UserServiceModel;
import app.services.UserService;

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
//            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/jsf/login.xhtml");
            throw new IllegalArgumentException("Something went wrong!");
        }

        HttpSession session = (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(true);

        session.setAttribute("username", user.getUsername());
        session.setAttribute("id", user.getId());
//        session.setAttribute("role", user.getRole());

        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/jsf/home.xhtml");

    }

}
