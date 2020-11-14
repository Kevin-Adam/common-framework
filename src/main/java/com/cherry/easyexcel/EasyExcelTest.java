package com.cherry.easyexcel;

import com.alibaba.excel.EasyExcel;
import com.cherry.easyexcel.listener.PersonInfoListener;
import com.cherry.entity.PersonInfo;
import org.junit.Test;

/**
 * @author zhangpcxy@163.com
 * @version 1.0
 * @create 2020/11/7 17:01
 * @desc
 */
public class EasyExcelTest {

    @Test
    public void readAndWritePerson() {
        //String fileName = "E:/test/一二组乱序.xlsx";
        String fileName = "E:/test/三四五组乱序 (2).xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 参数一：读取的excel文件路径
        // 参数二：读取sheet的一行，将参数封装在PersonInfo实体类中
        // 参数三：读取每一行的时候会执行PersonInfoListener监听器
        EasyExcel.read(fileName, PersonInfo.class, new PersonInfoListener()).sheet().doRead();
    }

}
