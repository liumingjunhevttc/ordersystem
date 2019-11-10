package com.baidu.controller;

import com.baidu.entity.Account;
import com.baidu.entity.Admin;
import com.baidu.feign.AccountFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AccountFeign accountFeign;

    @GetMapping("")
    public String login(){
        return "login";
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public Map<String,Object> login(@RequestBody Account account, HttpServletRequest request){
        Map<String, Object> result = accountFeign.login(account.getUsername(), account.getPassword(), account.getType());
        if((Integer)result.get("code")==0){
            account.setId((Integer) result.get("id"));
            request.getSession().setAttribute("account", account);
        }
        result.put("type",account.getType());
        return result;
    }

    @GetMapping("/error")
    public String error(){
        System.out.println("test");
        return "404.html";
    }

    @PostMapping("/getUser")
    @ResponseBody
    public Account getUser(HttpServletRequest request){
        Account account = (Account) request.getSession().getAttribute("account");
        return account;
    }

    @PostMapping("/resetPasword")
    @ResponseBody
    public Map<String,Object> resetPasword(@RequestParam String password,HttpServletRequest request){
        Account account = (Account)request.getSession().getAttribute("account");
        Map<String, Object> param = accountFeign.resetAccountPassword(account, password);
        if((Integer)param.get("code") == 2000) {
            account.setPassword(password);
            request.getSession().setAttribute("account",account);
        }
        return param;
    }

    @PostMapping("resetSystem")
    @ResponseBody
    public Map<String,Object> resetSystem(HttpServletRequest request){
        request.getSession().removeAttribute("account");
        Map<String,Object> map = new HashMap<>();
        map.put("code",2000);
        map.put("err","success");
        return map;
    }
}
