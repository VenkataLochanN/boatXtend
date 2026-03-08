package com.baidu.mapsdkplatform.comapi.favrite;

import android.os.Bundle;
import android.text.TextUtils;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.inner.Point;
import com.ido.common.constant.LanguageRegion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static a f3491b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.baidu.mapsdkplatform.comjni.map.favorite.a f3492a = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3493c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f3494d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Vector<String> f3495e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Vector<String> f3496f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f3497g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private c f3498h;
    private b i;

    /* JADX INFO: renamed from: com.baidu.mapsdkplatform.comapi.favrite.a$a, reason: collision with other inner class name */
    class C0032a implements Comparator<String> {
        C0032a() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(String str, String str2) {
            return str2.compareTo(str);
        }
    }

    private class b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private long f3501b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private long f3502c;

        private b() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            this.f3501b = System.currentTimeMillis();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            this.f3502c = System.currentTimeMillis();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean c() {
            return this.f3502c - this.f3501b > 1000;
        }
    }

    private class c {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f3504b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private long f3505c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private long f3506d;

        private c() {
            this.f3505c = BootloaderScanner.TIMEOUT;
            this.f3506d = 0L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String a() {
            return this.f3504b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String str) {
            this.f3504b = str;
            this.f3506d = System.currentTimeMillis();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean b() {
            return TextUtils.isEmpty(this.f3504b);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean c() {
            return true;
        }
    }

    private a() {
        this.f3498h = new c();
        this.i = new b();
    }

    public static a a() {
        if (f3491b == null) {
            synchronized (a.class) {
                if (f3491b == null) {
                    f3491b = new a();
                    f3491b.h();
                }
            }
        }
        return f3491b;
    }

    public static boolean g() {
        com.baidu.mapsdkplatform.comjni.map.favorite.a aVar;
        a aVar2 = f3491b;
        return (aVar2 == null || (aVar = aVar2.f3492a) == null || !aVar.d()) ? false : true;
    }

    private boolean h() {
        if (this.f3492a == null) {
            this.f3492a = new com.baidu.mapsdkplatform.comjni.map.favorite.a();
            if (this.f3492a.a() == 0) {
                this.f3492a = null;
                return false;
            }
            j();
            i();
        }
        return true;
    }

    private boolean i() {
        if (this.f3492a == null) {
            return false;
        }
        String str = SysOSUtil.getModuleFileName() + "/";
        this.f3492a.a(1);
        return this.f3492a.a(str, "fav_poi", "fifo", 10, 501, -1);
    }

    private void j() {
        this.f3493c = false;
        this.f3494d = false;
    }

    public synchronized int a(String str, FavSyncPoi favSyncPoi) {
        if (this.f3492a == null) {
            return 0;
        }
        if (str != null && !str.equals("") && favSyncPoi != null) {
            j();
            ArrayList<String> arrayListE = e();
            if ((arrayListE != null ? arrayListE.size() : 0) + 1 > 500) {
                return -2;
            }
            if (arrayListE != null && arrayListE.size() > 0) {
                Iterator<String> it = arrayListE.iterator();
                while (it.hasNext()) {
                    FavSyncPoi favSyncPoiB = b(it.next());
                    if (favSyncPoiB != null && str.equals(favSyncPoiB.f3484b)) {
                        return -1;
                    }
                }
            }
            try {
                JSONObject jSONObject = new JSONObject();
                favSyncPoi.f3484b = str;
                String strValueOf = String.valueOf(System.currentTimeMillis());
                String str2 = strValueOf + "_" + favSyncPoi.hashCode();
                favSyncPoi.f3490h = strValueOf;
                favSyncPoi.f3483a = str2;
                jSONObject.put("bdetail", favSyncPoi.i);
                jSONObject.put("uspoiname", favSyncPoi.f3484b);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("x", favSyncPoi.f3485c.getmPtx());
                jSONObject2.put("y", favSyncPoi.f3485c.getmPty());
                jSONObject.put(LanguageRegion.PT, jSONObject2);
                jSONObject.put("ncityid", favSyncPoi.f3487e);
                jSONObject.put("npoitype", favSyncPoi.f3489g);
                jSONObject.put("uspoiuid", favSyncPoi.f3488f);
                jSONObject.put("addr", favSyncPoi.f3486d);
                jSONObject.put("addtimesec", favSyncPoi.f3490h);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("Fav_Sync", jSONObject);
                jSONObject3.put("Fav_Content", favSyncPoi.j);
                if (!this.f3492a.a(str2, jSONObject3.toString())) {
                    return 0;
                }
                j();
                return 1;
            } catch (JSONException unused) {
                return 0;
            } finally {
                g();
            }
        }
        return -1;
    }

    public synchronized boolean a(String str) {
        if (this.f3492a == null) {
            return false;
        }
        if (str != null && !str.equals("")) {
            if (!c(str)) {
                return false;
            }
            j();
            return this.f3492a.a(str);
        }
        return false;
    }

    public FavSyncPoi b(String str) {
        if (this.f3492a != null && str != null && !str.equals("")) {
            try {
                if (!c(str)) {
                    return null;
                }
                FavSyncPoi favSyncPoi = new FavSyncPoi();
                String strB = this.f3492a.b(str);
                if (strB != null && !strB.equals("")) {
                    JSONObject jSONObject = new JSONObject(strB);
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("Fav_Sync");
                    String strOptString = jSONObject.optString("Fav_Content");
                    favSyncPoi.f3484b = jSONObjectOptJSONObject.optString("uspoiname");
                    JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject(LanguageRegion.PT);
                    favSyncPoi.f3485c = new Point(jSONObjectOptJSONObject2.optInt("x"), jSONObjectOptJSONObject2.optInt("y"));
                    favSyncPoi.f3487e = jSONObjectOptJSONObject.optString("ncityid");
                    favSyncPoi.f3488f = jSONObjectOptJSONObject.optString("uspoiuid");
                    favSyncPoi.f3489g = jSONObjectOptJSONObject.optInt("npoitype");
                    favSyncPoi.f3486d = jSONObjectOptJSONObject.optString("addr");
                    favSyncPoi.f3490h = jSONObjectOptJSONObject.optString("addtimesec");
                    favSyncPoi.i = jSONObjectOptJSONObject.optBoolean("bdetail");
                    favSyncPoi.j = strOptString;
                    favSyncPoi.f3483a = str;
                    return favSyncPoi;
                }
                return null;
            } catch (NullPointerException e2) {
                e2.printStackTrace();
            } catch (JSONException e3) {
                e3.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public void b() {
        a aVar = f3491b;
        if (aVar != null) {
            com.baidu.mapsdkplatform.comjni.map.favorite.a aVar2 = aVar.f3492a;
            if (aVar2 != null) {
                aVar2.b();
                f3491b.f3492a = null;
            }
            f3491b = null;
        }
    }

    public synchronized boolean b(String str, FavSyncPoi favSyncPoi) {
        boolean z = false;
        if (this.f3492a != null && str != null && !str.equals("") && favSyncPoi != null) {
            if (!c(str)) {
                return false;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("uspoiname", favSyncPoi.f3484b);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("x", favSyncPoi.f3485c.getmPtx());
                jSONObject2.put("y", favSyncPoi.f3485c.getmPty());
                jSONObject.put(LanguageRegion.PT, jSONObject2);
                jSONObject.put("ncityid", favSyncPoi.f3487e);
                jSONObject.put("npoitype", favSyncPoi.f3489g);
                jSONObject.put("uspoiuid", favSyncPoi.f3488f);
                jSONObject.put("addr", favSyncPoi.f3486d);
                favSyncPoi.f3490h = String.valueOf(System.currentTimeMillis());
                jSONObject.put("addtimesec", favSyncPoi.f3490h);
                jSONObject.put("bdetail", false);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("Fav_Sync", jSONObject);
                jSONObject3.put("Fav_Content", favSyncPoi.j);
                j();
                if (this.f3492a != null) {
                    if (this.f3492a.b(str, jSONObject3.toString())) {
                        z = true;
                    }
                }
                return z;
            } catch (JSONException unused) {
                return false;
            }
        }
        return false;
    }

    public synchronized boolean c() {
        if (this.f3492a == null) {
            return false;
        }
        j();
        boolean zC = this.f3492a.c();
        g();
        return zC;
    }

    public boolean c(String str) {
        return (this.f3492a == null || str == null || str.equals("") || !this.f3492a.c(str)) ? false : true;
    }

    public ArrayList<String> d() {
        String strB;
        Vector<String> vector;
        if (this.f3492a == null) {
            return null;
        }
        if (this.f3494d && (vector = this.f3496f) != null) {
            return new ArrayList<>(vector);
        }
        try {
            Bundle bundle = new Bundle();
            this.f3492a.a(bundle);
            String[] stringArray = bundle.getStringArray("rstString");
            if (stringArray != null) {
                if (this.f3496f == null) {
                    this.f3496f = new Vector<>();
                } else {
                    this.f3496f.clear();
                }
                for (int i = 0; i < stringArray.length; i++) {
                    if (!stringArray[i].equals("data_version") && (strB = this.f3492a.b(stringArray[i])) != null && !strB.equals("")) {
                        this.f3496f.add(stringArray[i]);
                    }
                }
                if (this.f3496f.size() > 0) {
                    try {
                        Collections.sort(this.f3496f, new C0032a());
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    this.f3494d = true;
                }
            } else if (this.f3496f != null) {
                this.f3496f.clear();
                this.f3496f = null;
            }
            if (this.f3496f != null && !this.f3496f.isEmpty()) {
                return new ArrayList<>(this.f3496f);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public ArrayList<String> e() {
        Vector<String> vector;
        if (this.f3492a == null) {
            return null;
        }
        if (this.f3493c && (vector = this.f3495e) != null) {
            return new ArrayList<>(vector);
        }
        try {
            Bundle bundle = new Bundle();
            this.f3492a.a(bundle);
            String[] stringArray = bundle.getStringArray("rstString");
            if (stringArray != null) {
                if (this.f3495e == null) {
                    this.f3495e = new Vector<>();
                } else {
                    this.f3495e.clear();
                }
                for (String str : stringArray) {
                    if (!str.equals("data_version")) {
                        this.f3495e.add(str);
                    }
                }
                if (this.f3495e.size() > 0) {
                    try {
                        Collections.sort(this.f3495e, new C0032a());
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    this.f3493c = true;
                }
            } else if (this.f3495e != null) {
                this.f3495e.clear();
                this.f3495e = null;
            }
            Vector<String> vector2 = this.f3495e;
            if (vector2 == null || vector2.size() == 0) {
                return null;
            }
            return new ArrayList<>(this.f3495e);
        } catch (Exception unused) {
            return null;
        }
    }

    public String f() {
        String strB;
        if (this.i.c() && !this.f3498h.c() && !this.f3498h.b()) {
            return this.f3498h.a();
        }
        this.i.a();
        if (this.f3492a == null) {
            return null;
        }
        ArrayList<String> arrayListD = d();
        JSONObject jSONObject = new JSONObject();
        if (arrayListD != null) {
            try {
                JSONArray jSONArray = new JSONArray();
                int i = 0;
                for (String str : arrayListD) {
                    if (str != null && !str.equals("data_version") && (strB = this.f3492a.b(str)) != null && !strB.equals("")) {
                        JSONObject jSONObjectOptJSONObject = new JSONObject(strB).optJSONObject("Fav_Sync");
                        jSONObjectOptJSONObject.put("key", str);
                        jSONArray.put(i, jSONObjectOptJSONObject);
                        i++;
                    }
                }
                if (i > 0) {
                    jSONObject.put("favcontents", jSONArray);
                    jSONObject.put("favpoinum", i);
                }
            } catch (JSONException unused) {
                return null;
            }
        }
        this.i.b();
        this.f3498h.a(jSONObject.toString());
        return this.f3498h.a();
    }
}