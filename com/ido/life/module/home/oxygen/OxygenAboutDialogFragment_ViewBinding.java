package com.ido.life.module.home.oxygen;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class OxygenAboutDialogFragment_ViewBinding implements Unbinder {
    private OxygenAboutDialogFragment target;
    private View view7f0a01dd;

    public OxygenAboutDialogFragment_ViewBinding(final OxygenAboutDialogFragment oxygenAboutDialogFragment, View view) {
        this.target = oxygenAboutDialogFragment;
        oxygenAboutDialogFragment.mTvage = (TextView) Utils.findRequiredViewAsType(view, R.id.rtv_oxygen_age, "field 'mTvage'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.ib_oxygen_close, "field 'mIbClose' and method 'doClickCancel'");
        oxygenAboutDialogFragment.mIbClose = (ImageButton) Utils.castView(viewFindRequiredView, R.id.ib_oxygen_close, "field 'mIbClose'", ImageButton.class);
        this.view7f0a01dd = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.home.oxygen.OxygenAboutDialogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                oxygenAboutDialogFragment.doClickCancel(view2);
            }
        });
        oxygenAboutDialogFragment.llOxygenAbout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_Oxygen_progress_about, "field 'llOxygenAbout'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        OxygenAboutDialogFragment oxygenAboutDialogFragment = this.target;
        if (oxygenAboutDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        oxygenAboutDialogFragment.mTvage = null;
        oxygenAboutDialogFragment.mIbClose = null;
        oxygenAboutDialogFragment.llOxygenAbout = null;
        this.view7f0a01dd.setOnClickListener(null);
        this.view7f0a01dd = null;
    }
}