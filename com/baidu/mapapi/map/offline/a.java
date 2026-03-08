package com.baidu.mapapi.map.offline;

import com.baidu.mapsdkplatform.comapi.map.v;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class a implements v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ MKOfflineMap f3060a;

    a(MKOfflineMap mKOfflineMap) {
        this.f3060a = mKOfflineMap;
    }

    @Override // com.baidu.mapsdkplatform.comapi.map.v
    public void a(int i, int i2) {
        MKOfflineMapListener mKOfflineMapListener;
        if (i == 4) {
            ArrayList<MKOLUpdateElement> allUpdateInfo = this.f3060a.getAllUpdateInfo();
            if (allUpdateInfo != null) {
                for (MKOLUpdateElement mKOLUpdateElement : allUpdateInfo) {
                    if (mKOLUpdateElement.update) {
                        this.f3060a.f3059c.onGetOfflineMapState(4, mKOLUpdateElement.cityID);
                    }
                }
                return;
            }
            return;
        }
        int i3 = 6;
        if (i == 6) {
            mKOfflineMapListener = this.f3060a.f3059c;
        } else if (i == 8) {
            this.f3060a.f3059c.onGetOfflineMapState(0, i2 >> 8);
            return;
        } else {
            if (i != 10) {
                if (i != 12) {
                    return;
                }
                this.f3060a.f3058b.a(true, false);
                return;
            }
            mKOfflineMapListener = this.f3060a.f3059c;
            i3 = 2;
        }
        mKOfflineMapListener.onGetOfflineMapState(i3, i2);
    }
}