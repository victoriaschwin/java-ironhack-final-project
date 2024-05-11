package com.example.demo.controllers.interfaces;

import com.example.demo.dto.RoleToUserDTO;
import com.example.demo.models.Role;

public interface IRoleController {

    void saveRole(Role role);
    void addRoleToUser(RoleToUserDTO roleToUserDTO);

}
