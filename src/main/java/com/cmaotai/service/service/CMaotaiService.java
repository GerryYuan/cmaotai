package com.cmaotai.service.service;

import com.cmaotai.service.address.Address;
import com.cmaotai.service.mobile.Mobile;
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

    List<String> USERAGENTS = Lists.newArrayList(
        "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_5 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Mobile/13G36[iphone/1.0.23/7b114f75dcb98d35c68ccef4007b3210/b079c2e3e943108643ca0387da836b09]");

    default String getUA() {
//        int max = USERAGENTS.size() - 1;
        return USERAGENTS.get(0);
    }

    HttpHeaders parseHeader(HttpHeaders httpHeaders);

    boolean signup(String mobile, String pwd) throws Exception;

    void loginBefore();

    void login(String mobile, String pwd) throws Exception;

    boolean isLogin();

    List<CMaotaiOrderStatus> getYsOrderStatusCount() throws Exception;

    CMotaiDefaultAddress getDefualtAdd() throws Exception;

    boolean submit(CMotaiDefaultAddress cMotaiDefaultAddress) throws UnsupportedEncodingException;

    boolean changePassword(String oldPwd, String newPwd);

    boolean addDefaultAddress();

    RestTemplate restTemplate = new RestTemplate();

    default ResponseEntity<String> post(String action, HttpHeaders httpHeaders) {
        return restTemplate
            .exchange(CMAOTAI_URL + action, HttpMethod.POST, new HttpEntity<String>(httpHeaders), String.class);
    }

    default ResponseEntity<String> get(String action) {
        return restTemplate.getForEntity(CMAOTAI_URL + action, String.class);
    }
}

