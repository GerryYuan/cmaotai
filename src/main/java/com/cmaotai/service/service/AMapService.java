package com.cmaotai.service.service;

import com.alibaba.fastjson.JSON;
import com.cmaotai.service.address.AMapAddress;
import com.cmaotai.service.address.AMapAddress.AMapAddressTip;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AMapService {

    public static List<AMapAddressTip> getAddress(String keywords) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
            "http://restapi.amap.com//v3/assistant/inputtips?s=rsv3&key=462f27e00614a30baa9ed1864455213f&city=&callback=jsonp_90668_&platform=JS&logversion=2.0&sdkversion=1.4.0&appname=https://www.cmaotai.com/ysh5/page/PersonalCenter/Address/addAddress.html?datas=2&csid=0CD0AA58-2574-4BFB-991F-ABD5CB0E6FFB&keywords="
                + keywords,
            String.class);
        String address = response.getBody().replace("jsonp_90668_(", "").replace(")", "");
        return JSON.parseObject(address, AMapAddress.class).getTips().stream()
            .filter(tip -> Strings.isNotBlank(tip.getAddress()) && !"[]".equals(tip.getAddress())
                && tip.getAddress().length() < 100).collect(
                Collectors.toList());
    }
}
