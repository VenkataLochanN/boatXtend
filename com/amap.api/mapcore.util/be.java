package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Handler;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: OfflineMapDownloadList.java */
/* JADX INFO: loaded from: classes.dex */
public class be {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ArrayList<OfflineMapProvince> f257a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private bp f258b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f259c;

    private boolean a(int i, int i2) {
        return i2 != 1 || i <= 2 || i >= 98;
    }

    private boolean b(int i) {
        return i == 4;
    }

    public boolean a(int i) {
        return i == 0 || i == 2 || i == 3 || i == 1 || i == 102 || i == 101 || i == 103 || i == -1;
    }

    public be(Context context, Handler handler) {
        this.f259c = context;
        this.f258b = bp.a(context);
    }

    private void a(bk bkVar) {
        bp bpVar = this.f258b;
        if (bpVar == null || bkVar == null) {
            return;
        }
        bpVar.a(bkVar);
    }

    private void b(bk bkVar) {
        bp bpVar = this.f258b;
        if (bpVar != null) {
            bpVar.b(bkVar);
        }
    }

    private boolean a(OfflineMapProvince offlineMapProvince) {
        if (offlineMapProvince == null) {
            return false;
        }
        Iterator<OfflineMapCity> it = offlineMapProvince.getCityList().iterator();
        while (it.hasNext()) {
            if (it.next().getState() != 4) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<OfflineMapProvince> a() {
        ArrayList<OfflineMapProvince> arrayList = new ArrayList<>();
        synchronized (this.f257a) {
            Iterator<OfflineMapProvince> it = this.f257a.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
        }
        return arrayList;
    }

    public OfflineMapCity a(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        synchronized (this.f257a) {
            Iterator<OfflineMapProvince> it = this.f257a.iterator();
            while (it.hasNext()) {
                for (OfflineMapCity offlineMapCity : it.next().getCityList()) {
                    if (offlineMapCity.getCode().equals(str)) {
                        return offlineMapCity;
                    }
                }
            }
            return null;
        }
    }

    public OfflineMapCity b(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        synchronized (this.f257a) {
            Iterator<OfflineMapProvince> it = this.f257a.iterator();
            while (it.hasNext()) {
                for (OfflineMapCity offlineMapCity : it.next().getCityList()) {
                    if (offlineMapCity.getCity().trim().equalsIgnoreCase(str.trim())) {
                        return offlineMapCity;
                    }
                }
            }
            return null;
        }
    }

    public OfflineMapProvince c(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        synchronized (this.f257a) {
            for (OfflineMapProvince offlineMapProvince : this.f257a) {
                if (offlineMapProvince.getProvinceName().trim().equalsIgnoreCase(str.trim())) {
                    return offlineMapProvince;
                }
            }
            return null;
        }
    }

    public ArrayList<OfflineMapCity> b() {
        ArrayList<OfflineMapCity> arrayList = new ArrayList<>();
        synchronized (this.f257a) {
            Iterator<OfflineMapProvince> it = this.f257a.iterator();
            while (it.hasNext()) {
                Iterator<OfflineMapCity> it2 = it.next().getCityList().iterator();
                while (it2.hasNext()) {
                    arrayList.add(it2.next());
                }
            }
        }
        return arrayList;
    }

    public void a(List<OfflineMapProvince> list) {
        OfflineMapProvince next;
        OfflineMapCity next2;
        synchronized (this.f257a) {
            if (this.f257a.size() > 0) {
                for (int i = 0; i < this.f257a.size(); i++) {
                    OfflineMapProvince offlineMapProvince = this.f257a.get(i);
                    Iterator<OfflineMapProvince> it = list.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                        if (offlineMapProvince.getPinyin().equals(next.getPinyin())) {
                            break;
                        }
                        if (offlineMapProvince.getPinyin().equals("quanguogaiyaotu") || offlineMapProvince.getProvinceCode().equals("000001") || offlineMapProvince.getProvinceCode().equals("100000")) {
                            if (next.getPinyin().equals("quanguogaiyaotu")) {
                                break;
                            }
                        }
                    }
                    if (next != null) {
                        a(offlineMapProvince, next);
                        ArrayList<OfflineMapCity> cityList = offlineMapProvince.getCityList();
                        ArrayList<OfflineMapCity> cityList2 = next.getCityList();
                        for (int i2 = 0; i2 < cityList.size(); i2++) {
                            OfflineMapCity offlineMapCity = cityList.get(i2);
                            Iterator<OfflineMapCity> it2 = cityList2.iterator();
                            while (true) {
                                if (it2.hasNext()) {
                                    next2 = it2.next();
                                    if (offlineMapCity.getPinyin().equals(next2.getPinyin())) {
                                        break;
                                    }
                                } else {
                                    next2 = null;
                                    break;
                                }
                            }
                            if (next2 != null) {
                                a(offlineMapCity, next2);
                            }
                        }
                    }
                }
            } else {
                Iterator<OfflineMapProvince> it3 = list.iterator();
                while (it3.hasNext()) {
                    this.f257a.add(it3.next());
                }
            }
        }
    }

    private void a(OfflineMapCity offlineMapCity, OfflineMapCity offlineMapCity2) {
        offlineMapCity.setUrl(offlineMapCity2.getUrl());
        offlineMapCity.setVersion(offlineMapCity2.getVersion());
        offlineMapCity.setSize(offlineMapCity2.getSize());
        offlineMapCity.setCode(offlineMapCity2.getCode());
        offlineMapCity.setPinyin(offlineMapCity2.getPinyin());
        offlineMapCity.setJianpin(offlineMapCity2.getJianpin());
    }

    private void a(OfflineMapProvince offlineMapProvince, OfflineMapProvince offlineMapProvince2) {
        offlineMapProvince.setUrl(offlineMapProvince2.getUrl());
        offlineMapProvince.setVersion(offlineMapProvince2.getVersion());
        offlineMapProvince.setSize(offlineMapProvince2.getSize());
        offlineMapProvince.setPinyin(offlineMapProvince2.getPinyin());
        offlineMapProvince.setJianpin(offlineMapProvince2.getJianpin());
    }

    public ArrayList<OfflineMapCity> c() {
        ArrayList<OfflineMapCity> arrayList;
        synchronized (this.f257a) {
            arrayList = new ArrayList<>();
            for (OfflineMapProvince offlineMapProvince : this.f257a) {
                if (offlineMapProvince != null) {
                    for (OfflineMapCity offlineMapCity : offlineMapProvince.getCityList()) {
                        if (offlineMapCity.getState() == 4 || offlineMapCity.getState() == 7) {
                            arrayList.add(offlineMapCity);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public ArrayList<OfflineMapProvince> d() {
        ArrayList<OfflineMapProvince> arrayList;
        synchronized (this.f257a) {
            arrayList = new ArrayList<>();
            for (OfflineMapProvince offlineMapProvince : this.f257a) {
                if (offlineMapProvince != null && (offlineMapProvince.getState() == 4 || offlineMapProvince.getState() == 7)) {
                    arrayList.add(offlineMapProvince);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<OfflineMapCity> e() {
        ArrayList<OfflineMapCity> arrayList;
        synchronized (this.f257a) {
            arrayList = new ArrayList<>();
            for (OfflineMapProvince offlineMapProvince : this.f257a) {
                if (offlineMapProvince != null) {
                    for (OfflineMapCity offlineMapCity : offlineMapProvince.getCityList()) {
                        if (a(offlineMapCity.getState())) {
                            arrayList.add(offlineMapCity);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public ArrayList<OfflineMapProvince> f() {
        ArrayList<OfflineMapProvince> arrayList;
        synchronized (this.f257a) {
            arrayList = new ArrayList<>();
            for (OfflineMapProvince offlineMapProvince : this.f257a) {
                if (offlineMapProvince != null && a(offlineMapProvince.getState())) {
                    arrayList.add(offlineMapProvince);
                }
            }
        }
        return arrayList;
    }

    public void a(az azVar) {
        String pinyin = azVar.getPinyin();
        synchronized (this.f257a) {
            Iterator<OfflineMapProvince> it = this.f257a.iterator();
            loop0: while (true) {
                if (!it.hasNext()) {
                    break;
                }
                OfflineMapProvince next = it.next();
                if (next != null) {
                    for (OfflineMapCity offlineMapCity : next.getCityList()) {
                        if (offlineMapCity.getPinyin().trim().equals(pinyin.trim())) {
                            a(azVar, offlineMapCity);
                            a(azVar, next);
                            break loop0;
                        }
                    }
                }
            }
        }
    }

    private void a(az azVar, OfflineMapCity offlineMapCity) {
        int iB = azVar.c().b();
        if (azVar.c().equals(azVar.f217a)) {
            b(azVar.x());
        } else {
            if (azVar.c().equals(azVar.f222f)) {
                bx.a("saveJSONObjectToFile  CITY " + azVar.getCity());
                b(azVar);
                azVar.x().c();
            }
            if (a(azVar.getcompleteCode(), azVar.c().b())) {
                a(azVar.x());
            }
        }
        offlineMapCity.setState(iB);
        offlineMapCity.setCompleteCode(azVar.getcompleteCode());
    }

    private void b(az azVar) {
        File[] fileArrListFiles = new File(er.c(this.f259c)).listFiles();
        if (fileArrListFiles == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            if (file.isFile() && file.exists() && file.getName().contains(azVar.getAdcode()) && file.getName().endsWith(".zip.tmp.dt")) {
                file.delete();
            }
        }
    }

    private void a(az azVar, OfflineMapProvince offlineMapProvince) {
        bk bkVar;
        int iB = azVar.c().b();
        if (iB == 6) {
            offlineMapProvince.setState(iB);
            offlineMapProvince.setCompleteCode(0);
            b(new bk(offlineMapProvince, this.f259c));
            try {
                bx.b(offlineMapProvince.getProvinceCode(), this.f259c);
                return;
            } catch (IOException e2) {
                e2.printStackTrace();
                return;
            } catch (Exception e3) {
                e3.printStackTrace();
                return;
            }
        }
        if (b(iB) && a(offlineMapProvince)) {
            if (azVar.getPinyin().equals(offlineMapProvince.getPinyin())) {
                offlineMapProvince.setState(iB);
                offlineMapProvince.setCompleteCode(azVar.getcompleteCode());
                offlineMapProvince.setVersion(azVar.getVersion());
                offlineMapProvince.setUrl(azVar.getUrl());
                bkVar = new bk(offlineMapProvince, this.f259c);
                bkVar.a(azVar.a());
                bkVar.d(azVar.getCode());
            } else {
                offlineMapProvince.setState(iB);
                offlineMapProvince.setCompleteCode(100);
                bkVar = new bk(offlineMapProvince, this.f259c);
            }
            bkVar.c();
            a(bkVar);
            bx.a("saveJSONObjectToFile  province " + bkVar.d());
        }
    }

    public void g() {
        h();
        this.f258b = null;
        this.f259c = null;
    }

    public void h() {
        ArrayList<OfflineMapProvince> arrayList = this.f257a;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f257a.clear();
            }
        }
    }
}