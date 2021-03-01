package com.example.demo.service;

import com.example.demo.entity.UsersEntity;
import com.example.demo.exceptions.NoCustomerException;
import com.example.demo.repository.IRoleDao;
import com.example.demo.repository.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private IUserDao userDao;
    private IRoleDao roleDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(IUserDao userDao,
                           IRoleDao roleDao,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<UsersEntity> listUsers() {

        return userDao.listUsers();
    }

    @Override
    @Transactional
    public String addUser(UsersEntity user) {

        checkRolesAvailabilityAndAccessibility(user);

        user.setId(0l);
        String bCryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(bCryptedPassword);

        return userDao.addUser(user);
    }

    @Override
    @Transactional
    public String updateUser(UsersEntity user) {

        checkRolesAvailabilityAndAccessibility(user);
        return userDao.updateUser(user);
    }

    @Override
    @Transactional
    public String deleteUser(Long id) {
        return userDao.deleteUser(id);
    }

    @Override
    public boolean isPresent(Long id) {
        return userDao.isPresent(id);
    }

    private void checkRolesAvailabilityAndAccessibility(UsersEntity user) {
        user.getRoles().stream()
                .forEach(userRoles -> {
                    if (!roleDao.isPresent(userRoles.getName())) {
                        throw new NoCustomerException("No role Available " + userRoles.getName());
                    }
                    if (userRoles.getName().equals("ADMIN")) {
                        throw new NoCustomerException("No access to add this role " + userRoles.getName());
                    }
                });

    }
}
