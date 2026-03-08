package com.ido.life.dialog;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.boat.Xtend.two.R;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationPermissionSettingDialog_ViewBinding implements Unbinder {
    private NotificationPermissionSettingDialog target;
    private View view7f0a07f9;
    private View view7f0a080e;

    public NotificationPermissionSettingDialog_ViewBinding(final NotificationPermissionSettingDialog notificationPermissionSettingDialog, View view) {
        this.target = notificationPermissionSettingDialog;
        View viewFindRequiredView = Utils.findRequiredView(view, R.id.tv_cancel, "method 'onClick'");
        this.view7f0a07f9 = viewFindRequiredView;
        viewFindRequiredView.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.NotificationPermissionSettingDialog_ViewBinding.1
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                notificationPermissionSettingDialog.onClick(view2);
            }
        });
        View viewFindRequiredView2 = Utils.findRequiredView(view, R.id.tv_confirm, "method 'onClick'");
        this.view7f0a080e = viewFindRequiredView2;
        viewFindRequiredView2.setOnClickListener(new DebouncingOnClickListener() { // from class: com.ido.life.dialog.NotificationPermissionSettingDialog_ViewBinding.2
            @Override // butterknife.internal.DebouncingOnClickListener
            public void doClick(View view2) {
                notificationPermissionSettingDialog.onClick(view2);
            }
        });
    }

    @Override // butterknife.Unbinder
    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view7f0a07f9.setOnClickListener(null);
        this.view7f0a07f9 = null;
        this.view7f0a080e.setOnClickListener(null);
        this.view7f0a080e = null;
    }
}