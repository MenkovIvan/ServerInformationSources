package ru.menkov.informsources.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.menkov.informsources.model.product.Source;

@Repository
public interface SourceRepository extends CrudRepository<Source,Integer> {
    Boolean existsSourceByName(String name);
    Source findSourceByName(String name);
    Iterable<Source> findAllByCatalog_id(Integer catalog_id);
    Iterable<Source> findAllByUser_id(Integer user_id);

    @Modifying
    @Transactional
    @Query(value = "update inf_sources.source set description = :description where name= :name", nativeQuery = true)
    void setSourceDescriptionByName(@Param("description") String description, @Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "update inf_sources.source set value = :value where name= :name", nativeQuery = true)
    void setSourceValueByName(@Param("value") String value, @Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "delete from inf_sources.source where name= :name", nativeQuery = true)
    void deleteSourceByName( @Param("name") String name);

}
