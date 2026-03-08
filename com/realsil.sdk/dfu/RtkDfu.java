package com.realsil.sdk.dfu;

import android.content.Context;
import com.realsil.sdk.core.bluetooth.BluetoothProfileManager;
import com.realsil.sdk.core.bluetooth.GlobalGatt;
import com.realsil.sdk.core.bluetooth.RtkBluetoothManager;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DependenceManager;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class RtkDfu {
    public static boolean DEBUG_ENABLE = false;

    public static void initialize(Context context) {
        initialize(context, false);
    }

    public static void initialize(Context context, boolean z) {
        DEBUG_ENABLE = z;
        ZLogger.d(true, String.format(Locale.US, "{\nAPPLICATION_ID=%s\nVERSION=%s-%d\nDEBUG=%b\nBUILD_TYPE=%s\nFLAVOR=%s\n}", "com.realsil.sdk.dfu", "3.2.11", 344, false, "release", ""));
        DependenceManager.getInstance().register(new DependenceManager.DependenceLib("com.realsil.sdk", "rtk-dfu", "3.2.11"));
        if (GlobalGatt.getInstance() == null) {
            GlobalGatt.initial(context);
        }
        BluetoothProfileManager.initial(context);
        RtkBluetoothManager.initial(context);
    }
}