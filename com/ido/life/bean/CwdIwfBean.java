package com.ido.life.bean;

import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CwdIwfBean.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u001a\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001:\u0001-BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u000fHÆ\u0003Ji\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u0010(\u001a\u00020\u00052\b\u0010)\u001a\u0004\u0018\u00010*HÖ\u0003J\t\u0010+\u001a\u00020\u000fHÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006."}, d2 = {"Lcom/ido/life/bean/CwdIwfBean;", "Ljava/io/Serializable;", "author", "", "battery", "", "bluetooth", "description", "disturb", "item", "", "Lcom/ido/life/bean/CwdIwfBean$Item;", AppMeasurementSdk.ConditionalUserProperty.NAME, "preview", "version", "", "(Ljava/lang/String;ZZLjava/lang/String;ZLjava/util/List;Ljava/lang/String;Ljava/lang/String;I)V", "getAuthor", "()Ljava/lang/String;", "getBattery", "()Z", "getBluetooth", "getDescription", "getDisturb", "getItem", "()Ljava/util/List;", "getName", "getPreview", "getVersion", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", FitnessActivities.OTHER, "", "hashCode", "toString", "Item", "app_release"}, k = 1, mv = {1, 1, 16})
public final /* data */ class CwdIwfBean implements Serializable {
    private final String author;
    private final boolean battery;
    private final boolean bluetooth;
    private final String description;
    private final boolean disturb;
    private final List<Item> item;
    private final String name;
    private final String preview;
    private final int version;

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getAuthor() {
        return this.author;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getBattery() {
        return this.battery;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getBluetooth() {
        return this.bluetooth;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getDisturb() {
        return this.disturb;
    }

    public final List<Item> component6() {
        return this.item;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getPreview() {
        return this.preview;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    public final int getVersion() {
        return this.version;
    }

    public final CwdIwfBean copy(String author, boolean battery, boolean bluetooth, String description, boolean disturb, List<Item> item, String name, String preview, int version) {
        Intrinsics.checkParameterIsNotNull(author, "author");
        Intrinsics.checkParameterIsNotNull(description, "description");
        Intrinsics.checkParameterIsNotNull(item, "item");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(preview, "preview");
        return new CwdIwfBean(author, battery, bluetooth, description, disturb, item, name, preview, version);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CwdIwfBean)) {
            return false;
        }
        CwdIwfBean cwdIwfBean = (CwdIwfBean) other;
        return Intrinsics.areEqual(this.author, cwdIwfBean.author) && this.battery == cwdIwfBean.battery && this.bluetooth == cwdIwfBean.bluetooth && Intrinsics.areEqual(this.description, cwdIwfBean.description) && this.disturb == cwdIwfBean.disturb && Intrinsics.areEqual(this.item, cwdIwfBean.item) && Intrinsics.areEqual(this.name, cwdIwfBean.name) && Intrinsics.areEqual(this.preview, cwdIwfBean.preview) && this.version == cwdIwfBean.version;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v3, types: [int] */
    /* JADX WARN: Type inference failed for: r2v8, types: [int] */
    public int hashCode() {
        String str = this.author;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        boolean z = this.battery;
        ?? r2 = z;
        if (z) {
            r2 = 1;
        }
        int i = (iHashCode + r2) * 31;
        boolean z2 = this.bluetooth;
        ?? r22 = z2;
        if (z2) {
            r22 = 1;
        }
        int i2 = (i + r22) * 31;
        String str2 = this.description;
        int iHashCode2 = (i2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        boolean z3 = this.disturb;
        ?? r23 = z3;
        if (z3) {
            r23 = 1;
        }
        int i3 = (iHashCode2 + r23) * 31;
        List<Item> list = this.item;
        int iHashCode3 = (i3 + (list != null ? list.hashCode() : 0)) * 31;
        String str3 = this.name;
        int iHashCode4 = (iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.preview;
        return ((iHashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31) + Integer.valueOf(this.version).hashCode();
    }

    public String toString() {
        return "CwdIwfBean(author=" + this.author + ", battery=" + this.battery + ", bluetooth=" + this.bluetooth + ", description=" + this.description + ", disturb=" + this.disturb + ", item=" + this.item + ", name=" + this.name + ", preview=" + this.preview + ", version=" + this.version + ")";
    }

    public CwdIwfBean(String author, boolean z, boolean z2, String description, boolean z3, List<Item> item, String name, String preview, int i) {
        Intrinsics.checkParameterIsNotNull(author, "author");
        Intrinsics.checkParameterIsNotNull(description, "description");
        Intrinsics.checkParameterIsNotNull(item, "item");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(preview, "preview");
        this.author = author;
        this.battery = z;
        this.bluetooth = z2;
        this.description = description;
        this.disturb = z3;
        this.item = item;
        this.name = name;
        this.preview = preview;
        this.version = i;
    }

    public final String getAuthor() {
        return this.author;
    }

    public final boolean getBattery() {
        return this.battery;
    }

    public final boolean getBluetooth() {
        return this.bluetooth;
    }

    public final String getDescription() {
        return this.description;
    }

    public final boolean getDisturb() {
        return this.disturb;
    }

    public final List<Item> getItem() {
        return this.item;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPreview() {
        return this.preview;
    }

    public final int getVersion() {
        return this.version;
    }

    /* JADX INFO: compiled from: CwdIwfBean.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b+\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\b\u0012\u0006\u0010\u000e\u001a\u00020\b¢\u0006\u0002\u0010\u000fJ\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\bHÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0006HÆ\u0003J\t\u0010,\u001a\u00020\bHÆ\u0003J\t\u0010-\u001a\u00020\bHÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\bHÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\t\u00101\u001a\u00020\bHÆ\u0003Jm\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\bHÆ\u0001J\u0013\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u0006H\u0096\u0002J\b\u00106\u001a\u00020\bH\u0016J\t\u00107\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\t\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001b\"\u0004\b\u001f\u0010\u001dR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0011R\u001a\u0010\u000b\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001b\"\u0004\b\"\u0010\u001dR\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0011R\u001a\u0010\r\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001b\"\u0004\b%\u0010\u001dR\u001a\u0010\u000e\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001b\"\u0004\b'\u0010\u001d¨\u00068"}, d2 = {"Lcom/ido/life/bean/CwdIwfBean$Item;", "Ljava/io/Serializable;", "bg", "", "fgcolor", "font", "", "fontnum", "", "h", "type", "w", "widget", "x", "y", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;IILjava/lang/String;ILjava/lang/String;II)V", "getBg", "()Ljava/lang/String;", "setBg", "(Ljava/lang/String;)V", "getFgcolor", "setFgcolor", "getFont", "()Ljava/lang/Object;", "setFont", "(Ljava/lang/Object;)V", "getFontnum", "()I", "setFontnum", "(I)V", "getH", "setH", "getType", "getW", "setW", "getWidget", "getX", "setX", "getY", "setY", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class Item implements Serializable {
        private String bg;
        private String fgcolor;
        private Object font;
        private int fontnum;
        private int h;
        private final String type;
        private int w;
        private final String widget;
        private int x;
        private int y;

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getBg() {
            return this.bg;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final int getY() {
            return this.y;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFgcolor() {
            return this.fgcolor;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Object getFont() {
            return this.font;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getFontnum() {
            return this.fontnum;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getH() {
            return this.h;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getType() {
            return this.type;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getW() {
            return this.w;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getWidget() {
            return this.widget;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final int getX() {
            return this.x;
        }

        public final Item copy(String bg, String fgcolor, Object font, int fontnum, int h2, String type, int w, String widget, int x, int y) {
            Intrinsics.checkParameterIsNotNull(bg, "bg");
            Intrinsics.checkParameterIsNotNull(fgcolor, "fgcolor");
            Intrinsics.checkParameterIsNotNull(font, "font");
            Intrinsics.checkParameterIsNotNull(type, "type");
            Intrinsics.checkParameterIsNotNull(widget, "widget");
            return new Item(bg, fgcolor, font, fontnum, h2, type, w, widget, x, y);
        }

        public String toString() {
            return "Item(bg=" + this.bg + ", fgcolor=" + this.fgcolor + ", font=" + this.font + ", fontnum=" + this.fontnum + ", h=" + this.h + ", type=" + this.type + ", w=" + this.w + ", widget=" + this.widget + ", x=" + this.x + ", y=" + this.y + ")";
        }

        public Item(String bg, String fgcolor, Object font, int i, int i2, String type, int i3, String widget, int i4, int i5) {
            Intrinsics.checkParameterIsNotNull(bg, "bg");
            Intrinsics.checkParameterIsNotNull(fgcolor, "fgcolor");
            Intrinsics.checkParameterIsNotNull(font, "font");
            Intrinsics.checkParameterIsNotNull(type, "type");
            Intrinsics.checkParameterIsNotNull(widget, "widget");
            this.bg = bg;
            this.fgcolor = fgcolor;
            this.font = font;
            this.fontnum = i;
            this.h = i2;
            this.type = type;
            this.w = i3;
            this.widget = widget;
            this.x = i4;
            this.y = i5;
        }

        public final String getBg() {
            return this.bg;
        }

        public final void setBg(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.bg = str;
        }

        public final String getFgcolor() {
            return this.fgcolor;
        }

        public final void setFgcolor(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.fgcolor = str;
        }

        public final Object getFont() {
            return this.font;
        }

        public final void setFont(Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, "<set-?>");
            this.font = obj;
        }

        public final int getFontnum() {
            return this.fontnum;
        }

        public final void setFontnum(int i) {
            this.fontnum = i;
        }

        public final int getH() {
            return this.h;
        }

        public final void setH(int i) {
            this.h = i;
        }

        public final String getType() {
            return this.type;
        }

        public final int getW() {
            return this.w;
        }

        public final void setW(int i) {
            this.w = i;
        }

        public final String getWidget() {
            return this.widget;
        }

        public final int getX() {
            return this.x;
        }

        public final void setX(int i) {
            this.x = i;
        }

        public final int getY() {
            return this.y;
        }

        public final void setY(int i) {
            this.y = i;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
                return false;
            }
            if (other != null) {
                return !(Intrinsics.areEqual(this.type, ((Item) other).type) ^ true);
            }
            throw new TypeCastException("null cannot be cast to non-null type com.ido.life.bean.CwdIwfBean.Item");
        }

        public int hashCode() {
            return this.type.hashCode();
        }
    }
}