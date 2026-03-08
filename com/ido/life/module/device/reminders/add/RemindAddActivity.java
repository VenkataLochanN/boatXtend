package com.ido.life.module.device.reminders.add;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.boat.Xtend.two.R;
import com.ido.ble.BLEManager;
import com.ido.ble.protocol.model.ScheduleReminderV3;
import com.ido.common.dialog.BirthdayDateDialogFragment;
import com.ido.common.dialog.TimeDialogFragment;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.widget.textview.RegularTextView;
import com.ido.life.base.BaseActivity;
import com.ido.life.customview.viewgroup.CommLoadingView;
import com.ido.life.customview.viewgroup.CustomItemLabelView;
import com.ido.life.util.DateUtil;
import com.veryfit.multi.nativeprotocol.b;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: RemindAddActivity.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0015\n\u0002\b\n\u0018\u0000 )2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0001)B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\r\u001a\u00020\fH\u0002J\b\u0010\u000e\u001a\u00020\u0007H\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0010H\u0014J\b\u0010\u0012\u001a\u00020\u0010H\u0002J\u0012\u0010\u0013\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0012\u0010\u0016\u001a\u00020\u00102\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0010H\u0016J\b\u0010\u001a\u001a\u00020\u0010H\u0016J \u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u0007H\u0016J&\u0010\u001b\u001a\u00020\u00102\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010 2\b\u0010\"\u001a\u0004\u0018\u00010 H\u0016J\u0018\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0007H\u0002J\u0018\u0010&\u001a\u00020\n2\u0006\u0010'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u0007H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/ido/life/module/device/reminders/add/RemindAddActivity;", "Lcom/ido/life/base/BaseActivity;", "Lcom/ido/life/module/device/reminders/add/RemindAddPresenter;", "Lcom/ido/life/module/device/reminders/add/IRemindAddView;", "Landroid/view/View$OnClickListener;", "()V", "MAX_LENGTH_DES", "", "MAX_NOTE_LENGTH", "mIsAdd", "", "mScheduleReminderV3", "Lcom/ido/ble/protocol/model/ScheduleReminderV3;", "getDefaultTime", "getLayoutResId", "initData", "", "initEvent", "initReminder", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSetEventReminderFailed", "onSetEventReminderSuccess", "setBirthday", "year", "month", "day", "startDate", "", "endDate", "selectedDate", "showTimePickerDialog", "startHour", "startMinute", "validRemindDateTime", "hour", "minute", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class RemindAddActivity extends BaseActivity<RemindAddPresenter> implements IRemindAddView, View.OnClickListener {
    public static final String EXTRA_SCHEDULE_ID = "schedule_id";
    public static final String EXTRA_SCHEDULE_REMINDER = "schedule_reminder";
    public static final String RESULT_SCHEDULE = "schedule_remindr";
    private static final String TAG = "RemindAddActivity";
    private final int MAX_LENGTH_DES = 75;
    private final int MAX_NOTE_LENGTH = b.Y;
    private HashMap _$_findViewCache;
    private boolean mIsAdd;
    private ScheduleReminderV3 mScheduleReminderV3;

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected int getLayoutResId() {
        return R.layout.activity_remind_add;
    }

    public static final /* synthetic */ RemindAddPresenter access$getMPresenter$p(RemindAddActivity remindAddActivity) {
        return (RemindAddPresenter) remindAddActivity.mPresenter;
    }

    @Override // com.ido.common.base.BaseCoreActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        Bundle extras = intent.getExtras();
        this.mScheduleReminderV3 = new ScheduleReminderV3();
        if (extras != null) {
            if (extras.getSerializable(EXTRA_SCHEDULE_REMINDER) != null) {
                Serializable serializable = extras.getSerializable(EXTRA_SCHEDULE_REMINDER);
                if (serializable == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.ido.ble.protocol.model.ScheduleReminderV3");
                }
                this.mScheduleReminderV3 = (ScheduleReminderV3) serializable;
            } else {
                int i = extras.getInt(EXTRA_SCHEDULE_ID, 0);
                ScheduleReminderV3 scheduleReminderV3 = this.mScheduleReminderV3;
                if (scheduleReminderV3 != null) {
                    scheduleReminderV3.setId(i);
                }
                this.mIsAdd = true;
            }
        }
        initReminder();
    }

    private final void initReminder() {
        ScheduleReminderV3 scheduleReminderV3 = this.mScheduleReminderV3;
        String timeByTimeMode = null;
        if (TextUtils.isEmpty(scheduleReminderV3 != null ? scheduleReminderV3.getTitle() : null)) {
            EditText et_title_des = (EditText) _$_findCachedViewById(com.ido.life.R.id.et_title_des);
            Intrinsics.checkExpressionValueIsNotNull(et_title_des, "et_title_des");
            et_title_des.setHint(LanguageUtil.getLanguageText(R.string.device_remind_title_hint));
        } else {
            EditText editText = (EditText) _$_findCachedViewById(com.ido.life.R.id.et_title_des);
            ScheduleReminderV3 scheduleReminderV32 = this.mScheduleReminderV3;
            editText.setText(scheduleReminderV32 != null ? scheduleReminderV32.getTitle() : null);
        }
        ScheduleReminderV3 scheduleReminderV33 = this.mScheduleReminderV3;
        if (TextUtils.isEmpty(scheduleReminderV33 != null ? scheduleReminderV33.getNote() : null)) {
            EditText et_note = (EditText) _$_findCachedViewById(com.ido.life.R.id.et_note);
            Intrinsics.checkExpressionValueIsNotNull(et_note, "et_note");
            et_note.setHint(LanguageUtil.getLanguageText(R.string.device_reminders_remarks));
        } else {
            EditText editText2 = (EditText) _$_findCachedViewById(com.ido.life.R.id.et_note);
            ScheduleReminderV3 scheduleReminderV34 = this.mScheduleReminderV3;
            editText2.setText(scheduleReminderV34 != null ? scheduleReminderV34.getNote() : null);
        }
        ScheduleReminderV3 defaultTime = getDefaultTime();
        ScheduleReminderV3 scheduleReminderV35 = this.mScheduleReminderV3;
        if (scheduleReminderV35 != null && scheduleReminderV35.getYear() == 0) {
            ScheduleReminderV3 scheduleReminderV36 = this.mScheduleReminderV3;
            if (scheduleReminderV36 != null) {
                scheduleReminderV36.setYear(defaultTime.getYear());
            }
            ScheduleReminderV3 scheduleReminderV37 = this.mScheduleReminderV3;
            if (scheduleReminderV37 != null) {
                scheduleReminderV37.setMon(defaultTime.getMon());
            }
            ScheduleReminderV3 scheduleReminderV38 = this.mScheduleReminderV3;
            if (scheduleReminderV38 != null) {
                scheduleReminderV38.setDay(defaultTime.getDay());
            }
        }
        CustomItemLabelView customItemLabelView = (CustomItemLabelView) _$_findCachedViewById(com.ido.life.R.id.item_remind_date);
        String languageText = getLanguageText(R.string.date_format);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string.date_format)");
        Object[] objArr = new Object[3];
        ScheduleReminderV3 scheduleReminderV39 = this.mScheduleReminderV3;
        objArr[0] = String.valueOf(scheduleReminderV39 != null ? Integer.valueOf(scheduleReminderV39.getYear()) : null);
        ScheduleReminderV3 scheduleReminderV310 = this.mScheduleReminderV3;
        objArr[1] = scheduleReminderV310 != null ? Integer.valueOf(scheduleReminderV310.getMon()) : null;
        ScheduleReminderV3 scheduleReminderV311 = this.mScheduleReminderV3;
        objArr[2] = scheduleReminderV311 != null ? Integer.valueOf(scheduleReminderV311.getDay()) : null;
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        customItemLabelView.setValue(str);
        ScheduleReminderV3 scheduleReminderV312 = this.mScheduleReminderV3;
        if (scheduleReminderV312 != null && scheduleReminderV312.getRemind_on_off() == 0) {
            ScheduleReminderV3 scheduleReminderV313 = this.mScheduleReminderV3;
            if (scheduleReminderV313 != null) {
                scheduleReminderV313.setHour(defaultTime.getHour());
            }
            ScheduleReminderV3 scheduleReminderV314 = this.mScheduleReminderV3;
            if (scheduleReminderV314 != null) {
                scheduleReminderV314.setMin(defaultTime.getMin());
            }
        }
        CustomItemLabelView customItemLabelView2 = (CustomItemLabelView) _$_findCachedViewById(com.ido.life.R.id.item_remind_time);
        ScheduleReminderV3 scheduleReminderV315 = this.mScheduleReminderV3;
        if (scheduleReminderV315 != null) {
            int hour = scheduleReminderV315.getHour();
            ScheduleReminderV3 scheduleReminderV316 = this.mScheduleReminderV3;
            if (scheduleReminderV316 != null) {
                timeByTimeMode = ((RemindAddPresenter) this.mPresenter).formatTimeByTimeMode(hour, scheduleReminderV316.getMin());
            }
        }
        customItemLabelView2.setValue(timeByTimeMode);
    }

    private final ScheduleReminderV3 getDefaultTime() {
        Calendar calendar = Calendar.getInstance();
        ScheduleReminderV3 scheduleReminderV3 = new ScheduleReminderV3();
        calendar.set(12, calendar.get(12) + 30);
        int i = calendar.get(12);
        if (i <= 30) {
            scheduleReminderV3.setYear(calendar.get(1));
            scheduleReminderV3.setMon(calendar.get(2) + 1);
            scheduleReminderV3.setDay(calendar.get(5));
            scheduleReminderV3.setHour(calendar.get(11));
            scheduleReminderV3.setMin(30);
        } else {
            calendar.set(12, i + 30);
            scheduleReminderV3.setYear(calendar.get(1));
            scheduleReminderV3.setMon(calendar.get(2) + 1);
            scheduleReminderV3.setDay(calendar.get(5));
            scheduleReminderV3.setHour(calendar.get(11));
            scheduleReminderV3.setMin(0);
        }
        return scheduleReminderV3;
    }

    @Override // com.ido.common.base.BaseCoreActivity
    protected void initEvent() {
        super.initEvent();
        this.mTitleBar.initLayout(2).setRightEnable(!this.mIsAdd);
        if (this.mIsAdd) {
            this.mTitleBar.setRightTextColor(getResources().getColor(R.color.com_color_white_50));
        } else {
            this.mTitleBar.setRightTextColor(getResources().getColor(R.color.white));
        }
        this.mTitleBar.setTitle(LanguageUtil.getLanguageText(R.string.device_reminders_title));
        this.mTitleBar.setRightText(LanguageUtil.getLanguageText(R.string.mine_complete));
        this.mTitleBar.setRightOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.reminders.add.RemindAddActivity.initEvent.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (!BLEManager.isConnected()) {
                    RemindAddActivity remindAddActivity = RemindAddActivity.this;
                    remindAddActivity.showToast(remindAddActivity.getLanguageText(R.string.me_disconnect));
                }
                ScheduleReminderV3 scheduleReminderV3 = RemindAddActivity.this.mScheduleReminderV3;
                if (scheduleReminderV3 != null) {
                    if (RemindAddActivity.this.validRemindDateTime(scheduleReminderV3.getHour(), scheduleReminderV3.getMin())) {
                        RemindAddActivity.this.showLoadingDialog();
                        RemindAddActivity.access$getMPresenter$p(RemindAddActivity.this).addRemind(scheduleReminderV3, RemindAddActivity.this.mIsAdd);
                    } else {
                        RemindAddActivity remindAddActivity2 = RemindAddActivity.this;
                        remindAddActivity2.showToast(remindAddActivity2.getLanguageText(R.string.invalid_date_time));
                    }
                }
            }
        });
        this.mTitleBar.setLeftTextOnClick(new View.OnClickListener() { // from class: com.ido.life.module.device.reminders.add.RemindAddActivity.initEvent.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RemindAddActivity.this.finish();
            }
        });
        RemindAddActivity remindAddActivity = this;
        ((CustomItemLabelView) _$_findCachedViewById(com.ido.life.R.id.item_remind_date)).setOnClickListener(remindAddActivity);
        ((CustomItemLabelView) _$_findCachedViewById(com.ido.life.R.id.item_remind_time)).setOnClickListener(remindAddActivity);
        RegularTextView tv_title_length = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_title_length);
        Intrinsics.checkExpressionValueIsNotNull(tv_title_length, "tv_title_length");
        StringBuilder sb = new StringBuilder();
        EditText et_title_des = (EditText) _$_findCachedViewById(com.ido.life.R.id.et_title_des);
        Intrinsics.checkExpressionValueIsNotNull(et_title_des, "et_title_des");
        sb.append(String.valueOf(et_title_des.getText().toString().length()));
        sb.append("/");
        sb.append(this.MAX_LENGTH_DES);
        tv_title_length.setText(sb.toString());
        EditText et_title_des2 = (EditText) _$_findCachedViewById(com.ido.life.R.id.et_title_des);
        Intrinsics.checkExpressionValueIsNotNull(et_title_des2, "et_title_des");
        et_title_des2.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(this.MAX_LENGTH_DES)});
        ((EditText) _$_findCachedViewById(com.ido.life.R.id.et_title_des)).addTextChangedListener(new TextWatcher() { // from class: com.ido.life.module.device.reminders.add.RemindAddActivity.initEvent.3
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Intrinsics.checkParameterIsNotNull(s, "s");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Intrinsics.checkParameterIsNotNull(s, "s");
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                Intrinsics.checkParameterIsNotNull(s, "s");
                if (s.length() >= RemindAddActivity.this.MAX_LENGTH_DES) {
                    CommonLogUtil.d("超过要求长度");
                }
                String str = String.valueOf(s.length()) + "/" + RemindAddActivity.this.MAX_LENGTH_DES;
                RegularTextView tv_title_length2 = (RegularTextView) RemindAddActivity.this._$_findCachedViewById(com.ido.life.R.id.tv_title_length);
                Intrinsics.checkExpressionValueIsNotNull(tv_title_length2, "tv_title_length");
                tv_title_length2.setText(str);
                ScheduleReminderV3 scheduleReminderV3 = RemindAddActivity.this.mScheduleReminderV3;
                if (scheduleReminderV3 != null) {
                    scheduleReminderV3.setTitle(s.toString());
                }
                if (TextUtils.isEmpty(s)) {
                    RemindAddActivity.this.mTitleBar.setRightTextColor(RemindAddActivity.this.getResources().getColor(R.color.com_color_white_50));
                } else {
                    RemindAddActivity.this.mTitleBar.setRightTextColor(RemindAddActivity.this.getResources().getColor(R.color.white));
                }
                RemindAddActivity.this.mTitleBar.setRightEnable(!TextUtils.isEmpty(r4));
                RemindAddPresenter remindAddPresenterAccess$getMPresenter$p = RemindAddActivity.access$getMPresenter$p(RemindAddActivity.this);
                if (remindAddPresenterAccess$getMPresenter$p != null) {
                    remindAddPresenterAccess$getMPresenter$p.verify();
                }
            }
        });
        RegularTextView tv_note_length = (RegularTextView) _$_findCachedViewById(com.ido.life.R.id.tv_note_length);
        Intrinsics.checkExpressionValueIsNotNull(tv_note_length, "tv_note_length");
        StringBuilder sb2 = new StringBuilder();
        EditText et_note = (EditText) _$_findCachedViewById(com.ido.life.R.id.et_note);
        Intrinsics.checkExpressionValueIsNotNull(et_note, "et_note");
        sb2.append(String.valueOf(et_note.getText().toString().length()));
        sb2.append("/");
        sb2.append(this.MAX_NOTE_LENGTH);
        tv_note_length.setText(sb2.toString());
        EditText et_note2 = (EditText) _$_findCachedViewById(com.ido.life.R.id.et_note);
        Intrinsics.checkExpressionValueIsNotNull(et_note2, "et_note");
        et_note2.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(this.MAX_NOTE_LENGTH)});
        ((EditText) _$_findCachedViewById(com.ido.life.R.id.et_note)).addTextChangedListener(new TextWatcher() { // from class: com.ido.life.module.device.reminders.add.RemindAddActivity.initEvent.4
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Intrinsics.checkParameterIsNotNull(s, "s");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Intrinsics.checkParameterIsNotNull(s, "s");
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                Intrinsics.checkParameterIsNotNull(s, "s");
                String str = String.valueOf(s.length()) + "/" + RemindAddActivity.this.MAX_NOTE_LENGTH;
                RegularTextView tv_note_length2 = (RegularTextView) RemindAddActivity.this._$_findCachedViewById(com.ido.life.R.id.tv_note_length);
                Intrinsics.checkExpressionValueIsNotNull(tv_note_length2, "tv_note_length");
                tv_note_length2.setText(str);
                ScheduleReminderV3 scheduleReminderV3 = RemindAddActivity.this.mScheduleReminderV3;
                if (scheduleReminderV3 != null) {
                    scheduleReminderV3.setNote(s.toString());
                }
            }
        });
    }

    @Override // com.ido.life.module.device.reminders.add.IRemindAddView
    public void setBirthday(int[] startDate, int[] endDate, int[] selectedDate) {
        BirthdayDateDialogFragment birthdayDateDialogFragmentNewInstance = BirthdayDateDialogFragment.newInstance(startDate, endDate, selectedDate);
        birthdayDateDialogFragmentNewInstance.setOnDateSelectedListener(new BirthdayDateDialogFragment.OnDateSelectedListener() { // from class: com.ido.life.module.device.reminders.add.RemindAddActivity.setBirthday.1
            @Override // com.ido.common.dialog.BirthdayDateDialogFragment.OnDateSelectedListener
            public final void onDateSelected(int i, int i2, int i3) {
                Calendar calendar = Calendar.getInstance(Locale.getDefault());
                calendar.set(1, i);
                calendar.set(2, i2);
                calendar.add(2, -1);
                calendar.set(5, i3);
                RemindAddActivity.this.setBirthday(i, i2, i3);
            }
        });
        birthdayDateDialogFragmentNewInstance.show(getSupportFragmentManager());
    }

    @Override // com.ido.life.module.device.reminders.add.IRemindAddView
    public void setBirthday(int year, int month, int day) {
        ScheduleReminderV3 scheduleReminderV3 = this.mScheduleReminderV3;
        if (scheduleReminderV3 != null) {
            scheduleReminderV3.setYear(year);
        }
        ScheduleReminderV3 scheduleReminderV32 = this.mScheduleReminderV3;
        if (scheduleReminderV32 != null) {
            scheduleReminderV32.setMon(month);
        }
        ScheduleReminderV3 scheduleReminderV33 = this.mScheduleReminderV3;
        if (scheduleReminderV33 != null) {
            scheduleReminderV33.setDay(day);
        }
        CustomItemLabelView customItemLabelView = (CustomItemLabelView) _$_findCachedViewById(com.ido.life.R.id.item_remind_date);
        String languageText = getLanguageText(R.string.date_format);
        Intrinsics.checkExpressionValueIsNotNull(languageText, "getLanguageText(R.string.date_format)");
        Object[] objArr = {Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day)};
        String str = String.format(languageText, Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        customItemLabelView.setValue(str);
    }

    @Override // com.ido.life.module.device.reminders.add.IRemindAddView
    public void onSetEventReminderSuccess() {
        dismissLoadingDialog();
        CommLoadingView comm_loading_view = (CommLoadingView) _$_findCachedViewById(com.ido.life.R.id.comm_loading_view);
        Intrinsics.checkExpressionValueIsNotNull(comm_loading_view, "comm_loading_view");
        comm_loading_view.setVisibility(8);
        Intent intent = new Intent(this, (Class<?>) RemindAddActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(RESULT_SCHEDULE, this.mScheduleReminderV3);
        bundle.putBoolean("isAdd", this.mIsAdd);
        intent.putExtras(bundle);
        setResult(-1, intent);
        finish();
    }

    @Override // com.ido.life.module.device.reminders.add.IRemindAddView
    public void onSetEventReminderFailed() {
        CommLoadingView comm_loading_view = (CommLoadingView) _$_findCachedViewById(com.ido.life.R.id.comm_loading_view);
        Intrinsics.checkExpressionValueIsNotNull(comm_loading_view, "comm_loading_view");
        comm_loading_view.setVisibility(8);
        dismissLoadingDialog();
        showCmdResultToast(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        Integer numValueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (numValueOf != null && numValueOf.intValue() == R.id.item_remind_date) {
            ScheduleReminderV3 scheduleReminderV3 = this.mScheduleReminderV3;
            Integer numValueOf2 = scheduleReminderV3 != null ? Integer.valueOf(scheduleReminderV3.getYear()) : null;
            ScheduleReminderV3 scheduleReminderV32 = this.mScheduleReminderV3;
            Integer numValueOf3 = scheduleReminderV32 != null ? Integer.valueOf(scheduleReminderV32.getMon()) : null;
            ScheduleReminderV3 scheduleReminderV33 = this.mScheduleReminderV3;
            Integer numValueOf4 = scheduleReminderV33 != null ? Integer.valueOf(scheduleReminderV33.getDay()) : null;
            RemindAddPresenter remindAddPresenter = (RemindAddPresenter) this.mPresenter;
            StringBuilder sb = new StringBuilder();
            sb.append(numValueOf2);
            sb.append(IOUtils.DIR_SEPARATOR_UNIX);
            sb.append(numValueOf3);
            sb.append(IOUtils.DIR_SEPARATOR_UNIX);
            sb.append(numValueOf4);
            remindAddPresenter.showCurrentBirthday(sb.toString());
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == R.id.item_remind_time) {
            ScheduleReminderV3 scheduleReminderV34 = this.mScheduleReminderV3;
            if (scheduleReminderV34 == null) {
                Intrinsics.throwNpe();
            }
            int hour = scheduleReminderV34.getHour();
            ScheduleReminderV3 scheduleReminderV35 = this.mScheduleReminderV3;
            if (scheduleReminderV35 == null) {
                Intrinsics.throwNpe();
            }
            showTimePickerDialog(hour, scheduleReminderV35.getMin());
        }
    }

    private final void showTimePickerDialog(int startHour, int startMinute) {
        TimeDialogFragment newInstance;
        if (((RemindAddPresenter) this.mPresenter).isTimeFormat24()) {
            newInstance = TimeDialogFragment.newInstance(startHour, startMinute);
            Intrinsics.checkExpressionValueIsNotNull(newInstance, "TimeDialogFragment.newIn…e(startHour, startMinute)");
        } else {
            P mPresenter = this.mPresenter;
            Intrinsics.checkExpressionValueIsNotNull(mPresenter, "mPresenter");
            newInstance = TimeDialogFragment.newInstance(startHour, startMinute, ((RemindAddPresenter) mPresenter).isTimeFormat24(), new String[]{getLanguageText(R.string.public_am), getLanguageText(R.string.public_pm)});
            Intrinsics.checkExpressionValueIsNotNull(newInstance, "newInstance");
        }
        newInstance.show(getSupportFragmentManager());
        newInstance.setOnItemSelectedListener(new TimeDialogFragment.OnItemSelectedListener() { // from class: com.ido.life.module.device.reminders.add.RemindAddActivity.showTimePickerDialog.1
            @Override // com.ido.common.dialog.TimeDialogFragment.OnItemSelectedListener
            public final void onTimeSelected(int i, int i2, int i3) {
                if (RemindAddActivity.this.mScheduleReminderV3 != null) {
                    if (RemindAddActivity.this.validRemindDateTime(i2, i3)) {
                        ScheduleReminderV3 scheduleReminderV3 = RemindAddActivity.this.mScheduleReminderV3;
                        if (scheduleReminderV3 != null) {
                            scheduleReminderV3.setHour(i2);
                        }
                        ScheduleReminderV3 scheduleReminderV32 = RemindAddActivity.this.mScheduleReminderV3;
                        if (scheduleReminderV32 != null) {
                            scheduleReminderV32.setMin(i3);
                        }
                        ((CustomItemLabelView) RemindAddActivity.this._$_findCachedViewById(com.ido.life.R.id.item_remind_time)).setValue(RemindAddActivity.access$getMPresenter$p(RemindAddActivity.this).formatTimeByTimeMode(i2, i3));
                        return;
                    }
                    RemindAddActivity remindAddActivity = RemindAddActivity.this;
                    remindAddActivity.showToast(remindAddActivity.getLanguageText(R.string.invalid_date_time));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean validRemindDateTime(int hour, int minute) {
        ScheduleReminderV3 scheduleReminderV3 = this.mScheduleReminderV3;
        if (scheduleReminderV3 == null) {
            Intrinsics.throwNpe();
        }
        int year = scheduleReminderV3.getYear();
        ScheduleReminderV3 scheduleReminderV32 = this.mScheduleReminderV3;
        if (scheduleReminderV32 == null) {
            Intrinsics.throwNpe();
        }
        int mon = scheduleReminderV32.getMon();
        if (this.mScheduleReminderV3 == null) {
            Intrinsics.throwNpe();
        }
        return !DateUtil.isExpire(DateUtil.parse(DateUtil.format(year, mon, r0.getDay(), hour, minute, 0), "yyyy-MM-dd HH:mm:ss"));
    }
}