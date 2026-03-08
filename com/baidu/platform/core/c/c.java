package com.baidu.platform.core.c;

import com.baidu.mapapi.search.poi.PoiIndoorOption;
import com.ido.alexa.AlexaCustomSkillConstant;

/* JADX INFO: loaded from: classes.dex */
public class c extends com.baidu.platform.base.e {
    public c(PoiIndoorOption poiIndoorOption) {
        a(poiIndoorOption);
    }

    private void a(PoiIndoorOption poiIndoorOption) {
        this.f3903a.a("qt", "indoor_s");
        this.f3903a.a("x", AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.f3903a.a("y", AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.f3903a.a("from", "android_map_sdk");
        String str = poiIndoorOption.bid;
        if (str != null && !str.equals("")) {
            this.f3903a.a("bid", str);
        }
        String str2 = poiIndoorOption.wd;
        if (str2 != null && !str2.equals("")) {
            this.f3903a.a("wd", str2);
        }
        String str3 = poiIndoorOption.floor;
        if (str3 != null && !str3.equals("")) {
            this.f3903a.a("floor", str3);
        }
        this.f3903a.a("current", poiIndoorOption.currentPage + "");
        this.f3903a.a("pageSize", poiIndoorOption.pageSize + "");
    }

    @Override // com.baidu.platform.base.e
    public String a(com.baidu.platform.domain.c cVar) {
        return cVar.c();
    }
}