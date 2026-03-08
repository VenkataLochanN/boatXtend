package com.amap.api.mapcore.util;

import android.app.Activity;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.amap.api.maps.offlinemap.OfflineMapActivity;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapManager;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: SearchListAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class fr extends BaseAdapter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private List<OfflineMapCity> f944a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private OfflineMapManager f945b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Activity f946c;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public fr(List<OfflineMapProvince> list, OfflineMapManager offlineMapManager, OfflineMapActivity offlineMapActivity) {
        this.f945b = offlineMapManager;
        this.f946c = offlineMapActivity;
    }

    public void a(List<OfflineMapCity> list) {
        this.f944a = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.f944a.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.f944a.get(i);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00ee A[Catch: Exception -> 0x00fb, TRY_LEAVE, TryCatch #0 {Exception -> 0x00fb, blocks: (B:2:0x0000, B:4:0x000a, B:6:0x004f, B:19:0x00ab, B:21:0x00af, B:22:0x00ba, B:23:0x00c7, B:24:0x00d4, B:25:0x00e1, B:26:0x00ee, B:5:0x0049), top: B:31:0x0000 }] */
    @Override // android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.view.View getView(int r7, android.view.View r8, android.view.ViewGroup r9) {
        /*
            Method dump skipped, instruction units count: 266
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.fr.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    /* JADX INFO: compiled from: SearchListAdapter.java */
    public final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f950a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f951b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f952c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public ImageView f953d;

        public a() {
        }
    }
}