package com.cmaotai.service.address;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class AMapAddress implements Serializable {

    private String info;

    private List<AMapAddressTip> tips;

    @Data
    public class AMapAddressTip implements Serializable {

        private String district;

        private String adcode;

        private String location;

        private String address;

    }
}


