package com.sh.user.controller;

import com.sh.user.model.dto.UserDto;
import com.sh.user.model.service.UserService;

public class UserController {
    private UserService userService = new UserService();

    public UserDto handleLogin(int id,String password ) {
        return userService.handleLogin(id, password);
    }
}
