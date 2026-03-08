package com.ido.ble.f.a.e;

import com.ido.ble.data.manage.database.HealthSpO2;
import com.ido.ble.data.manage.database.HealthSpO2Dao;
import java.util.Date;
import java.util.List;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/* JADX INFO: loaded from: classes2.dex */
public class l extends com.ido.ble.f.a.e.p.a implements com.ido.ble.f.a.d.j.a<HealthSpO2> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static l f4441a;

    private l() {
    }

    private HealthSpO2Dao b() {
        return a().getHealthSpO2Dao();
    }

    public static synchronized l c() {
        if (f4441a == null) {
            f4441a = new l();
        }
        return f4441a;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ido.ble.f.a.d.j.a
    public HealthSpO2 a(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSpO2> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSpO2Dao.Properties.DId.eq(Long.valueOf(j)), HealthSpO2Dao.Properties.Year.eq(Integer.valueOf(i)), HealthSpO2Dao.Properties.Month.eq(Integer.valueOf(i2)), HealthSpO2Dao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSpO2Dao.Properties.Date);
        return queryBuilder.unique();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSpO2> a(long j, int i) {
        QueryBuilder<HealthSpO2> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSpO2Dao.Properties.DId.eq(Long.valueOf(j)), HealthSpO2Dao.Properties.Year.eq(Integer.valueOf(i)), new WhereCondition[0]), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSpO2Dao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSpO2> a(long j, int i, int i2) {
        Date dateB = b(i, i2);
        Date dateA = a(i, i2);
        QueryBuilder<HealthSpO2> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSpO2Dao.Properties.DId.eq(Long.valueOf(j)), HealthSpO2Dao.Properties.Date.ge(dateB), HealthSpO2Dao.Properties.Date.le(dateA)), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSpO2Dao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSpO2> a(long j, WhereCondition whereCondition, WhereCondition... whereConditionArr) {
        QueryBuilder<HealthSpO2> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSpO2Dao.Properties.DId.eq(Long.valueOf(j)), whereCondition, whereConditionArr), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSpO2Dao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* JADX INFO: renamed from: a, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void b(long j, HealthSpO2 healthSpO2) {
        if (healthSpO2 == null) {
            return;
        }
        HealthSpO2 healthSpO2A = a(j, healthSpO2.getYear(), healthSpO2.getMonth(), healthSpO2.getDay());
        if (healthSpO2A == null) {
            healthSpO2.setDId(j);
            b().insert(healthSpO2);
        } else {
            healthSpO2.setId(healthSpO2A.getId());
            a(j, healthSpO2);
        }
    }

    @Override // com.ido.ble.f.a.d.j.a
    public List<HealthSpO2> b(long j, int i, int i2) {
        QueryBuilder<HealthSpO2> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSpO2Dao.Properties.DId.eq(Long.valueOf(j)), HealthSpO2Dao.Properties.Year.eq(Integer.valueOf(i)), HealthSpO2Dao.Properties.Month.eq(Integer.valueOf(i2))), new WhereCondition[0]);
        queryBuilder.orderDesc(HealthSpO2Dao.Properties.Date);
        return queryBuilder.list();
    }

    @Override // com.ido.ble.f.a.d.j.a
    public void b(long j, int i, int i2, int i3) {
        QueryBuilder<HealthSpO2> queryBuilder = b().queryBuilder();
        queryBuilder.where(queryBuilder.and(HealthSpO2Dao.Properties.DId.eq(Long.valueOf(j)), HealthSpO2Dao.Properties.Year.eq(Integer.valueOf(i)), HealthSpO2Dao.Properties.Month.eq(Integer.valueOf(i2)), HealthSpO2Dao.Properties.Day.eq(Integer.valueOf(i3))), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    @Override // com.ido.ble.f.a.d.j.a
    /* JADX INFO: renamed from: b, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
    public void a(long j, HealthSpO2 healthSpO2) {
        if (healthSpO2 == null || healthSpO2.getId() == null) {
            return;
        }
        healthSpO2.setId(Long.valueOf(j));
        b().update(healthSpO2);
    }
}