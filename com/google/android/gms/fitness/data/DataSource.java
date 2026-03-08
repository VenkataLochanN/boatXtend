package com.google.android.gms.fitness.data;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;

/* JADX INFO: loaded from: classes.dex */
public class DataSource extends AbstractSafeParcelable {
    public static final int DATA_QUALITY_BLOOD_GLUCOSE_ISO151972003 = 8;
    public static final int DATA_QUALITY_BLOOD_GLUCOSE_ISO151972013 = 9;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_AAMI = 3;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_BHS_A_A = 4;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_BHS_A_B = 5;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_BHS_B_A = 6;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_BHS_B_B = 7;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_ESH2002 = 1;
    public static final int DATA_QUALITY_BLOOD_PRESSURE_ESH2010 = 2;
    public static final String EXTRA_DATA_SOURCE = "vnd.google.fitness.data_source";
    public static final int TYPE_DERIVED = 1;
    public static final int TYPE_RAW = 0;
    private final String name;
    private final int type;
    private final Device zzax;
    private final zzb zzay;
    private final String zzaz;
    private final int[] zzba;
    private final String zzbb;
    private final DataType zzq;
    private static final int[] zzaw = new int[0];
    public static final Parcelable.Creator<DataSource> CREATOR = new zzk();

    public static final class Builder {
        private String name;
        private Device zzax;
        private zzb zzay;
        private int[] zzba;
        private DataType zzq;
        private int type = -1;
        private String zzaz = "";

        public final DataSource build() {
            Preconditions.checkState(this.zzq != null, "Must set data type");
            Preconditions.checkState(this.type >= 0, "Must set data source type");
            return new DataSource(this);
        }

        public final Builder setAppPackageName(Context context) {
            return setAppPackageName(context.getPackageName());
        }

        public final Builder setAppPackageName(String str) {
            this.zzay = zzb.zza(str);
            return this;
        }

        public final Builder setDataQualityStandards(int... iArr) {
            this.zzba = iArr;
            return this;
        }

        public final Builder setDataType(DataType dataType) {
            this.zzq = dataType;
            return this;
        }

        public final Builder setDevice(Device device) {
            this.zzax = device;
            return this;
        }

        public final Builder setName(String str) {
            this.name = str;
            return this;
        }

        public final Builder setStreamName(String str) {
            Preconditions.checkArgument(str != null, "Must specify a valid stream name");
            this.zzaz = str;
            return this;
        }

        public final Builder setType(int i) {
            this.type = i;
            return this;
        }
    }

    private DataSource(Builder builder) {
        this.zzq = builder.zzq;
        this.type = builder.type;
        this.name = builder.name;
        this.zzax = builder.zzax;
        this.zzay = builder.zzay;
        this.zzaz = builder.zzaz;
        this.zzbb = zzj();
        this.zzba = builder.zzba;
    }

    public DataSource(DataType dataType, String str, int i, Device device, zzb zzbVar, String str2, int[] iArr) {
        this.zzq = dataType;
        this.type = i;
        this.name = str;
        this.zzax = device;
        this.zzay = zzbVar;
        this.zzaz = str2;
        this.zzbb = zzj();
        this.zzba = iArr == null ? zzaw : iArr;
    }

    public static DataSource extract(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (DataSource) SafeParcelableSerializer.deserializeFromIntentExtra(intent, EXTRA_DATA_SOURCE, CREATOR);
    }

    private final String getTypeString() {
        int i = this.type;
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "derived" : "converted" : "cleaned" : "derived" : "raw";
    }

    public static String zzd(int i) {
        switch (i) {
            case 1:
                return "blood_pressure_esh2002";
            case 2:
                return "blood_pressure_esh2010";
            case 3:
                return "blood_pressure_aami";
            case 4:
                return "blood_pressure_bhs_a_a";
            case 5:
                return "blood_pressure_bhs_a_b";
            case 6:
                return "blood_pressure_bhs_b_a";
            case 7:
                return "blood_pressure_bhs_b_b";
            case 8:
                return "blood_glucose_iso151972003";
            case 9:
                return "blood_glucose_iso151972013";
            default:
                return "unknown";
        }
    }

    private final String zzj() {
        StringBuilder sb = new StringBuilder();
        sb.append(getTypeString());
        sb.append(":");
        sb.append(this.zzq.getName());
        if (this.zzay != null) {
            sb.append(":");
            sb.append(this.zzay.getPackageName());
        }
        if (this.zzax != null) {
            sb.append(":");
            sb.append(this.zzax.getStreamIdentifier());
        }
        if (this.zzaz != null) {
            sb.append(":");
            sb.append(this.zzaz);
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof DataSource) {
            return this.zzbb.equals(((DataSource) obj).zzbb);
        }
        return false;
    }

    public String getAppPackageName() {
        zzb zzbVar = this.zzay;
        if (zzbVar == null) {
            return null;
        }
        return zzbVar.getPackageName();
    }

    public int[] getDataQualityStandards() {
        return this.zzba;
    }

    public DataType getDataType() {
        return this.zzq;
    }

    public Device getDevice() {
        return this.zzax;
    }

    public String getName() {
        return this.name;
    }

    public String getStreamIdentifier() {
        return this.zzbb;
    }

    public String getStreamName() {
        return this.zzaz;
    }

    public int getType() {
        return this.type;
    }

    public int hashCode() {
        return this.zzbb.hashCode();
    }

    public final String toDebugString() {
        String strConcat;
        String string;
        int i = this.type;
        String str = i != 0 ? i != 1 ? i != 2 ? i != 3 ? "?" : "v" : "c" : "d" : "r";
        String strZzm = this.zzq.zzm();
        zzb zzbVar = this.zzay;
        String strConcat2 = "";
        if (zzbVar == null) {
            strConcat = "";
        } else if (zzbVar.equals(zzb.zzad)) {
            strConcat = ":gms";
        } else {
            String strValueOf = String.valueOf(this.zzay.getPackageName());
            strConcat = strValueOf.length() != 0 ? ":".concat(strValueOf) : new String(":");
        }
        Device device = this.zzax;
        if (device != null) {
            String model = device.getModel();
            String uid = this.zzax.getUid();
            StringBuilder sb = new StringBuilder(String.valueOf(model).length() + 2 + String.valueOf(uid).length());
            sb.append(":");
            sb.append(model);
            sb.append(":");
            sb.append(uid);
            string = sb.toString();
        } else {
            string = "";
        }
        String str2 = this.zzaz;
        if (str2 != null) {
            String strValueOf2 = String.valueOf(str2);
            strConcat2 = strValueOf2.length() != 0 ? ":".concat(strValueOf2) : new String(":");
        }
        StringBuilder sb2 = new StringBuilder(str.length() + 1 + String.valueOf(strZzm).length() + String.valueOf(strConcat).length() + String.valueOf(string).length() + String.valueOf(strConcat2).length());
        sb2.append(str);
        sb2.append(":");
        sb2.append(strZzm);
        sb2.append(strConcat);
        sb2.append(string);
        sb2.append(strConcat2);
        return sb2.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DataSource{");
        sb.append(getTypeString());
        if (this.name != null) {
            sb.append(":");
            sb.append(this.name);
        }
        if (this.zzay != null) {
            sb.append(":");
            sb.append(this.zzay);
        }
        if (this.zzax != null) {
            sb.append(":");
            sb.append(this.zzax);
        }
        if (this.zzaz != null) {
            sb.append(":");
            sb.append(this.zzaz);
        }
        sb.append(":");
        sb.append(this.zzq);
        sb.append("}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataType(), i, false);
        SafeParcelWriter.writeString(parcel, 2, getName(), false);
        SafeParcelWriter.writeInt(parcel, 3, getType());
        SafeParcelWriter.writeParcelable(parcel, 4, getDevice(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzay, i, false);
        SafeParcelWriter.writeString(parcel, 6, getStreamName(), false);
        SafeParcelWriter.writeIntArray(parcel, 8, getDataQualityStandards(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final zzb zzi() {
        return this.zzay;
    }
}