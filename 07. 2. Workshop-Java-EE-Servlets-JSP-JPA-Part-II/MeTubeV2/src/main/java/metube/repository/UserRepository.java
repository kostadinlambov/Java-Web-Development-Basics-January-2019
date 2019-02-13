package metube.repository;

import metube.domain.entities.Tube;
import metube.domain.entities.User;

public interface UserRepository extends GenericRepository<User, String> {

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
