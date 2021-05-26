package com.jackson.springboot.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhoujunhui
 * @description: TODO
 * @date 2021/5/10 15:44
 */
@Controller
public class HelloController {
    @RequestMapping("/index")
    public String sayHello(){
        return "index";
    }
}
