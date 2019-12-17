package com.supcon.whd.login.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.supcon.whd.common.base.ui.adapter.BaseListDataRecycleViewAdapter;
import com.supcon.whd.common.base.ui.adapter.BaseRecyclerViewAdapter;
import com.supcon.whd.common.base.ui.adapter.viewholder.BaseRecyclerViewHolder;
import com.supcon.whd.login.R;
import com.supcon.whd.login.R2;
import com.supcon.whd.login.model.bean.StudentEntity;
import butterknife.BindView;

public class StudentAdapter extends BaseListDataRecycleViewAdapter<StudentEntity> {

    public StudentAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseRecyclerViewHolder<StudentEntity> getViewHolder(View view) {
        return new StudentHolder(view);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_student;
    }
    class StudentHolder extends BaseRecyclerViewHolder<StudentEntity>{

        @BindView(R2.id.itemStudentName)
        TextView itemStudentName;

        public StudentHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bind(StudentEntity studentEntity) {
            itemStudentName.setText(studentEntity.name);
        }
    }
}
