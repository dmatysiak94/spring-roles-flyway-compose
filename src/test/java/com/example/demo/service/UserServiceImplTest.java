package com.example.demo.service;

import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UsersEntity;
import com.example.demo.repository.IRoleDao;
import com.example.demo.repository.IUserDao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private UsersEntity usersEntity;

    @Mock
    private IUserDao userDao;

    @Mock
    private IRoleDao roleDao;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private IUserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userDao, roleDao, bCryptPasswordEncoder);

        usersEntity = new UsersEntity();
        RoleEntity roleEntity = new RoleEntity();
        Set<RoleEntity> roleEntitySet = new HashSet<>();
        List<UsersEntity> usersEntities = new ArrayList<>();
        roleEntity.setName("TEST");
        roleEntity.setId(1);
        roleEntitySet.add(roleEntity);
        usersEntity.setId(1l);
        usersEntity.setPassword("test");
        usersEntity.setUsername("test");
        usersEntity.setRoles(roleEntitySet);
        usersEntities.add(usersEntity);
    }

    @Test
    public void ListAllTest(){
        List<UsersEntity> list = new ArrayList<>();
        list.add(usersEntity);
        Mockito.when(userDao.listUsers()).thenReturn(list);

        Assertions.assertThat(userService.listUsers()).hasSize(1);
    }

    @Test
    public void isPresentTest(){
        Long id = 1l;
        Mockito.when(userDao.isPresent(id)).thenReturn(true);
        Assertions.assertThat(userService.isPresent(id)).isTrue();
    }

}
