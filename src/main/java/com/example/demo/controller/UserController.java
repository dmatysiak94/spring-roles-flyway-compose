package com.example.demo.controller;

import com.example.demo.entity.UsersEntity;
import com.example.demo.exceptions.NoCustomerException;
import com.example.demo.repository.IRoleDao;
import com.example.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private IUserService userService;
    private IRoleDao roleDao;


    @Autowired
    public UserController(IUserService userService,
                          IRoleDao roleDao) {
        this.userService = userService;
        this.roleDao = roleDao;
    }

    @GetMapping("/user")
    public ResponseEntity<List<UsersEntity>> listUsers() {

        checkIfRequiredRoleExists("ROLE_LIST_USERS");

        return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody UsersEntity user) {

        checkIfRequiredRoleExists("ROLE_ADD_USER");

        return new ResponseEntity<>(userService.addUser(user), HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<UsersEntity> updateUser(@RequestBody UsersEntity user) {

        checkIfRequiredRoleExists("ROLE_UPDATE_USER");

        if (!userService.isPresent(user.getId())) {
            throw new NoCustomerException("user id not found" + user.getId());
        }
        userService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        checkIfRequiredRoleExists("ROLE_DELETE_USER");
        if (!userService.isPresent(id)) {
            throw new NoCustomerException("user id not found " + id);
        }

        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    private void checkIfRequiredRoleExists(String role) {
        if (!roleDao.isPresent(role)){
            if(!roleDao.isPresent("ROLE_ADMIN"))
                throw new NoCustomerException("No existing Role which allow to process this operation ");
        }
    }

}
