package com.ido.life.database.model;

import com.boat.Xtend.two.R;
import com.ido.life.bean.MainData;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class HomeCard {
    public static final String BLOODOXY_CARD = "BLOODOXY_CARD";
    public static final String CALORIE_EXERCISE_WALK_CARD = "CALORIE_EXERCISE_WALK_CARD";
    public static final String HEARTRATE_CARD = "HEARTRATE_CARD";
    public static final String MENSES_CARD = "MENSES_CARD";
    public static final String NOISE_CARD = "NOISE_CARD";
    public static final String OXYGEN_UPTAKE_CARD = "MAXIMAL_OXYGEN_UPTAKE_CARD";
    public static final String PRESSURE_CARD = "PRESSURE_CARD";
    public static final String SLEEP_CARD = "SLEEP_CARD";
    public static final String SPORT_CARD = "SPORT_CARD";
    public static final String STEP_CARD = "STEP_CARD";
    public static final String WEIGHT_CARD = "WEIGHT_CARD";
    private long CreateTime;
    private List<Integer> HideValueList;
    private Long Id;
    private long UpdateTime;
    private boolean UploadSuccess;
    private Long UserId;
    private List<Integer> ValueList;
    private transient DaoSession daoSession;
    private transient HomeCardDao myDao;

    public static int getTypeName(int i) {
        switch (i) {
            case 1:
                return R.string.sport_detail_step;
            case 2:
                return R.string.home_card_calorie_stronger_activity;
            case 3:
                return R.string.home_card_sport_record;
            case 4:
                return R.string.home_card_sleep;
            case 5:
                return R.string.home_card_heart_rate;
            case 6:
                return R.string.home_card_pressure;
            case 7:
                return R.string.home_card_blood_oxygen;
            case 8:
                return R.string.home_card_weight;
            case 9:
                return R.string.home_card_physiological_cycle;
            case 10:
            default:
                return R.string.home_card_calorie_title;
            case 11:
                return R.string.ambient_volume;
            case 12:
                return R.string.help_max_spo2_title;
        }
    }

    public HomeCard(Long l, Long l2, List<Integer> list, List<Integer> list2, boolean z, long j, long j2) {
        this.Id = l;
        this.UserId = l2;
        this.ValueList = list;
        this.HideValueList = list2;
        this.UploadSuccess = z;
        this.CreateTime = j;
        this.UpdateTime = j2;
    }

    public HomeCard() {
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long l) {
        this.Id = l;
    }

    public Long getUserId() {
        return this.UserId;
    }

    public void setUserId(Long l) {
        this.UserId = l;
    }

    public List<Integer> getValueList() {
        return this.ValueList;
    }

    public void setValueList(List<Integer> list) {
        this.ValueList = list;
    }

    public boolean isUploadSuccess() {
        return this.UploadSuccess;
    }

    public void setUploadSuccess(boolean z) {
        this.UploadSuccess = z;
    }

    public long getCreateTime() {
        return this.CreateTime;
    }

    public void setCreateTime(long j) {
        this.CreateTime = j;
    }

    public long getUpdateTime() {
        return this.UpdateTime;
    }

    public void setUpdateTime(long j) {
        this.UpdateTime = j;
    }

    public boolean getUploadSuccess() {
        return this.UploadSuccess;
    }

    public List<Integer> getDefaultValueList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        return arrayList;
    }

    public static LinkedList<MainData> getDefaultHomeCardList() {
        LinkedList<MainData> linkedList = new LinkedList<>();
        linkedList.add(new MainData(1));
        linkedList.add(new MainData(4));
        linkedList.add(new MainData(5));
        linkedList.add(new MainData(3));
        return linkedList;
    }

    public static List<Integer> getDefaultHomeShowCardValueList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(3);
        return arrayList;
    }

    public static List<Integer> getDefaultHomeHideCardValueList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(8);
        return arrayList;
    }

    public void delete() {
        HomeCardDao homeCardDao = this.myDao;
        if (homeCardDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        homeCardDao.delete(this);
    }

    public void refresh() {
        HomeCardDao homeCardDao = this.myDao;
        if (homeCardDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        homeCardDao.refresh(this);
    }

    public void update() {
        if (this.myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        this.UpdateTime = System.currentTimeMillis();
        this.myDao.update(this);
    }

    public List<Integer> getHideValueList() {
        return this.HideValueList;
    }

    public void setHideValueList(List<Integer> list) {
        this.HideValueList = list;
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getHomeCardDao() : null;
    }
}