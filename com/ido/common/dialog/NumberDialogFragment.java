package com.ido.common.dialog;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.ido.common.widget.view.CustomPickerView;

/* JADX INFO: loaded from: classes2.dex */
public class NumberDialogFragment extends BaseDialogFragment {
    private static final String CHOOSE_DATA = "choosedata";
    private static final String CHOOSE_MAX = "choosemax";
    private static final String CHOOSE_MIN = "choosemin";
    private static final String CHOOSE_UNIT = "chooseunit";

    @BindView(R2.id.custom_picker)
    CustomPickerView mCustomPickerView;
    private OnItemSelectedListener mOnItemSelectedListener;

    @BindView(R2.id.tv_cancel)
    TextView mTvCancel;

    @BindView(R2.id.tv_confirm)
    TextView mTvConfirm;

    public interface OnItemSelectedListener {
        void onItemSelected(int i);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getWindowAnimations() {
        return R.style.DialogAnimSlideInBottom;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }

    public static NumberDialogFragment newInstance(int i, int i2, int i3, String str) {
        NumberDialogFragment numberDialogFragment = new NumberDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CHOOSE_MIN, i);
        bundle.putInt(CHOOSE_MAX, i2);
        bundle.putInt(CHOOSE_DATA, i3);
        if (!TextUtils.isEmpty(str)) {
            bundle.putString(CHOOSE_UNIT, str);
        }
        numberDialogFragment.setArguments(bundle);
        numberDialogFragment.setStyle(1, R.style.AlertDialog_Dark);
        return numberDialogFragment;
    }

    public void setCurrentValue(int i) {
        this.mCustomPickerView.setCurrentValue(i);
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
        this.mTvCancel.setTextColor(ResourceUtil.getColor(R.color.color_FA791F));
        this.mTvConfirm.setTextColor(ResourceUtil.getColor(R.color.color_FA791F));
        this.mCustomPickerView.setUnitTextColor(ResourceUtil.getColor(R.color.color_FA791F));
        this.mCustomPickerView.setWeelCenterTextColor(ResourceUtil.getColor(R.color.com_color_red));
        this.mCustomPickerView.setWeelOutTextColor(ResourceUtil.getColor(R.color.white));
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        super.initData();
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mCustomPickerView.setData(arguments.getInt(CHOOSE_MIN), arguments.getInt(CHOOSE_MAX), arguments.getInt(CHOOSE_DATA), arguments.getString(CHOOSE_UNIT));
        }
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_pick;
    }

    @OnClick({R2.id.tv_cancel})
    public void toCancel(View view) {
        dismissAllowingStateLoss();
    }

    @OnClick({R2.id.tv_confirm})
    public void toConfirm(View view) {
        int i = this.mCustomPickerView.getmData();
        OnItemSelectedListener onItemSelectedListener = this.mOnItemSelectedListener;
        if (onItemSelectedListener != null) {
            onItemSelectedListener.onItemSelected(i);
        }
        dismissAllowingStateLoss();
    }
}