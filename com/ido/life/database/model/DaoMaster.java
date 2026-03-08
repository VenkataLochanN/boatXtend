package com.ido.life.database.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseOpenHelper;
import org.greenrobot.greendao.database.StandardDatabase;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

/* JADX INFO: loaded from: classes2.dex */
public class DaoMaster extends AbstractDaoMaster {
    public static final int SCHEMA_VERSION = 4;

    public static void createAllTables(Database database, boolean z) {
        ActiveTimeDayDataDao.createTable(database, z);
        AgreementPrivacyVersionDao.createTable(database, z);
        CalorieDayDataDao.createTable(database, z);
        DataDownLoadStateDao.createTable(database, z);
        DataPullConfigInfoDao.createTable(database, z);
        GpsColorsDao.createTable(database, z);
        HealthPressureDao.createTable(database, z);
        HealthVolumeDataDao.createTable(database, z);
        HomeCardDao.createTable(database, z);
        LifeCycleItemBeanDao.createTable(database, z);
        MapEngineDao.createTable(database, z);
        MenstruationConfigDao.createTable(database, z);
        MessageEntityDao.createTable(database, z);
        MultiDaysWalkTotalDataDao.createTable(database, z);
        NoticeAppEntityDao.createTable(database, z);
        PrivateSafeSettingDao.createTable(database, z);
        ServerBloodOxyDayDataDao.createTable(database, z);
        ServerBloodOxyMonthDataDao.createTable(database, z);
        ServerDaysBloodOxyDataDao.createTable(database, z);
        ServerDaysSleepDataDao.createTable(database, z);
        ServerHeartRateDayDataDao.createTable(database, z);
        ServerMultiMonthBloodOxyTotalDataDao.createTable(database, z);
        ServerMultiMonthSleepTotalDataDao.createTable(database, z);
        ServerSleepDayDataDao.createTable(database, z);
        ServerSleepMonthDataDao.createTable(database, z);
        SportCardDao.createTable(database, z);
        SportDistanceBeanDao.createTable(database, z);
        SportGpsDataDao.createTable(database, z);
        SportHealthDao.createTable(database, z);
        StepDayDataDao.createTable(database, z);
        TempUnitSettingDao.createTable(database, z);
        TimeSetDao.createTable(database, z);
        UnitSettingDao.createTable(database, z);
        UserInfoDao.createTable(database, z);
        UserMedalInfoDao.createTable(database, z);
        UserTargetNewDao.createTable(database, z);
        WalkDayDataDao.createTable(database, z);
        WalkMonthTotalDataDao.createTable(database, z);
        WalkYearTotalDataDao.createTable(database, z);
        WeekStartSettingDao.createTable(database, z);
        WeightItemBeanDao.createTable(database, z);
    }

    public static void dropAllTables(Database database, boolean z) {
        ActiveTimeDayDataDao.dropTable(database, z);
        AgreementPrivacyVersionDao.dropTable(database, z);
        CalorieDayDataDao.dropTable(database, z);
        DataDownLoadStateDao.dropTable(database, z);
        DataPullConfigInfoDao.dropTable(database, z);
        GpsColorsDao.dropTable(database, z);
        HealthPressureDao.dropTable(database, z);
        HealthVolumeDataDao.dropTable(database, z);
        HomeCardDao.dropTable(database, z);
        LifeCycleItemBeanDao.dropTable(database, z);
        MapEngineDao.dropTable(database, z);
        MenstruationConfigDao.dropTable(database, z);
        MessageEntityDao.dropTable(database, z);
        MultiDaysWalkTotalDataDao.dropTable(database, z);
        NoticeAppEntityDao.dropTable(database, z);
        PrivateSafeSettingDao.dropTable(database, z);
        ServerBloodOxyDayDataDao.dropTable(database, z);
        ServerBloodOxyMonthDataDao.dropTable(database, z);
        ServerDaysBloodOxyDataDao.dropTable(database, z);
        ServerDaysSleepDataDao.dropTable(database, z);
        ServerHeartRateDayDataDao.dropTable(database, z);
        ServerMultiMonthBloodOxyTotalDataDao.dropTable(database, z);
        ServerMultiMonthSleepTotalDataDao.dropTable(database, z);
        ServerSleepDayDataDao.dropTable(database, z);
        ServerSleepMonthDataDao.dropTable(database, z);
        SportCardDao.dropTable(database, z);
        SportDistanceBeanDao.dropTable(database, z);
        SportGpsDataDao.dropTable(database, z);
        SportHealthDao.dropTable(database, z);
        StepDayDataDao.dropTable(database, z);
        TempUnitSettingDao.dropTable(database, z);
        TimeSetDao.dropTable(database, z);
        UnitSettingDao.dropTable(database, z);
        UserInfoDao.dropTable(database, z);
        UserMedalInfoDao.dropTable(database, z);
        UserTargetNewDao.dropTable(database, z);
        WalkDayDataDao.dropTable(database, z);
        WalkMonthTotalDataDao.dropTable(database, z);
        WalkYearTotalDataDao.dropTable(database, z);
        WeekStartSettingDao.dropTable(database, z);
        WeightItemBeanDao.dropTable(database, z);
    }

    public static DaoSession newDevSession(Context context, String str) {
        return new DaoMaster(new DevOpenHelper(context, str).getWritableDb()).newSession();
    }

    public DaoMaster(SQLiteDatabase sQLiteDatabase) {
        this(new StandardDatabase(sQLiteDatabase));
    }

    public DaoMaster(Database database) {
        super(database, 4);
        registerDaoClass(ActiveTimeDayDataDao.class);
        registerDaoClass(AgreementPrivacyVersionDao.class);
        registerDaoClass(CalorieDayDataDao.class);
        registerDaoClass(DataDownLoadStateDao.class);
        registerDaoClass(DataPullConfigInfoDao.class);
        registerDaoClass(GpsColorsDao.class);
        registerDaoClass(HealthPressureDao.class);
        registerDaoClass(HealthVolumeDataDao.class);
        registerDaoClass(HomeCardDao.class);
        registerDaoClass(LifeCycleItemBeanDao.class);
        registerDaoClass(MapEngineDao.class);
        registerDaoClass(MenstruationConfigDao.class);
        registerDaoClass(MessageEntityDao.class);
        registerDaoClass(MultiDaysWalkTotalDataDao.class);
        registerDaoClass(NoticeAppEntityDao.class);
        registerDaoClass(PrivateSafeSettingDao.class);
        registerDaoClass(ServerBloodOxyDayDataDao.class);
        registerDaoClass(ServerBloodOxyMonthDataDao.class);
        registerDaoClass(ServerDaysBloodOxyDataDao.class);
        registerDaoClass(ServerDaysSleepDataDao.class);
        registerDaoClass(ServerHeartRateDayDataDao.class);
        registerDaoClass(ServerMultiMonthBloodOxyTotalDataDao.class);
        registerDaoClass(ServerMultiMonthSleepTotalDataDao.class);
        registerDaoClass(ServerSleepDayDataDao.class);
        registerDaoClass(ServerSleepMonthDataDao.class);
        registerDaoClass(SportCardDao.class);
        registerDaoClass(SportDistanceBeanDao.class);
        registerDaoClass(SportGpsDataDao.class);
        registerDaoClass(SportHealthDao.class);
        registerDaoClass(StepDayDataDao.class);
        registerDaoClass(TempUnitSettingDao.class);
        registerDaoClass(TimeSetDao.class);
        registerDaoClass(UnitSettingDao.class);
        registerDaoClass(UserInfoDao.class);
        registerDaoClass(UserMedalInfoDao.class);
        registerDaoClass(UserTargetNewDao.class);
        registerDaoClass(WalkDayDataDao.class);
        registerDaoClass(WalkMonthTotalDataDao.class);
        registerDaoClass(WalkYearTotalDataDao.class);
        registerDaoClass(WeekStartSettingDao.class);
        registerDaoClass(WeightItemBeanDao.class);
    }

    @Override // org.greenrobot.greendao.AbstractDaoMaster
    public DaoSession newSession() {
        return new DaoSession(this.db, IdentityScopeType.Session, this.daoConfigMap);
    }

    @Override // org.greenrobot.greendao.AbstractDaoMaster
    public DaoSession newSession(IdentityScopeType identityScopeType) {
        return new DaoSession(this.db, identityScopeType, this.daoConfigMap);
    }

    public static abstract class OpenHelper extends DatabaseOpenHelper {
        public OpenHelper(Context context, String str) {
            super(context, str, 4);
        }

        public OpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory, 4);
        }

        @Override // org.greenrobot.greendao.database.DatabaseOpenHelper
        public void onCreate(Database database) {
            Log.i("greenDAO", "Creating tables for schema version 4");
            DaoMaster.createAllTables(database, false);
        }
    }

    public static class DevOpenHelper extends OpenHelper {
        public DevOpenHelper(Context context, String str) {
            super(context, str);
        }

        public DevOpenHelper(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory) {
            super(context, str, cursorFactory);
        }

        @Override // org.greenrobot.greendao.database.DatabaseOpenHelper
        public void onUpgrade(Database database, int i, int i2) {
            Log.i("greenDAO", "Upgrading schema from version " + i + " to " + i2 + " by dropping all tables");
            DaoMaster.dropAllTables(database, true);
            onCreate(database);
        }
    }
}