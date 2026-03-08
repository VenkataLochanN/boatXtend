package com.ido.life.module.user.userdata.height;

import android.view.View;
import android.widget.ImageButton;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.BaseRulerNewView;
import com.ido.life.module.user.view.MyTabLayout;

/* JADX INFO: loaded from: classes3.dex */
public class HeightFragment_ViewBinding implements Unbinder {
    private HeightFragment target;
    private View view7f0a02f1;
    private View view7f0a0311;

    public HeightFragment_ViewBinding(final HeightFragment heightFragment, View view) {
        this.target = heightFragment;
        heightFragment.mTabUserHeight = (MyTabLayout) Utils.findRequiredViewAsType(view, R.id.tab_user_height, "field 'mTabUserHeight'", MyTabLayout.class);
        heightFragment.personInfoHeight = (BaseRulerNewView) Utils.findRequiredViewAsType(view, R.id.person_info_height, "field 'personInfoHeight'", BaseRulerNewView.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.iv_forward, "field 'mIvForward' and method 'checkHeight'");
        heightFragment.mIvForward = (ImageButton) Utils.castView(viewFindRequiredView, R.id.iv_forward, "field 'mIvForward'", ImageButton.class);
        this.view7f0a0311 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.userdata.height.HeightFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                heightFragment.checkHeight(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.iv_back_forward, "method 'backForward'");
        this.view7f0a02f1 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.user.userdata.height.HeightFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                heightFragment.backForward(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        HeightFragment heightFragment = this.target;
        if (heightFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        heightFragment.mTabUserHeight = null;
        heightFragment.personInfoHeight = null;
        heightFragment.mIvForward = null;
        this.view7f0a0311.setOnClickListener(null);
        this.view7f0a0311 = null;
        this.view7f0a02f1.setOnClickListener(null);
        this.view7f0a02f1 = null;
    }
}