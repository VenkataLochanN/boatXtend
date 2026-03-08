package com.ido.ble.watch.custom;

import android.text.TextUtils;
import com.ido.ble.common.k;
import com.ido.ble.logs.LogTool;
import com.ido.ble.watch.custom.callback.PhotoWallpaperOperateCallBack;
import com.ido.ble.watch.custom.callback.WatchPlateCallBack;
import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.ble.watch.custom.model.PhotoWallpaperOperation;
import com.ido.ble.watch.custom.model.WatchPlateFileInfo;
import com.ido.ble.watch.custom.model.WatchPlateOperation;
import com.ido.ble.watch.custom.model.WatchPlateScreenInfo;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class c {
    public static void a(int i, int i2, int i3) {
    }

    public static void a(int i, byte[] bArr, int i2) {
        if (i == 5035) {
            a(bArr);
        }
        if (i == 5036) {
            b(bArr);
            return;
        }
        switch (i) {
            case 5006:
                d(bArr);
                break;
            case 5007:
                e(bArr);
                break;
            case 5008:
                c(bArr);
                break;
        }
    }

    private static void a(byte[] bArr) {
        DialPlateParam dialPlateParam;
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            dialPlateParam = null;
        } else {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[IDO_WATCH_PLATE] [handleDialPlateParam] " + strD);
            dialPlateParam = (DialPlateParam) k.c(strD, DialPlateParam.class);
        }
        WatchPlateCallBack.a(dialPlateParam);
    }

    public static boolean a(int i) {
        if (i == 5035 || i == 5036) {
            return true;
        }
        switch (i) {
            case 5006:
            case 5007:
            case 5008:
            case 5009:
                return true;
            default:
                return false;
        }
    }

    private static void b(byte[] bArr) {
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            WatchPlateCallBack.a((DialPlateParam) null);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f4633a, "[IDO_WATCH_PLATE] [handlePhotoWallpaperOperation] " + strD);
        PhotoWallpaperOperateCallBack.a((PhotoWallpaperOperation.ResponseInfo) k.c(strD, PhotoWallpaperOperation.ResponseInfo.class));
    }

    private static void c(byte[] bArr) {
        WatchPlateOperation watchPlateOperation;
        String str;
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            str = "[IDO_WATCH_PLATE] [handleWatchOperation] jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[IDO_WATCH_PLATE] [handleWatchOperation] " + strD);
            try {
                watchPlateOperation = (WatchPlateOperation) k.c(strD, WatchPlateOperation.class);
            } catch (Exception e2) {
                LogTool.b(com.ido.ble.logs.a.f4633a, "[IDO_WATCH_PLATE] [handleWatchOperation] " + e2.getMessage());
                watchPlateOperation = null;
            }
            if (watchPlateOperation != null) {
                int i = watchPlateOperation.operate;
                if (i == 0) {
                    WatchPlateCallBack.a(watchPlateOperation.fileName);
                    return;
                }
                if (1 == i) {
                    WatchPlateCallBack.b(watchPlateOperation.errCode == 0);
                    return;
                } else {
                    if (2 == i) {
                        WatchPlateCallBack.a(watchPlateOperation.errCode == 0);
                        return;
                    }
                    return;
                }
            }
            str = "[IDO_WATCH_PLATE] [handleWatchOperation] operation is null";
        }
        LogTool.b(com.ido.ble.logs.a.f4633a, str);
    }

    private static void d(byte[] bArr) {
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            WatchPlateCallBack.a((WatchPlateFileInfo) null);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f4633a, "[IDO_WATCH_PLATE] [handleWatchPlateInfo] " + strD);
        WatchPlateFileInfo watchPlateFileInfo = new WatchPlateFileInfo();
        watchPlateFileInfo.fileNameList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(strD);
            watchPlateFileInfo.availableCount = jSONObject.getInt("availableCount");
            watchPlateFileInfo.fileMaxSize = jSONObject.getInt("fileMaxSize");
            watchPlateFileInfo.version = jSONObject.getInt("version");
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("item");
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                    if (jSONObject2.has("fileName")) {
                        watchPlateFileInfo.fileNameList.add(jSONObject2.getString("fileName"));
                    }
                }
            }
            WatchPlateCallBack.a(watchPlateFileInfo);
        } catch (JSONException e2) {
            LogTool.b(com.ido.ble.logs.a.f4633a, com.ido.ble.logs.a.m + e2.getMessage());
            WatchPlateCallBack.a((WatchPlateFileInfo) null);
        }
    }

    private static void e(byte[] bArr) {
        WatchPlateScreenInfo watchPlateScreenInfo;
        String strD = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(strD)) {
            watchPlateScreenInfo = null;
        } else {
            LogTool.d(com.ido.ble.logs.a.f4633a, "[IDO_WATCH_PLATE] [handleWatchScreenInfo] " + strD);
            watchPlateScreenInfo = (WatchPlateScreenInfo) k.c(strD, WatchPlateScreenInfo.class);
        }
        WatchPlateCallBack.a(watchPlateScreenInfo);
    }
}