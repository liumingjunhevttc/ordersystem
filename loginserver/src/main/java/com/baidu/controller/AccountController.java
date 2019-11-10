package com.baidu.controller;

import com.baidu.entity.Account;
import com.baidu.entity.User;
import com.baidu.repository.AdminReposiory;
import com.baidu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminReposiory adminReposiory;

    @PostMapping("/login/{username}/{passsword}/{type}")
    public Map<String,Object> login(@PathVariable("username")String username,@PathVariable("passsword") String passsword,@PathVariable("type")String type){
        Account account = null;
        switch (type){
            case "user":
                account = userRepository.judgeUsernameAndPassword(username, passsword);
                break;
            case "admin":
                account = adminReposiory.judgeByUsernameAndPasswor(username,passsword);
                break;
        }
        HashMap<String,Object> map = new HashMap<String, Object>();
        if(account!=null){
            map.put("msg","success");
            map.put("code",0);
            map.put("id", account.getId());
        } else {
            map.put("msg","error");
            map.put("code",403);
        }
        return map;
    }

    @PostMapping("/resetAccountPassword/{nowpassword}")
    public Map<String,Object> resetAccountPassword(@RequestBody Account account,@PathVariable("nowpassword") String nowpassword){
        HashMap<String,Object> map = new HashMap<>();
        int temp = adminReposiory.resetPasswordByIdAndNewPassword(account.getId(), nowpassword);
        if(temp>0){
            map.put("msg","success");
            map.put("code",2000);
        } else {
            map.put("msg","error");
            map.put("code",5000);
        }
        return map;
    }
}
