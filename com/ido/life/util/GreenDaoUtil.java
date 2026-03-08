package com.ido.life.util;

import android.database.Cursor;
import android.text.TextUtils;
import android.util.Pair;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.BaseEntity;
import com.ido.life.bean.HeartItemBean;
import com.ido.life.bean.SavePrivateSafeSettingBean;
import com.ido.life.ble.PrivacyandSecurityCallBack;
import com.ido.life.data.me.remote.AccountManager;
import com.ido.life.database.GreenDaoManager;
import com.ido.life.database.model.ActiveTimeDayData;
import com.ido.life.database.model.ActiveTimeDayDataDao;
import com.ido.life.database.model.AgreementPrivacyVersion;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.CalorieDayDataDao;
import com.ido.life.database.model.DaoSession;
import com.ido.life.database.model.DataDownLoadState;
import com.ido.life.database.model.DataDownLoadStateDao;
import com.ido.life.database.model.DataPullConfigInfo;
import com.ido.life.database.model.DataPullConfigInfoDao;
import com.ido.life.database.model.HealthPressure;
import com.ido.life.database.model.HealthPressureDao;
import com.ido.life.database.model.HealthVolumeData;
import com.ido.life.database.model.HealthVolumeDataDao;
import com.ido.life.database.model.HomeCard;
import com.ido.life.database.model.HomeCardDao;
import com.ido.life.database.model.LifeCycleItemBean;
import com.ido.life.database.model.LifeCycleItemBeanDao;
import com.ido.life.database.model.MapEngine;
import com.ido.life.database.model.MapEngineDao;
import com.ido.life.database.model.MenstruationConfig;
import com.ido.life.database.model.MenstruationConfigDao;
import com.ido.life.database.model.MessageEntity;
import com.ido.life.database.model.MessageEntityDao;
import com.ido.life.database.model.MultiDaysWalkTotalDataDao;
import com.ido.life.database.model.PrivateSafeSetting;
import com.ido.life.database.model.PrivateSafeSettingDao;
import com.ido.life.database.model.ServerBloodOxyDayData;
import com.ido.life.database.model.ServerBloodOxyDayDataDao;
import com.ido.life.database.model.ServerBloodOxyMonthDataDao;
import com.ido.life.database.model.ServerDaysBloodOxyDataDao;
import com.ido.life.database.model.ServerDaysSleepDataDao;
import com.ido.life.database.model.ServerHeartRateDayData;
import com.ido.life.database.model.ServerHeartRateDayDataDao;
import com.ido.life.database.model.ServerMultiMonthBloodOxyTotalDataDao;
import com.ido.life.database.model.ServerMultiMonthSleepTotalDataDao;
import com.ido.life.database.model.ServerSleepDayData;
import com.ido.life.database.model.ServerSleepDayDataDao;
import com.ido.life.database.model.ServerSleepMonthDataDao;
import com.ido.life.database.model.SportCard;
import com.ido.life.database.model.SportCardDao;
import com.ido.life.database.model.SportDistanceBean;
import com.ido.life.database.model.SportDistanceBeanDao;
import com.ido.life.database.model.SportGps;
import com.ido.life.database.model.SportGpsData;
import com.ido.life.database.model.SportGpsDataDao;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.SportHealthDao;
import com.ido.life.database.model.StepDayData;
import com.ido.life.database.model.StepDayDataDao;
import com.ido.life.database.model.TempUnitSetting;
import com.ido.life.database.model.TempUnitSettingDao;
import com.ido.life.database.model.TimeSet;
import com.ido.life.database.model.TimeSetDao;
import com.ido.life.database.model.UnitSetting;
import com.ido.life.database.model.UnitSettingDao;
import com.ido.life.database.model.UserInfo;
import com.ido.life.database.model.UserInfoDao;
import com.ido.life.database.model.UserMedalInfo;
import com.ido.life.database.model.UserMedalInfoDao;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.database.model.UserTargetNewDao;
import com.ido.life.database.model.WalkDayData;
import com.ido.life.database.model.WalkDayDataDao;
import com.ido.life.database.model.WalkMonthTotalDataDao;
import com.ido.life.database.model.WalkYearTotalDataDao;
import com.ido.life.database.model.WeekStartSetting;
import com.ido.life.database.model.WeekStartSettingDao;
import com.ido.life.database.model.WeightItemBean;
import com.ido.life.database.model.WeightItemBeanDao;
import com.ido.life.log.SportLogHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.WhereCondition;

/* JADX INFO: loaded from: classes3.dex */
public class GreenDaoUtil {
    private static final String TAG = GreenDaoUtil.class.getSimpleName();

    public static DaoSession getDaoSession() {
        return GreenDaoManager.getInstance().getDaoSession();
    }

    public static UserTargetNew queryUserLastestTarget(long j) {
        if (getDaoSession().getUserTargetNewDao().queryBuilder().where(UserTargetNewDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count() == 0) {
            return null;
        }
        return getDaoSession().getUserTargetNewDao().queryBuilder().where(UserTargetNewDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).orderDesc(UserTargetNewDao.Properties.Date).limit(1).unique();
    }

    public static UserTargetNew queryUserTarget(long j, String str) {
        if (getDaoSession().getUserTargetNewDao().queryBuilder().where(UserTargetNewDao.Properties.UserId.eq(Long.valueOf(j)), UserTargetNewDao.Properties.Date.eq(str)).count() <= 0) {
            return null;
        }
        return getDaoSession().getUserTargetNewDao().queryBuilder().where(UserTargetNewDao.Properties.UserId.eq(Long.valueOf(j)), UserTargetNewDao.Properties.Date.eq(str)).limit(1).unique();
    }

    public static List<UserTargetNew> queryUserTarget(long j, String str, String str2) {
        return getDaoSession().getUserTargetNewDao().queryBuilder().where(UserTargetNewDao.Properties.UserId.eq(Long.valueOf(j)), UserTargetNewDao.Properties.Date.ge(str), UserTargetNewDao.Properties.Date.le(str2)).orderAsc(UserTargetNewDao.Properties.Date).list();
    }

    public static UserTargetNew queryUserLatestTarget(long j) {
        if (getDaoSession().getUserTargetNewDao().queryBuilder().where(UserTargetNewDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count() == 0) {
            return null;
        }
        return getDaoSession().getUserTargetNewDao().queryBuilder().where(UserTargetNewDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).orderDesc(UserTargetNewDao.Properties.UpdateTime).limit(1).unique();
    }

    public static List<UserTargetNew> queryUnUploadUserTarget(long j) {
        return getDaoSession().getUserTargetNewDao().queryBuilder().where(UserTargetNewDao.Properties.UserId.eq(Long.valueOf(j)), UserTargetNewDao.Properties.HasUpload.eq(false)).list();
    }

    public static void addUserTarget(UserTargetNew userTargetNew) {
        if (userTargetNew == null) {
            return;
        }
        UserTargetNew userTargetNewQueryUserTarget = queryUserTarget(userTargetNew.getUserId(), userTargetNew.getDate());
        if (userTargetNewQueryUserTarget != null) {
            userTargetNew.setId(userTargetNewQueryUserTarget.getId());
            getDaoSession().getUserTargetNewDao().update(userTargetNew);
        } else {
            getDaoSession().getUserTargetNewDao().insert(userTargetNew);
        }
    }

    public static void deleteUserTarget(long j) {
        List<UserTargetNew> list = getDaoSession().getUserTargetNewDao().queryBuilder().where(UserTargetNewDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).list();
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<UserTargetNew> it = list.iterator();
        while (it.hasNext()) {
            it.next().delete();
        }
        list.clear();
    }

    public static UserInfo queryUserInfo(long j) {
        if (getDaoSession().getUserInfoDao().queryBuilder().where(UserInfoDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count() <= 0) {
            return null;
        }
        return getDaoSession().getUserInfoDao().queryBuilder().where(UserInfoDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).limit(1).unique();
    }

    public static UserInfo queryUserInfo(String str) {
        if (getDaoSession().getUserInfoDao().queryBuilder().where(UserInfoDao.Properties.Email.eq(str), new WhereCondition[0]).count() <= 0) {
            return null;
        }
        return getDaoSession().getUserInfoDao().queryBuilder().where(UserInfoDao.Properties.Email.eq(str), new WhereCondition[0]).limit(1).unique();
    }

    public static void addUserInfo(UserInfo userInfo) {
        if (userInfo == null) {
            return;
        }
        UserInfo userInfoQueryUserInfo = queryUserInfo(userInfo.getUserId());
        if (userInfoQueryUserInfo == null) {
            deleteUserInfo(userInfo.getUserId());
            getDaoSession().getUserInfoDao().insertOrReplace(userInfo);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "addUserInfo()，localUserInfo为空，数据库直接插入保存传入的userInfo");
        } else if (userInfoQueryUserInfo.getUpdateTime() < userInfo.getUpdateTime()) {
            deleteUserInfo(userInfo.getUserId());
            getDaoSession().getUserInfoDao().insertOrReplace(userInfo);
            CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "addUserInfo()，localUserInfo没有传入的userInfo新，数据库更新保存传入的userInfo");
        }
    }

    public static void deleteAllLoginUserTarget(UserTargetNewDao userTargetNewDao) {
        List<UserTargetNew> list = userTargetNewDao.queryBuilder().where(UserTargetNewDao.Properties.UserId.notEq(-1), new WhereCondition[0]).list();
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<UserTargetNew> it = list.iterator();
        while (it.hasNext()) {
            it.next().delete();
        }
        list.clear();
    }

    public static void deleteUserInfo(long j) {
        List<UserInfo> list = getDaoSession().getUserInfoDao().queryBuilder().where(UserInfoDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).list();
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<UserInfo> it = list.iterator();
        while (it.hasNext()) {
            it.next().delete();
        }
        list.clear();
    }

    public static UserInfo queryLatestUserInfo() {
        if (getDaoSession().getUserInfoDao().count() <= 0) {
            return null;
        }
        UserInfo userInfoUnique = getDaoSession().getUserInfoDao().queryBuilder().orderDesc(UserInfoDao.Properties.UpdateTime).limit(1).unique();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "查询最新用户信息,userInfo=" + userInfoUnique.toString());
        return userInfoUnique;
    }

    public static UnitSetting queryUnitSetting(long j) {
        if (getDaoSession().getUnitSettingDao().queryBuilder().where(UnitSettingDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count() == 0) {
            return null;
        }
        return getDaoSession().getUnitSettingDao().queryBuilder().where(UnitSettingDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).limit(1).unique();
    }

    public static void deleteUnitSetting(long j) {
        List<UnitSetting> list = getDaoSession().getUnitSettingDao().queryBuilder().where(UnitSettingDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).list();
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<UnitSetting> it = list.iterator();
        while (it.hasNext()) {
            it.next().delete();
        }
        list.clear();
    }

    public static TempUnitSetting queryTempUnitSetting(long j) {
        if (getDaoSession().getTempUnitSettingDao().queryBuilder().where(TempUnitSettingDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count() > 0) {
            return getDaoSession().getTempUnitSettingDao().queryBuilder().where(TempUnitSettingDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).limit(1).unique();
        }
        return null;
    }

    public static void saveTempUnitSetting(TempUnitSetting tempUnitSetting) {
        if (tempUnitSetting == null) {
            return;
        }
        TempUnitSetting tempUnitSettingQueryTempUnitSetting = queryTempUnitSetting(tempUnitSetting.getUserId());
        if (tempUnitSettingQueryTempUnitSetting == null) {
            getDaoSession().getTempUnitSettingDao().insert(tempUnitSetting);
        } else {
            tempUnitSetting.setId(tempUnitSettingQueryTempUnitSetting.getId());
            getDaoSession().getTempUnitSettingDao().update(tempUnitSetting);
        }
    }

    public static void deleteAllLoginUserInfo(UserInfoDao userInfoDao) {
        List<UserInfo> list = userInfoDao.queryBuilder().where(UserInfoDao.Properties.UserId.notEq(-1), new WhereCondition[0]).list();
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<UserInfo> it = list.iterator();
        while (it.hasNext()) {
            it.next().delete();
        }
        list.clear();
    }

    public static void addUnitSetting(UnitSetting unitSetting) {
        if (unitSetting == null) {
            return;
        }
        UnitSetting unitSettingQueryUnitSetting = queryUnitSetting(unitSetting.getUserId());
        if (unitSettingQueryUnitSetting == null) {
            getDaoSession().getUnitSettingDao().insert(unitSetting);
        } else {
            unitSetting.setId(unitSettingQueryUnitSetting.getId());
            getDaoSession().getUnitSettingDao().update(unitSetting);
        }
    }

    public static TimeSet queryTimeSet(long j) {
        if (getDaoSession().getTimeSetDao().queryBuilder().where(TimeSetDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count() == 0) {
            return null;
        }
        return getDaoSession().getTimeSetDao().queryBuilder().where(TimeSetDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).limit(1).unique();
    }

    public static void deleteTimeSet(long j) {
        List<TimeSet> list = getDaoSession().getTimeSetDao().queryBuilder().where(TimeSetDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).list();
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<TimeSet> it = list.iterator();
        while (it.hasNext()) {
            it.next().delete();
        }
        list.clear();
    }

    public static void addTimeSet(TimeSet timeSet) {
        if (timeSet == null) {
            return;
        }
        TimeSet timeSetQueryTimeSet = queryTimeSet(timeSet.getUserId());
        if (timeSetQueryTimeSet == null) {
            getDaoSession().getTimeSetDao().insert(timeSet);
        } else {
            timeSet.setId(timeSetQueryTimeSet.getId());
            getDaoSession().getTimeSetDao().update(timeSet);
        }
    }

    public static WeekStartSetting queryWeekStart(long j) {
        if (getDaoSession().getWeekStartSettingDao().queryBuilder().where(WeekStartSettingDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count() == 0) {
            return null;
        }
        return getDaoSession().getWeekStartSettingDao().queryBuilder().where(WeekStartSettingDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).limit(1).unique();
    }

    public static void deleteWeekStart(long j) {
        List<WeekStartSetting> list = getDaoSession().getWeekStartSettingDao().queryBuilder().where(WeekStartSettingDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).list();
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<WeekStartSetting> it = list.iterator();
        while (it.hasNext()) {
            it.next().delete();
        }
        list.clear();
    }

    public static void addWeekStart(WeekStartSetting weekStartSetting) {
        if (weekStartSetting == null) {
            return;
        }
        WeekStartSetting weekStartSettingQueryWeekStart = queryWeekStart(weekStartSetting.getUserId());
        if (weekStartSettingQueryWeekStart == null) {
            getDaoSession().getWeekStartSettingDao().insert(weekStartSetting);
        } else {
            weekStartSetting.setId(weekStartSettingQueryWeekStart.getId());
            getDaoSession().getWeekStartSettingDao().update(weekStartSetting);
        }
    }

    public static MapEngine queryMapEngine(long j) {
        if (getDaoSession().getMapEngineDao().queryBuilder().where(MapEngineDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count() == 0) {
            return null;
        }
        return getDaoSession().getMapEngineDao().queryBuilder().where(MapEngineDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).limit(1).unique();
    }

    public static void deleteMapEngine(long j) {
        List<MapEngine> list = getDaoSession().getMapEngineDao().queryBuilder().where(MapEngineDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).list();
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<MapEngine> it = list.iterator();
        while (it.hasNext()) {
            it.next().delete();
        }
        list.clear();
    }

    public static void addMapEngine(MapEngine mapEngine) {
        if (mapEngine == null) {
            return;
        }
        MapEngine mapEngineQueryMapEngine = queryMapEngine(mapEngine.getUserId());
        if (mapEngineQueryMapEngine == null) {
            getDaoSession().getMapEngineDao().insert(mapEngine);
        } else {
            mapEngine.setId(mapEngineQueryMapEngine.getId());
            getDaoSession().getMapEngineDao().update(mapEngine);
        }
    }

    public static PrivateSafeSetting queryPrivateSafeSetting(long j) {
        if (getDaoSession().getPrivateSafeSettingDao().queryBuilder().where(PrivateSafeSettingDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count() == 0) {
            return null;
        }
        return getDaoSession().getPrivateSafeSettingDao().queryBuilder().where(PrivateSafeSettingDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).limit(1).unique();
    }

    public static void deletePrivateSafeSetting(long j) {
        List<PrivateSafeSetting> list = getDaoSession().getPrivateSafeSettingDao().queryBuilder().where(PrivateSafeSettingDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).list();
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<PrivateSafeSetting> it = list.iterator();
        while (it.hasNext()) {
            it.next().delete();
        }
        list.clear();
    }

    public static void addPrivateSafeSetting(PrivateSafeSetting privateSafeSetting) {
        if (privateSafeSetting == null) {
            return;
        }
        PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = queryPrivateSafeSetting(privateSafeSetting.getUserId());
        if (privateSafeSettingQueryPrivateSafeSetting == null) {
            getDaoSession().getPrivateSafeSettingDao().insert(privateSafeSetting);
        } else {
            privateSafeSetting.setId(privateSafeSettingQueryPrivateSafeSetting.getId());
            getDaoSession().getPrivateSafeSettingDao().update(privateSafeSetting);
        }
    }

    public static AgreementPrivacyVersion queryAgreementPrivacyVersion() {
        List<AgreementPrivacyVersion> list = getDaoSession().getAgreementPrivacyVersionDao().queryBuilder().list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    public static void deleteAgreementPrivacyVersion() {
        List<AgreementPrivacyVersion> list = getDaoSession().getAgreementPrivacyVersionDao().queryBuilder().list();
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<AgreementPrivacyVersion> it = list.iterator();
        while (it.hasNext()) {
            it.next().delete();
        }
        list.clear();
    }

    public static void addAgreementPrivacyVersion(AgreementPrivacyVersion agreementPrivacyVersion) {
        if (agreementPrivacyVersion == null) {
            return;
        }
        deleteAgreementPrivacyVersion();
        getDaoSession().getAgreementPrivacyVersionDao().insertOrReplace(agreementPrivacyVersion);
    }

    public static HomeCard queryHomeCardInfo(long j) {
        if (getDaoSession().getHomeCardDao().queryBuilder().where(HomeCardDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count() == 0) {
            return null;
        }
        HomeCard homeCardUnique = getDaoSession().getHomeCardDao().queryBuilder().where(HomeCardDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).limit(1).unique();
        if (homeCardUnique != null && homeCardUnique.getValueList() != null) {
            List<Integer> valueList = homeCardUnique.getValueList();
            if (valueList.contains(2)) {
                Collections.replaceAll(valueList, 2, 1);
            }
        }
        return homeCardUnique;
    }

    public static void deleteHomeCardInfo(long j) {
        List<HomeCard> list = getDaoSession().getHomeCardDao().queryBuilder().where(HomeCardDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).list();
        if (list == null || list.size() <= 0) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            HomeCard homeCard = list.get(i);
            if (homeCard != null) {
                homeCard.delete();
            }
        }
        list.clear();
    }

    public static void addHomeCardInfo(HomeCard homeCard) {
        if (homeCard == null) {
            return;
        }
        HomeCard homeCardQueryHomeCardInfo = queryHomeCardInfo(homeCard.getUserId().longValue());
        if (homeCardQueryHomeCardInfo == null) {
            getDaoSession().getHomeCardDao().insert(homeCard);
        } else {
            homeCard.setId(homeCardQueryHomeCardInfo.getId());
            getDaoSession().getHomeCardDao().update(homeCard);
        }
    }

    public static SportCard querySportCard(long j) {
        List<SportCard> list = getDaoSession().getSportCardDao().queryBuilder().where(SportCardDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    public static void deleteSportCard(long j) {
        List<SportCard> list = getDaoSession().getSportCardDao().queryBuilder().where(SportCardDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).list();
        if (list == null || list.size() <= 0) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            SportCard sportCard = list.get(i);
            if (sportCard != null) {
                sportCard.delete();
            }
        }
        list.clear();
    }

    public static void addSportCard(SportCard sportCard) {
        if (sportCard == null) {
            return;
        }
        deleteSportCard(sportCard.getUserId());
        getDaoSession().getSportCardDao().insert(sportCard);
    }

    public static void addWeight(WeightItemBean weightItemBean) {
        if (weightItemBean == null) {
            return;
        }
        WeightItemBean weightItemBeanQueryWeightByDate = queryWeightByDate(weightItemBean.getUserId(), weightItemBean.getDate());
        if (weightItemBeanQueryWeightByDate == null) {
            getDaoSession().getWeightItemBeanDao().insert(weightItemBean);
        } else {
            weightItemBean.setId(weightItemBeanQueryWeightByDate.getId());
            getDaoSession().getWeightItemBeanDao().update(weightItemBean);
        }
    }

    public static void deleteWeightByDate(long j, String str) {
        List<WeightItemBean> list = getDaoSession().getWeightItemBeanDao().queryBuilder().where(WeightItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), WeightItemBeanDao.Properties.Date.eq(str)).list();
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<WeightItemBean> it = list.iterator();
        while (it.hasNext()) {
            it.next().delete();
        }
        list.clear();
    }

    public static List<WeightItemBean> queryAllUnUploadWeightData(long j) {
        return getDaoSession().getWeightItemBeanDao().queryBuilder().where(WeightItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), WeightItemBeanDao.Properties.UploadSuccess.eq(false)).list();
    }

    public static WeightItemBean queryWeightByDate(long j, String str) {
        if (getDaoSession().getWeightItemBeanDao().queryBuilder().where(WeightItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), WeightItemBeanDao.Properties.Date.eq(str)).count() == 0) {
            return null;
        }
        return getDaoSession().getWeightItemBeanDao().queryBuilder().where(WeightItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), WeightItemBeanDao.Properties.Date.eq(str)).limit(1).unique();
    }

    public static List<WeightItemBean> queryNewestWeightRecord(long j, int i) {
        if (i <= 0) {
            return null;
        }
        long jCount = getDaoSession().getWeightItemBeanDao().queryBuilder().where(WeightItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count();
        if (jCount == 0) {
            return null;
        }
        return jCount >= ((long) i) ? getDaoSession().getWeightItemBeanDao().queryBuilder().where(WeightItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).orderDesc(WeightItemBeanDao.Properties.Date).limit(i).list() : getDaoSession().getWeightItemBeanDao().queryBuilder().where(WeightItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).orderAsc(WeightItemBeanDao.Properties.Date).list();
    }

    public static WeightItemBean queryNewestWeightRecord(long j) {
        long jCount = getDaoSession().getWeightItemBeanDao().queryBuilder().where(WeightItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), WeightItemBeanDao.Properties.TotalWeight.ge(10)).count();
        if (jCount != 0 && jCount > 0) {
            return getDaoSession().getWeightItemBeanDao().queryBuilder().where(WeightItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), WeightItemBeanDao.Properties.TotalWeight.ge(10)).orderDesc(WeightItemBeanDao.Properties.TimeStamp).limit(1).unique();
        }
        return null;
    }

    public static List<WeightItemBean> queryYearWeightList(int i, long j) {
        return getDaoSession().getWeightItemBeanDao().queryBuilder().where(WeightItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), WeightItemBeanDao.Properties.Date.like("%" + i + "%")).list();
    }

    public static List<WeightItemBean> queryWeightListByDateArea(String str, String str2, long j) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return getDaoSession().getWeightItemBeanDao().queryBuilder().where(WeightItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), WeightItemBeanDao.Properties.Date.ge(str), WeightItemBeanDao.Properties.Date.le(str2)).list();
    }

    public static List<WeightItemBean> queryWeightRecordByYear(long j, int i) {
        return queryWeightRecordByLikeQuery(j, String.valueOf(i));
    }

    public static List<WeightItemBean> queryWeightRecordByMonth(long j, int i, int i2) {
        return queryWeightRecordByLikeQuery(j, String.format("%d-%02d", Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public static List<WeightItemBean> queryWeightRecordByLikeQuery(long j, String str) {
        return getDaoSession().getWeightItemBeanDao().queryBuilder().where(WeightItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), WeightItemBeanDao.Properties.Date.like("%" + str + "%")).list();
    }

    public static SportDistanceBean querySportDistanceByDate(long j, String str) {
        if (TextUtils.isEmpty(str) || getDaoSession().getSportDistanceBeanDao().queryBuilder().where(SportDistanceBeanDao.Properties.UserId.eq(Long.valueOf(j)), SportDistanceBeanDao.Properties.Date.eq(str)).count() == 0) {
            return null;
        }
        return getDaoSession().getSportDistanceBeanDao().queryBuilder().where(SportDistanceBeanDao.Properties.UserId.eq(Long.valueOf(j)), SportDistanceBeanDao.Properties.Date.eq(str)).limit(1).unique();
    }

    public static void deleteSportDistanceByDate(long j, String str) {
        List<SportDistanceBean> list = getDaoSession().getSportDistanceBeanDao().queryBuilder().where(SportDistanceBeanDao.Properties.Date.eq(str), SportDistanceBeanDao.Properties.UserId.eq(Long.valueOf(j))).list();
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<SportDistanceBean> it = list.iterator();
        while (it.hasNext()) {
            it.next().delete();
        }
        list.clear();
    }

    public static void addSportDistance(SportDistanceBean sportDistanceBean) {
        if (sportDistanceBean == null) {
            return;
        }
        SportDistanceBean sportDistanceBeanQuerySportDistanceByDate = querySportDistanceByDate(sportDistanceBean.getUserId(), sportDistanceBean.getDate());
        if (sportDistanceBeanQuerySportDistanceByDate == null) {
            getDaoSession().getSportDistanceBeanDao().insert(sportDistanceBean);
        } else {
            sportDistanceBean.setId(sportDistanceBeanQuerySportDistanceByDate.getId());
            getDaoSession().getSportDistanceBeanDao().update(sportDistanceBean);
        }
    }

    public static List<SportDistanceBean> querySportDistanceByDateArea(long j, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return getDaoSession().getSportDistanceBeanDao().queryBuilder().where(SportDistanceBeanDao.Properties.UserId.eq(Long.valueOf(j)), SportDistanceBeanDao.Properties.Date.ge(str), SportDistanceBeanDao.Properties.Date.le(str2)).list();
    }

    public static List<SportDistanceBean> querySportDistanceByYear(long j, int i) {
        return getDaoSession().getSportDistanceBeanDao().queryBuilder().where(SportDistanceBeanDao.Properties.Date.like("%" + i + "%"), SportDistanceBeanDao.Properties.UserId.eq(Long.valueOf(j))).list();
    }

    public static long queryDistanceRecordCount(long j) {
        return getDaoSession().getSportDistanceBeanDao().queryBuilder().where(SportDistanceBeanDao.Properties.UserId.eq(Long.valueOf(j)), SportDistanceBeanDao.Properties.TotalDistance.gt(0)).count();
    }

    public static void addPressureDayData(HealthPressure healthPressure) {
        if (healthPressure == null || TextUtils.isEmpty(healthPressure.getDate())) {
            return;
        }
        HealthPressure healthPressureQueryHealthPressureByDate = queryHealthPressureByDate(healthPressure.getUserId(), healthPressure.getDate());
        if (healthPressureQueryHealthPressureByDate == null) {
            getDaoSession().getHealthPressureDao().insert(healthPressure);
        } else {
            healthPressure.setId(healthPressureQueryHealthPressureByDate.getId());
            getDaoSession().getHealthPressureDao().update(healthPressure);
        }
    }

    public static HealthPressure getNearHealthPressureDetail(long j) {
        if (getDaoSession().getHealthPressureDao().queryBuilder().where(HealthPressureDao.Properties.LastestPressure.gt(0), HealthPressureDao.Properties.UserId.eq(Long.valueOf(j))).count() == 0) {
            return null;
        }
        return getDaoSession().getHealthPressureDao().queryBuilder().where(HealthPressureDao.Properties.LastestPressure.gt(0), HealthPressureDao.Properties.UserId.eq(Long.valueOf(j))).orderDesc(HealthPressureDao.Properties.Date).limit(1).unique();
    }

    public static HealthPressure getHealthPressureDetailByDay(long j, String str) {
        if (TextUtils.isEmpty(str) || getDaoSession().getHealthPressureDao().queryBuilder().where(HealthPressureDao.Properties.UserId.eq(Long.valueOf(j)), HealthPressureDao.Properties.Date.eq(str)).count() == 0) {
            return null;
        }
        return getDaoSession().getHealthPressureDao().queryBuilder().where(HealthPressureDao.Properties.UserId.eq(Long.valueOf(j)), HealthPressureDao.Properties.Date.eq(str)).limit(1).unique();
    }

    public static List<HealthPressure> getHealthPressureDetailByDateArea(long j, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return getDaoSession().getHealthPressureDao().queryBuilder().where(HealthPressureDao.Properties.UserId.eq(Long.valueOf(j)), HealthPressureDao.Properties.Date.ge(str), HealthPressureDao.Properties.Date.le(str2)).list();
    }

    public static long queryPressureDataCount(long j) {
        return getDaoSession().getHealthPressureDao().queryBuilder().where(HealthPressureDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count();
    }

    public static long queryHealthPressureRecordCount(long j) {
        return getDaoSession().getHealthPressureDao().queryBuilder().where(HealthPressureDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count();
    }

    public static List<HealthPressure> queryAllUnUploadPressureData(long j) {
        return getDaoSession().getHealthPressureDao().queryBuilder().where(HealthPressureDao.Properties.UserId.eq(Long.valueOf(j)), HealthPressureDao.Properties.UploadSuccess.eq(false), HealthPressureDao.Properties.Items.isNotNull()).list();
    }

    public static HealthPressure queryNearestPressureData(long j) {
        if (getDaoSession().getHealthPressureDao().queryBuilder().where(HealthPressureDao.Properties.UserId.eq(Long.valueOf(j)), HealthPressureDao.Properties.LastestPressure.gt(0)).count() > 0) {
            return getDaoSession().getHealthPressureDao().queryBuilder().where(HealthPressureDao.Properties.UserId.eq(Long.valueOf(j)), HealthPressureDao.Properties.LastestPressure.gt(0)).orderDesc(HealthPressureDao.Properties.TimeStamp).limit(1).unique();
        }
        return null;
    }

    public static List<SportDistanceBean> queryAllUnUploadDistanceData(long j) {
        return getDaoSession().getSportDistanceBeanDao().queryBuilder().where(SportDistanceBeanDao.Properties.UserId.eq(Long.valueOf(j)), SportDistanceBeanDao.Properties.UploadSuccess.eq(false), SportDistanceBeanDao.Properties.Items.isNotNull()).list();
    }

    public static SportDistanceBean queryNearestDistance(long j) {
        if (getDaoSession().getSportDistanceBeanDao().queryBuilder().where(SportDistanceBeanDao.Properties.UserId.eq(Long.valueOf(j)), SportDistanceBeanDao.Properties.TotalDistance.gt(0)).count() > 0) {
            return getDaoSession().getSportDistanceBeanDao().queryBuilder().where(SportDistanceBeanDao.Properties.UserId.eq(Long.valueOf(j)), SportDistanceBeanDao.Properties.TotalDistance.gt(0)).orderDesc(SportDistanceBeanDao.Properties.Date, SportDistanceBeanDao.Properties.TimeStamp).limit(1).unique();
        }
        return null;
    }

    public static List<UserMedalInfo> queryAllUnUploadMedalData(long j) {
        return getDaoSession().getUserMedalInfoDao().queryBuilder().where(UserMedalInfoDao.Properties.UserId.eq(Long.valueOf(j)), UserMedalInfoDao.Properties.UploadSuccess.eq(false)).list();
    }

    public static List<UserMedalInfo> queryAllMedalData(long j) {
        return getDaoSession().getUserMedalInfoDao().queryBuilder().where(UserMedalInfoDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).list();
    }

    public static void addUserMedalInfo(UserMedalInfo userMedalInfo) {
        if (userMedalInfo == null || TextUtils.isEmpty(userMedalInfo.getDate())) {
            return;
        }
        deleteUserMedalInfo(userMedalInfo.getMedalId(), userMedalInfo.getUserId());
        getDaoSession().getUserMedalInfoDao().insertOrReplace(userMedalInfo);
    }

    public static void addUserMedalInfoList(List<UserMedalInfo> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (UserMedalInfo userMedalInfo : list) {
            deleteUserMedalInfo(userMedalInfo.getMedalId(), userMedalInfo.getUserId());
        }
        getDaoSession().getUserMedalInfoDao().insertInTx(list);
    }

    public static void deleteUserMedalInfo(int i, long j) {
        List<UserMedalInfo> list = getDaoSession().getUserMedalInfoDao().queryBuilder().where(UserMedalInfoDao.Properties.UserId.eq(Long.valueOf(j)), UserMedalInfoDao.Properties.MedalId.eq(Integer.valueOf(i))).list();
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<UserMedalInfo> it = list.iterator();
        while (it.hasNext()) {
            it.next().delete();
        }
        list.clear();
    }

    public static void deleteUserMedalInfo(long j) {
        List<UserMedalInfo> list = getDaoSession().getUserMedalInfoDao().queryBuilder().where(UserMedalInfoDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).list();
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<UserMedalInfo> it = list.iterator();
        while (it.hasNext()) {
            it.next().delete();
        }
        list.clear();
    }

    public static List<UserMedalInfo> queryAllUserMedalInfo(long j) {
        return getDaoSession().getUserMedalInfoDao().queryBuilder().where(UserMedalInfoDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).list();
    }

    public static UserMedalInfo queryUserMedalInfo(long j, int i) {
        List<UserMedalInfo> list = getDaoSession().getUserMedalInfoDao().queryBuilder().where(UserMedalInfoDao.Properties.UserId.eq(Long.valueOf(j)), UserMedalInfoDao.Properties.MedalId.eq(Integer.valueOf(i))).list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    public static List<MessageEntity> queryMessageList(long j) {
        return getDaoSession().getMessageEntityDao().queryBuilder().where(MessageEntityDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).orderAsc(MessageEntityDao.Properties.CreateTime).list();
    }

    public static void deleteMessageByType(int i) {
        getDaoSession().getDatabase().execSQL("delete from Message where TYPE=" + i);
    }

    public static List<MessageEntity> queryMessageListByType(long j, int i) {
        return getDaoSession().getMessageEntityDao().queryBuilder().where(MessageEntityDao.Properties.UserId.eq(Long.valueOf(j)), MessageEntityDao.Properties.Type.eq(Integer.valueOf(i))).orderAsc(MessageEntityDao.Properties.CreateTime).list();
    }

    public static long queryUnReadReportCount(long j, int i) {
        List<MessageEntity> list;
        if (i == 2) {
            list = getDaoSession().getMessageEntityDao().queryBuilder().where(MessageEntityDao.Properties.UserId.eq(Long.valueOf(j)), MessageEntityDao.Properties.MondayGenerate.eq(true), MessageEntityDao.Properties.Type.eq(3), MessageEntityDao.Properties.HasRead.eq(false)).orderDesc(MessageEntityDao.Properties.CreateTime).list();
            if ((list != null ? list.size() : 0) > 0) {
                Iterator<MessageEntity> it = list.iterator();
                Calendar calendar = Calendar.getInstance(Locale.CHINA);
                Calendar calendar2 = Calendar.getInstance(Locale.CHINA);
                while (it.hasNext()) {
                    calendar.setTime(DateUtil.string2Date(it.next().getStartDayMonday(), DateUtil.DATE_FORMAT_YMD));
                    calendar.add(5, 6);
                    if (calendar2.getTimeInMillis() - calendar.getTimeInMillis() < DateUtil.DAY) {
                        it.remove();
                    }
                }
            }
        } else if (i == 7) {
            list = getDaoSession().getMessageEntityDao().queryBuilder().where(MessageEntityDao.Properties.UserId.eq(Long.valueOf(j)), MessageEntityDao.Properties.SaturdayGenerate.eq(true), MessageEntityDao.Properties.Type.eq(3), MessageEntityDao.Properties.HasRead.eq(false)).orderDesc(MessageEntityDao.Properties.CreateTime).list();
            if ((list != null ? list.size() : 0) > 0) {
                Iterator<MessageEntity> it2 = list.iterator();
                Calendar calendar3 = Calendar.getInstance(Locale.CHINA);
                Calendar calendar4 = Calendar.getInstance(Locale.CHINA);
                while (it2.hasNext()) {
                    calendar3.setTime(DateUtil.string2Date(it2.next().getStartDaySaturday(), DateUtil.DATE_FORMAT_YMD));
                    calendar3.add(5, 6);
                    if (calendar4.getTimeInMillis() - calendar3.getTimeInMillis() < DateUtil.DAY) {
                        it2.remove();
                    }
                }
            }
        } else {
            list = getDaoSession().getMessageEntityDao().queryBuilder().where(MessageEntityDao.Properties.UserId.eq(Long.valueOf(j)), MessageEntityDao.Properties.SundayGenerate.eq(true), MessageEntityDao.Properties.Type.eq(3), MessageEntityDao.Properties.HasRead.eq(false)).orderDesc(MessageEntityDao.Properties.CreateTime).list();
            if ((list != null ? list.size() : 0) > 0) {
                Iterator<MessageEntity> it3 = list.iterator();
                Calendar calendar5 = Calendar.getInstance(Locale.CHINA);
                Calendar calendar6 = Calendar.getInstance(Locale.CHINA);
                while (it3.hasNext()) {
                    calendar5.setTime(DateUtil.string2Date(it3.next().getStartDaySunday(), DateUtil.DATE_FORMAT_YMD));
                    calendar5.add(5, 6);
                    if (calendar6.getTimeInMillis() - calendar5.getTimeInMillis() < DateUtil.DAY) {
                        it3.remove();
                    }
                }
            }
        }
        if (list == null) {
            return 0L;
        }
        return list.size();
    }

    public static List<MessageEntity> queryReportByWeekStart(long j, int i) {
        List<MessageEntity> list;
        if (i == 2) {
            list = getDaoSession().getMessageEntityDao().queryBuilder().where(MessageEntityDao.Properties.UserId.eq(Long.valueOf(j)), MessageEntityDao.Properties.MondayGenerate.eq(true), MessageEntityDao.Properties.Type.eq(3)).orderDesc(MessageEntityDao.Properties.CreateTime).list();
            if ((list != null ? list.size() : 0) > 0) {
                Iterator<MessageEntity> it = list.iterator();
                Calendar calendar = Calendar.getInstance(Locale.CHINA);
                Calendar calendar2 = Calendar.getInstance(Locale.CHINA);
                while (it.hasNext()) {
                    calendar.setTime(DateUtil.string2Date(it.next().getStartDayMonday(), DateUtil.DATE_FORMAT_YMD));
                    calendar.add(5, 6);
                    if (calendar2.getTimeInMillis() - calendar.getTimeInMillis() < DateUtil.DAY) {
                        it.remove();
                    }
                }
            }
        } else if (i == 7) {
            list = getDaoSession().getMessageEntityDao().queryBuilder().where(MessageEntityDao.Properties.UserId.eq(Long.valueOf(j)), MessageEntityDao.Properties.SaturdayGenerate.eq(true), MessageEntityDao.Properties.Type.eq(3)).orderDesc(MessageEntityDao.Properties.CreateTime).list();
            if ((list != null ? list.size() : 0) > 0) {
                Iterator<MessageEntity> it2 = list.iterator();
                Calendar calendar3 = Calendar.getInstance(Locale.CHINA);
                Calendar calendar4 = Calendar.getInstance(Locale.CHINA);
                while (it2.hasNext()) {
                    calendar3.setTime(DateUtil.string2Date(it2.next().getStartDaySaturday(), DateUtil.DATE_FORMAT_YMD));
                    calendar3.add(5, 6);
                    if (calendar4.getTimeInMillis() - calendar3.getTimeInMillis() < DateUtil.DAY) {
                        it2.remove();
                    }
                }
            }
        } else {
            list = getDaoSession().getMessageEntityDao().queryBuilder().where(MessageEntityDao.Properties.UserId.eq(Long.valueOf(j)), MessageEntityDao.Properties.SundayGenerate.eq(true), MessageEntityDao.Properties.Type.eq(3)).orderDesc(MessageEntityDao.Properties.CreateTime).list();
            if ((list != null ? list.size() : 0) > 0) {
                Iterator<MessageEntity> it3 = list.iterator();
                Calendar calendar5 = Calendar.getInstance(Locale.CHINA);
                Calendar calendar6 = Calendar.getInstance(Locale.CHINA);
                while (it3.hasNext()) {
                    calendar5.setTime(DateUtil.string2Date(it3.next().getStartDaySunday(), DateUtil.DATE_FORMAT_YMD));
                    calendar5.add(5, 6);
                    if (calendar6.getTimeInMillis() - calendar5.getTimeInMillis() < DateUtil.DAY) {
                        it3.remove();
                    }
                }
            }
        }
        return list;
    }

    public static long queryWeekReportCount(long j, int i) {
        if (queryReportByWeekStart(j, i) == null) {
            return 0L;
        }
        return r0.size();
    }

    public static long queryUnreadReportCount(long j) {
        return getDaoSession().getMessageEntityDao().queryBuilder().where(MessageEntityDao.Properties.UserId.eq(Long.valueOf(j)), MessageEntityDao.Properties.Type.eq(3), MessageEntityDao.Properties.HasRead.eq(false)).count();
    }

    public static List<MessageEntity> queryReadReportList(long j) {
        return getDaoSession().getMessageEntityDao().queryBuilder().where(MessageEntityDao.Properties.UserId.eq(Long.valueOf(j)), MessageEntityDao.Properties.Type.eq(3), MessageEntityDao.Properties.HasRead.eq(true), MessageEntityDao.Properties.HasUpload.eq(false)).list();
    }

    public static void addMessageEntity(MessageEntity messageEntity) {
        if (messageEntity == null) {
            return;
        }
        MessageEntity messageEntityQueryHealthReportByMonday = queryHealthReportByMonday(messageEntity.getUserId(), messageEntity.getStartDayMonday());
        if (messageEntityQueryHealthReportByMonday != null) {
            messageEntityQueryHealthReportByMonday.delete();
        }
        getDaoSession().getMessageEntityDao().insertOrReplace(messageEntity);
    }

    public static long queryUnReadMessageCount(long j) {
        return getDaoSession().getMessageEntityDao().queryBuilder().where(MessageEntityDao.Properties.UserId.eq(Long.valueOf(j)), MessageEntityDao.Properties.HasRead.eq(false)).count();
    }

    public static long queryUnReadMessageCountByType(long j, int i) {
        return getDaoSession().getMessageEntityDao().queryBuilder().where(MessageEntityDao.Properties.UserId.eq(Long.valueOf(j)), MessageEntityDao.Properties.HasRead.eq(false), MessageEntityDao.Properties.Type.eq(Integer.valueOf(i))).count();
    }

    public static void updateMessageReadStateByType(long j, int i, boolean z) {
        List<MessageEntity> list = getDaoSession().getMessageEntityDao().queryBuilder().where(MessageEntityDao.Properties.UserId.eq(Long.valueOf(j)), MessageEntityDao.Properties.Type.eq(Integer.valueOf(i)), MessageEntityDao.Properties.HasRead.notEq(Boolean.valueOf(z))).list();
        if (list == null || list.size() <= 0) {
            return;
        }
        for (MessageEntity messageEntity : list) {
            messageEntity.setHasRead(z);
            messageEntity.update();
        }
    }

    public static MessageEntity queryHealthReportByMonday(long j, String str) {
        List<MessageEntity> list = getDaoSession().getMessageEntityDao().queryBuilder().where(MessageEntityDao.Properties.UserId.eq(Long.valueOf(j)), MessageEntityDao.Properties.StartDayMonday.eq(str), MessageEntityDao.Properties.Type.eq(3)).list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    public static MessageEntity queryHealthReportBySaturday(long j, String str) {
        List<MessageEntity> list = getDaoSession().getMessageEntityDao().queryBuilder().where(MessageEntityDao.Properties.UserId.eq(Long.valueOf(j)), MessageEntityDao.Properties.StartDaySaturday.eq(str), MessageEntityDao.Properties.Type.eq(3)).list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    public static MessageEntity queryHealthReportBySunday(long j, String str) {
        List<MessageEntity> list = getDaoSession().getMessageEntityDao().queryBuilder().where(MessageEntityDao.Properties.UserId.eq(Long.valueOf(j)), MessageEntityDao.Properties.StartDaySunday.eq(str), MessageEntityDao.Properties.Type.eq(3)).list();
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    public static long addDataDownLoadStateRecord(DataDownLoadState dataDownLoadState) {
        if (dataDownLoadState == null) {
            return -1L;
        }
        DataDownLoadState dataDownloadState = getDataDownloadState(dataDownLoadState.getUserId(), dataDownLoadState.getDataName(), dataDownLoadState.getTaskId());
        if (dataDownloadState == null) {
            return getDaoSession().getDataDownLoadStateDao().insert(dataDownLoadState);
        }
        dataDownLoadState.setId(dataDownloadState.getId());
        getDaoSession().getDataDownLoadStateDao().update(dataDownLoadState);
        return dataDownLoadState.getId().longValue();
    }

    public static DataDownLoadState queryHomeDataDownloadState(long j, String str) {
        if (getDaoSession().getDataDownLoadStateDao().queryBuilder().where(DataDownLoadStateDao.Properties.UserId.eq(Long.valueOf(j)), DataDownLoadStateDao.Properties.DataName.eq(str), DataDownLoadStateDao.Properties.TaskId.eq(0)).count() == 0) {
            return null;
        }
        return getDaoSession().getDataDownLoadStateDao().queryBuilder().where(DataDownLoadStateDao.Properties.UserId.eq(Long.valueOf(j)), DataDownLoadStateDao.Properties.DataName.eq(str), DataDownLoadStateDao.Properties.TaskId.eq(0)).limit(1).unique();
    }

    public static List<DataDownLoadState> queryHistoryDataDownloadState(long j, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return getDaoSession().getDataDownLoadStateDao().queryBuilder().where(DataDownLoadStateDao.Properties.UserId.eq(Long.valueOf(j)), DataDownLoadStateDao.Properties.DataName.eq(str), DataDownLoadStateDao.Properties.TaskId.notEq(0)).list();
    }

    public static Pair<List<Long>, List<Long>> queryLoadingOrFailedTask(long j, List<String> list, String str, String str2, String str3) {
        List<DataDownLoadState> historyDownloadState;
        if (list == null || list.isEmpty() || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || (historyDownloadState = getHistoryDownloadState(j, list)) == null || historyDownloadState.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Calendar calendar2 = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(DateUtil.string2Date(str, str3));
        calendar2.setTime(DateUtil.string2Date(str2, str3));
        long timeInMillis = calendar.getTimeInMillis();
        long timeInMillis2 = calendar2.getTimeInMillis();
        Calendar calendar3 = Calendar.getInstance(Locale.CHINA);
        Calendar calendar4 = Calendar.getInstance(Locale.CHINA);
        for (DataDownLoadState dataDownLoadState : historyDownloadState) {
            if (dataDownLoadState.getDownloadState() != 3) {
                try {
                    calendar3.setTime(DateUtil.string2Date(dataDownLoadState.getParamsMap().get("start"), str3));
                    calendar4.setTime(DateUtil.string2Date(dataDownLoadState.getParamsMap().get("end"), str3));
                    if (timeInMillis2 >= calendar3.getTimeInMillis() && timeInMillis <= calendar4.getTimeInMillis()) {
                        if (dataDownLoadState.getDownloadState() == 4) {
                            arrayList.add(dataDownLoadState.getId());
                        } else {
                            arrayList2.add(dataDownLoadState.getId());
                        }
                    }
                } catch (Exception unused) {
                    if (dataDownLoadState.getDownloadState() == 4) {
                        arrayList.add(dataDownLoadState.getId());
                    } else {
                        arrayList2.add(dataDownLoadState.getId());
                    }
                }
            }
        }
        return new Pair<>(arrayList2, arrayList);
    }

    public static List<DataDownLoadState> getHistoryDownloadState(long j, List<String> list) {
        return getDaoSession().getDataDownLoadStateDao().queryBuilder().where(DataDownLoadStateDao.Properties.UserId.eq(Long.valueOf(j)), DataDownLoadStateDao.Properties.DataName.in(list), DataDownLoadStateDao.Properties.TaskId.notIn(0)).orderAsc(DataDownLoadStateDao.Properties.TaskId).list();
    }

    public static DataDownLoadState getHomeDataDownloadState(long j, String str) {
        return getDataDownloadState(j, str, 0);
    }

    public static List<DataDownLoadState> getHomeDataDownloadState(long j) {
        return getDaoSession().getDataDownLoadStateDao().queryBuilder().where(DataDownLoadStateDao.Properties.UserId.eq(Long.valueOf(j)), DataDownLoadStateDao.Properties.TaskId.eq(0)).list();
    }

    public static List<DataDownLoadState> getHistoryDataDownloadState(long j) {
        return getDaoSession().getDataDownLoadStateDao().queryBuilder().where(DataDownLoadStateDao.Properties.UserId.eq(Long.valueOf(j)), DataDownLoadStateDao.Properties.TaskId.notIn(0)).list();
    }

    public static List<DataDownLoadState> getHistoryDataDownloadState(long j, String str) {
        return getDaoSession().getDataDownLoadStateDao().queryBuilder().where(DataDownLoadStateDao.Properties.UserId.eq(Long.valueOf(j)), DataDownLoadStateDao.Properties.DataName.eq(str), DataDownLoadStateDao.Properties.TaskId.notIn(0)).list();
    }

    public static DataDownLoadState getDataDownloadState(long j, String str, int i) {
        if (getDaoSession().getDataDownLoadStateDao().queryBuilder().where(DataDownLoadStateDao.Properties.UserId.eq(Long.valueOf(j)), DataDownLoadStateDao.Properties.DataName.eq(str), DataDownLoadStateDao.Properties.TaskId.eq(Integer.valueOf(i))).count() == 0) {
            return null;
        }
        return getDaoSession().getDataDownLoadStateDao().queryBuilder().where(DataDownLoadStateDao.Properties.UserId.eq(Long.valueOf(j)), DataDownLoadStateDao.Properties.DataName.eq(str), DataDownLoadStateDao.Properties.TaskId.eq(Integer.valueOf(i))).limit(1).unique();
    }

    public static DataDownLoadState getDataDownloadStateById(long j) {
        if (getDaoSession().getDataDownLoadStateDao().queryBuilder().where(DataDownLoadStateDao.Properties.Id.eq(Long.valueOf(j)), new WhereCondition[0]).count() == 0) {
            return null;
        }
        return getDaoSession().getDataDownLoadStateDao().queryBuilder().where(DataDownLoadStateDao.Properties.Id.eq(Long.valueOf(j)), new WhereCondition[0]).limit(1).unique();
    }

    public static boolean queryAllDataLoadSuccess(long j, String str) {
        List<DataDownLoadState> list = getDaoSession().getDataDownLoadStateDao().queryBuilder().where(DataDownLoadStateDao.Properties.UserId.eq(Long.valueOf(j)), DataDownLoadStateDao.Properties.DataName.eq(str), DataDownLoadStateDao.Properties.TaskId.notEq(0), DataDownLoadStateDao.Properties.DownloadState.notEq(3)).list();
        return list == null || list.size() == 0;
    }

    public static int queryLatestDataLoadState(long j, String str) {
        DataDownLoadState dataDownLoadStateQueryHomeDataDownloadState = queryHomeDataDownloadState(j, str);
        if (dataDownLoadStateQueryHomeDataDownloadState == null) {
            return 4;
        }
        return dataDownLoadStateQueryHomeDataDownloadState.getDownloadState();
    }

    public static List<DataDownLoadState> queryAllDataDownloadState(long j) {
        return getDaoSession().getDataDownLoadStateDao().queryBuilder().where(DataDownLoadStateDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).list();
    }

    public static long getDataDownloadStateCount(long j) {
        return getDaoSession().getDataDownLoadStateDao().queryBuilder().where(DataDownLoadStateDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count();
    }

    public static void deleteMessageByType(long j, int i) {
        List<MessageEntity> list = getDaoSession().getMessageEntityDao().queryBuilder().where(MessageEntityDao.Properties.UserId.eq(Long.valueOf(j)), MessageEntityDao.Properties.Type.eq(Integer.valueOf(i))).list();
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<MessageEntity> it = list.iterator();
        while (it.hasNext()) {
            it.next().delete();
        }
        list.clear();
    }

    public static ActiveTimeDayData queryActiveTimeDayByDate(long j, String str) {
        if (!TextUtils.isEmpty(str) && getDaoSession().getActiveTimeDayDataDao().queryBuilder().where(ActiveTimeDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ActiveTimeDayDataDao.Properties.Date.eq(str)).count() > 0) {
            return getDaoSession().getActiveTimeDayDataDao().queryBuilder().where(ActiveTimeDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ActiveTimeDayDataDao.Properties.Date.eq(str)).limit(1).unique();
        }
        return null;
    }

    public static void deleteDataByKey(String str, String str2, Object obj) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        getDaoSession().getDatabase().execSQL("delete from " + str + " where " + str2 + "=" + obj);
    }

    public static ServerBloodOxyDayData queryBloodByDate(long j, String str) {
        if (!TextUtils.isEmpty(str) && getDaoSession().getServerBloodOxyDayDataDao().queryBuilder().where(ServerBloodOxyDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerBloodOxyDayDataDao.Properties.Date.eq(str)).count() > 0) {
            getDaoSession().getServerBloodOxyDayDataDao().queryBuilder().where(ServerBloodOxyDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerBloodOxyDayDataDao.Properties.Date.eq(str)).limit(1).unique();
        }
        return null;
    }

    public static CalorieDayData queryCalorieDayByDate(long j, String str) {
        if (!TextUtils.isEmpty(str) && getDaoSession().getCalorieDayDataDao().queryBuilder().where(CalorieDayDataDao.Properties.UserId.eq(Long.valueOf(j)), CalorieDayDataDao.Properties.Date.eq(str)).count() > 0) {
            return getDaoSession().getCalorieDayDataDao().queryBuilder().where(CalorieDayDataDao.Properties.UserId.eq(Long.valueOf(j)), CalorieDayDataDao.Properties.Date.eq(str)).limit(1).unique();
        }
        return null;
    }

    public static boolean hasCalorieDayData(long j, String str) {
        return !TextUtils.isEmpty(str) && getDaoSession().getCalorieDayDataDao().queryBuilder().where(CalorieDayDataDao.Properties.UserId.eq(Long.valueOf(j)), CalorieDayDataDao.Properties.Date.eq(str)).count() > 0;
    }

    public static LifeCycleItemBean queryLifeCycleItemBeanByDate(long j, String str) {
        if (!TextUtils.isEmpty(str) && getDaoSession().getLifeCycleItemBeanDao().queryBuilder().where(LifeCycleItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), LifeCycleItemBeanDao.Properties.Month.eq(str)).count() > 0) {
            return getDaoSession().getLifeCycleItemBeanDao().queryBuilder().where(LifeCycleItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), LifeCycleItemBeanDao.Properties.Month.eq(str)).limit(1).unique();
        }
        return null;
    }

    public static List<LifeCycleItemBean> getAllUploadLifeCycleBean(long j) {
        return getDaoSession().getLifeCycleItemBeanDao().queryBuilder().where(LifeCycleItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), LifeCycleItemBeanDao.Properties.Upload.eq(false)).list();
    }

    public static LifeCycleItemBean queryOldestLifeCycle(long j) {
        if (getDaoSession().getLifeCycleItemBeanDao().queryBuilder().where(LifeCycleItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count() == 0) {
            return null;
        }
        return getDaoSession().getLifeCycleItemBeanDao().queryBuilder().where(LifeCycleItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).orderAsc(LifeCycleItemBeanDao.Properties.TimeStamp).limit(1).unique();
    }

    public static void insertLifeCycle(List<LifeCycleItemBean> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        getDaoSession().getLifeCycleItemBeanDao().insertOrReplaceInTx(list);
    }

    public static void insertLifeCycle(LifeCycleItemBean lifeCycleItemBean) {
        if (lifeCycleItemBean == null) {
            return;
        }
        LifeCycleItemBean lifeCycleItemBeanQueryLifeCycleItemBeanByDate = queryLifeCycleItemBeanByDate(lifeCycleItemBean.getUserId(), lifeCycleItemBean.getMonth());
        if (lifeCycleItemBeanQueryLifeCycleItemBeanByDate != null) {
            lifeCycleItemBean.setId(lifeCycleItemBeanQueryLifeCycleItemBeanByDate.getId());
            getDaoSession().getLifeCycleItemBeanDao().update(lifeCycleItemBean);
        } else {
            getDaoSession().getLifeCycleItemBeanDao().insert(lifeCycleItemBean);
        }
    }

    public static StepDayData queryStepByDate(long j, String str) {
        if (!TextUtils.isEmpty(str) && getDaoSession().getStepDayDataDao().queryBuilder().where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(j)), StepDayDataDao.Properties.Date.eq(str)).count() > 0) {
            return getDaoSession().getStepDayDataDao().queryBuilder().where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(j)), StepDayDataDao.Properties.Date.eq(str)).limit(1).unique();
        }
        return null;
    }

    public static HealthVolumeData getRecentVolume() {
        if (getDaoSession().getHealthVolumeDataDao().queryBuilder().count() == 0) {
            return null;
        }
        return getDaoSession().getHealthVolumeDataDao().queryBuilder().where(HealthVolumeDataDao.Properties.LatestValue.gt(0), new WhereCondition[0]).orderDesc(HealthVolumeDataDao.Properties.Timestamp).limit(1).unique();
    }

    public static HealthVolumeData getHealthVolumeDate(long j, String str) {
        if (getDaoSession().getHealthVolumeDataDao().queryBuilder().where(HealthVolumeDataDao.Properties.UserId.eq(Long.valueOf(j)), HealthVolumeDataDao.Properties.Date.eq(str)).count() == 0) {
            return null;
        }
        return getDaoSession().getHealthVolumeDataDao().queryBuilder().where(HealthVolumeDataDao.Properties.UserId.eq(Long.valueOf(j)), HealthVolumeDataDao.Properties.Date.eq(str)).limit(1).unique();
    }

    public static List<HealthVolumeData> getAllNotUploadAmbientVolumeDayData(long j) {
        return getDaoSession().queryBuilder(HealthVolumeData.class).where(HealthVolumeDataDao.Properties.UserId.eq(Long.valueOf(j)), HealthVolumeDataDao.Properties.HasUpdate.eq(false)).list();
    }

    public static void saveHealthVolumeData(HealthVolumeData healthVolumeData) {
        if (healthVolumeData == null) {
            return;
        }
        HealthVolumeData healthVolumeDate = getHealthVolumeDate(healthVolumeData.getUserId(), healthVolumeData.getDate());
        if (healthVolumeDate == null) {
            getDaoSession().getHealthVolumeDataDao().insert(healthVolumeData);
        } else {
            healthVolumeData.setId(healthVolumeDate.getId());
            getDaoSession().getHealthVolumeDataDao().update(healthVolumeData);
        }
    }

    public static HealthVolumeData queryHealthVolumeData(long j, String str) {
        if (TextUtils.isEmpty(str) || getDaoSession().getHealthVolumeDataDao().queryBuilder().where(HealthVolumeDataDao.Properties.UserId.eq(Long.valueOf(j)), HealthVolumeDataDao.Properties.Date.eq(str)).count() == 0) {
            return null;
        }
        return getDaoSession().getHealthVolumeDataDao().queryBuilder().where(HealthVolumeDataDao.Properties.UserId.eq(Long.valueOf(j)), HealthVolumeDataDao.Properties.Date.eq(str)).limit(1).unique();
    }

    public static List<HealthVolumeData> queryHealthVolumeDataArea(long j, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return getDaoSession().getHealthVolumeDataDao().queryBuilder().where(HealthVolumeDataDao.Properties.UserId.eq(Long.valueOf(j)), HealthVolumeDataDao.Properties.Date.ge(str), HealthVolumeDataDao.Properties.Date.le(str2)).list();
    }

    public static long getHealthVolumeCount(long j) {
        return getDaoSession().getHealthVolumeDataDao().queryBuilder().where(HealthVolumeDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count();
    }

    public static WalkDayData queryWalkDayByDate(long j, String str) {
        if (!TextUtils.isEmpty(str) && getDaoSession().getWalkDayDataDao().queryBuilder().where(WalkDayDataDao.Properties.UserId.eq(Long.valueOf(j)), WalkDayDataDao.Properties.Date.eq(str)).count() > 0) {
            return getDaoSession().getWalkDayDataDao().queryBuilder().where(WalkDayDataDao.Properties.UserId.eq(Long.valueOf(j)), WalkDayDataDao.Properties.Date.eq(str)).limit(1).unique();
        }
        return null;
    }

    public static ServerSleepDayData querySleepDayByDate(long j, String str) {
        if (!TextUtils.isEmpty(str) && getDaoSession().getServerSleepDayDataDao().queryBuilder().where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerSleepDayDataDao.Properties.Date.eq(str)).count() > 0) {
            return getDaoSession().getServerSleepDayDataDao().queryBuilder().where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerSleepDayDataDao.Properties.Date.eq(str)).limit(1).unique();
        }
        return null;
    }

    public static SportHealth querySportHealthByDate(long j, String str) {
        if (!TextUtils.isEmpty(str) && getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.UserId.eq(Long.valueOf(j)), SportHealthDao.Properties.DateTime.eq(str)).count() > 0) {
            return getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.UserId.eq(Long.valueOf(j)), SportHealthDao.Properties.DateTime.eq(str)).limit(1).unique();
        }
        return null;
    }

    public static void saveActivityData(SportHealth sportHealth) {
        SportLogHelper.saveSportLog(TAG, "saveActivityData: " + sportHealth.getStartTime() + "时间戳" + sportHealth.getTimestamp());
        SportHealth activityData = getActivityData(sportHealth.getUserId().longValue(), sportHealth.getDateTime());
        if (activityData == null) {
            if (sportHealth.getSourceType() == 0) {
                sportHealth.setSourceType(2);
            }
            SportLogHelper.saveSportLog(TAG, "saveActivityData: null" + sportHealth.getStartTime());
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
        SportLogHelper.saveSportLog(TAG, "saveActivityData: 不等于null" + sportHealth.getStartTime());
        getDaoSession().update(sportHealth);
    }

    public static SportHealth getActivityData(long j, String str) {
        if (TextUtils.isEmpty(str) || getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.UserId.eq(Long.valueOf(j)), SportHealthDao.Properties.DateTime.eq(str)).count() == 0) {
            return null;
        }
        return getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.UserId.eq(Long.valueOf(j)), SportHealthDao.Properties.DateTime.eq(str)).limit(1).unique();
    }

    public static boolean hasActiveTimeDayData(long j, String str) {
        return !TextUtils.isEmpty(str) && getDaoSession().getActiveTimeDayDataDao().queryBuilder().where(ActiveTimeDayDataDao.Properties.Date.eq(str), ActiveTimeDayDataDao.Properties.UserId.eq(Long.valueOf(j))).count() > 0;
    }

    public static void saveAppGpsFromTrace(long j, SportGps sportGps, String str) {
        if (TextUtils.isEmpty(str) || sportGps == null || TextUtils.isEmpty(sportGps.getItems())) {
            return;
        }
        SportGpsData sportGpsData = new SportGpsData();
        sportGpsData.setUserId(j);
        sportGpsData.setTimeMillis(com.ido.common.utils.TimeUtil.convTimeYmdhmsToLong(str));
        sportGpsData.setGpsData(sportGps);
        sportGpsData.setDown(true);
        saveSportGpsData(sportGpsData);
    }

    public static void saveSportGpsData(SportGpsData sportGpsData) {
        if (sportGpsData == null) {
            return;
        }
        CommonLogUtil.d(TAG, "savAppGpsTrace: " + sportGpsData.toString());
        SportGpsData sportGpsData2 = getSportGpsData(sportGpsData.getUserId(), sportGpsData.timeMillis);
        if (sportGpsData2 == null) {
            getDaoSession().insertOrReplace(sportGpsData);
        } else {
            sportGpsData.setId(sportGpsData2.getId());
            getDaoSession().update(sportGpsData);
        }
    }

    public static void addAppGpsData(SportGpsData sportGpsData) {
        if (sportGpsData == null) {
            return;
        }
        getDaoSession().getSportGpsDataDao().insert(sportGpsData);
    }

    public static SportHealth getSportHealthRecordTime() {
        return getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.UserId.eq(Long.valueOf(RunTimeUtil.getInstance().getUserId())), SportHealthDao.Properties.RecoverTime.gt(0)).orderDesc(SportHealthDao.Properties.DateTime).limit(1).unique();
    }

    public static SportHealth queryRecentOxyUptake(long j) {
        try {
            return getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.UserId.eq(Long.valueOf(j)), SportHealthDao.Properties.Vo2max.gt(0)).orderDesc(SportHealthDao.Properties.Timestamp).limit(1).unique();
        } catch (Exception unused) {
            return null;
        }
    }

    public static List<SportHealth> getDaySportByDate(long j, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return queryHealthSportByLikeQuery(j, String.valueOf(str));
    }

    public static List<SportHealth> getDaySportByWeekDate(long j, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.UserId.eq(Long.valueOf(j)), SportHealthDao.Properties.DateTime.ge(str.trim() + " 00:00:00"), SportHealthDao.Properties.DateTime.le(str2.trim() + " 23:59:59"), SportHealthDao.Properties.Vo2max.gt(0)).list();
    }

    public static List<SportHealth> querySportHealthByMonth(long j, int i, int i2) {
        return queryHealthSportByLikeQuery(j, String.format("%d-%02d", Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public static List<SportHealth> querySportHealthByYearDate(long j, int i) {
        return queryHealthSportByLikeQuery(j, String.valueOf(i));
    }

    public static List<SportHealth> queryHealthSportByLikeQuery(long j, String str) {
        return getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.UserId.eq(Long.valueOf(j)), SportHealthDao.Properties.DateTime.like("%" + str + "%"), SportHealthDao.Properties.Vo2max.gt(0)).list();
    }

    public static SportGpsData getSportGpsData(long j, long j2) {
        if (getDaoSession().getSportGpsDataDao().queryBuilder().where(SportGpsDataDao.Properties.UserId.eq(Long.valueOf(j)), SportGpsDataDao.Properties.TimeMillis.eq(Long.valueOf(j2))).count() > 0) {
            return getDaoSession().getSportGpsDataDao().queryBuilder().where(SportGpsDataDao.Properties.UserId.eq(Long.valueOf(j)), SportGpsDataDao.Properties.TimeMillis.eq(Long.valueOf(j2))).limit(1).unique();
        }
        return null;
    }

    public static boolean execSql(String str) {
        Database database;
        boolean z = false;
        if (TextUtils.isEmpty(str) || (database = getDaoSession().getDatabase()) == null) {
            return false;
        }
        if (database.isDbLockedByCurrentThread()) {
            database.execSQL(str);
        } else {
            database.beginTransaction();
            try {
                try {
                    database.execSQL(str);
                    database.setTransactionSuccessful();
                    z = true;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } finally {
                database.endTransaction();
            }
        }
        return z;
    }

    public static Cursor execSql(String str, String[] strArr) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return getDaoSession().getDatabase().rawQuery(str, strArr);
    }

    public static void clearTable(AbstractDao abstractDao) {
        if (abstractDao == null) {
            return;
        }
        abstractDao.deleteAll();
    }

    public static void clearUserData(long j) {
        getDaoSession().getSportGpsDataDao().queryBuilder().where(SportGpsDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getCalorieDayDataDao().queryBuilder().where(CalorieDayDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getDataDownLoadStateDao().queryBuilder().where(DataDownLoadStateDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getHealthPressureDao().queryBuilder().where(HealthPressureDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getMapEngineDao().queryBuilder().where(MapEngineDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getMessageEntityDao().queryBuilder().where(MessageEntityDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getMultiDaysWalkTotalDataDao().queryBuilder().where(MultiDaysWalkTotalDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getPrivateSafeSettingDao().queryBuilder().where(PrivateSafeSettingDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getActiveTimeDayDataDao().queryBuilder().where(ActiveTimeDayDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getServerBloodOxyDayDataDao().queryBuilder().where(ServerBloodOxyDayDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getServerBloodOxyMonthDataDao().queryBuilder().where(ServerBloodOxyMonthDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getServerDaysBloodOxyDataDao().queryBuilder().where(ServerDaysBloodOxyDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getServerDaysSleepDataDao().queryBuilder().where(ServerDaysSleepDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getServerHeartRateDayDataDao().queryBuilder().where(ServerHeartRateDayDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getServerMultiMonthBloodOxyTotalDataDao().queryBuilder().where(ServerMultiMonthBloodOxyTotalDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getServerMultiMonthSleepTotalDataDao().queryBuilder().where(ServerMultiMonthSleepTotalDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getServerSleepDayDataDao().queryBuilder().where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getServerSleepMonthDataDao().queryBuilder().where(ServerSleepMonthDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getSportDistanceBeanDao().queryBuilder().where(SportDistanceBeanDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getStepDayDataDao().queryBuilder().where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getTimeSetDao().queryBuilder().where(TimeSetDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getUnitSettingDao().queryBuilder().where(UnitSettingDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getUserInfoDao().queryBuilder().where(UserInfoDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getUserMedalInfoDao().queryBuilder().where(UserMedalInfoDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getUserTargetNewDao().queryBuilder().where(UserTargetNewDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getUserTargetNewDao().queryBuilder().where(UserTargetNewDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getWalkDayDataDao().queryBuilder().where(WalkDayDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getWalkMonthTotalDataDao().queryBuilder().where(WalkMonthTotalDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getWalkYearTotalDataDao().queryBuilder().where(WalkYearTotalDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getWeekStartSettingDao().queryBuilder().where(WeekStartSettingDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().getWeightItemBeanDao().queryBuilder().where(WeightItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
        getDaoSession().clear();
    }

    private static void reallyConvertData(Database database, String str, String str2, long j) {
        if (database == null || TextUtils.isEmpty(str2)) {
            return;
        }
        database.execSQL("update " + str + " set " + str2 + "=" + j + " where " + str2 + "=-1");
        StringBuilder sb = new StringBuilder();
        sb.append("delete from ");
        sb.append(str);
        sb.append(" where ");
        sb.append(str2);
        sb.append("!=");
        sb.append(j);
        database.execSQL(sb.toString());
    }

    public static void switchDataToUser(long j) {
        if (j == -1) {
            return;
        }
        Database database = getDaoSession().getDatabase();
        reallyConvertData(database, SportGpsDataDao.TABLENAME, SportGpsDataDao.Properties.UserId.columnName, j);
        reallyConvertData(database, CalorieDayDataDao.TABLENAME, CalorieDayDataDao.Properties.UserId.columnName, j);
        reallyConvertData(database, "HEALTH_PRESSURE", HealthPressureDao.Properties.UserId.columnName, j);
        reallyConvertData(database, "MAP_ENGINE", MapEngineDao.Properties.UserId.columnName, j);
        reallyConvertData(database, MessageEntityDao.TABLENAME, MessageEntityDao.Properties.UserId.columnName, j);
        reallyConvertData(database, PrivateSafeSettingDao.TABLENAME, PrivateSafeSettingDao.Properties.UserId.columnName, j);
        reallyConvertData(database, ActiveTimeDayDataDao.TABLENAME, ActiveTimeDayDataDao.Properties.UserId.columnName, j);
        reallyConvertData(database, ServerBloodOxyDayDataDao.TABLENAME, ServerBloodOxyDayDataDao.Properties.UserId.columnName, j);
        reallyConvertData(database, ServerDaysBloodOxyDataDao.TABLENAME, ServerDaysBloodOxyDataDao.Properties.UserId.columnName, j);
        reallyConvertData(database, ServerBloodOxyMonthDataDao.TABLENAME, ServerBloodOxyMonthDataDao.Properties.UserId.columnName, j);
        reallyConvertData(database, ServerMultiMonthBloodOxyTotalDataDao.TABLENAME, ServerMultiMonthBloodOxyTotalDataDao.Properties.UserId.columnName, j);
        reallyConvertData(database, ServerSleepDayDataDao.TABLENAME, ServerSleepDayDataDao.Properties.UserId.columnName, j);
        reallyConvertData(database, ServerDaysSleepDataDao.TABLENAME, ServerDaysSleepDataDao.Properties.UserId.columnName, j);
        reallyConvertData(database, ServerSleepMonthDataDao.TABLENAME, ServerSleepMonthDataDao.Properties.UserId.columnName, j);
        reallyConvertData(database, ServerMultiMonthSleepTotalDataDao.TABLENAME, ServerMultiMonthSleepTotalDataDao.Properties.UserId.columnName, j);
        reallyConvertData(database, WalkDayDataDao.TABLENAME, WalkDayDataDao.Properties.UserId.columnName, j);
        reallyConvertData(database, MultiDaysWalkTotalDataDao.TABLENAME, MultiDaysWalkTotalDataDao.Properties.UserId.columnName, j);
        reallyConvertData(database, WalkMonthTotalDataDao.TABLENAME, WalkMonthTotalDataDao.Properties.UserId.columnName, j);
        reallyConvertData(database, WalkYearTotalDataDao.TABLENAME, WalkYearTotalDataDao.Properties.UserId.columnName, j);
        reallyConvertData(database, ServerHeartRateDayDataDao.TABLENAME, ServerHeartRateDayDataDao.Properties.UserId.columnName, j);
        reallyConvertData(database, SportDistanceBeanDao.TABLENAME, SportDistanceBeanDao.Properties.UserId.columnName, j);
        reallyConvertData(database, SportHealthDao.TABLENAME, SportHealthDao.Properties.UserId.columnName, j);
        reallyConvertData(database, StepDayDataDao.TABLENAME, StepDayDataDao.Properties.UserId.columnName, j);
        reallyConvertData(database, TimeSetDao.TABLENAME, TimeSetDao.Properties.UserId.columnName, j);
        reallyConvertData(database, UnitSettingDao.TABLENAME, UnitSettingDao.Properties.UserId.columnName, j);
        reallyConvertData(database, UserMedalInfoDao.TABLENAME, UserMedalInfoDao.Properties.UserId.columnName, j);
        reallyConvertData(database, WeekStartSettingDao.TABLENAME, WeekStartSettingDao.Properties.UserId.columnName, j);
        reallyConvertData(database, WeightItemBeanDao.TABLENAME, WeightItemBeanDao.Properties.UserId.columnName, j);
        reallyConvertData(database, HealthVolumeDataDao.TABLENAME, HealthVolumeDataDao.Properties.UserId.columnName, j);
    }

    public static void insertActiveTime(List<ActiveTimeDayData> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        getDaoSession().getActiveTimeDayDataDao().insertInTx(list);
    }

    public static void insertActiveTime(ActiveTimeDayData activeTimeDayData) {
        if (activeTimeDayData == null) {
            return;
        }
        getDaoSession().getActiveTimeDayDataDao().insertOrReplace(activeTimeDayData);
    }

    public static void insertBlood(List<ServerBloodOxyDayData> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        getDaoSession().getServerBloodOxyDayDataDao().insertInTx(list);
    }

    public static void insertBlood(ServerBloodOxyDayData serverBloodOxyDayData) {
        if (serverBloodOxyDayData == null) {
            return;
        }
        getDaoSession().getServerBloodOxyDayDataDao().insertOrReplace(serverBloodOxyDayData);
    }

    public static void insertCategory(List<CalorieDayData> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        getDaoSession().getCalorieDayDataDao().insertInTx(list);
    }

    public static void insertCategory(CalorieDayData calorieDayData) {
        if (calorieDayData == null) {
            return;
        }
        getDaoSession().getCalorieDayDataDao().insertOrReplace(calorieDayData);
    }

    public static void insertDistance(List<SportDistanceBean> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        getDaoSession().getSportDistanceBeanDao().insertInTx(list);
    }

    public static void insertHeartRate(List<ServerHeartRateDayData> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        getDaoSession().getServerHeartRateDayDataDao().insertInTx(list);
    }

    public static void insertHeartRate(ServerHeartRateDayData serverHeartRateDayData) {
        if (serverHeartRateDayData == null) {
            return;
        }
        getDaoSession().getServerHeartRateDayDataDao().insertOrReplace(serverHeartRateDayData);
    }

    public static void insertPressure(List<HealthPressure> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        getDaoSession().getHealthPressureDao().insertInTx(list);
    }

    public static void insertSleep(List<ServerSleepDayData> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        getDaoSession().getServerSleepDayDataDao().insertInTx(list);
    }

    public static void insertSleep(ServerSleepDayData serverSleepDayData) {
        if (serverSleepDayData == null) {
            return;
        }
        getDaoSession().getServerSleepDayDataDao().insertOrReplace(serverSleepDayData);
    }

    public static void insertStep(List<StepDayData> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        getDaoSession().getStepDayDataDao().insertInTx(list);
    }

    public static void insertStep(StepDayData stepDayData) {
        if (stepDayData == null) {
            return;
        }
        getDaoSession().getStepDayDataDao().insertOrReplace(stepDayData);
    }

    public static void insertWalk(List<WalkDayData> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        getDaoSession().getWalkDayDataDao().insertInTx(list);
    }

    public static boolean hasWalkData(long j, String str) {
        return !TextUtils.isEmpty(str) && getDaoSession().getWalkDayDataDao().queryBuilder().where(WalkDayDataDao.Properties.UserId.eq(Long.valueOf(j)), WalkDayDataDao.Properties.Date.eq(str)).count() > 0;
    }

    public static void insertWalk(WalkDayData walkDayData) {
        if (walkDayData == null) {
            return;
        }
        getDaoSession().getWalkDayDataDao().insertOrReplace(walkDayData);
    }

    public static void insertWeight(List<WeightItemBean> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        getDaoSession().getWeightItemBeanDao().insertInTx(list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.util.List<int[]>] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0070 -> B:27:0x0071). Please report as a decompilation issue!!! */
    public static List<int[]> convertHeartItems(String str) {
        ?? r7;
        ?? r1 = 0;
        r1 = 0;
         = 0;
        ?? r12 = 0;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Gson gson = new Gson();
        try {
        } catch (Exception e2) {
            e = e2;
        }
        if (!str.contains("hr_value")) {
            r7 = (List) gson.fromJson(str, new TypeToken<List<int[]>>() { // from class: com.ido.life.util.GreenDaoUtil.1
            }.getType());
        } else {
            List list = (List) gson.fromJson(str, new TypeToken<List<HeartItemBean>>() { // from class: com.ido.life.util.GreenDaoUtil.2
            }.getType());
            if (list == null || list.size() <= 0) {
                r7 = r12;
                r1 = r12;
            } else {
                ArrayList arrayList = new ArrayList();
                try {
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        HeartItemBean heartItemBean = (HeartItemBean) list.get(i);
                        if (heartItemBean != null) {
                            arrayList.add(new int[]{heartItemBean.getHrValue(), heartItemBean.getOffset()});
                        }
                    }
                    r7 = arrayList;
                    r1 = size;
                } catch (Exception e3) {
                    e = e3;
                    r1 = arrayList;
                    e.printStackTrace();
                    r12 = r1;
                    r7 = r12;
                    r1 = r12;
                }
            }
        }
        return r7;
    }

    public static ServerHeartRateDayData getDayHeartRateByDate(long j, String str) {
        if (TextUtils.isEmpty(str) || getDaoSession().getServerHeartRateDayDataDao().queryBuilder().where(ServerHeartRateDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerHeartRateDayDataDao.Properties.Date.eq(str)).count() == 0) {
            return null;
        }
        return getDaoSession().getServerHeartRateDayDataDao().queryBuilder().where(ServerHeartRateDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerHeartRateDayDataDao.Properties.Date.eq(str)).limit(1).unique();
    }

    public static ServerHeartRateDayData queryRecentHeartRate(long j) {
        if (getDaoSession().getServerHeartRateDayDataDao().queryBuilder().where(ServerHeartRateDayDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count() == 0) {
            return null;
        }
        return getDaoSession().getServerHeartRateDayDataDao().queryBuilder().where(ServerHeartRateDayDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).orderDesc(ServerHeartRateDayDataDao.Properties.Timestamp).limit(1).unique();
    }

    public static List<ServerHeartRateDayData> queryHeartRateColumnByDateArea(long j, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return getDaoSession().getServerHeartRateDayDataDao().queryBuilder().where(ServerHeartRateDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerHeartRateDayDataDao.Properties.Date.ge(str), ServerHeartRateDayDataDao.Properties.Date.le(str2)).list();
    }

    public static List<ServerHeartRateDayData> queryHeartRateYearData(long j, int i) {
        return queryHeartRateColumnByLikeQuery(j, String.valueOf(i));
    }

    public static List<ServerHeartRateDayData> queryHeartRateMonthData(long j, int i, int i2) {
        return queryHeartRateColumnByLikeQuery(j, String.format("%d-%02d", Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public static List<ServerHeartRateDayData> queryHeartRateColumnByLikeQuery(long j, String str) {
        return getDaoSession().getServerHeartRateDayDataDao().queryBuilder().where(ServerHeartRateDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerHeartRateDayDataDao.Properties.Date.like("%" + str + "%")).list();
    }

    public static ServerHeartRateDayData queryHeartRateByDate(long j, String str) {
        ServerHeartRateDayData serverHeartRateDayData;
        String[] strArr = {ServerHeartRateDayDataDao.Properties.Date.columnName, ServerHeartRateDayDataDao.Properties.MinValue.columnName, ServerHeartRateDayDataDao.Properties.MaxValue.columnName, ServerHeartRateDayDataDao.Properties.AvgValue.columnName, ServerHeartRateDayDataDao.Properties.SilentValue.columnName, ServerHeartRateDayDataDao.Properties.LatestValue.columnName};
        int length = strArr.length;
        String strConcat = "select ";
        for (int i = 0; i < length; i++) {
            strConcat = i == length - 1 ? strConcat.concat(strArr[i]) : strConcat.concat(strArr[i] + AppInfo.DELIM);
        }
        Cursor cursorRawQuery = getDaoSession().getDatabase().rawQuery(strConcat.concat(" from SERVER_HEART_RATE_DAY_DATA").concat(" where " + ServerHeartRateDayDataDao.Properties.UserId.columnName + "=? and " + ServerHeartRateDayDataDao.Properties.Date.columnName + "=?"), new String[]{String.valueOf(j), str});
        if (cursorRawQuery == null || !cursorRawQuery.moveToNext()) {
            serverHeartRateDayData = null;
        } else {
            serverHeartRateDayData = new ServerHeartRateDayData();
            serverHeartRateDayData.setDate(str);
            serverHeartRateDayData.setMinValue(cursorRawQuery.getInt(1));
            serverHeartRateDayData.setMaxValue(cursorRawQuery.getInt(2));
            serverHeartRateDayData.setAvgValue(cursorRawQuery.getInt(3));
            serverHeartRateDayData.setSilentValue(cursorRawQuery.getInt(4));
            serverHeartRateDayData.setLatestValue(cursorRawQuery.getInt(5));
        }
        if (cursorRawQuery != null) {
            try {
                cursorRawQuery.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return serverHeartRateDayData;
    }

    public static List<HealthPressure> queryHealthPressureByDateArea(long j, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return getDaoSession().getHealthPressureDao().queryBuilder().where(HealthPressureDao.Properties.UserId.eq(Long.valueOf(j)), HealthPressureDao.Properties.Date.ge(str), HealthPressureDao.Properties.Date.le(str2)).list();
    }

    public static List<HealthPressure> queryHealthPressureByYear(long j, int i) {
        return queryHealthPressureByLikeQuery(j, String.valueOf(i));
    }

    public static List<HealthPressure> queryHealthPressureByMonth(long j, int i, int i2) {
        return queryHealthPressureByLikeQuery(j, String.format("%d-%02d", Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public static List<HealthPressure> queryHealthPressureByLikeQuery(long j, String str) {
        return getDaoSession().getHealthPressureDao().queryBuilder().where(HealthPressureDao.Properties.UserId.eq(Long.valueOf(j)), HealthPressureDao.Properties.Date.like("%" + str + "%")).list();
    }

    public static HealthPressure queryHealthPressureByDate(long j, String str) {
        HealthPressure healthPressure;
        String[] strArr = {HealthPressureDao.Properties.Date.columnName, HealthPressureDao.Properties.MaxPressure.columnName, HealthPressureDao.Properties.MinPressure.columnName, HealthPressureDao.Properties.AvgPressure.columnName, HealthPressureDao.Properties.LastestPressure.columnName, HealthPressureDao.Properties.RelaxRatio.columnName, HealthPressureDao.Properties.NormalRatio.columnName, HealthPressureDao.Properties.MediumRatio.columnName, HealthPressureDao.Properties.HigherRatio.columnName, HealthPressureDao.Properties.RelaxCount.columnName, HealthPressureDao.Properties.NormalCount.columnName, HealthPressureDao.Properties.MediumCount.columnName, HealthPressureDao.Properties.HigherCount.columnName};
        int length = strArr.length;
        String strConcat = "select ";
        for (int i = 0; i < length; i++) {
            strConcat = i == length - 1 ? strConcat.concat(strArr[i]) : strConcat.concat(strArr[i] + AppInfo.DELIM);
        }
        Cursor cursorRawQuery = getDaoSession().getDatabase().rawQuery(strConcat.concat(" from HEALTH_PRESSURE").concat(" where " + HealthPressureDao.Properties.UserId.columnName + "=? and " + HealthPressureDao.Properties.Date.columnName + "=?"), new String[]{String.valueOf(j), str});
        if (cursorRawQuery == null || !cursorRawQuery.moveToNext()) {
            healthPressure = null;
        } else {
            healthPressure = new HealthPressure();
            healthPressure.setDate(str);
            healthPressure.setMaxPressure(cursorRawQuery.getInt(1));
            healthPressure.setMinPressure(cursorRawQuery.getInt(2));
            healthPressure.setAvgPressure(cursorRawQuery.getInt(3));
            healthPressure.setLastestPressure(cursorRawQuery.getInt(4));
            healthPressure.setRelaxRatio(cursorRawQuery.getInt(5));
            healthPressure.setNormalRatio(cursorRawQuery.getInt(6));
            healthPressure.setMediumRatio(cursorRawQuery.getInt(7));
            healthPressure.setHigherRatio(cursorRawQuery.getInt(8));
            healthPressure.setRelaxCount(cursorRawQuery.getInt(9));
            healthPressure.setNormalCount(cursorRawQuery.getInt(10));
            healthPressure.setMediumCount(cursorRawQuery.getInt(11));
            healthPressure.setHigherCount(cursorRawQuery.getInt(12));
        }
        if (cursorRawQuery != null) {
            try {
                cursorRawQuery.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return healthPressure;
    }

    public static List<SportDistanceBean> queryDistanceByDateArea(long j, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return getDaoSession().getSportDistanceBeanDao().queryBuilder().where(SportDistanceBeanDao.Properties.UserId.eq(Long.valueOf(j)), SportDistanceBeanDao.Properties.Date.ge(str), SportDistanceBeanDao.Properties.Date.le(str2)).list();
    }

    public static List<SportDistanceBean> queryDistanceByMonth(long j, int i, int i2) {
        return queryDistanceLikeQuery(j, String.format("%d-%02d", Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public static List<SportDistanceBean> queryDistanceByYear(long j, int i) {
        return queryDistanceLikeQuery(j, String.valueOf(i));
    }

    public static List<SportDistanceBean> queryDistanceLikeQuery(long j, String str) {
        return getDaoSession().getSportDistanceBeanDao().queryBuilder().where(SportDistanceBeanDao.Properties.UserId.eq(Long.valueOf(j)), SportDistanceBeanDao.Properties.Date.like("%" + str + "%")).list();
    }

    public static boolean hasPressureRecord(long j, String str, String str2) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || getDaoSession().getHealthPressureDao().queryBuilder().where(HealthPressureDao.Properties.UserId.eq(Long.valueOf(j)), HealthPressureDao.Properties.Date.ge(str), HealthPressureDao.Properties.Date.le(str2)).count() <= 0) ? false : true;
    }

    public static boolean hasPressure(long j, String str) {
        return !TextUtils.isEmpty(str) && getDaoSession().getHealthPressureDao().queryBuilder().where(HealthPressureDao.Properties.UserId.eq(Long.valueOf(j)), HealthPressureDao.Properties.Date.eq(str)).count() > 0;
    }

    public static boolean hasWeightRecord(long j, String str, String str2) {
        return (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || getDaoSession().getWeightItemBeanDao().queryBuilder().where(WeightItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), WeightItemBeanDao.Properties.Date.ge(str), WeightItemBeanDao.Properties.Date.le(str2)).count() <= 0) ? false : true;
    }

    public static boolean hasWeightData(long j, String str) {
        return !TextUtils.isEmpty(str) && getDaoSession().getWeightItemBeanDao().queryBuilder().where(WeightItemBeanDao.Properties.UserId.eq(Long.valueOf(j)), WeightItemBeanDao.Properties.Date.eq(str)).count() > 0;
    }

    public static SportDistanceBean queryDistanceByDate(long j, String str) {
        SportDistanceBean sportDistanceBean;
        String[] strArr = {SportDistanceBeanDao.Properties.Date.columnName, SportDistanceBeanDao.Properties.TotalDistance.columnName};
        int length = strArr.length;
        String strConcat = "select ";
        for (int i = 0; i < length; i++) {
            strConcat = i == length - 1 ? strConcat.concat(strArr[i]) : strConcat.concat(strArr[i] + AppInfo.DELIM);
        }
        Cursor cursorRawQuery = getDaoSession().getDatabase().rawQuery(strConcat.concat(" from SportDistance").concat(" where " + SportDistanceBeanDao.Properties.UserId.columnName + "=? and " + SportDistanceBeanDao.Properties.Date.columnName + "=?"), new String[]{String.valueOf(j), str});
        if (cursorRawQuery == null || !cursorRawQuery.moveToNext()) {
            sportDistanceBean = null;
        } else {
            sportDistanceBean = new SportDistanceBean();
            sportDistanceBean.setDate(str);
            sportDistanceBean.setTotalDistance(cursorRawQuery.getInt(1));
        }
        if (cursorRawQuery != null) {
            try {
                cursorRawQuery.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return sportDistanceBean;
    }

    public static boolean hasDistanceData(long j, String str) {
        return !TextUtils.isEmpty(str) && getDaoSession().getSportDistanceBeanDao().queryBuilder().where(SportDistanceBeanDao.Properties.Date.eq(str), SportDistanceBeanDao.Properties.UserId.eq(Long.valueOf(j))).count() > 0;
    }

    public static DataPullConfigInfo queryDataPullConfigInfo(long j) {
        if (getDaoSession().getDataPullConfigInfoDao().queryBuilder().where(DataPullConfigInfoDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count() == 0) {
            return null;
        }
        return getDaoSession().getDataPullConfigInfoDao().queryBuilder().where(DataPullConfigInfoDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).limit(1).unique();
    }

    public static void addDataPullConfigInfo(DataPullConfigInfo dataPullConfigInfo) {
        if (dataPullConfigInfo == null) {
            return;
        }
        deleteDataPullConfigInfo(dataPullConfigInfo.getUserId());
        getDaoSession().getDataPullConfigInfoDao().insert(dataPullConfigInfo);
    }

    public static void deleteDataPullConfigInfo(long j) {
        getDaoSession().getDatabase().execSQL("delete from DATA_PULL_CONFIG_INFO where " + DataPullConfigInfoDao.Properties.UserId.columnName + "=" + j);
    }

    public static void saveConfig(boolean z, boolean z2, boolean z3, final PrivacyandSecurityCallBack privacyandSecurityCallBack) {
        ArrayList arrayList = new ArrayList();
        PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting = queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
        if (privateSafeSettingQueryPrivateSafeSetting != null) {
            privateSafeSettingQueryPrivateSafeSetting.setSaveSportData(z2);
            privateSafeSettingQueryPrivateSafeSetting.setSaveHealthData(z3);
            privateSafeSettingQueryPrivateSafeSetting.setSavePrivateData(z);
            privateSafeSettingQueryPrivateSafeSetting.setUploadSuccess(false);
            privateSafeSettingQueryPrivateSafeSetting.update();
        } else {
            PrivateSafeSetting privateSafeSetting = new PrivateSafeSetting();
            privateSafeSetting.setSavePrivateData(z);
            privateSafeSetting.setSaveHealthData(z3);
            privateSafeSetting.setSaveSportData(z2);
            privateSafeSetting.setUserId(RunTimeUtil.getInstance().getUserId());
            privateSafeSetting.setUploadSuccess(false);
            addPrivateSafeSetting(privateSafeSetting);
        }
        String str = SavePrivateSafeSettingBean.ON;
        arrayList.add(new SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem(SavePrivateSafeSettingBean.TYPE_PRIVATE, z ? SavePrivateSafeSettingBean.ON : SavePrivateSafeSettingBean.OFF));
        arrayList.add(new SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem(SavePrivateSafeSettingBean.TYPE_HEALTH, z3 ? SavePrivateSafeSettingBean.ON : SavePrivateSafeSettingBean.OFF));
        if (!z2) {
            str = SavePrivateSafeSettingBean.OFF;
        }
        arrayList.add(new SavePrivateSafeSettingBean.SavePrivateSafeSettingBeanItem(SavePrivateSafeSettingBean.TYPE_SPORT, str));
        AccountManager.savePriveteSafeSetting(new SavePrivateSafeSettingBean(arrayList), new AccountManager.OnCommCallback<BaseEntity>() { // from class: com.ido.life.util.GreenDaoUtil.3
            @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
            public void onSuccess(BaseEntity baseEntity) {
                PrivateSafeSetting privateSafeSettingQueryPrivateSafeSetting2 = GreenDaoUtil.queryPrivateSafeSetting(RunTimeUtil.getInstance().getUserId());
                if (privateSafeSettingQueryPrivateSafeSetting2 != null) {
                    privateSafeSettingQueryPrivateSafeSetting2.setUploadSuccess(true);
                    privateSafeSettingQueryPrivateSafeSetting2.update();
                }
                PrivacyandSecurityCallBack privacyandSecurityCallBack2 = privacyandSecurityCallBack;
                if (privacyandSecurityCallBack2 != null) {
                    privacyandSecurityCallBack2.onSuccess(baseEntity);
                }
            }

            @Override // com.ido.life.data.me.remote.AccountManager.OnCommCallback
            public void onFailed(String str2) {
                PrivacyandSecurityCallBack privacyandSecurityCallBack2 = privacyandSecurityCallBack;
                if (privacyandSecurityCallBack2 != null) {
                    privacyandSecurityCallBack2.onFailed(str2);
                }
            }
        });
    }

    public static ServerBloodOxyDayData getBloodOxyDailyDataByDate(long j, String str) {
        if (TextUtils.isEmpty(str) || getDaoSession().getServerBloodOxyDayDataDao().queryBuilder().where(ServerBloodOxyDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerBloodOxyDayDataDao.Properties.Date.eq(str)).count() == 0) {
            return null;
        }
        return getDaoSession().getServerBloodOxyDayDataDao().queryBuilder().where(ServerBloodOxyDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerBloodOxyDayDataDao.Properties.Date.eq(str)).limit(1).unique();
    }

    public static long getBloodCount(long j) {
        return getDaoSession().getServerBloodOxyDayDataDao().queryBuilder().where(ServerBloodOxyDayDataDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count();
    }

    public static ServerSleepDayData getSleepDailyDataByDate(long j, String str) {
        if (TextUtils.isEmpty(str) || getDaoSession().getServerSleepDayDataDao().queryBuilder().where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerSleepDayDataDao.Properties.Date.eq(str)).count() == 0) {
            return null;
        }
        return getDaoSession().getServerSleepDayDataDao().queryBuilder().where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerSleepDayDataDao.Properties.Date.eq(str)).orderDesc(ServerSleepDayDataDao.Properties.EndTimeMinuteOffset).limit(1).unique();
    }

    public static List<ServerSleepDayData> querySleepDailyDataByDate(long j, String str) {
        return getDaoSession().getServerSleepDayDataDao().queryBuilder().where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(j)), ServerSleepDayDataDao.Properties.Date.eq(str)).list();
    }

    public static StepDayData getStepDailyDataByDate(long j, String str) {
        if (TextUtils.isEmpty(str) || getDaoSession().getStepDayDataDao().queryBuilder().where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(j)), StepDayDataDao.Properties.Date.eq(str)).count() == 0) {
            return null;
        }
        return getDaoSession().getStepDayDataDao().queryBuilder().where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(j)), StepDayDataDao.Properties.Date.eq(str)).limit(1).unique();
    }

    public static CalorieDayData getCalorieDailyDataByDate(long j, String str) {
        if (TextUtils.isEmpty(str) || getDaoSession().getCalorieDayDataDao().queryBuilder().where(CalorieDayDataDao.Properties.UserId.eq(Long.valueOf(j)), CalorieDayDataDao.Properties.Date.eq(str)).count() == 0) {
            return null;
        }
        return getDaoSession().getCalorieDayDataDao().queryBuilder().where(CalorieDayDataDao.Properties.UserId.eq(Long.valueOf(j)), CalorieDayDataDao.Properties.Date.eq(str)).limit(1).unique();
    }

    public static long getTodaySportRecordCount(long j) {
        String formatDate = DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate());
        return getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.UserId.eq(Long.valueOf(j)), SportHealthDao.Properties.DateTime.like("%" + formatDate + "%")).count();
    }

    public static long getTodaySportRecordCountByType(long j, int i) {
        String formatDate = DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, DateUtil.getTodayDate());
        return getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.UserId.eq(Long.valueOf(j)), SportHealthDao.Properties.DateTime.like("%" + formatDate + "%"), SportHealthDao.Properties.Type.eq(Integer.valueOf(i))).count();
    }

    public static long getNearlySevenDasySportRecordCount(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.getTodayDate());
        calendar.set(5, calendar.get(5) - 6);
        return getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.UserId.eq(Long.valueOf(j)), SportHealthDao.Properties.DateTime.gt(DateUtil.getFormatDate(DateUtil.DATE_FORMAT_YMD, calendar.getTime()))).count();
    }

    public static void clearVisitorAllDao() {
        clearTable(getDaoSession().getUserInfoDao());
    }

    public static void clearCache() {
        Collection<AbstractDao<?, ?>> allDaos = getDaoSession().getAllDaos();
        if (allDaos == null || allDaos.size() <= 0) {
            return;
        }
        Iterator<AbstractDao<?, ?>> it = allDaos.iterator();
        while (it.hasNext()) {
            it.next().deleteAll();
        }
    }

    public static MenstruationConfig queryMenstruationConfig(long j) {
        if (getDaoSession().getMenstruationConfigDao().queryBuilder().where(MenstruationConfigDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).count() == 0) {
            return null;
        }
        return getDaoSession().getMenstruationConfigDao().queryBuilder().where(MenstruationConfigDao.Properties.UserId.eq(Long.valueOf(j)), new WhereCondition[0]).limit(1).unique();
    }

    public static void addMenstruationConfig(MenstruationConfig menstruationConfig) {
        if (menstruationConfig == null) {
            return;
        }
        MenstruationConfig menstruationConfigQueryMenstruationConfig = queryMenstruationConfig(menstruationConfig.getUserId());
        if (menstruationConfigQueryMenstruationConfig != null) {
            menstruationConfig.setId(menstruationConfigQueryMenstruationConfig.getId());
            getDaoSession().getMenstruationConfigDao().update(menstruationConfig);
        } else {
            getDaoSession().getMenstruationConfigDao().insert(menstruationConfig);
        }
    }
}