package com.ido.life.dialog;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSeekBar;
import butterknife.OnClick;
import com.boat.Xtend.two.R;
import com.ido.common.R2;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.widget.textview.MediumTextView;

/* JADX INFO: loaded from: classes2.dex */
public class ChooseBloodReminderDialogFragment extends BaseDialogFragment {
    private static final String REMINDER_VALUE = "REMINDER_VALUE";
    private OnItemSelectedListener mOnItemSelectedListener;
    private MediumTextView mTvOxygen;
    private int mValue;

    public interface OnItemSelectedListener {
        void onItemSelected(int i);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_choose_low_blood;
    }

    public static ChooseBloodReminderDialogFragment newInstance(int i) {
        ChooseBloodReminderDialogFragment chooseBloodReminderDialogFragment = new ChooseBloodReminderDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(REMINDER_VALUE, i);
        chooseBloodReminderDialogFragment.setArguments(bundle);
        chooseBloodReminderDialogFragment.setStyle(1, 2131886083);
        return chooseBloodReminderDialogFragment;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        this.mValue = getArguments().getInt(REMINDER_VALUE);
        initView();
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
        window.setAttributes(attributes);
    }

    private void initView() {
        TextView textView = (TextView) getView().findViewById(R.id.remind_max);
        TextView textView2 = (TextView) getView().findViewById(R.id.remind_min);
        AppCompatSeekBar appCompatSeekBar = (AppCompatSeekBar) getView().findViewById(R.id.seek_oxygen);
        this.mTvOxygen = (MediumTextView) getView().findViewById(R.id.tv_oxygen);
        textView.setText(String.valueOf(90));
        textView2.setText(String.valueOf(75));
        appCompatSeekBar.setMax(15);
        appCompatSeekBar.setProgress(this.mValue - 75);
        this.mTvOxygen.setText(String.valueOf(this.mValue));
        appCompatSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.ido.life.dialog.ChooseBloodReminderDialogFragment.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                ChooseBloodReminderDialogFragment.this.mValue = i + 75;
                ChooseBloodReminderDialogFragment.this.mTvOxygen.setText(String.valueOf(ChooseBloodReminderDialogFragment.this.mValue));
            }
        });
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }

    @OnClick({R2.id.tv_cancel, R2.id.tv_confirm})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.tv_cancel) {
            dismiss();
            return;
        }
        if (id == R.id.tv_confirm) {
            dismiss();
            OnItemSelectedListener onItemSelectedListener = this.mOnItemSelectedListener;
            if (onItemSelectedListener != null) {
                onItemSelectedListener.onItemSelected(this.mValue);
            }
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            dismissAllowingStateLoss();
        }
    }
}