package com.jackson.springboot.demo.web;

import com.alibaba.fastjson.JSON;
import com.jackson.springboot.demo.entity.Order;
import com.jackson.springboot.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Random;

/**
 * @author zhoujunhui
 * @description: TODO
 * @date 2021/5/26 10:06
 */
@Controller
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ResponseBody
    @GetMapping("insert")
    public String insert(){
        Random r = new Random();

        for(int i = 1;i <= 8;i++){
            Order order = new Order();
            order.setId(Long.valueOf(i)+r.nextInt(1000));
            order.setTaskId(Long.valueOf(i));
            order.setName("zhang-"+i);
            order.setCreateTime(new Date());
            order.setVersion(i);
            orderService.insert(order);
        }
        return "ok";
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public String getById(@PathVariable("id") Long id){
        log.info("id={}",id);
        return JSON.toJSONString(orderService.selectById(id));
    }

    @GetMapping("/getByTaskId/{taskId}")
    @ResponseBody
    public String getByTaskId(@PathVariable("taskId") Long taskId){
        log.info("taskId={}",taskId);
        return JSON.toJSONString(orderService.selectByTaskId(taskId));
    }
}
