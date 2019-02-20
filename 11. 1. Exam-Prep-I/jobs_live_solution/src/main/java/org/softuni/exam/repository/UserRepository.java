package org.softuni.exam.repository;

import org.softuni.exam.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {
    User findByUsername(String username);
}
