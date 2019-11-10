package com.baidu.controller;

import com.baidu.entity.Account;
import com.baidu.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderFeign orderFeign;

    @PostMapping("/orderDishes/{mid}")
    public Map<String,Object> orderDishes(@PathVariable("mid")Integer mid, HttpServletRequest request){
        Account account = (Account)request.getSession().getAttribute("account");
        return orderFeign.orderDishes(account.getId(),mid);
    }
    @PostMapping("/selectByPage/{currentpage}/{pagesize}/{state}")
    public Map<String,Object> selectByPage(@PathVariable("state")String state,@PathVariable("currentpage")Integer currentpage, @PathVariable("pagesize")Integer pagesize, @RequestParam("name")String name,HttpServletRequest request){
        Account account = (Account)request.getSession().getAttribute("account");
        return orderFeign.selectByPage(state, currentpage, pagesize, name,account.getId());
    }
    @PostMapping("/selectQlByPage/{currentpage}/{pagesize}/{state}")
    public Map<String,Object> selectQlByPage(@PathVariable("state")String state,@PathVariable("currentpage")Integer currentpage, @PathVariable("pagesize")Integer pagesize, @RequestParam("name")String name){
        return orderFeign.selectQlByPage(state, currentpage, pagesize, name);
    }
    @PostMapping("/deleteOrder/{id}")
    public Map<String,Object> deleteOrder(@PathVariable("id")Integer id){
        return orderFeign.deleteOrder(id);
    }

    @PostMapping("/receiveOrder/{id}")
    public Map<String,Object> receiveOrder(@PathVariable("id")Integer id) {
        return orderFeign.receiveOrder(id);
    }

    @PostMapping("/sendOrder/{id}")
    public Map<String,Object> sendOrder(@PathVariable("id")Integer id){
        return orderFeign.sendOrder(id);
    }
    @PostMapping("/receiveOrderObj/{id}")
    public Map<String,Object> receiveOrderObj(@PathVariable("id")Integer id){
        return orderFeign.receiveOrderObj(id);
    }
}
