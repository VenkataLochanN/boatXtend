package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class DataPullConfigInfoDao extends AbstractDao<DataPullConfigInfo, Long> {
    public static final String TABLENAME = "DATA_PULL_CONFIG_INFO";
    private final AtomicIntegerConverter HasDownloadCountConverter;
    private DaoSession daoSession;

    public static class Properties {
        public static final Property Id = new Property(0, Long.class, "Id", true, "_id");
        public static final Property UserId = new Property(1, Long.TYPE, "UserId", false, "USER_ID");
        public static final Property AutoDownload = new Property(2, Boolean.TYPE, "AutoDownload", false, "AUTO_DOWNLOAD");
        public static final Property HasDownloadCount = new Property(3, Integer.class, "HasDownloadCount", false, "HAS_DOWNLOAD_COUNT");
        public static final Property DataTotalCount = new Property(4, Integer.TYPE, "DataTotalCount", false, "DATA_TOTAL_COUNT");
        public static final Property BloodOxyCount = new Property(5, Integer.TYPE, "BloodOxyCount", false, "BLOOD_OXY_COUNT");
        public static final Property BloodStartTime = new Property(6, Long.TYPE, "BloodStartTime", false, "BLOOD_START_TIME");
        public static final Property BloodEndTime = new Property(7, Long.TYPE, "BloodEndTime", false, "BLOOD_END_TIME");
        public static final Property CalorieCount = new Property(8, Integer.TYPE, "CalorieCount", false, "CALORIE_COUNT");
        public static final Property CalorieStartTime = new Property(9, Long.TYPE, "CalorieStartTime", false, "CALORIE_START_TIME");
        public static final Property CalorieEndTime = new Property(10, Long.TYPE, "CalorieEndTime", false, "CALORIE_END_TIME");
        public static final Property DistanceCount = new Property(11, Integer.TYPE, "DistanceCount", false, "DISTANCE_COUNT");
        public static final Property DistanceStartTime = new Property(12, Long.TYPE, "DistanceStartTime", false, "DISTANCE_START_TIME");
        public static final Property DistanceEndTime = new Property(13, Long.TYPE, "DistanceEndTime", false, "DISTANCE_END_TIME");
        public static final Property ExerciseCount = new Property(14, Integer.TYPE, "ExerciseCount", false, "EXERCISE_COUNT");
        public static final Property ExerciseStartTime = new Property(15, Long.TYPE, "ExerciseStartTime", false, "EXERCISE_START_TIME");
        public static final Property ExerciseEndTime = new Property(16, Long.TYPE, "ExerciseEndTime", false, "EXERCISE_END_TIME");
        public static final Property MensesCount = new Property(17, Integer.TYPE, "MensesCount", false, "MENSES_COUNT");
        public static final Property MensesStartTime = new Property(18, Long.TYPE, "MensesStartTime", false, "MENSES_START_TIME");
        public static final Property MensesEndTime = new Property(19, Long.TYPE, "MensesEndTime", false, "MENSES_END_TIME");
        public static final Property HeartRateCount = new Property(20, Integer.TYPE, "HeartRateCount", false, "HEART_RATE_COUNT");
        public static final Property HeartRateStartTime = new Property(21, Long.TYPE, "HeartRateStartTime", false, "HEART_RATE_START_TIME");
        public static final Property HeartRateEndTime = new Property(22, Long.TYPE, "HeartRateEndTime", false, "HEART_RATE_END_TIME");
        public static final Property PressureCount = new Property(23, Integer.TYPE, "PressureCount", false, "PRESSURE_COUNT");
        public static final Property PressureStartTime = new Property(24, Long.TYPE, "PressureStartTime", false, "PRESSURE_START_TIME");
        public static final Property PressureEndTime = new Property(25, Long.TYPE, "PressureEndTime", false, "PRESSURE_END_TIME");
        public static final Property SleepCount = new Property(26, Integer.TYPE, "SleepCount", false, "SLEEP_COUNT");
        public static final Property SleepStartTime = new Property(27, Long.TYPE, "SleepStartTime", false, "SLEEP_START_TIME");
        public static final Property SleepEndTime = new Property(28, Long.TYPE, "SleepEndTime", false, "SLEEP_END_TIME");
        public static final Property SportCount = new Property(29, Integer.TYPE, "SportCount", false, "SPORT_COUNT");
        public static final Property SportStartTime = new Property(30, Long.TYPE, "SportStartTime", false, "SPORT_START_TIME");
        public static final Property SportEndTime = new Property(31, Long.TYPE, "SportEndTime", false, "SPORT_END_TIME");
        public static final Property StepCount = new Property(32, Integer.TYPE, "StepCount", false, "STEP_COUNT");
        public static final Property StepStartTime = new Property(33, Long.TYPE, "StepStartTime", false, "STEP_START_TIME");
        public static final Property StepEndTime = new Property(34, Long.TYPE, "StepEndTime", false, "STEP_END_TIME");
        public static final Property WalkCount = new Property(35, Integer.TYPE, "WalkCount", false, "WALK_COUNT");
        public static final Property WalkStartTime = new Property(36, Long.TYPE, "WalkStartTime", false, "WALK_START_TIME");
        public static final Property WalkEndTime = new Property(37, Long.TYPE, "WalkEndTime", false, "WALK_END_TIME");
        public static final Property WeightCount = new Property(38, Integer.TYPE, "WeightCount", false, "WEIGHT_COUNT");
        public static final Property WeightStartTime = new Property(39, Long.TYPE, "WeightStartTime", false, "WEIGHT_START_TIME");
        public static final Property WeightEndTime = new Property(40, Long.TYPE, "WeightEndTime", false, "WEIGHT_END_TIME");
        public static final Property TargetCount = new Property(41, Integer.TYPE, "TargetCount", false, "TARGET_COUNT");
        public static final Property TargetStartTime = new Property(42, Long.TYPE, "TargetStartTime", false, "TARGET_START_TIME");
        public static final Property TargetEndTime = new Property(43, Long.TYPE, "TargetEndTime", false, "TARGET_END_TIME");
        public static final Property NoiseCount = new Property(44, Integer.TYPE, "NoiseCount", false, "NOISE_COUNT");
        public static final Property NoiseStartTime = new Property(45, Long.TYPE, "NoiseStartTime", false, "NOISE_START_TIME");
        public static final Property NoiseEndTime = new Property(46, Long.TYPE, "NoiseEndTime", false, "NOISE_END_TIME");
        public static final Property TemperatureCout = new Property(47, Long.TYPE, "TemperatureCout", false, "TEMPERATURE_COUT");
        public static final Property TemperatureStartTime = new Property(48, Long.TYPE, "TemperatureStartTime", false, "TEMPERATURE_START_TIME");
        public static final Property TemperatureEndTime = new Property(49, Long.TYPE, "TemperatureEndTime", false, "TEMPERATURE_END_TIME");
        public static final Property RateNoticeCount = new Property(50, Long.TYPE, "RateNoticeCount", false, "RATE_NOTICE_COUNT");
        public static final Property RateNoticeStartTime = new Property(51, Long.TYPE, "RateNoticeStartTime", false, "RATE_NOTICE_START_TIME");
        public static final Property RateNoticeEndTime = new Property(52, Long.TYPE, "RateNoticeEndTime", false, "RATE_NOTICE_END_TIME");
        public static final Property ShowTipDialog = new Property(53, Boolean.TYPE, "ShowTipDialog", false, "SHOW_TIP_DIALOG");
        public static final Property ShowState = new Property(54, Boolean.TYPE, "ShowState", false, "SHOW_STATE");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public DataPullConfigInfoDao(DaoConfig daoConfig) {
        super(daoConfig);
        this.HasDownloadCountConverter = new AtomicIntegerConverter();
    }

    public DataPullConfigInfoDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.HasDownloadCountConverter = new AtomicIntegerConverter();
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"DATA_PULL_CONFIG_INFO\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"AUTO_DOWNLOAD\" INTEGER NOT NULL ,\"HAS_DOWNLOAD_COUNT\" INTEGER,\"DATA_TOTAL_COUNT\" INTEGER NOT NULL ,\"BLOOD_OXY_COUNT\" INTEGER NOT NULL ,\"BLOOD_START_TIME\" INTEGER NOT NULL ,\"BLOOD_END_TIME\" INTEGER NOT NULL ,\"CALORIE_COUNT\" INTEGER NOT NULL ,\"CALORIE_START_TIME\" INTEGER NOT NULL ,\"CALORIE_END_TIME\" INTEGER NOT NULL ,\"DISTANCE_COUNT\" INTEGER NOT NULL ,\"DISTANCE_START_TIME\" INTEGER NOT NULL ,\"DISTANCE_END_TIME\" INTEGER NOT NULL ,\"EXERCISE_COUNT\" INTEGER NOT NULL ,\"EXERCISE_START_TIME\" INTEGER NOT NULL ,\"EXERCISE_END_TIME\" INTEGER NOT NULL ,\"MENSES_COUNT\" INTEGER NOT NULL ,\"MENSES_START_TIME\" INTEGER NOT NULL ,\"MENSES_END_TIME\" INTEGER NOT NULL ,\"HEART_RATE_COUNT\" INTEGER NOT NULL ,\"HEART_RATE_START_TIME\" INTEGER NOT NULL ,\"HEART_RATE_END_TIME\" INTEGER NOT NULL ,\"PRESSURE_COUNT\" INTEGER NOT NULL ,\"PRESSURE_START_TIME\" INTEGER NOT NULL ,\"PRESSURE_END_TIME\" INTEGER NOT NULL ,\"SLEEP_COUNT\" INTEGER NOT NULL ,\"SLEEP_START_TIME\" INTEGER NOT NULL ,\"SLEEP_END_TIME\" INTEGER NOT NULL ,\"SPORT_COUNT\" INTEGER NOT NULL ,\"SPORT_START_TIME\" INTEGER NOT NULL ,\"SPORT_END_TIME\" INTEGER NOT NULL ,\"STEP_COUNT\" INTEGER NOT NULL ,\"STEP_START_TIME\" INTEGER NOT NULL ,\"STEP_END_TIME\" INTEGER NOT NULL ,\"WALK_COUNT\" INTEGER NOT NULL ,\"WALK_START_TIME\" INTEGER NOT NULL ,\"WALK_END_TIME\" INTEGER NOT NULL ,\"WEIGHT_COUNT\" INTEGER NOT NULL ,\"WEIGHT_START_TIME\" INTEGER NOT NULL ,\"WEIGHT_END_TIME\" INTEGER NOT NULL ,\"TARGET_COUNT\" INTEGER NOT NULL ,\"TARGET_START_TIME\" INTEGER NOT NULL ,\"TARGET_END_TIME\" INTEGER NOT NULL ,\"NOISE_COUNT\" INTEGER NOT NULL ,\"NOISE_START_TIME\" INTEGER NOT NULL ,\"NOISE_END_TIME\" INTEGER NOT NULL ,\"TEMPERATURE_COUT\" INTEGER NOT NULL ,\"TEMPERATURE_START_TIME\" INTEGER NOT NULL ,\"TEMPERATURE_END_TIME\" INTEGER NOT NULL ,\"RATE_NOTICE_COUNT\" INTEGER NOT NULL ,\"RATE_NOTICE_START_TIME\" INTEGER NOT NULL ,\"RATE_NOTICE_END_TIME\" INTEGER NOT NULL ,\"SHOW_TIP_DIALOG\" INTEGER NOT NULL ,\"SHOW_STATE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"DATA_PULL_CONFIG_INFO\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, DataPullConfigInfo dataPullConfigInfo) {
        databaseStatement.clearBindings();
        Long id = dataPullConfigInfo.getId();
        if (id != null) {
            databaseStatement.bindLong(1, id.longValue());
        }
        databaseStatement.bindLong(2, dataPullConfigInfo.getUserId());
        databaseStatement.bindLong(3, dataPullConfigInfo.getAutoDownload() ? 1L : 0L);
        if (dataPullConfigInfo.getHasDownloadCount() != null) {
            databaseStatement.bindLong(4, this.HasDownloadCountConverter.convertToDatabaseValue(r0).intValue());
        }
        databaseStatement.bindLong(5, dataPullConfigInfo.getDataTotalCount());
        databaseStatement.bindLong(6, dataPullConfigInfo.getBloodOxyCount());
        databaseStatement.bindLong(7, dataPullConfigInfo.getBloodStartTime());
        databaseStatement.bindLong(8, dataPullConfigInfo.getBloodEndTime());
        databaseStatement.bindLong(9, dataPullConfigInfo.getCalorieCount());
        databaseStatement.bindLong(10, dataPullConfigInfo.getCalorieStartTime());
        databaseStatement.bindLong(11, dataPullConfigInfo.getCalorieEndTime());
        databaseStatement.bindLong(12, dataPullConfigInfo.getDistanceCount());
        databaseStatement.bindLong(13, dataPullConfigInfo.getDistanceStartTime());
        databaseStatement.bindLong(14, dataPullConfigInfo.getDistanceEndTime());
        databaseStatement.bindLong(15, dataPullConfigInfo.getExerciseCount());
        databaseStatement.bindLong(16, dataPullConfigInfo.getExerciseStartTime());
        databaseStatement.bindLong(17, dataPullConfigInfo.getExerciseEndTime());
        databaseStatement.bindLong(18, dataPullConfigInfo.getMensesCount());
        databaseStatement.bindLong(19, dataPullConfigInfo.getMensesStartTime());
        databaseStatement.bindLong(20, dataPullConfigInfo.getMensesEndTime());
        databaseStatement.bindLong(21, dataPullConfigInfo.getHeartRateCount());
        databaseStatement.bindLong(22, dataPullConfigInfo.getHeartRateStartTime());
        databaseStatement.bindLong(23, dataPullConfigInfo.getHeartRateEndTime());
        databaseStatement.bindLong(24, dataPullConfigInfo.getPressureCount());
        databaseStatement.bindLong(25, dataPullConfigInfo.getPressureStartTime());
        databaseStatement.bindLong(26, dataPullConfigInfo.getPressureEndTime());
        databaseStatement.bindLong(27, dataPullConfigInfo.getSleepCount());
        databaseStatement.bindLong(28, dataPullConfigInfo.getSleepStartTime());
        databaseStatement.bindLong(29, dataPullConfigInfo.getSleepEndTime());
        databaseStatement.bindLong(30, dataPullConfigInfo.getSportCount());
        databaseStatement.bindLong(31, dataPullConfigInfo.getSportStartTime());
        databaseStatement.bindLong(32, dataPullConfigInfo.getSportEndTime());
        databaseStatement.bindLong(33, dataPullConfigInfo.getStepCount());
        databaseStatement.bindLong(34, dataPullConfigInfo.getStepStartTime());
        databaseStatement.bindLong(35, dataPullConfigInfo.getStepEndTime());
        databaseStatement.bindLong(36, dataPullConfigInfo.getWalkCount());
        databaseStatement.bindLong(37, dataPullConfigInfo.getWalkStartTime());
        databaseStatement.bindLong(38, dataPullConfigInfo.getWalkEndTime());
        databaseStatement.bindLong(39, dataPullConfigInfo.getWeightCount());
        databaseStatement.bindLong(40, dataPullConfigInfo.getWeightStartTime());
        databaseStatement.bindLong(41, dataPullConfigInfo.getWeightEndTime());
        databaseStatement.bindLong(42, dataPullConfigInfo.getTargetCount());
        databaseStatement.bindLong(43, dataPullConfigInfo.getTargetStartTime());
        databaseStatement.bindLong(44, dataPullConfigInfo.getTargetEndTime());
        databaseStatement.bindLong(45, dataPullConfigInfo.getNoiseCount());
        databaseStatement.bindLong(46, dataPullConfigInfo.getNoiseStartTime());
        databaseStatement.bindLong(47, dataPullConfigInfo.getNoiseEndTime());
        databaseStatement.bindLong(48, dataPullConfigInfo.getTemperatureCout());
        databaseStatement.bindLong(49, dataPullConfigInfo.getTemperatureStartTime());
        databaseStatement.bindLong(50, dataPullConfigInfo.getTemperatureEndTime());
        databaseStatement.bindLong(51, dataPullConfigInfo.getRateNoticeCount());
        databaseStatement.bindLong(52, dataPullConfigInfo.getRateNoticeStartTime());
        databaseStatement.bindLong(53, dataPullConfigInfo.getRateNoticeEndTime());
        databaseStatement.bindLong(54, dataPullConfigInfo.getShowTipDialog() ? 1L : 0L);
        databaseStatement.bindLong(55, dataPullConfigInfo.getShowState() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, DataPullConfigInfo dataPullConfigInfo) {
        sQLiteStatement.clearBindings();
        Long id = dataPullConfigInfo.getId();
        if (id != null) {
            sQLiteStatement.bindLong(1, id.longValue());
        }
        sQLiteStatement.bindLong(2, dataPullConfigInfo.getUserId());
        sQLiteStatement.bindLong(3, dataPullConfigInfo.getAutoDownload() ? 1L : 0L);
        if (dataPullConfigInfo.getHasDownloadCount() != null) {
            sQLiteStatement.bindLong(4, this.HasDownloadCountConverter.convertToDatabaseValue(r0).intValue());
        }
        sQLiteStatement.bindLong(5, dataPullConfigInfo.getDataTotalCount());
        sQLiteStatement.bindLong(6, dataPullConfigInfo.getBloodOxyCount());
        sQLiteStatement.bindLong(7, dataPullConfigInfo.getBloodStartTime());
        sQLiteStatement.bindLong(8, dataPullConfigInfo.getBloodEndTime());
        sQLiteStatement.bindLong(9, dataPullConfigInfo.getCalorieCount());
        sQLiteStatement.bindLong(10, dataPullConfigInfo.getCalorieStartTime());
        sQLiteStatement.bindLong(11, dataPullConfigInfo.getCalorieEndTime());
        sQLiteStatement.bindLong(12, dataPullConfigInfo.getDistanceCount());
        sQLiteStatement.bindLong(13, dataPullConfigInfo.getDistanceStartTime());
        sQLiteStatement.bindLong(14, dataPullConfigInfo.getDistanceEndTime());
        sQLiteStatement.bindLong(15, dataPullConfigInfo.getExerciseCount());
        sQLiteStatement.bindLong(16, dataPullConfigInfo.getExerciseStartTime());
        sQLiteStatement.bindLong(17, dataPullConfigInfo.getExerciseEndTime());
        sQLiteStatement.bindLong(18, dataPullConfigInfo.getMensesCount());
        sQLiteStatement.bindLong(19, dataPullConfigInfo.getMensesStartTime());
        sQLiteStatement.bindLong(20, dataPullConfigInfo.getMensesEndTime());
        sQLiteStatement.bindLong(21, dataPullConfigInfo.getHeartRateCount());
        sQLiteStatement.bindLong(22, dataPullConfigInfo.getHeartRateStartTime());
        sQLiteStatement.bindLong(23, dataPullConfigInfo.getHeartRateEndTime());
        sQLiteStatement.bindLong(24, dataPullConfigInfo.getPressureCount());
        sQLiteStatement.bindLong(25, dataPullConfigInfo.getPressureStartTime());
        sQLiteStatement.bindLong(26, dataPullConfigInfo.getPressureEndTime());
        sQLiteStatement.bindLong(27, dataPullConfigInfo.getSleepCount());
        sQLiteStatement.bindLong(28, dataPullConfigInfo.getSleepStartTime());
        sQLiteStatement.bindLong(29, dataPullConfigInfo.getSleepEndTime());
        sQLiteStatement.bindLong(30, dataPullConfigInfo.getSportCount());
        sQLiteStatement.bindLong(31, dataPullConfigInfo.getSportStartTime());
        sQLiteStatement.bindLong(32, dataPullConfigInfo.getSportEndTime());
        sQLiteStatement.bindLong(33, dataPullConfigInfo.getStepCount());
        sQLiteStatement.bindLong(34, dataPullConfigInfo.getStepStartTime());
        sQLiteStatement.bindLong(35, dataPullConfigInfo.getStepEndTime());
        sQLiteStatement.bindLong(36, dataPullConfigInfo.getWalkCount());
        sQLiteStatement.bindLong(37, dataPullConfigInfo.getWalkStartTime());
        sQLiteStatement.bindLong(38, dataPullConfigInfo.getWalkEndTime());
        sQLiteStatement.bindLong(39, dataPullConfigInfo.getWeightCount());
        sQLiteStatement.bindLong(40, dataPullConfigInfo.getWeightStartTime());
        sQLiteStatement.bindLong(41, dataPullConfigInfo.getWeightEndTime());
        sQLiteStatement.bindLong(42, dataPullConfigInfo.getTargetCount());
        sQLiteStatement.bindLong(43, dataPullConfigInfo.getTargetStartTime());
        sQLiteStatement.bindLong(44, dataPullConfigInfo.getTargetEndTime());
        sQLiteStatement.bindLong(45, dataPullConfigInfo.getNoiseCount());
        sQLiteStatement.bindLong(46, dataPullConfigInfo.getNoiseStartTime());
        sQLiteStatement.bindLong(47, dataPullConfigInfo.getNoiseEndTime());
        sQLiteStatement.bindLong(48, dataPullConfigInfo.getTemperatureCout());
        sQLiteStatement.bindLong(49, dataPullConfigInfo.getTemperatureStartTime());
        sQLiteStatement.bindLong(50, dataPullConfigInfo.getTemperatureEndTime());
        sQLiteStatement.bindLong(51, dataPullConfigInfo.getRateNoticeCount());
        sQLiteStatement.bindLong(52, dataPullConfigInfo.getRateNoticeStartTime());
        sQLiteStatement.bindLong(53, dataPullConfigInfo.getRateNoticeEndTime());
        sQLiteStatement.bindLong(54, dataPullConfigInfo.getShowTipDialog() ? 1L : 0L);
        sQLiteStatement.bindLong(55, dataPullConfigInfo.getShowState() ? 1L : 0L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(DataPullConfigInfo dataPullConfigInfo) {
        super.attachEntity(dataPullConfigInfo);
        dataPullConfigInfo.__setDaoSession(this.daoSession);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 0;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public DataPullConfigInfo readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        int i3 = i + 3;
        return new DataPullConfigInfo(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)), cursor.getLong(i + 1), cursor.getShort(i + 2) != 0, cursor.isNull(i3) ? null : this.HasDownloadCountConverter.convertToEntityProperty(Integer.valueOf(cursor.getInt(i3))), cursor.getInt(i + 4), cursor.getInt(i + 5), cursor.getLong(i + 6), cursor.getLong(i + 7), cursor.getInt(i + 8), cursor.getLong(i + 9), cursor.getLong(i + 10), cursor.getInt(i + 11), cursor.getLong(i + 12), cursor.getLong(i + 13), cursor.getInt(i + 14), cursor.getLong(i + 15), cursor.getLong(i + 16), cursor.getInt(i + 17), cursor.getLong(i + 18), cursor.getLong(i + 19), cursor.getInt(i + 20), cursor.getLong(i + 21), cursor.getLong(i + 22), cursor.getInt(i + 23), cursor.getLong(i + 24), cursor.getLong(i + 25), cursor.getInt(i + 26), cursor.getLong(i + 27), cursor.getLong(i + 28), cursor.getInt(i + 29), cursor.getLong(i + 30), cursor.getLong(i + 31), cursor.getInt(i + 32), cursor.getLong(i + 33), cursor.getLong(i + 34), cursor.getInt(i + 35), cursor.getLong(i + 36), cursor.getLong(i + 37), cursor.getInt(i + 38), cursor.getLong(i + 39), cursor.getLong(i + 40), cursor.getInt(i + 41), cursor.getLong(i + 42), cursor.getLong(i + 43), cursor.getInt(i + 44), cursor.getLong(i + 45), cursor.getLong(i + 46), cursor.getLong(i + 47), cursor.getLong(i + 48), cursor.getLong(i + 49), cursor.getLong(i + 50), cursor.getLong(i + 51), cursor.getLong(i + 52), cursor.getShort(i + 53) != 0, cursor.getShort(i + 54) != 0);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, DataPullConfigInfo dataPullConfigInfo, int i) {
        int i2 = i + 0;
        dataPullConfigInfo.setId(cursor.isNull(i2) ? null : Long.valueOf(cursor.getLong(i2)));
        dataPullConfigInfo.setUserId(cursor.getLong(i + 1));
        dataPullConfigInfo.setAutoDownload(cursor.getShort(i + 2) != 0);
        int i3 = i + 3;
        dataPullConfigInfo.setHasDownloadCount(cursor.isNull(i3) ? null : this.HasDownloadCountConverter.convertToEntityProperty(Integer.valueOf(cursor.getInt(i3))));
        dataPullConfigInfo.setDataTotalCount(cursor.getInt(i + 4));
        dataPullConfigInfo.setBloodOxyCount(cursor.getInt(i + 5));
        dataPullConfigInfo.setBloodStartTime(cursor.getLong(i + 6));
        dataPullConfigInfo.setBloodEndTime(cursor.getLong(i + 7));
        dataPullConfigInfo.setCalorieCount(cursor.getInt(i + 8));
        dataPullConfigInfo.setCalorieStartTime(cursor.getLong(i + 9));
        dataPullConfigInfo.setCalorieEndTime(cursor.getLong(i + 10));
        dataPullConfigInfo.setDistanceCount(cursor.getInt(i + 11));
        dataPullConfigInfo.setDistanceStartTime(cursor.getLong(i + 12));
        dataPullConfigInfo.setDistanceEndTime(cursor.getLong(i + 13));
        dataPullConfigInfo.setExerciseCount(cursor.getInt(i + 14));
        dataPullConfigInfo.setExerciseStartTime(cursor.getLong(i + 15));
        dataPullConfigInfo.setExerciseEndTime(cursor.getLong(i + 16));
        dataPullConfigInfo.setMensesCount(cursor.getInt(i + 17));
        dataPullConfigInfo.setMensesStartTime(cursor.getLong(i + 18));
        dataPullConfigInfo.setMensesEndTime(cursor.getLong(i + 19));
        dataPullConfigInfo.setHeartRateCount(cursor.getInt(i + 20));
        dataPullConfigInfo.setHeartRateStartTime(cursor.getLong(i + 21));
        dataPullConfigInfo.setHeartRateEndTime(cursor.getLong(i + 22));
        dataPullConfigInfo.setPressureCount(cursor.getInt(i + 23));
        dataPullConfigInfo.setPressureStartTime(cursor.getLong(i + 24));
        dataPullConfigInfo.setPressureEndTime(cursor.getLong(i + 25));
        dataPullConfigInfo.setSleepCount(cursor.getInt(i + 26));
        dataPullConfigInfo.setSleepStartTime(cursor.getLong(i + 27));
        dataPullConfigInfo.setSleepEndTime(cursor.getLong(i + 28));
        dataPullConfigInfo.setSportCount(cursor.getInt(i + 29));
        dataPullConfigInfo.setSportStartTime(cursor.getLong(i + 30));
        dataPullConfigInfo.setSportEndTime(cursor.getLong(i + 31));
        dataPullConfigInfo.setStepCount(cursor.getInt(i + 32));
        dataPullConfigInfo.setStepStartTime(cursor.getLong(i + 33));
        dataPullConfigInfo.setStepEndTime(cursor.getLong(i + 34));
        dataPullConfigInfo.setWalkCount(cursor.getInt(i + 35));
        dataPullConfigInfo.setWalkStartTime(cursor.getLong(i + 36));
        dataPullConfigInfo.setWalkEndTime(cursor.getLong(i + 37));
        dataPullConfigInfo.setWeightCount(cursor.getInt(i + 38));
        dataPullConfigInfo.setWeightStartTime(cursor.getLong(i + 39));
        dataPullConfigInfo.setWeightEndTime(cursor.getLong(i + 40));
        dataPullConfigInfo.setTargetCount(cursor.getInt(i + 41));
        dataPullConfigInfo.setTargetStartTime(cursor.getLong(i + 42));
        dataPullConfigInfo.setTargetEndTime(cursor.getLong(i + 43));
        dataPullConfigInfo.setNoiseCount(cursor.getInt(i + 44));
        dataPullConfigInfo.setNoiseStartTime(cursor.getLong(i + 45));
        dataPullConfigInfo.setNoiseEndTime(cursor.getLong(i + 46));
        dataPullConfigInfo.setTemperatureCout(cursor.getLong(i + 47));
        dataPullConfigInfo.setTemperatureStartTime(cursor.getLong(i + 48));
        dataPullConfigInfo.setTemperatureEndTime(cursor.getLong(i + 49));
        dataPullConfigInfo.setRateNoticeCount(cursor.getLong(i + 50));
        dataPullConfigInfo.setRateNoticeStartTime(cursor.getLong(i + 51));
        dataPullConfigInfo.setRateNoticeEndTime(cursor.getLong(i + 52));
        dataPullConfigInfo.setShowTipDialog(cursor.getShort(i + 53) != 0);
        dataPullConfigInfo.setShowState(cursor.getShort(i + 54) != 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(DataPullConfigInfo dataPullConfigInfo, long j) {
        dataPullConfigInfo.setId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(DataPullConfigInfo dataPullConfigInfo) {
        if (dataPullConfigInfo != null) {
            return dataPullConfigInfo.getId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(DataPullConfigInfo dataPullConfigInfo) {
        return dataPullConfigInfo.getId() != null;
    }
}