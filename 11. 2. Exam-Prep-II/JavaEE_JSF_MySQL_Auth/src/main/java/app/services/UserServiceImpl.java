package app.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import app.domain.entities.User;
import app.domain.models.service.UserServiceModel;
import app.repositories.UserRepository;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

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
    public UserServiceModel findUserById(String id) {
        User user = this.userRepository.findById(id);
        if(user == null){
//            throw new IllegalArgumentException("User does not exists!");
            return null;
        }
        return this.modelMapper.map(user, UserServiceModel.class);

    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        List<User> users = this.userRepository.findAll();
        if(users.size() >  0){
            List<UserServiceModel> userServiceModelList = users.stream()
                    .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                    .collect(Collectors.toList());
            return userServiceModelList;
        }
        return null;
    }


    @Override
    public List<UserServiceModel> findAllToShowOnHomePage(){
        String id = getCurrentUserId();

        List<User> users = this.userRepository.findAll();
        User currentUser = this.userRepository.findById(id);


        List<User> usersToDisplay = users
                .stream()
                .filter(user -> !user.getId().equals(id) && !currentUser.getFriends().contains(user))
                .collect(Collectors.toList());

//        List<User> usersToDisplay = userList.stream().filter(user -> !currentUser.getFriends().contains(user)).collect(Collectors.toList());

        if(usersToDisplay.size() >  0){
            return usersToDisplay.stream()
                    .map(user ->  this.modelMapper.map(user, UserServiceModel.class))
                    .collect(Collectors.toList());
        }
        return null;

    }

    @Override
    public boolean addFriend(String id) {
        User friendCandidate = this.userRepository.findById(id);
        User loggedInUser = this.userRepository.findById(this.getCurrentUserId());

        if(loggedInUser != null && friendCandidate != null){
            if(!loggedInUser.getFriends().contains(friendCandidate)&& !friendCandidate.getId().equals(this.getCurrentUserId())){
                loggedInUser.getFriends().add(friendCandidate);

                this.userRepository.update(loggedInUser);

            }
            if(!friendCandidate.getFriends().contains(loggedInUser)&& !friendCandidate.getId().equals(this.getCurrentUserId())){
                friendCandidate.getFriends().add(loggedInUser);

                this.userRepository.update(friendCandidate);

            }
            return true;


        }



        return false;
    }

    @Override
    public boolean removeFriend(String friendId) {
        User friendToRemove = this.userRepository.findById(friendId);
        User loggedInUser = this.userRepository.findById(this.getCurrentUserId());

        if(loggedInUser != null && friendToRemove != null){
            if(loggedInUser.getFriends().contains(friendToRemove)&& !friendToRemove.getId().equals(this.getCurrentUserId())){
                loggedInUser.getFriends().remove(friendToRemove);

                this.userRepository.update(loggedInUser);

            }
            if(friendToRemove.getFriends().contains(loggedInUser)&& !friendToRemove.getId().equals(this.getCurrentUserId())){
                friendToRemove.getFriends().remove(loggedInUser);

                this.userRepository.update(loggedInUser);

            }
            return true;

        }

        return false;
    }



    @Override
    public List<UserServiceModel> getAllFriends() {
        User loggedInUser = this.userRepository.findById(this.getCurrentUserId());
        if(loggedInUser.getFriends().size() > 0){
            return loggedInUser.getFriends()
                    .stream()
                    .map(friend -> this.modelMapper.map(friend, UserServiceModel.class))
                    .collect(Collectors.toList());
        }

        return null;
    }



    private String getCurrentUserId(){
        HttpSession session = (HttpSession) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getSession(false);

        return (String) session.getAttribute("id");

    }


}
