package com.jackson.springboot.demo.service;

import com.jackson.springboot.demo.entity.Order;
import com.jackson.springboot.demo.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhoujunhui
 * @description: TODO
 * @date 2021/5/26 10:22
 */
@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;

    public int insert(Order order){
        return orderMapper.insertOne(order);
    }

    public Order selectById(Long id){
        return orderMapper.selectById(id);
    }

    public Order selectByTaskId(Long taskId){
        return orderMapper.selectByTaskId(taskId);
    }
}
