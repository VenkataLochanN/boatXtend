package com.ido.life.module.device.fragment;

import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class MyDialFragment_ViewBinding implements Unbinder {
    private MyDialFragment target;

    public MyDialFragment_ViewBinding(MyDialFragment myDialFragment, View view) {
        this.target = myDialFragment;
        myDialFragment.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mRecyclerView'", RecyclerView.class);
        myDialFragment.mDialContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.dial_content, "field 'mDialContent'", LinearLayout.class);
        myDialFragment.mLayoutLoadFailed = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_load_failed, "field 'mLayoutLoadFailed'", LinearLayout.class);
        myDialFragment.clv_record = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.clv_record, "field 'clv_record'", CustomItemLabelView.class);
        myDialFragment.clv_collect = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.clv_collect, "field 'clv_collect'", CustomItemLabelView.class);
        myDialFragment.rTvMemory = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_memory, "field 'rTvMemory'", RegularTextView.class);
        myDialFragment.mIvLoading = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.iv_loading, "field 'mIvLoading'", AppCompatImageView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MyDialFragment myDialFragment = this.target;
        if (myDialFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        myDialFragment.mRecyclerView = null;
        myDialFragment.mDialContent = null;
        myDialFragment.mLayoutLoadFailed = null;
        myDialFragment.clv_record = null;
        myDialFragment.clv_collect = null;
        myDialFragment.rTvMemory = null;
        myDialFragment.mIvLoading = null;
    }
}