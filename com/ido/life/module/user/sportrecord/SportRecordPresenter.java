package com.ido.life.module.user.sportrecord;

import android.text.TextUtils;
import android.util.Pair;
import com.boat.Xtend.two.R;
import com.ido.common.IdoApp;
import com.ido.common.log.CommonLogUtil;
import com.ido.common.log.LogPathImpl;
import com.ido.common.utils.LanguageUtil;
import com.ido.common.utils.NetworkUtil;
import com.ido.life.base.BasePresenter;
import com.ido.life.data.cache.MotionTypeManager;
import com.ido.life.data.health.remote.HealthManager;
import com.ido.life.database.model.SportHealth;
import com.ido.life.module.home.HomeHelperKt;
import com.ido.life.module.user.sportrecord.model.SportChildItem;
import com.ido.life.module.user.sportrecord.model.SportGroupItem;
import com.ido.life.module.user.sportrecord.model.SportScreenType;
import com.ido.life.syncdownload.DataDownLoadService;
import com.ido.life.syncdownload.ITaskExecutedCallBack;
import com.ido.life.syncdownload.NewTask;
import com.ido.life.util.DateUtil;
import com.ido.life.util.GreenDaoUtil;
import com.ido.life.util.RunTimeUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes3.dex */
public class SportRecordPresenter extends BasePresenter<ISportRecordView> implements ITaskExecutedCallBack {
    private static final int DEFAULT_TYPE = -1;
    private static final String TAG = "SportRecordPresenter";
    private boolean mHasFinish = false;
    List<SportHealth> list = new ArrayList();
    private List<Long> mFocusTaskIdList = new ArrayList();

    private List<SportScreenType> getSportTypes() {
        List<Integer> sportTypes = HealthManager.getInstance().getSportTypes();
        ArrayList arrayList = new ArrayList();
        SportScreenType sportScreenType = new SportScreenType();
        sportScreenType.setTypeId(-1);
        sportScreenType.setIndex(-1);
        sportScreenType.setTypeName(LanguageUtil.getLanguageText(R.string.sport_sport_record_all));
        arrayList.add(sportScreenType);
        if (sportTypes != null && sportTypes.size() > 0) {
            for (int i = 0; i < sportTypes.size(); i++) {
                CommonLogUtil.d(TAG, "getSportTypes: " + sportTypes.get(i));
                SportScreenType sportScreenType2 = new SportScreenType();
                sportScreenType2.setIndex(i);
                sportScreenType2.setTypeId(sportTypes.get(i).intValue());
                sportScreenType2.setTypeName(MotionTypeManager.INSTANCE.getMotionTypeName(sportTypes.get(i).intValue()));
                arrayList.add(sportScreenType2);
            }
        }
        return arrayList;
    }

    public void getRecordSport(int i, boolean z) {
        this.mFocusTaskIdList.clear();
        if (getView() == null) {
            return;
        }
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        String str = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
        calendar.add(1, -100);
        String str2 = DateUtil.format(calendar, DateUtil.DATE_FORMAT_YMD);
        ArrayList arrayList = new ArrayList();
        arrayList.add(SportHealth.class.getSimpleName());
        Pair<List<Long>, List<Long>> pairQueryLoadingOrFailedTask = GreenDaoUtil.queryLoadingOrFailedTask(RunTimeUtil.getInstance().getUserId(), arrayList, str2, str, DateUtil.DATE_FORMAT_YMD);
        if (pairQueryLoadingOrFailedTask == null || pairQueryLoadingOrFailedTask.first == null || pairQueryLoadingOrFailedTask.second == null || ((List) pairQueryLoadingOrFailedTask.first).size() + ((List) pairQueryLoadingOrFailedTask.second).size() == 0) {
            getView().showLoading();
            getLocalSportHealthData(i);
            return;
        }
        if (((List) pairQueryLoadingOrFailedTask.second).size() > 0) {
            getView().showLoading();
            if (NetworkUtil.isConnected(IdoApp.getAppContext())) {
                new TreeSet();
                if (((List) pairQueryLoadingOrFailedTask.first).size() > 0) {
                    this.mFocusTaskIdList.addAll((Collection) pairQueryLoadingOrFailedTask.first);
                }
                this.mFocusTaskIdList.addAll((Collection) pairQueryLoadingOrFailedTask.second);
                DataDownLoadService.requestPullData((List) pairQueryLoadingOrFailedTask.second);
            } else {
                getLocalSportHealthData(i);
            }
        } else {
            getView().showLoading();
            this.mFocusTaskIdList.addAll((Collection) pairQueryLoadingOrFailedTask.first);
        }
        addListener();
    }

    private void getLocalSportHealthData(final int i) {
        if (getView() == null) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "开始获取运动列表数据");
        new Thread(new Runnable() { // from class: com.ido.life.module.user.sportrecord.SportRecordPresenter.1
            @Override // java.lang.Runnable
            public void run() {
                CommonLogUtil.d(SportRecordPresenter.TAG, "type=" + i);
                SportRecordPresenter.this.list = HealthManager.getInstance().queryLocalSportRecord(i);
                if (SportRecordPresenter.this.list == null || SportRecordPresenter.this.list.size() <= 0) {
                    if (SportRecordPresenter.this.getView() != null) {
                        ((ISportRecordView) SportRecordPresenter.this.getView()).hideLoading();
                        ((ISportRecordView) SportRecordPresenter.this.getView()).showEmpty();
                        ((ISportRecordView) SportRecordPresenter.this.getView()).setEmptyText(LanguageUtil.getLanguageText(R.string.sport_record_no_data));
                        return;
                    }
                    return;
                }
                if (SportRecordPresenter.this.getView() != null) {
                    ((ISportRecordView) SportRecordPresenter.this.getView()).hideEmpty();
                }
                SportRecordPresenter.this.mHasFinish = false;
                SportRecordPresenter sportRecordPresenter = SportRecordPresenter.this;
                sportRecordPresenter.getSportRecord(sportRecordPresenter.list);
            }
        }).start();
    }

    public void addListener() {
        printAndSave("添加数据加载监听");
        DataDownLoadService.addTaskExecutedCallback(this);
    }

    private void printAndSave(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, str);
    }

    public void toSportDetailByType(int i, String str, int i2) {
        if (i == 54) {
            getView().toSwim(54, str);
        } else if (i == 55) {
            getView().toSwim(55, str);
        } else {
            getView().toSportDetail(i, str);
        }
    }

    public void activityFinish() {
        this.mHasFinish = true;
        DataDownLoadService.removeTaskExecutedCallback(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getSportRecord(List<SportHealth> list) {
        List<SportScreenType> sportTypes = getSportTypes();
        if (sportTypes != null && sportTypes.size() > 0 && getView() != null && !this.mHasFinish) {
            getView().setSportScreenType(sportTypes);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        HashMap map = new HashMap();
        int size = list.size();
        CommonLogUtil.d(TAG, "getSportRecord: " + this.mHasFinish);
        for (int i = 0; i < size; i++) {
            if (this.mHasFinish) {
                getView().hideLoading();
                return;
            }
            SportHealth sportHealth = list.get(i);
            StringBuilder sb = new StringBuilder(DateUtil.format(DateUtil.string2Date(sportHealth.getEndTime(), "yyyy-MM-dd HH:mm:ss"), DateUtil.DATE_FORMAT_MY));
            if (map.containsKey(sb.toString())) {
                SportGroupItem sportGroupItem = (SportGroupItem) map.get(sb.toString());
                SportChildItem sportChildItem = new SportChildItem();
                sportChildItem.id = sportGroupItem.children.size();
                sportGroupItem.addChild(sportChildItem);
                sportChildItem.group = sportGroupItem;
                changeSportItem(sportChildItem, sportHealth);
                sportGroupItem.totalSecond += sportHealth.getTotalSeconds();
                sportGroupItem.count = sportGroupItem.children.size();
                sportGroupItem.totalCalorie += sportChildItem.getNumCalories();
            } else {
                SportGroupItem sportGroupItem2 = new SportGroupItem();
                sportGroupItem2.id = map.size();
                sportGroupItem2.title = sb.toString();
                sportGroupItem2.totalSecond = sportHealth.getTotalSeconds();
                sportGroupItem2.count = 1;
                sportGroupItem2.totalCalorie = sportHealth.getNumCalories();
                ArrayList<SportChildItem> arrayList3 = new ArrayList<>();
                sportGroupItem2.children = arrayList3;
                SportChildItem sportChildItem2 = new SportChildItem();
                sportChildItem2.id = arrayList3.size();
                sportChildItem2.group = sportGroupItem2;
                changeSportItem(sportChildItem2, sportHealth);
                sportGroupItem2.totalSecond = sportHealth.getTotalSeconds();
                sportGroupItem2.totalCalorie = sportChildItem2.getNumCalories();
                sportGroupItem2.addChild(sportChildItem2);
                sportGroupItem2.count = arrayList3.size();
                map.put(sb.toString(), sportGroupItem2);
                arrayList2.add(sportGroupItem2);
            }
        }
        getView().hideLoading();
        getView().showSportRecord(arrayList2, arrayList);
    }

    private SportChildItem changeSportItem(SportChildItem sportChildItem, SportHealth sportHealth) {
        sportChildItem.setActivityId(sportHealth.getActivityId());
        sportChildItem.setAerobicSeconds(sportHealth.getAerobicSeconds());
        sportChildItem.setAnaerobicSecond(sportHealth.getAnaerobicSecond());
        sportChildItem.setAvgHrValue(sportHealth.getAvgHrValue());
        sportChildItem.setAvgPace(sportHealth.getAvgPace());
        sportChildItem.setAvgSpeed(sportHealth.getAvgSpeed());
        sportChildItem.setBurnFatSeconds(sportHealth.getBurnFatSeconds());
        sportChildItem.setDateTime(sportHealth.getDateTime());
        sportChildItem.setDistance(sportHealth.getDistance());
        sportChildItem.setEndTime(sportHealth.getEndTime());
        sportChildItem.setExtremeSecond(sportHealth.getExtremeSecond());
        sportChildItem.setMaxHrValue(sportHealth.getMaxHrValue());
        sportChildItem.setMinHrValue(sportHealth.getMinHrValue());
        sportChildItem.setSourceMac(sportHealth.getSourceMac());
        sportChildItem.setSid(sportHealth.getSid());
        sportChildItem.setNumCalories(sportHealth.getNumCalories());
        sportChildItem.setMaxSpeed(sportHealth.getMaxSpeed());
        sportChildItem.setStepRange(sportHealth.getStepRange());
        sportChildItem.setTotalSeconds(sportHealth.getTotalSeconds());
        sportChildItem.setType(sportHealth.getType());
        sportChildItem.setWarmupSeconds(sportHealth.getWarmupSeconds());
        sportChildItem.setPoolDistance(sportHealth.getPoolDistance());
        sportChildItem.setSourceType(sportHealth.getSourceType());
        sportChildItem.setIcon(sportHealth.getIcon());
        sportChildItem.setIsLocus(sportHealth.getIsLocus());
        sportChildItem.setSid(sportHealth.getSid());
        return sportChildItem;
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public void onTaskComplete() {
        if (getView() == null) {
            return;
        }
        getView().hideLoading();
        printAndSave("onTaskComplete");
        if (HomeHelperKt.queryHistoryDataDownloadSuccess(RunTimeUtil.getInstance().getUserId(), SportHealth.class.getSimpleName())) {
            printAndSave("运动记录列表获取成功");
            getLocalSportHealthData(-1);
        } else {
            printAndSave("运动列表任务执行完成，但是有部分任务执行失败");
            getLocalSportHealthData(-1);
        }
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public void onTaskExecutedFailed(NewTask.NewTaskInfo newTaskInfo, String str) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "onTaskExecutedFailed: " + str);
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public void onTaskExecutedSuccess(NewTask.NewTaskInfo newTaskInfo) {
        CommonLogUtil.printAndSave(LogPathImpl.getInstance().getSportLogPath(), TAG, "onTaskExecutedSuccess: " + newTaskInfo.toString());
    }

    @Override // com.ido.life.syncdownload.ITaskExecutedCallBack
    public List<Long> getFocusTaskList() {
        return this.mFocusTaskIdList;
    }
}