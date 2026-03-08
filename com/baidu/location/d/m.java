package com.baidu.location.d;

import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.baidu.location.d.l;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
enum m extends l.b {
    m(String str, int i, String str2, String str3, String str4, int i2, int i3) {
        super(str, i, str2, str3, str4, i2, i3);
    }

    @Override // com.baidu.location.d.l.b
    List<String> a(JSONObject jSONObject, String str, int i) throws JSONException {
        int i2;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String string;
        String string2;
        String string3;
        String string4;
        String string5;
        String str8 = "dist";
        String str9 = "ct";
        String str10 = "ctc";
        String str11 = "prov";
        String str12 = "cyc";
        String str13 = "cy";
        Iterator<String> itKeys = jSONObject.keys();
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            Iterator<String> it = itKeys;
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                if (jSONObject2.has(str13)) {
                    str7 = str13;
                    string = jSONObject2.getString(str13);
                } else {
                    str7 = str13;
                    string = null;
                }
                try {
                    if (jSONObject2.has(str12)) {
                        str6 = str12;
                        string2 = jSONObject2.getString(str12);
                    } else {
                        str6 = str12;
                        string2 = null;
                    }
                    try {
                        if (jSONObject2.has(str11)) {
                            str5 = str11;
                            string3 = jSONObject2.getString(str11);
                        } else {
                            str5 = str11;
                            string3 = null;
                        }
                        try {
                            if (jSONObject2.has(str10)) {
                                str4 = str10;
                                string4 = jSONObject2.getString(str10);
                            } else {
                                str4 = str10;
                                string4 = null;
                            }
                            try {
                                if (jSONObject2.has(str9)) {
                                    str3 = str9;
                                    string5 = jSONObject2.getString(str9);
                                } else {
                                    str3 = str9;
                                    string5 = null;
                                }
                                try {
                                    String string6 = jSONObject2.has(str8) ? jSONObject2.getString(str8) : null;
                                    if (stringBuffer.length() > 0) {
                                        str2 = str8;
                                        try {
                                            stringBuffer.append(AppInfo.DELIM);
                                        } catch (JSONException unused) {
                                        }
                                    } else {
                                        str2 = str8;
                                    }
                                    stringBuffer.append("(\"");
                                    stringBuffer.append(next);
                                    stringBuffer.append("\",\"");
                                    stringBuffer.append(string);
                                    stringBuffer.append("\",\"");
                                    stringBuffer.append(string2);
                                    stringBuffer.append("\",\"");
                                    stringBuffer.append(string3);
                                    stringBuffer.append("\",\"");
                                    stringBuffer.append(string5);
                                    stringBuffer.append("\",\"");
                                    stringBuffer.append(string4);
                                    stringBuffer.append("\",\"");
                                    stringBuffer.append(string6);
                                    stringBuffer.append("\",");
                                    stringBuffer.append(System.currentTimeMillis() / 1000);
                                    stringBuffer.append(",\"\")");
                                    try {
                                        l.b.b(stringBuffer2, next, str, 0);
                                    } catch (JSONException unused2) {
                                    }
                                } catch (JSONException unused3) {
                                    str2 = str8;
                                }
                            } catch (JSONException unused4) {
                                str2 = str8;
                                str3 = str9;
                            }
                        } catch (JSONException unused5) {
                            str2 = str8;
                            str3 = str9;
                            str4 = str10;
                        }
                    } catch (JSONException unused6) {
                        str2 = str8;
                        str3 = str9;
                        str4 = str10;
                        str5 = str11;
                    }
                } catch (JSONException unused7) {
                    str2 = str8;
                    str3 = str9;
                    str4 = str10;
                    str5 = str11;
                    str6 = str12;
                }
            } catch (JSONException unused8) {
                str2 = str8;
                str3 = str9;
                str4 = str10;
                str5 = str11;
                str6 = str12;
                str7 = str13;
            }
            if (i3 % 50 == 49 && stringBuffer.length() > 0) {
                arrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", "RGCAREA", stringBuffer));
                arrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", "RGCUPDATE", stringBuffer2));
                stringBuffer.setLength(0);
            }
            i3++;
            itKeys = it;
            str13 = str7;
            str8 = str2;
            str12 = str6;
            str11 = str5;
            str10 = str4;
            str9 = str3;
        }
        if (stringBuffer.length() > 0) {
            i2 = 1;
            arrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", "RGCAREA", stringBuffer));
            arrayList.add(String.format(Locale.US, "INSERT OR REPLACE INTO %s VALUES %s", "RGCUPDATE", stringBuffer2));
            stringBuffer.setLength(0);
        } else {
            i2 = 1;
        }
        Locale locale = Locale.US;
        Object[] objArr = new Object[i2];
        objArr[0] = Integer.valueOf(i);
        arrayList.add(String.format(locale, "DELETE FROM RGCAREA WHERE gridkey NOT IN (SELECT gridkey FROM RGCAREA LIMIT %d);", objArr));
        return arrayList;
    }
}