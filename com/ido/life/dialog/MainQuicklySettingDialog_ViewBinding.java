package com.ido.life.dialog;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;

/* JADX INFO: loaded from: classes2.dex */
public class MainQuicklySettingDialog_ViewBinding implements Unbinder {
    private MainQuicklySettingDialog target;
    private View view7f0a024e;
    private View view7f0a028b;
    private View view7f0a0299;
    private View view7f0a02b9;
    private View view7f0a080e;
    private View view7f0a084c;

    public MainQuicklySettingDialog_ViewBinding(final MainQuicklySettingDialog mainQuicklySettingDialog, View view) {
        this.target = mainQuicklySettingDialog;
        mainQuicklySettingDialog.mRtvTargetStep = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_target_step, "field 'mRtvTargetStep'", RegularTextView.class);
        mainQuicklySettingDialog.mRtvTargetWeight = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.rtv_target_weight, "field 'mRtvTargetWeight'", RegularTextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.item_target, "field 'mItemTarget' and method 'onViewClicked'");
        mainQuicklySettingDialog.mItemTarget = (RelativeLayout) Utils.castView(viewFindRequiredView, R.id.item_target, "field 'mItemTarget'", RelativeLayout.class);
        this.view7f0a02b9 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.MainQuicklySettingDialog_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainQuicklySettingDialog.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_msg_notification, "field 'mItemMsgNotification' and method 'onViewClicked'");
        mainQuicklySettingDialog.mItemMsgNotification = (CustomItemLabelView) Utils.castView(viewFindRequiredView2, R.id.item_msg_notification, "field 'mItemMsgNotification'", CustomItemLabelView.class);
        this.view7f0a028b = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.MainQuicklySettingDialog_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainQuicklySettingDialog.onViewClicked(view2);
            }
        });
        mainQuicklySettingDialog.mItemCallRemind = (CustomItemLabelView) Utils.findRequiredViewAsType(view, R.id.item_call_remind, "field 'mItemCallRemind'", CustomItemLabelView.class);
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.item_amazon_alexa, "field 'mItemAmazonAlexa' and method 'onViewClicked'");
        mainQuicklySettingDialog.mItemAmazonAlexa = (CustomItemLabelView) Utils.castView(viewFindRequiredView3, R.id.item_amazon_alexa, "field 'mItemAmazonAlexa'", CustomItemLabelView.class);
        this.view7f0a024e = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.MainQuicklySettingDialog_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainQuicklySettingDialog.onViewClicked(view2);
            }
        });
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.item_preferences, "field 'mItemPreferences' and method 'onViewClicked'");
        mainQuicklySettingDialog.mItemPreferences = (CustomItemLabelView) Utils.castView(viewFindRequiredView4, R.id.item_preferences, "field 'mItemPreferences'", CustomItemLabelView.class);
        this.view7f0a0299 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.MainQuicklySettingDialog_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainQuicklySettingDialog.onViewClicked(view2);
            }
        });
        mainQuicklySettingDialog.mLayoutDialogRoot = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_dialog_root, "field 'mLayoutDialogRoot'", LinearLayout.class);
        View viewFindRequiredView5 = Utils.findRequiredView(view, R.id.tv_electronic_instruction, "field 'mTvElectronicInstruction' and method 'onViewClicked'");
        mainQuicklySettingDialog.mTvElectronicInstruction = (RegularTextView) Utils.castView(viewFindRequiredView5, R.id.tv_electronic_instruction, "field 'mTvElectronicInstruction'", RegularTextView.class);
        this.view7f0a084c = viewFindRequiredView5;
        viewFindRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.MainQuicklySettingDialog_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainQuicklySettingDialog.onViewClicked(view2);
            }
        });
        mainQuicklySettingDialog.mTvDeviceName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_device_name, "field 'mTvDeviceName'", TextView.class);
        View viewFindRequiredView6 = Utils.findRequiredView(view, R.id.tv_confirm, "method 'onViewClicked'");
        this.view7f0a080e = viewFindRequiredView6;
        viewFindRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.MainQuicklySettingDialog_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                mainQuicklySettingDialog.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MainQuicklySettingDialog mainQuicklySettingDialog = this.target;
        if (mainQuicklySettingDialog == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        mainQuicklySettingDialog.mRtvTargetStep = null;
        mainQuicklySettingDialog.mRtvTargetWeight = null;
        mainQuicklySettingDialog.mItemTarget = null;
        mainQuicklySettingDialog.mItemMsgNotification = null;
        mainQuicklySettingDialog.mItemCallRemind = null;
        mainQuicklySettingDialog.mItemAmazonAlexa = null;
        mainQuicklySettingDialog.mItemPreferences = null;
        mainQuicklySettingDialog.mLayoutDialogRoot = null;
        mainQuicklySettingDialog.mTvElectronicInstruction = null;
        mainQuicklySettingDialog.mTvDeviceName = null;
        this.view7f0a02b9.setOnClickListener(null);
        this.view7f0a02b9 = null;
        this.view7f0a028b.setOnClickListener(null);
        this.view7f0a028b = null;
        this.view7f0a024e.setOnClickListener(null);
        this.view7f0a024e = null;
        this.view7f0a0299.setOnClickListener(null);
        this.view7f0a0299 = null;
        this.view7f0a084c.setOnClickListener(null);
        this.view7f0a084c = null;
        this.view7f0a080e.setOnClickListener(null);
        this.view7f0a080e = null;
    }
}