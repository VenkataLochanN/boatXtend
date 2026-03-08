package com.ido.ble.f.a.e;

import com.ido.ble.data.manage.database.HealthActivity;
import com.ido.ble.data.manage.database.HealthActivityDao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/* JADX INFO: loaded from: classes2.dex */
public class a extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.e<HealthActivity> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static a f4430a;

    private a() {
    }

    private HealthActivityDao b() {
        return a().getHealthActivityDao();
    }

    public static synchronized a c() {
        if (f4430a == null) {
            f4430a = new a();
        }
        return f4430a;
    }

    @Override // com.ido.ble.f.a.d.e
    public List<HealthActivity> a(long j, int i) {
        QueryBuilder<HealthActivity> queryBuilder = b().queryBuilder();
        queryBuilder.where(HealthActivityDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthActivityDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.e
    public List<HealthActivity> a(long j, int i, int i2) {
        Date dateB = b(i, i2);
        Date dateA = a(i, i2);
        QueryBuilder<HealthActivity> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthActivityDao.Properties.DId.eq(Long.valueOf(j)), HealthActivityDao.Properties.Date.ge(dateB), HealthActivityDao.Properties.Date.le(dateA)), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthActivityDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.e
    public List<HealthActivity> a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthActivity> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthActivityDao.Properties.DId.eq(Long.valueOf(j)), HealthActivityDao.Properties.Year.eq(Integer.valueOf(i)), HealthActivityDao.Properties.Month.eq(Integer.valueOf(i2)), HealthActivityDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.e
    public List<HealthActivity> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthActivity> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthActivityDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.e
    public void a(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        QueryBuilder<HealthActivity> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthActivityDao.Properties.DId.eq(Long.valueOf(j)), HealthActivityDao.Properties.Year.eq(Integer.valueOf(i)), HealthActivityDao.Properties.Month.eq(Integer.valueOf(i2)), HealthActivityDao.Properties.Day.eq(Integer.valueOf(i3)), HealthActivityDao.Properties.Hour.eq(Integer.valueOf(i4)), HealthActivityDao.Properties.Minute.eq(Integer.valueOf(i5)), HealthActivityDao.Properties.Second.eq(Integer.valueOf(i6))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.e
    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void b(long j, HealthActivity healthActivity) {
        if (healthActivity == null) {
            return;
        }
        HealthActivity healthActivityB = b(j, healthActivity.getYear(), healthActivity.getMonth(), healthActivity.getDay(), healthActivity.getHour(), healthActivity.getMinute(), healthActivity.getSecond());
        if (healthActivityB == null) {
            healthActivity.setDId(j);
            b().insert(healthActivity);
        } else {
            healthActivity.setActivityId(healthActivityB.getActivityId());
            a(j, healthActivity);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.ble.f.a.d.e
    public HealthActivity b(long j, int i, int i2, int i3, int i4, int i5, int i6) {
        QueryBuilder<HealthActivity> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthActivityDao.Properties.DId.eq(Long.valueOf(j)), HealthActivityDao.Properties.Year.eq(Integer.valueOf(i)), HealthActivityDao.Properties.Month.eq(Integer.valueOf(i2)), HealthActivityDao.Properties.Day.eq(Integer.valueOf(i3)), HealthActivityDao.Properties.Hour.eq(Integer.valueOf(i4)), HealthActivityDao.Properties.Minute.eq(Integer.valueOf(i5)), HealthActivityDao.Properties.Second.eq(Integer.valueOf(i6))), new WhereCondition[0]);
        return queryBuilder.unique();
    }

    @Override // com.ido.ble.f.a.d.e
    public List<HealthActivity> b(long j, int i, int i2) {
        QueryBuilder<HealthActivity> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthActivityDao.Properties.Year.eq(Integer.valueOf(i)), HealthActivityDao.Properties.Month.eq(Integer.valueOf(i2)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthActivityDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.e
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthActivity> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthActivityDao.Properties.DId.eq(Long.valueOf(j)), HealthActivityDao.Properties.Year.eq(Integer.valueOf(i)), HealthActivityDao.Properties.Month.eq(Integer.valueOf(i2)), HealthActivityDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.e
    /* JADX INFO: renamed from: b, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void a(long j, HealthActivity healthActivity) {
        if (healthActivity == null || healthActivity.getActivityId() == null) {
            return;
        }
        healthActivity.setDId(j);
        b().update(healthActivity);
    }
}