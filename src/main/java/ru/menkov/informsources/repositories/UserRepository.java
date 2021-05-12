package ru.menkov.informsources.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.menkov.informsources.model.custom.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    User findUserByName(String name);
    User findUserByEmail(String email);
    Boolean existsUserByEmailAndPassword(String email, String password);
    Boolean existsUserById(Integer id);
}
