package com.ido.life.module.device.reminders.mode;

import com.google.android.gms.fitness.FitnessActivities;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReminderMode.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0018\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0007HÆ\u0003J\t\u0010%\u001a\u00020\u0007HÆ\u0003J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\t\u0010'\u001a\u00020\u000bHÆ\u0003JO\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010,\u001a\u00020\u0007HÖ\u0001J\t\u0010-\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\b\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000e\"\u0004\b\u001a\u0010\u0010R\u001a\u0010\t\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006."}, d2 = {"Lcom/ido/life/module/device/reminders/mode/ReminderMode;", "", "desc", "", "remind", "dateTime", "hour", "", "minute", "remind_on_off", "weeks", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;III[Z)V", "getDateTime", "()Ljava/lang/String;", "setDateTime", "(Ljava/lang/String;)V", "getDesc", "setDesc", "getHour", "()I", "setHour", "(I)V", "getMinute", "setMinute", "getRemind", "setRemind", "getRemind_on_off", "setRemind_on_off", "getWeeks", "()[Z", "setWeeks", "([Z)V", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class ReminderMode {
    private String dateTime;
    private String desc;
    private int hour;
    private int minute;
    private String remind;
    private int remind_on_off;
    private boolean[] weeks;

    public static /* synthetic */ ReminderMode copy$default(ReminderMode reminderMode, String str, String str2, String str3, int i, int i2, int i3, boolean[] zArr, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = reminderMode.desc;
        }
        if ((i4 & 2) != 0) {
            str2 = reminderMode.remind;
        }
        String str4 = str2;
        if ((i4 & 4) != 0) {
            str3 = reminderMode.dateTime;
        }
        String str5 = str3;
        if ((i4 & 8) != 0) {
            i = reminderMode.hour;
        }
        int i5 = i;
        if ((i4 & 16) != 0) {
            i2 = reminderMode.minute;
        }
        int i6 = i2;
        if ((i4 & 32) != 0) {
            i3 = reminderMode.remind_on_off;
        }
        int i7 = i3;
        if ((i4 & 64) != 0) {
            zArr = reminderMode.weeks;
        }
        return reminderMode.copy(str, str4, str5, i5, i6, i7, zArr);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getRemind() {
        return this.remind;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getDateTime() {
        return this.dateTime;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getHour() {
        return this.hour;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getMinute() {
        return this.minute;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getRemind_on_off() {
        return this.remind_on_off;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final boolean[] getWeeks() {
        return this.weeks;
    }

    public final ReminderMode copy(String desc, String remind, String dateTime, int hour, int minute, int remind_on_off, boolean[] weeks) {
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        Intrinsics.checkParameterIsNotNull(remind, "remind");
        Intrinsics.checkParameterIsNotNull(dateTime, "dateTime");
        Intrinsics.checkParameterIsNotNull(weeks, "weeks");
        return new ReminderMode(desc, remind, dateTime, hour, minute, remind_on_off, weeks);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReminderMode)) {
            return false;
        }
        ReminderMode reminderMode = (ReminderMode) other;
        return Intrinsics.areEqual(this.desc, reminderMode.desc) && Intrinsics.areEqual(this.remind, reminderMode.remind) && Intrinsics.areEqual(this.dateTime, reminderMode.dateTime) && this.hour == reminderMode.hour && this.minute == reminderMode.minute && this.remind_on_off == reminderMode.remind_on_off && Intrinsics.areEqual(this.weeks, reminderMode.weeks);
    }

    public int hashCode() {
        String str = this.desc;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.remind;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.dateTime;
        int iHashCode3 = (((((((iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + Integer.valueOf(this.hour).hashCode()) * 31) + Integer.valueOf(this.minute).hashCode()) * 31) + Integer.valueOf(this.remind_on_off).hashCode()) * 31;
        boolean[] zArr = this.weeks;
        return iHashCode3 + (zArr != null ? Arrays.hashCode(zArr) : 0);
    }

    public String toString() {
        return "ReminderMode(desc=" + this.desc + ", remind=" + this.remind + ", dateTime=" + this.dateTime + ", hour=" + this.hour + ", minute=" + this.minute + ", remind_on_off=" + this.remind_on_off + ", weeks=" + Arrays.toString(this.weeks) + ")";
    }

    public ReminderMode(String desc, String remind, String dateTime, int i, int i2, int i3, boolean[] weeks) {
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        Intrinsics.checkParameterIsNotNull(remind, "remind");
        Intrinsics.checkParameterIsNotNull(dateTime, "dateTime");
        Intrinsics.checkParameterIsNotNull(weeks, "weeks");
        this.desc = desc;
        this.remind = remind;
        this.dateTime = dateTime;
        this.hour = i;
        this.minute = i2;
        this.remind_on_off = i3;
        this.weeks = weeks;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final void setDesc(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.desc = str;
    }

    public final String getDateTime() {
        return this.dateTime;
    }

    public final int getHour() {
        return this.hour;
    }

    public final String getRemind() {
        return this.remind;
    }

    public final void setDateTime(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.dateTime = str;
    }

    public final void setHour(int i) {
        this.hour = i;
    }

    public final void setRemind(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.remind = str;
    }

    public final int getMinute() {
        return this.minute;
    }

    public final int getRemind_on_off() {
        return this.remind_on_off;
    }

    public final void setMinute(int i) {
        this.minute = i;
    }

    public final void setRemind_on_off(int i) {
        this.remind_on_off = i;
    }

    public final boolean[] getWeeks() {
        return this.weeks;
    }

    public final void setWeeks(boolean[] zArr) {
        Intrinsics.checkParameterIsNotNull(zArr, "<set-?>");
        this.weeks = zArr;
    }
}