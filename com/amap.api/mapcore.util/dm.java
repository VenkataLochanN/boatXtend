package com.amap.api.mapcore.util;

import android.text.TextUtils;
import com.google.android.gms.fitness.FitnessActivities;
import com.ido.life.module.user.country.CountryChooseActivity;
import com.ido.life.net.BaseUrl;

/* JADX INFO: compiled from: StyleItemAdaptor.java */
/* JADX INFO: loaded from: classes.dex */
public class dm {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[][] f631a = {new int[]{0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}, new int[]{12}, new int[]{1}, new int[]{13}, new int[]{14}, new int[]{15, 16}, new int[]{17, 18, 19, 20, 21, 26, 27, 28}, new int[]{22, 23}, new int[]{24, 25}, new int[]{39, 40, 41}, new int[]{29, 30, 31}, new int[]{32, 33, 34, 35, 36, 37, 38}};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f632b = {"land", "water", "green", "building", "highway", "arterial", "local", "railway", "subway", "boundary", "poilabel", "districtlable"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String[][] f633c = {new String[]{"land", "edu", "public", "traffic", "scenicSpot", "culture", BaseUrl.URL_NAME_HEALTH, "sports", "business", "parkingLot", "subway"}, new String[]{"water"}, new String[]{"green"}, new String[]{"buildings"}, new String[]{"highWay"}, new String[]{"ringRoad", "nationalRoad"}, new String[]{"provincialRoad", "secondaryRoad", "levelThreeRoad", "levelFourRoad", "roadsBeingBuilt", "overPass", "underPass", FitnessActivities.OTHER}, new String[]{"railway", "highSpeedRailway"}, new String[]{"subwayline", "subwayBeingBuilt"}, new String[]{"China", "foreign", "provincial"}, new String[]{"guideBoards", "pois", "aois"}, new String[]{"continent", CountryChooseActivity.COUNTRY, "province", "city", "district", "town", "village"}};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String[] f634d = {"regions", "water", "regions", "buildings", "roads", "roads", "roads", "roads", "roads", "borders", "labels", "labels"};

    public static String[] a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int i = -1;
        int i2 = 0;
        while (true) {
            String[] strArr = f632b;
            if (i2 >= strArr.length) {
                break;
            }
            if (strArr[i2].equals(str)) {
                i = i2;
                break;
            }
            i2++;
        }
        if (i >= 0) {
            return f633c[i];
        }
        return null;
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int i = -1;
        int i2 = 0;
        while (true) {
            String[] strArr = f632b;
            if (i2 >= strArr.length) {
                break;
            }
            if (strArr[i2].equals(str)) {
                i = i2;
                break;
            }
            i2++;
        }
        if (i >= 0) {
            return f634d[i];
        }
        return null;
    }
}