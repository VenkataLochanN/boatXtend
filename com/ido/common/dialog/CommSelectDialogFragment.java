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
import com.ido.common.widget.textview.RegularTextView;
import com.watch.life.wheelview.adapter.ArrayWheelAdapter;
import com.watch.life.wheelview.view.WheelView;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class CommSelectDialogFragment extends BaseDialogFragment {
    private static final String INDEX = " index";
    private static final String ITEMS = " items";
    private static final String SHOW_CANCEL = "show_cancel";
    private static final String UNIT = "unit";
    private static final String VISIBLE_COUNT = "visible_count";
    private String[] mItems;
    private OnItemSelectedListener mOnItemSelectedListener;

    @BindView(R2.id.rtv_unit)
    RegularTextView mRtvUnit;
    private int mSelectedIndex;
    private boolean mShowCancel = true;

    @BindView(R2.id.tv_cancel)
    TextView mTvCancel;

    @BindView(R2.id.tv_confirm)
    TextView mTvConfirm;
    private String mUnit;
    private int mVisibleCount;

    @BindView(R2.id.wheel_view)
    WheelView mWheelView;

    public interface OnItemSelectedListener {
        void onItemSelected(int i);
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_comm_select_picker;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getWindowAnimations() {
        return R.style.DialogAnimSlideInBottom;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }

    public static CommSelectDialogFragment newInstance(String[] strArr, int i) {
        return newInstance(strArr, "", i, 3, true);
    }

    public static CommSelectDialogFragment newInstance(String[] strArr, String str, int i, int i2) {
        return newInstance(strArr, str, i, i2, true);
    }

    public static CommSelectDialogFragment newInstance(String[] strArr, String str, int i, int i2, boolean z) {
        CommSelectDialogFragment commSelectDialogFragment = new CommSelectDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray(ITEMS, strArr);
        bundle.putInt(INDEX, i);
        bundle.putInt(VISIBLE_COUNT, i2);
        bundle.putBoolean(SHOW_CANCEL, z);
        bundle.putString(UNIT, str);
        commSelectDialogFragment.setArguments(bundle);
        commSelectDialogFragment.setStyle(1, R.style.AlertDialog_Dark);
        return commSelectDialogFragment;
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
            this.mItems = arguments.getStringArray(ITEMS);
            this.mSelectedIndex = arguments.getInt(INDEX);
            this.mVisibleCount = arguments.getInt(VISIBLE_COUNT);
            this.mShowCancel = arguments.getBoolean(SHOW_CANCEL);
            this.mUnit = arguments.getString(UNIT);
            initView();
        }
        this.mTvCancel.setVisibility(this.mShowCancel ? 0 : 8);
    }

    private void initView() {
        this.mWheelView.setAdapter(new ArrayWheelAdapter(Arrays.asList(this.mItems)));
        this.mWheelView.setCurrentItem(this.mSelectedIndex);
        this.mWheelView.setItemsVisibleCount(this.mVisibleCount);
        this.mWheelView.setCyclic(false);
        if (TextUtils.isEmpty(this.mUnit)) {
            this.mRtvUnit.setVisibility(8);
        } else {
            this.mRtvUnit.setVisibility(0);
            this.mRtvUnit.setText(this.mUnit);
        }
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
                onItemSelectedListener.onItemSelected(this.mWheelView.getCurrentItem());
            }
        }
    }
}