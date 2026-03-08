package com.baidu.platform.util;

import com.baidu.mapsdkplatform.comjni.util.AppMD5;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class a implements ParamBuilder<a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected Map<String, String> f3951a;

    public a a(String str, String str2) {
        if (this.f3951a == null) {
            this.f3951a = new LinkedHashMap();
        }
        this.f3951a.put(str, str2);
        return this;
    }

    public String a() {
        StringBuilder sb;
        Map<String, String> map = this.f3951a;
        if (map == null || map.isEmpty()) {
            return null;
        }
        String str = new String();
        int i = 0;
        for (String str2 : this.f3951a.keySet()) {
            String strEncodeUrlParamsValue = AppMD5.encodeUrlParamsValue(this.f3951a.get(str2));
            if (i == 0) {
                sb = new StringBuilder();
            } else {
                sb = new StringBuilder();
                sb.append(str);
                str = "&";
            }
            sb.append(str);
            sb.append(str2);
            sb.append("=");
            sb.append(strEncodeUrlParamsValue);
            str = sb.toString();
            i++;
        }
        return str;
    }
}