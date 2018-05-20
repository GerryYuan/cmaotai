package com.cmaotai.service.list;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class CMaotaiList implements Serializable {

    private static final long serialVersionUID = -7853332670974533356L;

    private int id;

    private int goodsId;

    private Date createTime;

    private int orderStatus;

}
