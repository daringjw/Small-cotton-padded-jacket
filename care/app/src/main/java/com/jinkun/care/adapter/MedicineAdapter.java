package com.jinkun.care.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinkun.care.R;
import com.jinkun.care.model.entity.MedicineUseEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Created by coderwjq on 2017/8/18 12:56.
 * @Desc
 */

public class MedicineAdapter extends BaseDiseaseAdapter {

    private OnItemClickListener mOnItemClickListener;

    public MedicineAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder getAddViewHolder(View view) {
        return new AddViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder getNormalViewHolder(View view) {
        return new MedicineInfoViewHolder(view);
    }

    @Override
    public int getAddViewId() {
        return R.layout.item_add_detail_info;
    }

    @Override
    public int getNormalViewId() {
        return R.layout.item_medicine_table_info;
    }

    @Override
    public void onBindNormalView(RecyclerView.ViewHolder holder, final int position) {
        MedicineInfoViewHolder viewHolder = (MedicineInfoViewHolder) holder;
        MedicineUseEntity entity = (MedicineUseEntity) mDatas.get(position);

        viewHolder.mTvMedicineName.setText(entity.getMedicineName());
        viewHolder.mTvMorning.setText(entity.getMorning());
        viewHolder.mTvNoon.setText(entity.getNoon());
        viewHolder.mTvNight.setText(entity.getNight());
        viewHolder.mTvBeforeSleep.setText(entity.getBeforeSleep());
        viewHolder.mTvRemark.setText(entity.getRemarks());
        viewHolder.mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onDeleteButtonClick(position);
            }
        });
    }

    @Override
    public void onBindAddView(RecyclerView.ViewHolder holder, int position) {
        AddViewHolder viewHolder = (AddViewHolder) holder;

        viewHolder.mLlAddContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onAddButtonClick();
                }
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onDeleteButtonClick(int position);

        void onAddButtonClick();
    }

    public static class MedicineInfoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_medicine_name)
        TextView mTvMedicineName;
        @BindView(R.id.tv_morning)
        TextView mTvMorning;
        @BindView(R.id.tv_noon)
        TextView mTvNoon;
        @BindView(R.id.tv_night)
        TextView mTvNight;
        @BindView(R.id.tv_before_sleep)
        TextView mTvBeforeSleep;
        @BindView(R.id.tv_remark)
        TextView mTvRemark;
        @BindView(R.id.btn_delete)
        Button mBtnDelete;

        MedicineInfoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class AddViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_add)
        TextView mTvAdd;
        @BindView(R.id.ll_add_container)
        LinearLayout mLlAddContainer;

        public AddViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
