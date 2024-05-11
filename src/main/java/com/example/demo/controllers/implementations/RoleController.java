package com.example.demo.controllers.implementations;

import com.example.demo.controllers.interfaces.IRoleController;
import com.example.demo.dto.RoleToUserDTO;
import com.example.demo.models.Role;
import com.example.demo.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleController implements IRoleController {

    @Autowired
    private IUserService userService;

    @PostMapping("/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRole(@RequestBody Role role) {
        userService.addNewRole(role);
    }

    @PostMapping("/roles/addtouser")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addRoleToUser(@RequestBody RoleToUserDTO roleToUserDTO) {
        userService.addRoleToUser(roleToUserDTO.getUsername(), roleToUserDTO.getRoleName());
    }
}
