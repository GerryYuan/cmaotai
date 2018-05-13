package com.cmaotai.service.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class CMaotaiUser implements Serializable {

    private String TelPhone;

    private String NickName;

    private String HeadPortrait;

    private String SalemanState;

    private int AuthState;

    private boolean IsLock;

    private boolean IsConfirmationProtocol;

    private String Certificate;

    private String CertIdent;

    private String HasPassword;

    private String DeviceIdent;

    private int UserId;

    private String Name;

}
