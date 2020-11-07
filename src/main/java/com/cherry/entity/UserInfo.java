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
public class UserInfo {

    @ExcelProperty("家庭（住所）成员")
    private String member;

    @ExcelProperty("公民身份证号码（18位）")
    private String IDnum;

    @ExcelProperty("外出成员现住地（仅外出成员填写）")
    private String address;

    @ExcelProperty("手机号码")
    private String phoneNum;

    @ExcelProperty("户主")
    private String houseHold;

    @ExcelProperty("是否外出")
    private String isOut;

    @ExcelProperty("外出人员详细地址")
    private String detailAddress;

    @ExcelProperty("备注")
    private String remark;

}
