package com.cherry.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhangpcxy@163.com
 * @create 2018/7/9 10:00
 * @desc
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 666534008757750297L;

    private Integer id;

    private String  name;

    private String sex;

}
