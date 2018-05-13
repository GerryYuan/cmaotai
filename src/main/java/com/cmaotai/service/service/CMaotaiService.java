package com.cmaotai.service.service;

import com.cmaotai.service.model.CMaotaiOrderStatus;
import com.cmaotai.service.model.CMotaiDefaultAddress;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public interface CMaotaiService {

    String CMAOTAI_URL = "https://www.cmaotai.com/API/Servers.ashx?";

    String UA = "Mozilla/5.0 (iPhone; CPU iPhone OS 11_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E216[iphone/1.0.23/6bcfa8eca598d009a9b6d43861783eee/63e60631de817fb5c28e35b95298411a]";
//    List<String> USERAGENTS = Lists.newArrayList(
//        "Mozilla/5.0 (iPhone; CPU iPhone OS 11_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E216[iphone/1.0.23/6bcfa8eca598d009a9b6d43861783eee/63e60631de817fb5c28e35b95298411a]",
//        "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_5 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Mobile/13G36[iphone/1.0.23/7b114f75dcb98d35c68ccef4007b3210/b079c2e3e943108643ca0387da836b09]");

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

