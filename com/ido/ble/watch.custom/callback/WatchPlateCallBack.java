package com.ido.ble.watch.custom.callback;

import com.ido.ble.watch.custom.model.DialPlateParam;
import com.ido.ble.watch.custom.model.WatchPlateFileInfo;
import com.ido.ble.watch.custom.model.WatchPlateScreenInfo;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class WatchPlateCallBack {

    public interface IAutoSetPlateCallBack {
        void onFailed();

        void onProgress(int i);

        void onStart();

        void onSuccess();
    }

    public interface IOperateCallBack {
        void onDeletePlate(boolean z);

        void onGetCurrentPlate(String str);

        void onGetDialPlateParam(DialPlateParam dialPlateParam);

        void onGetPlateFileInfo(WatchPlateFileInfo watchPlateFileInfo);

        void onGetScreenInfo(WatchPlateScreenInfo watchPlateScreenInfo);

        void onSetPlate(boolean z);
    }

    public interface ISetPlatErrorCallback {
        void onFailed(int i);
    }

    public static void a(final DialPlateParam dialPlateParam) {
        a.c().a(new Runnable() { // from class: com.ido.ble.watch.custom.callback.WatchPlateCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IOperateCallBack> it = a.c().a().iterator();
                while (it.hasNext()) {
                    it.next().onGetDialPlateParam(dialPlateParam);
                }
            }
        });
    }

    public static void a(final WatchPlateFileInfo watchPlateFileInfo) {
        a.c().a(new Runnable() { // from class: com.ido.ble.watch.custom.callback.WatchPlateCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IOperateCallBack> it = a.c().a().iterator();
                while (it.hasNext()) {
                    it.next().onGetPlateFileInfo(watchPlateFileInfo);
                }
            }
        });
    }

    public static void a(final WatchPlateScreenInfo watchPlateScreenInfo) {
        a.c().a(new Runnable() { // from class: com.ido.ble.watch.custom.callback.WatchPlateCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IOperateCallBack> it = a.c().a().iterator();
                while (it.hasNext()) {
                    it.next().onGetScreenInfo(watchPlateScreenInfo);
                }
            }
        });
    }

    public static void a(final String str) {
        a.c().a(new Runnable() { // from class: com.ido.ble.watch.custom.callback.WatchPlateCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IOperateCallBack> it = a.c().a().iterator();
                while (it.hasNext()) {
                    it.next().onGetCurrentPlate(str);
                }
            }
        });
    }

    public static void a(final boolean z) {
        a.c().a(new Runnable() { // from class: com.ido.ble.watch.custom.callback.WatchPlateCallBack.6
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IOperateCallBack> it = a.c().a().iterator();
                while (it.hasNext()) {
                    it.next().onDeletePlate(z);
                }
            }
        });
    }

    public static void b(final boolean z) {
        a.c().a(new Runnable() { // from class: com.ido.ble.watch.custom.callback.WatchPlateCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                Iterator<IOperateCallBack> it = a.c().a().iterator();
                while (it.hasNext()) {
                    it.next().onSetPlate(z);
                }
            }
        });
    }
}