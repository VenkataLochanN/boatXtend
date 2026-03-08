package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.autonavi.base.amap.mapcore.AeUtil;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzaa implements Runnable {
    private final zzw zza;

    zzaa(zzw zzwVar) {
        this.zza = zzwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzah<?> zzahVarPoll;
        zzw zzwVar = this.zza;
        while (true) {
            synchronized (zzwVar) {
                if (zzwVar.zza != 2) {
                    return;
                }
                if (zzwVar.zzd.isEmpty()) {
                    zzwVar.zzb();
                    return;
                } else {
                    zzahVarPoll = zzwVar.zzd.poll();
                    zzwVar.zze.put(zzahVarPoll.zza, zzahVarPoll);
                    zzwVar.zzf.zzc.schedule(new Runnable(zzwVar, zzahVarPoll) { // from class: com.google.firebase.iid.zzac
                        private final zzw zza;
                        private final zzah zzb;

                        zzac(zzw zzwVar2, zzah zzahVarPoll2) {
                            this.zza = zzwVar2;
                            this.zzb = zzahVarPoll2;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            this.zza.zza(this.zzb.zza);
                        }
                    }, 30L, TimeUnit.SECONDS);
                }
            }
            if (Log.isLoggable("MessengerIpcClient", 3)) {
                String strValueOf = String.valueOf(zzahVarPoll2);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 8);
                sb.append("Sending ");
                sb.append(strValueOf);
                Log.d("MessengerIpcClient", sb.toString());
            }
            Context context = zzwVar2.zzf.zzb;
            Messenger messenger = zzwVar2.zzb;
            Message messageObtain = Message.obtain();
            messageObtain.what = zzahVarPoll2.zzc;
            messageObtain.arg1 = zzahVarPoll2.zza;
            messageObtain.replyTo = messenger;
            Bundle bundle = new Bundle();
            bundle.putBoolean("oneWay", zzahVarPoll2.zza());
            bundle.putString("pkg", context.getPackageName());
            bundle.putBundle(AeUtil.ROOT_DATA_PATH_OLD_NAME, zzahVarPoll2.zzd);
            messageObtain.setData(bundle);
            try {
                zzwVar2.zzc.zza(messageObtain);
            } catch (RemoteException e2) {
                zzwVar2.zza(2, e2.getMessage());
            }
        }
    }
}