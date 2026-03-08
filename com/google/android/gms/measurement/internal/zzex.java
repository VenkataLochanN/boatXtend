package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.google.android.gms.common.util.Clock;
import com.ido.life.constants.Constants;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzex extends zze {
    private final zzfa zza;
    private boolean zzb;

    zzex(zzgf zzgfVar) {
        super(zzgfVar);
        this.zza = new zzfa(this, zzn(), "google_app_measurement_local.db");
    }

    @Override // com.google.android.gms.measurement.internal.zze
    protected final boolean zzz() {
        return false;
    }

    public final void zzab() {
        zzb();
        zzd();
        try {
            int iDelete = zzae().delete("messages", null, null) + 0;
            if (iDelete > 0) {
                zzr().zzx().zza("Reset local analytics data. records", Integer.valueOf(iDelete));
            }
        } catch (SQLiteException e2) {
            zzr().zzf().zza("Error resetting local analytics data. error", e2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x012a  */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r12v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r12v7, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v2, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r7v3, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean zza(int r17, byte[] r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 317
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzex.zza(int, byte[]):boolean");
    }

    public final boolean zza(zzan zzanVar) {
        Parcel parcelObtain = Parcel.obtain();
        zzanVar.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        if (bArrMarshall.length > 131072) {
            zzr().zzg().zza("Event is too long for local database. Sending event directly to service");
            return false;
        }
        return zza(0, bArrMarshall);
    }

    public final boolean zza(zzkq zzkqVar) {
        Parcel parcelObtain = Parcel.obtain();
        zzkqVar.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        if (bArrMarshall.length > 131072) {
            zzr().zzg().zza("User property too long for local database. Sending directly to service");
            return false;
        }
        return zza(1, bArrMarshall);
    }

    public final boolean zza(zzv zzvVar) {
        zzp();
        byte[] bArrZza = zzkv.zza((Parcelable) zzvVar);
        if (bArrZza.length > 131072) {
            zzr().zzg().zza("Conditional user property too long for local database. Sending directly to service");
            return false;
        }
        return zza(2, bArrZza);
    }

    /* JADX WARN: Removed duplicated region for block: B:139:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0207 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0255 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0255 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0255 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable> zza(int r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 627
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzex.zza(int):java.util.List");
    }

    public final boolean zzac() {
        return zza(3, new byte[0]);
    }

    public final boolean zzad() {
        zzd();
        zzb();
        if (this.zzb || !zzaf()) {
            return false;
        }
        int i = 5;
        for (int i2 = 0; i2 < 5; i2++) {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                try {
                    SQLiteDatabase sQLiteDatabaseZzae = zzae();
                    if (sQLiteDatabaseZzae == null) {
                        this.zzb = true;
                        if (sQLiteDatabaseZzae != null) {
                            sQLiteDatabaseZzae.close();
                        }
                        return false;
                    }
                    sQLiteDatabaseZzae.beginTransaction();
                    sQLiteDatabaseZzae.delete("messages", "type == ?", new String[]{Integer.toString(3)});
                    sQLiteDatabaseZzae.setTransactionSuccessful();
                    sQLiteDatabaseZzae.endTransaction();
                    if (sQLiteDatabaseZzae != null) {
                        sQLiteDatabaseZzae.close();
                    }
                    return true;
                } catch (SQLiteDatabaseLockedException unused) {
                    SystemClock.sleep(i);
                    i += 20;
                    if (0 != 0) {
                        sQLiteDatabase.close();
                    }
                } catch (SQLiteException e2) {
                    if (0 != 0) {
                        try {
                            if (sQLiteDatabase.inTransaction()) {
                                sQLiteDatabase.endTransaction();
                            }
                        } catch (Throwable th) {
                            if (0 != 0) {
                                sQLiteDatabase.close();
                            }
                            throw th;
                        }
                    }
                    zzr().zzf().zza("Error deleting app launch break from local database", e2);
                    this.zzb = true;
                    if (0 != 0) {
                        sQLiteDatabase.close();
                    }
                }
            } catch (SQLiteFullException e3) {
                zzr().zzf().zza("Error deleting app launch break from local database", e3);
                this.zzb = true;
                if (0 != 0) {
                    sQLiteDatabase.close();
                }
            }
        }
        zzr().zzi().zza("Error deleting app launch break from local database in reasonable time");
        return false;
    }

    private static long zza(SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = null;
        try {
            cursorQuery = sQLiteDatabase.query("messages", new String[]{"rowid"}, "type=?", new String[]{Constants.DIALDEFNED_VERSION_CONNECT}, null, null, "rowid desc", "1");
            if (cursorQuery.moveToFirst()) {
                return cursorQuery.getLong(0);
            }
            if (cursorQuery == null) {
                return -1L;
            }
            cursorQuery.close();
            return -1L;
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    private final SQLiteDatabase zzae() throws SQLiteException {
        if (this.zzb) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zza.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzb = true;
        return null;
    }

    private final boolean zzaf() {
        return zzn().getDatabasePath("google_app_measurement_local.db").exists();
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzb zze() {
        return super.zze();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzhk zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzey zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzis zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzin zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzex zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzjt zzk() {
        return super.zzk();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzah zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ Clock zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ Context zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzez zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzkv zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ zzgc zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ zzfb zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzfo zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ zzw zzu() {
        return super.zzu();
    }
}