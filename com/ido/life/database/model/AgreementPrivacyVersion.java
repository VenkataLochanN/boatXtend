package com.ido.life.database.model;

import org.greenrobot.greendao.DaoException;

/* JADX INFO: loaded from: classes2.dex */
public class AgreementPrivacyVersion {
    private Long APP_HELP;
    private long CreateTime;
    private Long HELP_EXPLAIN;
    private Long Id;
    private Long LINK_HELP;
    private Long PRIVACY_POLICY;
    private Long USER_AGREEMENT;
    private long UpdateTime;
    private long UserId;
    private transient DaoSession daoSession;
    private transient AgreementPrivacyVersionDao myDao;

    public AgreementPrivacyVersion() {
    }

    public AgreementPrivacyVersion(Long l, Long l2, Long l3, Long l4, Long l5, long j, long j2) {
        this.USER_AGREEMENT = l;
        this.PRIVACY_POLICY = l2;
        this.APP_HELP = l3;
        this.LINK_HELP = l4;
        this.HELP_EXPLAIN = l5;
        this.CreateTime = j;
        this.UpdateTime = j2;
    }

    public AgreementPrivacyVersion(Long l, long j, Long l2, Long l3, Long l4, Long l5, Long l6, long j2, long j3) {
        this.Id = l;
        this.UserId = j;
        this.USER_AGREEMENT = l2;
        this.PRIVACY_POLICY = l3;
        this.APP_HELP = l4;
        this.LINK_HELP = l5;
        this.HELP_EXPLAIN = l6;
        this.CreateTime = j2;
        this.UpdateTime = j3;
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

    public Long getUSER_AGREEMENT() {
        return this.USER_AGREEMENT;
    }

    public void setUSER_AGREEMENT(Long l) {
        this.USER_AGREEMENT = l;
    }

    public Long getPRIVACY_POLICY() {
        return this.PRIVACY_POLICY;
    }

    public void setPRIVACY_POLICY(Long l) {
        this.PRIVACY_POLICY = l;
    }

    public Long getAPP_HELP() {
        return this.APP_HELP;
    }

    public void setAPP_HELP(Long l) {
        this.APP_HELP = l;
    }

    public Long getLINK_HELP() {
        return this.LINK_HELP;
    }

    public void setLINK_HELP(Long l) {
        this.LINK_HELP = l;
    }

    public Long getHELP_EXPLAIN() {
        return this.HELP_EXPLAIN;
    }

    public void setHELP_EXPLAIN(Long l) {
        this.HELP_EXPLAIN = l;
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

    public void delete() {
        AgreementPrivacyVersionDao agreementPrivacyVersionDao = this.myDao;
        if (agreementPrivacyVersionDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        agreementPrivacyVersionDao.delete(this);
    }

    public void refresh() {
        AgreementPrivacyVersionDao agreementPrivacyVersionDao = this.myDao;
        if (agreementPrivacyVersionDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        agreementPrivacyVersionDao.refresh(this);
    }

    public void update() {
        AgreementPrivacyVersionDao agreementPrivacyVersionDao = this.myDao;
        if (agreementPrivacyVersionDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        agreementPrivacyVersionDao.update(this);
    }

    public String toString() {
        return "AgreementPrivacyVersion{Id=" + this.Id + ", UserId=" + this.UserId + ", USER_AGREEMENT=" + this.USER_AGREEMENT + ", PRIVACY_POLICY=" + this.PRIVACY_POLICY + ", APP_HELP=" + this.APP_HELP + ", LINK_HELP=" + this.LINK_HELP + ", HELP_EXPLAIN=" + this.HELP_EXPLAIN + ", CreateTime=" + this.CreateTime + ", UpdateTime=" + this.UpdateTime + '}';
    }

    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        this.myDao = daoSession != null ? daoSession.getAgreementPrivacyVersionDao() : null;
    }
}