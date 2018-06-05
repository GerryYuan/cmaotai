package com.cmaotai.service.list;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class CMaotaiOrderReturn<T> implements Serializable {

    private static final long serialVersionUID = -2119296244967845936L;

    private int index;

    private String size;

    private int count;

    private List<T> Data;

}
