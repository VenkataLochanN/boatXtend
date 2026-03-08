package com.ido.life.module.device.activity;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.common.dialog.TimeDialogFragment;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.CustomToggleButton;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.presenter.DNDModePresenter;
import com.ido.life.module.device.view.IDNDModeView;
import com.ido.life.module.home.HomeFragmentPresenter;

/* JADX INFO: loaded from: classes2.dex */
public class DNDModeActivity extends BaseActivity<DNDModePresenter> implements TimeDialogFragment.OnItemSelectedListener, IDNDModeView {
    private static final int DND_DAYTIME_END = 1;
    private static final int DND_DAYTIME_START = 0;
    public static final String DND_MODE = "dnd_mode";
    private static final int DND_NIGHTTIME_END = 3;
    private static final int DND_NIGHTTIME_START = 2;

    @BindView(R.id.comm_loading_view)
    CommLoadingView mCommLoadingView;
    private NotDisturbPara mDNDModeState;

    @BindView(R.id.item_dnd_end_time)
    CustomItemLabelView mItemDayEndTime;

    @BindView(R.id.item_dnd_start_time)
    CustomItemLabelView mItemDayStartTime;

    @BindView(R.id.item_dnd_daytime)
    CustomItemLabelView mItemDndDaytimeSwitch;

    @BindView(R.id.item_dnd_nighttime)
    CustomItemLabelView mItemDndNighttimeSwitch;

    @BindView(R.id.item_night_light_end_time)
    CustomItemLabelView mItemNightEndTime;

    @BindView(R.id.item_night_light_start_time)
    CustomItemLabelView mItemNightStartTime;

    @BindView(R.id.layout_dnd_time_daytime)
    LinearLayout mLayoutDndTimeDaytime;

    @BindView(R.id.layout_dnd_time_nighttime)
    LinearLayout mLayoutDndTimeNightTime;

    @BindView(R.id.rtv_dnd_mode_tip)
    RegularTextView mRtvDndModeTip;
    private int selected;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_dnd_mode;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mDNDModeState = (NotDisturbPara) getIntent().getSerializableExtra(DND_MODE);
        if (this.mDNDModeState == null) {
            this.mDNDModeState = ((DNDModePresenter) this.mPresenter).getDNDModeState();
        }
        if (((DNDModePresenter) this.mPresenter).isSupportGetDndMode()) {
            ((DNDModePresenter) this.mPresenter).getDNDModeFromDevice();
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.initLayout(1).setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DNDModeActivity$jHYVU4H-Fup5H7MVWKNOOvDWjyU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$DNDModeActivity(view);
            }
        });
        initDataState();
        this.mItemDndDaytimeSwitch.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DNDModeActivity$sqCkZJUgxU9YCAEduTxwP99HSxU
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initEvent$1$DNDModeActivity(view, z);
            }
        });
        this.mItemDndNighttimeSwitch.setOnSwitchListener(new CustomToggleButton.OnSwitchListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$DNDModeActivity$-1_ZOxMoP3uRxohTHlusfazrcl0
            @Override // com.ido.life.customview.CustomToggleButton.OnSwitchListener
            public final void onSwitched(View view, boolean z) {
                this.f$0.lambda$initEvent$2$DNDModeActivity(view, z);
            }
        });
    }

    public /* synthetic */ void lambda$initEvent$0$DNDModeActivity(View view) {
        saveData();
    }

    public /* synthetic */ void lambda$initEvent$1$DNDModeActivity(View view, boolean z) {
        this.mDNDModeState.onOFf = z ? 170 : 85;
        this.mLayoutDndTimeDaytime.setAlpha(z ? 1.0f : 0.3f);
    }

    public /* synthetic */ void lambda$initEvent$2$DNDModeActivity(View view, boolean z) {
        this.mDNDModeState.noontimeRestOnOff = z ? 170 : 85;
        this.mLayoutDndTimeNightTime.setAlpha(z ? 1.0f : 0.3f);
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mItemDayStartTime.setValue(((DNDModePresenter) this.mPresenter).formatTimeByTimeMode(this.mDNDModeState.startHour, this.mDNDModeState.startMinute));
        this.mItemDayEndTime.setValue(((DNDModePresenter) this.mPresenter).formatTimeByTimeMode(this.mDNDModeState.endHour, this.mDNDModeState.endMinute));
        this.mItemNightStartTime.setValue(((DNDModePresenter) this.mPresenter).formatTimeByTimeMode(this.mDNDModeState.noontimeRestStartHour, this.mDNDModeState.noontimeRestStartMinute));
        this.mItemNightEndTime.setValue(((DNDModePresenter) this.mPresenter).formatTimeByTimeMode(this.mDNDModeState.noontimeRestEndHour, this.mDNDModeState.noontimeRestEndMinute));
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_dnd_mode));
        this.mItemDndDaytimeSwitch.setTitle(getLanguageText(R.string.device_dnd_daytime));
        this.mItemDayStartTime.setTitle(getLanguageText(R.string.device_start_time));
        this.mItemDayEndTime.setTitle(getLanguageText(R.string.device_end_time));
        this.mItemDndNighttimeSwitch.setTitle(getLanguageText(R.string.device_dnd_nighttime));
        this.mItemNightStartTime.setTitle(getLanguageText(R.string.device_start_time));
        this.mItemNightEndTime.setTitle(getLanguageText(R.string.device_end_time));
        this.mRtvDndModeTip.setText(getLanguageText(R.string.device_dnd_mode_tip));
    }

    private void initDataState() {
        boolean z = this.mDNDModeState.onOFf == 170;
        this.mItemDndDaytimeSwitch.setSwitchStatus(z);
        this.mLayoutDndTimeDaytime.setAlpha(z ? 1.0f : 0.3f);
        boolean z2 = this.mDNDModeState.noontimeRestOnOff == 170;
        this.mItemDndNighttimeSwitch.setSwitchStatus(z2);
        this.mLayoutDndTimeNightTime.setAlpha(z2 ? 1.0f : 0.3f);
    }

    @OnClick({R.id.item_dnd_start_time, R.id.item_dnd_end_time, R.id.item_night_light_start_time, R.id.item_night_light_end_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_dnd_end_time /* 2131362400 */:
                if (this.mItemDndDaytimeSwitch.getSwitchStatus()) {
                    this.selected = 1;
                    showTimePickerDialog(this.mDNDModeState.endHour, this.mDNDModeState.endMinute);
                }
                break;
            case R.id.item_dnd_start_time /* 2131362403 */:
                if (this.mItemDndDaytimeSwitch.getSwitchStatus()) {
                    this.selected = 0;
                    showTimePickerDialog(this.mDNDModeState.startHour, this.mDNDModeState.startMinute);
                }
                break;
            case R.id.item_night_light_end_time /* 2131362449 */:
                if (this.mItemDndNighttimeSwitch.getSwitchStatus()) {
                    this.selected = 3;
                    showTimePickerDialog(this.mDNDModeState.noontimeRestEndHour, this.mDNDModeState.noontimeRestEndMinute);
                }
                break;
            case R.id.item_night_light_start_time /* 2131362450 */:
                if (this.mItemDndNighttimeSwitch.getSwitchStatus()) {
                    this.selected = 2;
                    showTimePickerDialog(this.mDNDModeState.noontimeRestStartHour, this.mDNDModeState.noontimeRestStartMinute);
                }
                break;
        }
    }

    private void showTimePickerDialog(int i, int i2) {
        TimeDialogFragment timeDialogFragmentNewInstance;
        if (((DNDModePresenter) this.mPresenter).isTimeFormat24()) {
            timeDialogFragmentNewInstance = TimeDialogFragment.newInstance(i, i2);
        } else {
            timeDialogFragmentNewInstance = TimeDialogFragment.newInstance(i, i2, ((DNDModePresenter) this.mPresenter).isTimeFormat24(), new String[]{getLanguageText(R.string.public_am), getLanguageText(R.string.public_pm)});
        }
        timeDialogFragmentNewInstance.show(getSupportFragmentManager());
        timeDialogFragmentNewInstance.setOnItemSelectedListener(this);
    }

    @Override // com.ido.common.dialog.TimeDialogFragment.OnItemSelectedListener
    public void onTimeSelected(int i, int i2, int i3) {
        CommonLogUtil.d("onTimeSelected = " + i + " : " + i2 + " : " + i3);
        int i4 = this.selected;
        if (i4 == 0) {
            NotDisturbPara notDisturbPara = this.mDNDModeState;
            notDisturbPara.startHour = i2;
            notDisturbPara.startMinute = i3;
            this.mItemDayStartTime.setValue(((DNDModePresenter) this.mPresenter).formatTimeByTimeMode(this.mDNDModeState.startHour, this.mDNDModeState.startMinute));
            return;
        }
        if (i4 == 1) {
            NotDisturbPara notDisturbPara2 = this.mDNDModeState;
            notDisturbPara2.endHour = i2;
            notDisturbPara2.endMinute = i3;
            this.mItemDayEndTime.setValue(((DNDModePresenter) this.mPresenter).formatTimeByTimeMode(this.mDNDModeState.endHour, this.mDNDModeState.endMinute));
            return;
        }
        if (i4 == 2) {
            NotDisturbPara notDisturbPara3 = this.mDNDModeState;
            notDisturbPara3.noontimeRestStartHour = i2;
            notDisturbPara3.noontimeRestStartMinute = i3;
            this.mItemNightStartTime.setValue(((DNDModePresenter) this.mPresenter).formatTimeByTimeMode(i2, i3));
            return;
        }
        if (i4 != 3) {
            return;
        }
        NotDisturbPara notDisturbPara4 = this.mDNDModeState;
        notDisturbPara4.noontimeRestEndHour = i2;
        notDisturbPara4.noontimeRestEndMinute = i3;
        this.mItemNightEndTime.setValue(((DNDModePresenter) this.mPresenter).formatTimeByTimeMode(i2, i3));
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        if (HomeFragmentPresenter.mIsSyncing) {
            showToast(getLanguageText(R.string.syncing_pls_try_again_later));
        } else {
            this.mCommLoadingView.setVisibility(0);
            ((DNDModePresenter) this.mPresenter).sendDNDMode2Device(this.mDNDModeState);
        }
    }

    @Override // com.ido.life.module.device.view.IDNDModeView
    public void onGetDNDMode(NotDisturbPara notDisturbPara) {
        this.mDNDModeState = notDisturbPara;
        initDataState();
        onStart();
    }

    @Override // com.ido.life.module.device.view.IDNDModeView
    public void onSetDNDModeSuccess() {
        this.mCommLoadingView.setVisibility(8);
        showCmdResultToast(true);
        finish();
    }

    @Override // com.ido.life.module.device.view.IDNDModeView
    public void onSetDNDModeFailed() {
        this.mCommLoadingView.setVisibility(8);
        showCmdResultToast(false);
    }
}