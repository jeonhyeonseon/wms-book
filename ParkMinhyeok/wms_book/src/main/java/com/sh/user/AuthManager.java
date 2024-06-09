package com.sh.user;

import com.sh.user.controller.UserController;
import com.sh.user.model.dto.UserDto;

public class AuthManager {
    private static UserController userController = new UserController();
    private static UserDto loginUser = null;
    public static boolean isLogin() {
//        return loginUser != null;
        return true; // 테스트를 위한 임시 로그인 처리
    }
    public static void login(int id,String password) {
        loginUser = userController.handleLogin(id, password);
    }
    public static UserDto getLoginUser() {
        return loginUser;
    }
    public static void logout() {
        loginUser = null;
    }
}
