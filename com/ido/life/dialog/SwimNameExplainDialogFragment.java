package com.ido.life.dialog;

import android.content.res.Configuration;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.boat.Xtend.two.R;
import com.ido.common.base.BaseDialogFragment;
import com.ido.common.utils.DipPixelUtil;

/* JADX INFO: loaded from: classes2.dex */
public class SwimNameExplainDialogFragment extends BaseDialogFragment {
    @Override // com.ido.common.base.BaseDialogFragment
    protected int getLayoutResId() {
        return R.layout.dialog_swim_name_explain;
    }

    public static SwimNameExplainDialogFragment newInstance() {
        SwimNameExplainDialogFragment swimNameExplainDialogFragment = new SwimNameExplainDialogFragment();
        swimNameExplainDialogFragment.setStyle(1, 2131886083);
        return swimNameExplainDialogFragment;
    }

    @Override // com.ido.common.base.BaseDialogFragment
    protected void initUI(View view) {
        if (getDialog() == null || getDialog().getWindow() == null) {
            return;
        }
        Window window = getDialog().getWindow();
        window.getDecorView().setPadding(DipPixelUtil.dip2px(10.0f), 0, DipPixelUtil.dip2px(10.0f), 0);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = 17;
        attributes.width = -1;
        window.setAttributes(attributes);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            dismissAllowingStateLoss();
        }
    }
}