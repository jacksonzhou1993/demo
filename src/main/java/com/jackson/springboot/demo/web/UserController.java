package com.jackson.springboot.demo.web;

import com.alibaba.fastjson.JSON;
import com.jackson.springboot.demo.entity.User;
import com.jackson.springboot.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author zhoujunhui
 * @description: TODO
 * @date 2021/5/10 15:58
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    @ResponseBody
    public String getAll(){
        log.info("get==================");
        List<User> list = userService.query();
        return JSON.toJSONString(list);
    }

    @GetMapping("/getById/{id}")
    @ResponseBody
    public String getById(@PathVariable("id") Long id){
        log.info("id={}",id);
        return JSON.toJSONString(userService.queryById(id));
    }
}
