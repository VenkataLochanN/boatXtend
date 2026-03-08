package com.google.android.gms.fitness.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.HexDumpUtils;
import com.google.android.gms.internal.fitness.zzfa;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public final class Value extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Value> CREATOR = new zzai();
    private final int format;
    private float value;
    private boolean zzee;
    private String zzef;
    private Map<String, MapValue> zzeg;
    private int[] zzeh;
    private float[] zzei;
    private byte[] zzej;

    public Value(int i) {
        this(i, false, 0.0f, null, null, null, null, null);
    }

    Value(int i, boolean z, float f2, String str, Bundle bundle, int[] iArr, float[] fArr, byte[] bArr) {
        ArrayMap arrayMap;
        this.format = i;
        this.zzee = z;
        this.value = f2;
        this.zzef = str;
        if (bundle == null) {
            arrayMap = null;
        } else {
            bundle.setClassLoader(MapValue.class.getClassLoader());
            arrayMap = new ArrayMap(bundle.size());
            for (String str2 : bundle.keySet()) {
                arrayMap.put(str2, (MapValue) bundle.getParcelable(str2));
            }
        }
        this.zzeg = arrayMap;
        this.zzeh = iArr;
        this.zzei = fArr;
        this.zzej = bArr;
    }

    public final String asActivity() {
        return zzfa.getName(asInt());
    }

    public final float asFloat() {
        Preconditions.checkState(this.format == 2, "Value is not in float format");
        return this.value;
    }

    public final int asInt() {
        Preconditions.checkState(this.format == 1, "Value is not in int format");
        return Float.floatToRawIntBits(this.value);
    }

    public final String asString() {
        Preconditions.checkState(this.format == 3, "Value is not in string format");
        return this.zzef;
    }

    public final void clearKey(String str) {
        Preconditions.checkState(this.format == 4, "Attempting to set a key's value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
        Map<String, MapValue> map = this.zzeg;
        if (map != null) {
            map.remove(str);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Value)) {
            return false;
        }
        Value value = (Value) obj;
        int i = this.format;
        if (i == value.format && this.zzee == value.zzee) {
            switch (i) {
                case 1:
                    if (asInt() == value.asInt()) {
                        return true;
                    }
                    break;
                case 2:
                    return this.value == value.value;
                case 3:
                    return Objects.equal(this.zzef, value.zzef);
                case 4:
                    return Objects.equal(this.zzeg, value.zzeg);
                case 5:
                    return Arrays.equals(this.zzeh, value.zzeh);
                case 6:
                    return Arrays.equals(this.zzei, value.zzei);
                case 7:
                    return Arrays.equals(this.zzej, value.zzej);
                default:
                    if (this.value == value.value) {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    public final int getFormat() {
        return this.format;
    }

    public final Float getKeyValue(String str) {
        Preconditions.checkState(this.format == 4, "Value is not in float map format");
        Map<String, MapValue> map = this.zzeg;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return Float.valueOf(this.zzeg.get(str).asFloat());
    }

    public final int hashCode() {
        return Objects.hashCode(Float.valueOf(this.value), this.zzef, this.zzeg, this.zzeh, this.zzei, this.zzej);
    }

    public final boolean isSet() {
        return this.zzee;
    }

    public final void setActivity(String str) {
        setInt(zzfa.zzl(str));
    }

    public final void setFloat(float f2) {
        Preconditions.checkState(this.format == 2, "Attempting to set an float value to a field that is not in FLOAT format.  Please check the data type definition and use the right format.");
        this.zzee = true;
        this.value = f2;
    }

    public final void setInt(int i) {
        Preconditions.checkState(this.format == 1, "Attempting to set an int value to a field that is not in INT32 format.  Please check the data type definition and use the right format.");
        this.zzee = true;
        this.value = Float.intBitsToFloat(i);
    }

    public final void setKeyValue(String str, float f2) {
        Preconditions.checkState(this.format == 4, "Attempting to set a key's value to a field that is not in FLOAT_MAP format.  Please check the data type definition and use the right format.");
        this.zzee = true;
        if (this.zzeg == null) {
            this.zzeg = new HashMap();
        }
        this.zzeg.put(str, new MapValue(2, f2));
    }

    public final void setString(String str) {
        Preconditions.checkState(this.format == 3, "Attempting to set a string value to a field that is not in STRING format.  Please check the data type definition and use the right format.");
        this.zzee = true;
        this.zzef = str;
    }

    public final String toString() {
        if (!this.zzee) {
            return "unset";
        }
        switch (this.format) {
            case 1:
                return Integer.toString(asInt());
            case 2:
                return Float.toString(this.value);
            case 3:
                return this.zzef;
            case 4:
                return new TreeMap(this.zzeg).toString();
            case 5:
                return Arrays.toString(this.zzeh);
            case 6:
                return Arrays.toString(this.zzei);
            case 7:
                byte[] bArr = this.zzej;
                return HexDumpUtils.dump(bArr, 0, bArr.length, false);
            default:
                return "unknown";
        }
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        Bundle bundle;
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getFormat());
        SafeParcelWriter.writeBoolean(parcel, 2, isSet());
        SafeParcelWriter.writeFloat(parcel, 3, this.value);
        SafeParcelWriter.writeString(parcel, 4, this.zzef, false);
        Map<String, MapValue> map = this.zzeg;
        if (map == null) {
            bundle = null;
        } else {
            Bundle bundle2 = new Bundle(map.size());
            for (Map.Entry<String, MapValue> entry : this.zzeg.entrySet()) {
                bundle2.putParcelable(entry.getKey(), entry.getValue());
            }
            bundle = bundle2;
        }
        SafeParcelWriter.writeBundle(parcel, 5, bundle, false);
        SafeParcelWriter.writeIntArray(parcel, 6, this.zzeh, false);
        SafeParcelWriter.writeFloatArray(parcel, 7, this.zzei, false);
        SafeParcelWriter.writeByteArray(parcel, 8, this.zzej, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}