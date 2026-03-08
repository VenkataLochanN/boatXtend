package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.HashMap;

/* JADX INFO: compiled from: SPConfig.java */
/* JADX INFO: loaded from: classes.dex */
public final class hz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static HashMap<String, String> f1301a = new HashMap<>();

    public static void a(Context context, gs gsVar, String str, String str2) {
        if (gsVar == null || TextUtils.isEmpty(gsVar.a())) {
            return;
        }
        String str3 = str + gsVar.a();
        f1301a.put(gsVar.a() + str, str2);
        if (context == null || TextUtils.isEmpty(str3) || TextUtils.isEmpty("d7afbc6a38848a6801f6e449f3ec8e53") || TextUtils.isEmpty(str2)) {
            return;
        }
        String strG = gt.g(gg.a(gt.a(str2)));
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("d7afbc6a38848a6801f6e449f3ec8e53", 0).edit();
        editorEdit.putString(str3, strG);
        editorEdit.commit();
    }

    public static String a(Context context, gs gsVar, String str) {
        if (gsVar == null || TextUtils.isEmpty(gsVar.a())) {
            return null;
        }
        String str2 = f1301a.get(gsVar.a() + str);
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        String str3 = str + gsVar.a();
        return (context == null || TextUtils.isEmpty(str3)) ? "" : gt.a(gg.b(gt.e(context.getSharedPreferences("d7afbc6a38848a6801f6e449f3ec8e53", 0).getString(str3, ""))));
    }
}