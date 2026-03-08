package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.ido.ble.event.stat.one.d;

/* JADX INFO: loaded from: classes.dex */
public final class Field extends AbstractSafeParcelable {
    public static final int FORMAT_FLOAT = 2;
    public static final int FORMAT_INT32 = 1;
    public static final int FORMAT_MAP = 4;
    public static final int FORMAT_STRING = 3;
    public static final int MEAL_TYPE_BREAKFAST = 1;
    public static final int MEAL_TYPE_DINNER = 3;
    public static final int MEAL_TYPE_LUNCH = 2;
    public static final int MEAL_TYPE_SNACK = 4;
    public static final int MEAL_TYPE_UNKNOWN = 0;
    public static final String NUTRIENT_CALCIUM = "calcium";
    public static final String NUTRIENT_CHOLESTEROL = "cholesterol";
    public static final String NUTRIENT_DIETARY_FIBER = "dietary_fiber";
    public static final String NUTRIENT_IRON = "iron";
    public static final String NUTRIENT_MONOUNSATURATED_FAT = "fat.monounsaturated";
    public static final String NUTRIENT_POLYUNSATURATED_FAT = "fat.polyunsaturated";
    public static final String NUTRIENT_POTASSIUM = "potassium";
    public static final String NUTRIENT_PROTEIN = "protein";
    public static final String NUTRIENT_SATURATED_FAT = "fat.saturated";
    public static final String NUTRIENT_SODIUM = "sodium";
    public static final String NUTRIENT_SUGAR = "sugar";
    public static final String NUTRIENT_TOTAL_CARBS = "carbs.total";
    public static final String NUTRIENT_TOTAL_FAT = "fat.total";
    public static final String NUTRIENT_TRANS_FAT = "fat.trans";
    public static final String NUTRIENT_UNSATURATED_FAT = "fat.unsaturated";
    public static final String NUTRIENT_VITAMIN_A = "vitamin_a";
    public static final String NUTRIENT_VITAMIN_C = "vitamin_c";
    public static final int RESISTANCE_TYPE_BARBELL = 1;
    public static final int RESISTANCE_TYPE_BODY = 6;
    public static final int RESISTANCE_TYPE_CABLE = 2;
    public static final int RESISTANCE_TYPE_DUMBBELL = 3;
    public static final int RESISTANCE_TYPE_KETTLEBELL = 4;
    public static final int RESISTANCE_TYPE_MACHINE = 5;
    public static final int RESISTANCE_TYPE_UNKNOWN = 0;
    private final int format;
    private final String name;
    private final Boolean zzdd;
    public static final Field FIELD_ACTIVITY = zzd("activity");
    public static final Field FIELD_CONFIDENCE = zzf("confidence");
    public static final Field FIELD_ACTIVITY_CONFIDENCE = zzh("activity_confidence");
    public static final Field FIELD_STEPS = zzd("steps");
    public static final Field FIELD_STEP_LENGTH = zzf("step_length");
    public static final Field FIELD_DURATION = zzd(d.C);
    public static final Field zzci = zze(d.C);
    private static final Field zzcj = zzh("activity_duration");
    public static final Field zzck = zzh("activity_duration.ascending");
    public static final Field zzcl = zzh("activity_duration.descending");
    public static final Field FIELD_BPM = zzf("bpm");
    public static final Field FIELD_LATITUDE = zzf("latitude");
    public static final Field FIELD_LONGITUDE = zzf("longitude");
    public static final Field FIELD_ACCURACY = zzf("accuracy");
    public static final Field FIELD_ALTITUDE = new Field("altitude", 2, true);
    public static final Field FIELD_DISTANCE = zzf("distance");
    public static final Field FIELD_HEIGHT = zzf("height");
    public static final Field FIELD_WEIGHT = zzf("weight");
    public static final Field FIELD_CIRCUMFERENCE = zzf("circumference");
    public static final Field FIELD_PERCENTAGE = zzf("percentage");
    public static final Field FIELD_SPEED = zzf("speed");
    public static final Field FIELD_RPM = zzf("rpm");
    public static final Field zzcm = zzi("google.android.fitness.GoalV2");
    public static final Field zzcn = zzi("google.android.fitness.StrideModel");
    public static final Field FIELD_REVOLUTIONS = zzd("revolutions");
    public static final String NUTRIENT_CALORIES = "calories";
    public static final Field FIELD_CALORIES = zzf(NUTRIENT_CALORIES);
    public static final Field FIELD_WATTS = zzf("watts");
    public static final Field FIELD_VOLUME = zzf("volume");
    public static final Field FIELD_MEAL_TYPE = zzd("meal_type");
    public static final Field FIELD_FOOD_ITEM = zzg("food_item");
    public static final Field FIELD_NUTRIENTS = zzh("nutrients");
    public static final Field zzco = zzf("elevation.change");
    public static final Field zzcp = zzh("elevation.gain");
    public static final Field zzcq = zzh("elevation.loss");
    public static final Field zzcr = zzf("floors");
    public static final Field zzcs = zzh("floor.gain");
    public static final Field zzct = zzh("floor.loss");
    public static final Field FIELD_EXERCISE = zzg("exercise");
    public static final Field FIELD_REPETITIONS = zzd("repetitions");
    public static final Field FIELD_RESISTANCE = zzf("resistance");
    public static final Field FIELD_RESISTANCE_TYPE = zzd("resistance_type");
    public static final Field FIELD_NUM_SEGMENTS = zzd("num_segments");
    public static final Field FIELD_AVERAGE = zzf("average");
    public static final Field FIELD_MAX = zzf("max");
    public static final Field FIELD_MIN = zzf("min");
    public static final Field FIELD_LOW_LATITUDE = zzf("low_latitude");
    public static final Field FIELD_LOW_LONGITUDE = zzf("low_longitude");
    public static final Field FIELD_HIGH_LATITUDE = zzf("high_latitude");
    public static final Field FIELD_HIGH_LONGITUDE = zzf("high_longitude");
    public static final Field FIELD_OCCURRENCES = zzd("occurrences");
    public static final Field zzcu = zzd("sensor_type");
    public static final Field zzcv = zzd("sensor_types");
    public static final Field zzcw = new Field("timestamps", 5);
    public static final Field zzcx = zzd("sample_period");
    public static final Field zzcy = zzd("num_samples");
    public static final Field zzcz = zzd("num_dimensions");
    public static final Field zzda = new Field("sensor_values", 6);
    public static final Field zzdb = zzf("intensity");
    public static final Field zzdc = zzf("probability");
    public static final Parcelable.Creator<Field> CREATOR = new zzq();

    public static class zza {
        public static final Field zzde = Field.zzf("x");
        public static final Field zzdf = Field.zzf("y");
        public static final Field zzdg = Field.zzf("z");
        public static final Field zzdh = Field.zzj("debug_session");
        public static final Field zzdi = Field.zzj("google.android.fitness.SessionV2");
    }

    private Field(String str, int i) {
        this(str, i, null);
    }

    Field(String str, int i, Boolean bool) {
        this.name = (String) Preconditions.checkNotNull(str);
        this.format = i;
        this.zzdd = bool;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0484  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.gms.fitness.data.Field zza(java.lang.String r2, int r3) {
        /*
            Method dump skipped, instruction units count: 2046
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.data.Field.zza(java.lang.String, int):com.google.android.gms.fitness.data.Field");
    }

    private static Field zzd(String str) {
        return new Field(str, 1);
    }

    static Field zze(String str) {
        return new Field(str, 1, true);
    }

    static Field zzf(String str) {
        return new Field(str, 2);
    }

    private static Field zzg(String str) {
        return new Field(str, 3);
    }

    private static Field zzh(String str) {
        return new Field(str, 4);
    }

    private static Field zzi(String str) {
        return new Field(str, 7);
    }

    static Field zzj(String str) {
        return new Field(str, 7, true);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Field)) {
            return false;
        }
        Field field = (Field) obj;
        return this.name.equals(field.name) && this.format == field.format;
    }

    public final int getFormat() {
        return this.format;
    }

    public final String getName() {
        return this.name;
    }

    public final int hashCode() {
        return this.name.hashCode();
    }

    public final Boolean isOptional() {
        return this.zzdd;
    }

    public final String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = this.name;
        objArr[1] = this.format == 1 ? "i" : "f";
        return String.format("%s(%s)", objArr);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeInt(parcel, 2, getFormat());
        SafeParcelWriter.writeBooleanObject(parcel, 3, isOptional(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}