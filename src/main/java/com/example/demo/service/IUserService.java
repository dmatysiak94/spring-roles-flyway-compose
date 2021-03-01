package com.example.demo.service;

import com.example.demo.entity.UsersEntity;

import java.util.List;

public interface IUserService {

    List<UsersEntity> listUsers();

    String addUser(UsersEntity user);

    String updateUser(UsersEntity user);

    String deleteUser(Long id);

    boolean isPresent(Long id);
}
