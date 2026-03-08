package com.baidu.mapsdkplatform.comapi.commonutils;

import android.content.Context;
import com.baidu.mapapi.NetworkUtil;
import com.baidu.mapsdkplatform.comapi.util.SysUpdateObserver;

/* JADX INFO: loaded from: classes.dex */
public class SysUpdateUtil implements SysUpdateObserver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static com.baidu.mapsdkplatform.comjni.map.commonmemcache.a f3476a = new com.baidu.mapsdkplatform.comjni.map.commonmemcache.a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f3477b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static String f3478c = "";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f3479d = 0;

    @Override // com.baidu.mapsdkplatform.comapi.util.SysUpdateObserver
    public void init() {
        com.baidu.mapsdkplatform.comjni.map.commonmemcache.a aVar = f3476a;
        if (aVar != null) {
            aVar.a();
            f3476a.b();
        }
    }

    @Override // com.baidu.mapsdkplatform.comapi.util.SysUpdateObserver
    public void updateNetworkInfo(Context context) {
        NetworkUtil.updateNetworkProxy(context);
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x00c2, code lost:
    
        if ("10.0.0.200".equals(r9.trim()) != false) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d5  */
    @Override // com.baidu.mapsdkplatform.comapi.util.SysUpdateObserver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void updateNetworkProxy(android.content.Context r9) {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mapsdkplatform.comapi.commonutils.SysUpdateUtil.updateNetworkProxy(android.content.Context):void");
    }

    @Override // com.baidu.mapsdkplatform.comapi.util.SysUpdateObserver
    public void updatePhoneInfo() {
        com.baidu.mapsdkplatform.comjni.map.commonmemcache.a aVar = f3476a;
        if (aVar != null) {
            aVar.b();
        }
    }
}