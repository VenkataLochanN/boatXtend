package com.ido.ble.data.manage.database;

import com.ido.ble.event.stat.one.faildata.FailLogInfoDao;
import com.ido.ble.event.stat.one.faildata.c;
import com.ido.ble.gps.database.HealthGps;
import com.ido.ble.gps.database.HealthGpsDao;
import com.ido.ble.gps.database.HealthGpsItem;
import com.ido.ble.gps.database.HealthGpsItemDao;
import java.util.Map;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class DaoSession extends AbstractDaoSession {
    private final FailLogInfoDao failLogInfoDao;
    private final DaoConfig failLogInfoDaoConfig;
    private final HealthActivityDao healthActivityDao;
    private final DaoConfig healthActivityDaoConfig;
    private final HealthBloodPressedDao healthBloodPressedDao;
    private final DaoConfig healthBloodPressedDaoConfig;
    private final HealthBloodPressedItemDao healthBloodPressedItemDao;
    private final DaoConfig healthBloodPressedItemDaoConfig;
    private final HealthGpsDao healthGpsDao;
    private final DaoConfig healthGpsDaoConfig;
    private final HealthGpsItemDao healthGpsItemDao;
    private final DaoConfig healthGpsItemDaoConfig;
    private final HealthHeartRateDao healthHeartRateDao;
    private final DaoConfig healthHeartRateDaoConfig;
    private final HealthHeartRateItemDao healthHeartRateItemDao;
    private final DaoConfig healthHeartRateItemDaoConfig;
    private final HealthHeartRateSecondDao healthHeartRateSecondDao;
    private final DaoConfig healthHeartRateSecondDaoConfig;
    private final HealthPressureDao healthPressureDao;
    private final DaoConfig healthPressureDaoConfig;
    private final HealthPressureItemDao healthPressureItemDao;
    private final DaoConfig healthPressureItemDaoConfig;
    private final HealthSleepDao healthSleepDao;
    private final DaoConfig healthSleepDaoConfig;
    private final HealthSleepItemDao healthSleepItemDao;
    private final DaoConfig healthSleepItemDaoConfig;
    private final HealthSpO2Dao healthSpO2Dao;
    private final DaoConfig healthSpO2DaoConfig;
    private final HealthSpO2ItemDao healthSpO2ItemDao;
    private final DaoConfig healthSpO2ItemDaoConfig;
    private final HealthSportDao healthSportDao;
    private final DaoConfig healthSportDaoConfig;
    private final HealthSportItemDao healthSportItemDao;
    private final DaoConfig healthSportItemDaoConfig;
    private final HealthSwimmingDao healthSwimmingDao;
    private final DaoConfig healthSwimmingDaoConfig;

    public DaoSession(Database database, IdentityScopeType identityScopeType, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig> map) {
        super(database);
        this.healthActivityDaoConfig = map.get(HealthActivityDao.class).clone();
        this.healthActivityDaoConfig.initIdentityScope(identityScopeType);
        this.healthBloodPressedDaoConfig = map.get(HealthBloodPressedDao.class).clone();
        this.healthBloodPressedDaoConfig.initIdentityScope(identityScopeType);
        this.healthBloodPressedItemDaoConfig = map.get(HealthBloodPressedItemDao.class).clone();
        this.healthBloodPressedItemDaoConfig.initIdentityScope(identityScopeType);
        this.healthHeartRateDaoConfig = map.get(HealthHeartRateDao.class).clone();
        this.healthHeartRateDaoConfig.initIdentityScope(identityScopeType);
        this.healthHeartRateItemDaoConfig = map.get(HealthHeartRateItemDao.class).clone();
        this.healthHeartRateItemDaoConfig.initIdentityScope(identityScopeType);
        this.healthHeartRateSecondDaoConfig = map.get(HealthHeartRateSecondDao.class).clone();
        this.healthHeartRateSecondDaoConfig.initIdentityScope(identityScopeType);
        this.healthPressureDaoConfig = map.get(HealthPressureDao.class).clone();
        this.healthPressureDaoConfig.initIdentityScope(identityScopeType);
        this.healthPressureItemDaoConfig = map.get(HealthPressureItemDao.class).clone();
        this.healthPressureItemDaoConfig.initIdentityScope(identityScopeType);
        this.healthSleepDaoConfig = map.get(HealthSleepDao.class).clone();
        this.healthSleepDaoConfig.initIdentityScope(identityScopeType);
        this.healthSleepItemDaoConfig = map.get(HealthSleepItemDao.class).clone();
        this.healthSleepItemDaoConfig.initIdentityScope(identityScopeType);
        this.healthSpO2DaoConfig = map.get(HealthSpO2Dao.class).clone();
        this.healthSpO2DaoConfig.initIdentityScope(identityScopeType);
        this.healthSpO2ItemDaoConfig = map.get(HealthSpO2ItemDao.class).clone();
        this.healthSpO2ItemDaoConfig.initIdentityScope(identityScopeType);
        this.healthSportDaoConfig = map.get(HealthSportDao.class).clone();
        this.healthSportDaoConfig.initIdentityScope(identityScopeType);
        this.healthSportItemDaoConfig = map.get(HealthSportItemDao.class).clone();
        this.healthSportItemDaoConfig.initIdentityScope(identityScopeType);
        this.healthSwimmingDaoConfig = map.get(HealthSwimmingDao.class).clone();
        this.healthSwimmingDaoConfig.initIdentityScope(identityScopeType);
        this.failLogInfoDaoConfig = map.get(FailLogInfoDao.class).clone();
        this.failLogInfoDaoConfig.initIdentityScope(identityScopeType);
        this.healthGpsDaoConfig = map.get(HealthGpsDao.class).clone();
        this.healthGpsDaoConfig.initIdentityScope(identityScopeType);
        this.healthGpsItemDaoConfig = map.get(HealthGpsItemDao.class).clone();
        this.healthGpsItemDaoConfig.initIdentityScope(identityScopeType);
        this.healthActivityDao = new HealthActivityDao(this.healthActivityDaoConfig, this);
        this.healthBloodPressedDao = new HealthBloodPressedDao(this.healthBloodPressedDaoConfig, this);
        this.healthBloodPressedItemDao = new HealthBloodPressedItemDao(this.healthBloodPressedItemDaoConfig, this);
        this.healthHeartRateDao = new HealthHeartRateDao(this.healthHeartRateDaoConfig, this);
        this.healthHeartRateItemDao = new HealthHeartRateItemDao(this.healthHeartRateItemDaoConfig, this);
        this.healthHeartRateSecondDao = new HealthHeartRateSecondDao(this.healthHeartRateSecondDaoConfig, this);
        this.healthPressureDao = new HealthPressureDao(this.healthPressureDaoConfig, this);
        this.healthPressureItemDao = new HealthPressureItemDao(this.healthPressureItemDaoConfig, this);
        this.healthSleepDao = new HealthSleepDao(this.healthSleepDaoConfig, this);
        this.healthSleepItemDao = new HealthSleepItemDao(this.healthSleepItemDaoConfig, this);
        this.healthSpO2Dao = new HealthSpO2Dao(this.healthSpO2DaoConfig, this);
        this.healthSpO2ItemDao = new HealthSpO2ItemDao(this.healthSpO2ItemDaoConfig, this);
        this.healthSportDao = new HealthSportDao(this.healthSportDaoConfig, this);
        this.healthSportItemDao = new HealthSportItemDao(this.healthSportItemDaoConfig, this);
        this.healthSwimmingDao = new HealthSwimmingDao(this.healthSwimmingDaoConfig, this);
        this.failLogInfoDao = new FailLogInfoDao(this.failLogInfoDaoConfig, this);
        this.healthGpsDao = new HealthGpsDao(this.healthGpsDaoConfig, this);
        this.healthGpsItemDao = new HealthGpsItemDao(this.healthGpsItemDaoConfig, this);
        registerDao(HealthActivity.class, this.healthActivityDao);
        registerDao(HealthBloodPressed.class, this.healthBloodPressedDao);
        registerDao(HealthBloodPressedItem.class, this.healthBloodPressedItemDao);
        registerDao(HealthHeartRate.class, this.healthHeartRateDao);
        registerDao(HealthHeartRateItem.class, this.healthHeartRateItemDao);
        registerDao(HealthHeartRateSecond.class, this.healthHeartRateSecondDao);
        registerDao(HealthPressure.class, this.healthPressureDao);
        registerDao(HealthPressureItem.class, this.healthPressureItemDao);
        registerDao(HealthSleep.class, this.healthSleepDao);
        registerDao(HealthSleepItem.class, this.healthSleepItemDao);
        registerDao(HealthSpO2.class, this.healthSpO2Dao);
        registerDao(HealthSpO2Item.class, this.healthSpO2ItemDao);
        registerDao(HealthSport.class, this.healthSportDao);
        registerDao(HealthSportItem.class, this.healthSportItemDao);
        registerDao(HealthSwimming.class, this.healthSwimmingDao);
        registerDao(c.class, this.failLogInfoDao);
        registerDao(HealthGps.class, this.healthGpsDao);
        registerDao(HealthGpsItem.class, this.healthGpsItemDao);
    }

    public void clear() {
        this.healthActivityDaoConfig.clearIdentityScope();
        this.healthBloodPressedDaoConfig.clearIdentityScope();
        this.healthBloodPressedItemDaoConfig.clearIdentityScope();
        this.healthHeartRateDaoConfig.clearIdentityScope();
        this.healthHeartRateItemDaoConfig.clearIdentityScope();
        this.healthHeartRateSecondDaoConfig.clearIdentityScope();
        this.healthPressureDaoConfig.clearIdentityScope();
        this.healthPressureItemDaoConfig.clearIdentityScope();
        this.healthSleepDaoConfig.clearIdentityScope();
        this.healthSleepItemDaoConfig.clearIdentityScope();
        this.healthSpO2DaoConfig.clearIdentityScope();
        this.healthSpO2ItemDaoConfig.clearIdentityScope();
        this.healthSportDaoConfig.clearIdentityScope();
        this.healthSportItemDaoConfig.clearIdentityScope();
        this.healthSwimmingDaoConfig.clearIdentityScope();
        this.failLogInfoDaoConfig.clearIdentityScope();
        this.healthGpsDaoConfig.clearIdentityScope();
        this.healthGpsItemDaoConfig.clearIdentityScope();
    }

    public FailLogInfoDao getFailLogInfoDao() {
        return this.failLogInfoDao;
    }

    public HealthActivityDao getHealthActivityDao() {
        return this.healthActivityDao;
    }

    public HealthBloodPressedDao getHealthBloodPressedDao() {
        return this.healthBloodPressedDao;
    }

    public HealthBloodPressedItemDao getHealthBloodPressedItemDao() {
        return this.healthBloodPressedItemDao;
    }

    public HealthGpsDao getHealthGpsDao() {
        return this.healthGpsDao;
    }

    public HealthGpsItemDao getHealthGpsItemDao() {
        return this.healthGpsItemDao;
    }

    public HealthHeartRateDao getHealthHeartRateDao() {
        return this.healthHeartRateDao;
    }

    public HealthHeartRateItemDao getHealthHeartRateItemDao() {
        return this.healthHeartRateItemDao;
    }

    public HealthHeartRateSecondDao getHealthHeartRateSecondDao() {
        return this.healthHeartRateSecondDao;
    }

    public HealthPressureDao getHealthPressureDao() {
        return this.healthPressureDao;
    }

    public HealthPressureItemDao getHealthPressureItemDao() {
        return this.healthPressureItemDao;
    }

    public HealthSleepDao getHealthSleepDao() {
        return this.healthSleepDao;
    }

    public HealthSleepItemDao getHealthSleepItemDao() {
        return this.healthSleepItemDao;
    }

    public HealthSpO2Dao getHealthSpO2Dao() {
        return this.healthSpO2Dao;
    }

    public HealthSpO2ItemDao getHealthSpO2ItemDao() {
        return this.healthSpO2ItemDao;
    }

    public HealthSportDao getHealthSportDao() {
        return this.healthSportDao;
    }

    public HealthSportItemDao getHealthSportItemDao() {
        return this.healthSportItemDao;
    }

    public HealthSwimmingDao getHealthSwimmingDao() {
        return this.healthSwimmingDao;
    }
}