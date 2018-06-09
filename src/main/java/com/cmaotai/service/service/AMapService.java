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

    private static List<String> shenzhenWhite = Lists.newArrayList(
        "福田黄祠巷48号,中康路西侧",
        "银湖路6号金湖山庄D1栋",
        "黄祠巷48号",
        "皇岗路2001-2号高科利大厦A栋1、3层",
        "民田路与福华一路交叉口",
        "罗湖泥岗东路1104号",
        "红荔路1011号",
        "后海滨路与蓝天路交汇处东南侧",
        "银湖路金湖山庄西门",
        "欣景路110号",
        "文锦渡沿河北路1002号",
        "梅坂大道91号",
        "坂田街道坂雪岗大道4026号维也纳酒店",
        "坂田街道坂雪岗大道4026号维也纳酒店",
        "大浪街道华盛路134号",
        "长安镇横安路62号",
        "新秀路168号",
        "新秀路18号",
        "人民南路2008号深圳嘉里中心B1层",
        "龙华街道梅龙路与东环一路交汇处",
        "宝安北路4008号",
        "北环大道10020号");

    private static List<String> dogguanWhite = Lists.newArrayList(
        "长安镇横安路62号",
        "博美村前路16号附近",
        "虎门镇环岛路66号",
        "鸿福路90号",
        "鸿福路92号",
        "南城街道鸿福路82号",
        "南城街道鸿福路200号",
        "鸿福路盒汇商业街区3层",
        "南城街道胜和路",
        "长安镇沙头滨河路46号",
        "滨河路17号",
        "宏图路88号福威大厦601室",
        "宏图路88号福威大厦201号",
        "莞太路37号",
        "莞城街道东一路183号经贸中心",
        "南城区新基社区滨河路28号",
        "南城街道胜和路",
        "鸿福路200号",
        "运河东一路184号",
        "莞太路胜和路段18号工行大厦",
        "南城滨河路28号",
        "南城区鸿福路滨河路28号景湖湾畔1区商铺127号(沿河路与新基路交界处",
        "莞城街道东一路183号经贸中心",
        "石排镇石排大道中198号");

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
            .filter(s -> s.getAddress().indexOf(";") == -1)
            .filter(s -> s.getAddress().indexOf("附近") == -1)
//            .filter(s -> shenzhenWhite.contains(s.getAddress()))
            .collect(
                Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(getAddress("广州市黄埔区黄埔东路188号怡港花园"));
    }
}
