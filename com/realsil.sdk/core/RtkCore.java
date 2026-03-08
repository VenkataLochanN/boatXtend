package com.realsil.sdk.core;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.realsil.sdk.core.RtkConfigure;
import com.realsil.sdk.core.bluetooth.GlobalGatt;
import com.realsil.sdk.core.bluetooth.channel.SppChannelImpl;
import com.realsil.sdk.core.logger.WriteLog;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.utility.DependenceManager;
import com.realsil.sdk.core.utility.DialogUtils;
import java.util.Arrays;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class RtkCore {
    public static boolean DEBUG = false;
    public static Context mContext;

    public static String getVersion() {
        return String.format(Locale.US, "%s (%d)", BuildConfig.VERSION_NAME, 81);
    }

    public static synchronized void init(Context context) {
        initialize(context, new RtkConfigure.Builder().build());
    }

    public static synchronized void init(Context context, boolean z, boolean z2) {
        initialize(context, new RtkConfigure.Builder().build());
    }

    public static synchronized void initialize(Context context) {
        initialize(context, new RtkConfigure.Builder().build());
    }

    public static synchronized void initialize(Context context, RtkConfigure rtkConfigure) {
        String str;
        if (mContext == null) {
            mContext = context.getApplicationContext();
        }
        if (rtkConfigure == null) {
            rtkConfigure = new RtkConfigure.Builder().build();
        }
        Log.d("Realtek", rtkConfigure.toString());
        DEBUG = rtkConfigure.isDebugEnabled();
        ZLogger.GLOBAL_LOG_LEVEL = rtkConfigure.getGlobalLogLevel();
        ZLogger.initialize(rtkConfigure.getLogTag(), rtkConfigure.isPrintLog());
        if (WriteLog.getInstance() == null) {
            WriteLog.install(context);
        }
        DialogUtils.initialize(mContext);
        if (GlobalGatt.getInstance() == null) {
            GlobalGatt.initial(mContext);
        }
        if (SppChannelImpl.getInstance() == null) {
            SppChannelImpl.initialize();
        }
        DependenceManager.getInstance().register(new DependenceManager.DependenceLib("com.realsil.sdk", "rtk-core", BuildConfig.VERSION_NAME));
        if (Build.VERSION.SDK_INT >= 21) {
            str = "supportedABIS: " + Arrays.toString(Build.SUPPORTED_ABIS);
        } else {
            str = "cupABI: " + Build.CPU_ABI;
        }
        ZLogger.v(str);
    }

    public static synchronized void initialize(Context context, boolean z) {
        initialize(context, new RtkConfigure.Builder().debugEnabled(z).build());
    }

    public static boolean isBluetoothSupported() {
        return GlobalGatt.getInstance().isBluetoothSupported();
    }
}