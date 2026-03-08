package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class TestActivity_ViewBinding implements Unbinder {
    private TestActivity target;
    private View view7f0a0248;
    private View view7f0a0249;
    private View view7f0a092a;

    public TestActivity_ViewBinding(TestActivity testActivity) {
        this(testActivity, testActivity.getWindow().getDecorView());
    }

    public TestActivity_ViewBinding(final TestActivity testActivity, View view) {
        this.target = testActivity;
        testActivity.mLayoutAgpsUpgrade = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_agps_upgrade, "field 'mLayoutAgpsUpgrade'", LinearLayout.class);
        testActivity.mEtCmd = (EditText) Utils.findRequiredViewAsType(view, R.id.et_cmd, "field 'mEtCmd'", EditText.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_send, "field 'mTvSend' and method 'onViewClicked'");
        testActivity.mTvSend = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_send, "field 'mTvSend'", TextView.class);
        this.view7f0a092a = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.TestActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                testActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.item_agps_online, "method 'onViewClicked'");
        this.view7f0a0249 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.TestActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                testActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.item_agps_offline, "method 'onViewClicked'");
        this.view7f0a0248 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.device.activity.TestActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                testActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        TestActivity testActivity = this.target;
        if (testActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        testActivity.mLayoutAgpsUpgrade = null;
        testActivity.mEtCmd = null;
        testActivity.mTvSend = null;
        this.view7f0a092a.setOnClickListener(null);
        this.view7f0a092a = null;
        this.view7f0a0249.setOnClickListener(null);
        this.view7f0a0249 = null;
        this.view7f0a0248.setOnClickListener(null);
        this.view7f0a0248 = null;
    }
}