package com.cherry.easyexcel.listener;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.cherry.entity.PersonInfo;
import com.cherry.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhangpcxy@163.com
 * @version 1.0
 * @create 2020/11/7 17:01
 * @desc
 */
public class PersonInfoListener extends AnalysisEventListener<PersonInfo> {

    List<PersonInfo> personInfoList = new ArrayList();

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    public PersonInfoListener() {}

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     * @param context
     */
    @Override
    public void invoke(PersonInfo data, AnalysisContext context) {
        System.out.println("解析到一条数据:" + JSON.toJSONString(data));
        personInfoList.add(data);
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println(JSON.toJSONString(personInfoList));

        // step1: 按照户主进行分组
        Map<String, List<PersonInfo>> map = personInfoList.stream().collect(Collectors.groupingBy(t -> t.getHouseHold()));
        System.out.println("按户主分组"+JSON.toJSONString(map));

        List<UserInfo> userInfos = new ArrayList<>();

        // step2: 对map处理，填排序后的表
        for(Map.Entry<String, List<PersonInfo>> entry : map.entrySet()){
            System.out.println("key:"+entry.getKey());
            System.out.println("value:"+JSON.toJSONString(entry.getValue()));

            List<PersonInfo> value = entry.getValue();

            for (PersonInfo info: value) {
                UserInfo userInfo = new UserInfo();
                userInfo.setMember(info.getUserName());
                userInfo.setIDnum(info.getIDnum());
                String address = info.getAddress();
                if (address!=null && address!=""){
                    address=address.replaceAll(",", "") + "（市，区）";
                }
                userInfo.setPhoneNum(info.getPhoneNum());
                userInfo.setHouseHold(info.getHouseHold());
                userInfo.setIsOut(info.getIsOut());
                if("是".equals(info.getIsOut())){
                    userInfo.setAddress(address);
                    userInfo.setDetailAddress(info.getDetailAddress());
                }else {
                    userInfo.setAddress("宁夏回族自治区固原市隆德县（市，区）");
                    if("杨坡村一组".equals(info.getGroup().trim())){
                        userInfo.setDetailAddress("温堡乡杨坡村一组");
                    }else {
                        userInfo.setDetailAddress("温堡乡杨坡村二组");
                    }
                }
                userInfos.add(userInfo);
            }
            //不同家庭之间空一格
            //userInfos.add(new UserInfo());
        }
        //step3:排序后写入新表格
        String fileName = "E:/test/人口普查分组排序1.xlsx";
        EasyExcel.write(fileName, UserInfo.class).sheet("排序").doWrite(userInfos);
    }

}
