package com.ido.life.module.alexa;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.viewgroup.CommLoadingView;

/* JADX INFO: loaded from: classes2.dex */
public class AlexaLoginActivity_ViewBinding implements Unbinder {
    private AlexaLoginActivity target;
    private View view7f0a0067;
    private View view7f0a0081;
    private View view7f0a0085;
    private View view7f0a019c;
    private View view7f0a04b6;
    private View view7f0a059b;
    private View view7f0a0646;
    private View view7f0a0654;
    private View view7f0a0670;
    private View view7f0a0671;
    private View view7f0a0724;

    public AlexaLoginActivity_ViewBinding(AlexaLoginActivity alexaLoginActivity) {
        this(alexaLoginActivity, alexaLoginActivity.getWindow().getDecorView());
    }

    public AlexaLoginActivity_ViewBinding(final AlexaLoginActivity alexaLoginActivity, View view) {
        this.target = alexaLoginActivity;
        alexaLoginActivity.testLayout = Utils.findRequiredView(view, R.id.testLayout, "field 'testLayout'");
        alexaLoginActivity.loadLayout = Utils.findRequiredView(view, R.id.loadLayout, "field 'loadLayout'");
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.rtv_login_with_amazon, "field 'mRtvLoginWithAmazon' and method 'onViewClicked'");
        alexaLoginActivity.mRtvLoginWithAmazon = (RegularTextView) Utils.castView(viewFindRequiredView, R.id.rtv_login_with_amazon, "field 'mRtvLoginWithAmazon'", RegularTextView.class);
        this.view7f0a0670 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.alexa.AlexaLoginActivity_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alexaLoginActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.rtv_logout_with_amazon, "field 'mRtvLogoutWithAmazon' and method 'onViewClicked'");
        alexaLoginActivity.mRtvLogoutWithAmazon = (RegularTextView) Utils.castView(viewFindRequiredView2, R.id.rtv_logout_with_amazon, "field 'mRtvLogoutWithAmazon'", RegularTextView.class);
        this.view7f0a0671 = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.alexa.AlexaLoginActivity_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alexaLoginActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView3 = Utils.findRequiredView(view, R.id.rtv_continue, "field 'mRtvContiune' and method 'onViewClicked'");
        alexaLoginActivity.mRtvContiune = (RegularTextView) Utils.castView(viewFindRequiredView3, R.id.rtv_continue, "field 'mRtvContiune'", RegularTextView.class);
        this.view7f0a0646 = viewFindRequiredView3;
        viewFindRequiredView3.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.alexa.AlexaLoginActivity_ViewBinding.3
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alexaLoginActivity.onViewClicked(view2);
            }
        });
        alexaLoginActivity.mRtvRecord = (Button) Utils.findRequiredViewAsType(view, R.id.rtv_record, "field 'mRtvRecord'", Button.class);
        alexaLoginActivity.mTvContent = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_content, "field 'mTvContent'", RegularTextView.class);
        alexaLoginActivity.mTvLostPackage = (RegularTextView) Utils.findRequiredViewAsType(view, R.id.tv_lostPackage, "field 'mTvLostPackage'", RegularTextView.class);
        alexaLoginActivity.mCommLoadingView = (CommLoadingView) Utils.findRequiredViewAsType(view, R.id.comm_loading_view, "field 'mCommLoadingView'", CommLoadingView.class);
        alexaLoginActivity.languageLayout = Utils.findRequiredView(view, R.id.languageLayout, "field 'languageLayout'");
        alexaLoginActivity.spinner = (SpinnerTextView) Utils.findRequiredViewAsType(view, R.id.languageSpinner, "field 'spinner'", SpinnerTextView.class);
        alexaLoginActivity.arrowImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.arrowImg, "field 'arrowImg'", ImageView.class);
        alexaLoginActivity.mediaLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.mediaLayout, "field 'mediaLayout'", LinearLayout.class);
        alexaLoginActivity.alexaLanguageTv = (TextView) Utils.findRequiredViewAsType(view, R.id.alexaLanguageTv, "field 'alexaLanguageTv'", TextView.class);
        View viewFindRequiredView4 = Utils.findRequiredView(view, R.id.loginedLayout, "field 'loginedLayout' and method 'onViewClicked'");
        alexaLoginActivity.loginedLayout = viewFindRequiredView4;
        this.view7f0a04b6 = viewFindRequiredView4;
        viewFindRequiredView4.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.alexa.AlexaLoginActivity_ViewBinding.4
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alexaLoginActivity.onViewClicked(view2);
            }
        });
        alexaLoginActivity.alexa_guide_layout = Utils.findRequiredView(view, R.id.alexa_guide_layout, "field 'alexa_guide_layout'");
        alexaLoginActivity.viewPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.alexa_guide_viewpager, "field 'viewPager'", ViewPager.class);
        alexaLoginActivity.dotsLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.dots, "field 'dotsLayout'", LinearLayout.class);
        alexaLoginActivity.alexaSetTipTv = (TextView) Utils.findRequiredViewAsType(view, R.id.alexaSetTipTv, "field 'alexaSetTipTv'", TextView.class);
        alexaLoginActivity.alexaAppTipTv = (TextView) Utils.findRequiredViewAsType(view, R.id.alexaAppTipTv, "field 'alexaAppTipTv'", TextView.class);
        View viewFindRequiredView5 = Utils.findRequiredView(view, R.id.rtv_endpoint, "field 'mEndpointTv' and method 'stopVoice'");
        alexaLoginActivity.mEndpointTv = (Button) Utils.castView(viewFindRequiredView5, R.id.rtv_endpoint, "field 'mEndpointTv'", Button.class);
        this.view7f0a0654 = viewFindRequiredView5;
        viewFindRequiredView5.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.alexa.AlexaLoginActivity_ViewBinding.5
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) throws Throwable {
                alexaLoginActivity.stopVoice(view2);
            }
        });
        View viewFindRequiredView6 = Utils.findRequiredView(view, R.id.btn_lostpackagerate, "field 'mBtnOpenLostPackage' and method 'stopVoice'");
        alexaLoginActivity.mBtnOpenLostPackage = (Button) Utils.castView(viewFindRequiredView6, R.id.btn_lostpackagerate, "field 'mBtnOpenLostPackage'", Button.class);
        this.view7f0a0085 = viewFindRequiredView6;
        viewFindRequiredView6.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.alexa.AlexaLoginActivity_ViewBinding.6
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) throws Throwable {
                alexaLoginActivity.stopVoice(view2);
            }
        });
        alexaLoginActivity.mSmartHomeTipTv = (TextView) Utils.findRequiredViewAsType(view, R.id.alexaSmartHomeTipTv, "field 'mSmartHomeTipTv'", TextView.class);
        alexaLoginActivity.alexaProductIdEt = (EditText) Utils.findRequiredViewAsType(view, R.id.alexaProductIdEt, "field 'alexaProductIdEt'", EditText.class);
        View viewFindRequiredView7 = Utils.findRequiredView(view, R.id.audioTipLayout, "method 'onViewClicked'");
        this.view7f0a0067 = viewFindRequiredView7;
        viewFindRequiredView7.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.alexa.AlexaLoginActivity_ViewBinding.7
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                alexaLoginActivity.onViewClicked(view2);
            }
        });
        View viewFindRequiredView8 = Utils.findRequiredView(view, R.id.playVoice, "method 'stopVoice'");
        this.view7f0a059b = viewFindRequiredView8;
        viewFindRequiredView8.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.alexa.AlexaLoginActivity_ViewBinding.8
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) throws Throwable {
                alexaLoginActivity.stopVoice(view2);
            }
        });
        View viewFindRequiredView9 = Utils.findRequiredView(view, R.id.stopVoice, "method 'stopVoice'");
        this.view7f0a0724 = viewFindRequiredView9;
        viewFindRequiredView9.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.alexa.AlexaLoginActivity_ViewBinding.9
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) throws Throwable {
                alexaLoginActivity.stopVoice(view2);
            }
        });
        View viewFindRequiredView10 = Utils.findRequiredView(view, R.id.exportVoice, "method 'stopVoice'");
        this.view7f0a019c = viewFindRequiredView10;
        viewFindRequiredView10.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.alexa.AlexaLoginActivity_ViewBinding.10
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) throws Throwable {
                alexaLoginActivity.stopVoice(view2);
            }
        });
        View viewFindRequiredView11 = Utils.findRequiredView(view, R.id.btn_clear, "method 'stopVoice'");
        this.view7f0a0081 = viewFindRequiredView11;
        viewFindRequiredView11.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.module.alexa.AlexaLoginActivity_ViewBinding.11
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) throws Throwable {
                alexaLoginActivity.stopVoice(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        AlexaLoginActivity alexaLoginActivity = this.target;
        if (alexaLoginActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        alexaLoginActivity.testLayout = null;
        alexaLoginActivity.loadLayout = null;
        alexaLoginActivity.mRtvLoginWithAmazon = null;
        alexaLoginActivity.mRtvLogoutWithAmazon = null;
        alexaLoginActivity.mRtvContiune = null;
        alexaLoginActivity.mRtvRecord = null;
        alexaLoginActivity.mTvContent = null;
        alexaLoginActivity.mTvLostPackage = null;
        alexaLoginActivity.mCommLoadingView = null;
        alexaLoginActivity.languageLayout = null;
        alexaLoginActivity.spinner = null;
        alexaLoginActivity.arrowImg = null;
        alexaLoginActivity.mediaLayout = null;
        alexaLoginActivity.alexaLanguageTv = null;
        alexaLoginActivity.loginedLayout = null;
        alexaLoginActivity.alexa_guide_layout = null;
        alexaLoginActivity.viewPager = null;
        alexaLoginActivity.dotsLayout = null;
        alexaLoginActivity.alexaSetTipTv = null;
        alexaLoginActivity.alexaAppTipTv = null;
        alexaLoginActivity.mEndpointTv = null;
        alexaLoginActivity.mBtnOpenLostPackage = null;
        alexaLoginActivity.mSmartHomeTipTv = null;
        alexaLoginActivity.alexaProductIdEt = null;
        this.view7f0a0670.setOnClickListener(null);
        this.view7f0a0670 = null;
        this.view7f0a0671.setOnClickListener(null);
        this.view7f0a0671 = null;
        this.view7f0a0646.setOnClickListener(null);
        this.view7f0a0646 = null;
        this.view7f0a04b6.setOnClickListener(null);
        this.view7f0a04b6 = null;
        this.view7f0a0654.setOnClickListener(null);
        this.view7f0a0654 = null;
        this.view7f0a0085.setOnClickListener(null);
        this.view7f0a0085 = null;
        this.view7f0a0067.setOnClickListener(null);
        this.view7f0a0067 = null;
        this.view7f0a059b.setOnClickListener(null);
        this.view7f0a059b = null;
        this.view7f0a0724.setOnClickListener(null);
        this.view7f0a0724 = null;
        this.view7f0a019c.setOnClickListener(null);
        this.view7f0a019c = null;
        this.view7f0a0081.setOnClickListener(null);
        this.view7f0a0081 = null;
    }
}