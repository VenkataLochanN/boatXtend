package com.ido.life.database.upgrade;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ido.common.utils.GsonUtil;
import com.ido.life.database.model.DataDownLoadStateDao;
import com.ido.life.database.model.LifeCycleItemBeanDao;
import com.ido.life.database.model.MenstruationConfigDao;
import com.ido.life.database.model.ServerMenstrualItem;
import com.ido.life.util.DateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class UpgradeDataBaseTo3 extends BaseUpgrateDataBase {
    private static final String CREATE_LIFE_CYCLE = "CREATE TABLE IF NOT EXISTS \"LifeCycle\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"MONTH\" TEXT,\"MENSES_CYCLE\" INTEGER NOT NULL ,\"MENSES_DAYS\" INTEGER NOT NULL ,\"SOURCE_MAC\" TEXT,\"DEVICE_NAME\" TEXT,\"ITEM_LIST\" TEXT,\"TIME_STAMP\" INTEGER NOT NULL ,\"MENSES_START_DAY\" INTEGER NOT NULL ,\"OVULATION_DAY\" INTEGER NOT NULL ,\"PREGNANCY_DAY_BEFORE_REMIND\" INTEGER NOT NULL ,\"REMINDER_TIME\" TEXT,\"UPLOAD\" INTEGER NOT NULL ,\"NEED_SYNC_TO_DEVICE\" INTEGER NOT NULL );";
    private static final String CREATE_MENS_CONFIG_TABLE = "CREATE TABLE IF NOT EXISTS \"MENSTRUATION_CONFIG\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"MENS_LENGTH\" INTEGER NOT NULL ,\"MENS_CYCLE\" INTEGER NOT NULL ,\"UPDATE_TIME_STAMP\" INTEGER NOT NULL ,\"UPLOAD_SUCCESS\" INTEGER NOT NULL );";
    private static final String DATA_DOWNLOAD_STATE_TABLE = "CREATE TABLE IF NOT EXISTS \"DATA_DOWN_LOAD_STATE\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"USER_ID\" INTEGER NOT NULL ,\"DATA_NAME\" TEXT,\"DOWNLOAD_STATE\" INTEGER NOT NULL ,\"TASK_ID\" INTEGER NOT NULL ,\"PARAMS_MAP\" TEXT,\"URL\" TEXT,\"DOWNLOAD_TIME_STAMP\" INTEGER NOT NULL );";

    @Override // com.ido.life.database.DatabaseUpgradeUtil.UpgrateDataBase
    public boolean processUpgrade(StandardDatabaseWraper standardDatabaseWraper) {
        return processLifeCycle(standardDatabaseWraper) && processActiveTimeData(standardDatabaseWraper) && processUserTargetData(standardDatabaseWraper) && processWalkData(standardDatabaseWraper) && processHeartRate(standardDatabaseWraper) && processDownloadStateTable(standardDatabaseWraper) && processDataPullConfig(standardDatabaseWraper) && processVolumeData(standardDatabaseWraper);
    }

    private boolean processVolumeData(StandardDatabaseWraper standardDatabaseWraper) {
        return standardDatabaseWraper.execSql("CREATE TABLE IF NOT EXISTS \"HEALTH_VOLUME_DATA\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ,\"TIMESTAMP\" INTEGER NOT NULL ,\"DATE\" TEXT,\"USER_ID\" INTEGER NOT NULL ,\"AVG_VALUE\" INTEGER NOT NULL ,\"MAX_VALUE\" INTEGER NOT NULL ,\"MIN_VALUE\" INTEGER NOT NULL ,\"LATEST_VALUE\" INTEGER NOT NULL ,\"INTERVAL\" INTEGER NOT NULL ,\"SOURCE_MAC\" TEXT,\"DEVICE_NAME\" TEXT,\"ITEMS\" TEXT,\"TOTAL_SECONDS\" INTEGER NOT NULL ,\"SUPER_HIGH_LEVEL_SECONDS\" INTEGER NOT NULL ,\"HIGH_LEVEL_SECONDS\" INTEGER NOT NULL ,\"NORMAL_LEVEL_SECONDS\" INTEGER NOT NULL ,\"LOW_LEVEL_SECONDS\" INTEGER NOT NULL ,\"HAS_UPDATE\" INTEGER NOT NULL );");
    }

    private boolean processLifeCycle(StandardDatabaseWraper standardDatabaseWraper) {
        String str;
        HashMap map;
        Iterator<ContentValues> it;
        long jLongValue;
        String asString;
        int iIntValue;
        int iIntValue2;
        String asString2;
        String asString3;
        String asString4;
        long jLongValue2;
        int i;
        int i2;
        ServerMenstrualItem serverMenstrualItem;
        String str2 = LifeCycleItemBeanDao.TABLENAME;
        List<ContentValues> tableValue = getTableValue(LifeCycleItemBeanDao.TABLENAME, standardDatabaseWraper);
        standardDatabaseWraper.dropTable(LifeCycleItemBeanDao.TABLENAME, true);
        standardDatabaseWraper.execSql(CREATE_LIFE_CYCLE);
        standardDatabaseWraper.execSql(CREATE_MENS_CONFIG_TABLE);
        if (tableValue == null || tableValue.size() <= 0) {
            return true;
        }
        HashMap map2 = new HashMap();
        Calendar calendar = Calendar.getInstance();
        Iterator<ContentValues> it2 = tableValue.iterator();
        while (it2.hasNext()) {
            ContentValues next = it2.next();
            try {
                jLongValue = next.getAsLong("USER_ID").longValue();
                asString = next.getAsString("LATEST_DATE");
                iIntValue = next.getAsInteger("MENSES_CYCLE").intValue();
                iIntValue2 = next.getAsInteger("MENSES_DAYS").intValue();
                it = it2;
                try {
                    asString2 = next.getAsString("SOURCE_MAC");
                    str = str2;
                    try {
                        asString3 = next.getAsString("DEVICE_NAME");
                        asString4 = next.getAsString("ITEMS");
                        jLongValue2 = next.getAsLong("TIMESTAMP").longValue();
                    } catch (Exception e2) {
                        e = e2;
                        map = map2;
                        e.printStackTrace();
                        map2 = map;
                        it2 = it;
                        str2 = str;
                    }
                } catch (Exception e3) {
                    e = e3;
                    str = str2;
                }
            } catch (Exception e4) {
                e = e4;
                str = str2;
                map = map2;
                it = it2;
            }
            if (!TextUtils.isEmpty(asString)) {
                ContentValues contentValues = (ContentValues) map2.get(Long.valueOf(jLongValue));
                if (contentValues == null) {
                    contentValues = new ContentValues();
                } else if (contentValues.getAsLong("TIME_STAMP").longValue() > jLongValue2) {
                }
                HashMap map3 = map2;
                try {
                    calendar.setTime(DateUtil.string2Date(asString, DateUtil.DATE_FORMAT_YMD));
                    i = calendar.get(5);
                    i2 = calendar.get(2);
                    serverMenstrualItem = (ServerMenstrualItem) GsonUtil.fromJson(asString4, ServerMenstrualItem.class);
                } catch (Exception e5) {
                    e = e5;
                    map = map3;
                }
                if (serverMenstrualItem == null) {
                    it2 = it;
                    str2 = str;
                    map2 = map3;
                } else {
                    contentValues.put("USER_ID", Long.valueOf(jLongValue));
                    contentValues.put("MONTH", DateUtil.format(calendar, DateUtil.DATE_FORMAT_YM_3));
                    contentValues.put("MENSES_CYCLE", Integer.valueOf(iIntValue));
                    contentValues.put("MENSES_DAYS", Integer.valueOf(iIntValue2));
                    contentValues.put("SOURCE_MAC", asString2);
                    contentValues.put("DEVICE_NAME", asString3);
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    for (int i3 = 0; i3 < iIntValue2 && calendar.get(2) == i2; i3++) {
                        arrayList2.add(Integer.valueOf(calendar.get(5)));
                        calendar.add(5, 1);
                    }
                    arrayList.add(arrayList2);
                    contentValues.put("ITEM_LIST", GsonUtil.toJson(arrayList));
                    contentValues.put("TIME_STAMP", Long.valueOf(jLongValue2));
                    contentValues.put("MENSES_START_DAY", Integer.valueOf(i));
                    contentValues.put("OVULATION_DAY", Integer.valueOf(serverMenstrualItem.getMenstrualRemindBeforeDays()));
                    contentValues.put("PREGNANCY_DAY_BEFORE_REMIND", Integer.valueOf(serverMenstrualItem.getMenstrualRemindBeforeDays()));
                    contentValues.put("REMINDER_TIME", String.format("%02d:%02d", Integer.valueOf(serverMenstrualItem.getHour()), Integer.valueOf(serverMenstrualItem.getMinute())));
                    contentValues.put("UPLOAD", (Boolean) false);
                    contentValues.put("NEED_SYNC_TO_DEVICE", (Boolean) true);
                    map = map3;
                    try {
                        map.put(Long.valueOf(jLongValue), contentValues);
                    } catch (Exception e6) {
                        e = e6;
                        e.printStackTrace();
                    }
                    map2 = map;
                    it2 = it;
                    str2 = str;
                }
            }
            it2 = it;
            str2 = str;
        }
        String str3 = str2;
        HashMap map4 = map2;
        if (!map4.isEmpty()) {
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            for (Long l : map4.keySet()) {
                ContentValues contentValues2 = (ContentValues) map4.get(l);
                calendar.setTime(DateUtil.string2Date(contentValues2.getAsString("MONTH"), DateUtil.DATE_FORMAT_YM_3));
                calendar.set(5, contentValues2.getAsInteger("MENSES_START_DAY").intValue());
                ContentValues contentValues3 = new ContentValues();
                contentValues3.put("USER_ID", l);
                contentValues3.put("MENS_LENGTH", contentValues2.getAsInteger("MENSES_DAYS"));
                contentValues3.put("MENS_CYCLE", contentValues2.getAsInteger("MENSES_CYCLE"));
                contentValues3.put("UPDATE_TIME_STAMP", Long.valueOf(calendar.getTimeInMillis()));
                contentValues3.put("UPLOAD_SUCCESS", (Boolean) false);
                arrayList4.add(contentValues3);
                arrayList3.add(contentValues2);
            }
            standardDatabaseWraper.insert(MenstruationConfigDao.TABLENAME, (String) null, arrayList4);
            standardDatabaseWraper.insert(str3, (String) null, arrayList3);
        }
        return true;
    }

    private boolean processDownloadStateTable(StandardDatabaseWraper standardDatabaseWraper) {
        Cursor cursor;
        StandardDatabaseWraper standardDatabaseWraper2;
        boolean z;
        ArrayList arrayList;
        Cursor cursor2;
        int i;
        String str;
        int i2;
        String str2;
        int i3;
        int i4;
        int i5;
        long j;
        String string;
        List list;
        List list2;
        String str3;
        String str4;
        String str5;
        String str6;
        UpgradeDataBaseTo3 upgradeDataBaseTo3 = this;
        Cursor cursorQuery = standardDatabaseWraper.query(DataDownLoadStateDao.TABLENAME);
        if (cursorQuery != null) {
            if (cursorQuery.getCount() > 0) {
                arrayList = new ArrayList();
                String str7 = "USER_ID";
                int columnIndex = cursorQuery.getColumnIndex("USER_ID");
                String str8 = "DATA_NAME";
                int columnIndex2 = cursorQuery.getColumnIndex("DATA_NAME");
                int columnIndex3 = cursorQuery.getColumnIndex("PARAMS_MAP");
                int columnIndex4 = cursorQuery.getColumnIndex("DOWNLOAD_STATE");
                int columnIndex5 = cursorQuery.getColumnIndex("DOWNLOAD_TIME_STAMP");
                while (cursorQuery.moveToNext()) {
                    try {
                        j = cursorQuery.getLong(columnIndex);
                        string = cursorQuery.getString(columnIndex2);
                        i2 = columnIndex;
                        try {
                            i3 = columnIndex2;
                        } catch (Exception unused) {
                            cursor2 = cursorQuery;
                            i = columnIndex5;
                            str = str7;
                            str2 = str8;
                            i3 = columnIndex2;
                            i4 = columnIndex3;
                            i5 = columnIndex4;
                            upgradeDataBaseTo3 = this;
                            columnIndex = i2;
                            columnIndex2 = i3;
                            columnIndex3 = i4;
                            columnIndex4 = i5;
                            columnIndex5 = i;
                            cursorQuery = cursor2;
                            str7 = str;
                            str8 = str2;
                        }
                    } catch (Exception unused2) {
                        cursor2 = cursorQuery;
                        i = columnIndex5;
                        str = str7;
                        i2 = columnIndex;
                    }
                    try {
                        i4 = columnIndex3;
                        try {
                            list = (List) new Gson().fromJson(cursorQuery.getString(columnIndex3), new TypeToken<List<Map<String, Long>>>() { // from class: com.ido.life.database.upgrade.UpgradeDataBaseTo3.1
                            }.getType());
                        } catch (Exception unused3) {
                            cursor2 = cursorQuery;
                            i = columnIndex5;
                            str = str7;
                            str2 = str8;
                        }
                    } catch (Exception unused4) {
                        cursor2 = cursorQuery;
                        i = columnIndex5;
                        str = str7;
                        str2 = str8;
                        i4 = columnIndex3;
                    }
                    if (list != null && list.size() != 0) {
                        i5 = columnIndex4;
                        try {
                            list2 = (List) new Gson().fromJson(cursorQuery.getString(columnIndex4), new TypeToken<List<Integer>>() { // from class: com.ido.life.database.upgrade.UpgradeDataBaseTo3.2
                            }.getType());
                        } catch (Exception unused5) {
                            cursor2 = cursorQuery;
                            i = columnIndex5;
                        }
                        if (list2 == null || list2.size() != list.size() + 1) {
                            cursor2 = cursorQuery;
                            i = columnIndex5;
                        } else {
                            long j2 = cursorQuery.getLong(columnIndex5);
                            int size = list2.size();
                            String str9 = DateUtil.DATE_FORMAT_YMD;
                            i = columnIndex5;
                            try {
                                if (TextUtils.equals(string, "SportHealth")) {
                                    str9 = "yyyy-MM-dd HH:mm:ss";
                                }
                                int i6 = 0;
                                cursor2 = cursorQuery;
                                try {
                                    String taskUrlByDataName = upgradeDataBaseTo3.getTaskUrlByDataName(string, false);
                                    while (i6 < size) {
                                        int i7 = size;
                                        try {
                                            ContentValues contentValues = new ContentValues();
                                            contentValues.put(str7, Long.valueOf(j));
                                            contentValues.put(str8, string);
                                            contentValues.put("DOWNLOAD_STATE", (Integer) list2.get(i6));
                                            contentValues.put("DOWNLOAD_TIME_STAMP", Long.valueOf(j2));
                                            str5 = str7;
                                            if (i6 == 0) {
                                                str6 = str8;
                                                try {
                                                    contentValues.put("TASK_ID", (Integer) 0);
                                                    contentValues.put("URL", upgradeDataBaseTo3.getTaskUrlByDataName(string, true));
                                                    str3 = taskUrlByDataName;
                                                    str4 = string;
                                                    arrayList.add(contentValues);
                                                } catch (Exception unused6) {
                                                    str3 = taskUrlByDataName;
                                                    str4 = string;
                                                }
                                            } else {
                                                str6 = str8;
                                                contentValues.put("TASK_ID", Integer.valueOf(i6));
                                                contentValues.put("URL", taskUrlByDataName);
                                                Map map = (Map) list.get(i6 - 1);
                                                if (map != null && !map.isEmpty()) {
                                                    HashMap map2 = new HashMap();
                                                    for (String str10 : map.keySet()) {
                                                        str3 = taskUrlByDataName;
                                                        str4 = string;
                                                        try {
                                                            map2.put(str10, DateUtil.format(((Long) map.get(str10)).longValue(), str9));
                                                            string = str4;
                                                            taskUrlByDataName = str3;
                                                        } catch (Exception unused7) {
                                                        }
                                                    }
                                                    str3 = taskUrlByDataName;
                                                    str4 = string;
                                                    contentValues.put("PARAMS_MAP", new Gson().toJson(map2));
                                                    arrayList.add(contentValues);
                                                }
                                                str3 = taskUrlByDataName;
                                                str4 = string;
                                            }
                                        } catch (Exception unused8) {
                                            str3 = taskUrlByDataName;
                                            str4 = string;
                                            str5 = str7;
                                            str6 = str8;
                                        }
                                        i6++;
                                        upgradeDataBaseTo3 = this;
                                        size = i7;
                                        str7 = str5;
                                        str8 = str6;
                                        string = str4;
                                        taskUrlByDataName = str3;
                                    }
                                    upgradeDataBaseTo3 = this;
                                    columnIndex = i2;
                                    columnIndex2 = i3;
                                    columnIndex3 = i4;
                                    columnIndex4 = i5;
                                    columnIndex5 = i;
                                    cursorQuery = cursor2;
                                } catch (Exception unused9) {
                                    str = str7;
                                    str2 = str8;
                                    upgradeDataBaseTo3 = this;
                                    columnIndex = i2;
                                    columnIndex2 = i3;
                                    columnIndex3 = i4;
                                    columnIndex4 = i5;
                                    columnIndex5 = i;
                                    cursorQuery = cursor2;
                                    str7 = str;
                                    str8 = str2;
                                }
                            } catch (Exception unused10) {
                                cursor2 = cursorQuery;
                            }
                        }
                        str = str7;
                        str2 = str8;
                        upgradeDataBaseTo3 = this;
                        columnIndex = i2;
                        columnIndex2 = i3;
                        columnIndex3 = i4;
                        columnIndex4 = i5;
                        columnIndex5 = i;
                        cursorQuery = cursor2;
                        str7 = str;
                        str8 = str2;
                    }
                    cursor2 = cursorQuery;
                    i = columnIndex5;
                    str = str7;
                    str2 = str8;
                    i5 = columnIndex4;
                    upgradeDataBaseTo3 = this;
                    columnIndex = i2;
                    columnIndex2 = i3;
                    columnIndex3 = i4;
                    columnIndex4 = i5;
                    columnIndex5 = i;
                    cursorQuery = cursor2;
                    str7 = str;
                    str8 = str2;
                }
                cursor = cursorQuery;
                standardDatabaseWraper2 = standardDatabaseWraper;
                z = true;
            } else {
                cursor = cursorQuery;
                standardDatabaseWraper2 = standardDatabaseWraper;
                z = true;
                arrayList = null;
            }
            standardDatabaseWraper2.dropTable(DataDownLoadStateDao.TABLENAME, z);
            standardDatabaseWraper2.execSql(DATA_DOWNLOAD_STATE_TABLE);
            cursor.close();
            if (arrayList != null && arrayList.size() > 0) {
                standardDatabaseWraper2.insert(DataDownLoadStateDao.TABLENAME, (String) null, arrayList);
            }
            return true;
        }
        standardDatabaseWraper.dropTable(DataDownLoadStateDao.TABLENAME, true);
        return true;
    }

    private boolean processActiveTimeData(StandardDatabaseWraper standardDatabaseWraper) {
        return standardDatabaseWraper.execSql("alter table ACTIVE_TIME_DAY_DATA add column TARGET_EXERCISE_DURATION integer default 0") && standardDatabaseWraper.execSql("alter table ACTIVE_TIME_DAY_DATA add column WEAR_DURATION_LIST text default null") && standardDatabaseWraper.execSql("alter table ACTIVE_TIME_DAY_DATA add column TOTAL_WEAR_DURATION integer default 0");
    }

    private boolean processUserTargetData(StandardDatabaseWraper standardDatabaseWraper) {
        return standardDatabaseWraper.execSql("alter table USER_TARGET_NEW add column SPORT_STEP integer default 100") && standardDatabaseWraper.execSql("alter table USER_TARGET_NEW add column ACTIVITY_TIME integer default 1800") && standardDatabaseWraper.execSql("alter table USER_TARGET_NEW add column WALK integer default 43200");
    }

    protected boolean processWalkData(StandardDatabaseWraper standardDatabaseWraper) {
        return standardDatabaseWraper.execSql("alter table WALK_DAY_DATA add column SEDENTARY_DURATION integer default 0") && standardDatabaseWraper.execSql("alter table WALK_DAY_DATA add column TARGET_WALK_DURATION integer default 0") && standardDatabaseWraper.execSql("alter table WALK_DAY_DATA add column WEAR_DURATION_LIST text default null");
    }

    protected boolean processHeartRate(StandardDatabaseWraper standardDatabaseWraper) {
        return standardDatabaseWraper.execSql("alter table SERVER_HEART_RATE_DAY_DATA add column CHART_ITEMS text default null");
    }

    protected boolean processDataPullConfig(StandardDatabaseWraper standardDatabaseWraper) {
        return standardDatabaseWraper.execSql("alter table DATA_PULL_CONFIG_INFO add column TARGET_COUNT integer default 0") && standardDatabaseWraper.execSql("alter table DATA_PULL_CONFIG_INFO add column TARGET_START_TIME integer default 0") && standardDatabaseWraper.execSql("alter table DATA_PULL_CONFIG_INFO add column TARGET_END_TIME integer default 0") && standardDatabaseWraper.execSql("alter table DATA_PULL_CONFIG_INFO add column NOISE_COUNT integer default 0") && standardDatabaseWraper.execSql("alter table DATA_PULL_CONFIG_INFO add column NOISE_START_TIME integer default 0") && standardDatabaseWraper.execSql("alter table DATA_PULL_CONFIG_INFO add column NOISE_END_TIME integer default 0") && standardDatabaseWraper.execSql("alter table DATA_PULL_CONFIG_INFO add column TEMPERATURE_COUT integer default 0") && standardDatabaseWraper.execSql("alter table DATA_PULL_CONFIG_INFO add column TEMPERATURE_START_TIME integer default 0") && standardDatabaseWraper.execSql("alter table DATA_PULL_CONFIG_INFO add column TEMPERATURE_END_TIME integer default 0") && standardDatabaseWraper.execSql("alter table DATA_PULL_CONFIG_INFO add column RATE_NOTICE_COUNT integer default 0") && standardDatabaseWraper.execSql("alter table DATA_PULL_CONFIG_INFO add column RATE_NOTICE_START_TIME integer default 0") && standardDatabaseWraper.execSql("alter table DATA_PULL_CONFIG_INFO add column RATE_NOTICE_END_TIME integer default 0") && standardDatabaseWraper.execSql("alter table DATA_PULL_CONFIG_INFO add column SHOW_STATE integer default 0");
    }

    private String getTaskUrlByDataName(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        switch (str) {
            case "WeightItemBean":
                return z ? "api/weight/data/last3days" : "api/weight/sync/data";
            case "UserMedalInfo":
                return "userapi/medal/get";
            case "ActiveTimeDayData":
                return z ? "api/exercise/data/last1days" : "api/exercise/sync/data";
            case "ServerBloodOxyDayData":
                return z ? "api/bloodoxy/data/last1days" : "api/bloodoxy/sync/data";
            case "CalorieDayData":
                return z ? "api/calorie/data/last1days" : "api/calorie/sync/data";
            case "SportDistanceBean":
                if (z) {
                    return "api/distance/data/last1days";
                }
                break;
            case "ServerHeartRateDayData":
                return z ? "api/heartrate/V2/data/last1days" : "api/heartrate/v2/300/sync/data";
            case "LifeCycleItemBean":
                return z ? "api/menses/data/last1days" : "api/menses/sync/data";
            case "HealthPressure":
                return z ? "api/pressure/data/last1days" : "api/pressure/sync/data";
            case "ServerSleepDayData":
                return z ? "api/sleep/data/last1days" : "api/sleep/sync/data";
            case "SportHealth":
                if (z) {
                    return "api/sports/getLatest";
                }
                break;
            case "StepDayData":
                return z ? "api/steps/data/last1days" : "api/steps/sync/data";
            case "WalkDayData":
                return z ? "api/walk/data/last1days" : "api/walk/sync/data";
            case "UserTargetNew":
                return z ? "api/sport/target/get" : "api/sport/target/sync/data";
            case "HealthVolumeData":
                return z ? "api/noise/data/last1days" : "api/noise/sync/data";
            default:
                return null;
        }
        return "api/sports/sync/data";
    }
}