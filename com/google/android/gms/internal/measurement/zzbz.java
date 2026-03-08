package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzbz implements zzcd {
    private static final Map<Uri, zzbz> zza = new ArrayMap();
    private static final String[] zzh = {"key", "value"};
    private final ContentResolver zzb;
    private final Uri zzc;
    private volatile Map<String, String> zzf;
    private final ContentObserver zzd = new zzcb(this, null);
    private final Object zze = new Object();
    private final List<zzca> zzg = new ArrayList();

    private zzbz(ContentResolver contentResolver, Uri uri) {
        this.zzb = contentResolver;
        this.zzc = uri;
        contentResolver.registerContentObserver(uri, false, this.zzd);
    }

    public static zzbz zza(ContentResolver contentResolver, Uri uri) {
        zzbz zzbzVar;
        synchronized (zzbz.class) {
            zzbzVar = zza.get(uri);
            if (zzbzVar == null) {
                try {
                    zzbz zzbzVar2 = new zzbz(contentResolver, uri);
                    try {
                        zza.put(uri, zzbzVar2);
                    } catch (SecurityException unused) {
                    }
                    zzbzVar = zzbzVar2;
                } catch (SecurityException unused2) {
                }
            }
        }
        return zzbzVar;
    }

    public final Map<String, String> zza() {
        Map<String, String> mapZze = this.zzf;
        if (mapZze == null) {
            synchronized (this.zze) {
                mapZze = this.zzf;
                if (mapZze == null) {
                    mapZze = zze();
                    this.zzf = mapZze;
                }
            }
        }
        return mapZze != null ? mapZze : Collections.emptyMap();
    }

    public final void zzb() {
        synchronized (this.zze) {
            this.zzf = null;
            zzcn.zza();
        }
        synchronized (this) {
            Iterator<zzca> it = this.zzg.iterator();
            while (it.hasNext()) {
                it.next().zza();
            }
        }
    }

    private final Map<String, String> zze() {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                return (Map) zzcc.zza(new zzcf(this) { // from class: com.google.android.gms.internal.measurement.zzby
                    private final zzbz zza;

                    {
                        this.zza = this;
                    }

                    @Override // com.google.android.gms.internal.measurement.zzcf
                    public final Object zza() {
                        return this.zza.zzd();
                    }
                });
            } catch (SQLiteException | IllegalStateException | SecurityException unused) {
                Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                return null;
            }
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }

    static synchronized void zzc() {
        for (zzbz zzbzVar : zza.values()) {
            zzbzVar.zzb.unregisterContentObserver(zzbzVar.zzd);
        }
        zza.clear();
    }

    @Override // com.google.android.gms.internal.measurement.zzcd
    public final /* synthetic */ Object zza(String str) {
        return zza().get(str);
    }

    final /* synthetic */ Map zzd() {
        Map map;
        Cursor cursorQuery = this.zzb.query(this.zzc, zzh, null, null, null);
        if (cursorQuery == null) {
            return Collections.emptyMap();
        }
        try {
            int count = cursorQuery.getCount();
            if (count == 0) {
                return Collections.emptyMap();
            }
            if (count <= 256) {
                map = new ArrayMap(count);
            } else {
                map = new HashMap(count, 1.0f);
            }
            while (cursorQuery.moveToNext()) {
                map.put(cursorQuery.getString(0), cursorQuery.getString(1));
            }
            return map;
        } finally {
            cursorQuery.close();
        }
    }
}