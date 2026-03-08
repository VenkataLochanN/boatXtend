package com.ido.ble.event.stat.one.faildata;

import com.ido.ble.data.manage.database.DaoSession;
import com.ido.ble.event.stat.one.faildata.FailLogInfoDao;
import java.util.List;
import org.greenrobot.greendao.query.WhereCondition;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static a f4403b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DaoSession f4404a;

    private a() {
    }

    public static synchronized a b() {
        if (f4403b == null) {
            f4403b = new a();
            f4403b.c();
        }
        return f4403b;
    }

    private void c() {
        this.f4404a = com.ido.ble.f.a.b.d().b();
    }

    public List<c> a() {
        return this.f4404a.getFailLogInfoDao().loadAll();
    }

    public synchronized void a(c cVar) {
        this.f4404a.getFailLogInfoDao().insertOrReplace(cVar);
    }

    public synchronized void a(String str) {
        this.f4404a.getFailLogInfoDao().queryBuilder().where(FailLogInfoDao.Properties.f4402c.eq(str), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }
}