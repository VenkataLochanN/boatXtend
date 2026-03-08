package com.baidu.platform.core.c;

import android.util.Log;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;

/* JADX INFO: loaded from: classes.dex */
public class e extends com.baidu.platform.base.e {
    e(PoiDetailSearchOption poiDetailSearchOption) {
        a(poiDetailSearchOption);
    }

    private void a(PoiDetailSearchOption poiDetailSearchOption) {
        if (poiDetailSearchOption == null) {
            Log.e(e.class.getSimpleName(), "Option is null");
            return;
        }
        if (!poiDetailSearchOption.isSearchByUids()) {
            poiDetailSearchOption.poiUids(poiDetailSearchOption.getUid());
        }
        this.f3903a.a("uids", poiDetailSearchOption.getUids());
        this.f3903a.a("output", "json");
        this.f3903a.a(AuthorizationResponseParser.SCOPE, "2");
    }

    @Override // com.baidu.platform.base.e
    public String a(com.baidu.platform.domain.c cVar) {
        return cVar.b();
    }
}