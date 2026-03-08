package com.ido.life.bean;

import android.graphics.drawable.Drawable;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NotificationApp.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001Bu\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0006¢\u0006\u0002\u0010\u0010J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0006HÆ\u0003J\t\u0010)\u001a\u00020\u0006HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0006HÆ\u0003J\t\u0010,\u001a\u00020\u0006HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\u0006HÆ\u0003Jy\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u0006HÆ\u0001J\u0013\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u000106H\u0096\u0002J\b\u00107\u001a\u00020\u0006H\u0016J\b\u00108\u001a\u00020\u0003H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u001a\u0010\u000f\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0012\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0014\"\u0004\b\u001f\u0010 R\u001a\u0010\u000e\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0012\"\u0004\b\"\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0014R\u001a\u0010\r\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0012\"\u0004\b&\u0010\u0019¨\u00069"}, d2 = {"Lcom/ido/life/bean/NotificationApp;", "Ljava/io/Serializable;", AppMeasurementSdk.ConditionalUserProperty.NAME, "", "pkg", "evt_type", "", "pic_flag", "icon", "Landroid/graphics/drawable/Drawable;", "fieldInFunctionList", "fieldIntInNotification", "fieldBoolInNotification", NotificationCompat.CATEGORY_STATUS, "oldStatus", "group", "(Ljava/lang/String;Ljava/lang/String;IILandroid/graphics/drawable/Drawable;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;III)V", "getEvt_type", "()I", "getFieldBoolInNotification", "()Ljava/lang/String;", "getFieldInFunctionList", "getFieldIntInNotification", "getGroup", "setGroup", "(I)V", "getIcon", "()Landroid/graphics/drawable/Drawable;", "setIcon", "(Landroid/graphics/drawable/Drawable;)V", "getName", "setName", "(Ljava/lang/String;)V", "getOldStatus", "setOldStatus", "getPic_flag", "getPkg", "getStatus", "setStatus", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class NotificationApp implements Serializable {
    private final int evt_type;
    private final String fieldBoolInNotification;
    private final String fieldInFunctionList;
    private final String fieldIntInNotification;
    private int group;
    private transient Drawable icon;
    private String name;
    private int oldStatus;
    private final int pic_flag;
    private final String pkg;
    private int status;

    public NotificationApp() {
        this(null, null, 0, 0, null, null, null, null, 0, 0, 0, 2047, null);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component10, reason: from getter */
    public final int getOldStatus() {
        return this.oldStatus;
    }

    /* JADX INFO: renamed from: component11, reason: from getter */
    public final int getGroup() {
        return this.group;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getPkg() {
        return this.pkg;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getEvt_type() {
        return this.evt_type;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getPic_flag() {
        return this.pic_flag;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Drawable getIcon() {
        return this.icon;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getFieldInFunctionList() {
        return this.fieldInFunctionList;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getFieldIntInNotification() {
        return this.fieldIntInNotification;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getFieldBoolInNotification() {
        return this.fieldBoolInNotification;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    public final NotificationApp copy(String name, String pkg, int evt_type, int pic_flag, Drawable icon, String fieldInFunctionList, String fieldIntInNotification, String fieldBoolInNotification, int status, int oldStatus, int group) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(pkg, "pkg");
        Intrinsics.checkParameterIsNotNull(fieldInFunctionList, "fieldInFunctionList");
        Intrinsics.checkParameterIsNotNull(fieldIntInNotification, "fieldIntInNotification");
        Intrinsics.checkParameterIsNotNull(fieldBoolInNotification, "fieldBoolInNotification");
        return new NotificationApp(name, pkg, evt_type, pic_flag, icon, fieldInFunctionList, fieldIntInNotification, fieldBoolInNotification, status, oldStatus, group);
    }

    public NotificationApp(String name, String pkg, int i, int i2, Drawable drawable, String fieldInFunctionList, String fieldIntInNotification, String fieldBoolInNotification, int i3, int i4, int i5) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(pkg, "pkg");
        Intrinsics.checkParameterIsNotNull(fieldInFunctionList, "fieldInFunctionList");
        Intrinsics.checkParameterIsNotNull(fieldIntInNotification, "fieldIntInNotification");
        Intrinsics.checkParameterIsNotNull(fieldBoolInNotification, "fieldBoolInNotification");
        this.name = name;
        this.pkg = pkg;
        this.evt_type = i;
        this.pic_flag = i2;
        this.icon = drawable;
        this.fieldInFunctionList = fieldInFunctionList;
        this.fieldIntInNotification = fieldIntInNotification;
        this.fieldBoolInNotification = fieldBoolInNotification;
        this.status = i3;
        this.oldStatus = i4;
        this.group = i5;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final String getPkg() {
        return this.pkg;
    }

    public final int getEvt_type() {
        return this.evt_type;
    }

    public final int getPic_flag() {
        return this.pic_flag;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ NotificationApp(String str, String str2, int i, int i2, Drawable drawable, String str3, String str4, String str5, int i3, int i4, int i5, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        String str6 = (i6 & 1) != 0 ? "" : str;
        String str7 = (i6 & 2) != 0 ? "" : str2;
        int i7 = (i6 & 4) != 0 ? 0 : i;
        int i8 = (i6 & 8) != 0 ? 0 : i2;
        Drawable drawable2 = (i6 & 16) != 0 ? (Drawable) null : drawable;
        String str8 = (i6 & 32) != 0 ? "" : str3;
        String str9 = (i6 & 64) != 0 ? "" : str4;
        String str10 = (i6 & 128) == 0 ? str5 : "";
        int i9 = (i6 & 256) != 0 ? 0 : i3;
        this(str6, str7, i7, i8, drawable2, str8, str9, str10, i9, (i6 & 512) != 0 ? i9 : i4, (i6 & 1024) == 0 ? i5 : 0);
    }

    public final Drawable getIcon() {
        return this.icon;
    }

    public final void setIcon(Drawable drawable) {
        this.icon = drawable;
    }

    public final String getFieldInFunctionList() {
        return this.fieldInFunctionList;
    }

    public final String getFieldIntInNotification() {
        return this.fieldIntInNotification;
    }

    public final String getFieldBoolInNotification() {
        return this.fieldBoolInNotification;
    }

    public final int getStatus() {
        return this.status;
    }

    public final void setStatus(int i) {
        this.status = i;
    }

    public final int getOldStatus() {
        return this.oldStatus;
    }

    public final void setOldStatus(int i) {
        this.oldStatus = i;
    }

    public final int getGroup() {
        return this.group;
    }

    public final void setGroup(int i) {
        this.group = i;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            return !(Intrinsics.areEqual(this.pkg, ((NotificationApp) other).pkg) ^ true);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.ido.life.bean.NotificationApp");
    }

    public int hashCode() {
        return this.pkg.hashCode();
    }

    public String toString() {
        return "NotificationApp(name='" + this.name + "', pkg='" + this.pkg + "', icon=" + this.icon + ", fieldInFunctionList='" + this.fieldInFunctionList + "', fieldIntInNotification='" + this.fieldIntInNotification + "', fieldBoolInNotification='" + this.fieldBoolInNotification + "', status=" + this.status + ", oldStatus=" + this.oldStatus + ')';
    }
}