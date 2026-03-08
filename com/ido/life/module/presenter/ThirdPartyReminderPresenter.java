package com.ido.life.module.presenter;

import com.ido.life.base.BasePresenter;
import com.ido.life.bean.SwitchStatus;
import com.ido.life.module.view.IThirdPartyReminderView;
import com.ido.life.util.AppLogUploadManager;
import com.ido.life.util.SPHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ThirdPartyReminderPresenter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u0010\u0010\t\u001a\u00020\n2\b\u0010\b\u001a\u0004\u0018\u00010\u0005J\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0007¨\u0006\r"}, d2 = {"Lcom/ido/life/module/presenter/ThirdPartyReminderPresenter;", "Lcom/ido/life/base/BasePresenter;", "Lcom/ido/life/module/view/IThirdPartyReminderView;", "()V", "getNotificationStatus", "Lcom/ido/life/bean/SwitchStatus$NotificationSwitch;", "isStatusChanged", "", "notificationSwitch", "saveNotificationStatus", "", "setMissCallSwitch", "isSwitchOn", "app_release"}, k = 1, mv = {1, 1, 16})
public final class ThirdPartyReminderPresenter extends BasePresenter<IThirdPartyReminderView> {
    public final SwitchStatus.NotificationSwitch getNotificationStatus() {
        SwitchStatus.NotificationSwitch notificationStatus = SPHelper.getNotificationStatus();
        Intrinsics.checkExpressionValueIsNotNull(notificationStatus, "SPHelper.getNotificationStatus()");
        return notificationStatus;
    }

    public final boolean isStatusChanged(SwitchStatus.NotificationSwitch notificationSwitch) {
        Intrinsics.checkParameterIsNotNull(notificationSwitch, "notificationSwitch");
        return !Intrinsics.areEqual(notificationSwitch, getNotificationStatus());
    }

    public final void saveNotificationStatus(SwitchStatus.NotificationSwitch notificationSwitch) {
        AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_MESSAGE_NOTIFICATION_SUCCESS, "", null);
        SPHelper.setNotificationStatus(notificationSwitch);
    }

    public final void setMissCallSwitch(boolean isSwitchOn) {
        SwitchStatus switchStatus = SPHelper.getSwitchStatus();
        switchStatus.notificationSwitch.missedCall = isSwitchOn;
        SPHelper.setSwitchStatus(switchStatus);
    }
}