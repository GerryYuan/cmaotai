package com.cmaotai.service.model;

import java.io.Serializable;
import lombok.Data;
import lombok.Getter;

@Data
public class CMaotaiOrderStatus implements Serializable {

    private int OrderStatus;

    private int OrderStatusNum;

    public enum OrderStatus {
        WAIT_PAY(1, "代付款"),

        WAIT_DELIVER_GOODS(2, "待发货"),

        WAIT_CONFIRMATION_GOODS(3, "待确认收货"),;

        @Getter
        private int code;

        @Getter
        private String name;

        OrderStatus(int code, String name) {
            this.code = code;
            this.name = name;
        }
    }
}
