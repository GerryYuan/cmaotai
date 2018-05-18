package com.cmaotai.service;

import java.io.Serializable;
import lombok.Data;

@Data
public class CMaotaiCancel implements Serializable {

    private static final long serialVersionUID = -2096521782651156642L;

    private Integer id;

    private Integer orderStatus;

    private Integer goodsId;

}
