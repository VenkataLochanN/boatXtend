package com.ido.life.database;

import android.database.Cursor;
import android.text.TextUtils;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.NumUtil;
import com.ido.common.utils.TimeUtil;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.database.model.ActiveTimeDayDataDao;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.CalorieDayDataDao;
import com.ido.life.database.model.DaoSession;
import com.ido.life.database.model.GpsColors;
import com.ido.life.database.model.GpsColorsDao;
import com.ido.life.database.model.HealthVolumeDataDao;
import com.ido.life.database.model.MultiDaysWalkTotalData;
import com.ido.life.database.model.MultiDaysWalkTotalDataDao;
import com.ido.life.database.model.ServerBloodOxyDayData;
import com.ido.life.database.model.ServerBloodOxyDayDataDao;
import com.ido.life.database.model.ServerBloodOxyMonthData;
import com.ido.life.database.model.ServerBloodOxyMonthDataDao;
import com.ido.life.database.model.ServerDaysBloodOxyData;
import com.ido.life.database.model.ServerDaysBloodOxyDataDao;
import com.ido.life.database.model.ServerDaysSleepData;
import com.ido.life.database.model.ServerDaysSleepDataDao;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.database.model.ServerHeartRateDayDataDao;
import com.ido.life.database.model.ServerMultiMonthBloodOxyTotalData;
import com.ido.life.database.model.ServerMultiMonthBloodOxyTotalDataDao;
import com.ido.life.database.model.ServerMultiMonthSleepTotalData;
import com.ido.life.database.model.ServerMultiMonthSleepTotalDataDao;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.database.model.ServerSleepDayDataDao;
import com.ido.life.database.model.ServerSleepMonthData;
import com.ido.life.database.model.ServerSleepMonthDataDao;
import com.ido.life.database.model.SportGps;
import com.ido.life.database.model.SportGpsData;
import com.ido.life.database.model.SportGpsDataDao;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.SportHealthDao;
import com.ido.life.database.model.StepDayData;
import com.ido.life.database.model.StepDayDataDao;
import com.ido.life.database.model.StepMonthData;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.database.model.WalkDayDataDao;
import com.ido.life.database.model.WalkMonthTotalData;
import com.ido.life.database.model.WalkMonthTotalDataDao;
import com.ido.life.database.model.WalkYearTotalData;
import com.ido.life.database.model.WalkYearTotalDataDao;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/* JADX INFO: loaded from: classes2.dex */
public class LocalHealthDataManager {
    public static final int DEFAULT_USER_ID = -1;
    private static final String TAG = "LocalHealthDataManager";
    public static final String YEAR_END_DATE = "-12-31";
    public static final String YEAR_START_DATE = "-01-01";
    private static LocalHealthDataManager sLocalHealthDataManager;

    public static LocalHealthDataManager getInstance() {
        if (sLocalHealthDataManager == null) {
            synchronized (LocalHealthDataManager.class) {
                sLocalHealthDataManager = new LocalHealthDataManager();
            }
        }
        return sLocalHealthDataManager;
    }

    private DaoSession getDaoSession() {
        return GreenDaoManager.getInstance().getDaoSession();
    }

    private long getUserId() {
        UserInfo userInfoQueryUserInfo = GreenDaoUtil.queryUserInfo(RunTimeUtil.getInstance().getUserId());
        if (userInfoQueryUserInfo != null) {
            return userInfoQueryUserInfo.getUserId();
        }
        return -1L;
    }

    public void saveStepDayData(StepDayData stepDayData) {
        stepDayData.setUserId(getUserId());
        StepDayData stepDailyDataByDate = getStepDailyDataByDate(stepDayData.getDate());
        if (stepDailyDataByDate == null) {
            getDaoSession().insert(stepDayData);
            return;
        }
        stepDayData.setId(stepDailyDataByDate.getId());
        if (TextUtils.isEmpty(stepDayData.getItems()) && stepDayData.getNumSteps() == stepDailyDataByDate.getNumSteps()) {
            stepDayData.setItems(stepDailyDataByDate.getItems());
        }
        getDaoSession().update(stepDayData);
    }

    public void saveMultiDaysStepData(final List<StepDayData> list, final boolean z) {
        getDaoSession().runInTx(new Runnable() { // from class: com.ido.life.database.-$$Lambda$LocalHealthDataManager$nTQx1C4ML7FfmGElQOjf_1EBV7Y
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$saveMultiDaysStepData$0$LocalHealthDataManager(list, z);
            }
        });
    }

    public /* synthetic */ void lambda$saveMultiDaysStepData$0$LocalHealthDataManager(List list, boolean z) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            StepDayData stepDayData = (StepDayData) it.next();
            stepDayData.setUploaded(z);
            saveStepDayData(stepDayData);
        }
    }

    public void deleteHealthData(Class cls) {
        getDaoSession().delete(cls);
    }

    public void deleteAllHealthData(Class cls) {
        getDaoSession().deleteAll(cls);
    }

    public StepDayData getStepDailyDataByDate(String str) {
        if (TextUtils.isEmpty(str) || getDaoSession().getStepDayDataDao().queryBuilder().where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), StepDayDataDao.Properties.Date.eq(str)).count() == 0) {
            return null;
        }
        return getDaoSession().getStepDayDataDao().queryBuilder().where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), StepDayDataDao.Properties.Date.eq(str)).limit(1).unique();
    }

    public boolean hasStepData(String str) {
        return !TextUtils.isEmpty(str) && getDaoSession().getStepDayDataDao().queryBuilder().where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), StepDayDataDao.Properties.Date.eq(str)).count() > 0;
    }

    public List<StepDayData> getStepDailyDataList(String str, String str2) {
        List<String> dates = DateUtil.getDates(str, str2);
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = dates.iterator();
        while (it.hasNext()) {
            arrayList.add(getStepDailyDataByDate(it.next()));
        }
        return arrayList;
    }

    public List<StepDayData> getStepYearData(long j, int i) {
        return getStepDataByLikeQuery(j, String.valueOf(i));
    }

    public List<StepDayData> getStepMonthData(long j, int i, int i2) {
        return getStepDataByLikeQuery(j, String.format("%d-%02d", Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public List<StepDayData> getStepDataByLikeQuery(long j, String str) {
        return getDaoSession().getStepDayDataDao().queryBuilder().where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(j)), StepDayDataDao.Properties.Date.like("%" + str + "%")).list();
    }

    public long getStepDailyDataListNonNull(long j, String str, String str2) {
        return getDaoSession().getStepDayDataDao().queryBuilder().where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(j)), StepDayDataDao.Properties.Date.ge(str), StepDayDataDao.Properties.Date.le(str2)).count();
    }

    public List<StepDayData> getAllStepDataList() {
        return getDaoSession().queryBuilder(StepDayData.class).where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), new WhereCondition[0]).list();
    }

    public StepDayData getOldestStepData() {
        if (getDaoSession().getStepDayDataDao().queryBuilder().where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), new WhereCondition[0]).count() <= 0) {
            return null;
        }
        return getDaoSession().getStepDayDataDao().queryBuilder().where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), new WhereCondition[0]).orderAsc(StepDayDataDao.Properties.Timestamp).limit(1).unique();
    }

    public List<ActiveTimeDayData> getAllActivityDataList() {
        return getDaoSession().queryBuilder(ActiveTimeDayData.class).where(ActiveTimeDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), new WhereCondition[0]).list();
    }

    public List<CalorieDayData> getAllCalorieDataList() {
        return getDaoSession().queryBuilder(CalorieDayData.class).where(CalorieDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), new WhereCondition[0]).list();
    }

    public void saveGpsColor(GpsColors gpsColors) {
        CommonLogUtil.d(TAG, "savAppGpsTrace: " + gpsColors.toString());
        GpsColors gpsColors2 = getGpsColors(gpsColors.getDateTime());
        if (gpsColors2 == null) {
            gpsColors.setUserId(getUserId());
            getDaoSession().insertOrReplace(gpsColors);
        } else {
            gpsColors.setId(gpsColors2.getId());
            getDaoSession().update(gpsColors);
        }
    }

    public GpsColors getGpsColors(String str) {
        CommonLogUtil.d(TAG, "getActivityData: " + str + AppInfo.DELIM + getUserId());
        List list = getDaoSession().queryBuilder(GpsColors.class).where(GpsColorsDao.Properties.UserId.eq(Long.valueOf(getUserId())), GpsColorsDao.Properties.DateTime.eq(str)).list();
        if (list == null || list.size() == 0) {
            return null;
        }
        return (GpsColors) list.get(0);
    }

    public StepDayData getStepDailyDataRecent(long j) {
        if (getDaoSession().getStepDayDataDao().queryBuilder().where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(j)), StepDayDataDao.Properties.NumSteps.gt(0)).count() == 0) {
            return null;
        }
        return getDaoSession().getStepDayDataDao().queryBuilder().where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(j)), StepDayDataDao.Properties.NumSteps.gt(0)).orderDesc(StepDayDataDao.Properties.Date, StepDayDataDao.Properties.Timestamp).limit(1).unique();
    }

    public List<StepDayData> getAllNotUploadStepDayData() {
        return getDaoSession().getStepDayDataDao().queryBuilder().where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), StepDayDataDao.Properties.Uploaded.eq(false), StepDayDataDao.Properties.Items.isNotNull()).list();
    }

    public void saveMonthStepData(StepMonthData stepMonthData) {
        if (stepMonthData == null) {
            return;
        }
        stepMonthData.setUserId(getUserId());
        StepMonthData monthStepData = getMonthStepData(stepMonthData.getMonth());
        if (monthStepData == null) {
            getDaoSession().insert(stepMonthData);
        } else {
            stepMonthData.setId(monthStepData.getId());
            getDaoSession().update(stepMonthData);
        }
    }

    public StepMonthData getMonthStepData(String str) {
        int numSteps;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StepMonthData stepMonthData = new StepMonthData();
        QueryBuilder queryBuilder = getDaoSession().queryBuilder(StepDayData.class);
        WhereCondition whereConditionEq = StepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId()));
        WhereCondition whereConditionLike = StepDayDataDao.Properties.Date.like(str + "%");
        int i = 0;
        List list = queryBuilder.where(whereConditionEq, whereConditionLike).list();
        if (list == null || list.size() == 0) {
            stepMonthData.setTotalSteps(0);
            stepMonthData.setAvgSteps(0);
            numSteps = 0;
        } else {
            int i2 = 0;
            numSteps = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                numSteps += ((StepDayData) list.get(i3)).getNumSteps();
                if (((StepDayData) list.get(i3)).getNumSteps() > 0) {
                    i2++;
                }
            }
            if (i2 > 0) {
                i = numSteps / i2;
            }
        }
        stepMonthData.setTotalSteps(numSteps);
        stepMonthData.setAvgSteps(i);
        return stepMonthData;
    }

    public List<StepMonthData> getMonthStepDataAnnual(int i) {
        List<String> monthList = getMonthList(i);
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = monthList.iterator();
        while (it.hasNext()) {
            arrayList.add(getMonthStepData(it.next()));
        }
        return arrayList;
    }

    private List<String> getMonthList(int i) {
        ArrayList arrayList = new ArrayList();
        int[] currentDate = DateUtil.getCurrentDate();
        int i2 = 0;
        int i3 = i == currentDate[0] ? currentDate[1] : 12;
        while (i2 < i3) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append("-");
            i2++;
            sb.append(NumUtil.numberFormat(i2));
            arrayList.add(sb.toString());
        }
        return arrayList;
    }

    public void saveHeartRateDayData(ServerHeartRateDayData serverHeartRateDayData) {
        if (serverHeartRateDayData == null) {
            return;
        }
        ServerHeartRateDayDataDao serverHeartRateDayDataDao = getDaoSession().getServerHeartRateDayDataDao();
        List<ServerHeartRateDayData> list = serverHeartRateDayDataDao.queryBuilder().where(ServerHeartRateDayDataDao.Properties.Date.eq(serverHeartRateDayData.getDate()), new WhereCondition[0]).list();
        if (list != null && list.size() > 0) {
            Iterator<ServerHeartRateDayData> it = list.iterator();
            while (it.hasNext()) {
                it.next().delete();
            }
            list.clear();
        }
        serverHeartRateDayDataDao.insert(serverHeartRateDayData);
    }

    public ServerHeartRateDayData getHeartRateDailyDataByDate(long j, String str) {
        List list = getDaoSession().queryBuilder(ServerHeartRateDayData.class).where(ServerHeartRateDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerHeartRateDayDataDao.Properties.Date.eq(str)).list();
        if (list == null || list.size() == 0) {
            return null;
        }
        return (ServerHeartRateDayData) list.get(0);
    }

    public ServerHeartRateDayData getNearHeartRateDailyData(long j) {
        if (getDaoSession().getServerHeartRateDayDataDao().queryBuilder().where(ServerHeartRateDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerHeartRateDayDataDao.Properties.LatestValue.ge(20)).count() == 0) {
            return null;
        }
        return getDaoSession().getServerHeartRateDayDataDao().queryBuilder().where(ServerHeartRateDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerHeartRateDayDataDao.Properties.LatestValue.ge(20)).orderDesc(ServerHeartRateDayDataDao.Properties.Date, ServerHeartRateDayDataDao.Properties.Timestamp).limit(1).unique();
    }

    public ServerHeartRateDayData getTodayLatestHeartRateDailyData(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        if (getDaoSession().getServerHeartRateDayDataDao().queryBuilder().where(ServerHeartRateDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerHeartRateDayDataDao.Properties.Date.eq(DateUtil.format(DateUtil.getTodayDate(), DateUtil.DATE_FORMAT_YMD)), ServerHeartRateDayDataDao.Properties.SourceMac.eq(str), ServerHeartRateDayDataDao.Properties.LatestValue.gt(0)).count() == 0) {
            return null;
        }
        return getDaoSession().getServerHeartRateDayDataDao().queryBuilder().where(ServerHeartRateDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerHeartRateDayDataDao.Properties.Date.eq(DateUtil.format(DateUtil.getTodayDate(), DateUtil.DATE_FORMAT_YMD)), ServerHeartRateDayDataDao.Properties.SourceMac.eq(str), ServerHeartRateDayDataDao.Properties.LatestValue.gt(0)).orderDesc(ServerHeartRateDayDataDao.Properties.Timestamp).limit(1).unique();
    }

    public List<ServerHeartRateDayData> getAllNotUploadHeartRateDayData(long j) {
        return getDaoSession().queryBuilder(ServerHeartRateDayData.class).where(ServerHeartRateDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerHeartRateDayDataDao.Properties.UploadSuccess.eq(false), ServerHeartRateDayDataDao.Properties.Items.isNotNull()).list();
    }

    public List<ServerHeartRateDayData> getAllHeartRateDayData(long j) {
        return getDaoSession().queryBuilder(ServerHeartRateDayData.class).where(ServerHeartRateDayDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).list();
    }

    public List<ServerHeartRateDayData> getHeartRateDailyDataList(long j, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        List<String> dates = DateUtil.getDates(str, str2);
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = dates.iterator();
        while (it.hasNext()) {
            ServerHeartRateDayData heartRateDailyDataByDate = getHeartRateDailyDataByDate(j, it.next());
            if (heartRateDailyDataByDate != null) {
                arrayList.add(heartRateDailyDataByDate);
            }
        }
        return arrayList;
    }

    public List<ServerHeartRateDayData> getYearHeartRate(long j, int i) {
        return getDaoSession().getServerHeartRateDayDataDao().queryBuilder().where(ServerHeartRateDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerHeartRateDayDataDao.Properties.Date.like("%" + i + "%")).list();
    }

    public long queryHeartRateRecordCount(long j) {
        return getDaoSession().getServerHeartRateDayDataDao().queryBuilder().where(ServerHeartRateDayDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count();
    }

    public void saveSleepDayData(ServerSleepDayData serverSleepDayData) {
        if (serverSleepDayData == null || TextUtils.isEmpty(serverSleepDayData.getDate())) {
            return;
        }
        serverSleepDayData.setUserId(getUserId());
        deleteOtherDeviceSleepData(serverSleepDayData.getDate(), serverSleepDayData.getSourceMac());
        ServerSleepDayData sleepDailyDataByDate = getSleepDailyDataByDate(serverSleepDayData.getDate(), serverSleepDayData.getStartTime(), serverSleepDayData.getEndTime());
        if (sleepDailyDataByDate == null) {
            getDaoSession().insert(serverSleepDayData);
            return;
        }
        serverSleepDayData.setId(sleepDailyDataByDate.getId());
        if (TextUtils.isEmpty(serverSleepDayData.getItems()) && serverSleepDayData.getTotalSeconds() == sleepDailyDataByDate.getTotalSeconds()) {
            serverSleepDayData.setItems(sleepDailyDataByDate.getItems());
        }
        getDaoSession().update(serverSleepDayData);
    }

    private void deleteOtherDeviceSleepData(String str, String str2) {
        List<ServerSleepDayData> otherDeviceSleepDailyDataByDate = getOtherDeviceSleepDailyDataByDate(str, str2);
        if (otherDeviceSleepDailyDataByDate != null) {
            for (ServerSleepDayData serverSleepDayData : otherDeviceSleepDailyDataByDate) {
                if (serverSleepDayData != null) {
                    getDaoSession().delete(serverSleepDayData);
                }
            }
        }
    }

    public List<ServerSleepDayData> getOtherDeviceSleepDailyDataByDate(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return getDaoSession().queryBuilder(ServerSleepDayData.class).where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerSleepDayDataDao.Properties.Date.eq(str), ServerSleepDayDataDao.Properties.SourceMac.notEq(str2)).list();
    }

    public List<ServerSleepDayData> getDeviceSleepDailyDataByDate(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return getDaoSession().queryBuilder(ServerSleepDayData.class).where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerSleepDayDataDao.Properties.Date.eq(str), ServerSleepDayDataDao.Properties.SourceMac.eq(str2)).list();
    }

    public ServerSleepDayData getSleepDailyDataByDate(String str) {
        List list;
        if (TextUtils.isEmpty(str) || (list = getDaoSession().queryBuilder(ServerSleepDayData.class).where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerSleepDayDataDao.Properties.Date.eq(str)).orderDesc(ServerSleepDayDataDao.Properties.EndTimeMinuteOffset).list()) == null || list.size() == 0) {
            return null;
        }
        return (ServerSleepDayData) list.get(0);
    }

    public List<ServerSleepDayData> querySleepDailyDataByDate(long j, String str) {
        return getDaoSession().getServerSleepDayDataDao().queryBuilder().where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerSleepDayDataDao.Properties.Date.eq(str)).list();
    }

    public boolean hasSleepData(String str) {
        return !TextUtils.isEmpty(str) && getDaoSession().getServerSleepDayDataDao().queryBuilder().where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerSleepDayDataDao.Properties.Date.eq(str)).count() > 0;
    }

    public List<ServerSleepDayData> getSleepListByDate(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return getDaoSession().queryBuilder(ServerSleepDayData.class).where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerSleepDayDataDao.Properties.Date.eq(str)).orderAsc(ServerSleepDayDataDao.Properties.EndTimeMinuteOffset).list();
    }

    public List<ServerSleepDayData> getMonthSleepList(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int[] iArrYearMonthDay = DateUtil.yearMonthDay(str);
        return getDaoSession().queryBuilder(ServerSleepDayData.class).where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerSleepDayDataDao.Properties.Date.like(iArrYearMonthDay[0] + "-" + NumUtil.numberFormat(iArrYearMonthDay[1]) + "-%")).orderAsc(ServerSleepDayDataDao.Properties.EndTimeMinuteOffset).list();
    }

    public ServerSleepDayData getNearestSleep() {
        if (getDaoSession().getServerSleepDayDataDao().queryBuilder().where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerSleepDayDataDao.Properties.Timestamp.le(Long.valueOf(System.currentTimeMillis()))).count() > 0) {
            return getDaoSession().getServerSleepDayDataDao().queryBuilder().where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerSleepDayDataDao.Properties.Timestamp.le(Long.valueOf(System.currentTimeMillis()))).orderDesc(ServerSleepDayDataDao.Properties.Timestamp).limit(1).unique();
        }
        return null;
    }

    public ServerSleepDayData getSleepDailyDataByDate(String str, String str2, String str3) {
        List list;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || (list = getDaoSession().queryBuilder(ServerSleepDayData.class).where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerSleepDayDataDao.Properties.Date.eq(str), ServerSleepDayDataDao.Properties.StartTime.eq(str2), ServerSleepDayDataDao.Properties.EndTime.eq(str3)).list()) == null || list.size() == 0) {
            return null;
        }
        return (ServerSleepDayData) list.get(0);
    }

    public List<ServerSleepDayData> getAllNotUploadSleepDayData() {
        return getDaoSession().getServerSleepDayDataDao().queryBuilder().where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerSleepDayDataDao.Properties.Uploaded.eq(false), ServerSleepDayDataDao.Properties.Items.isNotNull()).orderDesc(ServerSleepDayDataDao.Properties.EndTime).list();
    }

    public void saveMultiDaysSleepTotalData(ServerDaysSleepData serverDaysSleepData) {
        if (serverDaysSleepData == null || TextUtils.isEmpty(serverDaysSleepData.getStartDate()) || TextUtils.isEmpty(serverDaysSleepData.getEndDate())) {
            return;
        }
        serverDaysSleepData.setUserId(getUserId());
        ServerDaysSleepData sleepMultiDaysTotalData = getSleepMultiDaysTotalData(serverDaysSleepData.getStartDate(), serverDaysSleepData.getEndDate());
        if (sleepMultiDaysTotalData == null) {
            getDaoSession().insert(serverDaysSleepData);
        } else {
            serverDaysSleepData.setId(sleepMultiDaysTotalData.getId());
            getDaoSession().update(serverDaysSleepData);
        }
    }

    public ServerDaysSleepData getSleepMultiDaysTotalData(String str, String str2) {
        List list;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (list = getDaoSession().queryBuilder(ServerDaysSleepData.class).where(ServerDaysSleepDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerDaysSleepDataDao.Properties.StartDate.eq(str), ServerDaysSleepDataDao.Properties.EndDate.eq(str2)).list()) == null || list.size() == 0) {
            return null;
        }
        return (ServerDaysSleepData) list.get(0);
    }

    public void saveMultiDaysSleepData(final List<ServerSleepDayData> list, final boolean z) {
        getDaoSession().runInTx(new Runnable() { // from class: com.ido.life.database.-$$Lambda$LocalHealthDataManager$dn-9ljdvEiH4jkBLmPeUX-BceZc
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$saveMultiDaysSleepData$1$LocalHealthDataManager(list, z);
            }
        });
    }

    public /* synthetic */ void lambda$saveMultiDaysSleepData$1$LocalHealthDataManager(List list, boolean z) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ServerSleepDayData serverSleepDayData = (ServerSleepDayData) it.next();
            serverSleepDayData.setUploaded(z);
            saveSleepDayData(serverSleepDayData);
        }
    }

    public List<ServerSleepDayData> getSleepDailyDataList(String str, String str2, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (z) {
                List<ServerSleepDayData> monthSleepList = getMonthSleepList(str);
                if (monthSleepList == null || monthSleepList.isEmpty()) {
                    return arrayList;
                }
                arrayList.addAll(monthSleepList);
            } else {
                Iterator<String> it = DateUtil.getDates(str, str2).iterator();
                while (it.hasNext()) {
                    List<ServerSleepDayData> sleepListByDate = getSleepListByDate(it.next());
                    if (sleepListByDate != null && !sleepListByDate.isEmpty()) {
                        arrayList.addAll(sleepListByDate);
                    } else {
                        arrayList.add(null);
                    }
                }
            }
        }
        return arrayList;
    }

    public void saveYearSleep(ServerMultiMonthSleepTotalData serverMultiMonthSleepTotalData) {
        if (serverMultiMonthSleepTotalData == null || TextUtils.isEmpty(serverMultiMonthSleepTotalData.getStartDate())) {
            return;
        }
        serverMultiMonthSleepTotalData.setUserId(getUserId());
        ServerMultiMonthSleepTotalData yearSleep = getYearSleep(serverMultiMonthSleepTotalData.getStartDate());
        if (yearSleep == null) {
            getDaoSession().insert(serverMultiMonthSleepTotalData);
        } else {
            serverMultiMonthSleepTotalData.setId(yearSleep.getId());
            getDaoSession().update(serverMultiMonthSleepTotalData);
        }
    }

    public ServerMultiMonthSleepTotalData getYearSleep(int i) {
        return getYearSleep(i + YEAR_START_DATE);
    }

    private ServerMultiMonthSleepTotalData getYearSleep(String str) {
        List list;
        if (TextUtils.isEmpty(str) || (list = getDaoSession().queryBuilder(ServerMultiMonthSleepTotalData.class).where(ServerMultiMonthSleepTotalDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerMultiMonthSleepTotalDataDao.Properties.StartDate.eq(str)).list()) == null || list.size() == 0) {
            return null;
        }
        return (ServerMultiMonthSleepTotalData) list.get(0);
    }

    public void saveMonthSleep(ServerSleepMonthData serverSleepMonthData) {
        if (serverSleepMonthData == null || TextUtils.isEmpty(serverSleepMonthData.getMonth())) {
            return;
        }
        serverSleepMonthData.setUserId(getUserId());
        ServerSleepMonthData monthSleep = getMonthSleep(serverSleepMonthData.getMonth());
        if (monthSleep == null) {
            getDaoSession().insert(serverSleepMonthData);
        } else {
            serverSleepMonthData.setId(monthSleep.getId());
            getDaoSession().update(serverSleepMonthData);
        }
    }

    public void saveMultiMonthSleepData(final List<ServerSleepMonthData> list) {
        getDaoSession().runInTx(new Runnable() { // from class: com.ido.life.database.-$$Lambda$LocalHealthDataManager$RcnaHtCJg20YzqX8nMeNYU-Qe8g
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$saveMultiMonthSleepData$2$LocalHealthDataManager(list);
            }
        });
    }

    public /* synthetic */ void lambda$saveMultiMonthSleepData$2$LocalHealthDataManager(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            saveMonthSleep((ServerSleepMonthData) it.next());
        }
    }

    public ServerSleepMonthData getMonthSleep(String str) {
        List list;
        if (TextUtils.isEmpty(str) || (list = getDaoSession().queryBuilder(ServerSleepMonthData.class).where(ServerSleepMonthDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerSleepMonthDataDao.Properties.Month.eq(str)).list()) == null || list.size() == 0) {
            return null;
        }
        return (ServerSleepMonthData) list.get(0);
    }

    public List<ServerSleepMonthData> getMonthSleepAnnual(int i) {
        List<String> monthList = getMonthList(i);
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = monthList.iterator();
        while (it.hasNext()) {
            arrayList.add(getMonthSleep(it.next()));
        }
        return arrayList;
    }

    public List<ServerSleepDayData> getAllSleepDayData() {
        return getDaoSession().queryBuilder(ServerSleepDayData.class).where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), new WhereCondition[0]).list();
    }

    public List<SportHealth> getAllSportRecordData() {
        return getDaoSession().queryBuilder(SportHealth.class).where(SportHealthDao.Properties.UserId.eq(Long.valueOf(getUserId())), new WhereCondition[0]).list();
    }

    public long getSportRecordCount() {
        return getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.UserId.eq(Long.valueOf(getUserId())), new WhereCondition[0]).count();
    }

    public void saveCalorieDayData(CalorieDayData calorieDayData) {
        calorieDayData.setUserId(getUserId());
        CalorieDayData calorieDailyDataByDate = getCalorieDailyDataByDate(getUserId(), calorieDayData.getDate());
        if (calorieDailyDataByDate == null) {
            getDaoSession().insert(calorieDayData);
            return;
        }
        calorieDayData.setId(calorieDailyDataByDate.getId());
        if (TextUtils.isEmpty(calorieDayData.getItems()) && calorieDayData.getTotalCalorie() == calorieDailyDataByDate.getTotalCalorie()) {
            calorieDayData.setItems(calorieDailyDataByDate.getItems());
        }
        getDaoSession().update(calorieDayData);
    }

    public CalorieDayData getCalorieDailyDataByDate(long j, String str) {
        if (TextUtils.isEmpty(str) || getDaoSession().getCalorieDayDataDao().queryBuilder().where(CalorieDayDataDao.Properties.UserId.eq(Long.valueOf(j)), CalorieDayDataDao.Properties.Date.eq(str)).count() == 0) {
            return null;
        }
        return getDaoSession().getCalorieDayDataDao().queryBuilder().where(CalorieDayDataDao.Properties.UserId.eq(Long.valueOf(j)), CalorieDayDataDao.Properties.Date.eq(str)).limit(1).unique();
    }

    public CalorieDayData getCalorieDailyDailyDataRecent(long j) {
        if (getDaoSession().getCalorieDayDataDao().queryBuilder().where(CalorieDayDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).whereOr(CalorieDayDataDao.Properties.ActivityCalorie.gt(0), CalorieDayDataDao.Properties.TotalCalorie.gt(0), new WhereCondition[0]).count() == 0) {
            return null;
        }
        return getDaoSession().getCalorieDayDataDao().queryBuilder().where(CalorieDayDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).whereOr(CalorieDayDataDao.Properties.ActivityCalorie.gt(0), CalorieDayDataDao.Properties.TotalCalorie.gt(0), new WhereCondition[0]).orderDesc(CalorieDayDataDao.Properties.Date, CalorieDayDataDao.Properties.Timestamp).limit(1).unique();
    }

    public boolean hasCalorieDayData(String str) {
        return !TextUtils.isEmpty(str) && getDaoSession().getCalorieDayDataDao().queryBuilder().where(CalorieDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), CalorieDayDataDao.Properties.Date.eq(str)).count() > 0;
    }

    public List<CalorieDayData> getCalorieYearData(long j, int i) {
        return getCalorieByLikeQuery(j, String.valueOf(i));
    }

    public List<CalorieDayData> getCalorieMonthData(long j, int i, int i2) {
        return getCalorieByLikeQuery(j, String.format("%d-%02d", Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public List<CalorieDayData> getCalorieByLikeQuery(long j, String str) {
        return getDaoSession().getCalorieDayDataDao().queryBuilder().where(CalorieDayDataDao.Properties.UserId.eq(Long.valueOf(j)), CalorieDayDataDao.Properties.Date.like("%" + str + "%")).list();
    }

    public List<CalorieDayData> getAllNotUploadCalorieDayData() {
        return getDaoSession().getCalorieDayDataDao().queryBuilder().where(CalorieDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), CalorieDayDataDao.Properties.Uploaded.eq(false), CalorieDayDataDao.Properties.Items.isNotNull()).list();
    }

    public List<CalorieDayData> getCalorieDailyDataList(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = DateUtil.getDates(str, str2).iterator();
        while (it.hasNext()) {
            arrayList.add(getCalorieDailyDataByDate(getUserId(), it.next()));
        }
        return arrayList;
    }

    public void saveActiveTimeDayData(ActiveTimeDayData activeTimeDayData) {
        activeTimeDayData.setUserId(getUserId());
        ActiveTimeDayData activeTimeDailyDataByDate = getActiveTimeDailyDataByDate(activeTimeDayData.getDate());
        if (activeTimeDailyDataByDate == null) {
            getDaoSession().insert(activeTimeDayData);
            return;
        }
        activeTimeDayData.setId(activeTimeDailyDataByDate.getId());
        if (TextUtils.isEmpty(activeTimeDayData.getItems()) && activeTimeDayData.getTotalSeconds() == activeTimeDailyDataByDate.getTotalSeconds()) {
            activeTimeDayData.setItems(activeTimeDailyDataByDate.getItems());
        }
        getDaoSession().update(activeTimeDayData);
    }

    public ActiveTimeDayData getActiveTimeDailyDataByDate(String str) {
        if (getDaoSession().getActiveTimeDayDataDao().queryBuilder().where(ActiveTimeDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ActiveTimeDayDataDao.Properties.Date.eq(str)).count() == 0) {
            return null;
        }
        return getDaoSession().getActiveTimeDayDataDao().queryBuilder().where(ActiveTimeDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ActiveTimeDayDataDao.Properties.Date.eq(str)).limit(1).unique();
    }

    public boolean hasActiveTimeDayData(String str) {
        return !TextUtils.isEmpty(str) && getDaoSession().getActiveTimeDayDataDao().queryBuilder().where(ActiveTimeDayDataDao.Properties.Date.eq(str), ActiveTimeDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId()))).count() > 0;
    }

    public ActiveTimeDayData getActiveTimeDailyDataRecent(long j) {
        if (getDaoSession().getActiveTimeDayDataDao().queryBuilder().where(ActiveTimeDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ActiveTimeDayDataDao.Properties.TotalSeconds.gt(0)).count() == 0) {
            return null;
        }
        return getDaoSession().getActiveTimeDayDataDao().queryBuilder().where(ActiveTimeDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ActiveTimeDayDataDao.Properties.TotalSeconds.gt(0)).orderDesc(ActiveTimeDayDataDao.Properties.Date, ActiveTimeDayDataDao.Properties.Timestamp).limit(1).unique();
    }

    public List<ActiveTimeDayData> getAllNotUploadActiveTimeDayData() {
        return getDaoSession().getActiveTimeDayDataDao().queryBuilder().where(ActiveTimeDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ActiveTimeDayDataDao.Properties.Uploaded.eq(false), ActiveTimeDayDataDao.Properties.Items.isNotNull()).list();
    }

    public List<ActiveTimeDayData> getActiveTimeDailyDataList(String str, String str2) {
        List<String> dates = DateUtil.getDates(str, str2);
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = dates.iterator();
        while (it.hasNext()) {
            arrayList.add(getActiveTimeDailyDataByDate(it.next()));
        }
        return arrayList;
    }

    public List<ActiveTimeDayData> getActiveTimeByYear(long j, int i) {
        return getActiveTimeLikeQuery(j, String.valueOf(i));
    }

    public List<ActiveTimeDayData> getActiveTimeByMonth(long j, int i, int i2) {
        return getActiveTimeLikeQuery(j, String.format("%d-%02d", Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public List<ActiveTimeDayData> getActiveTimeLikeQuery(long j, String str) {
        return getDaoSession().getActiveTimeDayDataDao().queryBuilder().where(ActiveTimeDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ActiveTimeDayDataDao.Properties.Date.like("%" + str + "%")).list();
    }

    public void savSportGpsData(SportGpsData sportGpsData, long j) {
        if (sportGpsData == null) {
            return;
        }
        sportGpsData.setUserId(j);
        SportGpsData sportGpsData2 = getSportGpsData(j, sportGpsData.timeMillis);
        if (sportGpsData2 == null) {
            getDaoSession().insertOrReplace(sportGpsData);
        } else {
            sportGpsData.setId(sportGpsData2.getId());
            getDaoSession().update(sportGpsData);
        }
    }

    public void savSportGpsData(SportGpsData sportGpsData) {
        savSportGpsData(sportGpsData, getUserId());
    }

    public void saveAppGpsFromTrace(SportGps sportGps, String str) {
        saveAppGpsFromTrace(getUserId(), sportGps, str);
    }

    public void saveAppGpsFromTrace(long j, SportGps sportGps, String str) {
        if (sportGps == null || TextUtils.isEmpty(sportGps.getItems())) {
            return;
        }
        SportGpsData sportGpsData = new SportGpsData();
        sportGpsData.setUserId(j);
        sportGpsData.setTimeMillis(TimeUtil.convTimeYmdhmsToLong(str));
        sportGpsData.setGpsData(sportGps);
        sportGpsData.setDown(true);
        savSportGpsData(sportGpsData);
    }

    public boolean hasSportRecord(long j, String str, String str2) {
        List<String> dates;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (dates = DateUtil.getDates(str, str2)) == null || dates.size() == 0) {
            return false;
        }
        int size = dates.size();
        for (int i = 0; i < size; i++) {
            if (getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.DateTime.like("%" + dates.get(i) + "%"), SportHealthDao.Properties.UserId.eq(Long.valueOf(j))).count() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSleepRecord(long j, String str, String str2) {
        List<String> dates;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (dates = DateUtil.getDates(str, str2)) == null || dates.size() == 0) {
            return false;
        }
        int size = dates.size();
        for (int i = 0; i < size; i++) {
            if (getDaoSession().getServerSleepDayDataDao().queryBuilder().where(ServerSleepDayDataDao.Properties.Date.eq(dates.get(i)), ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(j))).count() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean hasHeartRateRecord(long j, String str, String str2) {
        List<String> dates;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (dates = DateUtil.getDates(str, str2)) == null || dates.size() == 0) {
            return false;
        }
        int size = dates.size();
        for (int i = 0; i < size; i++) {
            if (getDaoSession().getServerHeartRateDayDataDao().queryBuilder().where(ServerHeartRateDayDataDao.Properties.Date.eq(dates.get(i)), ServerHeartRateDayDataDao.Properties.UserId.eq(Long.valueOf(j))).count() > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean hasHeartRate(String str) {
        return !TextUtils.isEmpty(str) && getDaoSession().getServerHeartRateDayDataDao().queryBuilder().where(ServerHeartRateDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerHeartRateDayDataDao.Properties.Date.eq(str)).count() > 0;
    }

    public boolean hasBloodRecord(long j, String str, String str2) {
        List<String> dates;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (dates = DateUtil.getDates(str, str2)) == null || dates.size() == 0) {
            return false;
        }
        int size = dates.size();
        for (int i = 0; i < size; i++) {
            if (getDaoSession().getServerBloodOxyDayDataDao().queryBuilder().where(ServerBloodOxyDayDataDao.Properties.Date.eq(dates.get(i)), ServerBloodOxyDayDataDao.Properties.UserId.eq(Long.valueOf(j))).count() > 0) {
                return true;
            }
        }
        return false;
    }

    public void saveActivityData(SportHealth sportHealth) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "saveActivityData: " + sportHealth.toString());
        SportHealth activityData = getActivityData(sportHealth.getDateTime());
        if (activityData == null) {
            if (sportHealth.getSourceType() == 0) {
                sportHealth.setSourceType(2);
            }
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "saveActivityData: null" + sportHealth.toString());
            getDaoSession().insert(sportHealth);
            return;
        }
        sportHealth.setSourceType(activityData.getSourceType());
        sportHealth.setActivityId(activityData.getActivityId());
        sportHealth.setTargetType(activityData.getTargetType());
        sportHealth.setTargetValue(activityData.getTargetValue());
        sportHealth.setStartTime(activityData.getStartTime());
        if (!TextUtils.isEmpty(activityData.getIcon())) {
            sportHealth.setIcon(activityData.getIcon());
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "saveActivityData: 不等于null" + sportHealth.toString());
        getDaoSession().update(sportHealth);
    }

    public List<SportHealth> getAllNotUploadActivityData() {
        return getDaoSession().queryBuilder(SportHealth.class).where(SportHealthDao.Properties.UserId.eq(Long.valueOf(getUserId())), SportHealthDao.Properties.IsUploaded.eq(false)).list();
    }

    public List<SportHealth> getAllNotUploadStravaActivityData(long j) {
        return getDaoSession().queryBuilder(SportHealth.class).where(SportHealthDao.Properties.UserId.eq(Long.valueOf(getUserId())), SportHealthDao.Properties.Timestamp.eq(Long.valueOf(j)), SportHealthDao.Properties.IsUploadedStrava.eq(false)).list();
    }

    public LinkedList<Long> getAllNotUploadStravaActivityDataTimeStamp() {
        LinkedList<Long> linkedList = new LinkedList<>();
        Cursor cursorRawQuery = getDaoSession().getDatabase().rawQuery("SELECT timestamp FROM SPORT_HEALTH WHERE " + SportHealthDao.Properties.UserId.columnName + "=? and " + SportHealthDao.Properties.IsUploadedStrava.columnName + "=?", new String[]{String.valueOf(RunTimeUtil.getInstance().getUserId()), AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE});
        while (cursorRawQuery.moveToNext()) {
            try {
                try {
                    linkedList.add(Long.valueOf(cursorRawQuery.getLong(cursorRawQuery.getColumnIndex(SportHealthDao.Properties.Timestamp.columnName))));
                    CommonLogUtil.d(TAG, "Timestamp.columnName: " + cursorRawQuery.getInt(0) + AppInfo.DELIM + cursorRawQuery.getLong(cursorRawQuery.getColumnIndex(SportHealthDao.Properties.Timestamp.columnName)));
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (cursorRawQuery != null) {
                    }
                }
            } finally {
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
            }
        }
        return linkedList;
    }

    public void updateStravaActivityData(String str) {
        SportHealth sportHealth = (SportHealth) getDaoSession().queryBuilder(SportHealth.class).where(SportHealthDao.Properties.StartTime.eq(str), new WhereCondition[0]).limit(1).unique();
        sportHealth.setUploadedStrava(true);
        if (sportHealth != null) {
            getDaoSession().update(sportHealth);
        }
    }

    public void saveMultiActivityData(final List<SportHealth> list, final boolean z) {
        if (list == null || list.size() == 0) {
            return;
        }
        getDaoSession().runInTx(new Runnable() { // from class: com.ido.life.database.-$$Lambda$LocalHealthDataManager$xkssPQq-092PUgXZwB_HWfn__RI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$saveMultiActivityData$3$LocalHealthDataManager(list, z);
            }
        });
    }

    public /* synthetic */ void lambda$saveMultiActivityData$3$LocalHealthDataManager(List list, boolean z) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            SportHealth sportHealth = (SportHealth) it.next();
            sportHealth.setUploaded(z);
            saveActivityData(sportHealth);
        }
    }

    public SportGpsData getSportGpsData(long j, long j2) {
        if (getDaoSession().getSportGpsDataDao().queryBuilder().where(SportGpsDataDao.Properties.UserId.eq(Long.valueOf(j)), SportGpsDataDao.Properties.TimeMillis.eq(Long.valueOf(j2))).count() > 0) {
            return getDaoSession().getSportGpsDataDao().queryBuilder().where(SportGpsDataDao.Properties.UserId.eq(Long.valueOf(j)), SportGpsDataDao.Properties.TimeMillis.eq(Long.valueOf(j2))).limit(1).unique();
        }
        return null;
    }

    public SportGpsData getSportGpsData(long j) {
        List list = getDaoSession().queryBuilder(SportGpsData.class).where(SportGpsDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), SportGpsDataDao.Properties.TimeMillis.eq(Long.valueOf(j))).list();
        if (list == null || list.size() == 0) {
            return null;
        }
        return (SportGpsData) list.get(0);
    }

    public void savAppGpsData(SportGpsData sportGpsData) {
        CommonLogUtil.d(TAG, "savAppGpsTrace: " + sportGpsData.toString());
        SportGpsData sportGpsData2 = getSportGpsData(sportGpsData.timeMillis);
        if (sportGpsData2 == null) {
            sportGpsData.setUserId(getUserId());
            getDaoSession().insertOrReplace(sportGpsData);
        } else {
            sportGpsData.setId(sportGpsData2.getId());
            getDaoSession().update(sportGpsData);
        }
    }

    public SportHealth getActivityData(String str) {
        CommonLogUtil.d(TAG, "getActivityData: " + str + AppInfo.DELIM + getUserId());
        List list = getDaoSession().queryBuilder(SportHealth.class).where(SportHealthDao.Properties.UserId.eq(Long.valueOf(getUserId())), SportHealthDao.Properties.DateTime.eq(str)).list();
        if (list == null || list.size() == 0) {
            return null;
        }
        return (SportHealth) list.get(0);
    }

    public List<SportHealth> getAllActiveData() {
        return getDaoSession().queryBuilder(SportHealth.class).where(SportHealthDao.Properties.UserId.eq(Long.valueOf(getUserId())), new WhereCondition[0]).list();
    }

    public void saveBloodOxyDayData(ServerBloodOxyDayData serverBloodOxyDayData) {
        if (serverBloodOxyDayData == null || TextUtils.isEmpty(serverBloodOxyDayData.getDate())) {
            return;
        }
        serverBloodOxyDayData.setUserId(getUserId());
        ServerBloodOxyDayData bloodOxyDailyDataByDate = getBloodOxyDailyDataByDate(serverBloodOxyDayData.getDate());
        if (bloodOxyDailyDataByDate == null) {
            getDaoSession().insert(serverBloodOxyDayData);
            return;
        }
        serverBloodOxyDayData.setId(bloodOxyDailyDataByDate.getId());
        if (TextUtils.isEmpty(serverBloodOxyDayData.getItems())) {
            serverBloodOxyDayData.setItems(bloodOxyDailyDataByDate.getItems());
        }
        getDaoSession().update(serverBloodOxyDayData);
    }

    public ServerBloodOxyDayData getBloodOxyDailyDataByDate(String str) {
        List list;
        if (TextUtils.isEmpty(str) || (list = getDaoSession().queryBuilder(ServerBloodOxyDayData.class).where(ServerBloodOxyDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerBloodOxyDayDataDao.Properties.Date.eq(str)).list()) == null || list.size() == 0) {
            return null;
        }
        return (ServerBloodOxyDayData) list.get(0);
    }

    public boolean hasBloodDayData(String str) {
        return !TextUtils.isEmpty(str) && getDaoSession().getServerBloodOxyDayDataDao().queryBuilder().where(ServerBloodOxyDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerBloodOxyDayDataDao.Properties.Date.eq(str)).count() > 0;
    }

    public ServerBloodOxyDayData getNearBloodOxyDailyData() {
        if (getDaoSession().getServerBloodOxyDayDataDao().queryBuilder().where(ServerBloodOxyDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerBloodOxyDayDataDao.Properties.MaxValue.notEq(0), ServerBloodOxyDayDataDao.Properties.Timestamp.le(Long.valueOf(System.currentTimeMillis()))).count() == 0) {
            return null;
        }
        return getDaoSession().getServerBloodOxyDayDataDao().queryBuilder().where(ServerBloodOxyDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerBloodOxyDayDataDao.Properties.MaxValue.notEq(0), ServerBloodOxyDayDataDao.Properties.Timestamp.le(Long.valueOf(System.currentTimeMillis()))).orderDesc(ServerBloodOxyDayDataDao.Properties.Timestamp).limit(1).unique();
    }

    public void saveSportGpsData(SportGpsData sportGpsData) {
        CommonLogUtil.d(TAG, "savAppGpsTrace: " + sportGpsData.toString());
        SportGpsData sportGpsData2 = getSportGpsData(getUserId(), sportGpsData.timeMillis);
        if (sportGpsData2 == null) {
            sportGpsData.setUserId(getUserId());
            getDaoSession().insertOrReplace(sportGpsData);
        } else {
            sportGpsData.setId(sportGpsData2.getId());
            getDaoSession().update(sportGpsData);
        }
    }

    public void addAppGpsData(SportGpsData sportGpsData) {
        if (sportGpsData == null) {
            return;
        }
        getDaoSession().getSportGpsDataDao().insert(sportGpsData);
    }

    public void saveMultiDaysBloodOxyData(final List<ServerBloodOxyDayData> list, final boolean z) {
        getDaoSession().runInTx(new Runnable() { // from class: com.ido.life.database.-$$Lambda$LocalHealthDataManager$iVcfRuBBvkLS8VupAUx9ZPhkDkI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$saveMultiDaysBloodOxyData$4$LocalHealthDataManager(list, z);
            }
        });
    }

    public /* synthetic */ void lambda$saveMultiDaysBloodOxyData$4$LocalHealthDataManager(List list, boolean z) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ServerBloodOxyDayData serverBloodOxyDayData = (ServerBloodOxyDayData) it.next();
            serverBloodOxyDayData.setUploaded(z);
            saveBloodOxyDayData(serverBloodOxyDayData);
        }
    }

    public void saveMultiDaysBloodOxyTotalData(ServerDaysBloodOxyData serverDaysBloodOxyData) {
        if (serverDaysBloodOxyData == null || TextUtils.isEmpty(serverDaysBloodOxyData.getStartDate()) || TextUtils.isEmpty(serverDaysBloodOxyData.getEndDate())) {
            return;
        }
        serverDaysBloodOxyData.setUserId(getUserId());
        ServerDaysBloodOxyData bloodOxyMultiDaysTotalData = getBloodOxyMultiDaysTotalData(serverDaysBloodOxyData.getStartDate(), serverDaysBloodOxyData.getEndDate());
        if (bloodOxyMultiDaysTotalData == null) {
            getDaoSession().insert(serverDaysBloodOxyData);
        } else {
            serverDaysBloodOxyData.setId(bloodOxyMultiDaysTotalData.getId());
            getDaoSession().update(serverDaysBloodOxyData);
        }
    }

    public ServerDaysBloodOxyData getBloodOxyMultiDaysTotalData(String str, String str2) {
        List list;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (list = getDaoSession().queryBuilder(ServerDaysBloodOxyData.class).where(ServerDaysBloodOxyDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerDaysBloodOxyDataDao.Properties.StartDate.eq(str), ServerDaysBloodOxyDataDao.Properties.EndDate.eq(str2)).list()) == null || list.size() == 0) {
            return null;
        }
        return (ServerDaysBloodOxyData) list.get(0);
    }

    public List<ServerBloodOxyDayData> getBloodOxyDailyDataList(String str, String str2, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (z) {
                arrayList.addAll(getMonthBloodOxyDayDataList(str));
            } else {
                Iterator<String> it = DateUtil.getDates(str, str2).iterator();
                while (it.hasNext()) {
                    arrayList.add(getBloodOxyDailyDataByDate(it.next()));
                }
            }
        }
        return arrayList;
    }

    public List<ServerBloodOxyDayData> getMonthBloodOxyDayDataList(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int[] iArrYearMonthDay = DateUtil.yearMonthDay(str);
        List<ServerBloodOxyDayData> list = getDaoSession().queryBuilder(ServerBloodOxyDayData.class).where(ServerBloodOxyDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerBloodOxyDayDataDao.Properties.Date.like(iArrYearMonthDay[0] + "-" + NumUtil.numberFormat(iArrYearMonthDay[1]) + "-%")).list();
        return list == null ? new ArrayList() : list;
    }

    public void saveYearBloodOxy(ServerMultiMonthBloodOxyTotalData serverMultiMonthBloodOxyTotalData) {
        if (serverMultiMonthBloodOxyTotalData == null || TextUtils.isEmpty(serverMultiMonthBloodOxyTotalData.getStartDate())) {
            return;
        }
        serverMultiMonthBloodOxyTotalData.setUserId(getUserId());
        ServerMultiMonthBloodOxyTotalData yearBloodOxy = getYearBloodOxy(serverMultiMonthBloodOxyTotalData.getStartDate());
        if (yearBloodOxy == null) {
            getDaoSession().insert(serverMultiMonthBloodOxyTotalData);
        } else {
            serverMultiMonthBloodOxyTotalData.setId(yearBloodOxy.getId());
            getDaoSession().update(serverMultiMonthBloodOxyTotalData);
        }
    }

    private ServerMultiMonthBloodOxyTotalData getYearBloodOxy(String str) {
        List list;
        if (TextUtils.isEmpty(str) || (list = getDaoSession().queryBuilder(ServerMultiMonthBloodOxyTotalData.class).where(ServerMultiMonthBloodOxyTotalDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerMultiMonthBloodOxyTotalDataDao.Properties.StartDate.eq(str)).list()) == null || list.size() == 0) {
            return null;
        }
        return (ServerMultiMonthBloodOxyTotalData) list.get(0);
    }

    public List<ServerBloodOxyDayData> getAllBloodOxyDayData() {
        return getDaoSession().queryBuilder(ServerBloodOxyDayData.class).where(ServerBloodOxyDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerBloodOxyDayDataDao.Properties.MaxValue.notEq(0)).list();
    }

    public ServerMultiMonthBloodOxyTotalData getYearBloodOxy(int i) {
        return getYearBloodOxy(i + YEAR_START_DATE);
    }

    public void saveMultiMonthBloodOxyData(final List<ServerBloodOxyMonthData> list) {
        getDaoSession().runInTx(new Runnable() { // from class: com.ido.life.database.-$$Lambda$LocalHealthDataManager$6Xn2WFtFCV55WL9R87d8Dzjl1og
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$saveMultiMonthBloodOxyData$5$LocalHealthDataManager(list);
            }
        });
    }

    public /* synthetic */ void lambda$saveMultiMonthBloodOxyData$5$LocalHealthDataManager(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            saveMonthBloodOxy((ServerBloodOxyMonthData) it.next());
        }
    }

    public void saveMonthBloodOxy(ServerBloodOxyMonthData serverBloodOxyMonthData) {
        if (serverBloodOxyMonthData == null || TextUtils.isEmpty(serverBloodOxyMonthData.getMonth())) {
            return;
        }
        serverBloodOxyMonthData.setUserId(getUserId());
        ServerBloodOxyMonthData monthBloodOxy = getMonthBloodOxy(serverBloodOxyMonthData.getMonth());
        if (monthBloodOxy == null) {
            getDaoSession().insert(serverBloodOxyMonthData);
        } else {
            serverBloodOxyMonthData.setId(monthBloodOxy.getId());
            getDaoSession().update(serverBloodOxyMonthData);
        }
    }

    public ServerBloodOxyMonthData getMonthBloodOxy(String str) {
        List list;
        if (TextUtils.isEmpty(str) || (list = getDaoSession().queryBuilder(ServerBloodOxyMonthData.class).where(ServerBloodOxyMonthDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerBloodOxyMonthDataDao.Properties.Month.eq(str)).list()) == null || list.size() == 0) {
            return null;
        }
        return (ServerBloodOxyMonthData) list.get(0);
    }

    public List<ServerBloodOxyMonthData> getMonthBloodOxyAnnual(int i) {
        List<String> monthList = getMonthList(i);
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = monthList.iterator();
        while (it.hasNext()) {
            arrayList.add(getMonthBloodOxy(it.next()));
        }
        return arrayList;
    }

    public List<ServerDaysBloodOxyData> getYearAllMonthTotalBloodOxyDataList(int i) {
        List<ServerDaysBloodOxyData> list = getDaoSession().queryBuilder(ServerDaysBloodOxyData.class).where(ServerDaysBloodOxyDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerDaysBloodOxyDataDao.Properties.StartDate.like(i + "-%-01")).whereOr(ServerDaysBloodOxyDataDao.Properties.EndDate.like(i + "-%-2%"), ServerDaysBloodOxyDataDao.Properties.EndDate.like(i + "-%-3%"), new WhereCondition[0]).list();
        return list == null ? new ArrayList() : list;
    }

    public List<ServerBloodOxyDayData> getAllNotUploadBloodOxyData() {
        return getDaoSession().getServerBloodOxyDayDataDao().queryBuilder().where(ServerBloodOxyDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), ServerBloodOxyDayDataDao.Properties.Uploaded.eq(false), ServerBloodOxyDayDataDao.Properties.Items.isNotNull()).list();
    }

    public WalkDayData getWalkDayData(String str) {
        List list;
        if (TextUtils.isEmpty(str) || (list = getDaoSession().queryBuilder(WalkDayData.class).where(WalkDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), WalkDayDataDao.Properties.Date.eq(str)).list()) == null || list.size() == 0) {
            return null;
        }
        return (WalkDayData) list.get(0);
    }

    public boolean hasWalkData(String str) {
        return !TextUtils.isEmpty(str) && getDaoSession().getWalkDayDataDao().queryBuilder().where(WalkDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), WalkDayDataDao.Properties.Date.eq(str)).count() > 0;
    }

    public void saveWalkDayData(WalkDayData walkDayData) {
        if (walkDayData == null || TextUtils.isEmpty(walkDayData.getDate())) {
            return;
        }
        walkDayData.setUserId(getUserId());
        WalkDayData walkDayData2 = getWalkDayData(walkDayData.getDate());
        if (walkDayData2 == null) {
            getDaoSession().insert(walkDayData);
            return;
        }
        walkDayData.setId(walkDayData2.getId());
        if (TextUtils.isEmpty(walkDayData.getItems())) {
            walkDayData.setItems(walkDayData2.getItems());
        }
        getDaoSession().update(walkDayData);
    }

    public MultiDaysWalkTotalData getMultiDaysWalkTotalData(String str, String str2) {
        List list;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (list = getDaoSession().queryBuilder(MultiDaysWalkTotalData.class).where(MultiDaysWalkTotalDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), MultiDaysWalkTotalDataDao.Properties.StartDate.eq(str), MultiDaysWalkTotalDataDao.Properties.EndDate.eq(str2)).list()) == null || list.size() == 0) {
            return null;
        }
        return (MultiDaysWalkTotalData) list.get(0);
    }

    public List<WalkDayData> getWalkDayDataList(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Iterator<String> it = DateUtil.getDates(str, str2).iterator();
            while (it.hasNext()) {
                arrayList.add(getWalkDayData(it.next()));
            }
        }
        return arrayList;
    }

    public List<WalkDayData> getWalkDayDataList(String str, String str2, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (z) {
                arrayList.addAll(getMonthWalkDayDataList(str));
            } else {
                Iterator<String> it = DateUtil.getDates(str, str2).iterator();
                while (it.hasNext()) {
                    arrayList.add(getWalkDayData(it.next()));
                }
            }
        }
        return arrayList;
    }

    public List<WalkDayData> getMonthWalkDayDataList(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int[] iArrYearMonthDay = DateUtil.yearMonthDay(str);
        List<WalkDayData> list = getDaoSession().queryBuilder(WalkDayData.class).where(WalkDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), WalkDayDataDao.Properties.Date.like(iArrYearMonthDay[0] + "-" + NumUtil.numberFormat(iArrYearMonthDay[1]) + "-%")).list();
        return list == null ? new ArrayList() : list;
    }

    public void saveMultiDaysWalkTotalData(MultiDaysWalkTotalData multiDaysWalkTotalData) {
        if (multiDaysWalkTotalData == null || TextUtils.isEmpty(multiDaysWalkTotalData.getStartDate()) || TextUtils.isEmpty(multiDaysWalkTotalData.getEndDate())) {
            return;
        }
        multiDaysWalkTotalData.setUserId(getUserId());
        MultiDaysWalkTotalData multiDaysWalkTotalData2 = getMultiDaysWalkTotalData(multiDaysWalkTotalData.getStartDate(), multiDaysWalkTotalData.getEndDate());
        if (multiDaysWalkTotalData2 == null) {
            getDaoSession().insert(multiDaysWalkTotalData);
        } else {
            multiDaysWalkTotalData.setId(multiDaysWalkTotalData2.getId());
            getDaoSession().update(multiDaysWalkTotalData);
        }
    }

    public WalkMonthTotalData getWalkMonthTotalData(String str) {
        List list;
        if (TextUtils.isEmpty(str) || (list = getDaoSession().queryBuilder(WalkMonthTotalData.class).where(WalkMonthTotalDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), WalkMonthTotalDataDao.Properties.Month.eq(str)).list()) == null || list.size() == 0) {
            return null;
        }
        return (WalkMonthTotalData) list.get(0);
    }

    public void saveWalkMonthTotalData(WalkMonthTotalData walkMonthTotalData) {
        if (walkMonthTotalData == null || TextUtils.isEmpty(walkMonthTotalData.getMonth())) {
            return;
        }
        walkMonthTotalData.setUserId(getUserId());
        WalkMonthTotalData walkMonthTotalData2 = getWalkMonthTotalData(walkMonthTotalData.getMonth());
        if (walkMonthTotalData2 == null) {
            getDaoSession().insert(walkMonthTotalData);
        } else {
            walkMonthTotalData.setId(walkMonthTotalData2.getId());
            getDaoSession().update(walkMonthTotalData);
        }
    }

    public List<MultiDaysWalkTotalData> getYearAllMonthWalkTotalDataList(int i) {
        List<MultiDaysWalkTotalData> list = getDaoSession().queryBuilder(MultiDaysWalkTotalData.class).where(MultiDaysWalkTotalDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), MultiDaysWalkTotalDataDao.Properties.StartDate.like(i + "-%-01")).whereOr(MultiDaysWalkTotalDataDao.Properties.EndDate.like(i + "-%-2%"), MultiDaysWalkTotalDataDao.Properties.EndDate.like(i + "-%-3%"), new WhereCondition[0]).list();
        return list == null ? new ArrayList() : list;
    }

    public WalkYearTotalData getWalkYearTotalData(int i) {
        return getWalkYearTotalData(i + YEAR_START_DATE);
    }

    private WalkYearTotalData getWalkYearTotalData(String str) {
        List list;
        if (TextUtils.isEmpty(str) || (list = getDaoSession().queryBuilder(WalkYearTotalData.class).where(WalkYearTotalDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), WalkYearTotalDataDao.Properties.StartDate.eq(str)).list()) == null || list.size() == 0) {
            return null;
        }
        return (WalkYearTotalData) list.get(0);
    }

    public void saveWalkYearTotalData(WalkYearTotalData walkYearTotalData) {
        if (walkYearTotalData == null || TextUtils.isEmpty(walkYearTotalData.getStartDate())) {
            return;
        }
        walkYearTotalData.setUserId(getUserId());
        WalkYearTotalData walkYearTotalData2 = getWalkYearTotalData(walkYearTotalData.getStartDate());
        if (walkYearTotalData2 == null) {
            getDaoSession().insert(walkYearTotalData);
        } else {
            walkYearTotalData.setId(walkYearTotalData2.getId());
            getDaoSession().update(walkYearTotalData);
        }
    }

    public boolean hasHistoryHealthVolume() {
        return getDaoSession().getHealthVolumeDataDao().queryBuilder().where(HealthVolumeDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), HealthVolumeDataDao.Properties.Timestamp.le(Long.valueOf(System.currentTimeMillis()))).count() > 0;
    }

    public List<WalkDayData> getAllNotUploadWalkData() {
        return getDaoSession().getWalkDayDataDao().queryBuilder().where(WalkDayDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), WalkDayDataDao.Properties.Uploaded.eq(false), WalkDayDataDao.Properties.Items.isNotNull()).list();
    }

    public void saveMultiWalkDayData(final List<WalkDayData> list, final boolean z) {
        getDaoSession().runInTx(new Runnable() { // from class: com.ido.life.database.-$$Lambda$LocalHealthDataManager$9FKbyGP6zm5x_dyYT-FQ7g0faMA
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$saveMultiWalkDayData$6$LocalHealthDataManager(list, z);
            }
        });
    }

    public /* synthetic */ void lambda$saveMultiWalkDayData$6$LocalHealthDataManager(List list, boolean z) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WalkDayData walkDayData = (WalkDayData) it.next();
            walkDayData.setUploaded(z);
            saveWalkDayData(walkDayData);
        }
    }

    public List<WalkMonthTotalData> getMonthWalkAnnual(int i) {
        List<String> monthList = getMonthList(i);
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = monthList.iterator();
        while (it.hasNext()) {
            arrayList.add(getWalkMonthTotalData(it.next()));
        }
        return arrayList;
    }
}