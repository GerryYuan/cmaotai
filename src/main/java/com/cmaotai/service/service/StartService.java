package com.cmaotai.service.service;

import org.apache.logging.log4j.util.Strings;
import org.joda.time.DateTime;

public class StartService {

    public static void start() throws Exception {
        boolean submit = Boolean.valueOf(System.getProperty("submit"));
        boolean order = Boolean.valueOf(System.getProperty("order"));
        boolean address = Boolean.valueOf(System.getProperty("address"));
        String pwd = System.getProperty("pwd");
        if (Strings.isBlank(pwd)) {
            System.out.println("请在命令行中输入密码，比如-Dpwd=123456");
            return;
        }
        String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
        if (submit) {
            if (FileService.isInvoke()) {
                System.out.println(now + "，开始执行下单操作........");
                CMaotaiServiceImpl.signUp(pwd);
            }
        }
        if (order) {
            System.out.println(now + "，开始执行查询订单操作........");
            CMaotaiServiceImpl.getOrderStatus(pwd);
        }
        if(address){
            System.out.println(now + "，开始执行添加默认地址操作........");
            CMaotaiServiceImpl.addDefaultAddress(pwd);
        }
    }
}
