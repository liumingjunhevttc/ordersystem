package com.baidu.repository;

import com.baidu.entity.Admin;

import java.util.Map;

public interface AdminReposiory {
    Admin judgeByUsernameAndPasswor(String username,String password);
    int resetPasswordByIdAndNewPassword(Integer id,String nowPassword);
}
