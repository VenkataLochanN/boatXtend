package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Pair;
import androidx.collection.ArrayMap;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.autonavi.base.amap.mapcore.AeUtil;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzbk;
import com.google.android.gms.internal.measurement.zzbs;
import com.google.android.gms.internal.measurement.zzfe;
import com.google.android.gms.internal.measurement.zzln;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzac extends zzkk {
    private static final String[] zzb = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
    private static final String[] zzc = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    private static final String[] zzd = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;"};
    private static final String[] zze = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    private static final String[] zzf = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    private static final String[] zzg = {"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzh = {"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
    private static final String[] zzi = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzad zzj;
    private final zzkg zzk;

    zzac(zzkj zzkjVar) {
        super(zzkjVar);
        this.zzk = new zzkg(zzm());
        this.zzj = new zzad(this, zzn(), "google_app_measurement.db");
    }

    @Override // com.google.android.gms.measurement.internal.zzkk
    protected final boolean zze() {
        return false;
    }

    public final void zzf() {
        zzak();
        c_().beginTransaction();
    }

    public final void b_() {
        zzak();
        c_().setTransactionSuccessful();
    }

    public final void zzh() {
        zzak();
        c_().endTransaction();
    }

    private final long zzb(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            try {
                Cursor cursorRawQuery = c_().rawQuery(str, strArr);
                if (cursorRawQuery.moveToFirst()) {
                    long j = cursorRawQuery.getLong(0);
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return j;
                }
                throw new SQLiteException("Database returned empty set");
            } catch (SQLiteException e2) {
                zzr().zzf().zza("Database error", str, e2);
                throw e2;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    private final long zza(String str, String[] strArr, long j) {
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = c_().rawQuery(str, strArr);
                if (cursorRawQuery.moveToFirst()) {
                    return cursorRawQuery.getLong(0);
                }
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return j;
            } catch (SQLiteException e2) {
                zzr().zzf().zza("Database error", str, e2);
                throw e2;
            }
        } finally {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
        }
    }

    final SQLiteDatabase c_() {
        zzd();
        try {
            return this.zzj.getWritableDatabase();
        } catch (SQLiteException e2) {
            zzr().zzi().zza("Error opening database", e2);
            throw e2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x015b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzaj zza(java.lang.String r27, java.lang.String r28) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 351
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zza(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzaj");
    }

    public final void zza(zzaj zzajVar) {
        Preconditions.checkNotNull(zzajVar);
        zzd();
        zzak();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzajVar.zza);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzajVar.zzb);
        contentValues.put("lifetime_count", Long.valueOf(zzajVar.zzc));
        contentValues.put("current_bundle_count", Long.valueOf(zzajVar.zzd));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzajVar.zzf));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzajVar.zzg));
        contentValues.put("last_bundled_day", zzajVar.zzh);
        contentValues.put("last_sampled_complex_event_id", zzajVar.zzi);
        contentValues.put("last_sampling_rate", zzajVar.zzj);
        if (zzt().zze(zzajVar.zza, zzap.zzbl)) {
            contentValues.put("current_session_count", Long.valueOf(zzajVar.zze));
        }
        contentValues.put("last_exempt_from_sampling", (zzajVar.zzk == null || !zzajVar.zzk.booleanValue()) ? null : 1L);
        try {
            if (c_().insertWithOnConflict("events", null, contentValues, 5) == -1) {
                zzr().zzf().zza("Failed to insert/update event aggregates (got -1). appId", zzfb.zza(zzajVar.zza));
            }
        } catch (SQLiteException e2) {
            zzr().zzf().zza("Error storing event aggregates. appId", zzfb.zza(zzajVar.zza), e2);
        }
    }

    public final void zzb(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzd();
        zzak();
        try {
            int iDelete = c_().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2});
            if (com.google.android.gms.internal.measurement.zzkw.zzb() && this.zza.zzb().zze(str, zzap.zzcx)) {
                return;
            }
            zzr().zzx().zza("Deleted user attribute rows", Integer.valueOf(iDelete));
        } catch (SQLiteException e2) {
            if (com.google.android.gms.internal.measurement.zzkw.zzb() && this.zza.zzb().zze(str, zzap.zzcx)) {
                zzr().zzf().zza("Error deleting user property. appId", zzfb.zza(str), zzo().zzc(str2), e2);
            } else {
                zzr().zzf().zza("Error deleting user attribute. appId", zzfb.zza(str), zzo().zzc(str2), e2);
            }
        }
    }

    public final boolean zza(zzks zzksVar) {
        Preconditions.checkNotNull(zzksVar);
        zzd();
        zzak();
        if (zzc(zzksVar.zza, zzksVar.zzc) == null) {
            if (zzkv.zza(zzksVar.zzc)) {
                if (zzb("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzksVar.zza}) >= 25) {
                    return false;
                }
            } else if (zzt().zze(zzksVar.zza, zzap.zzaz)) {
                if (!"_npa".equals(zzksVar.zzc) && zzb("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzksVar.zza, zzksVar.zzb}) >= 25) {
                    return false;
                }
            } else if (zzb("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzksVar.zza, zzksVar.zzb}) >= 25) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzksVar.zza);
        contentValues.put("origin", zzksVar.zzb);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzksVar.zzc);
        contentValues.put("set_timestamp", Long.valueOf(zzksVar.zzd));
        zza(contentValues, "value", zzksVar.zze);
        try {
            if (c_().insertWithOnConflict("user_attributes", null, contentValues, 5) == -1) {
                zzr().zzf().zza("Failed to insert/update user property (got -1). appId", zzfb.zza(zzksVar.zza));
            }
        } catch (SQLiteException e2) {
            zzr().zzf().zza("Error storing user property. appId", zzfb.zza(zzksVar.zza), e2);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ac  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzks zzc(java.lang.String r19, java.lang.String r20) {
        /*
            r18 = this;
            r8 = r20
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r19)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r20)
            r18.zzd()
            r18.zzak()
            r9 = 0
            android.database.sqlite.SQLiteDatabase r10 = r18.c_()     // Catch: java.lang.Throwable -> L81 android.database.sqlite.SQLiteException -> L86
            java.lang.String r11 = "user_attributes"
            java.lang.String r0 = "set_timestamp"
            java.lang.String r1 = "value"
            java.lang.String r2 = "origin"
            java.lang.String[] r12 = new java.lang.String[]{r0, r1, r2}     // Catch: java.lang.Throwable -> L81 android.database.sqlite.SQLiteException -> L86
            java.lang.String r13 = "app_id=? and name=?"
            r0 = 2
            java.lang.String[] r14 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L81 android.database.sqlite.SQLiteException -> L86
            r1 = 0
            r14[r1] = r19     // Catch: java.lang.Throwable -> L81 android.database.sqlite.SQLiteException -> L86
            r2 = 1
            r14[r2] = r8     // Catch: java.lang.Throwable -> L81 android.database.sqlite.SQLiteException -> L86
            r15 = 0
            r16 = 0
            r17 = 0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17)     // Catch: java.lang.Throwable -> L81 android.database.sqlite.SQLiteException -> L86
            boolean r3 = r10.moveToFirst()     // Catch: java.lang.Throwable -> L79 android.database.sqlite.SQLiteException -> L7d
            if (r3 != 0) goto L42
            if (r10 == 0) goto L41
            r10.close()
        L41:
            return r9
        L42:
            long r5 = r10.getLong(r1)     // Catch: java.lang.Throwable -> L79 android.database.sqlite.SQLiteException -> L7d
            r11 = r18
            java.lang.Object r7 = r11.zza(r10, r2)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> La9
            java.lang.String r3 = r10.getString(r0)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> La9
            com.google.android.gms.measurement.internal.zzks r0 = new com.google.android.gms.measurement.internal.zzks     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> La9
            r1 = r0
            r2 = r19
            r4 = r20
            r1.<init>(r2, r3, r4, r5, r7)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> La9
            boolean r1 = r10.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> La9
            if (r1 == 0) goto L71
            com.google.android.gms.measurement.internal.zzfb r1 = r18.zzr()     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> La9
            com.google.android.gms.measurement.internal.zzfd r1 = r1.zzf()     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> La9
            java.lang.String r2 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzfb.zza(r19)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> La9
            r1.zza(r2, r3)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> La9
        L71:
            if (r10 == 0) goto L76
            r10.close()
        L76:
            return r0
        L77:
            r0 = move-exception
            goto L8a
        L79:
            r0 = move-exception
            r11 = r18
            goto Laa
        L7d:
            r0 = move-exception
            r11 = r18
            goto L8a
        L81:
            r0 = move-exception
            r11 = r18
            r10 = r9
            goto Laa
        L86:
            r0 = move-exception
            r11 = r18
            r10 = r9
        L8a:
            com.google.android.gms.measurement.internal.zzfb r1 = r18.zzr()     // Catch: java.lang.Throwable -> La9
            com.google.android.gms.measurement.internal.zzfd r1 = r1.zzf()     // Catch: java.lang.Throwable -> La9
            java.lang.String r2 = "Error querying user property. appId"
            java.lang.Object r3 = com.google.android.gms.measurement.internal.zzfb.zza(r19)     // Catch: java.lang.Throwable -> La9
            com.google.android.gms.measurement.internal.zzez r4 = r18.zzo()     // Catch: java.lang.Throwable -> La9
            java.lang.String r4 = r4.zzc(r8)     // Catch: java.lang.Throwable -> La9
            r1.zza(r2, r3, r4, r0)     // Catch: java.lang.Throwable -> La9
            if (r10 == 0) goto La8
            r10.close()
        La8:
            return r9
        La9:
            r0 = move-exception
        Laa:
            if (r10 == 0) goto Laf
            r10.close()
        Laf:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zzc(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzks");
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.google.android.gms.measurement.internal.zzks> zza(java.lang.String r14) throws java.lang.Throwable {
        /*
            r13 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            r13.zzd()
            r13.zzak()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r13.c_()     // Catch: java.lang.Throwable -> L85 android.database.sqlite.SQLiteException -> L88
            java.lang.String r3 = "user_attributes"
            java.lang.String r4 = "name"
            java.lang.String r5 = "origin"
            java.lang.String r6 = "set_timestamp"
            java.lang.String r7 = "value"
            java.lang.String[] r4 = new java.lang.String[]{r4, r5, r6, r7}     // Catch: java.lang.Throwable -> L85 android.database.sqlite.SQLiteException -> L88
            java.lang.String r5 = "app_id=?"
            r11 = 1
            java.lang.String[] r6 = new java.lang.String[r11]     // Catch: java.lang.Throwable -> L85 android.database.sqlite.SQLiteException -> L88
            r12 = 0
            r6[r12] = r14     // Catch: java.lang.Throwable -> L85 android.database.sqlite.SQLiteException -> L88
            r7 = 0
            r8 = 0
            java.lang.String r9 = "rowid"
            java.lang.String r10 = "1000"
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L85 android.database.sqlite.SQLiteException -> L88
            boolean r3 = r2.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            if (r3 != 0) goto L42
            if (r2 == 0) goto L41
            r2.close()
        L41:
            return r0
        L42:
            java.lang.String r7 = r2.getString(r12)     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            java.lang.String r3 = r2.getString(r11)     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            if (r3 != 0) goto L4e
            java.lang.String r3 = ""
        L4e:
            r6 = r3
            r3 = 2
            long r8 = r2.getLong(r3)     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            r3 = 3
            java.lang.Object r10 = r13.zza(r2, r3)     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            if (r10 != 0) goto L6d
            com.google.android.gms.measurement.internal.zzfb r3 = r13.zzr()     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            com.google.android.gms.measurement.internal.zzfd r3 = r3.zzf()     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            java.lang.String r4 = "Read invalid user property value, ignoring it. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzfb.zza(r14)     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            r3.zza(r4, r5)     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            goto L77
        L6d:
            com.google.android.gms.measurement.internal.zzks r3 = new com.google.android.gms.measurement.internal.zzks     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            r4 = r3
            r5 = r14
            r4.<init>(r5, r6, r7, r8, r10)     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            r0.add(r3)     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
        L77:
            boolean r3 = r2.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L83 java.lang.Throwable -> La1
            if (r3 != 0) goto L42
            if (r2 == 0) goto L82
            r2.close()
        L82:
            return r0
        L83:
            r0 = move-exception
            goto L8a
        L85:
            r14 = move-exception
            r2 = r1
            goto La2
        L88:
            r0 = move-exception
            r2 = r1
        L8a:
            com.google.android.gms.measurement.internal.zzfb r3 = r13.zzr()     // Catch: java.lang.Throwable -> La1
            com.google.android.gms.measurement.internal.zzfd r3 = r3.zzf()     // Catch: java.lang.Throwable -> La1
            java.lang.String r4 = "Error querying user properties. appId"
            java.lang.Object r14 = com.google.android.gms.measurement.internal.zzfb.zza(r14)     // Catch: java.lang.Throwable -> La1
            r3.zza(r4, r14, r0)     // Catch: java.lang.Throwable -> La1
            if (r2 == 0) goto La0
            r2.close()
        La0:
            return r1
        La1:
            r14 = move-exception
        La2:
            if (r2 == 0) goto La7
            r2.close()
        La7:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zza(java.lang.String):java.util.List");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0093, code lost:
    
        zzr().zzf().zza("Read more than the max allowed user properties, ignoring excess", 1000);
     */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x012a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.google.android.gms.measurement.internal.zzks> zza(java.lang.String r22, java.lang.String r23, java.lang.String r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zza(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    public final boolean zza(zzv zzvVar) {
        Preconditions.checkNotNull(zzvVar);
        zzd();
        zzak();
        if (zzc(zzvVar.zza, zzvVar.zzc.zza) == null && zzb("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzvVar.zza}) >= 1000) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzvVar.zza);
        contentValues.put("origin", zzvVar.zzb);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzvVar.zzc.zza);
        zza(contentValues, "value", zzvVar.zzc.zza());
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.ACTIVE, Boolean.valueOf(zzvVar.zze));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_EVENT_NAME, zzvVar.zzf);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGER_TIMEOUT, Long.valueOf(zzvVar.zzh));
        zzp();
        contentValues.put("timed_out_event", zzkv.zza((Parcelable) zzvVar.zzg));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.CREATION_TIMESTAMP, Long.valueOf(zzvVar.zzd));
        zzp();
        contentValues.put("triggered_event", zzkv.zza((Parcelable) zzvVar.zzi));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TRIGGERED_TIMESTAMP, Long.valueOf(zzvVar.zzc.zzb));
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.TIME_TO_LIVE, Long.valueOf(zzvVar.zzj));
        zzp();
        contentValues.put("expired_event", zzkv.zza((Parcelable) zzvVar.zzk));
        try {
            if (c_().insertWithOnConflict("conditional_properties", null, contentValues, 5) == -1) {
                zzr().zzf().zza("Failed to insert/update conditional user property (got -1)", zzfb.zza(zzvVar.zza));
            }
        } catch (SQLiteException e2) {
            zzr().zzf().zza("Error storing conditional user property", zzfb.zza(zzvVar.zza), e2);
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x012c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzv zzd(java.lang.String r30, java.lang.String r31) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 304
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zzd(java.lang.String, java.lang.String):com.google.android.gms.measurement.internal.zzv");
    }

    public final int zze(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzd();
        zzak();
        try {
            return c_().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e2) {
            zzr().zzf().zza("Error deleting conditional property", zzfb.zza(str), zzo().zzc(str2), e2);
            return 0;
        }
    }

    public final List<zzv> zzb(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzd();
        zzak();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zza(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0059, code lost:
    
        zzr().zzf().zza("Read more than the max allowed conditional properties, ignoring extra", 1000);
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x012b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.google.android.gms.measurement.internal.zzv> zza(java.lang.String r25, java.lang.String[] r26) {
        /*
            Method dump skipped, instruction units count: 303
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zza(java.lang.String, java.lang.String[]):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0222  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.measurement.internal.zzg zzb(java.lang.String r35) {
        /*
            Method dump skipped, instruction units count: 550
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zzb(java.lang.String):com.google.android.gms.measurement.internal.zzg");
    }

    public final void zza(zzg zzgVar) {
        Preconditions.checkNotNull(zzgVar);
        zzd();
        zzak();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzgVar.zzc());
        contentValues.put("app_instance_id", zzgVar.zzd());
        contentValues.put("gmp_app_id", zzgVar.zze());
        contentValues.put("resettable_device_id_hash", zzgVar.zzh());
        contentValues.put("last_bundle_index", Long.valueOf(zzgVar.zzs()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzgVar.zzj()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzgVar.zzk()));
        contentValues.put("app_version", zzgVar.zzl());
        contentValues.put("app_store", zzgVar.zzn());
        contentValues.put("gmp_version", Long.valueOf(zzgVar.zzo()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzgVar.zzp()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzgVar.zzr()));
        contentValues.put("day", Long.valueOf(zzgVar.zzw()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzgVar.zzx()));
        contentValues.put("daily_events_count", Long.valueOf(zzgVar.zzy()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzgVar.zzz()));
        contentValues.put("config_fetched_time", Long.valueOf(zzgVar.zzt()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzgVar.zzu()));
        contentValues.put("app_version_int", Long.valueOf(zzgVar.zzm()));
        contentValues.put("firebase_instance_id", zzgVar.zzi());
        contentValues.put("daily_error_events_count", Long.valueOf(zzgVar.zzab()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzgVar.zzaa()));
        contentValues.put("health_monitor_sample", zzgVar.zzac());
        contentValues.put("android_id", Long.valueOf(zzgVar.zzae()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzgVar.zzaf()));
        contentValues.put("ssaid_reporting_enabled", Boolean.valueOf(zzgVar.zzag()));
        contentValues.put("admob_app_id", zzgVar.zzf());
        contentValues.put("dynamite_version", Long.valueOf(zzgVar.zzq()));
        if (zzgVar.zzai() != null) {
            if (zzgVar.zzai().size() == 0) {
                zzr().zzi().zza("Safelisted events should not be an empty list. appId", zzgVar.zzc());
            } else {
                contentValues.put("safelisted_events", TextUtils.join(AppInfo.DELIM, zzgVar.zzai()));
            }
        }
        if (zzln.zzb() && zzt().zze(zzgVar.zzc(), zzap.zzcf)) {
            contentValues.put("ga_app_id", zzgVar.zzg());
        }
        try {
            SQLiteDatabase sQLiteDatabaseC_ = c_();
            if (sQLiteDatabaseC_.update("apps", contentValues, "app_id = ?", new String[]{zzgVar.zzc()}) == 0 && sQLiteDatabaseC_.insertWithOnConflict("apps", null, contentValues, 5) == -1) {
                zzr().zzf().zza("Failed to insert/update app (got -1). appId", zzfb.zza(zzgVar.zzc()));
            }
        } catch (SQLiteException e2) {
            zzr().zzf().zza("Error storing app. appId", zzfb.zza(zzgVar.zzc()), e2);
        }
    }

    public final long zzc(String str) {
        Preconditions.checkNotEmpty(str);
        zzd();
        zzak();
        try {
            return c_().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, zzt().zzb(str, zzap.zzo))))});
        } catch (SQLiteException e2) {
            zzr().zzf().zza("Error deleting over the limit events. appId", zzfb.zza(str), e2);
            return 0L;
        }
    }

    public final zzab zza(long j, String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        Preconditions.checkNotEmpty(str);
        zzd();
        zzak();
        String[] strArr = {str};
        zzab zzabVar = new zzab();
        Cursor cursor = null;
        try {
            try {
                SQLiteDatabase sQLiteDatabaseC_ = c_();
                Cursor cursorQuery = sQLiteDatabaseC_.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{str}, null, null, null);
                if (!cursorQuery.moveToFirst()) {
                    zzr().zzi().zza("Not updating daily counts, app is not known. appId", zzfb.zza(str));
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return zzabVar;
                }
                if (cursorQuery.getLong(0) == j) {
                    zzabVar.zzb = cursorQuery.getLong(1);
                    zzabVar.zza = cursorQuery.getLong(2);
                    zzabVar.zzc = cursorQuery.getLong(3);
                    zzabVar.zzd = cursorQuery.getLong(4);
                    zzabVar.zze = cursorQuery.getLong(5);
                }
                if (z) {
                    zzabVar.zzb++;
                }
                if (z2) {
                    zzabVar.zza++;
                }
                if (z3) {
                    zzabVar.zzc++;
                }
                if (z4) {
                    zzabVar.zzd++;
                }
                if (z5) {
                    zzabVar.zze++;
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("day", Long.valueOf(j));
                contentValues.put("daily_public_events_count", Long.valueOf(zzabVar.zza));
                contentValues.put("daily_events_count", Long.valueOf(zzabVar.zzb));
                contentValues.put("daily_conversions_count", Long.valueOf(zzabVar.zzc));
                contentValues.put("daily_error_events_count", Long.valueOf(zzabVar.zzd));
                contentValues.put("daily_realtime_events_count", Long.valueOf(zzabVar.zze));
                sQLiteDatabaseC_.update("apps", contentValues, "app_id=?", strArr);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return zzabVar;
            } catch (SQLiteException e2) {
                zzr().zzf().zza("Error updating daily counts. appId", zzfb.zza(str), e2);
                if (0 != 0) {
                    cursor.close();
                }
                return zzabVar;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final byte[] zzd(java.lang.String r11) throws java.lang.Throwable {
        /*
            r10 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r11)
            r10.zzd()
            r10.zzak()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r10.c_()     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteException -> L57
            java.lang.String r2 = "apps"
            java.lang.String r3 = "remote_config"
            java.lang.String[] r3 = new java.lang.String[]{r3}     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteException -> L57
            java.lang.String r4 = "app_id=?"
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteException -> L57
            r9 = 0
            r5[r9] = r11     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteException -> L57
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L54 android.database.sqlite.SQLiteException -> L57
            boolean r2 = r1.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L52 java.lang.Throwable -> L70
            if (r2 != 0) goto L31
            if (r1 == 0) goto L30
            r1.close()
        L30:
            return r0
        L31:
            byte[] r2 = r1.getBlob(r9)     // Catch: android.database.sqlite.SQLiteException -> L52 java.lang.Throwable -> L70
            boolean r3 = r1.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L52 java.lang.Throwable -> L70
            if (r3 == 0) goto L4c
            com.google.android.gms.measurement.internal.zzfb r3 = r10.zzr()     // Catch: android.database.sqlite.SQLiteException -> L52 java.lang.Throwable -> L70
            com.google.android.gms.measurement.internal.zzfd r3 = r3.zzf()     // Catch: android.database.sqlite.SQLiteException -> L52 java.lang.Throwable -> L70
            java.lang.String r4 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.measurement.internal.zzfb.zza(r11)     // Catch: android.database.sqlite.SQLiteException -> L52 java.lang.Throwable -> L70
            r3.zza(r4, r5)     // Catch: android.database.sqlite.SQLiteException -> L52 java.lang.Throwable -> L70
        L4c:
            if (r1 == 0) goto L51
            r1.close()
        L51:
            return r2
        L52:
            r2 = move-exception
            goto L59
        L54:
            r11 = move-exception
            r1 = r0
            goto L71
        L57:
            r2 = move-exception
            r1 = r0
        L59:
            com.google.android.gms.measurement.internal.zzfb r3 = r10.zzr()     // Catch: java.lang.Throwable -> L70
            com.google.android.gms.measurement.internal.zzfd r3 = r3.zzf()     // Catch: java.lang.Throwable -> L70
            java.lang.String r4 = "Error querying remote config. appId"
            java.lang.Object r11 = com.google.android.gms.measurement.internal.zzfb.zza(r11)     // Catch: java.lang.Throwable -> L70
            r3.zza(r4, r11, r2)     // Catch: java.lang.Throwable -> L70
            if (r1 == 0) goto L6f
            r1.close()
        L6f:
            return r0
        L70:
            r11 = move-exception
        L71:
            if (r1 == 0) goto L76
            r1.close()
        L76:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zzd(java.lang.String):byte[]");
    }

    public final boolean zza(zzbs.zzg zzgVar, boolean z) {
        zzd();
        zzak();
        Preconditions.checkNotNull(zzgVar);
        Preconditions.checkNotEmpty(zzgVar.zzx());
        Preconditions.checkState(zzgVar.zzk());
        zzv();
        long jCurrentTimeMillis = zzm().currentTimeMillis();
        if (zzgVar.zzl() < jCurrentTimeMillis - zzx.zzj() || zzgVar.zzl() > zzx.zzj() + jCurrentTimeMillis) {
            zzr().zzi().zza("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzfb.zza(zzgVar.zzx()), Long.valueOf(jCurrentTimeMillis), Long.valueOf(zzgVar.zzl()));
        }
        try {
            byte[] bArrZzc = zzg().zzc(zzgVar.zzbi());
            zzr().zzx().zza("Saving bundle, size", Integer.valueOf(bArrZzc.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzgVar.zzx());
            contentValues.put("bundle_end_timestamp", Long.valueOf(zzgVar.zzl()));
            contentValues.put(AeUtil.ROOT_DATA_PATH_OLD_NAME, bArrZzc);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            if (zzgVar.zzaz()) {
                contentValues.put("retry_count", Integer.valueOf(zzgVar.zzba()));
            }
            try {
                if (c_().insert("queue", null, contentValues) != -1) {
                    return true;
                }
                zzr().zzf().zza("Failed to insert bundle (got -1). appId", zzfb.zza(zzgVar.zzx()));
                return false;
            } catch (SQLiteException e2) {
                zzr().zzf().zza("Error storing bundle. appId", zzfb.zza(zzgVar.zzx()), e2);
                return false;
            }
        } catch (IOException e3) {
            zzr().zzf().zza("Data loss. Failed to serialize bundle. appId", zzfb.zza(zzgVar.zzx()), e3);
            return false;
        }
    }

    public final String d_() throws Throwable {
        Throwable th;
        Cursor cursorRawQuery;
        try {
            cursorRawQuery = c_().rawQuery("select app_id from queue order by has_realtime desc, rowid asc limit 1;", null);
        } catch (SQLiteException e2) {
            e = e2;
            cursorRawQuery = null;
        } catch (Throwable th2) {
            th = th2;
            cursorRawQuery = null;
        }
        try {
            try {
                if (!cursorRawQuery.moveToFirst()) {
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return null;
                }
                String string = cursorRawQuery.getString(0);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return string;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (SQLiteException e3) {
            e = e3;
            zzr().zzf().zza("Database error getting next bundle app id", e);
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            return null;
        }
        th = th3;
        if (cursorRawQuery != null) {
            cursorRawQuery.close();
        }
        throw th;
    }

    public final boolean zzk() {
        return zzb("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    public final List<Pair<zzbs.zzg, Long>> zza(String str, int i, int i2) {
        byte[] bArrZzb;
        zzd();
        zzak();
        Preconditions.checkArgument(i > 0);
        Preconditions.checkArgument(i2 > 0);
        Preconditions.checkNotEmpty(str);
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = c_().query("queue", new String[]{"rowid", AeUtil.ROOT_DATA_PATH_OLD_NAME, "retry_count"}, "app_id=?", new String[]{str}, null, null, "rowid", String.valueOf(i));
                if (!cursorQuery.moveToFirst()) {
                    List<Pair<zzbs.zzg, Long>> listEmptyList = Collections.emptyList();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return listEmptyList;
                }
                ArrayList arrayList = new ArrayList();
                int length = 0;
                do {
                    long j = cursorQuery.getLong(0);
                    try {
                        bArrZzb = zzg().zzb(cursorQuery.getBlob(1));
                    } catch (IOException e2) {
                        zzr().zzf().zza("Failed to unzip queued bundle. appId", zzfb.zza(str), e2);
                    }
                    if (!arrayList.isEmpty() && bArrZzb.length + length > i2) {
                        break;
                    }
                    try {
                        zzbs.zzg.zza zzaVar = (zzbs.zzg.zza) zzkr.zza(zzbs.zzg.zzbf(), bArrZzb);
                        if (!cursorQuery.isNull(2)) {
                            zzaVar.zzi(cursorQuery.getInt(2));
                        }
                        length += bArrZzb.length;
                        arrayList.add(Pair.create((zzbs.zzg) ((com.google.android.gms.internal.measurement.zzfe) zzaVar.zzv()), Long.valueOf(j)));
                    } catch (IOException e3) {
                        zzr().zzf().zza("Failed to merge queued bundle. appId", zzfb.zza(str), e3);
                    }
                    if (!cursorQuery.moveToNext()) {
                        break;
                    }
                } while (length <= i2);
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return arrayList;
            } catch (SQLiteException e4) {
                zzr().zzf().zza("Error querying bundles. appId", zzfb.zza(str), e4);
                List<Pair<zzbs.zzg, Long>> listEmptyList2 = Collections.emptyList();
                if (0 != 0) {
                    cursor.close();
                }
                return listEmptyList2;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    final void zzv() {
        int iDelete;
        zzd();
        zzak();
        if (zzam()) {
            long jZza = zzs().zzf.zza();
            long jElapsedRealtime = zzm().elapsedRealtime();
            if (Math.abs(jElapsedRealtime - jZza) > zzap.zzx.zza(null).longValue()) {
                zzs().zzf.zza(jElapsedRealtime);
                zzd();
                zzak();
                if (!zzam() || (iDelete = c_().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzm().currentTimeMillis()), String.valueOf(zzx.zzj())})) <= 0) {
                    return;
                }
                zzr().zzx().zza("Deleted stale rows. rowsDeleted", Integer.valueOf(iDelete));
            }
        }
    }

    final void zza(List<Long> list) {
        zzd();
        zzak();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzam()) {
            String strJoin = TextUtils.join(AppInfo.DELIM, list);
            StringBuilder sb = new StringBuilder(String.valueOf(strJoin).length() + 2);
            sb.append("(");
            sb.append(strJoin);
            sb.append(")");
            String string = sb.toString();
            StringBuilder sb2 = new StringBuilder(String.valueOf(string).length() + 80);
            sb2.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
            sb2.append(string);
            sb2.append(" AND retry_count =  2147483647 LIMIT 1");
            if (zzb(sb2.toString(), (String[]) null) > 0) {
                zzr().zzi().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase sQLiteDatabaseC_ = c_();
                StringBuilder sb3 = new StringBuilder(String.valueOf(string).length() + 127);
                sb3.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
                sb3.append(string);
                sb3.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");
                sQLiteDatabaseC_.execSQL(sb3.toString());
            } catch (SQLiteException e2) {
                zzr().zzf().zza("Error incrementing retry count. error", e2);
            }
        }
    }

    private final boolean zza(String str, int i, zzbk.zzb zzbVar) {
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzbVar);
        if (TextUtils.isEmpty(zzbVar.zzc())) {
            zzr().zzi().zza("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzfb.zza(str), Integer.valueOf(i), String.valueOf(zzbVar.zza() ? Integer.valueOf(zzbVar.zzb()) : null));
            return false;
        }
        byte[] bArrZzbi = zzbVar.zzbi();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i));
        contentValues.put("filter_id", zzbVar.zza() ? Integer.valueOf(zzbVar.zzb()) : null);
        contentValues.put("event_name", zzbVar.zzc());
        if (zzt().zze(str, zzap.zzbk)) {
            contentValues.put("session_scoped", zzbVar.zzj() ? Boolean.valueOf(zzbVar.zzk()) : null);
        }
        contentValues.put(AeUtil.ROOT_DATA_PATH_OLD_NAME, bArrZzbi);
        try {
            if (c_().insertWithOnConflict("event_filters", null, contentValues, 5) != -1) {
                return true;
            }
            zzr().zzf().zza("Failed to insert event filter (got -1). appId", zzfb.zza(str));
            return true;
        } catch (SQLiteException e2) {
            zzr().zzf().zza("Error storing event filter. appId", zzfb.zza(str), e2);
            return false;
        }
    }

    private final boolean zza(String str, int i, zzbk.zze zzeVar) {
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzeVar);
        if (TextUtils.isEmpty(zzeVar.zzc())) {
            zzr().zzi().zza("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzfb.zza(str), Integer.valueOf(i), String.valueOf(zzeVar.zza() ? Integer.valueOf(zzeVar.zzb()) : null));
            return false;
        }
        byte[] bArrZzbi = zzeVar.zzbi();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("audience_id", Integer.valueOf(i));
        contentValues.put("filter_id", zzeVar.zza() ? Integer.valueOf(zzeVar.zzb()) : null);
        contentValues.put("property_name", zzeVar.zzc());
        if (zzt().zze(str, zzap.zzbk)) {
            contentValues.put("session_scoped", zzeVar.zzg() ? Boolean.valueOf(zzeVar.zzh()) : null);
        }
        contentValues.put(AeUtil.ROOT_DATA_PATH_OLD_NAME, bArrZzbi);
        try {
            if (c_().insertWithOnConflict("property_filters", null, contentValues, 5) != -1) {
                return true;
            }
            zzr().zzf().zza("Failed to insert property filter (got -1). appId", zzfb.zza(str));
            return false;
        } catch (SQLiteException e2) {
            zzr().zzf().zza("Error storing property filter. appId", zzfb.zza(str), e2);
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b7  */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v3, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzbk.zzb>> zzf(java.lang.String r13, java.lang.String r14) throws java.lang.Throwable {
        /*
            r12 = this;
            r12.zzak()
            r12.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.c_()
            r9 = 0
            java.lang.String r2 = "event_filters"
            java.lang.String r3 = "audience_id"
            java.lang.String r4 = "data"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4}     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            java.lang.String r4 = "app_id=? AND event_name=?"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            r10 = 0
            r5[r10] = r13     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            r11 = 1
            r5[r11] = r14     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            boolean r1 = r14.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            if (r1 != 0) goto L42
            java.util.Map r13 = java.util.Collections.emptyMap()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            if (r14 == 0) goto L41
            r14.close()
        L41:
            return r13
        L42:
            byte[] r1 = r14.getBlob(r11)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.internal.measurement.zzbk$zzb$zza r2 = com.google.android.gms.internal.measurement.zzbk.zzb.zzl()     // Catch: java.io.IOException -> L78 android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.internal.measurement.zzgp r1 = com.google.android.gms.measurement.internal.zzkr.zza(r2, r1)     // Catch: java.io.IOException -> L78 android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.internal.measurement.zzbk$zzb$zza r1 = (com.google.android.gms.internal.measurement.zzbk.zzb.zza) r1     // Catch: java.io.IOException -> L78 android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.internal.measurement.zzgm r1 = r1.zzv()     // Catch: java.io.IOException -> L78 android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.internal.measurement.zzfe r1 = (com.google.android.gms.internal.measurement.zzfe) r1     // Catch: java.io.IOException -> L78 android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.internal.measurement.zzbk$zzb r1 = (com.google.android.gms.internal.measurement.zzbk.zzb) r1     // Catch: java.io.IOException -> L78 android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            int r2 = r14.getInt(r10)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.lang.Object r3 = r0.get(r3)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.util.List r3 = (java.util.List) r3     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            if (r3 != 0) goto L74
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            r3.<init>()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            r0.put(r2, r3)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
        L74:
            r3.add(r1)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            goto L8a
        L78:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzfb r2 = r12.zzr()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.measurement.internal.zzfd r2 = r2.zzf()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.lang.String r3 = "Failed to merge filter. appId"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzfb.zza(r13)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            r2.zza(r3, r4, r1)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
        L8a:
            boolean r1 = r14.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            if (r1 != 0) goto L42
            if (r14 == 0) goto L95
            r14.close()
        L95:
            return r0
        L96:
            r0 = move-exception
            goto L9d
        L98:
            r13 = move-exception
            r14 = r9
            goto Lb5
        L9b:
            r0 = move-exception
            r14 = r9
        L9d:
            com.google.android.gms.measurement.internal.zzfb r1 = r12.zzr()     // Catch: java.lang.Throwable -> Lb4
            com.google.android.gms.measurement.internal.zzfd r1 = r1.zzf()     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzfb.zza(r13)     // Catch: java.lang.Throwable -> Lb4
            r1.zza(r2, r13, r0)     // Catch: java.lang.Throwable -> Lb4
            if (r14 == 0) goto Lb3
            r14.close()
        Lb3:
            return r9
        Lb4:
            r13 = move-exception
        Lb5:
            if (r14 == 0) goto Lba
            r14.close()
        Lba:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zzf(java.lang.String, java.lang.String):java.util.Map");
    }

    final Map<Integer, List<zzbk.zzb>> zze(String str) {
        Preconditions.checkNotEmpty(str);
        ArrayMap arrayMap = new ArrayMap();
        Cursor cursor = null;
        try {
            try {
                Cursor cursorQuery = c_().query("event_filters", new String[]{"audience_id", AeUtil.ROOT_DATA_PATH_OLD_NAME}, "app_id=?", new String[]{str}, null, null, null);
                if (!cursorQuery.moveToFirst()) {
                    Map<Integer, List<zzbk.zzb>> mapEmptyMap = Collections.emptyMap();
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return mapEmptyMap;
                }
                do {
                    try {
                        zzbk.zzb zzbVar = (zzbk.zzb) ((com.google.android.gms.internal.measurement.zzfe) ((zzbk.zzb.zza) zzkr.zza(zzbk.zzb.zzl(), cursorQuery.getBlob(1))).zzv());
                        if (zzbVar.zzf()) {
                            int i = cursorQuery.getInt(0);
                            List arrayList = (List) arrayMap.get(Integer.valueOf(i));
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                                arrayMap.put(Integer.valueOf(i), arrayList);
                            }
                            arrayList.add(zzbVar);
                        }
                    } catch (IOException e2) {
                        zzr().zzf().zza("Failed to merge filter. appId", zzfb.zza(str), e2);
                    }
                } while (cursorQuery.moveToNext());
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return arrayMap;
            } catch (SQLiteException e3) {
                zzr().zzf().zza("Database error querying filters. appId", zzfb.zza(str), e3);
                Map<Integer, List<zzbk.zzb>> mapEmptyMap2 = Collections.emptyMap();
                if (0 != 0) {
                    cursor.close();
                }
                return mapEmptyMap2;
            }
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00b7  */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v3, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzbk.zze>> zzg(java.lang.String r13, java.lang.String r14) throws java.lang.Throwable {
        /*
            r12 = this;
            r12.zzak()
            r12.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.c_()
            r9 = 0
            java.lang.String r2 = "property_filters"
            java.lang.String r3 = "audience_id"
            java.lang.String r4 = "data"
            java.lang.String[] r3 = new java.lang.String[]{r3, r4}     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            java.lang.String r4 = "app_id=? AND property_name=?"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            r10 = 0
            r5[r10] = r13     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            r11 = 1
            r5[r11] = r14     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L98 android.database.sqlite.SQLiteException -> L9b
            boolean r1 = r14.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            if (r1 != 0) goto L42
            java.util.Map r13 = java.util.Collections.emptyMap()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            if (r14 == 0) goto L41
            r14.close()
        L41:
            return r13
        L42:
            byte[] r1 = r14.getBlob(r11)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.internal.measurement.zzbk$zze$zza r2 = com.google.android.gms.internal.measurement.zzbk.zze.zzi()     // Catch: java.io.IOException -> L78 android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.internal.measurement.zzgp r1 = com.google.android.gms.measurement.internal.zzkr.zza(r2, r1)     // Catch: java.io.IOException -> L78 android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.internal.measurement.zzbk$zze$zza r1 = (com.google.android.gms.internal.measurement.zzbk.zze.zza) r1     // Catch: java.io.IOException -> L78 android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.internal.measurement.zzgm r1 = r1.zzv()     // Catch: java.io.IOException -> L78 android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.internal.measurement.zzfe r1 = (com.google.android.gms.internal.measurement.zzfe) r1     // Catch: java.io.IOException -> L78 android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.internal.measurement.zzbk$zze r1 = (com.google.android.gms.internal.measurement.zzbk.zze) r1     // Catch: java.io.IOException -> L78 android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            int r2 = r14.getInt(r10)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.lang.Object r3 = r0.get(r3)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.util.List r3 = (java.util.List) r3     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            if (r3 != 0) goto L74
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            r3.<init>()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            r0.put(r2, r3)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
        L74:
            r3.add(r1)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            goto L8a
        L78:
            r1 = move-exception
            com.google.android.gms.measurement.internal.zzfb r2 = r12.zzr()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            com.google.android.gms.measurement.internal.zzfd r2 = r2.zzf()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            java.lang.String r3 = "Failed to merge filter"
            java.lang.Object r4 = com.google.android.gms.measurement.internal.zzfb.zza(r13)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            r2.zza(r3, r4, r1)     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
        L8a:
            boolean r1 = r14.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L96 java.lang.Throwable -> Lb4
            if (r1 != 0) goto L42
            if (r14 == 0) goto L95
            r14.close()
        L95:
            return r0
        L96:
            r0 = move-exception
            goto L9d
        L98:
            r13 = move-exception
            r14 = r9
            goto Lb5
        L9b:
            r0 = move-exception
            r14 = r9
        L9d:
            com.google.android.gms.measurement.internal.zzfb r1 = r12.zzr()     // Catch: java.lang.Throwable -> Lb4
            com.google.android.gms.measurement.internal.zzfd r1 = r1.zzf()     // Catch: java.lang.Throwable -> Lb4
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.measurement.internal.zzfb.zza(r13)     // Catch: java.lang.Throwable -> Lb4
            r1.zza(r2, r13, r0)     // Catch: java.lang.Throwable -> Lb4
            if (r14 == 0) goto Lb3
            r14.close()
        Lb3:
            return r9
        Lb4:
            r13 = move-exception
        Lb5:
            if (r14 == 0) goto Lba
            r14.close()
        Lba:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zzg(java.lang.String, java.lang.String):java.util.Map");
    }

    final Map<Integer, List<Integer>> zza(String str, List<String> list) throws Throwable {
        Cursor cursorQuery;
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        ArrayMap arrayMap = new ArrayMap();
        if (list.isEmpty()) {
            return arrayMap;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("app_id=? AND property_name in (");
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append(AppInfo.DELIM);
            }
            sb.append("?");
        }
        sb.append(")");
        ArrayList arrayList = new ArrayList(list);
        arrayList.add(0, str);
        try {
            cursorQuery = c_().query("property_filters", new String[]{"audience_id", "filter_id"}, sb.toString(), (String[]) arrayList.toArray(new String[0]), null, null, null);
        } catch (SQLiteException e2) {
            e = e2;
            cursorQuery = null;
        } catch (Throwable th) {
            th = th;
            cursorQuery = null;
        }
        try {
            try {
                if (!cursorQuery.moveToFirst()) {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return arrayMap;
                }
                do {
                    int i2 = cursorQuery.getInt(0);
                    List arrayList2 = (List) arrayMap.get(Integer.valueOf(i2));
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                        arrayMap.put(Integer.valueOf(i2), arrayList2);
                    }
                    arrayList2.add(Integer.valueOf(cursorQuery.getInt(1)));
                } while (cursorQuery.moveToNext());
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
                return arrayMap;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (SQLiteException e3) {
            e = e3;
            zzr().zzf().zza("Database error querying filters. appId", zzfb.zza(str), e);
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return null;
        }
        th = th2;
        if (cursorQuery != null) {
            cursorQuery.close();
        }
        throw th;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0086  */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v3, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final java.util.Map<java.lang.Integer, java.util.List<java.lang.Integer>> zzf(java.lang.String r8) throws java.lang.Throwable {
        /*
            r7 = this;
            r7.zzak()
            r7.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8)
            androidx.collection.ArrayMap r0 = new androidx.collection.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r7.c_()
            r2 = 0
            java.lang.String r3 = "select audience_id, filter_id from event_filters where app_id = ? and session_scoped = 1 UNION select audience_id, filter_id from property_filters where app_id = ? and session_scoped = 1;"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L67 android.database.sqlite.SQLiteException -> L6a
            r5 = 0
            r4[r5] = r8     // Catch: java.lang.Throwable -> L67 android.database.sqlite.SQLiteException -> L6a
            r6 = 1
            r4[r6] = r8     // Catch: java.lang.Throwable -> L67 android.database.sqlite.SQLiteException -> L6a
            android.database.Cursor r1 = r1.rawQuery(r3, r4)     // Catch: java.lang.Throwable -> L67 android.database.sqlite.SQLiteException -> L6a
            boolean r3 = r1.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L65 java.lang.Throwable -> L83
            if (r3 != 0) goto L32
            java.util.Map r8 = java.util.Collections.emptyMap()     // Catch: android.database.sqlite.SQLiteException -> L65 java.lang.Throwable -> L83
            if (r1 == 0) goto L31
            r1.close()
        L31:
            return r8
        L32:
            int r3 = r1.getInt(r5)     // Catch: android.database.sqlite.SQLiteException -> L65 java.lang.Throwable -> L83
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)     // Catch: android.database.sqlite.SQLiteException -> L65 java.lang.Throwable -> L83
            java.lang.Object r4 = r0.get(r4)     // Catch: android.database.sqlite.SQLiteException -> L65 java.lang.Throwable -> L83
            java.util.List r4 = (java.util.List) r4     // Catch: android.database.sqlite.SQLiteException -> L65 java.lang.Throwable -> L83
            if (r4 != 0) goto L4e
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch: android.database.sqlite.SQLiteException -> L65 java.lang.Throwable -> L83
            r4.<init>()     // Catch: android.database.sqlite.SQLiteException -> L65 java.lang.Throwable -> L83
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: android.database.sqlite.SQLiteException -> L65 java.lang.Throwable -> L83
            r0.put(r3, r4)     // Catch: android.database.sqlite.SQLiteException -> L65 java.lang.Throwable -> L83
        L4e:
            int r3 = r1.getInt(r6)     // Catch: android.database.sqlite.SQLiteException -> L65 java.lang.Throwable -> L83
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch: android.database.sqlite.SQLiteException -> L65 java.lang.Throwable -> L83
            r4.add(r3)     // Catch: android.database.sqlite.SQLiteException -> L65 java.lang.Throwable -> L83
            boolean r3 = r1.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L65 java.lang.Throwable -> L83
            if (r3 != 0) goto L32
            if (r1 == 0) goto L64
            r1.close()
        L64:
            return r0
        L65:
            r0 = move-exception
            goto L6c
        L67:
            r8 = move-exception
            r1 = r2
            goto L84
        L6a:
            r0 = move-exception
            r1 = r2
        L6c:
            com.google.android.gms.measurement.internal.zzfb r3 = r7.zzr()     // Catch: java.lang.Throwable -> L83
            com.google.android.gms.measurement.internal.zzfd r3 = r3.zzf()     // Catch: java.lang.Throwable -> L83
            java.lang.String r4 = "Database error querying scoped filters. appId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzfb.zza(r8)     // Catch: java.lang.Throwable -> L83
            r3.zza(r4, r8, r0)     // Catch: java.lang.Throwable -> L83
            if (r1 == 0) goto L82
            r1.close()
        L82:
            return r2
        L83:
            r8 = move-exception
        L84:
            if (r1 == 0) goto L89
            r1.close()
        L89:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zzf(java.lang.String):java.util.Map");
    }

    private final boolean zzc(String str, List<Integer> list) {
        Preconditions.checkNotEmpty(str);
        zzak();
        zzd();
        SQLiteDatabase sQLiteDatabaseC_ = c_();
        try {
            long jZzb = zzb("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int iMax = Math.max(0, Math.min(2000, zzt().zzb(str, zzap.zzae)));
            if (jZzb <= iMax) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = list.get(i);
                if (num == null || !(num instanceof Integer)) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String strJoin = TextUtils.join(AppInfo.DELIM, arrayList);
            StringBuilder sb = new StringBuilder(String.valueOf(strJoin).length() + 2);
            sb.append("(");
            sb.append(strJoin);
            sb.append(")");
            String string = sb.toString();
            StringBuilder sb2 = new StringBuilder(String.valueOf(string).length() + 140);
            sb2.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
            sb2.append(string);
            sb2.append(" order by rowid desc limit -1 offset ?)");
            return sQLiteDatabaseC_.delete("audience_filter_values", sb2.toString(), new String[]{str, Integer.toString(iMax)}) > 0;
        } catch (SQLiteException e2) {
            zzr().zzf().zza("Database error querying filters. appId", zzfb.zza(str), e2);
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x009d  */
    /* JADX WARN: Type inference failed for: r0v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final java.util.Map<java.lang.Integer, com.google.android.gms.internal.measurement.zzbs.zzi> zzg(java.lang.String r12) throws java.lang.Throwable {
        /*
            r11 = this;
            r11.zzak()
            r11.zzd()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            android.database.sqlite.SQLiteDatabase r0 = r11.c_()
            r8 = 0
            java.lang.String r1 = "audience_filter_values"
            java.lang.String r2 = "audience_id"
            java.lang.String r3 = "current_results"
            java.lang.String[] r2 = new java.lang.String[]{r2, r3}     // Catch: java.lang.Throwable -> L7e android.database.sqlite.SQLiteException -> L81
            java.lang.String r3 = "app_id=?"
            r9 = 1
            java.lang.String[] r4 = new java.lang.String[r9]     // Catch: java.lang.Throwable -> L7e android.database.sqlite.SQLiteException -> L81
            r10 = 0
            r4[r10] = r12     // Catch: java.lang.Throwable -> L7e android.database.sqlite.SQLiteException -> L81
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L7e android.database.sqlite.SQLiteException -> L81
            boolean r1 = r0.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
            if (r1 != 0) goto L33
            if (r0 == 0) goto L32
            r0.close()
        L32:
            return r8
        L33:
            androidx.collection.ArrayMap r1 = new androidx.collection.ArrayMap     // Catch: android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
            r1.<init>()     // Catch: android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
        L38:
            int r2 = r0.getInt(r10)     // Catch: android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
            byte[] r3 = r0.getBlob(r9)     // Catch: android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
            com.google.android.gms.internal.measurement.zzbs$zzi$zza r4 = com.google.android.gms.internal.measurement.zzbs.zzi.zzi()     // Catch: java.io.IOException -> L5a android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
            com.google.android.gms.internal.measurement.zzgp r3 = com.google.android.gms.measurement.internal.zzkr.zza(r4, r3)     // Catch: java.io.IOException -> L5a android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
            com.google.android.gms.internal.measurement.zzbs$zzi$zza r3 = (com.google.android.gms.internal.measurement.zzbs.zzi.zza) r3     // Catch: java.io.IOException -> L5a android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
            com.google.android.gms.internal.measurement.zzgm r3 = r3.zzv()     // Catch: java.io.IOException -> L5a android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
            com.google.android.gms.internal.measurement.zzfe r3 = (com.google.android.gms.internal.measurement.zzfe) r3     // Catch: java.io.IOException -> L5a android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
            com.google.android.gms.internal.measurement.zzbs$zzi r3 = (com.google.android.gms.internal.measurement.zzbs.zzi) r3     // Catch: java.io.IOException -> L5a android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
            r1.put(r2, r3)     // Catch: android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
            goto L70
        L5a:
            r3 = move-exception
            com.google.android.gms.measurement.internal.zzfb r4 = r11.zzr()     // Catch: android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
            com.google.android.gms.measurement.internal.zzfd r4 = r4.zzf()     // Catch: android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
            java.lang.String r5 = "Failed to merge filter results. appId, audienceId, error"
            java.lang.Object r6 = com.google.android.gms.measurement.internal.zzfb.zza(r12)     // Catch: android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch: android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
            r4.zza(r5, r6, r2, r3)     // Catch: android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
        L70:
            boolean r2 = r0.moveToNext()     // Catch: android.database.sqlite.SQLiteException -> L7c java.lang.Throwable -> L9a
            if (r2 != 0) goto L38
            if (r0 == 0) goto L7b
            r0.close()
        L7b:
            return r1
        L7c:
            r1 = move-exception
            goto L83
        L7e:
            r12 = move-exception
            r0 = r8
            goto L9b
        L81:
            r1 = move-exception
            r0 = r8
        L83:
            com.google.android.gms.measurement.internal.zzfb r2 = r11.zzr()     // Catch: java.lang.Throwable -> L9a
            com.google.android.gms.measurement.internal.zzfd r2 = r2.zzf()     // Catch: java.lang.Throwable -> L9a
            java.lang.String r3 = "Database error querying filter results. appId"
            java.lang.Object r12 = com.google.android.gms.measurement.internal.zzfb.zza(r12)     // Catch: java.lang.Throwable -> L9a
            r2.zza(r3, r12, r1)     // Catch: java.lang.Throwable -> L9a
            if (r0 == 0) goto L99
            r0.close()
        L99:
            return r8
        L9a:
            r12 = move-exception
        L9b:
            if (r0 == 0) goto La0
            r0.close()
        La0:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zzg(java.lang.String):java.util.Map");
    }

    private static void zza(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else {
            if (obj instanceof Double) {
                contentValues.put(str, (Double) obj);
                return;
            }
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        if (type == 0) {
            zzr().zzf().zza("Loaded invalid null value from database");
            return null;
        }
        if (type == 1) {
            return Long.valueOf(cursor.getLong(i));
        }
        if (type == 2) {
            return Double.valueOf(cursor.getDouble(i));
        }
        if (type == 3) {
            return cursor.getString(i);
        }
        if (type == 4) {
            zzr().zzf().zza("Loaded invalid blob type value, ignoring it");
            return null;
        }
        zzr().zzf().zza("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
        return null;
    }

    public final long zzw() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0L);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(5:(3:30|3|4)|(5:6|(3:8|9|10)(1:11)|25|26|27)|32|12|(3:14|15|16)(3:17|18|27)) */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00c0, code lost:
    
        r3 = e;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final long zzh(java.lang.String r14, java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zzh(java.lang.String, java.lang.String):long");
    }

    public final long zzx() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0L);
    }

    public final long zza(zzbs.zzg zzgVar) throws IOException {
        zzd();
        zzak();
        Preconditions.checkNotNull(zzgVar);
        Preconditions.checkNotEmpty(zzgVar.zzx());
        byte[] bArrZzbi = zzgVar.zzbi();
        long jZza = zzg().zza(bArrZzbi);
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzgVar.zzx());
        contentValues.put("metadata_fingerprint", Long.valueOf(jZza));
        contentValues.put("metadata", bArrZzbi);
        try {
            c_().insertWithOnConflict("raw_events_metadata", null, contentValues, 4);
            return jZza;
        } catch (SQLiteException e2) {
            zzr().zzf().zza("Error storing raw event metadata. appId", zzfb.zza(zzgVar.zzx()), e2);
            throw e2;
        }
    }

    public final boolean zzy() {
        return zzb("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzz() {
        return zzb("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    public final long zzh(String str) {
        Preconditions.checkNotEmpty(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0L);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:26:0x005b  */
    /* JADX WARN: Type inference failed for: r5v0, types: [long] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v3, types: [android.database.Cursor] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String zza(long r5) throws java.lang.Throwable {
        /*
            r4 = this;
            r4.zzd()
            r4.zzak()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r4.c_()     // Catch: java.lang.Throwable -> L40 android.database.sqlite.SQLiteException -> L43
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L40 android.database.sqlite.SQLiteException -> L43
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch: java.lang.Throwable -> L40 android.database.sqlite.SQLiteException -> L43
            r6 = 0
            r3[r6] = r5     // Catch: java.lang.Throwable -> L40 android.database.sqlite.SQLiteException -> L43
            android.database.Cursor r5 = r1.rawQuery(r2, r3)     // Catch: java.lang.Throwable -> L40 android.database.sqlite.SQLiteException -> L43
            boolean r1 = r5.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L3e java.lang.Throwable -> L58
            if (r1 != 0) goto L34
            com.google.android.gms.measurement.internal.zzfb r6 = r4.zzr()     // Catch: android.database.sqlite.SQLiteException -> L3e java.lang.Throwable -> L58
            com.google.android.gms.measurement.internal.zzfd r6 = r6.zzx()     // Catch: android.database.sqlite.SQLiteException -> L3e java.lang.Throwable -> L58
            java.lang.String r1 = "No expired configs for apps with pending events"
            r6.zza(r1)     // Catch: android.database.sqlite.SQLiteException -> L3e java.lang.Throwable -> L58
            if (r5 == 0) goto L33
            r5.close()
        L33:
            return r0
        L34:
            java.lang.String r6 = r5.getString(r6)     // Catch: android.database.sqlite.SQLiteException -> L3e java.lang.Throwable -> L58
            if (r5 == 0) goto L3d
            r5.close()
        L3d:
            return r6
        L3e:
            r6 = move-exception
            goto L45
        L40:
            r6 = move-exception
            r5 = r0
            goto L59
        L43:
            r6 = move-exception
            r5 = r0
        L45:
            com.google.android.gms.measurement.internal.zzfb r1 = r4.zzr()     // Catch: java.lang.Throwable -> L58
            com.google.android.gms.measurement.internal.zzfd r1 = r1.zzf()     // Catch: java.lang.Throwable -> L58
            java.lang.String r2 = "Error selecting expired configs"
            r1.zza(r2, r6)     // Catch: java.lang.Throwable -> L58
            if (r5 == 0) goto L57
            r5.close()
        L57:
            return r0
        L58:
            r6 = move-exception
        L59:
            if (r5 == 0) goto L5e
            r5.close()
        L5e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zza(long):java.lang.String");
    }

    public final long zzaa() {
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = c_().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
                if (!cursorRawQuery.moveToFirst()) {
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return -1L;
                }
                long j = cursorRawQuery.getLong(0);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return j;
            } catch (SQLiteException e2) {
                zzr().zzf().zza("Error querying raw events", e2);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return -1L;
            }
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0094  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.util.Pair<com.google.android.gms.internal.measurement.zzbs.zzc, java.lang.Long> zza(java.lang.String r8, java.lang.Long r9) throws java.lang.Throwable {
        /*
            r7 = this;
            r7.zzd()
            r7.zzak()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r7.c_()     // Catch: java.lang.Throwable -> L79 android.database.sqlite.SQLiteException -> L7c
            java.lang.String r2 = "select main_event, children_to_process from main_event_params where app_id=? and event_id=?"
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L79 android.database.sqlite.SQLiteException -> L7c
            r4 = 0
            r3[r4] = r8     // Catch: java.lang.Throwable -> L79 android.database.sqlite.SQLiteException -> L7c
            java.lang.String r5 = java.lang.String.valueOf(r9)     // Catch: java.lang.Throwable -> L79 android.database.sqlite.SQLiteException -> L7c
            r6 = 1
            r3[r6] = r5     // Catch: java.lang.Throwable -> L79 android.database.sqlite.SQLiteException -> L7c
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch: java.lang.Throwable -> L79 android.database.sqlite.SQLiteException -> L7c
            boolean r2 = r1.moveToFirst()     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L91
            if (r2 != 0) goto L37
            com.google.android.gms.measurement.internal.zzfb r8 = r7.zzr()     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L91
            com.google.android.gms.measurement.internal.zzfd r8 = r8.zzx()     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L91
            java.lang.String r9 = "Main event not found"
            r8.zza(r9)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L91
            if (r1 == 0) goto L36
            r1.close()
        L36:
            return r0
        L37:
            byte[] r2 = r1.getBlob(r4)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L91
            long r3 = r1.getLong(r6)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L91
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L91
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r4 = com.google.android.gms.internal.measurement.zzbs.zzc.zzj()     // Catch: java.io.IOException -> L5f android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L91
            com.google.android.gms.internal.measurement.zzgp r2 = com.google.android.gms.measurement.internal.zzkr.zza(r4, r2)     // Catch: java.io.IOException -> L5f android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L91
            com.google.android.gms.internal.measurement.zzbs$zzc$zza r2 = (com.google.android.gms.internal.measurement.zzbs.zzc.zza) r2     // Catch: java.io.IOException -> L5f android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L91
            com.google.android.gms.internal.measurement.zzgm r2 = r2.zzv()     // Catch: java.io.IOException -> L5f android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L91
            com.google.android.gms.internal.measurement.zzfe r2 = (com.google.android.gms.internal.measurement.zzfe) r2     // Catch: java.io.IOException -> L5f android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L91
            com.google.android.gms.internal.measurement.zzbs$zzc r2 = (com.google.android.gms.internal.measurement.zzbs.zzc) r2     // Catch: java.io.IOException -> L5f android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L91
            android.util.Pair r8 = android.util.Pair.create(r2, r3)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L91
            if (r1 == 0) goto L5e
            r1.close()
        L5e:
            return r8
        L5f:
            r2 = move-exception
            com.google.android.gms.measurement.internal.zzfb r3 = r7.zzr()     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L91
            com.google.android.gms.measurement.internal.zzfd r3 = r3.zzf()     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L91
            java.lang.String r4 = "Failed to merge main event. appId, eventId"
            java.lang.Object r8 = com.google.android.gms.measurement.internal.zzfb.zza(r8)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L91
            r3.zza(r4, r8, r9, r2)     // Catch: android.database.sqlite.SQLiteException -> L77 java.lang.Throwable -> L91
            if (r1 == 0) goto L76
            r1.close()
        L76:
            return r0
        L77:
            r8 = move-exception
            goto L7e
        L79:
            r8 = move-exception
            r1 = r0
            goto L92
        L7c:
            r8 = move-exception
            r1 = r0
        L7e:
            com.google.android.gms.measurement.internal.zzfb r9 = r7.zzr()     // Catch: java.lang.Throwable -> L91
            com.google.android.gms.measurement.internal.zzfd r9 = r9.zzf()     // Catch: java.lang.Throwable -> L91
            java.lang.String r2 = "Error selecting main event"
            r9.zza(r2, r8)     // Catch: java.lang.Throwable -> L91
            if (r1 == 0) goto L90
            r1.close()
        L90:
            return r0
        L91:
            r8 = move-exception
        L92:
            if (r1 == 0) goto L97
            r1.close()
        L97:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzac.zza(java.lang.String, java.lang.Long):android.util.Pair");
    }

    public final boolean zza(String str, Long l, long j, zzbs.zzc zzcVar) {
        zzd();
        zzak();
        Preconditions.checkNotNull(zzcVar);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        byte[] bArrZzbi = zzcVar.zzbi();
        zzr().zzx().zza("Saving complex main event, appId, data size", zzo().zza(str), Integer.valueOf(bArrZzbi.length));
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", str);
        contentValues.put("event_id", l);
        contentValues.put("children_to_process", Long.valueOf(j));
        contentValues.put("main_event", bArrZzbi);
        try {
            if (c_().insertWithOnConflict("main_event_params", null, contentValues, 5) != -1) {
                return true;
            }
            zzr().zzf().zza("Failed to insert complex main event (got -1). appId", zzfb.zza(str));
            return false;
        } catch (SQLiteException e2) {
            zzr().zzf().zza("Error storing complex main event. appId", zzfb.zza(str), e2);
            return false;
        }
    }

    public final boolean zza(zzak zzakVar, long j, boolean z) {
        zzd();
        zzak();
        Preconditions.checkNotNull(zzakVar);
        Preconditions.checkNotEmpty(zzakVar.zza);
        zzbs.zzc.zza zzaVarZzb = zzbs.zzc.zzj().zzb(zzakVar.zzd);
        for (String str : zzakVar.zze) {
            zzbs.zze.zza zzaVarZza = zzbs.zze.zzk().zza(str);
            zzg().zza(zzaVarZza, zzakVar.zze.zza(str));
            zzaVarZzb.zza(zzaVarZza);
        }
        byte[] bArrZzbi = ((zzbs.zzc) ((com.google.android.gms.internal.measurement.zzfe) zzaVarZzb.zzv())).zzbi();
        if (!com.google.android.gms.internal.measurement.zzkw.zzb() || !zzt().zze(zzakVar.zza, zzap.zzcx)) {
            zzr().zzx().zza("Saving event, name, data size", zzo().zza(zzakVar.zzb), Integer.valueOf(bArrZzbi.length));
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzakVar.zza);
        contentValues.put(AppMeasurementSdk.ConditionalUserProperty.NAME, zzakVar.zzb);
        contentValues.put("timestamp", Long.valueOf(zzakVar.zzc));
        contentValues.put("metadata_fingerprint", Long.valueOf(j));
        contentValues.put(AeUtil.ROOT_DATA_PATH_OLD_NAME, bArrZzbi);
        contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
        try {
            if (c_().insert("raw_events", null, contentValues) != -1) {
                return true;
            }
            zzr().zzf().zza("Failed to insert raw event (got -1). appId", zzfb.zza(zzakVar.zza));
            return false;
        } catch (SQLiteException e2) {
            zzr().zzf().zza("Error storing raw event. appId", zzfb.zza(zzakVar.zza), e2);
            return false;
        }
    }

    final void zzb(String str, List<zzbk.zza> list) {
        boolean z;
        boolean z2;
        Preconditions.checkNotNull(list);
        for (int i = 0; i < list.size(); i++) {
            zzbk.zza.C0040zza c0040zzaZzbl = list.get(i).zzbl();
            if (c0040zzaZzbl.zzb() != 0) {
                zzbk.zza.C0040zza c0040zzaZza = c0040zzaZzbl;
                for (int i2 = 0; i2 < c0040zzaZza.zzb(); i2++) {
                    zzbk.zzb.zza zzaVarZzbl = c0040zzaZza.zzb(i2).zzbl();
                    zzbk.zzb.zza zzaVar = (zzbk.zzb.zza) ((zzfe.zza) zzaVarZzbl.clone());
                    String strZzb = zzhe.zzb(zzaVarZzbl.zza());
                    if (strZzb != null) {
                        zzaVar.zza(strZzb);
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    boolean z3 = z2;
                    for (int i3 = 0; i3 < zzaVarZzbl.zzb(); i3++) {
                        zzbk.zzc zzcVarZza = zzaVarZzbl.zza(i3);
                        String strZza = zzhd.zza(zzcVarZza.zzh());
                        if (strZza != null) {
                            zzaVar.zza(i3, (zzbk.zzc) ((com.google.android.gms.internal.measurement.zzfe) zzcVarZza.zzbl().zza(strZza).zzv()));
                            z3 = true;
                        }
                    }
                    if (z3) {
                        c0040zzaZza = c0040zzaZza.zza(i2, zzaVar);
                        list.set(i, (zzbk.zza) ((com.google.android.gms.internal.measurement.zzfe) c0040zzaZza.zzv()));
                    }
                }
                c0040zzaZzbl = c0040zzaZza;
            }
            if (c0040zzaZzbl.zza() != 0) {
                for (int i4 = 0; i4 < c0040zzaZzbl.zza(); i4++) {
                    zzbk.zze zzeVarZza = c0040zzaZzbl.zza(i4);
                    String strZza2 = zzhg.zza(zzeVarZza.zzc());
                    if (strZza2 != null) {
                        c0040zzaZzbl = c0040zzaZzbl.zza(i4, zzeVarZza.zzbl().zza(strZza2));
                        list.set(i, (zzbk.zza) ((com.google.android.gms.internal.measurement.zzfe) c0040zzaZzbl.zzv()));
                    }
                }
            }
        }
        zzak();
        zzd();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(list);
        SQLiteDatabase sQLiteDatabaseC_ = c_();
        sQLiteDatabaseC_.beginTransaction();
        try {
            zzak();
            zzd();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase sQLiteDatabaseC_2 = c_();
            sQLiteDatabaseC_2.delete("property_filters", "app_id=?", new String[]{str});
            sQLiteDatabaseC_2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzbk.zza zzaVar2 : list) {
                zzak();
                zzd();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zzaVar2);
                if (!zzaVar2.zza()) {
                    zzr().zzi().zza("Audience with no ID. appId", zzfb.zza(str));
                } else {
                    int iZzb = zzaVar2.zzb();
                    Iterator<zzbk.zzb> it = zzaVar2.zze().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (!it.next().zza()) {
                                zzr().zzi().zza("Event filter with no ID. Audience definition ignored. appId, audienceId", zzfb.zza(str), Integer.valueOf(iZzb));
                                break;
                            }
                        } else {
                            Iterator<zzbk.zze> it2 = zzaVar2.zzc().iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    if (!it2.next().zza()) {
                                        zzr().zzi().zza("Property filter with no ID. Audience definition ignored. appId, audienceId", zzfb.zza(str), Integer.valueOf(iZzb));
                                        break;
                                    }
                                } else {
                                    Iterator<zzbk.zzb> it3 = zzaVar2.zze().iterator();
                                    while (true) {
                                        if (it3.hasNext()) {
                                            if (!zza(str, iZzb, it3.next())) {
                                                z = false;
                                                break;
                                            }
                                        } else {
                                            z = true;
                                            break;
                                        }
                                    }
                                    if (z) {
                                        Iterator<zzbk.zze> it4 = zzaVar2.zzc().iterator();
                                        while (true) {
                                            if (it4.hasNext()) {
                                                if (!zza(str, iZzb, it4.next())) {
                                                    z = false;
                                                    break;
                                                }
                                            } else {
                                                break;
                                            }
                                        }
                                    }
                                    if (!z) {
                                        zzak();
                                        zzd();
                                        Preconditions.checkNotEmpty(str);
                                        SQLiteDatabase sQLiteDatabaseC_3 = c_();
                                        sQLiteDatabaseC_3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(iZzb)});
                                        sQLiteDatabaseC_3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(iZzb)});
                                    }
                                }
                            }
                        }
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            for (zzbk.zza zzaVar3 : list) {
                arrayList.add(zzaVar3.zza() ? Integer.valueOf(zzaVar3.zzb()) : null);
            }
            zzc(str, arrayList);
            sQLiteDatabaseC_.setTransactionSuccessful();
        } finally {
            sQLiteDatabaseC_.endTransaction();
        }
    }

    private final boolean zzam() {
        return zzn().getDatabasePath("google_app_measurement.db").exists();
    }
}