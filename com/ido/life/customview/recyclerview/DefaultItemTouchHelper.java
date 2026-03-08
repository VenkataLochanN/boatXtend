package com.ido.life.customview.recyclerview;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultItemTouchHelper extends ItemTouchHelper {
    DefaultItemTouchHelperCallback itemTouchHelpCallback;

    public DefaultItemTouchHelper(DefaultItemTouchHelperCallback defaultItemTouchHelperCallback) {
        super(defaultItemTouchHelperCallback);
        this.itemTouchHelpCallback = defaultItemTouchHelperCallback;
    }

    public void setDragEnable(boolean z) {
        this.itemTouchHelpCallback.setDragEnable(z);
    }

    public void setSwipeEnable(boolean z) {
        this.itemTouchHelpCallback.setSwipeEnable(z);
    }

    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        if (this.itemTouchHelpCallback.isDragEnabled()) {
            startDrag(viewHolder);
        }
    }
}