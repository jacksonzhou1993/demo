package com.jackson.springboot.demo.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zhoujunhui
 * @description: TODO
 * @date 2021/5/26 10:07
 */
@Data
public class Order {
    private Long id;
    private Long taskId;
    private String name;
    private Date createTime;
    private Integer version;
}