package com.ido.life.module.sport.setting.voice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.customview.MediumRadioButton;
import com.ido.life.util.RunTimeUtil;

/* JADX INFO: loaded from: classes2.dex */
public class SportVoiceActivity extends BaseCoreActivity {
    public static final String EXTRA_DISTANCE_INTERVAL = "distance_interval";
    public static final int REQUEST_CODE = 1001;
    public static final int RESULT_CODE = 1002;
    private static final String TAG = "SportVoiceActivity";
    private int mDistanceInterval = 0;

    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;

    @BindView(R.id.mrb_five_km)
    MediumRadioButton mRbFiveKm;

    @BindView(R.id.mrb_no_remind)
    MediumRadioButton mRbNoRemind;

    @BindView(R.id.mrb_one_km)
    MediumRadioButton mRbOneKm;

    @BindView(R.id.mrb_ten_km)
    MediumRadioButton mRbTenKm;

    @BindView(R.id.mrb_three_km)
    MediumRadioButton mRbThreeKm;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @BindView(R.id.tv_remind_frequency)
    RegularTextView mTvRemindFrequency;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_sport_voice;
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, (Class<?>) SportVoiceActivity.class));
    }

    public static void startActivityForResult(Activity activity, String str) {
        Intent intent = new Intent(activity, (Class<?>) SportVoiceActivity.class);
        intent.putExtra(EXTRA_DISTANCE_INTERVAL, str);
        activity.startActivityForResult(intent, 1001);
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initViews() {
        super.initViews();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        String stringExtra = getIntent().getStringExtra(EXTRA_DISTANCE_INTERVAL);
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        if (LanguageUtil.getLanguageText(R.string.sport_setting_remind_no).equals(stringExtra)) {
            this.mDistanceInterval = 0;
        } else {
            String languageText = LanguageUtil.getLanguageText(R.string.sport_run_distance_unit);
            try {
                this.mDistanceInterval = Integer.parseInt(stringExtra.replace(languageText, "").replace(LanguageUtil.getLanguageText(R.string.sport_run_distance_unit_mi), ""));
            } catch (Exception e2) {
                e2.printStackTrace();
                this.mDistanceInterval = 0;
            }
        }
        refreshChecked();
    }

    private void refreshChecked() {
        int i = this.mDistanceInterval;
        if (i == 0) {
            this.mRadioGroup.check(this.mRbNoRemind.getId());
            return;
        }
        if (i == 1) {
            this.mRadioGroup.check(this.mRbOneKm.getId());
            return;
        }
        if (i == 3) {
            this.mRadioGroup.check(this.mRbThreeKm.getId());
        } else if (i == 5) {
            this.mRadioGroup.check(this.mRbFiveKm.getId());
        } else {
            if (i != 10) {
                return;
            }
            this.mRadioGroup.check(this.mRbTenKm.getId());
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.ido.life.module.sport.setting.voice.SportVoiceActivity.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i != R.id.mrb_five_km) {
                    switch (i) {
                        case R.id.mrb_no_remind /* 2131363071 */:
                            SportVoiceActivity.this.mDistanceInterval = 0;
                            break;
                        case R.id.mrb_one_km /* 2131363072 */:
                            SportVoiceActivity.this.mDistanceInterval = 1;
                            break;
                        case R.id.mrb_ten_km /* 2131363073 */:
                            SportVoiceActivity.this.mDistanceInterval = 10;
                            break;
                        case R.id.mrb_three_km /* 2131363074 */:
                            SportVoiceActivity.this.mDistanceInterval = 3;
                            break;
                    }
                }
                SportVoiceActivity.this.mDistanceInterval = 5;
            }
        });
    }

    @OnClick({R.id.title_leftBtn})
    public void toBack(View view) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_DISTANCE_INTERVAL, this.mDistanceInterval);
        intent.putExtras(bundle);
        setResult(1002, intent);
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        toBack(null);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mTitleText.setText(LanguageUtil.getLanguageText(R.string.sport_setting_voice_title));
        this.mTvRemindFrequency.setText(LanguageUtil.getLanguageText(R.string.sport_setting_remind_frequency));
        this.mRbNoRemind.setText(LanguageUtil.getLanguageText(R.string.sport_setting_remind_no));
        if (RunTimeUtil.getInstance().getUnitSet() == 1) {
            this.mRbOneKm.setText(LanguageUtil.getLanguageText(R.string.sport_setting_one_km));
            this.mRbThreeKm.setText(LanguageUtil.getLanguageText(R.string.sport_setting_three_km));
            this.mRbFiveKm.setText(LanguageUtil.getLanguageText(R.string.sport_setting_five_km));
            this.mRbTenKm.setText(LanguageUtil.getLanguageText(R.string.sport_setting_ten_km));
            return;
        }
        this.mRbOneKm.setText(1 + LanguageUtil.getLanguageText(R.string.sport_detail_distance_unit_mi));
        this.mRbThreeKm.setText(3 + LanguageUtil.getLanguageText(R.string.sport_detail_distance_unit_mi));
        this.mRbFiveKm.setText(5 + LanguageUtil.getLanguageText(R.string.sport_detail_distance_unit_mi));
        this.mRbTenKm.setText(10 + LanguageUtil.getLanguageText(R.string.sport_detail_distance_unit_mi));
    }
}