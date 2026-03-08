package com.realsil.sdk.core.corespec;

import android.content.Context;
import android.os.Handler;

/* JADX INFO: loaded from: classes3.dex */
public abstract class BleManager {
    public final Context mContext;
    public final Handler mHandler = new Handler();

    public BleManager(Context context) {
        this.mContext = context;
    }
}