package com.cmaotai.service.address;

import com.cmaotai.service.address.AMapAddress.AMapAddressTip;
import com.cmaotai.service.mobile.Mobile;
import com.cmaotai.service.model.CMotaiDefaultAddress;
import com.cmaotai.service.service.AMapService;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.Getter;
import org.apache.logging.log4j.util.Strings;

public class Address {

    private static List<CMotaiDefaultAddress> ADDRESS = Lists.newArrayList();

    private static List<String> CHONGQING_DISTRICTS = Lists.newArrayList("重庆市大渡口区春晖路", "重庆市北部新区金渝大道");

    public static String chongqingDistricts() {
        int max = CHONGQING_DISTRICTS.size() - 1;
        return CHONGQING_DISTRICTS.get(random(max));
    }

    private static List<String> WUXI_DISTRICTS = Lists
        .newArrayList("无锡市崇安区", "无锡市梁溪区", "无锡新区", "无锡锡山区", "无锡惠山区");

    private static List<String> qtys = Lists.newArrayList();

    public static void initQty(Integer start, Integer end) {
        if (start == null) {
            start = 6;
        }
        if (end == null) {
            end = 6;
        }
        for (int i = start; i <= end; i++) {
            qtys.add(i + "");
        }
    }

    public static String getQty() {
        int max = qtys.size() - 1;
        if (max == 0) {
            return qtys.get(max);
        } else {
            return qtys.get(random(max));
        }
    }

    public static String getWuXiDistricts() {
        int max = WUXI_DISTRICTS.size() - 1;
        return WUXI_DISTRICTS.get(random(max));
    }

    public static List<CMotaiDefaultAddress> chongqingAddress(int num) {
        List<AMapAddressTip> aMapAddressTips = AMapService.getAddress(chongqingDistricts());
        for (; aMapAddressTips.size() < num; ) {
            aMapAddressTips.addAll(AMapService.getAddress(chongqingDistricts()));
        }
        System.out.println(aMapAddressTips.size() + "个地址已生成");
        return aMapAddressTips.stream().map(aMapAddressTip -> {
            CMotaiDefaultAddress cMotaiDefaultAddress = new CMotaiDefaultAddress();
            cMotaiDefaultAddress.setProvinceId("500103");
            cMotaiDefaultAddress.setCityId("500103");
            cMotaiDefaultAddress.setDistrictsId(aMapAddressTip.getAdcode());
            cMotaiDefaultAddress.setAddressInfo(aMapAddressTip.getDistrict());
            cMotaiDefaultAddress.setAddress(aMapAddressTip.getAddress());
            cMotaiDefaultAddress.setShipTo(getCallName());
            cMotaiDefaultAddress.setCallPhone(getCallPhone());
            cMotaiDefaultAddress.setZipcode("000000");
            cMotaiDefaultAddress.setIsDefault("1");
            String[] ll = aMapAddressTip.getLocation().split(",");
            cMotaiDefaultAddress.setLongitude(ll[0]);
            cMotaiDefaultAddress.setLatitude(ll[1]);
            return cMotaiDefaultAddress;
        }).collect(Collectors.toList());
    }

    public static CMotaiDefaultAddress getAddress(String from) {
        return getChongqingAddress();
    }

    private static CMotaiDefaultAddress getChongqingAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(chongqingAddress(50));
        }
        int max = ADDRESS.size() - 1;
        return ADDRESS.get(random(max));
    }

    public static String getCallPhone() {
        int max = Mobile.MOBILES.size() - 1;
        return Mobile.MOBILES.get(random(max));
    }

    public static String getCallName() {
        int max = Mobile.CALL_NAMES.size() - 1;
        return Mobile.CALL_NAMES.get(random(max));
    }

    public static int random(int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - 0 + 1) + 0;
    }

}
