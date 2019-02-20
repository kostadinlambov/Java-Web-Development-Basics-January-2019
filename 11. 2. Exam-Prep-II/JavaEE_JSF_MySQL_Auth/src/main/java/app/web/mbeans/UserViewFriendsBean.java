package app.web.mbeans;

import app.domain.models.service.UserServiceModel;
import app.domain.models.view.UserAllViewModel;
import app.services.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserViewFriendsBean {
    private ModelMapper modelMapper;
    private UserService userService;
    private List<UserAllViewModel> userAllViewModels;

    public UserViewFriendsBean() {
    }

    @Inject
    public UserViewFriendsBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostConstruct
    public void init(){

        List<UserServiceModel> allFriends = this.userService.getAllFriends();

        if(allFriends == null){
            this.userAllViewModels = new ArrayList<>();
        }else{
            this.userAllViewModels = allFriends
                    .stream()
                    .map( user-> this.modelMapper
                            .map(user, UserAllViewModel.class))
                    .collect(Collectors.toList());
        }
    }

    public List<UserAllViewModel> getUserAllViewModels() {
        return this.userAllViewModels;
    }

    public void setUserAllViewModels(List<UserAllViewModel> userAllViewModels) {
        this.userAllViewModels = userAllViewModels;
    }
}
