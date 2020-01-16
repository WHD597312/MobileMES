package com.supcon.whd.login.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.supcon.whd.common.base.ui.adapter.BaseListDataRecycleViewAdapter;
import com.supcon.whd.common.base.ui.adapter.BaseRecyclerViewAdapter;
import com.supcon.whd.common.base.ui.adapter.viewholder.BaseRecyclerViewHolder;
import com.supcon.whd.common.base.ui.view.listener.CustomSwipeListener;
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
    class StudentHolder extends BaseRecyclerViewHolder<StudentEntity> implements View.OnClickListener {

        @BindView(R2.id.itemStudentName)
        TextView itemStudentName;
        @BindView(R2.id.itemSwipeLayout)
        SwipeLayout itemSwipeLayout;
        @BindView(R2.id.itemDelete)
        TextView itemDelete;
        @BindView(R2.id.itemUpdate)
        TextView itemUpdate;
        @Override
        public void initListener() {
            super.initListener();
            itemSwipeLayout.addSwipeListener(new SimpleSwipeListener());
        }

        public StudentHolder(View itemView) {
            super(itemView);
            itemDelete.setOnClickListener(this);
            itemUpdate.setOnClickListener(this);
        }

        @Override
        public void bind(StudentEntity studentEntity) {
            itemStudentName.setText(studentEntity.name);

        }

        @Override
        public void onClick(View view) {
            int id=view.getId();

            if (id==R.id.itemDelete){
                remove(getAdapterPosition());
                notifyDataSetChanged();
            }else if (id==R.id.itemUpdate){
                StudentEntity entity=getItemEntity(getLayoutPosition());
                entity.name="更新学生姓名";
                notifyItemChanged(getAdapterPosition());
            }
        }
    }
}
