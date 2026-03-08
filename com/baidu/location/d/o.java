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
enum o extends l.b {
    o(String str, int i, String str2, String str3, String str4, int i2, int i3) {
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
        Iterator<String> it;
        String str6;
        String str7;
        String str8;
        String str9;
        Iterator<String> it2;
        String string;
        String string2;
        Double dValueOf;
        String str10 = "y";
        String str11 = "x";
        String str12 = "stn";
        String str13 = "st";
        Iterator<String> itKeys = jSONObject.keys();
        ArrayList arrayList = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            int i3 = 0;
            if (!itKeys.hasNext()) {
                break;
            }
            StringBuffer stringBuffer2 = new StringBuffer();
            String next = itKeys.next();
            l.b.b(stringBuffer, next, str, 0);
            try {
                jSONArray = jSONObject.getJSONArray(next);
            } catch (JSONException unused) {
                jSONArray = null;
            }
            if (jSONArray != null) {
                while (i3 < jSONArray.length()) {
                    try {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i3);
                        if (jSONObject2.has(str13)) {
                            str9 = str13;
                            string = jSONObject2.getString(str13);
                        } else {
                            str9 = str13;
                            string = null;
                        }
                        try {
                            if (jSONObject2.has(str12)) {
                                str8 = str12;
                                string2 = jSONObject2.getString(str12);
                            } else {
                                str8 = str12;
                                string2 = null;
                            }
                            try {
                                if (jSONObject2.has(str11)) {
                                    str7 = str11;
                                    dValueOf = Double.valueOf(jSONObject2.getDouble(str11));
                                } else {
                                    str7 = str11;
                                    dValueOf = null;
                                }
                                try {
                                    Double dValueOf2 = jSONObject2.has(str10) ? Double.valueOf(jSONObject2.getDouble(str10)) : null;
                                    str6 = str10;
                                    if (stringBuffer2.length() > 0) {
                                        try {
                                            stringBuffer2.append(AppInfo.DELIM);
                                        } catch (JSONException unused2) {
                                            it2 = itKeys;
                                        }
                                    }
                                    it2 = itKeys;
                                    try {
                                        stringBuffer2.append("(NULL,\"");
                                        stringBuffer2.append(next);
                                        stringBuffer2.append("\",\"");
                                        stringBuffer2.append(string);
                                        stringBuffer2.append("\",\"");
                                        stringBuffer2.append(string2);
                                        stringBuffer2.append("\",");
                                        stringBuffer2.append(dValueOf);
                                        stringBuffer2.append(AppInfo.DELIM);
                                        stringBuffer2.append(dValueOf2);
                                        stringBuffer2.append(")");
                                    } catch (JSONException unused3) {
                                    }
                                } catch (JSONException unused4) {
                                    str6 = str10;
                                }
                            } catch (JSONException unused5) {
                                str6 = str10;
                                str7 = str11;
                            }
                        } catch (JSONException unused6) {
                            str6 = str10;
                            str7 = str11;
                            str8 = str12;
                        }
                    } catch (JSONException unused7) {
                        str6 = str10;
                        str7 = str11;
                        str8 = str12;
                        str9 = str13;
                    }
                    if (i3 % 50 == 49 && stringBuffer2.length() > 0) {
                        arrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", "RGCSITE", stringBuffer2.toString()));
                        stringBuffer2.setLength(0);
                    }
                    i3++;
                    str13 = str9;
                    str12 = str8;
                    str11 = str7;
                    itKeys = it2;
                    str10 = str6;
                }
                str2 = str10;
                str3 = str11;
                str4 = str12;
                str5 = str13;
                it = itKeys;
                if (stringBuffer2.length() > 0) {
                    arrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", "RGCSITE", stringBuffer2.toString()));
                }
            } else {
                str2 = str10;
                str3 = str11;
                str4 = str12;
                str5 = str13;
                it = itKeys;
            }
            str13 = str5;
            str12 = str4;
            str11 = str3;
            itKeys = it;
            str10 = str2;
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
        arrayList.add(String.format(locale, "DELETE FROM RGCSITE WHERE _id NOT IN (SELECT _id FROM RGCSITE LIMIT %d);", objArr));
        return arrayList;
    }
}