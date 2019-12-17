package com.supcon.whd.common.base.model;

import com.google.gson.Gson;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    private static final long serialVersionUID = -7273594391472323321L;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
