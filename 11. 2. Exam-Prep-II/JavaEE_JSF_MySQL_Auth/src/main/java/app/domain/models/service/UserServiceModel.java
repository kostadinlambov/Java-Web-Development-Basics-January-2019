package app.domain.models.service;



import app.domain.entities.Gender;
import app.domain.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceModel {
    private String id;
    private String username;
    private String password;
    private Gender gender;
    private List<User> users;
    private List<User> friends;


    public UserServiceModel() {
//        this.users = new ArrayList<>();
//        this.friends = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<User> getFriends() {
        return this.friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }
}
