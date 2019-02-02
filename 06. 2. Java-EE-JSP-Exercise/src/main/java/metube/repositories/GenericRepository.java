package metube.repositories;

import java.util.List;

public interface GenericRepository<Entity, Key> {
    Entity save(Entity entity);

    Entity findById(Key id);

    List<Entity> getAll();
}
