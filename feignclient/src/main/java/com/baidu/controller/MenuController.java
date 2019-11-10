package com.baidu.controller;

import com.baidu.entity.Menu;
import com.baidu.entity.Type;
import com.baidu.feign.MenuFeign;
import com.baidu.feign.TypeFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuFeign menuFeign;

    @PostMapping("/selectByPage/{currentpage}/{pagesize}")
    public Map<String,Object> selectByPage(@PathVariable("currentpage") Integer currentpage, @PathVariable("pagesize")Integer pagesize, @RequestParam("name")String name){
        return menuFeign.selectByPage(currentpage,pagesize,name);
    }
    @PostMapping("/save")
    public Map<String,Object> save(@RequestBody Menu menu){
        return menuFeign.save(menu);
    }

    @PostMapping("/judgeUsername")
    public Map<String,Object> judgeUsername(@RequestParam("name") String name){
        return menuFeign.judgeUsername(name);
    }
    @GetMapping("/findById/{id}")
    public Menu findById(@PathVariable("id") Integer id){
        return menuFeign.findById(id);
    }

    @PostMapping("/update")
    public Map<String,Object> update(@RequestBody Menu menu){
        return menuFeign.update(menu);
    }

    @PostMapping("/deleteById/{id}")
    public Map<String,Object> deleteById(@PathVariable("id") Integer id){
        return menuFeign.deleteById(id);
    }
}
