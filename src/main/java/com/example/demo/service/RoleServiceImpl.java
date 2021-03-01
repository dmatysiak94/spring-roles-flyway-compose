package com.example.demo.service;

import com.example.demo.entity.AvailableRoleEntity;
import com.example.demo.repository.IRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    private IRoleDao roleDao;

    @Autowired
    public RoleServiceImpl(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<AvailableRoleEntity> listRoles() {
        return roleDao.listRoles();
    }

    @Override
    @Transactional
    public String addRole(AvailableRoleEntity role) {
        return roleDao.addRole(role);
    }

    @Override
    @Transactional
    public void updateRole(AvailableRoleEntity role) {
        roleDao.updateRole(role);
    }

    @Override
    @Transactional
    public String deleteRole(AvailableRoleEntity role) {
        return roleDao.deleteRole(role);
    }

    @Override
    public boolean isPresent(String role) {
        return roleDao.isPresent(role);
    }

}
