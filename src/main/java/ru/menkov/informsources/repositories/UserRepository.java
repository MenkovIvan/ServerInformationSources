package ru.menkov.informsources.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.menkov.informsources.model.custom.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    User findUserByName(String name);
    User findUserByEmail(String email);
    Boolean existsUserByEmailAndPassword(String email, String password);
    Boolean existsUserById(Integer id);
    @Modifying
    @Query("update inf_sources.users u set u.online = ?1")
    void setUserInfoById(Boolean online);
}
