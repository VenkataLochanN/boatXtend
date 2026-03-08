package com.baidu.mapsdkplatform.comapi;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.VersionInfo;
import com.baidu.mapsdkplatform.comapi.util.PermissionCheck;
import com.baidu.mapsdkplatform.comapi.util.SysUpdateObservable;
import com.baidu.mapsdkplatform.comapi.util.i;

/* JADX INFO: loaded from: classes.dex */
public class a implements PermissionCheck.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static a f3407g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f3409b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f3410c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private e f3411d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f3412e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3413f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3406a = a.class.getSimpleName();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static int f3408h = -100;

    static {
        NativeLoader.getInstance().loadLibrary(VersionInfo.getKitName());
        com.baidu.mapsdkplatform.comjni.tools.a.b();
    }

    private a() {
    }

    public static a a() {
        if (f3407g == null) {
            f3407g = new a();
        }
        return f3407g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Message message) {
        Intent intent;
        if (message.what != 2012) {
            if (message.arg2 == 3) {
                this.f3409b.sendBroadcast(new Intent(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR));
            }
            if (message.arg2 != 2 && message.arg2 != 404 && message.arg2 != 5 && message.arg2 != 8) {
                return;
            } else {
                intent = new Intent(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
            }
        } else if (message.arg1 == 0) {
            intent = new Intent(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
        } else {
            Intent intent2 = new Intent(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
            intent2.putExtra(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE, message.arg1);
            intent2.putExtra(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_MESSAGE, (String) message.obj);
            intent = intent2;
        }
        this.f3409b.sendBroadcast(intent);
    }

    private void f() {
        e eVar;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        Context context = this.f3409b;
        if (context == null || (eVar = this.f3411d) == null) {
            return;
        }
        context.registerReceiver(eVar, intentFilter);
    }

    private void g() {
        Context context;
        e eVar = this.f3411d;
        if (eVar == null || (context = this.f3409b) == null) {
            return;
        }
        context.unregisterReceiver(eVar);
    }

    public void a(Context context) {
        this.f3409b = context;
    }

    @Override // com.baidu.mapsdkplatform.comapi.util.PermissionCheck.c
    public void a(PermissionCheck.b bVar) {
        if (bVar == null) {
            return;
        }
        if (bVar.f3818a == 0) {
            i.y = bVar.f3822e;
            i.a(bVar.f3819b, bVar.f3820c);
        } else {
            Log.e("baidumapsdk", "Authentication Error\n" + bVar.toString());
        }
        if (bVar.f3818a != PermissionCheck.f3811b && bVar.f3818a != PermissionCheck.f3810a && bVar.f3818a != PermissionCheck.f3812c) {
            com.baidu.mapsdkplatform.comapi.util.d.a().a(bVar.f3823f);
        }
        if (this.f3410c == null || bVar.f3818a == f3408h) {
            return;
        }
        f3408h = bVar.f3818a;
        Message messageObtainMessage = this.f3410c.obtainMessage();
        messageObtainMessage.what = GLMapStaticValue.AM_PARAMETERNAME_SETISSTIMAP;
        messageObtainMessage.arg1 = bVar.f3818a;
        messageObtainMessage.obj = bVar.f3821d;
        this.f3410c.sendMessage(messageObtainMessage);
    }

    public void a(String str) {
        this.f3412e = str;
    }

    public void b() {
        if (this.f3413f == 0) {
            if (this.f3409b == null) {
                throw new IllegalStateException("BDMapSDKException: you have not supplyed the global app context info from SDKInitializer.initialize(Context) function.");
            }
            this.f3411d = new e();
            f();
            SysUpdateObservable.getInstance().updateNetworkInfo(this.f3409b);
        }
        this.f3413f++;
    }

    public boolean c() {
        if (this.f3409b == null) {
            throw new IllegalStateException("BDMapSDKException: you have not supplyed the global app context info from SDKInitializer.initialize(Context) function.");
        }
        this.f3410c = new b(this);
        i.b(this.f3409b);
        com.baidu.mapsdkplatform.comapi.util.d.a().a(this.f3409b);
        i.f();
        PermissionCheck.init(this.f3409b);
        PermissionCheck.setPermissionCheckResultListener(this);
        PermissionCheck.permissionCheck();
        return true;
    }

    public void d() {
        this.f3413f--;
        if (this.f3413f == 0) {
            g();
            i.a();
        }
    }

    public Context e() {
        Context context = this.f3409b;
        if (context != null) {
            return context;
        }
        throw new IllegalStateException("BDMapSDKException: you have not supplyed the global app context info from SDKInitializer.initialize(Context) function.");
    }
}