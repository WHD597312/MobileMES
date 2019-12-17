package com.supcon.whd.common.base.model;


public class CommonEntity<T extends BaseEntity>  extends BaseEntity {
    public boolean success;
    public String errMsg;
    public T result;
}
