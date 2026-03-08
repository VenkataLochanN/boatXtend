package com.ido.life.base;

import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: classes2.dex */
public class SingleTaskIntent extends Intent {
    public SingleTaskIntent(Context context, Class<?> cls) {
        super(context, cls);
        setFlags(67108864);
    }
}