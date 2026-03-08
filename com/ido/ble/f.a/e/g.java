package com.ido.ble.f.a.e;

import com.ido.ble.common.TimeUtil;
import com.ido.ble.data.manage.database.HealthPressureItem;
import com.ido.ble.data.manage.database.HealthPressureItemDao;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/* JADX INFO: loaded from: classes2.dex */
public class g extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.j.b<HealthPressureItem> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static g f4436a;

    private g() {
    }

    private HealthPressureItemDao b() {
        return a().getHealthPressureItemDao();
    }

    public static synchronized g c() {
        if (f4436a == null) {
            f4436a = new g();
        }
        return f4436a;
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthPressureItem> a(long j, int i) {
        QueryBuilder<HealthPressureItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureItemDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureItemDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthPressureItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthPressureItem> a(long j, int i, int i2) {
        return b(j, b(i, i2), a(i, i2));
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthPressureItem> a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthPressureItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureItemDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthPressureItemDao.Properties.Month.eq(Integer.valueOf(i2)), HealthPressureItemDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthPressureItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthPressureItem> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthPressureItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureItemDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthPressureItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        a(j, TimeUtil.getStartDate(i, i2, i3), TimeUtil.getEndDate(i, i2, i3));
    }

    @Override // com.ido.ble.f.a.d.j.b
    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void b(long j, HealthPressureItem healthPressureItem) {
        if (healthPressureItem != null) {
            healthPressureItem.setDId(j);
            b().insert(healthPressureItem);
        }
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, Date date, Date date2) {
        QueryBuilder<HealthPressureItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureItemDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureItemDao.Properties.Date.ge(date), HealthPressureItemDao.Properties.Date.le(date2)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void a(long j, List<HealthPressureItem> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<HealthPressureItem> it = list.iterator();
        while (it.hasNext()) {
            it.next().setDId(j);
        }
        b().insertInTx(list);
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthPressureItem> b(long j, int i, int i2) {
        QueryBuilder<HealthPressureItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureItemDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthPressureItemDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthPressureItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthPressureItem> b(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        return b(j, TimeUtil.getStartDate(i, i2, i3), TimeUtil.getEndDate(i, i2, i3));
    }

    @Override // com.ido.ble.f.a.d.j.b
    public List<HealthPressureItem> b(long j, Date date, Date date2) {
        QueryBuilder<HealthPressureItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureItemDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureItemDao.Properties.Date.ge(date), HealthPressureItemDao.Properties.Date.le(date2)), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthPressureItemDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthPressureItem> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureItemDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureItemDao.Properties.Year.eq(Integer.valueOf(i)), HealthPressureItemDao.Properties.Month.eq(Integer.valueOf(i2)), HealthPressureItemDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.b
    /* JADX INFO: renamed from: b, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void a(long j, HealthPressureItem healthPressureItem) {
        if (healthPressureItem != null) {
            healthPressureItem.setDId(j);
            b().update(healthPressureItem);
        }
    }

    @Override // com.ido.ble.f.a.d.j.b
    public void b(long j, List<HealthPressureItem> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<HealthPressureItem> it = list.iterator();
        while (it.hasNext()) {
            it.next().setDId(j);
        }
        b().updateInTx(list);
    }
}