package com.ido.life.enums;

import com.autonavi.amap.mapcore.tools.GlMapUtil;
import com.boat.Xtend.two.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: StageInfoEnum.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u001d\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001BW\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\rJ\b\u0010\"\u001a\u0004\u0018\u00010\u0000R\u0013\u0010\u000e\u001a\u00020\u00038G¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u000b8G¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u00020\u00038G¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0013\u0010\u0016\u001a\u00020\u00038G¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0013\u0010\u0018\u001a\u00020\u00038G¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0010R\u0015\u0010\u001a\u001a\u0004\u0018\u00010\u000b8G¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0013\u0010\u001c\u001a\u00020\u00038G¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0010R\u0013\u0010\u001e\u001a\u00020\u00038G¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0010R\u0013\u0010 \u001a\u00020\u00038G¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0010j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'¨\u0006("}, d2 = {"Lcom/ido/life/enums/StageInfoEnum;", "", "titleResId", "", "recentStartDayCount", "recentEndDayCount", "recentValidDayCount", "pastStartDayCount", "pastEndDayCount", "pastValidDayCount", "recentStageDesc", "", "pastStageDesc", "(Ljava/lang/String;IIIIIIIILjava/lang/String;Ljava/lang/String;)V", "mPastEndDayCount", "getPastEndDayCount", "()I", "mPastStageDesc", "getPastStageDesc", "()Ljava/lang/String;", "mPastStartDayCount", "getPastStartDayCount", "mPastValidDayCount", "getPastValidDayCount", "mRecentEndDayCount", "getRecentEndDayCount", "mRecentStageDesc", "getRecentStageDesc", "mRecentStartDayCount", "getRecentStartDayCount", "mRecentValidDayCount", "getRecentValidDayCount", "mTitleResId", "getTitle", "getNextStage", "NODATA", "PRIMARY", "SECOND", "THIRD", "TERMINAL", "app_release"}, k = 1, mv = {1, 1, 16})
public enum StageInfoEnum {
    NODATA(R.string.fitness_stage_no_data, 0, 0, 0, 0, 0, 0, "无数据阶段", "无数据阶段"),
    PRIMARY(R.string.fitness_stage_primary, 1, 7, 4, 8, 28, 7, "初阶 最近：7天（第7至昨天）", "初阶 过去：21天（第28至8天）"),
    SECOND(R.string.fitness_stage_second, 1, 21, 11, 22, 81, 21, "二阶 最近：21天（第21至昨天）", "二阶 过去：60天（第81至22天）"),
    THIRD(R.string.fitness_stage_third, 1, 60, 30, 61, GlMapUtil.DEVICE_DISPLAY_DPI_MEDIAN, 60, "三阶 最近：60天（第60至昨天）", "三阶 过去：180天（第240至61天）"),
    TERMINAL(R.string.fitness_stage_terminal, 1, 90, 45, 91, 455, 300, "终阶 最近：90天（第90至昨天）", "终阶 过去：365天（第455至91天）");

    private final int mPastEndDayCount;
    private final String mPastStageDesc;
    private final int mPastStartDayCount;
    private final int mPastValidDayCount;
    private final int mRecentEndDayCount;
    private final String mRecentStageDesc;
    private final int mRecentStartDayCount;
    private final int mRecentValidDayCount;
    private final int mTitleResId;

    StageInfoEnum(int i, int i2, int i3, int i4, int i5, int i6, int i7, String str, String str2) {
        this.mTitleResId = i;
        this.mRecentStartDayCount = i2;
        this.mRecentEndDayCount = i3;
        this.mRecentValidDayCount = i4;
        this.mPastStartDayCount = i5;
        this.mPastEndDayCount = i6;
        this.mPastValidDayCount = i7;
        this.mRecentStageDesc = str;
        this.mPastStageDesc = str2;
    }

    /* synthetic */ StageInfoEnum(int i, int i2, int i3, int i4, int i5, int i6, int i7, String str, String str2, int i8, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, i4, i5, i6, i7, (i8 & 128) != 0 ? (String) null : str, (i8 & 256) != 0 ? (String) null : str2);
    }

    /* JADX INFO: renamed from: getTitle, reason: from getter */
    public final int getMTitleResId() {
        return this.mTitleResId;
    }

    /* JADX INFO: renamed from: getRecentStartDayCount, reason: from getter */
    public final int getMRecentStartDayCount() {
        return this.mRecentStartDayCount;
    }

    /* JADX INFO: renamed from: getRecentEndDayCount, reason: from getter */
    public final int getMRecentEndDayCount() {
        return this.mRecentEndDayCount;
    }

    /* JADX INFO: renamed from: getRecentValidDayCount, reason: from getter */
    public final int getMRecentValidDayCount() {
        return this.mRecentValidDayCount;
    }

    /* JADX INFO: renamed from: getPastStartDayCount, reason: from getter */
    public final int getMPastStartDayCount() {
        return this.mPastStartDayCount;
    }

    /* JADX INFO: renamed from: getPastEndDayCount, reason: from getter */
    public final int getMPastEndDayCount() {
        return this.mPastEndDayCount;
    }

    /* JADX INFO: renamed from: getPastValidDayCount, reason: from getter */
    public final int getMPastValidDayCount() {
        return this.mPastValidDayCount;
    }

    /* JADX INFO: renamed from: getRecentStageDesc, reason: from getter */
    public final String getMRecentStageDesc() {
        return this.mRecentStageDesc;
    }

    /* JADX INFO: renamed from: getPastStageDesc, reason: from getter */
    public final String getMPastStageDesc() {
        return this.mPastStageDesc;
    }

    public final StageInfoEnum getNextStage() {
        StageInfoEnum stageInfoEnum = this;
        if (stageInfoEnum == NODATA) {
            return PRIMARY;
        }
        if (stageInfoEnum == PRIMARY) {
            return SECOND;
        }
        if (stageInfoEnum == SECOND) {
            return THIRD;
        }
        if (stageInfoEnum == THIRD) {
            return TERMINAL;
        }
        return null;
    }
}