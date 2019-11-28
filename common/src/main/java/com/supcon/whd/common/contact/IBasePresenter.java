package com.supcon.whd.common.contact;

/**
 * created by wanghaidong
 */
public interface IBasePresenter<V> {

    void attachView(V v);

    V getView();

    void detachView();
}

