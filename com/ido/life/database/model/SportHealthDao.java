package com.ido.life.database.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.king.zxing.Intents;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

/* JADX INFO: loaded from: classes2.dex */
public class SportHealthDao extends AbstractDao<SportHealth, Long> {
    public static final String TABLENAME = "SPORT_HEALTH";
    private DaoSession daoSession;
    private final ConvertGps gpsConverter;
    private final ConvertItem heartrateConverter;
    private final ConvertPaceItem paceConverter;
    private final ConvertItem rangeConverter;
    private final ConvertItem rateConverter;
    private final ConvertRealPaceItem realTimePaceConverter;
    private final ConvertSwimSwolf swolfConverter;

    public static class Properties {
        public static final Property Sid = new Property(0, String.class, "sid", false, "SID");
        public static final Property DateTime = new Property(1, String.class, "dateTime", false, "DATE_TIME");
        public static final Property Type = new Property(2, Integer.TYPE, "type", false, Intents.WifiConnect.TYPE);
        public static final Property SubType = new Property(3, Integer.TYPE, "subType", false, "SUB_TYPE");
        public static final Property TotalSeconds = new Property(4, Integer.TYPE, "totalSeconds", false, "TOTAL_SECONDS");
        public static final Property NumCalories = new Property(5, Integer.TYPE, "numCalories", false, "NUM_CALORIES");
        public static final Property NumSteps = new Property(6, Integer.TYPE, "numSteps", false, "NUM_STEPS");
        public static final Property Distance = new Property(7, Integer.TYPE, "distance", false, "DISTANCE");
        public static final Property StartTime = new Property(8, String.class, "startTime", false, "START_TIME");
        public static final Property EndTime = new Property(9, String.class, "endTime", false, "END_TIME");
        public static final Property TargetType = new Property(10, Integer.TYPE, "targetType", false, "TARGET_TYPE");
        public static final Property TargetValue = new Property(11, Integer.TYPE, "targetValue", false, "TARGET_VALUE");
        public static final Property WarmupSeconds = new Property(12, Integer.TYPE, "warmupSeconds", false, "WARMUP_SECONDS");
        public static final Property BurnFatSeconds = new Property(13, Integer.TYPE, "burnFatSeconds", false, "BURN_FAT_SECONDS");
        public static final Property AerobicSeconds = new Property(14, Integer.TYPE, "aerobicSeconds", false, "AEROBIC_SECONDS");
        public static final Property AnaerobicSecond = new Property(15, Integer.TYPE, "anaerobicSecond", false, "ANAEROBIC_SECOND");
        public static final Property ExtremeSecond = new Property(16, Integer.TYPE, "extremeSecond", false, "EXTREME_SECOND");
        public static final Property SourceMac = new Property(17, String.class, "sourceMac", false, "SOURCE_MAC");
        public static final Property DeviceName = new Property(18, String.class, "deviceName", false, "DEVICE_NAME");
        public static final Property SourceType = new Property(19, Integer.TYPE, "sourceType", false, "SOURCE_TYPE");
        public static final Property MinHrValue = new Property(20, Integer.TYPE, "minHrValue", false, "MIN_HR_VALUE");
        public static final Property MaxHrValue = new Property(21, Integer.TYPE, "maxHrValue", false, "MAX_HR_VALUE");
        public static final Property AvgHrValue = new Property(22, Integer.TYPE, "avgHrValue", false, "AVG_HR_VALUE");
        public static final Property MinSpeed = new Property(23, Integer.TYPE, "minSpeed", false, "MIN_SPEED");
        public static final Property MaxSpeed = new Property(24, Integer.TYPE, "maxSpeed", false, "MAX_SPEED");
        public static final Property AvgSpeed = new Property(25, Integer.TYPE, "avgSpeed", false, "AVG_SPEED");
        public static final Property MinPace = new Property(26, Integer.TYPE, "minPace", false, "MIN_PACE");
        public static final Property MaxPace = new Property(27, Integer.TYPE, "maxPace", false, "MAX_PACE");
        public static final Property AvgPace = new Property(28, Integer.TYPE, "avgPace", false, "AVG_PACE");
        public static final Property IsLocus = new Property(29, Integer.TYPE, "isLocus", false, "IS_LOCUS");
        public static final Property StepRange = new Property(30, Integer.TYPE, "stepRange", false, "STEP_RANGE");
        public static final Property MinRate = new Property(31, Integer.TYPE, "minRate", false, "MIN_RATE");
        public static final Property StepRateMax = new Property(32, Integer.TYPE, "stepRateMax", false, "STEP_RATE_MAX");
        public static final Property StepRate = new Property(33, Integer.TYPE, "stepRate", false, "STEP_RATE");
        public static final Property SwimmingPosture = new Property(34, Integer.TYPE, "swimmingPosture", false, "SWIMMING_POSTURE");
        public static final Property TotalStrokesNumber = new Property(35, Integer.TYPE, "totalStrokesNumber", false, "TOTAL_STROKES_NUMBER");
        public static final Property PoolDistance = new Property(36, Integer.TYPE, "poolDistance", false, "POOL_DISTANCE");
        public static final Property Trips = new Property(37, Integer.TYPE, "trips", false, "TRIPS");
        public static final Property BestSWOLF = new Property(38, Integer.TYPE, "bestSWOLF", false, "BEST_SWOLF");
        public static final Property MaxSwolf = new Property(39, Integer.TYPE, "maxSwolf", false, "MAX_SWOLF");
        public static final Property AverageSWOLF = new Property(40, Integer.TYPE, "averageSWOLF", false, "AVERAGE_SWOLF");
        public static final Property SourceOs = new Property(41, Integer.TYPE, "sourceOs", false, "SOURCE_OS");
        public static final Property Gps = new Property(42, String.class, "gps", false, "GPS");
        public static final Property Heartrate = new Property(43, String.class, "heartrate", false, "HEARTRATE");
        public static final Property Rate = new Property(44, String.class, "rate", false, "RATE");
        public static final Property Range = new Property(45, String.class, "range", false, "RANGE");
        public static final Property Pace = new Property(46, String.class, "pace", false, "PACE");
        public static final Property RealTimePace = new Property(47, String.class, "realTimePace", false, "REAL_TIME_PACE");
        public static final Property Swolf = new Property(48, String.class, "swolf", false, "SWOLF");
        public static final Property StepRangeMax = new Property(49, Integer.TYPE, "stepRangeMax", false, "STEP_RANGE_MAX");
        public static final Property IsUploadedStrava = new Property(50, Boolean.TYPE, "isUploadedStrava", false, "IS_UPLOADED_STRAVA");
        public static final Property UploadedStrava = new Property(51, Integer.TYPE, "uploadedStrava", false, "UPLOADED_STRAVA");
        public static final Property IsUploaded = new Property(52, Boolean.TYPE, "isUploaded", false, "IS_UPLOADED");
        public static final Property ActivityId = new Property(53, Long.class, "activityId", true, "_id");
        public static final Property UserId = new Property(54, Long.TYPE, "userId", false, "USER_ID");
        public static final Property Hr_data_vlaue_json = new Property(55, String.class, "hr_data_vlaue_json", false, "HR_DATA_VLAUE_JSON");
        public static final Property StepItem = new Property(56, String.class, "stepItem", false, "STEP_ITEM");
        public static final Property RangeItem = new Property(57, String.class, "rangeItem", false, "RANGE_ITEM");
        public static final Property HrDataIntervalMinute = new Property(58, Integer.TYPE, "hrDataIntervalMinute", false, "HR_DATA_INTERVAL_MINUTE");
        public static final Property Fast_km_speed = new Property(59, Integer.TYPE, "fast_km_speed", false, "FAST_KM_SPEED");
        public static final Property AvgFrequency = new Property(60, Integer.TYPE, "avgFrequency", false, "AVG_FREQUENCY");
        public static final Property MaxFrequency = new Property(61, Integer.TYPE, "maxFrequency", false, "MAX_FREQUENCY");
        public static final Property SwimmingDetailStr = new Property(62, String.class, "swimmingDetailStr", false, "SWIMMING_DETAIL_STR");
        public static final Property IntervalSecond = new Property(63, Integer.TYPE, "intervalSecond", false, "INTERVAL_SECOND");
        public static final Property Timestamp = new Property(64, Long.TYPE, "timestamp", false, "TIMESTAMP");
        public static final Property GpsSourceType = new Property(65, Integer.TYPE, "gpsSourceType", false, "GPS_SOURCE_TYPE");
        public static final Property Icon = new Property(66, String.class, "icon", false, "ICON");
        public static final Property IsLoadDetail = new Property(67, Boolean.TYPE, "isLoadDetail", false, "IS_LOAD_DETAIL");
        public static final Property Vo2max = new Property(68, Integer.TYPE, "vo2max", false, "VO2MAX");
        public static final Property RecoverTime = new Property(69, Integer.TYPE, "recoverTime", false, "RECOVER_TIME");
        public static final Property DiscoverDateTime = new Property(70, String.class, "discoverDateTime", false, "DISCOVER_DATE_TIME");
        public static final Property TrainingEffectScore = new Property(71, Float.TYPE, "trainingEffectScore", false, "TRAINING_EFFECT_SCORE");
        public static final Property IsSupportTrainingEffect = new Property(72, Integer.TYPE, "isSupportTrainingEffect", false, "IS_SUPPORT_TRAINING_EFFECT");
        public static final Property CumulativeClimb = new Property(73, Integer.TYPE, "cumulativeClimb", false, "CUMULATIVE_CLIMB");
        public static final Property CumulativeDecline = new Property(74, Integer.TYPE, "cumulativeDecline", false, "CUMULATIVE_DECLINE");
    }

    @Override // org.greenrobot.greendao.AbstractDao
    protected final boolean isEntityUpdateable() {
        return true;
    }

    public SportHealthDao(DaoConfig daoConfig) {
        super(daoConfig);
        this.gpsConverter = new ConvertGps();
        this.heartrateConverter = new ConvertItem();
        this.rateConverter = new ConvertItem();
        this.rangeConverter = new ConvertItem();
        this.paceConverter = new ConvertPaceItem();
        this.realTimePaceConverter = new ConvertRealPaceItem();
        this.swolfConverter = new ConvertSwimSwolf();
    }

    public SportHealthDao(DaoConfig daoConfig, DaoSession daoSession) {
        super(daoConfig, daoSession);
        this.gpsConverter = new ConvertGps();
        this.heartrateConverter = new ConvertItem();
        this.rateConverter = new ConvertItem();
        this.rangeConverter = new ConvertItem();
        this.paceConverter = new ConvertPaceItem();
        this.realTimePaceConverter = new ConvertRealPaceItem();
        this.swolfConverter = new ConvertSwimSwolf();
        this.daoSession = daoSession;
    }

    public static void createTable(Database database, boolean z) {
        database.execSQL("CREATE TABLE " + (z ? "IF NOT EXISTS " : "") + "\"SPORT_HEALTH\" (\"SID\" TEXT,\"DATE_TIME\" TEXT,\"TYPE\" INTEGER NOT NULL ,\"SUB_TYPE\" INTEGER NOT NULL ,\"TOTAL_SECONDS\" INTEGER NOT NULL ,\"NUM_CALORIES\" INTEGER NOT NULL ,\"NUM_STEPS\" INTEGER NOT NULL ,\"DISTANCE\" INTEGER NOT NULL ,\"START_TIME\" TEXT,\"END_TIME\" TEXT,\"TARGET_TYPE\" INTEGER NOT NULL ,\"TARGET_VALUE\" INTEGER NOT NULL ,\"WARMUP_SECONDS\" INTEGER NOT NULL ,\"BURN_FAT_SECONDS\" INTEGER NOT NULL ,\"AEROBIC_SECONDS\" INTEGER NOT NULL ,\"ANAEROBIC_SECOND\" INTEGER NOT NULL ,\"EXTREME_SECOND\" INTEGER NOT NULL ,\"SOURCE_MAC\" TEXT,\"DEVICE_NAME\" TEXT,\"SOURCE_TYPE\" INTEGER NOT NULL ,\"MIN_HR_VALUE\" INTEGER NOT NULL ,\"MAX_HR_VALUE\" INTEGER NOT NULL ,\"AVG_HR_VALUE\" INTEGER NOT NULL ,\"MIN_SPEED\" INTEGER NOT NULL ,\"MAX_SPEED\" INTEGER NOT NULL ,\"AVG_SPEED\" INTEGER NOT NULL ,\"MIN_PACE\" INTEGER NOT NULL ,\"MAX_PACE\" INTEGER NOT NULL ,\"AVG_PACE\" INTEGER NOT NULL ,\"IS_LOCUS\" INTEGER NOT NULL ,\"STEP_RANGE\" INTEGER NOT NULL ,\"MIN_RATE\" INTEGER NOT NULL ,\"STEP_RATE_MAX\" INTEGER NOT NULL ,\"STEP_RATE\" INTEGER NOT NULL ,\"SWIMMING_POSTURE\" INTEGER NOT NULL ,\"TOTAL_STROKES_NUMBER\" INTEGER NOT NULL ,\"POOL_DISTANCE\" INTEGER NOT NULL ,\"TRIPS\" INTEGER NOT NULL ,\"BEST_SWOLF\" INTEGER NOT NULL ,\"MAX_SWOLF\" INTEGER NOT NULL ,\"AVERAGE_SWOLF\" INTEGER NOT NULL ,\"SOURCE_OS\" INTEGER NOT NULL ,\"GPS\" TEXT,\"HEARTRATE\" TEXT,\"RATE\" TEXT,\"RANGE\" TEXT,\"PACE\" TEXT,\"REAL_TIME_PACE\" TEXT,\"SWOLF\" TEXT,\"STEP_RANGE_MAX\" INTEGER NOT NULL ,\"IS_UPLOADED_STRAVA\" INTEGER NOT NULL ,\"UPLOADED_STRAVA\" INTEGER NOT NULL ,\"IS_UPLOADED\" INTEGER NOT NULL ,\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"HR_DATA_VLAUE_JSON\" TEXT,\"STEP_ITEM\" TEXT,\"RANGE_ITEM\" TEXT,\"HR_DATA_INTERVAL_MINUTE\" INTEGER NOT NULL ,\"FAST_KM_SPEED\" INTEGER NOT NULL ,\"AVG_FREQUENCY\" INTEGER NOT NULL ,\"MAX_FREQUENCY\" INTEGER NOT NULL ,\"SWIMMING_DETAIL_STR\" TEXT,\"INTERVAL_SECOND\" INTEGER NOT NULL ,\"TIMESTAMP\" INTEGER NOT NULL ,\"GPS_SOURCE_TYPE\" INTEGER NOT NULL ,\"ICON\" TEXT,\"IS_LOAD_DETAIL\" INTEGER NOT NULL ,\"VO2MAX\" INTEGER NOT NULL ,\"RECOVER_TIME\" INTEGER NOT NULL ,\"DISCOVER_DATE_TIME\" TEXT,\"TRAINING_EFFECT_SCORE\" REAL NOT NULL ,\"IS_SUPPORT_TRAINING_EFFECT\" INTEGER NOT NULL ,\"CUMULATIVE_CLIMB\" INTEGER NOT NULL ,\"CUMULATIVE_DECLINE\" INTEGER NOT NULL );");
    }

    public static void dropTable(Database database, boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("DROP TABLE ");
        sb.append(z ? "IF EXISTS " : "");
        sb.append("\"SPORT_HEALTH\"");
        database.execSQL(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(DatabaseStatement databaseStatement, SportHealth sportHealth) {
        databaseStatement.clearBindings();
        String sid = sportHealth.getSid();
        if (sid != null) {
            databaseStatement.bindString(1, sid);
        }
        String dateTime = sportHealth.getDateTime();
        if (dateTime != null) {
            databaseStatement.bindString(2, dateTime);
        }
        databaseStatement.bindLong(3, sportHealth.getType());
        databaseStatement.bindLong(4, sportHealth.getSubType());
        databaseStatement.bindLong(5, sportHealth.getTotalSeconds());
        databaseStatement.bindLong(6, sportHealth.getNumCalories());
        databaseStatement.bindLong(7, sportHealth.getNumSteps());
        databaseStatement.bindLong(8, sportHealth.getDistance());
        String startTime = sportHealth.getStartTime();
        if (startTime != null) {
            databaseStatement.bindString(9, startTime);
        }
        String endTime = sportHealth.getEndTime();
        if (endTime != null) {
            databaseStatement.bindString(10, endTime);
        }
        databaseStatement.bindLong(11, sportHealth.getTargetType());
        databaseStatement.bindLong(12, sportHealth.getTargetValue());
        databaseStatement.bindLong(13, sportHealth.getWarmupSeconds());
        databaseStatement.bindLong(14, sportHealth.getBurnFatSeconds());
        databaseStatement.bindLong(15, sportHealth.getAerobicSeconds());
        databaseStatement.bindLong(16, sportHealth.getAnaerobicSecond());
        databaseStatement.bindLong(17, sportHealth.getExtremeSecond());
        String sourceMac = sportHealth.getSourceMac();
        if (sourceMac != null) {
            databaseStatement.bindString(18, sourceMac);
        }
        String deviceName = sportHealth.getDeviceName();
        if (deviceName != null) {
            databaseStatement.bindString(19, deviceName);
        }
        databaseStatement.bindLong(20, sportHealth.getSourceType());
        databaseStatement.bindLong(21, sportHealth.getMinHrValue());
        databaseStatement.bindLong(22, sportHealth.getMaxHrValue());
        databaseStatement.bindLong(23, sportHealth.getAvgHrValue());
        databaseStatement.bindLong(24, sportHealth.getMinSpeed());
        databaseStatement.bindLong(25, sportHealth.getMaxSpeed());
        databaseStatement.bindLong(26, sportHealth.getAvgSpeed());
        databaseStatement.bindLong(27, sportHealth.getMinPace());
        databaseStatement.bindLong(28, sportHealth.getMaxPace());
        databaseStatement.bindLong(29, sportHealth.getAvgPace());
        databaseStatement.bindLong(30, sportHealth.getIsLocus());
        databaseStatement.bindLong(31, sportHealth.getStepRange());
        databaseStatement.bindLong(32, sportHealth.getMinRate());
        databaseStatement.bindLong(33, sportHealth.getStepRateMax());
        databaseStatement.bindLong(34, sportHealth.getStepRate());
        databaseStatement.bindLong(35, sportHealth.getSwimmingPosture());
        databaseStatement.bindLong(36, sportHealth.getTotalStrokesNumber());
        databaseStatement.bindLong(37, sportHealth.getPoolDistance());
        databaseStatement.bindLong(38, sportHealth.getTrips());
        databaseStatement.bindLong(39, sportHealth.getBestSWOLF());
        databaseStatement.bindLong(40, sportHealth.getMaxSwolf());
        databaseStatement.bindLong(41, sportHealth.getAverageSWOLF());
        databaseStatement.bindLong(42, sportHealth.getSourceOs());
        SportGps gps = sportHealth.getGps();
        if (gps != null) {
            databaseStatement.bindString(43, this.gpsConverter.convertToDatabaseValue(gps));
        }
        SportItem heartrate = sportHealth.getHeartrate();
        if (heartrate != null) {
            databaseStatement.bindString(44, this.heartrateConverter.convertToDatabaseValue(heartrate));
        }
        SportItem rate = sportHealth.getRate();
        if (rate != null) {
            databaseStatement.bindString(45, this.rateConverter.convertToDatabaseValue(rate));
        }
        SportItem range = sportHealth.getRange();
        if (range != null) {
            databaseStatement.bindString(46, this.rangeConverter.convertToDatabaseValue(range));
        }
        SportItemPace pace = sportHealth.getPace();
        if (pace != null) {
            databaseStatement.bindString(47, this.paceConverter.convertToDatabaseValue(pace));
        }
        SportRealTimePace realTimePace = sportHealth.getRealTimePace();
        if (realTimePace != null) {
            databaseStatement.bindString(48, this.realTimePaceConverter.convertToDatabaseValue(realTimePace));
        }
        SportSwimSwolf swolf = sportHealth.getSwolf();
        if (swolf != null) {
            databaseStatement.bindString(49, this.swolfConverter.convertToDatabaseValue(swolf));
        }
        databaseStatement.bindLong(50, sportHealth.getStepRangeMax());
        databaseStatement.bindLong(51, sportHealth.getIsUploadedStrava() ? 1L : 0L);
        databaseStatement.bindLong(52, sportHealth.getUploadedStrava());
        databaseStatement.bindLong(53, sportHealth.getIsUploaded() ? 1L : 0L);
        Long activityId = sportHealth.getActivityId();
        if (activityId != null) {
            databaseStatement.bindLong(54, activityId.longValue());
        }
        databaseStatement.bindLong(55, sportHealth.getUserId().longValue());
        String hr_data_vlaue_json = sportHealth.getHr_data_vlaue_json();
        if (hr_data_vlaue_json != null) {
            databaseStatement.bindString(56, hr_data_vlaue_json);
        }
        String stepItem = sportHealth.getStepItem();
        if (stepItem != null) {
            databaseStatement.bindString(57, stepItem);
        }
        String rangeItem = sportHealth.getRangeItem();
        if (rangeItem != null) {
            databaseStatement.bindString(58, rangeItem);
        }
        databaseStatement.bindLong(59, sportHealth.getHrDataIntervalMinute());
        databaseStatement.bindLong(60, sportHealth.getFast_km_speed());
        databaseStatement.bindLong(61, sportHealth.getAvgFrequency());
        databaseStatement.bindLong(62, sportHealth.getMaxFrequency());
        String swimmingDetailStr = sportHealth.getSwimmingDetailStr();
        if (swimmingDetailStr != null) {
            databaseStatement.bindString(63, swimmingDetailStr);
        }
        databaseStatement.bindLong(64, sportHealth.getIntervalSecond());
        databaseStatement.bindLong(65, sportHealth.getTimestamp());
        databaseStatement.bindLong(66, sportHealth.getGpsSourceType());
        String icon = sportHealth.getIcon();
        if (icon != null) {
            databaseStatement.bindString(67, icon);
        }
        databaseStatement.bindLong(68, sportHealth.getIsLoadDetail() ? 1L : 0L);
        databaseStatement.bindLong(69, sportHealth.getVo2max());
        databaseStatement.bindLong(70, sportHealth.getRecoverTime());
        String discoverDateTime = sportHealth.getDiscoverDateTime();
        if (discoverDateTime != null) {
            databaseStatement.bindString(71, discoverDateTime);
        }
        databaseStatement.bindDouble(72, sportHealth.getTrainingEffectScore());
        databaseStatement.bindLong(73, sportHealth.getIsSupportTrainingEffect());
        databaseStatement.bindLong(74, sportHealth.getCumulativeClimb());
        databaseStatement.bindLong(75, sportHealth.getCumulativeDecline());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void bindValues(SQLiteStatement sQLiteStatement, SportHealth sportHealth) {
        sQLiteStatement.clearBindings();
        String sid = sportHealth.getSid();
        if (sid != null) {
            sQLiteStatement.bindString(1, sid);
        }
        String dateTime = sportHealth.getDateTime();
        if (dateTime != null) {
            sQLiteStatement.bindString(2, dateTime);
        }
        sQLiteStatement.bindLong(3, sportHealth.getType());
        sQLiteStatement.bindLong(4, sportHealth.getSubType());
        sQLiteStatement.bindLong(5, sportHealth.getTotalSeconds());
        sQLiteStatement.bindLong(6, sportHealth.getNumCalories());
        sQLiteStatement.bindLong(7, sportHealth.getNumSteps());
        sQLiteStatement.bindLong(8, sportHealth.getDistance());
        String startTime = sportHealth.getStartTime();
        if (startTime != null) {
            sQLiteStatement.bindString(9, startTime);
        }
        String endTime = sportHealth.getEndTime();
        if (endTime != null) {
            sQLiteStatement.bindString(10, endTime);
        }
        sQLiteStatement.bindLong(11, sportHealth.getTargetType());
        sQLiteStatement.bindLong(12, sportHealth.getTargetValue());
        sQLiteStatement.bindLong(13, sportHealth.getWarmupSeconds());
        sQLiteStatement.bindLong(14, sportHealth.getBurnFatSeconds());
        sQLiteStatement.bindLong(15, sportHealth.getAerobicSeconds());
        sQLiteStatement.bindLong(16, sportHealth.getAnaerobicSecond());
        sQLiteStatement.bindLong(17, sportHealth.getExtremeSecond());
        String sourceMac = sportHealth.getSourceMac();
        if (sourceMac != null) {
            sQLiteStatement.bindString(18, sourceMac);
        }
        String deviceName = sportHealth.getDeviceName();
        if (deviceName != null) {
            sQLiteStatement.bindString(19, deviceName);
        }
        sQLiteStatement.bindLong(20, sportHealth.getSourceType());
        sQLiteStatement.bindLong(21, sportHealth.getMinHrValue());
        sQLiteStatement.bindLong(22, sportHealth.getMaxHrValue());
        sQLiteStatement.bindLong(23, sportHealth.getAvgHrValue());
        sQLiteStatement.bindLong(24, sportHealth.getMinSpeed());
        sQLiteStatement.bindLong(25, sportHealth.getMaxSpeed());
        sQLiteStatement.bindLong(26, sportHealth.getAvgSpeed());
        sQLiteStatement.bindLong(27, sportHealth.getMinPace());
        sQLiteStatement.bindLong(28, sportHealth.getMaxPace());
        sQLiteStatement.bindLong(29, sportHealth.getAvgPace());
        sQLiteStatement.bindLong(30, sportHealth.getIsLocus());
        sQLiteStatement.bindLong(31, sportHealth.getStepRange());
        sQLiteStatement.bindLong(32, sportHealth.getMinRate());
        sQLiteStatement.bindLong(33, sportHealth.getStepRateMax());
        sQLiteStatement.bindLong(34, sportHealth.getStepRate());
        sQLiteStatement.bindLong(35, sportHealth.getSwimmingPosture());
        sQLiteStatement.bindLong(36, sportHealth.getTotalStrokesNumber());
        sQLiteStatement.bindLong(37, sportHealth.getPoolDistance());
        sQLiteStatement.bindLong(38, sportHealth.getTrips());
        sQLiteStatement.bindLong(39, sportHealth.getBestSWOLF());
        sQLiteStatement.bindLong(40, sportHealth.getMaxSwolf());
        sQLiteStatement.bindLong(41, sportHealth.getAverageSWOLF());
        sQLiteStatement.bindLong(42, sportHealth.getSourceOs());
        SportGps gps = sportHealth.getGps();
        if (gps != null) {
            sQLiteStatement.bindString(43, this.gpsConverter.convertToDatabaseValue(gps));
        }
        SportItem heartrate = sportHealth.getHeartrate();
        if (heartrate != null) {
            sQLiteStatement.bindString(44, this.heartrateConverter.convertToDatabaseValue(heartrate));
        }
        SportItem rate = sportHealth.getRate();
        if (rate != null) {
            sQLiteStatement.bindString(45, this.rateConverter.convertToDatabaseValue(rate));
        }
        SportItem range = sportHealth.getRange();
        if (range != null) {
            sQLiteStatement.bindString(46, this.rangeConverter.convertToDatabaseValue(range));
        }
        SportItemPace pace = sportHealth.getPace();
        if (pace != null) {
            sQLiteStatement.bindString(47, this.paceConverter.convertToDatabaseValue(pace));
        }
        SportRealTimePace realTimePace = sportHealth.getRealTimePace();
        if (realTimePace != null) {
            sQLiteStatement.bindString(48, this.realTimePaceConverter.convertToDatabaseValue(realTimePace));
        }
        SportSwimSwolf swolf = sportHealth.getSwolf();
        if (swolf != null) {
            sQLiteStatement.bindString(49, this.swolfConverter.convertToDatabaseValue(swolf));
        }
        sQLiteStatement.bindLong(50, sportHealth.getStepRangeMax());
        sQLiteStatement.bindLong(51, sportHealth.getIsUploadedStrava() ? 1L : 0L);
        sQLiteStatement.bindLong(52, sportHealth.getUploadedStrava());
        sQLiteStatement.bindLong(53, sportHealth.getIsUploaded() ? 1L : 0L);
        Long activityId = sportHealth.getActivityId();
        if (activityId != null) {
            sQLiteStatement.bindLong(54, activityId.longValue());
        }
        sQLiteStatement.bindLong(55, sportHealth.getUserId().longValue());
        String hr_data_vlaue_json = sportHealth.getHr_data_vlaue_json();
        if (hr_data_vlaue_json != null) {
            sQLiteStatement.bindString(56, hr_data_vlaue_json);
        }
        String stepItem = sportHealth.getStepItem();
        if (stepItem != null) {
            sQLiteStatement.bindString(57, stepItem);
        }
        String rangeItem = sportHealth.getRangeItem();
        if (rangeItem != null) {
            sQLiteStatement.bindString(58, rangeItem);
        }
        sQLiteStatement.bindLong(59, sportHealth.getHrDataIntervalMinute());
        sQLiteStatement.bindLong(60, sportHealth.getFast_km_speed());
        sQLiteStatement.bindLong(61, sportHealth.getAvgFrequency());
        sQLiteStatement.bindLong(62, sportHealth.getMaxFrequency());
        String swimmingDetailStr = sportHealth.getSwimmingDetailStr();
        if (swimmingDetailStr != null) {
            sQLiteStatement.bindString(63, swimmingDetailStr);
        }
        sQLiteStatement.bindLong(64, sportHealth.getIntervalSecond());
        sQLiteStatement.bindLong(65, sportHealth.getTimestamp());
        sQLiteStatement.bindLong(66, sportHealth.getGpsSourceType());
        String icon = sportHealth.getIcon();
        if (icon != null) {
            sQLiteStatement.bindString(67, icon);
        }
        sQLiteStatement.bindLong(68, sportHealth.getIsLoadDetail() ? 1L : 0L);
        sQLiteStatement.bindLong(69, sportHealth.getVo2max());
        sQLiteStatement.bindLong(70, sportHealth.getRecoverTime());
        String discoverDateTime = sportHealth.getDiscoverDateTime();
        if (discoverDateTime != null) {
            sQLiteStatement.bindString(71, discoverDateTime);
        }
        sQLiteStatement.bindDouble(72, sportHealth.getTrainingEffectScore());
        sQLiteStatement.bindLong(73, sportHealth.getIsSupportTrainingEffect());
        sQLiteStatement.bindLong(74, sportHealth.getCumulativeClimb());
        sQLiteStatement.bindLong(75, sportHealth.getCumulativeDecline());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final void attachEntity(SportHealth sportHealth) {
        super.attachEntity(sportHealth);
        sportHealth.__setDaoSession(this.daoSession);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public Long readKey(Cursor cursor, int i) {
        int i2 = i + 53;
        if (cursor.isNull(i2)) {
            return null;
        }
        return Long.valueOf(cursor.getLong(i2));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.greenrobot.greendao.AbstractDao
    public SportHealth readEntity(Cursor cursor, int i) {
        int i2 = i + 0;
        String string = cursor.isNull(i2) ? null : cursor.getString(i2);
        int i3 = i + 1;
        String string2 = cursor.isNull(i3) ? null : cursor.getString(i3);
        int i4 = cursor.getInt(i + 2);
        int i5 = cursor.getInt(i + 3);
        int i6 = cursor.getInt(i + 4);
        int i7 = cursor.getInt(i + 5);
        int i8 = cursor.getInt(i + 6);
        int i9 = cursor.getInt(i + 7);
        int i10 = i + 8;
        String string3 = cursor.isNull(i10) ? null : cursor.getString(i10);
        int i11 = i + 9;
        String string4 = cursor.isNull(i11) ? null : cursor.getString(i11);
        int i12 = cursor.getInt(i + 10);
        int i13 = cursor.getInt(i + 11);
        int i14 = cursor.getInt(i + 12);
        int i15 = cursor.getInt(i + 13);
        int i16 = cursor.getInt(i + 14);
        int i17 = cursor.getInt(i + 15);
        int i18 = cursor.getInt(i + 16);
        int i19 = i + 17;
        String string5 = cursor.isNull(i19) ? null : cursor.getString(i19);
        int i20 = i + 18;
        String string6 = cursor.isNull(i20) ? null : cursor.getString(i20);
        int i21 = cursor.getInt(i + 19);
        int i22 = cursor.getInt(i + 20);
        int i23 = cursor.getInt(i + 21);
        int i24 = cursor.getInt(i + 22);
        int i25 = cursor.getInt(i + 23);
        int i26 = cursor.getInt(i + 24);
        int i27 = cursor.getInt(i + 25);
        int i28 = cursor.getInt(i + 26);
        int i29 = cursor.getInt(i + 27);
        int i30 = cursor.getInt(i + 28);
        int i31 = cursor.getInt(i + 29);
        int i32 = cursor.getInt(i + 30);
        int i33 = cursor.getInt(i + 31);
        int i34 = cursor.getInt(i + 32);
        int i35 = cursor.getInt(i + 33);
        int i36 = cursor.getInt(i + 34);
        int i37 = cursor.getInt(i + 35);
        int i38 = cursor.getInt(i + 36);
        int i39 = cursor.getInt(i + 37);
        int i40 = cursor.getInt(i + 38);
        int i41 = cursor.getInt(i + 39);
        int i42 = cursor.getInt(i + 40);
        int i43 = cursor.getInt(i + 41);
        int i44 = i + 42;
        SportGps sportGpsConvertToEntityProperty = cursor.isNull(i44) ? null : this.gpsConverter.convertToEntityProperty(cursor.getString(i44));
        int i45 = i + 43;
        SportItem sportItemConvertToEntityProperty = cursor.isNull(i45) ? null : this.heartrateConverter.convertToEntityProperty(cursor.getString(i45));
        int i46 = i + 44;
        SportItem sportItemConvertToEntityProperty2 = cursor.isNull(i46) ? null : this.rateConverter.convertToEntityProperty(cursor.getString(i46));
        int i47 = i + 45;
        SportItem sportItemConvertToEntityProperty3 = cursor.isNull(i47) ? null : this.rangeConverter.convertToEntityProperty(cursor.getString(i47));
        int i48 = i + 46;
        SportItemPace sportItemPaceConvertToEntityProperty = cursor.isNull(i48) ? null : this.paceConverter.convertToEntityProperty(cursor.getString(i48));
        int i49 = i + 47;
        SportRealTimePace sportRealTimePaceConvertToEntityProperty = cursor.isNull(i49) ? null : this.realTimePaceConverter.convertToEntityProperty(cursor.getString(i49));
        int i50 = i + 48;
        SportSwimSwolf sportSwimSwolfConvertToEntityProperty = cursor.isNull(i50) ? null : this.swolfConverter.convertToEntityProperty(cursor.getString(i50));
        int i51 = cursor.getInt(i + 49);
        boolean z = cursor.getShort(i + 50) != 0;
        int i52 = cursor.getInt(i + 51);
        boolean z2 = cursor.getShort(i + 52) != 0;
        int i53 = i + 53;
        Long lValueOf = cursor.isNull(i53) ? null : Long.valueOf(cursor.getLong(i53));
        long j = cursor.getLong(i + 54);
        int i54 = i + 55;
        String string7 = cursor.isNull(i54) ? null : cursor.getString(i54);
        int i55 = i + 56;
        String string8 = cursor.isNull(i55) ? null : cursor.getString(i55);
        int i56 = i + 57;
        String string9 = cursor.isNull(i56) ? null : cursor.getString(i56);
        int i57 = cursor.getInt(i + 58);
        int i58 = cursor.getInt(i + 59);
        int i59 = cursor.getInt(i + 60);
        int i60 = cursor.getInt(i + 61);
        int i61 = i + 62;
        String string10 = cursor.isNull(i61) ? null : cursor.getString(i61);
        int i62 = cursor.getInt(i + 63);
        long j2 = cursor.getLong(i + 64);
        int i63 = cursor.getInt(i + 65);
        int i64 = i + 66;
        String string11 = cursor.isNull(i64) ? null : cursor.getString(i64);
        int i65 = i + 70;
        return new SportHealth(string, string2, i4, i5, i6, i7, i8, i9, string3, string4, i12, i13, i14, i15, i16, i17, i18, string5, string6, i21, i22, i23, i24, i25, i26, i27, i28, i29, i30, i31, i32, i33, i34, i35, i36, i37, i38, i39, i40, i41, i42, i43, sportGpsConvertToEntityProperty, sportItemConvertToEntityProperty, sportItemConvertToEntityProperty2, sportItemConvertToEntityProperty3, sportItemPaceConvertToEntityProperty, sportRealTimePaceConvertToEntityProperty, sportSwimSwolfConvertToEntityProperty, i51, z, i52, z2, lValueOf, j, string7, string8, string9, i57, i58, i59, i60, string10, i62, j2, i63, string11, cursor.getShort(i + 67) != 0, cursor.getInt(i + 68), cursor.getInt(i + 69), cursor.isNull(i65) ? null : cursor.getString(i65), cursor.getFloat(i + 71), cursor.getInt(i + 72), cursor.getInt(i + 73), cursor.getInt(i + 74));
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public void readEntity(Cursor cursor, SportHealth sportHealth, int i) {
        int i2 = i + 0;
        sportHealth.setSid(cursor.isNull(i2) ? null : cursor.getString(i2));
        int i3 = i + 1;
        sportHealth.setDateTime(cursor.isNull(i3) ? null : cursor.getString(i3));
        sportHealth.setType(cursor.getInt(i + 2));
        sportHealth.setSubType(cursor.getInt(i + 3));
        sportHealth.setTotalSeconds(cursor.getInt(i + 4));
        sportHealth.setNumCalories(cursor.getInt(i + 5));
        sportHealth.setNumSteps(cursor.getInt(i + 6));
        sportHealth.setDistance(cursor.getInt(i + 7));
        int i4 = i + 8;
        sportHealth.setStartTime(cursor.isNull(i4) ? null : cursor.getString(i4));
        int i5 = i + 9;
        sportHealth.setEndTime(cursor.isNull(i5) ? null : cursor.getString(i5));
        sportHealth.setTargetType(cursor.getInt(i + 10));
        sportHealth.setTargetValue(cursor.getInt(i + 11));
        sportHealth.setWarmupSeconds(cursor.getInt(i + 12));
        sportHealth.setBurnFatSeconds(cursor.getInt(i + 13));
        sportHealth.setAerobicSeconds(cursor.getInt(i + 14));
        sportHealth.setAnaerobicSecond(cursor.getInt(i + 15));
        sportHealth.setExtremeSecond(cursor.getInt(i + 16));
        int i6 = i + 17;
        sportHealth.setSourceMac(cursor.isNull(i6) ? null : cursor.getString(i6));
        int i7 = i + 18;
        sportHealth.setDeviceName(cursor.isNull(i7) ? null : cursor.getString(i7));
        sportHealth.setSourceType(cursor.getInt(i + 19));
        sportHealth.setMinHrValue(cursor.getInt(i + 20));
        sportHealth.setMaxHrValue(cursor.getInt(i + 21));
        sportHealth.setAvgHrValue(cursor.getInt(i + 22));
        sportHealth.setMinSpeed(cursor.getInt(i + 23));
        sportHealth.setMaxSpeed(cursor.getInt(i + 24));
        sportHealth.setAvgSpeed(cursor.getInt(i + 25));
        sportHealth.setMinPace(cursor.getInt(i + 26));
        sportHealth.setMaxPace(cursor.getInt(i + 27));
        sportHealth.setAvgPace(cursor.getInt(i + 28));
        sportHealth.setIsLocus(cursor.getInt(i + 29));
        sportHealth.setStepRange(cursor.getInt(i + 30));
        sportHealth.setMinRate(cursor.getInt(i + 31));
        sportHealth.setStepRateMax(cursor.getInt(i + 32));
        sportHealth.setStepRate(cursor.getInt(i + 33));
        sportHealth.setSwimmingPosture(cursor.getInt(i + 34));
        sportHealth.setTotalStrokesNumber(cursor.getInt(i + 35));
        sportHealth.setPoolDistance(cursor.getInt(i + 36));
        sportHealth.setTrips(cursor.getInt(i + 37));
        sportHealth.setBestSWOLF(cursor.getInt(i + 38));
        sportHealth.setMaxSwolf(cursor.getInt(i + 39));
        sportHealth.setAverageSWOLF(cursor.getInt(i + 40));
        sportHealth.setSourceOs(cursor.getInt(i + 41));
        int i8 = i + 42;
        sportHealth.setGps(cursor.isNull(i8) ? null : this.gpsConverter.convertToEntityProperty(cursor.getString(i8)));
        int i9 = i + 43;
        sportHealth.setHeartrate(cursor.isNull(i9) ? null : this.heartrateConverter.convertToEntityProperty(cursor.getString(i9)));
        int i10 = i + 44;
        sportHealth.setRate(cursor.isNull(i10) ? null : this.rateConverter.convertToEntityProperty(cursor.getString(i10)));
        int i11 = i + 45;
        sportHealth.setRange(cursor.isNull(i11) ? null : this.rangeConverter.convertToEntityProperty(cursor.getString(i11)));
        int i12 = i + 46;
        sportHealth.setPace(cursor.isNull(i12) ? null : this.paceConverter.convertToEntityProperty(cursor.getString(i12)));
        int i13 = i + 47;
        sportHealth.setRealTimePace(cursor.isNull(i13) ? null : this.realTimePaceConverter.convertToEntityProperty(cursor.getString(i13)));
        int i14 = i + 48;
        sportHealth.setSwolf(cursor.isNull(i14) ? null : this.swolfConverter.convertToEntityProperty(cursor.getString(i14)));
        sportHealth.setStepRangeMax(cursor.getInt(i + 49));
        sportHealth.setIsUploadedStrava(cursor.getShort(i + 50) != 0);
        sportHealth.setUploadedStrava(cursor.getInt(i + 51));
        sportHealth.setIsUploaded(cursor.getShort(i + 52) != 0);
        int i15 = i + 53;
        sportHealth.setActivityId(cursor.isNull(i15) ? null : Long.valueOf(cursor.getLong(i15)));
        sportHealth.setUserId(cursor.getLong(i + 54));
        int i16 = i + 55;
        sportHealth.setHr_data_vlaue_json(cursor.isNull(i16) ? null : cursor.getString(i16));
        int i17 = i + 56;
        sportHealth.setStepItem(cursor.isNull(i17) ? null : cursor.getString(i17));
        int i18 = i + 57;
        sportHealth.setRangeItem(cursor.isNull(i18) ? null : cursor.getString(i18));
        sportHealth.setHrDataIntervalMinute(cursor.getInt(i + 58));
        sportHealth.setFast_km_speed(cursor.getInt(i + 59));
        sportHealth.setAvgFrequency(cursor.getInt(i + 60));
        sportHealth.setMaxFrequency(cursor.getInt(i + 61));
        int i19 = i + 62;
        sportHealth.setSwimmingDetailStr(cursor.isNull(i19) ? null : cursor.getString(i19));
        sportHealth.setIntervalSecond(cursor.getInt(i + 63));
        sportHealth.setTimestamp(cursor.getLong(i + 64));
        sportHealth.setGpsSourceType(cursor.getInt(i + 65));
        int i20 = i + 66;
        sportHealth.setIcon(cursor.isNull(i20) ? null : cursor.getString(i20));
        sportHealth.setIsLoadDetail(cursor.getShort(i + 67) != 0);
        sportHealth.setVo2max(cursor.getInt(i + 68));
        sportHealth.setRecoverTime(cursor.getInt(i + 69));
        int i21 = i + 70;
        sportHealth.setDiscoverDateTime(cursor.isNull(i21) ? null : cursor.getString(i21));
        sportHealth.setTrainingEffectScore(cursor.getFloat(i + 71));
        sportHealth.setIsSupportTrainingEffect(cursor.getInt(i + 72));
        sportHealth.setCumulativeClimb(cursor.getInt(i + 73));
        sportHealth.setCumulativeDecline(cursor.getInt(i + 74));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.greenrobot.greendao.AbstractDao
    public final Long updateKeyAfterInsert(SportHealth sportHealth, long j) {
        sportHealth.setActivityId(Long.valueOf(j));
        return Long.valueOf(j);
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public Long getKey(SportHealth sportHealth) {
        if (sportHealth != null) {
            return sportHealth.getActivityId();
        }
        return null;
    }

    @Override // org.greenrobot.greendao.AbstractDao
    public boolean hasKey(SportHealth sportHealth) {
        return sportHealth.getActivityId() != null;
    }
}