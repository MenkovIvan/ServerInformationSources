package ru.menkov.informsources.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.menkov.informsources.model.product.Source;

@Repository
public interface SourceRepository extends CrudRepository<Source,Integer> {
}
