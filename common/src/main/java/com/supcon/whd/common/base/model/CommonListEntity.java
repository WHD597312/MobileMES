package com.supcon.whd.common.base.model;

import java.util.List;

public class CommonListEntity<T extends BaseEntity> extends BaseEntity {
    public boolean success;
    public String  errMsg;
    public List<T> result;
}
