package com.ido.life.bean;

import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class UserBean implements Serializable, Cloneable {
    public String account;
    public int accountStatus;
    public String birthday;
    public String city;
    public String county;
    public String dataUrl;
    public String dbName;

    @Deprecated
    public String headerPath;
    public String headerUrl;
    public int heightLb;
    public String id;
    public boolean migrated;
    public String openId;
    public String pwd;
    public String username;
    public int weightLb;
    public int weightSt;
    public int year;
    public int month = 1;
    public int day = 1;
    public int gender = 0;
    public int height = 175;
    public int weight = 60;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UserBean userBean = (UserBean) obj;
        return this.year == userBean.year && this.month == userBean.month && this.day == userBean.day && this.gender == userBean.gender && this.height == userBean.height && this.weight == userBean.weight && Objects.equals(this.username, userBean.username);
    }

    public String toString() {
        return "UserBean{username='" + this.username + "', year=" + this.year + ", month=" + this.month + ", day=" + this.day + ", gender=" + this.gender + ", height=" + this.height + ", account=" + this.account + ", weight=" + this.weight + ", dataUrl=" + this.dataUrl + ", dbName=" + this.dbName + ", birthday=" + this.birthday + ", migrated=" + this.migrated + ", headerUrl=" + this.headerUrl + ", headerPath='" + this.headerPath + "'}";
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}