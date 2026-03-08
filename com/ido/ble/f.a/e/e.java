package com.ido.ble.f.a.e;

import com.ido.ble.data.manage.database.HealthHeartRate;
import com.ido.ble.data.manage.database.HealthHeartRateDao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/* JADX INFO: loaded from: classes2.dex */
public class e extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static e f4434a;

    private e() {
    }

    private HealthHeartRateDao b() {
        return a().getHealthHeartRateDao();
    }

    public static synchronized e c() {
        if (f4434a == null) {
            f4434a = new e();
        }
        return f4434a;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.ble.f.a.d.j.a
    public HealthHeartRate a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthHeartRate> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateDao.Properties.Year.eq(Integer.valueOf(i)), HealthHeartRateDao.Properties.Month.eq(Integer.valueOf(i2)), HealthHeartRateDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateDao.Properties.Date);
        return queryBuilder.unique();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthHeartRate> a(long j, int i) {
        QueryBuilder<HealthHeartRate> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthHeartRate> a(long j, int i, int i2) {
        Date dateB = b(i, i2);
        Date dateA = a(i, i2);
        QueryBuilder<HealthHeartRate> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateDao.Properties.Date.ge(dateB), HealthHeartRateDao.Properties.Date.le(dateA)), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthHeartRate> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthHeartRate> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void b(long j, HealthHeartRate healthHeartRate) {
        if (healthHeartRate == null) {
            return;
        }
        HealthHeartRate healthHeartRateA = a(j, healthHeartRate.getYear(), healthHeartRate.getMonth(), healthHeartRate.getDay());
        if (healthHeartRateA == null) {
            healthHeartRate.setDId(j);
            b().insert(healthHeartRate);
        } else {
            healthHeartRate.setRateDataId(healthHeartRateA.getRateDataId());
            a(j, healthHeartRate);
        }
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthHeartRate> b(long j, int i, int i2) {
        QueryBuilder<HealthHeartRate> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateDao.Properties.Year.eq(Integer.valueOf(i)), HealthHeartRateDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthHeartRate> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateDao.Properties.Year.eq(Integer.valueOf(i)), HealthHeartRateDao.Properties.Month.eq(Integer.valueOf(i2)), HealthHeartRateDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* JADX INFO: renamed from: b, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void a(long j, HealthHeartRate healthHeartRate) {
        if (healthHeartRate == null || healthHeartRate.getRateDataId() == null) {
            return;
        }
        healthHeartRate.setDId(j);
        b().update(healthHeartRate);
    }
}