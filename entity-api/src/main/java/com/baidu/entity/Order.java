package com.baidu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Integer id;
    private Integer uid;
    private Integer mid;
    private Integer aid;
    private Date date;
    private Integer state;
    private User user;
    private Menu menu;
    private Admin admin;
    private String statestr;
}
