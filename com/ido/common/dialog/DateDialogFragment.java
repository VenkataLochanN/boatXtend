package com.ido.common.dialog;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.google.android.material.timepicker.TimeModel;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.R;
import com.ido.common.R2;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.ResourceUtil;
import com.ido.life.constants.Constants;
import com.watch.life.wheelview.adapter.ArrayWheelAdapter;
import com.watch.life.wheelview.adapter.NumericWheelAdapter;
import com.watch.life.wheelview.listener.OnItemSelectedListener;
import com.watch.life.wheelview.view.WheelView;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class DateDialogFragment extends BaseDialogFragment {
    private static final String END_DATE = "end_date";
    private static final String LIMIT_AGE = "limit_age";
    private static final String SELECTED_DATE = "selected_date";
    private static final String START_DATE = "start_date";
    private static final String TAG = "DateDialogFragment";
    private static final String TEXT_DESC = "desc_text";
    private static int tempDay;
    private static int tempYear;
    private int mEndYear;
    private OnDateSelectedListener mOnDateSelectedListener;

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
    private int mStartYear = 1920;
    private int[] mSelectedDate = new int[3];
    private int[] mStartDate = new int[3];
    private int[] mEndDate = new int[3];
    private boolean mLimitAge = true;

    public interface OnDateSelectedListener {
        void onDateSelected(int i, int i2, int i3);
    }

    public void setOnDateSelectedListener(OnDateSelectedListener onDateSelectedListener) {
        this.mOnDateSelectedListener = onDateSelectedListener;
    }

    public static DateDialogFragment newInstance(int[] iArr, int[] iArr2) {
        DateDialogFragment dateDialogFragment = new DateDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putIntArray("start_date", iArr);
        bundle.putIntArray(SELECTED_DATE, iArr2);
        dateDialogFragment.setArguments(bundle);
        dateDialogFragment.setStyle(1, R.style.AlertDialog_Dark);
        return dateDialogFragment;
    }

    public static DateDialogFragment newInstance(int[] iArr, int[] iArr2, String str) {
        DateDialogFragment dateDialogFragment = new DateDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putIntArray("start_date", iArr);
        bundle.putIntArray(SELECTED_DATE, iArr2);
        bundle.putString(TEXT_DESC, str);
        dateDialogFragment.setArguments(bundle);
        dateDialogFragment.setStyle(1, R.style.AlertDialog_Dark);
        return dateDialogFragment;
    }

    public static DateDialogFragment newInstance(int[] iArr, int[] iArr2, int[] iArr3) {
        DateDialogFragment dateDialogFragment = new DateDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putIntArray("start_date", iArr);
        bundle.putIntArray("end_date", iArr2);
        bundle.putIntArray(SELECTED_DATE, iArr3);
        dateDialogFragment.setArguments(bundle);
        dateDialogFragment.setStyle(1, R.style.AlertDialog_Dark);
        return dateDialogFragment;
    }

    public static DateDialogFragment newInstance(int[] iArr, int[] iArr2, int[] iArr3, String str, boolean z) {
        DateDialogFragment dateDialogFragment = new DateDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putIntArray("start_date", iArr);
        bundle.putIntArray("end_date", iArr2);
        bundle.putIntArray(SELECTED_DATE, iArr3);
        bundle.putString(TEXT_DESC, str);
        bundle.putBoolean(LIMIT_AGE, z);
        dateDialogFragment.setArguments(bundle);
        dateDialogFragment.setStyle(1, R.style.AlertDialog_Dark);
        return dateDialogFragment;
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
        this.mWheelYear.setTextColorCenter(ResourceUtil.getColor(R.color.color_FF4A00));
        this.mWheelYear.setTextColorOut(ResourceUtil.getColor(R.color.color_999999));
        this.mWheelMonth.setTextColorCenter(ResourceUtil.getColor(R.color.color_FF4A00));
        this.mWheelMonth.setTextColorOut(ResourceUtil.getColor(R.color.color_999999));
        this.mWheelDay.setTextColorCenter(ResourceUtil.getColor(R.color.color_FF4A00));
        this.mWheelDay.setTextColorOut(ResourceUtil.getColor(R.color.color_999999));
        this.mTvCancel.setTextColor(ResourceUtil.getColor(R.color.color_FF4A00));
        this.mTvConfirm.setTextColor(ResourceUtil.getColor(R.color.color_FF4A00));
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
        if (this.mEndDate == null) {
            Calendar calendar = Calendar.getInstance();
            if (this.mLimitAge) {
                this.mEndDate = new int[]{calendar.get(1) - 16, calendar.get(2) + 1, calendar.get(5)};
            } else {
                this.mEndDate = new int[]{calendar.get(1) - 100, calendar.get(2) + 1, calendar.get(5)};
            }
        }
        if (this.mStartDate == null) {
            int[] iArr = this.mEndDate;
            this.mStartDate = new int[]{iArr[0] - 84, iArr[1], iArr[2]};
        }
        initTimePicker(this.mStartDate, this.mEndDate, this.mSelectedDate);
    }

    private void initTimePicker(int[] iArr, int[] iArr2, int[] iArr3) {
        List list;
        char c2;
        char c3;
        int i;
        int i2;
        int i3 = iArr2[2];
        final int i4 = iArr2[1];
        final int i5 = iArr2[0];
        int[] iArr4 = iArr3 == null ? new int[]{i5, i4, i3} : iArr3;
        final int i6 = iArr4[0];
        int i7 = iArr4[1];
        int i8 = iArr4[2];
        String[] strArr = {"1", Constants.DIALDEFNED_VERSION_CONNECT, "5", "7", AlexaCustomSkillConstant.EVENT_BRIGHTNESS, "10", "12"};
        String[] strArr2 = {AlexaCustomSkillConstant.EVENT_START_SPORT, "6", AlexaCustomSkillConstant.EVENT_HR, "11"};
        final List listAsList = Arrays.asList(strArr);
        List listAsList2 = Arrays.asList(strArr2);
        this.mEndYear = iArr2[0];
        if (iArr == null) {
            this.mStartYear = this.mEndYear - 84;
        } else {
            this.mStartYear = iArr[0];
        }
        this.mWheelYear.setAdapter(new NumericWheelAdapter(this.mStartYear, this.mEndYear));
        this.mWheelYear.setCyclic(false);
        this.mWheelYear.setItemsVisibleCount(5);
        this.mWheelYear.setCurrentItem(iArr4[0] - this.mStartYear);
        tempYear = iArr4[0];
        tempDay = iArr4[2];
        setYearsMonth(iArr4[0]);
        this.mWheelMonth.setCyclic(false);
        this.mWheelMonth.setItemsVisibleCount(5);
        this.mWheelMonth.setCurrentItem(iArr4[1] - (this.mStartYear == iArr4[0] ? this.mStartDate[1] : 1));
        this.mWheelDay.setCyclic(false);
        this.mWheelDay.setItemsVisibleCount(5);
        String[] strArr3 = new String[31];
        String[] strArr4 = new String[30];
        String[] strArr5 = new String[29];
        String[] strArr6 = new String[28];
        int i9 = 0;
        while (i9 < strArr3.length) {
            int i10 = i3;
            int i11 = i9 + 1;
            strArr3[i9] = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i11));
            List list2 = listAsList2;
            if (i9 < strArr4.length) {
                i2 = 1;
                strArr4[i9] = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i11));
            } else {
                i2 = 1;
            }
            if (i9 < strArr5.length) {
                Object[] objArr = new Object[i2];
                objArr[0] = Integer.valueOf(i11);
                strArr5[i9] = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, objArr);
            }
            if (i9 < strArr6.length) {
                Object[] objArr2 = new Object[i2];
                objArr2[0] = Integer.valueOf(i11);
                strArr6[i9] = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, objArr2);
            }
            listAsList2 = list2;
            i9 = i11;
            i3 = i10;
        }
        final int i12 = i3;
        List list3 = listAsList2;
        if (i7 == i4 && i6 == i5) {
            int[] iArr5 = this.mStartDate;
            if (i7 == iArr5[1] && i6 == this.mStartYear) {
                c3 = 2;
                i = iArr5[2];
            } else {
                c3 = 2;
                i = 1;
            }
            int[] iArr6 = this.mEndDate;
            this.mWheelDay.setAdapter(new NumericWheelAdapter(i, (i7 == iArr6[1] && i6 == this.mEndYear) ? iArr6[c3] : i12));
            int i13 = iArr4[c3];
            if (i13 <= i8) {
                this.mWheelDay.setCurrentItem(i13 - this.mStartDate[c3]);
            } else {
                this.mWheelDay.setCurrentItem(0);
            }
        } else if (i7 == this.mStartDate[1] && i6 == this.mStartYear) {
            int monthDayCount = getMonthDayCount();
            int[] iArr7 = this.mEndDate;
            if (i7 == iArr7[1] && i6 == this.mEndYear) {
                c2 = 2;
                monthDayCount = iArr7[2];
            } else {
                c2 = 2;
            }
            this.mWheelDay.setAdapter(new NumericWheelAdapter(this.mStartDate[c2], monthDayCount));
            this.mWheelDay.setCurrentItem(i8 - this.mStartDate[c2]);
        } else {
            if (listAsList.contains(String.valueOf(i7))) {
                this.mWheelDay.setAdapter(new ArrayWheelAdapter(Arrays.asList(strArr3)));
                list = list3;
            } else {
                String strValueOf = String.valueOf(i7);
                list = list3;
                if (list.contains(strValueOf)) {
                    this.mWheelDay.setAdapter(new ArrayWheelAdapter(Arrays.asList(strArr4)));
                } else if ((i6 % 4 == 0 && i6 % 100 != 0) || i6 % 400 == 0) {
                    this.mWheelDay.setAdapter(new ArrayWheelAdapter(Arrays.asList(strArr5)));
                } else {
                    this.mWheelDay.setAdapter(new ArrayWheelAdapter(Arrays.asList(strArr6)));
                }
            }
            this.mWheelDay.setCurrentItem(iArr4[2] - 1);
            this.mWheelYear.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.common.dialog.-$$Lambda$DateDialogFragment$5jxpYMAVjqHG5_5ISTIHR2OH1Rc
                @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
                public final void onItemSelected(int i14) {
                    this.f$0.lambda$initTimePicker$0$DateDialogFragment(i14);
                }
            });
            final List list4 = list;
            this.mWheelMonth.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.common.dialog.-$$Lambda$DateDialogFragment$Z5bmgUcZQWgu9I4qJue4OAju7g8
                @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
                public final void onItemSelected(int i14) {
                    this.f$0.lambda$initTimePicker$1$DateDialogFragment(i4, i5, i12, i6, listAsList, list4, i14);
                }
            });
        }
        list = list3;
        this.mWheelYear.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.common.dialog.-$$Lambda$DateDialogFragment$5jxpYMAVjqHG5_5ISTIHR2OH1Rc
            @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
            public final void onItemSelected(int i14) {
                this.f$0.lambda$initTimePicker$0$DateDialogFragment(i14);
            }
        });
        final List list42 = list;
        this.mWheelMonth.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.common.dialog.-$$Lambda$DateDialogFragment$Z5bmgUcZQWgu9I4qJue4OAju7g8
            @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
            public final void onItemSelected(int i14) {
                this.f$0.lambda$initTimePicker$1$DateDialogFragment(i4, i5, i12, i6, listAsList, list42, i14);
            }
        });
    }

    public /* synthetic */ void lambda$initTimePicker$0$DateDialogFragment(int i) {
        int i2 = this.mStartYear;
        int i3 = i + i2;
        tempYear = i + i2;
        this.mWheelMonth.setAdapter(new NumericWheelAdapter(i3 == i2 ? this.mStartDate[1] : 1, i3 == this.mEndYear ? this.mEndDate[1] : 12));
        setYearsMonth(i3);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0053  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ void lambda$initTimePicker$1$DateDialogFragment(int r6, int r7, int r8, int r9, java.util.List r10, java.util.List r11, int r12) {
        /*
            Method dump skipped, instruction units count: 310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.common.dialog.DateDialogFragment.lambda$initTimePicker$1$DateDialogFragment(int, int, int, int, java.util.List, java.util.List, int):void");
    }

    private int getMonthDayCount() {
        Calendar calendar = Calendar.getInstance();
        int[] iArr = this.mStartDate;
        calendar.set(iArr[0], iArr[1], 1);
        calendar.add(2, 1);
        calendar.add(5, -1);
        return calendar.get(5);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setYearsMonth(int r11) {
        /*
            Method dump skipped, instruction units count: 206
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.common.dialog.DateDialogFragment.setYearsMonth(int):void");
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_date;
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
        int currentItem2;
        if (this.mOnDateSelectedListener != null) {
            int i = 1;
            if (this.mLimitAge) {
                currentItem = this.mWheelMonth.getCurrentItem() + (tempYear == this.mStartYear ? this.mStartDate[1] : 1);
                int currentItem3 = this.mWheelDay.getCurrentItem();
                if (tempYear == this.mStartYear) {
                    int[] iArr = this.mStartDate;
                    if (currentItem == iArr[1]) {
                        i = iArr[2];
                    }
                }
                currentItem2 = currentItem3 + i;
            } else {
                currentItem = this.mWheelMonth.getCurrentItem() + 1;
                currentItem2 = this.mWheelDay.getCurrentItem() + 1;
            }
            this.mOnDateSelectedListener.onDateSelected(tempYear, currentItem, currentItem2);
        }
        dismissAllowingStateLoss();
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected String getLanguage() {
        return super.getLanguage();
    }
}