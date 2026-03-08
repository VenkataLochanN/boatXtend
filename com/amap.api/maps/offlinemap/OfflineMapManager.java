package com.amap.api.maps.offlinemap;

import android.content.Context;
import android.os.Handler;
import com.amap.api.mapcore.util.az;
import com.amap.api.mapcore.util.ba;
import com.amap.api.mapcore.util.be;
import com.amap.api.mapcore.util.ep;
import com.amap.api.mapcore.util.er;
import com.amap.api.mapcore.util.go;
import com.amap.api.mapcore.util.hn;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapException;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class OfflineMapManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    be f1904a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    ba f1905b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f1906c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private OfflineMapDownloadListener f1907d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private OfflineLoadedListener f1908e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Handler f1909f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private Handler f1910g;

    public interface OfflineLoadedListener {
        void onVerifyComplete();
    }

    public interface OfflineMapDownloadListener {
        void onCheckUpdate(boolean z, String str);

        void onDownload(int i, int i2, String str);

        void onRemove(boolean z, String str, String str2);
    }

    public void restart() {
    }

    public OfflineMapManager(Context context, OfflineMapDownloadListener offlineMapDownloadListener) {
        this.f1907d = offlineMapDownloadListener;
        this.f1906c = context.getApplicationContext();
        this.f1909f = new Handler(this.f1906c.getMainLooper());
        this.f1910g = new Handler(this.f1906c.getMainLooper());
        a(context);
        go.a().a(this.f1906c);
    }

    public OfflineMapManager(Context context, OfflineMapDownloadListener offlineMapDownloadListener, AMap aMap) {
        this.f1907d = offlineMapDownloadListener;
        this.f1906c = context.getApplicationContext();
        this.f1909f = new Handler(this.f1906c.getMainLooper());
        this.f1910g = new Handler(this.f1906c.getMainLooper());
        try {
            a(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(Context context) {
        this.f1906c = context.getApplicationContext();
        ba.f238b = false;
        this.f1905b = ba.a(this.f1906c);
        this.f1905b.a(new ba.a() { // from class: com.amap.api.maps.offlinemap.OfflineMapManager.1
            @Override // com.amap.api.mapcore.util.ba.a
            public void a(final az azVar) {
                if (OfflineMapManager.this.f1907d == null || azVar == null) {
                    return;
                }
                OfflineMapManager.this.f1909f.post(new Runnable() { // from class: com.amap.api.maps.offlinemap.OfflineMapManager.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            OfflineMapManager.this.f1907d.onDownload(azVar.c().b(), azVar.getcompleteCode(), azVar.getCity());
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }

            @Override // com.amap.api.mapcore.util.ba.a
            public void b(final az azVar) {
                if (OfflineMapManager.this.f1907d == null || azVar == null) {
                    return;
                }
                OfflineMapManager.this.f1909f.post(new Runnable() { // from class: com.amap.api.maps.offlinemap.OfflineMapManager.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (azVar.c().equals(azVar.f223g) || azVar.c().equals(azVar.f217a)) {
                                OfflineMapManager.this.f1907d.onCheckUpdate(true, azVar.getCity());
                            } else {
                                OfflineMapManager.this.f1907d.onCheckUpdate(false, azVar.getCity());
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }

            @Override // com.amap.api.mapcore.util.ba.a
            public void c(final az azVar) {
                if (OfflineMapManager.this.f1907d == null || azVar == null) {
                    return;
                }
                OfflineMapManager.this.f1909f.post(new Runnable() { // from class: com.amap.api.maps.offlinemap.OfflineMapManager.1.3
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            if (azVar.c().equals(azVar.f217a)) {
                                OfflineMapManager.this.f1907d.onRemove(true, azVar.getCity(), "");
                            } else {
                                OfflineMapManager.this.f1907d.onRemove(false, azVar.getCity(), "");
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                });
            }

            @Override // com.amap.api.mapcore.util.ba.a
            public void a() {
                if (OfflineMapManager.this.f1908e != null) {
                    OfflineMapManager.this.f1909f.post(new Runnable() { // from class: com.amap.api.maps.offlinemap.OfflineMapManager.1.4
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                OfflineMapManager.this.f1908e.onVerifyComplete();
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
        try {
            this.f1905b.a();
            this.f1904a = this.f1905b.f242f;
            ep.b(context);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void downloadByCityCode(String str) throws AMapException {
        try {
            this.f1905b.e(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void downloadByCityName(String str) throws AMapException {
        try {
            this.f1905b.d(str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void downloadByProvinceName(String str) throws AMapException {
        try {
            a();
            OfflineMapProvince itemByProvinceName = getItemByProvinceName(str);
            if (itemByProvinceName == null) {
                throw new AMapException(AMapException.ERROR_INVALID_PARAMETER);
            }
            Iterator<OfflineMapCity> it = itemByProvinceName.getCityList().iterator();
            while (it.hasNext()) {
                final String city = it.next().getCity();
                this.f1910g.post(new Runnable() { // from class: com.amap.api.maps.offlinemap.OfflineMapManager.2
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            OfflineMapManager.this.f1905b.d(city);
                        } catch (AMapException e2) {
                            hn.c(e2, "OfflineMapManager", "downloadByProvinceName");
                        }
                    }
                });
            }
        } catch (Throwable th) {
            if (th instanceof AMapException) {
                throw th;
            }
            hn.c(th, "OfflineMapManager", "downloadByProvinceName");
        }
    }

    public void remove(String str) {
        try {
            if (this.f1905b.b(str)) {
                this.f1905b.c(str);
                return;
            }
            OfflineMapProvince offlineMapProvinceC = this.f1904a.c(str);
            if (offlineMapProvinceC != null && offlineMapProvinceC.getCityList() != null) {
                Iterator<OfflineMapCity> it = offlineMapProvinceC.getCityList().iterator();
                while (it.hasNext()) {
                    final String city = it.next().getCity();
                    this.f1910g.post(new Runnable() { // from class: com.amap.api.maps.offlinemap.OfflineMapManager.3
                        @Override // java.lang.Runnable
                        public void run() {
                            OfflineMapManager.this.f1905b.c(city);
                        }
                    });
                }
                return;
            }
            if (this.f1907d != null) {
                this.f1907d.onRemove(false, str, "没有该城市");
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public ArrayList<OfflineMapProvince> getOfflineMapProvinceList() {
        return this.f1904a.a();
    }

    public OfflineMapCity getItemByCityCode(String str) {
        return this.f1904a.a(str);
    }

    public OfflineMapCity getItemByCityName(String str) {
        return this.f1904a.b(str);
    }

    public OfflineMapProvince getItemByProvinceName(String str) {
        return this.f1904a.c(str);
    }

    public ArrayList<OfflineMapCity> getOfflineMapCityList() {
        return this.f1904a.b();
    }

    public ArrayList<OfflineMapCity> getDownloadingCityList() {
        return this.f1904a.e();
    }

    public ArrayList<OfflineMapProvince> getDownloadingProvinceList() {
        return this.f1904a.f();
    }

    public ArrayList<OfflineMapCity> getDownloadOfflineMapCityList() {
        return this.f1904a.c();
    }

    public ArrayList<OfflineMapProvince> getDownloadOfflineMapProvinceList() {
        return this.f1904a.d();
    }

    private void a(String str, String str2) throws AMapException {
        this.f1905b.a(str);
    }

    public void updateOfflineCityByCode(String str) throws AMapException {
        OfflineMapCity itemByCityCode = getItemByCityCode(str);
        if (itemByCityCode == null || itemByCityCode.getCity() == null) {
            throw new AMapException(AMapException.ERROR_INVALID_PARAMETER);
        }
        a(itemByCityCode.getCity(), "cityname");
    }

    public void updateOfflineCityByName(String str) throws AMapException {
        a(str, "cityname");
    }

    public void updateOfflineMapProvinceByName(String str) throws AMapException {
        a(str, "cityname");
    }

    private void a() throws AMapException {
        if (!er.d(this.f1906c)) {
            throw new AMapException(AMapException.ERROR_CONNECTION);
        }
    }

    public void stop() {
        this.f1905b.c();
    }

    public void pause() {
        this.f1905b.d();
    }

    public void destroy() {
        try {
            if (this.f1905b != null) {
                this.f1905b.e();
            }
            b();
            if (this.f1909f != null) {
                this.f1909f.removeCallbacksAndMessages(null);
            }
            this.f1909f = null;
            if (this.f1910g != null) {
                this.f1910g.removeCallbacksAndMessages(null);
            }
            this.f1910g = null;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void b() {
        this.f1907d = null;
    }

    public void setOnOfflineLoadedListener(OfflineLoadedListener offlineLoadedListener) {
        this.f1908e = offlineLoadedListener;
    }
}