package com.ido.life.dialog;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class SportSetPowerDialogFragment_ViewBinding implements Unbinder {
    private SportSetPowerDialogFragment target;
    private View view7f0a01db;
    private View view7f0a08cb;

    public SportSetPowerDialogFragment_ViewBinding(final SportSetPowerDialogFragment sportSetPowerDialogFragment, View view) {
        this.target = sportSetPowerDialogFragment;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_next, "field 'mTvNext' and method 'doClickConfirm'");
        sportSetPowerDialogFragment.mTvNext = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_next, "field 'mTvNext'", TextView.class);
        this.view7f0a08cb = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.SportSetPowerDialogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportSetPowerDialogFragment.doClickConfirm(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.ib_close, "field 'mIbClose' and method 'doClickCancel'");
        sportSetPowerDialogFragment.mIbClose = (ImageButton) Utils.castView(viewFindRequiredView2, R.id.ib_close, "field 'mIbClose'", ImageButton.class);
        this.view7f0a01db = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.SportSetPowerDialogFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportSetPowerDialogFragment.doClickCancel(view2);
            }
        });
        sportSetPowerDialogFragment.mTvSettingRemind = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_setting_remind, "field 'mTvSettingRemind'", TextView.class);
        sportSetPowerDialogFragment.mIvSettingRemind = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_setting_remind, "field 'mIvSettingRemind'", ImageView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportSetPowerDialogFragment sportSetPowerDialogFragment = this.target;
        if (sportSetPowerDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportSetPowerDialogFragment.mTvNext = null;
        sportSetPowerDialogFragment.mIbClose = null;
        sportSetPowerDialogFragment.mTvSettingRemind = null;
        sportSetPowerDialogFragment.mIvSettingRemind = null;
        this.view7f0a08cb.setOnClickListener(null);
        this.view7f0a08cb = null;
        this.view7f0a01db.setOnClickListener(null);
        this.view7f0a01db = null;
    }
}