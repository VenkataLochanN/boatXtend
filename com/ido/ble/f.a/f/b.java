package com.ido.ble.f.a.f;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.common.d;
import com.ido.ble.common.e;
import com.ido.ble.common.k;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class b extends d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f4452c = "common_info";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f4453d = "lastConnectedDeviceInfo";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f4454e = "hasMigrateData";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f4455f = "bindMacAddressList";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static b f4456g;

    private b() {
    }

    public static b e() {
        if (f4456g == null) {
            f4456g = new b();
            f4456g.a(e.a());
        }
        return f4456g;
    }

    public void a(Context context) {
        super.a(context, f4452c);
    }

    public void a(BLEDevice bLEDevice) {
        b(f4453d, new Gson().toJson(bLEDevice));
    }

    public void a(boolean z) {
        b(f4454e, z);
    }

    public List<String> b() {
        List listA;
        String strA = a(f4455f, "");
        if (!TextUtils.isEmpty(strA) && (listA = k.a(strA, String[].class)) != null) {
            return new ArrayList(listA);
        }
        return new ArrayList();
    }

    public BLEDevice c() {
        return (BLEDevice) a(f4453d, BLEDevice.class);
    }

    public void c(String str) {
        List<String> listB = b();
        Iterator<String> it = listB.iterator();
        while (it.hasNext()) {
            if (it.next().equals(str)) {
                return;
            }
        }
        listB.add(str);
        b(f4455f, k.a(listB));
    }

    public void d(String str) {
        List<String> listB = b();
        ArrayList arrayList = new ArrayList(listB);
        int i = 0;
        while (true) {
            if (i >= listB.size()) {
                break;
            }
            if (listB.get(i).equals(str)) {
                arrayList.remove(i);
                break;
            }
            i++;
        }
        b(f4455f, k.a(arrayList));
    }

    public boolean d() {
        return a(f4454e, false);
    }
}