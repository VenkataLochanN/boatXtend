package com.ido.life.module.device.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.AlarmV3;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.ScreenUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.base.SingleTopIntent;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.module.device.presenter.AlarmV3EditPresenter;
import com.ido.life.util.TimeUtil;
import com.watch.life.wheelview.adapter.ArrayWheelAdapter;
import com.watch.life.wheelview.adapter.NumericWheelAdapter;
import com.watch.life.wheelview.listener.OnItemSelectedListener;
import com.watch.life.wheelview.view.WheelView;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmClockV3EditActivity extends BaseActivity<AlarmV3EditPresenter> {
    public static final String ALARM_KEY = "alarm";
    private AlarmV3 defaultAlarm;
    private boolean isAm;

    @BindView(R.id.item_alarm_name)
    CustomItemLabelView mItemAlarmName;

    @BindView(R.id.item_repetition)
    CustomItemLabelView mItemRepetition;

    @BindView(R.id.rtv_delete_alarm)
    RegularTextView mRtvDeleteAlarm;
    private AlarmV3 mSelectedAlarm;

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
        this.mSelectedAlarm = (AlarmV3) getIntent().getSerializableExtra("alarm");
        if (this.mSelectedAlarm == null) {
            this.mRtvDeleteAlarm.setVisibility(8);
            this.defaultAlarm = null;
            this.mSelectedAlarm = ((AlarmV3EditPresenter) this.mPresenter).initAlarm();
        } else {
            this.mRtvDeleteAlarm.setVisibility(0);
            this.defaultAlarm = ((AlarmV3EditPresenter) this.mPresenter).cloneAlarm(this.mSelectedAlarm);
        }
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.initLayout(1).setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmClockV3EditActivity$AgfxbBP0PbDvklESGVj-595pjec
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$AlarmClockV3EditActivity(view);
            }
        });
        initWheelView();
        this.mItemRepetition.setValue(((AlarmV3EditPresenter) this.mPresenter).formatWeekRepeat(this.mSelectedAlarm.getWeekRepeat()));
        this.mItemAlarmName.setValue(this.mSelectedAlarm.name);
    }

    public /* synthetic */ void lambda$initEvent$0$AlarmClockV3EditActivity(View view) {
        saveData();
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        int i = this.mSelectedAlarm.hour;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.timeLayout.getLayoutParams();
        if (((AlarmV3EditPresenter) this.mPresenter).isTimeFormat24()) {
            this.mWvHour.setCyclic(false);
            this.mWvAmPm.setVisibility(8);
            this.mWvHour.setAdapter(new NumericWheelAdapter(0, 23));
            this.mWvHour.setCurrentItem(i);
            layoutParams.width = (ScreenUtil.getScreenW() * 2) / 3;
        } else {
            this.mWvHour.setCyclic(true);
            this.mWvAmPm.setVisibility(0);
            this.isAm = (i > 0 && i < 12) || i == 24 || i == 0;
            this.mWvAmPm.setCurrentItem(!this.isAm ? 1 : 0);
            NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(1, 24);
            numericWheelAdapter.setLoop(true);
            this.mWvHour.setAdapter(numericWheelAdapter);
            this.mWvHour.setNumberCount(1);
            this.mWvHour.setCurrentItem(i != 0 ? i - 1 : 23);
            layoutParams.width = ScreenUtil.getScreenW();
        }
        this.timeLayout.setLayoutParams(layoutParams);
        this.mWvMin.setCurrentItem(this.mSelectedAlarm.minute);
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
        this.mWvHour.setItemsVisibleCount(5);
        this.mWvMin.setAdapter(new NumericWheelAdapter(0, 59));
        this.mWvMin.setCyclic(false);
        this.mWvMin.setItemsVisibleCount(5);
        this.mWvAmPm.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmClockV3EditActivity$rzy7tZYs-xZUijiebZXg5RzJ4Ww
            @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
            public final void onItemSelected(int i) {
                this.f$0.lambda$initWheelView$1$AlarmClockV3EditActivity(i);
            }
        });
        this.mWvHour.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmClockV3EditActivity$Vi9i-NE5buTLnvLSuQwT5JW_ROg
            @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
            public final void onItemSelected(int i) {
                this.f$0.lambda$initWheelView$2$AlarmClockV3EditActivity(i);
            }
        });
        this.mWvMin.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$AlarmClockV3EditActivity$7PW4y6Nhs5f9_OUMrSsqmDnzkK0
            @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
            public final void onItemSelected(int i) {
                this.f$0.lambda$initWheelView$3$AlarmClockV3EditActivity(i);
            }
        });
    }

    public /* synthetic */ void lambda$initWheelView$1$AlarmClockV3EditActivity(int i) {
        this.isAm = i == 0;
        if (!((AlarmV3EditPresenter) this.mPresenter).isTimeFormat24()) {
            int i2 = this.mSelectedAlarm.hour == 0 ? 24 : this.mSelectedAlarm.hour;
            if (i2 == 12 && this.isAm) {
                this.mWvHour.setCurrentItem(23);
                this.mSelectedAlarm.hour = 0;
                return;
            }
            if (i2 == 24 && !this.isAm) {
                this.mWvHour.setCurrentItem(11);
                this.mSelectedAlarm.hour = 12;
                return;
            } else {
                if (i2 == 12 || i2 == 24) {
                    return;
                }
                this.isAm = i == 0;
                AlarmV3 alarmV3 = this.mSelectedAlarm;
                alarmV3.hour = TimeUtil.formatHourByAm_or_pm(alarmV3.hour, this.isAm);
                this.mWvHour.setCurrentItem(this.mSelectedAlarm.hour - 1);
                return;
            }
        }
        AlarmV3 alarmV32 = this.mSelectedAlarm;
        alarmV32.hour = TimeUtil.formatHourByAm_or_pm(alarmV32.hour, this.isAm);
    }

    public /* synthetic */ void lambda$initWheelView$2$AlarmClockV3EditActivity(int i) {
        if (((AlarmV3EditPresenter) this.mPresenter).isTimeFormat24()) {
            this.mSelectedAlarm.hour = i;
        } else {
            int i2 = i + 1;
            if (i2 >= 12 && i2 < 24) {
                this.mWvAmPm.setCurrentItem(1);
                this.isAm = i2 == 12;
            } else {
                this.mWvAmPm.setCurrentItem(0);
                if (i2 != 24 && i2 >= 12) {
                    z = false;
                }
                this.isAm = z;
            }
            this.mSelectedAlarm.hour = TimeUtil.formatHourByAm_or_pm(i2, this.isAm);
        }
        CommonLogUtil.d("selectedHour = " + this.mSelectedAlarm.hour);
    }

    public /* synthetic */ void lambda$initWheelView$3$AlarmClockV3EditActivity(int i) {
        this.mSelectedAlarm.minute = i;
        CommonLogUtil.d("selectedMin = " + this.mSelectedAlarm.minute);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent == null) {
            return;
        }
        if (i2 == 22) {
            String stringExtra = intent.getStringExtra(AlarmNameEditActivity.ALARM_NAME);
            this.mItemAlarmName.setValue(stringExtra);
            this.mSelectedAlarm.name = stringExtra;
        } else {
            if (i2 != 66) {
                return;
            }
            this.mSelectedAlarm.setWeekRepeat(intent.getBooleanArrayExtra(RepetitionPeriodSettingActivity.REPETITION_PERIOD));
            this.mItemRepetition.setValue(((AlarmV3EditPresenter) this.mPresenter).formatWeekRepeat(this.mSelectedAlarm.getWeekRepeat()));
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

    @OnClick({R.id.rtv_delete_alarm, R.id.item_repetition, R.id.item_alarm_name})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.item_alarm_name) {
            SingleTopIntent singleTopIntent = new SingleTopIntent(this, (Class<?>) AlarmNameEditActivity.class);
            singleTopIntent.putExtra(AlarmNameEditActivity.ALARM_NAME, this.mSelectedAlarm.name);
            startActivityForResult(singleTopIntent, 22);
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