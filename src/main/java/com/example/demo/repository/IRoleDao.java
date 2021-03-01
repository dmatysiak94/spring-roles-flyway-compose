package com.example.demo.repository;

import com.example.demo.entity.AvailableRoleEntity;

import java.util.List;

public interface IRoleDao {

    List<AvailableRoleEntity> listRoles();

    String addRole(AvailableRoleEntity role);

    void updateRole(AvailableRoleEntity role);

    String deleteRole(AvailableRoleEntity role);

    boolean isPresent(String role);
}
