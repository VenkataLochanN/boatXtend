package com.ido.life.bean;

import com.ido.life.constants.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NotificationApp.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0006"}, d2 = {"isCalendar", "", "Lcom/ido/life/bean/NotificationApp;", "isEmail", "isMissCall", "isSms", "app_release"}, k = 2, mv = {1, 1, 16})
public final class NotificationAppKt {
    public static final boolean isSms(NotificationApp isSms) {
        Intrinsics.checkParameterIsNotNull(isSms, "$this$isSms");
        return Intrinsics.areEqual(Constants.AppPackage.SMS, isSms.getPkg());
    }

    public static final boolean isEmail(NotificationApp isEmail) {
        Intrinsics.checkParameterIsNotNull(isEmail, "$this$isEmail");
        return Intrinsics.areEqual("email", isEmail.getPkg());
    }

    public static final boolean isMissCall(NotificationApp isMissCall) {
        Intrinsics.checkParameterIsNotNull(isMissCall, "$this$isMissCall");
        return Intrinsics.areEqual(Constants.AppPackage.MISS_CALL, isMissCall.getPkg());
    }

    public static final boolean isCalendar(NotificationApp isCalendar) {
        Intrinsics.checkParameterIsNotNull(isCalendar, "$this$isCalendar");
        return Intrinsics.areEqual(Constants.AppPackage.CALENDAR, isCalendar.getPkg());
    }
}