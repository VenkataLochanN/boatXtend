package com.ido.ble.f.a.e;

import com.ido.ble.data.manage.database.HealthSleep;
import com.ido.ble.data.manage.database.HealthSleepDao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/* JADX INFO: loaded from: classes2.dex */
public class j extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static j f4439a;

    private j() {
    }

    private HealthSleepDao b() {
        return a().getHealthSleepDao();
    }

    public static synchronized j c() {
        if (f4439a == null) {
            f4439a = new j();
        }
        return f4439a;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.ble.f.a.d.j.a
    public HealthSleep a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSleep> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSleepDao.Properties.DId.eq(Long.valueOf(j)), HealthSleepDao.Properties.Year.eq(Integer.valueOf(i)), HealthSleepDao.Properties.Month.eq(Integer.valueOf(i2)), HealthSleepDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        return queryBuilder.unique();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSleep> a(long j, int i) {
        QueryBuilder<HealthSleep> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSleepDao.Properties.DId.eq(Long.valueOf(j)), HealthSleepDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSleep> a(long j, int i, int i2) {
        Date dateB = b(i, i2);
        Date dateA = a(i, i2);
        QueryBuilder<HealthSleep> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSleepDao.Properties.DId.eq(Long.valueOf(j)), HealthSleepDao.Properties.Date.ge(dateB), HealthSleepDao.Properties.Date.le(dateA)), new WhereCondition[0]);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSleep> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthSleep> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSleepDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void b(long j, HealthSleep healthSleep) {
        if (healthSleep == null) {
            return;
        }
        HealthSleep healthSleepA = a(j, healthSleep.getYear(), healthSleep.getMonth(), healthSleep.getDay());
        if (healthSleepA == null) {
            healthSleep.setDId(j);
            b().insert(healthSleep);
        } else {
            healthSleep.setSleepDataId(healthSleepA.getSleepDataId());
            a(j, healthSleep);
        }
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSleep> b(long j, int i, int i2) {
        QueryBuilder<HealthSleep> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSleepDao.Properties.DId.eq(Long.valueOf(j)), HealthSleepDao.Properties.Year.eq(Integer.valueOf(i)), HealthSleepDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSleep> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSleepDao.Properties.DId.eq(Long.valueOf(j)), HealthSleepDao.Properties.Year.eq(Integer.valueOf(i)), HealthSleepDao.Properties.Month.eq(Integer.valueOf(i2)), HealthSleepDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* JADX INFO: renamed from: b, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void a(long j, HealthSleep healthSleep) {
        if (healthSleep == null || healthSleep.getSleepDataId() == null) {
            return;
        }
        healthSleep.setDId(j);
        b().update(healthSleep);
    }
}