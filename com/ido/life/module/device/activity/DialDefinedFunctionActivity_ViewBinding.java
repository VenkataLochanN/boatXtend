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
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.customview.viewgroup.LoadingTextView;

/* JADX INFO: loaded from: classes2.dex */
public class DialDefinedFunctionActivity_ViewBinding implements Unbinder {
    private DialDefinedFunctionActivity target;
    private View view7f0a0141;
    private View view7f0a01bb;
    private View view7f0a0634;
    private View view7f0a0a36;
    private View view7f0a0a4e;

    public DialDefinedFunctionActivity_ViewBinding(DialDefinedFunctionActivity dialDefinedFunctionActivity) {
        this(dialDefinedFunctionActivity, dialDefinedFunctionActivity.getWindow().getDecorView());
    }

    public DialDefinedFunctionActivity_ViewBinding(final DialDefinedFunctionActivity dialDefinedFunctionActivity, View view) {
        this.target = dialDefinedFunctionActivity;
        dialDefinedFunctionActivity.mMtvDialName = (TextView) Utils.findRequiredViewAsType(view, R.id.mtv_dial_name, "field 'mMtvDialName'", TextView.class);
        dialDefinedFunctionActivity.mMtvDialSize = (TextView) Utils.findRequiredViewAsType(view, R.id.mtv_dial_size, "field 'mMtvDialSize'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.rtv_add_and_install, "field 'mRtvAddAndInstall' and method 'onViewClicked'");
        dialDefinedFunctionActivity.mRtvAddAndInstall = (RegularTextView) Utils.castView(viewFindRequiredView, R.id.rtv_add_and_install, "field 'mRtvAddAndInstall'", RegularTextView.class);
        this.view7f0a0634 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DialDefinedFunctionActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dialDefinedFunctionActivity.onViewClicked(view2);
            }
        });
        dialDefinedFunctionActivity.mLayoutDesc = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_description, "field 'mLayoutDesc'", LinearLayout.class);
        dialDefinedFunctionActivity.mMtvDescription = (ExpandLayout) Utils.findRequiredViewAsType(view, R.id.mtv_description, "field 'mMtvDescription'", ExpandLayout.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.view_set_use, "field 'mViewSetUse' and method 'onViewClicked'");
        dialDefinedFunctionActivity.mViewSetUse = (LoadingTextView) Utils.castView(viewFindRequiredView2, R.id.view_set_use, "field 'mViewSetUse'", LoadingTextView.class);
        this.view7f0a0a4e = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DialDefinedFunctionActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dialDefinedFunctionActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.view_delete_dial, "field 'mViewDeleteDial' and method 'onViewClicked'");
        dialDefinedFunctionActivity.mViewDeleteDial = (LoadingTextView) Utils.castView(viewFindRequiredView3, R.id.view_delete_dial, "field 'mViewDeleteDial'", LoadingTextView.class);
        this.view7f0a0a36 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DialDefinedFunctionActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dialDefinedFunctionActivity.onViewClicked(view2);
            }
        });
        dialDefinedFunctionActivity.mDividerBottom = Utils.findRequiredView(view, R.id.divider_bottom, "field 'mDividerBottom'");
        dialDefinedFunctionActivity.mLayoutBottom = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_bottom, "field 'mLayoutBottom'", LinearLayout.class);
        dialDefinedFunctionActivity.mIvLoading = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.iv_loading, "field 'mIvLoading'", AppCompatImageView.class);
        dialDefinedFunctionActivity.mLayoutDialInfo = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.layout_dial_info, "field 'mLayoutDialInfo'", RelativeLayout.class);
        dialDefinedFunctionActivity.mDividerTop = Utils.findRequiredView(view, R.id.divider_top, "field 'mDividerTop'");
        dialDefinedFunctionActivity.mLayoutNetworkError = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_network_error, "field 'mLayoutNetworkError'", LinearLayout.class);
        dialDefinedFunctionActivity.dialColorLl = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.dial_color_ll, "field 'dialColorLl'", LinearLayout.class);
        dialDefinedFunctionActivity.recyclerViewDefined_bg = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recyclerView_defined_bg, "field 'recyclerViewDefined_bg'", RecyclerView.class);
        dialDefinedFunctionActivity.recyclerViewDefined = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.recyclerView_defined, "field 'recyclerViewDefined'", RecyclerView.class);
        dialDefinedFunctionActivity.ivDialRl = (DialRoundRelativelayout) Utils.findRequiredViewAsType(view, R.id.iv_dial_rl, "field 'ivDialRl'", DialRoundRelativelayout.class);
        dialDefinedFunctionActivity.dialSv = (NestedScrollView) Utils.findRequiredViewAsType(view, R.id.dial_sv, "field 'dialSv'", NestedScrollView.class);
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.function_edit_rtv, "field 'mDialfunctionEdit' and method 'onViewClicked'");
        dialDefinedFunctionActivity.mDialfunctionEdit = (RegularTextView) Utils.castView(viewFindRequiredView4, R.id.function_edit_rtv, "field 'mDialfunctionEdit'", RegularTextView.class);
        this.view7f0a01bb = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DialDefinedFunctionActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dialDefinedFunctionActivity.onViewClicked(view2);
            }
        });
        dialDefinedFunctionActivity.mDialBgll = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.dial_defined_bg_ll, "field 'mDialBgll'", LinearLayout.class);
        dialDefinedFunctionActivity.mDialStylell = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.dial_defined_style_ll, "field 'mDialStylell'", LinearLayout.class);
        dialDefinedFunctionActivity.mColorLL = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.dial_defined_color_ll, "field 'mColorLL'", LinearLayout.class);
        dialDefinedFunctionActivity.mDialFunctionll = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.dial_defined_function_ll, "field 'mDialFunctionll'", LinearLayout.class);
        dialDefinedFunctionActivity.mFunctionContentRtv = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.function_content_rtv, "field 'mFunctionContentRtv'", RegularTextView.class);
        View viewFindRequiredView5 = Utils.findRequiredView(view, R.id.dial_defined_worldtime_ll, "field 'mWorldTimeLL' and method 'onViewClicked'");
        dialDefinedFunctionActivity.mWorldTimeLL = (LinearLayout) Utils.castView(viewFindRequiredView5, R.id.dial_defined_worldtime_ll, "field 'mWorldTimeLL'", LinearLayout.class);
        this.view7f0a0141 = viewFindRequiredView5;
        viewFindRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DialDefinedFunctionActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dialDefinedFunctionActivity.onViewClicked(view2);
            }
        });
        dialDefinedFunctionActivity.mClvCurrentCity = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.clv_city, "field 'mClvCurrentCity'", CustomItemLabelView.class);
        dialDefinedFunctionActivity.mContentLL = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.content_ll, "field 'mContentLL'", LinearLayout.class);
        dialDefinedFunctionActivity.mDialLoadll = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.dial_load_Ll, "field 'mDialLoadll'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DialDefinedFunctionActivity dialDefinedFunctionActivity = this.target;
        if (dialDefinedFunctionActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dialDefinedFunctionActivity.mMtvDialName = null;
        dialDefinedFunctionActivity.mMtvDialSize = null;
        dialDefinedFunctionActivity.mRtvAddAndInstall = null;
        dialDefinedFunctionActivity.mLayoutDesc = null;
        dialDefinedFunctionActivity.mMtvDescription = null;
        dialDefinedFunctionActivity.mViewSetUse = null;
        dialDefinedFunctionActivity.mViewDeleteDial = null;
        dialDefinedFunctionActivity.mDividerBottom = null;
        dialDefinedFunctionActivity.mLayoutBottom = null;
        dialDefinedFunctionActivity.mIvLoading = null;
        dialDefinedFunctionActivity.mLayoutDialInfo = null;
        dialDefinedFunctionActivity.mDividerTop = null;
        dialDefinedFunctionActivity.mLayoutNetworkError = null;
        dialDefinedFunctionActivity.dialColorLl = null;
        dialDefinedFunctionActivity.recyclerViewDefined_bg = null;
        dialDefinedFunctionActivity.recyclerViewDefined = null;
        dialDefinedFunctionActivity.ivDialRl = null;
        dialDefinedFunctionActivity.dialSv = null;
        dialDefinedFunctionActivity.mDialfunctionEdit = null;
        dialDefinedFunctionActivity.mDialBgll = null;
        dialDefinedFunctionActivity.mDialStylell = null;
        dialDefinedFunctionActivity.mColorLL = null;
        dialDefinedFunctionActivity.mDialFunctionll = null;
        dialDefinedFunctionActivity.mFunctionContentRtv = null;
        dialDefinedFunctionActivity.mWorldTimeLL = null;
        dialDefinedFunctionActivity.mClvCurrentCity = null;
        dialDefinedFunctionActivity.mContentLL = null;
        dialDefinedFunctionActivity.mDialLoadll = null;
        this.view7f0a0634.setOnClickListener(null);
        this.view7f0a0634 = null;
        this.view7f0a0a4e.setOnClickListener(null);
        this.view7f0a0a4e = null;
        this.view7f0a0a36.setOnClickListener(null);
        this.view7f0a0a36 = null;
        this.view7f0a01bb.setOnClickListener(null);
        this.view7f0a01bb = null;
        this.view7f0a0141.setOnClickListener(null);
        this.view7f0a0141 = null;
    }
}