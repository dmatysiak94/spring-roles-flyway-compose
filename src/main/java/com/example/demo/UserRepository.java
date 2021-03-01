package com.example.demo;

import com.example.demo.entity.UsersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UsersEntity, Integer> {

    @Query("SELECT u FROM UsersEntity u WHERE u.username = :username")
    UsersEntity getUserByUsername(@Param("username") String username);
}
