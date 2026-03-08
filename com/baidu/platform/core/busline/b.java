package com.baidu.platform.core.busline;

import com.baidu.mapapi.search.busline.BusLineSearchOption;
import com.baidu.platform.base.e;
import com.ido.alexa.AlexaCustomSkillConstant;

/* JADX INFO: loaded from: classes.dex */
public class b extends e {
    public b(BusLineSearchOption busLineSearchOption) {
        a(busLineSearchOption);
    }

    private void a(BusLineSearchOption busLineSearchOption) {
        this.f3903a.a("qt", "bsl");
        this.f3903a.a("rt_info", "1");
        this.f3903a.a("ie", "utf-8");
        this.f3903a.a("oue", AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.f3903a.a("c", busLineSearchOption.mCity);
        this.f3903a.a("uid", busLineSearchOption.mUid);
        this.f3903a.a("t", System.currentTimeMillis() + "");
    }

    @Override // com.baidu.platform.base.e
    public String a(com.baidu.platform.domain.c cVar) {
        return cVar.m();
    }
}