package com.baidu.entity;

import lombok.Data;

@Data
public class Menu {
    private Integer id;
    private String name;
    private Double price;
    private String flavor;
    private Integer tid;
    private Type type;
}
