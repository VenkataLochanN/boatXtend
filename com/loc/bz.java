package com.loc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: WifiCollector.java */
/* JADX INFO: loaded from: classes3.dex */
public final class bz {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private dc f4915b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<dd> f4914a = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ArrayList<dd> f4916c = new ArrayList<>();

    /* JADX WARN: Multi-variable type inference failed */
    private void a(List<dd> list, List<dd> list2) {
        list.clear();
        if (list2 != null) {
            ArrayList arrayList = new ArrayList();
            HashMap map = new HashMap();
            for (int i = 0; i < list2.size(); i++) {
                dd ddVar = list2.get(i);
                map.put(Integer.valueOf(ddVar.f4970c), ddVar);
            }
            arrayList.addAll(map.values());
            Collections.sort(arrayList, new Comparator<dd>() { // from class: com.loc.bz.1
                @Override // java.util.Comparator
                public final /* bridge */ /* synthetic */ int compare(dd ddVar2, dd ddVar3) {
                    return ddVar3.f4970c - ddVar2.f4970c;
                }
            });
            int size = arrayList.size();
            if (size > 40) {
                size = 40;
            }
            for (int i2 = 0; i2 < size; i2++) {
                list.add(arrayList.get(i2));
            }
        }
    }

    final List<dd> a(dc dcVar, List<dd> list, boolean z, long j, long j2) {
        List<dd> list2;
        boolean z2 = false;
        if (z) {
            float f2 = 10.0f;
            if ((j > 0 && j2 - j < ((long) (dcVar.f4966g >= 10.0f ? 2000 : 3500))) && list != null && list.size() > 0) {
                if (this.f4915b != null) {
                    if (dcVar.f4966g > 10.0f) {
                        f2 = 200.0f;
                    } else if (dcVar.f4966g > 2.0f) {
                        f2 = 50.0f;
                    }
                    boolean z3 = dcVar.a(this.f4915b) > ((double) f2);
                    if (z3) {
                        z2 = z3;
                    } else {
                        List<dd> list3 = this.f4914a;
                        if (list != null && list3 != null) {
                            int size = list.size();
                            int size2 = list3.size();
                            int i = size + size2;
                            if (size > size2) {
                                list2 = list3;
                                list3 = list;
                            } else {
                                list2 = list;
                            }
                            HashMap map = new HashMap(list3.size());
                            Iterator<dd> it = list3.iterator();
                            while (it.hasNext()) {
                                map.put(Long.valueOf(it.next().f4968a), 1);
                            }
                            Iterator<dd> it2 = list2.iterator();
                            int i2 = 0;
                            while (it2.hasNext()) {
                                if (((Integer) map.get(Long.valueOf(it2.next().f4968a))) != null) {
                                    i2++;
                                }
                            }
                            if (((double) i2) * 2.0d >= ((double) i) * 0.5d) {
                                z2 = true;
                            }
                        }
                        z2 = !z2;
                    }
                } else {
                    z2 = true;
                }
            }
        }
        if (!z2) {
            return null;
        }
        a(this.f4916c, list);
        this.f4914a.clear();
        this.f4914a.addAll(list);
        this.f4915b = dcVar;
        return this.f4916c;
    }
}