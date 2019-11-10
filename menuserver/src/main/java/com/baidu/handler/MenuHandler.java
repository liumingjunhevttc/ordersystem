package com.baidu.handler;

import com.baidu.entity.Menu;
import com.baidu.entity.Type;
import com.baidu.repository.MenuRepository;
import com.baidu.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuHandler {

    @Autowired
    private MenuRepository menuRepository;

    @PostMapping("/selectByPage/{currentpage}/{pagesize}")
    public Map<String,Object> selectByPage(@PathVariable("currentpage")Integer currentpage, @PathVariable("pagesize")Integer pagesize, @RequestParam("name")String name){
        HashMap<String,Object> param = new HashMap<>();
        param.put("name",name);
        param.put("startcount",(currentpage-1)*pagesize);
        param.put("datasize",currentpage*pagesize);
        List<Menu> list = menuRepository.selectByPage(param);
        Integer total = menuRepository.selectByCount(param);
        HashMap<String,Object> resultParams = new HashMap<>();
        resultParams.put("data",list);
        resultParams.put("currentpage",currentpage);
        resultParams.put("total",total);
        return resultParams;
    }

    @PostMapping("/save")
    public Map<String,Object> save(@RequestBody Menu menu){
        Map<String,Object> params = new HashMap<>();
        int temp = menuRepository.save(menu);
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
        int temp = menuRepository.judgeUsername(name);
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
    public Menu findById(@PathVariable("id") Integer id) {
        return menuRepository.findById(id);
    }

    @PostMapping("/update")
    public Map<String,Object> update(@RequestBody Menu menu){
        Map<String,Object> params = new HashMap<>();
        int temp = menuRepository.update(menu);
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
        int temp = menuRepository.deleteById(id);
        if(temp>0){
            params.put("code",20000);
            params.put("msg","success");
        } else {
            params.put("code",50000);
            params.put("msg","error");
        }
        return params;
    }
}
