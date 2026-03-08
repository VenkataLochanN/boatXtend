package com.google.android.gms.internal.fitness;

import com.google.android.gms.fitness.data.Field;
import com.ido.ble.event.stat.one.d;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class zzk {
    private static final double zzel = 10.0d / TimeUnit.SECONDS.toNanos(1);
    private static final double zzem = 1000.0d / TimeUnit.SECONDS.toNanos(1);
    private static final double zzen = 2000.0d / TimeUnit.HOURS.toNanos(1);
    private static final double zzeo = 100.0d / TimeUnit.SECONDS.toNanos(1);
    public static final Set<String> zzep = Collections.unmodifiableSet(new HashSet(Arrays.asList("altitude", d.C, "food_item", "meal_type", "repetitions", "resistance", "resistance_type", "debug_session", "google.android.fitness.SessionV2")));
    private static final zzk zzes = new zzk();
    private final Map<String, Map<String, zzm>> zzeq;
    private final Map<String, zzm> zzer;

    private zzk() {
        HashMap map = new HashMap();
        map.put("latitude", new zzm(-90.0d, 90.0d));
        map.put("longitude", new zzm(-180.0d, 180.0d));
        map.put("accuracy", new zzm(0.0d, 10000.0d));
        map.put("bpm", new zzm(0.0d, 1000.0d));
        map.put("altitude", new zzm(-100000.0d, 100000.0d));
        map.put("percentage", new zzm(0.0d, 100.0d));
        map.put("confidence", new zzm(0.0d, 100.0d));
        map.put(d.C, new zzm(0.0d, 9.223372036854776E18d));
        map.put("height", new zzm(0.0d, 3.0d));
        map.put("weight", new zzm(0.0d, 1000.0d));
        map.put("speed", new zzm(0.0d, 11000.0d));
        this.zzer = Collections.unmodifiableMap(map);
        HashMap map2 = new HashMap();
        map2.put("com.google.step_count.delta", zza("steps", new zzm(0.0d, zzel)));
        map2.put("com.google.calories.consumed", zza(Field.NUTRIENT_CALORIES, new zzm(0.0d, zzem)));
        map2.put("com.google.calories.expended", zza(Field.NUTRIENT_CALORIES, new zzm(0.0d, zzen)));
        map2.put("com.google.distance.delta", zza("distance", new zzm(0.0d, zzeo)));
        this.zzeq = Collections.unmodifiableMap(map2);
    }

    private static <K, V> Map<K, V> zza(K k, V v) {
        HashMap map = new HashMap();
        map.put(k, v);
        return map;
    }

    public static zzk zzs() {
        return zzes;
    }

    final zzm zza(String str, String str2) {
        Map<String, zzm> map = this.zzeq.get(str);
        if (map != null) {
            return map.get(str2);
        }
        return null;
    }

    final zzm zzk(String str) {
        return this.zzer.get(str);
    }
}