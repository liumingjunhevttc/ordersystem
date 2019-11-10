package com.baidu.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User extends Account{
    private String nickname;
    private String gender;
    private String telephone;
    private String registerdatestr;
    private Date registerdate;
    private String address;
}
