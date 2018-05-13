package com.cmaotai.service.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class DataResult<T> implements Serializable {

    private boolean state;

    private int code;

    private T data;

}
