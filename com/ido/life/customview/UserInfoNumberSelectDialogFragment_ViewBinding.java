package com.ido.life.customview;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;
import com.ido.common.widget.view.UserInfoPickerView;

/* JADX INFO: loaded from: classes2.dex */
public class UserInfoNumberSelectDialogFragment_ViewBinding implements Unbinder {
    private UserInfoNumberSelectDialogFragment target;
    private View view7f0a07f9;
    private View view7f0a080e;

    public UserInfoNumberSelectDialogFragment_ViewBinding(final UserInfoNumberSelectDialogFragment userInfoNumberSelectDialogFragment, View view) {
        this.target = userInfoNumberSelectDialogFragment;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_cancel, "field 'mTvCancel' and method 'toCancel'");
        userInfoNumberSelectDialogFragment.mTvCancel = (TextView) Utils.castView(viewFindRequiredView, R.id.tv_cancel, "field 'mTvCancel'", TextView.class);
        this.view7f0a07f9 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.customview.UserInfoNumberSelectDialogFragment_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                userInfoNumberSelectDialogFragment.toCancel(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_confirm, "field 'mTvConfirm' and method 'toConfirm'");
        userInfoNumberSelectDialogFragment.mTvConfirm = (TextView) Utils.castView(viewFindRequiredView2, R.id.tv_confirm, "field 'mTvConfirm'", TextView.class);
        this.view7f0a080e = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.customview.UserInfoNumberSelectDialogFragment_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                userInfoNumberSelectDialogFragment.toConfirm(view2);
            }
        });
        userInfoNumberSelectDialogFragment.mCustomPickerView = (UserInfoPickerView) Utils.findRequiredViewAsType(view, R.id.custom_picker, "field 'mCustomPickerView'", UserInfoPickerView.class);
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        UserInfoNumberSelectDialogFragment userInfoNumberSelectDialogFragment = this.target;
        if (userInfoNumberSelectDialogFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        userInfoNumberSelectDialogFragment.mTvCancel = null;
        userInfoNumberSelectDialogFragment.mTvConfirm = null;
        userInfoNumberSelectDialogFragment.mCustomPickerView = null;
        this.view7f0a07f9.setOnClickListener(null);
        this.view7f0a07f9 = null;
        this.view7f0a080e.setOnClickListener(null);
        this.view7f0a080e = null;
    }
}