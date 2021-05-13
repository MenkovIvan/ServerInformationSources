package ru.menkov.informsources.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.menkov.informsources.model.custom.User;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    User findUserByName(String name);
    User findUserByEmail(String email);
    User findUserByEmailAndName(String email, String name);
    Boolean existsUserByEmailAndPassword(String email, String password);
    Boolean existsUserById(Integer id);
    
    @Modifying
    @Transactional
    @Query(value = "update inf_sources.users set online = :online where email= :email", nativeQuery = true)
    void setUserInfoById(@Param("online") Boolean online, @Param("email") String email);
}
