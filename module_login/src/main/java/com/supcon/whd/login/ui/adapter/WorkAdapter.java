package com.supcon.whd.login.ui.adapter;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.supcon.whd.common.base.ui.adapter.BaseRecyclerViewAdapter;
import com.supcon.whd.common.base.ui.adapter.viewholder.BaseRecyclerViewHolder;
import com.supcon.whd.login.R;
import com.supcon.whd.login.R2;
import com.supcon.whd.login.model.bean.WorkEntity;

import java.util.List;

import butterknife.BindView;


public class WorkAdapter extends BaseRecyclerViewAdapter<WorkEntity> {

    public WorkAdapter(Context context, List<WorkEntity> list) {
        super(context, list);
    }

    @Override
    public BaseRecyclerViewHolder<WorkEntity> getViewHolder(View view) {
        return new WorkHolder(view);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_work;
    }

    class WorkHolder extends BaseRecyclerViewHolder<WorkEntity> implements View.OnClickListener {

       @BindView(R2.id.itemWorkView)
        RelativeLayout itemWorkView;
        @BindView(R2.id.itemWorkTv)
        TextView itemWorkTv;
        @BindView(R2.id.itemWorkImg)
        ImageView itemWorkImg;

        public WorkHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initListener() {
            super.initListener();
            itemWorkView.setOnClickListener(this);
        }

        @Override
        public void bind(WorkEntity workEntity) {
            itemWorkImg.setImageResource(workEntity.res);
            itemWorkTv.setText(workEntity.content);
        }

        @Override
        public void onClick(View view) {
            onItemViewClick(view,getAdapterPosition());
        }
    }
}
