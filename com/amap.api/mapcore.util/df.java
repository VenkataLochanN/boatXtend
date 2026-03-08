package com.amap.api.mapcore.util;

import android.content.Context;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: CustomStyleRequest.java */
/* JADX INFO: loaded from: classes.dex */
public class df extends ga<String, a> {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f598h;
    private boolean i;
    private String j;

    /* JADX INFO: compiled from: CustomStyleRequest.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public byte[] f599a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f600b = -1;
    }

    @Override // com.amap.api.mapcore.util.ga
    protected String a() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amap.api.mapcore.util.ga
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public a b(String str) throws fz {
        return null;
    }

    @Override // com.amap.api.mapcore.util.iq
    public boolean isSupportIPV6() {
        return true;
    }

    public df(Context context, String str) {
        super(context, str);
        this.i = false;
        this.j = null;
        this.f1009g = "/map/styles";
    }

    public df(Context context, String str, boolean z) {
        super(context, str);
        this.i = false;
        this.j = null;
        this.i = z;
        if (z) {
            this.f1009g = "/sdk/map/styles";
            this.isPostFlag = false;
        } else {
            this.f1009g = "/map/styles";
        }
    }

    public void a(String str) {
        this.j = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amap.api.mapcore.util.ga
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public a b(byte[] bArr) throws fz {
        a aVar = new a();
        aVar.f599a = bArr;
        if (this.i && bArr != null) {
            if (bArr.length == 0) {
                aVar.f599a = null;
            } else if (aVar.f599a.length <= 1024) {
                try {
                    if (new String(bArr, "utf-8").contains("errcode")) {
                        aVar.f599a = null;
                    }
                } catch (Exception e2) {
                    hn.c(e2, "CustomStyleRequest", "loadData");
                }
            }
        }
        return aVar;
    }

    @Override // com.amap.api.mapcore.util.ga, com.amap.api.mapcore.util.iq
    public Map<String, String> getRequestHead() {
        gs gsVarE = er.e();
        String strB = gsVarE != null ? gsVarE.b() : null;
        Hashtable hashtable = new Hashtable(16);
        hashtable.put("User-Agent", m.f1685c);
        hashtable.put("Accept-Encoding", "gzip");
        hashtable.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", strB, "3dmap"));
        hashtable.put("x-INFO", gl.a(this.f1008f));
        hashtable.put("key", gi.f(this.f1008f));
        hashtable.put("logversion", "2.1");
        return hashtable;
    }

    @Override // com.amap.api.mapcore.util.dp, com.amap.api.mapcore.util.iq
    public Map<String, String> getParams() {
        Hashtable hashtable = new Hashtable(16);
        hashtable.put("key", gi.f(this.f1008f));
        if (!this.i) {
            hashtable.put("output", "bin");
        } else {
            hashtable.put("sdkType", this.j);
        }
        hashtable.put("styleid", this.f598h);
        String strA = gl.a();
        String strA2 = gl.a(this.f1008f, strA, gt.c(hashtable));
        hashtable.put("ts", strA);
        hashtable.put("scode", strA2);
        return hashtable;
    }

    @Override // com.amap.api.mapcore.util.iq
    public String getURL() {
        return "http://restsdk.amap.com/v4" + this.f1009g;
    }

    @Override // com.amap.api.mapcore.util.iq
    public String getIPV6URL() {
        return er.a(getURL());
    }

    public void d(String str) {
        this.f598h = str;
    }
}