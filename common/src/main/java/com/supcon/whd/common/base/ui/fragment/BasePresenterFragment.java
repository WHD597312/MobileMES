package com.supcon.whd.common.base.ui.fragment;

import com.supcon.whd.common.annotation.Presenter;
import com.supcon.whd.common.contact.IBaseView;
import com.supcon.whd.common.presenter.BasePresenter;
import com.supcon.whd.common.presenter.PresenterRouter;
import com.supcon.whd.common.utils.InstanceUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public abstract class BasePresenterFragment extends BaseFragment implements IBaseView {

    protected PresenterRouter presenterRouter;
    private List<BasePresenter> mPresenters = new ArrayList<>();
    @Override
    protected void onInit() {
        super.onInit();
    }
    protected void initPresenter() {
        presenterRouter = new PresenterRouter();
        Annotation[] annotations = getClass().getAnnotations();
        for (Annotation annotation : annotations) {

            if (annotation instanceof Presenter) {
                Class[] presenters = ((Presenter) annotation).value();

                for (Class presenter : presenters) {
                    if (this instanceof IBaseView) {
                        if (presenterRouter.hasPresenter(presenter)) {
                            BasePresenter basePresenter = (BasePresenter) presenterRouter.getPresenter(presenter);
                            addPresenter(basePresenter);
                        } else {
                            BasePresenter basePresenter = (BasePresenter) InstanceUtils.getInstance(presenter);
                            presenterRouter.register(basePresenter);
                            addPresenter(basePresenter);
                        }
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
    public void onDestroyView() {
        super.onDestroyView();
        for (BasePresenter presenter : mPresenters) {
            if (presenter != null) {
                presenter.detachView();
            }
        }
    }


}
