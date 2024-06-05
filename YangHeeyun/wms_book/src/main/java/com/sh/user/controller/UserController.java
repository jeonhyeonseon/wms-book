package com.sh.user.controller;

import com.sh.common.AuthManager;
import com.sh.user.model.dto.UserDto;
import com.sh.user.service.UserService;
import com.sh.user.view.ResultView;

public class UserController {
    private UserService userService = new UserService();

    /**
     * 로그인 처리 메소드
     * - 로그인에 성공하면 AuthManager.login(UserDto)를 통해 메모리상에서 사용자객체를 관리한다.
     * - 로그인에 실패하면 로그인 처리를 하지 않는다.
     *
     * 리턴값은 필요시에 활요할 수 있다.
     * @param id
     * @param password
     */


    public UserDto login(int id, String password) {
            // 1. UserService에 로그인을 요청후 UserDto를 반환받는다.
            UserDto userDto = userService.login(id,password);

            // 2. 로그인 결과 처리
            if(userDto != null)
                // 로그인 성공
                AuthManager.login(userDto);
            else {
                // 로그인 실패( userDto == null 반환시)
            }
            // 3. 로그인 결과 출력
            ResultView.checkUser(userDto);
            return userDto;
    }
}
