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
import com.watch.life.wheelview.adapter.ArrayWheelAdapter;
import com.watch.life.wheelview.adapter.NumericWheelAdapter;
import com.watch.life.wheelview.view.WheelView;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class TimeDialogFragment extends BaseDialogFragment {
    private static final String CURRENT_HOUR = "current_hour";
    private static final String CURRENT_MIN = "current_min";
    private static final String FORMAT_STR = "format_str";
    private static final String IS_FORMAT_24 = "is_format_24";
    private static final String MINUTE_SET_ABLE = "minute_set_able";
    private int mCurrentHour;
    private int mCurrentMin;
    private String[] mFormatStrs;
    private boolean mIsFormat24;
    private boolean mMinuteSetAble;
    private OnItemSelectedListener mOnItemSelectedListener;

    @BindView(R2.id.tv_cancel)
    TextView mTvCancel;

    @BindView(R2.id.tv_confirm)
    TextView mTvConfirm;

    @BindView(R2.id.wheel_hour)
    WheelView mWheelHour;

    @BindView(R2.id.wheel_minuter)
    WheelView mWheelMinuter;

    @BindView(R2.id.wheel_time_format)
    WheelView mWheelTimeFormat;

    public interface OnItemSelectedListener {
        void onTimeSelected(int i, int i2, int i3);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_time_picker;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getWindowAnimations() {
        return R.style.DialogAnimSlideInBottom;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }

    public static TimeDialogFragment newInstance(int i, int i2) {
        return newInstance(i, i2, true, null);
    }

    public static TimeDialogFragment newInstance(int i, int i2, boolean z) {
        return newInstance(i, i2, true, null, z);
    }

    public static TimeDialogFragment newInstance(int i, int i2, boolean z, String[] strArr) {
        return newInstance(i, i2, z, strArr, true);
    }

    public static TimeDialogFragment newInstance(int i, int i2, boolean z, String[] strArr, boolean z2) {
        TimeDialogFragment timeDialogFragment = new TimeDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_FORMAT_24, z);
        bundle.putStringArray(FORMAT_STR, strArr);
        bundle.putInt(CURRENT_HOUR, i);
        bundle.putInt(CURRENT_MIN, i2);
        bundle.putBoolean(MINUTE_SET_ABLE, z2);
        timeDialogFragment.setArguments(bundle);
        timeDialogFragment.setStyle(1, R.style.AlertDialog_Dark);
        return timeDialogFragment;
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
        this.mIsFormat24 = true;
        if (arguments != null) {
            this.mIsFormat24 = arguments.getBoolean(IS_FORMAT_24);
            this.mFormatStrs = arguments.getStringArray(FORMAT_STR);
            this.mCurrentHour = arguments.getInt(CURRENT_HOUR);
            this.mCurrentMin = arguments.getInt(CURRENT_MIN);
            this.mMinuteSetAble = arguments.getBoolean(MINUTE_SET_ABLE, true);
        }
        initWheel();
    }

    private void initWheel() {
        String[] strArr;
        boolean z = (this.mIsFormat24 || (strArr = this.mFormatStrs) == null || strArr.length <= 0) ? false : true;
        this.mWheelTimeFormat.setVisibility(z ? 0 : 8);
        if (z) {
            this.mWheelTimeFormat.setCyclic(false);
            this.mWheelTimeFormat.setItemsVisibleCount(5);
            this.mWheelTimeFormat.setAdapter(new ArrayWheelAdapter(Arrays.asList(this.mFormatStrs)));
            WheelView wheelView = this.mWheelTimeFormat;
            int i = this.mCurrentHour;
            wheelView.setCurrentItem((i == 0 || i > 12) ? this.mFormatStrs.length - 1 : 0);
        }
        this.mWheelHour.setItemsVisibleCount(5);
        this.mWheelHour.setCyclic(false);
        if (this.mIsFormat24) {
            this.mWheelHour.setAdapter(new NumericWheelAdapter(0, 23));
            this.mWheelHour.setCurrentItem(this.mCurrentHour);
        } else {
            this.mWheelHour.setAdapter(new NumericWheelAdapter(1, 12));
            int itemsCount = this.mCurrentHour % 12;
            WheelView wheelView2 = this.mWheelHour;
            if (itemsCount == 0) {
                itemsCount = wheelView2.getItemsCount();
            }
            wheelView2.setCurrentItem(itemsCount - 1);
        }
        this.mWheelMinuter.setCyclic(false);
        this.mWheelMinuter.setItemsVisibleCount(5);
        if (this.mMinuteSetAble) {
            this.mWheelMinuter.setAdapter(new NumericWheelAdapter(0, 59));
        } else {
            WheelView wheelView3 = this.mWheelMinuter;
            int i2 = this.mCurrentMin;
            wheelView3.setAdapter(new NumericWheelAdapter(i2, i2));
        }
        this.mWheelMinuter.setCurrentItem(this.mCurrentMin);
    }

    @OnClick({R2.id.tv_confirm})
    public void onConfirmClicked() {
        dismiss();
        if (this.mOnItemSelectedListener != null) {
            int currentItem = this.mWheelHour.getCurrentItem();
            if (!this.mIsFormat24) {
                currentItem = this.mWheelTimeFormat.getCurrentItem() == 0 ? currentItem + 1 : currentItem + 13;
                if (currentItem == 24) {
                    currentItem = 0;
                }
            }
            this.mOnItemSelectedListener.onTimeSelected(this.mWheelTimeFormat.getCurrentItem(), currentItem, this.mWheelMinuter.getCurrentItem());
        }
    }

    @OnClick({R2.id.tv_cancel})
    public void onCancelClicked() {
        dismiss();
    }
}