package com.supcon.whd.common.controller;

import com.supcon.whd.common.annotation.Presenter;
import com.supcon.whd.common.contact.IBaseView;
import com.supcon.whd.common.presenter.BasePresenter;
import com.supcon.whd.common.presenter.PresenterRouter;
import com.supcon.whd.common.utils.InstanceUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class BasePresenterController extends BaseController implements IBaseView{

    protected PresenterRouter presenterRouter;
    private List<BasePresenter> mPresenters = new ArrayList<>();

    public BasePresenterController() {
        initPresenter();
    }
    public void initPresenter(){

        presenterRouter = new PresenterRouter();
        Annotation[] annotations = getClass().getAnnotations();
        for (Annotation annotation:annotations){

            if(annotation instanceof Presenter){
                Class[] presenters = ((Presenter) annotation).value();

                for(Class presenter : presenters){

                    if(this instanceof IBaseView){
                        BasePresenter basePresenter = (BasePresenter) InstanceUtils.getInstance(presenter);
                        presenterRouter.register(basePresenter);
                        addPresenter(basePresenter);
                    }

                }

            }
        }

    }

    private void addPresenter(BasePresenter basePresenter) {
        basePresenter.attachView(this);
        mPresenters.add(basePresenter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        for(BasePresenter presenter: mPresenters){

            if(presenter!=null){
                presenter.detachView();
            }

        }

    }


}
