package org.softuni.exam.repository;

import java.util.List;

public interface GenericRepository<Entity, Id> {
    Entity save(Entity entity);

    List<Entity> findAll();

    Entity findById(Id id);
}
