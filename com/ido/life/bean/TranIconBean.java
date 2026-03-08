package com.ido.life.bean;

import android.graphics.drawable.Drawable;
import com.ido.life.database.model.NoticeAppEntity;

/* JADX INFO: loaded from: classes2.dex */
public class TranIconBean extends NoticeAppEntity {
    private String fileSuffix;
    private int group;
    private Drawable icon;
    private boolean installed = true;
    private int pic_num;
    private int sport_size_type;
    private int sport_type;

    public Drawable getIcon() {
        return this.icon;
    }

    public void setIcon(Drawable drawable) {
        this.icon = drawable;
    }

    public String getFileSuffix() {
        return this.fileSuffix;
    }

    public void setFileSuffix(String str) {
        this.fileSuffix = str;
    }

    public int getSport_size_type() {
        return this.sport_size_type;
    }

    public void setSport_size_type(int i) {
        this.sport_size_type = i;
    }

    public int getSport_type() {
        return this.sport_type;
    }

    public void setSport_type(int i) {
        this.sport_type = i;
    }

    public int getPic_num() {
        return this.pic_num;
    }

    public void setPic_num(int i) {
        this.pic_num = i;
    }

    public int getGroup() {
        return this.group;
    }

    public void setGroup(int i) {
        this.group = i;
    }

    public TranIconBean() {
    }

    public boolean isInstalled() {
        return this.installed;
    }

    public void setInstalled(boolean z) {
        this.installed = z;
    }

    public TranIconBean(NoticeAppEntity noticeAppEntity) {
        setType(noticeAppEntity.getType());
        setIsPush(noticeAppEntity.getIsPush());
        setAppUpdateTime(noticeAppEntity.getAppUpdateTime());
        setReminderCounts(noticeAppEntity.getReminderCounts());
        setId(noticeAppEntity.getId());
        setAppName(noticeAppEntity.getAppName());
        setPkgName(noticeAppEntity.getPkgName());
    }

    @Override // com.ido.life.database.model.NoticeAppEntity
    public String toString() {
        return "TranIconBean{, fileSuffix='" + this.fileSuffix + "', sport_type=" + this.sport_type + ", sport_size_type=" + this.sport_size_type + ", pic_num=" + this.pic_num + ", Type=" + getType() + ", pkgName='" + getPkgName() + "', reminderCounts=" + getReminderCounts() + ", appUpdateTime=" + getAppUpdateTime() + ", appName='" + getAppName() + "', isPush=" + getIsPush() + ", installed=" + this.installed + '}';
    }
}