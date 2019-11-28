package com.supcon.whd.common.base.model;


public class CommonEntity<T extends BaseEntity>  extends BaseEntity {
    public int  returnCode;
    public String returnMsg;
    public T returnData;
}
