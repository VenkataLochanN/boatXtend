package com.ido.life.autotest;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class AutoTestActivity_ViewBinding implements Unbinder {
    private AutoTestActivity target;
    private View view7f0a007f;
    private View view7f0a0776;

    public AutoTestActivity_ViewBinding(AutoTestActivity autoTestActivity) {
        this(autoTestActivity, autoTestActivity.getWindow().getDecorView());
    }

    public AutoTestActivity_ViewBinding(final AutoTestActivity autoTestActivity, View view) {
        this.target = autoTestActivity;
        autoTestActivity.mTitleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title_text, "field 'mTitleText'", TextView.class);
        autoTestActivity.inputCmdEt = (EditText) Utils.findRequiredViewAsType(view, R.id.et_auto_test, "field 'inputCmdEt'", EditText.class);
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.btn_auto_test, "field 'sendButton' and method 'sendCmdToDevice'");
        autoTestActivity.sendButton = (Button) Utils.castView(viewFindRequiredView, R.id.btn_auto_test, "field 'sendButton'", Button.class);
        this.view7f0a007f = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.autotest.AutoTestActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                autoTestActivity.sendCmdToDevice();
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.title_leftBtn, "method 'toBack'");
        this.view7f0a0776 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.autotest.AutoTestActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                autoTestActivity.toBack(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AutoTestActivity autoTestActivity = this.target;
        if (autoTestActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        autoTestActivity.mTitleText = null;
        autoTestActivity.inputCmdEt = null;
        autoTestActivity.sendButton = null;
        this.view7f0a007f.setOnClickListener(null);
        this.view7f0a007f = null;
        this.view7f0a0776.setOnClickListener(null);
        this.view7f0a0776 = null;
    }
}