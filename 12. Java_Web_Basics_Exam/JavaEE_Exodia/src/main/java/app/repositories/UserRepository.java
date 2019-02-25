package app.repositories;

import app.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {
    User findByName(String username);

    User findByEmail(String email);
}
