package com.baidu.controller;

import com.baidu.entity.Order;
import com.baidu.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/orderDishes/{uid}/{mid}")
    public Map<String,Object> orderDishes(@PathVariable("uid")Integer uid,@PathVariable("mid")Integer mid){
        Map<String,Object> param = new HashMap<>();
        Order order = new Order();
        order.setUid(uid);
        order.setMid(mid);
        order.setDate(new Date());
        int temp = orderRepository.orderDishes(order);
        if(temp>0){
            param.put("code",2000);
            param.put("msg","success");
        } else {
            param.put("code",5000);
            param.put("msg","error");
        }
        return param;
    }
    @PostMapping("/selectByPage/{currentpage}/{pagesize}/{state}")
    public Map<String,Object> selectByPage(@PathVariable("state")String state,@PathVariable("currentpage")Integer currentpage, @PathVariable("pagesize")Integer pagesize, @RequestParam("name")String name,@RequestParam("uid")Integer uid){
        Map<String,Object> params = new HashMap<>();
        params.put("name",name);
        params.put("startpage",(currentpage-1)*pagesize);
        params.put("datasize",currentpage*pagesize);
        params.put("uid",uid);
        params.put("state",state);
        List<Order> orders = orderRepository.selectByPage(params);
        int total = orderRepository.selectByCount(params);
        Map<String,Object> resultParams = new HashMap<>();
        resultParams.put("data",orders);
        resultParams.put("total",total);
        return resultParams;
    }
    @PostMapping("/selectQlByPage/{currentpage}/{pagesize}/{state}")
    public Map<String,Object> selectQlByPage(@PathVariable("state")String state,@PathVariable("currentpage")Integer currentpage, @PathVariable("pagesize")Integer pagesize, @RequestParam("name")String name){
        Map<String,Object> params = new HashMap<>();
        params.put("name",name);
        params.put("startpage",(currentpage-1)*pagesize);
        params.put("datasize",currentpage*pagesize);
        params.put("state",state);
        List<Order> orders = orderRepository.selectByPage(params);
        int total = orderRepository.selectByCount(params);
        Map<String,Object> resultParams = new HashMap<>();
        resultParams.put("data",orders);
        resultParams.put("total",total);
        return resultParams;
    }
    @PostMapping("/deleteOrder/{id}")
    public Map<String,Object> deleteOrder(@PathVariable("id")Integer id){
        Map<String,Object> param = new HashMap<>();
        int temp = orderRepository.deleteOrder(id);
        if(temp>0){
            param.put("code",2000);
            param.put("msg","success");
        } else {
            param.put("code",5000);
            param.put("msg","error");
        }
        return param;
    }
    @PostMapping("/receiveOrder/{id}")
    public Map<String,Object> receiveOrder(@PathVariable("id")Integer id){
        Map<String,Object> param = new HashMap<>();
        int temp = orderRepository.receiveOrder(id);
        if(temp>0){
            param.put("code",2000);
            param.put("msg","success");
        } else {
            param.put("code",5000);
            param.put("msg","error");
        }
        return param;
    }

    @PostMapping("/sendOrder/{id}")
    public Map<String,Object> sendOrder(@PathVariable("id")Integer id){
        Map<String,Object> param = new HashMap<>();
        int temp = orderRepository.sendOrder(id);
        if(temp>0){
            param.put("code",2000);
            param.put("msg","success");
        } else {
            param.put("code",5000);
            param.put("msg","error");
        }
        return param;
    }

    @PostMapping("/receiveOrderObj/{id}")
    public Map<String,Object> receiveOrderObj(@PathVariable("id")Integer id){
        Map<String,Object> param = new HashMap<>();
        int temp = orderRepository.receiveOrderObj(id);
        if(temp>0){
            param.put("code",2000);
            param.put("msg","success");
        } else {
            param.put("code",5000);
            param.put("msg","error");
        }
        return param;
    }
}
