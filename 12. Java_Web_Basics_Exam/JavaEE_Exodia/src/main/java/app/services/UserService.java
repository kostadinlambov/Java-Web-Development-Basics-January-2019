package app.services;

import app.domain.entities.User;
import app.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {

    UserServiceModel userRegister(UserServiceModel userServiceModel);

    UserServiceModel userLogin(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsername(String username);

    List<UserServiceModel> findAllUsers();

    User getByEmailValidation(String email);

}
