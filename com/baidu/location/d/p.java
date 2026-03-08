package com.baidu.location.d;

import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.baidu.location.d.l;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
enum p extends l.b {
    p(String str, int i, String str2, String str3, String str4, int i2, int i3) {
        super(str, i, str2, str3, str4, i2, i3);
    }

    @Override // com.baidu.location.d.l.b
    List<String> a(JSONObject jSONObject, String str, int i) throws JSONException {
        int i2;
        char c2;
        JSONArray jSONArray;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        Iterator<String> it;
        String str8;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        JSONArray jSONArray2;
        String string;
        String string2;
        String string3;
        Integer numValueOf;
        Double dValueOf;
        String str14 = "y";
        String str15 = "x";
        String str16 = "rk";
        String str17 = "tp";
        String str18 = "ne";
        String str19 = "pid";
        Iterator<String> itKeys = jSONObject.keys();
        ArrayList arrayList = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        while (itKeys.hasNext()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            String next = itKeys.next();
            l.b.b(stringBuffer, next, str, 1);
            try {
                jSONArray = jSONObject.getJSONArray(next);
            } catch (JSONException unused) {
                jSONArray = null;
            }
            if (jSONArray != null) {
                it = itKeys;
                int i3 = 0;
                while (i3 < jSONArray.length()) {
                    try {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i3);
                        if (jSONObject2.has(str19)) {
                            str13 = str19;
                            string = jSONObject2.getString(str19);
                        } else {
                            str13 = str19;
                            string = null;
                        }
                        try {
                            if (jSONObject2.has(str18)) {
                                str12 = str18;
                                string2 = jSONObject2.getString(str18);
                            } else {
                                str12 = str18;
                                string2 = null;
                            }
                            try {
                                if (jSONObject2.has(str17)) {
                                    str11 = str17;
                                    string3 = jSONObject2.getString(str17);
                                } else {
                                    str11 = str17;
                                    string3 = null;
                                }
                                try {
                                    if (jSONObject2.has(str16)) {
                                        str10 = str16;
                                        numValueOf = Integer.valueOf(jSONObject2.getInt(str16));
                                    } else {
                                        str10 = str16;
                                        numValueOf = null;
                                    }
                                    try {
                                        if (jSONObject2.has(str15)) {
                                            str9 = str15;
                                            dValueOf = Double.valueOf(jSONObject2.getDouble(str15));
                                        } else {
                                            str9 = str15;
                                            dValueOf = null;
                                        }
                                        try {
                                            Double dValueOf2 = jSONObject2.has(str14) ? Double.valueOf(jSONObject2.getDouble(str14)) : null;
                                            str8 = str14;
                                            if (stringBuffer2.length() > 0) {
                                                try {
                                                    stringBuffer2.append(AppInfo.DELIM);
                                                } catch (JSONException unused2) {
                                                    jSONArray2 = jSONArray;
                                                }
                                            }
                                            jSONArray2 = jSONArray;
                                            try {
                                                stringBuffer2.append("(\"");
                                                stringBuffer2.append(string);
                                                stringBuffer2.append("\",\"");
                                                stringBuffer2.append(next);
                                                stringBuffer2.append("\",\"");
                                                stringBuffer2.append(string2);
                                                stringBuffer2.append("\",\"");
                                                stringBuffer2.append(string3);
                                                stringBuffer2.append("\",");
                                                stringBuffer2.append(dValueOf);
                                                stringBuffer2.append(AppInfo.DELIM);
                                                stringBuffer2.append(dValueOf2);
                                                stringBuffer2.append(AppInfo.DELIM);
                                                stringBuffer2.append(numValueOf);
                                                stringBuffer2.append(")");
                                            } catch (JSONException unused3) {
                                            }
                                        } catch (JSONException unused4) {
                                            str8 = str14;
                                        }
                                    } catch (JSONException unused5) {
                                        str8 = str14;
                                        str9 = str15;
                                    }
                                } catch (JSONException unused6) {
                                    str8 = str14;
                                    str9 = str15;
                                    str10 = str16;
                                }
                            } catch (JSONException unused7) {
                                str8 = str14;
                                str9 = str15;
                                str10 = str16;
                                str11 = str17;
                            }
                        } catch (JSONException unused8) {
                            str8 = str14;
                            str9 = str15;
                            str10 = str16;
                            str11 = str17;
                            str12 = str18;
                        }
                    } catch (JSONException unused9) {
                        str8 = str14;
                        str9 = str15;
                        str10 = str16;
                        str11 = str17;
                        str12 = str18;
                        str13 = str19;
                    }
                    if (i3 % 50 == 49) {
                        arrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", "RGCPOI", stringBuffer2.toString()));
                        stringBuffer2.setLength(0);
                    }
                    i3++;
                    str19 = str13;
                    str18 = str12;
                    str17 = str11;
                    str16 = str10;
                    str15 = str9;
                    jSONArray = jSONArray2;
                    str14 = str8;
                }
                str2 = str14;
                str3 = str15;
                str4 = str16;
                str5 = str17;
                str6 = str18;
                str7 = str19;
                if (stringBuffer2.length() > 0) {
                    arrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", "RGCPOI", stringBuffer2.toString()));
                }
            } else {
                str2 = str14;
                str3 = str15;
                str4 = str16;
                str5 = str17;
                str6 = str18;
                str7 = str19;
                it = itKeys;
            }
            itKeys = it;
            str19 = str7;
            str18 = str6;
            str17 = str5;
            str16 = str4;
            str15 = str3;
            str14 = str2;
        }
        if (stringBuffer.length() > 0) {
            c2 = 0;
            i2 = 1;
            arrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", "RGCUPDATE", stringBuffer));
        } else {
            i2 = 1;
            c2 = 0;
        }
        Locale locale = Locale.US;
        Object[] objArr = new Object[i2];
        objArr[c2] = Integer.valueOf(i);
        arrayList.add(String.format(locale, "DELETE FROM RGCPOI WHERE pid NOT IN (SELECT pid FROM RGCPOI LIMIT %d);", objArr));
        return arrayList;
    }
}