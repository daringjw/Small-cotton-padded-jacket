package com.jinkun.care.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinkun.care.R;
import com.jinkun.care.model.entity.DetailDiseaseEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Created by coderwjq on 2017/8/17 15:56.
 * @Desc
 */

public class DetailDiseaseAdapter extends RecyclerView.Adapter {
    private static final int BASE_COUNT = 1;
    private static final int VIEW_TYPE_NORMAL = 1;
    private static final int VIEW_TYPE_ADD = 2;
    private Context mContext;
    private List<DetailDiseaseEntity> mDiseaseEntities;
    private OnItemClickListener mOnItemClickListener;

    public DetailDiseaseAdapter(Context content) {
        mContext = content;
    }

    public void setOnAddButtonClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setDiseaseEntities(List<DetailDiseaseEntity> diseaseEntities) {
        mDiseaseEntities = diseaseEntities;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return onCreateNormalView(parent);
            case VIEW_TYPE_ADD:
                return onCreateAddView(parent);
        }
        return null;
    }

    private RecyclerView.ViewHolder onCreateAddView(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_add_detail_info, parent, false);
        return new AddViewHolder(view);
    }

    private RecyclerView.ViewHolder onCreateNormalView(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_detail_disease, parent, false);
        return new DiseaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case VIEW_TYPE_NORMAL:
                onBindDiseaseViewHolder((DiseaseViewHolder) holder, position);
                break;
            case VIEW_TYPE_ADD:
                onBindAddViewHolder((AddViewHolder) holder, position);
                break;
        }

    }

    private void onBindAddViewHolder(AddViewHolder holder, int position) {
        holder.mLlAddContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onAddButtonClick();
                }
            }
        });
    }

    private void onBindDiseaseViewHolder(final DiseaseViewHolder holder, final int position) {
        DetailDiseaseEntity diseaseEntity = mDiseaseEntities.get(position);

        holder.mTvDisease.setText(diseaseEntity.getDiseaseName());

        holder.mTvDiagnoseTime.setText(diseaseEntity.getDiagnoseTime());

        holder.mLlContainer.setSelected(diseaseEntity.isChecked());

        holder.mLlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onNormalButtonClick(position, view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDiseaseEntities == null ? 0 + BASE_COUNT : mDiseaseEntities.size() + BASE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mDiseaseEntities.size()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_ADD;
        }
    }

    public interface OnItemClickListener {
        void onNormalButtonClick(int position, View view);

        void onAddButtonClick();
    }

    public class DiseaseViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_disease)
        TextView mTvDisease;
        @BindView(R.id.tv_diagnose_time)
        TextView mTvDiagnoseTime;
        @BindView(R.id.ll_container)
        LinearLayout mLlContainer;

        public DiseaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
