package com.ido.life.module.main;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.life.customview.NotifyRadioButton;

/* JADX INFO: loaded from: classes2.dex */
public class MainActivity_ViewBinding implements Unbinder {
    private MainActivity target;

    public MainActivity_ViewBinding(MainActivity mainActivity) {
        this(mainActivity, mainActivity.getWindow().getDecorView());
    }

    public MainActivity_ViewBinding(MainActivity mainActivity, View view) {
        this.target = mainActivity;
        mainActivity.mRadioGroup = (RadioGroup) Utils.findRequiredViewAsType(view, R.id.radio_group, "field 'mRadioGroup'", RadioGroup.class);
        mainActivity.mRbTabHome = (NotifyRadioButton) Utils.findRequiredViewAsType(view, R.id.rb_tab_home, "field 'mRbTabHome'", NotifyRadioButton.class);
        mainActivity.mRbTabSport = (NotifyRadioButton) Utils.findRequiredViewAsType(view, R.id.rb_tab_sport, "field 'mRbTabSport'", NotifyRadioButton.class);
        mainActivity.mRbTabDevice = (NotifyRadioButton) Utils.findRequiredViewAsType(view, R.id.rb_tab_device, "field 'mRbTabDevice'", NotifyRadioButton.class);
        mainActivity.mRbTabUser = (NotifyRadioButton) Utils.findRequiredViewAsType(view, R.id.rb_tab_user, "field 'mRbTabUser'", NotifyRadioButton.class);
        mainActivity.mBottomTabs = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.bottom_tabs, "field 'mBottomTabs'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        MainActivity mainActivity = this.target;
        if (mainActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        mainActivity.mRadioGroup = null;
        mainActivity.mRbTabHome = null;
        mainActivity.mRbTabSport = null;
        mainActivity.mRbTabDevice = null;
        mainActivity.mRbTabUser = null;
        mainActivity.mBottomTabs = null;
    }
}