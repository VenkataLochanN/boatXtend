package com.ido.ble.event.stat.one;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Map<String, Integer> f4390a = new HashMap();

    public void a() {
        this.f4390a.clear();
    }

    public void a(String str) {
        if (!this.f4390a.containsKey(str)) {
            this.f4390a.put(str, 1);
        } else {
            this.f4390a.put(str, Integer.valueOf(this.f4390a.get(str).intValue() + 1));
        }
    }

    public String b() {
        return this.f4390a.size() == 0 ? "null" : new Gson().toJson(this.f4390a);
    }
}