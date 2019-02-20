package sboj.repositories;

import sboj.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {
    User findByName(String username);
}
