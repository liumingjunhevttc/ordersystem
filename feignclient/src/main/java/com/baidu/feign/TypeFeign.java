package com.baidu.feign;

import com.baidu.entity.Type;
import com.baidu.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("zuulServer")
public interface TypeFeign {

    @PostMapping("/menu-service/type/selectByPage/{currentpage}/{pagesize}")
    public Map<String,Object> selectByPage(@PathVariable("currentpage")Integer currentpage, @PathVariable("pagesize")Integer pagesize, @RequestParam("name")String name);

    @PostMapping("/menu-service/type/save")
    public Map<String,Object> save(@RequestBody Type type);

    @PostMapping("/menu-service/type/judgeUsername")
    public Map<String,Object> judgeUsername(@RequestParam("name") String name);

    @GetMapping("/menu-service/type/findById/{id}")
    public Type findById(@PathVariable("id") Integer id);

    @PostMapping("/menu-service/type/update")
    public Map<String,Object> update(@RequestBody Type type);

    @PostMapping("/menu-service/type/deleteById/{id}")
    public Map<String,Object> deleteById(@PathVariable("id") Integer id);

    @PostMapping("/menu-service/type/selectAllList")
    public List<Type> selectAllList();
}
