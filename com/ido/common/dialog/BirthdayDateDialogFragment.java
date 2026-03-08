package com.ido.common.dialog;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.ido.common.R;
import com.ido.common.R2;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.ResourceUtil;
import com.watch.life.wheelview.adapter.NumericWheelAdapter;
import com.watch.life.wheelview.listener.OnItemSelectedListener;
import com.watch.life.wheelview.view.WheelView;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class BirthdayDateDialogFragment extends BaseDialogFragment {
    private static final String END_DATE = "end_date";
    private static final String LIMIT_AGE = "limit_age";
    private static final String SELECTED_DATE = "selected_date";
    private static final String START_DATE = "start_date";
    private static final String TAG = "BirthdayDateDialogFragment";
    private static final String TEXT_DESC = "desc_text";
    private int[] mEndDate;
    private OnDateSelectedListener mOnDateSelectedListener;
    private int[] mSelectedDate;
    private int[] mStartDate;

    @BindView(R2.id.tv_cancel)
    TextView mTvCancel;

    @BindView(R2.id.tv_confirm)
    TextView mTvConfirm;

    @BindView(R2.id.tv_desc)
    TextView mTvDesc;

    @BindView(R2.id.wheel_day)
    WheelView mWheelDay;

    @BindView(R2.id.wheel_month)
    WheelView mWheelMonth;

    @BindView(R2.id.wheel_year)
    WheelView mWheelYear;
    private int mPreSelectedYear = 0;
    private int mPreSelectedMonth = 0;
    private boolean mLimitAge = true;

    public interface OnDateSelectedListener {
        void onDateSelected(int i, int i2, int i3);
    }

    public void setOnDateSelectedListener(OnDateSelectedListener onDateSelectedListener) {
        this.mOnDateSelectedListener = onDateSelectedListener;
    }

    public static BirthdayDateDialogFragment newInstance(int[] iArr, int[] iArr2) {
        BirthdayDateDialogFragment birthdayDateDialogFragment = new BirthdayDateDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putIntArray("start_date", iArr);
        bundle.putIntArray(SELECTED_DATE, iArr2);
        birthdayDateDialogFragment.setArguments(bundle);
        birthdayDateDialogFragment.setStyle(1, R.style.AlertDialog_Dark);
        return birthdayDateDialogFragment;
    }

    public static BirthdayDateDialogFragment newInstance(int[] iArr, int[] iArr2, String str) {
        BirthdayDateDialogFragment birthdayDateDialogFragment = new BirthdayDateDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putIntArray("start_date", iArr);
        bundle.putIntArray(SELECTED_DATE, iArr2);
        bundle.putString(TEXT_DESC, str);
        birthdayDateDialogFragment.setArguments(bundle);
        birthdayDateDialogFragment.setStyle(1, R.style.AlertDialog_Dark);
        return birthdayDateDialogFragment;
    }

    public static BirthdayDateDialogFragment newInstance(int[] iArr, int[] iArr2, int[] iArr3) {
        BirthdayDateDialogFragment birthdayDateDialogFragment = new BirthdayDateDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putIntArray("start_date", iArr);
        bundle.putIntArray("end_date", iArr2);
        bundle.putIntArray(SELECTED_DATE, iArr3);
        birthdayDateDialogFragment.setArguments(bundle);
        birthdayDateDialogFragment.setStyle(1, R.style.AlertDialog_Dark);
        return birthdayDateDialogFragment;
    }

    public static BirthdayDateDialogFragment newInstance(int[] iArr, int[] iArr2, int[] iArr3, String str, boolean z) {
        BirthdayDateDialogFragment birthdayDateDialogFragment = new BirthdayDateDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putIntArray("start_date", iArr);
        bundle.putIntArray("end_date", iArr2);
        bundle.putIntArray(SELECTED_DATE, iArr3);
        bundle.putString(TEXT_DESC, str);
        bundle.putBoolean(LIMIT_AGE, z);
        birthdayDateDialogFragment.setArguments(bundle);
        birthdayDateDialogFragment.setStyle(1, R.style.AlertDialog_Dark);
        return birthdayDateDialogFragment;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        super.initUI(view);
        if (getDialog() != null && getDialog().getWindow() != null) {
            Window window = getDialog().getWindow();
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 80;
            attributes.width = -1;
            window.setSoftInputMode(18);
            window.setAttributes(attributes);
        }
        this.mWheelYear.setTextColorCenter(ResourceUtil.getColor(R.color.color_E51C23));
        this.mWheelYear.setTextColorOut(ResourceUtil.getColor(R.color.white));
        this.mWheelMonth.setTextColorCenter(ResourceUtil.getColor(R.color.color_E51C23));
        this.mWheelMonth.setTextColorOut(ResourceUtil.getColor(R.color.white));
        this.mWheelDay.setTextColorCenter(ResourceUtil.getColor(R.color.color_E51C23));
        this.mWheelDay.setTextColorOut(ResourceUtil.getColor(R.color.white));
        this.mTvCancel.setTextColor(ResourceUtil.getColor(R.color.color_E51C23));
        this.mTvConfirm.setTextColor(ResourceUtil.getColor(R.color.color_E51C23));
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        super.initData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mStartDate = arguments.getIntArray("start_date");
            this.mEndDate = arguments.getIntArray("end_date");
            this.mSelectedDate = arguments.getIntArray(SELECTED_DATE);
            this.mTvDesc.setText(arguments.getString(TEXT_DESC, ""));
            this.mLimitAge = arguments.getBoolean(LIMIT_AGE, this.mLimitAge);
        }
        int[] iArr = this.mEndDate;
        if (iArr == null || iArr.length != 3) {
            Calendar calendar = Calendar.getInstance();
            if (this.mLimitAge) {
                this.mEndDate = new int[]{calendar.get(1) - 16, calendar.get(2) + 1, calendar.get(5)};
            } else {
                this.mEndDate = new int[]{calendar.get(1), calendar.get(2) + 1, calendar.get(5)};
            }
        }
        int[] iArr2 = this.mStartDate;
        if (iArr2 == null || iArr2.length != 3) {
            if (this.mLimitAge) {
                int[] iArr3 = this.mEndDate;
                this.mStartDate = new int[]{iArr3[0] - 84, iArr3[1], iArr3[2]};
            } else {
                int[] iArr4 = this.mEndDate;
                this.mStartDate = new int[]{iArr4[0] - 100, iArr4[1], iArr4[2]};
            }
        }
        int[] iArr5 = this.mSelectedDate;
        if (iArr5 == null || iArr5.length != 3) {
            int[] iArr6 = this.mStartDate;
            this.mSelectedDate = new int[]{iArr6[0], iArr6[1], iArr6[2]};
        }
        initTimePicker();
    }

    private void initTimePicker() {
        initYearAndMonth();
        this.mWheelYear.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.common.dialog.-$$Lambda$BirthdayDateDialogFragment$tKcTx8sEt_G0mS946CRAmr3hZ1E
            @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
            public final void onItemSelected(int i) {
                this.f$0.lambda$initTimePicker$0$BirthdayDateDialogFragment(i);
            }
        });
        this.mWheelMonth.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.common.dialog.-$$Lambda$BirthdayDateDialogFragment$cMGRhaA69WOs6gBEOs4dW9iW5lU
            @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
            public final void onItemSelected(int i) {
                this.f$0.lambda$initTimePicker$1$BirthdayDateDialogFragment(i);
            }
        });
    }

    public /* synthetic */ void lambda$initTimePicker$0$BirthdayDateDialogFragment(int i) {
        int currentItem;
        int i2;
        int[] iArr = this.mStartDate;
        int actualMaximum = 0;
        int i3 = i + iArr[0];
        if (this.mPreSelectedYear == iArr[0] && this.mPreSelectedMonth == iArr[1]) {
            currentItem = this.mWheelDay.getCurrentItem() + this.mStartDate[0];
        } else {
            currentItem = this.mWheelDay.getCurrentItem() + 1;
        }
        int i4 = 12;
        int[] iArr2 = this.mStartDate;
        if (i3 == iArr2[0]) {
            this.mPreSelectedMonth = Math.max(this.mPreSelectedMonth, iArr2[1]);
            i2 = this.mStartDate[1];
        } else {
            i2 = 1;
        }
        int[] iArr3 = this.mEndDate;
        if (i3 == iArr3[0]) {
            this.mPreSelectedMonth = Math.min(this.mPreSelectedMonth, iArr3[1]);
            i4 = this.mEndDate[1];
        }
        this.mWheelMonth.setAdapter(new NumericWheelAdapter(i2, i4));
        this.mWheelMonth.setCurrentItem(this.mPreSelectedMonth - i2);
        this.mPreSelectedYear = i3;
        int i5 = this.mPreSelectedYear;
        int[] iArr4 = this.mStartDate;
        int i6 = (i5 == iArr4[0] && this.mPreSelectedMonth == iArr4[1]) ? iArr4[2] : 1;
        int i7 = this.mPreSelectedYear;
        int[] iArr5 = this.mEndDate;
        if (i7 == iArr5[0] && this.mPreSelectedMonth == iArr5[1]) {
            actualMaximum = iArr5[2];
        }
        if (actualMaximum == 0) {
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            calendar.set(1, i3);
            calendar.set(2, this.mPreSelectedMonth);
            calendar.add(2, -1);
            actualMaximum = calendar.getActualMaximum(5);
        }
        this.mWheelDay.setAdapter(new NumericWheelAdapter(i6, actualMaximum));
        this.mWheelDay.setCurrentItem(currentItem - i6);
    }

    public /* synthetic */ void lambda$initTimePicker$1$BirthdayDateDialogFragment(int i) {
        int currentItem;
        int i2 = this.mPreSelectedYear;
        int[] iArr = this.mStartDate;
        int actualMaximum = 0;
        int i3 = i2 == iArr[0] ? i + iArr[1] : i + 1;
        int i4 = this.mPreSelectedYear;
        int[] iArr2 = this.mStartDate;
        if (i4 == iArr2[0] && this.mPreSelectedMonth == iArr2[1]) {
            currentItem = this.mWheelDay.getCurrentItem() + this.mStartDate[2];
        } else {
            currentItem = this.mWheelDay.getCurrentItem() + 1;
        }
        int i5 = this.mPreSelectedYear;
        int[] iArr3 = this.mStartDate;
        int i6 = (i5 == iArr3[0] && i3 == iArr3[1]) ? iArr3[2] : 1;
        int i7 = this.mPreSelectedYear;
        int[] iArr4 = this.mEndDate;
        if (i7 == iArr4[0] && i3 == iArr4[1]) {
            actualMaximum = iArr4[2];
        }
        if (actualMaximum == 0) {
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            calendar.set(1, this.mPreSelectedYear);
            calendar.set(2, i3);
            calendar.add(2, -1);
            actualMaximum = calendar.getActualMaximum(5);
        }
        if (currentItem < i6) {
            currentItem = i6;
        }
        if (currentItem > actualMaximum) {
            currentItem = actualMaximum;
        }
        this.mWheelDay.setAdapter(new NumericWheelAdapter(i6, actualMaximum));
        this.mWheelDay.setCurrentItem(currentItem - i6);
        this.mPreSelectedMonth = i3;
    }

    private void initYearAndMonth() {
        this.mWheelYear.setCyclic(false);
        this.mWheelYear.setItemsVisibleCount(5);
        this.mWheelMonth.setCyclic(false);
        this.mWheelMonth.setItemsVisibleCount(5);
        this.mWheelDay.setCyclic(false);
        this.mWheelDay.setItemsVisibleCount(5);
        this.mWheelYear.setAdapter(new NumericWheelAdapter(this.mStartDate[0], this.mEndDate[0]));
        int[] iArr = this.mSelectedDate;
        this.mPreSelectedYear = iArr[0];
        this.mWheelYear.setCurrentItem(iArr[0] - this.mStartDate[0]);
        int i = this.mSelectedDate[0];
        int[] iArr2 = this.mStartDate;
        int i2 = i == iArr2[0] ? iArr2[1] : 1;
        int i3 = this.mSelectedDate[0];
        int[] iArr3 = this.mEndDate;
        this.mWheelMonth.setAdapter(new NumericWheelAdapter(i2, i3 == iArr3[0] ? iArr3[1] : 12));
        this.mWheelMonth.setCurrentItem(this.mSelectedDate[1] - i2);
        this.mPreSelectedMonth = this.mSelectedDate[1];
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.set(1, this.mSelectedDate[0]);
        calendar.set(2, this.mSelectedDate[1]);
        calendar.add(2, -1);
        int actualMaximum = calendar.getActualMaximum(5);
        int[] iArr4 = this.mSelectedDate;
        int i4 = iArr4[0];
        int[] iArr5 = this.mStartDate;
        int i5 = (i4 == iArr5[0] && iArr4[1] == iArr5[1]) ? iArr5[2] : 1;
        int[] iArr6 = this.mSelectedDate;
        int i6 = iArr6[0];
        int[] iArr7 = this.mEndDate;
        if (i6 == iArr7[0] && iArr6[1] == iArr7[1]) {
            actualMaximum = iArr7[2];
        }
        this.mWheelDay.setAdapter(new NumericWheelAdapter(i5, actualMaximum));
        this.mWheelDay.setCurrentItem(this.mSelectedDate[2] - i5);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_birthday_date;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getWindowAnimations() {
        return R.style.DialogAnimSlideInBottom;
    }

    @OnClick({R2.id.tv_cancel})
    public void toCancel(View view) {
        dismissAllowingStateLoss();
    }

    @OnClick({R2.id.tv_confirm})
    public void toConfirm(View view) {
        int currentItem;
        if (this.mOnDateSelectedListener != null) {
            int i = this.mPreSelectedYear;
            int[] iArr = this.mStartDate;
            if (i == iArr[0] && this.mPreSelectedMonth == iArr[1]) {
                currentItem = this.mWheelDay.getCurrentItem() + this.mStartDate[2];
            } else {
                currentItem = this.mWheelDay.getCurrentItem() + 1;
            }
            this.mOnDateSelectedListener.onDateSelected(this.mPreSelectedYear, this.mPreSelectedMonth, currentItem);
        }
        dismissAllowingStateLoss();
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected String getLanguage() {
        return super.getLanguage();
    }
}