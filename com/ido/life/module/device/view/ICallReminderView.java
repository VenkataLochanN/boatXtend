package com.ido.life.module.device.view;

import com.ido.life.base.IBaseView;

/* JADX INFO: loaded from: classes2.dex */
public interface ICallReminderView extends IBaseView {
    void hideLoading();

    void onCallReminderStatusSetFailed();

    void onCallReminderStatusSetSuccess();

    void showLoading();
}