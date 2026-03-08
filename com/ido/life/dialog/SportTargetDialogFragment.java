package com.ido.life.dialog;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.boat.Xtend.two.R;
import com.ido.common.R2;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.LanguageUtil;
import com.ido.life.util.ArrayUtils;
import com.ido.life.util.RunTimeUtil;
import com.watch.life.wheelview.adapter.ArrayWheelAdapter;
import com.watch.life.wheelview.listener.OnItemSelectedListener;
import com.watch.life.wheelview.view.WheelView;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class SportTargetDialogFragment extends BaseDialogFragment {
    private static final String SELECTED_VALUE = "selected_value";
    private static final String TAB_ARRAY = "tab_array";
    private static final String TAB_RIDE = "tab_ride";
    private static final String TAG = "SportTargetDialogFragment";
    public static int[] distances = new int[102];
    private boolean mIsRide;
    private OnDateSelectedListener mOnDateSelectedListener;
    private String mSelectedValue;

    @BindView(R2.id.tv_cancel)
    TextView mTvCancel;

    @BindView(R2.id.tv_confirm)
    TextView mTvConfirm;
    private String[] mTypes;

    @BindView(R.id.wheel_tab)
    WheelView mWheeTab;

    @BindView(R.id.wheel_value)
    WheelView mWheelValue;
    private String[] noValues = new String[0];
    public int mStepMin = 5000;
    private String[] mCalories = new String[20];
    private String[] mSteps = new String[46];
    private String[] mTimes = new String[60];

    public interface OnDateSelectedListener {
        void onDateSelected(int i, int i2);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_sport_target;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getWindowAnimations() {
        return R.style.DialogAnimSlideInBottom;
    }

    public void setOnDateSelectedListener(OnDateSelectedListener onDateSelectedListener) {
        this.mOnDateSelectedListener = onDateSelectedListener;
    }

    public static SportTargetDialogFragment newInstance(String[] strArr, boolean z, String str) {
        SportTargetDialogFragment sportTargetDialogFragment = new SportTargetDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray(TAB_ARRAY, strArr);
        bundle.putString(SELECTED_VALUE, str);
        bundle.putBoolean(TAB_RIDE, z);
        sportTargetDialogFragment.setArguments(bundle);
        sportTargetDialogFragment.setStyle(1, 2131886083);
        return sportTargetDialogFragment;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        super.initUI(view);
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = -1;
        window.setSoftInputMode(18);
        window.setAttributes(attributes);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        super.initData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mTypes = arguments.getStringArray(TAB_ARRAY);
            this.mSelectedValue = arguments.getString(SELECTED_VALUE);
            this.mIsRide = arguments.getBoolean(TAB_RIDE);
        }
        initDistance();
        initCategory();
        initStep();
        initTime();
        initTargetPicker(this.mTypes, this.mSelectedValue);
    }

    private void initDistance() {
        int i = 0;
        while (i < 102) {
            int i2 = i + 1;
            distances[i] = i2;
            i = i2;
        }
    }

    private void initStep() {
        for (int i = 0; i <= 45; i++) {
            this.mSteps[i] = ((i * 1000) + this.mStepMin) + LanguageUtil.getLanguageText(R.string.public_sport_step);
        }
    }

    private void initCategory() {
        int i = 0;
        while (i < 20) {
            String[] strArr = this.mCalories;
            StringBuilder sb = new StringBuilder();
            int i2 = i + 1;
            sb.append(i2 * 100);
            sb.append(LanguageUtil.getLanguageText(R.string.public_heat_calorie));
            strArr[i] = sb.toString();
            i = i2;
        }
    }

    private void initTime() {
        int i = 0;
        while (i < 60) {
            String[] strArr = this.mTimes;
            StringBuilder sb = new StringBuilder();
            int i2 = i + 1;
            sb.append(i2 * 5);
            sb.append(LanguageUtil.getLanguageText(R.string.public_time_minute));
            strArr[i] = sb.toString();
            i = i2;
        }
    }

    private void initTargetPicker(String[] strArr, String str) {
        int[] numByName = getNumByName(str);
        this.mWheeTab.setCyclic(false);
        this.mWheeTab.setItemsVisibleCount(5);
        this.mWheeTab.setAdapter(new ArrayWheelAdapter(Arrays.asList(strArr)));
        if (numByName != null && numByName.length == 2) {
            this.mWheeTab.setCurrentItem(numByName[0]);
            setTabValues(numByName[0], numByName[1]);
        } else {
            this.mWheeTab.setCurrentItem(0);
            setTabValues(0, 0);
        }
        this.mWheeTab.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.ido.life.dialog.SportTargetDialogFragment.1
            @Override // com.watch.life.wheelview.listener.OnItemSelectedListener
            public void onItemSelected(int i) {
                SportTargetDialogFragment.this.setTabValues(i, SportTargetDialogFragment.this.getValueByIndex(i));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getValueByIndex(int i) {
        if (i == 1) {
            return 4;
        }
        if (i == 2) {
            return 1;
        }
        if (i != 3) {
            if (i == 4) {
                return 5;
            }
        } else if (this.mIsRide) {
            return 5;
        }
        return 0;
    }

    private int[] getNumByName(String str) {
        String languageText = LanguageUtil.getLanguageText(R.string.sport_run_distance_unit);
        String languageText2 = LanguageUtil.getLanguageText(R.string.sport_run_distance_unit_mi);
        CharSequence languageText3 = LanguageUtil.getLanguageText(R.string.public_heat_calorie);
        CharSequence languageText4 = LanguageUtil.getLanguageText(R.string.public_sport_step);
        if (str.contains(LanguageUtil.getLanguageText(R.string.public_time_minute))) {
            return this.mIsRide ? new int[]{3, ArrayUtils.getNumInArray(str, this.mTimes)} : new int[]{4, ArrayUtils.getNumInArray(str, this.mTimes)};
        }
        if (str.contains(languageText)) {
            int i = Integer.parseInt(str.substring(0, str.length() - languageText.length()));
            return i <= 22 ? new int[]{1, ArrayUtils.getNumInArray(i, distances)} : (i <= 22 || i >= 43) ? new int[]{1, ArrayUtils.getNumInArray(i + 2, distances)} : new int[]{1, ArrayUtils.getNumInArray(i + 1, distances)};
        }
        if (str.contains(languageText2)) {
            int i2 = Integer.parseInt(str.substring(0, str.length() - languageText2.length()));
            return i2 <= 14 ? new int[]{1, ArrayUtils.getNumInArray(i2, distances)} : (i2 <= 14 || i2 >= 27) ? new int[]{1, ArrayUtils.getNumInArray(i2 + 2, distances)} : new int[]{1, ArrayUtils.getNumInArray(i2 + 1, distances)};
        }
        if (str.contains(languageText3)) {
            return new int[]{2, ArrayUtils.getNumInArray(str, this.mCalories)};
        }
        if (str.contains(languageText4)) {
            return new int[]{3, ArrayUtils.getNumInArray(str, this.mSteps)};
        }
        if (RunTimeUtil.getInstance().getUnitSet() == 1 && LanguageUtil.getLanguageText(R.string.sport_half_horse).equals(str)) {
            return new int[]{1, 22};
        }
        if (RunTimeUtil.getInstance().getUnitSet() == 1 && LanguageUtil.getLanguageText(R.string.sport_whole_horse).equals(str)) {
            return new int[]{1, 43};
        }
        if (RunTimeUtil.getInstance().getUnitSet() == 2 && LanguageUtil.getLanguageText(R.string.sport_half_horse).equals(str)) {
            return new int[]{1, 14};
        }
        if (RunTimeUtil.getInstance().getUnitSet() == 2 && LanguageUtil.getLanguageText(R.string.sport_whole_horse).equals(str)) {
            return new int[]{1, 27};
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTabValues(int i, int i2) {
        CommonLogUtil.d(TAG, "setTabValues: " + i + AppInfo.DELIM + i2);
        int i3 = 0;
        if (i == 0) {
            this.mWheelValue.setCyclic(false);
            this.mWheelValue.setItemsVisibleCount(5);
            this.mWheelValue.setAdapter(new ArrayWheelAdapter(Arrays.asList(this.noValues)));
            this.mWheelValue.setCurrentItem(i2);
            return;
        }
        if (i != 1) {
            if (i == 2) {
                this.mWheelValue.setCyclic(false);
                this.mWheelValue.setItemsVisibleCount(5);
                this.mWheelValue.setAdapter(new ArrayWheelAdapter(Arrays.asList(this.mCalories)));
                this.mWheelValue.setCurrentItem(i2);
                return;
            }
            if (i != 3) {
                if (i != 4) {
                    return;
                }
                this.mWheelValue.setCyclic(false);
                this.mWheelValue.setItemsVisibleCount(5);
                this.mWheelValue.setAdapter(new ArrayWheelAdapter(Arrays.asList(this.mTimes)));
                this.mWheelValue.setCurrentItem(i2);
                return;
            }
            if (this.mIsRide) {
                this.mWheelValue.setCyclic(false);
                this.mWheelValue.setItemsVisibleCount(5);
                this.mWheelValue.setAdapter(new ArrayWheelAdapter(Arrays.asList(this.mTimes)));
                this.mWheelValue.setCurrentItem(i2);
                return;
            }
            this.mWheelValue.setCyclic(false);
            this.mWheelValue.setItemsVisibleCount(5);
            this.mWheelValue.setAdapter(new ArrayWheelAdapter(Arrays.asList(this.mSteps)));
            this.mWheelValue.setCurrentItem(i2);
            return;
        }
        this.mWheelValue.setCyclic(false);
        this.mWheelValue.setItemsVisibleCount(5);
        LanguageUtil.getLanguageText(R.string.sport_run_distance_unit);
        String[] strArr = new String[distances.length];
        if (RunTimeUtil.getInstance().getUnitSet() == 1) {
            String languageText = LanguageUtil.getLanguageText(R.string.sport_run_distance_unit);
            while (i3 < strArr.length) {
                if (i3 < 22) {
                    strArr[i3] = distances[i3] + languageText;
                } else if (i3 == 22) {
                    strArr[i3] = LanguageUtil.getLanguageText(R.string.sport_half_horse);
                } else if (i3 > 22 && i3 < 43) {
                    strArr[i3] = (distances[i3] - 1) + languageText;
                } else if (i3 == 43) {
                    strArr[i3] = LanguageUtil.getLanguageText(R.string.sport_whole_horse);
                } else {
                    strArr[i3] = (distances[i3] - 2) + languageText;
                }
                i3++;
            }
        } else {
            String languageText2 = LanguageUtil.getLanguageText(R.string.sport_run_distance_unit_mi);
            while (i3 < strArr.length) {
                if (i3 < 14) {
                    strArr[i3] = distances[i3] + languageText2;
                } else if (i3 == 14) {
                    strArr[i3] = LanguageUtil.getLanguageText(R.string.sport_half_horse);
                } else if (i3 > 14 && i3 < 27) {
                    strArr[i3] = (distances[i3] - 1) + languageText2;
                } else if (i3 == 27) {
                    strArr[i3] = LanguageUtil.getLanguageText(R.string.sport_whole_horse);
                } else {
                    strArr[i3] = (distances[i3] - 2) + languageText2;
                }
                i3++;
            }
        }
        this.mWheelValue.setAdapter(new ArrayWheelAdapter(Arrays.asList(strArr)));
        this.mWheelValue.setCurrentItem(i2);
    }

    @OnClick({R2.id.tv_cancel})
    public void toCancel(View view) {
        dismissAllowingStateLoss();
    }

    @OnClick({R2.id.tv_confirm})
    public void toConfirm(View view) {
        OnDateSelectedListener onDateSelectedListener = this.mOnDateSelectedListener;
        if (onDateSelectedListener != null) {
            onDateSelectedListener.onDateSelected(this.mWheeTab.getCurrentItem(), this.mWheelValue.getCurrentItem() + 1);
        }
        dismissAllowingStateLoss();
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected String getLanguage() {
        return super.getLanguage();
    }
}