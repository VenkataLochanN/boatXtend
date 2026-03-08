package com.ido.life.data.health.remote;

import android.database.Cursor;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.net.BaseEntity;
import com.ido.common.net.BaseEntityNew;
import com.ido.common.net.DataListEntity;
import com.ido.common.net.http.Result;
import com.ido.common.utils.GsonUtil;
import com.ido.common.utils.TimeUtil;
import com.ido.life.bean.LatLngBean;
import com.ido.life.bean.ServerBean;
import com.ido.life.data.api.HealthApi;
import com.ido.life.data.api.entity.DataSyncCountItem;
import com.ido.life.data.api.entity.ResultBEntity;
import com.ido.life.data.api.entity.ResultSEntity;
import com.ido.life.data.api.entity.SportHealthDetailEntity;
import com.ido.life.data.api.entity.SportHealthDetailSidEntity;
import com.ido.life.data.api.entity.SportSummaryEntity;
import com.ido.life.data.api.entity.UserTargetEntity;
import com.ido.life.data.listener.ApiCallback;
import com.ido.life.data.listener.OnResponseCallback;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.database.GreenDaoManager;
import com.ido.life.database.model.DaoSession;
import com.ido.life.database.model.SportGpsData;
import com.ido.life.database.model.SportGpsDataDao;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.SportHealthDao;
import com.ido.life.database.model.middleModel.UploadSportHealth;
import com.ido.life.util.RunTimeUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import no.nordicsemi.android.dfu.DfuBaseService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.greenrobot.greendao.query.WhereCondition;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class HealthManager {
    public static final int DEFAULT_TEMP_USER_ID = -2;
    public static final int DEFAULT_USER_ID = -1;
    private static final String TAG = "HealthManager";
    private static HealthManager mHealthManager;

    public interface CommonApiCallback<T> {
        void onFailed(String str);

        void onSuccess(T t);
    }

    public interface DataSyncCountCallback {
        void onFailed(String str);

        void onSuccess(List<DataSyncCountItem> list);
    }

    public interface OnUserSportDetailSidCallback {
        void onFailed(String str);

        void onSuccess(SportHealthDetailSidEntity sportHealthDetailSidEntity);
    }

    public interface OnUserSportRecordDetailCallback {
        void onFailed(String str);

        void onSuccess(SportHealthDetailEntity sportHealthDetailEntity);
    }

    public interface OnUserSportRecordListCallback {
        void onFailed(String str);

        void onSuccess(DataListEntity<SportHealth> dataListEntity);
    }

    public interface OnUserSportSummaryCallback {
        void onFailed(String str);

        void onSuccess(SportSummaryEntity sportSummaryEntity);
    }

    public interface OnUserTargetCallback {
        void onFailed(String str);

        void onSuccess(UserTargetEntity userTargetEntity);
    }

    public static HealthManager getInstance() {
        if (mHealthManager == null) {
            synchronized (HealthManager.class) {
                mHealthManager = new HealthManager();
            }
        }
        return mHealthManager;
    }

    public static void getUserTarget(OnUserTargetCallback onUserTargetCallback) {
        HealthApi.api.getUserTarget().enqueue(getApiCallBack(onUserTargetCallback));
    }

    public static void setUserTarget(String str, int i, int i2, long j, OnResultCallback onResultCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("date", str);
            jSONObject.put("numSteps", i);
            jSONObject.put("weight", i2);
            jSONObject.put("timestamp", j);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getLoginRegisterLogPath(), TAG, "注册时的设置目标界面setUserTarget: " + jSONObject.toString());
        HealthApi.api.setUserTarget(RequestBody.create(MediaType.parse("application/json"), jSONObject.toString())).enqueue(getResultBApiCallBack(onResultCallback));
    }

    public static void getSportSummary(int i, OnUserSportSummaryCallback onUserSportSummaryCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", i);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        HealthApi.api.getSummary(RequestBody.create(MediaType.parse("application/json"), jSONObject.toString())).enqueue(getSportSummaryApiCallBack(onUserSportSummaryCallback));
    }

    public static void getSportRecord(String str, int i, OnUserSportRecordListCallback onUserSportRecordListCallback) {
        HealthApi.api.getSportRecords(str, 15, i).enqueue(getSportSportListApiCallBack(onUserSportRecordListCallback));
    }

    public static void getSportDetailBySid(String str, OnUserSportRecordDetailCallback onUserSportRecordDetailCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sid", str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        HealthApi.api.getSportDetailBySid(RequestBody.create(MediaType.parse("application/json"), jSONObject.toString())).enqueue(getSportDetailBySidApiCallBack(onUserSportRecordDetailCallback));
    }

    public static void uploadSportHealth(SportHealth sportHealth, OnUserSportDetailSidCallback onUserSportDetailSidCallback) {
        if (sportHealth == null) {
            if (onUserSportDetailSidCallback != null) {
                onUserSportDetailSidCallback.onFailed("sportHealth is null");
                return;
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(sportHealth);
        UploadSportHealth uploadSportHealth = new UploadSportHealth();
        uploadSportHealth.datas = arrayList;
        String json = GsonUtil.toJson(uploadSportHealth);
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "uploadSportHealth: " + json);
        HealthApi.api.uploadSportHealth(RequestBody.create(MediaType.parse("application/json"), json)).enqueue(uploadSportHealthApiCallBack(onUserSportDetailSidCallback));
    }

    public static void getDataSyncCount(DataSyncCountCallback dataSyncCountCallback) {
        HealthApi.api.getDataSyncCount().enqueue(getDataSyncCountApiCallBack(dataSyncCountCallback));
    }

    public static void deleteSportRecordBySid(String str, OnResponseCallback onResponseCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sid", str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        HealthApi.api.deleteSportRecordBySid(RequestBody.create(MediaType.parse("application/json"), jSONObject.toString())).enqueue(getApiCallBack(onResponseCallback));
    }

    public static void updateFileFd(String str, OnResultCallback onResultCallback) {
        File file = new File(str);
        HealthApi.api.uploadFileFdImg(MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse(DfuBaseService.MIME_TYPE_OCTET_STREAM), file))).enqueue(getResultSApiCallBack(onResultCallback));
    }

    private static ApiCallback<ResultSEntity> getResultSApiCallBack(final OnResultCallback onResultCallback) {
        return new ApiCallback<ResultSEntity>() { // from class: com.ido.life.data.health.remote.HealthManager.1
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(ResultSEntity resultSEntity) {
                Result result = new Result();
                result.setData(resultSEntity.getResult());
                onResultCallback.onSuccess(result);
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                CommonLogUtil.d(HealthManager.TAG, "onError: " + i + AppInfo.DELIM + str);
                onResultCallback.onFailed(str);
            }
        };
    }

    public static void deleteLocalSportRecord(String str) {
        getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.DateTime.eq(str), new WhereCondition[0]).buildDelete().executeDeleteWithoutDetachingEntities();
    }

    public static int getSportHealthDistanceList(int i) {
        Cursor cursorRawQuery = getDaoSession().getDatabase().rawQuery("SELECT distance FROM SPORT_HEALTH WHERE " + SportHealthDao.Properties.Type.columnName + "=?", new String[]{String.valueOf(i)});
        int i2 = 0;
        while (cursorRawQuery.moveToNext()) {
            try {
                try {
                    i2 += cursorRawQuery.getInt(0);
                    CommonLogUtil.d(TAG, "getSportHealthDistanceList: " + cursorRawQuery.getInt(0) + AppInfo.DELIM + i2);
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
        return i2;
    }

    public static int getSportHealthDistanceList(int i, String str) {
        Cursor cursorRawQuery = getDaoSession().getDatabase().rawQuery("SELECT distance FROM SPORT_HEALTH WHERE " + SportHealthDao.Properties.Type.columnName + "=? and " + SportHealthDao.Properties.DateTime.columnName + " like '%" + str + "%'", new String[]{String.valueOf(i)});
        int i2 = 0;
        while (cursorRawQuery.moveToNext()) {
            try {
                try {
                    i2 += cursorRawQuery.getInt(0);
                    CommonLogUtil.d(TAG, "getSportHealthDistanceList: " + cursorRawQuery.getInt(0) + AppInfo.DELIM + i2);
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
        return i2;
    }

    public List<LatLngBean> getLatLngBeanList(long j) {
        List listAnalysisJsonArrayToList;
        ArrayList arrayList = new ArrayList();
        SportGpsData sportGpsData = (SportGpsData) getDaoSession().queryBuilder(SportGpsData.class).where(SportGpsDataDao.Properties.UserId.eq(Long.valueOf(getUserId())), SportGpsDataDao.Properties.TimeMillis.eq(Long.valueOf(j))).limit(1).unique();
        if (sportGpsData != null && (listAnalysisJsonArrayToList = GsonUtil.analysisJsonArrayToList(sportGpsData.getGpsData().getItems(), String[][].class)) != null && listAnalysisJsonArrayToList.size() > 0) {
            for (int i = 0; i < listAnalysisJsonArrayToList.size(); i++) {
                String[] strArr = (String[]) listAnalysisJsonArrayToList.get(i);
                if (strArr != null && strArr.length == 3) {
                    try {
                        LatLngBean latLngBean = new LatLngBean();
                        latLngBean.latitude = Double.parseDouble(strArr[0]);
                        latLngBean.longitude = Double.parseDouble(strArr[1]);
                        long j2 = Long.parseLong(strArr[2]);
                        if (j2 < 1000000000000L) {
                            j2 *= 1000;
                        }
                        latLngBean.currentTimeMillis = TimeUtil.convTimeDetail(j2);
                        arrayList.add(latLngBean);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        try {
                            LatLngBean latLngBean2 = new LatLngBean();
                            latLngBean2.latitude = Double.parseDouble(strArr[0]);
                            latLngBean2.longitude = Double.parseDouble(strArr[1]);
                            double d2 = Double.parseDouble(strArr[2]);
                            if (d2 < 1.0E12d) {
                                d2 *= 1000.0d;
                            }
                            latLngBean2.currentTimeMillis = TimeUtil.convTimeDetail((long) d2);
                            arrayList.add(latLngBean2);
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public SportHealth getSportHealth(String str) {
        return (SportHealth) getDaoSession().queryBuilder(SportHealth.class).where(SportHealthDao.Properties.DateTime.eq(str), SportHealthDao.Properties.UserId.eq(Long.valueOf(getUserId()))).limit(1).unique();
    }

    public boolean getSportLocus(String str) {
        SportHealth sportHealth;
        String[] strArr = {SportHealthDao.Properties.IsLocus.columnName};
        int length = strArr.length;
        String strConcat = "select ";
        for (int i = 0; i < length; i++) {
            strConcat = i == length - 1 ? strConcat.concat(strArr[i]) : strConcat.concat(strArr[i] + AppInfo.DELIM);
        }
        Cursor cursorRawQuery = getDaoSession().getDatabase().rawQuery(strConcat.concat(" from SPORT_HEALTH").concat(" where " + SportHealthDao.Properties.UserId.columnName + "=? and " + SportHealthDao.Properties.DateTime.columnName + "=?"), new String[]{String.valueOf(getUserId()), str});
        if (cursorRawQuery == null || !cursorRawQuery.moveToNext()) {
            sportHealth = null;
        } else {
            sportHealth = new SportHealth();
            sportHealth.setIsLocus(cursorRawQuery.getInt(0));
        }
        if (cursorRawQuery != null) {
            try {
                cursorRawQuery.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return (sportHealth == null || sportHealth.getIsLocus() == 0) ? false : true;
    }

    public List<SportHealth> queryLocalSportRecord(int i) {
        Cursor cursorRawQuery;
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "开始查询运动列表数据库");
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        int i3 = 1;
        String[] strArr = {SportHealthDao.Properties.Type.columnName, SportHealthDao.Properties.SubType.columnName, SportHealthDao.Properties.SourceType.columnName, SportHealthDao.Properties.TotalSeconds.columnName, SportHealthDao.Properties.Distance.columnName, SportHealthDao.Properties.AvgPace.columnName, SportHealthDao.Properties.AvgSpeed.columnName, SportHealthDao.Properties.EndTime.columnName, SportHealthDao.Properties.DateTime.columnName, SportHealthDao.Properties.NumCalories.columnName, SportHealthDao.Properties.AvgHrValue.columnName, SportHealthDao.Properties.Icon.columnName, SportHealthDao.Properties.IsLocus.columnName, SportHealthDao.Properties.Sid.columnName};
        int length = strArr.length;
        String strConcat = "select ";
        for (int i4 = 0; i4 < length; i4++) {
            strConcat = i4 == length - 1 ? strConcat.concat(strArr[i4]) : strConcat.concat(strArr[i4] + AppInfo.DELIM);
        }
        String strConcat2 = strConcat.concat(" from SPORT_HEALTH");
        if (i != -1) {
            String strConcat3 = strConcat2.concat(" where " + SportHealthDao.Properties.UserId.columnName + "=? and " + SportHealthDao.Properties.Type.columnName + "=?");
            StringBuilder sb = new StringBuilder();
            sb.append(" order by ");
            sb.append(SportHealthDao.Properties.StartTime.columnName);
            sb.append(" desc ");
            cursorRawQuery = getDaoSession().getDatabase().rawQuery(strConcat3.concat(sb.toString()), new String[]{String.valueOf(getUserId()), String.valueOf(i)});
        } else {
            cursorRawQuery = getDaoSession().getDatabase().rawQuery(strConcat2.concat(" where " + SportHealthDao.Properties.UserId.columnName + "=? ").concat(" order by " + SportHealthDao.Properties.StartTime.columnName + " desc "), new String[]{String.valueOf(getUserId())});
        }
        while (cursorRawQuery.moveToNext()) {
            SportHealth sportHealth = new SportHealth();
            sportHealth.setType(cursorRawQuery.getInt(i2));
            sportHealth.setSubType(cursorRawQuery.getInt(i3));
            sportHealth.setSourceType(cursorRawQuery.getInt(2));
            sportHealth.setTotalSeconds(cursorRawQuery.getInt(3));
            sportHealth.setDistance(cursorRawQuery.getInt(4));
            sportHealth.setAvgPace(cursorRawQuery.getInt(5));
            sportHealth.setAvgSpeed(cursorRawQuery.getInt(6));
            sportHealth.setEndTime(cursorRawQuery.getString(7));
            sportHealth.setDateTime(cursorRawQuery.getString(8));
            sportHealth.setNumCalories(cursorRawQuery.getInt(9));
            sportHealth.setAvgHrValue(cursorRawQuery.getInt(10));
            sportHealth.setIcon(cursorRawQuery.getString(11));
            sportHealth.setIsLocus(cursorRawQuery.getInt(12));
            sportHealth.setSid(cursorRawQuery.getString(13));
            arrayList.add(sportHealth);
            i2 = 0;
            i3 = 1;
        }
        if (cursorRawQuery != null) {
            try {
                cursorRawQuery.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    public List<Integer> getSportTypes() {
        ArrayList arrayList = new ArrayList();
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "开始查询运动列表数据库");
        new ArrayList();
        Cursor cursorRawQuery = getDaoSession().getDatabase().rawQuery("select ".concat(new String[]{SportHealthDao.Properties.Type.columnName}[0]).concat(" from SPORT_HEALTH").concat(" where " + SportHealthDao.Properties.UserId.columnName + "=? ").concat(" order by " + SportHealthDao.Properties.StartTime.columnName + " desc "), new String[]{String.valueOf(getUserId())});
        while (cursorRawQuery.moveToNext()) {
            if (!arrayList.contains(Integer.valueOf(cursorRawQuery.getInt(0)))) {
                arrayList.add(Integer.valueOf(cursorRawQuery.getInt(0)));
            }
        }
        if (cursorRawQuery != null) {
            try {
                cursorRawQuery.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    public long queryLocalSportRecordCount(int i) {
        if (i == -1) {
            return getDaoSession().getSportHealthDao().queryBuilder().count();
        }
        return getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.Type.eq(Integer.valueOf(i)), SportHealthDao.Properties.UserId.eq(Long.valueOf(getUserId()))).count();
    }

    public SportHealth queryLastestSportRecord() {
        if (getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.UserId.eq(Long.valueOf(getUserId())), new WhereCondition[0]).orderDesc(SportHealthDao.Properties.Timestamp).count() == 0) {
            return null;
        }
        return getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.UserId.eq(Long.valueOf(getUserId())), new WhereCondition[0]).orderDesc(SportHealthDao.Properties.Timestamp).limit(1).unique();
    }

    private static DaoSession getDaoSession() {
        return GreenDaoManager.getInstance().getDaoSession();
    }

    private long getUserId() {
        return RunTimeUtil.getInstance().getUserId();
    }

    private static ApiCallback<ResultBEntity> getResultBApiCallBack(final OnResultCallback onResultCallback) {
        return new ApiCallback<ResultBEntity>() { // from class: com.ido.life.data.health.remote.HealthManager.2
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(ResultBEntity resultBEntity) {
                Result result = new Result();
                result.setData(Boolean.valueOf(resultBEntity.isResult()));
                onResultCallback.onSuccess(result);
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                onResultCallback.onFailed(str);
            }
        };
    }

    private static ApiCallback<SportSummaryEntity> getSportSummaryApiCallBack(final OnUserSportSummaryCallback onUserSportSummaryCallback) {
        return new ApiCallback<SportSummaryEntity>() { // from class: com.ido.life.data.health.remote.HealthManager.3
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(SportSummaryEntity sportSummaryEntity) {
                CommonLogUtil.d(HealthManager.TAG, "onSuccess: " + sportSummaryEntity.toString());
                OnUserSportSummaryCallback onUserSportSummaryCallback2 = onUserSportSummaryCallback;
                if (onUserSportSummaryCallback2 != null) {
                    onUserSportSummaryCallback2.onSuccess(HealthManager.convertSportSummaryResponse(sportSummaryEntity));
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                CommonLogUtil.d(HealthManager.TAG, "onError: " + str);
                OnUserSportSummaryCallback onUserSportSummaryCallback2 = onUserSportSummaryCallback;
                if (onUserSportSummaryCallback2 != null) {
                    onUserSportSummaryCallback2.onFailed(str);
                }
            }
        };
    }

    private static ApiCallback<SportHealthDetailEntity> getSportDetailBySidApiCallBack(final OnUserSportRecordDetailCallback onUserSportRecordDetailCallback) {
        return new ApiCallback<SportHealthDetailEntity>() { // from class: com.ido.life.data.health.remote.HealthManager.4
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(SportHealthDetailEntity sportHealthDetailEntity) {
                OnUserSportRecordDetailCallback onUserSportRecordDetailCallback2 = onUserSportRecordDetailCallback;
                if (onUserSportRecordDetailCallback2 != null) {
                    onUserSportRecordDetailCallback2.onSuccess(sportHealthDetailEntity);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnUserSportRecordDetailCallback onUserSportRecordDetailCallback2 = onUserSportRecordDetailCallback;
                if (onUserSportRecordDetailCallback2 != null) {
                    onUserSportRecordDetailCallback2.onFailed(str);
                }
            }
        };
    }

    private static ApiCallback<SportHealthDetailSidEntity> uploadSportHealthApiCallBack(final OnUserSportDetailSidCallback onUserSportDetailSidCallback) {
        return new ApiCallback<SportHealthDetailSidEntity>() { // from class: com.ido.life.data.health.remote.HealthManager.5
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(SportHealthDetailSidEntity sportHealthDetailSidEntity) {
                OnUserSportDetailSidCallback onUserSportDetailSidCallback2 = onUserSportDetailSidCallback;
                if (onUserSportDetailSidCallback2 != null) {
                    onUserSportDetailSidCallback2.onSuccess(sportHealthDetailSidEntity);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnUserSportDetailSidCallback onUserSportDetailSidCallback2 = onUserSportDetailSidCallback;
                if (onUserSportDetailSidCallback2 != null) {
                    onUserSportDetailSidCallback2.onFailed(str);
                }
            }
        };
    }

    private static ApiCallback<DataListEntity<SportHealth>> getSportSportListApiCallBack(final OnUserSportRecordListCallback onUserSportRecordListCallback) {
        return new ApiCallback<DataListEntity<SportHealth>>() { // from class: com.ido.life.data.health.remote.HealthManager.6
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(DataListEntity<SportHealth> dataListEntity) {
                OnUserSportRecordListCallback onUserSportRecordListCallback2 = onUserSportRecordListCallback;
                if (onUserSportRecordListCallback2 != null) {
                    onUserSportRecordListCallback2.onSuccess(dataListEntity);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnUserSportRecordListCallback onUserSportRecordListCallback2 = onUserSportRecordListCallback;
                if (onUserSportRecordListCallback2 != null) {
                    onUserSportRecordListCallback2.onFailed(str);
                }
            }
        };
    }

    private static ApiCallback<BaseEntityNew<List<DataSyncCountItem>>> getDataSyncCountApiCallBack(final DataSyncCountCallback dataSyncCountCallback) {
        return new ApiCallback<BaseEntityNew<List<DataSyncCountItem>>>() { // from class: com.ido.life.data.health.remote.HealthManager.7
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<List<DataSyncCountItem>> baseEntityNew) {
                DataSyncCountCallback dataSyncCountCallback2 = dataSyncCountCallback;
                if (dataSyncCountCallback2 != null) {
                    dataSyncCountCallback2.onSuccess(baseEntityNew == null ? null : baseEntityNew.getResult());
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                DataSyncCountCallback dataSyncCountCallback2 = dataSyncCountCallback;
                if (dataSyncCountCallback2 != null) {
                    dataSyncCountCallback2.onFailed(str + "code==" + i);
                }
            }
        };
    }

    private static ApiCallback<UserTargetEntity> getApiCallBack(final OnUserTargetCallback onUserTargetCallback) {
        return new ApiCallback<UserTargetEntity>() { // from class: com.ido.life.data.health.remote.HealthManager.8
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(UserTargetEntity userTargetEntity) {
                CommonLogUtil.d(HealthManager.TAG, "onSuccess: " + userTargetEntity.toString());
                OnUserTargetCallback onUserTargetCallback2 = onUserTargetCallback;
                if (onUserTargetCallback2 != null) {
                    onUserTargetCallback2.onSuccess(HealthManager.convertUserInfoResponse(userTargetEntity));
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                CommonLogUtil.d(HealthManager.TAG, "onError: " + str);
                OnUserTargetCallback onUserTargetCallback2 = onUserTargetCallback;
                if (onUserTargetCallback2 != null) {
                    onUserTargetCallback2.onFailed(str + "code==" + i);
                }
            }
        };
    }

    public static void uploadLifeCycle(ServerBean.LifeCycleUploadBean lifeCycleUploadBean, final CommonApiCallback<BaseEntityNew<Boolean>> commonApiCallback) {
        HealthApi.api.uploadLifeCycle(lifeCycleUploadBean).enqueue(new ApiCallback<BaseEntityNew<Boolean>>() { // from class: com.ido.life.data.health.remote.HealthManager.9
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntityNew<Boolean> baseEntityNew) {
                CommonApiCallback commonApiCallback2 = commonApiCallback;
                if (commonApiCallback2 != null) {
                    commonApiCallback2.onSuccess(baseEntityNew);
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                CommonApiCallback commonApiCallback2 = commonApiCallback;
                if (commonApiCallback2 != null) {
                    commonApiCallback2.onFailed(str);
                }
            }
        });
    }

    public static UserTargetEntity convertUserInfoResponse(UserTargetEntity userTargetEntity) {
        UserTargetEntity userTargetEntity2 = new UserTargetEntity();
        userTargetEntity2.setResult(userTargetEntity.getResult());
        return userTargetEntity2;
    }

    public static SportSummaryEntity convertSportSummaryResponse(SportSummaryEntity sportSummaryEntity) {
        SportSummaryEntity sportSummaryEntity2 = new SportSummaryEntity();
        sportSummaryEntity2.setResult(sportSummaryEntity.getResult());
        return sportSummaryEntity2;
    }

    private static ApiCallback<BaseEntity> getApiCallBack(final OnResponseCallback onResponseCallback) {
        return new ApiCallback<BaseEntity>() { // from class: com.ido.life.data.health.remote.HealthManager.10
            @Override // com.ido.life.data.listener.NetCallback
            public void onSuccess(BaseEntity baseEntity) {
                OnResponseCallback onResponseCallback2 = onResponseCallback;
                if (onResponseCallback2 != null) {
                    onResponseCallback2.onSuccess();
                }
            }

            @Override // com.ido.life.data.listener.NetCallback
            public void onError(Throwable th, int i, String str) {
                OnResponseCallback onResponseCallback2 = onResponseCallback;
                if (onResponseCallback2 != null) {
                    onResponseCallback2.onFailed(str);
                }
            }
        };
    }
}