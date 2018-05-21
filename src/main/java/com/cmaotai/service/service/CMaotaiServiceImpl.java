package com.cmaotai.service.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.cmaotai.service.address.Address;
import com.cmaotai.service.list.CMaotaiList;
import com.cmaotai.service.list.CMaotaiReturn;
import com.cmaotai.service.mobile.Invoice;
import com.cmaotai.service.mobile.Mobile;
import com.cmaotai.service.model.CMaotaiOrderStatus;
import com.cmaotai.service.model.CMaotaiOrderStatus.OrderStatus;
import com.cmaotai.service.model.CMaotaiUser;
import com.cmaotai.service.model.CMotaiDefaultAddress;
import com.cmaotai.service.model.CMotaiProduct;
import com.cmaotai.service.model.DataResult;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javax.swing.JTextArea;
import org.apache.commons.math3.analysis.function.Add;
import org.apache.logging.log4j.util.Strings;
import org.joda.time.DateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class CMaotaiServiceImpl implements CMaotaiService {

    private HttpHeaders headers = new HttpHeaders();

    private Map<String, String> cookies = Maps.newHashMap();

    @Override
    public HttpHeaders parseHeader(HttpHeaders httpHeaders) {
        httpHeaders.get(HttpHeaders.SET_COOKIE).stream()
            .forEach(s -> {
                String ss = Splitter.on(";").splitToList(s).get(0);
                String[] sss = ss.split("=");
                cookies.put(sss[0], ss);
            });
        headers.put(HttpHeaders.COOKIE, cookies.values().stream().collect(Collectors.toList()));
        headers.remove(HttpHeaders.USER_AGENT);
        headers.add(HttpHeaders.USER_AGENT, getUA());
        return headers;
    }

    @Override
    public boolean defaultSignup(String mobile, String pwd) throws Exception {
        loginBefore();
        login(mobile, pwd);
        return defaultSubmit(getDefualtAdd());
    }

    @Override
    public boolean invoiceSignup(String mobile, String pwd) throws Exception {
        loginBefore();
        login(mobile, pwd);
        return invoiceSubmit(getDefualtAdd());
    }

    @Override
    public void loginBefore() {
        String action = "action=AppManager.StartPage";
        ResponseEntity<String> response = get(action);
        headers = parseHeader(response.getHeaders());
    }

    @Override
    public void login(String mobile, String pwd) throws Exception {
        long now = new Date().getTime();
        String action =
            "action=UserManager.login&tel=" + mobile + "&pwd=" + pwd + "&timestamp121=" + now;
        ResponseEntity<String> response = post(action, headers);
        DataResult<CMaotaiUser> result = JSON
            .parseObject(response.getBody(), new TypeReference<DataResult<CMaotaiUser>>() {
            });
        if (result.getCode() != 0) {
            throw new Exception("登录失败!" + result.getMsg());
        }
        headers = parseHeader(response.getHeaders());
    }

    @Override
    public List<CMaotaiOrderStatus> getYsOrderStatusCount() throws Exception {
        String action = "action=OrderManager.GetYsOrderStatusCount&timestamp121=" + new Date().getTime();
        ResponseEntity<String> response = post(action, headers);
        DataResult<List<CMaotaiOrderStatus>> dataResult = JSON
            .parseObject(response.getBody(), new TypeReference<DataResult<List<CMaotaiOrderStatus>>>() {
            });
        if (!dataResult.isState()) {
            throw new Exception("获取订单状态失败");
        }
        return dataResult.getData();
    }

    @Override
    public CMotaiDefaultAddress getDefualtAdd() throws Exception {
        String action = "action=GrabSingleManager.getDefualtAdd&timestamp121=" + new Date().getTime();
        ResponseEntity<String> response = post(action, headers);
        headers = parseHeader(response.getHeaders());
        DataResult<CMotaiDefaultAddress> dataResult = JSON
            .parseObject(response.getBody(), new TypeReference<DataResult<CMotaiDefaultAddress>>() {
            });
        if (dataResult.getData() == null) {
            throw new Exception("获取默认地址失败！");
        }
        return dataResult.getData();
    }

    @Override
    public int getInvoiceId() throws Exception {
        String action = "action=GrabSingleManager.invoiceList&timestamp121=" + new Date().getTime();
        ResponseEntity<String> response = post(action, headers);
        headers = parseHeader(response.getHeaders());
        DataResult<List<Invoice>> dataResult = JSON
            .parseObject(response.getBody(), new TypeReference<DataResult<List<Invoice>>>() {
            });
        if (dataResult.getData().size() <= 0) {
            throw new Exception("获取发票失败！");
        }
        return dataResult.getData().get(0).getId();
    }

    @Override
    public boolean createInvioceInfo() throws Exception {
        String action =
            "InvoiceType=2&InvoiceName=扬子江药业集团有限公司&TaxpayerID=913212007286987632&Address=江苏省泰州市高港区扬子江南路1号|0523-86961999&AccountLine=中行泰州惠裕支行&BankAccount=489758206195&IsDefault=1&InvoiceMail=0&action=UserManager.CreateInvioceInfo&timestamp121="
                + new Date().getTime();
        ResponseEntity<String> response = ysPost(action, headers);
        headers = parseHeader(response.getHeaders());
        DataResult<String> dataResult = JSON.parseObject(response.getBody(), new TypeReference<DataResult<String>>() {
        });
        if (!dataResult.isState()) {
            throw new Exception("添加发票失败！");
        }
        return true;
    }

    @Override
    public CMaotaiList getList() throws Exception {
        String action =
            "action=GrabSingleManager.getList&status=-1&index=1&size=10&timestamp121=" + new Date().getTime();
        ResponseEntity<String> response = post(action, headers);
        DataResult<String> dataResult = JSON.parseObject(response.getBody(), new TypeReference<DataResult<String>>() {
        });
        if (!dataResult.isState()) {
            throw new Exception("获取下单列表失败！");
        }
        headers = parseHeader(response.getHeaders());
        CMaotaiReturn<CMaotaiList> returns = JSON
            .parseObject(dataResult.getData(), new TypeReference<CMaotaiReturn<CMaotaiList>>() {
            });
        List<CMaotaiList> cMaotaiLists = returns.getData().getData();
        if (cMaotaiLists == null) {
            return null;
        }
        CMaotaiList cMaotaiList = cMaotaiLists.stream().filter(s -> s.getOrderStatus() == 99)
            .findFirst().orElse(null);
        if (cMaotaiList == null) {
            cMaotaiList = cMaotaiLists.stream().filter(s -> s.getOrderStatus() == 6)
                .findFirst().orElse(null);
        }
        return cMaotaiList;
    }

    @Override
    public boolean cancel() throws Exception {
        CMaotaiList cMaotaiList = getList();
        if (cMaotaiList == null) {
            System.out.println("取消失败！未查到相关下单信息");
            return false;
        }
        if (cMaotaiList.getOrderStatus() == 6) {
            return cancel(cMaotaiList.getId());
        }
        String action = "action=GrabSingleManager.cancel&id=" + cMaotaiList.getId() + "&pid=" + cMaotaiList.getGoodsId()
            + "&timestamp121=" + new Date().getTime();
        ResponseEntity<String> response = post(action, headers);
        DataResult<String> dataResult = JSON.parseObject(response.getBody(), new TypeReference<DataResult<String>>() {
        });
        if (!dataResult.isState()) {
            throw new Exception("取消失败！");
        }
        headers = parseHeader(response.getHeaders());
        CMaotaiReturn<CMaotaiList> returns = JSON
            .parseObject(dataResult.getData(), new TypeReference<CMaotaiReturn<CMaotaiList>>() {
            });
        if (returns.getCount() != 1) {
            throw new Exception("取消失败！");
        }
        return true;
    }

    @Override
    public boolean cancel(Integer id) throws Exception {
        if (id == null) {
            System.out.println("取消失败！未查到相关下单信息");
            return false;
        }
        String action = "action=GrabSingleManager.cancel&id=" + id + "&pid=391&timestamp121=" + new Date().getTime();
        ResponseEntity<String> response = post(action, headers);
        DataResult<String> dataResult = JSON.parseObject(response.getBody(), new TypeReference<DataResult<String>>() {
        });
        if (!dataResult.isState()) {
            throw new Exception("取消失败！");
        }
        headers = parseHeader(response.getHeaders());
        CMaotaiReturn<CMaotaiList> returns = JSON
            .parseObject(dataResult.getData(), new TypeReference<CMaotaiReturn<CMaotaiList>>() {
            });
        if (returns.getCount() != 1) {
            System.out.println("取消失败，id+1继续尝试");
            return cancel(id + 1);
        }
        return true;
    }

    @Override
    public boolean defaultSubmit(CMotaiDefaultAddress cMotaiDefaultAddress) throws Exception {
        String action =
            "action=GrabSingleManager.submit&iid=-1&qty=" + Address.getQty() + "&express=14&timestamp121=" + new Date()
                .getTime() + "&sid=" + cMotaiDefaultAddress.getSId() + "&remark=" + "&product=" + JSON
                .toJSONString(new CMotaiProduct());
        ResponseEntity<String> response = post(action, headers);
        DataResult<Integer> result = JSON.parseObject(response.getBody(), new TypeReference<DataResult<Integer>>() {
        });
        if (result.getCode() != 0) {
            throw new Exception("下单失败！" + result.getMsg());
        }
        logout();
        return true;
    }

    @Override
    public boolean invoiceSubmit(CMotaiDefaultAddress cMotaiDefaultAddress)
        throws Exception {
        String action =
            "action=GrabSingleManager.submit&iid=" + getInvoiceId() + "&qty=2&express=14&timestamp121=" + new Date()
                .getTime() + "&sid="
                + cMotaiDefaultAddress.getSId() + "&remark=" + "&product=" + JSON.toJSONString(new CMotaiProduct());
        ResponseEntity<String> response = post(action, headers);
        DataResult<Integer> result = JSON.parseObject(response.getBody(), new TypeReference<DataResult<Integer>>() {
        });
        if (result.getCode() != 0) {
            throw new Exception("下单失败！" + result.getMsg());
        }
        logout();
        return true;
    }

    @Override
    public boolean logout() throws Exception {
        String action = "action=UserManager.LogOut";
        ResponseEntity<String> response = post(action, headers);
        DataResult<String> dataResult = JSON.parseObject(response.getBody(), new TypeReference<DataResult<String>>() {
        });
        if (dataResult.getCode() != 0) {
            throw new Exception("用户注销失败");
        }
        return true;
    }

    @Override
    public boolean changePassword(String oldPwd, String newPwd) {
        String action =
            "action=UserManager.ChangePwd&newPassword=" + newPwd + "&oldPassword=" + oldPwd + "&timestamp121="
                + new Date().getTime();
        ResponseEntity<String> response = ysPost(action, headers);
        DataResult<?> dataResult = JSON.parseObject(response.getBody(), new TypeReference<DataResult>() {
        });
        return dataResult.isState();
    }

    @Override
    public boolean addDefaultAddress(String from) {
        CMotaiDefaultAddress address = Address.getAddress(from);
        String action =
            "action=AddressManager.add&provinceId=" + address.getProvinceId()
                + "&cityId=" + address.getCityId() + "&districtsId=" +
                address.getDistrictsId() + "&addressInfo=" + address.getAddressInfo()
                + "&address=" + address.getAddress() + "&shipTo=" + address.getShipTo()
                + "&callPhone=" + address.getCallPhone() + "&zipcode=" + address.getZipcode()
                + "&isDef=" + address.getIsDefault() + "&lng=" + address.getLongitude() + "&lat=" + address
                .getLatitude() + "&timestamp121="
                + new Date().getTime();
        ResponseEntity<String> response = post(action, headers);
        DataResult<?> dataResult = JSON.parseObject(response.getBody(), new TypeReference<DataResult>() {
        });
        return dataResult.isState();
    }

    public static void defaultSignup(String pwd, String path, int timer) throws IOException {
        List<String> mobiles = Files.readLines(new File(path), Charset.defaultCharset()).stream()
            .collect(Collectors.toList());
        AtomicInteger atomicInteger = new AtomicInteger(0);
        AtomicInteger num = new AtomicInteger(mobiles.size());
        List<String> failMobiles = Lists.newArrayList();
        mobiles.forEach(s -> {
            CMaotaiServiceImpl cMaotaiService = new CMaotaiServiceImpl();
            try {
                System.out.println("开始下单，手机号【" + s + "】，剩余【" + num.addAndGet(-1) + "】个");
                if (cMaotaiService.defaultSignup(s, pwd)) {
                    System.out.println("手机号【" + s + "】等记成功！");
                    atomicInteger.addAndGet(1);
                    String now = DateTime.now().toString("yyyy-MM-dd HH:mm:ss");
                    System.out.println("现在【" + now + "】,休息【" + timer + "】分钟，再继续登记哟！");
                    Thread.sleep(1000 * 60 * timer);
                } else {
                    failMobiles.add(s);
                    System.err.println("最后，手机号【" + s + "】等记失败！");
                }
            } catch (Exception e) {
                failMobiles.add(s);
                System.err.println("手机号【" + s + "】等记异常！" + e.getMessage());
            }
        });
        System.out.println(
            "登记结果：总登记【" + mobiles.size() + "】，成功【" + atomicInteger.get() + "】,失败【" + (mobiles.size() - atomicInteger
                .get()) + "】");
        if (failMobiles.size() > 0) {
            System.out.println("登记失败手机号：" + failMobiles);
        }
    }

    @Deprecated
    private static void invoiceSignup(String pwd) throws IOException {
        String path = System.getProperty("path");
        if (Strings.isBlank(path)) {
            System.out.println("请在命令行中输入路劲，比如-Dpath=http");
        }
        List<String> mobiles = Files.readLines(new File(path), Charset.defaultCharset()).stream()
            .filter(Strings::isNotBlank).collect(
                Collectors.toList());
        AtomicInteger atomicInteger = new AtomicInteger(0);
        List<String> failMobiles = Lists.newArrayList();
        mobiles.forEach(s -> {
            CMaotaiServiceImpl cMaotaiService = new CMaotaiServiceImpl();
            try {
                System.out.println("开始，登记手机号【" + s + "】");
                if (cMaotaiService.invoiceSignup(s, pwd)) {
                    System.out.println("最后，手机号【" + s + "】等记成功！");
                    atomicInteger.addAndGet(1);
                } else {
                    failMobiles.add(s);
                    System.err.println("最后，手机号【" + s + "】等记失败！");
                }
            } catch (Exception e) {
                failMobiles.add(s);
                System.err.println("手机号【" + s + "】等记异常！" + e.getMessage());
            }
        });
        System.out.println(
            "登记结果：总登记【" + mobiles.size() + "】，成功【" + atomicInteger.get() + "】,失败【" + (mobiles.size() - atomicInteger
                .get()) + "】");
        if (failMobiles.size() > 0) {
            System.out.println("登记失败手机号：" + failMobiles);
        }
    }

    public static void getOrderStatus(String pwd, String path) throws IOException {
        List<String> mobiles = Files.readLines(new File(path), Charset.defaultCharset()).stream()
            .filter(Strings::isNotBlank).collect(
                Collectors.toList());
        AtomicInteger WAIT_PAY = new AtomicInteger(0);
        AtomicInteger WAIT_DELIVER_GOODS = new AtomicInteger(0);
        AtomicInteger WAIT_CONFIRMATION_GOODS = new AtomicInteger(0);
        AtomicInteger num = new AtomicInteger(mobiles.size());
        List<String> WAIT_PAYMobile = Lists.newArrayList();
        List<String> WAIT_DELIVER_GOODSMobile = Lists.newArrayList();
        List<String> WAIT_CONFIRMATION_GOODSMobile = Lists.newArrayList();
        System.out.println("开始查单操作，需要花费一段时间，请等待......");
        mobiles.forEach(s -> {
            System.out.println("【" + s + "】查单中，剩余【" + num.addAndGet(-1) + "】个。");
            CMaotaiServiceImpl cMaotaiService = new CMaotaiServiceImpl();
            cMaotaiService.loginBefore();
            try {
                cMaotaiService.login(s, pwd);
                List<CMaotaiOrderStatus> cMaotaiOrderStatuses = cMaotaiService.getYsOrderStatusCount();
                if (cMaotaiOrderStatuses.size() <= 0) {
                    return;
                }
                cMaotaiOrderStatuses.forEach(ss -> {
                    if (OrderStatus.WAIT_PAY.getCode() == ss.getOrderStatus()) {
                        WAIT_PAY.addAndGet(ss.getOrderStatusNum());
                        WAIT_PAYMobile.add(s);
                    }
                    if (OrderStatus.WAIT_DELIVER_GOODS.getCode() == ss.getOrderStatus()) {
                        WAIT_DELIVER_GOODS.addAndGet(ss.getOrderStatusNum());
                        WAIT_DELIVER_GOODSMobile.add(s);
                    }
                    if (OrderStatus.WAIT_CONFIRMATION_GOODS.getCode() == ss.getOrderStatus()) {
                        WAIT_CONFIRMATION_GOODS.addAndGet(ss.getOrderStatusNum());
                        WAIT_CONFIRMATION_GOODSMobile.add(s);
                    }
                });
            } catch (Exception e) {
                System.err.println("手机号【" + s + "】登录异常！" + e.getMessage());
            }
        });
        System.out.println(
            "查询订单结果，总账号数：【" + mobiles.size() + "】：待支付【" + WAIT_PAY.get() + "】，待发货【" + WAIT_DELIVER_GOODS.get()
                + "】,待确认收货【" + WAIT_CONFIRMATION_GOODS.get() + "】");
        System.out.println("待支付手机号：" + WAIT_PAYMobile);
        System.out.println("待发货手机号：" + WAIT_DELIVER_GOODSMobile);
        System.out.println("待确认收货：" + WAIT_CONFIRMATION_GOODSMobile);
    }

    public static void changePwd(String path, String pwd, String newPwd) throws IOException {
        List<String> mobiles = Files.readLines(new File(path), Charset.defaultCharset()).stream()
            .filter(Strings::isNotBlank).collect(
                Collectors.toList());
        List<String> failMobiles = Lists.newArrayList();
        AtomicInteger succ = new AtomicInteger(0);
        mobiles.forEach(s -> {
            CMaotaiServiceImpl cMaotaiService = new CMaotaiServiceImpl();
            cMaotaiService.loginBefore();
            try {
                cMaotaiService.login(s, pwd);
                if (cMaotaiService.changePassword(pwd, newPwd)) {
                    succ.addAndGet(1);
                    System.out.println("手机号【" + s + "】修改密码成功!");
                } else {
                    failMobiles.add(s);
                    System.err.println("手机号【" + s + "】修改密码失败！");
                }
            } catch (Exception e) {
                failMobiles.add(s);
                System.err.println("手机号【" + s + "】登录异常！" + e.getMessage());
            }
        });
        System.out.println("修改结果：总修改【" + mobiles.size() + "】，成功【" + succ.get() + "】,失败【" + failMobiles.size() + "】");
        if (failMobiles.size() > 0) {
            System.out.println("修改失败手机号：" + failMobiles);
        }
    }

    public static void addDefaultAddress(String path, String pwd, String from) throws IOException {
        List<String> mobiles = Files.readLines(new File(path), Charset.defaultCharset()).stream()
            .filter(Strings::isNotBlank).collect(
                Collectors.toList());
        List<String> failMobiles = Lists.newArrayList();
        AtomicInteger succ = new AtomicInteger(0);
        mobiles.forEach(s -> {
            CMaotaiServiceImpl cMaotaiService = new CMaotaiServiceImpl();
            cMaotaiService.loginBefore();
            try {
                cMaotaiService.login(s, pwd);
                if (cMaotaiService.addDefaultAddress(from)) {
                    succ.addAndGet(1);
                    System.out.println("手机号【" + s + "】默认地址添加成功!");
                } else {
                    failMobiles.add(s);
                    System.err.println("手机号【" + s + "】默认地址添加失败！");
                }
            } catch (Exception e) {
                failMobiles.add(s);
                System.err.println("手机号【" + s + "】登录异常！" + e.getMessage());
            }
        });
        System.out.println("添加结果：总添加【" + mobiles.size() + "】，成功【" + succ.get() + "】,失败【" + failMobiles.size() + "】");
        if (failMobiles.size() > 0) {
            System.out.println("添加失败手机号：" + failMobiles);
        }
    }

    protected static void addInvoice(String pwd) throws IOException {
        String path = System.getProperty("path");
        if (Strings.isBlank(path)) {
            System.out.println("请在命令行中输入路劲，比如-Dpath=http");
        }
        List<String> mobiles = Files.readLines(new File(path), Charset.defaultCharset()).stream()
            .filter(Strings::isNotBlank).collect(
                Collectors.toList());
        List<String> failMobiles = Lists.newArrayList();
        AtomicInteger succ = new AtomicInteger(0);
        mobiles.forEach(s -> {
            CMaotaiServiceImpl cMaotaiService = new CMaotaiServiceImpl();
            cMaotaiService.loginBefore();
            try {
                cMaotaiService.login(s, pwd);
                if (cMaotaiService.createInvioceInfo()) {
                    succ.addAndGet(1);
                    System.out.println("手机号【" + s + "】发票添加成功!");
                } else {
                    failMobiles.add(s);
                    System.err.println("手机号【" + s + "】发票添加失败！");
                }
            } catch (Exception e) {
                failMobiles.add(s);
                System.err.println("手机号【" + s + "】异常！" + e.getMessage());
            }
        });
        System.out.println("添加结果：总添加【" + mobiles.size() + "】，成功【" + succ.get() + "】,失败【" + failMobiles.size() + "】");
        if (failMobiles.size() > 0) {
            System.out.println("添加失败的手机号：" + failMobiles);
        }
    }

    public static void cancel(String pwd, String path) throws IOException {
        List<String> mobiles = Files.readLines(new File(path), Charset.defaultCharset()).stream()
            .filter(Strings::isNotBlank).collect(
                Collectors.toList());
        AtomicInteger succ = new AtomicInteger(0);
        List<String> failMobiles = Lists.newArrayList();
        mobiles.forEach(s -> {
            CMaotaiServiceImpl cMaotaiService = new CMaotaiServiceImpl();
            cMaotaiService.loginBefore();
            try {
                cMaotaiService.login(s, pwd);
                if (cMaotaiService.cancel()) {
                    succ.addAndGet(1);
                    System.out.println("手机号【" + s + "】修复成功!");
                } else {
                    failMobiles.add(s);
                    System.err.println("手机号【" + s + "】修复失败！");
                }
            } catch (Exception e) {
                failMobiles.add(s);
                System.err.println("手机号【" + s + "】异常！" + e.getMessage());
            }
        });
        System.out.println("修复结果：总修复【" + mobiles.size() + "】，成功【" + succ.get() + "】,失败【" + failMobiles.size() + "】");
        if (failMobiles.size() > 0) {
            System.out.println("修复失败的手机号：" + failMobiles);
        }
    }

}
