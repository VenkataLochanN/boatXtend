package com.ido.life.customview.recyclerview;

import android.graphics.Canvas;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultItemTouchHelperCallback extends ItemTouchHelper.Callback {
    public static final float ALPHA_FULL = 1.0f;
    private boolean isCanDrag = false;
    private boolean isCanSwipe = false;
    private boolean isLongPressDrag = false;
    private final ItemTouchHelperAdapter mAdapter;

    public void setDragEnable(boolean z) {
        this.isCanDrag = z;
    }

    public void setLongPressDragEnable(boolean z) {
        this.isLongPressDrag = z;
    }

    public void setSwipeEnable(boolean z) {
        this.isCanSwipe = z;
    }

    public boolean isDragEnabled() {
        return this.isCanDrag;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isLongPressDragEnabled() {
        return this.isLongPressDrag;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return this.isCanSwipe;
    }

    public DefaultItemTouchHelperCallback(ItemTouchHelperAdapter itemTouchHelperAdapter) {
        this.mAdapter = itemTouchHelperAdapter;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            return makeMovementFlags(15, 0);
        }
        return makeMovementFlags(3, 48);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        if (viewHolder.getItemViewType() != viewHolder2.getItemViewType()) {
            return false;
        }
        this.mAdapter.onItemMove(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
        return true;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        this.mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f2, float f3, int i, boolean z) {
        if (i == 1) {
            viewHolder.itemView.setAlpha(1.0f - (Math.abs(f2) / viewHolder.itemView.getWidth()));
            viewHolder.itemView.setTranslationX(f2);
            return;
        }
        super.onChildDraw(canvas, recyclerView, viewHolder, f2, f3, i, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
        if (i != 0 && (viewHolder instanceof ItemTouchHelperViewHolder)) {
            ((ItemTouchHelperViewHolder) viewHolder).onItemSelected();
        }
        super.onSelectedChanged(viewHolder, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setAlpha(1.0f);
        if (viewHolder instanceof ItemTouchHelperViewHolder) {
            ((ItemTouchHelperViewHolder) viewHolder).onItemClear();
        }
    }
}