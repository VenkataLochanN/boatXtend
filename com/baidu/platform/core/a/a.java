package com.baidu.platform.core.a;

import com.baidu.mapapi.search.district.DistrictSearchOption;
import com.ido.alexa.AlexaCustomSkillConstant;

/* JADX INFO: loaded from: classes.dex */
public class a extends com.baidu.platform.base.e {
    a(DistrictSearchOption districtSearchOption) {
        a(districtSearchOption);
    }

    private void a(DistrictSearchOption districtSearchOption) {
        com.baidu.platform.util.a aVar;
        String str;
        if (districtSearchOption == null) {
            return;
        }
        this.f3903a.a("qt", "con");
        this.f3903a.a("rp_format", "json");
        this.f3903a.a("rp_filter", "mobile");
        this.f3903a.a("area_res", "true");
        this.f3903a.a("addr_identify", "1");
        this.f3903a.a("ie", "utf-8");
        this.f3903a.a("pn", AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
        this.f3903a.a("rn", "10");
        this.f3903a.a("c", districtSearchOption.mCityName);
        if (districtSearchOption.mDistrictName == null || districtSearchOption.mDistrictName.equals("")) {
            aVar = this.f3903a;
            str = districtSearchOption.mCityName;
        } else {
            aVar = this.f3903a;
            str = districtSearchOption.mDistrictName;
        }
        aVar.a("wd", str);
    }

    @Override // com.baidu.platform.base.e
    public String a(com.baidu.platform.domain.c cVar) {
        return cVar.n();
    }
}