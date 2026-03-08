package com.ido.ble.watch.custom.callback;

import android.os.Handler;
import android.os.Looper;
import com.ido.ble.watch.custom.callback.PhotoWallpaperOperateCallBack;
import com.ido.ble.watch.custom.callback.WatchPlateCallBack;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static a f4666d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<WatchPlateCallBack.IOperateCallBack> f4667a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private List<PhotoWallpaperOperateCallBack.ICallBack> f4668b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Handler f4669c = new Handler(Looper.getMainLooper());

    private a() {
    }

    public static a c() {
        if (f4666d == null) {
            f4666d = new a();
        }
        return f4666d;
    }

    List<WatchPlateCallBack.IOperateCallBack> a() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4667a);
        return arrayList;
    }

    public void a(PhotoWallpaperOperateCallBack.ICallBack iCallBack) {
        this.f4668b.add(iCallBack);
    }

    public void a(WatchPlateCallBack.IOperateCallBack iOperateCallBack) {
        this.f4667a.add(iOperateCallBack);
    }

    void a(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            this.f4669c.post(runnable);
        }
    }

    List<PhotoWallpaperOperateCallBack.ICallBack> b() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f4668b);
        return arrayList;
    }

    public void b(PhotoWallpaperOperateCallBack.ICallBack iCallBack) {
        this.f4668b.remove(iCallBack);
    }

    public void b(WatchPlateCallBack.IOperateCallBack iOperateCallBack) {
        this.f4667a.remove(iOperateCallBack);
    }
}