package ru.menkov.informsources.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.menkov.informsources.model.product.Catalog;

@Repository
public interface CatalogRepository extends CrudRepository<Catalog,Integer> {
    Boolean existsCatalogByName(String name);
    Boolean existsCatalogById(Integer id);
    Catalog findCatalogById(Integer id);
    Catalog findCatalogByName(String name);

    @Modifying
    @Transactional
    @Query(value = "update inf_sources.catalog set description = :description where name= :name", nativeQuery = true)
    void setCatalogDescriptionByName(@Param("description") String description, @Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "delete from inf_sources.catalog where name= :name", nativeQuery = true)
    void deleteCatalogByName( @Param("name") String name);


    @Override
    Iterable<Catalog> findAll();
}
