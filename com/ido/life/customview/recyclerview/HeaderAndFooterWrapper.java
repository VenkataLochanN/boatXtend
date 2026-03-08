package com.ido.life.customview.recyclerview;

import android.view.View;
import android.view.ViewGroup;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ido.life.customview.recyclerview.WrapperUtils;

/* JADX INFO: loaded from: classes2.dex */
public class HeaderAndFooterWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;
    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private RecyclerView.Adapter mInnerAdapter;
    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();

    public HeaderAndFooterWrapper(RecyclerView.Adapter adapter) {
        this.mInnerAdapter = adapter;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.mHeaderViews.get(i) != null) {
            return CommonRecyclerViewHolder.createViewHolder(viewGroup.getContext(), this.mHeaderViews.get(i));
        }
        if (this.mFootViews.get(i) != null) {
            return CommonRecyclerViewHolder.createViewHolder(viewGroup.getContext(), this.mFootViews.get(i));
        }
        return this.mInnerAdapter.onCreateViewHolder(viewGroup, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (isHeaderViewPos(i)) {
            return this.mHeaderViews.keyAt(i);
        }
        if (isFooterViewPos(i)) {
            return this.mFootViews.keyAt((i - getHeadersCount()) - getRealItemCount());
        }
        return this.mInnerAdapter.getItemViewType(i - getHeadersCount());
    }

    private int getRealItemCount() {
        return this.mInnerAdapter.getItemCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (isHeaderViewPos(i) || isFooterViewPos(i)) {
            return;
        }
        this.mInnerAdapter.onBindViewHolder(viewHolder, i - getHeadersCount());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        WrapperUtils.onAttachedToRecyclerView(this.mInnerAdapter, recyclerView, new WrapperUtils.SpanSizeCallback() { // from class: com.ido.life.customview.recyclerview.-$$Lambda$HeaderAndFooterWrapper$Y7XaQufhqmJ5WF1jdaS3Z9h8K1g
            @Override // com.ido.life.customview.recyclerview.WrapperUtils.SpanSizeCallback
            public final int getSpanSize(GridLayoutManager gridLayoutManager, GridLayoutManager.SpanSizeLookup spanSizeLookup, int i) {
                return this.f$0.lambda$onAttachedToRecyclerView$0$HeaderAndFooterWrapper(gridLayoutManager, spanSizeLookup, i);
            }
        });
    }

    public /* synthetic */ int lambda$onAttachedToRecyclerView$0$HeaderAndFooterWrapper(GridLayoutManager gridLayoutManager, GridLayoutManager.SpanSizeLookup spanSizeLookup, int i) {
        int itemViewType = getItemViewType(i);
        if (this.mHeaderViews.get(itemViewType) != null) {
            return gridLayoutManager.getSpanCount();
        }
        if (this.mFootViews.get(itemViewType) != null) {
            return gridLayoutManager.getSpanCount();
        }
        if (spanSizeLookup != null) {
            return spanSizeLookup.getSpanSize(i);
        }
        return 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        this.mInnerAdapter.onViewAttachedToWindow(viewHolder);
        int layoutPosition = viewHolder.getLayoutPosition();
        if (isHeaderViewPos(layoutPosition) || isFooterViewPos(layoutPosition)) {
            WrapperUtils.setFullSpan(viewHolder);
        }
    }

    private boolean isHeaderViewPos(int i) {
        return i < getHeadersCount();
    }

    private boolean isFooterViewPos(int i) {
        return i >= getHeadersCount() + getRealItemCount();
    }

    public void addHeaderView(View view) {
        SparseArrayCompat<View> sparseArrayCompat = this.mHeaderViews;
        sparseArrayCompat.put(sparseArrayCompat.size() + 100000, view);
        this.mInnerAdapter.notifyDataSetChanged();
    }

    public void addFootView(View view) {
        SparseArrayCompat<View> sparseArrayCompat = this.mFootViews;
        sparseArrayCompat.put(sparseArrayCompat.size() + BASE_ITEM_TYPE_FOOTER, view);
        this.mInnerAdapter.notifyDataSetChanged();
    }

    public int getHeadersCount() {
        return this.mHeaderViews.size();
    }

    public int getFootersCount() {
        return this.mFootViews.size();
    }

    public void removeFootView(View view) {
        int iIndexOfValue;
        if (view == null || (iIndexOfValue = this.mFootViews.indexOfValue(view)) == -1) {
            return;
        }
        this.mFootViews.removeAt(iIndexOfValue);
        this.mInnerAdapter.notifyDataSetChanged();
    }

    public void removeHeaderView(View view) {
        int iIndexOfValue;
        if (view == null || (iIndexOfValue = this.mHeaderViews.indexOfValue(view)) == -1) {
            return;
        }
        this.mHeaderViews.removeAt(iIndexOfValue);
        this.mInnerAdapter.notifyDataSetChanged();
    }
}