package com.example.demo.repository;

import com.example.demo.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(name = "User.getAllUsersWithRoles",
            value = "FROM User as u LEFT JOIN FETCH u.roles")
    List<User> getAllUsers();

    @Query(name = "User.getUserByIdWithRoles",
            value = "FROM User as u LEFT JOIN FETCH u.roles WHERE u.id =:id")
    User findUserById(@Param("id")Long id);

    @Query(name = "User.getUserByEmailWithRoles",
            value = "FROM User as u LEFT JOIN FETCH u.roles WHERE u.email =:email")
    User findUserByEmail(@Param("email") String email);

}
