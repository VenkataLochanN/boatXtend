package com.ido.life.module.guide;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class WelcomeGuideActivity_ViewBinding implements Unbinder {
    private WelcomeGuideActivity target;
    private View view7f0a008a;
    private View view7f0a0321;
    private View view7f0a0345;
    private View view7f0a034f;

    public WelcomeGuideActivity_ViewBinding(WelcomeGuideActivity welcomeGuideActivity) {
        this(welcomeGuideActivity, welcomeGuideActivity.getWindow().getDecorView());
    }

    public WelcomeGuideActivity_ViewBinding(final WelcomeGuideActivity welcomeGuideActivity, View view) {
        this.target = welcomeGuideActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.iv_one_dot, "field 'mIvOneDot' and method 'dotClick'");
        welcomeGuideActivity.mIvOneDot = (ImageView) Utils.castView(viewFindRequiredView, R.id.iv_one_dot, "field 'mIvOneDot'", ImageView.class);
        this.view7f0a0321 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.guide.WelcomeGuideActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                welcomeGuideActivity.dotClick(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.iv_two_dot, "field 'mIvTwoDot' and method 'dotClick'");
        welcomeGuideActivity.mIvTwoDot = (ImageView) Utils.castView(viewFindRequiredView2, R.id.iv_two_dot, "field 'mIvTwoDot'", ImageView.class);
        this.view7f0a034f = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.guide.WelcomeGuideActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                welcomeGuideActivity.dotClick(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.iv_three_dot, "field 'mIvThreeDot' and method 'dotClick'");
        welcomeGuideActivity.mIvThreeDot = (ImageView) Utils.castView(viewFindRequiredView3, R.id.iv_three_dot, "field 'mIvThreeDot'", ImageView.class);
        this.view7f0a0345 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.guide.WelcomeGuideActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                welcomeGuideActivity.dotClick(view2);
            }
        });
        welcomeGuideActivity.mViewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.guide_viewpager, "field 'mViewPager'", ViewPager.class);
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.btn_start, "field 'mBtnStart' and method 'doClickStart'");
        welcomeGuideActivity.mBtnStart = (Button) Utils.castView(viewFindRequiredView4, R.id.btn_start, "field 'mBtnStart'", Button.class);
        this.view7f0a008a = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.guide.WelcomeGuideActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                welcomeGuideActivity.doClickStart(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        WelcomeGuideActivity welcomeGuideActivity = this.target;
        if (welcomeGuideActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        welcomeGuideActivity.mIvOneDot = null;
        welcomeGuideActivity.mIvTwoDot = null;
        welcomeGuideActivity.mIvThreeDot = null;
        welcomeGuideActivity.mViewPager = null;
        welcomeGuideActivity.mBtnStart = null;
        this.view7f0a0321.setOnClickListener(null);
        this.view7f0a0321 = null;
        this.view7f0a034f.setOnClickListener(null);
        this.view7f0a034f = null;
        this.view7f0a0345.setOnClickListener(null);
        this.view7f0a0345 = null;
        this.view7f0a008a.setOnClickListener(null);
        this.view7f0a008a = null;
    }
}