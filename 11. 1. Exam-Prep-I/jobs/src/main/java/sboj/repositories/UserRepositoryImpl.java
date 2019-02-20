package sboj.repositories;

import sboj.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User save(User entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public List<User> findAll() {
        this.entityManager.getTransaction().begin();

        List<User> userList = this.entityManager
                .createQuery("SELECT u FROM User AS u", User.class)
                .getResultList();

        this.entityManager.getTransaction().commit();

        return userList;
    }

    @Override
    public User findById(String id) {
        this.entityManager.getTransaction().begin();

        User user = this.entityManager
                .createQuery("SELECT u FROM User AS u WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();

        this.entityManager.getTransaction().commit();

        return user;
    }

    @Override
    public Long size() {
        this.entityManager.getTransaction().begin();

        Long size = this.entityManager
                .createQuery("SELECT count(u) FROM User AS u", Long.class).getSingleResult();

        this.entityManager.getTransaction().commit();

        return size;
    }

    @Override
    public User findByName(String username) {
        this.entityManager.getTransaction().begin();

        try {
            User user = this.entityManager
                    .createQuery("SELECT u FROM User AS u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();

            this.entityManager.getTransaction().commit();

            return user;
        }catch (Exception e){
            this.entityManager.getTransaction().commit();
            return null;
        }
    }
}
