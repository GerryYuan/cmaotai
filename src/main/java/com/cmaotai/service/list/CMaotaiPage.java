package com.cmaotai.service.list;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
public class CMaotaiPage<T> implements Serializable {

    private static final long serialVersionUID = -2119296244967845936L;

    private int totel;

    private List<T> data;
}
