package com.ido.life.syncdownload;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseLastestTaskListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b&\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0006"}, d2 = {"Lcom/ido/life/syncdownload/BaseLastestTaskListener;", "Lcom/ido/life/syncdownload/BaseDataDownloadTaskListener;", "userId", "", "(J)V", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public abstract class BaseLastestTaskListener extends BaseDataDownloadTaskListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Map<String, Map<Long, String>> mRecentDateMap = new LinkedHashMap();

    public static final Map<String, Map<Long, String>> getMRecentDateMap() {
        Companion companion = INSTANCE;
        return mRecentDateMap;
    }

    public BaseLastestTaskListener(long j) {
        super(j, 1);
        Map<String, Map<Long, String>> map = mRecentDateMap;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this::class.java.simpleName");
        map.put(simpleName, MapsKt.mutableMapOf(new Pair(Long.valueOf(j), "")));
    }

    /* JADX INFO: compiled from: BaseLastestTaskListener.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R4\u0010\u0003\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00050\u00040\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/ido/life/syncdownload/BaseLastestTaskListener$Companion;", "", "()V", "mRecentDateMap", "", "", "", "mRecentDateMap$annotations", "getMRecentDateMap", "()Ljava/util/Map;", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        @JvmStatic
        public static /* synthetic */ void mRecentDateMap$annotations() {
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Map<String, Map<Long, String>> getMRecentDateMap() {
            return BaseLastestTaskListener.mRecentDateMap;
        }
    }
}