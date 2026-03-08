package com.ido.ble.g.a;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Map<String, Object> f4552a = new HashMap();

    public a() {
        this.f4552a.put("__time__", Integer.valueOf(new Long(System.currentTimeMillis() / 1000).intValue()));
    }

    public Map<String, Object> a() {
        return this.f4552a;
    }

    public void a(int i) {
        this.f4552a.put("__time__", Integer.valueOf(i));
    }

    public void a(String str, String str2) {
        if (str == null || str.isEmpty()) {
            return;
        }
        if (str2 == null) {
            this.f4552a.put(str, "");
        } else {
            this.f4552a.put(str, str2);
        }
    }

    public String toString() {
        return "Log{mContent=" + this.f4552a + '}';
    }
}