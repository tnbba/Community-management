package org.xhx.xuda.modules.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xhx.xuda.modules.login.entity.User;
import org.xhx.xuda.modules.login.service.UserService;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/info/{id}")
    public User info(@PathVariable Integer id) {
        return userService.queryObject(id);
    }
}
