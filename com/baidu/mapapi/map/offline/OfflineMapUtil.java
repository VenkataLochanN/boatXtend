package com.baidu.mapapi.map.offline;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapsdkplatform.comapi.map.q;
import com.baidu.mapsdkplatform.comapi.map.t;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class OfflineMapUtil {
    public static MKOLSearchRecord getSearchRecordFromLocalCityInfo(q qVar) {
        if (qVar == null) {
            return null;
        }
        MKOLSearchRecord mKOLSearchRecord = new MKOLSearchRecord();
        mKOLSearchRecord.cityID = qVar.f3607a;
        mKOLSearchRecord.cityName = qVar.f3608b;
        mKOLSearchRecord.cityType = qVar.f3610d;
        long j = 0;
        if (qVar.a() != null) {
            ArrayList<MKOLSearchRecord> arrayList = new ArrayList<>();
            for (q qVar2 : qVar.a()) {
                arrayList.add(getSearchRecordFromLocalCityInfo(qVar2));
                j += (long) qVar2.f3609c;
                mKOLSearchRecord.childCities = arrayList;
            }
        }
        if (mKOLSearchRecord.cityType != 1) {
            j = qVar.f3609c;
        }
        mKOLSearchRecord.dataSize = j;
        return mKOLSearchRecord;
    }

    public static MKOLUpdateElement getUpdatElementFromLocalMapElement(t tVar) {
        if (tVar == null) {
            return null;
        }
        MKOLUpdateElement mKOLUpdateElement = new MKOLUpdateElement();
        mKOLUpdateElement.cityID = tVar.f3618a;
        mKOLUpdateElement.cityName = tVar.f3619b;
        if (tVar.f3624g != null) {
            mKOLUpdateElement.geoPt = CoordUtil.mc2ll(tVar.f3624g);
        }
        mKOLUpdateElement.level = tVar.f3622e;
        mKOLUpdateElement.ratio = tVar.i;
        mKOLUpdateElement.serversize = tVar.f3625h;
        mKOLUpdateElement.size = tVar.i == 100 ? tVar.f3625h : (tVar.f3625h / 100) * tVar.i;
        mKOLUpdateElement.status = tVar.l;
        mKOLUpdateElement.update = tVar.j;
        return mKOLUpdateElement;
    }
}