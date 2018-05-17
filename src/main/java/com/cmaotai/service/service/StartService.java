package com.cmaotai.service.service;

import org.apache.logging.log4j.util.Strings;
import org.joda.time.DateTime;
import org.springframework.util.NumberUtils;

public class StartService {

    public void start() throws Exception {
        boolean submit = Boolean.valueOf(System.getProperty("submit"));
        boolean order = Boolean.valueOf(System.getProperty("order"));
        boolean address = Boolean.valueOf(System.getProperty("address"));
        String pwd = System.getProperty("pwd");
        String min = System.getProperty("min");
        int timer = Strings.isNotBlank(min) ? Integer.valueOf(min) : 0;
        if (Strings.isBlank(pwd)) {
            System.err.println("请在命令行中输入密码，比如-Dpwd=123456");
            return;
        }
        String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
        if (submit) {
            System.out.println(now + "，开始执行下单操作........");
            CMaotaiServiceImpl.defaultSignup(pwd, timer);
        }
        if (order) {
            System.out.println(now + "，开始执行查询订单操作........");
            CMaotaiServiceImpl.getOrderStatus(pwd);
        }
        if (address) {
            System.out.println(now + "，开始执行添加默认地址操作........");
            CMaotaiServiceImpl.addDefaultAddress(pwd);
        }
        boolean invoice = Boolean.valueOf(System.getProperty("invoice"));
        if (invoice) {
            System.out.println(now + "，开始执行添加发票操作........");
            CMaotaiServiceImpl.addInvoice(pwd);
        }
        boolean changepwd = Boolean.valueOf(System.getProperty("changepwd"));
        if (changepwd) {
            String newpwd = System.getProperty("newpwd");
            if (Strings.isBlank(newpwd)) {
                System.err.println("请在命令行中输入密码，比如-Dnewpwd=123456");
                return;
            }
            System.out.println(now + "，开始执行修改密码操作........");
            CMaotaiServiceImpl.changePwd(pwd, newpwd);
        }
    }
}
