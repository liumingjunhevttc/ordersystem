package com.baidu.handler;

import com.baidu.entity.Type;
import com.baidu.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/type")
public class TypeHandler {

    @Autowired
    private TypeRepository typeRepository;

    @PostMapping("/selectByPage/{currentpage}/{pagesize}")
    public Map<String,Object> selectByPage(@PathVariable("currentpage")Integer currentpage, @PathVariable("pagesize")Integer pagesize, @RequestParam("name")String name){
        HashMap<String,Object> param = new HashMap<>();
        param.put("name",name);
        param.put("startcount",(currentpage-1)*pagesize);
        param.put("datasize",currentpage*pagesize);
        List<Type> list = typeRepository.selectByPage(param);
        Integer total = typeRepository.selectByCount(param);
        HashMap<String,Object> resultParams = new HashMap<>();
        resultParams.put("data",list);
        resultParams.put("currentpage",currentpage);
        resultParams.put("total",total);
        return resultParams;
    }

    @PostMapping("/save")
    public Map<String,Object> save(@RequestBody Type type){
        Map<String,Object> params = new HashMap<>();
        int temp = typeRepository.save(type);
        if(temp>0){
            params.put("msg","success");
            params.put("code",20000);
        } else {
            params.put("msg","error");
            params.put("code",50000);
        }
        return params;
    }

    @PostMapping("/judgeUsername")
    public Map<String,Object> judgeUsername(@RequestParam("name") String name){
        Map<String,Object> params = new HashMap<>();
        int temp = typeRepository.judgeUsername(name);
        if(temp==0){
            params.put("code",20000);
            params.put("msg","success");
        } else {
            params.put("code",50000);
            params.put("msg","error");
        }
        return params;
    }
    @GetMapping("/findById/{id}")
    public Type findById(@PathVariable("id") Integer id) {
        return typeRepository.findById(id);
    }

    @PostMapping("/update")
    public Map<String,Object> update(@RequestBody Type type){
        Map<String,Object> params = new HashMap<>();
        int temp = typeRepository.update(type);
        if(temp>0){
            params.put("code",20000);
            params.put("msg","success");
        } else {
            params.put("code",50000);
            params.put("msg","error");
        }
        return params;
    }

    @PostMapping("/deleteById/{id}")
    public Map<String,Object> deleteById(@PathVariable("id") Integer id){
        Map<String,Object> params = new HashMap<>();
        int temp = typeRepository.deleteById(id);
        if(temp>0){
            params.put("code",20000);
            params.put("msg","success");
        } else {
            params.put("code",50000);
            params.put("msg","error");
        }
        return params;
    }

    @PostMapping("/selectAllList")
    public List<Type> selectAllList(){
        return typeRepository.selectAllList();
    }
}
