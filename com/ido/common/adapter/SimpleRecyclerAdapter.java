package com.ido.common.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class SimpleRecyclerAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private List<T> data;

    public abstract int getItemLayout(int i);

    public abstract View getItemLayoutView(int i, Context context);

    public SimpleRecyclerAdapter() {
        this.data = new ArrayList();
    }

    public SimpleRecyclerAdapter(List<T> list) {
        this();
        addAll(list);
    }

    public SimpleRecyclerAdapter(T... tArr) {
        this();
        addAll(tArr);
    }

    public int indexOf(T t) {
        if (t != null) {
            return this.data.indexOf(t);
        }
        return -1;
    }

    public List<T> getAll() {
        return this.data;
    }

    public T getItem(int i) {
        if (i < 0 || i >= this.data.size()) {
            return null;
        }
        return this.data.get(i);
    }

    public SimpleRecyclerAdapter<T> addItem(T t) {
        if (t != null) {
            this.data.add(t);
        }
        return this;
    }

    public SimpleRecyclerAdapter<T> addItem(T t, int i) {
        if (t != null && i >= 0 && i < this.data.size()) {
            this.data.add(i, t);
        } else if (t != null && i >= 0 && i >= this.data.size()) {
            this.data.add(t);
        }
        return this;
    }

    public SimpleRecyclerAdapter<T> addAll(List<T> list) {
        if (list != null && list.size() > 0) {
            this.data.addAll(list);
        }
        return this;
    }

    public SimpleRecyclerAdapter<T> addAlluniq(List<T> list) {
        if (list != null && list.size() > 0) {
            HashSet hashSet = new HashSet();
            hashSet.addAll(this.data);
            for (T t : list) {
                if (hashSet.add(t)) {
                    this.data.add(t);
                } else {
                    List<T> list2 = this.data;
                    list2.set(list2.indexOf(t), t);
                }
            }
        }
        return this;
    }

    public SimpleRecyclerAdapter<T> addAll(T... tArr) {
        if (tArr != null && tArr.length > 0) {
            for (T t : tArr) {
                this.data.add(t);
            }
        }
        return this;
    }

    public T remove(int i) {
        if (i < 0 || i >= this.data.size()) {
            return null;
        }
        return this.data.remove(i);
    }

    public SimpleRecyclerAdapter<T> removeAll() {
        this.data.clear();
        return this;
    }

    public SimpleRecyclerAdapter<T> removeAll(List<T> list) {
        if (list != null) {
            this.data.removeAll(list);
        }
        return this;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = getItemLayoutView(i, viewGroup.getContext());
        if (itemLayoutView == null) {
            itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(getItemLayout(i), (ViewGroup) null, false);
        }
        return new ViewHolder(itemLayoutView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private SparseArray<View> holder;

        public ViewHolder(View view) {
            super(view);
            this.holder = new SparseArray<>();
        }

        public <K extends View> K getView(int i) {
            K k = (K) this.holder.get(i);
            if (k != null) {
                return k;
            }
            K k2 = (K) this.itemView.findViewById(i);
            this.holder.put(i, k2);
            return k2;
        }
    }
}