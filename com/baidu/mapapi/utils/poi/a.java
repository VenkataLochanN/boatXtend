package com.baidu.mapapi.utils.poi;

import android.content.Context;
import android.util.Log;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.platform.comapi.pano.a;
import com.baidu.platform.comapi.pano.c;

/* JADX INFO: loaded from: classes.dex */
final class a implements a.InterfaceC0037a<c> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ Context f3377a;

    a(Context context) {
        this.f3377a = context;
    }

    @Override // com.baidu.platform.comapi.pano.a.InterfaceC0037a
    public void a(HttpClient.HttpStateError httpStateError) {
        String str;
        int i = b.f3379b[httpStateError.ordinal()];
        if (i == 1) {
            str = "current network is not available";
        } else if (i != 2) {
            return;
        } else {
            str = "network inner error, please check network";
        }
        Log.d("baidumapsdk", str);
    }

    @Override // com.baidu.platform.comapi.pano.a.InterfaceC0037a
    public void a(c cVar) {
        String str;
        if (cVar == null) {
            Log.d("baidumapsdk", "pano info is null");
            return;
        }
        int i = b.f3378a[cVar.a().ordinal()];
        if (i == 1) {
            str = "pano uid is error, please check param poi uid";
        } else if (i == 2) {
            str = "pano id not found for this poi point";
        } else if (i == 3) {
            str = "please check ak for permission";
        } else {
            if (i != 4) {
                return;
            }
            if (cVar.c() == 1) {
                try {
                    BaiduMapPoiSearch.b(cVar.b(), this.f3377a);
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            str = "this point do not support for pano show";
        }
        Log.d("baidumapsdk", str);
    }
}