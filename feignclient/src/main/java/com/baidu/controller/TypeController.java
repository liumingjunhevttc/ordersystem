package com.baidu.controller;

import com.baidu.entity.Type;
import com.baidu.feign.TypeFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeFeign typeFeign;

    @PostMapping("/selectByPage/{currentpage}/{pagesize}")
    public Map<String,Object> selectByPage(@PathVariable("currentpage") Integer currentpage, @PathVariable("pagesize")Integer pagesize, @RequestParam("name")String name){
        return typeFeign.selectByPage(currentpage,pagesize,name);
    }
    @PostMapping("/save")
    public Map<String,Object> save(@RequestBody Type type){
        return typeFeign.save(type);
    }

    @PostMapping("/judgeUsername")
    public Map<String,Object> judgeUsername(@RequestParam("name") String name){
        return typeFeign.judgeUsername(name);
    }
    @GetMapping("/findById/{id}")
    public Type findById(@PathVariable("id") Integer id){
        return typeFeign.findById(id);
    }

    @PostMapping("/update")
    public Map<String,Object> update(@RequestBody Type type){
        return typeFeign.update(type);
    }

    @PostMapping("/deleteById/{id}")
    public Map<String,Object> deleteById(@PathVariable("id") Integer id){
        return typeFeign.deleteById(id);
    }

    @PostMapping("/selectAllList")
    public List<Type> selectAllList(){
        return typeFeign.selectAllList();
    }
}
