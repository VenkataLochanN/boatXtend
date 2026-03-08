package com.ido.life.module.device.view;

import com.ido.ble.protocol.model.DeviceUnreadReminder;
import com.ido.life.base.IBaseView;

/* JADX INFO: loaded from: classes2.dex */
public interface INotificationView extends IBaseView {
    void onGetAllInstalledApp();

    void onGetDeviceUnreadReminderFailed();

    void onGetDeviceUnreadReminderSuccess(DeviceUnreadReminder deviceUnreadReminder);

    void onSetDeviceUnreadReminderFailed();

    void onSetDeviceUnreadReminderSuccess();
}