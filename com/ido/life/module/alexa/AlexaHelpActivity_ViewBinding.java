package com.ido.life.module.alexa;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaHelpActivity_ViewBinding implements Unbinder {
    private AlexaHelpActivity target;
    private View view7f0a0643;
    private View view7f0a0665;
    private View view7f0a0666;
    private View view7f0a066f;

    public AlexaHelpActivity_ViewBinding(AlexaHelpActivity alexaHelpActivity) {
        this(alexaHelpActivity, alexaHelpActivity.getWindow().getDecorView());
    }

    public AlexaHelpActivity_ViewBinding(final AlexaHelpActivity alexaHelpActivity, View view) {
        this.target = alexaHelpActivity;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.rtv_help_tips, "field 'mTvHelpTips' and method 'onViewClicked'");
        alexaHelpActivity.mTvHelpTips = (RegularTextView) Utils.castView(viewFindRequiredView, R.id.rtv_help_tips, "field 'mTvHelpTips'", RegularTextView.class);
        this.view7f0a0666 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.alexa.AlexaHelpActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alexaHelpActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.rtv_login, "field 'mTvLogin' and method 'onViewClicked'");
        alexaHelpActivity.mTvLogin = (RegularTextView) Utils.castView(viewFindRequiredView2, R.id.rtv_login, "field 'mTvLogin'", RegularTextView.class);
        this.view7f0a066f = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.alexa.AlexaHelpActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alexaHelpActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.rtv_close, "field 'mTvClose' and method 'onViewClicked'");
        alexaHelpActivity.mTvClose = (RegularTextView) Utils.castView(viewFindRequiredView3, R.id.rtv_close, "field 'mTvClose'", RegularTextView.class);
        this.view7f0a0643 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.alexa.AlexaHelpActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alexaHelpActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.rtv_help_dialog, "method 'onViewClicked'");
        this.view7f0a0665 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.alexa.AlexaHelpActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alexaHelpActivity.onViewClicked(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AlexaHelpActivity alexaHelpActivity = this.target;
        if (alexaHelpActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        alexaHelpActivity.mTvHelpTips = null;
        alexaHelpActivity.mTvLogin = null;
        alexaHelpActivity.mTvClose = null;
        this.view7f0a0666.setOnClickListener(null);
        this.view7f0a0666 = null;
        this.view7f0a066f.setOnClickListener(null);
        this.view7f0a066f = null;
        this.view7f0a0643.setOnClickListener(null);
        this.view7f0a0643 = null;
        this.view7f0a0665.setOnClickListener(null);
        this.view7f0a0665 = null;
    }
}