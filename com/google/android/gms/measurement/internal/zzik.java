package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzik implements Runnable {
    private final URL zza;
    private final byte[] zzb;
    private final zzih zzc;
    private final String zzd;
    private final Map<String, String> zze;
    private final /* synthetic */ zzii zzf;

    public zzik(zzii zziiVar, String str, URL url, byte[] bArr, Map<String, String> map, zzih zzihVar) {
        this.zzf = zziiVar;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(url);
        Preconditions.checkNotNull(zzihVar);
        this.zza = url;
        this.zzb = null;
        this.zzc = zzihVar;
        this.zzd = str;
        this.zze = null;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        HttpURLConnection httpURLConnectionZza;
        int i;
        Map<String, List<String>> map;
        int i2;
        Map<String, List<String>> map2;
        Map<String, List<String>> headerFields;
        this.zzf.zzc();
        int responseCode = 0;
        try {
            httpURLConnectionZza = this.zzf.zza(this.zza);
            try {
                if (this.zze != null) {
                    for (Map.Entry<String, String> entry : this.zze.entrySet()) {
                        httpURLConnectionZza.addRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                responseCode = httpURLConnectionZza.getResponseCode();
                headerFields = httpURLConnectionZza.getHeaderFields();
            } catch (IOException e2) {
                e = e2;
                i2 = responseCode;
                map2 = null;
            } catch (Throwable th) {
                th = th;
                i = responseCode;
                map = null;
            }
        } catch (IOException e3) {
            e = e3;
            httpURLConnectionZza = null;
            i2 = 0;
            map2 = null;
        } catch (Throwable th2) {
            th = th2;
            httpURLConnectionZza = null;
            i = 0;
            map = null;
        }
        try {
            zzii zziiVar = this.zzf;
            byte[] bArrZza = zzii.zza(httpURLConnectionZza);
            if (httpURLConnectionZza != null) {
                httpURLConnectionZza.disconnect();
            }
            zzb(responseCode, null, bArrZza, headerFields);
        } catch (IOException e4) {
            i2 = responseCode;
            map2 = headerFields;
            e = e4;
            if (httpURLConnectionZza != null) {
                httpURLConnectionZza.disconnect();
            }
            zzb(i2, e, null, map2);
        } catch (Throwable th3) {
            i = responseCode;
            map = headerFields;
            th = th3;
            if (httpURLConnectionZza != null) {
                httpURLConnectionZza.disconnect();
            }
            zzb(i, null, null, map);
            throw th;
        }
    }

    private final void zzb(final int i, final Exception exc, final byte[] bArr, final Map<String, List<String>> map) {
        this.zzf.zzq().zza(new Runnable(this, i, exc, bArr, map) { // from class: com.google.android.gms.measurement.internal.zzij
            private final zzik zza;
            private final int zzb;
            private final Exception zzc;
            private final byte[] zzd;
            private final Map zze;

            {
                this.zza = this;
                this.zzb = i;
                this.zzc = exc;
                this.zzd = bArr;
                this.zze = map;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze);
            }
        });
    }

    final /* synthetic */ void zza(int i, Exception exc, byte[] bArr, Map map) {
        this.zzc.zza(this.zzd, i, exc, bArr, map);
    }
}