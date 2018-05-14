package com.cmaotai.service.address;

import com.cmaotai.service.address.AMapAddress.AMapAddressTip;
import com.cmaotai.service.mobile.Mobile;
import com.cmaotai.service.model.CMotaiDefaultAddress;
import com.cmaotai.service.service.AMapService;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Address {

    private static List<CMotaiDefaultAddress> ADDRESS = Lists.newArrayList();

    private static List<String> DISTRICTS = Lists
        .newArrayList("贵阳市花溪区碧云窝", "贵阳市南明区瑞金南路", "贵阳市乌当区顺新社区新添大道北段", "贵阳市云岩区北京路", "贵阳市云岩区盐务街", "贵阳市南明区花果园", "贵阳市云岩区紫林庵",
            "贵阳市南明区南厂路", "贵阳市白云区白云南路", "贵阳市南明区西湖路");

    public static String getDistricts() {
        int max = DISTRICTS.size() - 1;
        return DISTRICTS.get(random(max));
    }

    public static List<CMotaiDefaultAddress> address(int num) {
        List<AMapAddressTip> aMapAddressTips = AMapService.getAddress(getDistricts());
        for (int i = aMapAddressTips.size(); i < num; i++) {
            aMapAddressTips.addAll(AMapService.getAddress(getDistricts()));
        }
        return aMapAddressTips.stream().map(aMapAddressTip -> {
            CMotaiDefaultAddress cMotaiDefaultAddress = new CMotaiDefaultAddress();
            cMotaiDefaultAddress.setProvinceId("520000");
            cMotaiDefaultAddress.setCityId("520000");
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

    public static CMotaiDefaultAddress getAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(address(300));
        }
        int max = ADDRESS.size() - 1;
        return ADDRESS.get(random(max));
    }

    public static String getCallPhone() {
        int max = Mobile.CALL_MOBILES.size() - 1;
        return Mobile.CALL_MOBILES.get(random(max));
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
