package com.google.android.gms.measurement.internal;

import android.os.Process;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgg extends Thread {
    private final Object zza;
    private final BlockingQueue<zzgd<?>> zzb;
    private boolean zzc = false;
    private final /* synthetic */ zzgc zzd;

    public zzgg(zzgc zzgcVar, String str, BlockingQueue<zzgd<?>> blockingQueue) {
        this.zzd = zzgcVar;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(blockingQueue);
        this.zza = new Object();
        this.zzb = blockingQueue;
        setName(str);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        boolean z = false;
        while (!z) {
            try {
                this.zzd.zzh.acquire();
                z = true;
            } catch (InterruptedException e2) {
                zza(e2);
            }
        }
        try {
            int threadPriority = Process.getThreadPriority(Process.myTid());
            while (true) {
                zzgd<?> zzgdVarPoll = this.zzb.poll();
                if (zzgdVarPoll != null) {
                    Process.setThreadPriority(zzgdVarPoll.zza ? threadPriority : 10);
                    zzgdVarPoll.run();
                } else {
                    synchronized (this.zza) {
                        if (this.zzb.peek() == null && !this.zzd.zzi) {
                            try {
                                this.zza.wait(30000L);
                            } catch (InterruptedException e3) {
                                zza(e3);
                            }
                        }
                    }
                    synchronized (this.zzd.zzg) {
                        if (this.zzb.peek() == null) {
                            break;
                        }
                    }
                }
            }
            if (this.zzd.zzt().zza(zzap.zzcr)) {
                zzb();
            }
        } finally {
            zzb();
        }
    }

    private final void zzb() {
        synchronized (this.zzd.zzg) {
            if (!this.zzc) {
                this.zzd.zzh.release();
                this.zzd.zzg.notifyAll();
                if (this == this.zzd.zza) {
                    zzgc.zza(this.zzd, null);
                } else if (this == this.zzd.zzb) {
                    zzgc.zzb(this.zzd, null);
                } else {
                    this.zzd.zzr().zzf().zza("Current scheduler thread is neither worker nor network");
                }
                this.zzc = true;
            }
        }
    }

    public final void zza() {
        synchronized (this.zza) {
            this.zza.notifyAll();
        }
    }

    private final void zza(InterruptedException interruptedException) {
        this.zzd.zzr().zzi().zza(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
    }
}