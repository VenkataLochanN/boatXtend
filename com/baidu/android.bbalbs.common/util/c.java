package com.baidu.android.bbalbs.common.util;

import com.baidu.android.bbalbs.common.util.b;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
class c implements Comparator<b.a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ b f1980a;

    c(b bVar) {
        this.f1980a = bVar;
    }

    @Override // java.util.Comparator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(b.a aVar, b.a aVar2) {
        int i = aVar2.f1974b - aVar.f1974b;
        if (i == 0) {
            if (aVar.f1976d && aVar2.f1976d) {
                return 0;
            }
            if (aVar.f1976d) {
                return -1;
            }
            if (aVar2.f1976d) {
                return 1;
            }
        }
        return i;
    }
}