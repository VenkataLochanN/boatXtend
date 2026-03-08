package com.ido.life.module.device.presenter;

import android.content.pm.PackageInfo;
import com.ido.ble.BLEManager;
import com.ido.ble.LocalDataManager;
import com.ido.ble.callback.SettingCallBack;
import com.ido.ble.protocol.model.DeviceUnreadReminder;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.bean.SwitchStatus;
import com.ido.life.constants.Constants;
import com.ido.life.module.device.view.INotificationView;
import com.ido.life.util.AppLogUploadManager;
import com.ido.life.util.AsyncTaskUtil;
import com.ido.life.util.MsgNotificationHelper;
import com.ido.life.util.SPHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class NotificationPresenter extends BaseCmdPresenter<INotificationView> {
    private List<String> mAllPackage;
    DeviceUnreadReminder mDeviceUnreadReminder;
    private SupportFunctionInfo mFunctionInfo;

    @Override // com.ido.life.base.BaseCmdPresenter, com.ido.life.base.BasePresenter
    public void init() {
        super.init();
        this.mFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        this.mAllPackage = new ArrayList();
        new AsyncTaskUtil(new AsyncTaskUtil.IAsyncTaskCallBack() { // from class: com.ido.life.module.device.presenter.NotificationPresenter.1
            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public Object doInBackground(String... strArr) {
                NotificationPresenter.this.getAllApp();
                return null;
            }

            @Override // com.ido.life.util.AsyncTaskUtil.IAsyncTaskCallBack
            public void onPostExecute(Object obj) {
                if (NotificationPresenter.this.isAttachView()) {
                    ((INotificationView) NotificationPresenter.this.getView()).onGetAllInstalledApp();
                }
            }
        }).execute("");
    }

    public SwitchStatus.NotificationSwitch getNotificationStatus() {
        return SPHelper.getNotificationStatus();
    }

    public boolean isStatusChanged(SwitchStatus.NotificationSwitch notificationSwitch) {
        return !notificationSwitch.toString().equals(getNotificationStatus().toString());
    }

    public void saveNotificationStatus(SwitchStatus.NotificationSwitch notificationSwitch) {
        AppLogUploadManager.uploadLog(AppLogUploadManager.LogInfo.DEVICE_CONTROL_MESSAGE_NOTIFICATION_SUCCESS, "", null);
        SPHelper.setNotificationStatus(notificationSwitch);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAllApp() {
        for (PackageInfo packageInfo : IdoApp.getAppContext().getPackageManager().getInstalledPackages(0)) {
            CommonLogUtil.d("PackageInfo = " + packageInfo.applicationInfo.toString());
            if (!this.mAllPackage.contains(packageInfo.packageName)) {
                this.mAllPackage.add(packageInfo.packageName);
            }
        }
        MsgNotificationHelper.saveLog("all installed APP count : " + this.mAllPackage.size());
    }

    public void setMissCallSwitch(boolean z) {
        SwitchStatus switchStatus = SPHelper.getSwitchStatus();
        switchStatus.notificationSwitch.missedCall = z;
        SPHelper.setSwitchStatus(switchStatus);
    }

    public boolean checkAppInstalled(String str) {
        return this.mAllPackage.isEmpty() || this.mAllPackage.contains(str);
    }

    public boolean hasSkype() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.skype && (checkAppInstalled("com.skype") || checkAppInstalled("com.skype.insiders") || checkAppInstalled("com.skype.raider"));
    }

    public boolean isSupportMissedCalls() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.V3_support_missed_calls;
    }

    public boolean hasFacebook() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.noticeFacebook && checkAppInstalled("com.facebook.katana");
    }

    public boolean hasInstagram() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.instagram && checkAppInstalled("com.instagram.android");
    }

    public boolean hasWechat() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.noticeWeixin && checkAppInstalled("com.tencent.mm");
    }

    public boolean hasGoogleMeet() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.V3_support_Google_meet && checkAppInstalled(Constants.AppPackage.GOOGLE_MEET);
    }

    public boolean hasLinkedin() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.linked_in && checkAppInstalled("com.linkedin.android");
    }

    public boolean hasTwitter() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.noticeTwitter && checkAppInstalled("com.twitter.android");
    }

    public boolean hasWhatsApp() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.whatsapp && checkAppInstalled("com.whatsapp");
    }

    public boolean hasQQ() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.noticeQQ && (checkAppInstalled("com.tencent.mobileqq") || checkAppInstalled("com.tencent.qqlite"));
    }

    public boolean hasKakaoTalk() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.KakaoTalk && checkAppInstalled("com.kakao.talk");
    }

    public boolean hasViber() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.Viber && checkAppInstalled("com.viber.voip");
    }

    public boolean hasLine() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.Line && (checkAppInstalled("jp.naver.line.android") || checkAppInstalled("line.android"));
    }

    public boolean hasVKontakte() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.VKontakte && checkAppInstalled("com.vkontakte.android");
    }

    public boolean hasGmail() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.Gmail && checkAppInstalled("com.google.android.gm");
    }

    public boolean hasOutlook() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.Outlook && checkAppInstalled("com.microsoft.office.outlook");
    }

    public boolean hasSnapchat() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.Snapchat && checkAppInstalled("com.snapchat.android");
    }

    public boolean hasMessengre() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.messengre && checkAppInstalled("com.facebook.orca");
    }

    public boolean hasSlack() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.notice_slack && checkAppInstalled("com.Slack");
    }

    public boolean hasChatwork() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.notice_chatwork && checkAppInstalled("jp.ecstudio.chatworkandroid");
    }

    public boolean hasTelegram() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.Telegram && checkAppInstalled("org.telegram.messenger");
    }

    public boolean hasYoutube() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.notice_youtube && checkAppInstalled("com.google.android.youtube");
    }

    public boolean hasPinterest() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.notice_pinterest_yahoo && checkAppInstalled("com.pinterest");
    }

    public boolean hasYahoo() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.notice_yahoo_mail && (checkAppInstalled("jp.co.yahoo.android.ymail") || checkAppInstalled("jg.works.yahoo.mail") || checkAppInstalled("com.yahoo.mobile.client.android.mail"));
    }

    public boolean hasTumblr() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.notice_tumblr && checkAppInstalled("com.tumblr");
    }

    public boolean hasTikTok() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.notice_TikTok && (checkAppInstalled("com.zhiliaoapp.musically") || checkAppInstalled("com.ss.android.ugc.trill"));
    }

    public boolean hasWhatsAppBusiness() {
        SupportFunctionInfo supportFunctionInfo = this.mFunctionInfo;
        return supportFunctionInfo != null && supportFunctionInfo.V3_support_WhatsApp_Business && checkAppInstalled("com.whatsapp.w4b");
    }

    public boolean isSupportRedPoint() {
        return getSupportFunctionInfo().v2_set_unread_app_reminder_03_EA;
    }

    public void getDeviceUnreadReminder() {
        DeviceUnreadReminder deviceUnreadReminder = SPHelper.getDeviceUnreadReminder();
        if (deviceUnreadReminder == null) {
            deviceUnreadReminder = new DeviceUnreadReminder();
            deviceUnreadReminder.on_off = 170;
        }
        ((INotificationView) getView()).onGetDeviceUnreadReminderSuccess(deviceUnreadReminder);
    }

    public void setDeviceUnreadReminder(DeviceUnreadReminder deviceUnreadReminder) {
        MsgNotificationHelper.saveLog("NotificationSettingPresenter：setDeviceUnreadReminder on_off = ${param.on_off}");
        this.mDeviceUnreadReminder = deviceUnreadReminder;
        BLEManager.setDeviceUnreadReminder(deviceUnreadReminder);
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    public void onSetCmdFailed(SettingCallBack.SettingType settingType) {
        super.onSetCmdFailed(settingType);
        if (settingType == SettingCallBack.SettingType.DEVICE_UNREAD_REMINDER) {
            MsgNotificationHelper.saveLog("NotificationSettingPresenter：setDeviceUnreadReminder onSetCmdFailed ");
            this.mDeviceUnreadReminder = null;
            ((INotificationView) getView()).onSetDeviceUnreadReminderFailed();
        }
    }

    @Override // com.ido.life.base.BaseCmdPresenter
    public void onSetCmdSuccess(SettingCallBack.SettingType settingType, Object obj) {
        super.onSetCmdSuccess(settingType, obj);
        if (settingType == SettingCallBack.SettingType.DEVICE_UNREAD_REMINDER) {
            MsgNotificationHelper.saveLog("NotificationSettingPresenter：setDeviceUnreadReminder onSetCmdSuccess ");
            SPHelper.setDeviceUnreadReminder(this.mDeviceUnreadReminder);
            ((INotificationView) getView()).onSetDeviceUnreadReminderSuccess();
        }
    }
}