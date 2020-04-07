package com.supcon.whd.login.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
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
    public boolean isExpandSwpie=false;
    class StudentHolder extends BaseRecyclerViewHolder<StudentEntity> implements View.OnClickListener {

        @BindView(R2.id.swipeLayout)
        SwipeLayout swipeLayout;
        @BindView(R2.id.itemStudentName)
        TextView itemStudentName;
        @BindView(R2.id.btnUpdate)
        Button btnUpdate;
        @BindView(R2.id.btnDelete)
        Button btnDelete;


        public StudentHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initListener() {
            super.initListener();
            btnDelete.setOnClickListener(this);
            btnUpdate.setOnClickListener(this);
        }

        @Override
        public void bind(StudentEntity studentEntity) {
            itemStudentName.setText(studentEntity.name);
            swipeLayout.addSwipeListener(new SimpleSwipeListener(){
                @Override
                public void onOpen(SwipeLayout layout) {
                    super.onOpen(layout);
                    isExpandSwpie=true;
                    Log.i("SwipeLayout","-->onOpen");
                }

                @Override
                public void onClose(SwipeLayout layout) {
                    super.onClose(layout);
                    isExpandSwpie=false;
                    Log.i("SwipeLayout","-->onClose");
                }
            });
        }

        @Override
        public void onClick(View view) {
            onItemViewClick(view,getAdapterPosition());
        }
    }
}
