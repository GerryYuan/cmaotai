package com.cmaotai.service.address;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author gerry
 * @version 1.0, 2018-05-29 21:09
 * @since com.hujiang 1.0.0
 */
@Data
public class Page<T> implements Serializable {

    private int size;

    private int index;

    private int count;

    private int datacount;

    private List<T> list;
}
