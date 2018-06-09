package com.cmaotai.service.address;

import com.cmaotai.service.address.AMapAddress.AMapAddressTip;
import com.cmaotai.service.mobile.Mobile;
import com.cmaotai.service.model.CMotaiAddress;
import com.cmaotai.service.service.AMapService;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.apache.logging.log4j.util.Strings;

public class Address {

    private static List<CMotaiAddress> ADDRESS = Lists.newArrayList();
    private static List<String> GUIYANG_DISTRICTS = Lists
        .newArrayList("贵州省贵阳市云岩区北京路", "贵阳市云岩区黔春路", "贵阳市云岩区中华中路",
            "贵州省贵阳市南明区狮峰路", "贵州省贵阳市南明区新华路",
            "贵阳市云岩区北京路", "贵阳市云岩区盐务街", "贵阳市南明区花果园", "贵阳市云岩区紫林庵",
            "贵阳市南明区南厂路", "贵阳市白云区白云南路", "贵阳市南明区西湖路");
    //    private static List<String> GUIYANG_DISTRICTS = Lists
//        .newArrayList(
//            "贵州省贵阳市观山湖区",
//            "贵州省贵阳市花溪区碧云窝",
//            "贵州省贵阳市花溪区黄河路",
//            "贵州省贵阳市花溪区田园北路",
//            "贵州省贵阳市花溪区小河香江路",
//            "贵州省贵阳市南明区宝山南路",
//            "贵州省贵阳市南明区机场路",
//            "贵州省贵阳市南明区狮峰路",
//            "贵州省贵阳市南明区西湖路",
//            "贵州省贵阳市南明区新华路",
//            "贵州省贵阳市云岩区北京路",
//            "贵州省贵阳市云岩区黔春路",
//            "贵州省贵阳市云岩区盐务街",
//            "贵州省贵阳市云岩区友谊路",
//            "贵州省贵阳市云岩区中华中路");
    //天河区 ，白云区
    private static List<String> GUANGZHOU_DISTRICTS = Lists
        .newArrayList(
            "广东省广州市白云区", "广东省广州市白云区江高镇", "广东省广州市白云区龙归北太路",
            "广东省广州市白云区龙归镇", "广东省广州市白云区友塘南街",
            "广东省广州市白云区新广从公路",
            "广东省广州市白云区龙归镇南村龙河西路",
            "广东省广州市白云区江仁路",
            "广东省广州市天河区", "广东省广州市花都区凤凰北路",
            "广东省广州市天河区五山街五山路",
            "广东省广州市天河区汇景北路",
            "广东省广州市天河区光宝路",
            "广东省广州市天河区东圃小新塘合景路",
            "广东省广州市天河区火炉山蓝鹏高尔夫练习场",
            "广东省广州市天河区长兴路", "广东省广州市南沙区进港大道",
            "广东省广州市增城区新塘镇府前路");
//    private static List<String> GUANGZHOU_DISTRICTS = Lists
//        .newArrayList("广东省广州市白云区黄园路",
//            "广东省广州市赵秀区环市中路", "广东省广州市天河区五山街五山路","广东省广州市天河区长兴路",
//            "广东省广州市天河区员村四横路蒲林街",
//            "广东省广州市赵秀区府前路", "广东省广州市白云区黄园路",
//            "广东省广州市花都区凤凰北路", "广东省广州市天河区员村四横路蒲林街",
//            "广东省广州市天河区广州大道中", "广东省广州市天河区粤垦路", "广东省广州市天河区珠江新城猎德大道");

    //东城
    private static List<String> DONGCHENG_DISTRICTS = Lists
        .newArrayList("北京市东城区王府井大街", "北京市东城区金宝街",
            "北京市东城区后圆恩寺胡同", "北京市东城区崇文门外大街", "北京市东城区中鲜鱼巷",
            "北京市东城区前门东大街", "北京市东城区天坛路", "北京市东城区工人文化宫幸福大街",
            "北京市东城区安定门外大街","北京市东城区前鼓楼苑胡同","北京市东城区东中街");

    private static List<String> DONGGUAN_DISTRICTS = Lists
        .newArrayList(
            "广东省东莞市宏图路", "广东省东莞市滨河路", "广东省东莞市虎门镇", "广东省东莞市石排镇",
            "广东省东莞市大朗镇松佛路", "广东省东莞市长安镇横安路", "广东省东莞市长安镇莲峰北路",
            "广东省东莞市万江区坝头东江大道", "广东省东莞市坝新路", "广东省东莞市大岭山镇玉兰街",
            "广东省东莞市莞太路", "广东省东莞市南城区鸿福路", "东莞市莞城区运河东一路",
            "广东省东莞市宝健二路", "广东省东莞市东城东泰新源南路与东骏路", "广东省东莞市南城区御泉山庄");

    private static List<String> SHENZHEN_DISTRICTS = Lists
        .newArrayList(
            "广东省深圳市北环大道",
            "广东省深圳市皇岗路", "广东省深圳市龙岗区布沙路",
            "广东省深圳市龙岗区龙岗街道深业紫麟山花园",
            "广东省深圳市龙岗区布吉街道金运路",
            "广东省深圳市龙岗区坂田街道雪岗大道",
            "广东省深圳市皇岗路",
            "广东省深圳市红岭北路",
            "广东省深圳市罗湖区银湖路金湖山庄",
            "广东省深圳市盐田区沙盐路",
            "广东省深圳市龙岗区坂田雅园路",
            "广东省深圳市盐田区沙盐路",
            "广东省深圳市人民南路", "广东省深圳市龙岗区龙城街道",
            "广东省深圳市龙华区大浪街道",
            "广东省深圳市罗湖区泥岗东路",
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
            "广东省深圳市宝安区68区隆昌路",
            "广东省深圳市宝安区新安街道办翻身大道常福楼",
            "广东省深圳市宝安区海秀路熙龙湾花园",
            "广东省深圳市新安四路",
            "广东省深圳市龙岗区欣景路",
            "广东省深圳市沙盐路",
            "广东省深圳市龙华区人民中路");

    private static List<String> FOSHAN_DISTRICTS = Lists
        .newArrayList("广东省佛山市顺德区", "广东省佛山市高明区", "广东省佛山市南海区", "广东省佛山禅城区",
            "广东省佛山市南海大道北", "广东省佛山市南海区大沥镇渤海路中", "广东省佛山市顺德区南源路", "广东省佛山市禅城区季华六路",
            "广东省佛山市南海区大沥镇", "广东省佛山市南海区桂城街道东平路", "广东省佛山市禅城区佛山大道北", "广东省佛山市南海区狮山镇",
            "广东省佛山市南海区桂城石龙南路", "广东省佛山市顺德区乐从镇", "广东省佛山市顺德区容桂风华路", "广东省佛山市高明区中山路");

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

    public static String getDongChengDistricts() {
        int max = DONGCHENG_DISTRICTS.size();
        return DONGCHENG_DISTRICTS.get(random(max));
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

    public static String getFoShanDistricts() {
        int max = FOSHAN_DISTRICTS.size();
        return FOSHAN_DISTRICTS.get(random(max));
    }

    public static String getZunYiDistricts() {
        int max = ZUNYI_DISTRICTS.size();
        return ZUNYI_DISTRICTS.get(random(max));
    }

    public static List<CMotaiAddress> wuXiAddress(int num) {
        List<AMapAddressTip> aMapAddressTips = AMapService.getAddress(getWuXiDistricts());
        for (; aMapAddressTips.size() < num; ) {
            aMapAddressTips.addAll(AMapService.getAddress(getWuXiDistricts()));
        }
        System.out.println(aMapAddressTips.size() + "个无锡地址已生成");
        return aMapAddressTips.stream().map(aMapAddressTip -> {
            CMotaiAddress cMotaiAddress = new CMotaiAddress();
            cMotaiAddress.setProvinceId("320000");
            cMotaiAddress.setCityId("320200");
            cMotaiAddress.setDistrictsId(aMapAddressTip.getAdcode());
            cMotaiAddress.setAddressInfo(aMapAddressTip.getDistrict());
            cMotaiAddress.setAddress(aMapAddressTip.getAddress());
            cMotaiAddress.setShipTo(getCallName());
            cMotaiAddress.setCallPhone(getCallPhone());
            cMotaiAddress.setZipcode("000000");
            cMotaiAddress.setIsDefault("1");
            String[] ll = aMapAddressTip.getLocation().split(",");
            cMotaiAddress.setLongitude(ll[0]);
            cMotaiAddress.setLatitude(ll[1]);
            return cMotaiAddress;
        }).collect(Collectors.toList());
    }

    public static List<CMotaiAddress> guangzhouAddress(int num) {
        List<AMapAddressTip> aMapAddressTips = AMapService.getAddress(getGuangZhouDistricts());
        for (; aMapAddressTips.size() < num; ) {
            aMapAddressTips.addAll(AMapService.getAddress(getGuangZhouDistricts()));
        }
        System.out.println(aMapAddressTips.size() + "个广州地址已生成");
        return aMapAddressTips.stream().map(aMapAddressTip -> {
            CMotaiAddress cMotaiAddress = new CMotaiAddress();
            cMotaiAddress.setProvinceId("440000");
            cMotaiAddress.setCityId("440100");
            cMotaiAddress.setDistrictsId(aMapAddressTip.getAdcode());
            cMotaiAddress.setAddressInfo(aMapAddressTip.getDistrict());
            cMotaiAddress.setAddress(aMapAddressTip.getAddress());
            cMotaiAddress.setShipTo(getCallName());
            cMotaiAddress.setCallPhone(getCallPhone());
            cMotaiAddress.setZipcode("000000");
            cMotaiAddress.setIsDefault("1");
            String[] ll = aMapAddressTip.getLocation().split(",");
            cMotaiAddress.setLongitude(ll[0]);
            cMotaiAddress.setLatitude(ll[1]);
            return cMotaiAddress;
        }).collect(Collectors.toList());
    }

    public static List<CMotaiAddress> dongGuanAddress(int num) {
        List<AMapAddressTip> aMapAddressTips = AMapService.getAddress(getDongGuanDistricts());
        for (; aMapAddressTips.size() < num; ) {
            aMapAddressTips.addAll(AMapService.getAddress(getDongGuanDistricts()));
        }
        System.out.println(aMapAddressTips.size() + "个东莞地址已生成");
        return aMapAddressTips.stream().map(aMapAddressTip -> {
            CMotaiAddress cMotaiAddress = new CMotaiAddress();
            cMotaiAddress.setProvinceId("440000");
            cMotaiAddress.setCityId("441900");
            cMotaiAddress.setDistrictsId(aMapAddressTip.getAdcode());
            cMotaiAddress.setAddressInfo(aMapAddressTip.getDistrict());
            cMotaiAddress.setAddress(aMapAddressTip.getAddress());
            cMotaiAddress.setShipTo(getCallName());
            cMotaiAddress.setCallPhone(getCallPhone());
            cMotaiAddress.setZipcode("000000");
            cMotaiAddress.setIsDefault("1");
            String[] ll = aMapAddressTip.getLocation().split(",");
            cMotaiAddress.setLongitude(ll[0]);
            cMotaiAddress.setLatitude(ll[1]);
            return cMotaiAddress;
        }).collect(Collectors.toList());
    }

    //    110000
    public static List<CMotaiAddress> dongChengAddress(int num) {
        List<AMapAddressTip> aMapAddressTips = AMapService.getAddress(getDongChengDistricts());
        for (; aMapAddressTips.size() < num; ) {
            aMapAddressTips.addAll(AMapService.getAddress(getDongChengDistricts()));
        }
        System.out.println(aMapAddressTips.size() + "个东城地址已生成");
        return aMapAddressTips.stream().map(aMapAddressTip -> {
            CMotaiAddress cMotaiAddress = new CMotaiAddress();
            cMotaiAddress.setProvinceId("110000");
            cMotaiAddress.setCityId("110100");
            cMotaiAddress.setDistrictsId(aMapAddressTip.getAdcode());
            cMotaiAddress.setAddressInfo(aMapAddressTip.getDistrict());
            cMotaiAddress.setAddress(aMapAddressTip.getAddress());
            cMotaiAddress.setShipTo(getCallName());
            cMotaiAddress.setCallPhone(getCallPhone());
            cMotaiAddress.setZipcode("000000");
            cMotaiAddress.setIsDefault("1");
            String[] ll = aMapAddressTip.getLocation().split(",");
            cMotaiAddress.setLongitude(ll[0]);
            cMotaiAddress.setLatitude(ll[1]);
            return cMotaiAddress;
        }).collect(Collectors.toList());
    }

    public static List<CMotaiAddress> shenZhenAddress(int num) {
        List<AMapAddressTip> aMapAddressTips = AMapService.getAddress(getShenZhenDistricts());
        for (; aMapAddressTips.size() < num; ) {
            aMapAddressTips.addAll(AMapService.getAddress(getShenZhenDistricts()));
        }
        System.out.println(aMapAddressTips.size() + "个深圳地址已生成");
        return aMapAddressTips.stream().map(aMapAddressTip -> {
            CMotaiAddress cMotaiAddress = new CMotaiAddress();
            cMotaiAddress.setProvinceId("440000");
            cMotaiAddress.setCityId("440300");
            cMotaiAddress.setDistrictsId(aMapAddressTip.getAdcode());
            cMotaiAddress.setAddressInfo(aMapAddressTip.getDistrict());
            cMotaiAddress.setAddress(aMapAddressTip.getAddress());
            cMotaiAddress.setShipTo(getCallName());
            cMotaiAddress.setCallPhone(getCallPhone());
            cMotaiAddress.setZipcode("000000");
            cMotaiAddress.setIsDefault("1");
            String[] ll = aMapAddressTip.getLocation().split(",");
            cMotaiAddress.setLongitude(ll[0]);
            cMotaiAddress.setLatitude(ll[1]);
            return cMotaiAddress;
        }).collect(Collectors.toList());
    }

    public static List<CMotaiAddress> foShanAddress(int num) {
        List<AMapAddressTip> aMapAddressTips = AMapService.getAddress(getFoShanDistricts());
        for (; aMapAddressTips.size() < num; ) {
            aMapAddressTips.addAll(AMapService.getAddress(getFoShanDistricts()));
        }
        System.out.println(aMapAddressTips.size() + "个佛山地址已生成");
        return aMapAddressTips.stream().map(aMapAddressTip -> {
            CMotaiAddress cMotaiAddress = new CMotaiAddress();
            cMotaiAddress.setProvinceId("440000");
            cMotaiAddress.setCityId("440600");
            cMotaiAddress.setDistrictsId(aMapAddressTip.getAdcode());
            cMotaiAddress.setAddressInfo(aMapAddressTip.getDistrict());
            cMotaiAddress.setAddress(aMapAddressTip.getAddress());
            cMotaiAddress.setShipTo(getCallName());
            cMotaiAddress.setCallPhone(getCallPhone());
            cMotaiAddress.setZipcode("000000");
            cMotaiAddress.setIsDefault("1");
            String[] ll = aMapAddressTip.getLocation().split(",");
            cMotaiAddress.setLongitude(ll[0]);
            cMotaiAddress.setLatitude(ll[1]);
            return cMotaiAddress;
        }).collect(Collectors.toList());
    }

    public static List<CMotaiAddress> zunYiAddress(int num) {
        List<AMapAddressTip> aMapAddressTips = AMapService.getAddress(getZunYiDistricts());
        for (; aMapAddressTips.size() < num; ) {
            aMapAddressTips.addAll(AMapService.getAddress(getZunYiDistricts()));
        }
        System.out.println(aMapAddressTips.size() + "个遵义地址已生成");
        return aMapAddressTips.stream().map(aMapAddressTip -> {
            CMotaiAddress cMotaiAddress = new CMotaiAddress();
            cMotaiAddress.setProvinceId("520000");
            cMotaiAddress.setCityId("520300");
            cMotaiAddress.setDistrictsId(aMapAddressTip.getAdcode());
            cMotaiAddress.setAddressInfo(aMapAddressTip.getDistrict());
            cMotaiAddress.setAddress(aMapAddressTip.getAddress());
            cMotaiAddress.setShipTo(getCallName());
            cMotaiAddress.setCallPhone(getCallPhone());
            cMotaiAddress.setZipcode("000000");
            cMotaiAddress.setIsDefault("1");
            String[] ll = aMapAddressTip.getLocation().split(",");
            cMotaiAddress.setLongitude(ll[0]);
            cMotaiAddress.setLatitude(ll[1]);
            return cMotaiAddress;
        }).collect(Collectors.toList());
    }

    public static List<CMotaiAddress> guiYangAddress(int num) {
        List<AMapAddressTip> aMapAddressTips = AMapService.getAddress(getGuiYangDistricts());
        for (; aMapAddressTips.size() < num; ) {
            aMapAddressTips.addAll(AMapService.getAddress(getGuiYangDistricts()));
        }
        System.out.println(aMapAddressTips.size() + "个贵阳地址已生成");
        return aMapAddressTips.stream().map(aMapAddressTip -> {
            CMotaiAddress cMotaiAddress = new CMotaiAddress();
            cMotaiAddress.setProvinceId("520000");
            cMotaiAddress.setCityId("520000");
            cMotaiAddress.setDistrictsId(aMapAddressTip.getAdcode());
            cMotaiAddress.setAddressInfo(aMapAddressTip.getDistrict());
            cMotaiAddress.setAddress(aMapAddressTip.getAddress());
            cMotaiAddress.setShipTo(getCallName());
            cMotaiAddress.setCallPhone(getCallPhone());
            cMotaiAddress.setZipcode("000000");
            cMotaiAddress.setIsDefault("1");
            String[] ll = aMapAddressTip.getLocation().split(",");
            cMotaiAddress.setLongitude(ll[0]);
            cMotaiAddress.setLatitude(ll[1]);
            return cMotaiAddress;
        }).collect(Collectors.toList());
    }

    public static String GUIYANG = "guiyang";

    public static String WUXI = "wuxi";

    public static String ZUNYI = "zunyi";

    public static String GUANGZHOU = "guangzhou";

    public static String DONGGUAN = "dongguan";

    public static String SHENZHEN = "shenzhen";

    public static String FOSHAN = "foshan";

    public static String DONGCHENG = "dongcheng";


    public static CMotaiAddress getAddress(String from) {
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
        } else if (DONGGUAN.equals(from)) {
            return getDongGuanAddress();
        } else if (SHENZHEN.equals(from)) {
            return getShenZhenAddress();
        } else if (FOSHAN.equals(from)) {
            return getFoShanAddress();
        } else if(DONGCHENG.equals(from)){
             return getDongChengAddress();
        }
        return getGuiYangAddress();
    }

    private static CMotaiAddress getDongChengAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(dongChengAddress(500));
        }
        int max = ADDRESS.size();
        return ADDRESS.get(random(max));
    }

    private static CMotaiAddress getGuiYangAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(guiYangAddress(300));
        }
        int max = ADDRESS.size();
        return ADDRESS.get(random(max));
    }

    private static CMotaiAddress getWuXiAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(wuXiAddress(42));
            System.out.println("42个无锡地址已生成，开始添加....");
        }
        int max = ADDRESS.size();
        return ADDRESS.get(random(max));
    }

    private static CMotaiAddress getZunYiAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(zunYiAddress(100));
        }
        int max = ADDRESS.size();
        return ADDRESS.get(random(max));
    }

    private static CMotaiAddress getGuangZhouAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(guangzhouAddress(300));
        }
        int max = ADDRESS.size();
        return ADDRESS.get(random(max));
    }

    private static CMotaiAddress getDongGuanAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(dongGuanAddress(500));
        }
        int max = ADDRESS.size();
        return ADDRESS.get(random(max));
    }

    private static CMotaiAddress getShenZhenAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(shenZhenAddress(800));
        }
        int max = ADDRESS.size();
        return ADDRESS.get(random(max));
    }

    private static CMotaiAddress getFoShanAddress() {
        if (ADDRESS.size() <= 0) {
            ADDRESS.addAll(foShanAddress(400));
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

    public static void clear() {
        ADDRESS.clear();
    }
}
