package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.RadioGroup;
import butterknife.BindView;
import com.boat.Xtend.two.R;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.MediumRadioButton;
import com.ido.life.module.device.presenter.UnitSetPresenter;
import com.ido.life.module.device.view.IUnitSetView;

/* JADX INFO: loaded from: classes2.dex */
public class TimeFormatActivity extends BaseActivity<UnitSetPresenter> implements RadioGroup.OnCheckedChangeListener, IUnitSetView {
    private int defaultFormat;

    @BindView(R.id.mrb_follow_system)
    MediumRadioButton mMrbFollowSystem;

    @BindView(R.id.mrb_format_12)
    MediumRadioButton mMrbFormat12;

    @BindView(R.id.mrb_format_24)
    MediumRadioButton mMrbFormat24;

    @BindView(R.id.radio_group)
    RadioGroup mRadioGroup;
    private int mTimeFormat;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_time_format;
    }

    @Override // com.ido.life.base.BaseActivity
    protected boolean isFunctionActivity() {
        return true;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mTimeFormat = ((UnitSetPresenter) this.mPresenter).getTimeFormat();
        this.defaultFormat = ((UnitSetPresenter) this.mPresenter).getTimeFormat();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.initLayout(1).setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$TimeFormatActivity$Xey6TEk8uPBue8MicN2j4b80s70
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$TimeFormatActivity(view);
            }
        }).setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$TimeFormatActivity$hnl1pOgfwedDhJ3EG2qeOdzbWJQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$1$TimeFormatActivity(view);
            }
        });
        this.mRadioGroup.setOnCheckedChangeListener(this);
        initDataState();
    }

    public /* synthetic */ void lambda$initEvent$0$TimeFormatActivity(View view) {
        saveData();
    }

    public /* synthetic */ void lambda$initEvent$1$TimeFormatActivity(View view) {
        onBackPressed();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_time_format));
        this.mMrbFollowSystem.setText(getLanguageText(R.string.device_follow_system_time));
        this.mMrbFormat12.setText(getLanguageText(R.string.device_hours_format_12));
        this.mMrbFormat24.setText(getLanguageText(R.string.device_hours_format_24));
    }

    private void initDataState() {
        int i = this.mTimeFormat;
        if (i == 0) {
            this.mRadioGroup.check(R.id.mrb_follow_system);
        } else if (i == 1) {
            this.mRadioGroup.check(R.id.mrb_format_24);
        } else {
            if (i != 2) {
                return;
            }
            this.mRadioGroup.check(R.id.mrb_format_12);
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.mrb_follow_system /* 2131363064 */:
                this.mTimeFormat = 0;
                break;
            case R.id.mrb_format_12 /* 2131363065 */:
                this.mTimeFormat = 2;
                break;
            case R.id.mrb_format_24 /* 2131363066 */:
                this.mTimeFormat = 1;
                break;
        }
    }

    private boolean isDataChanged() {
        return this.mTimeFormat != this.defaultFormat;
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        if (isDataChanged()) {
            showLoadingDialog();
            ((UnitSetPresenter) this.mPresenter).sendTimeFormat2Device(this.mTimeFormat);
        } else {
            finish();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (isDataChanged()) {
            showDataChangedDialog();
        } else {
            super.onBackPressed();
        }
    }

    @Override // com.ido.life.module.device.view.IUnitSetView
    public void onSetUnitSuccess() {
        dismissLoadingDialog();
        showCmdResultToast(true);
        finish();
    }

    @Override // com.ido.life.module.device.view.IUnitSetView
    public void onSetUnitFailed() {
        dismissLoadingDialog();
        showCmdResultToast(false);
    }
}