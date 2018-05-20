package com.cmaotai.service.service;

import com.cmaotai.service.address.Address;
import com.cmaotai.service.list.CMaotaiList;
import com.cmaotai.service.mobile.Invoice;
import com.cmaotai.service.model.CMaotaiOrderStatus;
import com.cmaotai.service.model.CMotaiDefaultAddress;
import com.google.common.collect.Lists;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public interface CMaotaiService {

    String CMAOTAI_URL = "https://www.cmaotai.com/API/Servers.ashx?";
    String CMAOTAI_YSAPP_URL = "https://www.cmaotai.com/YSApp_API/YSAppServer.ashx?";
    List<String> USERAGENTS = Lists.newArrayList(
        "Mozilla/5.0 (iPhone; CPU iPhone OS 11_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E216[iphone/1.0.23/6bcfa8eca598d009a9b6d43861783eee/63e60631de817fb5c28e35b95298411a]",
        "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_5 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Mobile/13G36[iphone/1.0.23/7b114f75dcb98d35c68ccef4007b3210/b079c2e3e943108643ca0387da836b09]",
        "Mozilla/5.0 (iPhone; CPU iPhone OS 10_3_2 like Mac OS X) AppleWebKit/603.2.4 (KHTML, like Gecko) Mobile/14F89[iphone/1.0.23/c1d1f2a823e0064dc865e6a2ad9386e9/60cb8e6580055022ee6d5fad77ab39aa]",
        "Mozilla/5.0 (iPhone; CPU iPhone OS 11_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E302[iphone/1.0.23/393b7b99466f0a0ceac3aa2e35a73495/dbfbf5530b25d01a432fbaf5f1f708ef]");

    default String getUA() {
        return USERAGENTS.get(Address.random(USERAGENTS.size() - 1));
    }

    HttpHeaders parseHeader(HttpHeaders httpHeaders);

    boolean defaultSignup(String mobile, String pwd) throws Exception;

    boolean invoiceSignup(String mobile, String pwd) throws Exception;

    void loginBefore();

    void login(String mobile, String pwd) throws Exception;

    List<CMaotaiOrderStatus> getYsOrderStatusCount() throws Exception;

    CMotaiDefaultAddress getDefualtAdd() throws Exception;

    boolean defaultSubmit(CMotaiDefaultAddress cMotaiDefaultAddress) throws Exception;

    boolean invoiceSubmit(CMotaiDefaultAddress cMotaiDefaultAddress) throws Exception;

    boolean logout() throws Exception;

    boolean changePassword(String oldPwd, String newPwd);

    boolean addDefaultAddress();

    int getInvoiceId() throws Exception;

    boolean createInvioceInfo() throws Exception;

    CMaotaiList getList() throws Exception;

    boolean cancel(CMaotaiList cMaotaiList) throws Exception;

    RestTemplate restTemplate = new RestTemplate();

    default ResponseEntity<String> post(String action, HttpHeaders httpHeaders) {
        return restTemplate
            .exchange(CMAOTAI_URL + action, HttpMethod.POST, new HttpEntity<String>(httpHeaders), String.class);
    }

    default ResponseEntity<String> ysPost(String action, HttpHeaders httpHeaders) {
        return restTemplate
            .exchange(CMAOTAI_YSAPP_URL + action, HttpMethod.POST, new HttpEntity<String>(httpHeaders), String.class);
    }

    default ResponseEntity<String> get(String action) {
        return restTemplate.getForEntity(CMAOTAI_URL + action, String.class);
    }
}

