package com.google.android.gms.auth.api.accounttransfer;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.ArraySet;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.internal.auth.zzbs;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class zzw extends zzbs {
    public static final Parcelable.Creator<zzw> CREATOR = new zzx();
    private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzbk;
    private final Set<Integer> zzbl;
    private String zzbx;
    private int zzby;
    private byte[] zzbz;
    private PendingIntent zzca;
    private DeviceMetaData zzcb;
    private final int zzy;

    static {
        HashMap<String, FastJsonResponse.Field<?, ?>> map = new HashMap<>();
        zzbk = map;
        map.put("accountType", FastJsonResponse.Field.forString("accountType", 2));
        zzbk.put(NotificationCompat.CATEGORY_STATUS, FastJsonResponse.Field.forInteger(NotificationCompat.CATEGORY_STATUS, 3));
        zzbk.put("transferBytes", (FastJsonResponse.Field<?, ?>) FastJsonResponse.Field.forBase64("transferBytes", 4));
    }

    public zzw() {
        this.zzbl = new ArraySet(3);
        this.zzy = 1;
    }

    zzw(Set<Integer> set, int i, String str, int i2, byte[] bArr, PendingIntent pendingIntent, DeviceMetaData deviceMetaData) {
        this.zzbl = set;
        this.zzy = i;
        this.zzbx = str;
        this.zzby = i2;
        this.zzbz = bArr;
        this.zzca = pendingIntent;
        this.zzcb = deviceMetaData;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    public /* synthetic */ Map getFieldMappings() {
        return zzbk;
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected Object getFieldValue(FastJsonResponse.Field field) {
        int i;
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 1) {
            i = this.zzy;
        } else {
            if (safeParcelableFieldId == 2) {
                return this.zzbx;
            }
            if (safeParcelableFieldId != 3) {
                if (safeParcelableFieldId == 4) {
                    return this.zzbz;
                }
                int safeParcelableFieldId2 = field.getSafeParcelableFieldId();
                StringBuilder sb = new StringBuilder(37);
                sb.append("Unknown SafeParcelable id=");
                sb.append(safeParcelableFieldId2);
                throw new IllegalStateException(sb.toString());
            }
            i = this.zzby;
        }
        return Integer.valueOf(i);
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected boolean isFieldSet(FastJsonResponse.Field field) {
        return this.zzbl.contains(Integer.valueOf(field.getSafeParcelableFieldId()));
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected void setDecodedBytesInternal(FastJsonResponse.Field<?, ?> field, String str, byte[] bArr) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 4) {
            this.zzbz = bArr;
            this.zzbl.add(Integer.valueOf(safeParcelableFieldId));
        } else {
            StringBuilder sb = new StringBuilder(59);
            sb.append("Field with id=");
            sb.append(safeParcelableFieldId);
            sb.append(" is not known to be an byte array.");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected void setIntegerInternal(FastJsonResponse.Field<?, ?> field, String str, int i) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId == 3) {
            this.zzby = i;
            this.zzbl.add(Integer.valueOf(safeParcelableFieldId));
        } else {
            StringBuilder sb = new StringBuilder(52);
            sb.append("Field with id=");
            sb.append(safeParcelableFieldId);
            sb.append(" is not known to be an int.");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    @Override // com.google.android.gms.common.server.response.FastJsonResponse
    protected void setStringInternal(FastJsonResponse.Field<?, ?> field, String str, String str2) {
        int safeParcelableFieldId = field.getSafeParcelableFieldId();
        if (safeParcelableFieldId != 2) {
            throw new IllegalArgumentException(String.format("Field with id=%d is not known to be a string.", Integer.valueOf(safeParcelableFieldId)));
        }
        this.zzbx = str2;
        this.zzbl.add(Integer.valueOf(safeParcelableFieldId));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        Set<Integer> set = this.zzbl;
        if (set.contains(1)) {
            SafeParcelWriter.writeInt(parcel, 1, this.zzy);
        }
        if (set.contains(2)) {
            SafeParcelWriter.writeString(parcel, 2, this.zzbx, true);
        }
        if (set.contains(3)) {
            SafeParcelWriter.writeInt(parcel, 3, this.zzby);
        }
        if (set.contains(4)) {
            SafeParcelWriter.writeByteArray(parcel, 4, this.zzbz, true);
        }
        if (set.contains(5)) {
            SafeParcelWriter.writeParcelable(parcel, 5, this.zzca, i, true);
        }
        if (set.contains(6)) {
            SafeParcelWriter.writeParcelable(parcel, 6, this.zzcb, i, true);
        }
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}