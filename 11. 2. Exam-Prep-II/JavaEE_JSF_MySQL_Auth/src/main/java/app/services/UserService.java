package app.services;

import app.domain.models.service.UserServiceModel;
import app.domain.models.view.UserAllViewModel;

import java.util.List;

public interface UserService {

    void userRegister(UserServiceModel userServiceModel);

    UserServiceModel userLogin(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsername(String username);

    UserServiceModel findUserById(String id);

    List<UserServiceModel> findAllUsers();

    List<UserServiceModel> findAllToShowOnHomePage();

    boolean addFriend(String id);

    List<UserServiceModel> getAllFriends();

    boolean removeFriend(String friendId);
}
