package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class DialMarketActivity_ViewBinding implements Unbinder {
    private DialMarketActivity target;
    private View view7f0a02fd;
    private View view7f0a02fe;
    private View view7f0a02ff;
    private View view7f0a0554;

    public DialMarketActivity_ViewBinding(DialMarketActivity dialMarketActivity) {
        this(dialMarketActivity, dialMarketActivity.getWindow().getDecorView());
    }

    public DialMarketActivity_ViewBinding(final DialMarketActivity dialMarketActivity, View view) {
        this.target = dialMarketActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.iv_dial_1, "field 'mIvDial1' and method 'onViewClicked'");
        dialMarketActivity.mIvDial1 = (AppCompatImageView) Utils.castView(viewFindRequiredView, R.id.iv_dial_1, "field 'mIvDial1'", AppCompatImageView.class);
        this.view7f0a02fd = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DialMarketActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dialMarketActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.iv_dial_2, "field 'mIvDial2' and method 'onViewClicked'");
        dialMarketActivity.mIvDial2 = (AppCompatImageView) Utils.castView(viewFindRequiredView2, R.id.iv_dial_2, "field 'mIvDial2'", AppCompatImageView.class);
        this.view7f0a02fe = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DialMarketActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dialMarketActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.iv_dial_3, "field 'mIvDial3' and method 'onViewClicked'");
        dialMarketActivity.mIvDial3 = (AppCompatImageView) Utils.castView(viewFindRequiredView3, R.id.iv_dial_3, "field 'mIvDial3'", AppCompatImageView.class);
        this.view7f0a02ff = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DialMarketActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dialMarketActivity.onViewClicked(view2);
            }
        });
        dialMarketActivity.mIvRight = (AppCompatImageView) Utils.findRequiredViewAsType(view, R.id.iv_right_icon, "field 'mIvRight'", AppCompatImageView.class);
        dialMarketActivity.mNewTv = (TextView) Utils.findRequiredViewAsType(view, R.id.new_tv, "field 'mNewTv'", TextView.class);
        dialMarketActivity.mLayoutDial = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_dial, "field 'mLayoutDial'", LinearLayout.class);
        dialMarketActivity.device_intall_count_tv = (TextView) Utils.findRequiredViewAsType(view, R.id.device_intall_count_tv, "field 'device_intall_count_tv'", TextView.class);
        dialMarketActivity.tvTitleDial = (TextView) Utils.findRequiredViewAsType(view, R.id.tvTitleDial, "field 'tvTitleDial'", TextView.class);
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.my_dial_rl, "method 'onViewClicked'");
        this.view7f0a0554 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.DialMarketActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                dialMarketActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        DialMarketActivity dialMarketActivity = this.target;
        if (dialMarketActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        dialMarketActivity.mIvDial1 = null;
        dialMarketActivity.mIvDial2 = null;
        dialMarketActivity.mIvDial3 = null;
        dialMarketActivity.mIvRight = null;
        dialMarketActivity.mNewTv = null;
        dialMarketActivity.mLayoutDial = null;
        dialMarketActivity.device_intall_count_tv = null;
        dialMarketActivity.tvTitleDial = null;
        this.view7f0a02fd.setOnClickListener(null);
        this.view7f0a02fd = null;
        this.view7f0a02fe.setOnClickListener(null);
        this.view7f0a02fe = null;
        this.view7f0a02ff.setOnClickListener(null);
        this.view7f0a02ff = null;
        this.view7f0a0554.setOnClickListener(null);
        this.view7f0a0554 = null;
    }
}