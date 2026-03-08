package com.ido.life.util;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import com.ido.common.log.CommonLogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BluetoothUtils.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/ido/life/util/BluetoothUtils;", "", "()V", "removeBondDevice", "", "macString", "", "app_release"}, k = 1, mv = {1, 1, 16})
public final class BluetoothUtils {
    public static final BluetoothUtils INSTANCE = new BluetoothUtils();

    private BluetoothUtils() {
    }

    public final void removeBondDevice(String macString) {
        BluetoothDevice remoteDevice;
        Intrinsics.checkParameterIsNotNull(macString, "macString");
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || (remoteDevice = defaultAdapter.getRemoteDevice(macString)) == null) {
            return;
        }
        try {
            remoteDevice.getClass().getMethod("removeBond", new Class[0]).invoke(remoteDevice, new Object[0]);
        } catch (Exception e2) {
            CommonLogUtil.d(e2.getMessage());
        }
    }
}