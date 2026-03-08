package com.ido.life.database.model;

import com.boat.Xtend.two.R;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class SportCard {
    public static final String HIKING = "HIKING";
    public static final String INDOOR_RUN = "INDOOR_RUN";
    public static final String INDOOR_WALK = "INDOOR_WALK";
    public static final String OUTDOOR_CYCLE = "OUTDOOR_CYCLE";
    public static final String OUTDOOR_RUN = "OUTDOOR_RUN";
    public static final String OUTDOOR_WALK = "OUTDOOR_WALK";
    private long CreateTime;
    private Long Id;
    private long UpdateTime;
    private boolean UploadSuccess;
    private long UserId;
    private List<Integer> ValueList;
    private transient DaoSession daoSession;
    private transient SportCardDao myDao;

    public static int initTypeNameId(int i) {
        if (i == 4) {
            return R.string.sport_walk_onfoot;
        }
        if (i == 52) {
            return R.string.sport_walk_outdoor;
        }
        if (i == 53) {
            return R.string.sport_walk_indoor;
        }
        switch (i) {
            case 48:
            default:
                return R.string.sport_run_outdoor;
            case 49:
                return R.string.sport_run_indoor;
            case 50:
                return R.string.sport_type_ride_outdoor;
        }
    }

    public SportCard(Long l, long j, List<Integer> list, boolean z, long j2, long j3) {
        this.Id = l;
        this.UserId = j;
        this.ValueList = list;
        this.UploadSuccess = z;
        this.CreateTime = j2;
        this.UpdateTime = j3;
    }

    public SportCard() {
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long l) {
        this.Id = l;
    }

    public long getUserId() {
        return this.UserId;
    }

    public void setUserId(long j) {
        this.UserId = j;
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

    public static List<Integer> getDefaultSportCard() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(48);
        arrayList.add(52);
        arrayList.add(50);
        arrayList.add(4);
        arrayList.add(49);
        arrayList.add(53);
        return arrayList;
    }

    public void delete() {
        SportCardDao sportCardDao = this.myDao;
        if (sportCardDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        sportCardDao.delete(this);
    }

    public void refresh() {
        SportCardDao sportCardDao = this.myDao;
        if (sportCardDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        sportCardDao.refresh(this);
    }

    public void update() {
        if (this.myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        this.UpdateTime = System.currentTimeMillis();
        this.myDao.update(this);
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getSportCardDao() : null;
    }
}