package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.collection.ArraySet;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class DataType extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final DataType AGGREGATE_ACTIVITY_SUMMARY;
    public static final DataType AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY;
    public static final DataType AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY;

    @Deprecated
    public static final DataType AGGREGATE_CALORIES_CONSUMED;
    public static final DataType AGGREGATE_CALORIES_EXPENDED;
    public static final DataType AGGREGATE_DISTANCE_DELTA;
    public static final DataType AGGREGATE_HEART_RATE_SUMMARY;
    public static final DataType AGGREGATE_HEIGHT_SUMMARY;
    public static final DataType AGGREGATE_HYDRATION;

    @Deprecated
    public static final Set<DataType> AGGREGATE_INPUT_TYPES;
    public static final DataType AGGREGATE_LOCATION_BOUNDING_BOX;
    public static final DataType AGGREGATE_NUTRITION_SUMMARY;
    public static final DataType AGGREGATE_POWER_SUMMARY;
    public static final DataType AGGREGATE_SPEED_SUMMARY;
    public static final DataType AGGREGATE_STEP_COUNT_DELTA;
    public static final DataType AGGREGATE_WEIGHT_SUMMARY;
    public static final Parcelable.Creator<DataType> CREATOR;
    public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.data_type/";
    public static final DataType zzbm;
    public static final DataType zzbn;
    public static final DataType zzbo;
    public static final DataType zzbp;
    public static final DataType zzbq;
    public static final DataType zzbr;
    public static final DataType zzbs;
    public static final DataType zzbt;
    private final String name;
    private final List<Field> zzbu;
    private final String zzbv;
    private final String zzbw;
    public static final DataType TYPE_STEP_COUNT_DELTA = new DataType("com.google.step_count.delta", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_STEPS);
    public static final DataType TYPE_STEP_COUNT_CUMULATIVE = new DataType("com.google.step_count.cumulative", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_STEPS);
    public static final DataType zzbc = new DataType("com.google.step_length", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_STEP_LENGTH);
    public static final DataType TYPE_STEP_COUNT_CADENCE = new DataType("com.google.step_count.cadence", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_RPM);
    public static final DataType zzbd = new DataType("com.google.internal.goal", Field.zzcm);
    public static final DataType zzbe = new DataType("com.google.stride_model", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zzcn);
    public static final DataType TYPE_ACTIVITY_SEGMENT = new DataType("com.google.activity.segment", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY);
    public static final DataType zzbf = new DataType("com.google.floor_change", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY, Field.FIELD_CONFIDENCE, Field.zzco, Field.zzcr);

    @Deprecated
    public static final DataType TYPE_CALORIES_CONSUMED = new DataType("com.google.calories.consumed", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_CALORIES);
    public static final DataType TYPE_CALORIES_EXPENDED = new DataType("com.google.calories.expended", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_CALORIES);
    public static final DataType TYPE_BASAL_METABOLIC_RATE = new DataType("com.google.calories.bmr", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_CALORIES);
    public static final DataType TYPE_POWER_SAMPLE = new DataType("com.google.power.sample", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_WATTS);

    @Deprecated
    public static final DataType TYPE_ACTIVITY_SAMPLE = new DataType("com.google.activity.sample", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY, Field.FIELD_CONFIDENCE);
    public static final DataType TYPE_ACTIVITY_SAMPLES = new DataType("com.google.activity.samples", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY_CONFIDENCE);
    public static final DataType zzbg = new DataType("com.google.accelerometer", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zza.zzde, Field.zza.zzdf, Field.zza.zzdg);
    public static final DataType zzbh = new DataType("com.google.sensor.events", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zzcu, Field.zzcw, Field.zzda);
    public static final DataType zzbi = new DataType("com.google.sensor.const_rate_events", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zzcv, Field.zzcx, Field.zzcy, Field.zzcz, Field.zzda);
    public static final DataType TYPE_HEART_RATE_BPM = new DataType("com.google.heart_rate.bpm", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ, Field.FIELD_BPM);
    public static final DataType TYPE_LOCATION_SAMPLE = new DataType("com.google.location.sample", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_ACCURACY, Field.FIELD_ALTITUDE);
    public static final DataType TYPE_LOCATION_TRACK = new DataType("com.google.location.track", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_LATITUDE, Field.FIELD_LONGITUDE, Field.FIELD_ACCURACY, Field.FIELD_ALTITUDE);
    public static final DataType zzbj = new DataType("com.google.moves", Field.zzci);
    public static final DataType TYPE_DISTANCE_DELTA = new DataType("com.google.distance.delta", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_DISTANCE);
    public static final DataType TYPE_DISTANCE_CUMULATIVE = new DataType("com.google.distance.cumulative", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_DISTANCE);
    public static final DataType TYPE_SPEED = new DataType("com.google.speed", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_SPEED);
    public static final DataType TYPE_CYCLING_WHEEL_REVOLUTION = new DataType("com.google.cycling.wheel_revolution.cumulative", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_REVOLUTIONS);
    public static final DataType TYPE_CYCLING_WHEEL_RPM = new DataType("com.google.cycling.wheel_revolution.rpm", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_RPM);
    public static final DataType TYPE_CYCLING_PEDALING_CUMULATIVE = new DataType("com.google.cycling.pedaling.cumulative", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_REVOLUTIONS);
    public static final DataType TYPE_CYCLING_PEDALING_CADENCE = new DataType("com.google.cycling.pedaling.cadence", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_RPM);
    public static final DataType TYPE_HEIGHT = new DataType("com.google.height", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_HEIGHT);
    public static final DataType TYPE_WEIGHT = new DataType("com.google.weight", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_WEIGHT);
    public static final DataType TYPE_BODY_FAT_PERCENTAGE = new DataType("com.google.body.fat.percentage", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_PERCENTAGE);
    public static final DataType zzbk = new DataType("com.google.body.waist.circumference", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_CIRCUMFERENCE);
    public static final DataType zzbl = new DataType("com.google.body.hip.circumference", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_CIRCUMFERENCE);
    public static final DataType TYPE_NUTRITION = new DataType("com.google.nutrition", Scopes.FITNESS_NUTRITION_READ, Scopes.FITNESS_NUTRITION_READ_WRITE, Field.FIELD_NUTRIENTS, Field.FIELD_MEAL_TYPE, Field.FIELD_FOOD_ITEM);
    public static final DataType TYPE_HYDRATION = new DataType("com.google.hydration", Scopes.FITNESS_NUTRITION_READ, Scopes.FITNESS_NUTRITION_READ_WRITE, Field.FIELD_VOLUME);
    public static final DataType TYPE_WORKOUT_EXERCISE = new DataType("com.google.activity.exercise", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_EXERCISE, Field.FIELD_REPETITIONS, Field.FIELD_DURATION, Field.FIELD_RESISTANCE_TYPE, Field.FIELD_RESISTANCE);

    public static final class zza {
        public static final DataType zzbx = new DataType("com.google.internal.session.debug", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zza.zzdh);
        public static final DataType zzby = new DataType("com.google.internal.session.v2", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zza.zzdi);
    }

    static {
        DataType dataType = new DataType("com.google.active_minutes", Field.zzci);
        zzbm = dataType;
        zzbn = dataType;
        zzbo = new DataType("com.google.device_on_body", Field.zzdc);
        AGGREGATE_ACTIVITY_SUMMARY = new DataType("com.google.activity.summary", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_ACTIVITY, Field.FIELD_DURATION, Field.FIELD_NUM_SEGMENTS);
        zzbp = new DataType("com.google.floor_change.summary", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.zzck, Field.zzcl, Field.zzcp, Field.zzcq, Field.zzcs, Field.zzct);
        AGGREGATE_BASAL_METABOLIC_RATE_SUMMARY = new DataType("com.google.calories.bmr.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_STEP_COUNT_DELTA = TYPE_STEP_COUNT_DELTA;
        AGGREGATE_DISTANCE_DELTA = TYPE_DISTANCE_DELTA;
        AGGREGATE_CALORIES_CONSUMED = TYPE_CALORIES_CONSUMED;
        AGGREGATE_CALORIES_EXPENDED = TYPE_CALORIES_EXPENDED;
        DataType dataType2 = new DataType("com.google.heart_minutes", Field.zzdb);
        zzbq = dataType2;
        zzbr = dataType2;
        AGGREGATE_HEART_RATE_SUMMARY = new DataType("com.google.heart_rate.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_LOCATION_BOUNDING_BOX = new DataType("com.google.location.bounding_box", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_LOW_LATITUDE, Field.FIELD_LOW_LONGITUDE, Field.FIELD_HIGH_LATITUDE, Field.FIELD_HIGH_LONGITUDE);
        AGGREGATE_POWER_SUMMARY = new DataType("com.google.power.summary", Scopes.FITNESS_ACTIVITY_READ, Scopes.FITNESS_ACTIVITY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_SPEED_SUMMARY = new DataType("com.google.speed.summary", Scopes.FITNESS_LOCATION_READ, Scopes.FITNESS_LOCATION_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_BODY_FAT_PERCENTAGE_SUMMARY = new DataType("com.google.body.fat.percentage.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        zzbs = new DataType("com.google.body.hip.circumference.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        zzbt = new DataType("com.google.body.waist.circumference.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_WEIGHT_SUMMARY = new DataType("com.google.weight.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_HEIGHT_SUMMARY = new DataType("com.google.height.summary", Scopes.FITNESS_BODY_READ, Scopes.FITNESS_BODY_READ_WRITE, Field.FIELD_AVERAGE, Field.FIELD_MAX, Field.FIELD_MIN);
        AGGREGATE_NUTRITION_SUMMARY = new DataType("com.google.nutrition.summary", Scopes.FITNESS_NUTRITION_READ, Scopes.FITNESS_NUTRITION_READ_WRITE, Field.FIELD_NUTRIENTS, Field.FIELD_MEAL_TYPE);
        AGGREGATE_HYDRATION = TYPE_HYDRATION;
        ArraySet arraySet = new ArraySet();
        AGGREGATE_INPUT_TYPES = arraySet;
        arraySet.add(TYPE_ACTIVITY_SEGMENT);
        AGGREGATE_INPUT_TYPES.add(TYPE_BASAL_METABOLIC_RATE);
        AGGREGATE_INPUT_TYPES.add(TYPE_BODY_FAT_PERCENTAGE);
        AGGREGATE_INPUT_TYPES.add(zzbl);
        AGGREGATE_INPUT_TYPES.add(zzbk);
        AGGREGATE_INPUT_TYPES.add(TYPE_CALORIES_CONSUMED);
        AGGREGATE_INPUT_TYPES.add(TYPE_CALORIES_EXPENDED);
        AGGREGATE_INPUT_TYPES.add(TYPE_DISTANCE_DELTA);
        AGGREGATE_INPUT_TYPES.add(zzbf);
        AGGREGATE_INPUT_TYPES.add(TYPE_LOCATION_SAMPLE);
        AGGREGATE_INPUT_TYPES.add(TYPE_NUTRITION);
        AGGREGATE_INPUT_TYPES.add(TYPE_HYDRATION);
        AGGREGATE_INPUT_TYPES.add(TYPE_HEART_RATE_BPM);
        AGGREGATE_INPUT_TYPES.add(TYPE_POWER_SAMPLE);
        AGGREGATE_INPUT_TYPES.add(TYPE_SPEED);
        AGGREGATE_INPUT_TYPES.add(TYPE_STEP_COUNT_DELTA);
        AGGREGATE_INPUT_TYPES.add(TYPE_WEIGHT);
        CREATOR = new zzl();
    }

    public DataType(String str, String str2, String str3, Field... fieldArr) {
        this(str, (List<Field>) Arrays.asList(fieldArr), str2, str3);
    }

    DataType(String str, List<Field> list, String str2, String str3) {
        this.name = str;
        this.zzbu = Collections.unmodifiableList(list);
        this.zzbv = str2;
        this.zzbw = str3;
    }

    private DataType(String str, Field... fieldArr) {
        this(str, (List<Field>) Arrays.asList(fieldArr), (String) null, (String) null);
    }

    public static List<DataType> getAggregatesForInput(DataType dataType) {
        List<DataType> list = com.google.android.gms.fitness.data.zza.zzac.get(dataType);
        return list == null ? Collections.emptyList() : Collections.unmodifiableList(list);
    }

    public static String getMimeType(DataType dataType) {
        String strValueOf = String.valueOf(dataType.getName());
        return strValueOf.length() != 0 ? MIME_TYPE_PREFIX.concat(strValueOf) : new String(MIME_TYPE_PREFIX);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataType)) {
            return false;
        }
        DataType dataType = (DataType) obj;
        return this.name.equals(dataType.name) && this.zzbu.equals(dataType.zzbu);
    }

    public final List<Field> getFields() {
        return this.zzbu;
    }

    public final String getName() {
        return this.name;
    }

    public final int hashCode() {
        return this.name.hashCode();
    }

    public final int indexOf(Field field) {
        int iIndexOf = this.zzbu.indexOf(field);
        Preconditions.checkArgument(iIndexOf >= 0, "%s not a field of %s", field, this);
        return iIndexOf;
    }

    public final String toString() {
        return String.format("DataType{%s%s}", this.name, this.zzbu);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getName(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, getFields(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzbv, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzbw, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final String zzk() {
        return this.zzbv;
    }

    public final String zzl() {
        return this.zzbw;
    }

    public final String zzm() {
        return this.name.startsWith("com.google.") ? this.name.substring(11) : this.name;
    }
}