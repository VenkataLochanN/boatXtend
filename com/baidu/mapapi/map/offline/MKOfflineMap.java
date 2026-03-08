package com.baidu.mapapi.map.offline;

import com.baidu.mapsdkplatform.comapi.map.i;
import com.baidu.mapsdkplatform.comapi.map.q;
import com.baidu.mapsdkplatform.comapi.map.r;
import com.baidu.mapsdkplatform.comapi.map.u;
import com.baidu.mapsdkplatform.comapi.map.v;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class MKOfflineMap {
    public static final int TYPE_DOWNLOAD_UPDATE = 0;
    public static final int TYPE_NETWORK_ERROR = 2;
    public static final int TYPE_NEW_OFFLINE = 6;
    public static final int TYPE_VER_UPDATE = 4;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3057a = MKOfflineMap.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private r f3058b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private MKOfflineMapListener f3059c;

    public void destroy() {
        this.f3058b.d(0);
        this.f3058b.b((v) null);
        this.f3058b.b();
        i.b();
    }

    public ArrayList<MKOLUpdateElement> getAllUpdateInfo() {
        ArrayList<u> arrayListE = this.f3058b.e();
        if (arrayListE == null) {
            return null;
        }
        ArrayList<MKOLUpdateElement> arrayList = new ArrayList<>();
        Iterator<u> it = arrayListE.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getUpdatElementFromLocalMapElement(it.next().a()));
        }
        return arrayList;
    }

    public ArrayList<MKOLSearchRecord> getHotCityList() {
        ArrayList<q> arrayListC = this.f3058b.c();
        if (arrayListC == null) {
            return null;
        }
        ArrayList<MKOLSearchRecord> arrayList = new ArrayList<>();
        Iterator<q> it = arrayListC.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getSearchRecordFromLocalCityInfo(it.next()));
        }
        return arrayList;
    }

    public ArrayList<MKOLSearchRecord> getOfflineCityList() {
        ArrayList<q> arrayListD = this.f3058b.d();
        if (arrayListD == null) {
            return null;
        }
        ArrayList<MKOLSearchRecord> arrayList = new ArrayList<>();
        Iterator<q> it = arrayListD.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getSearchRecordFromLocalCityInfo(it.next()));
        }
        return arrayList;
    }

    public MKOLUpdateElement getUpdateInfo(int i) {
        u uVarG = this.f3058b.g(i);
        if (uVarG == null) {
            return null;
        }
        return OfflineMapUtil.getUpdatElementFromLocalMapElement(uVarG.a());
    }

    @Deprecated
    public int importOfflineData() {
        return importOfflineData(false);
    }

    @Deprecated
    public int importOfflineData(boolean z) {
        ArrayList<u> arrayListE = this.f3058b.e();
        int size = arrayListE != null ? arrayListE.size() : 0;
        int size2 = size;
        this.f3058b.a(z, true);
        ArrayList<u> arrayListE2 = this.f3058b.e();
        if (arrayListE2 != null) {
            size2 = arrayListE2.size();
        }
        return size2 - size;
    }

    public boolean init(MKOfflineMapListener mKOfflineMapListener) {
        i.a();
        this.f3058b = r.a();
        r rVar = this.f3058b;
        if (rVar == null) {
            return false;
        }
        rVar.a(new a(this));
        this.f3059c = mKOfflineMapListener;
        return true;
    }

    public boolean pause(int i) {
        return this.f3058b.c(i);
    }

    public boolean remove(int i) {
        return this.f3058b.e(i);
    }

    public ArrayList<MKOLSearchRecord> searchCity(String str) {
        ArrayList<q> arrayListA = this.f3058b.a(str);
        if (arrayListA == null) {
            return null;
        }
        ArrayList<MKOLSearchRecord> arrayList = new ArrayList<>();
        Iterator<q> it = arrayListA.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getSearchRecordFromLocalCityInfo(it.next()));
        }
        return arrayList;
    }

    public boolean start(int i) {
        r rVar = this.f3058b;
        if (rVar == null) {
            return false;
        }
        if (rVar.e() != null) {
            for (u uVar : this.f3058b.e()) {
                if (uVar.f3626a.f3618a == i) {
                    if (uVar.f3626a.j || uVar.f3626a.l == 2 || uVar.f3626a.l == 3 || uVar.f3626a.l == 6) {
                        return this.f3058b.b(i);
                    }
                    return false;
                }
            }
        }
        return this.f3058b.a(i);
    }

    public boolean update(int i) {
        r rVar = this.f3058b;
        if (rVar != null && rVar.e() != null) {
            Iterator<u> it = this.f3058b.e().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                u next = it.next();
                if (next.f3626a.f3618a == i) {
                    if (next.f3626a.j) {
                        return this.f3058b.f(i);
                    }
                }
            }
        }
        return false;
    }
}