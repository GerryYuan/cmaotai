package com.cmaotai.service.list;

import java.io.Serializable;
import lombok.Data;

@Data
public class CMaotaiReturn<T> implements Serializable {

    private static final long serialVersionUID = -2119296244967845936L;

    private int retcode;

    private String retmsg;

    private int count;

    private CMaotaiPage<T> data;

}
