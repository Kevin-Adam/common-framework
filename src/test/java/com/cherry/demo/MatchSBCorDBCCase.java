package com.cherry.demo;

import org.junit.Test;

/**
 * @author zhangpcxy@163.com
 * @create 2018/7/24 14:31
 * @desc Java如何判断字符串中包含有全角, 半角符号
 */
public class MatchSBCorDBCCase {

    /**
     * 输出所有的字符编码来确定全半角范围
     */
    @Test
    public void testRange() {
        for (int i = Character.MIN_VALUE; i <= Character.MAX_VALUE; ++i) {
            System.out.println(i + "    " + (char) i);
        }
    }

    /**
     * 使用正则表达式
     */
    @Test
    public void testByRegular() {
        // 纯半角，包含有数字，字母，特殊符号，空格，汉字
        String test = "0123456789abcde!@#$%^& 你好";
        char[] chars_test = test.toCharArray();
        for (int i = 0; i < chars_test.length; i++) {
            String temp = String.valueOf(chars_test[i]);
            // 判断是全角字符
            if (temp.matches("[^\\x00-\\xff]")) {
                System.out.println("全角   " + temp);
            }
            // 判断是半角字符
            else {
                System.out.println("半角    " + temp);
            }
        }
    }

    /**
     * 去除字符串中汉字
     */
    @Test
    public void testChinese() {
        String ss = "qwen你ra是sd谁f";
        System.out.println(ss.replaceAll("[\u4e00-\u9fa5]", ""));
    }

    /**
     *截取出字符串中的汉字
     */
    @Test
    public void testGetChinese() {
        String s = "qwen你ra是sd谁f";
        char[] chars_ss = s.toCharArray();
        String test = "";
        for (int i = 0; i < chars_ss.length; i++) {
            String temp = String.valueOf(chars_ss[i]);
            // 判断是汉字
            if (temp.matches("[\u4e00-\u9fa5]")) {
                test += temp;
            }
        }
        System.out.println(test);
    }

    /**
     * 使用字符的unicode码进行判断
     */
    @Test
    public void testUnicode(){
        // 纯半角，包含有数字，字母，特殊符号，空格，汉字
        String test = "0123456789abcde!@#$%^& 你好";
        // 首先将汉字用空格替换掉
        test = test.replaceAll("[\u4e00-\u9fa5]", "");
        char[] chars_test = test.toCharArray();
        for (int i = 0; i < chars_test.length; i++) {
            int charValue = (int) chars_test[i];
            // 判断是全角字符
            if (charValue >= 65281 && charValue <= 65374 || charValue == 12288) {
                System.out.println("全角  " + (char) charValue);
            }
            // 判断是半角字符
            else if (charValue >= 33 && charValue <= 126 || charValue == 32) {
                System.out.println("半角  " + (char) charValue);
            }
        }
    }
}
