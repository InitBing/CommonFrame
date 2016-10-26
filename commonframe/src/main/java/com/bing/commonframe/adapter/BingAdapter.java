package com.bing.commonframe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by bingo on 2016/10/26.
 */

public abstract class BingAdapter <D,T extends BingAdapter.BaseViewHolder> extends RecyclerView.Adapter<T> {

    protected List<D> listData = null;

    public List<D> getListData() {
        return listData;
    }

    public void setListData(List<D> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    public void addItem(D t) {
        this.listData.add(t);
        notifyDataSetChanged();
    }

    public void addItem(int position, D t) {
        if (t == null) return;
        this.listData.add(position, t);
        notifyItemChanged(position);
    }

    public void cleanItem() {
        listData.clear();
        notifyDataSetChanged();
    }

    public void addItemList(List<D> ts) {
        if (ts == null) return;
        this.listData.addAll(ts);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        holder.bindData();
    }

    public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bindData();
    }

}

