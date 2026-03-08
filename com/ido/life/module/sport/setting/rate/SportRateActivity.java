package com.ido.life.module.sport.setting.rate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.dialog.NumberDialogFragment;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.TimeUtil;
import com.ido.common.widget.textview.MediumTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.ble.BleSdkWrapper;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.NormalToast;
import com.ido.life.customview.viewgroup.OptionView;
import com.ido.life.module.sport.setting.explain.RateExplainActivity;

/* JADX INFO: loaded from: classes2.dex */
public class SportRateActivity extends BaseActivity<SportRatePresenter> implements ISportRateView {
    public static final String EXTRA_RATE_VALUE = "extra_rate_value";
    private static final int MAX_RATE = 220;
    private static final int MIN_RATE = 100;
    public static final int REQUEST_CODE = 1003;
    public static final int RESULT_CODE = 1004;
    private static final String TAG = "SportRateActivity";

    @BindView(R.id.ll_bg_rate)
    LinearLayout mLlGbRate;

    @BindView(R.id.opt_sport_aerobic_endurance)
    OptionView mOptSportAerobicEndurance;

    @BindView(R.id.opt_sport_anaerobic_endurance)
    OptionView mOptSportAnaerobicEndurance;

    @BindView(R.id.opt_sport_burning_grease)
    OptionView mOptSportBurningGrease;

    @BindView(R.id.opt_sport_heart_rate)
    OptionView mOptSportHeartRate;

    @BindView(R.id.opt_sport_limit)
    OptionView mOptSportLimit;

    @BindView(R.id.opt_sport_warm_up)
    OptionView mOptSportWarmUp;

    @BindView(R.id.rv_rate_upper_limit_warning)
    RelativeLayout mRvRateUpperLimitWarning;

    @BindView(R.id.title_leftBtn)
    ImageButton mTitleLeftBtn;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @BindView(R.id.toggle)
    CustomToggleButton mToggle;

    @BindView(R.id.tv_rate_max)
    MediumTextView mTvRateMax;

    @BindView(R.id.tv_rate_upper_limit_warning)
    TextView mTvRateUpperLimitWarning;

    @BindView(R.id.tv_rate_upper_limit_warning_now)
    TextView mTvRateUpperLimitWarningNow;
    private int mChoose = 220;
    private int mMaxHeart = 220;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_sport_rate;
    }

    @OnClick({R.id.tv_rate_max})
    public void toSetRateMax(View view) {
    }

    public static void startActivityForResult(Activity activity) {
        activity.startActivityForResult(new Intent(activity, (Class<?>) SportRateActivity.class), 1003);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        if (!BleSdkWrapper.isConnected()) {
            this.mToggle.setTouchEnable(false);
            NormalToast.showToast(LanguageUtil.getLanguageText(R.string.device_pls_connect_device));
        } else {
            this.mToggle.setTouchEnable(true);
        }
        this.mToggle.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.sport.setting.rate.SportRateActivity.1
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public void onSwitched(View view, boolean z) {
                ((SportRatePresenter) SportRateActivity.this.mPresenter).openOrCloseRateLimit(z);
            }
        });
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "initData: " + TimeUtil.convTimeDetail(TimeUtil.now()));
        ((SportRatePresenter) this.mPresenter).getMaxHeart();
        ((SportRatePresenter) this.mPresenter).getHeartLimit();
        ((SportRatePresenter) this.mPresenter).getSupportFunction();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "initData2: " + TimeUtil.convTimeDetail(TimeUtil.now()));
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mTitleText.setText(LanguageUtil.getLanguageText(R.string.sport_setting_rate_title));
        this.mTvRateUpperLimitWarning.setText(LanguageUtil.getLanguageText(R.string.sport_setting_rate_explain_rate_up_limit_warning));
        this.mTvRateUpperLimitWarningNow.setText(LanguageUtil.getLanguageText(R.string.sport_setting_rate_explain_rate_up_limit_warning_now));
        this.mOptSportLimit.setStartText(LanguageUtil.getLanguageText(R.string.sport_setting_limitation));
        this.mOptSportAnaerobicEndurance.setStartText(LanguageUtil.getLanguageText(R.string.sport_setting_anaerobic_endurance));
        this.mOptSportAerobicEndurance.setStartText(LanguageUtil.getLanguageText(R.string.sport_setting_aerobic_endurance));
        this.mOptSportBurningGrease.setStartText(LanguageUtil.getLanguageText(R.string.sport_setting_fat_burning));
        this.mOptSportWarmUp.setStartText(LanguageUtil.getLanguageText(R.string.sport_setting_warm_up));
    }

    @OnClick({R.id.opt_sport_heart_rate})
    public void toSportHeart(View view) {
        if (this.mToggle.getSwitchStatus()) {
            NumberDialogFragment numberDialogFragmentNewInstance = NumberDialogFragment.newInstance(100, 220, this.mChoose, LanguageUtil.getLanguageText(R.string.public_heartbeat_unit));
            numberDialogFragmentNewInstance.show(getSupportFragmentManager());
            numberDialogFragmentNewInstance.setOnItemSelectedListener(new NumberDialogFragment.OnItemSelectedListener() { // from class: com.ido.life.module.sport.setting.rate.SportRateActivity.2
                @Override // com.ido.common.dialog.NumberDialogFragment.OnItemSelectedListener
                public void onItemSelected(int i) {
                    ((SportRatePresenter) SportRateActivity.this.mPresenter).chooseHeartLimit(i);
                }
            });
        }
    }

    @OnClick({R.id.tv_rate_max_title})
    public void toRateMax(View view) {
        RateExplainActivity.start(this);
    }

    @Override // com.ido.life.module.sport.setting.rate.ISportRateView
    public void setRateWarnIsOpen(boolean z) {
        this.mToggle.setSwitchStatus(z);
        setRateLayoutBg(z);
    }

    @Override // com.ido.life.module.sport.setting.rate.ISportRateView
    public void setRateUpperLimit(String str) {
        this.mOptSportHeartRate.setEndText(str);
    }

    @Override // com.ido.life.module.sport.setting.rate.ISportRateView
    public void setRateMax(String str) {
        this.mTvRateMax.setText(str + " " + getLanguageText(R.string.home_rate_unit));
    }

    @Override // com.ido.life.module.sport.setting.rate.ISportRateView
    public void setRateMax(int i) {
        this.mMaxHeart = i;
    }

    @Override // com.ido.life.module.sport.setting.rate.ISportRateView
    public void setRateLimit(String str) {
        this.mOptSportLimit.setEndText(str);
    }

    @Override // com.ido.life.module.sport.setting.rate.ISportRateView
    public void setRateAnaerobicEndurance(String str) {
        this.mOptSportAnaerobicEndurance.setEndText(str);
    }

    @Override // com.ido.life.module.sport.setting.rate.ISportRateView
    public void setRateAerobicEndurance(String str) {
        this.mOptSportAerobicEndurance.setEndText(str);
    }

    @Override // com.ido.life.module.sport.setting.rate.ISportRateView
    public void setRateBurningGrease(String str) {
        this.mOptSportBurningGrease.setEndText(str);
    }

    @Override // com.ido.life.module.sport.setting.rate.ISportRateView
    public void setRateWarmUp(String str) {
        this.mOptSportWarmUp.setEndText(str);
    }

    @Override // com.ido.life.module.sport.setting.rate.ISportRateView
    public void setRateLimitEnable(boolean z) {
        this.mOptSportHeartRate.setEnabled(z);
    }

    @Override // com.ido.life.module.sport.setting.rate.ISportRateView
    public void setRateLayoutBg(boolean z) {
        if (z) {
            this.mLlGbRate.setAlpha(1.0f);
        } else {
            this.mLlGbRate.setAlpha(0.3f);
        }
    }

    @Override // com.ido.life.module.sport.setting.rate.ISportRateView
    public void setHeartLimit(int i) {
        this.mChoose = i;
    }

    @Override // com.ido.life.module.sport.setting.rate.ISportRateView
    public void showMessage(String str) {
        showToast(str);
    }

    @Override // com.ido.life.module.sport.setting.rate.ISportRateView
    public void showRateUpper(boolean z) {
        this.mRvRateUpperLimitWarning.setVisibility(z ? 0 : 8);
    }

    @Override // com.ido.life.module.sport.setting.rate.ISportRateView
    public void showOptSportHeartRate(boolean z) {
        this.mOptSportHeartRate.setVisibility(z ? 0 : 8);
    }

    @OnClick({R.id.title_leftBtn})
    public void toBack(View view) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        String languageText = LanguageUtil.getLanguageText(R.string.sport_setting_rate_close);
        ((SportRatePresenter) this.mPresenter).setHeartLimit(this.mChoose);
        if (this.mToggle.getSwitchStatus()) {
            languageText = String.format(LanguageUtil.getLanguageText(R.string.sport_setting_rate_explain_rate_up_value), Integer.valueOf(this.mChoose));
        }
        bundle.putString(EXTRA_RATE_VALUE, languageText);
        intent.putExtras(bundle);
        setResult(1004, intent);
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        toBack(null);
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.mPresenter != 0) {
            ((SportRatePresenter) this.mPresenter).unregisterSettingCallBack();
        }
    }
}