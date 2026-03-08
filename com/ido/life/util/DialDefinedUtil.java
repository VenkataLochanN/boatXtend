package com.ido.life.util;

import android.text.TextUtils;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.ble.LocalDataManager;
import com.ido.life.data.api.entity.DeviceInfo;
import com.ido.life.data.device.remote.DeviceManager;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class DialDefinedUtil {
    public static String appFaceVersion = null;
    public static String mSupportVersion = "-1";

    public static void getDialDefinedVersion(final DeviceManager.OnDeviceCallback<String> onDeviceCallback) {
        appFaceVersion = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
        final int i = LocalDataManager.getBasicInfo().user_defined_dial_main_version;
        if (i == 0) {
            mSupportVersion = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
            onDeviceCallback.onSuccess(mSupportVersion);
        } else {
            DeviceManager.queryDeviceInfoList(new DeviceManager.OnDeviceCallback<List<DeviceInfo>>() { // from class: com.ido.life.util.DialDefinedUtil.1
                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onSuccess(List<DeviceInfo> list) {
                    if (onDeviceCallback != null) {
                        if (list == null || list.size() == 0) {
                            onDeviceCallback.onSuccess(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                            return;
                        }
                        if (list == null || list.size() == 0 || TextUtils.isEmpty(list.get(0).getDeviceName())) {
                            DialDefinedUtil.appFaceVersion = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
                        } else {
                            DialDefinedUtil.getAppFaceVersion(list.get(0).getDeviceName());
                        }
                        DialDefinedUtil.mSupportVersion = String.valueOf(i);
                        onDeviceCallback.onSuccess(DialDefinedUtil.mSupportVersion);
                    }
                }

                @Override // com.ido.life.data.device.remote.DeviceManager.OnDeviceCallback
                public void onFailed(int i2, String str) {
                    DeviceManager.OnDeviceCallback onDeviceCallback2 = onDeviceCallback;
                    if (onDeviceCallback2 != null) {
                        onDeviceCallback2.onSuccess(AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                    }
                }
            }, LocalDataManager.getBasicInfo().deivceId + "");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getAppFaceVersion(java.lang.String r5) {
        /*
            java.lang.String r0 = "0"
            com.ido.life.util.DialDefinedUtil.appFaceVersion = r0
            int r0 = r5.hashCode()
            r1 = 4
            r2 = 3
            r3 = 2
            r4 = 1
            switch(r0) {
                case -1937263791: goto L38;
                case 2197422: goto L2e;
                case 68158540: goto L24;
                case 69492413: goto L1a;
                case 2111724002: goto L10;
                default: goto Lf;
            }
        Lf:
            goto L42
        L10:
            java.lang.String r0 = "GT01-A"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L42
            r5 = r3
            goto L43
        L1a:
            java.lang.String r0 = "ID206"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L42
            r5 = r4
            goto L43
        L24:
            java.lang.String r0 = "GTX01"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L42
            r5 = r1
            goto L43
        L2e:
            java.lang.String r0 = "GT01"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L42
            r5 = 0
            goto L43
        L38:
            java.lang.String r0 = "ID208BT"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L42
            r5 = r2
            goto L43
        L42:
            r5 = -1
        L43:
            java.lang.String r0 = "1"
            if (r5 == 0) goto L60
            if (r5 == r4) goto L5d
            if (r5 == r3) goto L5a
            if (r5 == r2) goto L55
            if (r5 == r1) goto L50
            goto L62
        L50:
            java.lang.String r5 = "5"
            com.ido.life.util.DialDefinedUtil.appFaceVersion = r5
            goto L62
        L55:
            java.lang.String r5 = "3"
            com.ido.life.util.DialDefinedUtil.appFaceVersion = r5
            goto L62
        L5a:
            com.ido.life.util.DialDefinedUtil.appFaceVersion = r0
            goto L62
        L5d:
            com.ido.life.util.DialDefinedUtil.appFaceVersion = r0
            goto L62
        L60:
            com.ido.life.util.DialDefinedUtil.appFaceVersion = r0
        L62:
            java.lang.String r5 = com.ido.life.util.DialDefinedUtil.appFaceVersion
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ido.life.util.DialDefinedUtil.getAppFaceVersion(java.lang.String):java.lang.String");
    }
}