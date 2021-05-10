package ru.menkov.informsources.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.menkov.informsources.model.custom.Group;

@Repository
public interface GroupRepository extends CrudRepository<Group,Integer> {
}
