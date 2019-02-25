package app.repositories;

import java.util.List;

public interface GenericRepository<E, ID> {
    E save(E entity);

    List<E> findAll();

    E findById(ID id);

    E update(E entity);

    Long size();
}
