package com.supcon.whd.common.base.ui.activity;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.supcon.whd.common.annotation.Controller;
import com.supcon.whd.common.base.ui.view.listener.Lifecycle;
import com.supcon.whd.common.base.ui.view.listener.LifecycleManage;
import com.supcon.whd.common.controller.BaseController;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseControllerActivity extends BaseActivity {


    protected LifecycleManage controllers = new LifecycleManage();
    protected List<BaseController> dataControllers = new ArrayList<>();


    protected void initControllers() {
        Annotation[] annotations = getClass().getAnnotations();
        for (Annotation annotation:annotations){

            if(annotation instanceof Controller){
                Class[] controllerClasses = ((Controller) annotation).value();

                for(Class controller : controllerClasses){
                    try {
                        BaseController baseController = (BaseController) controller.newInstance();
                        if(baseController!=null) {
                            registerController(controller.getSimpleName(), baseController);
                            addDataController(baseController);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    protected void addDataController(BaseController baseController){
        dataControllers.add(baseController);
    }
    /**
     * 注册控制器
     *
     * @param key        控制器key
     * @param controller 控制器
     */
    protected void registerController(String key, Lifecycle controller) {
        if (!TextUtils.isEmpty(key) && controller != null) {
            controllers.register(key, controller);
        }
    }

    /**
     * 获取注册的控制器
     *
     * @param  clazz
     * @return 注册器
     */
    public <T extends Lifecycle> T  getController(Class<T> clazz) {
        return (T) controllers.get(clazz.getSimpleName());
    }

    /**
     * 获取注册的控制器
     *
     * @param key key
     * @return 注册器
     */
    public Lifecycle  getController(String key) {
        return controllers.get(key);
    }



}
