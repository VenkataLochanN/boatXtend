package com.ido.ble.f.a.e;

import com.ido.ble.data.manage.database.HealthPressure;
import com.ido.ble.data.manage.database.HealthPressureDao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/* JADX INFO: loaded from: classes2.dex */
public class h extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.j.a<HealthPressure> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static h f4437a;

    private h() {
    }

    private HealthPressureDao b() {
        return a().getHealthPressureDao();
    }

    public static synchronized h c() {
        if (f4437a == null) {
            f4437a = new h();
        }
        return f4437a;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.ble.f.a.d.j.a
    public HealthPressure a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthPressure> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureDao.Properties.Year.eq(Integer.valueOf(i)), HealthPressureDao.Properties.Month.eq(Integer.valueOf(i2)), HealthPressureDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthPressureDao.Properties.Date);
        return queryBuilder.unique();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthPressure> a(long j, int i) {
        QueryBuilder<HealthPressure> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthPressureDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthPressure> a(long j, int i, int i2) {
        Date dateB = b(i, i2);
        Date dateA = a(i, i2);
        QueryBuilder<HealthPressure> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureDao.Properties.Date.ge(dateB), HealthPressureDao.Properties.Date.le(dateA)), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthPressureDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthPressure> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthPressure> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthPressureDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void b(long j, HealthPressure healthPressure) {
        if (healthPressure == null) {
            return;
        }
        HealthPressure healthPressureA = a(j, healthPressure.getYear(), healthPressure.getMonth(), healthPressure.getDay());
        if (healthPressureA == null) {
            healthPressure.setDId(j);
            b().insert(healthPressure);
        } else {
            healthPressure.setId(healthPressureA.getId());
            a(j, healthPressure);
        }
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthPressure> b(long j, int i, int i2) {
        QueryBuilder<HealthPressure> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureDao.Properties.Year.eq(Integer.valueOf(i)), HealthPressureDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthPressureDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthPressure> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthPressureDao.Properties.DId.eq(Long.valueOf(j)), HealthPressureDao.Properties.Year.eq(Integer.valueOf(i)), HealthPressureDao.Properties.Month.eq(Integer.valueOf(i2)), HealthPressureDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* JADX INFO: renamed from: b, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void a(long j, HealthPressure healthPressure) {
        if (healthPressure == null || healthPressure.getId() == null) {
            return;
        }
        healthPressure.setId(Long.valueOf(j));
        b().update(healthPressure);
    }
}