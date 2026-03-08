package com.ido.life.module.device.activity;

import android.content.Intent;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.ble.protocol.model.Menstrual;
import com.ido.ble.protocol.model.MenstrualRemind;
import com.ido.common.dialog.CommSelectDialogFragment;
import com.ido.common.dialog.TimeDialogFragment;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.util.DateUtil;
import com.ido.life.util.HealthCareUtil;
import com.ido.life.util.TimeUtil;

/* JADX INFO: loaded from: classes2.dex */
public class MenstrualRemindSetActivity extends BaseActivity implements TimeDialogFragment.OnItemSelectedListener, CommSelectDialogFragment.OnItemSelectedListener {
    private static final int ITEM_MENSTRUAL_REMIND = 0;
    private static final int ITEM_OVULATION_REMIND = 1;
    private String[] mAmOrPm;
    private String[] mDaysArr;

    @BindView(R.id.item_menstrual_remind)
    CustomItemLabelView mItemMenstrualRemind;

    @BindView(R.id.item_ovulation_remind)
    CustomItemLabelView mItemOvulationRemind;

    @BindView(R.id.item_remind_time)
    CustomItemLabelView mItemRemindTime;
    private Menstrual mMenstrual;
    private MenstrualRemind mMenstrualRemind;
    private String[] mMenstrualRemindDataArray;
    private int mMenstrualRemindDaysIndex;
    private String[] mOvulationRemindDataArray;
    private int mOvulationRemindDaysIndex;
    private int[] mRemindTime = new int[2];

    @BindView(R.id.rtv_menstruation_remind_tip)
    RegularTextView mRtvMenstruationRemindTip;

    @BindView(R.id.rtv_remind_time_tip)
    RegularTextView mRtvRemindTimeTip;
    private int mSelected;

    @BindView(R.id.tv_menstrual_date)
    RegularTextView mTvMenstrualDate;

    @BindView(R.id.tv_menstrual_detail)
    RegularTextView mTvMenstrualDetail;

    @BindView(R.id.tv_ovulation_date)
    RegularTextView mTvOvulationDate;

    @BindView(R.id.tv_ovulation_detail)
    RegularTextView mTvOvulationDetail;

    @Override // com.ido.common.base.BaseCoreActivity
    public int getLayoutResId() {
        return R.layout.activity_remind_set;
    }

    @Override // com.ido.life.base.BaseActivity
    protected boolean isFunctionActivity() {
        return true;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    public void initData() {
        this.mDaysArr = getResources().getStringArray(R.array.items_remind);
        initDialogDataArray();
        initDefaultData();
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.setLeftOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.activity.-$$Lambda$MenstrualRemindSetActivity$_OwvX69RpDwEftvmPyndnycQRSI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEvent$0$MenstrualRemindSetActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$initEvent$0$MenstrualRemindSetActivity(View view) {
        back2PreviousPage();
    }

    @Override // com.ido.life.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mItemMenstrualRemind.setValue(this.mMenstrualRemindDataArray[this.mMenstrualRemindDaysIndex]);
        this.mItemOvulationRemind.setValue(this.mOvulationRemindDataArray[this.mOvulationRemindDaysIndex]);
        setRemindTime();
    }

    @Override // com.ido.life.base.BaseActivity
    protected void initLabelLanguage() {
        super.initLabelLanguage();
        this.mTitleBar.setTitle(getLanguageText(R.string.device_remind_set));
        this.mAmOrPm = new String[]{getLanguageText(R.string.public_am), getLanguageText(R.string.public_pm)};
        this.mDaysArr[0] = getLanguageText(R.string.device_advance_one_day);
        this.mDaysArr[1] = getLanguageText(R.string.device_advance_two_day);
        this.mDaysArr[2] = getLanguageText(R.string.device_advance_three_day);
        this.mRtvMenstruationRemindTip.setText(getLanguageText(R.string.device_tip_menstruation_remind));
        this.mItemMenstrualRemind.setTitle(getLanguageText(R.string.device_menstrual_remind));
        this.mItemOvulationRemind.setTitle(getLanguageText(R.string.device_ovulation_remind));
        this.mRtvRemindTimeTip.setText(getLanguageText(R.string.device_tip_remind_time));
        this.mItemRemindTime.setTitle(getLanguageText(R.string.device_remind_the_time));
    }

    private void setRemindTime() {
        String strTimeFormatter;
        CustomItemLabelView customItemLabelView = this.mItemRemindTime;
        if (TimeUtil.is24Hour()) {
            int[] iArr = this.mRemindTime;
            strTimeFormatter = TimeUtil.timeFormatter(iArr[0], iArr[1], true, this.mAmOrPm);
        } else {
            int[] iArr2 = this.mRemindTime;
            strTimeFormatter = TimeUtil.timeFormatter(iArr2[0], iArr2[1], false, this.mAmOrPm);
        }
        customItemLabelView.setValue(strTimeFormatter);
    }

    private void initDefaultData() {
        this.mMenstrualRemind = (MenstrualRemind) getIntent().getSerializableExtra(HealthCareUtil.MENSTRUAL_REMIND);
        this.mMenstrual = (Menstrual) getIntent().getSerializableExtra(HealthCareUtil.MENSTRUAL);
        this.mMenstrualRemindDaysIndex = this.mMenstrualRemind.start_day - 1;
        int i = this.mMenstrualRemindDaysIndex;
        if (i < 0) {
            i = 0;
        }
        this.mMenstrualRemindDaysIndex = i;
        int length = this.mMenstrualRemindDaysIndex;
        String[] strArr = this.mMenstrualRemindDataArray;
        if (length > strArr.length - 1) {
            length = strArr.length - 1;
        }
        this.mMenstrualRemindDaysIndex = length;
        this.mOvulationRemindDaysIndex = this.mMenstrualRemind.ovulation_day - 1;
        int i2 = this.mOvulationRemindDaysIndex;
        if (i2 < 0) {
            i2 = 0;
        }
        this.mOvulationRemindDaysIndex = i2;
        int length2 = this.mOvulationRemindDaysIndex;
        String[] strArr2 = this.mOvulationRemindDataArray;
        if (length2 > strArr2.length - 1) {
            length2 = strArr2.length - 1;
        }
        this.mOvulationRemindDaysIndex = length2;
        this.mRemindTime[0] = this.mMenstrualRemind.hour;
        this.mRemindTime[1] = this.mMenstrualRemind.minute;
        refreshData(0);
    }

    private void initDialogDataArray() {
        String[] strArr = this.mDaysArr;
        this.mMenstrualRemindDataArray = strArr;
        this.mOvulationRemindDataArray = strArr;
    }

    private void refreshData(int i) {
        String str = this.mMenstrual.last_menstrual_year + "/" + this.mMenstrual.last_menstrual_month + "/" + this.mMenstrual.last_menstrual_day;
        String nextMenstrualRemindTime = HealthCareUtil.getNextMenstrualRemindTime(str, this.mRemindTime, this.mMenstrualRemind.start_day, this.mMenstrual.menstrual_cycle);
        String nextOvulationRemindTime = HealthCareUtil.getNextOvulationRemindTime(str, this.mRemindTime, this.mMenstrualRemind.ovulation_day, this.mMenstrual.menstrual_cycle);
        long timeInterval = DateUtil.getTimeInterval(nextMenstrualRemindTime, this.mMenstrual.menstrual_cycle, DateUtil.DATE_FORMAT_YMDHm);
        long timeInterval2 = DateUtil.getTimeInterval(nextOvulationRemindTime, this.mMenstrual.menstrual_cycle, DateUtil.DATE_FORMAT_YMDHm);
        if (i == 0) {
            this.mTvMenstrualDate.setText(DateUtil.getDateFormatMd(nextMenstrualRemindTime, "/"));
            this.mTvOvulationDate.setText(DateUtil.getDateFormatMd(nextOvulationRemindTime, "/"));
            setRemindDetail(this.mTvMenstrualDetail, timeInterval);
            if (this.mMenstrualRemind.ovulation_day <= 0) {
                this.mTvOvulationDetail.setText((CharSequence) null);
                return;
            } else {
                setRemindDetail(this.mTvOvulationDetail, timeInterval2);
                return;
            }
        }
        if (i == 100) {
            this.mTvMenstrualDate.setText(DateUtil.getDateFormatMd(nextMenstrualRemindTime, "/"));
            setRemindDetail(this.mTvMenstrualDetail, timeInterval);
        } else {
            if (i != 200) {
                return;
            }
            this.mTvOvulationDate.setText(DateUtil.getDateFormatMd(nextOvulationRemindTime, "/"));
            if (this.mMenstrualRemind.ovulation_day <= 0) {
                this.mTvOvulationDetail.setText((CharSequence) null);
            } else {
                setRemindDetail(this.mTvOvulationDetail, timeInterval2);
            }
        }
    }

    private void setRemindDetail(TextView textView, long j) {
        int[] intervalTypeAndValue = DateUtil.getIntervalTypeAndValue(j);
        int i = intervalTypeAndValue[0];
        if (i == 0) {
            textView.setText(String.format(getLanguageText(R.string.device_remind_after_days), Integer.valueOf(intervalTypeAndValue[1])));
        } else if (i == 1) {
            textView.setText(String.format(getLanguageText(R.string.device_remind_after_hours_android), Integer.valueOf(intervalTypeAndValue[1])));
        } else {
            if (i != 2) {
                return;
            }
            textView.setText(String.format(getLanguageText(R.string.device_remind_after_minutes_android), Integer.valueOf(intervalTypeAndValue[1])));
        }
    }

    private void back2PreviousPage() {
        Intent intent = new Intent();
        intent.putExtra(HealthCareUtil.MENSTRUAL_REMIND, this.mMenstrualRemind);
        setResult(200, intent);
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        back2PreviousPage();
    }

    @OnClick({R.id.item_menstrual_remind, R.id.layout_menstrual_remind, R.id.item_ovulation_remind, R.id.layout_ovulation_remind, R.id.item_remind_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.item_menstrual_remind /* 2131362434 */:
            case R.id.layout_menstrual_remind /* 2131362845 */:
                this.mSelected = 0;
                showAdvanceDaysPickerDialog(this.mMenstrualRemindDaysIndex);
                break;
            case R.id.item_ovulation_remind /* 2131362454 */:
            case R.id.layout_ovulation_remind /* 2131362850 */:
                this.mSelected = 1;
                showAdvanceDaysPickerDialog(this.mOvulationRemindDaysIndex);
                break;
            case R.id.item_remind_time /* 2131362469 */:
                int[] iArr = this.mRemindTime;
                showTimePickerDialog(iArr[0], iArr[1]);
                break;
        }
    }

    private void showAdvanceDaysPickerDialog(int i) {
        CommSelectDialogFragment commSelectDialogFragmentNewInstance = CommSelectDialogFragment.newInstance(this.mDaysArr, i);
        commSelectDialogFragmentNewInstance.show(getSupportFragmentManager());
        commSelectDialogFragmentNewInstance.setOnItemSelectedListener(this);
    }

    private void showTimePickerDialog(int i, int i2) {
        TimeDialogFragment timeDialogFragmentNewInstance;
        if (DateFormat.is24HourFormat(this)) {
            timeDialogFragmentNewInstance = TimeDialogFragment.newInstance(i, i2);
        } else {
            timeDialogFragmentNewInstance = TimeDialogFragment.newInstance(i, i2, DateFormat.is24HourFormat(this), this.mAmOrPm);
        }
        timeDialogFragmentNewInstance.show(getSupportFragmentManager());
        timeDialogFragmentNewInstance.setOnItemSelectedListener(this);
    }

    @Override // com.ido.common.dialog.TimeDialogFragment.OnItemSelectedListener
    public void onTimeSelected(int i, int i2, int i3) {
        MenstrualRemind menstrualRemind = this.mMenstrualRemind;
        menstrualRemind.hour = i2;
        menstrualRemind.minute = i3;
        this.mRemindTime[0] = menstrualRemind.hour;
        this.mRemindTime[1] = this.mMenstrualRemind.minute;
        setRemindTime();
        refreshData(0);
    }

    @Override // com.ido.common.dialog.CommSelectDialogFragment.OnItemSelectedListener
    public void onItemSelected(int i) {
        int i2 = this.mSelected;
        if (i2 == 0) {
            this.mMenstrualRemindDaysIndex = i;
            MenstrualRemind menstrualRemind = this.mMenstrualRemind;
            int i3 = this.mMenstrualRemindDaysIndex;
            menstrualRemind.start_day = i3 + 1;
            this.mItemMenstrualRemind.setValue(this.mDaysArr[i3]);
            refreshData(100);
            return;
        }
        if (i2 != 1) {
            return;
        }
        this.mOvulationRemindDaysIndex = i;
        MenstrualRemind menstrualRemind2 = this.mMenstrualRemind;
        int i4 = this.mOvulationRemindDaysIndex;
        menstrualRemind2.ovulation_day = i4 + 1;
        this.mItemOvulationRemind.setValue(this.mDaysArr[i4]);
        refreshData(200);
    }
}