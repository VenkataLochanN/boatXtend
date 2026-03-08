package com.ido.life.module.guide;

import android.view.View;
import androidx.fragment.app.FragmentManager;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.dialog.NewSplashConfirmDialogFragment;
import com.ido.life.module.guide.WelcomeGuideContract;
import com.ido.life.util.SPUtils;

/* JADX INFO: loaded from: classes2.dex */
public class WelcomeGuidePresenter implements WelcomeGuideContract.Presenter {
    private static final String TAG = "WelcomeGuidePresenter";
    private WelcomeGuideContract.View mView;

    @Override // com.ido.common.base.BasePresenter
    public void release() {
    }

    @Override // com.ido.common.base.BasePresenter
    public void start() {
    }

    public WelcomeGuidePresenter(WelcomeGuideContract.View view) {
        this.mView = view;
    }

    @Override // com.ido.life.module.guide.WelcomeGuideContract.Presenter
    public void toShowPrivicy(FragmentManager fragmentManager) {
        final NewSplashConfirmDialogFragment newSplashConfirmDialogFragmentNewInstance = NewSplashConfirmDialogFragment.newInstance(LanguageUtil.getLanguageText(R.string.logn_welcome_APPName_ios), LanguageUtil.getLanguageText(R.string.register_agree_agreement_privacy), LanguageUtil.getLanguageText(R.string.login_agree_continue), LanguageUtil.getLanguageText(R.string.login_not_agree), true);
        newSplashConfirmDialogFragmentNewInstance.show(fragmentManager);
        newSplashConfirmDialogFragmentNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.guide.WelcomeGuidePresenter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                newSplashConfirmDialogFragmentNewInstance.dismissAllowingStateLoss();
                WelcomeGuidePresenter.this.mView.quitApp();
            }
        });
        newSplashConfirmDialogFragmentNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.guide.WelcomeGuidePresenter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (newSplashConfirmDialogFragmentNewInstance.getCheckBoxState()) {
                    newSplashConfirmDialogFragmentNewInstance.dismissAllowingStateLoss();
                    SPUtils.put(Constants.FIRST_START_APP, false);
                    SPUtils.put(Constants.AGREE_POLICY_TIME, Long.valueOf(System.currentTimeMillis()));
                    WelcomeGuidePresenter.this.mView.toPreLoginAndRegister();
                    return;
                }
                NormalToast.showToastAtOneThird(IdoApp.getAppContext(), LanguageUtil.getLanguageText(R.string.register_agree_protocol_privicy));
            }
        });
    }
}