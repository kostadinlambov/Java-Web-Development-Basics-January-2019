package sboj.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import sboj.domain.entities.User;
import sboj.domain.models.service.UserServiceModel;
import sboj.repositories.UserRepository;

import javax.inject.Inject;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void userRegister(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public UserServiceModel userLogin(UserServiceModel userServiceModel) {
        UserServiceModel userByUsername = this.findUserByUsername(userServiceModel.getUsername());

        if(userByUsername == null || !DigestUtils.sha256Hex(userServiceModel.getPassword()).equals(userByUsername.getPassword())) {
//               throw new IllegalArgumentException("Incorrect credentials!");
            return null;
        }

        return this.modelMapper.map(userByUsername, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findUserByUsername(String username) {
        User user = this.userRepository.findByName(username);
        if(user == null){
//            throw new IllegalArgumentException("User does not exists!");
            return null;
        }

        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);

        return userServiceModel;
    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        return null;
    }


}
