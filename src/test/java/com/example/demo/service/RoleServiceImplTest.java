package com.example.demo.service;

import com.example.demo.entity.AvailableRoleEntity;
import com.example.demo.entity.RoleEntity;
import com.example.demo.repository.IRoleDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    private AvailableRoleEntity roleEntity;
    private final String ROLE_TEST = "ROLE_TEST";

    @Mock
    private IRoleDao roleDao;

    private IRoleService roleService;

    @BeforeEach
    public void setUpTest(){
        roleService = new RoleServiceImpl(roleDao);

        roleEntity = new AvailableRoleEntity();
        roleEntity.setRoleId(1);
        roleEntity.setRole("TEST");
    }

    @Test
    public void listRolesTest() {
        List<AvailableRoleEntity> roleEntities = new ArrayList<>();
        roleEntities.add(roleEntity);

        Mockito.when(roleDao.listRoles()).thenReturn(roleEntities);

        Assertions.assertThat(roleService.listRoles()).hasSize(1);
    }

    @Test
    public void isPresentTest(){
        Mockito.when(roleDao.isPresent(ROLE_TEST)).thenReturn(true);

        Assertions.assertThat(roleService.isPresent(ROLE_TEST)).isTrue();
    }
}
