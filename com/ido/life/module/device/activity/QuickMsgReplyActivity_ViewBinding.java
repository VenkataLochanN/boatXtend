package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class QuickMsgReplyActivity_ViewBinding implements Unbinder {
    private QuickMsgReplyActivity target;
    private View view7f0a02ed;

    public QuickMsgReplyActivity_ViewBinding(QuickMsgReplyActivity quickMsgReplyActivity) {
        this(quickMsgReplyActivity, quickMsgReplyActivity.getWindow().getDecorView());
    }

    public QuickMsgReplyActivity_ViewBinding(final QuickMsgReplyActivity quickMsgReplyActivity, View view) {
        this.target = quickMsgReplyActivity;
        quickMsgReplyActivity.mItemMsgContent = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_msg_content, "field 'mItemMsgContent'", CustomItemLabelView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.iv_add_msg, "field 'mIvAddMsg' and method 'onViewClicked'");
        quickMsgReplyActivity.mIvAddMsg = (ImageView) Utils.castView(viewFindRequiredView, R.id.iv_add_msg, "field 'mIvAddMsg'", ImageView.class);
        this.view7f0a02ed = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.QuickMsgReplyActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                quickMsgReplyActivity.onViewClicked();
            }
        });
        quickMsgReplyActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        quickMsgReplyActivity.mLayoutMsgContent = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.layout_msg_content, "field 'mLayoutMsgContent'", RelativeLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        QuickMsgReplyActivity quickMsgReplyActivity = this.target;
        if (quickMsgReplyActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        quickMsgReplyActivity.mItemMsgContent = null;
        quickMsgReplyActivity.mIvAddMsg = null;
        quickMsgReplyActivity.mRecyclerView = null;
        quickMsgReplyActivity.mLayoutMsgContent = null;
        this.view7f0a02ed.setOnClickListener(null);
        this.view7f0a02ed = null;
    }
}