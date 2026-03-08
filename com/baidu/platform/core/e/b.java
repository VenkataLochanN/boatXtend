package com.baidu.platform.core.e;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.mapapi.search.share.LocationShareURLOption;

/* JADX INFO: loaded from: classes.dex */
public class b extends com.baidu.platform.base.e {
    public b(LocationShareURLOption locationShareURLOption) {
        a(locationShareURLOption);
    }

    private void a(LocationShareURLOption locationShareURLOption) {
        this.f3903a.a("qt", "cs");
        Point pointLl2point = CoordUtil.ll2point(locationShareURLOption.mLocation);
        this.f3903a.a("geo", pointLl2point.x + "|" + pointLl2point.y);
        this.f3903a.a("t", locationShareURLOption.mName);
        this.f3903a.a("cnt", locationShareURLOption.mSnippet);
        b(false);
        a(false);
    }

    @Override // com.baidu.platform.base.e
    public String a(com.baidu.platform.domain.c cVar) {
        return cVar.q();
    }
}