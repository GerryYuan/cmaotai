package com.cmaotai.service.list;

import java.io.Serializable;
import lombok.Data;

@Data
public class CMaotaiOrderDetail implements Serializable {

    private Delivery Delivery;

    private Network Network;

    @Data
    public class Delivery implements Serializable {

        private static final long serialVersionUID = -1261104526726170330L;
        private String Name;
        private String TelPhone;
        private String CellPhone;

    }

    @Data
    public class Network implements Serializable {

        private static final long serialVersionUID = 277836830014516590L;

        private String Address;

        private String LinkTel;

    }
}
