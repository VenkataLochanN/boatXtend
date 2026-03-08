package com.baidu.location.indoor.mapversion.a;

import android.text.TextUtils;
import android.util.Base64;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashMap<String, c> f2639a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HashSet<a> f2640b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private File f2641c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f2642d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f2643e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private String f2644f;

    public interface a {
        void a(boolean z);
    }

    private void a(boolean z) {
        Iterator<a> it = this.f2640b.iterator();
        while (it.hasNext()) {
            it.next().a(z);
        }
        this.f2642d = false;
    }

    private boolean b(String str) {
        if (str != null) {
            try {
                if (!str.equalsIgnoreCase("")) {
                    JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("item");
                    this.f2639a = new HashMap<>();
                    for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                        c cVar = new c(jSONArrayOptJSONArray.getJSONObject(i));
                        if (cVar.b() != null && cVar.c() != null) {
                            this.f2639a.put(c.a(cVar.c()), cVar);
                        }
                    }
                    return true;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    private void c() {
        new Thread(new e(this)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d() {
        try {
            File file = new File(this.f2641c, this.f2643e);
            long jCurrentTimeMillis = System.currentTimeMillis();
            file.lastModified();
            if (file.exists() && jCurrentTimeMillis - file.lastModified() <= 604800000) {
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                this.f2644f = bufferedReader.readLine();
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        bufferedReader.close();
                        a(b(new String(Base64.decode(sb.toString(), 0))));
                        return true;
                    }
                    sb.append(line);
                    sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                }
            }
            this.f2642d = false;
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        } finally {
            this.f2642d = false;
        }
    }

    public c a(String str, String str2) {
        String str3;
        if (this.f2639a == null || (str3 = this.f2643e) == null || !str.equalsIgnoreCase(str3)) {
            return null;
        }
        return this.f2639a.get(c.a(str2));
    }

    public void a(String str) {
        if (this.f2642d) {
            return;
        }
        this.f2642d = true;
        if (TextUtils.isEmpty(str)) {
            a(false);
            return;
        }
        String str2 = this.f2643e;
        if (str2 != null && str.equalsIgnoreCase(str2) && a()) {
            a(true);
            return;
        }
        this.f2643e = str;
        this.f2644f = null;
        if (d()) {
            return;
        }
        c();
    }

    public boolean a() {
        HashMap<String, c> map = this.f2639a;
        return map != null && map.size() > 0;
    }

    public String b() {
        if (!a()) {
            return "A001";
        }
        Object[] array = this.f2639a.keySet().toArray();
        return (array.length <= 0 || this.f2639a.get(array[0].toString()) == null) ? "A001" : this.f2639a.get(array[0].toString()).c();
    }
}