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

    private static List<String> GUIYANG_DISTRICTS = Lists
        .newArrayList("贵阳市花溪区碧云窝", "贵阳市南明区瑞金南路", "贵阳市乌当区顺新社区新添大道北段", "贵阳市云岩区北京路", "贵阳市云岩区盐务街", "贵阳市南明区花果园", "贵阳市云岩区紫林庵",
            "贵阳市南明区南厂路", "贵阳市白云区白云南路", "贵阳市南明区西湖路");

    private static List<String> WUXI_DISTRICTS = Lists
        .newArrayList("无锡市崇安区", "无锡市梁溪区", "无锡新区", "无锡锡山区", "无锡惠山区");

    private static List<String> ZUNYI_DISTRICTS = Lists
        .newArrayList("红花岗区石龙路", "红花岗区银河北路", "汇川区天津路", "汇川区珠海路", "贵州省遵义市汇川区南京路", "遵义市红花岗区南宫山湘江大道");

    @Getter
    private static String qty;

    public static void initQty(Integer start, Integer end) {
        if (start == null) {
            start = 6;
        }
        if (end == null) {
            end = 6;
        }
        List<String> qtys = Lists.newArrayList();
        for (int i = start; i <= end; i++) {
            qtys.add(i + "");
        }
        int max = qtys.size() - 1;
        if (max == 0) {
            qty = qtys.get(max);
        } else {
            qty = qtys.get(random(max));
        }
    }

    public static String getGuiYangDistricts() {
        int max = GUIYANG_DISTRICTS.size() - 1;
        return GUIYANG_DISTRICTS.get(random(max));
    }

    public static String getWuXiDistricts() {
        int max = WUXI_DISTRICTS.size() - 1;
        return WUXI_DISTRICTS.get(random(max));
    }

    public static String getZunYiDistricts() {
        int max = ZUNYI_DISTRICTS.size() - 1;
        return ZUNYI_DISTRICTS.get(random(max));
    }

    public static List<CMotaiDefaultAddress> wuXiAddress(int num) {
        List<AMapAddressTip> aMapAddressTips = AMapService.getAddress(getWuXiDistricts());
        for (int i = aMapAddressTips.size(); i < num; i++) {
            aMapAddressTips.addAll(AMapService.getAddress(getWuXiDistricts()));
        }
        System.out.println(aMapAddressTips.size() + "个无锡地址已生成");
        return aMapAddressTips.stream().map(aMapAddressTip -> {
            CMotaiDefaultAddress cMotaiDefaultAddress = new CMotaiDefaultAddress();
            cMotaiDefaultAddress.setProvinceId("320000");
            cMotaiDefaultAddress.setCityId("320200");
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

    public static List<CMotaiDefaultAddress> zunYiAddress(int num) {
        List<AMapAddressTip> aMapAddressTips = AMapService.getAddress(getZunYiDistricts());
        for (int i = aMapAddressTips.size(); i < num; i++) {
            aMapAddressTips.addAll(AMapService.getAddress(getZunYiDistricts()));
        }
        System.out.println(aMapAddressTips.size() + "个遵义地址已生成");
        return aMapAddressTips.stream().map(aMapAddressTip -> {
            CMotaiDefaultAddress cMotaiDefaultAddress = new CMotaiDefaultAddress();
            cMotaiDefaultAddress.setProvinceId("520000");
            cMotaiDefaultAddress.setCityId("520300");
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

    public static List<CMotaiDefaultAddress> guiYangAddress(int num) {
        List<AMapAddressTip> aMapAddressTips = AMapService.getAddress(getGuiYangDistricts());
        for (; aMapAddressTips.size() < num; ) {
            aMapAddressTips.addAll(AMapService.getAddress(getGuiYangDistricts()));
        }
        System.out.println(aMapAddressTips.size() + "个贵阳地址已生成");
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

    public static CMotaiDefaultAddress getAddress(String from) {
        if (Strings.isBlank(from)) {
            return getGuiYangAddress();
        }
        if ("guiyang".equals(from)) {
            return getGuiYangAddress();
        } else if ("wuxi".equals(from)) {
            return getWuXiAddress();
        } else if ("zunyi".equals(from)) {
            return getZunYiAddress();
        }
        return getGuiYangAddress();
    }

    private static CMotaiDefaultAddress getGuiYangAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(guiYangAddress(300));
        }
        int max = ADDRESS.size() - 1;
        return ADDRESS.get(random(max));
    }

    private static CMotaiDefaultAddress getWuXiAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(wuXiAddress(42));
            System.out.println("42个无锡地址已生成，开始添加....");
        }
        int max = ADDRESS.size() - 1;
        return ADDRESS.get(random(max));
    }

    private static CMotaiDefaultAddress getZunYiAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(zunYiAddress(100));
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
