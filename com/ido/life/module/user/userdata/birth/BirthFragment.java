package com.ido.life.module.user.userdata.birth;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.google.android.material.timepicker.TimeModel;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.dialog.WaitingDialog;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.TimeUtil;
import com.ido.life.base.BaseFragment;
import com.ido.life.constants.Constants;
import com.ido.life.customview.NormalToast;
import com.ido.life.module.user.userdata.OnChangeListener;
import com.ido.life.module.user.userdata.birth.BirthContract;
import com.ido.life.util.DateUtil;
import com.ido.life.util.SPUtils;
import com.watch.life.wheelview.adapter.ArrayWheelAdapter;
import com.watch.life.wheelview.adapter.NumericWheelAdapter;
import com.watch.life.wheelview.listener.OnItemSelectedListener;
import com.watch.life.wheelview.view.WheelView;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class BirthFragment extends BaseFragment implements BirthContract.View {
    private static final String SELECTED_DATE = "selected_date";
    private static final String START_DATE = "start_date";
    private static final String TAG = "BirthFragment";
    private static int tempDay;
    private static int tempYear;
    private boolean isShowHint;

    @BindView(R.id.low_hint)
    TextView mHintTv;

    @BindView(R.id.iv_forward)
    ImageButton mIvForward;

    @BindView(R.id.tv_title_birth)
    TextView mIvTitleBirth;
    private OnChangeListener mOnChangeListener;
    private BirthContract.Presenter mPresenter;

    @BindView(R.id.wheel_year)
    WheelView mWheeYear;

    @BindView(R.id.wheel_day)
    WheelView mWheelDay;

    @BindView(R.id.wheel_month)
    WheelView mWheelMonth;
    private int mStartYear = 1920;
    private int mEndYear = 2019;
    private int[] mSelectedDate = new int[3];
    private int[] mStartDate = new int[3];

    private void initListener() {
    }

    private void showSoftInputFromWindow() {
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.View
    public void clearDay() {
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.View
    public void clearMonth() {
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.View
    public void clearYear() {
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.View
    public String getDay() {
        return null;
    }

    @Override // com.ido.common.base.BaseCoreFragment
    protected int getLayoutResId() {
        return R.layout.fragment_birth;
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.View
    public String getMonth() {
        return null;
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.View
    public String getYear() {
        return null;
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.View
    public void setDay(String str) {
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.View
    public void setMonth(String str) {
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.View
    public void setYear(String str) {
    }

    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.mOnChangeListener = onChangeListener;
    }

    public static BirthFragment newInstance(int[] iArr, int[] iArr2) {
        BirthFragment birthFragment = new BirthFragment();
        Bundle bundle = new Bundle();
        bundle.putIntArray("start_date", iArr);
        bundle.putIntArray(SELECTED_DATE, iArr2);
        birthFragment.setArguments(bundle);
        return birthFragment;
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initData();
        initListener();
    }

    @OnClick({R.id.iv_forward})
    public void toUpdateBirth(View view) {
        int currentItem = this.mWheeYear.getCurrentItem() + this.mStartYear;
        int currentItem2 = this.mWheelMonth.getCurrentItem() + 1;
        int currentItem3 = this.mWheelDay.getCurrentItem() + 1;
        String str = DateUtil.format(currentItem, currentItem2, currentItem3);
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.set(1, currentItem);
        calendar.set(2, currentItem2);
        calendar.set(5, currentItem3);
        calendar.add(2, -1);
        this.mPresenter.saveBirthday(str);
    }

    @OnClick({R.id.iv_back_forward})
    public void backForward(View view) {
        OnChangeListener onChangeListener = this.mOnChangeListener;
        if (onChangeListener != null) {
            onChangeListener.onPageBack();
        }
    }

    @Override // com.ido.life.base.BaseFragment, com.ido.common.base.BaseCoreFragment
    protected void initData() {
        this.mPresenter = new BirthPresenter(this);
        Bundle arguments = getArguments();
        this.isShowHint = LanguageUtil.hasLowIntervalCountry((String) SPUtils.get(Constants.CHOOSE_COUNTRY_CODE, ""));
        if (arguments != null) {
            this.mStartDate = arguments.getIntArray("start_date");
            this.mSelectedDate = arguments.getIntArray(SELECTED_DATE);
        }
        initTimePicker(this.mStartDate, this.mSelectedDate);
        this.mHintTv.setVisibility(this.isShowHint ? 0 : 8);
    }

    @Override // com.ido.life.base.BaseFragment
    protected void refreshLanguage() {
        super.refreshLanguage();
    }

    private void initTimePicker(int[] iArr, int[] iArr2) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.add(2, 1);
        final int i = calendar.get(5);
        final int i2 = calendar.get(2);
        final int i3 = calendar.get(1);
        int[] iArr3 = iArr2 == null ? new int[]{i3, i2, calendar.get(5)} : iArr2;
        int i4 = iArr3[0];
        int i5 = iArr3[1];
        int i6 = iArr3[2];
        String[] strArr = {"1", Constants.DIALDEFNED_VERSION_CONNECT, "5", "7", AlexaCustomSkillConstant.EVENT_BRIGHTNESS, "10", "12"};
        String[] strArr2 = {AlexaCustomSkillConstant.EVENT_START_SPORT, "6", AlexaCustomSkillConstant.EVENT_HR, "11"};
        final List listAsList = Arrays.asList(strArr);
        final List listAsList2 = Arrays.asList(strArr2);
        this.mEndYear = Integer.parseInt(TimeUtil.getCurrentYearDate());
        this.mEndYear = this.isShowHint ? this.mEndYear - 16 : this.mEndYear;
        if (iArr == null) {
            this.mStartYear = this.mEndYear - 100;
        } else {
            this.mStartYear = iArr[0];
        }
        this.mWheeYear.setAdapter(new NumericWheelAdapter(this.mStartYear, this.mEndYear));
        this.mWheeYear.setCyclic(false);
        this.mWheeYear.setItemsVisibleCount(5);
        this.mWheeYear.setCurrentItem(iArr3[0] - this.mStartYear);
        String[] strArr3 = new String[12];
        int i7 = 0;
        while (i7 < strArr3.length) {
            int i8 = i7 + 1;
            strArr3[i7] = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i8));
            i7 = i8;
        }
        tempDay = iArr3[2];
        tempYear = iArr3[0];
        setYearsMonth(iArr3[0]);
        this.mWheelMonth.setCyclic(false);
        this.mWheelMonth.setItemsVisibleCount(5);
        int i9 = 1;
        this.mWheelMonth.setCurrentItem(iArr3[1] - 1);
        this.mWheelDay.setCyclic(false);
        this.mWheelDay.setItemsVisibleCount(5);
        String[] strArr4 = new String[31];
        int i10 = 0;
        while (i10 < strArr4.length) {
            Object[] objArr = new Object[i9];
            int i11 = i10 + 1;
            objArr[0] = Integer.valueOf(i11);
            strArr4[i10] = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, objArr);
            i10 = i11;
            i9 = 1;
        }
        String[] strArr5 = new String[30];
        int i12 = 0;
        while (i12 < strArr5.length) {
            int i13 = i12 + 1;
            strArr5[i12] = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i13));
            i12 = i13;
        }
        String[] strArr6 = new String[29];
        int i14 = 0;
        while (i14 < strArr6.length) {
            int i15 = i14 + 1;
            strArr6[i14] = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i15));
            i4 = i4;
            i14 = i15;
        }
        int i16 = i4;
        String[] strArr7 = new String[28];
        int i17 = 0;
        while (i17 < strArr7.length) {
            int i18 = i17 + 1;
            strArr7[i17] = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i18));
            i17 = i18;
            strArr6 = strArr6;
        }
        String[] strArr8 = strArr6;
        if (i5 == i2 && this.mWheeYear.getCurrentItem() + this.mStartYear == i3) {
            this.mWheelDay.setAdapter(new NumericWheelAdapter(1, i));
            int i19 = iArr3[2];
            if (i19 <= i6) {
                this.mWheelDay.setCurrentItem(i19 - 1);
            } else {
                this.mWheelDay.setCurrentItem(0);
            }
        } else {
            if (listAsList.contains(String.valueOf(i5))) {
                this.mWheelDay.setAdapter(new ArrayWheelAdapter(Arrays.asList(strArr4)));
            } else if (listAsList2.contains(String.valueOf(i5))) {
                this.mWheelDay.setAdapter(new ArrayWheelAdapter(Arrays.asList(strArr5)));
            } else if ((i16 % 4 == 0 && i16 % 100 != 0) || i16 % 400 == 0) {
                this.mWheelDay.setAdapter(new ArrayWheelAdapter(Arrays.asList(strArr8)));
            } else {
                this.mWheelDay.setAdapter(new ArrayWheelAdapter(Arrays.asList(strArr7)));
            }
            this.mWheelDay.setCurrentItem(iArr3[2] - 1);
        }
        this.mWheeYear.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.life.module.user.userdata.birth.BirthFragment.1
            @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
            public void onItemSelected(int i20) {
                int i21 = BirthFragment.this.mStartYear + i20;
                int unused = BirthFragment.tempYear = i20 + BirthFragment.this.mStartYear;
                BirthFragment.this.setYearsMonth(i21);
            }
        });
        this.mWheelMonth.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.life.module.user.userdata.birth.BirthFragment.2
            @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
            public void onItemSelected(int i20) {
                int i21 = i20 + 1;
                int unused = BirthFragment.tempDay = BirthFragment.this.mWheelDay.getCurrentItem() + 1;
                if (i2 == i21 && BirthFragment.tempYear == BirthFragment.this.mEndYear) {
                    BirthFragment.this.mWheelDay.setAdapter(new NumericWheelAdapter(1, i));
                    int i22 = BirthFragment.tempDay;
                    int i23 = i;
                    if (i22 > i23) {
                        int unused2 = BirthFragment.tempDay = i23;
                    }
                } else if (listAsList.contains(String.valueOf(i21))) {
                    BirthFragment.this.mWheelDay.setAdapter(new NumericWheelAdapter(1, 31));
                } else if (!listAsList2.contains(String.valueOf(i21))) {
                    if (((BirthFragment.this.mWheeYear.getCurrentItem() + BirthFragment.this.mStartYear) % 4 == 0 && (BirthFragment.this.mWheeYear.getCurrentItem() + BirthFragment.this.mStartYear) % 100 != 0) || (BirthFragment.this.mWheeYear.getCurrentItem() + BirthFragment.this.mStartYear) % 400 == 0) {
                        BirthFragment.this.mWheelDay.setAdapter(new NumericWheelAdapter(1, 29));
                        if (BirthFragment.tempDay > 29) {
                            int unused3 = BirthFragment.tempDay = 29;
                        }
                    } else {
                        BirthFragment.this.mWheelDay.setAdapter(new NumericWheelAdapter(1, 28));
                        if (BirthFragment.tempDay > 28) {
                            int unused4 = BirthFragment.tempDay = 28;
                        }
                    }
                } else {
                    BirthFragment.this.mWheelDay.setAdapter(new NumericWheelAdapter(1, 30));
                    if (BirthFragment.tempDay > 30) {
                        int unused5 = BirthFragment.tempDay = 30;
                    }
                }
                if (BirthFragment.this.mWheeYear.getCurrentItem() + BirthFragment.this.mStartYear == i3 && i21 == i2) {
                    int i24 = BirthFragment.tempDay;
                    int i25 = i;
                    if (i24 <= i25) {
                        i25 = BirthFragment.tempDay;
                    }
                    int unused6 = BirthFragment.tempDay = i25;
                    BirthFragment.this.mWheelDay.setAdapter(new NumericWheelAdapter(1, i));
                }
                BirthFragment.this.mWheelDay.setCurrentItem(BirthFragment.tempDay - 1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setYearsMonth(int i) {
        int i2;
        int i3;
        Calendar calendar = Calendar.getInstance();
        int i4 = calendar.get(1);
        if (this.isShowHint) {
            i4 -= 16;
        }
        int currentItem = this.mWheelMonth.getCurrentItem() + 1;
        calendar.set(i, currentItem, 1);
        calendar.add(5, -1);
        int i5 = calendar.get(5);
        int currentItem2 = this.mWheelDay.getCurrentItem() + 1;
        int i6 = currentItem2 > i5 ? i5 : currentItem2;
        if (i4 == i) {
            Calendar calendar2 = Calendar.getInstance();
            i2 = calendar2.get(2) + 1;
            i3 = currentItem > i2 ? i2 : currentItem;
            if (i2 == i3 && i6 > (i5 = calendar2.get(5))) {
                i6 = i5;
            }
        } else {
            i2 = 12;
            i3 = currentItem;
        }
        this.mWheelMonth.setAdapter(new NumericWheelAdapter(1, i2));
        if (currentItem != i3) {
            this.mWheelMonth.setCurrentItem(i3 - 1);
        }
        this.mWheelDay.setAdapter(new NumericWheelAdapter(1, i5));
        if (currentItem2 != i6) {
            this.mWheelDay.setCurrentItem(i6 - 1);
        }
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.View
    public void showMessage() {
        NormalToast.showToast(getLanguageText(R.string.user_data_input_effect_age));
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.View
    public void setForward() {
        OnChangeListener onChangeListener = this.mOnChangeListener;
        if (onChangeListener != null) {
            onChangeListener.onPageNext();
        }
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.View
    public void setForwardEnable(boolean z) {
        this.mIvForward.setEnabled(z);
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.View
    public void showLoading() {
        WaitingDialog.showDialog(getActivity());
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.View
    public void hideLoading() {
        WaitingDialog.hideDialog();
    }

    @Override // com.ido.life.module.user.userdata.birth.BirthContract.View
    public void showMessage(String str) {
        NormalToast.showToast(str);
    }

    @Override // com.ido.common.base.BaseView
    public void setPresenter(BirthContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}