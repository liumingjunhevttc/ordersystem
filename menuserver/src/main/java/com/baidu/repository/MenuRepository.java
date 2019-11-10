package com.baidu.repository;

import com.baidu.entity.Menu;
import com.baidu.entity.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MenuRepository {
    public List<Menu> selectByPage(HashMap<String,Object> params);
    public Integer selectByCount(Map<String,Object> params);
    public int save(Menu menu);
    public int judgeUsername(String name);
    public Menu findById(Integer id);
    public int update(Menu menu);
    public int deleteById(Integer id);
}
