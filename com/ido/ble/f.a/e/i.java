package com.ido.ble.f.a.e;

import com.ido.ble.common.TimeUtil;
import com.ido.ble.data.manage.database.HealthSleepItem;
import com.ido.ble.data.manage.database.HealthSleepItemDao;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/* JADX INFO: loaded from: classes2.dex */
public class i extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static i f4438a;

    private i() {
    }

    private HealthSleepItemDao b() {
        return a().getHealthSleepItemDao();
    }

    public static synchronized i c() {
        if (f4438a == null) {
            f4438a = new i();
        }
        return f4438a;
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSleepItem> a(long j, int i) {
        QueryBuilder<HealthSleepItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSleepItemDao.Properties.DId.eq(Long.valueOf(j)), HealthSleepItemDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSleepItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSleepItem> a(long j, int i, int i2) {
        return b(j, b(i, i2), a(i, i2));
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSleepItem> a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSleepItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSleepItemDao.Properties.DId.eq(Long.valueOf(j)), HealthSleepItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthSleepItemDao.Properties.Month.eq(Integer.valueOf(i2)), HealthSleepItemDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSleepItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSleepItem> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthSleepItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSleepItemDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSleepItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        a(j, TimeUtil.getStartDate(i, i2, i3), TimeUtil.getEndDate(i, i2, i3));
    }

    @Override // com.ido.ble.f.a.d.j.b
    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void b(long j, HealthSleepItem healthSleepItem) {
        if (healthSleepItem != null) {
            healthSleepItem.setDId(j);
            b().insert(healthSleepItem);
        }
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, Date date, Date date2) {
        QueryBuilder<HealthSleepItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSleepItemDao.Properties.DId.eq(Long.valueOf(j)), HealthSleepItemDao.Properties.Date.ge(date), HealthSleepItemDao.Properties.Date.le(date2)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, List<HealthSleepItem> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<HealthSleepItem> it = list.iterator();
        while (it.hasNext()) {
            it.next().setDId(j);
        }
        b().insertInTx(list);
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSleepItem> b(long j, int i, int i2) {
        QueryBuilder<HealthSleepItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSleepItemDao.Properties.DId.eq(Long.valueOf(j)), HealthSleepItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthSleepItemDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSleepItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSleepItem> b(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        return b(j, TimeUtil.getStartDate(i, i2, i3), TimeUtil.getEndDate(i, i2, i3));
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthSleepItem> b(long j, Date date, Date date2) {
        QueryBuilder<HealthSleepItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSleepItemDao.Properties.DId.eq(Long.valueOf(j)), HealthSleepItemDao.Properties.Date.ge(date), HealthSleepItemDao.Properties.Date.le(date2)), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSleepItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSleepItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSleepItemDao.Properties.DId.eq(Long.valueOf(j)), HealthSleepItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthSleepItemDao.Properties.Month.eq(Integer.valueOf(i2)), HealthSleepItemDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.b
    /* JADX INFO: renamed from: b, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void a(long j, HealthSleepItem healthSleepItem) {
        if (healthSleepItem != null) {
            healthSleepItem.setDId(j);
            b().update(healthSleepItem);
        }
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void b(long j, List<HealthSleepItem> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<HealthSleepItem> it = list.iterator();
        while (it.hasNext()) {
            it.next().setDId(j);
        }
        b().updateInTx(list);
    }
}