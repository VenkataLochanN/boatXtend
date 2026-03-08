package com.ido.life.module.sport.power;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.event.stat.one.d;
import com.ido.common.base.BaseCoreActivity;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.AppInfoUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.ResourceUtil;
import com.ido.common.utils.RomUtils;

/* JADX INFO: loaded from: classes2.dex */
public class SportSettingPowerActivity extends BaseCoreActivity {
    private static final String TAG = "SportSettingPowerActiviy";

    @BindView(R.id.iv_step_five)
    ImageView mIvStepFive;

    @BindView(R.id.iv_step_four)
    ImageView mIvStepFour;

    @BindView(R.id.iv_step_one)
    ImageView mIvStepOne;

    @BindView(R.id.iv_step_six)
    ImageView mIvStepSix;

    @BindView(R.id.iv_step_three)
    ImageView mIvStepThree;

    @BindView(R.id.iv_step_two)
    ImageView mIvStepTwo;

    @BindView(R.id.ll_clear)
    LinearLayout mLlClear;

    @BindView(R.id.ll_launch)
    LinearLayout mLlLaunch;

    @BindView(R.id.ll_power)
    LinearLayout mLlPower;

    @BindView(R.id.ll_step_one_two)
    LinearLayout mLlStepOneTwo;

    @BindView(R.id.ll_step_two)
    LinearLayout mLlStepTwo;

    @BindView(R.id.rb_sport_power_four)
    RadioButton mRbSportPowerFour;

    @BindView(R.id.rb_sport_power_one)
    RadioButton mRbSportPowerOne;

    @BindView(R.id.rb_sport_power_three)
    RadioButton mRbSportPowerThree;

    @BindView(R.id.rb_sport_power_two)
    RadioButton mRbSportPowerTwo;

    @BindView(R.id.rg_sport_power_tab_one)
    RadioGroup mRgSportPowerTabOne;

    @BindView(R.id.rg_sport_power_tab_two)
    RadioGroup mRgSportPowerTabTwo;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @BindView(R.id.tv_associated_title)
    TextView mTvAssociatedTitle;

    @BindView(R.id.tv_content_clear)
    TextView mTvContentClear;

    @BindView(R.id.tv_content_power)
    TextView mTvContentPower;

    @BindView(R.id.tv_associated_content)
    TextView mTvEffect;

    @BindView(R.id.tv_launch_content)
    TextView mTvLaunchContent;

    @BindView(R.id.tv_launch_title)
    TextView mTvLaunchTitle;

    @BindView(R.id.tv_set_associated)
    TextView mTvSetAssociated;

    @BindView(R.id.tv_set_clear)
    TextView mTvSetClear;

    @BindView(R.id.tv_set_launch)
    TextView mTvSetLaunch;

    @BindView(R.id.tv_set_power)
    TextView mTvSetPower;

    @BindView(R.id.tv_step)
    TextView mTvStep;

    @BindView(R.id.tv_step_one)
    TextView mTvStepOne;

    @BindView(R.id.tv_step_two)
    TextView mTvStepTwo;

    @BindView(R.id.tv_suggest)
    TextView mTvSuggest;

    @BindView(R.id.tv_title_clear)
    TextView mTvTitleClear;

    @BindView(R.id.tv_title_power)
    TextView mTvTitlePower;

    @BindView(R.id.view_associated)
    View mViewAssociated;

    @BindView(R.id.view_clear)
    View mViewClear;

    @BindView(R.id.view_launch)
    View mViewLaunch;

    @BindView(R.id.view_power)
    View mViewPower;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_sport_setting_power;
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStatusBarColor(getColor(R.color.color_1E1E1E), false);
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, (Class<?>) SportSettingPowerActivity.class));
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mTitleText.setText(LanguageUtil.getLanguageText(R.string.sport_setting_power_title));
        this.mTvSuggest.setText(LanguageUtil.getLanguageText(R.string.sport_setting_power_suggest));
        this.mTvAssociatedTitle.setText(LanguageUtil.getLanguageText(R.string.sport_setting_powers_set_remind));
        this.mTvSetAssociated.setText(LanguageUtil.getLanguageText(R.string.sport_setting_power_set));
        this.mTvEffect.setText(LanguageUtil.getLanguageText(R.string.sport_setting_power_effect));
        this.mTvStep.setText(LanguageUtil.getLanguageText(R.string.sport_setting_power_step));
        this.mTvStepOne.setText(LanguageUtil.getLanguageText(R.string.sport_setting_step_one));
        this.mTvStepTwo.setText(LanguageUtil.getLanguageText(R.string.sport_setting_step_two));
        showTypeByPhone();
        getSetGuideByLanguage();
    }

    private void showTypeByPhone() {
        if (RomUtils.isHuawei()) {
            this.mLlLaunch.setVisibility(8);
            this.mLlClear.setVisibility(8);
            this.mLlPower.setVisibility(8);
            this.mTvLaunchContent.setVisibility(8);
            this.mTvContentPower.setVisibility(8);
            this.mTvContentClear.setVisibility(8);
            this.mViewLaunch.setVisibility(8);
            this.mViewPower.setVisibility(8);
            this.mViewClear.setVisibility(8);
            this.mRgSportPowerTabOne.setVisibility(8);
            this.mRgSportPowerTabTwo.setVisibility(8);
            this.mLlStepOneTwo.setVisibility(8);
            this.mTvAssociatedTitle.setText(LanguageUtil.getLanguageText(R.string.sport_setting_powers_set_remind));
            return;
        }
        if (RomUtils.isXiaomi()) {
            this.mLlClear.setVisibility(8);
            this.mTvContentClear.setVisibility(8);
            this.mViewClear.setVisibility(8);
            this.mRbSportPowerFour.setVisibility(4);
            this.mTvAssociatedTitle.setText(LanguageUtil.getLanguageText(R.string.sport_setting_powers_set_permissions_title));
            return;
        }
        if (RomUtils.isVivo()) {
            this.mLlClear.setVisibility(8);
            this.mTvContentClear.setVisibility(8);
            this.mViewClear.setVisibility(8);
            this.mRbSportPowerFour.setVisibility(0);
            this.mLlStepOneTwo.setVisibility(0);
            this.mTvAssociatedTitle.setText(LanguageUtil.getLanguageText(R.string.sport_setting_powers_set_permissions_title));
            return;
        }
        if (RomUtils.isOppo()) {
            this.mLlClear.setVisibility(8);
            this.mTvContentClear.setVisibility(8);
            this.mViewClear.setVisibility(8);
            this.mRbSportPowerFour.setVisibility(0);
            this.mLlPower.setVisibility(8);
            this.mTvContentPower.setVisibility(8);
            this.mViewPower.setVisibility(8);
            this.mTvAssociatedTitle.setText(LanguageUtil.getLanguageText(R.string.sport_setting_powers_set_permissions_title));
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mRgSportPowerTabOne.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.ido.life.module.sport.power.SportSettingPowerActivity.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_sport_power_one) {
                    if (SportSettingPowerActivity.this.mRbSportPowerOne.isChecked()) {
                        if (SportSettingPowerActivity.this.mRbSportPowerThree.isChecked() || SportSettingPowerActivity.this.mRbSportPowerFour.isChecked()) {
                            SportSettingPowerActivity.this.mRgSportPowerTabTwo.clearCheck();
                        }
                        SportSettingPowerActivity.this.checkStart();
                        return;
                    }
                    return;
                }
                if (i == R.id.rb_sport_power_two && SportSettingPowerActivity.this.mRbSportPowerTwo.isChecked()) {
                    if (SportSettingPowerActivity.this.mRbSportPowerThree.isChecked() || SportSettingPowerActivity.this.mRbSportPowerFour.isChecked()) {
                        SportSettingPowerActivity.this.mRgSportPowerTabTwo.clearCheck();
                    }
                    SportSettingPowerActivity.this.checkAssociated();
                    CommonLogUtil.d(SportSettingPowerActivity.TAG, "onCheckedChanged: 2," + i);
                }
            }
        });
        this.mRgSportPowerTabTwo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.ido.life.module.sport.power.SportSettingPowerActivity.2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rb_sport_power_three) {
                    if (SportSettingPowerActivity.this.mRbSportPowerThree.isChecked()) {
                        if (SportSettingPowerActivity.this.mRbSportPowerOne.isChecked() || SportSettingPowerActivity.this.mRbSportPowerTwo.isChecked()) {
                            SportSettingPowerActivity.this.mRgSportPowerTabOne.clearCheck();
                        }
                        SportSettingPowerActivity.this.checkPower();
                        CommonLogUtil.d(SportSettingPowerActivity.TAG, "onCheckedChanged: 3," + i);
                        return;
                    }
                    return;
                }
                if (i == R.id.rb_sport_power_four && SportSettingPowerActivity.this.mRbSportPowerFour.isChecked()) {
                    if (SportSettingPowerActivity.this.mRbSportPowerOne.isChecked() || SportSettingPowerActivity.this.mRbSportPowerTwo.isChecked()) {
                        SportSettingPowerActivity.this.mRgSportPowerTabOne.clearCheck();
                    }
                    SportSettingPowerActivity.this.checkClear();
                    CommonLogUtil.d(SportSettingPowerActivity.TAG, "onCheckedChanged: 4," + i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkClear() {
        if (RomUtils.isOppo()) {
            setClearOPPO();
        } else if (RomUtils.isVivo()) {
            setClearVIVO();
        } else {
            RomUtils.isXiaomi();
        }
    }

    private void setClearVIVO() {
        this.mTvStepOne.setVisibility(0);
        this.mLlStepOneTwo.setVisibility(8);
        this.mLlStepTwo.setVisibility(0);
        this.mIvStepOne.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_clear_one_vivo_en));
        this.mIvStepThree.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_clear_two_vivo_en));
        this.mIvStepTwo.setVisibility(4);
        this.mIvStepFour.setVisibility(4);
        this.mTvStepOne.setText(LanguageUtil.getLanguageText(R.string.sport_setting_vivo_clear_step_one));
        this.mTvStepTwo.setText(LanguageUtil.getLanguageText(R.string.sport_setting_vivo_clear_step_two));
    }

    private void setClearOPPO() {
        this.mIvStepOne.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_clear_one_oppo_en));
        this.mIvStepThree.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_clear_two_oppo_en));
        this.mIvStepTwo.setVisibility(4);
        this.mIvStepFour.setVisibility(4);
        this.mTvStepOne.setText(LanguageUtil.getLanguageText(R.string.sport_setting_oppo_clear_step_one));
        this.mTvStepTwo.setText(LanguageUtil.getLanguageText(R.string.sport_setting_oppo_clear_step_two));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkPower() {
        if (RomUtils.isOppo()) {
            setPowerOPPO();
        } else if (RomUtils.isVivo()) {
            setPowerVIVO();
        } else if (RomUtils.isXiaomi()) {
            setPowerXiaomi();
        }
    }

    private void setPowerXiaomi() {
        this.mTvStepOne.setVisibility(0);
        this.mLlStepOneTwo.setVisibility(0);
        this.mLlStepTwo.setVisibility(0);
        this.mIvStepFour.setVisibility(0);
        this.mIvStepOne.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_power_one_mi_en));
        this.mIvStepTwo.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_power_two_mi_en));
        this.mIvStepFive.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_power_three_mi_en));
        this.mIvStepThree.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_power_four_mi_en));
        this.mIvStepFour.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_power_five_mi_en));
        this.mTvStepOne.setText(LanguageUtil.getLanguageText(R.string.sport_setting_xiaomi_power_step_one));
        this.mTvStepTwo.setText(LanguageUtil.getLanguageText(R.string.sport_setting_xiaomi_power_step_two));
    }

    private void setPowerVIVO() {
        this.mTvStepOne.setVisibility(0);
        this.mLlStepOneTwo.setVisibility(8);
        this.mLlStepTwo.setVisibility(0);
        this.mIvStepOne.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_power_one_vivo_en));
        this.mIvStepTwo.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_power_two_vivo_en));
        this.mIvStepThree.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_power_three_vivo_en));
        this.mIvStepFour.setVisibility(4);
        this.mTvStepOne.setText(LanguageUtil.getLanguageText(R.string.sport_setting_vivo_power_step_one));
        this.mTvStepTwo.setText(LanguageUtil.getLanguageText(R.string.sport_setting_vivo_power_step_two));
    }

    private void setPowerOPPO() {
        this.mLlStepOneTwo.setVisibility(8);
        this.mIvStepOne.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_power_one_oppo_en));
        this.mIvStepTwo.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_power_two_oppo_en));
        this.mIvStepThree.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_power_three_oppo_en));
        this.mIvStepFour.setVisibility(4);
        this.mTvStepOne.setText(LanguageUtil.getLanguageText(R.string.sport_setting_oppo_power_step_one));
        this.mTvStepTwo.setText(LanguageUtil.getLanguageText(R.string.sport_setting_oppo_power_step_two));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAssociated() {
        if (RomUtils.isOppo()) {
            setAssociatedOPPO();
        } else if (RomUtils.isVivo()) {
            setAssociatedVIVO();
        } else if (RomUtils.isXiaomi()) {
            setAssociatedXiaomi();
        }
    }

    private void setAssociatedXiaomi() {
        this.mTvStepOne.setVisibility(0);
        this.mLlStepOneTwo.setVisibility(0);
        this.mLlStepTwo.setVisibility(0);
        this.mIvStepOne.setVisibility(0);
        this.mIvStepTwo.setVisibility(0);
        this.mIvStepFour.setVisibility(0);
        this.mIvStepFive.setVisibility(0);
        this.mIvStepSix.setVisibility(4);
        this.mIvStepOne.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associated_one_mi_en));
        this.mIvStepTwo.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associated_two_mi_en));
        this.mIvStepFive.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associated_three_mi_en));
        this.mIvStepThree.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associated_four_mi_en));
        this.mIvStepFour.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associated_five_mi_en));
        this.mTvStepOne.setText(LanguageUtil.getLanguageText(R.string.sport_setting_xiaomi_associated_step_one));
        this.mTvStepTwo.setText(LanguageUtil.getLanguageText(R.string.sport_setting_xiaomi_associated_step_two));
    }

    private void setAssociatedVIVO() {
        this.mTvStepOne.setVisibility(0);
        this.mLlStepOneTwo.setVisibility(0);
        this.mLlStepTwo.setVisibility(8);
        this.mIvStepOne.setVisibility(0);
        this.mIvStepTwo.setVisibility(0);
        this.mIvStepFive.setVisibility(0);
        this.mIvStepSix.setVisibility(0);
        this.mIvStepOne.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associate_one_vivo_en));
        this.mIvStepTwo.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associate_two_vivo_en));
        this.mIvStepFive.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associate_three_vivo_en));
        this.mIvStepSix.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associate_four_vivo_en));
        this.mTvStepOne.setText(LanguageUtil.getLanguageText(R.string.sport_setting_vivo_associated_step_one));
    }

    private void setAssociatedOPPO() {
        this.mLlStepOneTwo.setVisibility(8);
        this.mIvStepOne.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associated_one_oppo_en));
        this.mIvStepTwo.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associated_two_oppo_en));
        this.mIvStepThree.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associated_three_oppo_en));
        this.mIvStepFour.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associated_four_oppo_en));
        this.mTvStepOne.setText(LanguageUtil.getLanguageText(R.string.sport_setting_oppo_associated_step_one));
        this.mTvStepTwo.setText(LanguageUtil.getLanguageText(R.string.sport_setting_oppo_associated_step_two));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkStart() {
        if (RomUtils.isOppo()) {
            setStartOPPO();
        } else if (RomUtils.isVivo()) {
            setStartVIVO();
        } else if (RomUtils.isXiaomi()) {
            setStartXiaomi();
        }
    }

    private void setStartVIVO() {
        this.mTvStepOne.setVisibility(0);
        this.mLlStepOneTwo.setVisibility(0);
        this.mLlStepTwo.setVisibility(0);
        this.mIvStepOne.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associate_one_vivo_en));
        this.mIvStepTwo.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associate_two_vivo_en));
        this.mIvStepFive.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associate_three_vivo_en));
        this.mIvStepThree.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associate_four_vivo_en));
        this.mIvStepFour.setVisibility(4);
        this.mIvStepSix.setVisibility(4);
        this.mTvStepOne.setText(LanguageUtil.getLanguageText(R.string.sport_setting_vivo_start_step_one));
        this.mTvStepTwo.setText(LanguageUtil.getLanguageText(R.string.sport_setting_oppo_start_step_two));
    }

    private void setStartOPPO() {
        this.mLlStepOneTwo.setVisibility(8);
        this.mIvStepOne.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associated_one_oppo_en));
        this.mIvStepTwo.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associated_two_oppo_en));
        this.mIvStepThree.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associated_three_oppo_en));
        this.mIvStepFour.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associated_four_oppo_en));
        this.mTvStepOne.setText(LanguageUtil.getLanguageText(R.string.sport_setting_oppo_start_step_one));
        this.mTvStepTwo.setText(LanguageUtil.getLanguageText(R.string.sport_setting_oppo_start_step_two));
    }

    private void getSetGuideByLanguage() {
        if (RomUtils.isOppo()) {
            setStartOPPO();
            return;
        }
        if (RomUtils.isHuawei()) {
            this.mIvStepOne.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_power_one_en));
            this.mIvStepTwo.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_power_two_vivo_en));
            this.mIvStepThree.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_power_three_en));
            this.mIvStepFour.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_power_four_en));
            return;
        }
        if (RomUtils.isVivo()) {
            setStartVIVO();
        } else if (RomUtils.isXiaomi()) {
            setStartXiaomi();
        }
    }

    private void setStartXiaomi() {
        this.mTvStepOne.setVisibility(0);
        this.mLlStepOneTwo.setVisibility(0);
        this.mLlStepTwo.setVisibility(0);
        this.mIvStepOne.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associated_one_mi_en));
        this.mIvStepTwo.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associated_two_mi_en));
        this.mIvStepFive.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associated_three_mi_en));
        this.mIvStepThree.setImageDrawable(ResourceUtil.getDrawable(R.mipmap.ic_sport_associated_four_mi_en));
        this.mIvStepFour.setVisibility(4);
        this.mIvStepSix.setVisibility(4);
        this.mTvStepOne.setText(LanguageUtil.getLanguageText(R.string.sport_setting_xiaomi_start_step_one));
        this.mTvStepTwo.setText(LanguageUtil.getLanguageText(R.string.sport_setting_xiaomi_start_step_two));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initViews() {
        super.initViews();
        this.mTitleText.setText(LanguageUtil.getLanguageText(R.string.sport_setting_power_title));
    }

    @OnClick({R.id.title_leftBtn})
    public void tpBack(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @OnClick({R.id.tv_set_associated})
    public void toAssociate(View view) {
        settingSportAssociate();
    }

    @OnClick({R.id.tv_set_launch})
    public void toSetStart(View view) {
        if (RomUtils.isOppo()) {
            setSportStart();
        } else {
            settingSportAssociate();
        }
    }

    private void setSportStart() {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        if (RomUtils.isOppo()) {
            if (Build.VERSION.SDK_INT >= 23) {
                try {
                    CommonLogUtil.d(TAG, "settingSportRight: 1");
                    intent.setFlags(268435456);
                    intent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity"));
                    startActivity(intent);
                    return;
                } catch (Exception unused) {
                    settingSportAssociate();
                    return;
                }
            }
            CommonLogUtil.d(TAG, "settingSportRight: 1");
            intent.setFlags(268435456);
            intent.setComponent(new ComponentName("com.color.safecenter", "com.color.safecenter.permission.startup.StartupAppListActivity"));
            startActivity(intent);
        }
    }

    @OnClick({R.id.tv_set_power})
    public void toPower(View view) {
        setPower();
    }

    private void setPower() {
        if (RomUtils.isXiaomi()) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.miui.powerkeeper", "com.miui.powerkeeper.ui.HiddenAppsConfigActivity"));
            intent.putExtra("package_name", AppInfoUtil.getPackageName(this));
            intent.putExtra("package_label", AppInfoUtil.getAppName(this));
            startActivity(intent);
            return;
        }
        if (RomUtils.isVivo()) {
            CommonLogUtil.d(TAG, "setPower: ++");
            Intent intent2 = new Intent();
            intent2.setClassName("com.iqoo.powersaving", "com.iqoo.powersaving.PowerSavingManagerActivity");
            intent2.putExtra("package_name", AppInfoUtil.getPackageName(this));
            startActivity(intent2);
            return;
        }
        RomUtils.isOppo();
    }

    private void settingSportAssociate() {
        ComponentName componentNameUnflattenFromString = null;
        if (RomUtils.isHuawei()) {
            int i = Build.VERSION.SDK_INT;
            try {
                Intent intent = new Intent();
                intent.addFlags(268435456);
                if (i >= 28) {
                    componentNameUnflattenFromString = ComponentName.unflattenFromString("com.huawei.systemmanager/.startupmgr.ui.StartupNormalAppListActivity");
                } else if (i >= 26) {
                    componentNameUnflattenFromString = ComponentName.unflattenFromString("com.huawei.systemmanager/.appcontrol.activity.StartupAppControlActivity");
                } else if (i >= 23) {
                    componentNameUnflattenFromString = ComponentName.unflattenFromString("com.huawei.systemmanager/.startupmgr.ui.StartupNormalAppListActivity");
                } else if (i >= 21) {
                    componentNameUnflattenFromString = ComponentName.unflattenFromString("com.huawei.systemmanager/com.huawei.permissionmanager.ui.MainActivity");
                }
                intent.setComponent(componentNameUnflattenFromString);
                startActivity(intent);
                return;
            } catch (Exception unused) {
                return;
            }
        }
        if (RomUtils.isXiaomi()) {
            Intent intent2 = new Intent();
            try {
                intent2.addFlags(268435456);
                intent2.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
                startActivity(intent2);
                return;
            } catch (Exception unused2) {
                jumpAPPInfo();
                return;
            }
        }
        if (RomUtils.isVivo()) {
            try {
                if ((Build.MODEL.contains("Y85") && !Build.MODEL.contains("Y85A")) || Build.MODEL.contains("vivo Y53L")) {
                    CommonLogUtil.d(TAG, "settingSportRight: +++++++++");
                    Intent intent3 = new Intent();
                    intent3.setClassName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.PurviewTabActivity");
                    intent3.putExtra("packagename", getPackageName());
                    intent3.putExtra("tabId", "1");
                    startActivity(intent3);
                    return;
                }
                CommonLogUtil.d(TAG, "settingSportRight: ------------");
                Intent intent4 = new Intent();
                intent4.setClassName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity");
                intent4.setAction("secure.intent.action.softPermissionDetail");
                intent4.putExtra("packagename", getPackageName());
                startActivity(intent4);
                return;
            } catch (Exception unused3) {
                Intent intent5 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent5.setData(Uri.fromParts("package", getPackageName(), null));
                startActivity(intent5);
                return;
            }
        }
        if (RomUtils.isOppo()) {
            Intent intent6 = new Intent();
            try {
                try {
                    if (Build.VERSION.SDK_INT >= 23) {
                        CommonLogUtil.d(TAG, "settingSportRight: 1");
                        intent6.setFlags(268435456);
                        intent6.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity"));
                        startActivity(intent6);
                    } else {
                        CommonLogUtil.d(TAG, "settingSportRight: 1");
                        intent6.setFlags(268435456);
                        intent6.setComponent(new ComponentName("com.color.safecenter", "com.color.safecenter.permission.startup.StartupAppListActivity"));
                        startActivity(intent6);
                    }
                } catch (Exception unused4) {
                    CommonLogUtil.d(TAG, "settingSportRight: 3");
                    Intent intent7 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent7.setData(Uri.fromParts("package", getPackageName(), null));
                    startActivity(intent7);
                }
            } catch (Exception unused5) {
                intent6.setFlags(268435456);
                intent6.putExtra("pkg_name", getPackageName());
                intent6.putExtra(d.j, getString(R.string.app_name));
                intent6.putExtra("class_name", "com.welab.notificationdemo.MainActivity");
                intent6.setComponent(new ComponentName("com.coloros.notificationmanager", "com.coloros.notificationmanager.AppDetailPreferenceActivity"));
                startActivity(intent6);
            }
        }
    }

    private void jumpAPPInfo() {
        try {
            try {
                Intent intent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                intent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
                intent.putExtra("extra_pkgname", getPackageName());
                startActivity(intent);
            } catch (Exception unused) {
                Intent intent2 = new Intent("miui.intent.action.APP_PERM_EDITOR");
                intent2.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                intent2.putExtra("extra_pkgname", getPackageName());
                startActivity(intent2);
            }
        } catch (Exception unused2) {
            Intent intent3 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent3.setData(Uri.fromParts("package", getPackageName(), null));
            startActivity(intent3);
        }
    }
}