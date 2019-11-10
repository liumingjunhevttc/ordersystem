package com.baidu.repository;

import com.baidu.entity.User;

public interface UserRepository {
    User judgeUsernameAndPassword(String username,String password);
}
