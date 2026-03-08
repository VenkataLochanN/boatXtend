package com.ido.life.constants;

import com.ido.life.module.device.presenter.BaseDialPresenter;
import com.realsil.sdk.core.bluetooth.impl.BluetoothHeadsetImpl;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;

/* JADX INFO: compiled from: WallpaperDialConstants.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\b"}, d2 = {"Lcom/ido/life/constants/WallpaperDialConstants;", "", "()V", "InstallStatus", "WidgetColor", "WidgetFunction", "WidgetLocation", "WidgetStatus", "app_release"}, k = 1, mv = {1, 1, 16})
public final class WallpaperDialConstants {

    /* JADX INFO: compiled from: WallpaperDialConstants.kt */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0087\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Lcom/ido/life/constants/WallpaperDialConstants$WidgetFunction;", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
    @kotlin.annotation.Target(allowedTargets = {AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface WidgetFunction {
        public static final int BATTERY = 6;
        public static final int CALORIE = 4;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;
        public static final int DISTANCE = 3;
        public static final int HEART_RATE = 5;
        public static final int STEP = 2;
        public static final int WEEK_DATE = 1;

        /* JADX INFO: compiled from: WallpaperDialConstants.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/ido/life/constants/WallpaperDialConstants$WidgetFunction$Companion;", "", "()V", BluetoothHeadsetImpl.VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT_BATTERY_LEVEL, "", "CALORIE", "DISTANCE", "HEART_RATE", "STEP", "WEEK_DATE", "app_release"}, k = 1, mv = {1, 1, 16})
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            public static final int BATTERY = 6;
            public static final int CALORIE = 4;
            public static final int DISTANCE = 3;
            public static final int HEART_RATE = 5;
            public static final int STEP = 2;
            public static final int WEEK_DATE = 1;

            private Companion() {
            }
        }
    }

    /* JADX INFO: compiled from: WallpaperDialConstants.kt */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0087\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Lcom/ido/life/constants/WallpaperDialConstants$WidgetLocation;", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
    @kotlin.annotation.Target(allowedTargets = {AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface WidgetLocation {
        public static final int CENTER = 5;
        public static final int CENTER_BOTTOM = 8;
        public static final int CENTER_TOP = 2;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;
        public static final int INVALID = 0;
        public static final int LEFT_BOTTOM = 7;
        public static final int LEFT_CENTER = 4;
        public static final int LEFT_TOP = 1;
        public static final int RIGHT_BOTTOM = 9;
        public static final int RIGHT_CENTER = 6;
        public static final int RIGHT_TOP = 3;

        /* JADX INFO: compiled from: WallpaperDialConstants.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/ido/life/constants/WallpaperDialConstants$WidgetLocation$Companion;", "", "()V", "CENTER", "", "CENTER_BOTTOM", "CENTER_TOP", "INVALID", "LEFT_BOTTOM", "LEFT_CENTER", "LEFT_TOP", "RIGHT_BOTTOM", "RIGHT_CENTER", "RIGHT_TOP", "app_release"}, k = 1, mv = {1, 1, 16})
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            public static final int CENTER = 5;
            public static final int CENTER_BOTTOM = 8;
            public static final int CENTER_TOP = 2;
            public static final int INVALID = 0;
            public static final int LEFT_BOTTOM = 7;
            public static final int LEFT_CENTER = 4;
            public static final int LEFT_TOP = 1;
            public static final int RIGHT_BOTTOM = 9;
            public static final int RIGHT_CENTER = 6;
            public static final int RIGHT_TOP = 3;

            private Companion() {
            }
        }
    }

    /* JADX INFO: compiled from: WallpaperDialConstants.kt */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0087\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Lcom/ido/life/constants/WallpaperDialConstants$WidgetStatus;", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
    @kotlin.annotation.Target(allowedTargets = {AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface WidgetStatus {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;
        public static final int HIDE = 1;
        public static final int SHOW = 0;

        /* JADX INFO: compiled from: WallpaperDialConstants.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/ido/life/constants/WallpaperDialConstants$WidgetStatus$Companion;", "", "()V", "HIDE", "", "SHOW", "app_release"}, k = 1, mv = {1, 1, 16})
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            public static final int HIDE = 1;
            public static final int SHOW = 0;

            private Companion() {
            }
        }
    }

    /* JADX INFO: compiled from: WallpaperDialConstants.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0014\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016¨\u0006\u0017"}, d2 = {"Lcom/ido/life/constants/WallpaperDialConstants$WidgetColor;", "", "color", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getColor", "()Ljava/lang/String;", "COLOR_BFA8FF", "COLOR_FF3333", "COLOR_826F60", "COLOR_FF8542", "COLOR_5CA4AC", "COLOR_7DC498", "COLOR_4694FF", "COLOR_4C68BE", "COLOR_718B83", "COLOR_B4C21D", "COLOR_CC8A89", "COLOR_FFD508", "COLOR_FDF19F", "COLOR_FFE0BF", "COLOR_C4DAE9", "COLOR_F2F2F2", "app_release"}, k = 1, mv = {1, 1, 16})
    public enum WidgetColor {
        COLOR_BFA8FF("#BFA8FF"),
        COLOR_FF3333("#FF3333"),
        COLOR_826F60("#826F60"),
        COLOR_FF8542("#FF8542"),
        COLOR_5CA4AC("#5CA4AC"),
        COLOR_7DC498("#7DC498"),
        COLOR_4694FF("#4694FF"),
        COLOR_4C68BE("#4C68BE"),
        COLOR_718B83("#718B83"),
        COLOR_B4C21D("#B4C21D"),
        COLOR_CC8A89("#CC8A89"),
        COLOR_FFD508("#FFD508"),
        COLOR_FDF19F("#FDF19F"),
        COLOR_FFE0BF("#FFE0BF"),
        COLOR_C4DAE9("#C4DAE9"),
        COLOR_F2F2F2("#F2F2F2");

        private final String color;

        WidgetColor(String str) {
            this.color = str;
        }

        public final String getColor() {
            return this.color;
        }
    }

    /* JADX INFO: compiled from: WallpaperDialConstants.kt */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0002\b\u0002\b\u0087\u0002\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002B\u0000¨\u0006\u0003"}, d2 = {"Lcom/ido/life/constants/WallpaperDialConstants$InstallStatus;", "", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
    @kotlin.annotation.Target(allowedTargets = {AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface InstallStatus {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = Companion.$$INSTANCE;
        public static final int DEFAULT = 0;
        public static final int DOWNLOADING = 5;
        public static final int DOWNLOAD_FAILED = 7;
        public static final int DOWNLOAD_SUCCESS = 6;
        public static final int INSTALLED_ALREADY = 4;
        public static final int INSTALLING = 1;
        public static final int INSTALL_FAILED = 2;
        public static final int INSTALL_SUCCESS = 3;

        /* JADX INFO: compiled from: WallpaperDialConstants.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/ido/life/constants/WallpaperDialConstants$InstallStatus$Companion;", "", "()V", BaseDialPresenter.DIAL_TYPE_BUILTIN, "", "DOWNLOADING", "DOWNLOAD_FAILED", "DOWNLOAD_SUCCESS", "INSTALLED_ALREADY", "INSTALLING", "INSTALL_FAILED", "INSTALL_SUCCESS", "app_release"}, k = 1, mv = {1, 1, 16})
        public static final class Companion {
            static final /* synthetic */ Companion $$INSTANCE = new Companion();
            public static final int DEFAULT = 0;
            public static final int DOWNLOADING = 5;
            public static final int DOWNLOAD_FAILED = 7;
            public static final int DOWNLOAD_SUCCESS = 6;
            public static final int INSTALLED_ALREADY = 4;
            public static final int INSTALLING = 1;
            public static final int INSTALL_FAILED = 2;
            public static final int INSTALL_SUCCESS = 3;

            private Companion() {
            }
        }
    }
}