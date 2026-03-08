package com.ido.common.base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import butterknife.ButterKnife;
import com.ido.common.R;
import com.ido.common.env.LanguagePreference;
import com.ido.common.utils.DipPixelUtil;
import com.ido.common.utils.LanguageUtil;

/* JADX INFO: loaded from: classes2.dex */
public class BaseDialogFragment extends DialogFragment {
    private String mLanguage;
    private OnDismissionListener mOnDismissionListener;
    private OnShowingListener mOnShowingListener;
    private OrientationListener mOrientationListener;
    protected int mRotation = 3;

    public interface OnDismissionListener {
        void onDismission();
    }

    public interface OnShowingListener {
        void onShowing();
    }

    protected int getLayoutResId() {
        return 0;
    }

    protected void initData() {
    }

    protected void initListener(View view) {
    }

    protected void setRotation(int i) {
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (getLayoutResId() == 0) {
            return super.onCreateView(layoutInflater, viewGroup, bundle);
        }
        return layoutInflater.inflate(getLayoutResId(), viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ButterKnife.bind(this, view);
        getDialog().getWindow().getAttributes().windowAnimations = getWindowAnimations();
        getDialog().getWindow().addFlags(8);
        initData();
        initUI(view);
        initListener(view);
    }

    protected void initUI(View view) {
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        window.getDecorView().setPadding(DipPixelUtil.dip2px(10.0f), 0, DipPixelUtil.dip2px(10.0f), DipPixelUtil.dip2px(15.0f));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 80;
        attributes.width = -1;
        window.setAttributes(attributes);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        getDialog().getWindow().addFlags(8);
        initOrientationListener();
    }

    private void initOrientationListener() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        this.mOrientationListener = new OrientationListener(activity) { // from class: com.ido.common.base.BaseDialogFragment.1
            @Override // com.ido.common.base.OrientationListener
            protected void onRotationChanged(int i) {
                BaseDialogFragment baseDialogFragment = BaseDialogFragment.this;
                baseDialogFragment.mRotation = i;
                baseDialogFragment.setRotation(i);
            }
        };
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        OnDismissionListener onDismissionListener = this.mOnDismissionListener;
        if (onDismissionListener != null) {
            onDismissionListener.onDismission();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        OrientationListener orientationListener = this.mOrientationListener;
        if (orientationListener != null) {
            orientationListener.enable();
        }
        OnShowingListener onShowingListener = this.mOnShowingListener;
        if (onShowingListener != null) {
            onShowingListener.onShowing();
        }
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().clearFlags(8);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        OrientationListener orientationListener = this.mOrientationListener;
        if (orientationListener != null) {
            orientationListener.disable();
        }
    }

    protected int getWindowAnimations() {
        return R.style.DialogAnimSlideInBottom;
    }

    public void show(FragmentManager fragmentManager) {
        try {
            if (fragmentManager.isDestroyed()) {
                return;
            }
            String fragmentTag = getFragmentTag();
            if (fragmentTag == null || !fragmentTag.equals(getClass().getSimpleName()) || fragmentManager.findFragmentByTag(fragmentTag) == null) {
                super.show(fragmentManager, fragmentTag);
            }
            fragmentManager.executePendingTransactions();
            if (getDialog() == null || getDialog().getWindow() == null) {
                return;
            }
            getDialog().getWindow().getDecorView().setSystemUiVisibility(getActivity().getWindow().getDecorView().getSystemUiVisibility());
            getDialog().getWindow().clearFlags(8);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void show(FragmentManager fragmentManager, int i) {
        this.mRotation = i;
        show(fragmentManager);
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        if (getActivity() != null) {
            super.dismiss();
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismissAllowingStateLoss() {
        if (getActivity() != null) {
            super.dismissAllowingStateLoss();
        }
    }

    protected String getFragmentTag() {
        return getClass().getSimpleName();
    }

    protected String getLanguage() {
        LanguagePreference.getInstance(LanguagePreference.Language_Region);
        return LanguagePreference.getLanguageName(getContext());
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        String language = getLanguage();
        if (TextUtils.equals(language, this.mLanguage)) {
            super.onAttach(context);
        } else {
            this.mLanguage = language;
            super.onAttach(LanguageUtil.updateResources(context, language));
        }
    }

    public void setOnDismissionListener(OnDismissionListener onDismissionListener) {
        this.mOnDismissionListener = onDismissionListener;
    }

    public void setOnShowingListner(OnShowingListener onShowingListener) {
        this.mOnShowingListener = onShowingListener;
    }

    public boolean isDialogShowing() {
        return getDialog() != null && getDialog().isShowing();
    }
}