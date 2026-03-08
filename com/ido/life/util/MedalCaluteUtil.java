package com.ido.life.util;

import android.text.TextUtils;
import android.util.Pair;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.GsonUtil;
import com.ido.life.constants.Constants;
import com.ido.life.database.model.CalorieDayData;
import com.ido.life.database.model.CalorieDayDataDao;
import com.ido.life.database.model.DaoSession;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.SportHealthDao;
import com.ido.life.database.model.StepDayData;
import com.ido.life.database.model.StepDayDataDao;
import com.ido.life.database.model.UserMedalInfo;
import com.ido.life.database.model.UserMedalInfoDao;
import com.ido.life.database.model.UserTargetNew;
import com.ido.life.enums.UserModelEnum;
import com.ido.life.util.eventbus.EventBusHelper;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.greenrobot.greendao.query.WhereCondition;

/* JADX INFO: loaded from: classes3.dex */
public class MedalCaluteUtil {
    private static final String TAG = MedalCaluteUtil.class.getSimpleName();

    private static String getToday() {
        return DateUtil.format(Calendar.getInstance(Locale.CHINA).getTime(), DateUtil.DATE_FORMAT_YMD);
    }

    public static void caluteStep() {
        Pair<StepDayData, Integer> stepHasToTargetInfo;
        Pair<StepDayData, Integer> stepHasToTargetInfo2;
        StepDayData stepDayDataStepHasToTarget;
        if (!queryUserMedalInfo(UserModelEnum.STEP_TARGET.getMedalId()) && (stepDayDataStepHasToTarget = stepHasToTarget()) != null) {
            printAndSaveLog("用户完成了步数达标任务:stepDayData=" + stepDayDataStepHasToTarget.toString());
            UserMedalInfo userMedalInfo = new UserMedalInfo();
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            calendar.setTimeInMillis(stepDayDataStepHasToTarget.getTimeStamp());
            String str = DateUtil.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
            printAndSaveLog("获取了第一次步数达标勋章 Date=" + str);
            userMedalInfo.setDate(str);
            userMedalInfo.setMedalId(UserModelEnum.STEP_TARGET.getMedalId());
            userMedalInfo.setShowToUser(false);
            userMedalInfo.setUserId(RunTimeUtil.getInstance().getUserId());
            GreenDaoUtil.addUserMedalInfo(userMedalInfo);
            if (!userMedalInfo.isShowToUser()) {
                EventBusHelper.post(606);
            }
        }
        if (!queryUserMedalInfo(UserModelEnum.STEP_7.getMedalId()) && (stepHasToTargetInfo2 = getStepHasToTargetInfo(UserModelEnum.STEP_7.getTargetValue())) != null && stepHasToTargetInfo2.first != null) {
            printAndSaveLog("获取了七天步数达标勋章获得stepDayDataPair=" + GsonUtil.toJson(stepHasToTargetInfo2));
            UserMedalInfo userMedalInfo2 = new UserMedalInfo();
            Calendar calendar2 = Calendar.getInstance(Locale.getDefault());
            calendar2.setTimeInMillis(((StepDayData) stepHasToTargetInfo2.first).getTimeStamp());
            String str2 = DateUtil.format(calendar2.getTime(), "yyyy-MM-dd HH:mm:ss");
            printAndSaveLog("获取连续七天步数达标勋章 Date=" + str2);
            userMedalInfo2.setDate(str2);
            userMedalInfo2.setMedalId(UserModelEnum.STEP_7.getMedalId());
            userMedalInfo2.setShowToUser(false);
            userMedalInfo2.setUserId(RunTimeUtil.getInstance().getUserId());
            GreenDaoUtil.addUserMedalInfo(userMedalInfo2);
            if (!userMedalInfo2.isShowToUser()) {
                EventBusHelper.post(607);
            }
        }
        if (queryUserMedalInfo(UserModelEnum.STEP_21.getMedalId()) || (stepHasToTargetInfo = getStepHasToTargetInfo(UserModelEnum.STEP_21.getTargetValue())) == null || stepHasToTargetInfo.first == null) {
            return;
        }
        printAndSaveLog("连续21天步数达标 stepDayDataPair=" + GsonUtil.toJson(stepHasToTargetInfo));
        UserMedalInfo userMedalInfo3 = new UserMedalInfo();
        Calendar calendar3 = Calendar.getInstance(Locale.getDefault());
        calendar3.setTimeInMillis(((StepDayData) stepHasToTargetInfo.first).getTimeStamp());
        String str3 = DateUtil.format(calendar3.getTime(), "yyyy-MM-dd HH:mm:ss");
        printAndSaveLog("获取了连续21天步数达标勋章 Date=" + str3);
        userMedalInfo3.setDate(str3);
        userMedalInfo3.setMedalId(UserModelEnum.STEP_21.getMedalId());
        userMedalInfo3.setShowToUser(false);
        userMedalInfo3.setUserId(RunTimeUtil.getInstance().getUserId());
        GreenDaoUtil.addUserMedalInfo(userMedalInfo3);
        if (userMedalInfo3.isShowToUser()) {
            return;
        }
        EventBusHelper.post(608);
    }

    public static void caluteCalorie() {
        Pair<CalorieDayData, Integer> caloriesHasToTargetInfo;
        Pair<CalorieDayData, Integer> caloriesHasToTargetInfo2;
        CalorieDayData calorieDayDataCaluteActiveCalories;
        if (!queryUserMedalInfo(UserModelEnum.ACTIVE_TARGET.getMedalId()) && (calorieDayDataCaluteActiveCalories = caluteActiveCalories()) != null) {
            printAndSaveLog("第一次活动达标勋 dayData=" + GsonUtil.toJson(calorieDayDataCaluteActiveCalories));
            UserMedalInfo userMedalInfo = new UserMedalInfo();
            Calendar calendar = Calendar.getInstance(Locale.getDefault());
            calendar.setTimeInMillis(calorieDayDataCaluteActiveCalories.getTimestamp());
            String str = DateUtil.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
            printAndSaveLog("获取了第一次活动达标勋章 Date=" + str);
            userMedalInfo.setDate(str);
            userMedalInfo.setMedalId(UserModelEnum.ACTIVE_TARGET.getMedalId());
            userMedalInfo.setShowToUser(false);
            userMedalInfo.setUserId(RunTimeUtil.getInstance().getUserId());
            GreenDaoUtil.addUserMedalInfo(userMedalInfo);
            if (!userMedalInfo.isShowToUser()) {
                EventBusHelper.post(609);
            }
        }
        if (!queryUserMedalInfo(UserModelEnum.ACTIVE_7.getMedalId()) && (caloriesHasToTargetInfo2 = getCaloriesHasToTargetInfo(UserModelEnum.ACTIVE_7.getTargetValue())) != null && caloriesHasToTargetInfo2.first != null) {
            printAndSaveLog("连续7天活动达标 dayDataPair=" + GsonUtil.toJson(caloriesHasToTargetInfo2));
            UserMedalInfo userMedalInfo2 = new UserMedalInfo();
            Calendar calendar2 = Calendar.getInstance(Locale.getDefault());
            calendar2.setTimeInMillis(((CalorieDayData) caloriesHasToTargetInfo2.first).getTimestamp());
            String str2 = DateUtil.format(calendar2.getTime(), "yyyy-MM-dd HH:mm:ss");
            printAndSaveLog("获取了连续7天活动达标勋章 Date=" + str2);
            userMedalInfo2.setDate(str2);
            userMedalInfo2.setMedalId(UserModelEnum.ACTIVE_7.getMedalId());
            userMedalInfo2.setShowToUser(false);
            userMedalInfo2.setUserId(RunTimeUtil.getInstance().getUserId());
            GreenDaoUtil.addUserMedalInfo(userMedalInfo2);
            if (!userMedalInfo2.isShowToUser()) {
                EventBusHelper.post(610);
            }
        }
        if (queryUserMedalInfo(UserModelEnum.ACTIVE_21.getMedalId()) || (caloriesHasToTargetInfo = getCaloriesHasToTargetInfo(UserModelEnum.ACTIVE_21.getTargetValue())) == null || caloriesHasToTargetInfo.first == null) {
            return;
        }
        printAndSaveLog("连续21天活动达标dayDataPair=" + GsonUtil.toJson(caloriesHasToTargetInfo));
        UserMedalInfo userMedalInfo3 = new UserMedalInfo();
        Calendar calendar3 = Calendar.getInstance(Locale.getDefault());
        calendar3.setTimeInMillis(((CalorieDayData) caloriesHasToTargetInfo.first).getTimestamp());
        String str3 = DateUtil.format(calendar3.getTime(), "yyyy-MM-dd HH:mm:ss");
        printAndSaveLog("获取了连续21天活动达标勋章 Date=" + str3);
        userMedalInfo3.setDate(str3);
        userMedalInfo3.setMedalId(UserModelEnum.ACTIVE_21.getMedalId());
        userMedalInfo3.setShowToUser(false);
        userMedalInfo3.setUserId(RunTimeUtil.getInstance().getUserId());
        GreenDaoUtil.addUserMedalInfo(userMedalInfo3);
        if (userMedalInfo3.isShowToUser()) {
            return;
        }
        EventBusHelper.post(611);
    }

    private static boolean queryUserMedalInfo(int i) {
        return getDaoSession().getUserMedalInfoDao().queryBuilder().where(UserMedalInfoDao.Properties.UserId.eq(Long.valueOf(RunTimeUtil.getInstance().getUserId())), UserMedalInfoDao.Properties.MedalId.eq(Integer.valueOf(i))).count() > 0;
    }

    public static void caluteHealthSport(int i) {
        SportHealth firstSportTarget;
        SportHealth firstSportTarget2;
        SportHealth firstSportTarget3;
        SportHealth firstSportTarget4;
        SportHealth firstSportTarget5;
        SportHealth firstSportTarget6;
        if (i == 4) {
            if (queryUserMedalInfo(UserModelEnum.FIRST_WALK.getMedalId()) || (firstSportTarget = getFirstSportTarget(i, UserModelEnum.FIRST_WALK.getTargetValue())) == null) {
                return;
            }
            printAndSaveLog("首次徒步达标sportHealth=" + GsonUtil.toJson(firstSportTarget));
            UserMedalInfo userMedalInfo = new UserMedalInfo();
            userMedalInfo.setUserId(RunTimeUtil.getInstance().getUserId());
            userMedalInfo.setMedalId(UserModelEnum.FIRST_WALK.getMedalId());
            userMedalInfo.setDate(firstSportTarget.getDateTime());
            Calendar.getInstance(Locale.CHINA).setTimeInMillis(firstSportTarget.getTimestamp());
            userMedalInfo.setShowToUser(false);
            printAndSaveLog("获取了首次徒步达标勋章 Date=" + firstSportTarget.getDateTime());
            GreenDaoUtil.addUserMedalInfo(userMedalInfo);
            if (userMedalInfo.isShowToUser()) {
                return;
            }
            EventBusHelper.post(Constants.EventConstants.EVENT_FIRST_ONFOOT);
            return;
        }
        if (i == 52) {
            if (queryUserMedalInfo(UserModelEnum.FIRST_WALK_OUTSIDE.getMedalId()) || (firstSportTarget2 = getFirstSportTarget(i, UserModelEnum.FIRST_WALK_OUTSIDE.getTargetValue())) == null) {
                return;
            }
            printAndSaveLog("首次户外走路达标sportHealth=" + GsonUtil.toJson(firstSportTarget2));
            UserMedalInfo userMedalInfo2 = new UserMedalInfo();
            userMedalInfo2.setUserId(RunTimeUtil.getInstance().getUserId());
            userMedalInfo2.setMedalId(UserModelEnum.FIRST_WALK_OUTSIDE.getMedalId());
            userMedalInfo2.setDate(firstSportTarget2.getDateTime());
            Calendar.getInstance(Locale.CHINA).setTimeInMillis(firstSportTarget2.getTimestamp());
            userMedalInfo2.setShowToUser(false);
            printAndSaveLog("获取了首次户外走路达标勋章 Date=" + firstSportTarget2.getDateTime());
            GreenDaoUtil.addUserMedalInfo(userMedalInfo2);
            if (userMedalInfo2.isShowToUser()) {
                return;
            }
            EventBusHelper.post(614);
            return;
        }
        if (i != 53) {
            switch (i) {
                case 48:
                    if (!queryUserMedalInfo(UserModelEnum.FIRST_RUN_OUTSIDE.getMedalId()) && (firstSportTarget4 = getFirstSportTarget(i, UserModelEnum.FIRST_RUN_OUTSIDE.getTargetValue())) != null) {
                        printAndSaveLog("首次户外跑步达标sportHealth=" + GsonUtil.toJson(firstSportTarget4));
                        UserMedalInfo userMedalInfo3 = new UserMedalInfo();
                        userMedalInfo3.setUserId(RunTimeUtil.getInstance().getUserId());
                        userMedalInfo3.setMedalId(UserModelEnum.FIRST_RUN_OUTSIDE.getMedalId());
                        printAndSaveLog("获取了首次户外跑步达标勋章 Date=" + firstSportTarget4.getDateTime());
                        userMedalInfo3.setDate(firstSportTarget4.getDateTime());
                        Calendar.getInstance(Locale.CHINA).setTimeInMillis(firstSportTarget4.getTimestamp());
                        userMedalInfo3.setShowToUser(false);
                        GreenDaoUtil.addUserMedalInfo(userMedalInfo3);
                        if (!userMedalInfo3.isShowToUser()) {
                            EventBusHelper.post(612);
                        }
                        break;
                    }
                    break;
                case 49:
                    if (!queryUserMedalInfo(UserModelEnum.FIRST_RUN_HOME.getMedalId()) && (firstSportTarget5 = getFirstSportTarget(i, UserModelEnum.FIRST_RUN_HOME.getTargetValue())) != null) {
                        printAndSaveLog("首次室内跑步达标sportHealth=" + GsonUtil.toJson(firstSportTarget5));
                        UserMedalInfo userMedalInfo4 = new UserMedalInfo();
                        userMedalInfo4.setUserId(RunTimeUtil.getInstance().getUserId());
                        userMedalInfo4.setMedalId(UserModelEnum.FIRST_RUN_HOME.getMedalId());
                        printAndSaveLog("获取了首次室内跑步达标勋章 Date=" + firstSportTarget5.getDateTime());
                        Calendar.getInstance(Locale.CHINA).setTimeInMillis(firstSportTarget5.getTimestamp());
                        userMedalInfo4.setShowToUser(false);
                        userMedalInfo4.setDate(firstSportTarget5.getDateTime());
                        GreenDaoUtil.addUserMedalInfo(userMedalInfo4);
                        if (!userMedalInfo4.isShowToUser()) {
                            EventBusHelper.post(613);
                        }
                        break;
                    }
                    break;
                case 50:
                    if (!queryUserMedalInfo(UserModelEnum.FIRST_DRIVE_OUTSIDE.getMedalId()) && (firstSportTarget6 = getFirstSportTarget(i, UserModelEnum.FIRST_DRIVE_OUTSIDE.getTargetValue())) != null) {
                        printAndSaveLog("首次骑行达标sportHealth=" + GsonUtil.toJson(firstSportTarget6));
                        UserMedalInfo userMedalInfo5 = new UserMedalInfo();
                        userMedalInfo5.setUserId(RunTimeUtil.getInstance().getUserId());
                        userMedalInfo5.setMedalId(UserModelEnum.FIRST_DRIVE_OUTSIDE.getMedalId());
                        userMedalInfo5.setDate(firstSportTarget6.getDateTime());
                        Calendar.getInstance(Locale.CHINA).setTimeInMillis(firstSportTarget6.getTimestamp());
                        userMedalInfo5.setShowToUser(false);
                        printAndSaveLog("获取了首次骑行达标勋章 Date=" + firstSportTarget6.getDateTime());
                        GreenDaoUtil.addUserMedalInfo(userMedalInfo5);
                        if (!userMedalInfo5.isShowToUser()) {
                            EventBusHelper.post(Constants.EventConstants.EVENT_DRIVING_OUT);
                        }
                        break;
                    }
                    break;
            }
            return;
        }
        if (queryUserMedalInfo(UserModelEnum.FIRST_WALK_HOME.getMedalId()) || (firstSportTarget3 = getFirstSportTarget(i, UserModelEnum.FIRST_WALK_HOME.getTargetValue())) == null) {
            return;
        }
        printAndSaveLog("首次室内走路达标sportHealth=" + GsonUtil.toJson(firstSportTarget3));
        UserMedalInfo userMedalInfo6 = new UserMedalInfo();
        userMedalInfo6.setUserId(RunTimeUtil.getInstance().getUserId());
        userMedalInfo6.setMedalId(UserModelEnum.FIRST_WALK_HOME.getMedalId());
        userMedalInfo6.setDate(firstSportTarget3.getDateTime());
        Calendar.getInstance(Locale.CHINA).setTimeInMillis(firstSportTarget3.getTimestamp());
        userMedalInfo6.setShowToUser(false);
        printAndSaveLog("获取了首次室内走路达标勋章 Date=" + firstSportTarget3.getDateTime());
        GreenDaoUtil.addUserMedalInfo(userMedalInfo6);
        if (userMedalInfo6.isShowToUser()) {
            return;
        }
        EventBusHelper.post(615);
    }

    public static StepDayData stepHasToTarget() {
        UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(RunTimeUtil.getInstance().getUserId());
        int step = userTargetNewQueryUserLatestTarget != null ? userTargetNewQueryUserLatestTarget.getStep() : 10000;
        List<StepDayData> list = getDaoSession().getStepDayDataDao().queryBuilder().where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(RunTimeUtil.getInstance().getUserId())), new WhereCondition[0]).list();
        if (list == null || list.size() == 0) {
            return null;
        }
        Collections.sort(list);
        for (StepDayData stepDayData : list) {
            if (stepDayData != null) {
                if (stepDayData.getTargetSteps() > 0) {
                    step = stepDayData.getTargetSteps();
                }
                if (stepDayData.getNumSteps() >= step) {
                    return stepDayData;
                }
            }
        }
        return null;
    }

    public static Pair<StepDayData, Integer> getStepHasToTargetInfo(int i) {
        List<StepDayData> list = getDaoSession().getStepDayDataDao().queryBuilder().where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(RunTimeUtil.getInstance().getUserId())), new WhereCondition[0]).orderDesc(StepDayDataDao.Properties.Id).list();
        StepDayData stepDayData = null;
        if (list == null || list.size() == 0) {
            return null;
        }
        Iterator<StepDayData> it = list.iterator();
        UserTargetNew userTargetNewQueryUserLatestTarget = GreenDaoUtil.queryUserLatestTarget(RunTimeUtil.getInstance().getUserId());
        int step = userTargetNewQueryUserLatestTarget != null ? userTargetNewQueryUserLatestTarget.getStep() : 10000;
        while (it.hasNext()) {
            StepDayData next = it.next();
            if (next == null) {
                it.remove();
            }
            if (next.getTargetSteps() > 0) {
                step = next.getTargetSteps();
            }
            if (step > next.getNumSteps()) {
                it.remove();
            }
        }
        if (list.size() == 0) {
            return null;
        }
        Collections.sort(list);
        int size = list.size();
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        boolean z = true;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= size) {
                break;
            }
            StepDayData stepDayData2 = list.get(i2);
            if (!TextUtils.isEmpty(stepDayData2.getDate())) {
                if (z) {
                    try {
                        calendar.setTime(DateUtil.string2Date(stepDayData2.getDate(), DateUtil.DATE_FORMAT_YMD));
                        i3++;
                        z = false;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        z = true;
                        i3 = 0;
                    }
                } else {
                    calendar.add(5, 1);
                    if (TextUtils.equals(stepDayData2.getDate(), DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD))) {
                        i3++;
                        if (i3 >= i) {
                            stepDayData = stepDayData2;
                            break;
                        }
                    } else {
                        z = true;
                        i3 = 0;
                    }
                }
            }
            i2++;
        }
        return new Pair<>(stepDayData, Integer.valueOf(i3));
    }

    public static CalorieDayData caluteActiveCalories() {
        List<CalorieDayData> list = getDaoSession().getCalorieDayDataDao().queryBuilder().where(CalorieDayDataDao.Properties.UserId.eq(Long.valueOf(RunTimeUtil.getInstance().getUserId())), new WhereCondition[0]).list();
        if (list == null || list.size() == 0) {
            return null;
        }
        Iterator<CalorieDayData> it = list.iterator();
        while (it.hasNext()) {
            CalorieDayData next = it.next();
            if (next == null || 500 > next.getActivityCalorie()) {
                it.remove();
            }
        }
        if (list.size() == 0) {
            return null;
        }
        Collections.sort(list, new Comparator() { // from class: com.ido.life.util.-$$Lambda$MedalCaluteUtil$GKh2iVCv7g2sGRs20ALWOgfZBZQ
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return MedalCaluteUtil.lambda$caluteActiveCalories$0((CalorieDayData) obj, (CalorieDayData) obj2);
            }
        });
        return list.get(0);
    }

    static /* synthetic */ int lambda$caluteActiveCalories$0(CalorieDayData calorieDayData, CalorieDayData calorieDayData2) {
        try {
            return DateUtil.string2Date(calorieDayData.getDate(), DateUtil.DATE_FORMAT_YMD).compareTo(DateUtil.string2Date(calorieDayData2.getDate(), DateUtil.DATE_FORMAT_YMD));
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static Pair<CalorieDayData, Integer> getCaloriesHasToTargetInfo(int i) {
        List<CalorieDayData> list = getDaoSession().getCalorieDayDataDao().queryBuilder().where(CalorieDayDataDao.Properties.UserId.eq(Long.valueOf(RunTimeUtil.getInstance().getUserId())), new WhereCondition[0]).list();
        CalorieDayData calorieDayData = null;
        if (list == null || list.size() == 0) {
            return null;
        }
        Iterator<CalorieDayData> it = list.iterator();
        while (it.hasNext()) {
            CalorieDayData next = it.next();
            if (next == null || 500 > next.getActivityCalorie()) {
                it.remove();
            }
        }
        if (list.size() == 0) {
            return null;
        }
        Collections.sort(list, new Comparator() { // from class: com.ido.life.util.-$$Lambda$MedalCaluteUtil$Rc5dDz0c318oA5HdCDcoLVHxpVI
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return MedalCaluteUtil.lambda$getCaloriesHasToTargetInfo$1((CalorieDayData) obj, (CalorieDayData) obj2);
            }
        });
        int size = list.size();
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        int i2 = 0;
        int i3 = 0;
        boolean z = true;
        while (true) {
            if (i2 >= size) {
                break;
            }
            CalorieDayData calorieDayData2 = list.get(i2);
            if (!TextUtils.isEmpty(calorieDayData2.getDate())) {
                if (z) {
                    try {
                        calendar.setTime(DateUtil.string2Date(calorieDayData2.getDate(), DateUtil.DATE_FORMAT_YMD));
                        i3++;
                        z = false;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        i3 = 0;
                        z = true;
                    }
                } else {
                    calendar.add(5, 1);
                    if (TextUtils.equals(DateUtil.format(calendar.getTime(), DateUtil.DATE_FORMAT_YMD), calorieDayData2.getDate())) {
                        i3++;
                        if (i3 >= i) {
                            CommonLogUtil.d(TAG, "连续${dayCount}天活动卡路里达标,获得勋章");
                            calorieDayData = calorieDayData2;
                            break;
                        }
                    } else {
                        i3 = 0;
                        z = true;
                    }
                }
            }
            i2++;
        }
        return new Pair<>(calorieDayData, Integer.valueOf(i3));
    }

    static /* synthetic */ int lambda$getCaloriesHasToTargetInfo$1(CalorieDayData calorieDayData, CalorieDayData calorieDayData2) {
        try {
            return DateUtil.string2Date(calorieDayData.getDate(), DateUtil.DATE_FORMAT_YMD).compareTo(DateUtil.string2Date(calorieDayData2.getDate(), DateUtil.DATE_FORMAT_YMD));
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static SportHealth getFirstSportTarget(int i, int i2) {
        List<SportHealth> list = getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.Type.eq(Integer.valueOf(i)), SportHealthDao.Properties.Distance.ge(Integer.valueOf(i2 * 1000)), SportHealthDao.Properties.UserId.eq(Long.valueOf(RunTimeUtil.getInstance().getUserId()))).orderAsc(SportHealthDao.Properties.DateTime).list();
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    private static DaoSession getDaoSession() {
        return GreenDaoUtil.getDaoSession();
    }

    private static void printAndSaveLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.d(TAG, str);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLogPath(), TAG, str);
    }
}