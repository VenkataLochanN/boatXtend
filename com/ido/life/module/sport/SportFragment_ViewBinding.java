package com.ido.life.module.sport;

import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class SportFragment_ViewBinding implements Unbinder {
    private SportFragment target;
    private View view7f0a020f;
    private View view7f0a02fb;

    public SportFragment_ViewBinding(final SportFragment sportFragment, View view) {
        this.target = sportFragment;
        sportFragment.llFgSportMain = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_fg_sport_main, "field 'llFgSportMain'", LinearLayout.class);
        sportFragment.rlSportTop = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.rl_sport_top, "field 'rlSportTop'", RelativeLayout.class);
        sportFragment.mHorizontalScrollView = (HorizontalScrollView) Utils.findRequiredViewAsType(view, R.id.hsv_sport, "field 'mHorizontalScrollView'", HorizontalScrollView.class);
        sportFragment.mSportRgTab = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.sport_rg_tab, "field 'mSportRgTab'", RadioGroup.class);
        sportFragment.mSportViewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.sport_viewpager, "field 'mSportViewPager'", ViewPager.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.iv_device_status, "field 'mIvDeviceStatus' and method 'getDeviceStatus'");
        sportFragment.mIvDeviceStatus = (ImageView) Utils.castView(viewFindRequiredView, R.id.iv_device_status, "field 'mIvDeviceStatus'", ImageView.class);
        this.view7f0a02fb = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.SportFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportFragment.getDeviceStatus(view2);
            }
        });
        sportFragment.mTvSport = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_sport, "field 'mTvSport'", TextView.class);
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.img_more, "method 'toMore'");
        this.view7f0a020f = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.sport.SportFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                sportFragment.toMore(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        SportFragment sportFragment = this.target;
        if (sportFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        sportFragment.llFgSportMain = null;
        sportFragment.rlSportTop = null;
        sportFragment.mHorizontalScrollView = null;
        sportFragment.mSportRgTab = null;
        sportFragment.mSportViewPager = null;
        sportFragment.mIvDeviceStatus = null;
        sportFragment.mTvSport = null;
        this.view7f0a02fb.setOnClickListener(null);
        this.view7f0a02fb = null;
        this.view7f0a020f.setOnClickListener(null);
        this.view7f0a020f = null;
    }
}