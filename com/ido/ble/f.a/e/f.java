package com.ido.ble.f.a.e;

import com.ido.ble.data.manage.database.HealthHeartRateSecond;
import com.ido.ble.data.manage.database.HealthHeartRateSecondDao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/* JADX INFO: loaded from: classes2.dex */
public class f extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.j.a<HealthHeartRateSecond> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static f f4435a;

    private f() {
    }

    private HealthHeartRateSecondDao b() {
        return a().getHealthHeartRateSecondDao();
    }

    public static synchronized f c() {
        if (f4435a == null) {
            f4435a = new f();
        }
        return f4435a;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.ble.f.a.d.j.a
    public HealthHeartRateSecond a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthHeartRateSecond> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateSecondDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateSecondDao.Properties.Year.eq(Integer.valueOf(i)), HealthHeartRateSecondDao.Properties.Month.eq(Integer.valueOf(i2)), HealthHeartRateSecondDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateSecondDao.Properties.Date);
        return queryBuilder.unique();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthHeartRateSecond> a(long j, int i) {
        QueryBuilder<HealthHeartRateSecond> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateSecondDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateSecondDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateSecondDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthHeartRateSecond> a(long j, int i, int i2) {
        Date dateB = b(i, i2);
        Date dateA = a(i, i2);
        QueryBuilder<HealthHeartRateSecond> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateSecondDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateSecondDao.Properties.Date.ge(dateB), HealthHeartRateSecondDao.Properties.Date.le(dateA)), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateSecondDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthHeartRateSecond> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthHeartRateSecond> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateSecondDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateSecondDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void b(long j, HealthHeartRateSecond healthHeartRateSecond) {
        if (healthHeartRateSecond == null) {
            return;
        }
        HealthHeartRateSecond healthHeartRateSecondA = a(j, healthHeartRateSecond.getYear(), healthHeartRateSecond.getMonth(), healthHeartRateSecond.getDay());
        if (healthHeartRateSecondA == null) {
            healthHeartRateSecond.setDId(j);
            b().insert(healthHeartRateSecond);
        } else {
            healthHeartRateSecond.setDataId(healthHeartRateSecondA.getDataId());
            a(j, healthHeartRateSecond);
        }
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthHeartRateSecond> b(long j, int i, int i2) {
        QueryBuilder<HealthHeartRateSecond> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateSecondDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateSecondDao.Properties.Year.eq(Integer.valueOf(i)), HealthHeartRateSecondDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthHeartRateSecondDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthHeartRateSecond> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthHeartRateSecondDao.Properties.DId.eq(Long.valueOf(j)), HealthHeartRateSecondDao.Properties.Year.eq(Integer.valueOf(i)), HealthHeartRateSecondDao.Properties.Month.eq(Integer.valueOf(i2)), HealthHeartRateSecondDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* JADX INFO: renamed from: b, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void a(long j, HealthHeartRateSecond healthHeartRateSecond) {
        if (healthHeartRateSecond == null || healthHeartRateSecond.getDataId() == null) {
            return;
        }
        healthHeartRateSecond.setDId(j);
        b().update(healthHeartRateSecond);
    }
}