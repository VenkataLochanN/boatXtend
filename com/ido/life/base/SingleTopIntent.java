package com.ido.life.base;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/* JADX INFO: loaded from: classes2.dex */
public class SingleTopIntent extends Intent {
    public SingleTopIntent() {
        setFlags(536870912);
    }

    public SingleTopIntent(Intent intent) {
        super(intent);
        setFlags(536870912);
    }

    public SingleTopIntent(String str) {
        super(str);
        setFlags(536870912);
    }

    public SingleTopIntent(String str, Uri uri) {
        super(str, uri);
        setFlags(536870912);
    }

    public SingleTopIntent(Context context, Class<?> cls) {
        super(context, cls);
        setFlags(536870912);
    }

    public SingleTopIntent(String str, Uri uri, Context context, Class<?> cls) {
        super(str, uri, context, cls);
        setFlags(536870912);
    }
}