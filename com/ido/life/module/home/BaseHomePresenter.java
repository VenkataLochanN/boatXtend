package com.ido.life.module.home;

import com.ido.life.base.BaseCmdPresenter;
import com.ido.life.base.IBaseView;
import com.ido.life.database.GreenDaoManager;
import com.ido.life.database.LocalHealthDataManager;
import com.ido.life.database.model.ActiveTimeDayDataDao;
import com.ido.life.database.model.CalorieDayDataDao;
import com.ido.life.database.model.ServerBloodOxyDayDataDao;
import com.ido.life.database.model.ServerSleepDayDataDao;
import com.ido.life.database.model.SportHealthDao;
import com.ido.life.database.model.StepDayDataDao;
import com.ido.life.database.model.WalkDayDataDao;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import org.greenrobot.greendao.query.WhereCondition;

/* JADX INFO: loaded from: classes2.dex */
public class BaseHomePresenter<V extends IBaseView> extends BaseCmdPresenter<V> {
    public boolean hasSleepData() {
        return GreenDaoManager.getInstance().getDaoSession().getServerSleepDayDataDao().queryBuilder().where(ServerSleepDayDataDao.Properties.UserId.eq(Long.valueOf(RunTimeUtil.getInstance().getUserId())), new WhereCondition[0]).count() > 0;
    }

    public boolean hasSportRecord() {
        return GreenDaoManager.getInstance().getDaoSession().getSportHealthDao().queryBuilder().where(SportHealthDao.Properties.UserId.eq(Long.valueOf(RunTimeUtil.getInstance().getUserId())), new WhereCondition[0]).count() > 0;
    }

    public boolean hasBloodOxyData() {
        return GreenDaoManager.getInstance().getDaoSession().getServerBloodOxyDayDataDao().queryBuilder().where(ServerBloodOxyDayDataDao.Properties.UserId.eq(Long.valueOf(RunTimeUtil.getInstance().getUserId())), ServerBloodOxyDayDataDao.Properties.MaxValue.notEq(0)).count() > 0;
    }

    public boolean hasWalkData() {
        return GreenDaoManager.getInstance().getDaoSession().getWalkDayDataDao().queryBuilder().where(WalkDayDataDao.Properties.UserId.eq(Long.valueOf(RunTimeUtil.getInstance().getUserId())), new WhereCondition[0]).count() > 0;
    }

    public boolean hasStepData() {
        return GreenDaoManager.getInstance().getDaoSession().getStepDayDataDao().queryBuilder().where(StepDayDataDao.Properties.UserId.eq(Long.valueOf(RunTimeUtil.getInstance().getUserId())), StepDayDataDao.Properties.NumSteps.gt(0)).count() > 0;
    }

    public boolean hasActivityData() {
        return GreenDaoManager.getInstance().getDaoSession().getActiveTimeDayDataDao().queryBuilder().where(ActiveTimeDayDataDao.Properties.UserId.eq(Long.valueOf(RunTimeUtil.getInstance().getUserId())), ActiveTimeDayDataDao.Properties.TotalSeconds.gt(0)).count() > 0;
    }

    public boolean hasCalorie() {
        return GreenDaoManager.getInstance().getDaoSession().getCalorieDayDataDao().queryBuilder().where(CalorieDayDataDao.Properties.UserId.eq(Long.valueOf(RunTimeUtil.getInstance().getUserId())), new WhereCondition[0]).count() > 0;
    }

    public boolean hasLifeCycle() {
        return HomeHelperKt.queryLatestLifeCycle(RunTimeUtil.getInstance().getUserId()) != null;
    }

    public boolean hasAmbientVolume() {
        return GreenDaoUtil.getHealthVolumeCount(RunTimeUtil.getInstance().getUserId()) > 0;
    }

    public boolean hasPressure() {
        return GreenDaoUtil.queryHealthPressureRecordCount(RunTimeUtil.getInstance().getUserId()) > 0;
    }

    public boolean hasHeartRate() {
        return LocalHealthDataManager.getInstance().queryHeartRateRecordCount(RunTimeUtil.getInstance().getUserId()) > 0;
    }

    public boolean hasDistance() {
        return GreenDaoUtil.queryDistanceRecordCount(RunTimeUtil.getInstance().getUserId()) > 0;
    }
}