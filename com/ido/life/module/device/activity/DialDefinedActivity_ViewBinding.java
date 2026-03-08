package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.DialRoundRelativelayout;
import com.ido.life.customview.ExpandLayout;
import com.ido.life.customview.viewgroup.LoadingTextView;

/* JADX INFO: loaded from: classes2.dex */
public class DialDefinedActivity_ViewBinding implements Unbinder {
    private DialDefinedActivity target;
    private View view7f0a0634;
    private View view7f0a0a36;
    private View view7f0a0a4e;

    public DialDefinedActivity_ViewBinding(DialDefinedActivity dialDefinedActivity) {
        this(dialDefinedActivity, dialDefinedActivity.getWindow().getDecorView());
    }

    public DialDefinedActivity_ViewBinding(final DialDefinedActivity dialDefinedActivity, View view) {
        this.target = dialDefinedActivity;
        dialDefinedActivity.mMtvDialName = (TextView) Utils.findRequiredViewAsType(view, R.id.mtv_dial_name, "field 'mMtvDialName'", TextView.class);
        dialDefinedActivity.mMtvDialSize = (TextView) Utils.findRequiredViewAsType(view, R.id.mtv_dial_size, "field 'mMtvDialSize'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.rtv_add_and_install, "field 'mRtvAddAndInstall' and method 'onViewClicked'");
        dialDefinedActivity.mRtvAddAndInstall = (RegularTextView) Utils.castView(viewFindRequiredView, R.id.rtv_add_and_install, "field 'mRtvAddAndInstall'", RegularTextView.class);
        this.view7f0a0634 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DialDefinedActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dialDefinedActivity.onViewClicked(view2);
            }
        });
        dialDefinedActivity.mLayoutDesc = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_description, "field 'mLayoutDesc'", LinearLayout.class);
        dialDefinedActivity.mMtvDescription = (ExpandLayout) Utils.findRequiredViewAsType(view, R.id.mtv_description, "field 'mMtvDescription'", ExpandLayout.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.view_set_use, "field 'mViewSetUse' and method 'onViewClicked'");
        dialDefinedActivity.mViewSetUse = (LoadingTextView) Utils.castView(viewFindRequiredView2, R.id.view_set_use, "field 'mViewSetUse'", LoadingTextView.class);
        this.view7f0a0a4e = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DialDefinedActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dialDefinedActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.view_delete_dial, "field 'mViewDeleteDial' and method 'onViewClicked'");
        dialDefinedActivity.mViewDeleteDial = (LoadingTextView) Utils.castView(viewFindRequiredView3, R.id.view_delete_dial, "field 'mViewDeleteDial'", LoadingTextView.class);
        this.view7f0a0a36 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DialDefinedActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dialDefinedActivity.onViewClicked(view2);
            }
        });
        dialDefinedActivity.mDividerBottom = Utils.findRequiredView(view, R.id.divider_bottom, "field 'mDividerBottom'");
        dialDefinedActivity.mLayoutBottom = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_bottom, "field 'mLayoutBottom'", LinearLayout.class);
        dialDefinedActivity.mIvLoading = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.iv_loading, "field 'mIvLoading'", AppCompatImageView.class);
        dialDefinedActivity.mLayoutDialInfo = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.layout_dial_info, "field 'mLayoutDialInfo'", RelativeLayout.class);
        dialDefinedActivity.mDividerTop = Utils.findRequiredView(view, R.id.divider_top, "field 'mDividerTop'");
        dialDefinedActivity.mLayoutNetworkError = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_network_error, "field 'mLayoutNetworkError'", LinearLayout.class);
        dialDefinedActivity.dialColorLl = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.dial_color_ll, "field 'dialColorLl'", LinearLayout.class);
        dialDefinedActivity.recyclerViewDefined = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recyclerView_defined, "field 'recyclerViewDefined'", RecyclerView.class);
        dialDefinedActivity.ivDialRl = (DialRoundRelativelayout) Utils.findRequiredViewAsType(view, R.id.iv_dial_rl, "field 'ivDialRl'", DialRoundRelativelayout.class);
        dialDefinedActivity.dialSv = (NestedScrollView) Utils.findRequiredViewAsType(view, R.id.dial_sv, "field 'dialSv'", NestedScrollView.class);
        dialDefinedActivity.rtlDial = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rtl_dial, "field 'rtlDial'", RelativeLayout.class);
        dialDefinedActivity.mContentLL = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.content_ll, "field 'mContentLL'", LinearLayout.class);
        dialDefinedActivity.mDialLoadll = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.dial_load_Ll, "field 'mDialLoadll'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DialDefinedActivity dialDefinedActivity = this.target;
        if (dialDefinedActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dialDefinedActivity.mMtvDialName = null;
        dialDefinedActivity.mMtvDialSize = null;
        dialDefinedActivity.mRtvAddAndInstall = null;
        dialDefinedActivity.mLayoutDesc = null;
        dialDefinedActivity.mMtvDescription = null;
        dialDefinedActivity.mViewSetUse = null;
        dialDefinedActivity.mViewDeleteDial = null;
        dialDefinedActivity.mDividerBottom = null;
        dialDefinedActivity.mLayoutBottom = null;
        dialDefinedActivity.mIvLoading = null;
        dialDefinedActivity.mLayoutDialInfo = null;
        dialDefinedActivity.mDividerTop = null;
        dialDefinedActivity.mLayoutNetworkError = null;
        dialDefinedActivity.dialColorLl = null;
        dialDefinedActivity.recyclerViewDefined = null;
        dialDefinedActivity.ivDialRl = null;
        dialDefinedActivity.dialSv = null;
        dialDefinedActivity.rtlDial = null;
        dialDefinedActivity.mContentLL = null;
        dialDefinedActivity.mDialLoadll = null;
        this.view7f0a0634.setOnClickListener(null);
        this.view7f0a0634 = null;
        this.view7f0a0a4e.setOnClickListener(null);
        this.view7f0a0a4e = null;
        this.view7f0a0a36.setOnClickListener(null);
        this.view7f0a0a36 = null;
    }
}