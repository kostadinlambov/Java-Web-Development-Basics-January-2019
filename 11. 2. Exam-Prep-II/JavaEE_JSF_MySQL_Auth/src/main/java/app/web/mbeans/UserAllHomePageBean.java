package app.web.mbeans;

import app.domain.models.service.UserServiceModel;
import app.domain.models.view.UserAllViewModel;
import app.services.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class UserAllHomePageBean {

    private ModelMapper modelMapper;
    private UserService userService;
    private List<UserAllViewModel> userAllViewModels;

    public UserAllHomePageBean() {
    }

    @Inject
    public UserAllHomePageBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @PostConstruct
    public void init(){
//        List<UserServiceModel> allUsers = this.userService.findAllUsers();
        List<UserServiceModel> allUsersToShow = this.userService.findAllToShowOnHomePage();

        System.out.println();
//        allUsers.stream().filter()

        if(allUsersToShow != null){
            this.userAllViewModels =  allUsersToShow.stream().map(userServiceModel -> this.modelMapper.map(userServiceModel, UserAllViewModel.class)).collect(Collectors.toList());
        }else{
            this.userAllViewModels = new ArrayList<>();
            System.out.println();
        }
    }

    public List<UserAllViewModel> getUserAllViewModels() {
        return this.userAllViewModels;
    }

    public void setUserAllViewModels(List<UserAllViewModel> userAllViewModels) {
        this.userAllViewModels = userAllViewModels;
    }
}
