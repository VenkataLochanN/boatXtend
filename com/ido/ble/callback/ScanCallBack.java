package com.ido.ble.callback;

import com.ido.ble.bluetooth.device.BLEDevice;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class ScanCallBack {

    public interface ICallBack {
        void onFindDevice(BLEDevice bLEDevice);

        void onScanFinished();

        void onStart();
    }

    public static final void a() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.ScanCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().z().iterator();
                while (it.hasNext()) {
                    it.next().onScanFinished();
                }
            }
        });
    }

    public static final void a(final BLEDevice bLEDevice) {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.ScanCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().z().iterator();
                while (it.hasNext()) {
                    it.next().onFindDevice(bLEDevice);
                }
            }
        });
    }

    public static final void b() {
        b.K().a(new Runnable() { // from class: com.ido.ble.callback.ScanCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator<ICallBack> it = b.K().z().iterator();
                while (it.hasNext()) {
                    it.next().onStart();
                }
            }
        });
    }
}