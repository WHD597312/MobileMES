package com.supcon.whd.login.model.bean;

public class WorkEntity {
    public int res;
    public String content;
    public String router;

    public WorkEntity(int res, String content,String router) {
        this.res = res;
        this.content = content;
        this.router=router;
    }
}
