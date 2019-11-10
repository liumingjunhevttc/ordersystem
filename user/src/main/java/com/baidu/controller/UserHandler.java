package com.baidu.controller;

import com.baidu.entity.Account;
import com.baidu.entity.User;
import com.baidu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/selectByPage/{currentpage}/{pagesize}")
    public Map<String,Object> selectByPage(@PathVariable("currentpage")Integer currentpage,@PathVariable("pagesize")Integer pagesize,@RequestParam("username")String username){
        HashMap<String,Object> param = new HashMap<>();
        param.put("username",username);
        param.put("startcount",(currentpage-1)*pagesize);
        param.put("datasize",currentpage*pagesize);
        List<User> list = userRepository.selectByPage(param);
        Integer total = userRepository.selectByCount(param);
        HashMap<String,Object> resultParams = new HashMap<>();
        resultParams.put("data",list);
        resultParams.put("currentpage",currentpage);
        resultParams.put("total",total);
        return resultParams;
    }

    @PostMapping("/save")
    public Map<String,Object> save(@RequestBody User user){
        user.setRegisterdate(new Date());
        Map<String,Object> params = new HashMap<>();
        int temp = userRepository.save(user);
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
    public Map<String,Object> judgeUsername(@RequestParam("username") String username){
        Map<String,Object> params = new HashMap<>();
        int temp = userRepository.judgeUsername(username);
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
    public User findById(@PathVariable("id") Integer id) {
        return userRepository.findById(id);
    }

    @PostMapping("/update")
    public Map<String,Object> update(@RequestBody User user){
        Map<String,Object> params = new HashMap<>();
        int temp = userRepository.update(user);
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
        int temp = userRepository.deleteById(id);
        if(temp>0){
            params.put("code",20000);
            params.put("msg","success");
        } else {
            params.put("code",50000);
            params.put("msg","error");
        }
        return params;
    }

    @PostMapping("/resetPasword/{nowpassword}")
    public Map<String,Object> resetPasword(@RequestBody Account account,@PathVariable("nowpassword")String nowpassword){
        Map<String,Object> params = new HashMap<>();
        int temp = userRepository.resetPasword(account.getId(),nowpassword);
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
