package com.cmaotai.service.address;

import com.cmaotai.service.mobile.Mobile;
import com.cmaotai.service.model.CMotaiDefaultAddress;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;

/**
 * @author gerry
 * @version 1.0, 2018-05-13 22:06
 * @since com.hujiang 1.0.0
 */
public class Address {

    private static List<CMotaiDefaultAddress> ADDRESS = Lists.newArrayList();

    static {
        CMotaiDefaultAddress address = new CMotaiDefaultAddress();
        address.setProvinceId("520000");
        address.setCityId("520000");
        address.setDistrictsId("520113");
        address.setAddressInfo("贵州省贵阳市白云区");
        address.setAddress("三一一七厂(公交站),公交站");
        address.setShipTo("马鑫");
        address.setCallPhone(getCallPhone());
        address.setZipcode("000000");
        address.setIsDefault("1");
        address.setLongitude("106.64917");
        address.setLatitude("26.670902");
        ADDRESS.add(address);
        CMotaiDefaultAddress address2 = new CMotaiDefaultAddress();
        address2.setProvinceId("520000");
        address2.setCityId("520000");
        address2.setDistrictsId("520103");
        address2.setAddressInfo("贵州省贵阳市云岩区");
        address2.setAddress("大营坡新添大道南段151号");
        address2.setShipTo("黄凯");
        address2.setCallPhone(getCallPhone());
        address2.setZipcode("000000");
        address2.setIsDefault("1");
        address2.setLongitude("106.724494");
        address2.setLatitude("26.604688");
        ADDRESS.add(address2);
        CMotaiDefaultAddress address3 = new CMotaiDefaultAddress();
        address3.setProvinceId("520000");
        address3.setCityId("520000");
        address3.setDistrictsId("520102");
        address3.setAddressInfo("贵州省贵阳市南明区");
        address3.setAddress("花果园(公交站)");
        address3.setShipTo("李伟");
        address3.setCallPhone(getCallPhone());
        address3.setZipcode("000000");
        address3.setIsDefault("1");
        address3.setLongitude("106.694290");
        address3.setLatitude("26.564888");
        ADDRESS.add(address3);
        CMotaiDefaultAddress address4 = new CMotaiDefaultAddress();
        address4.setProvinceId("520000");
        address4.setCityId("520000");
        address4.setDistrictsId("520102");
        address4.setAddressInfo("贵州省贵阳市南明区");
        address4.setAddress("南明区花果园B南区3栋240商铺");
        address4.setShipTo("黄凯");
        address4.setCallPhone(getCallPhone());
        address4.setZipcode("000000");
        address4.setIsDefault("1");
        address4.setLongitude("106.693495");
        address4.setLatitude("26.569053");
        ADDRESS.add(address4);
        CMotaiDefaultAddress address5 = new CMotaiDefaultAddress();
        address5.setProvinceId("520000");
        address5.setCityId("520000");
        address5.setDistrictsId("520102");
        address5.setAddressInfo("贵州省贵阳市南明区");
        address5.setAddress("松山路,花果园1期");
        address5.setShipTo("李斯");
        address5.setCallPhone(getCallPhone());
        address5.setZipcode("000000");
        address5.setIsDefault("1");
        address5.setLongitude("106.684513");
        address5.setLatitude("26.567585");
        ADDRESS.add(address5);
        CMotaiDefaultAddress address6 = new CMotaiDefaultAddress();
        address6.setProvinceId("520000");
        address6.setCityId("520000");
        address6.setDistrictsId("520103");
        address6.setAddressInfo("贵州省贵阳市云岩区");
        address6.setAddress("北京西路银海元隆广场12号楼1层");
        address6.setShipTo("李翔");
        address6.setCallPhone(getCallPhone());
        address6.setZipcode("000000");
        address6.setIsDefault("1");
        address6.setLongitude("106.701690");
        address6.setLatitude("26.591146");
        ADDRESS.add(address6);
        CMotaiDefaultAddress address7 = new CMotaiDefaultAddress();
        address7.setProvinceId("520000");
        address7.setCityId("520000");
        address7.setDistrictsId("520103");
        address7.setAddressInfo("贵州省贵阳市云岩区");
        address7.setAddress("北京路19");
        address7.setShipTo("李达");
        address7.setCallPhone(getCallPhone());
        address7.setZipcode("000000");
        address7.setIsDefault("1");
        address7.setLongitude("106.713281");
        address7.setLatitude("26.596161");
        ADDRESS.add(address7);
        CMotaiDefaultAddress address8 = new CMotaiDefaultAddress();
        address8.setProvinceId("520000");
        address8.setCityId("520000");
        address8.setDistrictsId("520102");
        address8.setAddressInfo("贵州省贵阳市南明区");
        address8.setAddress("南厂路71号");
        address8.setShipTo("李欣");
        address8.setCallPhone(getCallPhone());
        address8.setZipcode("000000");
        address8.setIsDefault("1");
        address8.setLongitude("106.71806");
        address8.setLatitude("26.558647");
        ADDRESS.add(address8);
        CMotaiDefaultAddress address9 = new CMotaiDefaultAddress();
        address9.setProvinceId("520000");
        address9.setCityId("520000");
        address9.setDistrictsId("520102");
        address9.setAddressInfo("贵州省贵阳市南明区");
        address9.setAddress("南厂路103号");
        address9.setShipTo("李达");
        address9.setCallPhone(getCallPhone());
        address9.setZipcode("000000");
        address9.setIsDefault("1");
        address9.setLongitude("106.714861");
        address9.setLatitude("26.556397");
        ADDRESS.add(address9);
        CMotaiDefaultAddress address10 = new CMotaiDefaultAddress();
        address10.setProvinceId("520000");
        address10.setCityId("520000");
        address10.setDistrictsId("520102");
        address10.setAddressInfo("贵州省贵阳市南明区");
        address10.setAddress("后巢乡南厂路1号");
        address10.setShipTo("李梅");
        address10.setCallPhone(getCallPhone());
        address10.setZipcode("000000");
        address10.setIsDefault("1");
        address10.setLongitude("106.718891");
        address10.setLatitude("26.560629");
        ADDRESS.add(address10);
    }

    public static CMotaiDefaultAddress getAddress() {
        int max = ADDRESS.size() - 1;
        return ADDRESS.get(random(max));
    }

    public static String getCallPhone() {
        int max = Mobile.MOBILES.size() - 1;
        return Mobile.MOBILES.get(random(max));
    }

    public static int random(int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - 0 + 1) + 0;
    }

}
