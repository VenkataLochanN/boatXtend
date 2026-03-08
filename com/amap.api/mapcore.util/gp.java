package com.amap.api.mapcore.util;

import android.net.Uri;
import android.text.TextUtils;

/* JADX INFO: compiled from: IPV6Request.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class gp extends iq {
    @Override // com.amap.api.mapcore.util.iq
    public boolean isSupportIPV6() {
        return true;
    }

    @Override // com.amap.api.mapcore.util.iq
    public String getIPV6URL() {
        if (TextUtils.isEmpty(getURL())) {
            return getURL();
        }
        String url = getURL();
        Uri uri = Uri.parse(url);
        if (uri.getAuthority().startsWith("dualstack-")) {
            return url;
        }
        return uri.buildUpon().authority("dualstack-" + uri.getAuthority()).build().toString();
    }
}