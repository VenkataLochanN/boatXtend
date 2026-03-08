package com.ido.ble.watch.custom.callback;

import com.ido.ble.watch.custom.model.PhotoWallpaperOperation;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class PhotoWallpaperOperateCallBack {

    public interface ICallBack {
        void onOperateResult(PhotoWallpaperOperation.ResponseInfo responseInfo);
    }

    public static void a(final PhotoWallpaperOperation.ResponseInfo responseInfo) {
        a.c().a(new Runnable() { // from class: com.ido.ble.watch.custom.callback.PhotoWallpaperOperateCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = a.c().b().iterator();
                while (it.hasNext()) {
                    it.next().onOperateResult(responseInfo);
                }
            }
        });
    }
}