package com.baidu.mapsdkplatform.comapi.b.a;

import com.baidu.mapsdkplatform.comapi.util.h;
import com.ido.common.utils.FileDialDefinedUtil;
import java.io.File;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
class d implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final /* synthetic */ c f3474a;

    d(c cVar) {
        this.f3474a = cVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        File[] fileArrListFiles;
        if (h.a().b() == null) {
            return;
        }
        File file = new File(c.f3469a);
        if (!file.exists() || (fileArrListFiles = file.listFiles()) == null || fileArrListFiles.length == 0) {
            return;
        }
        try {
            Arrays.sort(fileArrListFiles, new e());
        } catch (Exception unused) {
        }
        int length = fileArrListFiles.length;
        if (length > 10) {
            length = 10;
        }
        for (int i = 0; i < length; i++) {
            File file2 = fileArrListFiles[i];
            if (!file2.isDirectory() && file2.exists() && file2.isFile() && file2.getName().contains(c.f3470b) && (file2.getName().endsWith(".txt") || (file2.getName().endsWith(FileDialDefinedUtil.FILE_ZIP) && file2.exists()))) {
                this.f3474a.a(file2);
            }
        }
        if (fileArrListFiles.length > 10) {
            this.f3474a.a(fileArrListFiles);
        }
    }
}