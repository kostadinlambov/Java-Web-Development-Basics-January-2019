package metube.service;

import metube.domain.models.service.UserServiceModel;

public interface UserService {

    boolean registerUser(UserServiceModel userServiceModel);

    boolean loginUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsername(String username);
}
