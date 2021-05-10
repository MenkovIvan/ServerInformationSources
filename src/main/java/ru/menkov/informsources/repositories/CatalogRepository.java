package ru.menkov.informsources.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.menkov.informsources.model.product.Catalog;

@Repository
public interface CatalogRepository extends CrudRepository<Catalog,Integer> {
}
