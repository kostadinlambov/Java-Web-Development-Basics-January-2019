package services;

import entities.User;

import java.util.List;

public interface UsersService {
    List<User> getAllUsers();

    void add(User user);
}
