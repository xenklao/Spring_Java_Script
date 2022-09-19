package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.service.RoleService;
import web.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(RoleService roleService, UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String greetingUser(){
        return "Hi user!";
    }

}
