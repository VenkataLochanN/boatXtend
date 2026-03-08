package com.tencent.bugly.crashreport.biz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.ido.life.util.DateUtil;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.n;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f5387a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f5388b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f5389c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f5390d;

    static /* synthetic */ void a(a aVar, UserInfoBean userInfoBean) {
        com.tencent.bugly.crashreport.common.info.a aVarB;
        if (userInfoBean == null || (aVarB = com.tencent.bugly.crashreport.common.info.a.b()) == null) {
            return;
        }
        userInfoBean.j = aVarB.e();
    }

    static /* synthetic */ void a(a aVar, UserInfoBean userInfoBean, boolean z) {
        List<UserInfoBean> listA;
        if (userInfoBean != null) {
            if (!z && userInfoBean.f5380b != 1 && (listA = aVar.a(com.tencent.bugly.crashreport.common.info.a.a(aVar.f5387a).f5417d)) != null && listA.size() >= 20) {
                y.a("[UserInfo] There are too many user info in local: %d", Integer.valueOf(listA.size()));
                return;
            }
            long jA = o.a().a("t_ui", a(userInfoBean), (n) null, true);
            if (jA >= 0) {
                y.c("[Database] insert %s success with ID: %d", "t_ui", Long.valueOf(jA));
                userInfoBean.f5379a = jA;
            }
        }
    }

    public a(Context context, boolean z) {
        this.f5390d = true;
        this.f5387a = context;
        this.f5390d = z;
    }

    public final void a(int i, boolean z, long j) {
        com.tencent.bugly.crashreport.common.strategy.a aVarA = com.tencent.bugly.crashreport.common.strategy.a.a();
        if (aVarA != null && !aVarA.c().f5430f && i != 1 && i != 3) {
            y.e("UserInfo is disable", new Object[0]);
            return;
        }
        if (i == 1 || i == 3) {
            this.f5389c++;
        }
        com.tencent.bugly.crashreport.common.info.a aVarA2 = com.tencent.bugly.crashreport.common.info.a.a(this.f5387a);
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.f5380b = i;
        userInfoBean.f5381c = aVarA2.f5417d;
        userInfoBean.f5382d = aVarA2.g();
        userInfoBean.f5383e = System.currentTimeMillis();
        userInfoBean.f5384f = -1L;
        userInfoBean.n = aVarA2.i;
        userInfoBean.o = i == 1 ? 1 : 0;
        userInfoBean.l = aVarA2.a();
        userInfoBean.m = aVarA2.o;
        userInfoBean.f5385g = aVarA2.p;
        userInfoBean.f5386h = aVarA2.q;
        userInfoBean.i = aVarA2.r;
        userInfoBean.k = aVarA2.s;
        userInfoBean.r = aVarA2.x();
        userInfoBean.s = aVarA2.C();
        userInfoBean.p = aVarA2.D();
        userInfoBean.q = aVarA2.E();
        x.a().a(new RunnableC0135a(userInfoBean, z), 0L);
    }

    public final void a() {
        this.f5388b = ab.b() + DateUtil.DAY;
        x.a().a(new b(), (this.f5388b - System.currentTimeMillis()) + BootloaderScanner.TIMEOUT);
    }

    /* JADX INFO: renamed from: com.tencent.bugly.crashreport.biz.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BUGLY */
    class RunnableC0135a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f5394a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private UserInfoBean f5395b;

        public RunnableC0135a(UserInfoBean userInfoBean, boolean z) {
            this.f5395b = userInfoBean;
            this.f5394a = z;
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                if (this.f5395b != null) {
                    a.a(a.this, this.f5395b);
                    y.c("[UserInfo] Record user info.", new Object[0]);
                    a.a(a.this, this.f5395b, false);
                }
                if (this.f5394a) {
                    a aVar = a.this;
                    x xVarA = x.a();
                    if (xVarA != null) {
                        xVarA.a(aVar.new AnonymousClass2());
                    }
                }
            } catch (Throwable th) {
                if (y.a(th)) {
                    return;
                }
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006a A[Catch: all -> 0x00eb, TryCatch #0 {, blocks: (B:3:0x0001, B:21:0x002b, B:23:0x003e, B:25:0x004f, B:28:0x0064, B:30:0x006a, B:32:0x006f, B:35:0x0076, B:39:0x008e, B:41:0x0094, B:44:0x009d, B:46:0x00a3, B:49:0x00ac, B:51:0x00b6, B:54:0x00bf, B:58:0x00dd, B:61:0x00e2, B:26:0x005e, B:6:0x0009, B:9:0x0010, B:12:0x0017, B:14:0x001d), top: B:67:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0094 A[Catch: all -> 0x00eb, TRY_LEAVE, TryCatch #0 {, blocks: (B:3:0x0001, B:21:0x002b, B:23:0x003e, B:25:0x004f, B:28:0x0064, B:30:0x006a, B:32:0x006f, B:35:0x0076, B:39:0x008e, B:41:0x0094, B:44:0x009d, B:46:0x00a3, B:49:0x00ac, B:51:0x00b6, B:54:0x00bf, B:58:0x00dd, B:61:0x00e2, B:26:0x005e, B:6:0x0009, B:9:0x0010, B:12:0x0017, B:14:0x001d), top: B:67:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x009d A[Catch: all -> 0x00eb, TRY_ENTER, TryCatch #0 {, blocks: (B:3:0x0001, B:21:0x002b, B:23:0x003e, B:25:0x004f, B:28:0x0064, B:30:0x006a, B:32:0x006f, B:35:0x0076, B:39:0x008e, B:41:0x0094, B:44:0x009d, B:46:0x00a3, B:49:0x00ac, B:51:0x00b6, B:54:0x00bf, B:58:0x00dd, B:61:0x00e2, B:26:0x005e, B:6:0x0009, B:9:0x0010, B:12:0x0017, B:14:0x001d), top: B:67:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void c() {
        /*
            Method dump skipped, instruction units count: 238
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.biz.a.c():void");
    }

    private static void a(List<UserInfoBean> list, List<UserInfoBean> list2) {
        int size = list.size() - 20;
        if (size > 0) {
            int i = 0;
            while (i < list.size() - 1) {
                int i2 = i + 1;
                for (int i3 = i2; i3 < list.size(); i3++) {
                    if (list.get(i).f5383e > list.get(i3).f5383e) {
                        UserInfoBean userInfoBean = list.get(i);
                        list.set(i, list.get(i3));
                        list.set(i3, userInfoBean);
                    }
                }
                i = i2;
            }
            for (int i4 = 0; i4 < size; i4++) {
                list2.add(list.get(i4));
            }
        }
    }

    private static void b(List<UserInfoBean> list, List<UserInfoBean> list2) {
        Iterator<UserInfoBean> it = list.iterator();
        while (it.hasNext()) {
            UserInfoBean next = it.next();
            if (next.f5384f != -1) {
                it.remove();
                if (next.f5383e < ab.b()) {
                    list2.add(next);
                }
            }
        }
    }

    private static int a(List<UserInfoBean> list, long j) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        int i = 0;
        for (UserInfoBean userInfoBean : list) {
            if (userInfoBean.f5383e > jCurrentTimeMillis - 600000 && (userInfoBean.f5380b == 1 || userInfoBean.f5380b == 4 || userInfoBean.f5380b == 3)) {
                i++;
            }
        }
        return i;
    }

    public final void b() {
        x xVarA = x.a();
        if (xVarA != null) {
            xVarA.a(new AnonymousClass2());
        }
    }

    /* JADX INFO: renamed from: com.tencent.bugly.crashreport.biz.a$2, reason: invalid class name */
    /* JADX INFO: compiled from: BUGLY */
    final class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                a.this.c();
            } catch (Throwable th) {
                y.a(th);
            }
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis < a.this.f5388b) {
                x.a().a(a.this.new b(), (a.this.f5388b - jCurrentTimeMillis) + BootloaderScanner.TIMEOUT);
            } else {
                a.this.a(3, false, 0L);
                a.this.a();
            }
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private long f5398a;

        public c(long j) {
            this.f5398a = 21600000L;
            this.f5398a = j;
        }

        @Override // java.lang.Runnable
        public final void run() {
            a aVar = a.this;
            x xVarA = x.a();
            if (xVarA != null) {
                xVarA.a(aVar.new AnonymousClass2());
            }
            a aVar2 = a.this;
            long j = this.f5398a;
            x.a().a(aVar2.new c(j), j);
        }
    }

    public final List<UserInfoBean> a(String str) {
        Cursor cursorA;
        String str2;
        try {
            if (ab.a(str)) {
                str2 = null;
            } else {
                str2 = "_pc = '" + str + "'";
            }
            cursorA = o.a().a("t_ui", null, str2, null, null, true);
            if (cursorA == null) {
                return null;
            }
            try {
                StringBuilder sb = new StringBuilder();
                ArrayList arrayList = new ArrayList();
                while (cursorA.moveToNext()) {
                    UserInfoBean userInfoBeanA = a(cursorA);
                    if (userInfoBeanA != null) {
                        arrayList.add(userInfoBeanA);
                    } else {
                        try {
                            long j = cursorA.getLong(cursorA.getColumnIndex("_id"));
                            sb.append(" or _id");
                            sb.append(" = ");
                            sb.append(j);
                        } catch (Throwable unused) {
                            y.d("[Database] unknown id.", new Object[0]);
                        }
                    }
                }
                String string = sb.toString();
                if (string.length() > 0) {
                    y.d("[Database] deleted %s error data %d", "t_ui", Integer.valueOf(o.a().a("t_ui", string.substring(4), (String[]) null, (n) null, true)));
                }
                if (cursorA != null) {
                    cursorA.close();
                }
                return arrayList;
            } catch (Throwable th) {
                th = th;
                try {
                    if (!y.a(th)) {
                        th.printStackTrace();
                    }
                    if (cursorA != null) {
                        cursorA.close();
                    }
                    return null;
                } finally {
                    if (cursorA != null) {
                        cursorA.close();
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursorA = null;
        }
    }

    private static void a(List<UserInfoBean> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size() && i < 50; i++) {
            UserInfoBean userInfoBean = list.get(i);
            sb.append(" or _id");
            sb.append(" = ");
            sb.append(userInfoBean.f5379a);
        }
        String string = sb.toString();
        if (string.length() > 0) {
            string = string.substring(4);
        }
        String str = string;
        sb.setLength(0);
        try {
            y.c("[Database] deleted %s data %d", "t_ui", Integer.valueOf(o.a().a("t_ui", str, (String[]) null, (n) null, true)));
        } catch (Throwable th) {
            if (y.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    private static ContentValues a(UserInfoBean userInfoBean) {
        if (userInfoBean == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (userInfoBean.f5379a > 0) {
                contentValues.put("_id", Long.valueOf(userInfoBean.f5379a));
            }
            contentValues.put("_tm", Long.valueOf(userInfoBean.f5383e));
            contentValues.put("_ut", Long.valueOf(userInfoBean.f5384f));
            contentValues.put("_tp", Integer.valueOf(userInfoBean.f5380b));
            contentValues.put("_pc", userInfoBean.f5381c);
            contentValues.put("_dt", ab.a(userInfoBean));
            return contentValues;
        } catch (Throwable th) {
            if (!y.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static UserInfoBean a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            byte[] blob = cursor.getBlob(cursor.getColumnIndex("_dt"));
            if (blob == null) {
                return null;
            }
            long j = cursor.getLong(cursor.getColumnIndex("_id"));
            UserInfoBean userInfoBean = (UserInfoBean) ab.a(blob, UserInfoBean.CREATOR);
            if (userInfoBean != null) {
                userInfoBean.f5379a = j;
            }
            return userInfoBean;
        } catch (Throwable th) {
            if (!y.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }
}