package com.baidu.mapsdkplatform.comjni.map.basemap;

import android.os.Bundle;
import android.util.LongSparseArray;

/* JADX INFO: loaded from: classes.dex */
public class BaseMapCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static LongSparseArray<b> f3858a = new LongSparseArray<>();

    public static int ReqLayerData(Bundle bundle, long j, int i, Bundle bundle2) {
        int size = f3858a.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVarValueAt = f3858a.valueAt(i2);
            if (bVarValueAt != null && bVarValueAt.a(j)) {
                return bVarValueAt.a(bundle, j, i, bundle2);
            }
        }
        return 0;
    }

    public static void addLayerDataInterface(long j, b bVar) {
        f3858a.put(j, bVar);
    }

    public static void removeLayerDataInterface(long j) {
        f3858a.remove(j);
    }
}