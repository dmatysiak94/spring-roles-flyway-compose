package com.example.demo.repository;

import com.example.demo.entity.UsersEntity;

import java.util.List;

public interface IUserDao {

    List<UsersEntity> listUsers();

    String addUser(UsersEntity user);

    String updateUser(UsersEntity user);

    String deleteUser(Long id);

    boolean isPresent(Long id);
}
