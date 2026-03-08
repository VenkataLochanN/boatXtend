package com.ido.life.data;

import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.utils.TimeUtil;
import com.ido.life.bean.LatLngBean;
import com.ido.life.database.GreenDaoManager;
import com.ido.life.database.model.SportGps;
import com.ido.life.database.model.SportGpsData;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.SportHealthDao;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.MedalCaluteUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.List;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes2.dex */
public class SportHealthDataManager {
    private static final String TAG = "SportHealthDataManager";

    public static void addDataFromApp(SportHealth sportHealth) {
        sportHealth.setTimestamp(DateUtil.parse(sportHealth.getEndTime(), "yyyy-MM-dd HH:mm:ss").getTime());
        GreenDaoUtil.saveActivityData(sportHealth);
        MedalCaluteUtil.caluteHealthSport(sportHealth.getType());
    }

    public static void addSportGpsData(List<LatLngBean> list, long j, long j2) {
        SportGpsData sportGpsData = GreenDaoUtil.getSportGpsData(j2, j);
        if (sportGpsData != null) {
            SportGps gpsData = sportGpsData.getGpsData();
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                JSONArray jSONArray2 = new JSONArray();
                LatLngBean latLngBean = list.get(i);
                jSONArray2.put(String.valueOf(latLngBean.latitude));
                jSONArray2.put(String.valueOf(latLngBean.longitude));
                jSONArray2.put(String.valueOf(TimeUtil.convTimeYmdhmsToLong(latLngBean.currentTimeMillis)));
                jSONArray.put(jSONArray2);
            }
            String items = gpsData.getItems();
            CommonLogUtil.d(TAG, "addSportGpsData: " + items + AppInfo.DELIM + items.contains("]"));
            gpsData.setItems(gpsData.getItems().substring(0, items.length() - 1) + AppInfo.DELIM + jSONArray.toString().substring(1));
            sportGpsData.setGpsData(gpsData);
        } else {
            sportGpsData = new SportGpsData();
            JSONArray jSONArray3 = new JSONArray();
            SportGps sportGps = new SportGps();
            for (int i2 = 0; i2 < list.size(); i2++) {
                JSONArray jSONArray4 = new JSONArray();
                LatLngBean latLngBean2 = list.get(i2);
                jSONArray4.put(String.valueOf(latLngBean2.latitude));
                jSONArray4.put(String.valueOf(latLngBean2.longitude));
                jSONArray4.put(String.valueOf(DateUtil.getLongFromDateStr(latLngBean2.currentTimeMillis)));
                jSONArray3.put(jSONArray4);
            }
            sportGps.setItems(jSONArray3.toString());
            sportGpsData.setUserId(j2);
            sportGpsData.setTimeMillis(j);
            sportGpsData.setGpsData(sportGps);
        }
        GreenDaoUtil.saveSportGpsData(sportGpsData);
    }

    public static int getAvgWeekActivityTime() {
        int year = com.ido.ble.common.TimeUtil.getYear();
        int month = com.ido.ble.common.TimeUtil.getMonth();
        String str = String.format("%d-%02d", Integer.valueOf(year), Integer.valueOf(month == 1 ? 12 : month - 1));
        List list = GreenDaoManager.getInstance().getDaoSession().queryBuilder(SportHealth.class).where(SportHealthDao.Properties.UserId.eq(Long.valueOf(RunTimeUtil.getInstance().getUserId())), SportHealthDao.Properties.DateTime.like("%" + str + "%")).list();
        if (list == null || list.size() <= 0) {
            return 0;
        }
        int totalSeconds = 0;
        for (int i = 0; i < list.size(); i++) {
            SportHealth sportHealth = (SportHealth) list.get(i);
            if (sportHealth.getType() == 48 || sportHealth.getType() == 50) {
                totalSeconds += sportHealth.getTotalSeconds();
            }
        }
        return totalSeconds / 60;
    }

    public static SportHealth getLastSportVo() {
        return (SportHealth) GreenDaoManager.getInstance().getDaoSession().queryBuilder(SportHealth.class).where(SportHealthDao.Properties.UserId.eq(Long.valueOf(RunTimeUtil.getInstance().getUserId())), SportHealthDao.Properties.Vo2max.gt(0)).orderDesc(SportHealthDao.Properties.DateTime).limit(1).unique();
    }

    public static int getSportRecoverTime() {
        SportHealth sportHealthRecordTime = GreenDaoUtil.getSportHealthRecordTime();
        if (sportHealthRecordTime == null) {
            return 0;
        }
        int recoverTime = sportHealthRecordTime.getRecoverTime();
        TimeUtil.now();
        sportHealthRecordTime.getTimestamp();
        long j = recoverTime;
        if ((((TimeUtil.now() - sportHealthRecordTime.getTimestamp()) / 1000) / 60) / 60 > j) {
            return 0;
        }
        return (int) (j - ((((TimeUtil.now() - sportHealthRecordTime.getTimestamp()) / 1000) / 60) / 60));
    }
}