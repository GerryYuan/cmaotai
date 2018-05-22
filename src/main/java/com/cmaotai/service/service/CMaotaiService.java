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
        "Mozilla/5.0 (Linux; Android 5.1; OPPO R9m Build/LMY47I; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/43.0.2357.121 Mobile Safari/537.36[android/1.0.23/35facc15ca64a3bb85c81875cde35d5c/84afe039b858896b6c41e5d811c2e920]");

    default String getUA() {
        if (USERAGENTS.size() == 1) {
            return USERAGENTS.get(0);
        }
        return USERAGENTS.get(Address.random(USERAGENTS.size()));
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

    boolean addDefaultAddress(String from);

    int getInvoiceId() throws Exception;

    boolean createInvioceInfo() throws Exception;

    CMaotaiList getList() throws Exception;

    boolean cancel() throws Exception;

    boolean cancel(Integer id) throws Exception;

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

