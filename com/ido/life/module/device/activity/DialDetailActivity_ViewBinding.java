package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.NestedScrollView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.ExpandLayout;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.LoadingTextView;

/* JADX INFO: loaded from: classes2.dex */
public class DialDetailActivity_ViewBinding implements Unbinder {
    private DialDetailActivity target;
    private View view7f0a0634;
    private View view7f0a0a36;
    private View view7f0a0a4e;

    public DialDetailActivity_ViewBinding(DialDetailActivity dialDetailActivity) {
        this(dialDetailActivity, dialDetailActivity.getWindow().getDecorView());
    }

    public DialDetailActivity_ViewBinding(final DialDetailActivity dialDetailActivity, View view) {
        this.target = dialDetailActivity;
        dialDetailActivity.mIvDial = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_dial, "field 'mIvDial'", ImageView.class);
        dialDetailActivity.mMtvDialName = (TextView) Utils.findRequiredViewAsType(view, R.id.mtv_dial_name, "field 'mMtvDialName'", TextView.class);
        dialDetailActivity.mMtvDialSize = (TextView) Utils.findRequiredViewAsType(view, R.id.mtv_dial_size, "field 'mMtvDialSize'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.rtv_add_and_install, "field 'mRtvAddAndInstall' and method 'onViewClicked'");
        dialDetailActivity.mRtvAddAndInstall = (RegularTextView) Utils.castView(viewFindRequiredView, R.id.rtv_add_and_install, "field 'mRtvAddAndInstall'", RegularTextView.class);
        this.view7f0a0634 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DialDetailActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dialDetailActivity.onViewClicked(view2);
            }
        });
        dialDetailActivity.mLayoutDesc = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_description, "field 'mLayoutDesc'", LinearLayout.class);
        dialDetailActivity.mMtvDescription = (ExpandLayout) Utils.findRequiredViewAsType(view, R.id.mtv_description, "field 'mMtvDescription'", ExpandLayout.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.view_set_use, "field 'mViewSetUse' and method 'onViewClicked'");
        dialDetailActivity.mViewSetUse = (LoadingTextView) Utils.castView(viewFindRequiredView2, R.id.view_set_use, "field 'mViewSetUse'", LoadingTextView.class);
        this.view7f0a0a4e = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DialDetailActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dialDetailActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.view_delete_dial, "field 'mViewDeleteDial' and method 'onViewClicked'");
        dialDetailActivity.mViewDeleteDial = (LoadingTextView) Utils.castView(viewFindRequiredView3, R.id.view_delete_dial, "field 'mViewDeleteDial'", LoadingTextView.class);
        this.view7f0a0a36 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DialDetailActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dialDetailActivity.onViewClicked(view2);
            }
        });
        dialDetailActivity.mDividerBottom = Utils.findRequiredView(view, R.id.divider_bottom, "field 'mDividerBottom'");
        dialDetailActivity.mLayoutBottom = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_bottom, "field 'mLayoutBottom'", LinearLayout.class);
        dialDetailActivity.mIvLoading = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.iv_loading, "field 'mIvLoading'", AppCompatImageView.class);
        dialDetailActivity.mLayoutDialInfo = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.layout_dial_info, "field 'mLayoutDialInfo'", RelativeLayout.class);
        dialDetailActivity.mDividerTop = Utils.findRequiredView(view, R.id.divider_top, "field 'mDividerTop'");
        dialDetailActivity.mScrollView = (NestedScrollView) Utils.findRequiredViewAsType(view, R.id.scroll_view, "field 'mScrollView'", NestedScrollView.class);
        dialDetailActivity.mLayoutNetworkError = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_network_error, "field 'mLayoutNetworkError'", LinearLayout.class);
        dialDetailActivity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
        dialDetailActivity.rtlDial = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rtl_dial, "field 'rtlDial'", RelativeLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DialDetailActivity dialDetailActivity = this.target;
        if (dialDetailActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dialDetailActivity.mIvDial = null;
        dialDetailActivity.mMtvDialName = null;
        dialDetailActivity.mMtvDialSize = null;
        dialDetailActivity.mRtvAddAndInstall = null;
        dialDetailActivity.mLayoutDesc = null;
        dialDetailActivity.mMtvDescription = null;
        dialDetailActivity.mViewSetUse = null;
        dialDetailActivity.mViewDeleteDial = null;
        dialDetailActivity.mDividerBottom = null;
        dialDetailActivity.mLayoutBottom = null;
        dialDetailActivity.mIvLoading = null;
        dialDetailActivity.mLayoutDialInfo = null;
        dialDetailActivity.mDividerTop = null;
        dialDetailActivity.mScrollView = null;
        dialDetailActivity.mLayoutNetworkError = null;
        dialDetailActivity.mCommLoadingView = null;
        dialDetailActivity.rtlDial = null;
        this.view7f0a0634.setOnClickListener(null);
        this.view7f0a0634 = null;
        this.view7f0a0a4e.setOnClickListener(null);
        this.view7f0a0a4e = null;
        this.view7f0a0a36.setOnClickListener(null);
        this.view7f0a0a36 = null;
    }
}