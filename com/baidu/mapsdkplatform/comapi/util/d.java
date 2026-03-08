package com.baidu.mapsdkplatform.comapi.util;

import android.content.Context;
import com.ido.life.constants.Constants;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ArrayList<Integer> f3833a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f3834b;

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final d f3835a = new d();
    }

    private d() {
        this.f3833a = new ArrayList<>();
    }

    private int a(String str) {
        Context context = this.f3834b;
        return context == null ? Constants.EventConstants.EVENT_TYPE_UNBIND_SUCCESS : context.getSharedPreferences("ad_auth", 0).getInt(str, 0);
    }

    public static d a() {
        return a.f3835a;
    }

    private void a(String str, int i) {
        Context context = this.f3834b;
        if (context == null) {
            return;
        }
        context.getSharedPreferences("ad_auth", 0).edit().putInt(str, i).apply();
    }

    public void a(int i) {
        if (i == -1 && (i = a("ad_key")) == -101) {
            return;
        }
        for (int i2 = i; i2 != 0; i2 /= 2) {
            this.f3833a.add(Integer.valueOf(i2 % 2));
        }
        a("ad_key", i);
    }

    public void a(Context context) {
        this.f3834b = context;
    }

    public boolean b() {
        ArrayList<Integer> arrayList = this.f3833a;
        return arrayList != null && arrayList.size() > 0 && this.f3833a.get(0).intValue() == 1;
    }
}