package com.cmaotai.service.service;

import com.alibaba.fastjson.JSON;
import com.cmaotai.service.address.AMapAddress;
import com.cmaotai.service.address.AMapAddress.AMapAddressTip;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class AMapService {

    private static List<String> blacks = Lists
        .newArrayList("49路;73路",
            "5路;6路;7路;19路;32路;33路;35路;41路;47路;52路;53路;69路;75路;81路;82路;202路;203路;248路;307路;601路;夜间9路",
            "34路;46路;221路;261路;308路;312路;313路;320路", "57路;292路;B1路;B2路;B3路;B4路;B224路;B236路;B259路;B267路", "(在建2号线",
            "4路;8路;14路;27路;39路;63路");

    private static List<String> whiteBlack = Lists.newArrayList(
        "福田黄祠巷48号,中康路西侧",
        "银湖路6号金湖山庄D1栋",
        "黄祠巷48号",
        "皇岗路2001-2号高科利大厦A栋1、3层",
        "民田路与福华一路交叉口",
        "罗湖泥岗东路1104号",
        "红荔路1011号",
        "后海滨路与蓝天路交汇处东南侧",
        "银湖路金湖山庄西门",
        "欣景路110号", "文锦渡沿河北路1002号","梅坂大道91号","坂田街道坂雪岗大道4026号维也纳酒店","坂田街道坂雪岗大道4026号维也纳酒店");

    public static List<AMapAddressTip> getAddress(String keywords) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
            "http://restapi.amap.com//v3/assistant/inputtips?s=rsv3&key=462f27e00614a30baa9ed1864455213f&city=&callback=jsonp_90668_&platform=JS&logversion=2.0&sdkversion=1.4.0&appname=https://www.cmaotai.com/ysh5/page/PersonalCenter/Address/addAddress.html?datas=2&csid=0CD0AA58-2574-4BFB-991F-ABD5CB0E6FFB&keywords="
                + keywords,
            String.class);
        String address = response.getBody().replace("jsonp_90668_(", "").replace(")", "");
        return JSON.parseObject(address, AMapAddress.class).getTips().stream()
            .filter(tip -> Strings.isNotBlank(tip.getAddress()) && !"[]".equals(tip.getAddress())
                && tip.getAddress().length() < 100)
            .filter(s -> !blacks.contains(s.getAddress()))
            .filter(s -> s.getAddress().indexOf(";") == -1).collect(
                Collectors.toList());
    }
}
