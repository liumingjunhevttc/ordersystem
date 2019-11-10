package com.baidu.feign;

import com.baidu.entity.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("zuulServer")
public interface AccountFeign {

    @PostMapping("/login-service/account/login/{username}/{password}/{type}")
    Map<String,Object> login(@PathVariable("username")String username,@PathVariable("password")String password,@PathVariable("type")String type);

    @PostMapping("/login-service/account/resetAccountPassword/{nowpassword}")
    public Map<String,Object> resetAccountPassword(@RequestBody Account account, @PathVariable("nowpassword") String nowpassword);
}
