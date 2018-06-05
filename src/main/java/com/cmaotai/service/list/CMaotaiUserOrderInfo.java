package com.cmaotai.service.list;

import java.io.Serializable;
import lombok.Data;

@Data
public class CMaotaiUserOrderInfo implements Serializable {

    private static final long serialVersionUID = 2865322554799645535L;

    private String OrderId;

    private String OrderDate;

    private int Quantity;

}
