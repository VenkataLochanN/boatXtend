package com.ido.ble.callback;

import com.ido.ble.f.a.c;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.ActivityDataCount;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.BatteryInfo;
import com.ido.ble.protocol.model.CanDownLangInfo;
import com.ido.ble.protocol.model.CanDownLangInfoV3;
import com.ido.ble.protocol.model.DeviceSummarySoftVersionInfo;
import com.ido.ble.protocol.model.FlashBinInfo;
import com.ido.ble.protocol.model.HIDInfo;
import com.ido.ble.protocol.model.LiveData;
import com.ido.ble.protocol.model.MacAddressInfo;
import com.ido.ble.protocol.model.NoticeReminderSwitchStatus;
import com.ido.ble.protocol.model.NoticeSwitchInfo;
import com.ido.ble.protocol.model.SNInfo;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import com.ido.ble.protocol.model.SystemTime;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class GetDeviceInfoCallBack {

    public interface ICallBack {
        void onGetActivityCount(ActivityDataCount activityDataCount);

        void onGetBasicInfo(BasicInfo basicInfo);

        void onGetBatteryInfo(BatteryInfo batteryInfo);

        void onGetCanDownloadLangInfo(CanDownLangInfo canDownLangInfo);

        void onGetCanDownloadLangInfoV3(CanDownLangInfoV3 canDownLangInfoV3);

        void onGetDeviceSummarySoftVersionInfo(DeviceSummarySoftVersionInfo deviceSummarySoftVersionInfo);

        void onGetFlashBinInfo(FlashBinInfo flashBinInfo);

        void onGetFunctionTable(SupportFunctionInfo supportFunctionInfo);

        void onGetHIDInfo(HIDInfo hIDInfo);

        void onGetLiveData(LiveData liveData);

        void onGetMacAddress(MacAddressInfo macAddressInfo);

        @Deprecated
        void onGetNoticeCenterSwitchStatus(NoticeSwitchInfo noticeSwitchInfo);

        void onGetNoticeReminderSwitchStatus(NoticeReminderSwitchStatus noticeReminderSwitchStatus);

        @Deprecated
        void onGetSNInfo(SNInfo sNInfo);

        @Deprecated
        void onGetTime(SystemTime systemTime);
    }

    public static void a(final ActivityDataCount activityDataCount) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.11
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.K().r()) {
                    if (com.ido.ble.i.a.a.Y()) {
                        if (b.K().r().size() > 1) {
                            LogTool.b(com.ido.ble.logs.a.f4633a, "[GET_INFO] is in 'sync config' state, 'GetDeviceInfoCallBack.IOperateCallBack' is disable!");
                        }
                        if (iCallBack.getClass() == c.b.class) {
                            iCallBack.onGetActivityCount(activityDataCount);
                            return;
                        }
                    } else {
                        iCallBack.onGetActivityCount(activityDataCount);
                    }
                }
            }
        });
    }

    public static void a(final BasicInfo basicInfo) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.K().r()) {
                    if (com.ido.ble.i.a.a.Y()) {
                        if (b.K().r().size() > 1) {
                            LogTool.b(com.ido.ble.logs.a.f4633a, "[GET_INFO] is in 'sync config' state, 'GetDeviceInfoCallBack.IOperateCallBack' is disable!");
                        }
                        if (iCallBack.getClass() == c.b.class) {
                            iCallBack.onGetBasicInfo(basicInfo);
                            return;
                        }
                    } else {
                        iCallBack.onGetBasicInfo(basicInfo);
                    }
                }
            }
        });
    }

    @Deprecated
    public static void a(final BatteryInfo batteryInfo) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().r().iterator();
                while (it.hasNext()) {
                    it.next().onGetBatteryInfo(batteryInfo);
                }
            }
        });
    }

    @Deprecated
    public static void a(final CanDownLangInfo canDownLangInfo) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.13
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().r().iterator();
                while (it.hasNext()) {
                    it.next().onGetCanDownloadLangInfo(canDownLangInfo);
                }
            }
        });
    }

    @Deprecated
    public static void a(final CanDownLangInfoV3 canDownLangInfoV3) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.14
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().r().iterator();
                while (it.hasNext()) {
                    it.next().onGetCanDownloadLangInfoV3(canDownLangInfoV3);
                }
            }
        });
    }

    @Deprecated
    public static void a(final DeviceSummarySoftVersionInfo deviceSummarySoftVersionInfo) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.12
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().r().iterator();
                while (it.hasNext()) {
                    it.next().onGetDeviceSummarySoftVersionInfo(deviceSummarySoftVersionInfo);
                }
            }
        });
    }

    @Deprecated
    public static void a(final FlashBinInfo flashBinInfo) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.6
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().r().iterator();
                while (it.hasNext()) {
                    it.next().onGetFlashBinInfo(flashBinInfo);
                }
            }
        });
    }

    public static void a(final HIDInfo hIDInfo) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.10
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.K().r()) {
                    if (com.ido.ble.i.a.a.Y()) {
                        if (b.K().r().size() > 1) {
                            LogTool.b(com.ido.ble.logs.a.f4633a, "[GET_INFO] is in 'sync config' state, 'GetDeviceInfoCallBack.IOperateCallBack' is disable!");
                        }
                        if (iCallBack.getClass() == c.b.class) {
                        }
                    }
                    iCallBack.onGetHIDInfo(hIDInfo);
                }
            }
        });
    }

    public static void a(final LiveData liveData) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.9
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.K().r()) {
                    if (com.ido.ble.i.a.a.Y()) {
                        if (b.K().r().size() > 1) {
                            LogTool.b(com.ido.ble.logs.a.f4633a, "[GET_INFO] is in 'sync config' state, 'GetDeviceInfoCallBack.IOperateCallBack' is disable!");
                        }
                        if (iCallBack.getClass() == c.b.class) {
                            iCallBack.onGetLiveData(liveData);
                            return;
                        }
                    } else {
                        iCallBack.onGetLiveData(liveData);
                    }
                }
            }
        });
    }

    public static void a(final MacAddressInfo macAddressInfo) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.K().r()) {
                    if (com.ido.ble.i.a.a.Y()) {
                        if (b.K().r().size() > 1) {
                            LogTool.b(com.ido.ble.logs.a.f4633a, "[GET_INFO] is in 'sync config' state, 'GetDeviceInfoCallBack.IOperateCallBack' is disable!");
                        }
                        if (iCallBack.getClass() == c.b.class) {
                            iCallBack.onGetMacAddress(macAddressInfo);
                            return;
                        }
                    } else {
                        iCallBack.onGetMacAddress(macAddressInfo);
                    }
                }
            }
        });
    }

    @Deprecated
    public static void a(final NoticeReminderSwitchStatus noticeReminderSwitchStatus) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.15
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().r().iterator();
                while (it.hasNext()) {
                    it.next().onGetNoticeReminderSwitchStatus(noticeReminderSwitchStatus);
                }
            }
        });
    }

    @Deprecated
    public static void a(final NoticeSwitchInfo noticeSwitchInfo) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.8
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().r().iterator();
                while (it.hasNext()) {
                    it.next().onGetNoticeCenterSwitchStatus(noticeSwitchInfo);
                }
            }
        });
    }

    @Deprecated
    public static void a(final SNInfo sNInfo) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.7
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().r().iterator();
                while (it.hasNext()) {
                    it.next().onGetSNInfo(sNInfo);
                }
            }
        });
    }

    public static void a(final SupportFunctionInfo supportFunctionInfo) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.K().r()) {
                    if (com.ido.ble.i.a.a.Y()) {
                        if (b.K().r().size() > 1) {
                            LogTool.b(com.ido.ble.logs.a.f4633a, "[GET_INFO] is in 'sync config' state, 'GetDeviceInfoCallBack.IOperateCallBack' is disable!");
                        }
                        if (iCallBack.getClass() == c.b.class) {
                            iCallBack.onGetFunctionTable(supportFunctionInfo);
                            return;
                        }
                    } else {
                        iCallBack.onGetFunctionTable(supportFunctionInfo);
                    }
                }
            }
        });
    }

    public static void a(final SystemTime systemTime) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.GetDeviceInfoCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.K().r()) {
                    if (com.ido.ble.i.a.a.Y()) {
                        if (b.K().r().size() > 1) {
                            LogTool.b(com.ido.ble.logs.a.f4633a, "[GET_INFO] is in 'sync config' state, 'GetDeviceInfoCallBack.IOperateCallBack' is disable!");
                        }
                        if (iCallBack.getClass() == c.b.class) {
                            iCallBack.onGetTime(systemTime);
                            return;
                        }
                    } else {
                        iCallBack.onGetTime(systemTime);
                    }
                }
            }
        });
    }
}