package com.baidu.location.d;

import android.database.Cursor;
import android.database.MatrixCursor;
import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.baidu.mapsdkplatform.comapi.location.CoordinateType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
final class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String[] f2349a = {"CoorType", "Time", "LocType", "Longitude", "Latitude", "Radius", "NetworkLocationType", "Country", "CountryCode", "Province", "City", "CityCode", "District", "Street", "StreetNumber", "PoiList", "LocationDescription"};

    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f2350a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final String f2351b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final boolean f2352c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final boolean f2353d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final boolean f2354e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final int f2355f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final BDLocation f2356g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        final boolean f2357h;
        final LinkedHashMap<String, Integer> i;

        public a(String[] strArr) {
            String str;
            boolean z;
            String str2;
            BDLocation bDLocation;
            String str3;
            String[] strArr2;
            if (strArr == null) {
                this.f2350a = null;
                this.f2351b = null;
                this.i = null;
                this.f2352c = false;
                this.f2353d = false;
                this.f2354e = false;
                this.f2356g = null;
                this.f2357h = false;
                this.f2355f = 8;
                return;
            }
            LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
            int iIntValue = 8;
            String str4 = null;
            String str5 = null;
            BDLocation bDLocation2 = null;
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = false;
            boolean z5 = false;
            for (int i = 0; i < strArr.length; i += 2) {
                try {
                    if (strArr[i].equals("-loc")) {
                        str4 = strArr[i + 1];
                        try {
                            String[] strArrSplit = str4.split("&");
                            String strSubstring = str5;
                            int i2 = 0;
                            while (i2 < strArrSplit.length) {
                                try {
                                    if (strArrSplit[i2].startsWith("cl=")) {
                                        try {
                                            strArr2 = strArrSplit;
                                            strSubstring = strArrSplit[i2].substring(3);
                                        } catch (Exception unused) {
                                            str = str4;
                                            str5 = strSubstring;
                                            str2 = str;
                                            z = false;
                                            this.f2350a = str2;
                                            this.f2351b = str5;
                                            this.i = linkedHashMap;
                                            this.f2352c = z;
                                            this.f2353d = z3;
                                            this.f2354e = z4;
                                            this.f2355f = iIntValue;
                                            this.f2356g = bDLocation2;
                                            this.f2357h = z5;
                                        }
                                    } else if (strArrSplit[i2].startsWith("wf=")) {
                                        String[] strArrSplit2 = strArrSplit[i2].substring(3).split("\\|");
                                        strArr2 = strArrSplit;
                                        int i3 = 0;
                                        while (i3 < strArrSplit2.length) {
                                            String[] strArrSplit3 = strArrSplit2[i3].split(";");
                                            String[] strArr3 = strArrSplit2;
                                            str3 = str4;
                                            if (strArrSplit3.length >= 2) {
                                                try {
                                                    linkedHashMap.put(strArrSplit3[0], Integer.valueOf(strArrSplit3[1]));
                                                } catch (Exception unused2) {
                                                    str5 = strSubstring;
                                                    str = str3;
                                                    str2 = str;
                                                    z = false;
                                                    this.f2350a = str2;
                                                    this.f2351b = str5;
                                                    this.i = linkedHashMap;
                                                    this.f2352c = z;
                                                    this.f2353d = z3;
                                                    this.f2354e = z4;
                                                    this.f2355f = iIntValue;
                                                    this.f2356g = bDLocation2;
                                                    this.f2357h = z5;
                                                }
                                            }
                                            i3++;
                                            strArrSplit2 = strArr3;
                                            str4 = str3;
                                        }
                                    } else {
                                        strArr2 = strArrSplit;
                                    }
                                    i2++;
                                    strArrSplit = strArr2;
                                    str4 = str4;
                                } catch (Exception unused3) {
                                    str3 = str4;
                                }
                            }
                            str5 = strSubstring;
                        } catch (Exception unused4) {
                            str3 = str4;
                        }
                    } else if (strArr[i].equals("-com")) {
                        String[] strArrSplit4 = strArr[i + 1].split(";");
                        if (strArrSplit4.length > 0) {
                            bDLocation = new BDLocation();
                            try {
                                bDLocation.setLatitude(Double.valueOf(strArrSplit4[0]).doubleValue());
                                bDLocation.setLongitude(Double.valueOf(strArrSplit4[1]).doubleValue());
                                bDLocation.setLocType(Integer.valueOf(strArrSplit4[2]).intValue());
                                bDLocation.setNetworkLocationType(strArrSplit4[3]);
                            } catch (Exception unused5) {
                                bDLocation2 = bDLocation;
                                str = str4;
                                str2 = str;
                                z = false;
                                this.f2350a = str2;
                                this.f2351b = str5;
                                this.i = linkedHashMap;
                                this.f2352c = z;
                                this.f2353d = z3;
                                this.f2354e = z4;
                                this.f2355f = iIntValue;
                                this.f2356g = bDLocation2;
                                this.f2357h = z5;
                            }
                        } else {
                            bDLocation = bDLocation2;
                        }
                        bDLocation2 = bDLocation;
                    } else if (strArr[i].equals("-log")) {
                        if (strArr[i + 1].equals("true")) {
                            z2 = true;
                        }
                    } else if (strArr[i].equals("-rgc")) {
                        if (strArr[i + 1].equals("true")) {
                            z4 = true;
                        }
                    } else if (strArr[i].equals("-poi")) {
                        if (strArr[i + 1].equals("true")) {
                            z3 = true;
                        }
                    } else if (strArr[i].equals("-minap")) {
                        try {
                            iIntValue = Integer.valueOf(strArr[i + 1]).intValue();
                        } catch (Exception unused6) {
                        }
                    } else if (strArr[i].equals("-des") && strArr[i + 1].equals("true")) {
                        z5 = true;
                    }
                } catch (Exception unused7) {
                }
            }
            z = true;
            str2 = !z2 ? null : str4;
            this.f2350a = str2;
            this.f2351b = str5;
            this.i = linkedHashMap;
            this.f2352c = z;
            this.f2353d = z3;
            this.f2354e = z4;
            this.f2355f = iIntValue;
            this.f2356g = bDLocation2;
            this.f2357h = z5;
        }
    }

    static Cursor a(BDLocation bDLocation) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(new Date(System.currentTimeMillis()));
        MatrixCursor matrixCursor = new MatrixCursor(f2349a);
        Object[] objArr = new Object[f2349a.length];
        objArr[matrixCursor.getColumnIndex("CoorType")] = CoordinateType.GCJ02;
        objArr[matrixCursor.getColumnIndex("Time")] = str9;
        objArr[matrixCursor.getColumnIndex("LocType")] = Integer.valueOf(bDLocation.getLocType());
        objArr[matrixCursor.getColumnIndex("Longitude")] = Double.valueOf(bDLocation.getLongitude());
        objArr[matrixCursor.getColumnIndex("Latitude")] = Double.valueOf(bDLocation.getLatitude());
        objArr[matrixCursor.getColumnIndex("Radius")] = Float.valueOf(bDLocation.getRadius());
        objArr[matrixCursor.getColumnIndex("NetworkLocationType")] = bDLocation.getNetworkLocationType();
        Address address = bDLocation.getAddress();
        if (address != null) {
            str2 = address.country;
            str3 = address.countryCode;
            str4 = address.province;
            str5 = address.city;
            str6 = address.cityCode;
            str7 = address.district;
            str8 = address.street;
            str = address.streetNumber;
        } else {
            str = null;
            str2 = null;
            str3 = null;
            str4 = null;
            str5 = null;
            str6 = null;
            str7 = null;
            str8 = null;
        }
        objArr[matrixCursor.getColumnIndex("Country")] = str2;
        objArr[matrixCursor.getColumnIndex("CountryCode")] = str3;
        objArr[matrixCursor.getColumnIndex("Province")] = str4;
        objArr[matrixCursor.getColumnIndex("City")] = str5;
        objArr[matrixCursor.getColumnIndex("CityCode")] = str6;
        objArr[matrixCursor.getColumnIndex("District")] = str7;
        objArr[matrixCursor.getColumnIndex("Street")] = str8;
        objArr[matrixCursor.getColumnIndex("StreetNumber")] = str;
        List<Poi> poiList = bDLocation.getPoiList();
        if (poiList == null) {
            objArr[matrixCursor.getColumnIndex("PoiList")] = null;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < poiList.size(); i++) {
                Poi poi = poiList.get(i);
                stringBuffer.append(poi.getId());
                stringBuffer.append(";");
                stringBuffer.append(poi.getName());
                stringBuffer.append(";");
                stringBuffer.append(poi.getRank());
                stringBuffer.append(";|");
            }
            objArr[matrixCursor.getColumnIndex("PoiList")] = stringBuffer.toString();
        }
        objArr[matrixCursor.getColumnIndex("LocationDescription")] = bDLocation.getLocationDescribe();
        matrixCursor.addRow(objArr);
        return matrixCursor;
    }

    /* JADX WARN: Removed duplicated region for block: B:87:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01db  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static com.baidu.location.BDLocation a(android.database.Cursor r23) {
        /*
            Method dump skipped, instruction units count: 519
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.j.a(android.database.Cursor):com.baidu.location.BDLocation");
    }

    static String a(BDLocation bDLocation, int i) {
        if (bDLocation == null || bDLocation.getLocType() == 67) {
            return String.format(Locale.CHINA, "&ofl=%s|%d", "1", Integer.valueOf(i));
        }
        String str = String.format(Locale.CHINA, "&ofl=%s|%d|%f|%f|%d", "1", Integer.valueOf(i), Double.valueOf(bDLocation.getLongitude()), Double.valueOf(bDLocation.getLatitude()), Integer.valueOf((int) bDLocation.getRadius()));
        if (bDLocation.getAddress() != null) {
            str = str + "&ofaddr=" + bDLocation.getAddress().address;
        }
        if (bDLocation.getPoiList() != null && bDLocation.getPoiList().size() > 0) {
            Poi poi = bDLocation.getPoiList().get(0);
            str = str + String.format(Locale.US, "&ofpoi=%s|%s", poi.getId(), poi.getName());
        }
        if (com.baidu.location.g.b.f2460e == null) {
            return str;
        }
        return str + String.format(Locale.US, "&pack=%s&sdk=%.3f", com.baidu.location.g.b.f2460e, Float.valueOf(7.72f));
    }

    static String a(BDLocation bDLocation, BDLocation bDLocation2, a aVar) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(bDLocation2 == null ? "&ofcl=0" : String.format(Locale.US, "&ofcl=1|%f|%f|%d", Double.valueOf(bDLocation2.getLongitude()), Double.valueOf(bDLocation2.getLatitude()), Integer.valueOf((int) bDLocation2.getRadius())));
        stringBuffer.append(bDLocation == null ? "&ofwf=0" : String.format(Locale.US, "&ofwf=1|%f|%f|%d", Double.valueOf(bDLocation.getLongitude()), Double.valueOf(bDLocation.getLatitude()), Integer.valueOf((int) bDLocation.getRadius())));
        stringBuffer.append((aVar == null || !aVar.f2354e) ? "&rgcn=0" : "&rgcn=1");
        stringBuffer.append((aVar == null || !aVar.f2353d) ? "&poin=0" : "&poin=1");
        stringBuffer.append((aVar == null || !aVar.f2357h) ? "&desc=0" : "&desc=1");
        if (aVar != null) {
            stringBuffer.append(String.format(Locale.US, "&aps=%d", Integer.valueOf(aVar.f2355f)));
        }
        return stringBuffer.toString();
    }

    static String[] a(com.baidu.location.e.a aVar, com.baidu.location.e.h hVar, BDLocation bDLocation, String str, boolean z, int i) {
        ArrayList arrayList = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        if (aVar != null) {
            stringBuffer.append(com.baidu.location.e.b.a().b(aVar));
        }
        if (hVar != null) {
            stringBuffer.append(hVar.a(30));
        }
        if (stringBuffer.length() > 0) {
            if (str != null) {
                stringBuffer.append(str);
            }
            arrayList.add("-loc");
            arrayList.add(stringBuffer.toString());
        }
        if (bDLocation != null) {
            String str2 = String.format(Locale.US, "%f;%f;%d;%s", Double.valueOf(bDLocation.getLatitude()), Double.valueOf(bDLocation.getLongitude()), Integer.valueOf(bDLocation.getLocType()), bDLocation.getNetworkLocationType());
            arrayList.add("-com");
            arrayList.add(str2);
        }
        if (z) {
            arrayList.add("-log");
            arrayList.add("true");
        }
        if (com.baidu.location.g.k.f2506g.equals("all")) {
            arrayList.add("-rgc");
            arrayList.add("true");
        }
        if (com.baidu.location.g.k.j) {
            arrayList.add("-poi");
            arrayList.add("true");
        }
        if (com.baidu.location.g.k.f2507h) {
            arrayList.add("-des");
            arrayList.add("true");
        }
        arrayList.add("-minap");
        arrayList.add(Integer.toString(i));
        String[] strArr = new String[arrayList.size()];
        arrayList.toArray(strArr);
        return strArr;
    }
}