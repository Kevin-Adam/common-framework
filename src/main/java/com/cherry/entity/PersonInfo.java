package com.cherry.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author zhangpcxy@163.com
 * @version 1.0
 * @create 2020/11/7 17:03
 * @desc
 */
@Data
public class PersonInfo {

    @ExcelProperty("用户")
    private String users;

    @ExcelProperty("提交时间")
    private String submitDate;

    @ExcelProperty("来源")
    private String source;

    @ExcelProperty("姓名")
    private String userName;

    @ExcelProperty("身份证号码")
    private String IDnum;

    @ExcelProperty("手机号码")
    private String phoneNum;

    @ExcelProperty("是否外出")
    private String isOut;

    @ExcelProperty("外出人员现住址（仅外出人员填写）")
    private String address;

    @ExcelProperty("外出人员详细地址")
    private String detailAddress;

    @ExcelProperty("户主姓名")
    private String houseHold;

    @ExcelProperty("所属组")
    private String group;

    @ExcelProperty("备注")
    private String remark;

    //来源	姓名	身份证号码	手机号码	是否外出	外出人员现住址（仅外出人员填写）	外出人员详细地址	户主姓名	所属组	备注
}