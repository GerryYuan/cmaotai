package com.cmaotai.service.model;

import java.io.Serializable;
import lombok.Data;
import lombok.Getter;

/**
 * @author gerry
 * @version 1.0, 2018-05-12 17:03
 * @since com.hujiang 1.0.0
 */
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
