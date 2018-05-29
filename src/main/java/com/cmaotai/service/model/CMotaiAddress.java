package com.cmaotai.service.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class CMotaiAddress implements Serializable {

    private int SId;

    private int UserId;

    private String TelPhone;

    private String Zipcode;

    private String Address;

    private String AddressInfo;

    private String ShipTo;

    private String Remark;

    private String ProvinceId;

    private String CityId;

    private String DistrictsId;

    private String IsDefault;

    private String Longitude;

    private String Latitude;

    private String CallPhone;
}
