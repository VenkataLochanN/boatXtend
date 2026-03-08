package com.ido.ble.f.a.e;

import com.ido.ble.data.manage.database.HealthSport;
import com.ido.ble.data.manage.database.HealthSportDao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/* JADX INFO: loaded from: classes2.dex */
public class n extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static n f4443a;

    private n() {
    }

    private HealthSportDao b() {
        return a().getHealthSportDao();
    }

    public static synchronized n c() {
        if (f4443a == null) {
            f4443a = new n();
        }
        return f4443a;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.ble.f.a.d.j.a
    public HealthSport a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSport> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSportDao.Properties.DId.eq(Long.valueOf(j)), HealthSportDao.Properties.Year.eq(Integer.valueOf(i)), HealthSportDao.Properties.Month.eq(Integer.valueOf(i2)), HealthSportDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        return queryBuilder.unique();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSport> a(long j, int i) {
        QueryBuilder<HealthSport> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSportDao.Properties.DId.eq(Long.valueOf(j)), HealthSportDao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSportDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSport> a(long j, int i, int i2) {
        Date dateB = b(i, i2);
        Date dateA = a(i, i2);
        QueryBuilder<HealthSport> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSportDao.Properties.DId.eq(Long.valueOf(j)), HealthSportDao.Properties.Date.ge(dateB), HealthSportDao.Properties.Date.le(dateA)), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSportDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSport> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthSport> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSportDao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSportDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void b(long j, HealthSport healthSport) {
        if (healthSport == null) {
            return;
        }
        HealthSport healthSportA = a(j, healthSport.getYear(), healthSport.getMonth(), healthSport.getDay());
        if (healthSportA == null) {
            healthSport.setDId(j);
            b().insert(healthSport);
        } else {
            healthSport.setSportDataId(healthSportA.getSportDataId());
            a(j, healthSport);
        }
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSport> b(long j, int i, int i2) {
        QueryBuilder<HealthSport> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSportDao.Properties.DId.eq(Long.valueOf(j)), HealthSportDao.Properties.Year.eq(Integer.valueOf(i)), HealthSportDao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSportDao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSport> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSportDao.Properties.DId.eq(Long.valueOf(j)), HealthSportDao.Properties.Year.eq(Integer.valueOf(i)), HealthSportDao.Properties.Month.eq(Integer.valueOf(i2)), HealthSportDao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* JADX INFO: renamed from: b, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void a(long j, HealthSport healthSport) {
        if (healthSport == null || healthSport.getSportDataId() == null) {
            return;
        }
        healthSport.setDId(j);
        b().update(healthSport);
    }
}