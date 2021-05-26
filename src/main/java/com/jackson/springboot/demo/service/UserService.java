package com.jackson.springboot.demo.service;

import com.jackson.springboot.demo.entity.User;
import com.jackson.springboot.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoujunhui
 * @description: TODO
 * @date 2021/5/10 15:49
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public List<User> query(){
        return userMapper.selectAll();
    }

    public User queryById(Long id){
        return userMapper.selectById(id);
    }


}
