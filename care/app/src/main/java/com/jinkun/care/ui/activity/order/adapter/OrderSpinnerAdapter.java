package com.jinkun.care.ui.activity.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jinkun.care.R;
import com.jinkun.care.ui.activity.order.model.OrderSpinnerBean;

import java.util.List;

/**
 * Created by coderwjq on 2017/9/6 17:21.
 */

public class OrderSpinnerAdapter extends BaseAdapter {
    private Context mContext;
    private List<OrderSpinnerBean> mData;

    public OrderSpinnerAdapter(Context context, List<OrderSpinnerBean> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public OrderSpinnerBean getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_order_spinner_layout, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mTvContent.setText(mData.get(position).getValue());

        return convertView;
    }

    static class ViewHolder {

        private final TextView mTvContent;

        public ViewHolder(View view) {
            mTvContent = view.findViewById(R.id.tv_content);
        }
    }
}
