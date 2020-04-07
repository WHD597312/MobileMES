package com.supcon.whd.login.ui;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.supcon.whd.common.annotation.Presenter;
import com.supcon.whd.common.base.ui.activity.BaseRefreshActivity;
import com.supcon.whd.common.base.ui.view.listener.IListAdapter;
import com.supcon.whd.common.base.ui.view.listener.OnItemViewClickListener;
import com.supcon.whd.common.constant.Constant;
import com.supcon.whd.common.utils.MyDecoration;
import com.supcon.whd.login.R;
import com.supcon.whd.login.model.api.StudentListAPI;
import com.supcon.whd.login.model.bean.StudentEntity;
import com.supcon.whd.login.model.bean.StudentListEntity;
import com.supcon.whd.login.model.contract.ContractStudentList;
import com.supcon.whd.login.presenter.StudentPresenter;
import com.supcon.whd.login.ui.adapter.StudentAdapter;
import com.thejoyrun.router.RouterActivity;

import androidx.recyclerview.widget.LinearLayoutManager;

@Presenter(StudentPresenter.class)
@RouterActivity(value = Constant.Router.STUDENTLIST)
public class StudentListActivity extends BaseRefreshActivity<StudentEntity> implements ContractStudentList.View {


    StudentAdapter studentAdapter;

    @Override
    protected IListAdapter<StudentEntity> getAdapter() {
        studentAdapter=new StudentAdapter(this);
        return studentAdapter;
    }

    @Override
    public int getLayoutId() {
        return R.layout.ac_student_list;
    }

    @Override
    public void initView() {
        super.initView();
        MyDecoration  decoration=new MyDecoration();
        decoration.setDeiverHeight(getDimen(R.dimen.dp_5))
                .setColor(Color.parseColor("#f5f5f5"))
                .setMargin(0f);
        contentView.addItemDecoration(decoration);
        contentView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onListener() {
        super.onListener();
        refreshController.setOnRefreshPageListener(pageNo -> {
            presenterRouter.create(StudentListAPI.class).getStudent(pageNo);
        });
        studentAdapter.setOnItemViewClickListener(new OnItemViewClickListener() {
            @Override
            public void onItemViewClick(View view, int position) {
                int id=view.getId();
                if (id==R.id.btnUpdate){
                    StudentEntity entity=studentAdapter.getItemEntity(position);
                    entity.name="更新学生"+position;
                    studentAdapter.notifyItemChanged(position);
                }else if (id==R.id.btnDelete){
                    studentAdapter.remove(position);
                }
            }
        });
    }



    @Override
    public void doStudentListSuccess(StudentListEntity studentListEntity) {
        refreshController.completeRefresh(studentListEntity.result);
    }

    @Override
    public void doStudentListFailed(String errMsg) {
        refreshController.completeRefresh();
    }
}
