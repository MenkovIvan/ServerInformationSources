package ru.menkov.informsources.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.menkov.informsources.model.custom.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    Boolean existsUserByEmailAndPassword(String email, String password);
    Boolean existsUserByUser_id(Integer id);
}
