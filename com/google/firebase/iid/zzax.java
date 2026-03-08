package com.google.firebase.iid;

import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzax {
    private int zza = 0;
    private final Map<Integer, TaskCompletionSource<Void>> zzb = new ArrayMap();
    private final zzat zzc;

    zzax(zzat zzatVar) {
        this.zzc = zzatVar;
    }

    final synchronized Task<Void> zza(String str) {
        String strZza;
        TaskCompletionSource<Void> taskCompletionSource;
        synchronized (this.zzc) {
            strZza = this.zzc.zza();
            zzat zzatVar = this.zzc;
            StringBuilder sb = new StringBuilder(String.valueOf(strZza).length() + 1 + String.valueOf(str).length());
            sb.append(strZza);
            sb.append(AppInfo.DELIM);
            sb.append(str);
            zzatVar.zza(sb.toString());
        }
        taskCompletionSource = new TaskCompletionSource<>();
        this.zzb.put(Integer.valueOf(this.zza + (TextUtils.isEmpty(strZza) ? 0 : strZza.split(AppInfo.DELIM).length - 1)), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    final synchronized boolean zza() {
        return zzb() != null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x000c, code lost:
    
        if (com.google.firebase.iid.FirebaseInstanceId.zzd() == false) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000e, code lost:
    
        android.util.Log.d("FirebaseInstanceId", "topic sync succeeded");
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0017, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final boolean zza(com.google.firebase.iid.FirebaseInstanceId r5) throws java.io.IOException {
        /*
            r4 = this;
        L0:
            monitor-enter(r4)
            java.lang.String r0 = r4.zzb()     // Catch: java.lang.Throwable -> L43
            r1 = 1
            if (r0 != 0) goto L18
            boolean r5 = com.google.firebase.iid.FirebaseInstanceId.zzd()     // Catch: java.lang.Throwable -> L43
            if (r5 == 0) goto L16
            java.lang.String r5 = "FirebaseInstanceId"
            java.lang.String r0 = "topic sync succeeded"
            android.util.Log.d(r5, r0)     // Catch: java.lang.Throwable -> L43
        L16:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L43
            return r1
        L18:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L43
            boolean r2 = zza(r5, r0)
            if (r2 != 0) goto L21
            r5 = 0
            return r5
        L21:
            monitor-enter(r4)
            java.util.Map<java.lang.Integer, com.google.android.gms.tasks.TaskCompletionSource<java.lang.Void>> r2 = r4.zzb     // Catch: java.lang.Throwable -> L40
            int r3 = r4.zza     // Catch: java.lang.Throwable -> L40
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: java.lang.Throwable -> L40
            java.lang.Object r2 = r2.remove(r3)     // Catch: java.lang.Throwable -> L40
            com.google.android.gms.tasks.TaskCompletionSource r2 = (com.google.android.gms.tasks.TaskCompletionSource) r2     // Catch: java.lang.Throwable -> L40
            r4.zzb(r0)     // Catch: java.lang.Throwable -> L40
            int r0 = r4.zza     // Catch: java.lang.Throwable -> L40
            int r0 = r0 + r1
            r4.zza = r0     // Catch: java.lang.Throwable -> L40
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L40
            if (r2 == 0) goto L0
            r0 = 0
            r2.setResult(r0)
            goto L0
        L40:
            r5 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L40
            throw r5
        L43:
            r5 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L43
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzax.zza(com.google.firebase.iid.FirebaseInstanceId):boolean");
    }

    private final String zzb() {
        String strZza;
        synchronized (this.zzc) {
            strZza = this.zzc.zza();
        }
        if (TextUtils.isEmpty(strZza)) {
            return null;
        }
        String[] strArrSplit = strZza.split(AppInfo.DELIM);
        if (strArrSplit.length <= 1 || TextUtils.isEmpty(strArrSplit[1])) {
            return null;
        }
        return strArrSplit[1];
    }

    private final synchronized boolean zzb(String str) {
        synchronized (this.zzc) {
            String strZza = this.zzc.zza();
            String strValueOf = String.valueOf(str);
            if (!strZza.startsWith(strValueOf.length() != 0 ? AppInfo.DELIM.concat(strValueOf) : new String(AppInfo.DELIM))) {
                return false;
            }
            String strValueOf2 = String.valueOf(str);
            this.zzc.zza(strZza.substring((strValueOf2.length() != 0 ? AppInfo.DELIM.concat(strValueOf2) : new String(AppInfo.DELIM)).length()));
            return true;
        }
    }

    private static boolean zza(FirebaseInstanceId firebaseInstanceId, String str) throws IOException {
        String[] strArrSplit = str.split("!");
        if (strArrSplit.length == 2) {
            String str2 = strArrSplit[0];
            String str3 = strArrSplit[1];
            byte b2 = -1;
            try {
                int iHashCode = str2.hashCode();
                if (iHashCode != 83) {
                    if (iHashCode == 85 && str2.equals("U")) {
                        b2 = 1;
                    }
                } else if (str2.equals("S")) {
                    b2 = 0;
                }
                if (b2 == 0) {
                    firebaseInstanceId.zzb(str3);
                    if (FirebaseInstanceId.zzd()) {
                        Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                    }
                } else if (b2 == 1) {
                    firebaseInstanceId.zzc(str3);
                    if (FirebaseInstanceId.zzd()) {
                        Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
                    }
                }
            } catch (IOException e2) {
                if ("SERVICE_NOT_AVAILABLE".equals(e2.getMessage()) || "INTERNAL_SERVER_ERROR".equals(e2.getMessage())) {
                    String message = e2.getMessage();
                    StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 53);
                    sb.append("Topic operation failed: ");
                    sb.append(message);
                    sb.append(". Will retry Topic operation.");
                    Log.e("FirebaseInstanceId", sb.toString());
                    return false;
                }
                if (e2.getMessage() == null) {
                    Log.e("FirebaseInstanceId", "Topic operation failed without exception message. Will retry Topic operation.");
                    return false;
                }
                throw e2;
            }
        }
        return true;
    }
}