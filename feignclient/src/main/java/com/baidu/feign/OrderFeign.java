package com.baidu.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient("zuulServer")
public interface OrderFeign {
    @PostMapping("/order-service/order/orderDishes/{uid}/{mid}")
    public Map<String,Object> orderDishes(@PathVariable("uid")Integer uid, @PathVariable("mid")Integer mid);

    @PostMapping("/order-service/order/selectByPage/{currentpage}/{pagesize}/{state}")
    public Map<String,Object> selectByPage(@PathVariable("state")String state,@PathVariable("currentpage")Integer currentpage, @PathVariable("pagesize")Integer pagesize, @RequestParam("name")String name,@RequestParam("uid")Integer id);

    @PostMapping("/order-service/order/selectQlByPage/{currentpage}/{pagesize}/{state}")
    public Map<String,Object> selectQlByPage(@PathVariable("state")String state,@PathVariable("currentpage")Integer currentpage, @PathVariable("pagesize")Integer pagesize, @RequestParam("name")String name);

    @PostMapping("/order-service/order/deleteOrder/{id}")
    public Map<String,Object> deleteOrder(@PathVariable("id")Integer id);

    @PostMapping("/order-service/order/receiveOrder/{id}")
    public Map<String,Object> receiveOrder(@PathVariable("id")Integer id);

    @PostMapping("/order-service/order/sendOrder/{id}")
    public Map<String,Object> sendOrder(@PathVariable("id")Integer id);
    @PostMapping("/order-service/order/receiveOrderObj/{id}")
    public Map<String,Object> receiveOrderObj(@PathVariable("id")Integer id);
}
