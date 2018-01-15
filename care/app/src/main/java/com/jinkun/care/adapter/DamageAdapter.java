package com.jinkun.care.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinkun.care.R;
import com.jinkun.care.model.entity.DamageEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Created by coderwjq on 2017/8/18 12:56.
 * @Desc
 */

public class DamageAdapter extends BaseDiseaseAdapter {

    private OnItemClickListener mOnItemClickListener;

    public DamageAdapter(Context context) {
        super(context);
    }

    public void setDamageEntities(List<DamageEntity> entities) {
        setDatas(entities);
    }

    @Override
    public RecyclerView.ViewHolder getAddViewHolder(View view) {
        return new AddViewHolder(view);
    }

    @Override
    public RecyclerView.ViewHolder getNormalViewHolder(View view) {
        return new DamageViewHolder(view);
    }

    @Override
    public int getAddViewId() {
        return R.layout.item_add_detail_info;
    }

    @Override
    public int getNormalViewId() {
        return R.layout.item_detail_damage;
    }

    @Override
    public void onBindNormalView(RecyclerView.ViewHolder holder, final int position) {
        DamageViewHolder viewHolder = (DamageViewHolder) holder;
        DamageEntity damageEntity = (DamageEntity) mDatas.get(position);

        viewHolder.mTvDamageName.setText(damageEntity.getDamageName());
        viewHolder.mTvDamageTime.setText(damageEntity.getDamageTime());
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

    public class DamageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_damage_name)
        TextView mTvDamageName;
        @BindView(R.id.tv_damage_time)
        TextView mTvDamageTime;
        @BindView(R.id.btn_delete)
        Button mBtnDelete;
        @BindView(R.id.ll_container)
        LinearLayout mLlContainer;

        public DamageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
