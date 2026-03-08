package com.google.firebase.iid;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.identity.auth.map.device.token.Token;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzas {
    private static final long zzb = TimeUnit.DAYS.toMillis(7);
    final String zza;
    private final String zzc;
    private final long zzd;

    private zzas(String str, String str2, long j) {
        this.zza = str;
        this.zzc = str2;
        this.zzd = j;
    }

    static zzas zza(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith("{")) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new zzas(jSONObject.getString(Token.KEY_TOKEN), jSONObject.getString("appVersion"), jSONObject.getLong("timestamp"));
            } catch (JSONException e2) {
                String strValueOf = String.valueOf(e2);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 23);
                sb.append("Failed to parse token: ");
                sb.append(strValueOf);
                Log.w("FirebaseInstanceId", sb.toString());
                return null;
            }
        }
        return new zzas(str, null, 0L);
    }

    static String zza(String str, String str2, long j) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Token.KEY_TOKEN, str);
            jSONObject.put("appVersion", str2);
            jSONObject.put("timestamp", j);
            return jSONObject.toString();
        } catch (JSONException e2) {
            String strValueOf = String.valueOf(e2);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 24);
            sb.append("Failed to encode token: ");
            sb.append(strValueOf);
            Log.w("FirebaseInstanceId", sb.toString());
            return null;
        }
    }

    static String zza(zzas zzasVar) {
        if (zzasVar == null) {
            return null;
        }
        return zzasVar.zza;
    }

    final boolean zzb(String str) {
        return System.currentTimeMillis() > this.zzd + zzb || !str.equals(this.zzc);
    }
}