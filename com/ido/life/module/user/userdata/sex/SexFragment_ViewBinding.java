package com.ido.life.module.user.userdata.sex;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes3.dex */
public class SexFragment_ViewBinding implements Unbinder {
    private SexFragment target;
    private View view7f0a02f1;
    private View view7f0a0311;

    public SexFragment_ViewBinding(final SexFragment sexFragment, View view) {
        this.target = sexFragment;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.iv_forward, "field 'mIvForward' and method 'toUpdateSex'");
        sexFragment.mIvForward = (ImageButton) Utils.castView(viewFindRequiredView, R.id.iv_forward, "field 'mIvForward'", ImageButton.class);
        this.view7f0a0311 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.userdata.sex.SexFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sexFragment.toUpdateSex(view2);
            }
        });
        sexFragment.mCvMan = (CardView) Utils.findRequiredViewAsType(view, R.id.cv_man, "field 'mCvMan'", CardView.class);
        sexFragment.mCvWoman = (CardView) Utils.findRequiredViewAsType(view, R.id.cv_woman, "field 'mCvWoman'", CardView.class);
        sexFragment.mTvTitleGender = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title_gender, "field 'mTvTitleGender'", TextView.class);
        sexFragment.mTvMan = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_man, "field 'mTvMan'", TextView.class);
        sexFragment.mTvWoman = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_woman, "field 'mTvWoman'", TextView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.iv_back_forward, "method 'backForward'");
        this.view7f0a02f1 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.userdata.sex.SexFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sexFragment.backForward(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SexFragment sexFragment = this.target;
        if (sexFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sexFragment.mIvForward = null;
        sexFragment.mCvMan = null;
        sexFragment.mCvWoman = null;
        sexFragment.mTvTitleGender = null;
        sexFragment.mTvMan = null;
        sexFragment.mTvWoman = null;
        this.view7f0a0311.setOnClickListener(null);
        this.view7f0a0311 = null;
        this.view7f0a02f1.setOnClickListener(null);
        this.view7f0a02f1 = null;
    }
}