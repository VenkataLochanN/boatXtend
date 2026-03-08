package com.ido.life.module.device.activity;

import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.common.dialog.TimeDialogFragment;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.presenter.NightLightPresenter;
import com.ido.life.module.device.view.INightLightView;
import com.ido.life.module.home.HomeFragmentPresenter;

/* JADX INFO: loaded from: classes2.dex */
public class NightLightActivity extends BaseActivity<NightLightPresenter> implements TimeDialogFragment.OnItemSelectedListener, INightLightView, CustomToggleButton.OnSwitchListener, View.OnClickListener {
    private static final int LIGHT_END_TIME = 2;
    private static final int LIGHT_START_TIME = 1;
    public static final String SCREEN_BRIGHTNESS = "screen_brightness";

    @BindView(R.id.item_end_time)
    CustomItemLabelView mItemEndTime;

    @BindView(R.id.item_night_light_switch)
    CustomItemLabelView mItemNightLightSwitch;

    @BindView(R.id.item_start_time)
    CustomItemLabelView mItemStartTime;
    private ScreenBrightness mNightLightState;
    private int selected;

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_night_light;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mNightLightState = ((NightLightPresenter) this.mPresenter).getNightLightState();
        ScreenBrightness screenBrightness = (ScreenBrightness) getIntent().getSerializableExtra(SCREEN_BRIGHTNESS);
        if (screenBrightness != null) {
            this.mNightLightState = screenBrightness;
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        initState();
        this.mItemNightLightSwitch.setOnSwitchListener(this);
        this.mTitleBar.setLeftOnClick(this);
    }

    private void initState() {
        boolean z = this.mNightLightState.autoAdjustNight == 3;
        this.mItemNightLightSwitch.setSwitchStatus(z);
        this.mItemStartTime.setAlpha(z ? 1.0f : 0.3f);
        this.mItemEndTime.setAlpha(z ? 1.0f : 0.3f);
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mItemStartTime.setValue(((NightLightPresenter) this.mPresenter).formatTimeByTimeMode(this.mNightLightState.startHour, this.mNightLightState.startMinute));
        this.mItemEndTime.setValue(((NightLightPresenter) this.mPresenter).formatTimeByTimeMode(this.mNightLightState.endHour, this.mNightLightState.endMinute));
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_night_auto_light));
        this.mItemNightLightSwitch.setTitle(getLanguageText(R.string.device_night_auto_light));
        this.mItemStartTime.setTitle(getLanguageText(R.string.device_start_time));
        this.mItemEndTime.setTitle(getLanguageText(R.string.device_end_time));
    }

    @OnClick({R.id.item_start_time, R.id.item_end_time})
    public void onViewClicked(View view) {
        if (this.mItemNightLightSwitch.getSwitchStatus()) {
            int id = view.getId();
            if (id == R.id.item_end_time) {
                this.selected = 2;
                showTimePickerDialog(this.mNightLightState.endHour, this.mNightLightState.endMinute);
            } else {
                if (id != R.id.item_start_time) {
                    return;
                }
                this.selected = 1;
                showTimePickerDialog(this.mNightLightState.startHour, this.mNightLightState.startMinute);
            }
        }
    }

    private void showTimePickerDialog(int i, int i2) {
        TimeDialogFragment timeDialogFragmentNewInstance;
        if (((NightLightPresenter) this.mPresenter).isTimeFormat24()) {
            timeDialogFragmentNewInstance = TimeDialogFragment.newInstance(i, i2);
        } else {
            timeDialogFragmentNewInstance = TimeDialogFragment.newInstance(i, i2, ((NightLightPresenter) this.mPresenter).isTimeFormat24(), new String[]{getLanguageText(R.string.public_am), getLanguageText(R.string.public_pm)});
        }
        timeDialogFragmentNewInstance.show(getSupportFragmentManager());
        timeDialogFragmentNewInstance.setOnItemSelectedListener(this);
    }

    @Override // com.ido.common.dialog.TimeDialogFragment.OnItemSelectedListener
    public void onTimeSelected(int i, int i2, int i3) {
        int i4 = this.selected;
        if (i4 == 1) {
            ScreenBrightness screenBrightness = this.mNightLightState;
            screenBrightness.startHour = i2;
            screenBrightness.startMinute = i3;
            this.mItemStartTime.setValue(((NightLightPresenter) this.mPresenter).formatTimeByTimeMode(this.mNightLightState.startHour, this.mNightLightState.startMinute));
            return;
        }
        if (i4 != 2) {
            return;
        }
        ScreenBrightness screenBrightness2 = this.mNightLightState;
        screenBrightness2.endHour = i2;
        screenBrightness2.endMinute = i3;
        this.mItemEndTime.setValue(((NightLightPresenter) this.mPresenter).formatTimeByTimeMode(this.mNightLightState.endHour, this.mNightLightState.endMinute));
    }

    @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
    public void onSwitched(View view, boolean z) {
        this.mNightLightState.autoAdjustNight = z ? 3 : 1;
        this.mItemStartTime.setAlpha(z ? 1.0f : 0.3f);
        this.mItemEndTime.setAlpha(z ? 1.0f : 0.3f);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        onBackPressed();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        saveData();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        if (HomeFragmentPresenter.mIsSyncing) {
            showToast(getLanguageText(R.string.syncing_pls_try_again_later));
        } else {
            ((NightLightPresenter) this.mPresenter).sendNightLightMode2Device(this.mNightLightState);
        }
        finish();
    }

    @Override // com.ido.life.module.device.view.INightLightView
    public void onGetScreenBrightness(ScreenBrightness screenBrightness) {
        this.mNightLightState = screenBrightness;
        initState();
        onStart();
    }

    @Override // com.ido.life.module.device.view.INightLightView
    public void onSetNightLightSuccess() {
        CommonLogUtil.d("onSetNightLightSuccess");
    }

    @Override // com.ido.life.module.device.view.INightLightView
    public void onSetNightLightFailed() {
        CommonLogUtil.d("onSetNightLightFailed");
    }
}