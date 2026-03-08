package com.ido.life.data.health;

import com.ido.life.bean.LatLngBean;
import com.ido.life.data.health.remote.HealthManager;
import com.ido.life.data.listener.OnResponseCallback;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.UserTargetNew;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface IHealthRepository {
    void deleteRecord(String str, OnResponseCallback onResponseCallback);

    List<LatLngBean> getLatLngByDateTime(long j);

    void getSportDetailBySid(String str, HealthManager.OnUserSportRecordDetailCallback onUserSportRecordDetailCallback);

    SportHealth getSportHealthByDateTime(String str);

    List<SportHealth> getSportRecord(int i);

    void getSportRecordList(String str, int i, HealthManager.OnUserSportRecordListCallback onUserSportRecordListCallback);

    void getSportSummary(int i, HealthManager.OnUserSportSummaryCallback onUserSportSummaryCallback);

    UserTargetNew getUserTarget(long j);

    void getUserTarget(HealthManager.OnUserTargetCallback onUserTargetCallback);

    void setUserTarget(int i, String str, int i2, int i3);

    void setUserTarget(String str, int i, int i2, long j, OnResultCallback onResultCallback);

    void uploadFile(String str, OnResultCallback onResultCallback);

    void uploadSportHealth(SportHealth sportHealth, HealthManager.OnUserSportDetailSidCallback onUserSportDetailSidCallback);
}