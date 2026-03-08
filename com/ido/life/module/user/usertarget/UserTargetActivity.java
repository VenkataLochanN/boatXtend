package com.ido.life.module.user.usertarget;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.core.app.ActivityCompat;
import butterknife.BindView;
import butterknife.OnClick;
import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.boat.Xtend.two.R;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.life.base.BaseActivity;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.module.bind.ChoiceBlueTypeActivity;

/* JADX INFO: loaded from: classes3.dex */
public class UserTargetActivity extends BaseActivity<UserTargetPresenter> implements UserTargetView, SeekBar.OnSeekBarChangeListener {
    public static final int REQUEST_CODE = 1003;
    public static final int RESULT_CODE = 1002;
    private static final String TAG = UserTargetActivity.class.getSimpleName();
    private boolean from_where;

    @BindView(R.id.title_rightBtn)
    Button mRightBtn;

    @BindView(R.id.seekBar_step_number)
    AppCompatSeekBar mSeekBarStepNumber;

    @BindView(R.id.seekBar_weight_goal)
    AppCompatSeekBar mSeekBarWeightGoal;

    @BindView(R.id.title_leftBtn)
    ImageButton mTitleLeftBtn;

    @BindView(R.id.title_text)
    TextView mTitleText;

    @BindView(R.id.tv_sport_time_last)
    TextView mTvSortTimeLast;

    @BindView(R.id.tv_step)
    TextView mTvStep;

    @BindView(R.id.tv_step_unit)
    TextView mTvStepUnit;

    @BindView(R.id.tv_steps_goal)
    TextView mTvStepsGoal;

    @BindView(R.id.tv_submit)
    TextView mTvSubmit;

    @BindView(R.id.tv_time_minute)
    TextView mTvTimeMinute;

    @BindView(R.id.tv_weight)
    TextView mTvWeight;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_user_target;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public static void startActivity(Activity activity) {
        activity.startActivity(new Intent(activity, (Class<?>) UserTargetActivity.class));
    }

    @Override // com.ido.life.base.BaseActivity, com.ido.common.base.BaseCoreActivity
    public void initViews() {
        super.initViews();
        this.from_where = getIntent().getBooleanExtra(Constants.IS_FROM_SPLASH, false);
        if (this.from_where) {
            this.mTitleLeftBtn.setVisibility(8);
        } else {
            this.mTitleLeftBtn.setVisibility(0);
        }
        this.mSeekBarStepNumber.setOnSeekBarChangeListener(this);
        this.mSeekBarWeightGoal.setOnSeekBarChangeListener(this);
        this.mSeekBarStepNumber.setMax(20);
    }

    @Override // com.ido.life.module.user.usertarget.UserTargetView
    public void refreshUnitSetting(int i) {
        if (i == 1) {
            this.mSeekBarWeightGoal.setMax(GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN);
            this.mTvTimeMinute.setText(getLanguageText(R.string.public_unit_gong_jin));
        } else {
            this.mSeekBarWeightGoal.setMax(529);
            this.mTvTimeMinute.setText(getLanguageText(R.string.public_unit_pound));
        }
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleText.setText(getLanguageText(R.string.mine_goal_set));
        this.mTvStepsGoal.setText(getLanguageText(R.string.mine_goal_steps));
        this.mTvStepUnit.setText(getLanguageText(R.string.public_sport_step));
        this.mTvSortTimeLast.setText(getLanguageText(R.string.me_weight_goal));
        this.mTvSubmit.setText(getLanguageText(R.string.mine_complete));
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        ((UserTargetPresenter) this.mPresenter).initUserTarget();
    }

    @OnClick({R.id.iv_minus})
    public void minusWeightGoal(View view) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "minus 体重的progress：" + this.mSeekBarWeightGoal.getProgress());
        int progress = this.mSeekBarWeightGoal.getProgress() + (-1);
        int iIntValue = Integer.valueOf((String) this.mTvWeight.getText()).intValue() + (-1);
        if (((UserTargetPresenter) this.mPresenter).getUnit() == 1 && iIntValue >= 10) {
            this.mSeekBarWeightGoal.setProgress(progress);
            this.mTvWeight.setText(String.valueOf(iIntValue));
        } else {
            if (((UserTargetPresenter) this.mPresenter).getUnit() != 2 || iIntValue < 22) {
                return;
            }
            this.mSeekBarWeightGoal.setProgress(progress);
            this.mTvWeight.setText(String.valueOf(iIntValue));
        }
    }

    @OnClick({R.id.iv_add})
    public void addWeightGoal(View view) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "add 体重的progress：" + this.mSeekBarWeightGoal.getProgress());
        int progress = this.mSeekBarWeightGoal.getProgress() + 1;
        int iIntValue = Integer.valueOf((String) this.mTvWeight.getText()).intValue() + 1;
        if (((UserTargetPresenter) this.mPresenter).getUnit() == 1 && iIntValue <= 250) {
            this.mSeekBarWeightGoal.setProgress(progress);
            this.mTvWeight.setText(String.valueOf(iIntValue));
        } else {
            if (((UserTargetPresenter) this.mPresenter).getUnit() != 2 || iIntValue > 551) {
                return;
            }
            this.mSeekBarWeightGoal.setProgress(progress);
            this.mTvWeight.setText(String.valueOf(iIntValue));
        }
    }

    @OnClick({R.id.tv_submit})
    public void toSubmit(View view) {
        int progress;
        int progress2 = (this.mSeekBarStepNumber.getProgress() * 1000) + 5000;
        if (((UserTargetPresenter) this.mPresenter).getUnit() == 1) {
            progress = this.mSeekBarWeightGoal.getProgress() + 10;
        } else {
            progress = this.mSeekBarWeightGoal.getProgress() + 22;
        }
        ((UserTargetPresenter) this.mPresenter).saveInfo(progress, progress2);
    }

    @OnClick({R.id.title_leftBtn})
    public void back(View view) {
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.usertarget.UserTargetView
    public void onDeviceDisConnected() {
        NormalToast.showToast(getLanguageText(R.string.device_pls_connect_device));
        Intent intent = new Intent(this, (Class<?>) ChoiceBlueTypeActivity.class);
        intent.putExtra(ChoiceBlueTypeActivity.FROM_WHERE, "register");
        startActivity(intent);
        supportFinishAfterTransition();
    }

    @Override // com.ido.life.module.user.usertarget.UserTargetView
    public void setStep(int i) {
        if (i >= 10000) {
            this.mSeekBarStepNumber.setProgress((i + FitnessStatusCodes.SUCCESS_NO_DATA_SOURCES) / 1000);
            this.mTvStep.setText(String.valueOf(i));
        } else {
            this.mSeekBarStepNumber.setProgress(0);
            this.mTvStep.setText(String.valueOf(5000));
        }
    }

    @Override // com.ido.life.module.user.usertarget.UserTargetView
    public void setWeight(int i, int i2) {
        if (i2 == 1) {
            if (i >= 10) {
                this.mSeekBarWeightGoal.setProgress(i - 10);
                this.mTvWeight.setText(String.valueOf(i));
                return;
            } else {
                this.mSeekBarWeightGoal.setProgress(0);
                this.mTvWeight.setText(String.valueOf(10));
                return;
            }
        }
        if (i >= 22) {
            this.mSeekBarWeightGoal.setProgress(i - 22);
            this.mTvWeight.setText(String.valueOf(i));
        } else {
            this.mSeekBarWeightGoal.setProgress(0);
            this.mTvWeight.setText(String.valueOf(22));
        }
    }

    @Override // com.ido.life.module.user.usertarget.UserTargetView
    public void showMessage(String str) {
        NormalToast.showToast(str);
    }

    @Override // com.ido.life.base.IBaseLoadingView
    public void showLoading() {
        showLoadingDialog();
    }

    @Override // com.ido.life.base.IBaseLoadingView
    public void dismissLoading() {
        dismissLoadingDialog();
    }

    @Override // com.ido.life.module.user.usertarget.UserTargetView
    public void showSuccess() {
        ActivityCompat.finishAfterTransition(this);
    }

    @Override // com.ido.life.module.user.usertarget.UserTargetView
    public void showFirstSuccess() {
        if (this.from_where) {
            Intent intent = new Intent(this, (Class<?>) ChoiceBlueTypeActivity.class);
            intent.putExtra(ChoiceBlueTypeActivity.FROM_WHERE, "register");
            startActivity(intent);
        } else {
            setResult(1002, new Intent());
        }
        showSuccess();
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            switch (seekBar.getId()) {
                case R.id.seekBar_step_number /* 2131363546 */:
                    this.mTvStep.setText(String.valueOf((i * 1000) + 5000));
                    break;
                case R.id.seekBar_weight_goal /* 2131363547 */:
                    if (((UserTargetPresenter) this.mPresenter).getUnit() == 1) {
                        this.mTvWeight.setText(String.valueOf(i + 10));
                    } else {
                        this.mTvWeight.setText(String.valueOf(i + 22));
                    }
                    break;
            }
        }
    }
}