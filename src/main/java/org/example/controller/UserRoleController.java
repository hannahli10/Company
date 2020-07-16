package org.example.controller;

import org.example.model.Role;
import org.example.model.User;
import org.example.service.RoleService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserRoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping(value = "/roles",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> findALLRoles(){
        return roleService.getAllRoles();
    }
}
