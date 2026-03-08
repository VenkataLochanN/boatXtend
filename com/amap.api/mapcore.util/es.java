package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.amap.api.mapcore.util.dc;
import com.amap.api.maps.model.Tile;
import com.amap.api.maps.model.TileProvider;

/* JADX INFO: compiled from: AbstractImageFetcher.java */
/* JADX INFO: loaded from: classes.dex */
public class es extends et {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TileProvider f765e;

    public es(Context context, int i, int i2) {
        super(context, i, i2);
        this.f765e = null;
        a(context);
    }

    private void a(Context context) {
        b(context);
    }

    public void a(TileProvider tileProvider) {
        this.f765e = tileProvider;
    }

    private void b(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
            return;
        }
        activeNetworkInfo.isConnectedOrConnecting();
    }

    private Bitmap c(dc.a aVar) {
        try {
            Tile tile = this.f765e.getTile(aVar.f547a, aVar.f548b, aVar.f549c);
            if (tile == null || tile == TileProvider.NO_TILE) {
                return null;
            }
            return BitmapFactory.decodeByteArray(tile.data, 0, tile.data.length);
        } catch (Throwable unused) {
            return null;
        }
    }

    @Override // com.amap.api.mapcore.util.et, com.amap.api.mapcore.util.eu
    protected Bitmap a(Object obj) {
        return c((dc.a) obj);
    }
}