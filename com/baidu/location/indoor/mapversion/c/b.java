package com.baidu.location.indoor.mapversion.c;

import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: classes.dex */
class b implements FilenameFilter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ String f2674a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final /* synthetic */ a f2675b;

    b(a aVar, String str) {
        this.f2675b = aVar;
        this.f2674a = str;
    }

    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f2675b.d(this.f2674a));
        sb.append("_");
        return str.startsWith(sb.toString());
    }
}