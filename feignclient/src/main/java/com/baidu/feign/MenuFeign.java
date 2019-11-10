package com.baidu.feign;

import com.baidu.entity.Menu;
import com.baidu.entity.Type;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("zuulServer")
public interface MenuFeign {

    @PostMapping("/menu-service/menu/selectByPage/{currentpage}/{pagesize}")
    public Map<String,Object> selectByPage(@PathVariable("currentpage") Integer currentpage, @PathVariable("pagesize") Integer pagesize, @RequestParam("name") String name);

    @PostMapping("/menu-service/menu/save")
    public Map<String,Object> save(@RequestBody Menu menu);

    @PostMapping("/menu-service/menu/judgeUsername")
    public Map<String,Object> judgeUsername(@RequestParam("name") String name);

    @GetMapping("/menu-service/menu/findById/{id}")
    public Menu findById(@PathVariable("id") Integer id);

    @PostMapping("/menu-service/menu/update")
    public Map<String,Object> update(@RequestBody Menu menu);

    @PostMapping("/menu-service/menu/deleteById/{id}")
    public Map<String,Object> deleteById(@PathVariable("id") Integer id);
}
