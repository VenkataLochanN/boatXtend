package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ido.common.utils.FileDialDefinedUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: Utility.java */
/* JADX INFO: loaded from: classes.dex */
public class bx {
    public static void a(String str) {
    }

    public static long a() {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return 0L;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return ((long) statFs.getFreeBlocks()) * ((long) statFs.getBlockSize());
    }

    public static List<OfflineMapProvince> a(String str, Context context) throws JSONException {
        if (str == null || "".equals(str)) {
            return new ArrayList();
        }
        return a(new JSONObject(str), context);
    }

    public static List<OfflineMapProvince> a(JSONObject jSONObject, Context context) throws Throwable {
        JSONObject jSONObjectOptJSONObject;
        JSONObject jSONObjectOptJSONObject2;
        ArrayList arrayList = new ArrayList();
        if (!jSONObject.has("result")) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("result", new JSONObject().put("offlinemap_with_province_vfour", jSONObject));
                c(jSONObject2.toString(), context);
                jSONObjectOptJSONObject = jSONObject2.optJSONObject("result");
            } catch (JSONException e2) {
                JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("result");
                hn.c(e2, "Utility", "parseJson");
                e2.printStackTrace();
                jSONObjectOptJSONObject = jSONObjectOptJSONObject3;
            }
        } else {
            jSONObjectOptJSONObject = jSONObject.optJSONObject("result");
        }
        if (jSONObjectOptJSONObject != null) {
            JSONObject jSONObjectOptJSONObject4 = jSONObjectOptJSONObject.optJSONObject("offlinemap_with_province_vfour");
            if (jSONObjectOptJSONObject4 == null) {
                return arrayList;
            }
            jSONObjectOptJSONObject2 = jSONObjectOptJSONObject4.optJSONObject("offlinemapinfo_with_province");
        } else {
            jSONObjectOptJSONObject2 = jSONObject.optJSONObject("offlinemapinfo_with_province");
        }
        if (jSONObjectOptJSONObject2 == null) {
            return arrayList;
        }
        if (jSONObjectOptJSONObject2.has("version")) {
            ba.f239d = a(jSONObjectOptJSONObject2, "version");
        }
        JSONArray jSONArrayOptJSONArray = jSONObjectOptJSONObject2.optJSONArray("provinces");
        if (jSONArrayOptJSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject5 = jSONArrayOptJSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject5 != null) {
                arrayList.add(a(jSONObjectOptJSONObject5));
            }
        }
        JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject2.optJSONArray("others");
        JSONObject jSONObject3 = null;
        if (jSONArrayOptJSONArray2 != null && jSONArrayOptJSONArray2.length() > 0) {
            jSONObject3 = jSONArrayOptJSONArray2.getJSONObject(0);
        }
        if (jSONObject3 == null) {
            return arrayList;
        }
        arrayList.add(a(jSONObject3));
        return arrayList;
    }

    public static OfflineMapProvince a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        OfflineMapProvince offlineMapProvince = new OfflineMapProvince();
        offlineMapProvince.setUrl(a(jSONObject, "url"));
        offlineMapProvince.setProvinceName(a(jSONObject, AppMeasurementSdk.ConditionalUserProperty.NAME));
        offlineMapProvince.setJianpin(a(jSONObject, "jianpin"));
        offlineMapProvince.setPinyin(a(jSONObject, "pinyin"));
        offlineMapProvince.setProvinceCode(d(a(jSONObject, "adcode")));
        offlineMapProvince.setVersion(a(jSONObject, "version"));
        offlineMapProvince.setSize(Long.parseLong(a(jSONObject, "size")));
        offlineMapProvince.setCityList(b(jSONObject));
        return offlineMapProvince;
    }

    private static String d(String str) {
        return "000001".equals(str) ? "100000" : str;
    }

    public static ArrayList<OfflineMapCity> b(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("cities");
        ArrayList<OfflineMapCity> arrayList = new ArrayList<>();
        if (jSONArrayOptJSONArray == null) {
            return arrayList;
        }
        if (jSONArrayOptJSONArray.length() == 0) {
            arrayList.add(c(jSONObject));
        }
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                arrayList.add(c(jSONObjectOptJSONObject));
            }
        }
        return arrayList;
    }

    public static OfflineMapCity c(JSONObject jSONObject) throws JSONException {
        OfflineMapCity offlineMapCity = new OfflineMapCity();
        offlineMapCity.setAdcode(d(a(jSONObject, "adcode")));
        offlineMapCity.setUrl(a(jSONObject, "url"));
        offlineMapCity.setCity(a(jSONObject, AppMeasurementSdk.ConditionalUserProperty.NAME));
        offlineMapCity.setCode(a(jSONObject, "citycode"));
        offlineMapCity.setPinyin(a(jSONObject, "pinyin"));
        offlineMapCity.setJianpin(a(jSONObject, "jianpin"));
        offlineMapCity.setVersion(a(jSONObject, "version"));
        offlineMapCity.setSize(Long.parseLong(a(jSONObject, "size")));
        return offlineMapCity;
    }

    public static long a(File file) {
        long length;
        if (!file.isDirectory()) {
            return file.length();
        }
        long j = 0;
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return 0L;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory()) {
                length = a(file2);
            } else {
                length = file2.length();
            }
            j += length;
        }
        return j;
    }

    public static boolean b(File file) throws Exception {
        if (file == null || !file.exists()) {
            return false;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (int i = 0; i < fileArrListFiles.length; i++) {
                if (fileArrListFiles[i].isFile()) {
                    if (!fileArrListFiles[i].delete()) {
                        return false;
                    }
                } else if (!b(fileArrListFiles[i])) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static String a(Context context, String str) {
        try {
            return er.a(el.a(context).open(str));
        } catch (Throwable th) {
            hn.c(th, "MapDownloadManager", "readOfflineAsset");
            th.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.io.BufferedReader] */
    public static String c(File file) {
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            try {
                try {
                    fileInputStream = new FileInputStream((File) file);
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "utf-8"));
                        while (true) {
                            try {
                                String line = bufferedReader.readLine();
                                if (line == null) {
                                    break;
                                }
                                stringBuffer.append(line);
                            } catch (FileNotFoundException e2) {
                                e = e2;
                                hn.c(e, "MapDownloadManager", "readOfflineSD filenotfound");
                                e.printStackTrace();
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                return null;
                            } catch (IOException e4) {
                                e = e4;
                                hn.c(e, "MapDownloadManager", "readOfflineSD io");
                                e.printStackTrace();
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e5) {
                                        e5.printStackTrace();
                                    }
                                }
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                return null;
                            }
                        }
                        String string = stringBuffer.toString();
                        try {
                            bufferedReader.close();
                        } catch (IOException e6) {
                            e6.printStackTrace();
                        }
                        try {
                            fileInputStream.close();
                        } catch (IOException e7) {
                            e7.printStackTrace();
                        }
                        return string;
                    } catch (FileNotFoundException e8) {
                        e = e8;
                        bufferedReader = null;
                    } catch (IOException e9) {
                        e = e9;
                        bufferedReader = null;
                    } catch (Throwable th) {
                        th = th;
                        file = 0;
                        if (file != 0) {
                            try {
                                file.close();
                            } catch (IOException e10) {
                                e10.printStackTrace();
                            }
                        }
                        if (fileInputStream == null) {
                            throw th;
                        }
                        try {
                            fileInputStream.close();
                            throw th;
                        } catch (IOException e11) {
                            e11.printStackTrace();
                            throw th;
                        }
                    }
                } catch (FileNotFoundException e12) {
                    e = e12;
                    bufferedReader = null;
                    fileInputStream = null;
                } catch (IOException e13) {
                    e = e13;
                    bufferedReader = null;
                    fileInputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    file = 0;
                    fileInputStream = null;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException e14) {
            e14.printStackTrace();
        }
    }

    public static void b(String str, Context context) throws Exception {
        File[] fileArrListFiles = new File(er.c(context)).listFiles();
        if (fileArrListFiles == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            if (file.exists() && file.getName().contains(str)) {
                b(file);
            }
        }
        b(er.c(context));
    }

    public static void b(String str) {
        File[] fileArrListFiles;
        File file = new File(str);
        if (file.exists() && file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.exists() && file2.isDirectory()) {
                    String[] list = file2.list();
                    if (list == null) {
                        file2.delete();
                    } else if (list.length == 0) {
                        file2.delete();
                    }
                }
            }
        }
    }

    public static String a(JSONObject jSONObject, String str) throws JSONException {
        return (jSONObject == null || !jSONObject.has(str) || "[]".equals(jSONObject.getString(str))) ? "" : jSONObject.optString(str).trim();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r7v13, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v21 */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* JADX WARN: Type inference failed for: r7v23 */
    /* JADX WARN: Type inference failed for: r7v24 */
    /* JADX WARN: Type inference failed for: r7v25 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.io.OutputStream] */
    public static void c(String str, Context context) throws Throwable {
        if ("".equals(er.c(context))) {
            return;
        }
        File file = new File(er.c(context) + "offlinemapv4.png");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e2) {
                hn.c(e2, "OfflineUpdateCityHandlerAbstract", "writeSD dirCreate");
                e2.printStackTrace();
            }
        }
        if (a() <= 1048576) {
            return;
        }
        ?? r7 = 0;
        r7 = 0;
        r7 = 0;
        r7 = 0;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    try {
                        String str2 = "utf-8";
                        fileOutputStream.write(str.getBytes("utf-8"));
                        fileOutputStream.close();
                        r7 = str2;
                    } catch (FileNotFoundException e3) {
                        e = e3;
                        r7 = fileOutputStream;
                        hn.c(e, "OfflineUpdateCityHandlerAbstract", "writeSD filenotfound");
                        e.printStackTrace();
                        if (r7 != 0) {
                            r7.close();
                            r7 = r7;
                        }
                    } catch (IOException e4) {
                        e = e4;
                        r7 = fileOutputStream;
                        hn.c(e, "OfflineUpdateCityHandlerAbstract", "writeSD io");
                        e.printStackTrace();
                        if (r7 != 0) {
                            r7.close();
                            r7 = r7;
                        }
                    } catch (Throwable th) {
                        th = th;
                        r7 = fileOutputStream;
                        if (r7 != 0) {
                            try {
                                r7.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (FileNotFoundException e6) {
                    e = e6;
                } catch (IOException e7) {
                    e = e7;
                }
            } catch (IOException e8) {
                e8.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String c(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return str.substring(str.lastIndexOf("/") + 1, str.indexOf(FileDialDefinedUtil.FILE_ZIP));
        } catch (Throwable th) {
            hn.c(th, "Utility", "getZipFileNameFromUrl");
            return null;
        }
    }
}