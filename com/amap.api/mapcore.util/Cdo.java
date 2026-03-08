package com.amap.api.mapcore.util;

import com.autonavi.base.ae.gmap.style.StyleItem;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: renamed from: com.amap.api.mapcore.util.do, reason: invalid class name */
/* JADX INFO: compiled from: StyleParserResult.java */
/* JADX INFO: loaded from: classes.dex */
public class Cdo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Map<Integer, StyleItem> f639a = new ConcurrentHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Object f640b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private StyleItem[] f641c;

    public Map<Integer, StyleItem> a() {
        return this.f639a;
    }

    public StyleItem[] b() {
        Map<Integer, StyleItem> map = this.f639a;
        if (map != null && map.size() != 0) {
            ArrayList arrayList = new ArrayList();
            for (StyleItem styleItem : this.f639a.values()) {
                if (styleItem.isValid()) {
                    arrayList.add(styleItem);
                }
            }
            int size = arrayList.size();
            if (size > 0) {
                this.f641c = (StyleItem[]) arrayList.toArray(new StyleItem[size]);
                return this.f641c;
            }
        }
        return null;
    }

    public StyleItem[] c() {
        return this.f641c;
    }

    public Object d() {
        return this.f640b;
    }
}