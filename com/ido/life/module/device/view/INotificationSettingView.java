package com.ido.life.module.device.view;

import com.ido.ble.protocol.model.DeviceUnreadReminder;
import com.ido.life.base.IBaseView;
import com.ido.life.bean.HealthMonitoringBean;
import com.ido.life.bean.NotificationApp;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: INotificationSettingView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u0016\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH&J\b\u0010\f\u001a\u00020\u0003H&J\u0016\u0010\r\u001a\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\nH&J\b\u0010\u0010\u001a\u00020\u0003H&J\b\u0010\u0011\u001a\u00020\u0003H&J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u000fH&J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u000fH&J\b\u0010\u0015\u001a\u00020\u0003H&J\b\u0010\u0016\u001a\u00020\u0003H&¨\u0006\u0017"}, d2 = {"Lcom/ido/life/module/device/view/INotificationSettingView;", "Lcom/ido/life/base/IBaseView;", "onGetDeviceUnreadReminderFailed", "", "onGetDeviceUnreadReminderSuccess", "param", "Lcom/ido/ble/protocol/model/DeviceUnreadReminder;", "onGetHealthAppListFailed", "onGetHealthAppListSuccess", "healthApps", "", "Lcom/ido/life/bean/HealthMonitoringBean;", "onGetNotificationAppListFailed", "onGetNotificationAppListSuccess", "notificationApps", "Lcom/ido/life/bean/NotificationApp;", "onSendHealthNotificationStatus2DeviceFailed", "onSendHealthNotificationStatus2DeviceSuccess", "onSendNotificationStatus2DeviceFailed", "mNotificationApp", "onSendNotificationStatus2DeviceSuccess", "onSetDeviceUnreadReminderFailed", "onSetDeviceUnreadReminderSuccess", "app_release"}, k = 1, mv = {1, 1, 16})
public interface INotificationSettingView extends IBaseView {
    void onGetDeviceUnreadReminderFailed();

    void onGetDeviceUnreadReminderSuccess(DeviceUnreadReminder param);

    void onGetHealthAppListFailed();

    void onGetHealthAppListSuccess(List<HealthMonitoringBean> healthApps);

    void onGetNotificationAppListFailed();

    void onGetNotificationAppListSuccess(List<NotificationApp> notificationApps);

    void onSendHealthNotificationStatus2DeviceFailed();

    void onSendHealthNotificationStatus2DeviceSuccess();

    void onSendNotificationStatus2DeviceFailed(NotificationApp mNotificationApp);

    void onSendNotificationStatus2DeviceSuccess(NotificationApp mNotificationApp);

    void onSetDeviceUnreadReminderFailed();

    void onSetDeviceUnreadReminderSuccess();
}