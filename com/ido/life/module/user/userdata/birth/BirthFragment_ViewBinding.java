package com.ido.life.module.user.userdata.birth;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.watch.life.wheelview.view.WheelView;

/* JADX INFO: loaded from: classes3.dex */
public class BirthFragment_ViewBinding implements Unbinder {
    private BirthFragment target;
    private View view7f0a02f1;
    private View view7f0a0311;

    public BirthFragment_ViewBinding(final BirthFragment birthFragment, View view) {
        this.target = birthFragment;
        birthFragment.mIvTitleBirth = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_birth, "field 'mIvTitleBirth'", TextView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.iv_forward, "field 'mIvForward' and method 'toUpdateBirth'");
        birthFragment.mIvForward = (ImageButton) Utils.castView(viewFindRequiredView, R.id.iv_forward, "field 'mIvForward'", ImageButton.class);
        this.view7f0a0311 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.userdata.birth.BirthFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                birthFragment.toUpdateBirth(view2);
            }
        });
        birthFragment.mWheeYear = (WheelView) Utils.findRequiredViewAsType(view, R.id.wheel_year, "field 'mWheeYear'", WheelView.class);
        birthFragment.mWheelMonth = (WheelView) Utils.findRequiredViewAsType(view, R.id.wheel_month, "field 'mWheelMonth'", WheelView.class);
        birthFragment.mWheelDay = (WheelView) Utils.findRequiredViewAsType(view, R.id.wheel_day, "field 'mWheelDay'", WheelView.class);
        birthFragment.mHintTv = (TextView) Utils.findRequiredViewAsType(view, R.id.low_hint, "field 'mHintTv'", TextView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.iv_back_forward, "method 'backForward'");
        this.view7f0a02f1 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.userdata.birth.BirthFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                birthFragment.backForward(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        BirthFragment birthFragment = this.target;
        if (birthFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        birthFragment.mIvTitleBirth = null;
        birthFragment.mIvForward = null;
        birthFragment.mWheeYear = null;
        birthFragment.mWheelMonth = null;
        birthFragment.mWheelDay = null;
        birthFragment.mHintTv = null;
        this.view7f0a0311.setOnClickListener(null);
        this.view7f0a0311 = null;
        this.view7f0a02f1.setOnClickListener(null);
        this.view7f0a02f1 = null;
    }
}