package com.jackson.springboot.demo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zhoujunhui
 * @description: TODO
 * @date 2021/5/10 15:50
 */
@Data
public class User {
    private Long id;
    private String name;
    private Date createTime;
}
