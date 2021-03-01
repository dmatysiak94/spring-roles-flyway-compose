package com.example.demo.service;

import com.example.demo.entity.AvailableRoleEntity;

import java.util.List;

public interface IRoleService {

    List<AvailableRoleEntity> listRoles();

    String addRole(AvailableRoleEntity role);

    void updateRole(AvailableRoleEntity role);

    String deleteRole(AvailableRoleEntity role);

    boolean isPresent(String role);
}
