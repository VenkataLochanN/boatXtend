package com.google.android.gms.measurement.module;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzv;
import com.google.android.gms.measurement.internal.zzgf;
import com.google.android.gms.measurement.internal.zzhd;
import com.google.android.gms.measurement.internal.zzhe;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public class Analytics {
    public static final String CRASH_ORIGIN = "crash";
    public static final String FCM_ORIGIN = "fcm";
    public static final String FIAM_ORIGIN = "fiam";
    private static volatile Analytics zza;
    private final zzgf zzb;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
    public static final class Event extends zzhe {
        public static final String AD_REWARD = "_ar";
        public static final String APP_EXCEPTION = "_ae";

        private Event() {
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
    public static final class Param extends zzhd {
        public static final String FATAL = "fatal";
        public static final String TIMESTAMP = "timestamp";
        public static final String TYPE = "type";

        private Param() {
        }
    }

    public static Analytics getInstance(Context context) {
        if (zza == null) {
            synchronized (Analytics.class) {
                if (zza == null) {
                    zza = new Analytics(zzgf.zza(context, (zzv) null));
                }
            }
        }
        return zza;
    }

    private Analytics(zzgf zzgfVar) {
        Preconditions.checkNotNull(zzgfVar);
        this.zzb = zzgfVar;
    }
}