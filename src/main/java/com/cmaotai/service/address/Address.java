package com.cmaotai.service.address;

import com.cmaotai.service.address.AMapAddress.AMapAddressTip;
import com.cmaotai.service.mobile.Mobile;
import com.cmaotai.service.model.CMotaiDefaultAddress;
import com.cmaotai.service.service.AMapService;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.apache.logging.log4j.util.Strings;

public class Address {

    private static List<CMotaiDefaultAddress> ADDRESS = Lists.newArrayList();

    private static List<String> GUIYANG_DISTRICTS = Lists
        .newArrayList("贵阳市花溪区碧云窝", "贵阳市南明区瑞金南路", "贵阳市乌当区顺新社区新添大道北段",
            "贵阳市云岩区北京路", "贵阳市云岩区盐务街", "贵阳市南明区花果园", "贵阳市云岩区紫林庵",
            "贵阳市南明区南厂路", "贵阳市白云区白云南路", "贵阳市南明区西湖路");

    //海珠区 天河区 ，白云区
    private static List<String> GUANGZHOU_DISTRICTS = Lists
        .newArrayList("广东省广州市海珠区", "广东省广州市海珠区新港东路", "广东省广州市海珠区华洲路",
            "广东省广州市海珠区东园横路", "广东省广州市海珠区洪德路", "广东省广州市海珠区广州大道",
            "广东省广州市海珠区滨江东路",
            "广东省广州市海珠区敦和路",
            "广东省广州市海珠区素社一巷", "广东省广州市海珠区革新路", "广东省广州市海珠区工业大道",
            "广东省广州市白云区", "广东省广州市白云区江高镇", "广东省广州市白云区龙归北太路",
            "广东省广州市白云区龙归镇", "广东省广州市白云区友塘南街",
            "广东省广州市白云区新广从公路",
            "广东省广州市白云区龙归镇南村龙河西路",
            "广东省广州市白云区江仁路",
            "广东省广州市天河区",
            "广东省广州市天河区五山街五山路",
            "广东省广州市天河区汇景北路",
            "广东省广州市天河区光宝路",
            "广东省广州市天河区东圃小新塘合景路",
            "广东省广州市天河区火炉山蓝鹏高尔夫练习场",
            "广东省广州市天河区长兴路",
            "广东省广州市天河区五山街五山路");

    private static List<String> WUXI_DISTRICTS = Lists
        .newArrayList("无锡市崇安区", "无锡市梁溪区", "无锡新区", "无锡锡山区", "无锡惠山区");

    private static List<String> ZUNYI_DISTRICTS = Lists
        .newArrayList("红花岗区石龙路", "红花岗区银河北路", "汇川区天津路", "汇川区珠海路", "贵州省遵义市汇川区南京路", "遵义市红花岗区南宫山湘江大道");

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
        int max = qtys.size();
        if (max == 1) {
            return qtys.get(0);
        } else {
            return qtys.get(random(max));
        }
    }

    public static String getGuiYangDistricts() {
        int max = GUIYANG_DISTRICTS.size();
        return GUIYANG_DISTRICTS.get(random(max));
    }

    public static String getWuXiDistricts() {
        int max = WUXI_DISTRICTS.size();
        return WUXI_DISTRICTS.get(random(max));
    }

    public static String getGuangZhouDistricts() {
        int max = GUANGZHOU_DISTRICTS.size();
        return GUANGZHOU_DISTRICTS.get(random(max));
    }

    public static String getZunYiDistricts() {
        int max = ZUNYI_DISTRICTS.size();
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

    public static List<CMotaiDefaultAddress> guangzhouAddress(int num) {
        List<AMapAddressTip> aMapAddressTips = AMapService.getAddress(getGuangZhouDistricts());
        for (int i = aMapAddressTips.size(); i < num; i++) {
            aMapAddressTips.addAll(AMapService.getAddress(getGuangZhouDistricts()));
        }
        System.out.println(aMapAddressTips.size() + "个广州地址已生成");
        return aMapAddressTips.stream().map(aMapAddressTip -> {
            CMotaiDefaultAddress cMotaiDefaultAddress = new CMotaiDefaultAddress();
            cMotaiDefaultAddress.setProvinceId("440000");
            cMotaiDefaultAddress.setCityId("440100");
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

    public static String GUIYANG = "guiyang";

    public static String WUXI = "wuxi";

    public static String ZUNYI = "zunyi";

    public static String GUANGZHOU = "guangzhou";

    public static CMotaiDefaultAddress getAddress(String from) {
        if (Strings.isBlank(from)) {
            return getGuiYangAddress();
        }
        if (GUIYANG.equals(from)) {
            return getGuiYangAddress();
        } else if (WUXI.equals(from)) {
            return getWuXiAddress();
        } else if (ZUNYI.equals(from)) {
            return getZunYiAddress();
        } else if (GUANGZHOU.equals(from)) {
            return getGuangZhouAddress();
        }
        return getGuiYangAddress();
    }

    private static CMotaiDefaultAddress getGuiYangAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(guiYangAddress(300));
        }
        int max = ADDRESS.size();
        return ADDRESS.get(random(max));
    }

    private static CMotaiDefaultAddress getWuXiAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(wuXiAddress(42));
            System.out.println("42个无锡地址已生成，开始添加....");
        }
        int max = ADDRESS.size();
        return ADDRESS.get(random(max));
    }

    private static CMotaiDefaultAddress getZunYiAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(zunYiAddress(100));
        }
        int max = ADDRESS.size();
        return ADDRESS.get(random(max));
    }

    private static CMotaiDefaultAddress getGuangZhouAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(guangzhouAddress(100));
        }
        int max = ADDRESS.size();
        return ADDRESS.get(random(max));
    }

    public static String getCallPhone() {
        int max = Mobile.CALL_MOBILES.size();
        return Mobile.CALL_MOBILES.get(random(max));
    }

    public static String getCallName() {
        int max = Mobile.CALL_NAMES.size();
        return Mobile.CALL_NAMES.get(random(max));
    }

    public static int random(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

}
