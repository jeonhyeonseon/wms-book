package com.sh.user.model.service;

import com.sh.user.model.dao.UserMapper;
import com.sh.user.model.dto.UserDto;
import org.apache.ibatis.session.SqlSession;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class UserService {
    public UserDto handleLogin(int id, String password) {
        try(SqlSession sqlSession = getSqlSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            return userMapper.handleLogin(id, password);
        }
    }
}
