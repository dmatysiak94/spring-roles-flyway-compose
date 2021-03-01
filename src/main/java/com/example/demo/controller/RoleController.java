package com.example.demo.controller;


import com.example.demo.entity.AvailableRoleEntity;
import com.example.demo.exceptions.NoCustomerException;
import com.example.demo.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoleController {

    private IRoleService roleService;

    @Autowired
    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/role")
    public ResponseEntity<List<AvailableRoleEntity>> getRoles() {

        return new ResponseEntity<>(roleService.listRoles(), HttpStatus.OK);
    }

    @PostMapping("/role")
    public ResponseEntity<String> addRole(@RequestBody AvailableRoleEntity role) {

        role.setRoleId(0);

        return new ResponseEntity<>(roleService.addRole(role), HttpStatus.OK);
    }

    @PutMapping("/role")
    public ResponseEntity<AvailableRoleEntity> updateRole(@RequestBody AvailableRoleEntity role) {

        if ("ROLE_ADMIN".equals(role.getRole())) {
            throw new NoCustomerException("no access to change this role " + role.getRole());
        }

        roleService.updateRole(role);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @DeleteMapping("/role")
    public ResponseEntity<String> deleteRole(@RequestBody AvailableRoleEntity role) {

        if (!roleService.isPresent(role.getRole())) {
            throw new NoCustomerException("Role not found" + role.getRoleId());
        }

        if ("ROLE_ADMIN".equals(role.getRole())) {
            throw new NoCustomerException("no access to change this role " + role.getRole());
        }



        return new ResponseEntity<>(roleService.deleteRole(role), HttpStatus.OK);
    }
}
