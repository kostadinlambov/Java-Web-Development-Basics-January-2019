package panda.repository;

import panda.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {

    User findByUsername(String username);
}
