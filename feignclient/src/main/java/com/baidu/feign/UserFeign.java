package com.baidu.feign;

import com.baidu.entity.Account;
import com.baidu.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient("zuulServer")
public interface UserFeign {
    @PostMapping("/user-service/user/selectByPage/{currentpage}/{pagesize}")
    public Map<String,Object> selectByPage(@PathVariable("currentpage")Integer currentpage, @PathVariable("pagesize")Integer pagesize,@RequestParam("username")String username);

    @PostMapping("/user-service/user/save")
    public Map<String,Object> save(@RequestBody User user);

    @PostMapping("/user-service/user/judgeUsername")
    public Map<String,Object> judgeUsername(@RequestParam("username") String username);

    @GetMapping("/user-service/user/findById/{id}")
    public User findById(@PathVariable("id") Integer id);

    @PostMapping("/user-service/user/update")
    public Map<String,Object> update(@RequestBody User user);

    @PostMapping("/user-service/user/deleteById/{id}")
    public Map<String,Object> deleteById(@PathVariable("id") Integer id);

    @PostMapping("/user-service/user/resetPasword/{nowpassword}")
    public Map<String,Object> resetPasword(@RequestBody Account account,@PathVariable("nowpassword")String nowpassword);
}
