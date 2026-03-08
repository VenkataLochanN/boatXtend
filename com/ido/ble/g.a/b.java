package com.ido.ble.g.a;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f4553a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f4554b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected List<a> f4555c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private long f4556d;

    public b() {
        this.f4553a = "";
        this.f4554b = "";
        this.f4555c = new ArrayList();
        this.f4556d = System.currentTimeMillis();
    }

    public b(String str, String str2) {
        this.f4553a = "";
        this.f4554b = "";
        this.f4555c = new ArrayList();
        this.f4556d = System.currentTimeMillis();
        this.f4553a = str;
        this.f4554b = str2;
    }

    public String a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("__source__", (Object) this.f4554b);
        jSONObject.put("__topic__", (Object) this.f4553a);
        JSONArray jSONArray = new JSONArray();
        Iterator<a> it = this.f4555c.iterator();
        while (it.hasNext()) {
            jSONArray.add(new JSONObject(it.next().a()));
        }
        jSONObject.put("__logs__", (Object) jSONArray);
        return jSONObject.toJSONString();
    }

    public void a(long j) {
        this.f4556d = j;
    }

    public void a(a aVar) {
        this.f4555c.add(aVar);
    }

    public void a(String str) {
        this.f4554b = str;
    }

    public long b() {
        return this.f4556d;
    }

    public void b(String str) {
        this.f4553a = str;
    }
}