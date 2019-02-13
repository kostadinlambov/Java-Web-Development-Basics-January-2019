package fdmc.repositories;

import java.util.List;

public interface GenericRepository<E, ID> {
    E save(E entity);

    List<E> getAll();

    E findById(ID id);
}
