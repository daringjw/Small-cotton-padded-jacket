package com.jinkun.care.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @Created by coderwjq on 2017/8/18 14:52.
 * @Desc
 */

public abstract class BaseDiseaseAdapter<T> extends RecyclerView.Adapter {
    private static final int BASE_COUNT = 1;
    private static final int VIEW_TYPE_NORMAL = 1;
    private static final int VIEW_TYPE_ADD = 2;
    protected List<T> mDatas;
    protected Context mContext;

    public BaseDiseaseAdapter(Context context) {
        mContext = context;
    }

    public void setDatas(List<T> datas) {
        mDatas = datas;
    }

    public abstract RecyclerView.ViewHolder getAddViewHolder(View view);

    public abstract RecyclerView.ViewHolder getNormalViewHolder(View view);

    public abstract int getAddViewId();

    public abstract int getNormalViewId();

    public abstract void onBindNormalView(RecyclerView.ViewHolder holder, int position);

    public abstract void onBindAddView(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 + BASE_COUNT : mDatas.size() + BASE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < mDatas.size()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_ADD;
        }
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
        View view = LayoutInflater.from(mContext).inflate(getAddViewId(), parent, false);
        return getAddViewHolder(view);
    }

    private RecyclerView.ViewHolder onCreateNormalView(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(getNormalViewId(), parent, false);
        return getNormalViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case VIEW_TYPE_NORMAL:
                onBindNormalView(holder, position);
                break;
            case VIEW_TYPE_ADD:
                onBindAddView(holder, position);
                break;
        }
    }
}
