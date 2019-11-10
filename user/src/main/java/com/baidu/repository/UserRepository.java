package com.baidu.repository;


import com.baidu.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserRepository {
    public List<User> selectByPage(HashMap<String,Object> params);
    public Integer selectByCount(Map<String,Object> params);
    public int save(User user);
    public int judgeUsername(String username);
    public User findById(Integer id);
    public int update(User user);
    public int deleteById(Integer id);
    public int resetPasword(@Param("id") Integer id,@Param("password") String password);
}
