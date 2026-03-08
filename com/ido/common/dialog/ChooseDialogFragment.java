package com.ido.common.dialog;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ido.common.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.DipPixelUtil;

/* JADX INFO: loaded from: classes2.dex */
public class ChooseDialogFragment extends BaseDialogFragment {
    private static final String CHOOSE_CANCEL = "choosecancel";
    public static final int CHOOSE_CANCEL_NONE = -1;
    private static final String CHOOSE_LIST = "chooselist";
    Button mChooseCancelBtn;
    LinearLayout mChooseContentFl;
    private OnChooseListener mOnChooseListener;

    public interface OnChooseListener {
        void choose(int i);
    }

    public static ChooseDialogFragment newInstance(String[] strArr, boolean z) {
        ChooseDialogFragment chooseDialogFragment = new ChooseDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray(CHOOSE_LIST, strArr);
        bundle.putBoolean(CHOOSE_CANCEL, z);
        chooseDialogFragment.setArguments(bundle);
        chooseDialogFragment.setStyle(1, R.style.AlertDialog_Dark);
        return chooseDialogFragment;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_choose;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initData() {
        Bundle arguments = getArguments();
        initView(arguments.getStringArray(CHOOSE_LIST), arguments.getBoolean(CHOOSE_CANCEL));
    }

    private void initView(String[] strArr, boolean z) {
        if (getDialog() != null && getDialog().getWindow() != null) {
            Window window = getDialog().getWindow();
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 80;
            attributes.width = -1;
            window.setAttributes(attributes);
        }
        this.mChooseCancelBtn = (Button) getView().findViewById(R.id.choose_cancel_btn);
        this.mChooseContentFl = (LinearLayout) getView().findViewById(R.id.choose_content_fl);
        this.mChooseCancelBtn.setOnClickListener(new View.OnClickListener() { // from class: com.ido.common.dialog.ChooseDialogFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChooseDialogFragment.this.cancel();
            }
        });
        this.mChooseCancelBtn.setVisibility(z ? 0 : 8);
        createChooseView(strArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancel() {
        OnChooseListener onChooseListener = this.mOnChooseListener;
        if (onChooseListener != null) {
            onChooseListener.choose(-1);
        }
        dismiss();
    }

    private void createChooseView(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        for (final int i = 0; i < strArr.length; i++) {
            TextView textView = new TextView(getActivity());
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, DipPixelUtil.dip2px(45.0f));
            layoutParams.gravity = 17;
            textView.setLayoutParams(layoutParams);
            textView.setGravity(17);
            textView.setTextSize(2, 15.0f);
            textView.setTextColor(getResources().getColor(R.color.com_color_black_text));
            textView.setBackgroundColor(0);
            textView.setText(strArr[i]);
            this.mChooseContentFl.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.ido.common.dialog.ChooseDialogFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ChooseDialogFragment.this.mOnChooseListener != null) {
                        ChooseDialogFragment.this.mOnChooseListener.choose(i);
                    }
                    ChooseDialogFragment.this.dismiss();
                }
            });
        }
    }

    public void setOnChooseListener(OnChooseListener onChooseListener) {
        this.mOnChooseListener = onChooseListener;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            dismissAllowingStateLoss();
        }
    }
}