package com.ido.ble.gps.database.b;

import com.ido.ble.gps.database.HealthGpsItem;
import com.ido.ble.gps.database.HealthGpsItemDao;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/* JADX INFO: loaded from: classes2.dex */
public class b extends com.ido.ble.f.a.e.p.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static b f4604a;

    private b() {
    }

    private HealthGpsItemDao b() {
        return a().getHealthGpsItemDao();
    }

    public static b c() {
        if (f4604a == null) {
            f4604a = new b();
        }
        return f4604a;
    }

    public List<HealthGpsItem> a(long j, long j2) {
        QueryBuilder<HealthGpsItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthGpsItemDao.Properties.DId.eq(Long.valueOf(j)), HealthGpsItemDao.Properties.Date.eq(Long.valueOf(j2)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthGpsItemDao.Properties.Date);
        return queryBuilder.list();
    }

    public List<HealthGpsItem> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthGpsItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthGpsItemDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthGpsItemDao.Properties.Date);
        return queryBuilder.list();
    }

    public void a(long j, HealthGpsItem healthGpsItem) {
        if (healthGpsItem == null) {
            return;
        }
        healthGpsItem.setDId(j);
        b().insert(healthGpsItem);
    }

    public void a(long j, List<HealthGpsItem> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<HealthGpsItem> it = list.iterator();
        while (it.hasNext()) {
            it.next().setDId(j);
        }
        b().insertInTx(list);
    }
}