package com.google.firebase.iid;

import android.os.Bundle;
import com.autonavi.base.amap.mapcore.AeUtil;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaj extends zzah<Bundle> {
    zzaj(int i, int i2, Bundle bundle) {
        super(i, 1, bundle);
    }

    @Override // com.google.firebase.iid.zzah
    final boolean zza() {
        return false;
    }

    @Override // com.google.firebase.iid.zzah
    final void zza(Bundle bundle) {
        Bundle bundle2 = bundle.getBundle(AeUtil.ROOT_DATA_PATH_OLD_NAME);
        if (bundle2 == null) {
            bundle2 = Bundle.EMPTY;
        }
        zza(bundle2);
    }
}