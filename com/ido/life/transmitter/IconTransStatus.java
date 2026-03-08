package com.ido.life.transmitter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.AnnotationTarget;

/* JADX INFO: compiled from: IconTransStatus.kt */
/* JADX INFO: loaded from: classes3.dex */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0087\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Lcom/ido/life/transmitter/IconTransStatus;", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION})
@Retention(RetentionPolicy.SOURCE)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
public @interface IconTransStatus {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final int PIC_BIG_DOWNLOAD_SUCCESS = 2;
    public static final int PIC_MIDDLE_BIG_DOWNLOAD_SUCCESS = 6;
    public static final int PIC_MIDDLE_DOWNLOAD_SUCCESS = 4;
    public static final int PIC_NEW_ALL_DOWNLOAD_SUCCESS = 7;
    public static final int PIC_NOT_DOWNLOAD = 0;
    public static final int PIC_SMALL_BIG_DOWNLOAD_SUCCESS = 3;
    public static final int PIC_SMALL_DOWNLOAD_SUCCESS = 1;
    public static final int PIC_SMALL_MIDDLE_DOWNLOAD_SUCCESS = 5;

    /* JADX INFO: compiled from: IconTransStatus.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004J\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004J\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/ido/life/transmitter/IconTransStatus$Companion;", "", "()V", "PIC_BIG_DOWNLOAD_SUCCESS", "", "PIC_MIDDLE_BIG_DOWNLOAD_SUCCESS", "PIC_MIDDLE_DOWNLOAD_SUCCESS", "PIC_NEW_ALL_DOWNLOAD_SUCCESS", "PIC_NOT_DOWNLOAD", "PIC_SMALL_BIG_DOWNLOAD_SUCCESS", "PIC_SMALL_DOWNLOAD_SUCCESS", "PIC_SMALL_MIDDLE_DOWNLOAD_SUCCESS", "requireBig", "", "iconFlag", "requireMiddle", "requireSmall", "app_release"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int PIC_BIG_DOWNLOAD_SUCCESS = 2;
        public static final int PIC_MIDDLE_BIG_DOWNLOAD_SUCCESS = 6;
        public static final int PIC_MIDDLE_DOWNLOAD_SUCCESS = 4;
        public static final int PIC_NEW_ALL_DOWNLOAD_SUCCESS = 7;
        public static final int PIC_NOT_DOWNLOAD = 0;
        public static final int PIC_SMALL_BIG_DOWNLOAD_SUCCESS = 3;
        public static final int PIC_SMALL_DOWNLOAD_SUCCESS = 1;
        public static final int PIC_SMALL_MIDDLE_DOWNLOAD_SUCCESS = 5;

        public final boolean requireBig(int iconFlag) {
            return iconFlag == 0 || iconFlag == 1 || iconFlag == 4 || iconFlag == 5;
        }

        public final boolean requireMiddle(int iconFlag) {
            return iconFlag == 0 || iconFlag == 1 || iconFlag == 2 || iconFlag == 3;
        }

        public final boolean requireSmall(int iconFlag) {
            return iconFlag == 0 || iconFlag == 2 || iconFlag == 4 || iconFlag == 6;
        }

        private Companion() {
        }
    }
}