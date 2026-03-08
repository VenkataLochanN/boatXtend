package com.ido.life.bean;

import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.ido.common.utils.FileDialDefinedUtil;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CwdAppBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001:\u0003&'(B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n¢\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\nHÆ\u0003J\t\u0010\u001c\u001a\u00020\nHÆ\u0003J\t\u0010\u001d\u001a\u00020\nHÆ\u0003JK\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020\nHÖ\u0001J\t\u0010$\u001a\u00020%HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011¨\u0006)"}, d2 = {"Lcom/ido/life/bean/CwdAppBean;", "Ljava/io/Serializable;", FileDialDefinedUtil.APP_FILE, "Lcom/ido/life/bean/CwdAppBean$App;", "locations", "", "Lcom/ido/life/bean/CwdAppBean$Location;", "select", "Lcom/ido/life/bean/CwdAppBean$Select;", "version", "", "zipName", "function_support", "(Lcom/ido/life/bean/CwdAppBean$App;Ljava/util/List;Lcom/ido/life/bean/CwdAppBean$Select;III)V", "getApp", "()Lcom/ido/life/bean/CwdAppBean$App;", "getFunction_support", "()I", "getLocations", "()Ljava/util/List;", "getSelect", "()Lcom/ido/life/bean/CwdAppBean$Select;", "getVersion", "getZipName", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "toString", "", "App", "Location", "Select", "app_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class CwdAppBean implements Serializable {
    private final App app;
    private final int function_support;
    private final List<Location> locations;
    private final Select select;
    private final int version;
    private final int zipName;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CwdAppBean copy$default(CwdAppBean cwdAppBean, App app, List list, Select select, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            app = cwdAppBean.app;
        }
        if ((i4 & 2) != 0) {
            list = cwdAppBean.locations;
        }
        List list2 = list;
        if ((i4 & 4) != 0) {
            select = cwdAppBean.select;
        }
        Select select2 = select;
        if ((i4 & 8) != 0) {
            i = cwdAppBean.version;
        }
        int i5 = i;
        if ((i4 & 16) != 0) {
            i2 = cwdAppBean.zipName;
        }
        int i6 = i2;
        if ((i4 & 32) != 0) {
            i3 = cwdAppBean.function_support;
        }
        return cwdAppBean.copy(app, list2, select2, i5, i6, i3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final App getApp() {
        return this.app;
    }

    public final List<Location> component2() {
        return this.locations;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Select getSelect() {
        return this.select;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getVersion() {
        return this.version;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getZipName() {
        return this.zipName;
    }

    /* JADX INFO: renamed from: component6, reason: from getter */
    public final int getFunction_support() {
        return this.function_support;
    }

    public final CwdAppBean copy(App app, List<Location> locations, Select select, int version, int zipName, int function_support) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(locations, "locations");
        Intrinsics.checkParameterIsNotNull(select, "select");
        return new CwdAppBean(app, locations, select, version, zipName, function_support);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CwdAppBean)) {
            return false;
        }
        CwdAppBean cwdAppBean = (CwdAppBean) other;
        return Intrinsics.areEqual(this.app, cwdAppBean.app) && Intrinsics.areEqual(this.locations, cwdAppBean.locations) && Intrinsics.areEqual(this.select, cwdAppBean.select) && this.version == cwdAppBean.version && this.zipName == cwdAppBean.zipName && this.function_support == cwdAppBean.function_support;
    }

    public int hashCode() {
        App app = this.app;
        int iHashCode = (app != null ? app.hashCode() : 0) * 31;
        List<Location> list = this.locations;
        int iHashCode2 = (iHashCode + (list != null ? list.hashCode() : 0)) * 31;
        Select select = this.select;
        return ((((((iHashCode2 + (select != null ? select.hashCode() : 0)) * 31) + Integer.valueOf(this.version).hashCode()) * 31) + Integer.valueOf(this.zipName).hashCode()) * 31) + Integer.valueOf(this.function_support).hashCode();
    }

    public String toString() {
        return "CwdAppBean(app=" + this.app + ", locations=" + this.locations + ", select=" + this.select + ", version=" + this.version + ", zipName=" + this.zipName + ", function_support=" + this.function_support + ")";
    }

    public CwdAppBean(App app, List<Location> locations, Select select, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(app, "app");
        Intrinsics.checkParameterIsNotNull(locations, "locations");
        Intrinsics.checkParameterIsNotNull(select, "select");
        this.app = app;
        this.locations = locations;
        this.select = select;
        this.version = i;
        this.zipName = i2;
        this.function_support = i3;
    }

    public final App getApp() {
        return this.app;
    }

    public final List<Location> getLocations() {
        return this.locations;
    }

    public /* synthetic */ CwdAppBean(App app, List list, Select select, int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(app, list, (i4 & 4) != 0 ? new Select(0, CollectionsKt.listOf(2), 0, 3) : select, i, i2, i3);
    }

    public final Select getSelect() {
        return this.select;
    }

    public final int getVersion() {
        return this.version;
    }

    public final int getZipName() {
        return this.zipName;
    }

    public final int getFunction_support() {
        return this.function_support;
    }

    /* JADX INFO: compiled from: CwdAppBean.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003JS\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0096\u0002J\b\u0010\u001b\u001a\u00020\u0003H\u0016J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001e"}, d2 = {"Lcom/ido/life/bean/CwdAppBean$Location;", "Ljava/io/Serializable;", "type", "", "time", "", "week", "day", "func", "(ILjava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getDay", "()Ljava/util/List;", "getFunc", "getTime", "getType", "()I", "getWeek", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class Location implements Serializable {
        private final List<Integer> day;
        private final List<Integer> func;
        private final List<Integer> time;
        private final int type;
        private final List<Integer> week;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Location copy$default(Location location, int i, List list, List list2, List list3, List list4, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = location.type;
            }
            if ((i2 & 2) != 0) {
                list = location.time;
            }
            List list5 = list;
            if ((i2 & 4) != 0) {
                list2 = location.week;
            }
            List list6 = list2;
            if ((i2 & 8) != 0) {
                list3 = location.day;
            }
            List list7 = list3;
            if ((i2 & 16) != 0) {
                list4 = location.func;
            }
            return location.copy(i, list5, list6, list7, list4);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getType() {
            return this.type;
        }

        public final List<Integer> component2() {
            return this.time;
        }

        public final List<Integer> component3() {
            return this.week;
        }

        public final List<Integer> component4() {
            return this.day;
        }

        public final List<Integer> component5() {
            return this.func;
        }

        public final Location copy(int type, List<Integer> time, List<Integer> week, List<Integer> day, List<Integer> func) {
            Intrinsics.checkParameterIsNotNull(time, "time");
            Intrinsics.checkParameterIsNotNull(week, "week");
            Intrinsics.checkParameterIsNotNull(day, "day");
            Intrinsics.checkParameterIsNotNull(func, "func");
            return new Location(type, time, week, day, func);
        }

        public String toString() {
            return "Location(type=" + this.type + ", time=" + this.time + ", week=" + this.week + ", day=" + this.day + ", func=" + this.func + ")";
        }

        public Location(int i, List<Integer> time, List<Integer> week, List<Integer> day, List<Integer> func) {
            Intrinsics.checkParameterIsNotNull(time, "time");
            Intrinsics.checkParameterIsNotNull(week, "week");
            Intrinsics.checkParameterIsNotNull(day, "day");
            Intrinsics.checkParameterIsNotNull(func, "func");
            this.type = i;
            this.time = time;
            this.week = week;
            this.day = day;
            this.func = func;
        }

        public final int getType() {
            return this.type;
        }

        public final List<Integer> getTime() {
            return this.time;
        }

        public final List<Integer> getWeek() {
            return this.week;
        }

        public final List<Integer> getDay() {
            return this.day;
        }

        public final List<Integer> getFunc() {
            return this.func;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
                return false;
            }
            if (other != null) {
                return this.type == ((Location) other).type;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.ido.life.bean.CwdAppBean.Location");
        }

        public int hashCode() {
            return this.type;
        }
    }

    /* JADX INFO: compiled from: CwdAppBean.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/ido/life/bean/CwdAppBean$App;", "Ljava/io/Serializable;", "bpp", "", "format", "", AppMeasurementSdk.ConditionalUserProperty.NAME, "(ILjava/lang/String;Ljava/lang/String;)V", "getBpp", "()I", "getFormat", "()Ljava/lang/String;", "getName", "component1", "component2", "component3", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class App implements Serializable {
        private final int bpp;
        private final String format;
        private final String name;

        public static /* synthetic */ App copy$default(App app, int i, String str, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = app.bpp;
            }
            if ((i2 & 2) != 0) {
                str = app.format;
            }
            if ((i2 & 4) != 0) {
                str2 = app.name;
            }
            return app.copy(i, str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getBpp() {
            return this.bpp;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFormat() {
            return this.format;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final App copy(int bpp, String format, String name) {
            Intrinsics.checkParameterIsNotNull(format, "format");
            Intrinsics.checkParameterIsNotNull(name, "name");
            return new App(bpp, format, name);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof App)) {
                return false;
            }
            App app = (App) other;
            return this.bpp == app.bpp && Intrinsics.areEqual(this.format, app.format) && Intrinsics.areEqual(this.name, app.name);
        }

        public int hashCode() {
            int iHashCode = Integer.valueOf(this.bpp).hashCode() * 31;
            String str = this.format;
            int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.name;
            return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "App(bpp=" + this.bpp + ", format=" + this.format + ", name=" + this.name + ")";
        }

        public App(int i, String format, String name) {
            Intrinsics.checkParameterIsNotNull(format, "format");
            Intrinsics.checkParameterIsNotNull(name, "name");
            this.bpp = i;
            this.format = format;
            this.name = name;
        }

        public final int getBpp() {
            return this.bpp;
        }

        public final String getFormat() {
            return this.format;
        }

        public final String getName() {
            return this.name;
        }
    }

    /* JADX INFO: compiled from: CwdAppBean.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J7\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR \u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\n¨\u0006 "}, d2 = {"Lcom/ido/life/bean/CwdAppBean$Select;", "Ljava/io/Serializable;", "funcColorIndex", "", "function", "", "timeColorIndex", "timeFuncLocation", "(ILjava/util/List;II)V", "getFuncColorIndex", "()I", "setFuncColorIndex", "(I)V", "getFunction", "()Ljava/util/List;", "setFunction", "(Ljava/util/List;)V", "getTimeColorIndex", "setTimeColorIndex", "getTimeFuncLocation", "component1", "component2", "component3", "component4", "copy", "equals", "", FitnessActivities.OTHER, "", "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class Select implements Serializable {
        private int funcColorIndex;
        private List<Integer> function;
        private int timeColorIndex;
        private final int timeFuncLocation;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Select copy$default(Select select, int i, List list, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = select.funcColorIndex;
            }
            if ((i4 & 2) != 0) {
                list = select.function;
            }
            if ((i4 & 4) != 0) {
                i2 = select.timeColorIndex;
            }
            if ((i4 & 8) != 0) {
                i3 = select.timeFuncLocation;
            }
            return select.copy(i, list, i2, i3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getFuncColorIndex() {
            return this.funcColorIndex;
        }

        public final List<Integer> component2() {
            return this.function;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getTimeColorIndex() {
            return this.timeColorIndex;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getTimeFuncLocation() {
            return this.timeFuncLocation;
        }

        public final Select copy(int funcColorIndex, List<Integer> function, int timeColorIndex, int timeFuncLocation) {
            Intrinsics.checkParameterIsNotNull(function, "function");
            return new Select(funcColorIndex, function, timeColorIndex, timeFuncLocation);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Select)) {
                return false;
            }
            Select select = (Select) other;
            return this.funcColorIndex == select.funcColorIndex && Intrinsics.areEqual(this.function, select.function) && this.timeColorIndex == select.timeColorIndex && this.timeFuncLocation == select.timeFuncLocation;
        }

        public int hashCode() {
            int iHashCode = Integer.valueOf(this.funcColorIndex).hashCode() * 31;
            List<Integer> list = this.function;
            return ((((iHashCode + (list != null ? list.hashCode() : 0)) * 31) + Integer.valueOf(this.timeColorIndex).hashCode()) * 31) + Integer.valueOf(this.timeFuncLocation).hashCode();
        }

        public String toString() {
            return "Select(funcColorIndex=" + this.funcColorIndex + ", function=" + this.function + ", timeColorIndex=" + this.timeColorIndex + ", timeFuncLocation=" + this.timeFuncLocation + ")";
        }

        public Select(int i, List<Integer> function, int i2, int i3) {
            Intrinsics.checkParameterIsNotNull(function, "function");
            this.funcColorIndex = i;
            this.function = function;
            this.timeColorIndex = i2;
            this.timeFuncLocation = i3;
        }

        public final int getFuncColorIndex() {
            return this.funcColorIndex;
        }

        public final void setFuncColorIndex(int i) {
            this.funcColorIndex = i;
        }

        public final List<Integer> getFunction() {
            return this.function;
        }

        public final void setFunction(List<Integer> list) {
            Intrinsics.checkParameterIsNotNull(list, "<set-?>");
            this.function = list;
        }

        public final int getTimeColorIndex() {
            return this.timeColorIndex;
        }

        public final void setTimeColorIndex(int i) {
            this.timeColorIndex = i;
        }

        public final int getTimeFuncLocation() {
            return this.timeFuncLocation;
        }
    }
}