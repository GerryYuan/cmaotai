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
        .newArrayList("南明区花果园国际中心",
            "南明区花果园彭家湾花果园项目C区",
            "南明区西湖路",
            "白云区白云南路",
            "高新区长岭南路",
            "观山湖区诚信北路",
            "观山湖区贵阳金融中心一期",
            "观山湖区贵阳世纪城",
            "观山湖区国家数字内容产业园",
            "观山湖区会展城B区",
            "观山湖区茅台国际商务中心",
            "观山湖区美的财智大厦",
            "观山湖区美的林城时代",
            "观山湖区世纪城U组团商业",
            "观山湖区世纪城北京西路",
            "观山湖区世纪金源国际财富中心",
            "观山湖区长岭北路",
            "观山湖区中天会展城",
            "花溪区班芙商业广场",
            "花溪区保利溪二期",
            "花溪区碧云窝",
            "花溪区黄河路",
            "花溪区田园北路",
            "花溪区小河香江路",
            "南明区宝山南路",
            "南明区公园南路",
            "南明区花果园C区",
            "南明区花果园国际中心",
            "南明区花果园金融街",
            "南明区花果园彭家湾花果园项目",
            "南明区机场路",
            "南明区箭道街",
            "南明区南厂路",
            "南明区瑞金南路",
            "南明区沙冲南路",
            "南明区狮峰路",
            "南明区西湖路",
            "南明区新华路",
            "南明区遵义路",
            "白云区南路育才路",
            "观山湖丽阳天下国酒茅台专营店",
            "观山湖区金阳北路",
            "贵阳市云岩区鹿冲关路",
            "贵阳市云岩区陕西路",
            "贵阳市云岩区中华北路",
            "南明区新华路",
            "云岩区北京西路",
            "云岩区鹿冲关路",
            "乌当区顺新社区新添大道北段",
            "云岩区宝山北路",
            "云岩区北京路",
            "云岩区贵开路",
            "云岩区甲秀北路恒大帝景",
            "云岩区金仓路",
            "云岩区南国花锦",
            "云岩区黔春路",
            "云岩区未来方舟",
            "云岩区延安东路",
            "云岩区盐务街",
            "云岩区友谊路",
            "云岩区中华中路",
            "云岩区中华中路1号峰会国际大厦");
    //天河区 ，白云区
//    private static List<String> GUANGZHOU_DISTRICTS = Lists
//        .newArrayList("广东省广州市海珠区", "广东省广州市海珠区新港东路", "广东省广州市海珠区华洲路",
//            "广东省广州市海珠区东园横路", "广东省广州市海珠区洪德路", "广东省广州市海珠区广州大道",
//            "广东省广州市海珠区滨江东路",
//            "广东省广州市海珠区敦和路",
//            "广东省广州市海珠区素社一巷", "广东省广州市海珠区革新路", "广东省广州市海珠区工业大道",
//            "广东省广州市白云区", "广东省广州市白云区江高镇", "广东省广州市白云区龙归北太路",
//            "广东省广州市白云区龙归镇", "广东省广州市白云区友塘南街",
//            "广东省广州市白云区新广从公路",
//            "广东省广州市白云区龙归镇南村龙河西路",
//            "广东省广州市白云区江仁路",
//            "广东省广州市天河区",
//            "广东省广州市天河区五山街五山路",
//            "广东省广州市天河区汇景北路",
//            "广东省广州市天河区光宝路",
//            "广东省广州市天河区东圃小新塘合景路",
//            "广东省广州市天河区火炉山蓝鹏高尔夫练习场",
//            "广东省广州市天河区长兴路");

    private static List<String> GUANGZHOU_DISTRICTS = Lists
        .newArrayList(
            "广东省广州市白云区黄园路", "广东省广州市白云区黄园路",
            "广东省广州市赵秀区环市中路", "广东省广州市赵秀区环市中路",
            "广东省广州市赵秀区府前路", "广东省广州市赵秀区府前路",
            "广东省广州市花都区凤凰北路", "广东省广州市花都区凤凰北路",
            "广东省广州市天河区员村四横路蒲林街",
            "广东省广州市天河区广州大道中", "广东省广州市天河区粤垦路", "广东省广州市天河区珠江新城猎德大道",
            "广东省广州市增城区新塘镇府前路", "广东省广州市增城区荔乡路", "广东省广州市南沙区进港大道",
            "广东省广州市白云区龙归镇", "广东省广州市白云区龙归镇南村龙河西路", "广东省广州市天河区五山街五山路",
            "广东省广州市天河区光宝路", "广东省广州市天河区长兴路");

    private static List<String> DONGGUAN_DISTRICTS = Lists
        .newArrayList(
            "广东省东莞市宏图路", "广东省东莞市滨河路",
            "广东省东莞市万江区坝头东江大道", "广东省东莞市坝新路",
            "广东省东莞市莞太路", "广东省东莞市南城区鸿福路",
            "广东省东莞市宝健二路", "广东省东莞市东城东泰新源南路与东骏路");

    private static List<String> SHENZHEN_DISTRICTS = Lists
        .newArrayList(
            "广东省深圳市北环大道",
            "广东省深圳市皇岗路",
            "广东省深圳市红岭北路",
            "广东省深圳市人民南路",
            "广东省深圳市罗湖区凤凰路",
            "广东省深圳市福田区笋岗西路",
            "广东省深圳市罗湖区水贝一路",
            "广东省深圳市罗湖区太安路",
            "广东省深圳市福田区合正名园",
            "广东省深圳市福田区民田路",
            "广东省深圳市龙岗区长兴北路",
            "广东省深圳市罗湖区新秀路",
            "广东省深圳市罗湖区太宁路",
            "广东省深圳市福田区梅林居",
            "广东省深圳市宝安区翻身路",
            "广东省深圳市红荔路",
            "广东省深圳市龙华区梅龙大道",
            "广东省深圳市龙华区梅坂大道",
            "广东省深圳市南山区后海滨路",
            "广东省深圳市华盛路",
            "广东省深圳市雅园路",
            "广东省深圳市桂园路",
            "广东省深圳市宝安区裕安一路",
            "广东省深圳市新安四路",
            "广东省深圳市龙岗区欣景路",
            "广东省深圳市沙盐路",
            "广东省深圳市龙华区人民中路");


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

    public static String getDongGuanDistricts() {
        int max = DONGGUAN_DISTRICTS.size();
        return DONGGUAN_DISTRICTS.get(random(max));
    }

    public static String getShenZhenDistricts() {
        int max = SHENZHEN_DISTRICTS.size();
        return SHENZHEN_DISTRICTS.get(random(max));
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

    public static List<CMotaiDefaultAddress> dongGuanAddress(int num) {
        List<AMapAddressTip> aMapAddressTips = AMapService.getAddress(getGuangZhouDistricts());
        for (int i = aMapAddressTips.size(); i < num; i++) {
            aMapAddressTips.addAll(AMapService.getAddress(getGuangZhouDistricts()));
        }
        System.out.println(aMapAddressTips.size() + "个东莞地址已生成");
        return aMapAddressTips.stream().map(aMapAddressTip -> {
            CMotaiDefaultAddress cMotaiDefaultAddress = new CMotaiDefaultAddress();
            cMotaiDefaultAddress.setProvinceId("440000");
            cMotaiDefaultAddress.setCityId("441900");
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

    public static List<CMotaiDefaultAddress> shenZhenAddress(int num) {
        List<AMapAddressTip> aMapAddressTips = AMapService.getAddress(getGuangZhouDistricts());
        for (int i = aMapAddressTips.size(); i < num; i++) {
            aMapAddressTips.addAll(AMapService.getAddress(getGuangZhouDistricts()));
        }
        System.out.println(aMapAddressTips.size() + "个深圳地址已生成");
        return aMapAddressTips.stream().map(aMapAddressTip -> {
            CMotaiDefaultAddress cMotaiDefaultAddress = new CMotaiDefaultAddress();
            cMotaiDefaultAddress.setProvinceId("440000");
            cMotaiDefaultAddress.setCityId("440300");
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

    public static String DONGGUAN = "dongguan";

    public static String SHENZHEN = "shenzhen";


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
        } else if(DONGGUAN.equals(from)){
            return getDongGuanAddress();
        }else if (SHENZHEN.equals(from)){
            return getShenZhenAddress();
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
            ADDRESS.addAll(guangzhouAddress(150));
        }
        int max = ADDRESS.size();
        return ADDRESS.get(random(max));
    }

    private static CMotaiDefaultAddress getDongGuanAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(dongGuanAddress(100));
        }
        int max = ADDRESS.size();
        return ADDRESS.get(random(max));
    }

    private static CMotaiDefaultAddress getShenZhenAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(shenZhenAddress(100));
        }
        int max = ADDRESS.size();
        return ADDRESS.get(random(max));
    }

    public static String getCallPhone() {
        int max = Mobile.MOBILES.size();
        return Mobile.MOBILES.get(random(max));
    }

    public static String getCallName() {
        int max = Mobile.CALL_NAMES.size();
        return Mobile.CALL_NAMES.get(random(max));
    }

    public static int random(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    public static void clear() {
        ADDRESS.clear();
    }
}
