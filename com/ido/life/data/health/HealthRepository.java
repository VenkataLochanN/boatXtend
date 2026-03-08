package com.ido.life.data.health;

import com.ido.life.bean.LatLngBean;
import com.ido.life.data.health.local.UserTargetPreference;
import com.ido.life.data.health.remote.HealthManager;
import com.ido.life.data.listener.OnResponseCallback;
import com.ido.life.data.listener.OnResultCallback;
import com.ido.life.database.model.SportHealth;
import com.ido.life.database.model.UserTargetNew;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class HealthRepository implements IHealthRepository {
    private static HealthRepository mInstance = new HealthRepository();

    public static HealthRepository getInstance() {
        return mInstance;
    }

    private HealthRepository() {
    }

    @Override // com.ido.life.data.health.IHealthRepository
    public void getUserTarget(HealthManager.OnUserTargetCallback onUserTargetCallback) {
        HealthManager.getUserTarget(onUserTargetCallback);
    }

    @Override // com.ido.life.data.health.IHealthRepository
    public void setUserTarget(String str, int i, int i2, long j, OnResultCallback onResultCallback) {
        HealthManager.setUserTarget(str, i, i2, j, onResultCallback);
    }

    @Override // com.ido.life.data.health.IHealthRepository
    public void setUserTarget(int i, String str, int i2, int i3) {
        UserTargetNew userTargetNew = new UserTargetNew();
        userTargetNew.setUserId(i);
        userTargetNew.setStep(i2);
        userTargetNew.setWeight(i3);
        userTargetNew.setDate(str);
        UserTargetPreference.savaUserTarget(userTargetNew);
    }

    @Override // com.ido.life.data.health.IHealthRepository
    public UserTargetNew getUserTarget(long j) {
        return UserTargetPreference.getUserTarget();
    }

    @Override // com.ido.life.data.health.IHealthRepository
    public void getSportSummary(int i, HealthManager.OnUserSportSummaryCallback onUserSportSummaryCallback) {
        HealthManager.getSportSummary(i, onUserSportSummaryCallback);
    }

    @Override // com.ido.life.data.health.IHealthRepository
    public void getSportRecordList(String str, int i, HealthManager.OnUserSportRecordListCallback onUserSportRecordListCallback) {
        HealthManager.getSportRecord(str, i, onUserSportRecordListCallback);
    }

    @Override // com.ido.life.data.health.IHealthRepository
    public void getSportDetailBySid(String str, HealthManager.OnUserSportRecordDetailCallback onUserSportRecordDetailCallback) {
        HealthManager.getSportDetailBySid(str, onUserSportRecordDetailCallback);
    }

    @Override // com.ido.life.data.health.IHealthRepository
    public void uploadSportHealth(SportHealth sportHealth, HealthManager.OnUserSportDetailSidCallback onUserSportDetailSidCallback) {
        HealthManager.uploadSportHealth(sportHealth, onUserSportDetailSidCallback);
    }

    @Override // com.ido.life.data.health.IHealthRepository
    public void deleteRecord(String str, OnResponseCallback onResponseCallback) {
        HealthManager.deleteSportRecordBySid(str, onResponseCallback);
    }

    @Override // com.ido.life.data.health.IHealthRepository
    public void uploadFile(String str, OnResultCallback onResultCallback) {
        HealthManager.updateFileFd(str, onResultCallback);
    }

    @Override // com.ido.life.data.health.IHealthRepository
    public List<SportHealth> getSportRecord(int i) {
        return HealthManager.getInstance().queryLocalSportRecord(i);
    }

    @Override // com.ido.life.data.health.IHealthRepository
    public SportHealth getSportHealthByDateTime(String str) {
        return HealthManager.getInstance().getSportHealth(str);
    }

    @Override // com.ido.life.data.health.IHealthRepository
    public List<LatLngBean> getLatLngByDateTime(long j) {
        return HealthManager.getInstance().getLatLngBeanList(j);
    }
}