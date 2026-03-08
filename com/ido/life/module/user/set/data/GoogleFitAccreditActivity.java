package com.ido.life.module.user.set.data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.database.model.PrivateSafeSetting;
import com.ido.life.module.user.set.data.googlefit.GoogleFitPresenter;
import com.ido.life.module.user.set.data.googlefit.ReOpenChangeListener;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class GoogleFitAccreditActivity extends BaseCoreActivity implements ReOpenChangeListener {
    private static final String TAG = "GoogleFitAccreditActivity";
    int STRAVA_ACCREDIT_CODE = 100;
    private GoogleFitPresenter mGoogleFitPresenter;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @BindView(R.id.tv_google_login)
    TextView mTvGoogleLogin;

    @BindView(R.id.tv_strava_tips)
    TextView mTvStravaTips;

    @BindView(R.id.tv_strava_title)
    TextView mTvStravaTitle;

    @BindView(R.id.tv_google_login_state)
    TextView tvGoogleLoginState;

    @BindView(R.id.tv_cancel_google_accredit)
    TextView tv_cancel_google_accredit;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_googlefit_accredit;
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) GoogleFitAccreditActivity.class));
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
        initView();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        boolean saveToGoogleFit;
        super.onResume();
        PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
        if (privateSafeSettingQueryPrivateSafeSetting != null) {
            saveToGoogleFit = privateSafeSettingQueryPrivateSafeSetting.getSaveToGoogleFit();
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "onResume()  mSafeSetting = " + privateSafeSettingQueryPrivateSafeSetting.toString());
        } else {
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "onResume()  mSafeSetting 为空");
            saveToGoogleFit = false;
        }
        if (saveToGoogleFit) {
            this.mTvGoogleLogin.setVisibility(8);
            this.tv_cancel_google_accredit.setVisibility(0);
            this.tvGoogleLoginState.setVisibility(0);
        } else {
            this.mTvGoogleLogin.setVisibility(0);
            this.tvGoogleLoginState.setVisibility(8);
        }
    }

    private void initView() {
        this.mTitleText.setText("Google Fit");
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        this.mGoogleFitPresenter = GoogleFitPresenter.getInstance();
        this.mGoogleFitPresenter.setReOpenListener(this);
    }

    @OnClick({R.id.title_leftBtn})
    public void toBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @OnClick({R.id.tv_cancel_google_accredit, R.id.tv_google_login})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id != R.id.tv_cancel_google_accredit) {
            if (id != R.id.tv_google_login) {
                return;
            }
            if (!NetworkUtil.isConnected(this)) {
                showToast(LanguageUtil.getLanguageText(R.string.logn_all_pleaseCheckNetWork_ios));
                return;
            } else {
                this.mGoogleFitPresenter.connectGoogle(this);
                return;
            }
        }
        PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
        if (privateSafeSettingQueryPrivateSafeSetting != null) {
            privateSafeSettingQueryPrivateSafeSetting.setSaveToGoogleFit(false);
            privateSafeSettingQueryPrivateSafeSetting.update();
        }
        this.mTvGoogleLogin.setVisibility(0);
        this.tv_cancel_google_accredit.setVisibility(8);
        this.tvGoogleLoginState.setVisibility(8);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        CommonLogUtil.d(TAG, "onActivityResult:  " + i + AppInfo.DELIM + i2);
        if (i2 != -1) {
            PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = GreenDaoUtil.queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
            if (privateSafeSettingQueryPrivateSafeSetting != null) {
                privateSafeSettingQueryPrivateSafeSetting.setSaveToGoogleFit(false);
                privateSafeSettingQueryPrivateSafeSetting.update();
                return;
            }
            return;
        }
        if (i == 3) {
            this.mGoogleFitPresenter.handleSignInResult(i, intent, this);
            return;
        }
        if (i == 10) {
            PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting2 = GreenDaoUtil.queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
            if (privateSafeSettingQueryPrivateSafeSetting2 != null) {
                privateSafeSettingQueryPrivateSafeSetting2.setSaveToGoogleFit(true);
                privateSafeSettingQueryPrivateSafeSetting2.update();
                CommonLogUtil.printAndSave(LogPathImpl.getInstance().getGoogleFitLogPath(), "onActivityResult入口：uploadData()");
                this.mGoogleFitPresenter.uploadData();
            }
            this.mTvGoogleLogin.setVisibility(8);
            this.tv_cancel_google_accredit.setVisibility(0);
            this.tvGoogleLoginState.setVisibility(0);
        }
    }

    @Override // com.ido.life.module.user.set.data.googlefit.ReOpenChangeListener
    public void reShowView() {
        this.mTvGoogleLogin.setVisibility(8);
        this.tv_cancel_google_accredit.setVisibility(0);
        this.tvGoogleLoginState.setVisibility(0);
    }
}