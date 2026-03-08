package com.ido.life.module.device.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.Alarm;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.presenter.AlarmEditPresenter;
import com.ido.life.util.TimeUtil;
import com.watch.life.wheelview.adapter.ArrayWheelAdapter;
import com.watch.life.wheelview.adapter.NumericWheelAdapter;
import com.watch.life.wheelview.listener.OnItemSelectedListener;
import com.watch.life.wheelview.view.WheelView;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmClockEditActivity extends BaseActivity<AlarmEditPresenter> {
    public static final String ALARM_KEY = "alarm";
    private Alarm defaultAlarm;
    private boolean isAm;

    @BindView(R.id.item_alarm_name)
    CustomItemLabelView mItemAlarmName;

    @BindView(R.id.item_repetition)
    CustomItemLabelView mItemRepetition;

    @BindView(R.id.rtv_delete_alarm)
    RegularTextView mRtvDeleteAlarm;
    private Alarm mSelectedAlarm;

    @BindView(R.id.wv_am_pm)
    WheelView mWvAmPm;

    @BindView(R.id.wv_hour)
    WheelView mWvHour;

    @BindView(R.id.wv_min)
    WheelView mWvMin;

    @BindView(R.id.timeLayout)
    LinearLayout timeLayout;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_alarm_clock_edit;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        this.mSelectedAlarm = (Alarm) getIntent().getSerializableExtra("alarm");
        if (this.mSelectedAlarm == null) {
            this.mRtvDeleteAlarm.setVisibility(8);
            this.defaultAlarm = null;
            this.mSelectedAlarm = ((AlarmEditPresenter) this.mPresenter).initAlarm();
        } else {
            this.mRtvDeleteAlarm.setVisibility(0);
            this.defaultAlarm = ((AlarmEditPresenter) this.mPresenter).cloneAlarm(this.mSelectedAlarm);
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.initLayout(1).setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmClockEditActivity$5E0XAT07ecE35Q0fSgQ7xX4Cjqc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$AlarmClockEditActivity(view);
            }
        }).setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmClockEditActivity$urPSgPJkJTaMUxPa0jN07K6UWk0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$1$AlarmClockEditActivity(view);
            }
        });
        initWheelView();
        this.mItemRepetition.setValue(((AlarmEditPresenter) this.mPresenter).formatWeekRepeat(this.mSelectedAlarm.getWeekRepeat()));
        this.mItemAlarmName.setValue(((AlarmEditPresenter) this.mPresenter).getAlarmNameByType(this.mSelectedAlarm.getAlarmType()));
        this.mItemAlarmName.setVisibility(((AlarmEditPresenter) this.mPresenter).isSupportAlarmType() ? 0 : 8);
    }

    public /* synthetic */ void lambda$initEvent$0$AlarmClockEditActivity(View view) {
        onBackPressed();
    }

    public /* synthetic */ void lambda$initEvent$1$AlarmClockEditActivity(View view) {
        saveData();
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        int alarmHour = this.mSelectedAlarm.getAlarmHour();
        boolean z = false;
        if (((AlarmEditPresenter) this.mPresenter).isTimeFormat24()) {
            this.mWvAmPm.setVisibility(8);
            this.mWvHour.setAdapter(new NumericWheelAdapter(0, 23));
            this.mWvHour.setCurrentItem(alarmHour);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.timeLayout.getLayoutParams();
            layoutParams.width = (ScreenUtil.getScreenW() * 2) / 3;
            this.timeLayout.setLayoutParams(layoutParams);
        } else {
            this.mWvAmPm.setVisibility(0);
            if (alarmHour > 0 && alarmHour <= 12) {
                z = true;
            }
            this.isAm = z;
            this.mWvAmPm.setCurrentItem(!this.isAm ? 1 : 0);
            this.mWvHour.setAdapter(new NumericWheelAdapter(1, 12));
            this.mWvHour.setNumberCount(1);
            int itemsCount = alarmHour % 12;
            if (itemsCount == 0) {
                itemsCount = this.mWvHour.getItemsCount();
            }
            this.mWvHour.setCurrentItem(itemsCount - 1);
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.timeLayout.getLayoutParams();
            layoutParams2.width = ScreenUtil.getScreenW();
            this.timeLayout.setLayoutParams(layoutParams2);
        }
        this.mWvMin.setCurrentItem(this.mSelectedAlarm.getAlarmMinute());
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        if (this.defaultAlarm == null) {
            this.mTitleBar.setTitle(getLanguageText(R.string.device_add_alarm_clock));
        } else {
            this.mTitleBar.setTitle(getLanguageText(R.string.device_edit_alarm_clock));
        }
    }

    private void initWheelView() {
        this.mWvAmPm.setAdapter(new ArrayWheelAdapter(Arrays.asList(getLanguageText(R.string.public_am), getLanguageText(R.string.public_pm))));
        this.mWvAmPm.setCyclic(false);
        this.mWvAmPm.setItemsVisibleCount(5);
        this.mWvHour.setCyclic(false);
        this.mWvHour.setItemsVisibleCount(5);
        this.mWvMin.setAdapter(new NumericWheelAdapter(0, 59));
        this.mWvMin.setCyclic(false);
        this.mWvMin.setItemsVisibleCount(5);
        this.mWvAmPm.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmClockEditActivity$fa00rtEfIVBNlmvjIRo8W5ql-O0
            @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
            public final void onItemSelected(int i) {
                this.f$0.lambda$initWheelView$2$AlarmClockEditActivity(i);
            }
        });
        this.mWvHour.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmClockEditActivity$LicAGwRbr-HXw9oLDT09K8gEXCE
            @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
            public final void onItemSelected(int i) {
                this.f$0.lambda$initWheelView$3$AlarmClockEditActivity(i);
            }
        });
        this.mWvMin.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmClockEditActivity$D0EhCYJCTChySew68aAh_z8Z1_0
            @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
            public final void onItemSelected(int i) {
                this.f$0.lambda$initWheelView$4$AlarmClockEditActivity(i);
            }
        });
    }

    public /* synthetic */ void lambda$initWheelView$2$AlarmClockEditActivity(int i) {
        this.isAm = i == 0;
        Alarm alarm = this.mSelectedAlarm;
        alarm.setAlarmHour(TimeUtil.formatHourByAm_or_pm(alarm.getAlarmHour(), this.isAm));
    }

    public /* synthetic */ void lambda$initWheelView$3$AlarmClockEditActivity(int i) {
        if (((AlarmEditPresenter) this.mPresenter).isTimeFormat24()) {
            this.mSelectedAlarm.setAlarmHour(i);
        } else {
            this.mSelectedAlarm.setAlarmHour(TimeUtil.format12To24(i + 1, this.isAm));
            if (i == this.mWvHour.getItemsCount() - 1) {
                WheelView wheelView = this.mWvAmPm;
                wheelView.setCurrentItem(wheelView.getCurrentItem() == 0 ? 1 : 0);
                this.isAm = this.mWvAmPm.getCurrentItem() == 0;
                Alarm alarm = this.mSelectedAlarm;
                alarm.setAlarmHour(TimeUtil.formatHourByAm_or_pm(alarm.getAlarmHour(), this.isAm));
            }
        }
        CommonLogUtil.d("selectedHour = " + this.mSelectedAlarm.getAlarmHour());
    }

    public /* synthetic */ void lambda$initWheelView$4$AlarmClockEditActivity(int i) {
        this.mSelectedAlarm.setAlarmMinute(i);
        CommonLogUtil.d("selectedMin = " + this.mSelectedAlarm.getAlarmMinute());
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent == null) {
            return;
        }
        if (i2 == 33) {
            int intExtra = intent.getIntExtra(AlarmTypeEditActivity.ALARM_TYPE, -1);
            this.mItemAlarmName.setValue(((AlarmEditPresenter) this.mPresenter).getAlarmNameByType(intExtra));
            this.mSelectedAlarm.setAlarmType(intExtra);
        } else {
            if (i2 != 66) {
                return;
            }
            this.mSelectedAlarm.setWeekRepeat(intent.getBooleanArrayExtra(RepetitionPeriodSettingActivity.REPETITION_PERIOD));
            this.mItemRepetition.setValue(((AlarmEditPresenter) this.mPresenter).formatWeekRepeat(this.mSelectedAlarm.getWeekRepeat()));
        }
    }

    private boolean isDataChanged() {
        return this.defaultAlarm == null || !this.mSelectedAlarm.toString().equals(this.defaultAlarm.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ido.life.base.BaseActivity
    public void saveData() {
        if (isDataChanged()) {
            sendCmd();
        } else {
            finish();
        }
    }

    @Override // com.ido.life.base.BaseActivity
    protected void sendCmd() {
        super.sendCmd();
        Intent intent = new Intent();
        intent.putExtra("alarm", this.mSelectedAlarm);
        setResult(88, intent);
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (isDataChanged()) {
            showDataChangedDialog();
        } else {
            super.onBackPressed();
        }
    }

    @OnClick({R.id.rtv_delete_alarm, R.id.item_repetition, R.id.item_alarm_name})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.item_alarm_name) {
            SingleTopIntent singleTopIntent = new SingleTopIntent(this, (Class<?>) AlarmTypeEditActivity.class);
            singleTopIntent.putExtra(AlarmTypeEditActivity.ALARM_TYPE, this.mSelectedAlarm.getAlarmType());
            startActivityForResult(singleTopIntent, 33);
        } else if (id == R.id.item_repetition) {
            SingleTopIntent singleTopIntent2 = new SingleTopIntent(this, (Class<?>) RepetitionPeriodSettingActivity.class);
            singleTopIntent2.putExtra(RepetitionPeriodSettingActivity.REPETITION_PERIOD, this.mSelectedAlarm.getWeekRepeat());
            startActivityForResult(singleTopIntent2, 66);
        } else {
            if (id != R.id.rtv_delete_alarm) {
                return;
            }
            setResult(88, new Intent());
            finish();
        }
    }
}