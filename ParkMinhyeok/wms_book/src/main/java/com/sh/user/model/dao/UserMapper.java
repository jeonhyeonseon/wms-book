package com.sh.user.model.dao;

import com.sh.user.model.dto.UserDto;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    UserDto handleLogin(@Param("userId") int id, @Param("password") String password);
}
