package com.ido.life.bean;

import com.ido.life.util.DateUtil;
import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes2.dex */
public class MenstrualCycle implements Serializable {
    public int cycleLength;
    public Date easyPregnancyStartDate;
    public Date endDate;
    public boolean isShow;
    public int menstrualLength;
    public Date ovulationDate;
    public Date startDate;

    public MenstrualCycle() {
    }

    public MenstrualCycle(Date date, int i, int i2) {
        this.startDate = date;
        this.cycleLength = i2;
        this.menstrualLength = i;
        setEndDate(DateUtil.getSpecifiedDayBefore(date, (-i2) + 1));
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date date) {
        this.startDate = date;
        setEndDate(DateUtil.getSpecifiedDayBefore(date, (-this.cycleLength) + 1));
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date date) {
        this.endDate = date;
        setOvulationDate(DateUtil.getSpecifiedDayBefore(getEndDate(), 13));
    }

    public int getCycleLength() {
        return this.cycleLength;
    }

    public void setCycleLength(int i) {
        this.cycleLength = i;
    }

    public int getMenstrualLength() {
        return this.menstrualLength;
    }

    public void setMenstrualLength(int i) {
        this.menstrualLength = i;
    }

    public Date getOvulationDate() {
        return this.ovulationDate;
    }

    public void setOvulationDate(Date date) {
        this.ovulationDate = date;
        setEasyPregnancyStartDate(DateUtil.getSpecifiedDayBefore(date, 4));
    }

    public Date getEasyPregnancyStartDate() {
        return this.easyPregnancyStartDate;
    }

    public void setEasyPregnancyStartDate(Date date) {
        this.easyPregnancyStartDate = date;
    }

    public boolean isShow() {
        return this.isShow;
    }

    public void setShow(boolean z) {
        this.isShow = z;
    }
}