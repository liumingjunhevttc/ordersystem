package com.baidu.controller;

import com.baidu.entity.Account;
import com.baidu.entity.User;
import com.baidu.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserFeign userFeign;

    @RequestMapping("/selectByPage/{currentpage}/{pagesize}")
    public Map<String,Object> selectByPage(@PathVariable("currentpage") Integer currentpage,@PathVariable("pagesize")Integer pagesize,@RequestParam("username")String username){
        return userFeign.selectByPage(currentpage,pagesize,username);
    }

    @PostMapping("/save")
    public Map<String,Object> save(@RequestBody User user){
        return userFeign.save(user);
    }

    @PostMapping("/judgeUsername")
    public Map<String,Object> judgeUsername(@RequestParam("username") String username){
        return userFeign.judgeUsername(username);
    }
    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") Integer id){
        return userFeign.findById(id);
    }

    @PostMapping("/update")
    public Map<String,Object> update(@RequestBody User user){
        return userFeign.update(user);
    }

    @PostMapping("/deleteById/{id}")
    public Map<String,Object> deleteById(@PathVariable("id") Integer id){
        return userFeign.deleteById(id);
    }

    @PostMapping("/getCurrentUser")
    public User getCurrentUser(HttpServletRequest request){
        Account account = (Account) request.getSession().getAttribute("account");
        User user = userFeign.findById(account.getId());
        return user;
    }
    @PostMapping("/resetPasword")
    public Map<String,Object> resetPasword(@RequestParam("password")String password,HttpServletRequest request){
        Account account = (Account) request.getSession().getAttribute("account");
        Map<String, Object> resultParam = userFeign.resetPasword(account, password);
        if((Integer)resultParam.get("code")==20000){
            account.setPassword(password);
            request.getSession().setAttribute("account",account);
        }
        return resultParam;
    }
}
