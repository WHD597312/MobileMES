package com.supcon.whd.login.presenter;

import com.supcon.whd.common.base.network.RxSchedulers;
import com.supcon.whd.login.model.bean.StudentEntity;
import com.supcon.whd.login.model.bean.StudentListEntity;
import com.supcon.whd.login.model.contract.ContractStudentList;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;

public class StudentPresenter extends ContractStudentList.Presenter {
    @Override
    public void getStudent(int page) {
        mCompositeSubscription.add(
                Flowable
                        .timer(1000, TimeUnit.MILLISECONDS)
                        .compose(RxSchedulers.io_main())
                        .subscribe(o->{
                            StudentListEntity studentListEntity=new StudentListEntity();
                            List<StudentEntity> studentEntities=new ArrayList<>();
                            for (int i = 1; i <= 20; i++) {
                                StudentEntity entity=new StudentEntity();
                                entity.name="学生"+i+"第"+page+"页";
                                studentEntities.add(entity);
                            }
                            studentListEntity.result=studentEntities;
                            getView().doStudentListSuccess(studentListEntity);
                        })
        );
    }
}
