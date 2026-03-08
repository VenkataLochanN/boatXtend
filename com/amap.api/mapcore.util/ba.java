package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;

/* JADX INFO: compiled from: OfflineDownloadManager.java */
/* JADX INFO: loaded from: classes.dex */
public class ba {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f237a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f238b = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static String f239d = "";
    private static volatile ba k;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public be f242f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    bg f243g;
    private Context i;
    private a l;
    private bj m;
    private bp n;
    private boolean j = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    List<az> f240c = new Vector();
    private ExecutorService o = null;
    private ExecutorService p = null;
    private ExecutorService q = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    b f241e = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    bd f244h = null;
    private boolean r = true;

    /* JADX INFO: compiled from: OfflineDownloadManager.java */
    public interface a {
        void a();

        void a(az azVar);

        void b(az azVar);

        void c(az azVar);
    }

    private ba(Context context) {
        this.i = context;
    }

    public static ba a(Context context) {
        if (k == null) {
            synchronized (ba.class) {
                if (k == null && !f238b) {
                    k = new ba(context.getApplicationContext());
                }
            }
        }
        return k;
    }

    public void a() {
        this.n = bp.a(this.i.getApplicationContext());
        h();
        this.f241e = new b(this.i.getMainLooper());
        this.f242f = new be(this.i, this.f241e);
        this.m = bj.a(1);
        g(er.c(this.i));
        try {
            i();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        synchronized (this.f240c) {
            Iterator<OfflineMapProvince> it = this.f242f.a().iterator();
            while (it.hasNext()) {
                for (OfflineMapCity offlineMapCity : it.next().getCityList()) {
                    if (offlineMapCity != null) {
                        this.f240c.add(new az(this.i, offlineMapCity));
                    }
                }
            }
        }
        this.f244h = new bd(this.i);
        this.f244h.start();
    }

    private void h() {
        try {
            bk bkVarA = this.n.a("000001");
            if (bkVarA != null) {
                this.n.c("000001");
                bkVarA.c("100000");
                this.n.a(bkVarA);
            }
        } catch (Throwable th) {
            hn.c(th, "OfflineDownloadManager", "changeBadCase");
        }
    }

    private void i() {
        String strC;
        if ("".equals(er.c(this.i))) {
            return;
        }
        File file = new File(er.c(this.i) + "offlinemapv4.png");
        if (!file.exists()) {
            strC = bx.a(this.i, "offlinemapv4.png");
        } else {
            strC = bx.c(file);
        }
        if (strC != null) {
            try {
                h(strC);
            } catch (JSONException e2) {
                if (file.exists()) {
                    file.delete();
                }
                hn.c(e2, "MapDownloadManager", "paseJson io");
                e2.printStackTrace();
            }
        }
    }

    private void h(String str) throws JSONException {
        be beVar;
        List<OfflineMapProvince> listA = bx.a(str, this.i.getApplicationContext());
        if (listA == null || listA.size() == 0 || (beVar = this.f242f) == null) {
            return;
        }
        beVar.a(listA);
    }

    private void j() {
        for (bk bkVar : this.n.a()) {
            if (bkVar != null && bkVar.d() != null && bkVar.f().length() >= 1) {
                if (bkVar.l != 4 && bkVar.l != 7 && bkVar.l >= 0) {
                    bkVar.l = 3;
                }
                az azVarI = i(bkVar.d());
                if (azVarI != null) {
                    String strE = bkVar.e();
                    if (strE != null && a(f239d, strE)) {
                        azVarI.a(7);
                    } else {
                        azVarI.a(bkVar.l);
                        azVarI.setCompleteCode(bkVar.h());
                    }
                    if (bkVar.e().length() > 0) {
                        azVarI.setVersion(bkVar.e());
                    }
                    List<String> listB = this.n.b(bkVar.f());
                    StringBuffer stringBuffer = new StringBuffer();
                    Iterator<String> it = listB.iterator();
                    while (it.hasNext()) {
                        stringBuffer.append(it.next());
                        stringBuffer.append(";");
                    }
                    azVarI.a(stringBuffer.toString());
                    be beVar = this.f242f;
                    if (beVar != null) {
                        beVar.a(azVarI);
                    }
                }
            }
        }
    }

    public void a(ArrayList<bk> arrayList) {
        j();
        a aVar = this.l;
        if (aVar != null) {
            try {
                aVar.a();
            } catch (Throwable th) {
                hn.c(th, "OfflineDownloadManager", "verifyCallBack");
            }
        }
    }

    public void a(final String str) {
        try {
            if (str == null) {
                if (this.l != null) {
                    this.l.b(null);
                }
            } else {
                if (this.o == null) {
                    this.o = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ee("AMapOfflineCheckUpdate"), new ThreadPoolExecutor.AbortPolicy());
                }
                this.o.execute(new Runnable() { // from class: com.amap.api.mapcore.util.ba.1
                    @Override // java.lang.Runnable
                    public void run() {
                        az azVarI = ba.this.i(str);
                        if (azVarI != null) {
                            try {
                                if (!azVarI.c().equals(azVarI.f219c) && !azVarI.c().equals(azVarI.f221e)) {
                                    String pinyin = azVarI.getPinyin();
                                    if (pinyin.length() > 0) {
                                        String strD = ba.this.n.d(pinyin);
                                        if (strD == null) {
                                            strD = azVarI.getVersion();
                                        }
                                        if (ba.f239d.length() > 0 && strD != null && ba.this.a(ba.f239d, strD)) {
                                            azVarI.j();
                                        }
                                    }
                                }
                                if (ba.this.l != null) {
                                    synchronized (ba.this) {
                                        try {
                                            ba.this.l.b(azVarI);
                                        } finally {
                                        }
                                    }
                                    return;
                                }
                                return;
                            } catch (Exception unused) {
                                if (ba.this.l != null) {
                                    synchronized (ba.this) {
                                        try {
                                            ba.this.l.b(azVarI);
                                        } finally {
                                            return;
                                        }
                                        return;
                                    }
                                }
                                return;
                            } catch (Throwable th) {
                                if (ba.this.l != null) {
                                    synchronized (ba.this) {
                                        try {
                                            ba.this.l.b(azVarI);
                                        } finally {
                                            throw th;
                                        }
                                    }
                                }
                                throw th;
                            }
                        }
                        ba.this.k();
                        bb bbVarC = new bc(ba.this.i, ba.f239d).c();
                        if (ba.this.l != null) {
                            if (bbVarC == null) {
                                if (ba.this.l != null) {
                                    synchronized (ba.this) {
                                        try {
                                            ba.this.l.b(azVarI);
                                        } finally {
                                        }
                                    }
                                    return;
                                }
                                return;
                            }
                            if (bbVarC.a()) {
                                ba.this.b();
                            }
                        }
                        if (ba.this.l != null) {
                            synchronized (ba.this) {
                                try {
                                    ba.this.l.b(azVarI);
                                } finally {
                                }
                            }
                        }
                    }
                });
            }
        } catch (Throwable th) {
            hn.c(th, "OfflineDownloadManager", "checkUpdate");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() throws AMapException {
        if (!er.d(this.i)) {
            throw new AMapException(AMapException.ERROR_CONNECTION);
        }
    }

    protected void b() throws AMapException {
        if (this.f242f == null) {
            return;
        }
        bh bhVar = new bh(this.i, "");
        bhVar.a(this.i);
        List<OfflineMapProvince> listC = bhVar.c();
        if (this.f240c != null) {
            this.f242f.a(listC);
        }
        List<az> list = this.f240c;
        if (list != null) {
            synchronized (list) {
                Iterator<OfflineMapProvince> it = this.f242f.a().iterator();
                while (it.hasNext()) {
                    for (OfflineMapCity offlineMapCity : it.next().getCityList()) {
                        for (az azVar : this.f240c) {
                            if (offlineMapCity.getPinyin().equals(azVar.getPinyin())) {
                                String version = azVar.getVersion();
                                if (azVar.getState() == 4 && f239d.length() > 0 && a(f239d, version)) {
                                    azVar.j();
                                    azVar.setUrl(offlineMapCity.getUrl());
                                    azVar.t();
                                } else {
                                    azVar.setCity(offlineMapCity.getCity());
                                    azVar.setUrl(offlineMapCity.getUrl());
                                    azVar.t();
                                    azVar.setAdcode(offlineMapCity.getAdcode());
                                    azVar.setVersion(offlineMapCity.getVersion());
                                    azVar.setSize(offlineMapCity.getSize());
                                    azVar.setCode(offlineMapCity.getCode());
                                    azVar.setJianpin(offlineMapCity.getJianpin());
                                    azVar.setPinyin(offlineMapCity.getPinyin());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str, String str2) {
        for (int i = 0; i < str2.length(); i++) {
            try {
                if (str.charAt(i) > str2.charAt(i)) {
                    return true;
                }
                if (str.charAt(i) < str2.charAt(i)) {
                    return false;
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    public boolean b(String str) {
        return i(str) != null;
    }

    public void c(String str) {
        az azVarI = i(str);
        if (azVarI == null) {
            a aVar = this.l;
            if (aVar != null) {
                try {
                    aVar.c(azVarI);
                    return;
                } catch (Throwable th) {
                    hn.c(th, "OfflineDownloadManager", "remove");
                    return;
                }
            }
            return;
        }
        d(azVarI);
        a(azVarI, true);
    }

    public void a(az azVar) {
        a(azVar, false);
    }

    private void a(final az azVar, final boolean z) {
        if (this.f243g == null) {
            this.f243g = new bg(this.i);
        }
        if (this.p == null) {
            this.p = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ee("AMapOfflineRemove"), new ThreadPoolExecutor.AbortPolicy());
        }
        try {
            this.p.execute(new Runnable() { // from class: com.amap.api.mapcore.util.ba.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (azVar.c().equals(azVar.f217a)) {
                            if (ba.this.l != null) {
                                ba.this.l.c(azVar);
                                return;
                            }
                            return;
                        }
                        if (azVar.getState() != 7 && azVar.getState() != -1) {
                            ba.this.f243g.a(azVar);
                            if (ba.this.l != null) {
                                ba.this.l.c(azVar);
                                return;
                            }
                            return;
                        }
                        ba.this.f243g.a(azVar);
                        if (!z || ba.this.l == null) {
                            return;
                        }
                        ba.this.l.c(azVar);
                    } catch (Throwable th) {
                        hn.c(th, "requestDelete", "removeExcecRunnable");
                    }
                }
            });
        } catch (Throwable th) {
            hn.c(th, "requestDelete", "removeExcecRunnable");
        }
    }

    public void b(az azVar) {
        try {
            if (this.m != null) {
                this.m.a(azVar, this.i, null);
            }
        } catch (gh e2) {
            e2.printStackTrace();
        }
    }

    public void c(az azVar) {
        be beVar = this.f242f;
        if (beVar != null) {
            beVar.a(azVar);
        }
        b bVar = this.f241e;
        if (bVar != null) {
            Message messageObtainMessage = bVar.obtainMessage();
            messageObtainMessage.obj = azVar;
            this.f241e.sendMessage(messageObtainMessage);
        }
    }

    public void c() {
        synchronized (this.f240c) {
            for (az azVar : this.f240c) {
                if (azVar.c().equals(azVar.f219c) || azVar.c().equals(azVar.f218b)) {
                    d(azVar);
                    azVar.g();
                }
            }
        }
    }

    public void d() {
        synchronized (this.f240c) {
            Iterator<az> it = this.f240c.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                az next = it.next();
                if (next.c().equals(next.f219c)) {
                    next.g();
                    break;
                }
            }
        }
    }

    public void e() {
        ExecutorService executorService = this.o;
        if (executorService != null && !executorService.isShutdown()) {
            this.o.shutdownNow();
        }
        ExecutorService executorService2 = this.q;
        if (executorService2 != null && !executorService2.isShutdown()) {
            this.q.shutdownNow();
        }
        bd bdVar = this.f244h;
        if (bdVar != null) {
            if (bdVar.isAlive()) {
                this.f244h.interrupt();
            }
            this.f244h = null;
        }
        b bVar = this.f241e;
        if (bVar != null) {
            bVar.removeCallbacksAndMessages(null);
            this.f241e = null;
        }
        bj bjVar = this.m;
        if (bjVar != null) {
            bjVar.b();
        }
        be beVar = this.f242f;
        if (beVar != null) {
            beVar.g();
        }
        f();
        this.j = true;
        g();
    }

    public static void f() {
        k = null;
        f238b = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public az i(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        synchronized (this.f240c) {
            for (az azVar : this.f240c) {
                if (str.equals(azVar.getCity()) || str.equals(azVar.getPinyin())) {
                    return azVar;
                }
            }
            return null;
        }
    }

    private az j(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        synchronized (this.f240c) {
            for (az azVar : this.f240c) {
                if (str.equals(azVar.getCode())) {
                    return azVar;
                }
            }
            return null;
        }
    }

    public void d(String str) throws AMapException {
        az azVarI = i(str);
        if (str == null || str.length() < 1 || azVarI == null) {
            throw new AMapException(AMapException.ERROR_INVALID_PARAMETER);
        }
        f(azVarI);
    }

    public void e(String str) throws AMapException {
        az azVarJ = j(str);
        if (azVarJ != null) {
            f(azVarJ);
            return;
        }
        throw new AMapException(AMapException.ERROR_INVALID_PARAMETER);
    }

    private void f(final az azVar) throws AMapException {
        k();
        if (azVar == null) {
            throw new AMapException(AMapException.ERROR_INVALID_PARAMETER);
        }
        if (this.q == null) {
            this.q = new ThreadPoolExecutor(1, 2, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ee("AMapOfflineDownload"), new ThreadPoolExecutor.AbortPolicy());
        }
        try {
            this.q.execute(new Runnable() { // from class: com.amap.api.mapcore.util.ba.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (ba.this.j) {
                            ba.this.k();
                            bb bbVarC = new bc(ba.this.i, ba.f239d).c();
                            if (bbVarC != null) {
                                ba.this.j = false;
                                if (bbVarC.a()) {
                                    ba.this.b();
                                }
                            }
                        }
                        azVar.setVersion(ba.f239d);
                        azVar.f();
                    } catch (AMapException e2) {
                        e2.printStackTrace();
                    } catch (Throwable th) {
                        hn.c(th, "OfflineDownloadManager", "startDownloadRunnable");
                    }
                }
            });
        } catch (Throwable th) {
            hn.c(th, "startDownload", "downloadExcecRunnable");
        }
    }

    public void d(az azVar) {
        bj bjVar = this.m;
        if (bjVar != null) {
            bjVar.a(azVar);
        }
    }

    public void e(az azVar) {
        bj bjVar = this.m;
        if (bjVar != null) {
            bjVar.b(azVar);
        }
    }

    public void a(a aVar) {
        this.l = aVar;
    }

    public void g() {
        synchronized (this) {
            this.l = null;
        }
    }

    /* JADX INFO: compiled from: OfflineDownloadManager.java */
    class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                message.getData();
                Object obj = message.obj;
                if (obj instanceof az) {
                    az azVar = (az) obj;
                    bx.a("OfflineMapHandler handleMessage CitObj  name: " + azVar.getCity() + " complete: " + azVar.getcompleteCode() + " status: " + azVar.getState());
                    if (ba.this.l != null) {
                        ba.this.l.a(azVar);
                    }
                } else {
                    bx.a("Do not callback by CityObject! ");
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public String f(String str) {
        az azVarI;
        return (str == null || (azVarI = i(str)) == null) ? "" : azVarI.getAdcode();
    }

    public static void g(String str) {
        f237a = str;
    }
}