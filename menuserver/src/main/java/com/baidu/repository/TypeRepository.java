package com.baidu.repository;

import com.baidu.entity.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TypeRepository {
    public List<Type> selectByPage(HashMap<String,Object> params);
    public Integer selectByCount(Map<String,Object> params);
    public int save(Type type);
    public int judgeUsername(String username);
    public Type findById(Integer id);
    public int update(Type type);
    public int deleteById(Integer id);
    List<Type> selectAllList();
}
