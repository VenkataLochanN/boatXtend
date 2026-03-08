package com.realsil.sdk.core.base;

import android.content.Context;
import android.view.LayoutInflater;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BaseRecyclerViewAdapter<D, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    public Context mContext;
    public final LayoutInflater mLayoutInflater;
    public List<D> n;

    public BaseRecyclerViewAdapter(Context context, List<D> list) {
        this.n = list;
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    public void appendEntityList(List<D> list) {
        if (list == null) {
            return;
        }
        if (this.n == null) {
            this.n = new ArrayList();
        }
        this.n.addAll(list);
        notifyDataSetChanged();
    }

    public D getEntity(int i) {
        List<D> list = this.n;
        if (list == null || i < 0 || i >= list.size()) {
            return null;
        }
        return this.n.get(i);
    }

    public List<D> getEntityList() {
        return this.n;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<D> list = this.n;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewRecycled(VH vh) {
        super.onViewRecycled(vh);
    }

    public void removeEntity(int i) {
        List<D> list = this.n;
        if (list == null || i < 0 || i >= list.size()) {
            return;
        }
        this.n.remove(i);
        notifyItemRemoved(i);
    }

    public void setEntityList(List<D> list) {
        this.n = list;
        notifyDataSetChanged();
    }
}