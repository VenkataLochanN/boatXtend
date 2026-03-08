package com.ido.life.module.sport.setting;

import android.content.Context;
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
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.RomUtils;
import com.ido.life.customview.viewgroup.OptionView;
import com.ido.life.dialog.SportSetPowerDialogFragment;
import com.ido.life.dialog.SportTargetDialogFragment;
import com.ido.life.module.sport.power.SportSettingPowerActivity;
import com.ido.life.module.sport.setting.SportSettingContract;
import com.ido.life.module.sport.setting.rate.SportRateActivity;
import com.ido.life.module.sport.setting.voice.SportVoiceActivity;

/* JADX INFO: loaded from: classes2.dex */
public class SportSettingActivity extends BaseCoreActivity implements SportSettingContract.View {
    private static final String EXTRA_SPORT_TYPE = "sport_type";
    private static final String TAG = "SportSettingActivity";
    private boolean mIsRide;

    @BindView(R.id.opt_sport_rate)
    OptionView mOptSportRate;

    @BindView(R.id.opt_sport_setting)
    OptionView mOptSportSetting;

    @BindView(R.id.opt_sport_target)
    OptionView mOptSportTarget;

    @BindView(R.id.opt_sport_voice)
    OptionView mOptSportVoice;
    private SportSettingContract.Presenter mPresenter;

    @BindView(R.id.title_text)
    TextView mTitleText;
    private int mType;

    @BindView(R.id.view_sport_setting_line)
    View mViewSportLine;
    private String[] tabsRide = {LanguageUtil.getLanguageText(R.string.sport_setting_no_target), LanguageUtil.getLanguageText(R.string.sport_setting_distance), LanguageUtil.getLanguageText(R.string.sport_setting_category), LanguageUtil.getLanguageText(R.string.sport_setting_time)};
    private String[] tabs = {LanguageUtil.getLanguageText(R.string.sport_setting_no_target), LanguageUtil.getLanguageText(R.string.sport_setting_distance), LanguageUtil.getLanguageText(R.string.sport_setting_category), LanguageUtil.getLanguageText(R.string.sport_setting_step), LanguageUtil.getLanguageText(R.string.sport_setting_time)};
    private String[] mTabs = new String[0];

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_sport_setting;
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(SportSettingContract.Presenter presenter) {
    }

    public static void start(Context context, int i) {
        Intent intent = new Intent(context, (Class<?>) SportSettingActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("sport_type", i);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mPresenter = new SportSettingPresenter(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mType = extras.getInt("sport_type");
            CommonLogUtil.d(TAG, "initData: " + this.mType);
            if (this.mType == 50) {
                this.mTabs = new String[4];
                System.arraycopy(this.tabsRide, 0, this.mTabs, 0, 4);
                this.mIsRide = true;
                this.mPresenter.setIsCycle(true);
            } else {
                this.mTabs = new String[5];
                System.arraycopy(this.tabs, 0, this.mTabs, 0, 5);
                this.mIsRide = false;
                this.mPresenter.setIsCycle(false);
            }
        }
        this.mPresenter.getHistoryData();
    }

    @OnClick({R.id.opt_sport_target})
    public void setSportTarget(View view) {
        SportTargetDialogFragment sportTargetDialogFragmentNewInstance = SportTargetDialogFragment.newInstance(this.mTabs, this.mIsRide, this.mOptSportTarget.getEndText());
        sportTargetDialogFragmentNewInstance.show(getSupportFragmentManager());
        sportTargetDialogFragmentNewInstance.setOnDateSelectedListener(new SportTargetDialogFragment.OnDateSelectedListener() { // from class: com.ido.life.module.sport.setting.SportSettingActivity.1
            @Override // com.ido.life.dialog.SportTargetDialogFragment.OnDateSelectedListener
            public void onDateSelected(int i, int i2) {
                SportSettingActivity.this.mPresenter.setTarget(i, i2);
            }
        });
    }

    @OnClick({R.id.opt_sport_voice})
    public void setSportVoice(View view) {
        SportVoiceActivity.startActivityForResult(this, this.mOptSportVoice.getEndText());
    }

    @OnClick({R.id.opt_sport_rate})
    public void setSportRate(View view) {
        SportRateActivity.startActivityForResult(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mTitleText.setText(LanguageUtil.getLanguageText(R.string.sport_setting_title));
        this.mOptSportTarget.setStartText(LanguageUtil.getLanguageText(R.string.sport_setting_current_target));
        this.mOptSportVoice.setStartText(LanguageUtil.getLanguageText(R.string.sport_setting_voice_title));
        this.mOptSportRate.setStartText(LanguageUtil.getLanguageText(R.string.sport_setting_rate_title));
        if (RomUtils.isHuawei() || RomUtils.isXiaomi() || RomUtils.isVivo() || RomUtils.isOppo()) {
            this.mOptSportSetting.setVisibility(0);
            this.mViewSportLine.setVisibility(0);
        } else {
            this.mOptSportSetting.setVisibility(8);
            this.mViewSportLine.setVisibility(8);
        }
    }

    @OnClick({R.id.title_leftBtn})
    public void toBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.sport.setting.SportSettingContract.View
    public void setSportTarget(String str) {
        this.mOptSportTarget.setEndText(str);
    }

    @Override // com.ido.life.module.sport.setting.SportSettingContract.View
    public void setSportDistance(String str) {
        this.mOptSportVoice.setEndText(str);
    }

    @Override // com.ido.life.module.sport.setting.SportSettingContract.View
    public void setRateRange(String str) {
        this.mOptSportRate.setEndText(str);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        CommonLogUtil.d(TAG, "onActivityResult: " + i + AppInfo.DELIM + i2);
        if (i == 1001) {
            if (intent == null) {
                return;
            }
            this.mPresenter.setDistanceInterval(intent.getExtras().getInt(SportVoiceActivity.EXTRA_DISTANCE_INTERVAL, 0));
            return;
        }
        if (i != 1003 || intent == null) {
            return;
        }
        String string = intent.getExtras().getString(SportRateActivity.EXTRA_RATE_VALUE, "");
        if (!this.mPresenter.getSupportFunction()) {
            setRateRange(string);
        } else {
            setRateRange("");
        }
        CommonLogUtil.d(TAG, "onActivityResult: ");
    }

    @OnClick({R.id.opt_sport_setting})
    public void toSportSetting(View view) {
        SportSettingPowerActivity.start(this);
        RomUtils.isHuawei();
    }

    private void showRemindPower() {
        final SportSetPowerDialogFragment sportSetPowerDialogFragmentNewInstance = SportSetPowerDialogFragment.newInstance();
        sportSetPowerDialogFragmentNewInstance.show(getSupportFragmentManager());
        sportSetPowerDialogFragmentNewInstance.setCancelListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.setting.SportSettingActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                sportSetPowerDialogFragmentNewInstance.dismissAllowingStateLoss();
            }
        });
        sportSetPowerDialogFragmentNewInstance.setOnConfirmListener(new View.OnClickListener() { // from class: com.ido.life.module.sport.setting.SportSettingActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                sportSetPowerDialogFragmentNewInstance.dismissAllowingStateLoss();
            }
        });
    }
}